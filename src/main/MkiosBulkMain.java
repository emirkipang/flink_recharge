package main;

import java.util.HashMap;
import java.util.Map;

import join.MkiosBulkLeftJoinLaccimaNULLOnly;
import join.MkiosBulkLeftJoinMDL;
import map.LaccimaFlatMap;
import map.MDLFlatMap;
import map.Output1BulkMkiosBulkFlatMap;
import map.MkiosBulkFlatMap;
import map.Output2BulkMkiosBulkFlatMap;
import map.TempMkiosBulkMap;
import model.Laccima;
import model.MDL;
import model.Output;
import model.MkiosBulk;
import model.OutputBulk;

import org.apache.flink.api.common.operators.base.JoinOperatorBase.JoinHint;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.core.fs.FileSystem.WriteMode;

import util.Constant;
import aggregate.OutputBulkGroupReducer;
import aggregate.OutputGroupReducer;
import filter.LaccimaFilter;
import filter.MDLFilter;
import filter.MkiosBulkFilter;
import filter.MkiosFilter;

public class MkiosBulkMain {
	private HashMap<String, DataSet<String>> dataset_inputs = new HashMap<String, DataSet<String>>();
	private ExecutionEnvironment env;
	private int proses_paralel;
	private int sink_paralel;
	private Configuration parameter;
	private String outputPath;

	// tuples variable
	private DataSet<MkiosBulk> MkiosBulk_tuples;

	private DataSet<Laccima> laccima_tuples;
	private DataSet<Laccima> laccima_tuples_4g;
	private DataSet<MDL> mdl_tuples;
	private DataSet<OutputBulk> output;

	public MkiosBulkMain(int proses_paralel, int sink_paralel, String outputPath) {
		this.env = ExecutionEnvironment.getExecutionEnvironment();
		this.parameter = new Configuration();
		this.outputPath = outputPath;

		this.proses_paralel = proses_paralel;
		this.sink_paralel = sink_paralel;
		this.env.setParallelism(this.proses_paralel);
		this.parameter.setBoolean("recursive.file.enumeration", true);

		// BasicConfigurator.configure(); //remove log warn

	}

	private String getOutputPath() {
		return this.outputPath;
	}

	private Configuration getParameter() {
		return this.parameter;
	}

	private ExecutionEnvironment getEnv() {
		return this.env;
	}

	public int getSink_paralel() {
		return this.sink_paralel;
	}

	private void setInput(HashMap<String, String> files) {

		for (Map.Entry<String, String> file : files.entrySet()) {
			dataset_inputs.put(
					file.getKey(),
					getEnv().readTextFile(file.getValue()).withParameters(
							getParameter()));
		}

	}

	public void processInput() {
		MkiosBulk_tuples = dataset_inputs.get("source_mkios").flatMap(
				new MkiosBulkFlatMap()).filter(new MkiosBulkFilter());
		laccima_tuples = dataset_inputs.get("ref_lacima").flatMap(
				new LaccimaFlatMap("3g")).filter(new LaccimaFilter());
		laccima_tuples_4g = dataset_inputs.get("ref_lacima_4g").flatMap(
				new LaccimaFlatMap("4g")).filter(new LaccimaFilter());
		mdl_tuples = dataset_inputs.get("ref_mdl").flatMap(new MDLFlatMap()).filter(new MDLFilter());

	}

	public void processAggregate() {
		// aggregate MkiosBulk
		// MkiosBulk_tuples = MkiosBulk_tuples.groupBy("Timestamp_R", "lacci",
		// "A_Party")
		// .reduceGroup(new MkiosBulkGroupReducer());

		/*********** OUTPUT 1 ***********/
		// 1. (+) LACCIMA 4G
		DataSet<OutputBulk> MkiosBulk_laccima4g = MkiosBulk_tuples
				.join(laccima_tuples_4g, JoinHint.REPARTITION_HASH_SECOND)
				.where("LACCI_subscriber_4g").equalTo("lacci")
				.flatMap(new Output1BulkMkiosBulkFlatMap());
		
		/*********** OUTPUT 2 ***********/
		// 2a. (-) LACCIMA 4G
		DataSet<MkiosBulk> MkiosBulk_laccima4g_null = MkiosBulk_tuples
				.leftOuterJoin(laccima_tuples_4g,
						JoinHint.REPARTITION_HASH_SECOND)
				.where("LACCI_subscriber_4g").equalTo("lacci")
				.with(new MkiosBulkLeftJoinLaccimaNULLOnly())
				.map(new TempMkiosBulkMap());
		
		// 2b. (-) LACCIMA 4G -> (+) LACCIMA 3G
		DataSet<OutputBulk> MkiosBulk_laccima4g_null_laccima3g = MkiosBulk_laccima4g_null
				.join(laccima_tuples)
				.where("LACCI_subscriber").equalTo("lacci")
				.flatMap(new Output1BulkMkiosBulkFlatMap());
		
		/*********** OUTPUT 3 ***********/
		// 3a. (-) LACCIMA 4G -> (-) LACCIMA 3G -> (+) MDL / UNKNOWN
		DataSet<MkiosBulk> MkiosBulk_laccima4g_null_laccima3g_null = MkiosBulk_laccima4g_null
				.leftOuterJoin(laccima_tuples, JoinHint.REPARTITION_HASH_SECOND)
				.where("LACCI_subscriber").equalTo("lacci")
				.with(new MkiosBulkLeftJoinLaccimaNULLOnly())
				.map(new TempMkiosBulkMap());

		DataSet<OutputBulk> MkiosBulk_mdl = MkiosBulk_laccima4g_null_laccima3g_null 
				.leftOuterJoin(mdl_tuples, JoinHint.REPARTITION_HASH_FIRST)
				.where("MSISDN").equalTo("msisdn")
				.with(new MkiosBulkLeftJoinMDL()).flatMap(new Output2BulkMkiosBulkFlatMap());

		output = MkiosBulk_laccima4g.union(MkiosBulk_laccima4g_null_laccima3g).union(MkiosBulk_mdl).groupBy(0, 1, 2, 3, 4, 5, 6,9,10,11)
				.reduceGroup(new OutputBulkGroupReducer("MKIOS"));


	}

	public void sink() throws Exception {
		
		output.writeAsCsv(getOutputPath(), "\n", "|", WriteMode.OVERWRITE)
				.setParallelism(getSink_paralel());
//		MkiosBulk_tuples.map(new Output5Map()).writeAsCsv(getOutputPath(), "\n", "|", WriteMode.OVERWRITE)
//		.setParallelism(getSink_paralel());

	}

	public static void main(String[] args) throws Exception {
		// set data input
		HashMap<String, String> files = new HashMap<String, String>();

		/** prod **/
		ParameterTool params = ParameterTool.fromArgs(args);

		int proses_paralel = params.getInt("slot");
		int sink_paralel = params.getInt("sink");
		String source = params.get("source");
		String laccima = params.get("laccima");
		String laccima_4g = params.get("laccima_4g");
		String mdl = params.get("mdl");
		String output = params.get("output");

		MkiosBulkMain main = new MkiosBulkMain(proses_paralel, sink_paralel, output);

		files.put("source_mkios", source);
		files.put("ref_lacima", laccima);
		files.put("ref_lacima_4g", laccima_4g);
		files.put("ref_mdl", mdl);

		/** dev **/
//		 int proses_paralel = 2;
//		 int sink_paralel = 1;
//		
//		 MkiosBulkMain main = new MkiosBulkMain(proses_paralel, sink_paralel, Constant.OUTPUT);
//		 files.put("source_mkios", Constant.FILE_MKIOS);
//		 files.put("ref_lacima", Constant.FILE_LACIMA);
//		 files.put("ref_lacima_4g", Constant.FILE_LACIMA_4G);
//		 files.put("ref_mdl", Constant.FILE_MDL);

		/****/

		main.setInput(files);

		main.processInput();

		main.processAggregate();

		main.sink();

		// System.out.println(env.getExecutionPlan());
		try {
			main.getEnv().execute("job flink MkiosBulk");
		} catch (Exception e) {
			// TODO Auto-generated catch blockF
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
}

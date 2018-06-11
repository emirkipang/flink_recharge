package main;

import java.util.HashMap;
import java.util.Map;

import join.OutputLeftJoinSplitCode;
import join.UrpLeftJoinLaccimaNULLOnly;
import join.UrpLeftJoinMDL;
import map.LaccimaFlatMap;
import map.MDLFlatMap;
import map.Output1UrpFlatMap;
import map.Output2UrpFlatMap;
import map.SplitCodeFlatMap;
import map.TempUrpMap;
import map.UrpFlatMap;
import model.Laccima;
import model.MDL;
import model.Output;
import model.SplitCode;
import model.Urp;

import org.apache.flink.api.common.operators.base.JoinOperatorBase.JoinHint;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.core.fs.FileSystem.WriteMode;

import util.Constant;
import aggregate.OutputGroupReducer;
import aggregate.OutputGroupReducer2;
import filter.LaccimaFilter;
import filter.MDLFilter;
import filter.UrpFilter;

public class UrpMain {
	private HashMap<String, DataSet<String>> dataset_inputs = new HashMap<String, DataSet<String>>();
	private ExecutionEnvironment env;
	private int proses_paralel;
	private int sink_paralel;
	private Configuration parameter;
	private String outputPath;

	// tuples variable
	private DataSet<Urp> Urp_tuples;

	private DataSet<Laccima> laccima_tuples;
	private DataSet<Laccima> laccima_tuples_4g;
	private DataSet<MDL> mdl_tuples;
	private DataSet<Output> output;
	private DataSet<SplitCode> splitcode_tuples;

	public UrpMain(int proses_paralel, int sink_paralel, String outputPath) {
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
		Urp_tuples = dataset_inputs.get("source_urp").flatMap(new UrpFlatMap())
				.filter(new UrpFilter());
		laccima_tuples = dataset_inputs.get("ref_lacima")
				.flatMap(new LaccimaFlatMap("3g")).filter(new LaccimaFilter());
		laccima_tuples_4g = dataset_inputs.get("ref_lacima_4g")
				.flatMap(new LaccimaFlatMap("4g")).filter(new LaccimaFilter());
		mdl_tuples = dataset_inputs.get("ref_mdl").flatMap(new MDLFlatMap())
				.filter(new MDLFilter());
		splitcode_tuples = dataset_inputs.get("ref_split_code").flatMap(
				new SplitCodeFlatMap());
	}

	public void processAggregate() {
		// aggregate Urp
		// Urp_tuples = Urp_tuples.groupBy("Timestamp_R", "lacci",
		// "A_Party")
		// .reduceGroup(new UrpGroupReducer());

		/*********** OUTPUT 1 ***********/
		// 1. (+) LACCIMA 4G
		DataSet<Output> urp_laccima4g = Urp_tuples
				.join(laccima_tuples_4g, JoinHint.REPARTITION_HASH_SECOND)
				.where("LACCI_4g").equalTo("lacci")
				.flatMap(new Output1UrpFlatMap());

		/*********** OUTPUT 2 ***********/
		// 2a. (-) LACCIMA 4G
		DataSet<Urp> urp_laccima4g_null = Urp_tuples
				.leftOuterJoin(laccima_tuples_4g,
						JoinHint.REPARTITION_HASH_SECOND).where("LACCI_4g")
				.equalTo("lacci").with(new UrpLeftJoinLaccimaNULLOnly())
				.map(new TempUrpMap());

		// 2b. (-) LACCIMA 4G -> (+) LACCIMA 3G
		DataSet<Output> urp_laccima4g_null_laccima3g = urp_laccima4g_null
				.join(laccima_tuples).where("LACCI").equalTo("lacci")
				.flatMap(new Output1UrpFlatMap());

		/*********** OUTPUT 3 ***********/
		// 3a. (-) LACCIMA 4G -> (-) LACCIMA 3G -> (+) MDL / UNKNOWN
		DataSet<Urp> urp_laccima4g_null_laccima3g_null = urp_laccima4g_null
				.leftOuterJoin(laccima_tuples, JoinHint.REPARTITION_HASH_SECOND)
				.where("LACCI").equalTo("lacci")
				.with(new UrpLeftJoinLaccimaNULLOnly()).map(new TempUrpMap());

		DataSet<Output> urp_mdl = urp_laccima4g_null_laccima3g_null
				.leftOuterJoin(mdl_tuples, JoinHint.REPARTITION_HASH_FIRST)
				.where("MSISDN").equalTo("msisdn").with(new UrpLeftJoinMDL())
				.flatMap(new Output2UrpFlatMap());

		output = urp_laccima4g.union(urp_laccima4g_null_laccima3g)
				.union(urp_mdl).groupBy(0, 1, 2, 3, 4, 5, 6)
				.reduceGroup(new OutputGroupReducer2())
				.leftOuterJoin(splitcode_tuples).where(0).equalTo("type")
				.with(new OutputLeftJoinSplitCode("URP"));

	}

	public void sink() throws Exception {

		output.writeAsCsv(getOutputPath(), "\n", "|", WriteMode.OVERWRITE)
				.setParallelism(getSink_paralel());
		// Urp_tuples.map(new Output5Map()).writeAsCsv(getOutputPath(), "\n",
		// "|", WriteMode.OVERWRITE)
		// .setParallelism(getSink_paralel());

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
		 String splitcode = params.get("splitcode");
		
		 UrpMain main = new UrpMain(proses_paralel, sink_paralel, output);
		
		 files.put("source_urp", source);
		 files.put("ref_lacima", laccima);
		 files.put("ref_lacima_4g", laccima_4g);
		 files.put("ref_mdl", mdl);
		 files.put("ref_split_code", splitcode);

		/** dev **/
//		int proses_paralel = 2;
//		int sink_paralel = 1;
//
//		UrpMain main = new UrpMain(proses_paralel, sink_paralel,
//				Constant.OUTPUT_URP);
//		files.put("source_urp", Constant.FILE_URP);
//		files.put("ref_lacima", Constant.FILE_LACIMA);
//		files.put("ref_lacima_4g", Constant.FILE_LACIMA_4G);
//		files.put("ref_mdl", Constant.FILE_MDL);
//		files.put("ref_split_code", Constant.FILE_SPLIT_CODE_URP);

		/****/

		main.setInput(files);

		main.processInput();

		main.processAggregate();

		main.sink();

		// System.out.println(env.getExecutionPlan());
		try {
			main.getEnv().execute("job flink Urp");
		} catch (Exception e) {
			// TODO Auto-generated catch blockF
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
}

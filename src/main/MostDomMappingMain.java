package main;

import java.util.HashMap;
import java.util.Map;

import join.MDLLeftJoinLaccima;
import join.MDLLeftJoinLaccimaNULLOnly;
import join.OutputLeftJoinSplitCode;
import join.UrpLeftJoinLaccimaNULLOnly;
import join.UrpLeftJoinMDL;
import map.LaccimaFlatMap;
import map.MDLFlatMap;
import map.MDLFlatMap2;
import map.Output1MDLMappingFlatMap;
import map.Output1UrpFlatMap;
import map.Output2UrpFlatMap;
import map.SplitCodeFlatMap;
import map.TempMDLMap;
import map.TempUrpMap;
import map.UrpFlatMap;
import model.Laccima;
import model.MDL;
import model.Output;
import model.Output1Tuple;
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

public class MostDomMappingMain {
	private HashMap<String, DataSet<String>> dataset_inputs = new HashMap<String, DataSet<String>>();
	private ExecutionEnvironment env;
	private int proses_paralel;
	private int sink_paralel;
	private Configuration parameter;
	private String outputPath;

	// tuples variable
	private DataSet<MDL> mdl_tuples;

	private DataSet<Laccima> laccima_tuples;
	private DataSet<Laccima> laccima_tuples_4g;
	private DataSet<Output1Tuple> output;

	public MostDomMappingMain(int proses_paralel, int sink_paralel, String outputPath) {
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
		laccima_tuples = dataset_inputs.get("ref_lacima")
				.flatMap(new LaccimaFlatMap("3g")).filter(new LaccimaFilter());
		laccima_tuples_4g = dataset_inputs.get("ref_lacima_4g")
				.flatMap(new LaccimaFlatMap("4g")).filter(new LaccimaFilter());
		mdl_tuples = dataset_inputs.get("ref_mdl").flatMap(new MDLFlatMap2())
			;
	}

	public void processAggregate() {
		// aggregate Urp
		// Urp_tuples = Urp_tuples.groupBy("Timestamp_R", "lacci",
		// "A_Party")
		// .reduceGroup(new UrpGroupReducer());
		
		//DataSet<Laccima> laccima = laccima_tuples.union(laccima_tuples_4g);
		
//		output = mdl_tuples
//				.leftOuterJoin(laccima,
//						JoinHint.REPARTITION_HASH_SECOND).where("lacci")
//				.equalTo("lacci").with(new MDLLeftJoinLaccima());		
		
		
		/*********** OUTPUT 1 ***********/
		// 1. (+) LACCIMA 4G
		DataSet<Output1Tuple> mdl_laccima4g = mdl_tuples
				.join(laccima_tuples_4g, JoinHint.REPARTITION_HASH_SECOND)
				.where("lacci_4g").equalTo("lacci")
				.flatMap(new Output1MDLMappingFlatMap());

		/*********** OUTPUT 2 ***********/
		// 2a. (-) LACCIMA 4G
		DataSet<MDL> mdl_laccima4g_null = mdl_tuples
				.leftOuterJoin(laccima_tuples_4g,
						JoinHint.REPARTITION_HASH_SECOND).where("lacci_4g")
				.equalTo("lacci").with(new MDLLeftJoinLaccimaNULLOnly())
				.map(new TempMDLMap());

		// 2b. (-) LACCIMA 4G -> (+) LACCIMA 3G/UNKNOWN
		DataSet<Output1Tuple> mdl_laccima4g_null_laccima3g = mdl_laccima4g_null
				.leftOuterJoin(laccima_tuples).where("lacci_3g").equalTo("lacci")
				.with(new MDLLeftJoinLaccima());
				
		output = mdl_laccima4g.union(mdl_laccima4g_null_laccima3g);
	}

	public void sink() throws Exception {		
		output.writeAsCsv(getOutputPath(), "\n", "|", WriteMode.OVERWRITE)
				.setParallelism(getSink_paralel());

	}

	public static void main(String[] args) throws Exception {
		// set data input
		HashMap<String, String> files = new HashMap<String, String>();

		/** prod **/
		 ParameterTool params = ParameterTool.fromArgs(args);
		
		 int proses_paralel = params.getInt("slot");
		 int sink_paralel = params.getInt("sink");
		 String laccima = params.get("laccima");
		 String laccima_4g = params.get("laccima_4g");
		 String mdl = params.get("mdl");
		 String output = params.get("output");
		
		 MostDomMappingMain main = new MostDomMappingMain(proses_paralel, sink_paralel, output);

		 files.put("ref_lacima", laccima);
		 files.put("ref_lacima_4g", laccima_4g);
		 files.put("ref_mdl", mdl);

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
			main.getEnv().execute("job mdl lacima");
		} catch (Exception e) {
			// TODO Auto-generated catch blockF
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
}

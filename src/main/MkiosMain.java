package main;

import java.util.HashMap;
import java.util.Map;

import join.MkiosLeftJoinLaccima;
import join.MkiosLeftJoinLaccimaNULLOnly;
import join.MkiosLeftJoinMDLNULLOnly;
import join.MkiosLeftJoinWhitelistNULLOnly;
import map.LaccimaFlatMap;
import map.MDLFlatMap;
import map.MkiosFlatMap;
import map.Output1MkiosFlatMap;
import map.Output2MkiosMap;
import map.TempMkiosMap;
import map.TempMkiosMap2;
import map.TempMkiosMap3;
import map.WhitelistFlatMap;
import model.Laccima;
import model.MDL;
import model.Mkios;
import model.Output;
import model.Whitelist;

import org.apache.flink.api.common.operators.base.JoinOperatorBase.JoinHint;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.core.fs.FileSystem.WriteMode;

import aggregate.OutputGroupReducer;
import filter.LaccimaFilter;
import filter.MDLFilter;
import filter.MkiosFilter;
import filter.WhitelistFilter;

public class MkiosMain {
	private HashMap<String, DataSet<String>> dataset_inputs = new HashMap<String, DataSet<String>>();
	private ExecutionEnvironment env;
	private int proses_paralel;
	private int sink_paralel;
	private Configuration parameter;
	private String outputPath;

	// tuples variable
	private DataSet<Mkios> mkios_tuples;

	private DataSet<Laccima> laccima_tuples;
	private DataSet<Laccima> laccima_tuples_4g;
	private DataSet<MDL> mdl_tuples;
	private DataSet<Whitelist> whitelist_tuples;

	private DataSet<Output> output;

	public MkiosMain(int proses_paralel, int sink_paralel, String outputPath) {
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
		mkios_tuples = dataset_inputs.get("source_mkios").flatMap(
				new MkiosFlatMap()).filter(new MkiosFilter());
		laccima_tuples = dataset_inputs.get("ref_lacima").flatMap(
				new LaccimaFlatMap("3g")).filter(new LaccimaFilter());
		laccima_tuples_4g = dataset_inputs.get("ref_lacima_4g").flatMap(
				new LaccimaFlatMap("4g")).filter(new LaccimaFilter());
		mdl_tuples = dataset_inputs.get("ref_mdl").flatMap(new MDLFlatMap()).filter(new MDLFilter());
		whitelist_tuples = dataset_inputs.get("ref_whitelist")
				.flatMap(new WhitelistFlatMap())
				.distinct("anumber", "bnumber", "combine")
				.filter(new WhitelistFilter());
	}

	public void processAggregate() {

		/*********** OUTPUT 1 ***********/
		// 1. (+) LACCIMA 4G
		DataSet<Output> mkios_laccima4g = mkios_tuples
				.join(laccima_tuples_4g, JoinHint.REPARTITION_HASH_SECOND)
				.where("LACCI_subscriber_4g").equalTo("lacci")
				.flatMap(new Output1MkiosFlatMap());

		/*********** OUTPUT 2 ***********/
		// 2a. (-) LACCIMA 4G
		DataSet<Mkios> mkios_laccima4g_null = mkios_tuples
				.leftOuterJoin(laccima_tuples_4g,
						JoinHint.REPARTITION_HASH_SECOND)
				.where("LACCI_subscriber_4g").equalTo("lacci")
				.with(new MkiosLeftJoinLaccimaNULLOnly())
				.map(new TempMkiosMap());

		// 2b. (-) LACCIMA 4G -> (+) LACCIMA 3G
		DataSet<Output> mkios_laccima4g_null_laccima3g = mkios_laccima4g_null
				.join(laccima_tuples, JoinHint.REPARTITION_HASH_SECOND)
				.where("LACCI_subscriber").equalTo("lacci")
				.flatMap(new Output1MkiosFlatMap());

		/*********** OUTPUT 3 ***********/
		// 3a. (-) LACCIMA 4G -> (-) LACCIMA 3G
		DataSet<Mkios> mkios_laccima4g_null_laccima3g_null = mkios_laccima4g_null
				.leftOuterJoin(laccima_tuples, JoinHint.REPARTITION_HASH_SECOND)
				.where("LACCI_subscriber").equalTo("lacci")
				.with(new MkiosLeftJoinLaccimaNULLOnly())
				.map(new TempMkiosMap());

		// 3b. (-) LACCIMA 4G -> (-) LACCIMA 3G -> (+) MDL
		DataSet<Output> mkios_mdl = mkios_laccima4g_null_laccima3g_null
				.join(mdl_tuples, JoinHint.REPARTITION_HASH_FIRST)
				.where("MSISDN").equalTo("msisdn").map(new Output2MkiosMap());

		/*********** OUTPUT 4 ***********/
		// 4a. (-) LACCIMA 4G -> (-) LACCIMA 3G -> (-) MDL
		DataSet<Mkios> mkios_mdl_null = mkios_laccima4g_null_laccima3g_null
				.leftOuterJoin(mdl_tuples, JoinHint.REPARTITION_HASH_FIRST)
				.where("MSISDN").equalTo("msisdn")
				.with(new MkiosLeftJoinMDLNULLOnly()).map(new TempMkiosMap2());

		// aman
		// DataSet<Output> mkios_mdl_null = mkios_laccima4g_null_laccima3g_null
		// .leftOuterJoin(mdl_tuples, JoinHint.REPARTITION_HASH_FIRST)
		// .where("MSISDN").equalTo("msisdn")
		// .with(new MkiosLeftJoinMDLNULLOnly()).map(new Output2Map());
		//
		//
		//
		// 4b. (-) LACCIMA -> (-) MDL -> (-) whitelist -> UNKNOWN
		DataSet<Output> mkios_mdl_null_whitelist_null = mkios_mdl_null
				.leftOuterJoin(whitelist_tuples).where("combine")
				.equalTo("combine").with(new MkiosLeftJoinWhitelistNULLOnly())
				.flatMap(new Output1MkiosFlatMap());
		//
		//

		/*********** OUTPUT 5 ***********/
		// 5a. (-) LACCIMA -> (-) MDL -> (+) whitelist
		DataSet<Mkios> whitelist = mkios_mdl_null.join(whitelist_tuples)
				.where("combine").equalTo("combine").map(new TempMkiosMap3());

		// 5b. whitelist -> (+) laccima 4g
		DataSet<Output> whitelist_laccima4g = whitelist.join(laccima_tuples_4g)
				.where("LACCI_RS_4g").equalTo("lacci")
				.flatMap(new Output1MkiosFlatMap());

		/*********** OUTPUT 6 ***********/
		// 6a. whitelist -> (-) laccima 4g
		DataSet<Mkios> whitelist_laccima4g_null = whitelist
				.leftOuterJoin(laccima_tuples_4g,
						JoinHint.REPARTITION_HASH_SECOND).where("LACCI_RS_4g")
				.equalTo("lacci").with(new MkiosLeftJoinLaccimaNULLOnly())
				.map(new TempMkiosMap());

		// 6b. (-) LACCIMA 4G -> (+) LACCIMA 3G / UNKNOWN
		DataSet<Output> whitelist_laccima4g_null_laccima3g_null = whitelist_laccima4g_null
				.leftOuterJoin(laccima_tuples, JoinHint.REPARTITION_HASH_SECOND)
				.where("LACCI_RS").equalTo("lacci")
				.with(new MkiosLeftJoinLaccima()).flatMap(new Output1MkiosFlatMap());

		/*********** OUTPUT FULL ***********/
		// 7. combine steps
		output = mkios_laccima4g.union(mkios_laccima4g_null_laccima3g)
				.union(mkios_mdl).union(mkios_mdl_null_whitelist_null)
				.union(whitelist_laccima4g)
				.union(whitelist_laccima4g_null_laccima3g_null)
				.groupBy(0, 1, 2, 3, 4, 5, 6)
				.reduceGroup(new OutputGroupReducer("MKIOS"));

	}

	public void sink() throws Exception {
		output.writeAsCsv(getOutputPath(), "\n", "|", WriteMode.OVERWRITE)
				.setParallelism(getSink_paralel());

		// whitelist_tuples.map(new Output3Map()).writeAsCsv(getOutputPath(),
		// "\n",
		// "|",
		// WriteMode.OVERWRITE)
		// .setParallelism(getSink_paralel());
		// mkios_tuples.map(new Output4Map()).writeAsCsv(getOutputPath(), "\n",
		// "|",
		// WriteMode.OVERWRITE)
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
		String whitelist = params.get("whitelist");

		MkiosMain main = new MkiosMain(proses_paralel, sink_paralel, output);

		files.put("source_mkios", source);
		files.put("ref_lacima", laccima);
		files.put("ref_lacima_4g", laccima_4g);
		files.put("ref_mdl", mdl);
		files.put("ref_whitelist", whitelist);

		/** dev **/
		// int proses_paralel = 2;
		// int sink_paralel = 1;
		//
		// MkiosMain main = new MkiosMain(proses_paralel, sink_paralel,
		// Constant.OUTPUT);
		// files.put("source_mkios", Constant.FILE_MKIOS);
		// files.put("ref_lacima", Constant.FILE_LACIMA);
		// files.put("ref_lacima_4g", Constant.FILE_LACIMA_4G);
		// files.put("ref_mdl", Constant.FILE_MDL);
		// files.put("ref_whitelist", Constant.FILE_WHITELIST);
		// /****/

		main.setInput(files);

		main.processInput();

		main.processAggregate();

		main.sink();

		// System.out.println(env.getExecutionPlan());
		try {
			main.getEnv().execute("job flink mkios");
		} catch (Exception e) {
			// TODO Auto-generated catch blockF
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
}

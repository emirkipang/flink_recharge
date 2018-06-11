package main;

import java.util.HashMap;
import java.util.Map;

import join.MkiosLeftJoinLaccima;
import join.MkiosLeftJoinLaccimaNULLOnly;
import join.MkiosLeftJoinListAD;
import join.MkiosLeftJoinMDLNULLOnly;
import join.MkiosLeftJoinWhitelistNULLOnly;
import join.OutputLeftJoinSplitCode;
import map.LaccimaFlatMap;
import map.ListADFlatMap;
import map.MDLFlatMap;
import map.MkiosFlatMap;
import map.Output1MkiosFlatMap;
import map.Output2MkiosMap;
import map.OutputMkiosANumberMap;
import map.SplitCodeFlatMap;
import map.TempMkiosMap;
import map.TempMkiosMap2;
import map.TempMkiosMap3;
import map.WhitelistFlatMap;
import model.Laccima;
import model.ListAD;
import model.MDL;
import model.Mkios;
import model.Output;
import model.Output1Tuple;
import model.SplitCode;
import model.Whitelist;

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
import filter.MkiosFilter;
import filter.WhitelistFilter;

//test
public class MkiosANumberMain {
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
	private DataSet<SplitCode> splitcode_tuples;
	private DataSet<ListAD> listad_tuples;

	private DataSet<Output> output;

	public MkiosANumberMain(int proses_paralel, int sink_paralel,
			String outputPath) {
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
		mkios_tuples = dataset_inputs.get("source_mkios")
				.flatMap(new MkiosFlatMap()).filter(new MkiosFilter());
		laccima_tuples = dataset_inputs.get("ref_lacima")
				.flatMap(new LaccimaFlatMap("3g")).filter(new LaccimaFilter());
		laccima_tuples_4g = dataset_inputs.get("ref_lacima_4g")
				.flatMap(new LaccimaFlatMap("4g")).filter(new LaccimaFilter());
		splitcode_tuples = dataset_inputs.get("ref_split_code").flatMap(
				new SplitCodeFlatMap());
		listad_tuples = dataset_inputs.get("ref_list_ad").flatMap(
				new ListADFlatMap());
	}

	public void processAggregate() {

		/*********** OUTPUT 1 ***********/
		// 1. (+) LACCIMA 4G
		DataSet<Output> mkios_laccima4g = mkios_tuples
				.join(laccima_tuples_4g, JoinHint.REPARTITION_HASH_SECOND)
				.where("LACCI_RS_4g").equalTo("lacci")
				.flatMap(new Output1MkiosFlatMap());

		/*********** OUTPUT 2 ***********/
		// 2a. (-) LACCIMA 4G
		DataSet<Mkios> mkios_laccima4g_null = mkios_tuples
				.leftOuterJoin(laccima_tuples_4g,
						JoinHint.REPARTITION_HASH_SECOND).where("LACCI_RS_4g")
				.equalTo("lacci").with(new MkiosLeftJoinLaccimaNULLOnly())
				.map(new TempMkiosMap());

		// 2b. (-) LACCIMA 4G -> (+) LACCIMA 3G
		DataSet<Output> mkios_laccima4g_null_laccima3g = mkios_laccima4g_null
				.join(laccima_tuples, JoinHint.REPARTITION_HASH_SECOND)
				.where("LACCI_RS").equalTo("lacci")
				.flatMap(new Output1MkiosFlatMap());

		/*********** OUTPUT 3 ***********/
		// 3a. (-) LACCIMA 4G -> (-) LACCIMA 3G
		DataSet<Mkios> mkios_laccima4g_null_laccima3g_null = mkios_laccima4g_null
				.leftOuterJoin(laccima_tuples, JoinHint.REPARTITION_HASH_SECOND)
				.where("LACCI_RS").equalTo("lacci")
				.with(new MkiosLeftJoinLaccimaNULLOnly())
				.map(new TempMkiosMap());

		// 3a. (-) LACCIMA 4G -> (-) LACCIMA 3G -> ListAD / UNKNOWN
		DataSet<Output> mkios_laccima4g_null_laccima3g_null_ADList = mkios_laccima4g_null_laccima3g_null
				.leftOuterJoin(listad_tuples).where("AD_MSISDN")
				.equalTo("LeadADMSISDN")
				.with(new MkiosLeftJoinListAD())
				.flatMap(new Output1MkiosFlatMap());
		
//		DataSet<Output1Tuple> mkios_laccima4g_null_laccima3g_null_ADList = mkios_laccima4g_null_laccima3g_null
//				.leftOuterJoin(listad_tuples).where("AD_MSISDN")
//				.equalTo("LeadADMSISDN")
//				.with(new MkiosLeftJoinListAD())
//				.map(new OutputMkiosANumberMap());
//		mkios_laccima4g_null_laccima3g_null_ADList.writeAsCsv(getOutputPath(), "\n", "|", WriteMode.OVERWRITE)
//		 .setParallelism(getSink_paralel());
		
		/*********** OUTPUT FULL ***********/
		 output =
				 mkios_laccima4g.union(mkios_laccima4g_null_laccima3g)
				 .union(mkios_laccima4g_null_laccima3g_null_ADList)
				 .groupBy(0, 1, 2, 3, 4, 5, 6)
				 .reduceGroup(new OutputGroupReducer2())
				 .leftOuterJoin(splitcode_tuples).where(0).equalTo("type")
				 .with(new OutputLeftJoinSplitCode("MKIOS"));

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
		 String source = params.get("source");
		 String laccima = params.get("laccima");
		 String laccima_4g = params.get("laccima_4g");
		 String mdl = params.get("mdl");
		 String output = params.get("output");
		 String whitelist = params.get("whitelist");
		 String splitcode = params.get("splitcode");
		 String listad = params.get("listad");
		
		 MkiosANumberMain main = new MkiosANumberMain(proses_paralel,
		 sink_paralel, output);
		
		 files.put("source_mkios", source);
		 files.put("ref_lacima", laccima);
		 files.put("ref_lacima_4g", laccima_4g);
		 files.put("ref_mdl", mdl);
		 files.put("ref_whitelist", whitelist);
		 files.put("ref_split_code", splitcode);
		 files.put("ref_list_ad", listad);

		/** dev **/
//		int proses_paralel = 2;
//		int sink_paralel = 1;
//
//		MkiosANumberMain main = new MkiosANumberMain(proses_paralel,
//				sink_paralel, Constant.OUTPUT_ANumber);
//		files.put("source_mkios", Constant.FILE_MKIOS);
//		files.put("ref_lacima", Constant.FILE_LACIMA);
//		files.put("ref_lacima_4g", Constant.FILE_LACIMA_4G);
//		files.put("ref_mdl", Constant.FILE_MDL);
//		files.put("ref_whitelist", Constant.FILE_WHITELIST);
//		files.put("ref_split_code", Constant.FILE_SPLIT_CODE_MKIOS);
//		files.put("ref_list_ad", Constant.FILE_LIST_AD);
		// /****/

		main.setInput(files);

		main.processInput();

		main.processAggregate();

		main.sink();

		// System.out.println(env.getExecutionPlan());
		try {
			main.getEnv().execute("job flink mkios A#");
		} catch (Exception e) {
			// TODO Auto-generated catch blockF
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
}

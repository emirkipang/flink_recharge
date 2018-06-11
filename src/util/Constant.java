package util;

public class Constant {
	public static final String BASEDIR = "D:/Data/GIA/output";

	public static final String FILE_MKIOS = BASEDIR + "/ref/in/mkios";
	public static final String FILE_URP = BASEDIR + "/ref/in_urp";
	public static final String FILE_LACIMA = BASEDIR
			+ "/ref/v_par_ref_lacima.csv";
	public static final String FILE_LACIMA_4G = BASEDIR
			+ "/ref/v_par_ref_lacima_4g.csv";
	public static final String FILE_MDL = BASEDIR + "/ref/mst_dmnt_201710.csv";
	public static final String FILE_WHITELIST = BASEDIR + "/ref/whitelist";
	public static final String FILE_SPLIT_CODE_MKIOS = BASEDIR + "/ref/split_code_mkios.csv";
	public static final String FILE_SPLIT_CODE_URP = BASEDIR + "/ref/split_code_urp.csv";
	public static final String FILE_LIST_AD = BASEDIR + "/ref/list_ad.csv";

	public static final String OUTPUT = BASEDIR
			+ "/out/mkios/recharge_mkios_summary.csv";
	public static final String OUTPUT_ANumber = BASEDIR
			+ "/out/mkios/recharge_mkios_summary_anumber.csv";
	public static final String OUTPUT_URP = BASEDIR
			+ "/out/urp/recharge_urp_summary.csv";
	// public static String BASEDIR = "/data/hnat_qsr/data";
	//
	// public static String FILE_MKIOS = BASEDIR
	// +
	// "/ref/HADOOP_2017-03-21_010117.000000_858618288_CHG_BJM_Pre_20170321140114_02530576_14.0_REG.csv";
	//
	// public static String FILE_LACIMA = BASEDIR +
	// "/ref/run_laccima_query.csv";
	// public static String FILE_LACIMA_4G = BASEDIR
	// + "/ref/run_laccima_4g_query.csv";
	// public static String FILE_MDL = BASEDIR + "/ref/run_mst_query.csv";
	//
	// public static String OUTPUT = BASEDIR + "/out/chg_summary.csv";

	public static final String[] MKIOS_REG = { "001" };
	public static final String[] MKIOS_VAS_FIX = { "013", "014", "015", "016",
			"062", "063", "075", "083", };
	public static final String[] MKIOS_VAS_BULK = { "076", "087", "089", "094" };

	public static final String[] URP_REG = { "001" };
	public static final String[] URP_VAS_FIX = { "066", "067", "088", "091",
			"104" };
	public static final String[] URP_VAS_BULK = { "037", "038", "039", "040",
			"041", "042", "043", "044", "045", "046", "047", "048", "049",
			"050", "051", "052", "053", "054", "055", "056", "057", "058",
			"074", "077", "079", "080", "081", "090", "093", "099", "100",
			"101", "102", "103" };
}

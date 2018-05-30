package util;

public class Constant {
	public static String BASEDIR = "D:/Data/GIA/output";

	public static String FILE_MKIOS = BASEDIR + "/ref/in";
	public static String FILE_URP = BASEDIR + "/ref/in_urp";
	public static String FILE_LACIMA = BASEDIR + "/ref/v_par_ref_lacima.csv";
	public static String FILE_LACIMA_4G = BASEDIR
			+ "/ref/v_par_ref_lacima_4g.csv";
	public static String FILE_MDL = BASEDIR + "/ref/mst_dmnt_201710.csv";
	public static String FILE_WHITELIST = BASEDIR + "/ref/whitelist";

	public static String OUTPUT = BASEDIR + "/out/mkios/mkios_recharge_summary.csv";
	public static String OUTPUT_URP = BASEDIR + "/out/mkios/mkios_urp_summary.csv";
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

	public static String joinRule(String in, int length) {
		int gap = length - in.length();

		if (gap != 0) {
			for (int i = 1; i <= gap; i++) {
				in = "0" + in;
			}
		}

		return in;
	}
	


}

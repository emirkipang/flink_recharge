package map;

import model.ListAD;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.util.Collector;

import util.Constant;
import util.Helper;

public class ListADFlatMap implements FlatMapFunction<String, ListAD> {

	/**
	 * 
* 
*/
	private static final long serialVersionUID = 1L;
	



	@Override
	public void flatMap(String in, Collector<ListAD> out) throws Exception {
		// TODO Auto-generated method stub
		String[] lines = in.split("\n");

		for (String line : lines) {

			String[] items = line.split("\\|", -1);

			
			String No = items[0];
			String Regional = items[1];
			String Branch = items[2];
			String SubBranch = items[3];
			String Cluster = items[4];
			String LeadADName = items[5];
			long LeadADMSISDN = Long.parseLong("62" + Helper.isEmpty(items[6].replaceAll("[^0-9]", "")));;
			String LeadADCode = items[7];
			String Area = "UNKNOWN";
				
			if (Regional.equalsIgnoreCase("SUMBAGSEL")
					|| Regional.equalsIgnoreCase("SUMBAGUT")
					|| Regional.equalsIgnoreCase("SUMBAGTENG")) {
				Area = "AREA 1";
			} else if (Regional.equalsIgnoreCase("WESTERN JABOTABEK")
					|| Regional.equalsIgnoreCase("EASTERN JABOTABEK")
					|| Regional.equalsIgnoreCase("CENTRAL JABOTABEK")
					|| Regional.equalsIgnoreCase("JABAR")
					|| Regional.equalsIgnoreCase("JAWA BARAT")
					|| Regional.equalsIgnoreCase("SAD REGIONAL")) {
				Area = "AREA 2";
			} else if (Regional.equalsIgnoreCase("JATENG-DIY")
					|| Regional.equalsIgnoreCase("JATENG")
					|| Regional.equalsIgnoreCase("JATIM")
					|| Regional.equalsIgnoreCase("BALI NUSRA")) {
				Area = "AREA 3";
			} else if (Regional.equalsIgnoreCase("KALIMANTAN")
					|| Regional.equalsIgnoreCase("SULAWESI")
					|| Regional.equalsIgnoreCase("PAPUA")
					|| Regional.equalsIgnoreCase("MALUKU DAN PAPUA")
					|| Regional.equalsIgnoreCase("PUMA")) {
				Area = "AREA 4";
			}

			out.collect(new ListAD(No, Regional, Branch,
					SubBranch, Cluster, LeadADName, LeadADMSISDN, LeadADCode, Area));

		}

	}

}

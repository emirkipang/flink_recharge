package map;

import model.MDL;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.util.Collector;

import util.Helper;

public class MDLFlatMap implements FlatMapFunction<String, MDL> {

	/**
* 
*/
	private static final long serialVersionUID = 1L;

	@Override
	public void flatMap(String in, Collector<MDL> out) throws Exception {
		// TODO Auto-generated method stub
		String[] lines = in.split("\n");

		for (String line : lines) {

			String[] items = line.split("\\|", -1);
			String strArea = "";

			if (!items[0].contains("brrc_mst_dmnt")) {
				if (items[4].equalsIgnoreCase("BALINUSRA")){
					items[4] = "BALI NUSRA";
				}
				
				if (items[4].equalsIgnoreCase("PUMA")){
					items[4] = "MALUKU DAN PAPUA";
				}
				
				if (items[4].equalsIgnoreCase("JATENG")){
					items[4] = "JATENG-DIY";
				}
				
				if (items[4].equalsIgnoreCase("JABAR")){
					items[4] = "JAWA BARAT";
				}


				if (items[4].equalsIgnoreCase("SUMBAGSEL")
						|| items[4].equalsIgnoreCase("SUMBAGUT")
						|| items[4].equalsIgnoreCase("SUMBAGTENG")) {
					strArea = "AREA 1";
				} else if (items[4].equalsIgnoreCase("WESTERN JABOTABEK")
						|| items[4].equalsIgnoreCase("EASTERN JABOTABEK")
						|| items[4].equalsIgnoreCase("CENTRAL JABOTABEK")
						|| items[4].equalsIgnoreCase("JAWA BARAT")
						|| items[4].equalsIgnoreCase("SAD REGIONAL")) {
					strArea = "AREA 2";
				} else if (items[4].equalsIgnoreCase("JATENG-DIY")
						|| items[4].equalsIgnoreCase("JATIM")
						|| items[4].equalsIgnoreCase("BALI NUSRA")) {
					strArea = "AREA 3";
				} else if (items[4].equalsIgnoreCase("KALIMANTAN")
						|| items[4].equalsIgnoreCase("SULAWESI")
						|| items[4].equalsIgnoreCase("PAPUA")
						|| items[4].equalsIgnoreCase("MALUKU DAN PAPUA")) {
					strArea = "AREA 4";
				}
								
				long msisdn = Long.parseLong(Helper.isEmpty(items[0].replaceAll("[^0-9]", "")));

				out.collect(new MDL(msisdn, items[3].toUpperCase(), items[4]
						.toUpperCase(), strArea.toUpperCase(), items[6]
						.toUpperCase(), items[7].toUpperCase()));
			}

		}

	}

}

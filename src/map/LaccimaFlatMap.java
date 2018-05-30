package map;

import model.Laccima;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.util.Collector;

import util.Constant;
import util.Helper;

public class LaccimaFlatMap implements FlatMapFunction<String, Laccima> {

	/**
* 
*/
	private static final long serialVersionUID = 1L;
	private String node;
	
	public LaccimaFlatMap(String node){
		this.node = node;
	}
	
	public String getNode() {
		return node;
	}

	public void setNode(String node) {
		this.node = node;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	@Override
	public void flatMap(String in, Collector<Laccima> out) throws Exception {
		// TODO Auto-generated method stub
		String[] lines = in.split("\n");

		for (String line : lines) {

			String[] items = line.split("\\|", -1);
			String strArea = "";
			
			String lac = null,ci = null, lacci;
			
			if(getNode().equals("3g")){
				lac = Constant.joinRule(Helper.isEmpty(items[0]),5);
				ci = Constant.joinRule(Helper.isEmpty(items[1]),5);
			}else if(getNode().equals("4g")){				
				lac = Constant.joinRule(Helper.isEmpty(items[0]),7);
				ci = Constant.joinRule(Helper.isEmpty(items[1]),3);
			} 
			lacci = lac+"|"+ci;
			
			if (items[17].equalsIgnoreCase("BALINUSRA")){
				items[17] = "BALI NUSRA";
			}
			
			if (items[17].equalsIgnoreCase("PUMA")){
				items[17] = "MALUKU DAN PAPUA";
			}

			if (items[17].equalsIgnoreCase("SUMBAGSEL")
					|| items[17].equalsIgnoreCase("SUMBAGUT")
					|| items[17].equalsIgnoreCase("SUMBAGTENG")) {
				strArea = "AREA 1";
			} else if (items[17].equalsIgnoreCase("WESTERN JABOTABEK")
					|| items[17].equalsIgnoreCase("EASTERN JABOTABEK")
					|| items[17].equalsIgnoreCase("CENTRAL JABOTABEK")
					|| items[17].equalsIgnoreCase("JABAR")
					|| items[17].equalsIgnoreCase("SAD REGIONAL")) {
				strArea = "AREA 2";
			} else if (items[17].equalsIgnoreCase("JATENG-DIY")
					|| items[17].equalsIgnoreCase("JATENG")
					|| items[17].equalsIgnoreCase("JATIM")
					|| items[17].equalsIgnoreCase("BALI NUSRA")) {
				strArea = "AREA 3";
			} else if (items[17].equalsIgnoreCase("KALIMANTAN")
					|| items[17].equalsIgnoreCase("SULAWESI")
					|| items[17].equalsIgnoreCase("PAPUA")
					|| items[17].equalsIgnoreCase("MALUKU DAN PAPUA")
					|| items[17].equalsIgnoreCase("PUMA")) {
				strArea = "AREA 4";
			}

			out.collect(new Laccima(lacci, items[17], items[18],
					items[19], items[22], strArea));

		}

	}

}

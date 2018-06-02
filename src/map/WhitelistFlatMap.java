package map;

import model.Whitelist;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.util.Collector;

import util.Constant;
import util.Helper;

public class WhitelistFlatMap implements FlatMapFunction<String, Whitelist> {

	/**
* 
*/
	private static final long serialVersionUID = 1L;

	// format : 20180602|81226300039|081390898306
	
	@Override
	public void flatMap(String in, Collector<Whitelist> out) throws Exception {
		// TODO Auto-generated method stub
		String[] lines = in.split("\n");

		for (String line : lines) {
			String[] items = line.split("\\|", -1);

			//String anumber = Helper.isEmpty(items[0]);
			//String dt_dt = Helper.isEmpty(items[0]);
			String anumber = Helper.isEmpty("62"+items[1]);
			String bnumber = Helper.isEmpty("62"+items[2].substring(1, items[2].length()));
			String combine = Helper.joinRule(anumber+"|"+bnumber,28);
			
			out.collect(new Whitelist(anumber, bnumber, combine));

		}

	}

}

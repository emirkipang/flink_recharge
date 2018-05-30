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

	@Override
	public void flatMap(String in, Collector<Whitelist> out) throws Exception {
		// TODO Auto-generated method stub
		String[] lines = in.split("\n");

		for (String line : lines) {
			String[] items = line.split("\\s+", -1);

			String anumber = Helper.isEmpty(items[0]);
			String bnumber = Helper.isEmpty("62"+items[1]);
			String combine = Constant.joinRule(anumber+"|"+bnumber,28);

			out.collect(new Whitelist(anumber, bnumber, combine));

		}

	}

}

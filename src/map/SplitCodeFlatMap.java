package map;

import model.SplitCode;
import model.Whitelist;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.util.Collector;

import util.Constant;
import util.Helper;

public class SplitCodeFlatMap implements FlatMapFunction<String, SplitCode> {

	/**
* 
*/
	private static final long serialVersionUID = 1L;

	// format : 20180602|81226300039|081390898306
	
	@Override
	public void flatMap(String in, Collector<SplitCode> out) throws Exception {
		// TODO Auto-generated method stub
		String[] lines = in.split("\n");

		for (String line : lines) {
			String[] items = line.split("\\|", -1);
			
			String type = items[0];
			String channel = items[1];

			
			out.collect(new SplitCode(type, channel));

		}

	}

}

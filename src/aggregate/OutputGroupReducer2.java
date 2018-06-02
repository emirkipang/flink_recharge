package aggregate;

import model.Output;

import org.apache.flink.api.common.functions.GroupReduceFunction;
import org.apache.flink.util.Collector;

public class OutputGroupReducer2 implements GroupReduceFunction<Output, Output> {

	private static final long serialVersionUID = 3761511818341655617L;

	@Override
	public void reduce(Iterable<Output> in, Collector<Output> out)
			throws Exception {
		String key1 = "";
		String key2 = "";
		String key3 = "";
		String key4 = "";
		String key5 = "";
		String key6 = "";
		String key7 = "";
		Double sum1 = 0.0;
		Double sum2 = 0.0;

		for (Output output : in) {
			key1 = output.f0;
			key2 = output.f1;
			key3 = output.f2;
			key4 = output.f3;
			key5 = output.f4;
			key6 = output.f5;
			key7 = output.f6;
			sum1 += Double.parseDouble(output.f7);
			sum2 += Double.parseDouble(output.f8);
		}

		Output output = new Output();
		output.setFields(key1, key2, key3, key4, key5, key6, key7,
				Double.toString(sum1), Double.toString(sum2));

		out.collect(output);
	}

}

package join;

import model.Laccima;
import model.Mkios;
import model.Output;
import model.SplitCode;

import org.apache.flink.api.common.functions.FlatJoinFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.util.Collector;

public class OutputLeftJoinSplitCode implements
		FlatJoinFunction<Output, SplitCode, Output> {
	private String channel;

	public OutputLeftJoinSplitCode(String channel) {
		this.channel = channel;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void join(Output leftElem, SplitCode rightElem, Collector<Output> out)
			throws Exception {
		// TODO Auto-generated method stub
		if (rightElem == null) { 
			//leftElem.f0 = leftElem.f0 + "_" + this.channel;
			leftElem.f0 = "MKIOS_VAS_BULK";
			out.collect(leftElem);
		} else {
			leftElem.f0 = rightElem.getChannel();
			out.collect(leftElem);
		}

	}

}

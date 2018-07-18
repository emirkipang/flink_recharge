package join;

import model.Laccima;
import model.MDL;

import org.apache.flink.api.common.functions.FlatJoinFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.util.Collector;

public class MDLLeftJoinLaccimaNULLOnly implements
		FlatJoinFunction<MDL, Laccima, Tuple2<MDL, Laccima>> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void join(MDL leftElem, Laccima rightElem,
			Collector<Tuple2<MDL, Laccima>> out) throws Exception {
		// TODO Auto-generated method stub
		if (rightElem == null) // MDLLaccimaLeftOuterJoinMstDmnt
			out.collect(new Tuple2<MDL, Laccima>(leftElem, new Laccima()));

	}

}

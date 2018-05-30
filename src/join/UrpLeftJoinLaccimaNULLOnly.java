package join;

import model.Laccima;
import model.Urp;

import org.apache.flink.api.common.functions.FlatJoinFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.util.Collector;

public class UrpLeftJoinLaccimaNULLOnly implements
		FlatJoinFunction<Urp, Laccima, Tuple2<Urp, Laccima>> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void join(Urp leftElem, Laccima rightElem,
			Collector<Tuple2<Urp, Laccima>> out) throws Exception {
		// TODO Auto-generated method stub
		if (rightElem == null) // UrpLaccimaLeftOuterJoinMstDmnt
			out.collect(new Tuple2<Urp, Laccima>(leftElem, new Laccima()));

	}

}

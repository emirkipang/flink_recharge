package join;

import model.Laccima;
import model.Mkios;

import org.apache.flink.api.common.functions.FlatJoinFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.util.Collector;

public class MkiosLeftJoinLaccimaNULLOnly implements
		FlatJoinFunction<Mkios, Laccima, Tuple2<Mkios, Laccima>> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void join(Mkios leftElem, Laccima rightElem,
			Collector<Tuple2<Mkios, Laccima>> out) throws Exception {
		// TODO Auto-generated method stub
		if (rightElem == null) // MkiosLaccimaLeftOuterJoinMstDmnt
			out.collect(new Tuple2<Mkios, Laccima>(leftElem, new Laccima()));

	}

}

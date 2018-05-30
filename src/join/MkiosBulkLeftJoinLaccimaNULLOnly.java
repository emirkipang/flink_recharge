package join;

import model.Laccima;
import model.MkiosBulk;

import org.apache.flink.api.common.functions.FlatJoinFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.util.Collector;

public class MkiosBulkLeftJoinLaccimaNULLOnly implements
		FlatJoinFunction<MkiosBulk, Laccima, Tuple2<MkiosBulk, Laccima>> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void join(MkiosBulk leftElem, Laccima rightElem,
			Collector<Tuple2<MkiosBulk, Laccima>> out) throws Exception {
		// TODO Auto-generated method stub
		if (rightElem == null) // MkiosBulkLaccimaLeftOuterJoinMstDmnt
			out.collect(new Tuple2<MkiosBulk, Laccima>(leftElem, new Laccima()));

	}

}

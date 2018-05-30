package join;

import model.Laccima;
import model.Mkios;
import model.Whitelist;

import org.apache.flink.api.common.functions.FlatJoinFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.util.Collector;

public class MkiosLeftJoinWhitelistNULLOnly implements
		FlatJoinFunction<Mkios, Whitelist, Tuple2<Mkios, Laccima>> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void join(Mkios leftElem, Whitelist rightElem,
			Collector<Tuple2<Mkios, Laccima>> out) throws Exception {
		// TODO Auto-generated method stub
		if (rightElem == null) // MkiosWhitelistLeftOuterJoinMstDmnt
			out.collect(new Tuple2<Mkios, Laccima>(leftElem, new Laccima()));
		
		
	}

}

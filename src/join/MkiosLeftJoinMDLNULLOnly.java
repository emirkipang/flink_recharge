package join;

import model.MDL;
import model.Mkios;

import org.apache.flink.api.common.functions.FlatJoinFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.util.Collector;

public class MkiosLeftJoinMDLNULLOnly implements
		FlatJoinFunction<Mkios, MDL, Tuple2<Mkios, MDL>> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void join(Mkios leftElem, MDL rightElem,
			Collector<Tuple2<Mkios, MDL>> out) throws Exception {
		// TODO Auto-generated method stub
		if (rightElem == null) // MkiosMDLLeftOuterJoinMstDmnt
			out.collect(new Tuple2<Mkios, MDL>(leftElem, new MDL()));
	

	}

}

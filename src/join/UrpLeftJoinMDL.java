package join;

import model.MDL;
import model.Urp;

import org.apache.flink.api.common.functions.FlatJoinFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.util.Collector;

public class UrpLeftJoinMDL implements
		FlatJoinFunction<Urp, MDL, Tuple2<Urp, MDL>> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void join(Urp leftElem, MDL rightElem,
			Collector<Tuple2<Urp, MDL>> out) throws Exception {
		// TODO Auto-generated method stub
		if (rightElem == null) // UrpMDLLeftOuterJoinMstDmnt
			out.collect(new Tuple2<Urp, MDL>(leftElem, new MDL()));
		else
			out.collect(new Tuple2<Urp, MDL>(leftElem, rightElem));

	}

}

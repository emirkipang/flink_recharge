package join;

import model.MDL;
import model.MkiosBulk;

import org.apache.flink.api.common.functions.FlatJoinFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.util.Collector;

public class MkiosBulkLeftJoinMDL implements
		FlatJoinFunction<MkiosBulk, MDL, Tuple2<MkiosBulk, MDL>> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void join(MkiosBulk leftElem, MDL rightElem,
			Collector<Tuple2<MkiosBulk, MDL>> out) throws Exception {
		// TODO Auto-generated method stub
		if (rightElem == null) // MkiosBulkMDLLeftOuterJoinMstDmnt
			out.collect(new Tuple2<MkiosBulk, MDL>(leftElem, new MDL()));
		else
			out.collect(new Tuple2<MkiosBulk, MDL>(leftElem, rightElem));

	}

}

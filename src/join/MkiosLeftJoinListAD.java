package join;

import model.Laccima;
import model.ListAD;
import model.Mkios;

import org.apache.flink.api.common.functions.FlatJoinFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.util.Collector;

public class MkiosLeftJoinListAD implements
		FlatJoinFunction<Mkios, ListAD, Tuple2<Mkios, Laccima>> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void join(Mkios leftElem, ListAD rightElem,
			Collector<Tuple2<Mkios, Laccima>> out) throws Exception {
		// TODO Auto-generated method stub
		if (rightElem == null) // MkiosLaccimaLeftOuterJoinMstDmnt
			out.collect(new Tuple2<Mkios, Laccima>(leftElem, new Laccima()));
		else {
			Laccima listAD = new Laccima();
			listAD.setArea(rightElem.getArea());
			listAD.setRegional_channel(rightElem.getRegional());
			listAD.setBranch(rightElem.getBranch());
			listAD.setSubbranch(rightElem.getSubBranch());
			listAD.setCluster(rightElem.getCluster());
			
			out.collect(new Tuple2<Mkios, Laccima>(leftElem, listAD));
		}
			
	}

}

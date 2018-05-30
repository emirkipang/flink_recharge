package map;


import model.Laccima;
import model.Output;
import model.Urp;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.util.Collector;


public class Output1UrpFlatMap implements FlatMapFunction<Tuple2<Urp, Laccima>, Output> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Override
	public void flatMap(Tuple2<Urp, Laccima> in, Collector<Output> out)
			throws Exception {
		// TODO Auto-generated method stub
		Output output = new Output();

		// 20170718112310

		output.setFields("", in.f0.getTransactionTime(), in.f1.getArea(),
				in.f1.getRegional_channel(), in.f1.getBranch(),
				in.f1.getSubbranch(), in.f1.getCluster(),
				in.f0.getRechargeNominal(), in.f0.getTrx());
		
		out.collect(output);
		
	}
}

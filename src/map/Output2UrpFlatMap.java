package map;


import model.MDL;
import model.Output;
import model.Urp;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.util.Collector;



public class Output2UrpFlatMap implements FlatMapFunction<Tuple2<Urp, MDL>, Output> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Override
	public void flatMap(Tuple2<Urp, MDL> in, Collector<Output> out)
			throws Exception {
		// TODO Auto-generated method stub
		Output output = new Output();

		// 20170718112310

		output.setFields("", in.f0.getTransactionTime(), in.f1.getArea_name(),
				in.f1.getRegion_name(), in.f1.getBranch(),
				in.f1.getSub_branch(), in.f1.getClust(),
				in.f0.getRechargeNominal(), in.f0.getTrx());
		out.collect(output);

	}
}

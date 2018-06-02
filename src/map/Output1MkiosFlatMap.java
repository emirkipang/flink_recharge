package map;

import model.Laccima;
import model.Mkios;
import model.Output;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.util.Collector;


public class Output1MkiosFlatMap implements
		FlatMapFunction<Tuple2<Mkios, Laccima>, Output> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void flatMap(Tuple2<Mkios, Laccima> in, Collector<Output> out)
			throws Exception {
		// TODO Auto-generated method stub
		Output output = new Output();

		// 20170718112310

		output.setFields(in.f0.getChannel(), in.f0.getRecharge_Date(), in.f1.getArea(),
				in.f1.getRegional_channel(), in.f1.getBranch(),
				in.f1.getSubbranch(), in.f1.getCluster(),
				in.f0.getAcceptor_actual_increase_money(), in.f0.getTrx());

		out.collect(output);
	}
}

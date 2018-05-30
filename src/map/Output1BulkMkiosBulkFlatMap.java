package map;

import model.Laccima;
import model.Output;
import model.MkiosBulk;
import model.OutputBulk;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.util.Collector;

public class Output1BulkMkiosBulkFlatMap implements
		FlatMapFunction<Tuple2<MkiosBulk, Laccima>, OutputBulk> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void flatMap(Tuple2<MkiosBulk, Laccima> in, Collector<OutputBulk> out)
			throws Exception {
		// TODO Auto-generated method stub
		OutputBulk output = new OutputBulk();

		// 20170718112310

		output.setFields("", in.f0.getRecharge_Date(), in.f1.getArea(),
				in.f1.getRegional_channel(), in.f1.getBranch(),
				in.f1.getSubbranch(), in.f1.getCluster(),
				in.f0.getAcceptor_actual_increase_money(), in.f0.getTrx(),
				in.f0.getSourceType(), in.f0.getRechargeType(),
				Long.toString(in.f0.getMSISDN()));

		out.collect(output);

	}
}

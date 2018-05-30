package map;


import model.MDL;
import model.Output;
import model.MkiosBulk;
import model.OutputBulk;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.util.Collector;



public class Output2BulkMkiosBulkFlatMap implements FlatMapFunction<Tuple2<MkiosBulk, MDL>, OutputBulk> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Override
	public void flatMap(Tuple2<MkiosBulk, MDL> in, Collector<OutputBulk> out)
			throws Exception {
		// TODO Auto-generated method stub
		OutputBulk output = new OutputBulk();

		// 20170718112310
		
		output.setFields("", in.f0.getRecharge_Date(), in.f1.getArea_name(),
				in.f1.getRegion_name(), in.f1.getBranch(),
				in.f1.getSub_branch(), in.f1.getClust(),
				in.f0.getAcceptor_actual_increase_money(), in.f0.getTrx(),
				in.f0.getSourceType(), in.f0.getRechargeType(),
				Long.toString(in.f0.getMSISDN()));
		
		out.collect(output);

	}
}

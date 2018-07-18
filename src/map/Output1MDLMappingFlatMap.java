package map;


import model.Laccima;
import model.MDL;
import model.Output;
import model.Output1Tuple;
import model.Urp;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.util.Collector;


public class Output1MDLMappingFlatMap implements FlatMapFunction<Tuple2<MDL, Laccima>, Output1Tuple> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Override
	public void flatMap(Tuple2<MDL, Laccima> in, Collector<Output1Tuple> out)
			throws Exception {
		// TODO Auto-generated method stub
		Output1Tuple output = new Output1Tuple();

		// 20170718112310
		output.setFields(in.f0.getMsisdn()+"|"+
				in.f0.getLac()+"|"+
				in.f0.getCi()+"|"+
				in.f1.getCluster()+"|"+
				in.f1.getRegional_channel()+"|"+
				in.f1.getArea()+"|"+
				in.f1.getBranch()+"|"+
				in.f1.getSubbranch()+"|"+
				""+"|"+
				""+"|"+
				"");
		
		out.collect(output);
		
	}
}

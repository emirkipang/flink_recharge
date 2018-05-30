package map;

import model.MDL;
import model.Mkios;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.tuple.Tuple2;


public class TempMkiosMap2 implements MapFunction<Tuple2<Mkios, MDL>, Mkios> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Mkios map(Tuple2<Mkios, MDL> in) throws Exception {
		// TODO Auto-generated method stub
		
		return in.f0;
	}
}

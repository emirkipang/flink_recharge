package map;

import model.Laccima;
import model.MDL;
import model.Urp;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.tuple.Tuple2;


public class TempMDLMap implements MapFunction<Tuple2<MDL, Laccima>, MDL> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public MDL map(Tuple2<MDL, Laccima> in) throws Exception {
		// TODO Auto-generated method stub
		
		return in.f0;
	}
}

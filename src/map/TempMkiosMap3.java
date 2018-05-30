package map;

import model.Mkios;
import model.Whitelist;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.tuple.Tuple2;



public class TempMkiosMap3 implements MapFunction<Tuple2<Mkios, Whitelist>, Mkios> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Mkios map(Tuple2<Mkios, Whitelist> in) throws Exception {
		// TODO Auto-generated method stub
		
		return in.f0;
	}
}

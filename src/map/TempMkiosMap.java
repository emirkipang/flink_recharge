package map;

import model.Laccima;
import model.Mkios;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.tuple.Tuple2;


public class TempMkiosMap implements MapFunction<Tuple2<Mkios, Laccima>, Mkios> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Mkios map(Tuple2<Mkios, Laccima> in) throws Exception {
		// TODO Auto-generated method stub
		
		return in.f0;
	}
}

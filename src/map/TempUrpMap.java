package map;

import model.Laccima;
import model.Urp;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.tuple.Tuple2;


public class TempUrpMap implements MapFunction<Tuple2<Urp, Laccima>, Urp> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Urp map(Tuple2<Urp, Laccima> in) throws Exception {
		// TODO Auto-generated method stub
		
		return in.f0;
	}
}

package map;

import model.Laccima;
import model.MkiosBulk;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.tuple.Tuple2;


public class TempMkiosBulkMap implements MapFunction<Tuple2<MkiosBulk, Laccima>, MkiosBulk> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public MkiosBulk map(Tuple2<MkiosBulk, Laccima> in) throws Exception {
		// TODO Auto-generated method stub
		
		return in.f0;
	}
}

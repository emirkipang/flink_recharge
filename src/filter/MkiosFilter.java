package filter;

import model.Mkios;

import org.apache.flink.api.common.functions.FilterFunction;


public class MkiosFilter implements FilterFunction<Mkios> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Override
	public boolean filter(Mkios in)
			throws Exception {
		return in.getResponse_state().equalsIgnoreCase("O00");
	}

}
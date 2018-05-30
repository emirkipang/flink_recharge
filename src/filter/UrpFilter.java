package filter;

import model.Urp;

import org.apache.flink.api.common.functions.FilterFunction;


public class UrpFilter implements FilterFunction<Urp> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Override
	public boolean filter(Urp in)
			throws Exception {
		return in.getResponseState().equalsIgnoreCase("61");
	}

}
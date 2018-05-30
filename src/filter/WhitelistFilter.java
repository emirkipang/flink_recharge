package filter;


import model.Whitelist;

import org.apache.flink.api.common.functions.FilterFunction;


public class WhitelistFilter implements FilterFunction<Whitelist> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Override
	public boolean filter(Whitelist in)
			throws Exception {
		return !in.getBnumber().equalsIgnoreCase("62");
	}

}
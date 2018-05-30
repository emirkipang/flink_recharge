package filter;

import model.Laccima;


import org.apache.flink.api.common.functions.FilterFunction;

public class LaccimaFilter implements FilterFunction<Laccima> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public boolean filter(Laccima in) throws Exception {
		return !in.getCluster().replaceAll("\\s", "").equals("")
				|| !in.getSubbranch().replaceAll("\\s", "").equals("")
				|| !in.getBranch().replaceAll("\\s", "").equals("")
				|| !in.getRegional_channel().replaceAll("\\s", "").equals("")
				|| !in.getArea().replaceAll("\\s", "").equals("");
	}

}
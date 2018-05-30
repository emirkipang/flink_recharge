package filter;

import model.MkiosBulk;

import org.apache.flink.api.common.functions.FilterFunction;



public class MkiosBulkFilter implements FilterFunction<MkiosBulk> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Override
	public boolean filter(MkiosBulk in)
			throws Exception {
		return in.getRechargeType().equalsIgnoreCase("bulk") && in.getResponse_state().equalsIgnoreCase("O00");
	}

}
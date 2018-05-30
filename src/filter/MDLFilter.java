package filter;

import model.MDL;

import org.apache.flink.api.common.functions.FilterFunction;

public class MDLFilter implements FilterFunction<MDL> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public boolean filter(MDL in) throws Exception {
		return !in.getClust().replaceAll("\\s", "").equals("")
				|| !in.getSub_branch().replaceAll("\\s", "").equals("")
				|| !in.getBranch().replaceAll("\\s", "").equals("")
				|| !in.getRegion_name().replaceAll("\\s", "").equals("")
				|| !in.getArea_name().replaceAll("\\s", "").equals("");
	}

}
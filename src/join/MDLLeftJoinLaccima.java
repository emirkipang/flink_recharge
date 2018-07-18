package join;

import model.Laccima;
import model.MDL;
import model.Output1Tuple;

import org.apache.flink.api.common.functions.FlatJoinFunction;
import org.apache.flink.util.Collector;

public class MDLLeftJoinLaccima implements
		FlatJoinFunction<MDL, Laccima, Output1Tuple> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void join(MDL leftElem, Laccima rightElem,
			Collector<Output1Tuple> out) throws Exception {
		// TODO Auto-generated method stub
		Output1Tuple output = new Output1Tuple();
		
		if (rightElem == null){
			output.setFields(leftElem.getMsisdn()+"|"+
					leftElem.getLac()+"|"+
					leftElem.getCi()+"|"+
					"UNKNOWN"+"|"+
					"UNKNOWN"+"|"+
					"UNKNOWN"+"|"+
					"UNKNOWN"+"|"+
					"UNKNOWN"+"|"+
					""+"|"+
					""+"|"+
					"");
		}
		else {
			output.setFields(leftElem.getMsisdn()+"|"+
					leftElem.getLac()+"|"+
					leftElem.getCi()+"|"+
					rightElem.getCluster()+"|"+
					rightElem.getRegional_channel()+"|"+
					rightElem.getArea()+"|"+
					rightElem.getBranch()+"|"+
					rightElem.getSubbranch()+"|"+
					""+"|"+
					""+"|"+
					"");
			
		}
		
		out.collect(output);
			
	}

}

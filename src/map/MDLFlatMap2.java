package map;

import model.MDL;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.util.Collector;

import util.Helper;

public class MDLFlatMap2 implements FlatMapFunction<String, MDL> {

	/**
* 
*/
	private static final long serialVersionUID = 1L;

	@Override
	public void flatMap(String in, Collector<MDL> out) throws Exception {
		// TODO Auto-generated method stub
		String[] lines = in.split("\n");

		for (String line : lines) {

			String[] items = line.split("\\|", -1);
			String strArea = "";

			if (!items[0].contains("par_reff_dom_lacci_201805")) {
				String msisdn = items[0];
				String lac = null, ci = null;
				// String clust = items[3];
				// String region_name = items[4];
				// String area_name = items[5];
				// String branch = items[6];
				// String sub_branch = items[7];
				// String node_type = items[8];
				// String total_revenue = items[9];
				// String total_trx = items[10];
				lac = Helper.joinRule(Helper.isEmpty(items[2]), 5);
				ci = Helper.joinRule(Helper.isEmpty(items[3]), 5);
				String lacci_3g = lac + "|" + ci;

				lac = Helper.joinRule(Helper.isEmpty(items[2]), 7);
				ci = Helper.joinRule(Helper.isEmpty(items[3]), 3);
				String lacci_4g = lac + "|" + ci;

				// out.collect(new MDL(Long.parseLong(msisdn), lac, ci, clust,
				// region_name, area_name,
				// branch, sub_branch, node_type, total_revenue, total_trx,
				// lacci));

				out.collect(new MDL(Long.parseLong(msisdn), lacci_3g, lacci_4g));
			}

		}

	}

}

package map;

import java.text.SimpleDateFormat;

import model.Mkios;
import model.MkiosBulk;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.util.Collector;

import util.Constant;
import util.Helper;

public class MkiosBulkFlatMap implements FlatMapFunction<String, MkiosBulk> {

	/**
* 
*/
	private static final long serialVersionUID = 1L;

	@Override
	public void flatMap(String in, Collector<MkiosBulk> out) throws Exception {
		// TODO Auto-generated method stub
		String[] lines = in.split("\n");

		for (String line : lines) {

			String[] items = line.split("\\|", -1);

			String sourceType = "";
			String rechargeType = "";

			if (items[76].toLowerCase().contains("rec")) {
				sourceType = "evcREC";
			} else if (items[76].toLowerCase().contains("vas")) {
				sourceType = "evcVAS";
			}

			if (items[31].equals("29")) {
				rechargeType = "bulk";
			} else {
				rechargeType = "reguler";
			}

			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
			String strDate = items[11].substring(0, 12);
			// Date date = sdf.parse(strDate);
			// date.setHours(date.getHours() - 7);

			long bnumber = Long.parseLong("62"
					+ Helper.isEmpty(items[9].replaceAll("[^0-9]", "")));
			long anumber = Long.parseLong("62"
					+ Helper.isEmpty(items[2].replaceAll("[^0-9]", "")));
			String laccia = Constant.joinRule(Helper.isEmpty(items[27]), 10);
			String laccib = Constant.joinRule(Helper.isEmpty(items[41]), 10);

			String laccia_3g = laccia.substring(0, 5) + "|"
					+ laccia.substring(5, 10);
			String laccia_4g = laccia.substring(0, 7) + "|"
					+ laccia.substring(7, 10);

			String laccib_3g = laccib.substring(0, 5) + "|"
					+ laccib.substring(5, 10);
			String laccib_4g = laccib.substring(0, 7) + "|"
					+ laccib.substring(7, 10);

			String combine = Constant.joinRule(Long.toString(anumber) + "|"
					+ Long.toString(bnumber), 28);

			out.collect(new MkiosBulk(bnumber, sourceType, rechargeType,
					strDate, items[13], items[19], laccib_3g, "1", laccia_3g,
					combine, laccia_4g, laccib_4g, anumber));
		}

	}

}

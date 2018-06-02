package map;

import java.text.SimpleDateFormat;
import java.util.Date;

import model.Urp;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.util.Collector;

import util.Constant;
import util.Helper;

public class UrpFlatMap implements FlatMapFunction<String, Urp> {

	/**
* 
*/
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("deprecation")
	@Override
	public void flatMap(String in, Collector<Urp> out) throws Exception {
		// TODO Auto-generated method stub
		String[] lines = in.split("\n");

		/**
		 * CDR Specification Layout Field-13 = MSISDN Field-8 = Transaction Time
		 * Field-6 = Response State Field-2 = Recharge Nominal Field-24 = LACCI
		 * B Number trx
		 */

		String strDate = "";

		for (String fields : lines) {
			strDate = "";

			String[] field = fields.split("\\/");

			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
			strDate = field[8].replace("-", "").replace(":", "")
					.replace(" ", "").substring(0, 12);
			//Date date = sdf.parse(strDate);
			//date.setHours(date.getHours() - 7);

			long msisdn = Long.parseLong(Helper.isEmpty(field[13].replaceAll("[^0-9]", "")));
			//String tanggal = sdf.format(date);
			String tempLacci = Helper.joinRule(Helper.isEmpty(field[24]), 10);

			String laccib_4g = tempLacci.substring(0, 7) + "|"
					+ tempLacci.substring(7, 10);

			String laccib = tempLacci.substring(0, 5) + "|"
					+ tempLacci.substring(5, 10);
			String response_state = field[6];
			String recharge_nominal = field[2];
			//String channel = Helper.getUrpChannel(field[18]);
			String channel = field[18];
			
			out.collect(new Urp(msisdn, strDate, response_state, recharge_nominal, laccib,
					"1", laccib_4g, channel));

		}

	}
}

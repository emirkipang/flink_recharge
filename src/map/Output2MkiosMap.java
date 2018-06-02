package map;

import model.Mkios;
import model.MDL;
import model.Output;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.tuple.Tuple2;

//Channel
//Tanggal
//Area
//Region
//Branch
//SubBranch
//Cluster
//Tot_Amount
//Tot_Trx
//prc_date
//prc_hour

public class Output2MkiosMap implements MapFunction<Tuple2<Mkios, MDL>, Output> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Output map(Tuple2<Mkios, MDL> in) throws Exception {
		// TODO Auto-generated method stub
		Output out = new Output();

		// 20170718112310

		out.setFields(in.f0.getChannel(), in.f0.getRecharge_Date(), in.f1.getArea_name(),
				in.f1.getRegion_name(), in.f1.getBranch(),
				in.f1.getSub_branch(), in.f1.getClust(),
				in.f0.getAcceptor_actual_increase_money(), in.f0.getTrx());


		
		return out;
	}
}

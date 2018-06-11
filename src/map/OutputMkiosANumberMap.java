package map;

import model.Laccima;
import model.Mkios;
import model.MDL;
import model.Output;
import model.Output1Tuple;

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

public class OutputMkiosANumberMap implements MapFunction<Tuple2<Mkios, Laccima>, Output1Tuple> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Output1Tuple map(Tuple2<Mkios, Laccima> in) throws Exception {
		// TODO Auto-generated method stub
		Output1Tuple out = new Output1Tuple();

		// 20170718112310

		out.setFields(in.f0.getAD_MSISDN()+"|"+in.f0.getHand_phone_No_of_a_Dealer()+"|"+in.f0.getLACCI_RS()+"|"+in.f0.getLACCI_RS_4g()+"|"+in.f1.getArea());


		
		return out;
	}
}

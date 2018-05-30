package model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.flink.api.java.tuple.Tuple10;
import org.apache.flink.api.java.tuple.Tuple11;
import org.apache.flink.api.java.tuple.Tuple12;
import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.api.java.tuple.Tuple9;

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

public class OutputBulk
		extends
		Tuple12<String, String, String, String, String, String, String, String, String, String, String, String> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void setFields(String Channel, String Tanggal, String Area,
			String Region, String Branch, String SubBranch, String Cluster,
			String Tot_Amount, String Tot_Trx, String sourceType, String rechargeType, String msisdn) {
		// TODO Auto-generated method stub


		super.setFields(Channel, Tanggal, Area, Region, Branch, SubBranch,
				Cluster, Tot_Amount, Tot_Trx, sourceType, rechargeType, msisdn);
	}

}

package model;

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

public class Output
		extends
		Tuple9<String, String, String, String, String, String, String, String, String> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public void setFields(String Channel, String Tanggal, String Area,
			String Region, String Branch, String SubBranch, String Cluster,
			String Tot_Amount, String Tot_Trx) {
		// TODO Auto-generated method stub

		super.setFields(Channel, Tanggal, Area, Region, Branch, SubBranch, Cluster,
				Tot_Amount, Tot_Trx);
	}

}

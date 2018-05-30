package model;


public class MDL {
	private long msisdn;// 0
	private String lac;// 1
	private String ci;// 2
	private String clust;// 3
	private String region_name;// 4
	private String area_name;// 5
	private String branch;// 6
	private String sub_branch;// 7
	private String node_type;// 8
	private String total_revenue;// 9
	private String total_trx;// 10

	// 0 msisdn
	// 3 clust
	// 4 region_name
	// 5 area_name
	// 6 branch
	// 7 sub_branch

	public MDL() {
		this.region_name = "UNKNOWN";
		this.branch = "UNKNOWN";
		this.sub_branch = "UNKNOWN";
		this.clust = "UNKNOWN";
		this.area_name = "UNKNOWN";
	}

	public MDL(long msisdn, String clust, String region_name,
			String area_name, String branch, String sub_branch) {
		this.msisdn = msisdn;
		this.clust = clust;
		this.region_name = region_name;
		this.area_name = area_name;
		this.branch = branch;
		this.sub_branch = sub_branch;
	}

	

	public long getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(long msisdn) {
		this.msisdn = msisdn;
	}

	public String getLac() {
		return lac;
	}

	public void setLac(String lac) {
		this.lac = lac;
	}

	public String getCi() {
		return ci;
	}

	public void setCi(String ci) {
		this.ci = ci;
	}

	public String getClust() {
		return clust;
	}

	public void setClust(String clust) {
		this.clust = clust;
	}

	public String getRegion_name() {
		return region_name;
	}

	public void setRegion_name(String region_name) {
		this.region_name = region_name;
	}

	public String getArea_name() {
		return area_name;
	}

	public void setArea_name(String area_name) {
		this.area_name = area_name;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getSub_branch() {
		return sub_branch;
	}

	public void setSub_branch(String sub_branch) {
		this.sub_branch = sub_branch;
	}

	public String getNode_type() {
		return node_type;
	}

	public void setNode_type(String node_type) {
		this.node_type = node_type;
	}

	public String getTotal_revenue() {
		return total_revenue;
	}

	public void setTotal_revenue(String total_revenue) {
		this.total_revenue = total_revenue;
	}

	public String getTotal_trx() {
		return total_trx;
	}

	public void setTotal_trx(String total_trx) {
		this.total_trx = total_trx;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getArea_name() + "|"
				+ getRegion_name() + "|"
				+ getBranch() + "|"
				+ getSub_branch() + "|"
				+ getClust();
	}

}

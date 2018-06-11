package model;

public class ListAD {
	private String No; //0
	private String Regional;//1
	private String Branch;//2
	private String SubBranch;//3
	private String Cluster;//4
	private String LeadADName;//5
	private long LeadADMSISDN;//6
	private String LeadADCode;//7
	
	private String Area; //-1
	
	public ListAD(){}

	public ListAD(String no, String regional, String branch, String subBranch,
			String cluster, String leadADName, long leadADMSISDN,
			String leadADCode, String area) {
		super();
		No = no;
		Regional = regional;
		Branch = branch;
		SubBranch = subBranch;
		Cluster = cluster;
		LeadADName = leadADName;
		LeadADMSISDN = leadADMSISDN;
		LeadADCode = leadADCode;
		Area = area;
	}

	public String getNo() {
		return No;
	}

	public void setNo(String no) {
		No = no;
	}

	public String getRegional() {
		return Regional;
	}

	public void setRegional(String regional) {
		Regional = regional;
	}

	public String getBranch() {
		return Branch;
	}

	public void setBranch(String branch) {
		Branch = branch;
	}

	public String getSubBranch() {
		return SubBranch;
	}

	public void setSubBranch(String subBranch) {
		SubBranch = subBranch;
	}

	public String getCluster() {
		return Cluster;
	}

	public void setCluster(String cluster) {
		Cluster = cluster;
	}

	public String getLeadADName() {
		return LeadADName;
	}

	public void setLeadADName(String leadADName) {
		LeadADName = leadADName;
	}

	public long getLeadADMSISDN() {
		return LeadADMSISDN;
	}

	public void setLeadADMSISDN(long leadADMSISDN) {
		LeadADMSISDN = leadADMSISDN;
	}

	public String getLeadADCode() {
		return LeadADCode;
	}

	public void setLeadADCode(String leadADCode) {
		LeadADCode = leadADCode;
	}

	public String getArea() {
		return Area;
	}

	public void setArea(String area) {
		Area = area;
	}
	
	

	
}

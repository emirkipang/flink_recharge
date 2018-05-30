package model;

public class Whitelist {
	private String anumber;
	private String bnumber;
	private String combine;

	public Whitelist() {

	}

	public Whitelist(String anumber, String bnumber, String combine) {
		super();
		this.anumber = anumber;
		this.bnumber = bnumber;
		this.combine = combine;
	}

	public String getAnumber() {
		return anumber;
	}

	public void setAnumber(String anumber) {
		this.anumber = anumber;
	}

	public String getBnumber() {
		return bnumber;
	}

	public void setBnumber(String bnumber) {
		this.bnumber = bnumber;
	}

	public String getCombine() {
		return combine;
	}

	public void setCombine(String combine) {
		this.combine = combine;
	}

	
}

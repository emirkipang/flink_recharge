package model;

public class Urp {
	/** 
	 * CDR Specification Layout
	 * Field-13 = MSISDN
	 * Field-8 = Transaction Time
	 * Field-6 = Response State
	 * Field-2 = Recharge Nominal
	 * Field-24 = LACCI B Number
	 */
	private long MSISDN;
	private String TransactionTime;
	private String ResponseState;
	private String RechargeNominal;
	private String LACCI;
	
	private String trx;
	private String LACCI_4g;
	private String channel;
	
	public Urp(){
		
	}

	public Urp(long mSISDN, String transactionTime, String responseState,
			String rechargeNominal, String lACCI, String trx, String LACCI_4g) {
		super();
		MSISDN = mSISDN;
		TransactionTime = transactionTime;
		ResponseState = responseState;
		RechargeNominal = rechargeNominal;
		LACCI = lACCI;
		this.trx = trx;
		this.LACCI_4g = LACCI_4g;
	}
	
	public Urp(long mSISDN, String transactionTime, String responseState,
			String rechargeNominal, String lACCI, String trx, String LACCI_4g, String channel) {
		super();
		
		MSISDN = mSISDN;
		TransactionTime = transactionTime;
		ResponseState = responseState;
		RechargeNominal = rechargeNominal;
		LACCI = lACCI;
		this.trx = trx;
		this.LACCI_4g = LACCI_4g;
		this.channel = channel;
	}

	
	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getLACCI_4g() {
		return LACCI_4g;
	}

	public void setLACCI_4g(String lACCI_4g) {
		LACCI_4g = lACCI_4g;
	}

	public long getMSISDN() {
		return MSISDN;
	}

	public void setMSISDN(long mSISDN) {
		MSISDN = mSISDN;
	}

	public String getTransactionTime() {
		return TransactionTime;
	}

	public void setTransactionTime(String transactionTime) {
		TransactionTime = transactionTime;
	}

	public String getResponseState() {
		return ResponseState;
	}

	public void setResponseState(String responseState) {
		ResponseState = responseState;
	}

	public String getRechargeNominal() {
		return RechargeNominal;
	}

	public void setRechargeNominal(String rechargeNominal) {
		RechargeNominal = rechargeNominal;
	}

	public String getLACCI() {
		return LACCI;
	}

	public void setLACCI(String lACCI) {
		LACCI = lACCI;
	}

	public String getTrx() {
		return trx;
	}

	public void setTrx(String trx) {
		this.trx = trx;
	}

	
}

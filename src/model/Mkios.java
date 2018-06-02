package model;

public class Mkios {
	private String Serial_No_in_the_system;// 0
	private String Region_ID;// 1
	private long Hand_phone_No_of_a_Dealer;// 2
	private String Reserved_String;// 3
	private String Initiator_Template_ID;// 4
	private String Dealers_name;// 5
	private String Brand_type;// 6
	private String Users_brand_type;// 7
	private String Price;// 8
	private long MSISDN;// 9
	private String Reserved_String1;// 10
	private String Recharge_Date;// 11
	private String SerialNo_of_a_rechargeable_card;// 12
	private String Response_state;// 13
	private String Transaction_status;// 14
	private String Reserved_String2;// 15
	private String Error_message;// 16
	private String Access_type;// 17
	private String Dealer_actual_decrease_money;// 18
	private String Acceptor_actual_increase_money;// 19
	private String Reserved;// 20
	private String Reserved2;// 21
	private String ICCID;// 22
	private String Balance_of_stock_before_adjustor;// 23
	private String Reserved3;// 24
	private String Balance_after_recharge;// 25
	private String Reserved4;// 26
	private String LACCI_RS;// 27
	private String Reserved_String3;// 28
	private String Reserved_String4;// 29
	private String Reserved5;// 30
	private String Account_Index;// 31
	private String Old_Balance;// 32
	private String New_Balance;// 33
	private String Reserved6;// 34
	private String Reserved_String5;// 35
	private String Reserved7;// 36
	private String Reserved8;// 37
	private String Reserved9;// 38
	private String Upstream_MSISDN;// 39
	private String Upstream_Name;// 40
	private String LACCI_subscriber;// 41
	private String Reserved_String6;// 42
	private String recharge_type;// 43
	private String SEPID;// 44
	private String GWNo;// 45
	private String Active_date_before;// 46
	private String Active_date_after;// 47
	private String Reseller_type;// 48
	private String Cross_region_identifier;// 49
	private String Unidentified_16;// 50
	private String Unidentified_17;// 51
	private String Unidentified_18;// 52
	private String Unidentified_19;// 53
	private String Unidentified_20;// 54
	private String Unidentified_21;// 55
	private String AD_MSISDN;// 56
	private String Unidentified_22;// 57
	private String Unidentified_23;// 58
	private String Unidentified_24;// 59
	private String Unidentified_25;// 60
	private String Unidentified_26;// 61
	private String Unidentified_27;// 62
	private String Unidentified_28;// 63
	private String Unidentified_29;// 64
	private String Unidentified_30;// 65
	private String Unidentified_31;// 66
	private String Unidentified_32;// 67
	private String Unidentified_33;// 68
	private String Unidentified_34;// 69
	private String Unidentified_35;// 70
	private String Unidentified_36;// 71
	private String Node_Flag;// 72
	private String Unidentified_38;// 73
	private String Unidentified_39;// 74
	private String Unidentified_40;// 75
	private String filename;// 76

	private String channel; // -1
	private String trx; // -1
	private String combine; // -1 anumber + bnumber
	private String LACCI_RS_4g; // -1
	private String LACCI_subscriber_4g; // -1

	/**
	 * CDR Specification Layout Field-9 = MSISDN Field-11 = Transaction Time
	 * Field-13 = Response State Field-19 = Recharge Nominal Field-41 = LACCI B
	 * Number Field-27 = LACCI A Number trx
	 */

	public Mkios(long MSISDN, String Recharge_Date, String Response_state,
			String Acceptor_actual_increase_money, String LACCI_subscriber,
			String trx, String LACCI_RS, String combine, String LACCI_RS_4g,
			String LACCI_subscriber_4g, long Hand_phone_No_of_a_Dealer) {
		this.MSISDN = MSISDN;
		this.Recharge_Date = Recharge_Date;
		this.Response_state = Response_state;
		this.Acceptor_actual_increase_money = Acceptor_actual_increase_money;
		this.LACCI_subscriber = LACCI_subscriber;
		this.trx = trx;
		this.LACCI_RS = LACCI_RS;
		this.combine = combine;
		this.LACCI_RS_4g = LACCI_RS_4g;
		this.LACCI_subscriber_4g = LACCI_subscriber_4g;
		this.Hand_phone_No_of_a_Dealer = Hand_phone_No_of_a_Dealer;
	}

	public Mkios(long MSISDN, String Recharge_Date, String Response_state,
			String Acceptor_actual_increase_money, String LACCI_subscriber,
			String trx, String LACCI_RS, String combine, String LACCI_RS_4g,
			String LACCI_subscriber_4g, long Hand_phone_No_of_a_Dealer,
			String channel) {

		this.MSISDN = MSISDN;
		this.Recharge_Date = Recharge_Date;
		this.Response_state = Response_state;
		this.Acceptor_actual_increase_money = Acceptor_actual_increase_money;
		this.LACCI_subscriber = LACCI_subscriber;
		this.trx = trx;
		this.LACCI_RS = LACCI_RS;
		this.combine = combine;
		this.LACCI_RS_4g = LACCI_RS_4g;
		this.LACCI_subscriber_4g = LACCI_subscriber_4g;
		this.Hand_phone_No_of_a_Dealer = Hand_phone_No_of_a_Dealer;
		this.channel = channel;
	}

	public Mkios() {
		super();
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getLACCI_RS_4g() {
		return LACCI_RS_4g;
	}

	public void setLACCI_RS_4g(String lACCI_RS_4g) {
		LACCI_RS_4g = lACCI_RS_4g;
	}

	public String getLACCI_subscriber_4g() {
		return LACCI_subscriber_4g;
	}

	public void setLACCI_subscriber_4g(String lACCI_subscriber_4g) {
		LACCI_subscriber_4g = lACCI_subscriber_4g;
	}

	public String getSerial_No_in_the_system() {
		return Serial_No_in_the_system;
	}

	public void setSerial_No_in_the_system(String serial_No_in_the_system) {
		Serial_No_in_the_system = serial_No_in_the_system;
	}

	public String getRegion_ID() {
		return Region_ID;
	}

	public void setRegion_ID(String region_ID) {
		Region_ID = region_ID;
	}

	public long getHand_phone_No_of_a_Dealer() {
		return Hand_phone_No_of_a_Dealer;
	}

	public void setHand_phone_No_of_a_Dealer(long hand_phone_No_of_a_Dealer) {
		Hand_phone_No_of_a_Dealer = hand_phone_No_of_a_Dealer;
	}

	public String getReserved_String() {
		return Reserved_String;
	}

	public void setReserved_String(String reserved_String) {
		Reserved_String = reserved_String;
	}

	public String getInitiator_Template_ID() {
		return Initiator_Template_ID;
	}

	public void setInitiator_Template_ID(String initiator_Template_ID) {
		Initiator_Template_ID = initiator_Template_ID;
	}

	public String getDealers_name() {
		return Dealers_name;
	}

	public void setDealers_name(String dealers_name) {
		Dealers_name = dealers_name;
	}

	public String getBrand_type() {
		return Brand_type;
	}

	public void setBrand_type(String brand_type) {
		Brand_type = brand_type;
	}

	public String getUsers_brand_type() {
		return Users_brand_type;
	}

	public void setUsers_brand_type(String users_brand_type) {
		Users_brand_type = users_brand_type;
	}

	public String getPrice() {
		return Price;
	}

	public void setPrice(String price) {
		Price = price;
	}

	public long getMSISDN() {
		return MSISDN;
	}

	public void setMSISDN(long mSISDN) {
		MSISDN = mSISDN;
	}

	public String getReserved_String1() {
		return Reserved_String1;
	}

	public void setReserved_String1(String reserved_String1) {
		Reserved_String1 = reserved_String1;
	}

	public String getRecharge_Date() {
		return Recharge_Date;
	}

	public void setRecharge_Date(String recharge_Date) {
		Recharge_Date = recharge_Date;
	}

	public String getSerialNo_of_a_rechargeable_card() {
		return SerialNo_of_a_rechargeable_card;
	}

	public void setSerialNo_of_a_rechargeable_card(
			String serialNo_of_a_rechargeable_card) {
		SerialNo_of_a_rechargeable_card = serialNo_of_a_rechargeable_card;
	}

	public String getResponse_state() {
		return Response_state;
	}

	public void setResponse_state(String response_state) {
		Response_state = response_state;
	}

	public String getTransaction_status() {
		return Transaction_status;
	}

	public void setTransaction_status(String transaction_status) {
		Transaction_status = transaction_status;
	}

	public String getReserved_String2() {
		return Reserved_String2;
	}

	public void setReserved_String2(String reserved_String2) {
		Reserved_String2 = reserved_String2;
	}

	public String getError_message() {
		return Error_message;
	}

	public void setError_message(String error_message) {
		Error_message = error_message;
	}

	public String getAccess_type() {
		return Access_type;
	}

	public void setAccess_type(String access_type) {
		Access_type = access_type;
	}

	public String getDealer_actual_decrease_money() {
		return Dealer_actual_decrease_money;
	}

	public void setDealer_actual_decrease_money(
			String dealer_actual_decrease_money) {
		Dealer_actual_decrease_money = dealer_actual_decrease_money;
	}

	public String getAcceptor_actual_increase_money() {
		return Acceptor_actual_increase_money;
	}

	public void setAcceptor_actual_increase_money(
			String acceptor_actual_increase_money) {
		Acceptor_actual_increase_money = acceptor_actual_increase_money;
	}

	public String getReserved() {
		return Reserved;
	}

	public void setReserved(String reserved) {
		Reserved = reserved;
	}

	public String getReserved2() {
		return Reserved2;
	}

	public void setReserved2(String reserved2) {
		Reserved2 = reserved2;
	}

	public String getICCID() {
		return ICCID;
	}

	public void setICCID(String iCCID) {
		ICCID = iCCID;
	}

	public String getBalance_of_stock_before_adjustor() {
		return Balance_of_stock_before_adjustor;
	}

	public void setBalance_of_stock_before_adjustor(
			String balance_of_stock_before_adjustor) {
		Balance_of_stock_before_adjustor = balance_of_stock_before_adjustor;
	}

	public String getReserved3() {
		return Reserved3;
	}

	public void setReserved3(String reserved3) {
		Reserved3 = reserved3;
	}

	public String getBalance_after_recharge() {
		return Balance_after_recharge;
	}

	public void setBalance_after_recharge(String balance_after_recharge) {
		Balance_after_recharge = balance_after_recharge;
	}

	public String getReserved4() {
		return Reserved4;
	}

	public void setReserved4(String reserved4) {
		Reserved4 = reserved4;
	}

	public String getLACCI_RS() {
		return LACCI_RS;
	}

	public void setLACCI_RS(String lACCI_RS) {
		LACCI_RS = lACCI_RS;
	}

	public String getReserved_String3() {
		return Reserved_String3;
	}

	public void setReserved_String3(String reserved_String3) {
		Reserved_String3 = reserved_String3;
	}

	public String getReserved_String4() {
		return Reserved_String4;
	}

	public void setReserved_String4(String reserved_String4) {
		Reserved_String4 = reserved_String4;
	}

	public String getReserved5() {
		return Reserved5;
	}

	public void setReserved5(String reserved5) {
		Reserved5 = reserved5;
	}

	public String getAccount_Index() {
		return Account_Index;
	}

	public void setAccount_Index(String account_Index) {
		Account_Index = account_Index;
	}

	public String getOld_Balance() {
		return Old_Balance;
	}

	public void setOld_Balance(String old_Balance) {
		Old_Balance = old_Balance;
	}

	public String getNew_Balance() {
		return New_Balance;
	}

	public void setNew_Balance(String new_Balance) {
		New_Balance = new_Balance;
	}

	public String getReserved6() {
		return Reserved6;
	}

	public void setReserved6(String reserved6) {
		Reserved6 = reserved6;
	}

	public String getReserved_String5() {
		return Reserved_String5;
	}

	public void setReserved_String5(String reserved_String5) {
		Reserved_String5 = reserved_String5;
	}

	public String getReserved7() {
		return Reserved7;
	}

	public void setReserved7(String reserved7) {
		Reserved7 = reserved7;
	}

	public String getReserved8() {
		return Reserved8;
	}

	public void setReserved8(String reserved8) {
		Reserved8 = reserved8;
	}

	public String getReserved9() {
		return Reserved9;
	}

	public void setReserved9(String reserved9) {
		Reserved9 = reserved9;
	}

	public String getUpstream_MSISDN() {
		return Upstream_MSISDN;
	}

	public void setUpstream_MSISDN(String upstream_MSISDN) {
		Upstream_MSISDN = upstream_MSISDN;
	}

	public String getUpstream_Name() {
		return Upstream_Name;
	}

	public void setUpstream_Name(String upstream_Name) {
		Upstream_Name = upstream_Name;
	}

	public String getLACCI_subscriber() {
		return LACCI_subscriber;
	}

	public void setLACCI_subscriber(String lACCI_subscriber) {
		LACCI_subscriber = lACCI_subscriber;
	}

	public String getReserved_String6() {
		return Reserved_String6;
	}

	public void setReserved_String6(String reserved_String6) {
		Reserved_String6 = reserved_String6;
	}

	public String getRecharge_type() {
		return recharge_type;
	}

	public void setRecharge_type(String recharge_type) {
		this.recharge_type = recharge_type;
	}

	public String getSEPID() {
		return SEPID;
	}

	public void setSEPID(String sEPID) {
		SEPID = sEPID;
	}

	public String getGWNo() {
		return GWNo;
	}

	public void setGWNo(String gWNo) {
		GWNo = gWNo;
	}

	public String getActive_date_before() {
		return Active_date_before;
	}

	public void setActive_date_before(String active_date_before) {
		Active_date_before = active_date_before;
	}

	public String getActive_date_after() {
		return Active_date_after;
	}

	public void setActive_date_after(String active_date_after) {
		Active_date_after = active_date_after;
	}

	public String getReseller_type() {
		return Reseller_type;
	}

	public void setReseller_type(String reseller_type) {
		Reseller_type = reseller_type;
	}

	public String getCross_region_identifier() {
		return Cross_region_identifier;
	}

	public void setCross_region_identifier(String cross_region_identifier) {
		Cross_region_identifier = cross_region_identifier;
	}

	public String getUnidentified_16() {
		return Unidentified_16;
	}

	public void setUnidentified_16(String unidentified_16) {
		Unidentified_16 = unidentified_16;
	}

	public String getUnidentified_17() {
		return Unidentified_17;
	}

	public void setUnidentified_17(String unidentified_17) {
		Unidentified_17 = unidentified_17;
	}

	public String getUnidentified_18() {
		return Unidentified_18;
	}

	public void setUnidentified_18(String unidentified_18) {
		Unidentified_18 = unidentified_18;
	}

	public String getUnidentified_19() {
		return Unidentified_19;
	}

	public void setUnidentified_19(String unidentified_19) {
		Unidentified_19 = unidentified_19;
	}

	public String getUnidentified_20() {
		return Unidentified_20;
	}

	public void setUnidentified_20(String unidentified_20) {
		Unidentified_20 = unidentified_20;
	}

	public String getUnidentified_21() {
		return Unidentified_21;
	}

	public void setUnidentified_21(String unidentified_21) {
		Unidentified_21 = unidentified_21;
	}

	public String getAD_MSISDN() {
		return AD_MSISDN;
	}

	public void setAD_MSISDN(String aD_MSISDN) {
		AD_MSISDN = aD_MSISDN;
	}

	public String getUnidentified_22() {
		return Unidentified_22;
	}

	public void setUnidentified_22(String unidentified_22) {
		Unidentified_22 = unidentified_22;
	}

	public String getUnidentified_23() {
		return Unidentified_23;
	}

	public void setUnidentified_23(String unidentified_23) {
		Unidentified_23 = unidentified_23;
	}

	public String getUnidentified_24() {
		return Unidentified_24;
	}

	public void setUnidentified_24(String unidentified_24) {
		Unidentified_24 = unidentified_24;
	}

	public String getUnidentified_25() {
		return Unidentified_25;
	}

	public void setUnidentified_25(String unidentified_25) {
		Unidentified_25 = unidentified_25;
	}

	public String getUnidentified_26() {
		return Unidentified_26;
	}

	public void setUnidentified_26(String unidentified_26) {
		Unidentified_26 = unidentified_26;
	}

	public String getUnidentified_27() {
		return Unidentified_27;
	}

	public void setUnidentified_27(String unidentified_27) {
		Unidentified_27 = unidentified_27;
	}

	public String getUnidentified_28() {
		return Unidentified_28;
	}

	public void setUnidentified_28(String unidentified_28) {
		Unidentified_28 = unidentified_28;
	}

	public String getUnidentified_29() {
		return Unidentified_29;
	}

	public void setUnidentified_29(String unidentified_29) {
		Unidentified_29 = unidentified_29;
	}

	public String getUnidentified_30() {
		return Unidentified_30;
	}

	public void setUnidentified_30(String unidentified_30) {
		Unidentified_30 = unidentified_30;
	}

	public String getUnidentified_31() {
		return Unidentified_31;
	}

	public void setUnidentified_31(String unidentified_31) {
		Unidentified_31 = unidentified_31;
	}

	public String getUnidentified_32() {
		return Unidentified_32;
	}

	public void setUnidentified_32(String unidentified_32) {
		Unidentified_32 = unidentified_32;
	}

	public String getUnidentified_33() {
		return Unidentified_33;
	}

	public void setUnidentified_33(String unidentified_33) {
		Unidentified_33 = unidentified_33;
	}

	public String getUnidentified_34() {
		return Unidentified_34;
	}

	public void setUnidentified_34(String unidentified_34) {
		Unidentified_34 = unidentified_34;
	}

	public String getUnidentified_35() {
		return Unidentified_35;
	}

	public void setUnidentified_35(String unidentified_35) {
		Unidentified_35 = unidentified_35;
	}

	public String getUnidentified_36() {
		return Unidentified_36;
	}

	public void setUnidentified_36(String unidentified_36) {
		Unidentified_36 = unidentified_36;
	}

	public String getNode_Flag() {
		return Node_Flag;
	}

	public void setNode_Flag(String node_Flag) {
		Node_Flag = node_Flag;
	}

	public String getUnidentified_38() {
		return Unidentified_38;
	}

	public void setUnidentified_38(String unidentified_38) {
		Unidentified_38 = unidentified_38;
	}

	public String getUnidentified_39() {
		return Unidentified_39;
	}

	public void setUnidentified_39(String unidentified_39) {
		Unidentified_39 = unidentified_39;
	}

	public String getUnidentified_40() {
		return Unidentified_40;
	}

	public void setUnidentified_40(String unidentified_40) {
		Unidentified_40 = unidentified_40;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getTrx() {
		return trx;
	}

	public void setTrx(String trx) {
		this.trx = trx;
	}

	public String getCombine() {
		return combine;
	}

	public void setCombine(String combine) {
		this.combine = combine;
	}

}

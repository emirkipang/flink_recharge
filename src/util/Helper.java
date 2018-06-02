package util;

public class Helper {
	public static final String isNull(String text) {
		return (text == null) ? "" : text;
	}
	
	public static final String isEmpty(String text) {
		text = text.replaceAll("\\s","");
		return (text.equals("")) ? "0" : text;
	}
	
	public static final String joinRule(String in, int length) {
		int gap = length - in.length();

		if (gap != 0) {
			for (int i = 1; i <= gap; i++) {
				in = "0" + in;
			}
		}

		return in;
	}
	
	public static final String getMkiosChannel(String rechargeType){
		if(rechargeType.equals(Constant.MKIOS_REG[0])){
			return "MKIOS_REG";
		}
		
		for (String x : Constant.MKIOS_VAS_FIX) {
			if (rechargeType.equals(x)){
				return "MKIOS_VAS_FIX";
			}
		}
		
		for (String x : Constant.MKIOS_VAS_BULK) {
			if (rechargeType.equals(x)){
				return "MKIOS_VAS_BULK";
			}
		}
		
		return rechargeType+"_MIOS";
	}
	
	public static final String getUrpChannel(String voucherType){
		if(voucherType.equals(Constant.URP_REG[0])){
			return "URP_REG";
		}
		
		for (String x : Constant.URP_VAS_FIX) {
			if (voucherType.equals(x)){
				return "URP_VAS_FIX";
			}
		}
		
		for (String x : Constant.URP_VAS_BULK) {
			if (voucherType.equals(x)){
				return "URP_VAS_BULK";
			}
		}
		
		return voucherType+"_URP";
	}
}

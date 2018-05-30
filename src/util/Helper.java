package util;

public class Helper {
	public static String isNull(String text) {
		return (text == null) ? "" : text;
	}
	
	public static String isEmpty(String text) {
		text = text.replaceAll("\\s","");
		return (text.equals("")) ? "0" : text;
	}
	
}

package model;

public class SplitCode {
	private String type;
	private String channel;
	
	public SplitCode(String type, String channel){
		this.type = type;
		this.channel = channel;
	}
	
	public SplitCode(){
		
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}
	
	
}

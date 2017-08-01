package cn.xhlcode.klfm.bean;

public class Album {
	private String code;
	private String message;
	private Results result;
	private String serverTime;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public Results getResult() {
		return result;
	}
	public void setResult(Results result) {
		this.result = result;
	}
	public String getServerTime() {
		return serverTime;
	}
	public void setServerTime(String serverTime) {
		this.serverTime = serverTime;
	}
	@Override
	public String toString() {
		return "Status [code=" + code + ", message=" + message + ", result=" + result + ", serverTime=" + serverTime
				+ "]";
	}
	
	
	
	

}

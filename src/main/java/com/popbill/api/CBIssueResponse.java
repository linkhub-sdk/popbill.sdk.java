package com.popbill.api;

public class CBIssueResponse {
	private long code;
	private String message;
	private String confirmNum;
	private String tradeDate;
	
	public long getCode() {
		return code;
	}
	public String getMessage() {
		return message;
	}
	public String getConfirmNum() {
		return confirmNum;
	}
	public String getTradeDate() {
		return tradeDate;
	}
}

package com.popbill.api;

/**
 * v1.37.0 추가
 */
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

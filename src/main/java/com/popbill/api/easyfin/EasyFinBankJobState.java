package com.popbill.api.easyfin;

public class EasyFinBankJobState {
	
	private String jobID;
	private String jobState;
	private String startDate;
	private String endDate;
	private long errorCode;
	private String errorReason;
	private String jobStartDT;
	private String jobEndDT;
	private String regDT;
	
	public String getJobID() {
		return jobID;
	}
	public String getJobState() {
		return jobState;
	}
	public String getStartDate() {
		return startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public long getErrorCode() {
		return errorCode;
	}
	public String getErrorReason() {
		return errorReason;
	}
	public String getJobStartDT() {
		return jobStartDT;
	}
	public String getJobEndDT() {
		return jobEndDT;
	}
	public String getRegDT() {
		return regDT;
	}
	
}

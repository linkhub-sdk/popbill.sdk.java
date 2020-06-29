package com.popbill.api;

import java.io.Serializable;

public class AccountCheckInfo implements Serializable{

	private static final long serialVersionUID = 7817999831512011251L;

	private String bankCode;
	private String accountNumber;
	private String accountName;
	private String checkDate;
	private String resultCode;
	private String resultMessage;
	
	public String getBankCode() {
		return bankCode;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public String getAccountName() {
		return accountName;
	}
	public String getCheckDate() {
		return checkDate;
	}
	public String getResultCode() {
		return resultCode;
	}
	public String getResultMessage() {
		return resultMessage;
	}
}

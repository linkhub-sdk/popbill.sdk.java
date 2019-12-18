package com.popbill.api.easyfin;

public class EasyFinBankAccount {
	
	private String accountNumber;
	private String bankCode;
	private String accountName;
	private String accountType;
	private int state;
	private String regDT;
	private String memo;
	
	public String getAccountNumber() {
		return accountNumber;
	}
	public String getBankCode() {
		return bankCode;
	}
	public String getAccountName() {
		return accountName;
	}
	public String getAccountType() {
		return accountType;
	}
	public int getState() {
		return state;
	}
	public String getRegDT() {
		return regDT;
	}
	public String getMemo() {
		return memo;
	}
}

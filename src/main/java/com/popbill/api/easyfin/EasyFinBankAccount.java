package com.popbill.api.easyfin;

public class EasyFinBankAccount {
	
	private String accountNumber;
	private String bankCode;
	private String accountName;
	private String accountType;
	private int state;
	private String regDT;
	private String memo;
	
	public String contractDT;
	public int baseDate;
	public String useEndDate;
	public int contractState;
	public Boolean closeRequestYN;
	private Boolean useRestrictYN;
	private Boolean closeOnExpired;
	private Boolean unPaidYN;
	
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
	public String getContractDT() {
		return contractDT;
	}
	public int getBaseDate() {
		return baseDate;
	}
	public String getUseEndDate() {
		return useEndDate;
	}
	public int getContractState() {
		return contractState;
	}
	public Boolean getCloseRequestYN() {
		return closeRequestYN;
	}
	public Boolean getUseRestrictYN() {
		return useRestrictYN;
	}
	public Boolean getCloseOnExpired() {
		return closeOnExpired;
	}
	public Boolean getUnPaidYN() {
		return unPaidYN;
	}
}

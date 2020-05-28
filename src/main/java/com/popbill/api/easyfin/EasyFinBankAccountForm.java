package com.popbill.api.easyfin;

public class EasyFinBankAccountForm {
	
	private String BankCode;
	private String AccountNumber;
	private String AccountPWD;
	private String AccountType;
	private String IdentityNumber;
	private String AccountName;
	private String BankID;
	private String FastID;
	private String FastPWD;
	private Integer UsePeriod;
	private String Memo;
	
	public String getBankCode() {
		return BankCode;
	}
	public void setBankCode(String bankCode) {
		BankCode = bankCode;
	}
	public String getAccountNumber() {
		return AccountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		AccountNumber = accountNumber;
	}
	public String getAccountPWD() {
		return AccountPWD;
	}
	public void setAccountPWD(String accountPWD) {
		AccountPWD = accountPWD;
	}
	public String getAccountType() {
		return AccountType;
	}
	public void setAccountType(String accountType) {
		AccountType = accountType;
	}
	public String getIdentityNumber() {
		return IdentityNumber;
	}
	public void setIdentityNumber(String identityNumber) {
		IdentityNumber = identityNumber;
	}
	public String getAccountName() {
		return AccountName;
	}
	public void setAccountName(String accountName) {
		AccountName = accountName;
	}
	public String getBankID() {
		return BankID;
	}
	public void setBankID(String bankID) {
		BankID = bankID;
	}
	public String getFastID() {
		return FastID;
	}
	public void setFastID(String fastID) {
		FastID = fastID;
	}
	public String getFastPWD() {
		return FastPWD;
	}
	public void setFastPWD(String fastPWD) {
		FastPWD = fastPWD;
	}
	public Integer getUsePeriod() {
		return UsePeriod;
	}
	public void setUsePeriod(int usePeriod) {
		UsePeriod = usePeriod;
	}
	public String getMemo() {
		return Memo;
	}
	public void setMemo(String memo) {
		Memo = memo;
	}
}

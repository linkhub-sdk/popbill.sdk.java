package com.popbill.api.easyfin;

import java.io.Serializable;

public class EasyFinBankSearchDetail implements Serializable{

	private static final long serialVersionUID = 5297705778091466939L;
	
	private String tid;
	private String accountID;
	private String trdate;
	private long trserial;
	private String trdt;
	private String accIn;
	private String accOut;
	private String balance;
	private String remark1;
	private String remark2;
	private String remark3;
	private String remark4;
	private String regDT;
	private String memo;
	
	public String getTid() {
		return tid;
	}
	public String getAccountID() {
		return accountID;
	}
	public String getTrdate() {
		return trdate;
	}
	public long getTrserial() {
		return trserial;
	}
	public String getTrdt() {
		return trdt;
	}
	public String getAccIn() {
		return accIn;
	}
	public String getAccOut() {
		return accOut;
	}
	public String getBalance() {
		return balance;
	}
	public String getRemark1() {
		return remark1;
	}
	public String getRemark2() {
		return remark2;
	}
	public String getRemark3() {
		return remark3;
	}
	public String getRemark4() {
		return remark4;
	}
	public String getRegDT() {
		return regDT;
	}
	public String getMemo() {
		return memo;
	}
	

}

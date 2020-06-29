package com.popbill.api;

/**
 * AccountCheck Service Interface.
 * 
 * @author John
 *
 */
public interface AccountCheckService extends BaseService{

	public float getUnitCost(String CorpNum) throws PopbillException;
	
	public ChargeInfo getChargeInfo(String CorpNum) throws PopbillException;
	
	AccountCheckInfo CheckAccountInfo(String MemberCorpNum, String BankCode, String AccountNumber) throws PopbillException;
	
	AccountCheckInfo CheckAccountInfo(String MemberCorpNum, String BankCode, String AccountNumber, String UserID) throws PopbillException;
}

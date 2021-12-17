package com.popbill.api;

/**
 * AccountCheck Service Interface.
 * 
 * @author John
 *
 */
public interface AccountCheckService extends BaseService {

    @Deprecated
    public float getUnitCost(String CorpNum) throws PopbillException;

    @Deprecated
    public ChargeInfo getChargeInfo(String CorpNum) throws PopbillException;

    public float getUnitCost(String CorpNum, String serviceType) throws PopbillException;

    public ChargeInfo getChargeInfo(String CorpNum, String serviceType) throws PopbillException;

    AccountCheckInfo CheckAccountInfo(String MemberCorpNum, String BankCode, String AccountNumber)
            throws PopbillException;

    AccountCheckInfo CheckAccountInfo(String MemberCorpNum, String BankCode, String AccountNumber, String UserID)
            throws PopbillException;

    DepositorCheckInfo CheckDepositorInfo(String MemberCorpNum, String BankCode, String AccountNumber,
            String IdentityNumType, String IdentityNum) throws PopbillException;

    DepositorCheckInfo CheckDepositorInfo(String MemberCorpNum, String BankCode, String AccountNumber,
            String IdentityNumType, String IdentityNum, String UserID) throws PopbillException;
}

package com.popbill.api.accountcheck;

import java.util.Arrays;
import java.util.List;

import com.popbill.api.AccountCheckInfo;
import com.popbill.api.AccountCheckService;
import com.popbill.api.BaseServiceImp;
import com.popbill.api.ChargeInfo;
import com.popbill.api.DepositorCheckInfo;
import com.popbill.api.PopbillException;
import com.popbill.api.util.ValidationUtils;

/**
 * @author John
 *
 */
public class AccountCheckServiceImp extends BaseServiceImp implements AccountCheckService {

    @Override
    protected List<String> getScopes() {
        return Arrays.asList("182", "183");
    }

    @Override
    @Deprecated
    public float getUnitCost(String CorpNum) throws PopbillException {
        return getUnitCost(CorpNum, null, null);
    }

    @Override
    public float getUnitCost(String CorpNum, String serviceType) throws PopbillException {
        return getUnitCost(CorpNum, serviceType, null);
    }

    @Override
    public float getUnitCost(String CorpNum, String serviceType, String UserID) throws PopbillException {
        String uri = "/EasyFin/AccountCheck/UnitCost";

        if (!ValidationUtils.isNullOrEmpty(serviceType))
            uri += "?serviceType=" + serviceType;

        UnitCostResponse response = httpget(uri, CorpNum, UserID, UnitCostResponse.class);

        return response.unitCost;
    }

    @Override
    @Deprecated
    public ChargeInfo getChargeInfo(String CorpNum) throws PopbillException {
        return getChargeInfo(CorpNum, null, null);
    }

    @Override
    public ChargeInfo getChargeInfo(String CorpNum, String serviceType) throws PopbillException {
        return getChargeInfo(CorpNum, serviceType, null);
    }

    @Override
    public ChargeInfo getChargeInfo(String CorpNum, String serviceType, String UserID) throws PopbillException {
        String uri = "/EasyFin/AccountCheck/ChargeInfo";

        if (!ValidationUtils.isNullOrEmpty(serviceType))
            uri += "?serviceType=" + serviceType;

        return httpget(uri, CorpNum, UserID, ChargeInfo.class);
    }

    @Override
    public AccountCheckInfo CheckAccountInfo(String CorpNum, String BankCode, String AccountNumber)
            throws PopbillException {
        return CheckAccountInfo(CorpNum, BankCode, AccountNumber, null);
    }

    @Override
    public AccountCheckInfo CheckAccountInfo(String CorpNum, String BankCode, String AccountNumber, String UserID)
            throws PopbillException {
        String uri = "/EasyFin/AccountCheck";

        uri += "?c=" + BankCode;
        uri += "&n=" + AccountNumber;

        return httppost(uri, CorpNum, null, UserID, AccountCheckInfo.class);
    }

    @Override
    public DepositorCheckInfo CheckDepositorInfo(String CorpNum, String BankCode, String AccountNumber,
            String IdentityNumType, String IdentityNum) throws PopbillException {
        return CheckDepositorInfo(CorpNum, BankCode, AccountNumber, IdentityNumType, IdentityNum, null);
    }

    @Override
    public DepositorCheckInfo CheckDepositorInfo(String CorpNum, String BankCode, String AccountNumber,
            String IdentityNumType, String IdentityNum, String UserID) throws PopbillException {
        String uri = "/EasyFin/DepositorCheck";

        uri += "?c=" + BankCode;
        uri += "&n=" + AccountNumber;
        uri += "&t=" + IdentityNumType;
        uri += "&p=" + IdentityNum;

        return httppost(uri, CorpNum, null, UserID, DepositorCheckInfo.class);
    }

}
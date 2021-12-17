package com.popbill.api.accountcheck;

import java.util.Arrays;
import java.util.List;

import com.popbill.api.AccountCheckInfo;
import com.popbill.api.AccountCheckService;
import com.popbill.api.BaseServiceImp;
import com.popbill.api.ChargeInfo;
import com.popbill.api.DepositorCheckInfo;
import com.popbill.api.PopbillException;

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
        UnitCostResponse response = httpget("/EasyFin/AccountCheck/UnitCost", CorpNum, null, UnitCostResponse.class);
        return response.unitCost;
    }

    @Override
    @Deprecated
    public ChargeInfo getChargeInfo(String CorpNum) throws PopbillException {
        return httpget("/EasyFin/AccountCheck/ChargeInfo", CorpNum, null, ChargeInfo.class);
    }

    @Override
    public float getUnitCost(String CorpNum, String serviceType) throws PopbillException {
        UnitCostResponse response = httpget("/EasyFin/AccountCheck/UnitCost?serviceType=" + serviceType, CorpNum, null,
                UnitCostResponse.class);
        return response.unitCost;
    }

    @Override
    public ChargeInfo getChargeInfo(String CorpNum, String serviceType) throws PopbillException {
        return httpget("/EasyFin/AccountCheck/ChargeInfo?serviceType=" + serviceType, CorpNum, null, ChargeInfo.class);
    }

    @Override
    public AccountCheckInfo CheckAccountInfo(String MemberCorpNum, String BankCode, String AccountNumber)
            throws PopbillException {
        return CheckAccountInfo(MemberCorpNum, BankCode, AccountNumber, null);
    }

    @Override
    public AccountCheckInfo CheckAccountInfo(String MemberCorpNum, String BankCode, String AccountNumber, String UserID)
            throws PopbillException {
        if (BankCode == null || BankCode.isEmpty())
            throw new PopbillException(-99999999, "은행코드가 입력되지 않았습니다.");

        if (BankCode.length() != 4)
            throw new PopbillException(-99999999, "은행코드가 올바르지 않습니다.");

        if (AccountNumber == null || AccountNumber.isEmpty())
            throw new PopbillException(-99999999, "계좌번호가 입력되지 않았습니다.");

        String uri = "/EasyFin/AccountCheck";

        uri += "?c=" + BankCode;
        uri += "&n=" + AccountNumber;

        return httppost(uri, MemberCorpNum, null, UserID, AccountCheckInfo.class);
    }

    @Override
    public DepositorCheckInfo CheckDepositorInfo(String MemberCorpNum, String BankCode, String AccountNumber,
            String IdentityNumType, String IdentityNum) throws PopbillException {
        return CheckDepositorInfo(MemberCorpNum, BankCode, AccountNumber, IdentityNumType, IdentityNum, null);
    }

    @Override
    public DepositorCheckInfo CheckDepositorInfo(String MemberCorpNum, String BankCode, String AccountNumber,
            String IdentityNumType, String IdentityNum, String UserID) throws PopbillException {
        if (BankCode == null || BankCode.isEmpty())
            throw new PopbillException(-99999999, "은행코드가 입력되지 않았습니다.");

        if (BankCode.length() != 4)
            throw new PopbillException(-99999999, "은행코드가 올바르지 않습니다.");

        if (AccountNumber == null || AccountNumber.isEmpty())
            throw new PopbillException(-99999999, "계좌번호가 입력되지 않았습니다.");

        if (IdentityNumType == null || IdentityNumType.isEmpty())
            throw new PopbillException(-99999999, "등록번호 유형이 입력되지 않았습니다.");

        if (false == IdentityNumType.matches("^[PB]$"))
            throw new PopbillException(-99999999, "올바른 등록번호 유형이 아닙니다.");

        if (IdentityNum == null || IdentityNum.isEmpty())
            throw new PopbillException(-99999999, "등록번호가 입력되지 않았습니다.");

        if (false == IdentityNum.matches("^\\d+$"))
            throw new PopbillException(-99999999, "등록번호는 숫자만 입력합니다.");

        String uri = "/EasyFin/DepositorCheck";

        uri += "?c=" + BankCode;
        uri += "&n=" + AccountNumber;
        uri += "&t=" + IdentityNumType;
        uri += "&p=" + IdentityNum;

        return httppost(uri, MemberCorpNum, null, UserID, DepositorCheckInfo.class);
    }

}

package com.popbill.api.accountcheck;

import java.util.Arrays;
import java.util.List;

import com.popbill.api.AccountCheckInfo;
import com.popbill.api.AccountCheckService;
import com.popbill.api.BaseServiceImp;
import com.popbill.api.ChargeInfo;
import com.popbill.api.PopbillException;

/**
 * @author John
 *
 */
public class AccountCheckServiceImp extends BaseServiceImp implements AccountCheckService{

	@Override
	protected List<String> getScopes() {
		return Arrays.asList("182"); 
	}

	@Override
	public float getUnitCost(String CorpNum) throws PopbillException {
		UnitCostResponse response = httpget("/EasyFin/AccountCheck/UnitCost",
				CorpNum, null, UnitCostResponse.class);

		return response.unitCost;
	}

	@Override
	public ChargeInfo getChargeInfo(String CorpNum) throws PopbillException {
		return httpget("/EasyFin/AccountCheck/ChargeInfo",CorpNum, null, ChargeInfo.class);
	}

	@Override
	public AccountCheckInfo CheckAccountInfo(String MemberCorpNum, String BankCode, String AccountNumber)
			throws PopbillException {
		return CheckAccountInfo(MemberCorpNum, BankCode, AccountNumber, null);
	}

	@Override
	public AccountCheckInfo CheckAccountInfo(String MemberCorpNum, String BankCode, String AccountNumber, String UserID)
			throws PopbillException {
		if (BankCode== null || BankCode.isEmpty())
			throw new PopbillException(-99999999, "은행코드가 입력되지 않았습니다.");
		
		if (BankCode.length() != 4)
			throw new PopbillException(-99999999, "은행코드가 올바르지 않습니다.");
		
		if (AccountNumber== null || AccountNumber.isEmpty())
			throw new PopbillException(-99999999, "계좌번호가 입력되지 않았습니다.");
				
		String uri = "/EasyFin/AccountCheck";
		
		uri += "?c="+BankCode;
		uri += "&n="+AccountNumber;
		
		return httppost(uri,  MemberCorpNum, null, UserID, AccountCheckInfo.class);
	}

	

}

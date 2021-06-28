package com.popbill.api.easyfin;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;

import com.popbill.api.BaseServiceImp;
import com.popbill.api.ChargeInfo;
import com.popbill.api.EasyFinBankService;
import com.popbill.api.FlatRateState;
import com.popbill.api.PopbillException;
import com.popbill.api.Response;

public class EasyFinBankServiceImp extends BaseServiceImp implements EasyFinBankService {

	@Override
	protected List<String> getScopes() {
		
		return Arrays.asList("180");
	}
	/*
	 * (non-Javadoc)
	 * @see com.popbill.api.EasyFinBankService#getBankAccountMgtURL(java.lang.String)
	 */
	@Override
	public String getBankAccountMgtURL(String CorpNum) throws PopbillException {
		
		return getBankAccountMgtURL(CorpNum, null);
	}

	/*
	 * (non-Javadoc)
	 * @see com.popbill.api.EasyFinBankService#getBankAccountMgtURL(java.lang.String, java.lang.String)
	 */
	@Override
	public String getBankAccountMgtURL(String CorpNum, String UserID) throws PopbillException {
		URLResponse response = httpget("/EasyFin/Bank?TG=BankAccount", CorpNum, UserID, URLResponse.class);
		
		return response.url;
	}

	
	@Override
	public Response registBankAccount(String CorpNum, EasyFinBankAccountForm form) throws PopbillException {
		
		return registBankAccount(CorpNum, form, null);
	}
	@Override
	public Response registBankAccount(String CorpNum, EasyFinBankAccountForm form, String UserID)
			throws PopbillException {
		
		if(form == null)
			throw new PopbillException(-99999999, "은행 계좌정보가 입력되지 않았습니다.");
		
		if (form.getBankCode()== null || form.getBankCode().isEmpty())
			throw new PopbillException(-99999999, "은행코드가 입력되지 않았습니다.");
		
		if (form.getBankCode().length() != 4)
			throw new PopbillException(-99999999, "은행코드가 올바르지 않습니다.");
		
		if (form.getAccountNumber()== null || form.getAccountNumber().isEmpty())
			throw new PopbillException(-99999999, "계좌번호가 입력되지 않았습니다.");
		
		if (form.getAccountPWD()== null || form.getAccountPWD().isEmpty())
			throw new PopbillException(-99999999, "계좌 비밀번호가 입력되지 않았습니다.");
		
		if (form.getAccountType()== null || form.getAccountType().isEmpty())
			throw new PopbillException(-99999999, "계좌유형이 입력되지 않았습니다.");
		
		if (form.getAccountType() != "개인" && form.getAccountType() != "법인")
			throw new PopbillException(-99999999, "계좌유형이 올바르지 않습니다.");
		
		if (form.getIdentityNumber()== null || form.getIdentityNumber().isEmpty())
			throw new PopbillException(-99999999, "예금주 식별정보가 입력되지 않았습니다.");
		
		String uri = "/EasyFin/Bank/BankAccount/Regist"; 
		if(null != form.getUsePeriod()) uri += "?UsePeriod=" + form.getUsePeriod();
		
		String PostData = toJsonString(form);
		
		return httppost(uri, CorpNum, PostData, UserID, Response.class);
	}
	
	
	@Override
	public Response closeBankAccount(String CorpNum, String BankCode, String AccountNumber, String CloseType)
			throws PopbillException {
		
		return closeBankAccount(CorpNum, BankCode, AccountNumber, CloseType, null);
	}	
	@Override
	public Response closeBankAccount(String CorpNum, String BankCode, String AccountNumber, String CloseType,
			String UserID) throws PopbillException {
		if (BankCode== null || BankCode.isEmpty())
			throw new PopbillException(-99999999, "은행코드가 입력되지 않았습니다.");
		
		if (BankCode.length() != 4)
			throw new PopbillException(-99999999, "은행코드가 올바르지 않습니다.");
		
		if (AccountNumber== null || AccountNumber.isEmpty())
			throw new PopbillException(-99999999, "계좌번호가 입력되지 않았습니다.");
		
				
		if (CloseType== null || CloseType.isEmpty())
			throw new PopbillException(-99999999, "정액제 해지유형이 입력되지 않았습니다.");
		
		if (CloseType != "중도" && CloseType != "일반")
			throw new PopbillException(-99999999, "정액제 해지유형이 올바르지 않습니다.");
		
		String uri = "/EasyFin/Bank/BankAccount/Close";
		
		uri += "?BankCode="+BankCode;
		uri += "&AccountNumber="+AccountNumber;
		uri += "&CloseType="+CloseType;
		
		return httppost(uri, CorpNum, null, UserID, Response.class);
	}
	
	@Override
	public Response revokeCloseBankAccount(String CorpNum, String BankCode, String AccountNumber)
			throws PopbillException {

		return revokeCloseBankAccount(CorpNum, BankCode, AccountNumber, null);
	}
	@Override
	public Response revokeCloseBankAccount(String CorpNum, String BankCode, String AccountNumber, String UserID)
			throws PopbillException {
		
		if (BankCode== null || BankCode.isEmpty())
			throw new PopbillException(-99999999, "은행코드가 입력되지 않았습니다.");
		
		if (BankCode.length() != 4)
			throw new PopbillException(-99999999, "은행코드가 올바르지 않습니다.");
		
		if (AccountNumber== null || AccountNumber.isEmpty())
			throw new PopbillException(-99999999, "계좌번호가 입력되지 않았습니다.");
		
				
		String uri = "/EasyFin/Bank/BankAccount/RevokeClose";
		
		uri += "?BankCode="+BankCode;
		uri += "&AccountNumber="+AccountNumber;
		
		return httppost(uri, CorpNum, null, UserID, Response.class);
	}

	@Override
	public Response deleteBankAccount(String CorpNum, String BankCode, String AccountNumber) throws PopbillException {
		
		return deleteBankAccount(CorpNum, BankCode, AccountNumber, null);
	}
	@Override
	public Response deleteBankAccount(String CorpNum, String BankCode, String AccountNumber, String UserID) throws PopbillException {
		
		if (BankCode== null || BankCode.isEmpty())
			throw new PopbillException(-99999999, "은행코드가 입력되지 않았습니다.");
		
		if (BankCode.length() != 4)
			throw new PopbillException(-99999999, "은행코드가 올바르지 않습니다.");
		
		if (AccountNumber== null || AccountNumber.isEmpty())
			throw new PopbillException(-99999999, "계좌번호가 입력되지 않았습니다.");
		
		String uri = "/EasyFin/Bank/BankAccount/Delete";	
		
		String PostData = "{'BankCode':" + BankCode + ", 'AccountNumber':"+ AccountNumber +"}"; 
		
		return httppost(uri, CorpNum, PostData, UserID, Response.class);
	}
	
	@Override
	public Response updateBankAccount(String CorpNum, EasyFinBankAccountForm form) throws PopbillException {
		return updateBankAccount(CorpNum, form, null);
	}
	@Override
	public Response updateBankAccount(String CorpNum, EasyFinBankAccountForm form, String UserID)
			throws PopbillException {
		
		if (form == null)
			throw new PopbillException(-99999999, "은행 계좌정보가 입력되지 않았습니다.");
		
		if (form.getBankCode()== null || form.getBankCode().isEmpty())
			throw new PopbillException(-99999999, "은행코드가 입력되지 않았습니다.");
		
		if (form.getBankCode().length() != 4)
			throw new PopbillException(-99999999, "은행코드가 올바르지 않습니다.");
		
		if (form.getAccountNumber()== null || form.getAccountNumber().isEmpty())
			throw new PopbillException(-99999999, "계좌번호가 입력되지 않았습니다.");
		
		if (form.getAccountPWD()== null || form.getAccountPWD().isEmpty())
			throw new PopbillException(-99999999, "계좌 비밀번호가 입력되지 않았습니다.");
		
		
		String uri = "/EasyFin/Bank/BankAccount/"+form.getBankCode()+"/"+form.getAccountNumber()+"/Update"; 
		
		String PostData = toJsonString(form);
		
		return httppost(uri, CorpNum, PostData, UserID, Response.class);
	}
	
	
	
	/*
	 * (non-Javadoc)
	 * @see com.popbill.api.EasyFinBankService#getBankAccountInfo(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public EasyFinBankAccount getBankAccountInfo(String CorpNum, String BankCode, String AccountNumber)
			throws PopbillException {
		
		return getBankAccountInfo(CorpNum, BankCode, AccountNumber, null);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.popbill.api.EasyFinBankService#getBankAccountInfo(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public EasyFinBankAccount getBankAccountInfo(String CorpNum, String BankCode, String AccountNumber, String UserID)
			throws PopbillException {
		if (BankCode == null || BankCode.isEmpty())
			throw new PopbillException(-99999999, "은행코드가 입력되지 않았습니다.");
		if (BankCode.length() != 4)
			throw new PopbillException(-99999999, "은행코드가 올바르지 않습니다.");
		if (AccountNumber == null || AccountNumber.isEmpty())
			throw new PopbillException(-99999999, "계좌번호가 입력되지 않았습니다.");
		
		return httpget("/EasyFin/Bank/BankAccount/"+BankCode+"/"+AccountNumber, CorpNum, UserID, EasyFinBankAccount.class);
		
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see com.popbill.api.EasyFinBankService#listBankAccount(java.lang.String)
	 */
	@Override
	public EasyFinBankAccount[] listBankAccount(String CorpNum) throws PopbillException {
		
		return listBankAccount(CorpNum, null);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.popbill.api.EasyFinBankService#listBankAccount(java.lang.String, java.lang.String)
	 */
	@Override
	public EasyFinBankAccount[] listBankAccount(String CorpNum, String UserID) throws PopbillException {
		
		return httpget("/EasyFin/Bank/ListBankAccount", CorpNum, UserID, EasyFinBankAccount[].class);
	}

	/*
	 * (non-Javadoc)
	 * @see com.popbill.api.EasyFinBankService#requestJob(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public String requestJob(String CorpNum, String BankCode, String AccountNumber, String SDate, String EDate)
			throws PopbillException {
		// 
		return requestJob(CorpNum, BankCode, AccountNumber, SDate, EDate, null);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.popbill.api.EasyFinBankService#requestJob(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public String requestJob(String CorpNum, String BankCode, String AccountNumber, String SDate, String EDate,
			String UserID) throws PopbillException {
		
		if (BankCode == null || BankCode.isEmpty())
			throw new PopbillException(-99999999, "은행코드가 입력되지 않았습니다.");
		if (AccountNumber == null || AccountNumber.isEmpty())
			throw new PopbillException(-99999999, "계좌번호가 입력되지 않았습니다.");
		if (SDate == null || SDate.isEmpty())
			throw new PopbillException(-99999999, "조회 시작일자가 입력되지 않았습니다.");
		if (EDate == null || EDate.isEmpty())
			throw new PopbillException(-99999999, "조회 종료일자가 입력되지 않았습니다.");
		
		
		JobIDResponse response = httppost("/EasyFin/Bank/BankAccount?AccountNumber="+AccountNumber+"&BankCode="+BankCode 
				+ "&SDate=" + SDate +"&EDate=" + EDate ,CorpNum, null, UserID, JobIDResponse.class);
		
		return response.jobID;
	}

	/*
	 * (non-Javadoc)
	 * @see com.popbill.api.EasyFinBankService#getJobState(java.lang.String, java.lang.String)
	 */
	@Override
	public EasyFinBankJobState getJobState(String CorpNum, String JobID) throws PopbillException {
		
		return getJobState(CorpNum, JobID, null);
	}

	/*
	 * (non-Javadoc)
	 * @see com.popbill.api.EasyFinBankService#getJobState(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public EasyFinBankJobState getJobState(String CorpNum, String JobID, String UserID) throws PopbillException {
		if (JobID == null || JobID.isEmpty())
			throw new PopbillException(-99999999, "작업아이디가 입력되지 않았습니다.");
		
		return httpget("/EasyFin/Bank/"+JobID+"/State",CorpNum, UserID, EasyFinBankJobState.class);
	}

	/*
	 * (non-Javadoc)
	 * @see com.popbill.api.EasyFinBankService#listActiveJob(java.lang.String)
	 */
	@Override
	public EasyFinBankJobState[] listActiveJob(String CorpNum) throws PopbillException {
		
		return listActiveJob(CorpNum, null);
	}

	/*
	 * (non-Javadoc)
	 * @see com.popbill.api.EasyFinBankService#listActiveJob(java.lang.String, java.lang.String)
	 */
	@Override
	public EasyFinBankJobState[] listActiveJob(String CorpNum, String UserID) throws PopbillException {
		// TODO Auto-generated method stub
		return httpget("/EasyFin/Bank/JobList",CorpNum, UserID, EasyFinBankJobState[].class);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.popbill.api.EasyFinBankService#search(java.lang.String, java.lang.String, java.lang.String[], java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.String)
	 */
	@Override
	public EasyFinBankSearchResult search(String CorpNum, String JobID, String[] TradeType, String SearchString, 
			Integer Page, Integer PerPage, String Order)
			throws PopbillException {
		
		return search(CorpNum, JobID, TradeType, SearchString, Page, PerPage, Order, null);
	}

	/*
	 * (non-Javadoc)
	 * @see com.popbill.api.EasyFinBankService#search(java.lang.String, java.lang.String, java.lang.String[], java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.String)
	 */
	@Override
	public EasyFinBankSearchResult search(String CorpNum, String JobID, String[] TradeType, String SearchString, 
			Integer Page, Integer PerPage, String Order, String UserID) throws PopbillException {
		

		if (JobID.length() != 18)
			throw new PopbillException(-99999999, "작업아이디가 올바르지 않습니다.");
		
		String uri = "/EasyFin/Bank/"+JobID;
		
		uri +="?TradeType="+ Arrays.toString(TradeType)
		.replaceAll("\\[|\\]|\\s", "");
		
		if (SearchString != "" && SearchString != null) {
			try {
				uri += "&SearchString=" + URLEncoder.encode(SearchString, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				throw new PopbillException(-99999999, "검색어(SearchString) 인코딩 오류");
			}
		}
		
		uri += "&Page=" + Integer.toString(Page);
		uri += "&PerPage=" + Integer.toString(PerPage);
		uri += "&Order=" + Order;
				
		
		return httpget(uri, CorpNum, UserID, EasyFinBankSearchResult.class);
	}

	/*
	 * (non-Javadoc)
	 * @see com.popbill.api.EasyFinBankService#summary(java.lang.String, java.lang.String, java.lang.String[], java.lang.String)
	 */
	@Override
	public EasyFinBankSummary summary(String CorpNum, String JobID, String[] TradeType, String SearchString)
			throws PopbillException {
		
		return summary(CorpNum, JobID, TradeType, SearchString, null);
	}

	/*
	 * (non-Javadoc)
	 * @see com.popbill.api.EasyFinBankService#summary(java.lang.String, java.lang.String, java.lang.String[], java.lang.String, java.lang.String)
	 */
	@Override
	public EasyFinBankSummary summary(String CorpNum, String JobID, String[] TradeType, String SearchString,
			String UserID) throws PopbillException {
		
		if (JobID.length() != 18)
			throw new PopbillException(-99999999, "작업아이디가 올바르지 않습니다.");
		
		String uri = "/EasyFin/Bank/"+JobID+"/Summary";
		
		uri +="?TradeType="+ Arrays.toString(TradeType)
		.replaceAll("\\[|\\]|\\s", "");
		
		if (SearchString != "" && SearchString != null) {
			try {
				uri += "&SearchString=" + URLEncoder.encode(SearchString, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				throw new PopbillException(-99999999, "검색어(SearchString) 인코딩 오류");
			}
		}
		
		
		return httpget(uri, CorpNum, UserID, EasyFinBankSummary.class);
	}

	/*
	 * (non-Javadoc)
	 * @see com.popbill.api.EasyFinBankService#saveMemo(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public Response saveMemo(String CorpNum, String TID, String Memo) throws PopbillException {
		
		return saveMemo(CorpNum, TID, Memo, null);
	}

	/*
	 * (non-Javadoc)
	 * @see com.popbill.api.EasyFinBankService#saveMemo(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public Response saveMemo(String CorpNum, String TID, String Memo, String UserID) throws PopbillException {
		
		if (TID == null || TID.isEmpty())
            throw new PopbillException(-99999999, "거래내역 아이디가 입력되지 않았습니다.");
		
		if (Memo == null)
            throw new PopbillException(-99999999, "메모가 입력되지 않았습니다.");
		
		
		String uri = "/EasyFin/Bank/SaveMemo";
		
		uri +="?TID="+ TID;
		
		if (Memo != "" && Memo != null) {
			try {
				uri += "&Memo=" + URLEncoder.encode(Memo, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				throw new PopbillException(-99999999, "메모(Memo) 인코딩 오류");
			}
		}
		
		return httppost(uri ,CorpNum, null, UserID, Response.class);
		
	}

	/*
	 * (non-Javadoc)
	 * @see com.popbill.api.EasyFinBankService#getFlatRateState(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public FlatRateState getFlatRateState(String CorpNum, String BankCode, String AccountNumber)
			throws PopbillException {
		
		return getFlatRateState(CorpNum, BankCode, AccountNumber, null);
	}

	/*
	 * (non-Javadoc)
	 * @see com.popbill.api.EasyFinBankService#getFlatRateState(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public FlatRateState getFlatRateState(String CorpNum, String BankCode, String AccountNumber, String UserID)
			throws PopbillException {
		
		if (BankCode == null || BankCode.isEmpty())
			throw new PopbillException(-99999999, "은행코드가 입력되지 않았습니다.");
		if (AccountNumber == null || AccountNumber.isEmpty())
			throw new PopbillException(-99999999, "계좌번호가 입력되지 않았습니다.");
		
		return httpget("/EasyFin/Bank/Contract/"+BankCode+"/"+AccountNumber, CorpNum, UserID, FlatRateState.class);
		
	}

	/*
	 * (non-Javadoc)
	 * @see com.popbill.api.EasyFinBankService#getFlatRatePopUpURL(java.lang.String)
	 */
	@Override
	public String getFlatRatePopUpURL(String CorpNum) throws PopbillException {

		return getFlatRatePopUpURL(CorpNum, null);
	}

	/*
	 * (non-Javadoc)
	 * @see com.popbill.api.EasyFinBankService#getFlatRatePopUpURL(java.lang.String, java.lang.String)
	 */
	@Override
	public String getFlatRatePopUpURL(String CorpNum, String UserID) throws PopbillException {
		
		URLResponse response = httpget("/EasyFin/Bank?TG=CHRG", CorpNum, UserID, URLResponse.class);
		
		return response.url;
	}

	/*
	 * (non-Javadoc)
	 * @see com.popbill.api.EasyFinBankService#getChargeInfo(java.lang.String)
	 */
	@Override
	public ChargeInfo getChargeInfo(String CorpNum) throws PopbillException {
		
		return httpget("/EasyFin/Bank/ChargeInfo", CorpNum, null, ChargeInfo.class);
	}
	
	
	
	protected class JobIDResponse {
		public String jobID;
	}
	
	protected class TIDMemoRequest {
		public String tID;
		public String memo;
	}

	

	
	

	

	

}

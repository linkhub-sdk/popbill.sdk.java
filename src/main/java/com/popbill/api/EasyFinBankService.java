package com.popbill.api;

import com.popbill.api.easyfin.EasyFinBankAccount;
import com.popbill.api.easyfin.EasyFinBankJobState;
import com.popbill.api.easyfin.EasyFinBankSearchResult;
import com.popbill.api.easyfin.EasyFinBankSummary;

/**
 * EasyFin Service Interface.
 * @author John
 * @version 1.0.0
 *
 */
public interface EasyFinBankService extends BaseService{
	
	
	
	/**
	 * 계좌 관리 팝업 URL
	 * 
	 * @param CorpNum
	 * @return
	 * @throws PopbillException
	 */
	public String getBankAccountMgtURL(String CorpNum) throws PopbillException;
	
	/**
	 * 계좌 관리 팝업 URL
	 * 
	 * @param CorpNum
	 * @param UserID
	 * @return
	 * @throws PopbillException
	 */
	public String getBankAccountMgtURL(String CorpNum, String UserID) throws PopbillException;
	
	/**
	 * 계좌 목록 조회 
	 * 
	 * @param CorpNum
	 * @return
	 * @throws PopbillException
	 */
	public EasyFinBankAccount[] listBankAccount(String CorpNum) throws PopbillException;
	
	/**
	 * 계좌 목록 조회 
	 * 
	 * @param CorpNum
	 * @param UserID
	 * @return
	 * @throws PopbillException
	 */
	public EasyFinBankAccount[] listBankAccount(String CorpNum, String UserID) throws PopbillException;
	
	/**
	 * 수집 요청 
	 * 
	 * @param CorpNum
	 * @param BankCode
	 * @param AccountNumber
	 * @param SDate
	 * @param EDate
	 * @return
	 * @throws PopbillException
	 */
	public String requestJob(String CorpNum, String BankCode, String AccountNumber, String SDate, String EDate) throws PopbillException;
	
	
	/**
	 * 수집 요청 
	 * @param CorpNum
	 * @param BankCode
	 * @param AccountNumber
	 * @param SDate
	 * @param EDate
	 * @param UserID
	 * @return
	 * @throws PopbillException
	 */
	public String requestJob(String CorpNum, String BankCode, String AccountNumber, String SDate, String EDate, String UserID) throws PopbillException;
	
	/**
	 * 수집 상태 목록 확인 
	 * 
	 * @param CorpNum
	 * @param JobID
	 * @return
	 * @throws PopbillException
	 */
	public EasyFinBankJobState getJobState(String CorpNum, String JobID) throws PopbillException;
	
	/**
	 * 수집 상태 확인 
	 * 
	 * @param CorpNum
	 * @param JobID
	 * @param UserID
	 * @return
	 * @throws PopbillException
	 */
	public EasyFinBankJobState getJobState(String CorpNum, String JobID, String UserID) throws PopbillException;
	
	
	/**
	 * 수집 상태 목록 확인 
	 * 
	 * @param CorpNum
	 * @return
	 * @throws PopbillException
	 */
	public EasyFinBankJobState[] listActiveJob(String CorpNum) throws PopbillException;
	
	
	/**
	 * 수집 상태 목록 확인 
	 * 
	 * @param CorpNum
	 * @param UserID
	 * @return
	 * @throws PopbillException
	 */
	public EasyFinBankJobState[] listActiveJob(String CorpNum, String UserID) throws PopbillException;
	
	/**
	 * 수집내역 조회
	 * @param CorpNum
	 * @param JobID
	 * @param TradeType
	 * @param SearchString
	 * @return
	 * @throws PopbillException
	 */
	public EasyFinBankSearchResult search(String CorpNum, String JobID, String[] TradeType, String SearchString, Integer Page, Integer PerPage, String Order) throws PopbillException;
	
	/**
	 * 수집내역 조회 
	 * @param CorpNum
	 * @param JobID
	 * @param TradeType
	 * @param SearchString
	 * @return
	 * @throws PopbillException
	 */
	public EasyFinBankSearchResult search(String CorpNum, String JobID, String[] TradeType, String SearchString, Integer Page, Integer PerPage, String Order, String UserID) throws PopbillException;
	
	/**
	 * 수집내역 요약정보 
	 * 
	 * @param CorpNum
	 * @param JobID
	 * @param TradeType
	 * @param SearchString
	 * @return
	 * @throws PopbillException
	 */
	public EasyFinBankSummary summary(String CorpNum, String JobID, String[] TradeType, String SearchString) throws PopbillException;
	
	/**
	 * 수집내역 요약정보 
	 * 
	 * @param CorpNum
	 * @param JobID
	 * @param TradeType
	 * @param SearchString
	 * @param UserID
	 * @return
	 * @throws PopbillException
	 */
	public EasyFinBankSummary summary(String CorpNum, String JobID, String[] TradeType, String SearchString, String UserID) throws PopbillException;
	
	/**
	  계좌 거래내역 메모 저장
	 * @param CorpNum
	 * @param TID
	 * @param Memo
	 * @return
	 * @throws PopbillException
	 */
	public Response saveMemo(String CorpNum, String TID, String Memo) throws PopbillException;
	
	/**
	 * 계좌 거래내역 메모 저장 
	 * @param CorpNum
	 * @param TID
	 * @param Memo
	 * @param UserID
	 * @return
	 * @throws PopbillException
	 */
	public Response saveMemo(String CorpNum, String TID, String Memo, String UserID) throws PopbillException;
	
	/**
	  정액제 서비스 상태 확인 
	 * @param CorpNum
	 * @param BankCode
	 * @param AccountNumber
	 * @return
	 * @throws PopbillException
	 */
	public FlatRateState getFlatRateState(String CorpNum, String BankCode, String AccountNumber) throws PopbillException;
	
	/**
	  정액제 서비스 상태 확인
	 * @param CorpNum
	 * @param BankCode
	 * @param AccountNumber
	 * @param UserID
	 * @return
	 * @throws PopbillException
	 */
	public FlatRateState getFlatRateState(String CorpNum, String BankCode, String AccountNumber, String UserID) throws PopbillException;
	
	/**
	 * 정액제 신청 URL
	 * @param CorpNum
	 * @return URL
	 * @throws PopbillException
	 */
	public String getFlatRatePopUpURL(String CorpNum) throws PopbillException;
	
	
	/**
	 * 정액제 신청 URL
	 * @param CorpNum
	 * @param UserID
	 * @return
	 * @throws PopbillException
	 */
	public String getFlatRatePopUpURL(String CorpNum, String UserID) throws PopbillException;
	
	
	/**
	 * 과금정보 확인
	 * @param CorpNum
	 * @return
	 * @throws PopbillException
	 */
	public ChargeInfo getChargeInfo(String CorpNum) throws PopbillException;
}

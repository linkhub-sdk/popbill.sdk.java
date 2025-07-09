package com.popbill.api;

import com.popbill.api.easyfin.EasyFinBankAccount;
import com.popbill.api.easyfin.EasyFinBankAccountForm;
import com.popbill.api.easyfin.EasyFinBankJobState;
import com.popbill.api.easyfin.EasyFinBankSearchResult;
import com.popbill.api.easyfin.EasyFinBankSummary;
import com.popbill.api.easyfin.UpdateEasyFinBankAccountForm;

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
     * @param CorpNum 팝빌회원 사업자번호 ('-' 제외)
     * @return
     * @throws PopbillException
     */
    public String getBankAccountMgtURL(String CorpNum) throws PopbillException;
    
    /**
     * 계좌 관리 팝업 URL
     * 
     * @param CorpNum 팝빌회원 사업자번호 ('-' 제외)
     * @param UserID 팝빌회원 아이디
     * @return
     * @throws PopbillException
     */
    public String getBankAccountMgtURL(String CorpNum, String UserID) throws PopbillException;
    
    
    /**
     * 계좌 등록
     * 
     * @param CorpNum 팝빌회원 사업자번호 ('-' 제외)
     * @param AccountForm 등록할 계좌 객체정보
     * @return
     * @throws PopbillException
     */
    public Response registBankAccount(String CorpNum, EasyFinBankAccountForm AccountForm) throws PopbillException;
    
    
    /**
     * 계좌 등록
     *
     * @param CorpNum 팝빌회원 사업자번호 ('-' 제외)
     * @param AccountForm 등록할 계좌 객체정보
     * @param UserID 팝빌회원 아이디
     * @return
     * @throws PopbillException
     */
    public Response registBankAccount(String CorpNum, EasyFinBankAccountForm AccountForm, String UserID) throws PopbillException;
    
    /**
     * 계좌정보 수정
     *
     * @param CorpNum 팝빌회원 사업자번호 ('-' 제외)
     * @param AccountForm 수정할 계좌 객체정보
     * @return
     * @throws PopbillException
     */
    public Response updateBankAccount(String CorpNum, EasyFinBankAccountForm AccountForm) throws PopbillException;
    
    /**
     * 계좌정보 수정
     *
     * @param CorpNum 팝빌회원 사업자번호 ('-' 제외)
     * @param AccountForm 수정할 계좌 객체정보
     * @param UserID 팝빌회원 아이디
     * @return
     * @throws PopbillException
     */
    public Response updateBankAccount(String CorpNum, EasyFinBankAccountForm AccountForm, String UserID) throws PopbillException;

    /**
     * 계좌정보 수정
     *
     * @param CorpNum 팝빌회원 사업자번호 ('-' 제외)
     * @param BankCode 은행 기관코드
     * @param AccountNumber 계좌번호
     * @param BankAccountInfo 수정할 계좌 객체정보
     * @return
     *
     * @throws PopbillException
     */
    public Response updateBankAccount(String CorpNum, String BankCode, String AccountNumber, UpdateEasyFinBankAccountForm BankAccountInfo) throws PopbillException;
    /**
     * 계좌정보 수정
     *
     * @param CorpNum 팝빌회원 사업자번호 ('-' 제외)
     * @param BankCode 은행 기관코드
     * @param AccountNumber 계좌번호
     * @param BankAccountInfo 수정할 계좌 객체정보
     * @param UserID 팝빌회원 아이디
     * @return
     *
     * @throws PopbillException
     */
    public Response updateBankAccount(String CorpNum, String BankCode, String AccountNumber, UpdateEasyFinBankAccountForm BankAccountInfo, String UserID) throws PopbillException;

    /**
     * 계좌 정액제 해지 요청
     * 
     * @param CorpNum 팝빌회원 사업자번호 ('-' 제외)
     * @param BankCode 은행 기관코드
     * @param AccountNumber 계좌번호
     * @param CloseType 정액제 해지 구분 ('일반')
     * @return
     * @throws PopbillException
     */
    public Response closeBankAccount(String CorpNum, String BankCode, String AccountNumber, String CloseType) throws PopbillException;
    
    /**
     * 계좌 정액제 해지 요청 
     *
     * @param CorpNum 팝빌회원 사업자번호 ('-' 제외)
     * @param BankCode 은행 기관코드
     * @param AccountNumber 계좌번호
     * @param CloseType 정액제 해지 구분 ('일반')
     * @param UserID 팝빌회원 아이디
     * @return
     * @throws PopbillException
     */
    public Response closeBankAccount(String CorpNum, String BankCode, String AccountNumber, String CloseType, String UserID) throws PopbillException;
    
    
    /**
     * 계좌 정액제 해지요청 취소
     *
     * @param CorpNum 팝빌회원 사업자번호 ('-' 제외)
     * @param BankCode 은행 기관코드
     * @param AccountNumber 계좌번호
     * @return
     * @throws PopbillException
     */
    public Response revokeCloseBankAccount(String CorpNum, String BankCode, String AccountNumber) throws PopbillException;
    
    
    /**
     * 계좌 정액제 해지요청 취소
     *
     * @param CorpNum 팝빌회원 사업자번호 ('-' 제외)
     * @param BankCode 은행 기관코드
     * @param AccountNumber 계좌번호
     * @param UserID 팝빌회원 아이디
     * @return
     * @throws PopbillException
     */
    public Response revokeCloseBankAccount(String CorpNum, String BankCode, String AccountNumber, String UserID) throws PopbillException;
    
    /**
     * 종량제 이용시 등록된 계좌 삭제
     *
     * @param CorpNum 팝빌회원 사업자번호 ('-' 제외)
     * @param BankCode 은행 기관코드
     * @param AccountNumber 계좌번호
     * @return
     * @throws PopbillException
     */
    public Response deleteBankAccount(String CorpNum, String BankCode, String AccountNumber) throws PopbillException;
    
    /**
     * 종량제 이용시 등록된 계좌 삭제
     *
     * @param CorpNum 팝빌회원 사업자번호 ('-' 제외)
     * @param BankCode 은행 기관코드
     * @param AccountNumber 계좌번호
     * @param UserID 팝빌회원 아이디
     * @return
     * @throws PopbillException
     */
    public Response deleteBankAccount(String CorpNum, String BankCode, String AccountNumber, String UserID) throws PopbillException;
    
    /**
     * 계좌정보 조회 
     *
     * @param CorpNum 팝빌회원 사업자번호 ('-' 제외)
     * @param BankCode 은행 기관코드
     * @param AccountNumber 계좌번호
     * @return EasyFinBankAccount Class
     * @throws PopbillException
     */
    public EasyFinBankAccount getBankAccountInfo(String CorpNum, String BankCode, String AccountNumber) throws PopbillException;

    /**
     * 계좌정보 조회
     * @param CorpNum 팝빌회원 사업자번호 ('-' 제외)
     * @param BankCode 은행 기관코드
     * @param AccountNumber 계좌번호
     * @param UserID 팝빌회원 아이디
     * @return EasyFinBankAccount Class
     * @throws PopbillException
     */
    public EasyFinBankAccount getBankAccountInfo(String CorpNum, String BankCode, String AccountNumber, String UserID) throws PopbillException;
    
    
    /**
     * 계좌 목록 조회 
     *
     * @param CorpNum 팝빌회원 사업자번호 ('-' 제외)
     * @return
     * @throws PopbillException
     */
    public EasyFinBankAccount[] listBankAccount(String CorpNum) throws PopbillException;
    
    /**
     * 계좌 목록 조회 
     *
     * @param CorpNum 팝빌회원 사업자번호 ('-' 제외)
     * @param UserID 팝빌회원 아이디
     * @return
     * @throws PopbillException
     */
    public EasyFinBankAccount[] listBankAccount(String CorpNum, String UserID) throws PopbillException;
    
    /**
     * 수집 요청 
     *
     * @param CorpNum 팝빌회원 사업자번호 ('-' 제외)
     * @param BankCode 은행 기관코드
     * @param AccountNumber 계좌번호
     * @param SDate 조회 기간의 시작일자
     * @param EDate 조회 기간의 종료일자
     * @return
     * @throws PopbillException
     */
    public String requestJob(String CorpNum, String BankCode, String AccountNumber, String SDate, String EDate) throws PopbillException;
    
    
    /**
     * 수집 요청 
     * @param CorpNum 팝빌회원 사업자번호 ('-' 제외)
     * @param BankCode 은행 기관코드
     * @param AccountNumber 계좌번호
     * @param SDate 조회 기간의 시작일자
     * @param EDate 조회 기간의 종료일자
     * @param UserID 팝빌회원 아이디
     * @return
     * @throws PopbillException
     */
    public String requestJob(String CorpNum, String BankCode, String AccountNumber, String SDate, String EDate, String UserID) throws PopbillException;
    
    /**
     * 수집 상태 목록 확인 
     *
     * @param CorpNum 팝빌회원 사업자번호 ('-' 제외)
     * @param JobID 작업아이디
     * @return
     * @throws PopbillException
     */
    public EasyFinBankJobState getJobState(String CorpNum, String JobID) throws PopbillException;
    
    /**
     * 수집 상태 확인 
     *
     * @param CorpNum 팝빌회원 사업자번호 ('-' 제외)
     * @param JobID 작업아이디
     * @param UserID 팝빌회원 아이디
     * @return
     * @throws PopbillException
     */
    public EasyFinBankJobState getJobState(String CorpNum, String JobID, String UserID) throws PopbillException;
    
    
    /**
     * 수집 상태 목록 확인 
     *
     * @param CorpNum 팝빌회원 사업자번호 ('-' 제외)
     * @return
     * @throws PopbillException
     */
    public EasyFinBankJobState[] listActiveJob(String CorpNum) throws PopbillException;
    
    
    /**
     * 수집 상태 목록 확인 
     *
     * @param CorpNum 팝빌회원 사업자번호 ('-' 제외)
     * @param UserID 팝빌회원 아이디
     * @return
     * @throws PopbillException
     */
    public EasyFinBankJobState[] listActiveJob(String CorpNum, String UserID) throws PopbillException;
    
    /**
     * 수집내역 조회
     * @param CorpNum 팝빌회원 사업자번호 ('-' 제외)
     * @param JobID 작업아이디
     * @param TradeType 거래유형 (다중선택 가능)
     * @param SearchString 조회 검색어
     * @return
     * @throws PopbillException
     */
    public EasyFinBankSearchResult search(String CorpNum, String JobID, String[] TradeType, String SearchString, Integer Page, Integer PerPage, String Order) throws PopbillException;
    
    /**
     * 수집내역 조회 
     * @param CorpNum 팝빌회원 사업자번호 ('-' 제외)
     * @param JobID 작업아이디
     * @param TradeType 거래유형 (다중선택 가능)
     * @param SearchString 조회 검색어
     * @param Page 목록 페이지 번호
     * @param PerPage 페이지당 표시할 목록 건수
     * @param Order 거래일자를 기준으로 하는 목록 정렬 방향
     * @param UserID 팝빌회원 아이디
     * @return
     * @throws PopbillException
     */
    public EasyFinBankSearchResult search(String CorpNum, String JobID, String[] TradeType, String SearchString, Integer Page, Integer PerPage, String Order, String UserID) throws PopbillException;
    
    /**
     * 수집내역 요약정보 
     *
     * @param CorpNum 팝빌회원 사업자번호 ('-' 제외)
     * @param JobID 작업아이디
     * @param TradeType 거래유형 (다중선택 가능)
     * @param SearchString 조회 검색어
     * @return
     * @throws PopbillException
     */
    public EasyFinBankSummary summary(String CorpNum, String JobID, String[] TradeType, String SearchString) throws PopbillException;
    
    /**
     * 수집내역 요약정보 
     *
     * @param CorpNum 팝빌회원 사업자번호 ('-' 제외)
     * @param JobID 작업아이디
     * @param TradeType 거래유형 (다중선택 가능)
     * @param SearchString 조회 검색어
     * @param UserID 팝빌회원 아이디
     * @return
     * @throws PopbillException
     */
    public EasyFinBankSummary summary(String CorpNum, String JobID, String[] TradeType, String SearchString, String UserID) throws PopbillException;
    
    /**
      계좌 거래내역 메모 저장
     * @param CorpNum 팝빌회원 사업자번호 ('-' 제외)
     * @param TID 메모를 저장할 거래내역 아이디
     * @param Memo 거래 내역에 저장할 메모
     * @return
     * @throws PopbillException
     */
    public Response saveMemo(String CorpNum, String TID, String Memo) throws PopbillException;
    
    /**
     * 계좌 거래내역 메모 저장 
     * @param CorpNum 팝빌회원 사업자번호 ('-' 제외)
     * @param TID 메모를 저장할 거래내역 아이디
     * @param Memo 거래 내역에 저장할 메모
     * @param UserID 팝빌회원 아이디
     * @return
     * @throws PopbillException
     */
    public Response saveMemo(String CorpNum, String TID, String Memo, String UserID) throws PopbillException;
    
    /**
      정액제 서비스 상태 확인 
     * @param CorpNum 팝빌회원 사업자번호 ('-' 제외)
     * @param BankCode 은행 기관코드
     * @param AccountNumber 은행 계좌번호(하이픈 '-' 제외)
     * @return
     * @throws PopbillException
     */
    public FlatRateState getFlatRateState(String CorpNum, String BankCode, String AccountNumber) throws PopbillException;
    
    /**
      정액제 서비스 상태 확인
     * @param CorpNum 팝빌회원 사업자번호 ('-' 제외)
     * @param BankCode 은행 기관코드
     * @param AccountNumber 은행 계좌번호(하이픈 '-' 제외)
     * @param UserID 팝빌회원 아이디
     * @return
     * @throws PopbillException
     */
    public FlatRateState getFlatRateState(String CorpNum, String BankCode, String AccountNumber, String UserID) throws PopbillException;
    
    /**
     * 정액제 신청 URL
     * @param CorpNum 팝빌회원 사업자번호 ('-' 제외)
     * @return URL
     * @throws PopbillException
     */
    public String getFlatRatePopUpURL(String CorpNum) throws PopbillException;
    
    
    /**
     * 정액제 신청 URL
     * @param CorpNum 팝빌회원 사업자번호 ('-' 제외)
     * @param UserID 팝빌회원 아이디
     * @return
     * @throws PopbillException
     */
    public String getFlatRatePopUpURL(String CorpNum, String UserID) throws PopbillException;
    
    
    /**
     * 과금정보 확인
     * @param CorpNum 팝빌회원 사업자번호 ('-' 제외)
     * @return
     * @throws PopbillException
     */
    public ChargeInfo getChargeInfo(String CorpNum) throws PopbillException;


    /**
     * 과금정보 확인
     * @param CorpNum 팝빌회원 사업자번호 ('-' 제외)
     * @param UserID 팝빌회원 아이디
     * @return
     * @throws PopbillException
     */
    public ChargeInfo getChargeInfo(String CorpNum, String UserID) throws PopbillException;
}
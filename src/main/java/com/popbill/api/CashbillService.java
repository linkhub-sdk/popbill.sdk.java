package com.popbill.api;

import java.util.List;

import com.popbill.api.cashbill.BulkCashbillResult;
import com.popbill.api.cashbill.CBSearchResult;
import com.popbill.api.cashbill.Cashbill;
import com.popbill.api.cashbill.CashbillInfo;
import com.popbill.api.cashbill.CashbillLog;

/**
 * Cashbill Service Interface.
 * 
 * @author JeongYoHan
 * @version 1.0.0
 */

public interface CashbillService extends BaseService{

    /**
     * 팝빌 현금영수증 관련 URL 확인, 반환된 URL은 30초 이내에 브라우저에 표시해야 함
     * @param CorpNum
     *          팝빌회원 사업자번호
     * @param TOGO
     *          지정값(TBOX : 임시문서함 URL, SBOX : 발행 문서함 URL)
     * @return 팝빌URL(AccessToken값 포함, Token값은 응답후 30초까지만 유효함)
     * @throws PopbillException
     */
    public String getURL(String CorpNum, String TOGO)
            throws PopbillException;
    
    /**
     * 팝빌 현금영수증 관련 URL 확인, 반환된 URL은 30초 이내에 브라우저에 표시해야 함
     * @param CorpNum
     *          팝빌회원 사업자번호
     * @param UserID
     *          팝빌회원 아이디
     * @param TOGO
     *          지정값(TBOX : 임시문서함 URL, SBOX : 발행 문서함 URL)
     * @return 팝빌URL(AccessToken값 포함, Token값은 응답후 30초까지만 유효함)
     * @throws PopbillException
     */
    public String getURL(String CorpNum, String UserID, String TOGO)
            throws PopbillException;
    
    /**
     * 현금영수증 발행단가 확인
     * @param CorpNum
     *          팝빌회원 사업자번호
     * @return 단가( ex. 10.0)
     * @throws PopbillException
     */
    public float getUnitCost(String CorpNum) 
            throws PopbillException;

    float getUnitCost(String CorpNum, String UserID) throws PopbillException;

    /**
     * 문서번호 사용여부 확인
     * @param CorpNum
     *          팝빌회원 사업자번호
     * @param MgtKey
     *          문서번호
     * @return 사용여부, true - 사용중 / false - 미사용
     * @throws PopbillException
     */
    public boolean checkMgtKeyInUse(String CorpNum, String MgtKey)
            throws PopbillException;

    /**
     * 문서번호 사용여부 확인
     * @param CorpNum
     *          팝빌회원 사업자번호
     * @param MgtKey
     *          문서번호
     * @param UserID
     *          팝빌회원 아이디
     * @return 사용여부, true - 사용중 / false - 미사용
     * @throws PopbillException
     */
    public boolean checkMgtKeyInUse(String CorpNum, String MgtKey, String UserID)
            throws PopbillException;

    /**
     * 현금영수증 1건 임시저장
     * @param CorpNum
     *          팝빌회원 사업자번호
     * @param Cashbill
     *          현금영수증 정보.(see. com.popbill.api.cashbill.Cashbill)
     * @return Response
     * @throws PopbillException
     */
    public Response register(String CorpNum, Cashbill Cashbill)
            throws PopbillException;
    
    /**
     * 현금영수증 1건 임시저장
     * @param CorpNum
     *          팝빌회원 사업자번호
     * @param Cashbill
     *          현금영수증 정보. (see. com.popbill.api.cashbill.Cashbill)
     * @param UserID
     *          팝빌회원아이디
     * @return Response
     * @throws PopbillException
     */
    public Response register(String CorpNum, Cashbill Cashbill, 
            String UserID) throws PopbillException;
    
    /**
     * 취소현금영수증 1건 임시저장
     * @param CorpNum
     *          팝빌회원 사업자번호
     * @param MgtKey
     *          취소현금영수증 문서번호
     * @param OrgConfirmNum
     *          당초 승인 현금영수증 승인번호
     * @param OrgTradeDate
     *          당초 승인 현금영수증 거래일자
     * @return Response
     * @throws PopbillException
     */ 
    public Response revokeRegister(String CorpNum, String MgtKey, String OrgConfirmNum,
            String OrgTradeDate) throws PopbillException;
    
    /**
     * 취소현금영수증 1건 임시저장
     * @param CorpNum
     *          팝빌회원 사업자번호
     * @param MgtKey
     *          취소현금영수증 문서번호
     * @param OrgConfirmNum
     *          당초 승인 현금영수증 승인번호
     * @param OrgTradeDate
     *          당초 승인 현금영수증 거래일자
     * @param SMSSendYN
     *          발행안내 문자전송여부
     * @return Response
     * @throws PopbillException
     */
    public Response revokeRegister(String CorpNum, String MgtKey, String OrgConfirmNum,
            String OrgTradeDate, Boolean SMSSendYN) throws PopbillException;
    
    /**
     * 취소현금영수증 1건 임시저장
     * @param CorpNum
     *          팝빌회원 사업자번호
     * @param MgtKey
     *          취소현금영수증 문서
     * @param OrgConfirmNum
     *          당초 승인 현금영수증 승인번호
     * @param OrgTradeDate
     *          당초 승인 현금영수증 거래일자
     * @param SMSSendYN
     *          발행안내 문자전송여부
     * @param UserID
     *          팝빌회원 아이디
     * @return Response
     * @throws PopbillException
     */
    public Response revokeRegister(String CorpNum, String MgtKey, String OrgConfirmNum,
            String OrgTradeDate, Boolean SMSSendYN, String UserID) throws PopbillException;
    
    /**
     * 취소현금영수증 1건 임시저장
     * @param CorpNum
     *          팝빌회원 사업자번호
     * @param MgtKey
     *          취소현금영수증 문서번호
     * @param OrgConfirmNum
     *          당초 승인 현금영수증 승인번호
     * @param OrgTradeDate
     *          당초 승인 현금영수증 거래일자
     * @param SMSSendYN
     *          발행안내 문자전송여부
     * @param IsPartCancel
     *          부분취소여부
     * @param CancelType
     *          취소사유
     * @param SupplyCost
     *          [취소] 공급가액
     * @param Tax
     *          [취소] 세액
     * @param ServiceFee
     *          [취소] 봉사료
     * @param TotalAmount 
     *          [취소] 합계금액
     * @return Response
     * @throws PopbillException
     */
    public Response revokeRegister(String CorpNum, String MgtKey, String OrgConfirmNum,
            String OrgTradeDate, Boolean SMSSendYN, Boolean IsPartCancel, Integer CancelType, 
            String SupplyCost, String Tax, String ServiceFee, String TotalAmount) throws PopbillException;

    /**
     * 취소현금영수증 1건 임시저장
     * @param CorpNum
     *          팝빌회원 사업자번호
     * @param MgtKey
     *          취소현금영수증 문서
     * @param OrgConfirmNum
     *          당초 승인 현금영수증 승인번호
     * @param OrgTradeDate
     *          당초 승인 현금영수증 거래일자
     * @param SMSSendYN
     *          발행안내 문자전송여부
     * @param IsPartCancel
     *          부분취소여부
     * @param CancelType
     *          취소사유
     * @param SupplyCost
     *          [취소] 공급가액
     * @param Tax
     *          [취소] 세액
     * @param ServiceFee
     *          [취소] 봉사료
     * @param TotalAmount 
     *          [취소] 합계금액
     * @param UserID
     *          팝빌회원 아이디
     * @return Response
     * @throws PopbillException
     */
    public Response revokeRegister(String CorpNum, String MgtKey, String OrgConfirmNum,
            String OrgTradeDate, Boolean SMSSendYN, Boolean IsPartCancel, Integer CancelType, 
            String SupplyCost, String Tax, String ServiceFee, String TotalAmount, String UserID) throws PopbillException;   
    
    /**
     * 취소현금영수증 1건 즉시발행
     * 
     * @param CorpNum
     *          팝빌회원 사업자번호
     * @param MgtKey
     *          취소현금영수증 문서번호
     * @param OrgConfirmNum
     *          당초 승인 현금영수증 승인번호
     * @param OrgTradeDate
     *          당초 승인 현금영수증 거래일자
     * @return CBIssueResponse
     * @throws PopbillException
     */
    public CBIssueResponse revokeRegistIssue(String CorpNum, String MgtKey, String OrgConfirmNum, 
            String OrgTradeDate) throws PopbillException;
    
    /**
     * 취소현금영수증 1건 즉시발행
     * 
     * @param CorpNum
     *          팝빌회원 사업자번호
     * @param MgtKey
     *          취소현금영수증 문서번호
     * @param OrgConfirmNum
     *          당초 승인 현금영수증 승인번호
     * @param OrgTradeDate
     *          당초 승인 현금영수증 거래일자
     * @param SMSSendYN
     *          발행안내 문자전송여부
     * @return CBIssueResponse
     * @throws PopbillException
     */
    public CBIssueResponse revokeRegistIssue(String CorpNum, String MgtKey, String OrgConfirmNum, 
            String OrgTradeDate, Boolean SMSSendYN) throws PopbillException;

    /**
     * 취소현금영수증 1건 즉시발행
     * 
     * @param CorpNum
     *          팝빌회원 사업자번호
     * @param MgtKey
     *          취소현금영수증 문서번호
     * @param OrgConfirmNum
     *          당초 승인 현금영수증 승인번호
     * @param OrgTradeDate
     *          당초 승인 현금영수증 거래일자
     * @param SMSSendYN
     *          발행안내 문자전송여부
     * @param Memo
     *          메모
     * @return CBIssueResponse
     * @throws PopbillException
     */
    public CBIssueResponse revokeRegistIssue(String CorpNum, String MgtKey, String OrgConfirmNum, 
            String OrgTradeDate, Boolean SMSSendYN, String Memo) throws PopbillException;
    
    /**
     * 취소현금영수증 1건 즉시발행
     * 
     * @param CorpNum
     *          팝빌회원 사업자번호
     * @param MgtKey
     *          취소현금영수증 문서번호
     * @param OrgConfirmNum
     *          당초 승인 현금영수증 승인번호
     * @param OrgTradeDate
     *          당초 승인 현금영수증 거래일자
     * @param SMSSendYN
     *          발행안내 문자전송여부
     * @param Memo
     *          메모
     * @param UserID
     *          팝빌회원 아이디
     * @return CBIssueResponse
     * @throws PopbillException
     */
    public CBIssueResponse revokeRegistIssue(String CorpNum, String MgtKey, String OrgConfirmNum, 
            String OrgTradeDate, Boolean SMSSendYN, String Memo, String UserID) throws PopbillException;
    /**
     * 취소현금영수증 1건 즉시발행
     * 
     * @param CorpNum
     *          팝빌회원 사업자번호
     * @param MgtKey
     *          취소현금영수증 문서번호
     * @param OrgConfirmNum
     *          당초 승인 현금영수증 승인번호
     * @param OrgTradeDate
     *          당초 승인 현금영수증 거래일자
     * @param SMSSendYN
     *          발행안내 문자전송여부
     * @param Memo
     *          메모
     * @param UserID
     *          팝빌회원 아이디
     * @param EmailSubject
     *          발행안내 메일 제목
     * @return CBIssueResponse
     * @throws PopbillException
     */
    public CBIssueResponse revokeRegistIssue(String CorpNum, String MgtKey, String OrgConfirmNum, 
            String OrgTradeDate, Boolean SMSSendYN, String Memo, String UserID, String EmailSubject) throws PopbillException;
    
    /**
     * 취소현금영수증 1건 즉시발행
     * 
     * @param CorpNum
     *          팝빌회원 사업자번호
     * @param MgtKey
     *          취소현금영수증 문서번호
     * @param OrgConfirmNum
     *          당초 승인 현금영수증 승인번호
     * @param OrgTradeDate
     *          당초 승인 현금영수증 거래일자
     * @param SMSSendYN
     *          발행안내 문자전송여부
     * @param Memo
     *          메모
     * @param IsPartCancel
     *          부분취소여부
     * @param CancelType
     *          취소사유
     * @param SupplyCost
     *          [취소] 공급가액
     * @param Tax
     *          [취소] 세액
     * @param ServiceFee
     *          [취소] 봉사료
     * @param TotalAmount 
     *          [취소] 합계금액
     * @return CBIssueResponse
     * @throws PopbillException
     */
    public CBIssueResponse revokeRegistIssue(String CorpNum, String MgtKey, String OrgConfirmNum, 
            String OrgTradeDate, Boolean SMSSendYN, String Memo, Boolean IsPartCancel,
            Integer CancelType, String SupplyCost, String Tax, String ServiceFee,
            String TotalAmount) throws PopbillException;
    
    /**
     * 취소현금영수증 1건 즉시발행
     * 
     * @param CorpNum
     *          팝빌회원 사업자번호
     * @param MgtKey
     *          취소현금영수증 문서번호
     * @param OrgConfirmNum
     *          당초 승인 현금영수증 승인번호
     * @param OrgTradeDate
     *          당초 승인 현금영수증 거래일자
     * @param SMSSendYN
     *          발행안내 문자전송여부
     * @param Memo
     *          메모
     * @param IsPartCancel
     *          부분취소여부
     * @param CancelType
     *          취소사유
     * @param SupplyCost
     *          [취소] 공급가액
     * @param Tax
     *          [취소] 세액
     * @param ServiceFee
     *          [취소] 봉사료
     * @param TotalAmount 
     *          [취소] 합계금액
     * @param UserID
     *          팝빌회원 아이디
     * @return CBIssueResponse
     * @throws PopbillException
     */
    public CBIssueResponse revokeRegistIssue(String CorpNum, String MgtKey, String OrgConfirmNum, 
            String OrgTradeDate, Boolean SMSSendYN, String Memo, Boolean IsPartCancel,
            Integer CancelType, String SupplyCost, String Tax, String ServiceFee,
            String TotalAmount, String UserID) throws PopbillException;
    
    /**
     * 취소현금영수증 1건 즉시발행
     * 
     * @param CorpNum
     *          팝빌회원 사업자번호
     * @param MgtKey
     *          취소현금영수증 문서번호
     * @param OrgConfirmNum
     *          당초 승인 현금영수증 승인번호
     * @param OrgTradeDate
     *          당초 승인 현금영수증 거래일자
     * @param SMSSendYN
     *          발행안내 문자전송여부
     * @param Memo
     *          메모
     * @param IsPartCancel
     *          부분취소여부
     * @param CancelType
     *          취소사유
     * @param SupplyCost
     *          [취소] 공급가액
     * @param Tax
     *          [취소] 세액
     * @param ServiceFee
     *          [취소] 봉사료
     * @param TotalAmount
     *          [취소] 공급가액합계금액
     * @param UserID
     *          팝빌회원 아이디
     * @param EmailSubject
     *          발행 안내메일 제목
     * @return CBIssueResponse
     * @throws PopbillException
     */
    public CBIssueResponse revokeRegistIssue(String CorpNum, String MgtKey, String OrgConfirmNum, String OrgTradeDate,
            Boolean SMSSendYN, String Memo, Boolean IsPartCancel, Integer CancelType, String SupplyCost, String Tax,
            String ServiceFee, String TotalAmount, String UserID, String EmailSubject) throws PopbillException;
    /**
     * 취소현금영수증 1건 즉시발행
     * 
     * @param CorpNum
     *          팝빌회원 사업자번호
     * @param MgtKey
     *          취소현금영수증 문서번호
     * @param OrgConfirmNum
     *          당초 승인 현금영수증 승인번호
     * @param OrgTradeDate
     *          당초 승인 현금영수증 거래일자
     * @param SMSSendYN
     *          발행안내 문자전송여부
     * @param Memo
     *          메모
     * @param IsPartCancel
     *          부분취소여부
     * @param CancelType
     *          취소사유
     * @param SupplyCost
     *          [취소] 공급가액
     * @param Tax
     *          [취소] 세액
     * @param ServiceFee
     *          [취소] 봉사료
     * @param TotalAmount
     *          [취소] 공급가액합계금액
     * @param UserID
     *          팝빌회원 아이디
     * @param EmailSubject
     *          발행 안내메일 제목
     * @param TradeDT
     *          거래일시
     * @return CBIssueResponse
     * @throws PopbillException
     */
    public CBIssueResponse revokeRegistIssue(String CorpNum, String MgtKey, String OrgConfirmNum, String OrgTradeDate,
            Boolean SMSSendYN, String Memo, Boolean IsPartCancel, Integer CancelType, String SupplyCost, String Tax,
            String ServiceFee, String TotalAmount, String UserID, String EmailSubject, String TradeDT) throws PopbillException;
    
    /**
     * 임시저장된 현금영수증 수정
     * @param CorpNum
     *          팝빌회원 사업자번호
     * @param MgtKey
     *          문서번호
     * @param Cashbill
     *          현금영수증 정보. (see. com.popbill.api.cashbill.Cashbill)
     * @return Response
     * @throws PopbillException
     */
    public Response update(String CorpNum, String MgtKey, 
            Cashbill Cashbill) throws PopbillException;
    
    /**
     * 임시저장된 현금영수증 수정
     * @param CorpNum
     *          팝빌회원 사업자번호
     * @param MgtKey
     *          문서번호
     * @param Cashbill
     *          현금영수증 정보. (see. com.popbill.api.cashbill.Cashbill)
     * @param UserID
     *          팝빌회원 아이디
     * @return Response
     * @throws PopbillException
     */
    public Response update(String CorpNum, String MgtKey, 
            Cashbill Cashbill, String UserID) throws PopbillException;
        
    /**
     * 현금영수증 삭제
     * @param CorpNum
     *          팝빌회원 사업자번호
     * @param MgtKey
     *          문서번호
     * @return Response
     * @throws PopbillException
     */
    public Response delete(String CorpNum, String MgtKey)
            throws PopbillException;
    
    /**
     * 현금영수증 삭제
     * @param CorpNum
     *          팝빌회원 사업자번호
     * @param MgtKey
     *          문서번호
     * @param UserID
     *          팝빌회원아이디
     * @return Response
     * @throws PopbillException
     */
    public Response delete(String CorpNum, String MgtKey,
            String UserID) throws PopbillException;

    /**
     * 현금영수증 발행
     * @param CorpNum
     *          팝빌회원 사업자번호
     * @param MgtKey
     *          문서번호
     * @param Memo
     *          처리 메모
     * @return CBIssueResponse
     * @throws PopbillException
     */
    public CBIssueResponse issue(String CorpNum, String MgtKey,
            String Memo) throws PopbillException;
    
    /**
     * 현금영수증 발행
     * @param CorpNum
     *          팝빌회원 사업자번호
     * @param MgtKey
     *          문서번호
     * @param Memo
     *          처리 메모
     * @param UserID
     *          팝빌회원 아이디
     * @return Response
     * @throws PopbillException
     */
    public CBIssueResponse issue(String CorpNum, String MgtKey,
            String Memo, String UserID) throws PopbillException;
    
    /**
     * 현금영수증 발행취소
     * @param CorpNum
     *          팝빌회원 사업자번호
     * @param MgtKey
     *          문서번호
     * @param Memo
     *          처리메모
     * @return CBIssueResponse
     * @throws PopbillException
     */
    public Response cancelIssue(String CorpNum, String MgtKey, 
            String Memo) throws PopbillException;
    
    /**
     * 현금영수증 발행취소
     * @param CorpNum
     *          팝빌회원 사업자번호
     * @param MgtKey
     *          문서번호
     * @param Memo
     *          처리메모
     * @param UserID
     *          팝빌회원 아이디
     * @return Response
     * @throws PopbillException
     */
    public Response cancelIssue(String CorpNum, String MgtKey, 
            String Memo, String UserID) throws PopbillException;
    
    /**
     * 현금영수증 알림메일 전송
     * @param CorpNum
     *          팝빌회원 사업자번호
     * @param MgtKey
     *          문서번호
     * @param Receiver
     *          수신 이메일주소
     * @return Response
     * @throws PopbillException
     */
    public Response sendEmail(String CorpNum, String MgtKey,
            String Receiver) throws PopbillException;
    
    /**
     * 현금영수증 알림메일 전송
     * @param CorpNum
     *          팝빌회원 사업자번호
     * @param MgtKey
     *          문서번호
     * @param Receiver
     *          수신 이메일주소
     * @param UserID
     *          팝빌회원 아이디
     * @return Response
     * @throws PopbillException
     */
    public Response sendEmail(String CorpNum, String MgtKey,
            String Receiver, String UserID) throws PopbillException;

    /**
     * 알림문자 전송
     * @param CorpNum
     *          팝빌회원 사업자번호
     * @param MgtKey
     *          문서번호
     * @param Sender
     *          발신번호
     * @param Receiver
     *          수신번호
     * @param Contents
     *          메시지내용
     * @return Response
     * @throws PopbillException
     */
    public Response sendSMS(String CorpNum, String MgtKey, String Sender, 
            String Receiver, String Contents) 
            throws PopbillException;
    
    /**
     * 알림문자 전송
     * @param CorpNum
     *          팝빌회원 사업자번호
     * @param MgtKey
     *          문서번호
     * @param Sender
     *          발신번호
     * @param Receiver
     *          수신번호
     * @param Contents
     *          메시지내용
     * @param UserID
     *          팝빌회원 아이디
     * @return Response
     * @throws PopbillException
     */
    public Response sendSMS(String CorpNum, String MgtKey, String Sender, 
            String Receiver, String Contents, String UserID) 
            throws PopbillException;
    
    /**
     * 현금영수증 팩스 전송
     * @param CorpNum
     *          팝빌회원 사업자번호
     * @param MgtKey
     *          문서번호
     * @param Sender
     *          발신번호
     * @param Receiver
     *          수신팩스번호
     * @return Response
     * @throws PopbillException
     */
    public Response sendFAX(String CorpNum, String MgtKey, String Sender,
            String Receiver) throws PopbillException;
    
    /**
     * 현금영수증 팩스 전송
     * @param CorpNum
     *          팝빌회원 사업자번호
     * @param MgtKey
     *          문서번호
     * @param Sender
     *          발신번호
     * @param Receiver
     *          수신팩스번호
     * @param UserID
     *          팝빌회원 아이디
     * @return Response
     * @throws PopbillException
     */
    public Response sendFAX(String CorpNum, String MgtKey, String Sender,
            String Receiver, String UserID) throws PopbillException;

    /**
     * 현금영수증 상세내역 조회
     * @param CorpNum
     *          팝빌회원 사업자번호
     * @param MgtKey
     *          문서번호
     * @return Cashbill
     * @throws PopbillException
     */
    public Cashbill getDetailInfo(String CorpNum, String MgtKey)
            throws PopbillException;

    /**
     * 현금영수증 상세내역 조회
     * @param CorpNum
     *          팝빌회원 사업자번호
     * @param MgtKey
     *          문서번호
     * @param UserID
     *          팝빌회원 아이디
     * @return Cashbill
     * @throws PopbillException
     */
    public Cashbill getDetailInfo(String CorpNum, String MgtKey, String UserID)
            throws PopbillException;

    /**
     * 현금영수증 요약/상태 정보 조회
     * @param CorpNum
     *          팝빌회원 사업자번호
     * @param MgtKey
     *          문서번호
     * @return CashbillInfo
     * @throws PopbillException
     */
    public CashbillInfo getInfo(String CorpNum, String MgtKey)
            throws PopbillException;

    /**
     * 현금영수증 요약/상태 정보 조회
     * @param CorpNum
     *          팝빌회원 사업자번호
     * @param MgtKey
     *          문서번호
     * @param UserID
     *          팝빌회원 아이디
     * @return CashbillInfo
     * @throws PopbillException
     */
    public CashbillInfo getInfo(String CorpNum, String MgtKey, String UserID)
            throws PopbillException;

    /**
     * 현금영수증 요약/상태 정보 대량 조회( 최대 1000건)
     * @param CorpNum
     *          팝빌회원 사업자번
     * @param MgtKeyList
     *          문서번호 배열
     * @return CashbillInfo 배열
     * @throws PopbillException
     */
    public CashbillInfo[] getInfos(String CorpNum, String[] MgtKeyList)
            throws PopbillException;

    /**
     * 현금영수증 요약/상태 정보 대량 조회( 최대 1000건)
     * @param CorpNum
     *          팝빌회원 사업자번
     * @param MgtKeyList
     *          문서번호 배열
     * @param UserID
     *          팝빌회원 아이디
     * @return CashbillInfo 배열
     * @throws PopbillException
     */
    public CashbillInfo[] getInfos(String CorpNum, String[] MgtKeyList, String UserID)
            throws PopbillException;

    /**
     * 현금영수증 문서 이력 조회
     * @param CorpNum
     *          팝빌회원 사업자번호
     * @param MgtKey
     *          문서번호
     * @return CashbillLog 배열
     * @throws PopbillException
     */
    public CashbillLog[] getLogs(String CorpNum, String MgtKey)
            throws PopbillException;

    /**
     * 현금영수증 인쇄 URL
     * @param CorpNum
     *          팝빌회원 사업자번호
     * @param MgtKey
     *          문서번호
     * @return 팝빌 url
     * @throws PopbillException
     */
    public String getPrintURL(String CorpNum, String MgtKey) throws PopbillException;
    
    /**
     * 현금영수증 인쇄 URL
     * @param CorpNum
     *          팝빌회원 사업자번호
     * @param MgtKey
     *          문서번호
     * @param UserID
     *          팝빌회원 아이디
     * @return 팝빌 url
     * @throws PopbillException
     */
    public String getPrintURL(String CorpNum, String MgtKey, 
            String UserID) throws PopbillException;
    
    
    /**
     * 현금영수증 인쇄 URL(공급받는자용)
     * @param CorpNum
     *          팝빌회원 사업자번호
     * @param MgtKey
     *          문서번호
     * @return 팝빌 URL
     * @throws PopbillException
     */
    public String getEPrintURL(String CorpNum, String MgtKey) throws PopbillException;
    
    /**
     * 현금영수증 인쇄 URL(공급받는자용)
     * @param CorpNum
     *          팝빌회원 사업자번호
     * @param MgtKey
     *          문서번호
     * @param UserID
     *          팝빌회원 아이디
     * @return 팝빌 URL
     * @throws PopbillException
     */
    public String getEPrintURL(String CorpNum, String MgtKey, 
            String UserID) throws PopbillException;
    
    
    /**
     * 다량 현금영수증 인쇄 URL 
     * @param CorpNum
     *          팝빌회원  사업자번호
     * @param MgtKeyList
     *          문서번호 배열
     * @return 팝빌 URL
     * @throws PopbillException
     */
    public String getMassPrintURL(String CorpNum, String[] MgtKeyList) throws PopbillException;
    
    
    /**
     * 다량 현금영수증 인쇄 URL 
     * @param CorpNum
     *          팝빌회원  사업자번호
     * @param MgtKeyList
     *          문서번호 배열
     * @param UserID
     *          팝빌회원 아이디
     * @return 팝빌 URL
     * @throws PopbillException
     */
    public String getMassPrintURL(String CorpNum, String[] MgtKeyList, 
            String UserID) throws PopbillException;
    
    /**
     * 공급받는자 메일링크 URL 
     * @param CorpNum
     *          팝빌회원 사업자번호
     * @param MgtKey
     *          문서번호
     * @return 팝빌 URL
     * @throws PopbillException
     */
    public String getMailURL(String CorpNum, String MgtKey) throws PopbillException;
    
    /**
     * 공급받는자 메일링크 URL 
     * @param CorpNum
     *          팝빌회원 사업자번호
     * @param MgtKey
     *          문서번호
     * @param UserID
     *          팝빌회원 아이디
     * @return 팝빌 URL
     * @throws PopbillException
     */
    public String getMailURL(String CorpNum, String MgtKey,
            String UserID) throws PopbillException;
    
    
    /**
     * 현금영수증 내용 보기 팝업 URL
     * @param CorpNum
     *          연동회원 사업자번호
     * @param MgtKey
     *          문서번호
     * @return 팝빌 URL
     * @throws PopbillException
     */
    public String getPopUpURL(String CorpNum, String MgtKey) throws PopbillException;
    
    /**
     * 현금영수증 내용 보기 팝업 URL
     * @param CorpNum
     *          연동회원 사업자번호
     * @param MgtKey
     *          문서번호
     * @param UserID
     *          팝빌회원 아이디
     * @return 팝빌 URL
     * @throws PopbillException
     */
    public String getPopUpURL(String CorpNum, String MgtKey, 
            String UserID) throws PopbillException;
    
      /**
     * 세금계산서 뷰 팝업 URL 
     * @param CorpNum
     *          연동회원 사업자번호
     * @param MgtKey
     *          문서번호
     * @return
     * @throws PopbillException
     */
    public String getViewURL(String CorpNum, String MgtKey) throws PopbillException;
    
    
     /**
     * 세금계산서 뷰 팝업 URL 
     * @param CorpNum
     *          연동회원 사업자번호
     * @param MgtKey
     *          문서번호
     * @param UserID
     *          팝빌회원 아이디
     * @return
     * @throws PopbillException
     */
    public String getViewURL(String CorpNum, String MgtKey, String UserID) throws PopbillException;
    
    /**
     * 현금영수증 즉시발행 
     * 
     * @param CorpNum
     *          연동회원 사업자번호 
     * @param Cashbill
     *          현금영수증 정보. (see com.popbill.api.cashbill.Cashbill)
     * @return Response 응답. 
     * @throws PopbillException
     */
    public CBIssueResponse registIssue(String CorpNum, Cashbill Cashbill)
            throws PopbillException;
    
    
    /**
     * 현금영수증 즉시발행 
     * 
     * @param CorpNum
     *          연동회원 사업자번호 
     * @param Cashbill
     *          현금영수증 정보. (see com.popbill.api.cashbill.Cashbill)
     * @param Memo
     *          메모
     * @return CBIssueResponse 응답. 
     * @throws PopbillException
     */
    public CBIssueResponse registIssue(String CorpNum, Cashbill Cashbill, String Memo)
            throws PopbillException;
    
    /**
     * 현금영수증 즉시발행 
     * 
     * @param CorpNum
     *          연동회원 사업자번호 
     * @param Cashbill
     *          현금영수증 정보. (see com.popbill.api.cashbill.Cashbill) 
     * @param Memo
     *          메모 
     * @param UserID
     *          팝빌회원 아이디 
     * @return CBIssueResponse 응답. 
     * @throws PopbillException
     */
    public CBIssueResponse registIssue(String CorpNum, Cashbill Cashbill, String Memo,
            String UserID) throws PopbillException;
    
    /**
     * 현금영수증 즉시발행
     * @param CorpNum
     *          연동회원 사업자번호
     * @param Cashbill
     *          현금영수증 Object
     * @param Memo
     *           메모
     * @param EmailSubject
     *          발행안내메일 제목
     * @param UserID
     *          팝빌회원 아이디
     * @return CBIssueResponse 응답 코드/메시지
     * @throws PopbillException
     */
    public CBIssueResponse registIssue(String CorpNum, Cashbill Cashbill, String Memo, String UserID, 
            String EmailSubject)
            throws PopbillException;

    /**
    * 
    * @param CorpNum
    *          연동회원 사업자번호
    * @param SubmitID
    *          접수 아이디
    * @param CashbillList
    *          현금영수증 배열
    * @return
    * @throws PopbillException
    */
   public BulkResponse bulkSubmit(String CorpNum, String SubmitID, List<Cashbill> CashbillList)
           throws PopbillException;
   
   /**
    * 
    * @param CorpNum
    *          연동회원 사업자번호
    * @param SubmitID
    *          접수 아이디
    * @param CashbillList
    *          현금영수증 배열
    * @param UserID
    *          팝빌회원 아이디
    * @return
    * @throws PopbillException
    */
   public BulkResponse bulkSubmit(String CorpNum, String SubmitID, List<Cashbill> CashbillList,
           String UserID)
           throws PopbillException;


    /**
     * 현금영수증 즉시발행 (승인번호 할당)
     *
     * @param CorpNum
     *          연동회원 사업자번호
     * @param Cashbill
     *          현금영수증 정보. (see com.popbill.api.cashbill.Cashbill)
     * @return Response 응답.
     * @throws PopbillException
     */
    public CBIssueResponse registIssueCN(String CorpNum, Cashbill Cashbill)
            throws PopbillException;


    /**
     * 현금영수증 즉시발행 (승인번호 할당)
     *
     * @param CorpNum
     *          연동회원 사업자번호
     * @param Cashbill
     *          현금영수증 정보. (see com.popbill.api.cashbill.Cashbill)
     * @param Memo
     *          메모
     * @return CBIssueResponse 응답.
     * @throws PopbillException
     */
    public CBIssueResponse registIssueCN(String CorpNum, Cashbill Cashbill, String Memo)
            throws PopbillException;

    /**
     * 현금영수증 즉시발행 (승인번호 할당)
     *
     * @param CorpNum
     *          연동회원 사업자번호
     * @param Cashbill
     *          현금영수증 정보. (see com.popbill.api.cashbill.Cashbill)
     * @param Memo
     *          메모
     * @param UserID
     *          팝빌회원 아이디
     * @return CBIssueResponse 응답.
     * @throws PopbillException
     */
    public CBIssueResponse registIssueCN(String CorpNum, Cashbill Cashbill, String Memo,
                                       String UserID) throws PopbillException;
    /**
     * 현금영수증 즉시발행 (승인번호 할당)
     *
     * @param CorpNum
     *          연동회원 사업자번호
     * @param Cashbill
     *          현금영수증 정보. (see com.popbill.api.cashbill.Cashbill)
     * @param Memo
     *          메모
     * @param UserID
     *          팝빌회원 아이디
     * @return CBIssueResponse 응답.
     * @throws PopbillException
     */
    public CBIssueResponse registIssueCN(String CorpNum, Cashbill Cashbill, String Memo,
                                       String UserID, String EmailSubject) throws PopbillException;

    /**
    * 현금영수증 대량발행 (승인번호 할당)
    *
    * @param CorpNum
    *          연동회원 사업자번호
    * @param SubmitID
    *          접수 아이디
    * @param CashbillList
    *          현금영수증 배열
    * @return
    * @throws PopbillException
    */
   public BulkResponse bulkSubmitCN(String CorpNum, String SubmitID, List<Cashbill> CashbillList)
           throws PopbillException;

   /**
    * 현금영수증 대량발행 (승인번호 할당)
    * @param CorpNum
    *          연동회원 사업자번호
    * @param SubmitID
    *          접수 아이디
    * @param CashbillList
    *          현금영수증 배열
    * @param UserID
    *          팝빌회원 아이디
    * @return
    * @throws PopbillException
    */
   public BulkResponse bulkSubmitCN(String CorpNum, String SubmitID, List<Cashbill> CashbillList,
           String UserID)
           throws PopbillException;


   /**
    * @param CorpNum
    *          연동회원 사업자번호
    * @param SubmitID
    *          접수 아이디
    * @return
    * @throws PopbillException
    */
   public BulkCashbillResult getBulkResult(String CorpNum, String SubmitID) throws PopbillException;
   
   /**
    * @param CorpNum
    *          연동회원 사업자번호
    * @param SubmitID
    *          접수 아이디
    * @param UserID
    *          팝빌회원 아이디
    * @return
    * @throws PopbillException
    */
   public BulkCashbillResult getBulkResult(String CorpNum, String SubmitID,
           String UserID) throws PopbillException;

    /**
     * 현금영수증 목록 조회 
     * 
     * @param CorpNum
     *          연동회원 사업자번호 
     * @param DType
     *          검색일자 유형 [R-등록일자, T-거래일자, I-발행일시]
     * @param SDate
     *          시작일자 (yyyyMMdd) 
     * @param EDate
     *          종료일자 (yyyyMMdd) 
     * @param State
     *          상태코드 배열
     * @param TradeType
     *          문서형태 배열 [N-승인거래, C-취소거래]
     * @param TradeUsage
     *          거래구분 배열 [P-소득공제용, C-지출증빙용]
     * @param TaxationType
     *          과세형태 배열 [T-과세, N-비과세]
     * @param Page
     *          페이지번호, 기본값 1 
     * @param PerPage
     *          페이지당 검색개수, 기본값 500, 최대 1000
     * @return 현금영수증 목록조회 결과. (see com.popbill.api.cashbill.CBSearchResult)
     * @throws PopbillException
     */
    public CBSearchResult search(String CorpNum, String DType, String SDate, 
            String EDate, String[] State, String[] TradeType, String[] TradeUsage,
            String[] TaxationType, Integer Page, Integer PerPage, String Order) throws PopbillException;

    /**
     * 현금영수증 목록 조회
     *
     * @param CorpNum
     *          연동회원 사업자번호
     * @param DType
     *          검색일자 유형 [R-등록일자, T-거래일자, I-발행일시]
     * @param SDate
     *          시작일자 (yyyyMMdd)
     * @param EDate
     *          종료일자 (yyyyMMdd)
     * @param State
     *          상태코드 배열
     * @param TradeType
     *          문서형태 배열 [N-승인거래, C-취소거래]
     * @param TradeUsage
     *          거래구분 배열 [P-소득공제용, C-지출증빙용]
     * @param TaxationType
     *          과세형태 배열 [T-과세, N-비과세]
     * @param QString
     *          식별번호, 휴대폰번호, 주민등록번호, 사업자등록번호 입력
     * @param Page
     *          페이지번호, 기본값 1
     * @param PerPage
     *          페이지당 검색개수, 기본값 500, 최대 1000
     * @return 현금영수증 목록조회 결과. (see com.popbill.api.cashbill.CBSearchResult)
     * @throws PopbillException
     */
    public CBSearchResult search(String CorpNum, String DType, String SDate, 
            String EDate, String[] State, String[] TradeType, String[] TradeUsage,
            String[] TaxationType, String QString, Integer Page, Integer PerPage, String Order) throws PopbillException;

    /**
     * 현금영수증 목록 조회
     *
     * @param CorpNum
     *          연동회원 사업자번호
     * @param DType
     *          검색일자 유형 [R-등록일자, T-거래일자, I-발행일시]
     * @param SDate
     *          시작일자 (yyyyMMdd)
     * @param EDate
     *          종료일자 (yyyyMMdd)
     * @param State
     *          상태코드 배열
     * @param TradeType
     *          문서형태 배열 [N-승인거래, C-취소거래]
     * @param TradeUsage
     *          거래구분 배열 [P-소득공제용, C-지출증빙용]
     * @param TradeOpt
     *          거래유형 배열 [N-일반, B-도서공연, T-대중교통]
     * @param TaxationType
     *          과세형태 배열 [T-과세, N-비과세]
     * @param QString
     *          식별번호, 휴대폰번호, 주민등록번호, 사업자등록번호 입력
     * @param Page
     *          페이지번호, 기본값 1
     * @param PerPage
     *          페이지당 검색개수, 기본값 500, 최대 1000
     * @return 현금영수증 목록조회 결과. (see com.popbill.api.cashbill.CBSearchResult)
     * @throws PopbillException
     */
    public CBSearchResult search(String CorpNum, String DType, String SDate,
                                 String EDate, String[] State, String[] TradeType, String[] TradeUsage, String[] TradeOpt,
                                 String[] TaxationType, String QString, Integer Page, Integer PerPage, String Order) throws PopbillException;

    /**
     * 현금영수증 목록 조회
     *
     * @param CorpNum
     *          연동회원 사업자번호
     * @param DType
     *          검색일자 유형 [R-등록일자, T-거래일자, I-발행일시]
     * @param SDate
     *          시작일자 (yyyyMMdd)
     * @param EDate
     *          종료일자 (yyyyMMdd)
     * @param State
     *          상태코드 배열
     * @param TradeType
     *          문서형태 배열 [N-승인거래, C-취소거래]
     * @param TradeUsage
     *          거래구분 배열 [P-소득공제용, C-지출증빙용]
     * @param TradeOpt
     *          거래유형 배열 [N-일반, B-도서공연, T-대중교통]
     * @param TaxationType
     *          과세형태 배열 [T-과세, N-비과세]
     * @param QString
     *          식별번호, 휴대폰번호, 주민등록번호, 사업자등록번호 입력
     * @param Page
     *          페이지번호, 기본값 1
     * @param PerPage
     *          페이지당 검색개수, 기본값 500, 최대 1000
     * @param FranchiseTaxRegID
     *          가맹점 종사업장 번호
     * @return 현금영수증 목록조회 결과. (see com.popbill.api.cashbill.CBSearchResult)
     * @throws PopbillException
     */
    public CBSearchResult search(String CorpNum, String DType, String SDate,
                                 String EDate, String[] State, String[] TradeType, String[] TradeUsage, String[] TradeOpt,
                                 String[] TaxationType, String QString, Integer Page, Integer PerPage, String Order, String FranchiseTaxRegID) throws PopbillException;

    /**
     * 현금영수증 목록 조회
     *
     * @param CorpNum
     *          연동회원 사업자번호
     * @param DType
     *          검색일자 유형 [R-등록일자, T-거래일자, I-발행일시]
     * @param SDate
     *          시작일자 (yyyyMMdd)
     * @param EDate
     *          종료일자 (yyyyMMdd)
     * @param State
     *          상태코드 배열
     * @param TradeType
     *          문서형태 배열 [N-승인거래, C-취소거래]
     * @param TradeUsage
     *          거래구분 배열 [P-소득공제용, C-지출증빙용]
     * @param TradeOpt
     *          거래유형 배열 [N-일반, B-도서공연, T-대중교통]
     * @param TaxationType
     *          과세형태 배열 [T-과세, N-비과세]
     * @param QString
     *          식별번호, 휴대폰번호, 주민등록번호, 사업자등록번호 입력
     * @param Page
     *          페이지번호, 기본값 1
     * @param PerPage
     *          페이지당 검색개수, 기본값 500, 최대 1000
     * @param FranchiseTaxRegID
     *          가맹점 종사업장 번호
     * @param UserID
     *          팝빌회원 아이디
     * @return 현금영수증 목록조회 결과. (see com.popbill.api.cashbill.CBSearchResult)
     * @throws PopbillException
     */
    public CBSearchResult search(String CorpNum, String DType, String SDate,
                                 String EDate, String[] State, String[] TradeType, String[] TradeUsage, String[] TradeOpt,
                                 String[] TaxationType, String QString, Integer Page, Integer PerPage, String Order, String FranchiseTaxRegID, String UserID) throws PopbillException;

    /**
     *  과금정보 확인
     *  
     * @param CorpNum
     *          연동회원 사업자번호
     * @return ChargeInfo 과금정보.
     * @throws PopbillException
     */
    public ChargeInfo getChargeInfo(String CorpNum) throws PopbillException;

    /**
     *  과금정보 확인
     *
     * @param CorpNum
     *          연동회원 사업자번호
     * @param UserID
     *          팝빌회원 아이디
     * @return ChargeInfo 과금정보.
     * @throws PopbillException
     */
    public ChargeInfo getChargeInfo(String CorpNum, String UserID) throws PopbillException;

    /**
     * 알림메일 전송설정 수정
     * 
     * @param CorpNum
     *          연동회원 사업자번호
     * @param EmailType
     *          메일전송유형
     * @param SendYN
     *          전송 여부 (True = 전송, False = 미전송)
     * @return Response.
     * @throws PopbillException
     */
    public Response updateEmailConfig(String CorpNum, String EmailType, Boolean SendYN)
            throws PopbillException;

    /**
     *  알림메일 전송설정 수정
     *  
     * @param CorpNum
     *          연동회원 사업자번호
     * @param EmailType
     *          메일전송유형
     * @param SendYN
     *          전송 여부 (True = 전송, False = 미전송)
     * @param UserID
     *          팝빌회원 아이디 
     * @return Response.
     * @throws PopbillException
     */
    public Response updateEmailConfig(String CorpNum, String EmailType, Boolean SendYN, 
            String UserID) throws PopbillException;
    
    /**
     *  알림메일 전송목록 조회
     *  
     * @param CorpNum
     *          연동회원 사업자번호
     * @return EmailSendConfig 배열.
     * @throws PopbillException
     */
    public EmailSendConfig[] listEmailConfig(String CorpNum) throws PopbillException;   
    
    /**
     *  알림메일 전송목록 조회
     *  
     * @param CorpNum
     *          연동회원 사업자번호
     * @param UserID
     *          팝빌회원 아이디 
     * @return EmailSendConfig 배열.
     * @throws PopbillException
     */
    public EmailSendConfig[] listEmailConfig(String CorpNum, String UserID) throws PopbillException;

    /**
     * 
     * @param CorpNum 연동회원 사업자번호
     * @param MgtKey 문서번호
     * @return
     * @throws PopbillException
     */
    String getPDFURL(String CorpNum, String MgtKey) throws PopbillException;

    /**
     * 
     * @param CorpNum 연동회원 사업자번호
     * @param MgtKey 문서번호
     * @param UserID 팝빌회원 아이디
     * @return
     * @throws PopbillException
     */
    String getPDFURL(String CorpNum, String MgtKey, String UserID) throws PopbillException;

    /**
     * 
     * @param CorpNum 연동회원 사업자번호
     * @param ItemKey 팝빌에서 현금영수증 관리 목적으로 할당한 식별번호
     * @param MgtKey 파트너가 할당한 문서번호
     * @return
     * @throws PopbillException
     */
    Response assignMgtKey(String CorpNum, String ItemKey, String MgtKey) throws PopbillException;

    /**
     * 
     * @param CorpNum 연동회원 사업자번호
     * @param ItemKey 팝빌에서 현금영수증 관리 목적으로 할당한 식별번호
     * @param MgtKey 파트너가 할당한 문서번호
     * @param UserID 팝빌회원 아이디
     * @return
     * @throws PopbillException
     */
    Response assignMgtKey(String CorpNum, String ItemKey, String MgtKey, String UserID) throws PopbillException;

    /**
     * 
     * @param CorpNum
     *          연동회원 사업자 번호
     * @param MgtKey
     *          문서관리 번호         
     * @return
     * @throws PopbillException
     */
    byte[] getPDF(String CorpNum, String MgtKey) throws PopbillException;
    
    /**
     * 
     * @param CorpNum
     *          연동회원 사업자 번호
     * @param Mgtkey
     *          문서관리 번호
     * @param UserID
     *          팝빌회원 아이디 
     * @return
     * @throws PopbillException
     */
    byte[] getPDF(String CorpNum, String Mgtkey, String UserID) throws PopbillException;
}
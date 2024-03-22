package com.popbill.api;

import java.io.File;
import java.util.Date;

import com.popbill.api.fax.FAXSearchResult;
import com.popbill.api.fax.FaxResult;
import com.popbill.api.fax.Receiver;
import com.popbill.api.fax.SenderNumber;

/**
 * Fax Service Interface.
 * 
 * @author KimSeongjun
 * @version 1.0.0
 */
public interface FaxService extends BaseService {

    /**
     * 회원의 팩스 전송단가 확인
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @return 단가 (ex. 55.0)
     * @throws PopbillException
     */
    public float getUnitCost(String CorpNum) throws PopbillException;
    
    /**
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param ReceiveNumType
     *            수신번호 유형
     * @return 단가 (ex 55.0)
     * @throws PopbillException
     */
    public float getUnitCost(String CorpNum, String ReceiveNumType) throws PopbillException;

    /**
     *
     * @param CorpNum
     *            연동회원 사업자번호
     * @param ReceiveNumType
     *            수신번호 유형
     * @param UserID
     *            팝빌회원 아이디
     * @return 단가 (ex 55.0)
     * @throws PopbillException
     */
    public float getUnitCost(String CorpNum, String ReceiveNumType, String UserID) throws PopbillException;

    /**
     * 팝빌 팩스전송 관련 URL 확인. 반환한 url은 30초이내에 브라우져에 표시하여야 함.
     * 
     * @param CorpNum
     *            연동회원 사업자번호.
     * @param TOGO
     *            지정값. (BOX : 팩스 전송 내역 조회 팝업)
     * @return 팝빌 URL (AccessToken값 포함. Token값은 응답후 30초까지만 유효함)
     * @throws PopbillException
     */
    public String getURL(String CorpNum, String TOGO)
            throws PopbillException;
    
    /**
     * 팝빌 팩스전송 관련 URL 확인. 반환한 url은 30초이내에 브라우져에 표시하여야 함.
     * 
     * @param CorpNum
     *            연동회원 사업자번호.
     * @param UserID
     *            연동회원의 회원아이디
     * @param TOGO
     *            지정값. (BOX : 팩스 전송 내역 조회 팝업)
     * @return 팝빌 URL (AccessToken값 포함. Token값은 응답후 30초까지만 유효함)
     * @throws PopbillException
     */
    public String getURL(String CorpNum, String UserID, String TOGO)
            throws PopbillException;

    /**
     * 팩스전송 단일파일 단일 수신자.
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param SendNum
     *            발신번호
     * @param ReceiveNum
     *            수신번호
     * @param ReceiveName
     *            수신자 명칭
     * @param File
     *            전송파일
     * @param ReserveDT
     *            예약일시, (형태 yyyyMMddHHmmss)
     * @return receiptNum 접수번호
     * @throws PopbillException
     */
    public String sendFAX(String CorpNum, String SendNum, String ReceiveNum,
            String ReceiveName, File File, Date ReserveDT)
            throws PopbillException;

    /**
     * 팩스 전송 (바이너리)
     *
     * @param CorpNum
     *            연동회원 사업자번호
     * @param SendNum
     *            발신번호
     * @param ReceiveNum
     *            수신번호
     * @param ReceiveName
     *            수신자명
     * @param File
     *            전송할 파일의 객체정보
     * @param ReserveDT
     *            예약일시, (형태 yyyyMMddHHmmss)
     * @return receiptNum 접수번호
     * @throws PopbillException
     */
    public String sendFAXBinary(String CorpNum, String SendNum, String ReceiveNum,
            String ReceiveName, FaxUploadFile File, Date ReserveDT)
            throws PopbillException;
    /**
     * 팩스전송 단일파일 단일 수신자. (광고팩스)
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param SendNum
     *            발신번호
     * @param ReceiveNum
     *            수신번호
     * @param ReceiveName
     *            수신자 명칭
     * @param File
     *            전송파일
     * @param ReserveDT
     *            예약일시, (형태 yyyyMMddHHmmss)
     * @param AdsYN
     *            광고팩스 전송여부
     * @return receiptNum 접수번호
     * @throws PopbillException
     */
    public String sendFAX(String CorpNum, String SendNum, String ReceiveNum,
            String ReceiveName, File File, Date ReserveDT, Boolean AdsYN)
            throws PopbillException;


    /**
     * 팩스 전송 (바이너리)
     *
     * @param CorpNum
     *            연동회원 사업자번호
     * @param SendNum
     *            발신번호
     * @param ReceiveNum
     *            수신번호
     * @param ReceiveName
     *            수신자명
     * @param File
     *            전송할 파일의 객체정보
     * @param ReserveDT
     *            예약일시, (형태 yyyyMMddHHmmss)
     * @param AdsYN
     *            광고팩스 전송여부
     * @return receiptNum 접수번호
     * @throws PopbillException
     */
    public String sendFAXBinary(String CorpNum, String SendNum, String ReceiveNum,
            String ReceiveName, FaxUploadFile File, Date ReserveDT, Boolean AdsYN)
            throws PopbillException;
    
    /**
     * 팩스전송 단일파일 단일 수신자. (광고팩스)
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param SendNum
     *            발신번호
     * @param ReceiveNum
     *            수신번호
     * @param ReceiveName
     *            수신자 명칭
     * @param File
     *            전송파일
     * @param ReserveDT
     *            예약일시, (형태 yyyyMMddHHmmss)
     * @param AdsYN
     *            광고팩스 전송여부
     * @param RequestNum
     *            전송요청번호
     * @return receiptNum 접수번호
     * @throws PopbillException
     */
    public String sendFAX(String CorpNum, String SendNum, String ReceiveNum,
            String ReceiveName, File File, Date ReserveDT, Boolean AdsYN, String RequestNum)
            throws PopbillException;


    /**
     * 팩스 전송 (바이너리)
     *
     * @param CorpNum
     *            연동회원 사업자번호
     * @param SendNum
     *            발신번호
     * @param ReceiveNum
     *            수신번호
     * @param ReceiveName
     *            수신자명
     * @param File
     *            전송할 파일의 객체정보
     * @param ReserveDT
     *            예약일시, (형태 yyyyMMddHHmmss)
     * @param AdsYN
     *            광고팩스 전송여부
     * @param RequestNum
     *          전송요청번호
     * @return receiptNum 접수번호
     * @throws PopbillException
     */
    public String sendFAXBinary(String CorpNum, String SendNum, String ReceiveNum,
            String ReceiveName, FaxUploadFile File, Date ReserveDT, Boolean AdsYN, String RequestNum)
            throws PopbillException;
    /**
     * 팩스전송 단일파일 단일 수신자.
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param SendNum
     *            발신번호
     * @param ReceiveNum
     *            수신번호
     * @param ReceiveName
     *            수신자 명칭
     * @param File
     *            전송파일
     * @param ReserveDT
     *            예약일시, (형태 yyyyMMddHHmmss)
     * @param UserID
     *            연동회원 아이디
     * @return receiptNum 접수번호
     * @throws PopbillException
     */
    public String sendFAX(String CorpNum, String SendNum, String ReceiveNum,
            String ReceiveName, File File, Date ReserveDT, String UserID)
            throws PopbillException;


    /**
     * 팩스 전송 (바이너리)
     *
     * @param CorpNum
     *            연동회원 사업자번호
     * @param SendNum
     *            발신번호
     * @param ReceiveNum
     *            수신번호
     * @param ReceiveName
     *            수신자명
     * @param File
     *            전송할 파일의 객체정보
     * @param ReserveDT
     *            예약일시, (형태 yyyyMMddHHmmss)
     * @param UserID
     *            연동회원 아이디
     * @return receiptNum 접수번호
     * @throws PopbillException
     */
    public String sendFAXBinary(String CorpNum, String SendNum, String ReceiveNum,
            String ReceiveName, FaxUploadFile File, Date ReserveDT, String UserID)
            throws PopbillException;
    
    /**
     * 팩스전송 단일파일 단일 수신자 (광고팩스).
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param SendNum
     *            발신번호
     * @param ReceiveNum
     *            수신번호
     * @param ReceiveName
     *            수신자 명칭
     * @param File
     *            전송파일
     * @param ReserveDT
     *            예약일시, (형태 yyyyMMddHHmmss)
     * @param UserID
     *            연동회원 아이디
     * @param AdsYN
     *            광고팩스 전송여부
     * @return receiptNum 접수번호
     * @throws PopbillException
     */
    public String sendFAX(String CorpNum, String SendNum, String ReceiveNum,
            String ReceiveName, File File, Date ReserveDT, String UserID, Boolean AdsYN)
            throws PopbillException;


    /**
     * 팩스 전송 (바이너리)
     *
     * @param CorpNum
     *            연동회원 사업자번호
     * @param SendNum
     *            발신번호
     * @param ReceiveNum
     *            수신번호
     * @param ReceiveName
     *            수신자명
     * @param File
     *            전송할 파일의 객체정보
     * @param ReserveDT
     *            예약일시, (형태 yyyyMMddHHmmss)
     * @param UserID
     *            연동회원 아이디
     * @param AdsYN
     *            광고팩스 전송여부
     * @return receiptNum 접수번호
     * @throws PopbillException
     */
    public String sendFAXBinary(String CorpNum, String SendNum, String ReceiveNum,
            String ReceiveName, FaxUploadFile File, Date ReserveDT, String UserID, Boolean AdsYN)
            throws PopbillException;
    /**
     * 팩스전송 단일파일 단일 수신자 (팩스제목)
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param SendNum
     *            발신번호
     * @param ReceiveNum
     *            수신번호
     * @param ReceiveName
     *            수신자 명칭
     * @param File
     *            전송파일
     * @param ReserveDT
     *            예약일시, (형태 yyyyMMddHHmmss)
     * @param UserID
     *            연동회원 아이디
     * @param AdsYN
     *            광고팩스 전송여부
     * @param Title
     *            팩스 제목
     * @return receiptNum 접수번호
     * @throws PopbillException
     */
    public String sendFAX(String CorpNum, String SendNum, String ReceiveNum,
            String ReceiveName, File File, Date ReserveDT, String UserID, Boolean AdsYN,
            String Title)
            throws PopbillException;


    /**
     * 팩스 전송 (바이너리)
     *
     * @param CorpNum
     *            연동회원 사업자번호
     * @param SendNum
     *            발신번호
     * @param ReceiveNum
     *            수신번호
     * @param ReceiveName
     *            수신자명
     * @param File
     *            전송할 파일의 객체정보
     * @param ReserveDT
     *            예약일시, (형태 yyyyMMddHHmmss)
     * @param UserID
     *            연동회원 아이디
     * @param AdsYN
     *            광고팩스 전송여부
     * @param Title
     *          팩스제목
     * @return receiptNum 접수번호
     * @throws PopbillException
     */
    public String sendFAXBinary(String CorpNum, String SendNum, String ReceiveNum,
            String ReceiveName, FaxUploadFile File, Date ReserveDT, String UserID, Boolean AdsYN,
            String Title)
            throws PopbillException;
    /**
     * 팩스전송 단일파일 단일 수신자 (팩스제목)
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param SendNum
     *            발신번호
     * @param ReceiveNum
     *            수신번호
     * @param ReceiveName
     *            수신자 명칭
     * @param File
     *            전송파일
     * @param ReserveDT
     *            예약일시, (형태 yyyyMMddHHmmss)
     * @param UserID
     *            연동회원 아이디
     * @param AdsYN
     *            광고팩스 전송여부
     * @param Title
     *            팩스 제목
     * @param RequestNum
     *            전송요청번호
     * @return receiptNum 접수번호
     * @throws PopbillException
     */
    public String sendFAX(String CorpNum, String SendNum, String ReceiveNum,
            String ReceiveName, File File, Date ReserveDT, String UserID, Boolean AdsYN,
            String Title, String RequestNum)
            throws PopbillException;

    /**
     * 팩스 전송 (바이너리)
     *
     * @param CorpNum
     *            연동회원 사업자번호
     * @param SendNum
     *            발신번호
     * @param ReceiveNum
     *            수신번호
     * @param ReceiveName
     *            수신자명
     * @param Files
     *            전송할 파일의 객체정보
     * @param ReserveDT
     *            예약일시, (형태 yyyyMMddHHmmss)
     * @param UserID
     *            연동회원 아이디
     * @param AdsYN
     *            광고팩스 전송여부
     * @param Title
     *          팩스제목
     * @param RequestNum
     *          전송요청번호
     * @return receiptNum 접수번호
     * @throws PopbillException
     */
    public String sendFAXBinary(String CorpNum, String SendNum, String ReceiveNum,
            String ReceiveName, FaxUploadFile File, Date ReserveDT, String UserID, Boolean AdsYN,
            String Title, String RequestNum)
            throws PopbillException;

    /**
     * 팩스전송 단일파일 동보전송.
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param SendNum
     *            발신번호
     * @param Receivers
     *            동보수신자 배열
     * @param File
     *            전송파일
     * @param ReserveDT
     *            예약일시, (형태 yyyyMMddHHmmss)
     * @return receiptNum 접수번호
     * @throws PopbillException
     */
    public String sendFAX(String CorpNum, String SendNum, Receiver[] Receivers,
            File File, Date ReserveDT) throws PopbillException;


    /**
     * 팩스 전송 (바이너리 동보전송)
     *
     * @param CorpNum
     *            연동회원 사업자번호
     * @param SendNum
     *            발신번호
     * @param Receivers
     *            동보수신자 배열
     * @param File
     *            전송할 파일의 객체 정보
     * @param ReserveDT
     *            예약일시, (형태 yyyyMMddHHmmss)
     *          전송요청번호
     * @return receiptNum 접수번호
     * @throws PopbillException
     */
    public String sendFAXBinary(String CorpNum, String SendNum, Receiver[] Receivers,
            FaxUploadFile File, Date ReserveDT) throws PopbillException;
    /**
     * 팩스전송 단일파일 동보전송. (광고팩스)
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param SendNum
     *            발신번호
     * @param Receivers
     *            동보수신자 배열
     * @param File
     *            전송파일
     * @param ReserveDT
     *            예약일시, (형태 yyyyMMddHHmmss)
     * @param AdsYN
     *            광고팩스 전송여부
     * @return receiptNum 접수번호
     * @throws PopbillException
     */
    public String sendFAX(String CorpNum, String SendNum, Receiver[] Receivers,
            File File, Date ReserveDT, Boolean AdsYN) throws PopbillException;

    /**
     * 팩스 전송 (바이너리 동보전송)
     *
     * @param CorpNum
     *            연동회원 사업자번호
     * @param SendNum
     *            발신번호
     * @param Receivers
     *            동보수신자 배열
     * @param File
     *            전송할 파일의 객체정보
     * @param ReserveDT
     *            예약일시, (형태 yyyyMMddHHmmss)
     * @param AdsYN
     *            광고팩스 전송여부
     * @return receiptNum 접수번호
     * @throws PopbillException
     */
    public String sendFAXBinary(String CorpNum, String SendNum, Receiver[] Receivers,
            FaxUploadFile File, Date ReserveDT, Boolean AdsYN) throws PopbillException; 
    
    /**
     * 팩스전송 단일파일 동보전송. (팩스제목)
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param SendNum
     *            발신번호
     * @param Receivers
     *            동보수신자 배열
     * @param File
     *            전송파일
     * @param ReserveDT
     *            예약일시, (형태 yyyyMMddHHmmss)
     * @param AdsYN
     *            광고팩스 전송여부
     * @param Title
     *            팩스제목
     * @return receiptNum 접수번호
     * @throws PopbillException
     */
    public String sendFAX(String CorpNum, String SendNum, Receiver[] Receivers,
            File File, Date ReserveDT, Boolean AdsYN, String Title) throws PopbillException;

    /**
     * 팩스 전송 (바이너리 동보전송)
     *
     * @param CorpNum
     *            연동회원 사업자번호
     * @param SendNum
     *            발신번호
     * @param Receivers
     *            동보수신자 배열
     * @param File
     *            전송할 파일의 객체정보
     * @param ReserveDT
     *            예약일시, (형태 yyyyMMddHHmmss)
     * @param AdsYN
     *            광고팩스 전송여부
     * @param Title
     *          팩스제목
     * @return receiptNum 접수번호
     * @throws PopbillException
     */
    public String sendFAXBinary(String CorpNum, String SendNum, Receiver[] Receivers,
            FaxUploadFile File, Date ReserveDT, Boolean AdsYN, String Title) throws PopbillException;
    
    /**
     * 팩스전송 단일파일 동보전송. (팩스제목)
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param SendNum
     *            발신번호
     * @param Receivers
     *            동보수신자 배열
     * @param File
     *            전송파일
     * @param ReserveDT
     *            예약일시, (형태 yyyyMMddHHmmss)
     * @param AdsYN
     *            광고팩스 전송여부
     * @param Title
     *            팩스제목
     * @param RequestNum
     *            전송요청번호
     * @return receiptNum 접수번호
     * @throws PopbillException
     */
    public String sendFAX(String CorpNum, String SendNum, Receiver[] Receivers,
            File File, Date ReserveDT, Boolean AdsYN, String Title, String RequestNum) throws PopbillException;

    /**
     * 팩스 전송 (바이너리 동보전송)
     *
     * @param CorpNum
     *            연동회원 사업자번호
     * @param SendNum
     *            발신번호
     * @param Receivers
     *            동보수신자 배열
     * @param File
     *            전송할 파일의 객체정보
     * @param ReserveDT
     *            예약일시, (형태 yyyyMMddHHmmss)
     * @param AdsYN
     *            광고팩스 전송여부
     * @param Title
     *          팩스제목
     * @param RequestNum
     *          전송요청번호
     * @return receiptNum 접수번호
     * @throws PopbillException
     */
    public String sendFAXBinary(String CorpNum, String SendNum, Receiver[] Receivers,
            FaxUploadFile File, Date ReserveDT, Boolean AdsYN, String Title, String RequestNum) throws PopbillException;

    /**
     * 팩스전송 단일파일 동보전송.
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param SendNum
     *            발신번호
     * @param Receivers
     *            동보수신자 배열
     * @param File
     *            전송파일
     * @param ReserveDT
     *            예약일시, (형태 yyyyMMddHHmmss)
     * @param UserID
     *            연동회원 아이디
     * @return receiptNum 접수번호
     * @throws PopbillException
     */
    public String sendFAX(String CorpNum, String SendNum, Receiver[] Receivers,
            File File, Date ReserveDT, String UserID) throws PopbillException;

    /**
     * 팩스 전송 (바이너리 동보전송)
     *
     * @param CorpNum
     *            연동회원 사업자번호
     * @param SendNum
     *            발신번호
     * @param Receivers
     *            동보수신자 배열
     * @param File
     *            전송할 파일의 객체정보
     * @param ReserveDT
     *            예약일시, (형태 yyyyMMddHHmmss)
     * @param UserID
     *            연동회원 아이디
     * @return receiptNum 접수번호
     * @throws PopbillException
     */
    public String sendFAXBinary(String CorpNum, String SendNum, Receiver[] Receivers,
            FaxUploadFile File, Date ReserveDT, String UserID) throws PopbillException;
    /**
     * 팩스전송 단일파일 동보전송. (팩스제목)
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param SendNum
     *            발신번호
     * @param Receivers
     *            동보수신자 배열
     * @param File
     *            전송파일
     * @param ReserveDT
     *            예약일시, (형태 yyyyMMddHHmmss)
     * @param UserID
     *            연동회원 아이디
     * @param Title 
     *            팩스제목
     * @return receiptNum 접수번호
     * @throws PopbillException
     */
    public String sendFAX(String CorpNum, String SendNum, Receiver[] Receivers,
            File File, Date ReserveDT, String UserID, String Title) throws PopbillException;

    /**
     * 팩스 전송 (바이너리 동보전송)
     *
     * @param CorpNum
     *            연동회원 사업자번호
     * @param SendNum
     *            발신번호
     * @param Receivers
     *            동보수신자 배열
     * @param File
     *            전송할 파일의 객체정보
     * @param ReserveDT
     *            예약일시, (형태 yyyyMMddHHmmss)
     * @param UserID
     *            연동회원 아이디
     * @param Title
     *          팩스제목
     * @return receiptNum 접수번호
     * @throws PopbillException
     */
    public String sendFAXBinary(String CorpNum, String SendNum, Receiver[] Receivers,
            FaxUploadFile File, Date ReserveDT, String UserID, String Title) throws PopbillException;
    /**
     * 팩스전송 단일파일 동보전송. (팩스제목)
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param SendNum
     *            발신번호
     * @param Receivers
     *            동보수신자 배열
     * @param File
     *            전송파일
     * @param ReserveDT
     *            예약일시, (형태 yyyyMMddHHmmss)
     * @param UserID
     *            연동회원 아이디
     * @param Title 
     *            팩스제목
     * @param RequestNum 
     *            전송요청번호
     * @return receiptNum 접수번호
     * @throws PopbillException
     */
    public String sendFAX(String CorpNum, String SendNum, Receiver[] Receivers,
            File File, Date ReserveDT, String UserID, String Title, String RequestNum) throws PopbillException;

    /**
     * 팩스 전송 (바이너리 동보전송)
     *
     * @param CorpNum
     *            연동회원 사업자번호
     * @param SendNum
     *            발신번호
     * @param Receivers
     *            동보수신자 배열
     * @param File
     *            전송할 파일의 객체정보
     * @param ReserveDT
     *            예약일시, (형태 yyyyMMddHHmmss)
     * @param UserID
     *            연동회원 아이디
     * @param Title
     *          팩스제목
     * @param RequestNum
     *          전송요청번호
     * @return receiptNum 접수번호
     * @throws PopbillException
     */
    public String sendFAXBinary(String CorpNum, String SendNum, Receiver[] Receivers,
            FaxUploadFile File, Date ReserveDT, String UserID, String Title, String RequestNum) throws PopbillException;

    /**
     * 팩스전송 단일파일 동보전송. (광고팩스)
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param SendNum
     *            발신번호
     * @param Receivers
     *            동보수신자 배열
     * @param File
     *            전송파일
     * @param ReserveDT
     *            예약일시, (형태 yyyyMMddHHmmss)
     * @param UserID
     *            연동회원 아이디
     * @param AdsYN
     *            광고팩스 전송여부
     * @return receiptNum 접수번호
     * @throws PopbillException
     */
    public String sendFAX(String CorpNum, String SendNum, Receiver[] Receivers,
            File File, Date ReserveDT, String UserID, Boolean AdsYN) throws PopbillException;

    /**
     * 팩스 전송 (바이너리 동보전송)
     *
     * @param CorpNum
     *            연동회원 사업자번호
     * @param SendNum
     *            발신번호
     * @param Receivers
     *            동보수신자 배열
     * @param File
     *            전송할 파일의 객체정보
     * @param ReserveDT
     *            예약일시, (형태 yyyyMMddHHmmss)
     * @param UserID
     *            연동회원 아이디
     * @param AdsYN
     *            광고팩스 전송여부
     * @return receiptNum 접수번호
     * @throws PopbillException
     */
    public String sendFAXBinary(String CorpNum, String SendNum, Receiver[] Receivers,
            FaxUploadFile File, Date ReserveDT, String UserID, Boolean AdsYN) throws PopbillException;
    /**
     * 팩스전송 단일파일 동보전송. (팩스제목)
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param SendNum
     *            발신번호
     * @param Receivers
     *            동보수신자 배열
     * @param File
     *            전송파일
     * @param ReserveDT
     *            예약일시, (형태 yyyyMMddHHmmss)
     * @param UserID
     *            연동회원 아이디
     * @param AdsYN
     *            광고팩스 전송여부
     * @param Title 
     *            팩스제목
     * @return receiptNum 접수번호
     * @throws PopbillException
     */
    public String sendFAX(String CorpNum, String SendNum, Receiver[] Receivers,
            File File, Date ReserveDT, String UserID, Boolean AdsYN, String Title)
                    throws PopbillException;

    /**
     * 팩스 전송 (바이너리 동보전송)
     *
     * @param CorpNum
     *            연동회원 사업자번호
     * @param SendNum
     *            발신번호
     * @param Receivers
     *            동보수신자 배열
     * @param File
     *            전송할 파일의 객체정보
     * @param ReserveDT
     *            예약일시, (형태 yyyyMMddHHmmss)
     * @param UserID
     *            연동회원 아이디
     * @param AdsYN
     *            광고팩스 전송여부
     * @param Title
     *          팩스제목
     *          전송요청번호
     * @return receiptNum 접수번호
     * @throws PopbillException
     */
    public String sendFAXBinary(String CorpNum, String SendNum, Receiver[] Receivers,
            FaxUploadFile File, Date ReserveDT, String UserID, Boolean AdsYN, String Title)
                    throws PopbillException;
    /**
     * 팩스전송 단일파일 동보전송. (팩스제목)
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param SendNum
     *            발신번호
     * @param Receivers
     *            동보수신자 배열
     * @param File
     *            전송파일
     * @param ReserveDT
     *            예약일시, (형태 yyyyMMddHHmmss)
     * @param UserID
     *            연동회원 아이디
     * @param AdsYN
     *            광고팩스 전송여부
     * @param Title 
     *            팩스제목
     * @param RequestNum 
     *            전송요청번호
     * @return receiptNum 접수번호
     * @throws PopbillException
     */
    public String sendFAX(String CorpNum, String SendNum, Receiver[] Receivers,
            File File, Date ReserveDT, String UserID, Boolean AdsYN, String Title, String RequestNum)
                    throws PopbillException;


    /**
     * 팩스 전송 (바이너리 동보전송)
     *
     * @param CorpNum
     *            연동회원 사업자번호
     * @param SendNum
     *            발신번호
     * @param Receivers
     *            동보수신자 배열
     * @param File
     *            전송할 파일의 객체정보
     * @param ReserveDT
     *            예약일시, (형태 yyyyMMddHHmmss)
     * @param UserID
     *            연동회원 아이디
     * @param AdsYN
     *            광고팩스 전송여부
     * @param Title
     *          팩스제목
     * @param RequestNum
     *          전송요청번호
     * @return receiptNum 접수번호
     * @throws PopbillException
     */
    public String sendFAXBinary(String CorpNum, String SendNum, Receiver[] Receivers,
            FaxUploadFile File, Date ReserveDT, String UserID, Boolean AdsYN, String Title, String RequestNum)
                    throws PopbillException;
    /**
     * 팩스전송 다중파일(최대5개) 단일 수신자.
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param SendNum
     *            발신번호
     * @param ReceiveNum
     *            수신번호
     * @param ReceiveName
     *            수신자 명칭
     * @param Files
     *            전송할 파일의 객체정보
     * @param ReserveDT
     *            예약일시, (형태 yyyyMMddHHmmss)
     * @return receiptNum 접수번호
     * @throws PopbillException
     */
    public String sendFAX(String CorpNum, String SendNum, String ReceiveNum,
            String ReceiveName, File[] Files, Date ReserveDT)
            throws PopbillException;

    /**
     * 팩스 전송 (바이너리)
     *
     * @param CorpNum
     *            연동회원 사업자번호
     * @param SendNum
     *            발신번호
     * @param ReceiveNum
     *            수신번호
     * @param ReceiveName
     *            수신자명
     * @param Files
     *            전송할 파일의 객체정보
     * @param ReserveDT
     *            예약일시, (형태 yyyyMMddHHmmss)
     * @return receiptNum 접수번호
     * @throws PopbillException
     */
    public String sendFAXBinary(String CorpNum, String SendNum, String ReceiveNum,
            String ReceiveName, FaxUploadFile[] Files, Date ReserveDT)
            throws PopbillException;
    /**
     * 팩스전송 다중파일(최대5개) 단일 수신자.
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param SendNum
     *            발신번호
     * @param ReceiveNum
     *            수신번호
     * @param ReceiveName
     *            수신자 명칭
     * @param Files
     *            전송할 파일의 객체정보
     * @param ReserveDT
     *            예약일시, (형태 yyyyMMddHHmmss)
     * @param AdsYN
     *            광고팩스 전송여부
     * @return receiptNum 접수번호
     * @throws PopbillException
     */
    public String sendFAX(String CorpNum, String SendNum, String ReceiveNum,
            String ReceiveName, File[] Files, Date ReserveDT, Boolean AdsYN)
            throws PopbillException;

    /**
     * 팩스 전송 (바이너리)
     *
     * @param CorpNum
     *            연동회원 사업자번호
     * @param SendNum
     *            발신번호
     * @param ReceiveNum
     *            수신번호
     * @param ReceiveName
     *            수신자명
     * @param Files
     *            전송할 파일의 객체정보
     * @param ReserveDT
     *            예약일시, (형태 yyyyMMddHHmmss)
     * @param AdsYN
     *            광고팩스 전송여부
     * @return receiptNum 접수번호
     * @throws PopbillException
     */
    public String sendFAXBinary(String CorpNum, String SendNum, String ReceiveNum,
            String ReceiveName, FaxUploadFile[] Files, Date ReserveDT, Boolean AdsYN)
            throws PopbillException;
    /**
     * 팩스전송 다중파일(최대5개) 단일 수신자.
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param SendNum
     *            발신번호
     * @param ReceiveNum
     *            수신번호
     * @param ReceiveName
     *            수신자 명칭
     * @param Files
     *            전송할 파일의 객체정보
     * @param ReserveDT
     *            예약일시, (형태 yyyyMMddHHmmss)
     * @param AdsYN
     *            광고팩스 전송여부
     * @param Title 
     *          팩스제목
     * @return receiptNum 접수번호
     * @throws PopbillException
     */
    public String sendFAX(String CorpNum, String SendNum, String ReceiveNum,
            String ReceiveName, File[] Files, Date ReserveDT, Boolean AdsYN, String Title)
            throws PopbillException;

    /**
     * 팩스 전송 (바이너리)
     *
     * @param CorpNum
     *            연동회원 사업자번호
     * @param SendNum
     *            발신번호
     * @param ReceiveNum
     *            수신번호
     * @param ReceiveName
     *            수신자명
     * @param Files
     *            전송할 파일의 객체정보
     * @param ReserveDT
     *            예약일시, (형태 yyyyMMddHHmmss)
     * @param AdsYN
     *            광고팩스 전송여부
     * @param Title
     *          팩스제목
     * @return receiptNum 접수번호
     * @throws PopbillException
     */
    public String sendFAXBinary(String CorpNum, String SendNum, String ReceiveNum,
            String ReceiveName, FaxUploadFile[] Files, Date ReserveDT, Boolean AdsYN, String Title)
            throws PopbillException;
    /**
     * 팩스전송 다중파일(최대5개) 단일 수신자.
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param SendNum
     *            발신번호
     * @param ReceiveNum
     *            수신번호
     * @param ReceiveName
     *            수신자 명칭
     * @param Files
     *            전송할 파일의 객체정보
     * @param ReserveDT
     *            예약일시, (형태 yyyyMMddHHmmss)
     * @param AdsYN
     *            광고팩스 전송여부
     * @param Title 
     *          팩스제목
     * @param RequestNum 
     *          전송요청번호
     * @return receiptNum 접수번호
     * @throws PopbillException
     */
    public String sendFAX(String CorpNum, String SendNum, String ReceiveNum,
            String ReceiveName, File[] Files, Date ReserveDT, Boolean AdsYN, String Title, String RequestNum)
            throws PopbillException;

    /**
     * 팩스 전송 (바이너리)
     *
     * @param CorpNum
     *            연동회원 사업자번호
     * @param SendNum
     *            발신번호
     * @param ReceiveNum
     *            수신번호
     * @param ReceiveName
     *            수신자명
     * @param Files
     *            전송할 파일의 객체정보
     * @param ReserveDT
     *            예약일시, (형태 yyyyMMddHHmmss)
     * @param AdsYN
     *            광고팩스 전송여부
     * @param Title
     *          팩스제목
     * @param RequestNum
     *          전송요청번호
     * @return receiptNum 접수번호
     * @throws PopbillException
     */
    public String sendFAXBinary(String CorpNum, String SendNum, String ReceiveNum,
            String ReceiveName, FaxUploadFile[] Files, Date ReserveDT, Boolean AdsYN, String Title, String RequestNum)
            throws PopbillException;
    /**
     * 팩스전송 다중파일(최대5개) 단일 수신자.
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param SendNum
     *            발신번호
     * @param ReceiveNum
     *            수신번호
     * @param ReceiveName
     *            수신자 명칭
     * @param Files
     *            전송할 파일의 객체정보
     * @param ReserveDT
     *            예약일시, (형태 yyyyMMddHHmmss)
     * @param UserID
     *            연동회원 아이디
     * @return receiptNum 접수번호
     * @throws PopbillException
     */
    public String sendFAX(String CorpNum, String SendNum, String ReceiveNum,
            String ReceiveName, File[] Files, Date ReserveDT, String UserID)
            throws PopbillException;

    /**
     * 팩스 전송 (바이너리)
     *
     * @param CorpNum
     *            연동회원 사업자번호
     * @param SendNum
     *            발신번호
     * @param ReceiveNum
     *            수신번호
     * @param ReceiveName
     *            수신자명
     * @param Files
     *            전송할 파일의 객체정보
     * @param ReserveDT
     *            예약일시, (형태 yyyyMMddHHmmss)
     * @param UserID
     *            연동회원 아이디
     * @return receiptNum 접수번호
     * @throws PopbillException
     */
    public String sendFAXBinary(String CorpNum, String SendNum, String ReceiveNum,
            String ReceiveName, FaxUploadFile[] Files, Date ReserveDT, String UserID)
            throws PopbillException;
    /**
     * 팩스전송 다중파일(최대5개) 단일 수신자.
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param SendNum
     *            발신번호
     * @param ReceiveNum
     *            수신번호
     * @param ReceiveName
     *            수신자 명칭
     * @param Files
     *            전송할 파일의 객체정보
     * @param ReserveDT
     *            예약일시, (형태 yyyyMMddHHmmss)
     * @param UserID
     *            연동회원 아이디
     * @param Title
     *            팩스제목
     * @return receiptNum 접수번호
     * @throws PopbillException
     */
    public String sendFAX(String CorpNum, String SendNum, String ReceiveNum,
            String ReceiveName, File[] Files, Date ReserveDT, String UserID,
            String Title) throws PopbillException;

    /**
     * 팩스 전송 (바이너리)
     *
     * @param CorpNum
     *            연동회원 사업자번호
     * @param SendNum
     *            발신번호
     * @param ReceiveNum
     *            수신번호
     * @param ReceiveName
     *            수신자명
     * @param Files
     *            전송할 파일의 객체정보
     * @param ReserveDT
     *            예약일시, (형태 yyyyMMddHHmmss)
     * @param UserID
     *            연동회원 아이디
     * @param Title
     *          팩스제목
     * @return receiptNum 접수번호
     * @throws PopbillException
     */
    public String sendFAXBinary(String CorpNum, String SendNum, String ReceiveNum,
            String ReceiveName, FaxUploadFile[] Files, Date ReserveDT, String UserID,
            String Title) throws PopbillException;
    /**
     * 팩스전송 다중파일(최대5개) 단일 수신자.
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param SendNum
     *            발신번호
     * @param ReceiveNum
     *            수신번호
     * @param ReceiveName
     *            수신자 명칭
     * @param Files
     *            전송할 파일의 객체정보
     * @param ReserveDT
     *            예약일시, (형태 yyyyMMddHHmmss)
     * @param UserID
     *            연동회원 아이디
     * @param Title
     *            팩스제목
     * @param RequestNum
     *            전송요청번호
     * @return receiptNum 접수번호
     * @throws PopbillException
     */
    public String sendFAX(String CorpNum, String SendNum, String ReceiveNum,
            String ReceiveName, File[] Files, Date ReserveDT, String UserID,
            String Title, String RequestNum) throws PopbillException;


    /**
     * 팩스 전송 (바이너리)
     *
     * @param CorpNum
     *            연동회원 사업자번호
     * @param SendNum
     *            발신번호
     * @param ReceiveNum
     *            수신번호
     * @param ReceiveName
     *            수신자명
     * @param Files
     *            전송할 파일의 객체정보
     * @param ReserveDT
     *            예약일시, (형태 yyyyMMddHHmmss)
     * @param UserID
     *            연동회원 아이디
     * @param Title
     *          팩스제목
     * @param RequestNum
     *          전송요청번호
     * @return receiptNum 접수번호
     * @throws PopbillException
     */
    public String sendFAXBinary(String CorpNum, String SendNum, String ReceiveNum,
            String ReceiveName, FaxUploadFile[] Files, Date ReserveDT, String UserID,
            String Title, String RequestNum) throws PopbillException;
    
    /**
     * 팩스전송 다중파일(최대5개) 단일 수신자. (광고팩스)
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param SendNum
     *            발신번호
     * @param ReceiveNum
     *            수신번호
     * @param ReceiveName
     *            수신자 명칭
     * @param Files
     *            전송할 파일의 객체정보
     * @param ReserveDT
     *            예약일시, (형태 yyyyMMddHHmmss)
     * @param UserID
     *            연동회원 아이디
     * @param AdsYN
     *            광고팩스 전송여부
     * @return receiptNum 접수번호
     * @throws PopbillException
     */
    public String sendFAX(String CorpNum, String SendNum, String ReceiveNum,
            String ReceiveName, File[] Files, Date ReserveDT, String UserID,
            Boolean AdsYN) throws PopbillException;

    /**
     * 팩스 전송 (바이너리)
     *
     * @param CorpNum
     *            연동회원 사업자번호
     * @param SendNum
     *            발신번호
     * @param ReceiveNum
     *            수신번호
     * @param ReceiveName
     *            수신자명
     * @param Files
     *            전송할 파일의 객체정보
     * @param ReserveDT
     *            예약일시, (형태 yyyyMMddHHmmss)
     * @param UserID
     *            연동회원 아이디
     * @param AdsYN
     *            광고팩스 전송여부
     * @return receiptNum 접수번호
     * @throws PopbillException
     */
    public String sendFAXBinary(String CorpNum, String SendNum, String ReceiveNum,
            String ReceiveName, FaxUploadFile[] Files, Date ReserveDT, String UserID,
            Boolean AdsYN) throws PopbillException;
    /**
     * 팩스전송 다중파일(최대5개) 단일 수신자. (팩스제목)
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param SendNum
     *            발신번호
     * @param ReceiveNum
     *            수신번호
     * @param ReceiveName
     *            수신자 명칭
     * @param Files
     *            전송할 파일의 객체정보
     * @param ReserveDT
     *            예약일시, (형태 yyyyMMddHHmmss)
     * @param UserID
     *            연동회원 아이디
     * @param AdsYN
     *            광고팩스 전송여부
     * @param Title 
     *          팩스제목
     * @return receiptNum 접수번호
     * @throws PopbillException
     */
    public String sendFAX(String CorpNum, String SendNum, String ReceiveNum,
            String ReceiveName, File[] Files, Date ReserveDT, String UserID,
            Boolean AdsYN, String Title) throws PopbillException;

    /**
     * 팩스 전송 (바이너리)
     *
     * @param CorpNum
     *            연동회원 사업자번호
     * @param SendNum
     *            발신번호
     * @param ReceiveNum
     *            수신번호
     * @param ReceiveName
     *            수신자명
     * @param Files
     *            전송할 파일의 객체정보
     * @param ReserveDT
     *            예약일시, (형태 yyyyMMddHHmmss)
     * @param UserID
     *            연동회원 아이디
     * @param AdsYN
     *            광고팩스 전송여부
     * @param Title
     *          팩스제목
     * @return receiptNum 접수번호
     * @throws PopbillException
     */
    public String sendFAXBinary(String CorpNum, String SendNum, String ReceiveNum,
            String ReceiveName, FaxUploadFile[] Files, Date ReserveDT, String UserID,
            Boolean AdsYN, String Title) throws PopbillException;
    /**
     * 팩스전송 다중파일(최대5개) 단일 수신자. (팩스제목)
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param SendNum
     *            발신번호
     * @param ReceiveNum
     *            수신번호
     * @param ReceiveName
     *            수신자 명칭
     * @param Files
     *            전송할 파일의 객체정보
     * @param ReserveDT
     *            예약일시, (형태 yyyyMMddHHmmss)
     * @param UserID
     *            연동회원 아이디
     * @param AdsYN
     *            광고팩스 전송여부
     * @param Title 
     *          팩스제목
     * @param RequestNum 
     *          전송요청번호
     * @return receiptNum 접수번호
     * @throws PopbillException
     */
    public String sendFAX(String CorpNum, String SendNum, String ReceiveNum,
            String ReceiveName, File[] Files, Date ReserveDT, String UserID,
            Boolean AdsYN, String Title, String RequestNum) throws PopbillException;

    /**
     * 팩스 전송 (바이너리)
     *
     * @param CorpNum
     *            연동회원 사업자번호
     * @param SendNum
     *            발신번호
     * @param ReceiveNum
     *            수신번호
     * @param ReceiveName
     *            수신자명
     * @param Files
     *            전송할 파일의 객체정보
     * @param ReserveDT
     *            예약일시, (형태 yyyyMMddHHmmss)
     * @param UserID
     *            연동회원 아이디
     * @param AdsYN
     *            광고팩스 전송여부
     * @param Title
     *          팩스제목
     * @param RequestNum
     *          전송요청번호
     * @return receiptNum 접수번호
     * @throws PopbillException
     */
    public String sendFAXBinary(String CorpNum, String SendNum, String ReceiveNum,
            String ReceiveName, FaxUploadFile[] Files, Date ReserveDT, String UserID,
            Boolean AdsYN, String Title, String RequestNum) throws PopbillException;
    
    /**
     * 팩스전송 다중파일(최대5개) 동보전송
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param SendNum
     *            발신번호
     * @param Receivers
     *            동보수신자 배열
     * @param Files
     *            전송할 파일의 객체정보
     * @param ReserveDT
     *            예약일시, (형태 yyyyMMddHHmmss)
     * @param UserID
     *            연동회원 아이디
     * @return receiptNum 접수번호
     * @throws PopbillException
     */
    public String sendFAX(String CorpNum, String SendNum, Receiver[] Receivers,
            File[] Files, Date ReserveDT, String UserID)
            throws PopbillException;


    /**
     * 팩스 전송 (바이너리 동보전송)
     *
     * @param CorpNum
     *            연동회원 사업자번호
     * @param SendNum
     *            발신번호
     * @param Receivers
     *            동보수신자 배열
     * @param Files
     *            전송할 파일의 객체정보
     * @param ReserveDT
     *            예약일시, (형태 yyyyMMddHHmmss)
     * @param UserID
     *            연동회원 아이디
     * @return receiptNum 접수번호
     * @throws PopbillException
     */
    public String sendFAXBinary(String CorpNum, String SendNum, Receiver[] Receivers,
            FaxUploadFile[] Files, Date ReserveDT, String UserID)
            throws PopbillException;
    
    /**
     * 팩스전송 다중파일(최대5개) 동보전송 - 팩스제목
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param SendNum
     *            발신번호
     * @param Receivers
     *            동보수신자 배열
     * @param Files
     *            전송할 파일의 객체정보
     * @param ReserveDT
     *            예약일시, (형태 yyyyMMddHHmmss)
     * @param UserID
     *            연동회원 아이디
     * @param Title
     *            팩스제목
     * @return receiptNum 접수번호
     * @throws PopbillException
     */
    public String sendFAX(String CorpNum, String SendNum, Receiver[] Receivers,
            File[] Files, Date ReserveDT, String UserID, String Title)
            throws PopbillException;


    /**
     * 팩스 전송 (바이너리 동보전송)
     *
     * @param CorpNum
     *            연동회원 사업자번호
     * @param SendNum
     *            발신번호
     * @param Receivers
     *            동보수신자 배열
     * @param Files
     *            전송할 파일의 객체정보
     * @param ReserveDT
     *            예약일시, (형태 yyyyMMddHHmmss)
     * @param UserID
     *            연동회원 아이디
     * @param Title
     *          팩스제목
     * @return receiptNum 접수번호
     * @throws PopbillException
     */
    public String sendFAXBinary(String CorpNum, String SendNum, Receiver[] Receivers,
            FaxUploadFile[] Files, Date ReserveDT, String UserID, String Title)
            throws PopbillException;
    /**
     * 팩스전송 다중파일(최대5개) 동보전송 - 팩스제목
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param SendNum
     *            발신번호
     * @param Receivers
     *            동보수신자 배열
     * @param Files
     *            전송할 파일의 객체정보
     * @param ReserveDT
     *            예약일시, (형태 yyyyMMddHHmmss)
     * @param UserID
     *            연동회원 아이디
     * @param Title
     *            팩스제목
     * @param RequestNum
     *            전송요청번호
     * @return receiptNum 접수번호
     * @throws PopbillException
     */
    public String sendFAX(String CorpNum, String SendNum, Receiver[] Receivers,
            File[] Files, Date ReserveDT, String UserID, String Title, String RequestNum)
            throws PopbillException;


    /**
     * 팩스 전송 (바이너리 동보전송)
     *
     * @param CorpNum
     *            연동회원 사업자번호
     * @param SendNum
     *            발신번호
     * @param Receivers
     *            동보수신자 배열
     * @param Files
     *            전송할 파일의 객체정보
     * @param ReserveDT
     *            예약일시, (형태 yyyyMMddHHmmss)
     * @param UserID
     *            연동회원 아이디
     * @param Title
     *          팩스제목
     * @param RequestNum
     *          전송요청번호
     * @return receiptNum 접수번호
     * @throws PopbillException
     */
    public String sendFAXBinary(String CorpNum, String SendNum, Receiver[] Receivers,
            FaxUploadFile[] Files, Date ReserveDT, String UserID, String Title, String RequestNum)
            throws PopbillException;
    
    /**
     * 팩스전송 다중파일(최대5개) 동보전송 (광고팩스)
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param SendNum
     *            발신번호
     * @param Receivers
     *            동보수신자 배열
     * @param Files
     *            전송할 파일의 객체정보
     * @param ReserveDT
     *            예약일시, (형태 yyyyMMddHHmmss)
     * @param UserID
     *            연동회원 아이디
     * @param AdsYN
     *            광고팩스 전송여부
     * @return receiptNum 접수번호
     * @throws PopbillException
     */
    public String sendFAX(String CorpNum, String SendNum, Receiver[] Receivers,
            File[] Files, Date ReserveDT, String UserID, Boolean AdsYN)
            throws PopbillException;


    /**
     * 팩스 전송 (바이너리 동보전송)
     *
     * @param CorpNum
     *            연동회원 사업자번호
     * @param SendNum
     *            발신번호
     * @param Receivers
     *            동보수신자 배열
     * @param Files
     *            전송할 파일의 객체정보
     * @param ReserveDT
     *            예약일시, (형태 yyyyMMddHHmmss)
     * @param UserID
     *            연동회원 아이디
     * @param AdsYN
     *            광고팩스 전송여부
     * @return receiptNum 접수번호
     * @throws PopbillException
     */
    public String sendFAXBinary(String CorpNum, String SendNum, Receiver[] Receivers,
            FaxUploadFile[] Files, Date ReserveDT, String UserID, Boolean AdsYN)
            throws PopbillException;    
    /**
     * 팩스전송 다중파일(최대5개) 동보전송 (팩스제목)
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param SendNum
     *            발신번호
     * @param Receivers
     *            동보수신자 배열
     * @param Files
     *            전송할 파일의 객체정보
     * @param ReserveDT
     *            예약일시, (형태 yyyyMMddHHmmss)
     * @param UserID
     *            연동회원 아이디
     * @param AdsYN
     *            광고팩스 전송여부
     * @param Title
     *          팩스제목
     * @return receiptNum 접수번호
     * @throws PopbillException
     */
    public String sendFAX(String CorpNum, String SendNum, Receiver[] Receivers,
            File[] Files, Date ReserveDT, String UserID, Boolean AdsYN, String Title)
            throws PopbillException;


    /**
     * 팩스 전송 (바이너리 동보전송)
     *
     * @param CorpNum
     *            연동회원 사업자번호
     * @param SendNum
     *            발신번호
     * @param Receivers
     *            동보수신자 배열
     * @param Files
     *            전송할 파일의 객체정보
     * @param ReserveDT
     *            예약일시, (형태 yyyyMMddHHmmss)
     * @param UserID
     *            연동회원 아이디
     * @param AdsYN
     *            광고팩스 전송여부
     * @param Title
     *          팩스제목
     * @return receiptNum 접수번호
     * @throws PopbillException
     */
    public String sendFAXBinary(String CorpNum, String SendNum, Receiver[] Receivers,
            FaxUploadFile[] Files, Date ReserveDT, String UserID, Boolean AdsYN, String Title)
            throws PopbillException;
    /**
     * 팩스전송 다중파일(최대5개) 동보전송 (팩스제목)
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param SendNum
     *            발신번호
     * @param Receivers
     *            동보수신자 배열
     * @param Files
     *            전송할 파일의 객체정보
     * @param ReserveDT
     *            예약일시, (형태 yyyyMMddHHmmss)
     * @param UserID
     *            연동회원 아이디
     * @param AdsYN
     *            광고팩스 전송여부
     * @param Title
     *          팩스제목
     * @param RequestNum
     *          전송요청번호
     * @return receiptNum 접수번호
     * @throws PopbillException
     */
    public String sendFAX(String CorpNum, String SendNum, Receiver[] Receivers,
            File[] Files, Date ReserveDT, String UserID, Boolean AdsYN, String Title, String RequestNum)
            throws PopbillException;


    /**
     * 팩스 전송 (바이너리 동보전송)
     *
     * @param CorpNum
     *            연동회원 사업자번호
     * @param SendNum
     *            발신번호
     * @param Receivers
     *            동보수신자 배열
     * @param Files
     *            전송할 파일의 객체정보
     * @param ReserveDT
     *            예약일시, (형태 yyyyMMddHHmmss)
     * @param UserID
     *            연동회원 아이디
     * @param AdsYN
     *            광고팩스 전송여부
     * @param Title
     *          팩스제목
     * @param RequestNum
     *          전송요청번호
     * @return receiptNum 접수번호
     * @throws PopbillException
     */
    public String sendFAXBinary(String CorpNum, String SendNum, Receiver[] Receivers,
            FaxUploadFile[] Files, Date ReserveDT, String UserID, Boolean AdsYN, String Title, String RequestNum)
            throws PopbillException;
    /**
     * 팩스전송 다중파일(최대5개) 동보전송
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param SendNum
     *            발신번호
     * @param SenderName
     *            발신자명
     * @param Receivers
     *            동보수신자 배열
     * @param Files
     *            전송할 파일의 객체정보
     * @param ReserveDT
     *            예약일시, (형태 yyyyMMddHHmmss)
     * @return receiptNum 접수번호
     * @throws PopbillException
     */
    public String sendFAX(String CorpNum, String SendNum, String SenderName, 
            Receiver[] Receivers, File[] Files, Date ReserveDT) throws PopbillException;


    /**
     * 팩스 전송 (바이너리 동보전송)
     *
     * @param CorpNum
     *            연동회원 사업자번호
     * @param SendNum
     *            발신번호
     * @param SenderName
     *            발신자명
     * @param Receivers
     *            동보수신자 배열
     * @param Files
     *            전송할 파일의 객체정보
     * @param ReserveDT
     *            예약일시, (형태 yyyyMMddHHmmss)
     * @return receiptNum 접수번호
     * @throws PopbillException
     */
    public String sendFAXBinary(String CorpNum, String SendNum, String SenderName,
            Receiver[] Receivers, FaxUploadFile[] Files, Date ReserveDT) throws PopbillException;
    /**
     * 팩스전송 다중파일(최대5개) 동보전송 (광고팩스)
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param SendNum
     *            발신번호
     * @param SenderName
     *            발신자명
     * @param Receivers
     *            동보수신자 배열
     * @param Files
     *            전송할 파일의 객체정보
     * @param ReserveDT
     *            예약일시, (형태 yyyyMMddHHmmss)
     * @param AdsYN
     *            광고팩스 전송여부
     * @return receiptNum 접수번호
     * @throws PopbillException
     */
    public String sendFAX(String CorpNum, String SendNum, String SenderName, 
            Receiver[] Receivers, File[] Files, Date ReserveDT, Boolean AdsYN)
            throws PopbillException;


    /**
     * 팩스 전송 (바이너리 동보전송)
     *
     * @param CorpNum
     *            연동회원 사업자번호
     * @param SendNum
     *            발신번호
     * @param SenderName
     *            발신자명
     * @param Receivers
     *            동보수신자 배열
     * @param Files
     *            전송할 파일의 객체정보
     * @param ReserveDT
     *            예약일시, (형태 yyyyMMddHHmmss)
     * @param AdsYN
     *            광고팩스 전송여부
     * @return receiptNum 접수번호
     * @throws PopbillException
     */
    public String sendFAXBinary(String CorpNum, String SendNum, String SenderName,
            Receiver[] Receivers, FaxUploadFile[] Files, Date ReserveDT, Boolean AdsYN)
            throws PopbillException;
    
    /**
     * 팩스전송 다중파일(최대5개) 동보전송 (광고팩스)
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param SendNum
     *            발신번호
     * @param SenderName
     *            발신자명
     * @param Receivers
     *            동보수신자 배열
     * @param Files
     *            전송할 파일의 객체정보
     * @param ReserveDT
     *            예약일시, (형태 yyyyMMddHHmmss)
     * @param AdsYN
     *            광고팩스 전송여부
     * @param RequestNum
     *            전송요청번호
     * @return receiptNum 접수번호
     * @throws PopbillException
     */
    public String sendFAX(String CorpNum, String SendNum, String SenderName, 
            Receiver[] Receivers, File[] Files, Date ReserveDT, Boolean AdsYN, String RequestNum)
            throws PopbillException;


    /**
     * 팩스 전송 (바이너리 동보전송)
     *
     * @param CorpNum
     *            연동회원 사업자번호
     * @param SendNum
     *            발신번호
     * @param SenderName
     *            발신자명
     * @param Receivers
     *            동보수신자 배열
     * @param Files
     *            전송할 파일의 객체정보
     * @param ReserveDT
     *            예약일시, (형태 yyyyMMddHHmmss)
     * @param AdsYN
     *            광고팩스 전송여부
     * @param RequestNum
     *          전송요청번호
     * @return receiptNum 접수번호
     * @throws PopbillException
     */
    public String sendFAXBinary(String CorpNum, String SendNum, String SenderName,
            Receiver[] Receivers, FaxUploadFile[] Files, Date ReserveDT, Boolean AdsYN, String RequestNum)
            throws PopbillException;

    /**
     * 팩스전송 다중파일(최대5개) 동보전송
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param SendNum
     *            발신번호
     * @param SenderName
     *            발신자명
     * @param Receivers
     *            동보수신자 배열
     * @param Files
     *            전송할 파일의 객체정보
     * @param ReserveDT
     *            예약일시, (형태 yyyyMMddHHmmss)
     * @param UserID
     *            연동회원 아이디
     * @return receiptNum 접수번호
     * @throws PopbillException
     */
    public String sendFAX(String CorpNum, String SendNum, String SenderName, Receiver[] Receivers,
            File[] Files, Date ReserveDT, String UserID)
            throws PopbillException;


    /**
     * 팩스 전송 (바이너리 동보전송)
     *
     * @param CorpNum
     *            연동회원 사업자번호
     * @param SendNum
     *            발신번호
     * @param SenderName
     *            발신자명
     * @param Receivers
     *            동보수신자 배열
     * @param Files
     *            전송할 파일의 객체정보
     * @param ReserveDT
     *            예약일시, (형태 yyyyMMddHHmmss)
     * @param UserID
     *            연동회원 아이디
     * @return receiptNum 접수번호
     * @throws PopbillException
     */
    public String sendFAXBinary(String CorpNum, String SendNum, String SenderName, Receiver[] Receivers,
            FaxUploadFile[] Files, Date ReserveDT, String UserID)
            throws PopbillException;
    
    
    /**
     * 팩스전송 다중파일(최대5개) 동보전송 - 팩스제목
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param SendNum
     *            발신번호
     * @param SenderName
     *            발신자명
     * @param Receivers
     *            동보수신자 배열
     * @param Files
     *            전송할 파일의 객체정보
     * @param ReserveDT
     *            예약일시, (형태 yyyyMMddHHmmss)
     * @param UserID
     *            연동회원 아이디
     * @param Title
     *          팩스제목
     * @return receiptNum 접수번호
     * @throws PopbillException
     */
    public String sendFAX(String CorpNum, String SendNum, String SenderName, Receiver[] Receivers,
            File[] Files, Date ReserveDT, String UserID, String Title)
            throws PopbillException;


    /**
     * 팩스 전송 (바이너리 동보전송)
     *
     * @param CorpNum
     *            연동회원 사업자번호
     * @param SendNum
     *            발신번호
     * @param SenderName
     *            발신자명
     * @param Receivers
     *            동보수신자 배열
     * @param Files
     *            전송할 파일의 객체정보
     * @param ReserveDT
     *            예약일시, (형태 yyyyMMddHHmmss)
     * @param UserID
     *            연동회원 아이디
     * @param Title
     *          팩스제목
     * @return receiptNum 접수번호
     * @throws PopbillException
     */
    public String sendFAXBinary(String CorpNum, String SendNum, String SenderName, Receiver[] Receivers,
            FaxUploadFile[] Files, Date ReserveDT, String UserID, String Title)
            throws PopbillException;
    
    /**
     * 팩스전송 다중파일(최대5개) 동보전송 - 팩스제목
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param SendNum
     *            발신번호
     * @param SenderName
     *            발신자명
     * @param Receivers
     *            동보수신자 배열
     * @param Files
     *            전송할 파일의 객체정보
     * @param ReserveDT
     *            예약일시, (형태 yyyyMMddHHmmss)
     * @param UserID
     *            연동회원 아이디
     * @param Title
     *          팩스제목
     * @param RequestNum
     *          전송요청번호
     * @return receiptNum 접수번호
     * @throws PopbillException
     */
    public String sendFAX(String CorpNum, String SendNum, String SenderName, Receiver[] Receivers,
            File[] Files, Date ReserveDT, String UserID, String Title, String RequestNum)
            throws PopbillException;


    /**
     * 팩스 전송 (바이너리 동보전송)
     *
     * @param CorpNum
     *            연동회원 사업자번호
     * @param SendNum
     *            발신번호
     * @param SenderName
     *            발신자명
     * @param Receivers
     *            동보수신자 배열
     * @param Files
     *            전송할 파일의 객체정보
     * @param ReserveDT
     *            예약일시, (형태 yyyyMMddHHmmss)
     * @param UserID
     *            연동회원 아이디
     * @param Title
     *          팩스제목
     * @param RequestNum
     *          전송요청번호
     * @return receiptNum 접수번호
     * @throws PopbillException
     */
    public String sendFAXBinary(String CorpNum, String SendNum, String SenderName, Receiver[] Receivers,
            FaxUploadFile[] Files, Date ReserveDT, String UserID, String Title, String RequestNum)
            throws PopbillException;

    /**
     * 팩스전송 다중파일(최대5개) 동보전송 (광고)
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param SendNum
     *            발신번호
     * @param SenderName
     *            발신자명
     * @param Receivers
     *            동보수신자 배열
     * @param Files
     *            전송할 파일의 객체정보
     * @param ReserveDT
     *            예약일시, (형태 yyyyMMddHHmmss)
     * @param UserID
     *            연동회원 아이디
     * @param AdsYN
     *            광고팩스 전송여부            
     * @return receiptNum 접수번호
     * @throws PopbillException
     */
    public String sendFAX(String CorpNum, String SendNum, String SenderName, Receiver[] Receivers,
            File[] Files, Date ReserveDT, String UserID, Boolean AdsYN)
            throws PopbillException;


    /**
     * 팩스 전송 (바이너리 동보전송)
     *
     * @param CorpNum
     *            연동회원 사업자번호
     * @param SendNum
     *            발신번호
     * @param SenderName
     *            발신자명
     * @param Receivers
     *            동보수신자 배열
     * @param Files
     *            전송할 파일의 객체정보
     * @param ReserveDT
     *            예약일시, (형태 yyyyMMddHHmmss)
     * @param UserID
     *            연동회원 아이디
     * @param AdsYN
     *            광고팩스 전송여부
     * @return receiptNum 접수번호
     * @throws PopbillException
     */
    public String sendFAXBinary(String CorpNum, String SendNum, String SenderName, Receiver[] Receivers,
            FaxUploadFile[] Files, Date ReserveDT, String UserID, Boolean AdsYN)
            throws PopbillException;
    
    /**
     * 팩스전송 다중파일(최대5개) 동보전송 (팩스제목)
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param SendNum
     *            발신번호
     * @param SenderName
     *            발신자명
     * @param Receivers
     *            동보수신자 배열
     * @param Files
     *            전송할 파일의 객체정보
     * @param ReserveDT
     *            예약일시, (형태 yyyyMMddHHmmss)
     * @param UserID
     *            연동회원 아이디
     * @param AdsYN
     *            광고팩스 전송여부     
     * @param Title
     *          팩스제목       
     * @return receiptNum 접수번호
     * @throws PopbillException
     */
    
    public String sendFAX(String CorpNum, String SendNum, String SenderName, Receiver[] Receivers,
            File[] Files, Date ReserveDT, String UserID, Boolean AdsYN, String Title)
            throws PopbillException;


    /**
     * 팩스 전송 (바이너리 동보전송)
     *
     * @param CorpNum
     *            연동회원 사업자번호
     * @param SendNum
     *            발신번호
     * @param SenderName
     *            발신자명
     * @param Receivers
     *            동보수신자 배열
     * @param Files
     *            전송할 파일의 객체정보
     * @param ReserveDT
     *            예약일시, (형태 yyyyMMddHHmmss)
     * @param UserID
     *            연동회원 아이디
     * @param AdsYN
     *            광고팩스 전송여부
     * @param Title
     *          팩스제목
     * @return receiptNum 접수번호
     * @throws PopbillException
     */
    public String sendFAXBinary(String CorpNum, String SendNum, String SenderName, Receiver[] Receivers,
            FaxUploadFile[] Files, Date ReserveDT, String UserID, Boolean AdsYN, String Title)
            throws PopbillException;
    
    /**
     * 팩스전송 다중파일(최대5개) 동보전송 (팩스제목)
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param SendNum
     *            발신번호
     * @param SenderName
     *            발신자명
     * @param Receivers
     *            동보수신자 배열
     * @param Files
     *            전송할 파일의 객체정보
     * @param ReserveDT
     *            예약일시, (형태 yyyyMMddHHmmss)
     * @param UserID
     *            연동회원 아이디
     * @param AdsYN      
     *            광고팩스 전송여부     
     * @param Title
     *          팩스제목       
     * @param RequestNum
     *          전송요청번호  
     * @return receiptNum 접수번호
     * @throws PopbillException
     */
    public String sendFAX(String CorpNum, String SendNum, String SenderName, Receiver[] Receivers,
            File[] Files, Date ReserveDT, String UserID, Boolean AdsYN, String Title, String RequestNum)
            throws PopbillException;

    /**
     * 팩스 전송 (바이너리 동보전송)
     *
     * @param CorpNum
     *            연동회원 사업자번호
     * @param SendNum
     *            발신번호
     * @param SenderName
     *            발신자명
     * @param Receivers
     *            동보수신자 배열
     * @param Files
     *            전송할 파일의 객체정보
     * @param ReserveDT
     *            예약일시, (형태 yyyyMMddHHmmss)
     * @param UserID
     *            연동회원 아이디
     * @param AdsYN
     *            광고팩스 전송여부
     * @param Title
     *          팩스제목
     * @param RequestNum
     *          전송요청번호
     * @return receiptNum 접수번호
     * @throws PopbillException
     */
    public String sendFAXBinary(String CorpNum, String SendNum, String SenderName, Receiver[] Receivers,
            FaxUploadFile[] Files, Date ReserveDT, String UserID, Boolean AdsYN, String Title, String RequestNum)
            throws PopbillException;
    
    /**
     * 팩스전송 동보 재전송
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param ReceiptNum
     *            팩스 접수번호
     * @param SendNum
     *            발신번호
     * @param SenderName
     *            발신자명           
     * @param ReceiveNum
     *            수신번호
     * @param ReceiveName
     *            수신자 명칭
     * @param ReserveDT
     *            예약일시, (형태 yyyyMMddHHmmss)
     * @param UserID
     *            연동회원 아이디
     * @return receiptNum 접수번호
     * @throws PopbillException
     */
    public String resendFAX(String CorpNum, String ReceiptNum, String SendNum, String SenderName,
            String ReceiveNum, String ReceiveName, Date ReserveDT, String UserID)
            throws PopbillException;
    
    /**
     * 팩스전송 동보 재전송
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param ReceiptNum
     *            팩스 접수번호
     * @param SendNum
     *            발신번호
     * @param SenderName
     *            발신자명           
     * @param ReceiveNum
     *            수신번호
     * @param ReceiveName
     *            수신자 명칭
     * @param ReserveDT
     *            예약일시, (형태 yyyyMMddHHmmss)
     * @param UserID
     *            연동회원 아이디
     * @param Title
     *          팩스 제목
     * @return receiptNum 접수번호
     * @throws PopbillException
     */
    public String resendFAX(String CorpNum, String ReceiptNum, String SendNum, String SenderName,
            String ReceiveNum, String ReceiveName, Date ReserveDT, String UserID, String Title)
            throws PopbillException;
    
    /**
     * 팩스전송 동보 재전송
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param ReceiptNum
     *            팩스 접수번호
     * @param SendNum
     *            발신번호
     * @param SenderName
     *            발신자명           
     * @param ReceiveNum
     *            수신번호
     * @param ReceiveName
     *            수신자 명칭
     * @param ReserveDT
     *            예약일시, (형태 yyyyMMddHHmmss)
     * @param UserID
     *            연동회원 아이디
     * @param Title
     *          팩스 제목
     * @param RequestNum
     *          전송요청번호
     * @return receiptNum 접수번호
     * @throws PopbillException
     */
    public String resendFAX(String CorpNum, String ReceiptNum, String SendNum, String SenderName,
            String ReceiveNum, String ReceiveName, Date ReserveDT, String UserID, String Title, String RequestNum)
            throws PopbillException;

    /**
     * 팩스전송 동보 재전송
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param ReceiptNum
     *            팩스 접수번호
     * @param SendNum
     *            발신번호
     * @param SenderName
     *            발신자명
     * @param Receivers
     *            수신자정보 배열
     * @param ReserveDT
     *            예약일시, (형태 yyyyMMddHHmmss)
     * @return receiptNum 접수번호
     * @throws PopbillException
     */
    public String resendFAX(String CorpNum, String ReceiptNum, String SendNum, 
            String SenderName, Receiver[] Receivers, Date ReserveDT)
            throws PopbillException;
    
        
    /**
     * 팩스전송 동보 재전송
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param ReceiptNum
     *            팩스 접수번호
     * @param SendNum
     *            발신번호
     * @param SenderName
     *            발신자명
     * @param Receivers
     *            수신자정보 배열
     * @param ReserveDT
     *            예약일시, (형태 yyyyMMddHHmmss)
     * @param UserID
     *            연동회원 아이디
     * @return receiptNum 접수번호
     * @throws PopbillException
     */
    public String resendFAX(String CorpNum, String ReceiptNum, String SendNum, 
            String SenderName, Receiver[] Receivers, Date ReserveDT, String UserID)
            throws PopbillException;
    
    
    /**
     * 팩스전송 동보 재전송
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param ReceiptNum
     *            팩스 접수번호
     * @param SendNum
     *            발신번호
     * @param SenderName
     *            발신자명
     * @param Receivers
     *            수신자정보 배열
     * @param ReserveDT
     *            예약일시, (형태 yyyyMMddHHmmss)
     * @param UserID
     *            연동회원 아이디
     * @param Title
     *          팩스제목
     * @return receiptNum 접수번호
     * @throws PopbillException
     */
    public String resendFAX(String CorpNum, String ReceiptNum, String SendNum, 
            String SenderName, Receiver[] Receivers, Date ReserveDT, String UserID, String Title)
            throws PopbillException;
    
    /**
     * 팩스전송 동보 재전송
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param ReceiptNum
     *            팩스 접수번호
     * @param SendNum
     *            발신번호
     * @param SenderName
     *            발신자명
     * @param Receivers
     *            수신자정보 배열
     * @param ReserveDT
     *            예약일시, (형태 yyyyMMddHHmmss)
     * @param UserID
     *            연동회원 아이디
     * @param Title
     *          팩스제목
     * @param RequestNum
     *          전송요청번호
     * @return receiptNum 접수번호
     * @throws PopbillException
     */
    public String resendFAX(String CorpNum, String ReceiptNum, String SendNum, 
            String SenderName, Receiver[] Receivers, Date ReserveDT, String UserID, String Title, String RequestNum)
            throws PopbillException;

    /**
     * 팩스전송 동보 재전송
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param RequestNum
     *            재전송 팩스의 전송요청번호
     * @param SendNum
     *            발신번호
     * @param SenderName
     *            발신자명           
     * @param ReceiveNum
     *            수신번호
     * @param ReceiveName
     *            수신자 명칭
     * @param ReserveDT
     *            예약일시, (형태 yyyyMMddHHmmss)
     * @param UserID
     *            연동회원 아이디
     * @param Title
     *          팩스 제목
     * @param OrgRequestNum
     *          원본 팩스 전송시 할당한 전송요청번호
     * @return receiptNum 접수번호
     * @throws PopbillException
     */
    public String resendFAXRN(String CorpNum, String RequestNum, String SendNum, String SenderName,
            String ReceiveNum, String ReceiveName, Date ReserveDT, String UserID, String Title,
            String OrgRequestNum)
            throws PopbillException;

    /**
     * 팩스전송 동보 재전송
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param RequestNum
     *             재전송 팩스의 전송요청번호
     * @param SendNum
     *            발신번호
     * @param SenderName
     *            발신자명
     * @param Receivers
     *            수신자정보 배열
     * @param ReserveDT
     *            예약일시, (형태 yyyyMMddHHmmss)
     * @param UserID
     *            연동회원 아이디
     * @param Title
     *          팩스제목
     * @param OrgRequestNum
     *          원본 팩스 전송시 할당한 전송요청번호
     * @return receiptNum 접수번호
     * @throws PopbillException
     */
    public String resendFAXRN(String CorpNum, String RequestNum, String SendNum,
            String SenderName, Receiver[] Receivers, Date ReserveDT, String UserID, String Title,
            String OrgRequestNum)
            throws PopbillException;    
    
    /**
     * 전송결과 확인
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param ReceiptNum
     *            팩스전송 접수번호
     * @return FaxResult 배열. (see com.popbill.api.fax.FaxResult)
     * @throws PopbillException
     */
    public FaxResult[] getFaxResult(String CorpNum, String ReceiptNum)
            throws PopbillException;

    /**
     * 전송결과 확인
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param RequestNum
     *            전송요청번호
     * @return FaxResult 배열. (see com.popbill.api.fax.FaxResult)
     * @throws PopbillException
     */
    public FaxResult[] getFaxResultRN(String CorpNum, String RequestNum)
            throws PopbillException;

    /**
     * 예약건 전송 취소.(예약시간 10분전 까지만 가능)
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param ReceiptNum
     *            팩스전송 접수번호
     * @return Response 응답
     * @throws PopbillException
     */
    public Response cancelReserve(String CorpNum, String ReceiptNum) throws PopbillException;
    
    
    /**
     * 예약건 전송 취소.(예약시간 10분전 까지만 가능)
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param ReceiptNum
     *            팩스전송 접수번호
     * @param UserID
     *            연동회원 아이디
     * @return Response 응답
     * @throws PopbillException
     */
    public Response cancelReserve(String CorpNum, String ReceiptNum,
            String UserID) throws PopbillException;
    
    /**
     * 예약건 전송 취소.(예약시간 10분전 까지만 가능)
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param RequestNum
     *            전송요청번호
     * @return Response 응답
     * @throws PopbillException
     */
    public Response cancelReserveRN(String CorpNum, String RequestNum) throws PopbillException;
    
    
    /**
     * 예약건 전송 취소.(예약시간 10분전 까지만 가능)
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param RequestNum
     *            전송요청번호
     * @param UserID
     *            연동회원 아이디
     * @return Response 응답
     * @throws PopbillException
     */
    public Response cancelReserveRN(String CorpNum, String RequestNum,
            String UserID) throws PopbillException;
    
    /**
     * 
     * @param CorpNum
     *          연동회원 사업자번호 
     * @param SDate
     *          시작일자 (yyyyMMdd)
     * @param EDate
     *          종료일자 (yyyyMMdd)
     * @param State
     *          전송상태 
     * @param ReserveYN
     *          예약전송여부 
     * @param SenderOnlyYN
     *          개인조회 여부 
     * @param Page
     *          페이지 번호, 기본값 1
     * @param PerPage
     *          페이지당 목록갯수, 기본값 500, 최대값 1000
     * @param Order
     *          정렬방향, D-내림차순, A-오름차순 
     * @return 팩스전송내역. see com.popbill.api.fax.FAXSearchResult
     * @throws PopbillException
     */
    public FAXSearchResult search(String CorpNum, String SDate, String EDate,
            String[] State, Boolean ReserveYN, Boolean SenderOnlyYN, 
            Integer Page, Integer PerPage, String Order) throws PopbillException;

    /**
     * 
     * @param CorpNum
     *          연동회원 사업자번호 
     * @param SDate
     *          시작일자 (yyyyMMdd)
     * @param EDate
     *          종료일자 (yyyyMMdd)
     * @param State
     *          전송상태 
     * @param ReserveYN
     *          예약전송여부 
     * @param SenderOnlyYN
     *          개인조회 여부 
     * @param Page
     *          페이지 번호, 기본값 1
     * @param PerPage
     *          페이지당 목록갯수, 기본값 500, 최대값 1000
     * @param Order
     *          정렬방향, D-내림차순, A-오름차순 
     * @param QString
     *           조회검색어. 발신자명 또는 수신자명 기재
     * @return 팩스전송내역. see com.popbill.api.fax.FAXSearchResult
     * @throws PopbillException
     */
    public FAXSearchResult search(String CorpNum, String SDate, String EDate,
            String[] State, Boolean ReserveYN, Boolean SenderOnlyYN, 
            Integer Page, Integer PerPage, String Order, String QString) throws PopbillException;   
    
    /**
     * 과금정보 조회
     * 
     * @param CorpNum
     *          연동회원 사업자번호 
     * @return 과금정보. see.com.popbill.api.ChargeInfo
     * @throws PopbillException
     */
    public ChargeInfo getChargeInfo(String CorpNum) throws PopbillException;
    
    /**
     * 
     * @param CorpNum
     *          연동회원 사업자번호
     * @param ReceiveNumType
     *          수신번호 유형
     * @return 과금정보. see.com.popbill.api.ChargeInfo
     * @throws PopbillException
     */
    public ChargeInfo getChargeInfo(String CorpNum, String ReceiveNumType) throws PopbillException;

    /**
     *
     * @param CorpNum
     *          연동회원 사업자번호
     * @param ReceiveNumType
     *          수신번호 유형
     * @param UserID
     *          팝빌회원 아이디
     * @return 과금정보. see.com.popbill.api.ChargeInfo
     * @throws PopbillException
     */
    public ChargeInfo getChargeInfo(String CorpNum, String ReceiveNumType, String UserID) throws PopbillException;

    /**
     * 등록된 발신번호 목록 확인 
     * 
     * @param CorpNum
     *          연동회원 사업자번호
     * @return 발신번호 목록  
     * @throws PopbillException
     */
    public SenderNumber[] getSenderNumberList(String CorpNum) throws PopbillException;
    
    /**
     * 등록된 발신번호 목록 확인 
     * 
     * @param CorpNum
     *          연동회원 사업자번호
     * @param UserID 
     *          연동회원 아이디
     * @return 발신번호 목록  
     * @throws PopbillException
     */
    public SenderNumber[] getSenderNumberList(String CorpNum, String UserID) throws PopbillException;

    /**
     * 팩스 미리보기 팝업 URL
     *
     * @param CorpNum 
     *          연동회원 사업자번호
     * @param ReceiptNum 
     *          접수번호
     * @return
     * @throws PopbillException
     */
    public String getPreviewURL(String CorpNum, String ReceiptNum) throws PopbillException;

    /**
     * 팩스 미리보기 팝업 URL
     *
     * @param CorpNum 
     *          연동회원 사업자번호
     * @param ReceiptNum 접수번호
     * @param UserID 연동회원 아이디
     * @return
     * @throws PopbillException
     */
    public String getPreviewURL(String CorpNum, String ReceiptNum, String UserID) throws PopbillException;


    /**
     *  팩스 발신번호 관리 팝업 URL을 반환
     *
     * @param CorpNum
     *          연동회원 사업자번호
     * @param UserID
     *          연동회원 유저아이디
     * @return 팝빌 URL (AccessToken값 포함. Token값은 응답후 30초까지만 유효함)
     * @throws PopbillException
     */
    public String getSenderNumberMgtURL(String CorpNum, String UserID) throws PopbillException;


    /**
     *  팩스 전송내역 팝업 URL을 반환
     *
     * @param CorpNum
     *          연동회원 사업자번호
     * @param UserID
     *          연동회원 유저아이디
     * @return 팝빌 URL (AccessToken값 포함. Token값은 응답후 30초까지만 유효함)
     * @throws PopbillException
     */
    public String getSentListURL(String CorpNum, String UserID) throws PopbillException;


    /**
     *  발신번호 등록여부 확인
     *
     * @param CorpNum
     *          연동회원 사업자번호
     * @param SenderNumber
     *          발신번호
     * @return Response 응답
     * @throws PopbillException
     */
    public Response checkSenderNumber(String CorpNum, String SenderNumber) throws PopbillException;

    /**
     *  발신번호 등록여부 확인
     *
     * @param CorpNum
     *          연동회원 사업자번호
     * @param SenderNumber
     *          발신번호
     * @param UserID
     *          연동회원 유저아이디
     * @return Response 응답
     * @throws PopbillException
     */
    public Response checkSenderNumber(String CorpNum, String SenderNumber, String UserID) throws PopbillException;

}
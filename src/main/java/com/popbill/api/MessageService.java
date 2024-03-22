package com.popbill.api;

import java.io.File;
import java.util.Date;

import com.popbill.api.message.AutoDeny;
import com.popbill.api.message.AutoDenyNumberInfo;
import com.popbill.api.message.MSGSearchResult;
import com.popbill.api.message.Message;
import com.popbill.api.message.MessageBriefInfo;
import com.popbill.api.message.MessageType;
import com.popbill.api.message.SenderNumber;
import com.popbill.api.message.SentMessage;

/**
 * Message Service Interface.
 * 
 * @author KimSeongjun
 * @version 1.0.0
 */
public interface MessageService extends BaseService {

    /**
     * 회원의 문자메시지 전송단가 확인
     * 
     * @param CorpNum
     *            팝빌회원 사업자번호
     * @param MsgType
     *            메시지 유형
     * @return 단가 (ex. 11.0)
     * @throws PopbillException
     */
    public float getUnitCost(String CorpNum, MessageType MsgType)
            throws PopbillException;
    
    /**
     * 팝빌 문자메시지 관련 URL 확인. 반환한 url은 30초이내에 브라우져에 표시하여야 함.
     * 
     * @param CorpNum
     *            팝빌회원 사업자번호
     * @param TOGO
     *            지정값. (BOX : 문자전송 내역 조회 팝업)
     * @return 팝빌 URL (AccessToken값 포함. Token값은 응답후 30초까지만 유효함)
     * @throws PopbillException
     */
    public String getURL(String CorpNum, String TOGO)
            throws PopbillException;
    
    /**
     * 팝빌 문자메시지 관련 URL 확인. 반환한 url은 30초이내에 브라우져에 표시하여야 함.
     * 
     * @param CorpNum
     *            팝빌회원 사업자번호
     * @param UserID
     *            팝빌회원의 회원아이디
     * @param TOGO
     *            지정값. (BOX : 문자전송 내역 조회 팝업)
     * @return 팝빌 URL (AccessToken값 포함. Token값은 응답후 30초까지만 유효함)
     * @throws PopbillException
     */
    public String getURL(String CorpNum, String UserID, String TOGO)
            throws PopbillException;

    /**
     * 단문문자메시지 1건 전송
     * 
     * @param CorpNum
     *            팝빌회원 사업자번호
     * @param Sender
     *            발신자번호
     * @param Receiver
     *            수신자번호
     * @param ReceiverName
     *            수신자명칭
     * @param Content
     *            단문메시지 내용 최대 90Byte.
     * @param ReserveDT
     *            예약전송시 예약일시.
     * @param UserID
     *            팝빌회원 아이디
     * @return ReceiptNum 접수번호.
     * @throws PopbillException
     */
    public String sendSMS(String CorpNum, String Sender, String Receiver,
            String ReceiverName, String Content, Date ReserveDT, String UserID)
            throws PopbillException;
    
    /**
     * 단문문자메시지 1건 전송
     * 
     * @param CorpNum
     *            팝빌회원 사업자번호
     * @param Sender
     *            발신자번호
     * @param Receiver
     *            수신자번호
     * @param ReceiverName
     *            수신자명칭
     * @param Content
     *            단문메시지 내용 최대 90Byte.
     * @param ReserveDT
     *            예약전송시 예약일시.
     * @param AdsYN
     *            광고문자 전송여부 
     * @param UserID
     *            팝빌회원 아이디
     * @return ReceiptNum 접수번호.
     * @throws PopbillException
     */
    public String sendSMS(String CorpNum, String Sender, String Receiver,
            String ReceiverName, String Content, Date ReserveDT, Boolean AdsYN, String UserID)
            throws PopbillException;


    /**
     * 단문문자메시지 1건 전송
     *
     * @param CorpNum
     *            팝빌회원 사업자번호
     * @param Sender
     *            발신자번호
     * @param Receiver
     *            수신자번호
     * @param ReceiverName
     *            수신자명칭
     * @param Content
     *            단문메시지 내용 최대 90Byte.
     * @param ReserveDT
     *            예약전송시 예약일시.
     * @param UserID
     *            팝빌회원 아이디
     * @param RequestNum
     *            전송요청번호
     * @return ReceiptNum 접수번호.
     * @throws PopbillException
     */
    public String sendSMS(String CorpNum, String Sender, String Receiver,
                          String ReceiverName, String Content, Date ReserveDT, String UserID, String RequestNum)
            throws PopbillException;

    /**
     * 단문문자메시지 1건 전송
     *
     * @param CorpNum
     *            팝빌회원 사업자번호
     * @param Sender
     *            발신자번호
     * @param Receiver
     *            수신자번호
     * @param ReceiverName
     *            수신자명칭
     * @param Content
     *            단문메시지 내용 최대 90Byte.
     * @param ReserveDT
     *            예약전송시 예약일시.
     * @param AdsYN
     *            광고문자 전송여부
     * @param UserID
     *            팝빌회원 아이디
     * @param RequestNum
     *            전송요청번호
     * @return ReceiptNum 접수번호.
     * @throws PopbillException
     */
    public String sendSMS(String CorpNum, String Sender, String Receiver,
                          String ReceiverName, String Content, Date ReserveDT, Boolean AdsYN, String UserID, String RequestNum)
            throws PopbillException;

    /**
     * 단문문자메시지 다량 전송. 1회 최대 1000건.
     * 
     * @param CorpNum
     *            팝빌회원 사업자번호
     * @param Messages
     *            메시지 배열.
     * @param ReserveDT
     *            예약일시
     * @param UserID
     *            팝빌회원 아이디
     * @return ReceiptNum 접수번호.
     * @throws PopbillException
     */
    public String sendSMS(String CorpNum, Message[] Messages, Date ReserveDT,
            String UserID) throws PopbillException;

    /**
     * 단문문자메시지 다량전송. 발신번호, 내용 동보전송. 1회 최대 1000건.
     * 
     * @param CorpNum
     *            팝빌회원 사업자번호
     * @param Sender
     *            동보전송 발신번호
     * @param Content
     *            동보전송 단문문자메시지 내용.
     * @param Messages
     *            메시지 배열. 수신자번호, 수신자명칭을 기재. 별도 발신번호와 내용 기재시 해당건만 개별전송.
     * @param ReserveDT
     *            예약일시
     * @param UserID
     *            팝빌회원 아이디
     * @return ReceiptNum 접수번호.
     * @throws PopbillException
     */
    public String sendSMS(String CorpNum, String Sender, String Content,
            Message[] Messages, Date ReserveDT, String UserID)
            throws PopbillException;
    
    /**
     * 단문문자메시지 다량전송. 발신번호, 내용 동보전송. 1회 최대 1000건.
     * 
     * @param CorpNum
     *            팝빌회원 사업자번호
     * @param Sender
     *            동보전송 발신번호
     * @param Content
     *            동보전송 단문문자메시지 내용.
     * @param Messages
     *            메시지 배열. 수신자번호, 수신자명칭을 기재. 별도 발신번호와 내용 기재시 해당건만 개별전송.
     * @param ReserveDT
     *            예약일시
     * @param AdsYN
     *            광고문자 전송여부
     * @param UserID
     *            팝빌회원 아이디
     * @return ReceiptNum 접수번호.
     * @throws PopbillException
     */
    public String sendSMS(String CorpNum, String Sender, String Content,
            Message[] Messages, Date ReserveDT, Boolean AdsYN, String UserID)
            throws PopbillException;
    
    /**
     * 단문문자메시지 다량전송. 발신번호, 내용 동보전송. 1회 최대 1000건.
     * 
     * @param CorpNum
     *            팝빌회원 사업자번호
     * @param Sender
     *            동보전송 발신번호
     * @param SenderName
     *            동보전송 발신자명
     * @param Content
     *            동보전송 단문문자메시지 내용.
     * @param Messages
     *            메시지 배열. 수신자번호, 수신자명칭을 기재. 별도 발신번호와 내용 기재시 해당건만 개별전송.
     * @param ReserveDT
     *            예약일시
     * @param AdsYN
     *            광고문자 전송여부
     * @param UserID
     *            팝빌회원 아이디
     * @return ReceiptNum 접수번호.
     * @throws PopbillException
     */
    public String sendSMS(String CorpNum, String Sender, String SenderName, String Content,
            Message[] Messages, Date ReserveDT, Boolean AdsYN, String UserID)
            throws PopbillException;


    /**
     * 단문문자메시지 다량 전송. 1회 최대 1000건.
     * 
     * @param CorpNum
     *            팝빌회원 사업자번호
     * @param Messages
     *            메시지 배열.
     * @param ReserveDT
     *            예약일시
     * @param UserID
     *            팝빌회원 아이디
     * @param RequestNum
     *            전송요청번호
     * @return ReceiptNum 접수번호.
     * @throws PopbillException
     */
    public String sendSMS(String CorpNum, Message[] Messages, Date ReserveDT,
            String UserID, String RequestNum) throws PopbillException;

    /**
     * 단문문자메시지 다량전송. 발신번호, 내용 동보전송. 1회 최대 1000건.
     * 
     * @param CorpNum
     *            팝빌회원 사업자번호
     * @param Sender
     *            동보전송 발신번호
     * @param Content
     *            동보전송 단문문자메시지 내용.
     * @param Messages
     *            메시지 배열. 수신자번호, 수신자명칭을 기재. 별도 발신번호와 내용 기재시 해당건만 개별전송.
     * @param ReserveDT
     *            예약일시
     * @param UserID
     *            팝빌회원 아이디
     * @param RequestNum
     *            전송요청번호
     * @return ReceiptNum 접수번호.
     * @throws PopbillException
     */
    public String sendSMS(String CorpNum, String Sender, String Content,
            Message[] Messages, Date ReserveDT, String UserID, String RequestNum)
            throws PopbillException;
    
    /**
     * 단문문자메시지 다량전송. 발신번호, 내용 동보전송. 1회 최대 1000건.
     * 
     * @param CorpNum
     *            팝빌회원 사업자번호
     * @param Sender
     *            동보전송 발신번호
     * @param Content
     *            동보전송 단문문자메시지 내용.
     * @param Messages
     *            메시지 배열. 수신자번호, 수신자명칭을 기재. 별도 발신번호와 내용 기재시 해당건만 개별전송.
     * @param ReserveDT
     *            예약일시
     * @param AdsYN
     *            광고문자 전송여부
     * @param UserID
     *            팝빌회원 아이디
     * @param RequestNum
     *            전송요청번호
     * @return ReceiptNum 접수번호.
     * @throws PopbillException
     */
    public String sendSMS(String CorpNum, String Sender, String Content,
            Message[] Messages, Date ReserveDT, Boolean AdsYN, String UserID, String RequestNum)
            throws PopbillException;
    
    /**
     * 단문문자메시지 다량전송. 발신번호, 내용 동보전송. 1회 최대 1000건.
     * 
     * @param CorpNum
     *            팝빌회원 사업자번호
     * @param Sender
     *            동보전송 발신번호
     * @param SenderName
     *            동보전송 발신자명
     * @param Content
     *            동보전송 단문문자메시지 내용.
     * @param Messages
     *            메시지 배열. 수신자번호, 수신자명칭을 기재. 별도 발신번호와 내용 기재시 해당건만 개별전송.
     * @param ReserveDT
     *            예약일시
     * @param AdsYN
     *            광고문자 전송여부
     * @param UserID
     *            팝빌회원 아이디
     * @param RequestNum
     *            전송요청번호
     * @return ReceiptNum 접수번호.
     * @throws PopbillException
     */
    public String sendSMS(String CorpNum, String Sender, String SenderName, String Content,
            Message[] Messages, Date ReserveDT, Boolean AdsYN, String UserID, String RequestNum)
            throws PopbillException;

    /**
     * 장문문자메시지 1건 전송
     * 
     * @param CorpNum
     *            팝빌회원 사업자번호
     * @param Sender
     *            발신자번호
     * @param Receiver
     *            수신자번호
     * @param ReceiverName
     *            수신자명칭
     * @param Subject
     *            장문메시지 제목
     * @param Content
     *            장문메시지 내용 최대 2000Byte.
     * @param ReserveDT
     *            예약전송시 예약일시.
     * @param UserID
     *            팝빌회원 아이디
     * @return ReceiptNum 접수번호.
     * @throws PopbillException
     */
    public String sendLMS(String CorpNum, String Sender, String Receiver,
            String ReceiverName, String Subject, String Content,
            Date ReserveDT, String UserID) throws PopbillException;
    
    /**
     * 장문문자메시지 1건 전송
     * 
     * @param CorpNum
     *            팝빌회원 사업자번호
     * @param Sender
     *            발신자번호
     * @param Receiver
     *            수신자번호
     * @param ReceiverName
     *            수신자명칭
     * @param Subject
     *            장문메시지 제목
     * @param Content
     *            장문메시지 내용 최대 2000Byte.
     * @param ReserveDT
     *            예약전송시 예약일시.
     * @param AdsYN
     *            광고문자 전송여부 
     * @param UserID
     *            팝빌회원 아이디
     * @return ReceiptNum 접수번호.
     * @throws PopbillException
     */
    public String sendLMS(String CorpNum, String Sender, String Receiver,
            String ReceiverName, String Subject, String Content,
            Date ReserveDT, Boolean AdsYN, String UserID) throws PopbillException;

    /**
     * 장문문자메시지 다량 전송. 1회 최대 1000건.
     * 
     * @param CorpNum
     *            팝빌회원 사업자번호
     * @param Messages
     *            메시지 배열.
     * @param ReserveDT
     *            예약일시
     * @param UserID
     *            팝빌회원 아이디
     * @return ReceiptNum 접수번호.
     * @throws PopbillException
     */
    public String sendLMS(String CorpNum, Message[] Messages, Date ReserveDT,
            String UserID) throws PopbillException;

    /**
     * 장문문자메시지 다량전송. 발신번호, 내용 동보전송. 1회 최대 1000건.
     * 
     * @param CorpNum
     *            팝빌회원 사업자번호
     * @param Sender
     *            동보전송 발신번호
     * @param Subject
     *            동보전송 장문메시지 제목
     * @param Content
     *            동보전송 장문메시지 내용.
     * @param Messages
     *            메시지 배열. 수신자번호, 수신자명칭을 기재. 별도 발신번호와 내용 기재시 해당건만 개별전송.
     * @param ReserveDT
     *            예약일시
     * @param UserID
     *            팝빌회원 아이디
     * @return ReceiptNum 접수번호.
     * @throws PopbillException
     */
    public String sendLMS(String CorpNum, String Sender, String Subject,
            String Content, Message[] Messages, Date ReserveDT, String UserID)
            throws PopbillException;
    
    /**
     * 장문문자메시지 다량전송. 발신번호, 내용 동보전송. 1회 최대 1000건.
     * 
     * @param CorpNum
     *            팝빌회원 사업자번호
     * @param Sender
     *            동보전송 발신번호
     * @param Subject
     *            동보전송 장문메시지 제목
     * @param Content
     *            동보전송 장문메시지 내용.
     * @param Messages
     *            메시지 배열. 수신자번호, 수신자명칭을 기재. 별도 발신번호와 내용 기재시 해당건만 개별전송.
     * @param ReserveDT
     *            예약일시
     * @parma AdsYN
     *            광고문자 전송여부 
     * @param UserID
     *            팝빌회원 아이디
     * @return ReceiptNum 접수번호.
     * @throws PopbillException
     */
    public String sendLMS(String CorpNum, String Sender, String Subject, String Content, 
            Message[] Messages, Date ReserveDT, Boolean AdsYN, String UserID)
            throws PopbillException;
    
    /**
     * 장문문자메시지 다량전송. 발신번호, 내용 동보전송. 1회 최대 1000건.
     * 
     * @param CorpNum
     *            팝빌회원 사업자번호
     * @param Sender
     *            동보전송 발신번호
     * @param SenderName
     *            동보전송 발신자명
     * @param Subject
     *            동보전송 장문메시지 제목
     * @param Content
     *            동보전송 장문메시지 내용.
     * @param Messages
     *            메시지 배열. 수신자번호, 수신자명칭을 기재. 별도 발신번호와 내용 기재시 해당건만 개별전송.
     * @param ReserveDT
     *            예약일시
     * @parma AdsYN
     *            광고문자 전송여부 
     * @param UserID
     *            팝빌회원 아이디
     * @return ReceiptNum 접수번호.
     * @throws PopbillException
     */
    public String sendLMS(String CorpNum, String Sender, String SenderName, String Subject, String Content, 
            Message[] Messages, Date ReserveDT, Boolean AdsYN, String UserID)
            throws PopbillException;

    /**
     * 장문문자메시지 1건 전송
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param Sender
     *            발신자번호
     * @param Receiver
     *            수신자번호
     * @param ReceiverName
     *            수신자명칭
     * @param Subject
     *            장문메시지 제목
     * @param Content
     *            장문메시지 내용 최대 2000Byte.
     * @param ReserveDT
     *            예약전송시 예약일시.
     * @param UserID
     *            팝빌회원 아이디
     * @param RequestNum
     *            전송요청번호
     * @return ReceiptNum 접수번호.
     * @throws PopbillException
     */
    public String sendLMS(String CorpNum, String Sender, String Receiver,
            String ReceiverName, String Subject, String Content,
            Date ReserveDT, String UserID, String RequestNum) throws PopbillException;
    
    /**
     * 장문문자메시지 1건 전송
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param Sender
     *            발신자번호
     * @param Receiver
     *            수신자번호
     * @param ReceiverName
     *            수신자명칭
     * @param Subject
     *            장문메시지 제목
     * @param Content
     *            장문메시지 내용 최대 2000Byte.
     * @param ReserveDT
     *            예약전송시 예약일시.
     * @param AdsYN
     *            광고문자 전송여부 
     * @param UserID
     *            팝빌회원 아이디
     * @param RequestNum
     *            전송요청번호
     * @return ReceiptNum 접수번호.
     * @throws PopbillException
     */
    public String sendLMS(String CorpNum, String Sender, String Receiver,
            String ReceiverName, String Subject, String Content,
            Date ReserveDT, Boolean AdsYN, String UserID, String RequestNum) throws PopbillException;

    /**
     * 장문문자메시지 다량 전송. 1회 최대 1000건.
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param Messages
     *            메시지 배열.
     * @param ReserveDT
     *            예약일시
     * @param UserID
     *            연동회원 아이디
     * @param RequestNum
     *            전송요청번호
     * @return ReceiptNum 접수번호.
     * @throws PopbillException
     */
    public String sendLMS(String CorpNum, Message[] Messages, Date ReserveDT,
            String UserID, String RequestNum) throws PopbillException;

    /**
     * 장문문자메시지 다량전송. 발신번호, 내용 동보전송. 1회 최대 1000건.
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param Sender
     *            동보전송 발신번호
     * @param Subject
     *            동보전송 장문메시지 제목
     * @param Content
     *            동보전송 장문메시지 내용.
     * @param Messages
     *            메시지 배열. 수신자번호, 수신자명칭을 기재. 별도 발신번호와 내용 기재시 해당건만 개별전송.
     * @param ReserveDT
     *            예약일시
     * @param UserID
     *            연동회원 아이디
     * @param RequestNum
     *            전송요청번호
     * @return ReceiptNum 접수번호.
     * @throws PopbillException
     */
    public String sendLMS(String CorpNum, String Sender, String Subject,
            String Content, Message[] Messages, Date ReserveDT, String UserID, String RequestNum)
            throws PopbillException;
    
    /**
     * 장문문자메시지 다량전송. 발신번호, 내용 동보전송. 1회 최대 1000건.
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param Sender
     *            동보전송 발신번호
     * @param Subject
     *            동보전송 장문메시지 제목
     * @param Content
     *            동보전송 장문메시지 내용.
     * @param Messages
     *            메시지 배열. 수신자번호, 수신자명칭을 기재. 별도 발신번호와 내용 기재시 해당건만 개별전송.
     * @param ReserveDT
     *            예약일시
     * @parma AdsYN
     *            광고문자 전송여부 
     * @param UserID
     *            연동회원 아이디
     * @param RequestNum
     *            전송요청번호
     * @return ReceiptNum 접수번호.
     * @throws PopbillException
     */
    public String sendLMS(String CorpNum, String Sender, String Subject, String Content, 
            Message[] Messages, Date ReserveDT, Boolean AdsYN, String UserID, String RequestNum)
            throws PopbillException;
    
    /**
     * 장문문자메시지 다량전송. 발신번호, 내용 동보전송. 1회 최대 1000건.
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param Sender
     *            동보전송 발신번호
     * @param SenderName
     *            동보전송 발신자명
     * @param Subject
     *            동보전송 장문메시지 제목
     * @param Content
     *            동보전송 장문메시지 내용.
     * @param Messages
     *            메시지 배열. 수신자번호, 수신자명칭을 기재. 별도 발신번호와 내용 기재시 해당건만 개별전송.
     * @param ReserveDT
     *            예약일시
     * @parma AdsYN
     *            광고문자 전송여부 
     * @param UserID
     *            연동회원 아이디
     * @param RequestNum
     *            전송요청번호
     * @return ReceiptNum 접수번호.
     * @throws PopbillException
     */
    public String sendLMS(String CorpNum, String Sender, String SenderName, String Subject, String Content, 
            Message[] Messages, Date ReserveDT, Boolean AdsYN, String UserID, String RequestNum)
            throws PopbillException;
    
    /**
     * 멀티문자메시지 1건 전송
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param Sender
     *            발신자번호
     * @param Receiver
     *            수신자번호
     * @param ReceiverName
     *            수신자명칭
     * @param Subject
     *            장문메시지 제목
     * @param Content
     *            장문메시지 내용 최대 2000Byte.
     * @param File
     *            전송파일 최대크기 300Kbyte
     * @param ReserveDT
     *            예약전송시 예약일시.
     * @param UserID
     *            팝빌회원 아이디
     * @return ReceiptNum 접수번호.
     * @throws PopbillException
     */
    public String sendMMS(String CorpNum, String Sender, String Receiver,
            String ReceiverName, String Subject, String Content, File File,
            Date ReserveDT, String UserID) throws PopbillException;
    
    /**
     * 멀티문자메시지 1건 전송
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param Sender
     *            발신자번호
     * @param Receiver
     *            수신자번호
     * @param ReceiverName
     *            수신자명칭
     * @param Subject
     *            장문메시지 제목
     * @param Content
     *            장문메시지 내용 최대 2000Byte.
     * @param File
     *            전송파일 최대크기 300Kbyte
     * @param ReserveDT
     *            예약전송시 예약일시.
     * @param UserID
     *            팝빌회원 아이디
     * @param AdsYN
     *            광고문자 전송여부 
     * @return ReceiptNum 접수번호.
     * @throws PopbillException
     */
    public String sendMMS(String CorpNum, String Sender, String Receiver,
            String ReceiverName, String Subject, String Content, File File,
            Date ReserveDT, Boolean AdsYN, String UserID) throws PopbillException;

    /**
     * 멀티 문자메시지 다량 전송. 1회 최대 1000건.
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param Messages
     *            메시지 배열.
     * @param File
     *            전송파일 최대크기 300Kbyte
     * @param ReserveDT
     *            예약일시
     * @param UserID
     *            연동회원 아이디
     * @return ReceiptNum 접수번호.
     * @throws PopbillException
     */
    public String sendMMS(String CorpNum, Message[] Messages, File File, Date ReserveDT,
            String UserID) throws PopbillException;

    /**
     * 멀티 문자메시지 다량 전송. 발신번호, 내용 동보전송. 1회 최대 1000건.
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param Sender
     *            동보전송 발신번호
     * @param Subject
     *            동보전송 장문메시지 제목
     * @param Content
     *            동보전송 장문메시지 내용.
     * @param Messages
     *            메시지 배열. 수신자번호, 수신자명칭을 기재. 별도 발신번호와 내용 기재시 해당건만 개별전송.
     * @param File
     *            전송파일 최대크기 300Kbyte
     * @param ReserveDT
     *            예약일시
     * @param UserID
     *            연동회원 아이디
     * @return ReceiptNum 접수번호.
     * @throws PopbillException
     */
    public String sendMMS(String CorpNum, String Sender, String Subject,
            String Content, Message[] Messages, File File, Date ReserveDT, String UserID)
            throws PopbillException;
    
    /**
     * 멀티 문자메시지 다량 전송. 발신번호, 내용 동보전송. 1회 최대 1000건.
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param Sender
     *            동보전송 발신번호
     * @param Subject
     *            동보전송 장문메시지 제목
     * @param Content
     *            동보전송 장문메시지 내용.
     * @param Messages
     *            메시지 배열. 수신자번호, 수신자명칭을 기재. 별도 발신번호와 내용 기재시 해당건만 개별전송.
     * @param File
     *            전송파일 최대크기 300Kbyte
     * @param ReserveDT
     *            예약일시
     * @param AdsYN
     *          광고문자 전송여부 
     * @param UserID
     *            연동회원 아이디
     * @return ReceiptNum 접수번호.
     * @throws PopbillException
     */
    public String sendMMS(String CorpNum, String Sender, String Subject, String Content, 
            Message[] Messages, File File, Date ReserveDT, Boolean AdsYN, String UserID)
            throws PopbillException;
    
    /**
     * 멀티 문자메시지 다량 전송. 발신번호, 내용 동보전송. 1회 최대 1000건.
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param Sender
     *            동보전송 발신번호
     * @param SenderName
     *            동보전송 발신자명
     * @param Subject
     *            동보전송 장문메시지 제목
     * @param Content
     *            동보전송 장문메시지 내용.
     * @param Messages
     *            메시지 배열. 수신자번호, 수신자명칭을 기재. 별도 발신번호와 내용 기재시 해당건만 개별전송.
     * @param File
     *            전송파일 최대크기 300Kbyte
     * @param ReserveDT
     *            예약일시
     * @param AdsYN
     *          광고문자 전송여부 
     * @param UserID
     *            연동회원 아이디
     * @return ReceiptNum 접수번호.
     * @throws PopbillException
     */
    public String sendMMS(String CorpNum, String Sender, String SenderName, String Subject, String Content, 
            Message[] Messages, File File, Date ReserveDT, Boolean AdsYN, String UserID)
            throws PopbillException;

    /**
     * 멀티문자메시지 1건 전송
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param Sender
     *            발신자번호
     * @param Receiver
     *            수신자번호
     * @param ReceiverName
     *            수신자명칭
     * @param Subject
     *            장문메시지 제목
     * @param Content
     *            장문메시지 내용 최대 2000Byte.
     * @param File
     *            전송파일 최대크기 300Kbyte
     * @param ReserveDT
     *            예약전송시 예약일시.
     * @param UserID
     *            팝빌회원 아이디
     * @param RequestNum
     *            전송요청번호
     * @return ReceiptNum 접수번호.
     * @throws PopbillException
     */
    public String sendMMS(String CorpNum, String Sender, String Receiver,
            String ReceiverName, String Subject, String Content, File File,
            Date ReserveDT, String UserID, String RequestNum) throws PopbillException;
    
    /**
     * 멀티문자메시지 1건 전송
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param Sender
     *            발신자번호
     * @param Receiver
     *            수신자번호
     * @param ReceiverName
     *            수신자명칭
     * @param Subject
     *            장문메시지 제목
     * @param Content
     *            장문메시지 내용 최대 2000Byte.
     * @param File
     *            전송파일 최대크기 300Kbyte
     * @param ReserveDT
     *            예약전송시 예약일시.
     * @param UserID
     *            팝빌회원 아이디
     * @param AdsYN
     *            광고문자 전송여부 
     * @param RequestNum
     *            전송요청번호
     * @return ReceiptNum 접수번호.
     * @throws PopbillException
     */
    public String sendMMS(String CorpNum, String Sender, String Receiver,
            String ReceiverName, String Subject, String Content, File File,
            Date ReserveDT, Boolean AdsYN, String UserID, String RequestNum) throws PopbillException;

    /**
     * 멀티 문자메시지 다량 전송. 1회 최대 1000건.
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param Messages
     *            메시지 배열.
     * @param File
     *            전송파일 최대크기 300Kbyte
     * @param ReserveDT
     *            예약일시
     * @param UserID
     *            연동회원 아이디
     * @param RequestNum
     *            전송요청번호
     * @return ReceiptNum 접수번호.
     * @throws PopbillException
     */
    public String sendMMS(String CorpNum, Message[] Messages, File File, Date ReserveDT,
            String UserID, String RequestNum) throws PopbillException;

    /**
     * 멀티 문자메시지 다량 전송. 발신번호, 내용 동보전송. 1회 최대 1000건.
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param Sender
     *            동보전송 발신번호
     * @param Subject
     *            동보전송 장문메시지 제목
     * @param Content
     *            동보전송 장문메시지 내용.
     * @param Messages
     *            메시지 배열. 수신자번호, 수신자명칭을 기재. 별도 발신번호와 내용 기재시 해당건만 개별전송.
     * @param File
     *            전송파일 최대크기 300Kbyte
     * @param ReserveDT
     *            예약일시
     * @param UserID
     *            연동회원 아이디
     * @param RequestNum
     *            전송요청번호
     * @return ReceiptNum 접수번호.
     * @throws PopbillException
     */
    public String sendMMS(String CorpNum, String Sender, String Subject,
            String Content, Message[] Messages, File File, Date ReserveDT, String UserID, String RequestNum)
            throws PopbillException;
    
    /**
     * 멀티 문자메시지 다량 전송. 발신번호, 내용 동보전송. 1회 최대 1000건.
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param Sender
     *            동보전송 발신번호
     * @param Subject
     *            동보전송 장문메시지 제목
     * @param Content
     *            동보전송 장문메시지 내용.
     * @param Messages
     *            메시지 배열. 수신자번호, 수신자명칭을 기재. 별도 발신번호와 내용 기재시 해당건만 개별전송.
     * @param File
     *            전송파일 최대크기 300Kbyte
     * @param ReserveDT
     *            예약일시
     * @param AdsYN
     *          광고문자 전송여부 
     * @param UserID
     *            연동회원 아이디
     * @param RequestNum
     *            전송요청번호
     * @return ReceiptNum 접수번호.
     * @throws PopbillException
     */
    public String sendMMS(String CorpNum, String Sender, String Subject, String Content, 
            Message[] Messages, File File, Date ReserveDT, Boolean AdsYN, String UserID, String RequestNum)
            throws PopbillException;
    
    /**
     * 멀티 문자메시지 다량 전송. 발신번호, 내용 동보전송. 1회 최대 1000건.
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param Sender
     *            동보전송 발신번호
     * @param SenderName
     *            동보전송 발신자명
     * @param Subject
     *            동보전송 장문메시지 제목
     * @param Content
     *            동보전송 장문메시지 내용.
     * @param Messages
     *            메시지 배열. 수신자번호, 수신자명칭을 기재. 별도 발신번호와 내용 기재시 해당건만 개별전송.
     * @param File
     *            전송파일 최대크기 300Kbyte
     * @param ReserveDT
     *            예약일시
     * @param AdsYN
     *          광고문자 전송여부 
     * @param UserID
     *            연동회원 아이디
     * @param RequestNum
     *            전송요청번호
     * @return ReceiptNum 접수번호.
     * @throws PopbillException
     */
    public String sendMMS(String CorpNum, String Sender, String SenderName, String Subject, String Content, 
            Message[] Messages, File File, Date ReserveDT, Boolean AdsYN, String UserID, String RequestNum)
            throws PopbillException;

    /**
     * 단/장문 문자메시지(메시지 길이에 따라 단문/장문을 선택하여 전송) 1건 전송
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param Sender
     *            발신자번호
     * @param Receiver
     *            수신자번호
     * @param ReceiverName
     *            수신자명칭
     * @param Subject
     *            메시지 제목
     * @param Content
     *            메시지 내용
     * @param ReserveDT
     *            예약전송시 예약일시.
     * @param UserID
     *            팝빌회원 아이디
     * @return ReceiptNum 접수번호.
     * @throws PopbillException
     */
    public String sendXMS(String CorpNum, String Sender, String Receiver,
            String ReceiverName, String Subject, String Content,
            Date ReserveDT, String UserID) throws PopbillException;
    
    /**
     * 단/장문 문자메시지(메시지 길이에 따라 단문/장문을 선택하여 전송) 1건 전송
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param Sender
     *            발신자번호
     * @param Receiver
     *            수신자번호
     * @param ReceiverName
     *            수신자명칭
     * @param Subject
     *            메시지 제목
     * @param Content
     *            메시지 내용
     * @param ReserveDT
     *            예약전송시 예약일시.
     * @param AdsYN
     *            광고문자 전송여부.
     * @param UserID
     *            팝빌회원 아이디
     * @return ReceiptNum 접수번호.
     * @throws PopbillException
     */
    public String sendXMS(String CorpNum, String Sender, String Receiver,
            String ReceiverName, String Subject, String Content,
            Date ReserveDT, Boolean AdsYN, String UserID) throws PopbillException;

    /**
     * 단/장문 문자메시지(메시지 길이에 따라 단문/장문을 선택하여 전송) 다량 전송. 1회 최대 1000건.
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param Messages
     *            메시지 배열.
     * @param ReserveDT
     *            예약일시
     * @param UserID
     *            연동회원 아이디
     * @return ReceiptNum 접수번호.
     * @throws PopbillException
     */
    public String sendXMS(String CorpNum, Message[] Messages, Date ReserveDT,
            String UserID) throws PopbillException;

    /**
     * 단/장문 문자메시지(메시지 길이에 따라 단문/장문을 선택하여 전송) 다량전송. 발신번호, 내용 동보전송. 1회 최대 1000건.
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param Sender
     *            동보전송 발신번호
     * @param Subject
     *            동보전송 메시지 제목
     * @param Content
     *            동보전송 메시지 내용.
     * @param Messages
     *            메시지 배열. 수신자번호, 수신자명칭을 기재. 별도 발신번호와 내용 기재시 해당건만 개별전송.
     * @param ReserveDT
     *            예약일시
     * @param UserID
     *            연동회원 아이디
     * @return ReceiptNum 접수번호.
     * @throws PopbillException
     */
    public String sendXMS(String CorpNum, String Sender, String Subject,
            String Content, Message[] Messages, Date ReserveDT, String UserID)
            throws PopbillException;
    
    /**
     * 단/장문 문자메시지(메시지 길이에 따라 단문/장문을 선택하여 전송) 다량전송. 발신번호, 내용 동보전송. 1회 최대 1000건.
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param Sender
     *            동보전송 발신번호
     * @param Subject
     *            동보전송 메시지 제목
     * @param Content
     *            동보전송 메시지 내용.
     * @param Messages
     *            메시지 배열. 수신자번호, 수신자명칭을 기재. 별도 발신번호와 내용 기재시 해당건만 개별전송.
     * @param ReserveDT
     *            예약일시
     * @param AdsYN
     *            광고문자 전송여부
     * @param UserID
     *            연동회원 아이디
     * @return ReceiptNum 접수번호.
     * @throws PopbillException
     */
    public String sendXMS(String CorpNum, String Sender, String Subject,
            String Content, Message[] Messages, Date ReserveDT, Boolean AdsYN, String UserID)
            throws PopbillException;
    
    /**
     * 단/장문 문자메시지(메시지 길이에 따라 단문/장문을 선택하여 전송) 다량전송. 발신번호, 내용 동보전송. 1회 최대 1000건.
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param Sender
     *            동보전송 발신번호
     * @param SenderName
     *            동보전송 발신자명
     * @param Subject
     *            동보전송 메시지 제목
     * @param Content
     *            동보전송 메시지 내용.
     * @param Messages
     *            메시지 배열. 수신자번호, 수신자명칭을 기재. 별도 발신번호와 내용 기재시 해당건만 개별전송.
     * @param ReserveDT
     *            예약일시
     * @param AdsYN
     *            광고문자 전송여부
     * @param UserID
     *            연동회원 아이디
     * @return ReceiptNum 접수번호.
     * @throws PopbillException
     */
    public String sendXMS(String CorpNum, String Sender, String SenderName, String Subject,
            String Content, Message[] Messages, Date ReserveDT, Boolean AdsYN, String UserID)
            throws PopbillException;

    /**
     * 단/장문 문자메시지(메시지 길이에 따라 단문/장문을 선택하여 전송) 1건 전송
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param Sender
     *            발신자번호
     * @param Receiver
     *            수신자번호
     * @param ReceiverName
     *            수신자명칭
     * @param Subject
     *            메시지 제목
     * @param Content
     *            메시지 내용
     * @param ReserveDT
     *            예약전송시 예약일시.
     * @param UserID
     *            팝빌회원 아이디
     * @param RequestNum
     *            전송요청번호
     * @return ReceiptNum 접수번호.
     * @throws PopbillException
     */
    public String sendXMS(String CorpNum, String Sender, String Receiver,
            String ReceiverName, String Subject, String Content,
            Date ReserveDT, String UserID, String RequestNum) throws PopbillException;
    
    /**
     * 단/장문 문자메시지(메시지 길이에 따라 단문/장문을 선택하여 전송) 1건 전송
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param Sender
     *            발신자번호
     * @param Receiver
     *            수신자번호
     * @param ReceiverName
     *            수신자명칭
     * @param Subject
     *            메시지 제목
     * @param Content
     *            메시지 내용
     * @param ReserveDT
     *            예약전송시 예약일시.
     * @param AdsYN
     *            광고문자 전송여부.
     * @param UserID
     *            팝빌회원 아이디
     * @param RequestNum
     *            전송요청번호
     * @return ReceiptNum 접수번호.
     * @throws PopbillException
     */
    public String sendXMS(String CorpNum, String Sender, String Receiver,
            String ReceiverName, String Subject, String Content,
            Date ReserveDT, Boolean AdsYN, String UserID, String RequestNum) throws PopbillException;

    /**
     * 단/장문 문자메시지(메시지 길이에 따라 단문/장문을 선택하여 전송) 다량 전송. 1회 최대 1000건.
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param Messages
     *            메시지 배열.
     * @param ReserveDT
     *            예약일시
     * @param UserID
     *            연동회원 아이디
     * @param RequestNum
     *            전송요청번호
     * @return ReceiptNum 접수번호.
     * @throws PopbillException
     */
    public String sendXMS(String CorpNum, Message[] Messages, Date ReserveDT,
            String UserID, String RequestNum) throws PopbillException;

    /**
     * 단/장문 문자메시지(메시지 길이에 따라 단문/장문을 선택하여 전송) 다량전송. 발신번호, 내용 동보전송. 1회 최대 1000건.
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param Sender
     *            동보전송 발신번호
     * @param Subject
     *            동보전송 메시지 제목
     * @param Content
     *            동보전송 메시지 내용.
     * @param Messages
     *            메시지 배열. 수신자번호, 수신자명칭을 기재. 별도 발신번호와 내용 기재시 해당건만 개별전송.
     * @param ReserveDT
     *            예약일시
     * @param UserID
     *            연동회원 아이디
     * @param RequestNum
     *            전송요청번호
     * @return ReceiptNum 접수번호.
     * @throws PopbillException
     */
    public String sendXMS(String CorpNum, String Sender, String Subject,
            String Content, Message[] Messages, Date ReserveDT, String UserID, String RequestNum)
            throws PopbillException;
    
    /**
     * 단/장문 문자메시지(메시지 길이에 따라 단문/장문을 선택하여 전송) 다량전송. 발신번호, 내용 동보전송. 1회 최대 1000건.
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param Sender
     *            동보전송 발신번호
     * @param Subject
     *            동보전송 메시지 제목
     * @param Content
     *            동보전송 메시지 내용.
     * @param Messages
     *            메시지 배열. 수신자번호, 수신자명칭을 기재. 별도 발신번호와 내용 기재시 해당건만 개별전송.
     * @param ReserveDT
     *            예약일시
     * @param AdsYN
     *            광고문자 전송여부
     * @param UserID
     *            연동회원 아이디
     * @param RequestNum
     *            전송요청번호
     * @return ReceiptNum 접수번호.
     * @throws PopbillException
     */
    public String sendXMS(String CorpNum, String Sender, String Subject,
            String Content, Message[] Messages, Date ReserveDT, Boolean AdsYN, String UserID, String RequestNum)
            throws PopbillException;
    
    /**
     * 단/장문 문자메시지(메시지 길이에 따라 단문/장문을 선택하여 전송) 다량전송. 발신번호, 내용 동보전송. 1회 최대 1000건.
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param Sender
     *            동보전송 발신번호
     * @param SenderName
     *            동보전송 발신자명
     * @param Subject
     *            동보전송 메시지 제목
     * @param Content
     *            동보전송 메시지 내용.
     * @param Messages
     *            메시지 배열. 수신자번호, 수신자명칭을 기재. 별도 발신번호와 내용 기재시 해당건만 개별전송.
     * @param ReserveDT
     *            예약일시
     * @param AdsYN
     *            광고문자 전송여부
     * @param UserID
     *            연동회원 아이디
     * @param RequestNum
     *            전송요청번호
     * @return ReceiptNum 접수번호.
     * @throws PopbillException
     */
    public String sendXMS(String CorpNum, String Sender, String SenderName, String Subject,
            String Content, Message[] Messages, Date ReserveDT, Boolean AdsYN, String UserID, String RequestNum)
            throws PopbillException;    
    
    /**
     * 전송상태 확인
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param ReceiptNum
     *            전송시 접수번호.
     * @return SentMessage 배열.
     * @throws PopbillException
     */
    public SentMessage[] getMessages(String CorpNum, String ReceiptNum)
            throws PopbillException;
    
    /**
     * 전송상태 확인
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param ReceiptNum
     *            전송시 접수번호.
     * @param UserID
     *            연동회원 아이디
     * @return SentMessage 배열.
     * @throws PopbillException
     */
    public SentMessage[] getMessages(String CorpNum, String ReceiptNum, String UserID)
            throws PopbillException;    
    
    /**
     * 전송상태 확인
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param RequestNum
     *            전송요청번호
     * @return SentMessage 배열.
     * @throws PopbillException
     */
    public SentMessage[] getMessagesRN(String CorpNum, String RequestNum)
            throws PopbillException;
    
    /**
     * 전송상태 확인
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param RequestNum
     *            전송요청번호
     * @param UserID
     *            연동회원 아이디
     * @return SentMessage 배열.
     * @throws PopbillException
     */
    public SentMessage[] getMessagesRN(String CorpNum, String RequestNum, String UserID)
            throws PopbillException;        

    /**
     * 전송내역 요약정보 확인
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param ReceiptNumList
     *            문자전송 접수번호 배열
     * @return MessageBriefInfo 배열
     * @throws PopbillException
     */
    public MessageBriefInfo[] getStates(String CorpNum, String[] ReceiptNumList)
            throws PopbillException;    

    /**
     * 전송내역 요약정보 확인
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param ReceiptNumList
     *            문자전송 접수번호 배열
     * @param UserID
     *            연동회원 아이디
     * @return MessageBriefInfo 배열
     * @throws PopbillException
     */
    public MessageBriefInfo[] getStates(String CorpNum, String[] ReceiptNumList, String UserID)
            throws PopbillException;    
    
    /**
     * 예약 메시지 전송 취소. 예약시간 기준 10분전의 건만 취소 가능.
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param ReceiptNum
     *            전송시 접수번호.
     * @return Response 응답
     * @throws PopbillException
     */
    public Response cancelReserve(String CorpNum, String ReceiptNum) throws PopbillException;

    /**
     * 예약 메시지 전송 취소. 예약시간 기준 10분전의 건만 취소 가능.
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param ReceiptNum
     *            전송시 접수번호.
     * @param UserID
     *            연동회원 아이디
     * @return Response 응답
     * @throws PopbillException
     */
    public Response cancelReserve(String CorpNum, String ReceiptNum,
            String UserID) throws PopbillException;
    
    /**
     * 예약 메시지 전송 취소. 예약시간 기준 10분전의 건만 취소 가능.
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
     * 예약 메시지 전송 취소. 예약시간 기준 10분전의 건만 취소 가능.
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
     * 예약 메시지 전송 취소. 예약시간 기준 10분전의 건만 취소 가능.
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param ReceiptNum
     *            전송시 접수번호.
     * @param ReceiveNum
     *            전송시 수신번호
     * @return Response 응답
     * @throws PopbillException
     */
    public Response cancelReservebyRCV(String CorpNum, String ReceiptNum, String ReceiveNum) throws PopbillException;
    
    /**
     * 예약 메시지 전송 취소. 예약시간 기준 10분전의 건만 취소 가능.
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param ReceiptNum
     *            전송시 접수번호.
     * @param ReceiveNum
     *            전송시 수신번호
     * @param UserID
     *            연동회원 아이디
     * @return Response 응답
     * @throws PopbillException
     */
    public Response cancelReservebyRCV(String CorpNum, String ReceiptNum, String ReceiveNum, 
            String UserID) throws PopbillException;
    
    /**
     * 예약 메시지 전송 취소. 예약시간 기준 10분전의 건만 취소 가능.
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param RequestNum
     *            전송시 요청번호
     * @param ReceiveNum
     *            전송시 수신번호
     * @return Response 응답
     * @throws PopbillException
     */
    public Response cancelReserveRNbyRCV(String CorpNum, String RequestNum, String ReceiveNum) throws PopbillException; 
    
    /**
     * 예약 메시지 전송 취소. 예약시간 기준 10분전의 건만 취소 가능.
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param RequestNum
     *            전송시 요청번호
     * @param ReceiveNum
     *            전송시 수신번호
     * @param UserID
     *            연동회원 아이디
     * @return Response 응답
     * @throws PopbillException
     */
    public Response cancelReserveRNbyRCV(String CorpNum, String RequestNum, String ReceiveNum,
            String UserID) throws PopbillException; 
    
    /**
     * 문자전송내역 조회 
     * 
     * @param CorpNum
     *          연동회원 사업자번호 
     * @param SDate
     *          시작일자(yyyyMMdd)
     * @param EDate
     *          종료일자(yyyyMMdd)
     * @param State
     *          전송상태 배열, 1-대기, 2-성공, 3-실패, 4-취소 
     * @param Item
     *          검색대상 배열, SMS-단문, LMS-장문 ,MMS-포토  
     * @param ReserveYN
     *          예약여부, true-예약전송조회, false-전체조회 
     * @param SenderYN
     *          개인조회여부, true-개인조회, false-전체조회
     * @param Page
     *          페이지번호 
     * @param PerPage
     *          페이지당 검색갯수, 기본값 500, 최대값 1000 
     * @param Order
     *          정렬방향, D-내림차순, A-오름차순
     * @return 전송내역 조회 (see, com.popbill.api.message.MSGSearchResult)
     * @throws PopbillException
     */
    public MSGSearchResult search(String CorpNum, String SDate, String EDate, 
            String[] State, String[] Item, Boolean ReserveYN, Boolean SenderYN, 
            Integer Page, Integer PerPage, String Order) throws PopbillException;
    
    /**
     * 문자전송내역 조회 
     * 
     * @param CorpNum
     *          연동회원 사업자번호 
     * @param SDate
     *          시작일자(yyyyMMdd)
     * @param EDate
     *          종료일자(yyyyMMdd)
     * @param State
     *          전송상태 배열, 1-대기, 2-성공, 3-실패, 4-취소 
     * @param Item
     *          검색대상 배열, SMS-단문, LMS-장문 ,MMS-포토  
     * @param ReserveYN
     *          예약여부, true-예약전송조회, false-전체조회 
     * @param SenderYN
     *          개인조회여부, true-개인조회, false-전체조회
     * @param Page
     *          페이지번호 
     * @param PerPage
     *          페이지당 검색갯수, 기본값 500, 최대값 1000 
     * @param Order
     *          정렬방향, D-내림차순, A-오름차순
     * @param QString
     *          조회검색어. 발신자명 또는 수신자명 기재
     * @return 전송내역 조회 (see, com.popbill.api.message.MSGSearchResult)
     * @throws PopbillException
     */
    public MSGSearchResult search(String CorpNum, String SDate, String EDate, 
            String[] State, String[] Item, Boolean ReserveYN, Boolean SenderYN, 
            Integer Page, Integer PerPage, String Order, String QString) throws PopbillException;

    /**
     * 문자전송내역 조회
     *
     * @param CorpNum
     *          연동회원 사업자번호
     * @param SDate
     *          시작일자(yyyyMMdd)
     * @param EDate
     *          종료일자(yyyyMMdd)
     * @param State
     *          전송상태 배열, 1-대기, 2-성공, 3-실패, 4-취소
     * @param Item
     *          검색대상 배열, SMS-단문, LMS-장문 ,MMS-포토
     * @param ReserveYN
     *          예약여부, true-예약전송조회, false-전체조회
     * @param SenderYN
     *          개인조회여부, true-개인조회, false-전체조회
     * @param Page
     *          페이지번호
     * @param PerPage
     *          페이지당 검색갯수, 기본값 500, 최대값 1000
     * @param Order
     *          정렬방향, D-내림차순, A-오름차순
     * @param QString
     *          조회검색어. 발신자명 또는 수신자명 기재
     * @param UserID
     *          팝빌회원 아이디
     * @return 전송내역 조회 (see, com.popbill.api.message.MSGSearchResult)
     * @throws PopbillException
     */
    public MSGSearchResult search(String CorpNum, String SDate, String EDate,
            String[] State, String[] Item, Boolean ReserveYN, Boolean SenderYN,
            Integer Page, Integer PerPage, String Order, String QString, String UserID) throws PopbillException;

    /**
     * 080 수신거부목록 확인 
     * 
     * @param CorpNum
     *          연동회원 사업자번호 
     * @return 080수신거부목록 
     * @throws PopbillException
     */
    public AutoDeny[] getAutoDenyList(String CorpNum) throws PopbillException;
    /**
     * 080 수신거부목록 확인
     *
     * @param CorpNum
     *          연동회원 사업자번호
     * @param UserID
     *          팝빌회원 아이디
     * @return 080수신거부목록
     * @throws PopbillException
     */
    public AutoDeny[] getAutoDenyList(String CorpNum, String UserID) throws PopbillException;

    /**
     * 080 번호 확인
     * 
     * @param CorpNum 연동회원 사업자번호 
     * @return 080수신거부 응답
     * @throws PopbillException
     */
    public AutoDenyNumberInfo checkAutoDenyNumber(String CorpNum) throws PopbillException;
    
    /**
     * 080 번호 확인
     * 
     * @param CorpNum 연동회원 사업자번호 
     * @param UserID 연동회원 유저아이디
     * @return 080수신거부 응답
     * @throws PopbillException
     */
    public AutoDenyNumberInfo checkAutoDenyNumber(String CorpNum, String UserID) throws PopbillException;
    
    /**
     * 과금정보 확인 
     * 
     * @param CorpNum
     *          연동회원 사업자번호 
     * @param MsgType
     *          메시지 유형
     * @return 과금정보 (see, com.popbill.api.ChargeInfo)
     * @throws PopbillException
     */
    public ChargeInfo getChargeInfo(String CorpNum, MessageType MsgType) throws PopbillException;
    

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
     *  문자 발신번호 관리 팝업 URL을 반환
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
     *  문자 전송내역 팝업 URL을 반환
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
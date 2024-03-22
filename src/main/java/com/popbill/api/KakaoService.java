package com.popbill.api;

import java.io.File;

import com.popbill.api.kakao.ATSTemplate;
import com.popbill.api.kakao.KakaoButton;
import com.popbill.api.kakao.KakaoReceiver;
import com.popbill.api.kakao.KakaoSearchResult;
import com.popbill.api.kakao.KakaoSentInfo;
import com.popbill.api.kakao.KakaoType;
import com.popbill.api.kakao.PlusFriendID;
import com.popbill.api.kakao.SenderNumber;

/**
 * 
 * @author JeongYooHan (code@linkhub.co.kr)
 * 
 */
public interface KakaoService extends BaseService {
    /**
     * 카카오톡 검색용 계정관리 팝업 URL을 반환
     *
     * @param CorpNum
     *          팝빌회원 사업자번호
     * @param UserID
     *          팝빌회원 아이디
     * @return 팝빌 URL (AccessToken값 포함. Token값은 응답후 30초까지만 유효함)
     * @throws PopbillException
     */
    public String getPlusFriendMgtURL(String CorpNum, String UserID) throws PopbillException;

    /**
     * 팝빌에 등록한 연동회원의 카카오톡 채널 목록을 확인합니다.
     * @param CorpNum 팝빌회원 사업자번호 ('-' 제외)
     * @return
     * @throws PopbillException
     */
    public PlusFriendID[] listPlusFriendID(String CorpNum)
    throws PopbillException;

    /**
     * 팝빌에 등록한 연동회원의 카카오톡 채널 목록을 확인합니다.
     * @param CorpNum 팝빌회원 사업자번호 ('-' 제외)
     * @param UserID 팝빌회원 아이디
     * @return
     * @throws PopbillException
     */
    public PlusFriendID[] listPlusFriendID(String CorpNum, String UserID)
    throws PopbillException;

    /**
     *  발신번호 등록여부 확인
     *
     * @param CorpNum
     *          팝빌회원 사업자번호
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
     *          팝빌회원 사업자번호
     * @param SenderNumber
     *          발신번호
     * @param UserID
     *          팝빌회원 아이디
     * @return Response 응답
     * @throws PopbillException
     */
    public Response checkSenderNumber(String CorpNum, String SenderNumber, String UserID) throws PopbillException;

    /**
     * 발신번호 관리 팝업 URL을 반환
     *
     * @param CorpNum
     *          팝빌회원 사업자번호
     * @param UserID
     *          팝빌회원 아이디
     * @return 팝빌 URL (AccessToken값 포함. Token값은 응답후 30초까지만 유효함)
     * @throws PopbillException
     */
    public String getSenderNumberMgtURL(String CorpNum, String UserID) throws PopbillException;

    /**
     * 팝빌에 등록한 연동회원의 카카오톡 발신번호 목록을 확인합니다.
     * @param CorpNum 팝빌회원 사업자번호 ('-' 제외)
     * @return
     * @throws PopbillException
     */
    public SenderNumber[] getSenderNumberList(String CorpNum) throws PopbillException;

    /**
     * 팝빌에 등록한 연동회원의 카카오톡 발신번호 목록을 확인합니다.
     * @param CorpNum 팝빌회원 사업자번호 ('-' 제외)
     * @param UserID 팝빌회원 아이디
     * @return
     * @throws PopbillException
     */
    public SenderNumber[] getSenderNumberList(String CorpNum, String UserID) throws PopbillException;

    /**
     * 알림톡 템플릿관리 팝업 URL을 반환
     *
     * @param CorpNum
     *          팝빌회원 사업자번호 ('-' 제외)
     * @param UserID
     *          팝빌회원 아이디
     * @return 팝빌 URL (AccessToken값 포함. Token값은 응답후 30초까지만 유효함)
     * @throws PopbillException
     */
    public String getATSTemplateMgtURL(String CorpNum, String UserID) throws PopbillException;

    /**
     *  템플릿 정보 확인
     *
     * @param CorpNum
     *          팝빌회원 사업자번호 ('-' 제외)
     * @param TemplateCode
     *          템플릿 코드
     * @return 팝빌 URL (AccessToken값 포함. Token값은 응답후 30초까지만 유효함)
     * @throws PopbillException
     */
    public ATSTemplate getATSTemplate(String CorpNum, String TemplateCode) throws PopbillException;

    /**
     *  템플릿 정보 확인
     *
     * @param CorpNum
     *          팝빌회원 사업자번호 ('-' 제외)
     * @param TemplateCode
     *          템플릿 코드
     * @param UserID
     *          팝빌회원 아이디
     * @return 팝빌 URL (AccessToken값 포함. Token값은 응답후 30초까지만 유효함)
     * @throws PopbillException
     */
    public ATSTemplate getATSTemplate(String CorpNum, String TemplateCode, String UserID) throws PopbillException;

    /**
     * 승인된 알림톡 템플릿 목록을 확인합니다.
     * 팝빌회원 사업자번호 ('-' 제외)
     * @return
     * @throws PopbillException
     */
    public ATSTemplate[] listATSTemplate(String CorpNum) throws PopbillException;

    /**
     * 승인된 알림톡 템플릿 목록을 확인합니다.
     * 팝빌회원 사업자번호 ('-' 제외)
     * @param UserID 팝빌회원 아이디
     * @return
     * @throws PopbillException
     */
    public ATSTemplate[] listATSTemplate(String CorpNum, String UserID) throws PopbillException;
    
    /**
     * 알림톡 단건전송 
     * 
     * @param CorpNum
     *            팝빌회원 사업자번호 ('-' 제외)
     * @param TemplateCode
     *            알림톡 템플릿코드
     * @param SenderNum
     *            발신번호
     * @param Content
     *            알림톡 내용
     * @param AltContent
     *            대체문자 내용
     * @param AltSendType
     *            대체문자 유형
     * @param ReceiverNum
     *            수신번호
     * @param ReceiverName
     *            수신자명
     * @param SndDT
     *            예약일시
     * @return ReceiptNum 접수번호
     * @throws PopbillException
     */
    public String sendATS(String CorpNum, String TemplateCode, String SenderNum, String Content, String AltContent, String AltSendType, String ReceiverNum, String ReceiverName, String SndDT)
        throws PopbillException;

    /**
     * 알림톡 단건전송 
     *
     * @param CorpNum
     *            팝빌회원 사업자번호 ('-' 제외)
     * @param TemplateCode
     *            알림톡 템플릿코드
     * @param SenderNum
     *            발신번호
     * @param Content
     *            알림톡 내용
     * @param AltContent
     *            대체문자 내용
     * @param AltSendType
     *            대체문자 유형
     * @param ReceiverNum
     *            수신번호
     * @param ReceiverName
     *            수신자명
     * @param SndDT
     *            예약일시
     * @param UserID
     *            팝빌회원 아이디
     * @return ReceiptNum 접수번호
     * @throws PopbillException
     */
    public String sendATS(String CorpNum, String TemplateCode, String SenderNum, String Content, String AltContent, String AltSendType, String ReceiverNum, String ReceiverName, String SndDT, String UserID)
            throws PopbillException;

    /**
     * 알림톡 단건전송
     *
     * @param CorpNum
     *            팝빌회원 사업자번호 ('-' 제외)
     * @param TemplateCode
     *            알림톡 템플릿코드
     * @param SenderNum
     *            발신번호
     * @param Content
     *            알림톡 내용
     * @param AltContent
     *            대체문자 내용
     * @param AltSendType
     *            대체문자 유형
     * @param ReceiverNum
     *            수신번호
     * @param ReceiverName
     *            수신자명
     * @param SndDT
     *            예약일시
     * @param RequestNum
     *            전송요청번호
     * @return ReceiptNum 접수번호
     * @throws PopbillException
     */
    public String sendATS(String CorpNum, String TemplateCode, String SenderNum, String Content, String AltContent, String AltSendType, String ReceiverNum, String ReceiverName, String SndDT, String UserID, String RequestNum)
            throws PopbillException;

    /**
     * 알림톡 단건전송
     *
     * @param CorpNum
     *            팝빌회원 사업자번호 ('-' 제외)
     * @param TemplateCode
     *            알림톡 템플릿코드
     * @param SenderNum
     *            발신번호
     * @param Content
     *            알림톡 내용
     * @param AltContent
     *            대체문자 내용
     * @param AltSendType
     *            대체문자 유형
     * @param ReceiverNum
     *            수신번호
     * @param ReceiverName
     *            수신자명
     * @param SndDT
     *            예약일시
     * @param UserID
     *            팝빌회원 아이디
     * @param RequestNum
     *            전송요청번호
     * @return ReceiptNum 접수번호
     * @throws PopbillException
     */
    public String sendATS(String CorpNum, String TemplateCode, String SenderNum, String Content, String AltSubject, String AltContent, String AltSendType, String ReceiverNum, String ReceiverName, String SndDT, String UserID, String RequestNum)
            throws PopbillException;
    
    /**
     * 알림톡 단건전송 버튼추가.
     * 
     * @param CorpNum
     *            팝빌회원 사업자번호 ('-' 제외)
     * @param TemplateCode
     *            알림톡 템플릿코드
     * @param SenderNum
     *            발신번호
     * @param Content
     *            알림톡 내용
     * @param AltContent
     *            대체문자 내용
     * @param AltSendType
     *            대체문자 유형
     * @param ReceiverNum
     *            수신번호
     * @param ReceiverName
     *            수신자명
     * @param SndDT
     *            예약일시
     * @return ReceiptNum 접수번호
     * @throws PopbillException
     */
    public String sendATS(String CorpNum, String TemplateCode, String SenderNum, String Content, String AltContent, String AltSendType, String ReceiverNum, String ReceiverName, String SndDT, KakaoButton[] Buttons)
            throws PopbillException;

    /**
     * 알림톡 단건전송 버튼추가.
     *
     * @param CorpNum
     *            팝빌회원 사업자번호 ('-' 제외)
     * @param TemplateCode
     *            알림톡 템플릿코드
     * @param SenderNum
     *            발신번호
     * @param Content
     *            알림톡 내용
     * @param AltContent
     *            대체문자 내용
     * @param AltSendType
     *            대체문자 유형
     * @param ReceiverNum
     *            수신번호
     * @param ReceiverName
     *            수신자명
     * @param SndDT
     *            예약일시
     * @param UserID
     *            팝빌회원 아이디
     * @return ReceiptNum 접수번호
     * @throws PopbillException
     */
    public String sendATS(String CorpNum, String TemplateCode, String SenderNum, String Content, String AltContent, String AltSendType, String ReceiverNum, String ReceiverName, String SndDT, String UserID, KakaoButton[] Buttons)
            throws PopbillException;

    /**
     * 알림톡 단건전송 버튼추가.
     *
     * @param CorpNum
     *            팝빌회원 사업자번호 ('-' 제외)
     * @param TemplateCode
     *            알림톡 템플릿코드
     * @param SenderNum
     *            발신번호
     * @param Content
     *            알림톡 내용
     * @param AltContent
     *            대체문자 내용
     * @param AltSendType
     *            대체문자 유형
     * @param ReceiverNum
     *            수신번호
     * @param ReceiverName
     *            수신자명
     * @param SndDT
     *            예약일시
     * @param UserID
     *            팝빌회원 아이디
     * @param RequestNum
     *            전송요청번호
     * @param Buttons
     *            버튼 목록
     * @return ReceiptNum 접수번호
     * @throws PopbillException
     */
    public String sendATS(String CorpNum, String TemplateCode, String SenderNum, String Content, String AltContent, String AltSendType, String ReceiverNum, String ReceiverName, String SndDT, String UserID, String RequestNum, KakaoButton[] Buttons)
            throws PopbillException;

    /**
     * 알림톡 단건전송 버튼추가.
     *
     * @param CorpNum
     *            팝빌회원 사업자번호 ('-' 제외)
     * @param TemplateCode
     *            알림톡 템플릿코드
     * @param SenderNum
     *            발신번호
     * @param Content
     *            알림톡 내용
     * @param AltContent
     *            대체문자 내용
     * @param AltSendType
     *            대체문자 유형
     * @param ReceiverNum
     *            수신번호
     * @param ReceiverName
     *            수신자명
     * @param SndDT
     *            예약일시
     * @param UserID
     *            팝빌회원 아이디
     * @param RequestNum
     *            팝빌회원 아이디
     * @param Buttons
     *            버튼 목록
     * @return ReceiptNum 접수번호
     * @throws PopbillException
     */
    public String  sendATS(String CorpNum, String TemplateCode, String SenderNum, String Content, String AltSubject, String AltContent, String AltSendType, String ReceiverNum, String ReceiverName, String SndDT, String UserID, String RequestNum, KakaoButton[] Buttons)
            throws PopbillException;
    
    /**
     * 알림톡 개별내용 대량전송
     * 
     * @param CorpNum
     *            팝빌회원 사업자번호
     * @param TemplateCode
     *            알림톡 템플릿코드
     * @param SenderNum
     *            발신번호
     * @param AltSendType
     *            대체문자 유형
     * @param Receivers
     *            개별수신자 배열
     * @param SndDT
     *            예약일시
     * @return ReceiptNum 접수번호
     * @throws PopbillException
     */
    public String sendATS(String CorpNum, String TemplateCode, String SenderNum, String AltSendType, KakaoReceiver[] Receivers, String SndDT)
            throws PopbillException;
    

    /**
     * 알림톡 개별내용 대량전송
     *
     * @param CorpNum
     *            팝빌회원 사업자번호
     * @param TemplateCode
     *            알림톡 템플릿코드
     * @param SenderNum
     *            발신번호
     * @param AltSendType
     *            대체문자 유형
     * @param Receivers
     *            개별수신자 배열
     * @param SndDT
     *            예약일시
     * @param UserID
     *            팝빌회원 아이디
     * @return ReceiptNum 접수번호
     * @throws PopbillException
     */
    public String sendATS(String CorpNum, String TemplateCode, String SenderNum, String AltSendType, KakaoReceiver[] Receivers, String SndDT, String UserID)
            throws PopbillException;


    /**
     * 알림톡 개별내용 대량전송
     *
     * @param CorpNum
     *            팝빌회원 사업자번호
     * @param TemplateCode
     *            알림톡 템플릿코드
     * @param SenderNum
     *            발신번호
     * @param AltSendType
     *            대체문자 유형
     * @param Receivers
     *            개별수신자 배열
     * @param SndDT
     *            예약일시
     * @param UserID
     *            팝빌회원 아이디
     * @param RequestNum
     *            전송요청번호
     * @return ReceiptNum 접수번호
     * @throws PopbillException
     */
    public String sendATS(String CorpNum, String TemplateCode, String SenderNum, String AltSendType, KakaoReceiver[] Receivers, String SndDT, String UserID, String RequestNum)
            throws PopbillException;
    
    /**
     * 알림톡 개별내용 대량전송 - 버튼추가
     * @param CorpNum 팝빌회원 사업자번호 ('-' 제외)
     * @param TemplateCode 승인된 알림톡 템플릿 코드
     * @param SenderNum 발신번호
     * @param AltSendType 대체문자 유형
     * @param Receivers 전송정보
     * @param SndDT 전송 예약일시
     * @param Buttons 버튼 목록
     * @return
     * @throws PopbillException
     */
    public String sendATS(String CorpNum, String TemplateCode, String SenderNum, String AltSendType, KakaoReceiver[] Receivers, String SndDT, KakaoButton[] Buttons)
            throws PopbillException;
    

    /**
     * 알림톡 개별내용 대량전송 - 버튼추가
     * @param CorpNum 팝빌회원 사업자번호 ('-' 제외)
     * @param TemplateCode 승인된 알림톡 템플릿 코드
     * @param SenderNum 발신번호
     * @param AltSendType 대체문자 유형
     * @param Receivers 전송정보
     * @param SndDT 전송 예약일시
     * @param Buttons 버튼 목록
     * @return
     * @throws PopbillException
     */
    public String sendATS(String CorpNum, String TemplateCode, String SenderNum, String AltSendType, KakaoReceiver[] Receivers, String SndDT, String UserID, KakaoButton[] Buttons)
            throws PopbillException;


    /**
     * 알림톡 개별내용 대량전송 - 버튼추가
     * @param CorpNum 팝빌회원 사업자번호 ('-' 제외)
     * @param TemplateCode 승인된 알림톡 템플릿 코드
     * @param SenderNum 발신번호
     * @param AltSendType 대체문자 유형
     * @param Receivers 전송정보
     * @param SndDT 전송 예약일시
     * @param UserID 팝빌회원 아이디
     * @param RequestNum 요청번호
     * @param Buttons 버튼목록
     * @return
     * @throws PopbillException
     */
    public String sendATS(String CorpNum, String TemplateCode, String SenderNum, String AltSendType, KakaoReceiver[] Receivers, String SndDT, String UserID, String RequestNum, KakaoButton[] Buttons)
            throws PopbillException;    
    
    /**
     * 알림톡 동일내용 대량전송
     * 
     * @param CorpNum
     *            팝빌회원 사업자번호
     * @param TemplateCode
     *            알림톡 템플릿코드
     * @param SenderNum
     *            발신번호
     * @param Content
     *            알림톡 내용
     * @param AltContent
     *            대체문자 내용
     * @param AltSendType
     *            대체문자 유형
     * @param Receivers
     *            동보수신자 배열
     * @param SndDT
     *            예약일시
     * @return ReceiptNum 접수번호
     * @throws PopbillException
     */
    public String sendATS(String CorpNum, String TemplateCode, String SenderNum, String Content, String AltContent, String AltSendType, KakaoReceiver[] Receivers, String SndDT)
        throws PopbillException;
    

    /**
     * 알림톡 동일내용 대량전송
     *
     * @param CorpNum
     *            팝빌회원 사업자번호
     * @param TemplateCode
     *            알림톡 템플릿코드
     * @param SenderNum
     *            발신번호
     * @param Content
     *            알림톡 내용
     * @param AltContent
     *            대체문자 내용
     * @param AltSendType
     *            대체문자 유형
     * @param Receivers
     *            동보수신자 배열
     * @param SndDT
     *            예약일시
     * @param UserID
     *            팝빌회원 아이디
     * @return ReceiptNum 접수번호
     * @throws PopbillException
     */
    public String sendATS(String CorpNum, String TemplateCode, String SenderNum, String Content, String AltContent, String AltSendType, KakaoReceiver[] Receivers, String SndDT, String UserID)
        throws PopbillException;
    

    /**
     * 알림톡 동일내용 대량전송
     *
     * @param CorpNum
     *            팝빌회원 사업자번호
     * @param TemplateCode
     *            알림톡 템플릿코드
     * @param SenderNum
     *            발신번호
     * @param Content
     *            알림톡 내용
     * @param AltContent
     *            대체문자 내용
     * @param AltSendType
     *            대체문자 유형
     * @param Receivers
     *            동보수신자 배열
     * @param SndDT
     *            예약일시
     * @param UserID
     *            팝빌회원 아이디
     * @param RequestNum
     *            전송요청번호
     * @return ReceiptNum 접수번호
     * @throws PopbillException
     */
    public String sendATS(String CorpNum, String TemplateCode, String SenderNum, String Content, String AltContent, String AltSendType, KakaoReceiver[] Receivers, String SndDT, String UserID, String RequestNum)
            throws PopbillException;
    

    /**
     * 알림톡 동일내용 대량전송
     *
     * @param CorpNum
     *            팝빌회원 사업자번호
     * @param TemplateCode
     *            알림톡 템플릿코드
     * @param SenderNum
     *            발신번호
     * @param Content
     *            알림톡 내용
     * @param AltSubject
     *            대체문자 제목
     * @param AltContent
     *            대체문자 내용
     * @param AltSendType
     *            대체문자 유형
     * @param Receivers
     *            동보수신자 배열
     * @param SndDT
     *            예약일시
     * @param UserID
     *            팝빌회원 아이디
     * @param RequestNum
     *            전송요청번호
     * @return ReceiptNum 접수번호
     * @throws PopbillException
     */
    public String sendATS(String CorpNum, String TemplateCode, String SenderNum, String Content, String AltSubject, String AltContent, String AltSendType, KakaoReceiver[] Receivers, String SndDT, String UserID, String RequestNum)
            throws PopbillException;

    /**
     * 알림톡 동일내용 대량전송 - 버튼추가.
     *
     * @param CorpNum 팝빌회원 사업자번호 ('-' 제외)
     * @param TemplateCode 승인된 알림톡 템플릿 코드
     * @param SenderNum 발신번호
     * @param Content 알림톡 내용
     * @param AltContent 대체문자 내용
     * @param AltSendType 대체문자 유형
     * @param Receivers 전송정보
     * @param SndDT 전송 예약일시
     * @param Buttons 버튼 목록
     * @return
     * @throws PopbillException
     */
    public String sendATS(String CorpNum, String TemplateCode, String SenderNum, String Content, String AltContent, String AltSendType, KakaoReceiver[] Receivers, String SndDT, KakaoButton[] Buttons)
            throws PopbillException;
        

    /**
     * 알림톡 동일내용 대량전송 - 버튼추가.
     *
     * @param CorpNum 팝빌회원 사업자번호 ('-' 제외)
     * @param TemplateCode 승인된 알림톡 템플릿 코드
     * @param SenderNum 발신번호
     * @param Content 알림톡 내용
     * @param AltContent 대체문자 내용
     * @param AltSendType 대체문자 유형
     * @param Receivers 전송정보
     * @param SndDT 전송 예약일시
     * @param UserID 팝빌회원 아이디
     * @param Buttons 버튼 목록
     * @return
     * @throws PopbillException
     */
    public String sendATS(String CorpNum, String TemplateCode, String SenderNum, String Content, String AltContent, String AltSendType, KakaoReceiver[] Receivers, String SndDT, String UserID, KakaoButton[] Buttons)
        throws PopbillException;


    /**
     * 알림톡 동일내용 대량전송 - 버튼추가.
     *
     * @param CorpNum 팝빌회원 사업자번호 ('-' 제외)
     * @param TemplateCode 승인된 알림톡 템플릿 코드
     * @param SenderNum 발신번호
     * @param Content 알림톡 내용
     * @param AltContent 대체문자 내용
     * @param AltSendType 대체문자 유형
     * @param Receivers 전송정보
     * @param SndDT 전송 예약일시
     * @param UserID 전송 예약일시
     * @param RequestNum 요청번호
     * @param Buttons 버튼 목록
     * @return
     * @throws PopbillException
     */
    public String sendATS(String CorpNum, String TemplateCode, String SenderNum, String Content, String AltContent, String AltSendType, KakaoReceiver[] Receivers, String SndDT, String UserID, String RequestNum , KakaoButton[] Buttons)
            throws PopbillException;
    

    /**
     * 알림톡 동일내용 대량전송 - 버튼추가.
     *
     * @param CorpNum 팝빌회원 사업자번호 ('-' 제외)
     * @param TemplateCode 승인된 알림톡 템플릿 코드
     * @param SenderNum 발신번호
     * @param Content 알림톡 내용
     * @param AltSubject 대체문자 제목
     * @param AltContent 대체문자 내용
     * @param AltSendType 대체문자 유형
     * @param Receivers 전송정보
     * @param SndDT 전송 예약일시
     * @param UserID 전송 예약일시
     * @param RequestNum 요청번호
     * @param Buttons 버튼 목록
     * @return
     * @throws PopbillException
     */
    public String sendATS(String CorpNum, String TemplateCode, String SenderNum, String Content, String AltSubject, String AltContent, String AltSendType, KakaoReceiver[] Receivers, String SndDT, String UserID, String RequestNum , KakaoButton[] Buttons)
            throws PopbillException;
    
    /**
     * 친구톡 단건전송
     * 
     * @param CorpNum
     *            팝빌회원 사업자번호
     * @param PlusFriendID
     *            카카오톡 검색용 아이디
     * @param SenderNum
     *            발신번호
     * @param Content
     *            친구톡 내용
     * @param AltContent
     *            대체문자 내용
     * @param AltSendType
     *            대체문자 유형
     * @param Buttons
     *            버튼 목록
     * @param ReceiverNum
     *            수신번호
     * @param ReceiverName
     *            수신자명
     * @param SndDT
     *            예약일시
     * @param AdsYN
     *            광고 전송여부
     * @return ReceiptNum 접수번호
     * @throws PopbillException
     */
    public String sendFTS(String CorpNum, String PlusFriendID, String SenderNum, String Content, String AltContent, String AltSendType, KakaoButton[] Buttons, String ReceiverNum, String ReceiverName, String SndDT, Boolean AdsYN)
        throws PopbillException;

    /**
     * 친구톡 단건전송
     *
     * @param CorpNum
     *            팝빌회원 사업자번호
     * @param PlusFriendID
     *            카카오톡 검색용 아이디
     * @param SenderNum
     *            발신번호
     * @param Content
     *            친구톡 내용
     * @param AltContent
     *            대체문자 내용
     * @param AltSendType
     *            대체문자 유형
     * @param Buttons
     *            버튼 목록
     * @param ReceiverNum
     *            수신번호
     * @param ReceiverName
     *            수신자명
     * @param SndDT
     *            예약일시
     * @param AdsYN
     *            광고 전송여부
     * @param UserID
     *            팝빌회원 아이디
     * @return ReceiptNum 접수번호
     * @throws PopbillException
     */
    public String sendFTS(String CorpNum, String PlusFriendID, String SenderNum, String Content, String AltContent, String AltSendType, KakaoButton[] Buttons, String ReceiverNum, String ReceiverName, String SndDT, Boolean AdsYN, String UserID)
            throws PopbillException;

    /**
     * 친구톡 단건전송
     *
     * @param CorpNum
     *            팝빌회원 사업자번호
     * @param PlusFriendID
     *            카카오톡 검색용 아이디
     * @param SenderNum
     *            발신번호
     * @param Content
     *            친구톡 내용
     * @param AltContent
     *            대체문자 내용
     * @param AltSendType
     *            대체문자 유형
     * @param Buttons
     *            버튼 목록
     * @param ReceiverNum
     *            수신번호
     * @param ReceiverName
     *            수신자명
     * @param SndDT
     *            예약일시
     * @param AdsYN
     *            광고 전송여부
     * @param UserID
     *            팝빌회원 아이디
     * @param RequestNum
     *            전송요청번호
     * @return ReceiptNum 접수번호
     * @throws PopbillException
     */
    public String sendFTS(String CorpNum, String PlusFriendID, String SenderNum, String Content, String AltContent, String AltSendType, KakaoButton[] Buttons, String ReceiverNum, String ReceiverName, String SndDT, Boolean AdsYN, String UserID, String RequestNum)
            throws PopbillException;

    /**
     * 친구톡 단건전송
     *
     * @param CorpNum
     *            팝빌회원 사업자번호
     * @param PlusFriendID
     *            카카오톡 검색용 아이디
     * @param SenderNum
     *            발신번호
     * @param Content
     *            친구톡 내용
     * @param AltContent
     *            대체문자 내용
     * @param AltSendType
     *            대체문자 유형
     * @param Buttons
     *            버튼 목록
     * @param ReceiverNum
     *            수신번호
     * @param ReceiverName
     *            수신자명
     * @param SndDT
     *            예약일시
     * @param AdsYN
     *            광고 전송여부
     * @param UserID
     *            팝빌회원 아이디
     * @param RequestNum
     *            전송요청번호
     * @return ReceiptNum 접수번호
     * @throws PopbillException
     */
    public String sendFTS(String CorpNum, String PlusFriendID, String SenderNum, String Content, String AltSubject, String AltContent, String AltSendType, KakaoButton[] Buttons, String ReceiverNum, String ReceiverName, String SndDT, Boolean AdsYN, String UserID, String RequestNum)
            throws PopbillException;
    
    /**
     * 친구톡 개별내용 대량전송
     * 
     * @param CorpNum
     *            팝빌회원 사업자번호
     * @param PlusFriendID
     *            카카오톡 검색용 아이디
     * @param SenderNum
     *            발신번호
     * @param AltSendType
     *            대체문자 유형
     * @param Receivers
     *            동보수신자 배열
     * @param Buttons
     *            버튼 목록
     * @param SndDT
     *            예약일시
     * @param AdsYN
     *            광고 전송여부
     * @return ReceiptNum 접수번호
     * @throws PopbillException
     */
    public String sendFTS(String CorpNum, String PlusFriendID, String SenderNum, String AltSendType, KakaoReceiver[] Receivers, KakaoButton[] Buttons, String SndDT, Boolean AdsYN)
            throws PopbillException;

        /**
     * 친구톡 개별내용 대량전송
     *
     * @param CorpNum
     *            팝빌회원 사업자번호
     * @param PlusFriendID
     *            카카오톡 검색용 아이디
     * @param SenderNum
     *            발신번호
     * @param AltSendType
     *            대체문자 유형
     * @param Receivers
     *            동보수신자 배열
     * @param Buttons
     *            버튼 목록
     * @param SndDT
     *            예약일시
     * @param AdsYN
     *            광고 전송여부
     * @param UserID
     *            팝빌회원 아이디
     * @return ReceiptNum 접수번호
     * @throws PopbillException
     */
    public String sendFTS(String CorpNum, String PlusFriendID, String SenderNum, String AltSendType, KakaoReceiver[] Receivers, KakaoButton[] Buttons, String SndDT, Boolean AdsYN, String UserID)
            throws PopbillException;

        /**
     * 친구톡 개별내용 대량전송
     *
     * @param CorpNum
     *            팝빌회원 사업자번호
     * @param PlusFriendID
     *            카카오톡 검색용 아이디
     * @param SenderNum
     *            발신번호
     * @param AltSendType
     *            대체문자 유형
     * @param Receivers
     *            동보수신자 배열
     * @param Buttons
     *            버튼 목록
     * @param SndDT
     *            예약일시
     * @param AdsYN
     *            광고 전송여부
     * @param UserID
     *            팝빌회원 아이디
     * @param RequestNum
     *            전송요청번호
     * @return ReceiptNum 접수번호
     * @throws PopbillException
     */
    public String sendFTS(String CorpNum, String PlusFriendID, String SenderNum, String AltSendType, KakaoReceiver[] Receivers, KakaoButton[] Buttons, String SndDT, Boolean AdsYN, String UserID, String RequestNum)
            throws PopbillException;
    
    /**
     * 친구톡 동일내용 대량전송
     * 
     * @param CorpNum
     *            팝빌회원 사업자번호
     * @param PlusFriendID
     *            카카오톡 검색용 아이디
     * @param SenderNum
     *            발신번호
     * @param Content
     *            친구톡 내용
     * @param AltContent
     *            대체문자 내용
     * @param AltSendType
     *            대체문자 유형
     * @param Receivers
     *            동보수신자 배열
     * @param Buttons
     *            버튼 목록
     * @param SndDT
     *            예약일시
     * @param AdsYN
     *            광고 전송여부
     * @return ReceiptNum 접수번호
     * @throws PopbillException
     */
    public String sendFTS(String CorpNum, String PlusFriendID, String SenderNum, String Content, String AltContent, String AltSendType, KakaoReceiver[] Receivers, KakaoButton[] Buttons, String SndDT, Boolean AdsYN)
        throws PopbillException;


    /**
     * 친구톡 동일내용 대량전송
     *
     * @param CorpNum
     *            팝빌회원 사업자번호
     * @param PlusFriendID
     *            카카오톡 검색용 아이디
     * @param SenderNum
     *            발신번호
     * @param Content
     *            친구톡 내용
     * @param AltContent
     *            대체문자 내용
     * @param AltSendType
     *            대체문자 유형
     * @param Receivers
     *            동보수신자 배열
     * @param Buttons
     *            버튼 목록
     * @param SndDT
     *            예약일시
     * @param AdsYN
     *            광고 전송여부
     * @param UserID
     *            팝빌회원 아이디
     * @return ReceiptNum 접수번호
     * @throws PopbillException
     */
    public String sendFTS(String CorpNum, String PlusFriendID, String SenderNum, String Content, String AltContent, String AltSendType, KakaoReceiver[] Receivers, KakaoButton[] Buttons, String SndDT, Boolean AdsYN, String UserID)
        throws PopbillException;


    /**
     * 친구톡 동일내용 대량전송
     *
     * @param CorpNum
     *            팝빌회원 사업자번호
     * @param PlusFriendID
     *            카카오톡 검색용 아이디
     * @param SenderNum
     *            발신번호
     * @param Content
     *            친구톡 내용
     * @param AltContent
     *            대체문자 내용
     * @param AltSendType
     *            대체문자 유형
     * @param Receivers
     *            동보수신자 배열
     * @param Buttons
     *            버튼 목록
     * @param SndDT
     *            예약일시
     * @param AdsYN
     *            광고 전송여부
     * @param UserID
     *            팝빌회원 아이디
     * @param RequestNum
     *            전송요청번호
     * @return ReceiptNum 접수번호
     * @throws PopbillException
     */
    public String sendFTS(String CorpNum, String PlusFriendID, String SenderNum, String Content, String AltContent, String AltSendType, KakaoReceiver[] Receivers, KakaoButton[] Buttons, String SndDT, Boolean AdsYN, String UserID, String RequestNum)
            throws PopbillException;


    /**
     * 친구톡 동일내용 대량전송
     *
     * @param CorpNum
     *            팝빌회원 사업자번호
     * @param PlusFriendID
     *            카카오톡 검색용 아이디
     * @param SenderNum
     *            발신번호
     * @param Content
     *            친구톡 내용
     * @param AltContent
     *            대체문자 내용
     * @param AltSendType
     *            대체문자 유형
     * @param Receivers
     *            동보수신자 배열
     * @param Buttons
     *            버튼 목록
     * @param SndDT
     *            예약일시
     * @param AdsYN
     *            광고 전송여부
     * @param UserID
     *            팝빌회원 아이디
     * @param RequestNum
     *            전송요청번호
     * @return ReceiptNum 접수번호
     * @throws PopbillException
     */
    public String sendFTS(String CorpNum, String PlusFriendID, String SenderNum, String Content, String AltSubject, String AltContent, String AltSendType, KakaoReceiver[] Receivers, KakaoButton[] Buttons, String SndDT, Boolean AdsYN, String UserID, String RequestNum)
            throws PopbillException;

    /**
     * 친구톡 이미지 단건전송
     * 
     * @param CorpNum
     *            팝빌회원 사업자번호
     * @param PlusFriendID
     *            카카오톡 검색용 아이디
     * @param SenderNum
     *            발신번호
     * @param Content
     *            친구톡 내용
     * @param AltContent
     *            대체문자 내용
     * @param AltSendType
     *            대체문자 유형
     * @param Buttons
     *            버튼 목록
     * @param ReceiverNum
     *            수신번호
     * @param ReceiverName
     *            수신자명
     * @param SndDT
     *            예약일시
     * @param AdsYN
     *            광고 전송여부
     * @param File
     *            이미지 파일
     * @param ImageURL
     *            친구톡 이미지 링크 URL
     * @return ReceiptNum 접수번호
     * @throws PopbillException
     */
    public String sendFMS(String CorpNum, String PlusFriendID, String SenderNum, String Content, String AltContent, String AltSendType, KakaoButton[] Buttons, String ReceiverNum, String ReceiverName, String SndDT, Boolean AdsYN, File File, String ImageURL)
            throws PopbillException;

    /**
     * 친구톡 이미지 단건전송
     *
     * @param CorpNum
     *            팝빌회원 사업자번호
     * @param PlusFriendID
     *            카카오톡 검색용 아이디
     * @param SenderNum
     *            발신번호
     * @param Content
     *            친구톡 내용
     * @param AltContent
     *            대체문자 내용
     * @param AltSendType
     *            대체문자 유형
     * @param Buttons
     *            버튼 목록
     * @param ReceiverNum
     *            수신번호
     * @param ReceiverName
     *            수신자명
     * @param SndDT
     *            예약일시
     * @param AdsYN
     *            광고 전송여부
     * @param File
     *            이미지 파일
     * @param ImageURL
     *            친구톡 이미지 링크 URL
     * @param UserID
     *            팝빌회원 아이디
     * @return ReceiptNum 접수번호
     * @throws PopbillException
     */
    public String sendFMS(String CorpNum, String PlusFriendID, String SenderNum, String Content, String AltContent, String AltSendType, KakaoButton[] Buttons, String ReceiverNum, String ReceiverName, String SndDT, Boolean AdsYN, File File, String ImageURL, String UserID)
            throws PopbillException;


    /**
     * 친구톡 이미지 단건전송
     *
     * @param CorpNum
     *            팝빌회원 사업자번호
     * @param PlusFriendID
     *            카카오톡 검색용 아이디
     * @param SenderNum
     *            발신번호
     * @param Content
     *            친구톡 내용
     * @param AltContent
     *            대체문자 내용
     * @param AltSendType
     *            대체문자 유형
     * @param Buttons
     *            버튼 목록
     * @param ReceiverNum
     *            수신번호
     * @param ReceiverName
     *            수신자명
     * @param SndDT
     *            예약일시
     * @param AdsYN
     *            광고 전송여부
     * @param File
     *            이미지 파일
     * @param ImageURL
     *            친구톡 이미지 링크 URL
     * @param UserID
     *            팝빌회원 아이디
     * @param RequestNum
     *            전송요청번호
     * @return ReceiptNum 접수번호
     * @throws PopbillException
     */
    public String sendFMS(String CorpNum, String PlusFriendID, String SenderNum, String Content, String AltContent, String AltSendType, KakaoButton[] Buttons, String ReceiverNum, String ReceiverName, String SndDT, Boolean AdsYN, File File, String ImageURL, String UserID, String RequestNum)
            throws PopbillException;

    /**
     * 친구톡 이미지 단건전송
     *
     * @param CorpNum
     *            팝빌회원 사업자번호
     * @param PlusFriendID
     *            카카오톡 검색용 아이디
     * @param SenderNum
     *            발신번호
     * @param Content
     *            친구톡 내용
     * @param AltContent
     *            대체문자 내용
     * @param AltSendType
     *            대체문자 유형
     * @param Buttons
     *            버튼 목록
     * @param ReceiverNum
     *            수신번호
     * @param ReceiverName
     *            수신자명
     * @param SndDT
     *            예약일시
     * @param AdsYN
     *            광고 전송여부
     * @param File
     *            이미지 파일
     * @param ImageURL
     *            친구톡 이미지 링크 URL
     * @param UserID
     *            팝빌회원 아이디
     * @param RequestNum
     *            전송요청번호
     * @return ReceiptNum 접수번호
     * @throws PopbillException
     */
    public String sendFMS(String CorpNum, String PlusFriendID, String SenderNum, String Content, String AltSubject, String AltContent, String AltSendType, KakaoButton[] Buttons, String ReceiverNum, String ReceiverName, String SndDT, Boolean AdsYN, File File, String ImageURL, String UserID, String RequestNum)
            throws PopbillException;
    
    /**
     * 친구톡 이미지 개별내용 대량전송
     * 
     * @param CorpNum
     *            팝빌회원 사업자번호
     * @param PlusFriendID
     *            카카오톡 검색용 아이디
     * @param SenderNum
     *            발신번호
     * @param AltSendType
     *            대체문자 유형
     * @param Receivers
     *            동보수신자 배열
     * @param Buttons
     *            버튼 목록
     * @param SndDT
     *            예약일시
     * @param AdsYN
     *            광고 전송여부
     * @param File
     *            이미지 파일
     * @param ImageURL
     *            친구톡 이미지 링크 URL
     * @return ReceiptNum 접수번호
     * @throws PopbillException
     */
    public String sendFMS(String CorpNum, String PlusFriendID, String SenderNum, String AltSendType, KakaoReceiver[] Receivers, KakaoButton[] Buttons, String SndDT, Boolean AdsYN, File File, String ImageURL)
            throws PopbillException;

    /**
     * 친구톡 이미지 개별내용 대량전송
     *
     * @param CorpNum
     *            팝빌회원 사업자번호
     * @param PlusFriendID
     *            카카오톡 검색용 아이디
     * @param SenderNum
     *            발신번호
     * @param AltSendType
     *            대체문자 유형
     * @param Receivers
     *            동보수신자 배열
     * @param Buttons
     *            버튼 목록
     * @param SndDT
     *            예약일시
     * @param AdsYN
     *            광고 전송여부
     * @param File
     *            이미지 파일
     * @param ImageURL
     *            친구톡 이미지 링크 URL
     * @param UserID
     *            팝빌회원 아이디
     * @return ReceiptNum 접수번호
     * @throws PopbillException
     */
    public String sendFMS(String CorpNum, String PlusFriendID, String SenderNum, String AltSendType, KakaoReceiver[] Receivers, KakaoButton[] Buttons, String SndDT, Boolean AdsYN, File File, String ImageURL, String UserID)
            throws PopbillException;

    /**
     * 친구톡 이미지 개별내용 대량전송
     *
     * @param CorpNum
     *            팝빌회원 사업자번호
     * @param PlusFriendID
     *            카카오톡 검색용 아이디
     * @param SenderNum
     *            발신번호
     * @param AltSendType
     *            대체문자 유형
     * @param Receivers
     *            동보수신자 배열
     * @param Buttons
     *            버튼 목록
     * @param SndDT
     *            예약일시
     * @param AdsYN
     *            광고 전송여부
     * @param File
     *            이미지 파일
     * @param ImageURL
     *            친구톡 이미지 링크 URL
     * @param UserID
     *            팝빌회원 아이디
     * @param RequestNum
     *            전송요청번호
     * @return ReceiptNum 접수번호
     * @throws PopbillException
     */
    public String sendFMS(String CorpNum, String PlusFriendID, String SenderNum, String AltSendType, KakaoReceiver[] Receivers, KakaoButton[] Buttons, String SndDT, Boolean AdsYN, File File, String ImageURL, String UserID, String RequestNum)
            throws PopbillException;
    
    /**
     * 친구톡 이미지 동일내용 대량전송
     * 
     * @param CorpNum
     *            팝빌회원 사업자번호
     * @param PlusFriendID
     *            카카오톡 검색용 아이디
     * @param SenderNum
     *            발신번호
     * @param Content
     *            친구톡 내용
     * @param AltContent
     *            대체문자 내용
     * @param AltSendType
     *            대체문자 유형
     * @param Receivers
     *            동보수신자 배열
     * @param Buttons
     *            버튼 목록
     * @param SndDT
     *            예약일시
     * @param AdsYN
     *            광고 전송여부
     * @param File
     *            이미지 파일
     * @param ImageURL
     *            친구톡 이미지 링크 URL
     * @return ReceiptNum 접수번호
     * @throws PopbillException
     */
    public String sendFMS(String CorpNum, String PlusFriendID, String SenderNum, String Content, String AltContent, String AltSendType, KakaoReceiver[] Receivers, KakaoButton[] Buttons, String SndDT, Boolean AdsYN, File File, String ImageURL)
            throws PopbillException;

    /**
     * 친구톡 이미지 동일내용 대량전송
     *
     * @param CorpNum
     *            팝빌회원 사업자번호
     * @param PlusFriendID
     *            카카오톡 검색용 아이디
     * @param SenderNum
     *            발신번호
     * @param Content
     *            친구톡 내용
     * @param AltContent
     *            대체문자 내용
     * @param AltSendType
     *            대체문자 유형
     * @param Receivers
     *            동보수신자 배열
     * @param Buttons
     *            버튼 목록
     * @param SndDT
     *            예약일시
     * @param AdsYN
     *            광고 전송여부
     * @param File
     *            이미지 파일
     * @param ImageURL
     *            친구톡 이미지 링크 URL
     * @param UserID
     *            팝빌회원 아이디
     * @return ReceiptNum 접수번호
     * @throws PopbillException
     */
    public String sendFMS(String CorpNum, String PlusFriendID, String SenderNum, String Content, String AltContent, String AltSendType, KakaoReceiver[] Receivers, KakaoButton[] Buttons, String SndDT, Boolean AdsYN, File File, String ImageURL, String UserID)
            throws PopbillException;

    /**
     * 친구톡 이미지 동일내용 대량전송
     *
     * @param CorpNum
     *            팝빌회원 사업자번호
     * @param PlusFriendID
     *            카카오톡 검색용 아이디
     * @param SenderNum
     *            발신번호
     * @param Content
     *            친구톡 내용
     * @param AltContent
     *            대체문자 내용
     * @param AltSendType
     *            대체문자 유형
     * @param Receivers
     *            동보수신자 배열
     * @param Buttons
     *            버튼 목록
     * @param SndDT
     *            예약일시
     * @param AdsYN
     *            광고 전송여부
     * @param File
     *            이미지 파일
     * @param ImageURL
     *            친구톡 이미지 링크 URL
     * @param UserID
     *            팝빌회원 아이디
     * @param RequestNum
     *            전송요청번호
     * @return ReceiptNum 접수번호
     * @throws PopbillException
     */
    public String sendFMS(String CorpNum, String PlusFriendID, String SenderNum, String Content, String AltContent, String AltSendType, KakaoReceiver[] Receivers, KakaoButton[] Buttons, String SndDT, Boolean AdsYN, File File, String ImageURL, String UserID, String RequestNum)
            throws PopbillException;

    /**
     * 친구톡 이미지 동일내용 대량전송
     *
     * @param CorpNum
     *            팝빌회원 사업자번호
     * @param PlusFriendID
     *            카카오톡 검색용 아이디
     * @param SenderNum
     *            발신번호
     * @param Content
     *            친구톡 내용
     * @param AltContent
     *            대체문자 내용
     * @param AltSendType
     *            대체문자 유형
     * @param Receivers
     *            동보수신자 배열
     * @param Buttons
     *            버튼 목록
     * @param SndDT
     *            예약일시
     * @param AdsYN
     *            광고 전송여부
     * @param File
     *            이미지 파일
     * @param ImageURL
     *            친구톡 이미지 링크 URL
     * @param UserID
     *            팝빌회원 아이디
     * @param RequestNum
     *            전송요청번호
     * @return ReceiptNum 접수번호
     * @throws PopbillException
     */
    public String sendFMS(String CorpNum, String PlusFriendID, String SenderNum, String Content, String AltSubject, String AltContent, String AltSendType, KakaoReceiver[] Receivers, KakaoButton[] Buttons, String SndDT, Boolean AdsYN, File File, String ImageURL, String UserID, String RequestNum)
            throws PopbillException;
    
    /**
     * 예약전송 취소
     * @param CorpNum
     *            팝빌회원 사업자번호
     * @param ReceiptNum
     *            접수번호
     * @return Response 응답
     * @throws PopbillException
     */
    public Response cancelReserve(String CorpNum, String ReceiptNum) 
            throws PopbillException;

    /**
     * 예약전송 취소
     * @param CorpNum
     *            팝빌회원 사업자번호
     * @param ReceiptNum
     *            접수번호
     * @param UserID
     *            팝빌회원 아이디
     * @return Response 응답
     * @throws PopbillException
     */
    public Response cancelReserve(String CorpNum, String ReceiptNum, String UserID)
    throws PopbillException;

    /**
     * 예약전송 취소
     * @param CorpNum
     *            팝빌회원 사업자번호
     * @param RequestNum
     *            전송요청번호
     * @return Response 응답
     * @throws PopbillException
     */
    public Response cancelReserveRN(String CorpNum, String RequestNum) 
            throws PopbillException;


    /**
     * 예약전송 취소
     * @param CorpNum
     *            팝빌회원 사업자번호
     * @param RequestNum
     *            전송요청번호
     * @param UserID
     *            팝빌회원 아이디
     * @return Response 응답
     * @throws PopbillException
     */
    public Response cancelReserveRN(String CorpNum, String RequestNum, String UserID) 
    throws PopbillException;
    
    /**
     * 예약 메시지 전송 취소. 예약시간 기준 10분전의 건만 취소 가능
     * 
     * @param CorpNum 팝빌회원 사업자번호
     * @param ReceiptNum 전송시 접수번호
     * @param ReceiveNum 전송시 수신번호
     * @return Response 응답
     * @throws PopbillException
     */
    public Response cancelReservebyRCV(String CorpNum, String ReceiptNum, String ReceiveNum) 
    		throws PopbillException;

    /**
     * 예약 메시지 전송 취소. 예약시간 기준 10분전의 건만 취소 가능
     *
     * @param CorpNum 팝빌회원 사업자번호
     * @param ReceiptNum 전송시 접수번호
     * @param ReceiveNum 전송시 수신번호
     * @param UserID 팝빌회원 아이디
     * @return Response 응답
     * @throws PopbillException
     */
    public Response cancelReservebyRCV(String CorpNum, String ReceiptNum, String ReceiveNum, String UserID)
    		throws PopbillException;
    
    /**
     * 예약 메시지 전송 취소. 예약시간 기준 10분전의 건만 취소 가능
     * 
     * @param CorpNum 팝빌회원 사업자번호
     * @param RequestNum 전송시 요청번호
     * @param ReceiveNum 전송시 수신번호
     * @return Response 응답
     * @throws PopbillException
     */
	public Response cancelReserveRNbyRCV(String CorpNum, String RequestNum, String ReceiveNum) 
			throws PopbillException;


    /**
     * 예약 메시지 전송 취소. 예약시간 기준 10분전의 건만 취소 가능
     *
     * @param CorpNum 팝빌회원 사업자번호
     * @param RequestNum 전송시 요청번호
     * @param ReceiveNum 전송시 수신번호
     * @param UserID 팝빌회원 아이디
     * @return Response 응답
     * @throws PopbillException
     */
	public Response cancelReserveRNbyRCV(String CorpNum, String RequestNum, String ReceiveNum, String UserID) 
			throws PopbillException;

    /**
     * 카카오톡 전송내역 상세조회
     * 
     * @param CorpNum
     *            팝빌회원 사업자번호
     * @param ReceiptNum
     *            접수번호
     * @return 카카오톡 전송내역 (see com.popbill.api.kakao.KakaoSentInfo)
     * @throws PopbillException
     */
	public KakaoSentInfo getMessages(String CorpNum, String ReceiptNum) 
	        throws PopbillException;

    /**
     * 카카오톡 전송내역 상세조회
     *
     * @param CorpNum
     *            팝빌회원 사업자번호
     * @param ReceiptNum
     *            접수번호
     * @param UserID
     *            팝빌회원 아이디
     * @return 카카오톡 전송내역 (see com.popbill.api.kakao.KakaoSentInfo)
     * @throws PopbillException
     */
	public KakaoSentInfo getMessages(String CorpNum, String ReceiptNum, String UserID)
	        throws PopbillException;
    
    /**
     * 카카오톡 전송내역 상세조회
     * 
     * @param CorpNum
     *            팝빌회원 사업자번호
     * @param RequestNum
     *            전송요청번호
     * @return 카카오톡 전송내역 (see com.popbill.api.kakao.KakaoSentInfo)
     * @throws PopbillException
     */
    public KakaoSentInfo getMessagesRN(String CorpNum, String RequestNum) 
            throws PopbillException;

    /**
     * 카카오톡 전송내역 상세조회
     *
     * @param CorpNum
     *            팝빌회원 사업자번호
     * @param RequestNum
     *            전송요청번호
     * @param UserID
     *            팝빌회원 아이디
     * @return 카카오톡 전송내역 (see com.popbill.api.kakao.KakaoSentInfo)
     * @throws PopbillException
     */
    public KakaoSentInfo getMessagesRN(String CorpNum, String RequestNum, String UserID)
            throws PopbillException;
    
    /**
     * 전송내역 목록조회
     * 
     * @param CorpNum
     *            팝빌회원 사업자번호
     * @param SDate
     *            시작일자
     * @param EDate
     *            종료일자
     * @param State
     *            전송상태
     * @param Item
     *            검색대상
     * @param ReserveYN
     *            예약여부
     * @param SenderYN
     *            개인조회여부
     * @param Page
     *            페이지 번호
     * @param PerPage
     *            페이지당 검색개수
     * @param Order
     *            정렬방향
     * @return 카카오톡 목록조회 결과 (see com.popbill.api.kakao.KakaoSearchResult)
     * @throws PopbillException
     */
    public KakaoSearchResult search(String CorpNum, String SDate, String EDate, String[] State, String[] Item, String ReserveYN, Boolean SenderYN, Integer Page, Integer PerPage, String Order)
            throws PopbillException;


    /**
     * 전송내역 목록조회
     *
     * @param CorpNum
     *            팝빌회원 사업자번호
     * @param SDate
     *            시작일자
     * @param EDate
     *            종료일자
     * @param State
     *            전송상태
     * @param Item
     *            검색대상
     * @param ReserveYN
     *            예약여부
     * @param SenderYN
     *            개인조회여부
     * @param Page
     *            페이지 번호
     * @param PerPage
     *            페이지당 검색개수
     * @param Order
     *            정렬방향
     * @param UserID
     *            팝빌회원 아이디
     * @return 카카오톡 목록조회 결과 (see com.popbill.api.kakao.KakaoSearchResult)
     * @throws PopbillException
     */
    public KakaoSearchResult search(String CorpNum, String SDate, String EDate, String[] State, String[] Item, String ReserveYN, Boolean SenderYN, Integer Page, Integer PerPage, String Order, String UserID)
            throws PopbillException;


    /**
     * 전송내역 목록조회
     *
     * @param CorpNum
     *            팝빌회원 사업자번호
     * @param SDate
     *            시작일자
     * @param EDate
     *            종료일자
     * @param State
     *            전송상태
     * @param Item
     *            검색대상
     * @param ReserveYN
     *            예약여부
     * @param SenderYN
     *            개인조회여부
     * @param Page
     *            페이지 번호
     * @param PerPage
     *            페이지당 검색개수
     * @param Order
     *            정렬방향
     * @param UserID
     *            팝빌회원 아이디
     * @param QString
     *            조회검색어 / 수신자명 기재
     * @return 카카오톡 목록조회 결과 (see com.popbill.api.kakao.KakaoSearchResult)
     * @throws PopbillException
     */
    public KakaoSearchResult search(String CorpNum, String SDate, String EDate, String[] State, String[] Item, String ReserveYN, Boolean SenderYN, Integer Page, Integer PerPage, String Order, String UserID, String QString)
            throws PopbillException;

    /**
     *  카카오톡 전송내역 팝업 URL을 반환
     *
     * @param CorpNum
     *          팝빌회원 사업자번호
     * @param UserID
     *          팝빌회원 아이디
     * @return 팝빌 URL (AccessToken값 포함. Token값은 응답후 30초까지만 유효함)
     * @throws PopbillException
     */
    public String getSentListURL(String CorpNum, String UserID) throws PopbillException;

    public String getURL(String CorpNum, String TOGO, String UserID)
    throws PopbillException;

    public float getUnitCost(String CorpNum, KakaoType KakaoType) 
    throws PopbillException;

    public ChargeInfo getChargeInfo(String CorpNum, KakaoType kakaoType) 
    throws PopbillException;

}
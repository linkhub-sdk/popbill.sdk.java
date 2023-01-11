package com.popbill.api.kako.test;

import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.util.ArrayList;

import org.junit.Test;

import com.popbill.api.ChargeInfo;
import com.popbill.api.KakaoService;
import com.popbill.api.PopbillException;
import com.popbill.api.Response;
import com.popbill.api.kakao.ATSTemplate;
import com.popbill.api.kakao.KakaoButton;
import com.popbill.api.kakao.KakaoReceiver;
import com.popbill.api.kakao.KakaoSearchResult;
import com.popbill.api.kakao.KakaoSentInfo;
import com.popbill.api.kakao.KakaoServiceImp;
import com.popbill.api.kakao.KakaoType;
import com.popbill.api.kakao.PlusFriendID;
import com.popbill.api.kakao.SenderNumber;

public class KakaoServiceTest {
    private final String testLinkID = "TESTER";
    private final String testSecretKey = "SwWxqU+0TErBXy/9TVjIPEnI0VTUMMSQZtJf3Ed8q3I=";

    private KakaoService kakaoService;
    
    public KakaoServiceTest() {
        KakaoServiceImp service = new KakaoServiceImp();

        service.setLinkID(testLinkID);
        service.setSecretKey(testSecretKey);
        service.setTest(true);
        
        kakaoService = service;
    }
    
    @Test
    public void GetPlusFriendMgtURL_TEST() throws PopbillException {
    
        String url = kakaoService.getPlusFriendMgtURL("1234567890","testkorea");
    
        System.out.println(url);
    }

    @Test
    public void listPlusFriend_TEST() throws PopbillException {
        
        PlusFriendID[] friendList = kakaoService.listPlusFriendID("1234567890");
        
        int i;
        for (i=0; i<friendList.length; i++){
            System.out.println(friendList[i].getPlusFriendID());
            System.out.println(friendList[i].getPlusFriendName());
            System.out.println(friendList[i].getRegDT());
            System.out.println(friendList[i].getState());
            System.out.println(friendList[i].getStateDT());
        }
    }

    @Test
    public void checkSenderNumber_TEST() throws PopbillException {
        Response response = kakaoService.checkSenderNumber("1234567890", "1400-00");
        assertNotNull(response);
        
        System.out.println(response.getCode());
        System.out.println(response.getMessage());
    }

    @Test
    public void GetSenderNumerMgtURL_TEST() throws PopbillException {
    
        String url = kakaoService.getSenderNumberMgtURL("1234567890","testkorea");
    
        System.out.println(url);
    }

    @Test
    public void getSenderNumberList_TEST() throws PopbillException {
        
        SenderNumber[] listInfo = kakaoService.getSenderNumberList("1234567890");
        
        int i;
        for (i=0; i<listInfo.length; i++){
            System.out.println(listInfo[i].getNumber());
            System.out.println(listInfo[i].getState());
            System.out.println(listInfo[i].getRepresentYN());
            System.out.println(listInfo[i].getMemo());
        }
    }

    @Test
    public void GetATSTemplateMgtURL_TEST() throws PopbillException {
    
        String url = kakaoService.getATSTemplateMgtURL("1234567890","testkorea");
    
        System.out.println(url);
    }

    @Test
    public void getATSTemplate_TEST() throws PopbillException {
        
        ATSTemplate templateInfo = kakaoService.getATSTemplate("1234567890", "022070000338");
        
        assertNotNull(templateInfo);
        
        System.out.println(templateInfo.getPlusFriendID());
        System.out.println(templateInfo.getTemplateCode());
        System.out.println(templateInfo.getTemplateName());
        System.out.println(templateInfo.getTemplate());
        System.out.println(templateInfo.getAds());
        System.out.println(templateInfo.getAppendix());
        System.out.println(templateInfo.getBtns());
        System.out.println(templateInfo.getSecureYN());
        System.out.println(templateInfo.getState());
        System.out.println(templateInfo.getStateDT());
    }

    @Test
    public void listATSTempalte_TEST() throws PopbillException {
        
        ATSTemplate[] templateList = kakaoService.listATSTemplate("1234567890");
        
        int i;
        for (i=0; i<templateList.length; i++){
            System.out.println(templateList[i].getTemplateCode());
            System.out.println(templateList[i].getTemplateName());
            System.out.println(templateList[i].getTemplate());
            System.out.println(templateList[i].getPlusFriendID());
            System.out.println(templateList[i].getAds());
            System.out.println(templateList[i].getAppendix());
            System.out.println(templateList[i].getBtns());
            System.out.println(templateList[i].getSecureYN());
            System.out.println(templateList[i].getState());
            System.out.println(templateList[i].getStateDT());
        }
    }

    @Test
    public void sendATS_01_TEST() throws PopbillException {
        String testCorpNum = "1234567890";
        String templateCode = "018060000159";
        String senderNum = "01011112222";
        String content = "[믿을 수 있는 팝빌]"
                + "안녕하세요. "
                + "고객 님!고객님의 공인인증서가 등록처리 되었음을 알려드립니다!"
                + "- 등록일자 : 2018-07-24 "
                + "- 인증서용도 : 전자세금계산서용 공인인증서 삭제는 팝빌 사이트에서 가능합니다. "
                + "감사합니다.";
        String altContent = "대체문자 내용";
        String altSendType = "";
        String receiverNum = "01011112222";
        String receiverName = "01수신자명";
        String sndDT = "20230111180000";
                        
        String receiptNum = kakaoService.sendATS(testCorpNum, templateCode, senderNum, content, altContent, altSendType, 
                receiverNum, receiverName, sndDT);
        
        assertNotNull(receiptNum);
        System.out.println(receiptNum);
    }    
    
    @Test
    public void sendATS_02_TEST() throws PopbillException {
        String testCorpNum = "1234567890";
        String templateCode = "018060000159";
        String senderNum = "01011112222";
        String content = "[믿을 수 있는 팝빌]"
                + "안녕하세요. "
                + "고객 님!고객님의 공인인증서가 등록처리 되었음을 알려드립니다!"
                + "- 등록일자 : 2018-07-24 "
                + "- 인증서용도 : 전자세금계산서용 공인인증서 삭제는 팝빌 사이트에서 가능합니다. "
                + "감사합니다.";
        String altContent = "대체문자 내용";
        String altSendType = "";
        String receiverNum = "01022223333";
        String receiverName = "02수신자명";
        String sndDT = "";
                        
        String receiptNum = kakaoService.sendATS(testCorpNum, templateCode, senderNum, content, altContent, altSendType, 
                receiverNum, receiverName, sndDT, "testkorea");
        
        assertNotNull(receiptNum);
        System.out.println(receiptNum);
    }    
    
    @Test
    public void sendATS_03_TEST() throws PopbillException {
        String testCorpNum = "1234567890";
        String templateCode = "018060000159";
        String senderNum = "01011112222";
        String content = "[믿을 수 있는 팝빌]"
                + "안녕하세요. "
                + "고객 님!고객님의 공인인증서가 등록처리 되었음을 알려드립니다!"
                + "- 등록일자 : 2018-07-24 "
                + "- 인증서용도 : 전자세금계산서용 공인인증서 삭제는 팝빌 사이트에서 가능합니다. "
                + "감사합니다.";
        String altContent = "대체문자 내용";
        String altSendType = "";
        String receiverNum = "01022223333";
        String receiverName = "03수신자명";
        String sndDT = "20230111180000";
                        
        String receiptNum = kakaoService.sendATS(testCorpNum, templateCode, senderNum, content, altContent, altSendType, 
                receiverNum, receiverName, sndDT, "testkorea", "20230111_ats_23");
        
        assertNotNull(receiptNum);
        System.out.println(receiptNum);
    }    
    
    @Test
    public void sendATS_04_TEST() throws PopbillException {
        String testCorpNum = "1234567890";
        String templateCode = "018060000159";
        String senderNum = "01011112222";
        String content = "[믿을 수 있는 팝빌]"
                + "안녕하세요. "
                + "고객 님!고객님의 공인인증서가 등록처리 되었음을 알려드립니다!"
                + "- 등록일자 : 2018-07-24 "
                + "- 인증서용도 : 전자세금계산서용 공인인증서 삭제는 팝빌 사이트에서 가능합니다. "
                + "감사합니다.";
        String altSubject = "대체문자제목";
        String altContent = "대체문자 내용";
        String altSendType = "";
        String receiverNum = "010111222";
        String receiverName = "03수신자명";
        String sndDT = "";
                        
        String receiptNum = kakaoService.sendATS(testCorpNum, templateCode, senderNum, content, altSubject, altContent, altSendType, 
                receiverNum, receiverName, sndDT, "testkorea", "20180724_ats_13");
        
        assertNotNull(receiptNum);
        System.out.println(receiptNum);
    }    
    
    @Test
    public void sendATS_05_TEST() throws PopbillException {
        String testCorpNum = "1234567890";
        String templateCode = "018060000159";
        String senderNum = "01011112222";
        String content = "[믿을 수 있는 팝빌]"
                + "안녕하세요. "
                + "고객 님!고객님의 공인인증서가 등록처리 되었음을 알려드립니다!"
                + "- 등록일자 : 2018-07-24 "
                + "- 인증서용도 : 전자세금계산서용 공인인증서 삭제는 팝빌 사이트에서 가능합니다. "
                + "감사합니다.";
        String altContent = "대체문자 내용";
        String altSendType = "";
        
        KakaoReceiver[] receivers = new KakaoReceiver[2];
        
        KakaoReceiver message = new KakaoReceiver();
        message.setReceiverNum("01011112222");
        message.setReceiverName("04-1수신자명");
        message.setMessage(content);
        message.setAltMessage(altContent);
        receivers[0] = message;
        message.setReceiverNum("01011112222");
        message.setReceiverName("04-2수신자명");
        message.setMessage(content);
        message.setAltMessage(altContent);
        receivers[1] = message;
        
        String sndDT = "";
        
        String receiptNum = kakaoService.sendATS(testCorpNum, templateCode, senderNum, altSendType, receivers, sndDT);
        
        assertNotNull(receiptNum);
        System.out.println(receiptNum);
    }
    
    @Test
    public void sendATS_06_TEST() throws PopbillException {
        String testCorpNum = "1234567890";
        String templateCode = "018060000159";
        String senderNum = "01011112222";
        String content = "[믿을 수 있는 팝빌]"
                + "안녕하세요. "
                + "고객 님!고객님의 공인인증서가 등록처리 되었음을 알려드립니다!"
                + "- 등록일자 : 2018-07-24 "
                + "- 인증서용도 : 전자세금계산서용 공인인증서 삭제는 팝빌 사이트에서 가능합니다. "
                + "감사합니다.";
        String altContent = "대체문자 내용";
        String altSendType = "";
        
        KakaoReceiver[] receivers = new KakaoReceiver[2];
        
        KakaoReceiver message = new KakaoReceiver();
        message.setReceiverNum("010111222");
        message.setReceiverName("05-1수신자명");
        message.setMessage(content);
        message.setAltMessage(altContent);
        receivers[0] = message;
        message.setReceiverNum("010111222");
        message.setReceiverName("05-2수신자명");
        message.setMessage(content);
        message.setAltMessage(altContent);
        receivers[1] = message;
        
        String sndDT = "";
        
        String receiptNum = kakaoService.sendATS(testCorpNum, templateCode, senderNum, altSendType, receivers, sndDT,
                "testkorea");
        
        assertNotNull(receiptNum);
        System.out.println(receiptNum);
    }
    
    @Test
    public void sendATS_07_TEST() throws PopbillException {
        String testCorpNum = "1234567890";
        String templateCode = "018060000159";
        String senderNum = "01011112222";
        String content = "[믿을 수 있는 팝빌]"
                + "안녕하세요. "
                + "고객 님!고객님의 공인인증서가 등록처리 되었음을 알려드립니다!"
                + "- 등록일자 : 2018-07-24 "
                + "- 인증서용도 : 전자세금계산서용 공인인증서 삭제는 팝빌 사이트에서 가능합니다. "
                + "감사합니다.";
        String altContent = "대체문자 내용";
        String altSendType = "";
        
        KakaoReceiver[] receivers = new KakaoReceiver[2];
        
        KakaoReceiver message = new KakaoReceiver();
        message.setReceiverNum("010111222");
        message.setReceiverName("06-1수신자명");
        message.setMessage(content);
        message.setAltMessage(altContent);
        receivers[0] = message;
        message.setReceiverNum("010111222");
        message.setReceiverName("06-2수신자명");
        message.setMessage(content);
        message.setAltMessage(altContent);
        receivers[1] = message;
        
        String sndDT = "";
        
        String receiptNum = kakaoService.sendATS(testCorpNum, templateCode, senderNum, altSendType, receivers, sndDT,
                "testkorea", "20180724_ats_16");
        
        assertNotNull(receiptNum);
        System.out.println(receiptNum);
    }
    
    @Test
    public void sendATS_08_TEST() throws PopbillException {
        String testCorpNum = "1234567890";
        String templateCode = "018060000159";
        String senderNum = "01011112222";
        String content = "[믿을 수 있는 팝빌]"
                + "안녕하세요. "
                + "고객 님!고객님의 공인인증서가 등록처리 되었음을 알려드립니다!"
                + "- 등록일자 : 2018-07-24 "
                + "- 인증서용도 : 전자세금계산서용 공인인증서 삭제는 팝빌 사이트에서 가능합니다. "
                + "감사합니다.";
        String altContent = "대체문자 내용";
        String altSendType = "";
        
        KakaoReceiver[] receivers = new KakaoReceiver[2];
        
        KakaoReceiver message = new KakaoReceiver();
        message.setReceiverNum("010111222");
        message.setReceiverName("07-1수신자명");
        message.setMessage(content);
        message.setAltMessage(altContent);
        receivers[0] = message;
        message.setReceiverNum("010111222");
        message.setReceiverName("07-2수신자명");
        message.setMessage(content);
        message.setAltMessage(altContent);
        receivers[1] = message;
        
        String sndDT = "";
        
        String receiptNum = kakaoService.sendATS(testCorpNum, templateCode, senderNum, content, altContent, 
                altSendType, receivers, sndDT);
        
        assertNotNull(receiptNum);
        System.out.println(receiptNum);
    }    
    
    @Test
    public void sendATS_09_TEST() throws PopbillException {
        String testCorpNum = "1234567890";
        String templateCode = "018060000159";
        String senderNum = "01011112222";
        String content = "[믿을 수 있는 팝빌]"
                + "안녕하세요. "
                + "고객 님!고객님의 공인인증서가 등록처리 되었음을 알려드립니다!"
                + "- 등록일자 : 2018-07-24 "
                + "- 인증서용도 : 전자세금계산서용 공인인증서 삭제는 팝빌 사이트에서 가능합니다. "
                + "감사합니다.";
        String altContent = "대체문자 내용";
        String altSendType = "";
        
        KakaoReceiver[] receivers = new KakaoReceiver[2];
        
        KakaoReceiver message = new KakaoReceiver();
        message.setReceiverNum("010111222");
        message.setReceiverName("08-1수신자명");
        message.setMessage(content);
        message.setAltMessage(altContent);
        receivers[0] = message;
        message.setReceiverNum("010111222");
        message.setReceiverName("08-2수신자명");
        message.setMessage(content);
        message.setAltMessage(altContent);
        receivers[1] = message;
        
        String sndDT = "";
        
        String receiptNum = kakaoService.sendATS(testCorpNum, templateCode, senderNum, content, altContent, 
                altSendType, receivers, sndDT, "testkorea", "");
        
        assertNotNull(receiptNum);
        System.out.println(receiptNum);
    }    
    
    @Test
    public void sendATS_10_TEST() throws PopbillException {
        String testCorpNum = "1234567890";
        String templateCode = "019020000163";
        String senderNum = "07043042991";
        String content = "[ 팝빌 ]\n"
                + "신청하신 #{템플릿코드}에 대한 심사가 완료되어 승인 처리되었습니다."
                + "해당 템플릿으로 전송 가능합니다.\n\n"
                + "문의사항 있으시면 파트너센터로 편하게 연락주시기 바랍니다.\n\n"
                + "팝빌 파트너센터 : 1600-8536\n"
                + "support@linkhub.co.kr";
        String altContent = "대체문자 내용";
        String altSendType = "";
        
        KakaoReceiver[] receivers = new KakaoReceiver[2];
        
        KakaoReceiver message = new KakaoReceiver();
        message.setReceiverNum("01022223333");
        message.setReceiverName("09-1수신자명");
        message.setMessage(content);
        message.setAltMessage(altContent);
        receivers[0] = message;
        
        String sndDT = "";
        
        KakaoButton[] btns = new KakaoButton[1];
        KakaoButton button = new KakaoButton();
        button.setN("템플릿 안내");
        button.setT("WL");
        button.setU1("https://www.popbill.com");
        button.setU2("http://test.popbill.com");
        btns[0] = button;
        
        String receiptNum = kakaoService.sendATS(testCorpNum, templateCode, senderNum, content, altContent, 
                altSendType, receivers, sndDT, "testkorea", "", btns);
        
        assertNotNull(receiptNum);
        System.out.println(receiptNum);
    }    
    
    @Test
    public void sendATS_11_TEST() throws PopbillException {
        String testCorpNum = "1234567890";
        String templateCode = "019020000163";
        String senderNum = "07043042991";
        String content = "[ 팝빌 ]\n"
                + "신청하신 #{템플릿코드}에 대한 심사가 완료되어 승인 처리되었습니다."
                + "해당 템플릿으로 전송 가능합니다.\n\n"
                + "문의사항 있으시면 파트너센터로 편하게 연락주시기 바랍니다.\n\n"
                + "팝빌 파트너센터 : 1600-8536\n"
                + "support@linkhub.co.kr";
        String altSubject = "대체문자 제목";
        String altContent = "대체문자 내용";
        String altSendType = "";
        
        KakaoReceiver[] receivers = new KakaoReceiver[2];
        
        KakaoReceiver message = new KakaoReceiver();
        message.setReceiverNum("01022223333");
        message.setReceiverName("09-1수신자명");
        message.setMessage(content);
        message.setAltMessage(altContent);
        receivers[0] = message;
        
        String sndDT = "";
        
        KakaoButton[] btns = new KakaoButton[1];
        KakaoButton button = new KakaoButton();
        button.setN("템플릿 안내");
        button.setT("WL");
        button.setU1("https://www.popbill.com");
        button.setU2("http://test.popbill.com");
        btns[0] = button;
        
        String receiptNum = kakaoService.sendATS(testCorpNum, templateCode, senderNum, content, altSubject, altContent, 
                altSendType, receivers, sndDT, "testkorea", "", btns);
        
        assertNotNull(receiptNum);
        System.out.println(receiptNum);
    }    
    
    @Test
    public void sendFTS_01_TEST() throws PopbillException {
        String testCorpNum = "1234567890";
        String plusFriendID = "@팝빌";
        String senderNum = "01011112222";
        String content = "친구톡 메시지 내용";
        String altContent = "대체문자 내용";
        String altSendType = "";
        String receiverNum = "010111222";
        String receiverName = "01수신자명";
        String sndDT = "";
        Boolean adsYN = false;
        
        KakaoButton[] btns = new KakaoButton[2];
        KakaoButton button = new KakaoButton();
        button.setN("버튼명");
        button.setT("WL");
        button.setU1("http://www.popbill.com");
        button.setU2("http://test.popbill.com");
        btns[0] = button;
        
        button = new KakaoButton();
        button.setN("버튼명2");
        button.setT("WL");
        button.setU1("http://www.popbill.com");
        button.setU2("http://test.popbill.com");
        btns[1] = button;
        
        String receiptNum = kakaoService.sendFTS(testCorpNum, plusFriendID, senderNum, content, 
                altContent, altSendType, btns, receiverNum, receiverName, sndDT, adsYN);
        
        assertNotNull(receiptNum);
        System.out.println(receiptNum);
    }
    
    @Test
    public void sendFTS_02_TEST() throws PopbillException {
        String testCorpNum = "1234567890";
        String plusFriendID = "@팝빌";
        String senderNum = "01011112222";
        String content = "친구톡 메시지 내용";
        String altContent = "대체문자 내용";
        String altSendType = "";
        String receiverNum = "010111222";
        String receiverName = "02수신자명";
        String sndDT = "";
        Boolean adsYN = false;
        
        KakaoButton[] btns = new KakaoButton[2];
        KakaoButton button = new KakaoButton();
        button.setN("버튼명");
        button.setT("WL");
        button.setU1("http://www.popbill.com");
        button.setU2("http://test.popbill.com");
        btns[0] = button;
        
        button = new KakaoButton();
        button.setN("버튼명2");
        button.setT("WL");
        button.setU1("http://www.popbill.com");
        button.setU2("http://test.popbill.com");
        btns[1] = button;
        
        String receiptNum = kakaoService.sendFTS(testCorpNum, plusFriendID, senderNum, content, 
                altContent, altSendType, btns, receiverNum, receiverName, sndDT, adsYN, 
                "testkorea");
        
        assertNotNull(receiptNum);
        System.out.println(receiptNum);
    }
    
    @Test
    public void sendFTS_03_TEST() throws PopbillException {
        String testCorpNum = "1234567890";
        String plusFriendID = "@팝빌";
        String senderNum = "01011112222";
        String content = "친구톡 메시지 내용";
        String altContent = "대체문자 내용";
        String altSendType = "";
        String receiverNum = "010111222";
        String receiverName = "03수신자명";
        String sndDT = "";
        Boolean adsYN = false;
        
        KakaoButton[] btns = new KakaoButton[2];
        KakaoButton button = new KakaoButton();
        button.setN("버튼명");
        button.setT("WL");
        button.setU1("http://www.popbill.com");
        button.setU2("http://test.popbill.com");
        btns[0] = button;
        
        button = new KakaoButton();
        button.setN("버튼명2");
        button.setT("WL");
        button.setU1("http://www.popbill.com");
        button.setU2("http://test.popbill.com");
        btns[1] = button;
        
        String receiptNum = kakaoService.sendFTS(testCorpNum, plusFriendID, senderNum, content, 
                altContent, altSendType, btns, receiverNum, receiverName, sndDT, adsYN,
                "testkorea", "20180724_fts_13");
        
        assertNotNull(receiptNum);
        System.out.println(receiptNum);
    }
    
    @Test
    public void sendFTS_04_TEST() throws PopbillException {
        String testCorpNum = "1234567890";
        String plusFriendID = "@팝빌";
        String senderNum = "01011112222";
        String content = "친구톡 메시지 내용";
        String altSubject = "";
        String altContent = "대체문자 내용";
        String altSendType = "";
        String receiverNum = "010111222";
        String receiverName = "03수신자명";
        String sndDT = "";
        Boolean adsYN = false;
        
        KakaoButton[] btns = new KakaoButton[2];
        KakaoButton button = new KakaoButton();
        button.setN("버튼명");
        button.setT("WL");
        button.setU1("http://www.popbill.com");
        button.setU2("http://test.popbill.com");
        btns[0] = button;
        
        button = new KakaoButton();
        button.setN("버튼명2");
        button.setT("WL");
        button.setU1("http://www.popbill.com");
        button.setU2("http://test.popbill.com");
        btns[1] = button;
        
        String receiptNum = kakaoService.sendFTS(testCorpNum, plusFriendID, senderNum, content, 
                altSubject, altContent, altSendType, btns, receiverNum, receiverName, sndDT, adsYN,
                "testkorea", "20180724_fts_13");
        
        assertNotNull(receiptNum);
        System.out.println(receiptNum);
    }
    
    @Test
    public void sendFTS_05_TEST() throws PopbillException {
        String testCorpNum = "1234567890";
        String plusFriendID = "@팝빌";
        String senderNum = "07043042991";
        String content = "친구톡 메시지 내용";
        String altContent = "대체문자 내용";
        String altSendType = "";
        String sndDT = "";
        Boolean adsYN = true;
        
        
        
        KakaoButton[] btns = new KakaoButton[2];
        KakaoButton button = new KakaoButton();
        button.setN("버튼명ddd");
        button.setT("WL");
        button.setU1("http://dddwww.popbill.com");
        button.setU2("http://dddtest.popbill.com");
        btns[0] = button;
        
        button = new KakaoButton();
        button.setN("버튼명2");
        button.setT("WL");
        button.setU1("http://www.popbill.com");
        button.setU2("http://test.popbill.com");
        btns[1] = button;
        
        KakaoReceiver[] receivers = new KakaoReceiver[2];
        
        KakaoReceiver message = new KakaoReceiver();
        message.setReceiverNum("010111222");
        message.setReceiverName("04-1수신자명");
        message.setBtns(new ArrayList<KakaoButton>());
        message.getBtns().add(btns[0]);
        message.getBtns().add(btns[1]);
        
        receivers[0] = message;
        
        button = new KakaoButton();
        button.setN("버튼명ddd");
        button.setT("WL");
        button.setU1("http://cccwww.popbill.com");
        button.setU2("http://ccctest.popbill.com");
        btns[0] = button;
        
        button = new KakaoButton();
        button.setN("버튼명2");
        button.setT("WL");
        button.setU1("http://www.popbill.com");
        button.setU2("http://test.popbill.com");
        btns[1] = button;
        
        message = new KakaoReceiver();
        message.setReceiverNum("010333444");
        message.setReceiverName("04-2수신자명");
        message.setBtns(new ArrayList<KakaoButton>());
        message.getBtns().add(btns[0]);
        message.getBtns().add(btns[1]);
        
        receivers[1] = message;

        String receiptNum = kakaoService.sendFTS(testCorpNum, plusFriendID, senderNum, content, 
                altContent, altSendType, receivers, btns, sndDT, adsYN);
        
        assertNotNull(receiptNum);
        System.out.println(receiptNum);
    }
    
    @Test
    public void sendFTS_06_TEST() throws PopbillException {
        String testCorpNum = "1234567890";
        String plusFriendID = "@팝빌";
        String senderNum = "01011112222";
        String content = "친구톡 메시지 내용";
        String altContent = "대체문자 내용";
        String altSendType = "";
        String sndDT = "";
        Boolean adsYN = true;
        
        KakaoButton[] btns = new KakaoButton[2];
        KakaoButton button = new KakaoButton();
        button.setN("버튼명");
        button.setT("WL");
        button.setU1("http://www.popbill.com");
        button.setU2("http://test.popbill.com");
        btns[0] = button;
        
        button = new KakaoButton();
        button.setN("버튼명2");
        button.setT("WL");
        button.setU1("http://www.popbill.com");
        button.setU2("http://test.popbill.com");
        btns[1] = button;
        
        KakaoReceiver[] receivers = new KakaoReceiver[2];
        
        KakaoReceiver message = new KakaoReceiver();
        message.setReceiverNum("010111222");
        message.setReceiverName("05-1수신자명");
        receivers[0] = message;
        
        message = new KakaoReceiver();
        message.setReceiverNum("010333444");
        message.setReceiverName("05-2수신자명");
        receivers[1] = message;

        String receiptNum = kakaoService.sendFTS(testCorpNum, plusFriendID, senderNum, content, 
                altContent, altSendType, receivers, btns, sndDT, adsYN, "testkorea");
        
        assertNotNull(receiptNum);
        System.out.println(receiptNum);
    }
    
    @Test
    public void sendFTS_07_TEST() throws PopbillException {
        String testCorpNum = "1234567890";
        String plusFriendID = "@팝빌";
        String senderNum = "01011112222";
        String content = "친구톡 메시지 내용";
        String altContent = "대체문자 내용";
        String altSendType = "";
        String sndDT = "";
        Boolean adsYN = true;
        
        KakaoButton[] btns = new KakaoButton[2];
        KakaoButton button = new KakaoButton();
        button.setN("버튼명");
        button.setT("WL");
        button.setU1("http://www.popbill.com");
        button.setU2("http://test.popbill.com");
        btns[0] = button;
        
        button = new KakaoButton();
        button.setN("버튼명2");
        button.setT("WL");
        button.setU1("http://www.popbill.com");
        button.setU2("http://test.popbill.com");
        btns[1] = button;
        
        KakaoReceiver[] receivers = new KakaoReceiver[2];
        
        KakaoReceiver message = new KakaoReceiver();
        message.setReceiverNum("010111222");
        message.setReceiverName("06-1수신자명");
        receivers[0] = message;
        
        message = new KakaoReceiver();
        message.setReceiverNum("010333444");
        message.setReceiverName("06-2수신자명");
        receivers[1] = message;

        String receiptNum = kakaoService.sendFTS(testCorpNum, plusFriendID, senderNum, content, 
                altContent, altSendType, receivers, btns, sndDT, adsYN, "testkorea", "20180724_fts_16");
        
        assertNotNull(receiptNum);
        System.out.println(receiptNum);
    }
    
    @Test
    public void sendFTS_08_TEST() throws PopbillException {
        String testCorpNum = "1234567890";
        String plusFriendID = "@팝빌";
        String senderNum = "01011112222";
        String content = "친구톡 메시지 내용";
        String altSubject = "대체문자 제목";
        String altContent = "대체문자 내용";
        String altSendType = "";
        String sndDT = "";
        Boolean adsYN = true;
        
        KakaoButton[] btns = new KakaoButton[2];
        KakaoButton button = new KakaoButton();
        button.setN("버튼명");
        button.setT("WL");
        button.setU1("http://www.popbill.com");
        button.setU2("http://test.popbill.com");
        btns[0] = button;
        
        button = new KakaoButton();
        button.setN("버튼명2");
        button.setT("WL");
        button.setU1("http://www.popbill.com");
        button.setU2("http://test.popbill.com");
        btns[1] = button;
        
        KakaoReceiver[] receivers = new KakaoReceiver[2];
        
        KakaoReceiver message = new KakaoReceiver();
        message.setReceiverNum("010111222");
        message.setReceiverName("06-1수신자명");
        receivers[0] = message;
        
        message = new KakaoReceiver();
        message.setReceiverNum("010333444");
        message.setReceiverName("06-2수신자명");
        receivers[1] = message;

        String receiptNum = kakaoService.sendFTS(testCorpNum, plusFriendID, senderNum, content, 
               altSubject, altContent, altSendType, receivers, btns, sndDT, adsYN, "testkorea", "20180724_fts_16");
        
        assertNotNull(receiptNum);
        System.out.println(receiptNum);
    }
    
    @Test
    public void sendFTS_09_TEST() throws PopbillException {
        String testCorpNum = "1234567890";
        String plusFriendID = "@팝빌";
        String senderNum = "01011112222";
        String altSendType = "";
        String sndDT = "";
        Boolean adsYN = false;
        
        KakaoButton[] btns = new KakaoButton[2];
        KakaoButton button = new KakaoButton();
        button.setN("버튼명");
        button.setT("WL");
        button.setU1("http://www.popbill.com");
        button.setU2("http://test.popbill.com");
        btns[0] = button;
        
        button = new KakaoButton();
        button.setN("버튼명2");
        button.setT("WL");
        button.setU1("http://www.popbill.com");
        button.setU2("http://test.popbill.com");
        btns[1] = button;
        
        KakaoReceiver[] receivers = new KakaoReceiver[2];
        
        KakaoReceiver message = new KakaoReceiver();
        message.setReceiverNum("010111222");
        message.setReceiverName("07-1수신자명");
        message.setMessage("[테스트] 테스트 템플릿입니다요1");
        message.setAltMessage("대체문자 내용");
        receivers[0] = message;
        
        message = new KakaoReceiver();
        message.setReceiverNum("010333444");
        message.setReceiverName("07-2수신자명");
        message.setMessage("[테스트] 테스트 템플릿입니다요2");
        message.setAltMessage("대체문자 내용");
        receivers[1] = message;

        String receiptNum = kakaoService.sendFTS(testCorpNum, plusFriendID, senderNum, altSendType, 
                receivers, btns, sndDT, adsYN);
        
        assertNotNull(receiptNum);
        System.out.println(receiptNum);
    }
    
    @Test
    public void sendFTS_10_TEST() throws PopbillException {
        String testCorpNum = "1234567890";
        String plusFriendID = "@팝빌";
        String senderNum = "01011112222";
        String altSendType = "";
        String sndDT = "";
        Boolean adsYN = false;
        
        KakaoButton[] btns = new KakaoButton[2];
        KakaoButton button = new KakaoButton();
        button.setN("버튼명");
        button.setT("WL");
        button.setU1("http://www.popbill.com");
        button.setU2("http://test.popbill.com");
        btns[0] = button;
        
        button = new KakaoButton();
        button.setN("버튼명2");
        button.setT("WL");
        button.setU1("http://www.popbill.com");
        button.setU2("http://test.popbill.com");
        btns[1] = button;
        
        KakaoReceiver[] receivers = new KakaoReceiver[2];
        
        KakaoReceiver message = new KakaoReceiver();
        message.setReceiverNum("010111222");
        message.setReceiverName("08-1수신자명");
        message.setMessage("[테스트] 테스트 템플릿입니다요1");
        message.setAltMessage("대체문자 내용");
        receivers[0] = message;
        
        message = new KakaoReceiver();
        message.setReceiverNum("010333444");
        message.setReceiverName("08-2수신자명");
        message.setMessage("[테스트] 테스트 템플릿입니다요2");
        message.setAltMessage("대체문자 내용");
        receivers[1] = message;

        String receiptNum = kakaoService.sendFTS(testCorpNum, plusFriendID, senderNum, altSendType, 
                receivers, btns, sndDT, adsYN, "testkorea");
        
        assertNotNull(receiptNum);
        System.out.println(receiptNum);
    }
    
    @Test
    public void sendFTS_11_TEST() throws PopbillException {
        String testCorpNum = "1234567890";
        String plusFriendID = "@팝빌";
        String senderNum = "01011112222";
        String altSendType = "";
        String sndDT = "";
        Boolean adsYN = false;
        
        KakaoButton[] btns = new KakaoButton[2];
        KakaoButton button = new KakaoButton();
        button.setN("버튼명");
        button.setT("WL");
        button.setU1("http://www.popbill.com");
        button.setU2("http://test.popbill.com");
        btns[0] = button;
        
        button = new KakaoButton();
        button.setN("버튼명2");
        button.setT("WL");
        button.setU1("http://www.popbill.com");
        button.setU2("http://test.popbill.com");
        btns[1] = button;
        
        KakaoReceiver[] receivers = new KakaoReceiver[2];
        
        KakaoReceiver message = new KakaoReceiver();
        message.setReceiverNum("010111222");
        message.setReceiverName("09-1수신자명");
        message.setMessage("[테스트] 테스트 템플릿입니다요1");
        message.setAltMessage("대체문자 내용");
        message.setInterOPRefKey("01");
        receivers[0] = message;
        
        message = new KakaoReceiver();
        message.setReceiverNum("010333444");
        message.setReceiverName("09-2수신자명");
        message.setMessage("[테스트] 테스트 템플릿입니다요2");
        message.setAltMessage("대체문자 내용");
        message.setInterOPRefKey("02");
        receivers[1] = message;

        String receiptNum = kakaoService.sendFTS(testCorpNum, plusFriendID, senderNum, altSendType, 
                receivers, btns, sndDT, adsYN, "testkorea", "20190203_01");
        
        assertNotNull(receiptNum);
        System.out.println(receiptNum);
    }
    
    @Test
    public void sendFMS_01_TEST() throws PopbillException {
        String testCorpNum = "1234567890";
        String plusFriendID = "@팝빌";
        String senderNum = "01011112222";
        String content = "친구톡 메시지 내용~~!";
        String altContent = "대체문자 내용";
        String altSendType = "";
        String receiverNum = "010111222";
        String receiverName = "01수신자명";
        String sndDT = "";
        Boolean adsYN = false;
        
        KakaoButton[] btns = new KakaoButton[2];
        KakaoButton button = new KakaoButton();
        button.setN("버튼명");
        button.setT("WL");
        button.setU1("http://www.popbill.com");
        button.setU2("http://test.popbill.com");
        btns[0] = button;
        
        button = new KakaoButton();
        button.setN("버튼명2");
        button.setT("WL");
        button.setU1("http://www.popbill.com");
        button.setU2("http://test.popbill.com");
        btns[1] = button;
        
        File file = new File("./test.jpg");
        String imageURL = "http://test.popbill.com";
        String receiptNum = kakaoService.sendFMS(testCorpNum, plusFriendID, senderNum, content, 
                altContent, altSendType, btns, receiverNum, receiverName, sndDT, adsYN, file, imageURL);
        
        assertNotNull(receiptNum);
        System.out.println(receiptNum);
    }
    
    @Test
    public void sendFMS_02_TEST() throws PopbillException {
        String testCorpNum = "1234567890";
        String plusFriendID = "@팝빌";
        String senderNum = "01011112222";
        String content = "친구톡 메시지 내용~~!";
        String altContent = "대체문자 내용";
        String altSendType = "";
        String receiverNum = "010111222";
        String receiverName = "02수신자명";
        String sndDT = "";
        Boolean adsYN = false;
        
        KakaoButton[] btns = new KakaoButton[2];
        KakaoButton button = new KakaoButton();
        button.setN("버튼명");
        button.setT("WL");
        button.setU1("http://www.popbill.com");
        button.setU2("http://test.popbill.com");
        btns[0] = button;
        
        button = new KakaoButton();
        button.setN("버튼명2");
        button.setT("WL");
        button.setU1("http://www.popbill.com");
        button.setU2("http://test.popbill.com");
        btns[1] = button;
        
        File file = new File("./test.jpg");
        String imageURL = "http://test.popbill.com";
        String receiptNum = kakaoService.sendFMS(testCorpNum, plusFriendID, senderNum, content, 
                altContent, altSendType, btns, receiverNum, receiverName, sndDT, adsYN, file, imageURL, 
                "testkorea");
        
        assertNotNull(receiptNum);
        System.out.println(receiptNum);
    }
    
    @Test
    public void sendFMS_03_TEST() throws PopbillException {
        String testCorpNum = "1234567890";
        String plusFriendID = "@팝빌";
        String senderNum = "01011112222";
        String content = "친구톡 메시지 내용~~!";
        String altContent = "대체문자 내용";
        String altSendType = "";
        String receiverNum = "010111222";
        String receiverName = "03수신자명";
        String sndDT = "";
        Boolean adsYN = false;
        
        KakaoButton[] btns = new KakaoButton[2];
        KakaoButton button = new KakaoButton();
        button.setN("버튼명");
        button.setT("WL");
        button.setU1("http://www.popbill.com");
        button.setU2("http://test.popbill.com");
        btns[0] = button;
        
        button = new KakaoButton();
        button.setN("버튼명2");
        button.setT("WL");
        button.setU1("http://www.popbill.com");
        button.setU2("http://test.popbill.com");
        btns[1] = button;
        
        File file = new File("./test.jpg");
        String imageURL = "http://test.popbill.com";
        String receiptNum = kakaoService.sendFMS(testCorpNum, plusFriendID, senderNum, content, 
                altContent, altSendType, btns, receiverNum, receiverName, sndDT, adsYN, file, imageURL, 
                "testkorea" , "20180724_fms_13");
        
        assertNotNull(receiptNum);
        System.out.println(receiptNum);
    }
    
    @Test
    public void sendFMS_04_TEST() throws PopbillException {
        String testCorpNum = "1234567890";
        String plusFriendID = "@팝빌";
        String senderNum = "01011112222";
        String content = "친구톡 메시지 내용~~!";
        String altSubject = "대체문자 제목";
        String altContent = "대체문자 내용";
        String altSendType = "";
        String receiverNum = "010111222";
        String receiverName = "03수신자명";
        String sndDT = "";
        Boolean adsYN = false;
        
        KakaoButton[] btns = new KakaoButton[2];
        KakaoButton button = new KakaoButton();
        button.setN("버튼명");
        button.setT("WL");
        button.setU1("http://www.popbill.com");
        button.setU2("http://test.popbill.com");
        btns[0] = button;
        
        button = new KakaoButton();
        button.setN("버튼명2");
        button.setT("WL");
        button.setU1("http://www.popbill.com");
        button.setU2("http://test.popbill.com");
        btns[1] = button;
        
        File file = new File("./test.jpg");
        String imageURL = "http://test.popbill.com";
        String receiptNum = kakaoService.sendFMS(testCorpNum, plusFriendID, senderNum, content, altSubject,
                altContent, altSendType, btns, receiverNum, receiverName, sndDT, adsYN, file, imageURL, 
                "testkorea" , "20180724_fms_13");
        
        assertNotNull(receiptNum);
        System.out.println(receiptNum);
    }
    
    @Test
    public void sendFMS_05_TEST() throws PopbillException {
        String testCorpNum = "1234567890";
        String plusFriendID = "@팝빌";
        String senderNum = "01011112222";
        String content = "친구톡 메시지 내용";
        String altContent = "대체문자 내용";
        String altSendType = "";
        String sndDT = "";
        Boolean adsYN = false;
        
        KakaoButton[] btns = new KakaoButton[2];
        KakaoButton button = new KakaoButton();
        button.setN("버튼명");
        button.setT("WL");
        button.setU1("http://www.popbill.com");
        button.setU2("http://test.popbill.com");
        btns[0] = button;
        
        button = new KakaoButton();
        button.setN("버튼명2");
        button.setT("WL");
        button.setU1("http://www.popbill.com");
        button.setU2("http://test.popbill.com");
        btns[1] = button;
        
        KakaoReceiver[] receivers = new KakaoReceiver[2];
        
        KakaoReceiver message = new KakaoReceiver();
        message.setReceiverNum("010111222");
        message.setReceiverName("04-1수신자명");
        receivers[0] = message;
        
        message = new KakaoReceiver();
        message.setReceiverNum("010222333");
        message.setReceiverName("04-2수신자명");
        receivers[1] = message;
        
        File file = new File("./test.jpg");
        String imageURL = "http://test.popbill.com";

        String receiptNum = kakaoService.sendFMS(testCorpNum, plusFriendID, senderNum, content, 
                altContent, altSendType, receivers, btns, sndDT, adsYN, file, imageURL);
        
        assertNotNull(receiptNum);

        System.out.println(receiptNum);
    }
    
    @Test
    public void sendFMS_06_TEST() throws PopbillException {
        String testCorpNum = "1234567890";
        String plusFriendID = "@팝빌";
        String senderNum = "01011112222";
        String content = "친구톡 메시지 내용";
        String altContent = "대체문자 내용";
        String altSendType = "";
        String sndDT = "";
        Boolean adsYN = false;
        
        KakaoButton[] btns = new KakaoButton[2];
        KakaoButton button = new KakaoButton();
        button.setN("버튼명");
        button.setT("WL");
        button.setU1("http://www.popbill.com");
        button.setU2("http://test.popbill.com");
        btns[0] = button;
        
        button = new KakaoButton();
        button.setN("버튼명2");
        button.setT("WL");
        button.setU1("http://www.popbill.com");
        button.setU2("http://test.popbill.com");
        btns[1] = button;
        
        KakaoReceiver[] receivers = new KakaoReceiver[2];
        
        KakaoReceiver message = new KakaoReceiver();
        message.setReceiverNum("010111222");
        message.setReceiverName("05-1수신자명");
        receivers[0] = message;
        
        message = new KakaoReceiver();
        message.setReceiverNum("010222333");
        message.setReceiverName("05-2수신자명");
        receivers[1] = message;
        
        File file = new File("./test.jpg");
        String imageURL = "http://test.popbill.com";

        String receiptNum = kakaoService.sendFMS(testCorpNum, plusFriendID, senderNum, content, 
                altContent, altSendType, receivers, btns, sndDT, adsYN, file, imageURL,
                "testkorea");
        
        assertNotNull(receiptNum);

        System.out.println(receiptNum);
    }
    
    @Test
    public void sendFMS_07_TEST() throws PopbillException {
        String testCorpNum = "1234567890";
        String plusFriendID = "@팝빌";
        String senderNum = "01011112222";
        String content = "친구톡 메시지 내용";
        String altContent = "대체문자 내용";
        String altSendType = "";
        String sndDT = "";
        Boolean adsYN = false;
        
        KakaoButton[] btns = new KakaoButton[2];
        KakaoButton button = new KakaoButton();
        button.setN("버튼명");
        button.setT("WL");
        button.setU1("http://www.popbill.com");
        button.setU2("http://test.popbill.com");
        btns[0] = button;
        
        button = new KakaoButton();
        button.setN("버튼명2");
        button.setT("WL");
        button.setU1("http://www.popbill.com");
        button.setU2("http://test.popbill.com");
        btns[1] = button;
        
        KakaoReceiver[] receivers = new KakaoReceiver[2];
        
        KakaoReceiver message = new KakaoReceiver();
        message.setReceiverNum("010111222");
        message.setReceiverName("06-1수신자명");
        receivers[0] = message;
        
        message = new KakaoReceiver();
        message.setReceiverNum("010222333");
        message.setReceiverName("06-2수신자명");
        receivers[1] = message;
        
        File file = new File("./test.jpg");
        String imageURL = "http://test.popbill.com";

        String receiptNum = kakaoService.sendFMS(testCorpNum, plusFriendID, senderNum, content, 
                altContent, altSendType, receivers, btns, sndDT, adsYN, file, imageURL,
                "testkorea", "20180724_fms_16");
        
        assertNotNull(receiptNum);

        System.out.println(receiptNum);
    }
    
    @Test
    public void sendFMS_08_TEST() throws PopbillException {
        String testCorpNum = "1234567890";
        String plusFriendID = "@팝빌";
        String senderNum = "01011112222";
        String content = "친구톡 메시지 내용";
        String altSubject = "대체문자 제목";
        String altContent = "대체문자 내용";
        String altSendType = "";
        String sndDT = "";
        Boolean adsYN = false;
        
        KakaoButton[] btns = new KakaoButton[2];
        KakaoButton button = new KakaoButton();
        button.setN("버튼명");
        button.setT("WL");
        button.setU1("http://www.popbill.com");
        button.setU2("http://test.popbill.com");
        btns[0] = button;
        
        button = new KakaoButton();
        button.setN("버튼명2");
        button.setT("WL");
        button.setU1("http://www.popbill.com");
        button.setU2("http://test.popbill.com");
        btns[1] = button;
        
        KakaoReceiver[] receivers = new KakaoReceiver[2];
        
        KakaoReceiver message = new KakaoReceiver();
        message.setReceiverNum("010111222");
        message.setReceiverName("06-1수신자명");
        receivers[0] = message;
        
        message = new KakaoReceiver();
        message.setReceiverNum("010222333");
        message.setReceiverName("06-2수신자명");
        receivers[1] = message;
        
        File file = new File("./test.jpg");
        String imageURL = "http://test.popbill.com";

        String receiptNum = kakaoService.sendFMS(testCorpNum, plusFriendID, senderNum, content, 
                altSubject, altContent, altSendType, receivers, btns, sndDT, adsYN, file, imageURL,
                "testkorea", "20180724_fms_16");
        
        assertNotNull(receiptNum);

        System.out.println(receiptNum);
    }
    
    @Test
    public void sendFMS_09_TEST() throws PopbillException {
        String testCorpNum = "1234567890";
        String plusFriendID = "@팝빌";
        String senderNum = "01011112222";
        String altSendType = "";
        String sndDT = "";
        Boolean adsYN = true;
        
        KakaoButton[] btns = new KakaoButton[2];
        KakaoButton button = new KakaoButton();
        button.setN("버튼명");
        button.setT("WL");
        button.setU1("http://www.popbill.com");
        button.setU2("http://test.popbill.com");
        btns[0] = button;
        
        button = new KakaoButton();
        button.setN("버튼명2");
        button.setT("WL");
        button.setU1("http://www.popbill.com");
        button.setU2("http://test.popbill.com");
        btns[1] = button;
        
        KakaoReceiver[] receivers = new KakaoReceiver[2];
        
        KakaoReceiver message = new KakaoReceiver();
        message.setReceiverNum("010111222");
        message.setReceiverName("07-1수신자명");
        message.setMessage("[테스트] 테스트 템플릿입니다요1");
        message.setAltMessage("대체문자 내용");
        receivers[0] = message;
        
        message = new KakaoReceiver();
        message.setReceiverNum("010333444");
        message.setReceiverName("07-2수신자명");
        message.setMessage("[테스트] 테스트 템플릿입니다요2");
        message.setAltMessage("대체문자 내용");
        receivers[1] = message;
        
        File file = new File("./test.jpg");
        String imageURL = "http://test.popbill.com";
        String receiptNum = kakaoService.sendFMS(testCorpNum, plusFriendID, senderNum, altSendType, 
                receivers, btns, sndDT, adsYN, file, imageURL);
        
        assertNotNull(receiptNum);
        System.out.println(receiptNum);
    }
    
    @Test
    public void sendFMS_10_TEST() throws PopbillException {
        String testCorpNum = "1234567890";
        String plusFriendID = "@팝빌";
        String senderNum = "01011112222";
        String altSendType = "";
        String sndDT = "";
        Boolean adsYN = true;
        
        KakaoButton[] btns = new KakaoButton[2];
        KakaoButton button = new KakaoButton();
        button.setN("버튼명");
        button.setT("WL");
        button.setU1("http://www.popbill.com");
        button.setU2("http://test.popbill.com");
        btns[0] = button;
        
        button = new KakaoButton();
        button.setN("버튼명2");
        button.setT("WL");
        button.setU1("http://www.popbill.com");
        button.setU2("http://test.popbill.com");
        btns[1] = button;
        
        KakaoReceiver[] receivers = new KakaoReceiver[2];
        
        KakaoReceiver message = new KakaoReceiver();
        message.setReceiverNum("010111222");
        message.setReceiverName("08-1수신자명");
        message.setMessage("[테스트] 테스트 템플릿입니다요1");
        message.setAltMessage("대체문자 내용");
        receivers[0] = message;
        
        message = new KakaoReceiver();
        message.setReceiverNum("010333444");
        message.setReceiverName("08-2수신자명");
        message.setMessage("[테스트] 테스트 템플릿입니다요2");
        message.setAltMessage("대체문자 내용");
        receivers[1] = message;
        
        File file = new File("./test.jpg");
        String imageURL = "http://test.popbill.com";
        String receiptNum = kakaoService.sendFMS(testCorpNum, plusFriendID, senderNum, altSendType, 
                receivers, btns, sndDT, adsYN, file, imageURL, "testkorea");
        
        assertNotNull(receiptNum);
        System.out.println(receiptNum);
    }
    
    @Test
    public void sendFMS_11_TEST() throws PopbillException {
        String testCorpNum = "1234567890";
        String plusFriendID = "@팝빌";
        String senderNum = "01011112222";
        String altSendType = "";
        String sndDT = "";
        Boolean adsYN = true;
        
        KakaoButton[] btns = new KakaoButton[2];
        KakaoButton button = new KakaoButton();
        button.setN("버튼명");
        button.setT("WL");
        button.setU1("http://www.popbill.com");
        button.setU2("http://test.popbill.com");
        btns[0] = button;
        
        button = new KakaoButton();
        button.setN("버튼명2");
        button.setT("WL");
        button.setU1("http://www.popbill.com");
        button.setU2("http://test.popbill.com");
        btns[1] = button;
        
        KakaoReceiver[] receivers = new KakaoReceiver[2];
        
        KakaoReceiver message = new KakaoReceiver();
        message.setReceiverNum("010111222");
        message.setReceiverName("09-1수신자명");
        message.setMessage("[테스트] 테스트 템플릿입니다요1");
        message.setAltMessage("대체문자 내용");
        receivers[0] = message;
        
        message = new KakaoReceiver();
        message.setReceiverNum("010333444");
        message.setReceiverName("09-2수신자명");
        message.setMessage("[테스트] 테스트 템플릿입니다요2");
        message.setAltMessage("대체문자 내용");
        receivers[1] = message;
        
        File file = new File("./test.jpg");
        String imageURL = "http://test.popbill.com";
        String receiptNum = kakaoService.sendFMS(testCorpNum, plusFriendID, senderNum, altSendType, 
                receivers, btns, sndDT, adsYN, file, imageURL, "testkorea", "20180724_fms_19");
        
        assertNotNull(receiptNum);
        System.out.println(receiptNum);
    }
    
    
    
    @Test
    public void sendATS_Single_TEST() throws PopbillException {
        String testCorpNum = "1234567890";
        
        String templateCode = "018020000001";
        
        String senderNum = "07043042993";
        
        String content = "[테스트] 테스트 템플릿입니다.d";
                
        String altContent = "대체문자 내용";
        
        String altSendType = "";
        
        String receiverNum = "01022223333";
        
        String receiverName = "수신자명";
        
        String sndDT = "";
                
        
        String receiptNum = kakaoService.sendATS(testCorpNum, templateCode, senderNum, content, altContent, altSendType, 
                receiverNum, receiverName, sndDT);
        
        assertNotNull(receiptNum);
        
        System.out.println(receiptNum);
    }    
    
    @Test
    public void sendATS_same_TEST() throws PopbillException {
        String testCorpNum = "1234567890";
        
        String templateCode = "018020000001";
        
        String senderNum = "07043042993";
        
        String content = "[테스트] 테스트 템플릿입니다";
                
        String altContent = "대체문자 내용";
        
        String altSendType = "";
        
        KakaoReceiver[] receivers = new KakaoReceiver[2];
        
        KakaoReceiver message = new KakaoReceiver();
        message.setReceiverNum("01022223333");
        message.setReceiverName("수신자명");
        receivers[0] = message;
        receivers[1] = message;
        
        String sndDT = "";
        
        String receiptNum = kakaoService.sendATS(testCorpNum, templateCode, senderNum, content, altContent, altSendType, receivers, sndDT);
        
        assertNotNull(receiptNum);
        
        System.out.println(receiptNum);
    }
    
    @Test
    public void sendATS_multi_TEST() throws PopbillException {
        String testCorpNum = "1234567890";
        
        String templateCode = "018020000001";
        
        String senderNum = "07043042993";
        
        String altSendType = "";
        
        KakaoReceiver[] receivers = new KakaoReceiver[2];
        
        KakaoReceiver message = new KakaoReceiver();
        message.setReceiverNum("01022223333");
        message.setReceiverName("수신자명");
        message.setMessage("[테스트] 테스트 템플릿입니다");
        message.setAltMessage("대체문자 내용");
        receivers[0] = message;
        
        message = new KakaoReceiver();
        message.setReceiverNum("01022223333");
        message.setReceiverName("수신자명");
        message.setMessage("[테스트] 테스트 템플릿입니다요");
        message.setAltMessage("대체문자 내용");
        receivers[1] = message;
        
        String sndDT = "";
        
        String receiptNum = kakaoService.sendATS(testCorpNum, templateCode, senderNum, altSendType, receivers, sndDT);
        
        assertNotNull(receiptNum);
        
        System.out.println(receiptNum);
    }
    
    @Test
    public void sendFTS_single_TEST() throws PopbillException {
        String testCorpNum = "1234567890";
        String plusFriendID = "@팝빌";
        String senderNum = "07043042993";
        String content = "친구톡 메시지 내용";
        String altContent = "대체문자 내용";
        String altSendType = "";
        String receiverNum = "01022223333";
        String receiverName = "수신자명";
        String sndDT = "";
        Boolean adsYN = false;
        
        KakaoButton[] btns = new KakaoButton[2];
        KakaoButton button = new KakaoButton();
        button.setN("버튼명");
        button.setT("WL");
        button.setU1("http://www.popbill.com");
        button.setU2("http://test.popbill.com");
        btns[0] = button;
        
        button = new KakaoButton();
        button.setN("버튼명2");
        button.setT("WL");
        button.setU1("http://www.popbill.com");
        button.setU2("http://test.popbill.com");
        btns[1] = button;
        
        String receiptNum = kakaoService.sendFTS(testCorpNum, plusFriendID, senderNum, content, 
                altContent, altSendType, btns, receiverNum, receiverName, sndDT, adsYN);
        
        assertNotNull(receiptNum);
        
        System.out.println(receiptNum);
    }
    

    
    @Test
    public void sendFTS_same_TEST() throws PopbillException {
        String testCorpNum = "1234567890";
        String plusFriendID = "@팝빌";
        String senderNum = "07043042993";
        String content = "친구톡 메시지 내용";
        String altContent = "대체문자 내용";
        String altSendType = "";
        String sndDT = "";
        Boolean adsYN = false;
        
        KakaoButton[] btns = new KakaoButton[2];
        KakaoButton button = new KakaoButton();
        button.setN("버튼명");
        button.setT("WL");
        button.setU1("http://www.popbill.com");
        button.setU2("http://test.popbill.com");
        btns[0] = button;
        
        button = new KakaoButton();
        button.setN("버튼명2");
        button.setT("WL");
        button.setU1("http://www.popbill.com");
        button.setU2("http://test.popbill.com");
        btns[1] = button;
        
        KakaoReceiver[] receivers = new KakaoReceiver[2];
        
        KakaoReceiver message = new KakaoReceiver();
        message.setReceiverNum("01022223333");
        message.setReceiverName("수신자명");
        receivers[0] = message;
        
        message = new KakaoReceiver();
        message.setReceiverNum("01022223333");
        message.setReceiverName("수신자명");
        receivers[1] = message;

        String receiptNum = kakaoService.sendFTS(testCorpNum, plusFriendID, senderNum, content, 
                altContent, altSendType, receivers, btns, sndDT, adsYN);
        
        assertNotNull(receiptNum);
        
        System.out.println(receiptNum);
    }
    
    @Test
    public void sendFTS_multi_TEST() throws PopbillException {
        String testCorpNum = "1234567890";
        String plusFriendID = "@팝빌";
        String senderNum = "07043042993";
        String altSendType = "";
        String sndDT = "";
        Boolean adsYN = false;
        
        KakaoButton[] btns = new KakaoButton[2];
        KakaoButton button = new KakaoButton();
        button.setN("버튼명");
        button.setT("WL");
        button.setU1("http://www.popbill.com");
        button.setU2("http://test.popbill.com");
        btns[0] = button;
        
        button = new KakaoButton();
        button.setN("버튼명2");
        button.setT("WL");
        button.setU1("http://www.popbill.com");
        button.setU2("http://test.popbill.com");
        btns[1] = button;
        
        KakaoReceiver[] receivers = new KakaoReceiver[2];
        
        KakaoReceiver message = new KakaoReceiver();
        message.setReceiverNum("01022223333");
        message.setReceiverName("수신자명");
        message.setMessage("[테스트] 테스트 템플릿입니다요1");
        message.setAltMessage("대체문자 내용");
        receivers[0] = message;
        
        message = new KakaoReceiver();
        message.setReceiverNum("01022223333");
        message.setReceiverName("수신자명");
        message.setMessage("[테스트] 테스트 템플릿입니다요2");
        message.setAltMessage("대체문자 내용");
        receivers[1] = message;

        String receiptNum = kakaoService.sendFTS(testCorpNum, plusFriendID, senderNum, altSendType, 
                receivers, btns, sndDT, adsYN);
        
        assertNotNull(receiptNum);
        
        System.out.println(receiptNum);
    }
    
    @Test
    public void sendFMS_single_TEST() throws PopbillException {
        String testCorpNum = "1234567890";
        String plusFriendID = "@팝빌";
        String senderNum = "07043042993";
        String content = "친구톡 메시지 내용";
        String altContent = "대체문자 내용";
        String altSendType = "";
        String receiverNum = "01022223333";
        String receiverName = "수신자명";
        String sndDT = "";
        Boolean adsYN = false;
        
        KakaoButton[] btns = new KakaoButton[2];
        KakaoButton button = new KakaoButton();
        button.setN("버튼명");
        button.setT("WL");
        button.setU1("http://www.popbill.com");
        button.setU2("http://test.popbill.com");
        btns[0] = button;
        
        button = new KakaoButton();
        button.setN("버튼명2");
        button.setT("WL");
        button.setU1("http://www.popbill.com");
        button.setU2("http://test.popbill.com");
        btns[1] = button;
        
        File file = new File("/Users/John/Desktop/tmp/test03.jpg");
        
        String imageURL = "http://test.popbill.com";
        
        String receiptNum = kakaoService.sendFMS(testCorpNum, plusFriendID, senderNum, content, 
                altContent, altSendType, btns, receiverNum, receiverName, sndDT, adsYN, file, imageURL);
        
        assertNotNull(receiptNum);
        
        System.out.println(receiptNum);
    }
    
    @Test
    public void sendFMS_same_TEST() throws PopbillException {
        String testCorpNum = "1234567890";
        String plusFriendID = "@팝빌";
        String senderNum = "07043042993";
        String content = "친구톡 메시지 내용";
        String altContent = "대체문자 내용";
        String altSendType = "";
        String sndDT = "";
        Boolean adsYN = false;
        
        KakaoButton[] btns = new KakaoButton[2];
        KakaoButton button = new KakaoButton();
        button.setN("버튼명");
        button.setT("WL");
        button.setU1("http://www.popbill.com");
        button.setU2("http://test.popbill.com");
        btns[0] = button;
        
        button = new KakaoButton();
        button.setN("버튼명2");
        button.setT("WL");
        button.setU1("http://www.popbill.com");
        button.setU2("http://test.popbill.com");
        btns[1] = button;
        
        KakaoReceiver[] receivers = new KakaoReceiver[2];
        
        KakaoReceiver message = new KakaoReceiver();
        message.setReceiverNum("01022223333");
        message.setReceiverName("수신자명");
        receivers[0] = message;
        
        message = new KakaoReceiver();
        message.setReceiverNum("01022223333");
        message.setReceiverName("수신자명");
        receivers[1] = message;
        
        File file = new File("/Users/John/Desktop/tmp/test03.jpg");
        
        String imageURL = "http://test.popbill.com";

        String receiptNum = kakaoService.sendFMS(testCorpNum, plusFriendID, senderNum, content, 
                altContent, altSendType, receivers, btns, sndDT, adsYN, file, imageURL);
        
        assertNotNull(receiptNum);

        System.out.println(receiptNum);
    }
    
    @Test
    public void sendFMS_multi_TEST() throws PopbillException {
        String testCorpNum = "1234567890";
        String plusFriendID = "@팝빌";
        String senderNum = "07043042993";
        String altSendType = "";
        String sndDT = "20180305200000";
        Boolean adsYN = true;
        
        KakaoButton[] btns = new KakaoButton[2];
        KakaoButton button = new KakaoButton();
        button.setN("버튼명");
        button.setT("WL");
        button.setU1("http://www.popbill.com");
        button.setU2("http://test.popbill.com");
        btns[0] = button;
        
        button = new KakaoButton();
        button.setN("버튼명2");
        button.setT("WL");
        button.setU1("http://www.popbill.com");
        button.setU2("http://test.popbill.com");
        btns[1] = button;
        
        KakaoReceiver[] receivers = new KakaoReceiver[2];
        
        KakaoReceiver message = new KakaoReceiver();
        message.setReceiverNum("01022223333");
        message.setReceiverName("수신자명");
        message.setMessage("[테스트] 테스트 템플릿입니다요1");
        message.setAltMessage("대체문자 내용");
        receivers[0] = message;
        
        message = new KakaoReceiver();
        message.setReceiverNum("01022223333");
        message.setReceiverName("수신자명");
        message.setMessage("[테스트] 테스트 템플릿입니다요2");
        message.setAltMessage("대체문자 내용");
        receivers[1] = message;
        
        File file = new File("/Users/John/Desktop/tmp/test03.jpg");
        String imageURL = "http://test.popbill.com";
        String receiptNum = kakaoService.sendFMS(testCorpNum, plusFriendID, senderNum, altSendType, 
                receivers, btns, sndDT, adsYN, file, imageURL);
        
        assertNotNull(receiptNum);
        System.out.println(receiptNum);
    }
    
    @Test
    public void cancelReserve_TEST() throws PopbillException {
        String receiptNum = "018072410120300001";
        
        Response response = kakaoService.cancelReserve("1234567890", receiptNum);
        assertNotNull(response);
        
        System.out.println(response.getMessage());
    }
    
    @Test
    public void cancelReservebyRCV_TEST() throws PopbillException {
    	String receiptNum = "023011114473900001";
        String receiveNum = "01011112222";
        
        Response response = kakaoService.cancelReservebyRCV("1234567890", receiptNum, receiveNum);
        assertNotNull(response);
        
        System.out.println(response.getMessage());
    }
    
    @Test
    public void cancelReserveRNbyRCV_TEST() throws PopbillException {
    	String requestNum = "20230111_ats_23";
        String receiveNum = "01022223333";
        
        Response response = kakaoService.cancelReserveRNbyRCV("1234567890", requestNum, receiveNum);
        assertNotNull(response);
        
        System.out.println(response.getMessage());
    }
    
    @Test
    public void cancelReserveRN_TEST() throws PopbillException {
        
        String requestNum = "20180724_fms_19";
        
        Response response = kakaoService.cancelReserveRN("1234567890", requestNum);
        assertNotNull(response);
        
        System.out.println(response.getMessage());
    }
    
    @Test
    public void getMessage_TEST() throws PopbillException {
        
        String receiptNum = "020020314162900001";
        
        KakaoSentInfo result = kakaoService.getMessages("1234567890", receiptNum);
        
        assertNotNull(result);
        
        System.out.println("contentType : " + result.getContentType());
        System.out.println("templateCode : " + result.getTemplateCode());
        System.out.println("plusFriendID : " + result.getPlusFriendID());
        System.out.println("sendNum : " + result.getSendNum());
        System.out.println("altSubject : " + result.getAltSubject());
        System.out.println("altContent : " + result.getAltContent());
        System.out.println("altSendType : " + result.getAltSendType());
        System.out.println("reserveDT : " + result.getReserveDT());
        System.out.println("adsYN : " + result.getAdsYN());
        System.out.println("imageURL : " + result.getImageURL());
        System.out.println("sendCnt : " + result.getSendCnt());
        System.out.println("successCnte : " + result.getSuccessCnt());
        System.out.println("failCnt : " + result.getFailCnt());
        System.out.println("altCnt : " + result.getAltCnt());
        System.out.println("cancelCnt : " + result.getCancelCnt());
        
        for (int i=0; i<result.getMsgs().size(); i++)
        {
            
            System.out.println("state : " + result.getMsgs().get(i).getState());
            System.out.println("sendDT : " + result.getMsgs().get(i).getSendDT());
            System.out.println("receiveNum : " + result.getMsgs().get(i).getReceiveNum());
            System.out.println("receiveName : " + result.getMsgs().get(i).getReceiveName());
            System.out.println("content : " + result.getMsgs().get(i).getContent());
            System.out.println("result : " + result.getMsgs().get(i).getResult());
            System.out.println("resultDT : " + result.getMsgs().get(i).getResultDT());
            System.out.println("altSubject : " + result.getMsgs().get(i).getAltSubject());
            System.out.println("altContent : " + result.getMsgs().get(i).getAltContent());
            System.out.println("altContentType : " + result.getMsgs().get(i).getAltContentType());
            System.out.println("altSendDT : " + result.getMsgs().get(i).getAltSendDT());
            System.out.println("altResult : " + result.getMsgs().get(i).getAltResult());
            System.out.println("altResultDT : " + result.getMsgs().get(i).getAltResultDT());
            System.out.println("receiptNum : " + result.getMsgs().get(i).getReceiptNum());
            System.out.println("requestNum : " + result.getMsgs().get(i).getRequestNum());
            System.out.println("interOPRefKey : " + result.getMsgs().get(i).getInterOPRefKey());
        }
    }
    
    @Test
    public void getMessageRN_TEST() throws PopbillException {
        
        String requestNum = "20230111_ats_17";
        
        KakaoSentInfo result = kakaoService.getMessagesRN("1234567890", requestNum);
        
        assertNotNull(result);
        
        System.out.println("contentType : " + result.getContentType());
        System.out.println("templateCode : " + result.getTemplateCode());
        System.out.println("plusFriendID : " + result.getPlusFriendID());
        System.out.println("sendNum : " + result.getSendNum());
        System.out.println("altSubject : " + result.getAltSubject());
        System.out.println("altContent : " + result.getAltContent());
        System.out.println("altSendType : " + result.getAltSendType());
        System.out.println("reserveDT : " + result.getReserveDT());
        System.out.println("adsYN : " + result.getAdsYN());
        System.out.println("imageURL : " + result.getImageURL());
        System.out.println("sendCnt : " + result.getSendCnt());
        System.out.println("successCnte : " + result.getSuccessCnt());
        System.out.println("failCnt : " + result.getFailCnt());
        System.out.println("altCnt : " + result.getAltCnt());
        System.out.println("cancelCnt : " + result.getCancelCnt());
        
        for (int i=0; i<result.getMsgs().size(); i++)
        {
            System.out.println("state : " + result.getMsgs().get(i).getState());
            System.out.println("sendDT : " + result.getMsgs().get(i).getSendDT());
            System.out.println("receiveNum : " + result.getMsgs().get(i).getReceiveNum());
            System.out.println("receiveName : " + result.getMsgs().get(i).getReceiveName());
            System.out.println("content : " + result.getMsgs().get(i).getContent());
            System.out.println("result : " + result.getMsgs().get(i).getResult());
            System.out.println("resultDT : " + result.getMsgs().get(i).getResultDT());
            System.out.println("altSubject : " + result.getMsgs().get(i).getAltSubject());
            System.out.println("altContent : " + result.getMsgs().get(i).getAltContent());
            System.out.println("altContentType : " + result.getMsgs().get(i).getAltContentType());
            System.out.println("altSendDT : " + result.getMsgs().get(i).getAltSendDT());
            System.out.println("altResult : " + result.getMsgs().get(i).getAltResult());
            System.out.println("altResultDT : " + result.getMsgs().get(i).getAltResultDT());
            System.out.println("receiptNum : " + result.getMsgs().get(i).getReceiptNum());
            System.out.println("requestNum : " + result.getMsgs().get(i).getRequestNum());
        }
    }

    
    @Test 
    public void search_TEST() throws PopbillException {
        
        String SDate = "20220111";
        String EDate = "20220206";
        String[] State = {"0", "1","2","3","4", "5"};
        String[] Item = {"ATS", "FTS", "FMS"};
        String ReserveYN = "";
        Boolean SenderYN = false;
        int Page = 1;
        int PerPage = 3;
        String Order = "D";
        String QString = "지헌 테스트";
        
        KakaoSearchResult result = kakaoService.search("1234567890", SDate, EDate, State, Item, ReserveYN, SenderYN, Page, PerPage, Order, "testkorea", QString);
        
        assertNotNull(result);
        
        System.out.println(result.getTotal());
        
        System.out.println("code : " + result.getCode());
        System.out.println("message : " + result.getMessage());
        System.out.println("total : " + result.getTotal());
        System.out.println("perPage : " + result.getPerPage());
        System.out.println("pageNum : " + result.getPageNum());
        System.out.println("pageCount : " + result.getPageCount());
                
        
        for (int i=0; i<result.getList().size(); i++)
        {
            System.out.println("****************************************************");
            System.out.println("state : " + result.getList().get(i).getState());
            System.out.println("sendDT : " + result.getList().get(i).getSendDT());
            System.out.println("receiveNum : " + result.getList().get(i).getReceiveNum());
            System.out.println("receiveName : " + result.getList().get(i).getReceiveName());
            System.out.println("content : " + result.getList().get(i).getContent());
            System.out.println("result : " + result.getList().get(i).getResult());
            System.out.println("resultDT : " + result.getList().get(i).getResultDT());
            System.out.println("altSubject : " + result.getList().get(i).getAltSubject());
            System.out.println("altContent : " + result.getList().get(i).getAltContent());
            System.out.println("altContentType : " + result.getList().get(i).getAltContentType());
            System.out.println("altSendDT : " + result.getList().get(i).getAltSendDT());
            System.out.println("altResult : " + result.getList().get(i).getAltResult());
            System.out.println("altResultDT : " + result.getList().get(i).getAltResultDT());
            System.out.println("reserveDT : " + result.getList().get(i).getReserveDT());
        }
        
    }


    @Test
    public void GetSentListURL_TEST() throws PopbillException {

        String url = kakaoService.getSentListURL("1234567890","testkorea");

        System.out.println(url);
    }

    @Test
    public void getURL_TEST() throws PopbillException {
        
        String url =  kakaoService.getURL("1234567890", "PLUSFRIEND", "testkorea");
    
        assertNotNull(url);
        System.out.println(url);
    }

    @Test
    public void getUnitCost_TEST() throws PopbillException {
        float UnitCost = kakaoService.getUnitCost("1234567890",KakaoType.FMS);
        System.out.println(UnitCost);
    }

    @Test
    public void getChargeInfo_TEST() throws PopbillException {
        
        ChargeInfo chrgInfo = kakaoService.getChargeInfo("1234567890", KakaoType.FMS);
        
        System.out.println(chrgInfo.getChargeMethod());
        System.out.println(chrgInfo.getRateSystem());
        System.out.println(chrgInfo.getUnitCost());
    }
}

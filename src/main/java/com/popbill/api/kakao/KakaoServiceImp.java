package com.popbill.api.kakao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.popbill.api.AttachFile;
import com.popbill.api.BaseServiceImp;
import com.popbill.api.ChargeInfo;
import com.popbill.api.KakaoService;
import com.popbill.api.PopbillException;
import com.popbill.api.Response;
import com.popbill.api.util.ValidationUtils;

public class KakaoServiceImp extends BaseServiceImp implements KakaoService {

    @Override
    protected List<String> getScopes() {

        return Arrays.asList("153", "154", "155");
    }

    @Override
    public String getPlusFriendMgtURL(String CorpNum, String UserID) throws PopbillException {

        URLResponse response = httpget("/KakaoTalk/?TG=PLUSFRIEND", CorpNum, UserID, URLResponse.class);

        return response.url;
    }

    @Override
    public PlusFriendID[] listPlusFriendID(String CorpNum) throws PopbillException {
        return listPlusFriendID(CorpNum, null);
    }

    @Override
    public PlusFriendID[] listPlusFriendID(String CorpNum, String UserID) throws PopbillException {
        return httpget("/KakaoTalk/ListPlusFriendID", CorpNum, UserID, PlusFriendID[].class);
    }

    @Override
    public Response checkSenderNumber(String CorpNum, String SenderNumber) throws PopbillException {
        return checkSenderNumber(CorpNum, SenderNumber, null);
    }

    @Override
    public Response checkSenderNumber(String CorpNum, String SenderNumber, String UserID) throws PopbillException {
        if (SenderNumber == null || SenderNumber.isEmpty())
            throw new PopbillException(-99999999, "발신번호가 입력되지 않았습니다.");

        return httpget("/KakaoTalk/CheckSenderNumber/" + SenderNumber, CorpNum, UserID, Response.class);
    }

    @Override
    public String getSenderNumberMgtURL(String CorpNum, String UserID) throws PopbillException {

        URLResponse response = httpget("/Message/?TG=SENDER", CorpNum, UserID, URLResponse.class);

        return response.url;
    }

    @Override
    public SenderNumber[] getSenderNumberList(String CorpNum) throws PopbillException {
        return getSenderNumberList(CorpNum, null);
    }

    @Override
    public SenderNumber[] getSenderNumberList(String CorpNum, String UserID) throws PopbillException {
        return httpget("/Message/SenderNumber", CorpNum, null, SenderNumber[].class);
    }

    @Override
    public String getATSTemplateMgtURL(String CorpNum, String UserID) throws PopbillException {

        URLResponse response = httpget("/KakaoTalk/?TG=TEMPLATE", CorpNum, UserID, URLResponse.class);

        return response.url;
    }

    @Override
    public ATSTemplate getATSTemplate(String CorpNum, String templateCode) throws PopbillException {
        return getATSTemplate(CorpNum, templateCode, null);
    }

    @Override
    public ATSTemplate getATSTemplate(String CorpNum, String templateCode, String UserID) throws PopbillException {
        if (templateCode == null || templateCode.isEmpty())
            throw new PopbillException(-99999999, "알림톡 템플릿코드(templateCode)가 입력되지 않았습니다.");

        return httpget("/KakaoTalk/GetATSTemplate/" + templateCode, CorpNum, UserID, ATSTemplate.class);
    }

    @Override
    public ATSTemplate[] listATSTemplate(String CorpNum) throws PopbillException {
        return listATSTemplate(CorpNum, null);
    }

    @Override
    public ATSTemplate[] listATSTemplate(String CorpNum, String UserID) throws PopbillException {
        return httpget("/KakaoTalk/ListATSTemplate", CorpNum, UserID, ATSTemplate[].class);
    }

    /******************
     *  알림톡 단건 전송
     ******************/
    @Override
    public String sendATS(String CorpNum, String templateCode, String senderNum, String content, String altContent, String altSendType,
                          String receiverNum, String receiverName, String sndDT) throws PopbillException {
        KakaoReceiver receiver = new KakaoReceiver();
        receiver.setReceiverNum(receiverNum);
        receiver.setReceiverName(receiverName);
        receiver.setMessage(content);
        receiver.setAltMessage(altContent);

        return sendATS(CorpNum, templateCode, senderNum, null, null, null, altSendType, new KakaoReceiver[]{receiver}, sndDT, null, null, null);
    }

    @Override
    public String sendATS(String CorpNum, String templateCode, String senderNum, String content, String altContent, String altSendType,
                          String receiverNum, String receiverName, String sndDT, String UserID) throws PopbillException {
        KakaoReceiver receiver = new KakaoReceiver();
        receiver.setReceiverNum(receiverNum);
        receiver.setReceiverName(receiverName);
        receiver.setMessage(content);
        receiver.setAltMessage(altContent);

        return sendATS(CorpNum, templateCode, senderNum, null, null, null, altSendType, new KakaoReceiver[]{receiver}, sndDT, UserID, null, null);
    }

    @Override
    public String sendATS(String CorpNum, String templateCode, String senderNum, String content, String altContent, String altSendType,
                          String receiverNum, String receiverName, String sndDT, String UserID, String requestNum) throws PopbillException {
        KakaoReceiver receiver = new KakaoReceiver();
        receiver.setReceiverNum(receiverNum);
        receiver.setReceiverName(receiverName);
        receiver.setMessage(content);
        receiver.setAltMessage(altContent);

        return sendATS(CorpNum, templateCode, senderNum, null, null, null, altSendType, new KakaoReceiver[]{receiver}, sndDT, UserID, requestNum, null);
    }

    @Override
    public String sendATS(String CorpNum, String templateCode, String senderNum, String content, String altSubject, String altContent,
                          String altSendType, String receiverNum, String receiverName, String sndDT, String UserID, String requestNum) throws PopbillException {
        KakaoReceiver receiver = new KakaoReceiver();
        receiver.setReceiverNum(receiverNum);
        receiver.setReceiverName(receiverName);
        receiver.setMessage(content);
        receiver.setAltSubject(altSubject);
        receiver.setAltMessage(altContent);

        return sendATS(CorpNum, templateCode, senderNum, null, null, null, altSendType, new KakaoReceiver[]{receiver}, sndDT, UserID, requestNum, null);
    }

    /********************
     * 알림톡 단건전송 버튼추가.
     ********************/

    @Override
    public String sendATS(String CorpNum, String templateCode, String senderNum, String content, String altContent, String altSendType,
                          String receiverNum, String receiverName, String sndDT, KakaoButton[] Buttons) throws PopbillException {
        KakaoReceiver receiver = new KakaoReceiver();
        receiver.setReceiverNum(receiverNum);
        receiver.setReceiverName(receiverName);
        receiver.setMessage(content);
        receiver.setAltMessage(altContent);

        return sendATS(CorpNum, templateCode, senderNum, null, null, null, altSendType, new KakaoReceiver[]{receiver}, sndDT, null, null, Buttons);
    }

    @Override
    public String sendATS(String CorpNum, String templateCode, String senderNum, String content, String altContent, String altSendType,
                          String receiverNum, String receiverName, String sndDT, String UserID, KakaoButton[] Buttons) throws PopbillException {
        KakaoReceiver receiver = new KakaoReceiver();
        receiver.setReceiverNum(receiverNum);
        receiver.setReceiverName(receiverName);
        receiver.setMessage(content);
        receiver.setAltMessage(altContent);

        return sendATS(CorpNum, templateCode, senderNum, null, null, null, altSendType, new KakaoReceiver[]{receiver}, sndDT, UserID, null, Buttons);
    }

    @Override
    public String sendATS(String CorpNum, String templateCode, String senderNum, String content, String altContent, String altSendType,
                          String receiverNum, String receiverName, String sndDT, String UserID, String requestNum, KakaoButton[] Buttons) throws PopbillException {
        KakaoReceiver receiver = new KakaoReceiver();
        receiver.setReceiverNum(receiverNum);
        receiver.setReceiverName(receiverName);
        receiver.setMessage(content);
        receiver.setAltMessage(altContent);

        return sendATS(CorpNum, templateCode, senderNum, null, null, null, altSendType, new KakaoReceiver[]{receiver}, sndDT, UserID, requestNum,
                Buttons);
    }

    @Override
    public String sendATS(String CorpNum, String templateCode, String senderNum, String content, String altSubject, String altContent, String altSendType,
                          String receiverNum, String receiverName, String sndDT, String UserID, String requestNum, KakaoButton[] Buttons) throws PopbillException {
        KakaoReceiver receiver = new KakaoReceiver();
        receiver.setReceiverNum(receiverNum);
        receiver.setReceiverName(receiverName);
        receiver.setMessage(content);
        receiver.setAltSubject(altSubject);
        receiver.setAltMessage(altContent);

        return sendATS(CorpNum, templateCode, senderNum, null, null, null, altSendType, new KakaoReceiver[]{receiver}, sndDT, UserID, requestNum,
                Buttons);
    }

    /********************
     * 알림톡 개별내용 대량전송
     ********************/

    @Override
    public String sendATS(String CorpNum, String templateCode, String senderNum, String altSendType, KakaoReceiver[] Receivers, String sndDT)
            throws PopbillException {
        return sendATS(CorpNum, templateCode, senderNum, null, null, null, altSendType, Receivers, sndDT, null, null, null);
    }

    @Override
    public String sendATS(String CorpNum, String templateCode, String senderNum, String altSendType, KakaoReceiver[] Receivers, String sndDT,
                          String UserID) throws PopbillException {
        return sendATS(CorpNum, templateCode, senderNum, null, null, null, altSendType, Receivers, sndDT, UserID, null, null);
    }

    @Override
    public String sendATS(String CorpNum, String templateCode, String senderNum, String altSendType, KakaoReceiver[] Receivers, String sndDT,
                          String UserID, String requestNum) throws PopbillException {
        return sendATS(CorpNum, templateCode, senderNum, null, null, null, altSendType, Receivers, sndDT, UserID, requestNum, null);
    }

    /*****************************
     * 알림톡 개별내용 대량전송 - 버튼추가
     *****************************/

    @Override
    public String sendATS(String CorpNum, String templateCode, String senderNum, String altSendType, KakaoReceiver[] Receivers, String sndDT,
                          KakaoButton[] Buttons) throws PopbillException {
        return sendATS(CorpNum, templateCode, senderNum, null, null, null, altSendType, Receivers, sndDT, null, null, Buttons);
    }

    @Override
    public String sendATS(String CorpNum, String templateCode, String senderNum, String altSendType, KakaoReceiver[] Receivers, String sndDT,
                          String UserID, KakaoButton[] Buttons) throws PopbillException {
        return sendATS(CorpNum, templateCode, senderNum, null, null, null, altSendType, Receivers, sndDT, UserID, null, Buttons);
    }

    @Override
    public String sendATS(String CorpNum, String templateCode, String senderNum, String altSendType, KakaoReceiver[] Receivers, String sndDT,
                          String UserID, String requestNum, KakaoButton[] Buttons) throws PopbillException {
        return sendATS(CorpNum, templateCode, senderNum, null, null, null, altSendType, Receivers, sndDT, UserID, requestNum, Buttons);
    }

    /********************
     * 알림톡 동일내용 대량전송
     ********************/

    @Override
    public String sendATS(String CorpNum, String templateCode, String senderNum, String content, String altContent, String altSendType,
                          KakaoReceiver[] Receivers, String sndDT) throws PopbillException {
        return sendATS(CorpNum, templateCode, senderNum, content, null, altContent, altSendType, Receivers, sndDT, null, null, null);
    }

    @Override
    public String sendATS(String CorpNum, String templateCode, String senderNum, String content, String altContent, String altSendType,
                          KakaoReceiver[] Receivers, String sndDT, String UserID) throws PopbillException {
        return sendATS(CorpNum, templateCode, senderNum, content, null, altContent, altSendType, Receivers, sndDT, UserID, null, null);
    }

    @Override
    public String sendATS(String CorpNum, String templateCode, String senderNum, String content, String altContent, String altSendType,
                          KakaoReceiver[] Receivers, String sndDT, String UserID, String requestNum) throws PopbillException {
        return sendATS(CorpNum, templateCode, senderNum, content, null, altContent, altSendType, Receivers, sndDT, UserID, requestNum, null);
    }

    @Override
    public String sendATS(String CorpNum, String templateCode, String senderNum, String content, String altSubject, String altContent,
                          String altSendType, KakaoReceiver[] Receivers, String sndDT, String UserID, String requestNum) throws PopbillException {
        return sendATS(CorpNum, templateCode, senderNum, content, altSubject, altContent, altSendType, Receivers, sndDT, UserID, requestNum, null);
    }

    /*****************************
     * 알림톡 동일내용 대량전송 - 버튼추가.
     *****************************/

    @Override
    public String sendATS(String CorpNum, String templateCode, String senderNum, String content, String altContent, String altSendType,
                          KakaoReceiver[] Receivers, String sndDT, KakaoButton[] Buttons) throws PopbillException {

        return sendATS(CorpNum, templateCode, senderNum, content, null, altContent, altSendType, Receivers, sndDT, null, null, Buttons);
    }

    @Override
    public String sendATS(String CorpNum, String templateCode, String senderNum, String content, String altContent, String altSendType,
                          KakaoReceiver[] Receivers, String sndDT, String UserID, KakaoButton[] Buttons) throws PopbillException {
        return sendATS(CorpNum, templateCode, senderNum, content, null, altContent, altSendType, Receivers, sndDT, UserID, null, Buttons);
    }

    @Override
    public String sendATS(String CorpNum, String templateCode, String senderNum, String content, String altContent, String altSendType,
                          KakaoReceiver[] Receivers, String sndDT, String UserID, String requestNum, KakaoButton[] Buttons) throws PopbillException {
        return sendATS(CorpNum, templateCode, senderNum, content, null, altContent, altSendType, Receivers, sndDT, UserID, requestNum, Buttons);
    }

    @Override
    public String sendATS(String CorpNum, String templateCode, String senderNum, String content, String altSubject, String altContent, String altSendType,
                          KakaoReceiver[] Receivers, String sndDT, String UserID, String requestNum, KakaoButton[] Buttons) throws PopbillException {

        if (templateCode == null || templateCode.isEmpty())
            throw new PopbillException(-99999999, "알림톡 템플릿코드(templateCode)가 입력되지 않았습니다.");

        ATSSendRequest request = new ATSSendRequest();
        request.templateCode = templateCode;
        request.snd = senderNum;
        request.content = content;
        request.altSubject = altSubject;
        request.altContent = altContent;
        request.altSendType = altSendType;
        if (null != Buttons)
            request.btns = Buttons;
        request.sndDT = sndDT;
        request.requestNum = requestNum;
        request.msgs = Receivers;

        String postData = toJsonString(request);

        ReceiptResponse response = httppost("/ATS", CorpNum, postData, UserID, ReceiptResponse.class);

        return response.receiptNum;
    }


    /**************
     * 친구톡 단건전송
     **************/
    @Override
    public String sendFTS(String CorpNum, String plusFriendID, String senderNum, String content, String altContent, String altSendType,
                          KakaoButton[] Buttons, String receiverNum, String receiverName, String sndDT, Boolean adsYN) throws PopbillException {
        KakaoReceiver receiver = new KakaoReceiver();
        receiver.setReceiverNum(receiverNum);
        receiver.setReceiverName(receiverName);
        receiver.setMessage(content);
        receiver.setAltMessage(altContent);

        return sendFTS(CorpNum, plusFriendID, senderNum, null, null, null, altSendType, new KakaoReceiver[]{receiver}, Buttons, sndDT, adsYN, null, null);
    }

    @Override
    public String sendFTS(String CorpNum, String plusFriendID, String senderNum, String content, String altContent, String altSendType,
                          KakaoButton[] Buttons, String receiverNum, String receiverName, String sndDT, Boolean adsYN, String UserID) throws PopbillException {
        KakaoReceiver receiver = new KakaoReceiver();
        receiver.setReceiverNum(receiverNum);
        receiver.setReceiverName(receiverName);
        receiver.setMessage(content);
        receiver.setAltMessage(altContent);

        return sendFTS(CorpNum, plusFriendID, senderNum, null, null, null, altSendType, new KakaoReceiver[]{receiver}, Buttons, sndDT, adsYN, UserID, null);
    }

    @Override
    public String sendFTS(String CorpNum, String plusFriendID, String senderNum, String content, String altContent, String altSendType,
                          KakaoButton[] Buttons, String receiverNum, String receiverName, String sndDT, Boolean adsYN, String UserID, String requestNum)
            throws PopbillException {

        KakaoReceiver receiver = new KakaoReceiver();
        receiver.setReceiverNum(receiverNum);
        receiver.setReceiverName(receiverName);
        receiver.setMessage(content);
        receiver.setAltMessage(altContent);

        return sendFTS(CorpNum, plusFriendID, senderNum, null, null, null, altSendType, new KakaoReceiver[]{receiver}, Buttons, sndDT, adsYN, UserID, requestNum);
    }

    @Override
    public String sendFTS(String CorpNum, String plusFriendID, String senderNum, String content, String altSubject, String altContent,
                          String altSendType, KakaoButton[] Buttons, String receiverNum, String receiverName, String sndDT, Boolean adsYN, String UserID,
                          String requestNum) throws PopbillException {

        KakaoReceiver receiver = new KakaoReceiver();
        receiver.setReceiverNum(receiverNum);
        receiver.setReceiverName(receiverName);
        receiver.setMessage(content);
        receiver.setAltSubject(altSubject);
        receiver.setAltMessage(altContent);

        return sendFTS(CorpNum, plusFriendID, senderNum, null, null, null, altSendType, new KakaoReceiver[]{receiver}, Buttons, sndDT, adsYN, UserID, requestNum);
    }

    /*********************
     * 친구톡 개별내용 대량전송
     *********************/
    @Override
    public String sendFTS(String CorpNum, String plusFriendID, String senderNum, String altSendType, KakaoReceiver[] Receivers, KakaoButton[] Buttons,
                          String sndDT, Boolean adsYN) throws PopbillException {
        return sendFTS(CorpNum, plusFriendID, senderNum, null, null, null, altSendType, Receivers, Buttons, sndDT, adsYN, null, null);
    }

    @Override
    public String sendFTS(String CorpNum, String plusFriendID, String senderNum, String altSendType, KakaoReceiver[] Receivers, KakaoButton[] Buttons,
                          String sndDT, Boolean adsYN, String UserID) throws PopbillException {
        return sendFTS(CorpNum, plusFriendID, senderNum, null, null, null, altSendType, Receivers, Buttons, sndDT, adsYN, UserID, null);
    }

    @Override
    public String sendFTS(String CorpNum, String plusFriendID, String senderNum, String altSendType, KakaoReceiver[] Receivers, KakaoButton[] Buttons,
                          String sndDT, Boolean adsYN, String UserID, String requestNum) throws PopbillException {
        return sendFTS(CorpNum, plusFriendID, senderNum, null, null, null, altSendType, Receivers, Buttons, sndDT, adsYN, UserID, requestNum);
    }

    /*********************
     * 친구톡 동일내용 대량전송
     *********************/
    @Override
    public String sendFTS(String CorpNum, String plusFriendID, String senderNum, String content, String altContent, String altSendType,
                          KakaoReceiver[] Receivers, KakaoButton[] Buttons, String sndDT, Boolean adsYN) throws PopbillException {
        return sendFTS(CorpNum, plusFriendID, senderNum, content, null, altContent, altSendType, Receivers, Buttons, sndDT, adsYN, null, null);
    }

    @Override
    public String sendFTS(String CorpNum, String plusFriendID, String senderNum, String content, String altContent, String altSendType,
                          KakaoReceiver[] Receivers, KakaoButton[] Buttons, String sndDT, Boolean adsYN, String UserID) throws PopbillException {
        return sendFTS(CorpNum, plusFriendID, senderNum, content, null, altContent, altSendType, Receivers, Buttons, sndDT, adsYN, UserID, null);
    }

    @Override
    public String sendFTS(String CorpNum, String plusFriendID, String senderNum, String content, String altContent, String altSendType,
                          KakaoReceiver[] Receivers, KakaoButton[] Buttons, String sndDT, Boolean adsYN, String UserID, String requestNum) throws PopbillException {
        return sendFTS(CorpNum, plusFriendID, senderNum, content, null, altContent, altSendType, Receivers, Buttons, sndDT, adsYN, UserID, requestNum);
    }

    @Override
    public String sendFTS(String CorpNum, String plusFriendID, String senderNum, String content, String altSubject, String altContent, String altSendType,
                          KakaoReceiver[] Receivers, KakaoButton[] Buttons, String sndDT, Boolean adsYN, String UserID, String requestNum) throws PopbillException {

        if (plusFriendID == null || plusFriendID.isEmpty())
            throw new PopbillException(-99999999, "카카오톡 검색용 아이디가 입력되지 않았습니다.");

        FTSSendRequest request = new FTSSendRequest();
        request.plusFriendID = plusFriendID;
        request.snd = senderNum;
        request.content = content;
        request.altSubject = altSubject;
        request.altContent = altContent;
        request.altSendType = altSendType;
        request.msgs = Receivers;
        request.btns = Buttons;
        request.sndDT = sndDT;
        request.adsYN = adsYN;
        request.requestNum = requestNum;

        String postData = toJsonString(request);

        ReceiptResponse response = httppost("/FTS", CorpNum, postData, UserID, ReceiptResponse.class);
        return response.receiptNum;
    }


    /*******************
     * 친구톡 이미지 단건전송
     *******************/
    @Override
    public String sendFMS(String CorpNum, String plusFriendID, String senderNum, String content, String altContent,
                          String altSendType, KakaoButton[] Buttons, String receiverNum, String receiverName, String sndDT, Boolean adsYN,
                          File file, String imageURL) throws PopbillException {

        KakaoReceiver receiver = new KakaoReceiver();
        receiver.setReceiverNum(receiverNum);
        receiver.setReceiverName(receiverName);
        receiver.setMessage(content);
        receiver.setAltMessage(altContent);

        return sendFMS(CorpNum, plusFriendID, senderNum, null, null, null, altSendType, new KakaoReceiver[]{receiver}, Buttons,
                sndDT, adsYN, file, imageURL, null, null);
    }

    @Override
    public String sendFMS(String CorpNum, String plusFriendID, String senderNum, String content, String altContent,
                          String altSendType, KakaoButton[] Buttons, String receiverNum, String receiverName, String sndDT, Boolean adsYN,
                          File file, String imageURL, String UserID) throws PopbillException {

        KakaoReceiver receiver = new KakaoReceiver();
        receiver.setReceiverNum(receiverNum);
        receiver.setReceiverName(receiverName);
        receiver.setMessage(content);
        receiver.setAltMessage(altContent);

        return sendFMS(CorpNum, plusFriendID, senderNum, null, null, null, altSendType, new KakaoReceiver[]{receiver}, Buttons,
                sndDT, adsYN, file, imageURL, UserID, null);
    }

    @Override
    public String sendFMS(String CorpNum, String plusFriendID, String senderNum, String content, String altContent,
                          String altSendType, KakaoButton[] Buttons, String receiverNum, String receiverName, String sndDT, Boolean adsYN,
                          File file, String imageURL, String UserID, String requestNum) throws PopbillException {

        KakaoReceiver receiver = new KakaoReceiver();
        receiver.setReceiverNum(receiverNum);
        receiver.setReceiverName(receiverName);
        receiver.setMessage(content);
        receiver.setAltMessage(altContent);

        return sendFMS(CorpNum, plusFriendID, senderNum, null, null, null, altSendType, new KakaoReceiver[]{receiver}, Buttons,
                sndDT, adsYN, file, imageURL, UserID, requestNum);
    }

    @Override
    public String sendFMS(String CorpNum, String plusFriendID, String senderNum, String content, String altSubject,
                          String altContent, String altSendType, KakaoButton[] Buttons, String receiverNum, String receiverName, String sndDT,
                          Boolean adsYN, File file, String imageURL, String UserID, String requestNum) throws PopbillException {

        KakaoReceiver receiver = new KakaoReceiver();
        receiver.setReceiverNum(receiverNum);
        receiver.setReceiverName(receiverName);
        receiver.setMessage(content);
        receiver.setAltSubject(altSubject);
        receiver.setAltMessage(altContent);

        return sendFMS(CorpNum, plusFriendID, senderNum, null, null, null, altSendType, new KakaoReceiver[]{receiver}, Buttons,
                sndDT, adsYN, file, imageURL, UserID, requestNum);
    }


    /**************************
     * 친구톡 이미지 개별내용 대량전송
     **************************/
    @Override
    public String sendFMS(String CorpNum, String plusFriendID, String senderNum, String altSendType, KakaoReceiver[] Receivers,
                          KakaoButton[] Buttons, String sndDT, Boolean adsYN, File file, String imageURL) throws PopbillException {
        return sendFMS(CorpNum, plusFriendID, senderNum, null, null, null, altSendType, Receivers, Buttons, sndDT, adsYN, file,
                imageURL, null, null);
    }

    @Override
    public String sendFMS(String CorpNum, String plusFriendID, String senderNum, String altSendType, KakaoReceiver[] Receivers,
                          KakaoButton[] Buttons, String sndDT, Boolean adsYN, File file, String imageURL, String UserID) throws PopbillException {
        return sendFMS(CorpNum, plusFriendID, senderNum, null, null, null, altSendType, Receivers, Buttons, sndDT, adsYN, file,
                imageURL, UserID, null);
    }

    @Override
    public String sendFMS(String CorpNum, String plusFriendID, String senderNum, String altSendType, KakaoReceiver[] Receivers,
                          KakaoButton[] Buttons, String sndDT, Boolean adsYN, File file, String imageURL, String UserID, String requestNum)
            throws PopbillException {
        return sendFMS(CorpNum, plusFriendID, senderNum, null, null, null, altSendType, Receivers, Buttons, sndDT, adsYN, file,
                imageURL, UserID, requestNum);
    }

    /*************************
     * 친구톡 이미지 동일내용 대량전송
     *************************/
    @Override
    public String sendFMS(String CorpNum, String plusFriendID, String senderNum, String content, String altContent, String altSendType,
                          KakaoReceiver[] Receivers, KakaoButton[] Buttons, String sndDT, Boolean adsYN, File file, String imageURL) throws PopbillException {
        return sendFMS(CorpNum, plusFriendID, senderNum, content, null, altContent, altSendType, Receivers, Buttons, sndDT, adsYN, file,
                imageURL, null, null);
    }

    @Override
    public String sendFMS(String CorpNum, String plusFriendID, String senderNum, String content, String altContent, String altSendType,
                          KakaoReceiver[] Receivers, KakaoButton[] Buttons, String sndDT, Boolean adsYN, File file, String imageURL, String UserID)
            throws PopbillException {
        return sendFMS(CorpNum, plusFriendID, senderNum, content, null, altContent, altSendType, Receivers, Buttons, sndDT, adsYN, file,
                imageURL, UserID, null);
    }

    @Override
    public String sendFMS(String CorpNum, String plusFriendID, String senderNum, String content, String altContent, String altSendType,
                          KakaoReceiver[] Receivers, KakaoButton[] Buttons, String sndDT, Boolean adsYN, File file, String imageURL, String UserID,
                          String requestNum) throws PopbillException {
        return sendFMS(CorpNum, plusFriendID, senderNum, content, null, altContent, altSendType, Receivers, Buttons, sndDT, adsYN, file,
                imageURL, UserID, requestNum);
    }

    @Override
    public String sendFMS(String CorpNum, String plusFriendID, String senderNum, String content, String altSubject, String altContent,
                          String altSendType, KakaoReceiver[] Receivers, KakaoButton[] Buttons, String sndDT, Boolean adsYN, File file, String imageURL,
                          String UserID, String requestNum) throws PopbillException {
        if (plusFriendID == null || plusFriendID.isEmpty())
            throw new PopbillException(-99999999, "카카오톡 검색용 아이디가 입력되지 않았습니다.");

        FTSSendRequest request = new FTSSendRequest();
        request.plusFriendID = plusFriendID;
        request.snd = senderNum;
        request.content = content;
        request.altSubject = altSubject;
        request.altContent = altContent;
        request.altSendType = altSendType;
        request.msgs = Receivers;
        request.btns = Buttons;
        request.sndDT = sndDT;
        request.adsYN = adsYN;
        request.imageURL = imageURL;
        request.requestNum = requestNum;

        String postData = toJsonString(request);

        List<UploadFile> uploadFiles = new ArrayList<UploadFile>();

        UploadFile uf = new UploadFile();
        uf.fieldName = "file";
        uf.fileName = file.getName();
        try {
            uf.fileData = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            throw new PopbillException(-99999999, "전송할 파일을 찾을 수 없습니다.", e);
        }
        uploadFiles.add(uf);

        ReceiptResponse response = httppostFiles("/FMS", CorpNum, postData, uploadFiles, UserID, ReceiptResponse.class);

        for (UploadFile f : uploadFiles) {
            if (f.fileData != null)
                try {
                    f.fileData.close();
                } catch (IOException e) {
                }
        }

        return response.receiptNum;
    }

    @Override
    public String sendFMSBinary(String CorpNum, String PlusFriendID, String SenderNum, String Content,
                                String AltSubject, String AltContent, String AltSendType, KakaoButton[] Buttons,
                                String ReceiverNum, String ReceiverName, String SndDT, Boolean AdsYN, AttachFile File,
                                String ImageURL, String UserID, String RequestNum) throws PopbillException {

        KakaoReceiver receiver = new KakaoReceiver();
        receiver.setReceiverNum(ReceiverNum);
        receiver.setReceiverName(ReceiverName);
        receiver.setMessage(Content);
        receiver.setAltSubject(AltSubject);
        receiver.setAltMessage(AltContent);

        return requestFMSBinary(CorpNum, PlusFriendID, SenderNum, null, null, null,
                AltSendType, new KakaoReceiver[]{receiver}, Buttons, SndDT, AdsYN, File, ImageURL, UserID, RequestNum);
    }

    @Override
    public String sendFMSBinary(String CorpNum, String PlusFriendID, String SenderNum, String AltSendType,
                                KakaoReceiver[] Receivers, KakaoButton[] Buttons, String SndDT, Boolean AdsYN,
                                AttachFile File, String ImageURL, String UserID, String RequestNum) throws PopbillException {

        return requestFMSBinary(CorpNum, PlusFriendID, SenderNum, null, null, null,
                AltSendType, Receivers, Buttons, SndDT, AdsYN, File, ImageURL, UserID, RequestNum);
    }

    @Override
    public String sendFMSBinary(String CorpNum, String PlusFriendID, String SenderNum, String Content,
                                String AltSubject, String AltContent, String AltSendType, KakaoReceiver[] Receivers,
                                KakaoButton[] Buttons, String SndDT, Boolean AdsYN, AttachFile File, String ImageURL,
                                String UserID, String RequestNum) throws PopbillException {

        return requestFMSBinary(CorpNum, PlusFriendID, SenderNum, Content, AltSubject, AltContent, AltSendType,
                Receivers, Buttons, SndDT, AdsYN, File, ImageURL, UserID, RequestNum);
    }

    private String requestFMSBinary(String CorpNum, String PlusFriendID, String SenderNum, String Content,
                                    String AltSubject, String AltContent, String AltSendType, KakaoReceiver[] Receivers,
                                    KakaoButton[] Buttons, String SndDT, Boolean AdsYN, AttachFile File, String ImageURL,
                                    String UserID, String RequestNum) throws PopbillException {

        if (File == null)
            throw new PopbillException(-99999999, "전송 이미지 파일 정보가 입력되지 않았습니다.");

        FTSSendRequest request = new FTSSendRequest();
        request.plusFriendID = PlusFriendID;
        request.snd = SenderNum;
        request.content = Content;
        request.altSubject = AltSubject;
        request.altContent = AltContent;
        request.altSendType = AltSendType;
        request.msgs = Receivers;
        request.btns = Buttons;
        request.sndDT = SndDT;
        request.adsYN = AdsYN;
        request.imageURL = ImageURL;
        request.requestNum = RequestNum;

        String postData = toJsonString(request);

        List<UploadFile> uploadFiles = new ArrayList<UploadFile>();

        UploadFile uf = new UploadFile();
        uf.fieldName = "file";
        uf.fileName = File.getFileName();
        uf.fileData = File.getFileData();
        uploadFiles.add(uf);

        ReceiptResponse response = httppostFiles("/FMS", CorpNum, postData, uploadFiles, UserID, ReceiptResponse.class);

        for (UploadFile f : uploadFiles) {
            if (f.fileData != null)
                try {
                    f.fileData.close();
                } catch (IOException e) {
                }
        }

        return response.receiptNum;
    }

    @Override
    public Response cancelReserve(String CorpNum, String receiptNum) throws PopbillException {
        return cancelReserve(CorpNum, receiptNum, null);
    }

    @Override
    public Response cancelReserve(String CorpNum, String receiptNum, String UserID) throws PopbillException {
        if (receiptNum == null || receiptNum.isEmpty())
            throw new PopbillException(-99999999, "접수번호가 입력되지 않았습니다.");

        return httpget("/KakaoTalk/" + receiptNum + "/Cancel", CorpNum, UserID, Response.class);
    }

    @Override
    public Response cancelReserveRN(String CorpNum, String requestNum) throws PopbillException {
        return cancelReserveRN(CorpNum, requestNum, null);
    }

    @Override
    public Response cancelReserveRN(String CorpNum, String requestNum, String UserID) throws PopbillException {
        if (requestNum == null || requestNum.isEmpty())
            throw new PopbillException(-99999999, "전송요청번호가 입력되지 않았습니다.");

        return httpget("/KakaoTalk/Cancel/" + requestNum, CorpNum, UserID, Response.class);
    }

    @Override
    public Response cancelReservebyRCV(String CorpNum, String receiptNum, String receiveNum) throws PopbillException {
        return cancelReservebyRCV(CorpNum, receiptNum, receiveNum, null);
    }

    @Override
    public Response cancelReservebyRCV(String CorpNum, String receiptNum, String receiveNum, String UserID)
            throws PopbillException {
        if (receiptNum == null || receiptNum.isEmpty())
            throw new PopbillException(-99999999, "접수번호가 입력되지 않았습니다.");
        if (receiveNum == null || receiveNum.isEmpty())
            throw new PopbillException(-99999999, "수신번호가 입력되지 않았습니다.");

        String PostData = toJsonString(receiveNum);

        return httppost("/KakaoTalk/" + receiptNum + "/Cancel", CorpNum, PostData, UserID, Response.class);
    }

    @Override
    public Response cancelReserveRNbyRCV(String CorpNum, String requestNum, String receiveNum) throws PopbillException {
        return cancelReserveRNbyRCV(CorpNum, requestNum, receiveNum, null);
    }

    @Override
    public Response cancelReserveRNbyRCV(String CorpNum, String requestNum, String receiveNum, String UserID)
            throws PopbillException {
        if (requestNum == null || requestNum.isEmpty())
            throw new PopbillException(-99999999, "전송요청번호가 입력되지 않았습니다.");
        if (receiveNum == null || receiveNum.isEmpty())
            throw new PopbillException(-99999999, "수신번호가 입력되지 않았습니다.");

        String PostData = toJsonString(receiveNum);

        return httppost("/KakaoTalk/Cancel/" + requestNum, CorpNum, PostData, UserID, Response.class);
    }

    @Override
    public KakaoSentInfo getMessages(String CorpNum, String receiptNum) throws PopbillException {
        return getMessages(CorpNum, receiptNum, null);
    }

    @Override
    public KakaoSentInfo getMessages(String CorpNum, String receiptNum, String UserID) throws PopbillException {
        if (receiptNum == null || receiptNum.isEmpty())
            throw new PopbillException(-99999999, "접수번호가 입력되지 않았습니다.");
        return httpget("/KakaoTalk/" + receiptNum, CorpNum, UserID, KakaoSentInfo.class);
    }

    @Override
    public KakaoSentInfo getMessagesRN(String CorpNum, String requestNum) throws PopbillException {
        return getMessagesRN(CorpNum, requestNum, null);
    }

    @Override
    public KakaoSentInfo getMessagesRN(String CorpNum, String requestNum, String UserID) throws PopbillException {
        if (requestNum == null || requestNum.isEmpty())
            throw new PopbillException(-99999999, "전송요청번호가 입력되지 않았습니다.");
        return httpget("/KakaoTalk/Get/" + requestNum, CorpNum, UserID, KakaoSentInfo.class);
    }

    @Override
    public KakaoSearchResult search(String CorpNum, String SDate, String EDate, String[] State, String[] Item, String ReserveYN, Boolean SenderOnly,
                                    Integer Page, Integer PerPage, String Order) throws PopbillException {
        return search(CorpNum, SDate, EDate, State, Item, ReserveYN, SenderOnly, Page, PerPage, Order, null);
    }

    @Override
    public KakaoSearchResult search(String CorpNum, String SDate, String EDate, String[] State, String[] Item, String ReserveYN, Boolean SenderOnly,
                                    Integer Page, Integer PerPage, String Order, String UserID) throws PopbillException {
        return search(CorpNum, SDate, EDate, State, Item, ReserveYN, SenderOnly, Page, PerPage, Order, UserID, null);
    }

    @Override
    public KakaoSearchResult search(String CorpNum, String SDate, String EDate, String[] State, String[] Item, String ReserveYN, Boolean SenderOnly,
                                    Integer Page, Integer PerPage, String Order, String UserID, String QString) throws PopbillException {
        if (SDate == null)
            throw new PopbillException(-99999999, "시작일자가 입력되지 않았습니다.");
        if (EDate == null)
            throw new PopbillException(-99999999, "종료일자가 입력되지 않았습니다.");
        if (State == null || State.length == 0)
            throw new PopbillException(-99999999, "전송상태가 입력되지 않았습니다.");

        String uri = "/KakaoTalk/Search?SDate=" + SDate;
        uri += "&EDate=" + EDate;
        uri += "&State=" + ValidationUtils.replaceInvalidUriChars(State);

        if (Item != null)
            uri += "&Item=" + ValidationUtils.replaceInvalidUriChars(Item);
        if (ReserveYN != null && !ReserveYN.isEmpty())
            uri += "&ReserveYN=" + ReserveYN;
        if (SenderOnly != null)
            uri += "&SenderOnly=" + SenderOnly;
        if (Page != null && Page > 0)
            uri += "&Page=" + Integer.toString(Page);
        if (PerPage != null && PerPage > 0 && PerPage <= 1000)
            uri += "&PerPage=" + Integer.toString(PerPage);
        if (Order != null && (Order.equals("D") || Order.equals("A")))
            uri += "&Order=" + Order;
        if (QString != null && !QString.isEmpty()) {
            try {
                uri += "&QString=" + URLEncoder.encode(QString, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                throw new PopbillException(-99999999, "검색어(QString) 인코딩 오류");
            }
        }

        return httpget(uri, CorpNum, UserID, KakaoSearchResult.class);
    }

    @Override
    public String getSentListURL(String CorpNum, String UserID) throws PopbillException {

        URLResponse response = httpget("/KakaoTalk/?TG=BOX", CorpNum, UserID, URLResponse.class);

        return response.url;
    }

    @Override
    public String getURL(String CorpNum, String TOGO, String UserID) throws PopbillException {

        String uri = "/KakaoTalk/?TG=";

        if (TOGO != null && TOGO.equals("SENDER"))
            uri = "/Message/?TG=";

        URLResponse response = httpget(uri + TOGO, CorpNum, UserID, URLResponse.class);

        return response.url;
    }

    @Override
    public float getUnitCost(String CorpNum, KakaoType kakaoType) throws PopbillException {
        if (kakaoType == null)
            throw new PopbillException(-99999999, "카카오톡 전송유형이 입력되지 않았습니다.");

        UnitCostResponse response = httpget("/KakaoTalk/UnitCost?Type=" + kakaoType.name(), CorpNum, null, UnitCostResponse.class);

        return response.unitCost;
    }

    @Override
    public ChargeInfo getChargeInfo(String CorpNum, KakaoType kakaoType) throws PopbillException {
        return httpget("/KakaoTalk/ChargeInfo?Type=" + kakaoType.name(), CorpNum, null, ChargeInfo.class);
    }

    protected class ATSSendRequest {
        public String templateCode;
        public String snd;
        public String content;
        public String altSubject;
        public String altContent;
        public String altSendType;
        public String sndDT;
        public String requestNum;

        public KakaoReceiver[] msgs;
        public KakaoButton[] btns;

    }

    protected class FTSSendRequest {
        public String plusFriendID;
        public String snd;
        public String content;
        public String altSubject;
        public String altContent;
        public String altSendType;
        public String sndDT;
        public String imageURL;
        public Boolean adsYN;
        public String requestNum;

        public KakaoReceiver[] msgs;
        public KakaoButton[] btns;
    }

    protected class ReceiptResponse {
        public String receiptNum;
    }

}
package com.popbill.api.kakao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.popbill.api.BaseServiceImp;
import com.popbill.api.ChargeInfo;
import com.popbill.api.KakaoService;
import com.popbill.api.PopbillException;
import com.popbill.api.Response;

public class KakaoServiceImp extends BaseServiceImp implements KakaoService {

    @Override
    protected List<String> getScopes() {

        return Arrays.asList("153", "154", "155");
    }

    @Override
    public float getUnitCost(String CorpNum, KakaoType kakaoType) throws PopbillException {
        if (kakaoType == null)
            throw new PopbillException(-99999999, "카카오톡 전송유형이 입력되지 않았습니다.");

        UnitCostResponse response = httpget("/KakaoTalk/UnitCost?Type=" + kakaoType.name(), CorpNum, null,
                UnitCostResponse.class);

        return response.unitCost;
    }

    @Override
    public String getURL(String CorpNum, String TOGO, String UserID) throws PopbillException {

        String uri = "/KakaoTalk/?TG=";
        if (TOGO == "SENDER")
            uri = "/Message/?TG=";

        URLResponse response = httpget(uri + TOGO, CorpNum, UserID, URLResponse.class);

        return response.url;
    }

    @Override
    public Response cancelReserve(String CorpNum, String receiptNum) throws PopbillException {
        return cancelReserve(CorpNum, receiptNum, null);
    }

    @Override
    public Response cancelReserve(String CorpNum, String receiptNum, String UserID) throws PopbillException {
        if (receiptNum == null || receiptNum == "")
            throw new PopbillException(-99999999, "접수번호가 입력되지 않았습니다.");

        return httpget("/KakaoTalk/" + receiptNum + "/Cancel", CorpNum, UserID, Response.class);
    }

    @Override
    public Response cancelReserveRN(String CorpNum, String requestNum) throws PopbillException {
        return cancelReserveRN(CorpNum, requestNum, null);
    }

    @Override
    public Response cancelReserveRN(String CorpNum, String requestNum, String UserID) throws PopbillException {
        if (requestNum == null || requestNum == "")
            throw new PopbillException(-99999999, "전송요청번호가 입력되지 않았습니다.");

        return httpget("/KakaoTalk/Cancel/" + requestNum, CorpNum, UserID, Response.class);
    }

    @Override
    public ChargeInfo getChargeInfo(String CorpNum, KakaoType kakaoType) throws PopbillException {
        return httpget("/KakaoTalk/ChargeInfo?Type=" + kakaoType.name(), CorpNum, null, ChargeInfo.class);
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
    public PlusFriendID[] listPlusFriendID(String CorpNum) throws PopbillException {
        return listPlusFriendID(CorpNum, null);
    }

    @Override
    public PlusFriendID[] listPlusFriendID(String CorpNum, String UserID) throws PopbillException {
        return httpget("/KakaoTalk/ListPlusFriendID", CorpNum, UserID, PlusFriendID[].class);
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

    @Override
    public String sendATS(String CorpNum, String templateCode, String senderNum, String content, String altContent,
            String altSendType, String receiverNum, String receiverName, String sndDT) throws PopbillException {
        return sendATS(CorpNum, templateCode, senderNum, content, altContent, altSendType, receiverNum, receiverName,
                sndDT, "");
    }

    @Override
    public String sendATS(String CorpNum, String templateCode, String senderNum, String content, String altContent,
            String altSendType, String receiverNum, String receiverName, String sndDT, String UserID)
            throws PopbillException {
        return sendATS(CorpNum, templateCode, senderNum, content, altContent, altSendType, receiverNum, receiverName,
                sndDT, UserID, "");
    }

    @Override
    public String sendATS(String CorpNum, String templateCode, String senderNum, String content, String altContent,
            String altSendType, String receiverNum, String receiverName, String sndDT, String UserID, String requestNum)
            throws PopbillException {
        KakaoReceiver receiver = new KakaoReceiver();
        receiver.setReceiverNum(receiverNum);
        receiver.setReceiverName(receiverName);
        receiver.setMessage(content);
        receiver.setAltMessage(altContent);

        return sendATS(CorpNum, templateCode, senderNum, null, null, altSendType, new KakaoReceiver[] { receiver },
                sndDT, UserID, requestNum, null);
    }

    @Override
    public String sendATS(String CorpNum, String templateCode, String senderNum, String altSendType,
            KakaoReceiver[] Receivers, String sndDT) throws PopbillException {
        return sendATS(CorpNum, templateCode, senderNum, altSendType, Receivers, sndDT, "", "", null);
    }

    @Override
    public String sendATS(String CorpNum, String templateCode, String senderNum, String altSendType,
            KakaoReceiver[] Receivers, String sndDT, String UserID) throws PopbillException {
        return sendATS(CorpNum, templateCode, senderNum, altSendType, Receivers, sndDT, UserID, "", null);
    }

    @Override
    public String sendATS(String CorpNum, String templateCode, String senderNum, String altSendType,
            KakaoReceiver[] Receivers, String sndDT, String UserID, String requestNum) throws PopbillException {
        return sendATS(CorpNum, templateCode, senderNum, null, null, altSendType, Receivers, sndDT, UserID, requestNum,
                null);
    }

    @Override
    public String sendATS(String CorpNum, String templateCode, String senderNum, String content, String altContent,
            String altSendType, KakaoReceiver[] Receivers, String sndDT) throws PopbillException {
        return sendATS(CorpNum, templateCode, senderNum, content, altContent, altSendType, Receivers, sndDT, "", "",
                null);
    }

    @Override
    public String sendATS(String CorpNum, String templateCode, String senderNum, String content, String altContent,
            String altSendType, KakaoReceiver[] Receivers, String sndDT, String UserID) throws PopbillException {
        return sendATS(CorpNum, templateCode, senderNum, content, altContent, altSendType, Receivers, sndDT, UserID, "",
                null);
    }

    @Override
    public String sendATS(String CorpNum, String templateCode, String senderNum, String content, String altContent,
            String altSendType, KakaoReceiver[] Receivers, String sndDT, String UserID, String requestNum)
            throws PopbillException {
        return sendATS(CorpNum, templateCode, senderNum, content, altContent, altSendType, Receivers, sndDT, UserID,
                requestNum, null);
    }

    @Override
    public String sendATS(String CorpNum, String templateCode, String senderNum, String content, String altContent,
            String altSendType, String receiverNum, String receiverName, String sndDT, KakaoButton[] Buttons)
            throws PopbillException {

        KakaoReceiver receiver = new KakaoReceiver();
        receiver.setReceiverNum(receiverNum);
        receiver.setReceiverName(receiverName);
        receiver.setMessage(content);
        receiver.setAltMessage(altContent);

        return sendATS(CorpNum, templateCode, senderNum, null, null, altSendType, new KakaoReceiver[] { receiver },
                sndDT, "", "", Buttons);
    }

    @Override
    public String sendATS(String CorpNum, String templateCode, String senderNum, String content, String altContent,
            String altSendType, String receiverNum, String receiverName, String sndDT, String UserID,
            KakaoButton[] Buttons) throws PopbillException {
        KakaoReceiver receiver = new KakaoReceiver();
        receiver.setReceiverNum(receiverNum);
        receiver.setReceiverName(receiverName);
        receiver.setMessage(content);
        receiver.setAltMessage(altContent);

        return sendATS(CorpNum, templateCode, senderNum, null, null, altSendType, new KakaoReceiver[] { receiver },
                sndDT, UserID, "", Buttons);
    }

    @Override
    public String sendATS(String CorpNum, String templateCode, String senderNum, String content, String altContent,
            String altSendType, String receiverNum, String receiverName, String sndDT, String UserID, String requestNum,
            KakaoButton[] Buttons) throws PopbillException {
        KakaoReceiver receiver = new KakaoReceiver();
        receiver.setReceiverNum(receiverNum);
        receiver.setReceiverName(receiverName);
        receiver.setMessage(content);
        receiver.setAltMessage(altContent);

        return sendATS(CorpNum, templateCode, senderNum, null, null, altSendType, new KakaoReceiver[] { receiver },
                sndDT, UserID, requestNum, Buttons);
    }

    @Override
    public String sendATS(String CorpNum, String templateCode, String senderNum, String altSendType,
            KakaoReceiver[] Receivers, String sndDT, KakaoButton[] Buttons) throws PopbillException {
        return sendATS(CorpNum, templateCode, senderNum, null, null, altSendType, Receivers, sndDT, "", "", Buttons);
    }

    @Override
    public String sendATS(String CorpNum, String templateCode, String senderNum, String altSendType,
            KakaoReceiver[] Receivers, String sndDT, String UserID, KakaoButton[] Buttons) throws PopbillException {
        return sendATS(CorpNum, templateCode, senderNum, null, null, altSendType, Receivers, sndDT, UserID, "",
                Buttons);
    }

    @Override
    public String sendATS(String CorpNum, String templateCode, String senderNum, String altSendType,
            KakaoReceiver[] Receivers, String sndDT, String UserID, String requestNum, KakaoButton[] Buttons)
            throws PopbillException {
        return sendATS(CorpNum, templateCode, senderNum, null, null, altSendType, Receivers, sndDT, UserID, requestNum,
                Buttons);
    }

    @Override
    public String sendATS(String CorpNum, String templateCode, String senderNum, String content, String altContent,
            String altSendType, KakaoReceiver[] Receivers, String sndDT, KakaoButton[] Buttons)
            throws PopbillException {

        return sendATS(CorpNum, templateCode, senderNum, content, altContent, altSendType, Receivers, sndDT, "", "",
                Buttons);
    }

    @Override
    public String sendATS(String CorpNum, String templateCode, String senderNum, String content, String altContent,
            String altSendType, KakaoReceiver[] Receivers, String sndDT, String UserID, KakaoButton[] Buttons)
            throws PopbillException {
        return sendATS(CorpNum, templateCode, senderNum, content, altContent, altSendType, Receivers, sndDT, UserID, "",
                Buttons);
    }

    @Override
    public String sendATS(String CorpNum, String templateCode, String senderNum, String content, String altContent,
            String altSendType, KakaoReceiver[] Receivers, String sndDT, String UserID, String requestNum,
            KakaoButton[] Buttons) throws PopbillException {

        if (templateCode == null || templateCode == "")
            throw new PopbillException(-99999999, "알림톡 템플릿코드(templateCode)가 입력되지 않았습니다.");

        ATSSendRequest request = new ATSSendRequest();
        request.templateCode = templateCode;
        request.snd = senderNum;
        request.content = content;
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

    @Override
    public String sendFTS(String CorpNum, String plusFriendID, String senderNum, String content, String altContent,
            String altSendType, KakaoButton[] Buttons, String receiverNum, String receiverName, String sndDT,
            Boolean adsYN) throws PopbillException {
        return sendFTS(CorpNum, plusFriendID, senderNum, content, altContent, altSendType, Buttons, receiverNum,
                receiverName, sndDT, adsYN, null);
    }

    @Override
    public String sendFTS(String CorpNum, String plusFriendID, String senderNum, String content, String altContent,
            String altSendType, KakaoButton[] Buttons, String receiverNum, String receiverName, String sndDT,
            Boolean adsYN, String UserID) throws PopbillException {
        return sendFTS(CorpNum, plusFriendID, senderNum, content, altContent, altSendType, Buttons, receiverNum,
                receiverName, sndDT, adsYN, UserID, null);
    }

    @Override
    public String sendFTS(String CorpNum, String plusFriendID, String senderNum, String content, String altContent,
            String altSendType, KakaoButton[] Buttons, String receiverNum, String receiverName, String sndDT,
            Boolean adsYN, String UserID, String requestNum) throws PopbillException {

        KakaoReceiver receiver = new KakaoReceiver();
        receiver.setReceiverNum(receiverNum);
        receiver.setReceiverName(receiverName);
        receiver.setMessage(content);
        receiver.setAltMessage(altContent);

        return sendFTS(CorpNum, plusFriendID, senderNum, null, null, altSendType, new KakaoReceiver[] { receiver },
                Buttons, sndDT, adsYN, UserID, requestNum);
    }

    @Override
    public String sendFTS(String CorpNum, String plusFriendID, String senderNum, String altSendType,
            KakaoReceiver[] Receivers, KakaoButton[] Buttons, String sndDT, Boolean adsYN) throws PopbillException {
        return sendFTS(CorpNum, plusFriendID, senderNum, altSendType, Receivers, Buttons, sndDT, adsYN, null);
    }

    @Override
    public String sendFTS(String CorpNum, String plusFriendID, String senderNum, String altSendType,
            KakaoReceiver[] Receivers, KakaoButton[] Buttons, String sndDT, Boolean adsYN, String UserID)
            throws PopbillException {
        return sendFTS(CorpNum, plusFriendID, senderNum, altSendType, Receivers, Buttons, sndDT, adsYN, UserID, null);
    }

    @Override
    public String sendFTS(String CorpNum, String plusFriendID, String senderNum, String altSendType,
            KakaoReceiver[] Receivers, KakaoButton[] Buttons, String sndDT, Boolean adsYN, String UserID,
            String requestNum) throws PopbillException {
        return sendFTS(CorpNum, plusFriendID, senderNum, null, null, altSendType, Receivers, Buttons, sndDT, adsYN,
                UserID, requestNum);
    }

    @Override
    public String sendFTS(String CorpNum, String plusFriendID, String senderNum, String content, String altContent,
            String altSendType, KakaoReceiver[] Receivers, KakaoButton[] Buttons, String sndDT, Boolean adsYN)
            throws PopbillException {
        return sendFTS(CorpNum, plusFriendID, senderNum, content, altContent, altSendType, Receivers, Buttons, sndDT,
                adsYN, null);
    }

    @Override
    public String sendFTS(String CorpNum, String plusFriendID, String senderNum, String content, String altContent,
            String altSendType, KakaoReceiver[] Receivers, KakaoButton[] Buttons, String sndDT, Boolean adsYN,
            String UserID) throws PopbillException {
        return sendFTS(CorpNum, plusFriendID, senderNum, content, altContent, altSendType, Receivers, Buttons, sndDT,
                adsYN, UserID, null);
    }

    @Override
    public String sendFTS(String CorpNum, String plusFriendID, String senderNum, String content, String altContent,
            String altSendType, KakaoReceiver[] Receivers, KakaoButton[] Buttons, String sndDT, Boolean adsYN,
            String UserID, String requestNum) throws PopbillException {

        if (plusFriendID == null || plusFriendID == "")
            throw new PopbillException(-99999999, "플러스친구 아이디가 입력되지 않았습니다.");

        FTSSendRequest request = new FTSSendRequest();
        request.plusFriendID = plusFriendID;
        request.snd = senderNum;
        request.content = content;
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

    @Override
    public String sendFMS(String CorpNum, String plusFriendID, String senderNum, String content, String altContent,
            String altSendType, KakaoButton[] Buttons, String receiverNum, String receiverName, String sndDT,
            Boolean adsYN, File file, String imageURL) throws PopbillException {
        return sendFMS(CorpNum, plusFriendID, senderNum, content, altContent, altSendType, Buttons, receiverNum,
                receiverName, sndDT, adsYN, file, imageURL, null);
    }

    @Override
    public String sendFMS(String CorpNum, String plusFriendID, String senderNum, String content, String altContent,
            String altSendType, KakaoButton[] Buttons, String receiverNum, String receiverName, String sndDT,
            Boolean adsYN, File file, String imageURL, String UserID) throws PopbillException {
        return sendFMS(CorpNum, plusFriendID, senderNum, content, altContent, altSendType, Buttons, receiverNum,
                receiverName, sndDT, adsYN, file, imageURL, UserID, null);
    }

    @Override
    public String sendFMS(String CorpNum, String plusFriendID, String senderNum, String content, String altContent,
            String altSendType, KakaoButton[] Buttons, String receiverNum, String receiverName, String sndDT,
            Boolean adsYN, File file, String imageURL, String UserID, String requestNum) throws PopbillException {

        KakaoReceiver receiver = new KakaoReceiver();
        receiver.setReceiverNum(receiverNum);
        receiver.setReceiverName(receiverName);
        receiver.setMessage(content);
        receiver.setAltMessage(altContent);

        return sendFMS(CorpNum, plusFriendID, senderNum, null, null, altSendType, new KakaoReceiver[] { receiver },
                Buttons, sndDT, adsYN, file, imageURL, UserID, requestNum);
    }

    @Override
    public String sendFMS(String CorpNum, String plusFriendID, String senderNum, String altSendType,
            KakaoReceiver[] Receivers, KakaoButton[] Buttons, String sndDT, Boolean adsYN, File file, String imageURL)
            throws PopbillException {
        return sendFMS(CorpNum, plusFriendID, senderNum, altSendType, Receivers, Buttons, sndDT, adsYN, file, imageURL,
                null);
    }

    @Override
    public String sendFMS(String CorpNum, String plusFriendID, String senderNum, String altSendType,
            KakaoReceiver[] Receivers, KakaoButton[] Buttons, String sndDT, Boolean adsYN, File file, String imageURL,
            String UserID) throws PopbillException {
        return sendFMS(CorpNum, plusFriendID, senderNum, altSendType, Receivers, Buttons, sndDT, adsYN, file, imageURL,
                UserID, null);
    }

    @Override
    public String sendFMS(String CorpNum, String plusFriendID, String senderNum, String altSendType,
            KakaoReceiver[] Receivers, KakaoButton[] Buttons, String sndDT, Boolean adsYN, File file, String imageURL,
            String UserID, String requestNum) throws PopbillException {
        return sendFMS(CorpNum, plusFriendID, senderNum, null, null, altSendType, Receivers, Buttons, sndDT, adsYN,
                file, imageURL, UserID, requestNum);
    }

    @Override
    public String sendFMS(String CorpNum, String plusFriendID, String senderNum, String content, String altContent,
            String altSendType, KakaoReceiver[] Receivers, KakaoButton[] Buttons, String sndDT, Boolean adsYN,
            File file, String imageURL) throws PopbillException {
        return sendFMS(CorpNum, plusFriendID, senderNum, content, altContent, altSendType, Receivers, Buttons, sndDT,
                adsYN, file, imageURL, null);
    }

    @Override
    public String sendFMS(String CorpNum, String plusFriendID, String senderNum, String content, String altContent,
            String altSendType, KakaoReceiver[] Receivers, KakaoButton[] Buttons, String sndDT, Boolean adsYN,
            File file, String imageURL, String UserID) throws PopbillException {
        return sendFMS(CorpNum, plusFriendID, senderNum, content, altContent, altSendType, Receivers, Buttons, sndDT,
                adsYN, file, imageURL, UserID, null);
    }

    @Override
    public String sendFMS(String CorpNum, String plusFriendID, String senderNum, String content, String altContent,
            String altSendType, KakaoReceiver[] Receivers, KakaoButton[] Buttons, String sndDT, Boolean adsYN,
            File file, String imageURL, String UserID, String requestNum) throws PopbillException {
        if (plusFriendID == null || plusFriendID == "")
            throw new PopbillException(-99999999, "플러스친구 아이디가 입력되지 않았습니다.");

        FTSSendRequest request = new FTSSendRequest();
        request.plusFriendID = plusFriendID;
        request.snd = senderNum;
        request.content = content;
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
    public KakaoSentInfo getMessages(String CorpNum, String receiptNum) throws PopbillException {
        return getMessages(CorpNum, receiptNum, null);
    }

    @Override
    public KakaoSentInfo getMessages(String CorpNum, String receiptNum, String UserID) throws PopbillException {
        if (receiptNum == null || receiptNum == "")
            throw new PopbillException(-99999999, "접수번호가 입력되지 않았습니다.");
        return httpget("/KakaoTalk/" + receiptNum, CorpNum, UserID, KakaoSentInfo.class);
    }

    @Override
    public KakaoSentInfo getMessagesRN(String CorpNum, String requestNum) throws PopbillException {
        return getMessagesRN(CorpNum, requestNum, null);
    }

    @Override
    public KakaoSentInfo getMessagesRN(String CorpNum, String requestNum, String UserID) throws PopbillException {
        if (requestNum == null || requestNum == "")
            throw new PopbillException(-99999999, "전송요청번호가 입력되지 않았습니다.");
        return httpget("/KakaoTalk/Get/" + requestNum, CorpNum, UserID, KakaoSentInfo.class);
    }

    @Override
    public KakaoSearchResult search(String CorpNum, String SDate, String EDate, String[] State, String[] Item,
            String ReserveYN, Boolean SenderYN, int Page, int PerPage, String Order) throws PopbillException {
        return search(CorpNum, SDate, EDate, State, Item, ReserveYN, SenderYN, Page, PerPage, Order, null);
    }

    @Override
    public KakaoSearchResult search(String CorpNum, String SDate, String EDate, String[] State, String[] Item,
            String ReserveYN, Boolean SenderYN, int Page, int PerPage, String Order, String UserID)
            throws PopbillException {
        return search(CorpNum, SDate, EDate, State, Item, ReserveYN, SenderYN, Page, PerPage, Order, UserID, null);
    }

    @Override
    public KakaoSearchResult search(String CorpNum, String SDate, String EDate, String[] State, String[] Item,
            String ReserveYN, Boolean SenderYN, int Page, int PerPage, String Order, String UserID, String QString)
            throws PopbillException {
        if (SDate == null)
            throw new PopbillException(-99999999, "시작일자가 입력되지 않았습니다.");
        if (EDate == null)
            throw new PopbillException(-99999999, "종료일자가 입력되지 않았습니다.");

        String uri = "/KakaoTalk/Search?SDate=" + SDate;
        uri += "&EDate=" + EDate;
        uri += "&State=" + Arrays.toString(State).replaceAll("\\[|\\]|\\s", "");

        uri += "&Item=" + Arrays.toString(Item).replaceAll("\\[|\\]|\\s", "");

        if (ReserveYN != null)
            uri += "&ReserveYN=" + ReserveYN;

        if (SenderYN) {
            uri += "&SenderYN=1";
        } else {
            uri += "&SenderYN=0";
        }

        uri += "&Page=" + Integer.toString(Page);
        uri += "&PerPage=" + Integer.toString(PerPage);
        uri += "&Order=" + Order;

        if (QString != null)
            uri += "&QString=" + QString;

        return httpget(uri, CorpNum, UserID, KakaoSearchResult.class);
    }

    @Override
    public String getPlusFriendMgtURL(String CorpNum, String UserID) throws PopbillException {

        URLResponse response = httpget("/KakaoTalk/?TG=PLUSFRIEND", CorpNum, UserID, URLResponse.class);

        return response.url;
    }

    @Override
    public String getSenderNumberMgtURL(String CorpNum, String UserID) throws PopbillException {

        URLResponse response = httpget("/Message/?TG=SENDER", CorpNum, UserID, URLResponse.class);

        return response.url;
    }

    @Override
    public String getATSTemplateMgtURL(String CorpNum, String UserID) throws PopbillException {

        URLResponse response = httpget("/KakaoTalk/?TG=TEMPLATE", CorpNum, UserID, URLResponse.class);

        return response.url;
    }

    @Override
    public String getSentListURL(String CorpNum, String UserID) throws PopbillException {

        URLResponse response = httpget("/KakaoTalk/?TG=BOX", CorpNum, UserID, URLResponse.class);

        return response.url;
    }

    protected class ATSSendRequest {
        public String templateCode;
        public String snd;
        public String content;
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
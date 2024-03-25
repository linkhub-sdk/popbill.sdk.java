package com.popbill.api.message;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.popbill.api.BaseServiceImp;
import com.popbill.api.ChargeInfo;
import com.popbill.api.MessageService;
import com.popbill.api.PopbillException;
import com.popbill.api.Response;

/**
 * Implementation of Popbill MessageService Interface
 *
 * @author KimSeongjun
 * @version 1.0.0
 * @see com.popbill.api.MessageService
 */
public class MessageServiceImp extends BaseServiceImp implements MessageService {

    @Override
    protected List<String> getScopes() {
        return Arrays.asList("150", "151", "152");
    }

    /*
     * (non-Javadoc)
     *
     * @see com.popbill.api.MessageService#getUnitCost(java.lang.String,
     * com.popbill.api.message.MessageType)
     */
    @Override
    public float getUnitCost(String CorpNum, MessageType MsgType) throws PopbillException {
        if (MsgType == null)
            throw new PopbillException(-99999999, "메시지 유형이 입력되지 않았습니다.");

        UnitCostResponse response = httpget("/Message/UnitCost?Type=" + MsgType.name(), CorpNum,
                null, UnitCostResponse.class);

        return response.unitCost;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.popbill.api.MessageService#getURL(java.lang.String, java.lang.String)
     */
    @Override
    public String getURL(String CorpNum, String TOGO) throws PopbillException {
        return getURL(CorpNum, null, TOGO);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.popbill.api.MessageService#getURL(java.lang.String, java.lang.String,
     * java.lang.String)
     */
    @Override
    public String getURL(String CorpNum, String UserID, String TOGO) throws PopbillException {

        URLResponse response = httpget("/Message/?TG=" + TOGO, CorpNum, UserID, URLResponse.class);

        return response.url;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.popbill.api.MessageService#sendSMS(java.lang.String, java.lang.String,
     * java.lang.String, java.lang.String, java.lang.String, java.util.Date, java.lang.String)
     */
    @Override
    public String sendSMS(String CorpNum, String Sender, String Receiver, String ReceiverName,
                          String Content, Date ReserveDT, String UserID) throws PopbillException {
        Message message = new Message();

        message.setSender(Sender);
        message.setReceiver(Receiver);
        message.setReceiverName(ReceiverName);
        message.setContent(Content);

        return sendSMS(CorpNum, new Message[]{message}, ReserveDT, UserID);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.popbill.api.MessageService#sendSMS(java.lang.String, java.lang.String,
     * java.lang.String, java.lang.String, java.lang.String, java.util.Date, java.lang.Boolean,
     * java.lang.String)
     */
    @Override
    public String sendSMS(String CorpNum, String Sender, String Receiver, String ReceiverName,
                          String Content, Date ReserveDT, Boolean AdsYN, String UserID) throws PopbillException {
        Message message = new Message();

        message.setSender(Sender);
        message.setReceiver(Receiver);
        message.setReceiverName(ReceiverName);
        message.setContent(Content);

        return sendSMS(CorpNum, null, null, new Message[]{message}, ReserveDT, AdsYN, UserID);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.popbill.api.MessageService#sendSMS(java.lang.String,
     * com.popbill.api.message.Message[], java.util.Date, java.lang.String)
     */
    @Override
    public String sendSMS(String CorpNum, Message[] Messages, Date ReserveDT, String UserID)
            throws PopbillException {
        return sendSMS(CorpNum, null, null, Messages, ReserveDT, UserID);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.popbill.api.MessageService#sendSMS(java.lang.String, java.lang.String,
     * java.lang.String, com.popbill.api.message.Message[], java.util.Date, java.lang.String)
     */
    @Override
    public String sendSMS(String CorpNum, String Sender, String Content, Message[] Messages,
                          Date ReserveDT, String UserID) throws PopbillException {
        return sendSMS(CorpNum, Sender, Content, Messages, ReserveDT, false, UserID);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.popbill.api.MessageService#sendSMS(java.lang.String, java.lang.String,
     * java.lang.String, com.popbill.api.message.Message[], java.util.Date, java.lang.Boolean,
     * java.lang.String)
     */
    @Override
    public String sendSMS(String CorpNum, String Sender, String Content, Message[] Messages,
                          Date ReserveDT, Boolean AdsYN, String UserID) throws PopbillException {
        return sendMessage(MessageType.SMS, CorpNum, Sender, null, null, Content, Messages,
                ReserveDT, AdsYN, UserID, null);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.popbill.api.MessageService#sendSMS(java.lang.String, java.lang.String,
     * java.lang.String, java.lang.String, com.popbill.api.message.Message[], java.util.Date,
     * java.lang.Boolean, java.lang.String)
     */
    @Override
    public String sendSMS(String CorpNum, String Sender, String SenderName, String Content,
                          Message[] Messages, Date ReserveDT, Boolean AdsYN, String UserID)
            throws PopbillException {
        return sendMessage(MessageType.SMS, CorpNum, Sender, SenderName, null, Content, Messages,
                ReserveDT, AdsYN, UserID, null);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.popbill.api.MessageService#sendSMS(java.lang.String, java.lang.String,
     * java.lang.String, java.lang.String, java.lang.String, java.util.Date, java.lang.String,
     * java.lang.String)
     */
    @Override
    public String sendSMS(String CorpNum, String Sender, String Receiver, String ReceiverName,
                          String Content, Date ReserveDT, String UserID, String RequestNum)
            throws PopbillException {
        Message message = new Message();

        message.setSender(Sender);
        message.setReceiver(Receiver);
        message.setReceiverName(ReceiverName);
        message.setContent(Content);

        return sendSMS(CorpNum, new Message[]{message}, ReserveDT, UserID, RequestNum);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.popbill.api.MessageService#sendSMS(java.lang.String, java.lang.String,
     * java.lang.String, java.lang.String, java.lang.String, java.util.Date, java.lang.Boolean,
     * java.lang.String, java.lang.String)
     */
    @Override
    public String sendSMS(String CorpNum, String Sender, String Receiver, String ReceiverName,
                          String Content, Date ReserveDT, Boolean AdsYN, String UserID, String RequestNum)
            throws PopbillException {
        Message message = new Message();

        message.setSender(Sender);
        message.setReceiver(Receiver);
        message.setReceiverName(ReceiverName);
        message.setContent(Content);

        return sendSMS(CorpNum, null, null, new Message[]{message}, ReserveDT, AdsYN, UserID,
                RequestNum);
    }


    /*
     * (non-Javadoc)
     *
     * @see com.popbill.api.MessageService#sendSMS(java.lang.String,
     * com.popbill.api.message.Message[], java.util.Date, java.lang.String, java.lang.String)
     */
    @Override
    public String sendSMS(String CorpNum, Message[] Messages, Date ReserveDT, String UserID,
                          String RequestNum) throws PopbillException {
        return sendSMS(CorpNum, null, null, Messages, ReserveDT, UserID, RequestNum);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.popbill.api.MessageService#sendSMS(java.lang.String, java.lang.String,
     * java.lang.String, com.popbill.api.message.Message[], java.util.Date, java.lang.String,
     * java.lang.String)
     */
    @Override
    public String sendSMS(String CorpNum, String Sender, String Content, Message[] Messages,
                          Date ReserveDT, String UserID, String RequestNum) throws PopbillException {
        return sendSMS(CorpNum, Sender, Content, Messages, ReserveDT, false, UserID, RequestNum);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.popbill.api.MessageService#sendSMS(java.lang.String, java.lang.String,
     * java.lang.String, com.popbill.api.message.Message[], java.util.Date, java.lang.Boolean,
     * java.lang.String, java.lang.String)
     */
    @Override
    public String sendSMS(String CorpNum, String Sender, String Content, Message[] Messages,
                          Date ReserveDT, Boolean AdsYN, String UserID, String RequestNum)
            throws PopbillException {
        return sendMessage(MessageType.SMS, CorpNum, Sender, null, null, Content, Messages,
                ReserveDT, AdsYN, UserID, RequestNum);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.popbill.api.MessageService#sendSMS(java.lang.String, java.lang.String,
     * java.lang.String, java.lang.String, com.popbill.api.message.Message[], java.util.Date,
     * java.lang.Boolean, java.lang.String, java.lang.String)
     */
    @Override
    public String sendSMS(String CorpNum, String Sender, String SenderName, String Content,
                          Message[] Messages, Date ReserveDT, Boolean AdsYN, String UserID, String RequestNum)
            throws PopbillException {
        return sendMessage(MessageType.SMS, CorpNum, Sender, SenderName, null, Content, Messages,
                ReserveDT, AdsYN, UserID, RequestNum);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.popbill.api.MessageService#sendLMS(java.lang.String, java.lang.String,
     * java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.util.Date,
     * java.lang.String)
     */
    @Override
    public String sendLMS(String CorpNum, String Sender, String Receiver, String ReceiverName,
                          String Subject, String Content, Date ReserveDT, String UserID) throws PopbillException {
        Message message = new Message();

        message.setSender(Sender);
        message.setReceiver(Receiver);
        message.setReceiverName(ReceiverName);
        message.setContent(Content);
        message.setSubject(Subject);

        return sendLMS(CorpNum, new Message[]{message}, ReserveDT, UserID);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.popbill.api.MessageService#sendLMS(java.lang.String, java.lang.String,
     * java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.util.Date,
     * java.lang.Boolean, java.lang.String)
     */
    @Override
    public String sendLMS(String CorpNum, String Sender, String Receiver, String ReceiverName,
                          String Subject, String Content, Date ReserveDT, Boolean AdsYN, String UserID)
            throws PopbillException {
        Message message = new Message();

        message.setSender(Sender);
        message.setReceiver(Receiver);
        message.setReceiverName(ReceiverName);
        message.setContent(Content);
        message.setSubject(Subject);

        return sendLMS(CorpNum, null, null, null, new Message[]{message}, ReserveDT, AdsYN,
                UserID);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.popbill.api.MessageService#sendLMS(java.lang.String,
     * com.popbill.api.message.Message[], java.util.Date, java.lang.String)
     */
    @Override
    public String sendLMS(String CorpNum, Message[] Messages, Date ReserveDT, String UserID)
            throws PopbillException {
        return sendLMS(CorpNum, null, null, null, Messages, ReserveDT, UserID);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.popbill.api.MessageService#sendLMS(java.lang.String, java.lang.String,
     * java.lang.String, java.lang.String, com.popbill.api.message.Message[], java.util.Date,
     * java.lang.String)
     */
    @Override
    public String sendLMS(String CorpNum, String Sender, String Subject, String Content,
                          Message[] Messages, Date ReserveDT, String UserID) throws PopbillException {
        return sendLMS(CorpNum, Sender, Subject, Content, Messages, ReserveDT, false, UserID);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.popbill.api.MessageService#sendLMS(java.lang.String, java.lang.String,
     * java.lang.String, java.lang.String, com.popbill.api.message.Message[], java.util.Date,
     * java.lang.Boolean, java.lang.String)
     */
    @Override
    public String sendLMS(String CorpNum, String Sender, String Subject, String Content,
                          Message[] Messages, Date ReserveDT, Boolean AdsYN, String UserID)
            throws PopbillException {
        return sendMessage(MessageType.LMS, CorpNum, Sender, null, Subject, Content, Messages,
                ReserveDT, AdsYN, UserID, null);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.popbill.api.MessageService#sendLMS(java.lang.String, java.lang.String,
     * java.lang.String, java.lang.String, java.lang.String, com.popbill.api.message.Message[],
     * java.util.Date, java.lang.Boolean, java.lang.String)
     */
    @Override
    public String sendLMS(String CorpNum, String Sender, String SenderName, String Subject,
                          String Content, Message[] Messages, Date ReserveDT, Boolean AdsYN, String UserID)
            throws PopbillException {
        return sendMessage(MessageType.LMS, CorpNum, Sender, SenderName, Subject, Content, Messages,
                ReserveDT, AdsYN, UserID, null);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.popbill.api.MessageService#sendLMS(java.lang.String, java.lang.String,
     * java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.util.Date,
     * java.lang.String, java.lang.String)
     */
    @Override
    public String sendLMS(String CorpNum, String Sender, String Receiver, String ReceiverName,
                          String Subject, String Content, Date ReserveDT, String UserID, String RequestNum)
            throws PopbillException {
        Message message = new Message();

        message.setSender(Sender);
        message.setReceiver(Receiver);
        message.setReceiverName(ReceiverName);
        message.setContent(Content);
        message.setSubject(Subject);

        return sendLMS(CorpNum, new Message[]{message}, ReserveDT, UserID, RequestNum);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.popbill.api.MessageService#sendLMS(java.lang.String, java.lang.String,
     * java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.util.Date,
     * java.lang.Boolean, java.lang.String, java.lang.String)
     */
    @Override
    public String sendLMS(String CorpNum, String Sender, String Receiver, String ReceiverName,
                          String Subject, String Content, Date ReserveDT, Boolean AdsYN, String UserID,
                          String RequestNum) throws PopbillException {
        Message message = new Message();

        message.setSender(Sender);
        message.setReceiver(Receiver);
        message.setReceiverName(ReceiverName);
        message.setContent(Content);
        message.setSubject(Subject);

        return sendLMS(CorpNum, null, null, null, new Message[]{message}, ReserveDT, AdsYN, UserID,
                RequestNum);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.popbill.api.MessageService#sendLMS(java.lang.String,
     * com.popbill.api.message.Message[], java.util.Date, java.lang.String, java.lang.String)
     */
    @Override
    public String sendLMS(String CorpNum, Message[] Messages, Date ReserveDT, String UserID,
                          String RequestNum) throws PopbillException {
        return sendLMS(CorpNum, null, null, null, Messages, ReserveDT, UserID, RequestNum);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.popbill.api.MessageService#sendLMS(java.lang.String, java.lang.String,
     * java.lang.String, java.lang.String, com.popbill.api.message.Message[], java.util.Date,
     * java.lang.String, java.lang.String)
     */
    @Override
    public String sendLMS(String CorpNum, String Sender, String Subject, String Content,
                          Message[] Messages, Date ReserveDT, String UserID, String RequestNum)
            throws PopbillException {
        return sendLMS(CorpNum, Sender, Subject, Content, Messages, ReserveDT, false, UserID,
                RequestNum);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.popbill.api.MessageService#sendLMS(java.lang.String, java.lang.String,
     * java.lang.String, java.lang.String, com.popbill.api.message.Message[], java.util.Date,
     * java.lang.Boolean, java.lang.String, java.lang.String)
     */
    @Override
    public String sendLMS(String CorpNum, String Sender, String Subject, String Content,
                          Message[] Messages, Date ReserveDT, Boolean AdsYN, String UserID, String RequestNum)
            throws PopbillException {
        return sendMessage(MessageType.LMS, CorpNum, Sender, null, Subject, Content, Messages,
                ReserveDT, AdsYN, UserID, RequestNum);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.popbill.api.MessageService#sendLMS(java.lang.String, java.lang.String,
     * java.lang.String, java.lang.String, java.lang.String, com.popbill.api.message.Message[],
     * java.util.Date, java.lang.Boolean, java.lang.String, java.lang.String)
     */
    @Override
    public String sendLMS(String CorpNum, String Sender, String SenderName, String Subject,
                          String Content, Message[] Messages, Date ReserveDT, Boolean AdsYN, String UserID,
                          String RequestNum) throws PopbillException {
        return sendMessage(MessageType.LMS, CorpNum, Sender, SenderName, Subject, Content, Messages,
                ReserveDT, AdsYN, UserID, RequestNum);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.popbill.api.MessageService#sendXMS(java.lang.String, java.lang.String,
     * java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.util.Date,
     * java.lang.String)
     */
    @Override
    public String sendXMS(String CorpNum, String Sender, String Receiver, String ReceiverName,
                          String Subject, String Content, Date ReserveDT, String UserID) throws PopbillException {
        Message message = new Message();

        message.setSender(Sender);
        message.setReceiver(Receiver);
        message.setReceiverName(ReceiverName);
        message.setContent(Content);
        message.setSubject(Subject);

        return sendXMS(CorpNum, new Message[]{message}, ReserveDT, UserID);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.popbill.api.MessageService#sendXMS(java.lang.String, java.lang.String,
     * java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.util.Date,
     * java.lang.String)
     */
    @Override
    public String sendXMS(String CorpNum, String Sender, String Receiver, String ReceiverName,
                          String Subject, String Content, Date ReserveDT, Boolean AdsYN, String UserID)
            throws PopbillException {
        Message message = new Message();

        message.setSender(Sender);
        message.setReceiver(Receiver);
        message.setReceiverName(ReceiverName);
        message.setContent(Content);
        message.setSubject(Subject);

        return sendXMS(CorpNum, null, null, null, new Message[]{message}, ReserveDT, AdsYN,
                UserID);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.popbill.api.MessageService#sendXMS(java.lang.String,
     * com.popbill.api.message.Message[], java.util.Date, java.lang.String)
     */
    @Override
    public String sendXMS(String CorpNum, Message[] Messages, Date ReserveDT, String UserID)
            throws PopbillException {
        return sendXMS(CorpNum, null, null, null, Messages, ReserveDT, UserID);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.popbill.api.MessageService#sendXMS(java.lang.String, java.lang.String,
     * java.lang.String, java.lang.String, com.popbill.api.message.Message[], java.util.Date,
     * java.lang.String)
     */
    @Override
    public String sendXMS(String CorpNum, String Sender, String Subject, String Content,
                          Message[] Messages, Date ReserveDT, String UserID) throws PopbillException {
        return sendXMS(CorpNum, Sender, Subject, Content, Messages, ReserveDT, false, UserID);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.popbill.api.MessageService#sendXMS(java.lang.String, java.lang.String,
     * java.lang.String, java.lang.String, com.popbill.api.message.Message[], java.util.Date,
     * java.lang.Boolean, java.lang.String)
     */
    @Override
    public String sendXMS(String CorpNum, String Sender, String Subject, String Content,
                          Message[] Messages, Date ReserveDT, Boolean AdsYN, String UserID)
            throws PopbillException {
        return sendMessage(MessageType.XMS, CorpNum, Sender, null, Subject, Content, Messages,
                ReserveDT, AdsYN, UserID, null);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.popbill.api.MessageService#sendXMS(java.lang.String, java.lang.String,
     * java.lang.String, java.lang.String, java.lang.String, com.popbill.api.message.Message[],
     * java.util.Date, java.lang.Boolean, java.lang.String)
     */
    @Override
    public String sendXMS(String CorpNum, String Sender, String SenderName, String Subject,
                          String Content, Message[] Messages, Date ReserveDT, Boolean AdsYN, String UserID)
            throws PopbillException {
        return sendMessage(MessageType.XMS, CorpNum, Sender, SenderName, Subject, Content, Messages,
                ReserveDT, AdsYN, UserID, null);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.popbill.api.MessageService#sendXMS(java.lang.String, java.lang.String,
     * java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.util.Date,
     * java.lang.String)
     */
    @Override
    public String sendXMS(String CorpNum, String Sender, String Receiver, String ReceiverName,
                          String Subject, String Content, Date ReserveDT, String UserID, String RequestNum)
            throws PopbillException {
        Message message = new Message();

        message.setSender(Sender);
        message.setReceiver(Receiver);
        message.setReceiverName(ReceiverName);
        message.setContent(Content);
        message.setSubject(Subject);

        return sendXMS(CorpNum, new Message[]{message}, ReserveDT, UserID, RequestNum);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.popbill.api.MessageService#sendXMS(java.lang.String, java.lang.String,
     * java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.util.Date,
     * java.lang.Boolean, java.lang.String)
     */
    @Override
    public String sendXMS(String CorpNum, String Sender, String Receiver, String ReceiverName,
                          String Subject, String Content, Date ReserveDT, Boolean AdsYN, String UserID,
                          String RequestNum) throws PopbillException {
        Message message = new Message();

        message.setSender(Sender);
        message.setReceiver(Receiver);
        message.setReceiverName(ReceiverName);
        message.setContent(Content);
        message.setSubject(Subject);

        return sendXMS(CorpNum, null, null, null, new Message[]{message}, ReserveDT, AdsYN, UserID,
                RequestNum);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.popbill.api.MessageService#sendXMS(java.lang.String,
     * com.popbill.api.message.Message[], java.util.Date, java.lang.String, java.lang.String)
     */
    @Override
    public String sendXMS(String CorpNum, Message[] Messages, Date ReserveDT, String UserID,
                          String RequestNum) throws PopbillException {
        return sendXMS(CorpNum, null, null, null, Messages, ReserveDT, UserID, RequestNum);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.popbill.api.MessageService#sendXMS(java.lang.String, java.lang.String,
     * java.lang.String, java.lang.String, com.popbill.api.message.Message[], java.util.Date,
     * java.lang.String, java.lang.String)
     */
    @Override
    public String sendXMS(String CorpNum, String Sender, String Subject, String Content,
                          Message[] Messages, Date ReserveDT, String UserID, String RequestNum)
            throws PopbillException {
        return sendXMS(CorpNum, Sender, Subject, Content, Messages, ReserveDT, false, UserID,
                RequestNum);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.popbill.api.MessageService#sendXMS(java.lang.String, java.lang.String,
     * java.lang.String, java.lang.String, com.popbill.api.message.Message[], java.util.Date,
     * java.lang.Boolean, java.lang.String, java.lang.String)
     */
    @Override
    public String sendXMS(String CorpNum, String Sender, String Subject, String Content,
                          Message[] Messages, Date ReserveDT, Boolean AdsYN, String UserID, String RequestNum)
            throws PopbillException {
        return sendMessage(MessageType.XMS, CorpNum, Sender, null, Subject, Content, Messages,
                ReserveDT, AdsYN, UserID, RequestNum);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.popbill.api.MessageService#sendXMS(java.lang.String, java.lang.String,
     * java.lang.String, java.lang.String, java.lang.String, com.popbill.api.message.Message[],
     * java.util.Date, java.lang.Boolean, java.lang.String, java.lang.String)
     */
    @Override
    public String sendXMS(String CorpNum, String Sender, String SenderName, String Subject,
                          String Content, Message[] Messages, Date ReserveDT, Boolean AdsYN, String UserID,
                          String RequestNum) throws PopbillException {
        return sendMessage(MessageType.XMS, CorpNum, Sender, SenderName, Subject, Content, Messages,
                ReserveDT, AdsYN, UserID, RequestNum);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.popbill.api.MessageService#getMessages(java.lang.String, java.lang.String)
     */
    @Override
    public SentMessage[] getMessages(String CorpNum, String ReceiptNum) throws PopbillException {
        if (ReceiptNum == null)
            throw new PopbillException(-99999999, "접수번호가 입력되지 않았습니다.");

        return httpget("/Message/" + ReceiptNum, CorpNum, null, SentMessage[].class);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.popbill.api.MessageService#getMessages(java.lang.String, java.lang.String,
     * java.lang.String)
     */
    @Override
    public SentMessage[] getMessages(String CorpNum, String ReceiptNum, String UserID)
            throws PopbillException {
        if (ReceiptNum == null)
            throw new PopbillException(-99999999, "접수번호가 입력되지 않았습니다.");

        return httpget("/Message/" + ReceiptNum, CorpNum, UserID, SentMessage[].class);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.popbill.api.MessageService#getMessagesRN(java.lang.String, java.lang.String)
     */
    @Override
    public SentMessage[] getMessagesRN(String CorpNum, String RequestNum) throws PopbillException {
        if (RequestNum == null)
            throw new PopbillException(-99999999, "전송요청번호가 입력되지 않았습니다.");

        return httpget("/Message/Get/" + RequestNum, CorpNum, null, SentMessage[].class);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.popbill.api.MessageService#getMessagesRN(java.lang.String, java.lang.String,
     * java.lang.String)
     */
    @Override
    public SentMessage[] getMessagesRN(String CorpNum, String RequestNum, String UserID)
            throws PopbillException {
        if (RequestNum == null)
            throw new PopbillException(-99999999, "전송요청번호가 입력되지 않았습니다.");

        return httpget("/Message/Get/" + RequestNum, CorpNum, UserID, SentMessage[].class);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.popbill.api.MessageService#getStates(java.lang.String, java.lang.String[])
     */
    @Override
    public MessageBriefInfo[] getStates(String corpNum, String[] ReceiptNumList)
            throws PopbillException {
        return getStates(corpNum, ReceiptNumList, null);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.popbill.api.MessageService#getStates(java.lang.String, java.lang.String[],
     * java.lang.String)
     */
    @Override
    public MessageBriefInfo[] getStates(String corpNum, String[] ReceiptNumList, String UserID)
            throws PopbillException {
        if (ReceiptNumList == null || ReceiptNumList.length == 0)
            throw new PopbillException(-99999999, "접수번호 목록이 입력되지 않았습니다.");

        String PostData = toJsonString(ReceiptNumList);

        return httppost("/Message/States", corpNum, PostData, UserID, MessageBriefInfo[].class);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.popbill.api.MessageService#cancelReserve(java.lang.String, java.lang.String)
     */
    @Override
    public Response cancelReserve(String CorpNum, String ReceiptNum) throws PopbillException {
        return cancelReserve(CorpNum, ReceiptNum, null);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.popbill.api.MessageService#cancelReserve(java.lang.String, java.lang.String,
     * java.lang.String)
     */
    @Override
    public Response cancelReserve(String CorpNum, String ReceiptNum, String UserID)
            throws PopbillException {
        if (ReceiptNum == null)
            throw new PopbillException(-99999999, "접수번호가 입력되지 않았습니다.");

        return httpget("/Message/" + ReceiptNum + "/Cancel", CorpNum, UserID, Response.class);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.popbill.api.MessageService#cancelReserveRN(java.lang.String, java.lang.String)
     */
    @Override
    public Response cancelReserveRN(String CorpNum, String RequestNum) throws PopbillException {
        return cancelReserveRN(CorpNum, RequestNum, null);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.popbill.api.MessageService#cancelReserveRN(java.lang.String, java.lang.String,
     * java.lang.String)
     */
    @Override
    public Response cancelReserveRN(String CorpNum, String RequestNum, String UserID)
            throws PopbillException {
        if (RequestNum == null)
            throw new PopbillException(-99999999, "전송요청번호가 입력되지 않았습니다.");

        return httpget("/Message/Cancel/" + RequestNum, CorpNum, UserID, Response.class);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.popbill.api.MessageService#sendMMS(java.lang.String, java.lang.String,
     * java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.io.File,
     * java.util.Date, java.lang.String)
     */
    @Override
    public String sendMMS(String CorpNum, String Sender, String Receiver, String ReceiverName,
                          String Subject, String Content, File File, Date ReserveDT, String UserID)
            throws PopbillException {

        Message message = new Message();

        message.setSender(Sender);
        message.setReceiver(Receiver);
        message.setReceiverName(ReceiverName);
        message.setContent(Content);
        message.setSubject(Subject);

        return sendMMS(CorpNum, new Message[]{message}, File, ReserveDT, UserID);
    }

    @Override
    public String sendMMS(String CorpNum, String Sender, String Receiver, String ReceiverName,
                          String Subject, String Content, File File, Date ReserveDT, Boolean AdsYN, String UserID)
            throws PopbillException {

        Message message = new Message();

        message.setSender(Sender);
        message.setReceiver(Receiver);
        message.setReceiverName(ReceiverName);
        message.setContent(Content);
        message.setSubject(Subject);

        return sendMMS(CorpNum, null, null, null, new Message[]{message}, File, ReserveDT, AdsYN,
                UserID);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.popbill.api.MessageService#sendMMS(java.lang.String,
     * com.popbill.api.message.Message[], java.io.File, java.util.Date, java.lang.String)
     */
    @Override
    public String sendMMS(String CorpNum, Message[] Messages, File File, Date ReserveDT,
                          String UserID) throws PopbillException {

        return sendMMS(CorpNum, null, null, null, Messages, File, ReserveDT, UserID);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.popbill.api.MessageService#sendMMS(java.lang.String, java.lang.String,
     * java.lang.String, java.lang.String, com.popbill.api.message.Message[], java.io.File,
     * java.util.Date, java.lang.String)
     */
    @Override
    public String sendMMS(String CorpNum, String Sender, String Subject, String Content,
                          Message[] Messages, File File, Date ReserveDT, String UserID) throws PopbillException {

        return sendMMS(CorpNum, Sender, Subject, Content, Messages, File, ReserveDT, false, UserID);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.popbill.api.MessageService#sendMMS(java.lang.String, java.lang.String,
     * java.lang.String, java.lang.String, com.popbill.api.message.Message[], java.io.File,
     * java.util.Date, java.lang.Boolean, java.lang.String)
     */
    @Override
    public String sendMMS(String CorpNum, String Sender, String Subject, String Content,
                          Message[] Messages, File File, Date ReserveDT, Boolean AdsYN, String UserID)
            throws PopbillException {
        return sendMMS(CorpNum, Sender, null, Subject, Content, Messages, File, ReserveDT, AdsYN,
                UserID);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.popbill.api.MessageService#sendMMS(java.lang.String, java.lang.String,
     * java.lang.String, java.lang.String, java.lang.String, com.popbill.api.message.Message[],
     * java.io.File, java.util.Date, java.lang.Boolean, java.lang.String)
     */
    @Override
    public String sendMMS(String CorpNum, String Sender, String SenderName, String Subject,
                          String Content, Message[] Messages, File File, Date ReserveDT, Boolean AdsYN,
                          String UserID) throws PopbillException {
        if (Messages == null || Messages.length == 0)
            throw new PopbillException(-99999999, "전송할 메시지가 입력되지 않았습니다.");

        SendRequest request = new SendRequest();
        request.snd = Sender;

        request.Content = Content;
        request.Subject = Subject;

        if (SenderName != null)
            request.sndnm = SenderName;

        if (AdsYN) {
            request.AdsYN = true;
        } else {
            request.AdsYN = false;
        }

        if (ReserveDT != null)
            request.sndDT = new SimpleDateFormat("yyyyMMddHHmmss", Locale.KOREA).format(ReserveDT);

        request.msgs = Messages;

        String PostData = toJsonString(request);

        List<UploadFile> uploadFiles = new ArrayList<UploadFile>();

        UploadFile uf = new UploadFile();
        uf.fieldName = "file";
        uf.fileName = File.getName();
        try {
            uf.fileData = new FileInputStream(File);
        } catch (FileNotFoundException e) {
            throw new PopbillException(-99999999, "전송할 파일을 찾을 수 없습니다.", e);
        }
        uploadFiles.add(uf);

        ReceiptResponse response = httppostFiles("/MMS", CorpNum, PostData, uploadFiles, UserID,
                ReceiptResponse.class);

        for (UploadFile f : uploadFiles) {
            if (f.fileData != null)
                try {
                    f.fileData.close();
                } catch (IOException e) {
                }
        }

        return response.receiptNum;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.popbill.api.MessageService#sendMMS(java.lang.String, java.lang.String,
     * java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.io.File,
     * java.util.Date, java.lang.String)
     */
    @Override
    public String sendMMS(String CorpNum, String Sender, String Receiver, String ReceiverName,
                          String Subject, String Content, File File, Date ReserveDT, String UserID,
                          String RequestNum) throws PopbillException {

        Message message = new Message();

        message.setSender(Sender);
        message.setReceiver(Receiver);
        message.setReceiverName(ReceiverName);
        message.setContent(Content);
        message.setSubject(Subject);

        return sendMMS(CorpNum, new Message[]{message}, File, ReserveDT, UserID, RequestNum);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.popbill.api.MessageService#sendMMS(java.lang.String, java.lang.String,
     * java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.io.File,
     * java.util.Date, java.lang.Boolean, java.lang.String)
     */
    @Override
    public String sendMMS(String CorpNum, String Sender, String Receiver, String ReceiverName,
                          String Subject, String Content, File File, Date ReserveDT, Boolean AdsYN, String UserID,
                          String RequestNum) throws PopbillException {

        Message message = new Message();

        message.setSender(Sender);
        message.setReceiver(Receiver);
        message.setReceiverName(ReceiverName);
        message.setContent(Content);
        message.setSubject(Subject);

        return sendMMS(CorpNum, null, null, null, new Message[]{message}, File, ReserveDT, AdsYN,
                UserID, RequestNum);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.popbill.api.MessageService#sendMMS(java.lang.String,
     * com.popbill.api.message.Message[], java.io.File, java.util.Date, java.lang.String)
     */
    @Override
    public String sendMMS(String CorpNum, Message[] Messages, File File, Date ReserveDT,
                          String UserID, String RequestNum) throws PopbillException {

        return sendMMS(CorpNum, null, null, null, Messages, File, ReserveDT, UserID, RequestNum);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.popbill.api.MessageService#sendMMS(java.lang.String, java.lang.String,
     * java.lang.String, java.lang.String, com.popbill.api.message.Message[], java.io.File,
     * java.util.Date, java.lang.String)
     */
    @Override
    public String sendMMS(String CorpNum, String Sender, String Subject, String Content,
                          Message[] Messages, File File, Date ReserveDT, String UserID, String RequestNum)
            throws PopbillException {

        return sendMMS(CorpNum, Sender, Subject, Content, Messages, File, ReserveDT, false, UserID,
                RequestNum);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.popbill.api.MessageService#sendMMS(java.lang.String, java.lang.String,
     * java.lang.String, java.lang.String, com.popbill.api.message.Message[], java.io.File,
     * java.util.Date, java.lang.Boolean, java.lang.String)
     */
    @Override
    public String sendMMS(String CorpNum, String Sender, String Subject, String Content,
                          Message[] Messages, File File, Date ReserveDT, Boolean AdsYN, String UserID,
                          String RequestNum) throws PopbillException {
        return sendMMS(CorpNum, Sender, null, Subject, Content, Messages, File, ReserveDT, AdsYN,
                UserID, RequestNum);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.popbill.api.MessageService#sendMMS(java.lang.String, java.lang.String,
     * java.lang.String, java.lang.String, java.lang.String, com.popbill.api.message.Message[],
     * java.io.File, java.util.Date, java.lang.Boolean, java.lang.String)
     */
    @Override
    public String sendMMS(String CorpNum, String Sender, String SenderName, String Subject,
                          String Content, Message[] Messages, File File, Date ReserveDT, Boolean AdsYN,
                          String UserID, String RequestNum) throws PopbillException {
        if (Messages == null || Messages.length == 0)
            throw new PopbillException(-99999999, "전송할 메시지가 입력되지 않았습니다.");

        SendRequest request = new SendRequest();
        request.snd = Sender;

        request.Content = Content;
        request.Subject = Subject;

        if (SenderName != null)
            request.sndnm = SenderName;

        if (AdsYN) {
            request.AdsYN = true;
        } else {
            request.AdsYN = false;
        }

        if (ReserveDT != null)
            request.sndDT = new SimpleDateFormat("yyyyMMddHHmmss", Locale.KOREA).format(ReserveDT);

        request.msgs = Messages;

        if (RequestNum != null)
            request.requestNum = RequestNum;

        String PostData = toJsonString(request);

        List<UploadFile> uploadFiles = new ArrayList<UploadFile>();

        UploadFile uf = new UploadFile();
        uf.fieldName = "file";
        uf.fileName = File.getName();
        try {
            uf.fileData = new FileInputStream(File);
        } catch (FileNotFoundException e) {
            throw new PopbillException(-99999999, "전송할 파일을 찾을 수 없습니다.", e);
        }
        uploadFiles.add(uf);

        ReceiptResponse response = httppostFiles("/MMS", CorpNum, PostData, uploadFiles, UserID,
                ReceiptResponse.class);

        for (UploadFile f : uploadFiles) {
            if (f.fileData != null)
                try {
                    f.fileData.close();
                } catch (IOException e) {
                }
        }

        return response.receiptNum;
    }

    private String sendMessage(MessageType MsgType, String CorpNum, String Sender,
                               String SenderName, String Subject, String Content, Message[] Messages, Date ReserveDT,
                               Boolean AdsYN, String UserID, String RequestNum) throws PopbillException {
        if (MsgType == null)
            throw new PopbillException(-99999999, "메시지 유형이 입력되지 않았습니다.");
        if (CorpNum == null || CorpNum.isEmpty())
            throw new PopbillException(-99999999, "회원 사업자번호가 입력되지 않았습니다.");

        if (Messages == null || Messages.length == 0)
            throw new PopbillException(-99999999, "전송할 메시지가 입력되지 않았습니다.");

        SendRequest request = new SendRequest();
        request.snd = Sender;
        request.Content = Content;
        request.Subject = Subject;

        if (SenderName != null)
            request.sndnm = SenderName;

        if (AdsYN) {
            request.AdsYN = true;
        } else {
            request.AdsYN = false;
        }

        if (ReserveDT != null)
            request.sndDT = new SimpleDateFormat("yyyyMMddHHmmss", Locale.KOREA).format(ReserveDT);

        request.msgs = Messages;

        if (RequestNum != null)
            request.requestNum = RequestNum;

        String PostData = toJsonString(request);

        ReceiptResponse response =
                httppost("/" + MsgType.name(), CorpNum, PostData, UserID, ReceiptResponse.class);

        return response.receiptNum;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.popbill.api.MessageService#search(java.lang.String, java.lang.String,
     * java.lang.String, java.lang.String[], java.lang.String[], java.lang.Boolean,
     * java.lang.Boolean, int, int, java.lang.String)
     */
    @Override
    public MSGSearchResult search(String CorpNum, String SDate, String EDate, String[] State,
                                  String[] Item, Boolean ReserveYN, Boolean SenderYN, Integer Page, Integer PerPage, String Order)
            throws PopbillException {
        return search(CorpNum, SDate, EDate, State, Item, ReserveYN, SenderYN, Page, PerPage, Order,
                null);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.popbill.api.MessageService#search(java.lang.String, java.lang.String,
     * java.lang.String, java.lang.String[], java.lang.String[], java.lang.Boolean,
     * java.lang.Boolean, int, int, java.lang.String)
     */
    @Override
    public MSGSearchResult search(String CorpNum, String SDate, String EDate, String[] State,
                                  String[] Item, Boolean ReserveYN, Boolean SenderYN, Integer Page, Integer PerPage, String Order,
                                  String QString) throws PopbillException {
        return search(CorpNum, SDate, EDate, State, Item, ReserveYN, SenderYN, Page, PerPage, Order, QString, null);
    }

    @Override
    public MSGSearchResult search(String CorpNum, String SDate, String EDate, String[] State, String[] Item, Boolean ReserveYN, Boolean SenderYN, Integer Page, Integer PerPage, String Order, String QString, String UserID) throws PopbillException {

        if (SDate == null)
            throw new PopbillException(-99999999, "시작일자가 입력되지 않았습니다.");
        if (EDate == null)
            throw new PopbillException(-99999999, "종료일자가 입력되지 않았습니다.");

        String uri = "/Message/Search?SDate=" + SDate;
        uri += "&EDate=" + EDate;
        uri += "&State=" + Arrays.toString(State == null ? new String[]{} : State).replaceAll("\\[|\\]|\\s", "");

        uri += "&Item=" + Arrays.toString(Item).replaceAll("\\[|\\]|\\s", "");
        if (ReserveYN != null)
            uri += "&ReserveYN=" + ReserveYN;

        if(SenderYN != null)
            uri += "&SenderYN=" + SenderYN;


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

        return httpget(uri, CorpNum, UserID, MSGSearchResult.class);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.popbill.api.MessageService#getAutoDenyList(java.lang.String)
     */
    @Override
    public AutoDeny[] getAutoDenyList(String CorpNum) throws PopbillException {
        return getAutoDenyList(CorpNum, null);
    }

    @Override
    public AutoDeny[] getAutoDenyList(String CorpNum, String UserID) throws PopbillException {
        return httpget("/Message/Denied", CorpNum, UserID, AutoDeny[].class);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.popbill.api.MessageService#checkAutoDenyNumber(java.lang.String)
     */
    @Override
    public AutoDenyNumberInfo checkAutoDenyNumber(String CorpNum) throws PopbillException {
        return checkAutoDenyNumber(CorpNum, null);
    }


    /*
     * (non-Javadoc)
     *
     * @see com.popbill.api.MessageService#checkAutoDenyNumber(java.lang.String, java.lang.String)
     */
    @Override
    public AutoDenyNumberInfo checkAutoDenyNumber(String CorpNum, String UserID)
            throws PopbillException {
        return httpget("/Message/AutoDenyNumberInfo", CorpNum, UserID, AutoDenyNumberInfo.class);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.popbill.api.MessageService#getChargeInfo(java.lang.String,
     * com.popbill.api.message.MessageType)
     */
    @Override
    public ChargeInfo getChargeInfo(String CorpNum, MessageType MsgType) throws PopbillException {
        return getChargeInfo(CorpNum, MsgType, null);
    }

    @Override
    public ChargeInfo getChargeInfo(String CorpNum, MessageType MsgType, String UserID) throws PopbillException {
        return httpget("/Message/ChargeInfo?Type=" + MsgType.name(), CorpNum, UserID,
                ChargeInfo.class);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.popbill.api.MessageService#getSenderNumberList(java.lang.String)
     */
    @Override
    public SenderNumber[] getSenderNumberList(String CorpNum) throws PopbillException {
        return getSenderNumberList(CorpNum, null);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.popbill.api.MessageService#getSenderNumberList(java.lang.String, java.lang.String)
     */
    @Override
    public SenderNumber[] getSenderNumberList(String CorpNum, String UserID)
            throws PopbillException {
        return httpget("/Message/SenderNumber", CorpNum, null, SenderNumber[].class);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.popbill.api.MessageService#getSenderNumberMgtURL(java.lang.String, java.lang.String)
     */
    @Override
    public String getSenderNumberMgtURL(String CorpNum, String UserID) throws PopbillException {

        URLResponse response = httpget("/Message/?TG=SENDER", CorpNum, UserID, URLResponse.class);

        return response.url;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.popbill.api.MessageService#getSentListURL(java.lang.String, java.lang.String)
     */
    @Override
    public String getSentListURL(String CorpNum, String UserID) throws PopbillException {

        URLResponse response = httpget("/Message/?TG=BOX", CorpNum, UserID, URLResponse.class);

        return response.url;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.popbill.api.MessageService#checkSenderNumber(java.lang.String, java.lang.String)
     */
    @Override
    public Response checkSenderNumber(String CorpNum, String SenderNumber) throws PopbillException {
        return checkSenderNumber(CorpNum, SenderNumber, null);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.popbill.api.MessageService#checkSenderNumber(java.lang.String, java.lang.String,
     * java.lang.String)
     */
    @Override
    public Response checkSenderNumber(String CorpNum, String SenderNumber, String UserID)
            throws PopbillException {
        if (SenderNumber == null || SenderNumber.isEmpty())
            throw new PopbillException(-99999999, "발신번호가 입력되지 않았습니다.");
        return httpget("/Message/CheckSenderNumber/" + SenderNumber, CorpNum, UserID,
                Response.class);
    }

    protected class SendRequest {
        public String snd;
        public String sndnm;
        public String Content;
        public String Subject;
        public String sndDT;
        public Boolean AdsYN;
        public String requestNum;

        public Message[] msgs;
    }

    protected class ReceiptResponse {
        public String receiptNum;
    }

    @Override
    public Response cancelReservebyRCV(String CorpNum, String ReceiptNum, String ReceiveNum)
            throws PopbillException {
        return cancelReservebyRCV(CorpNum, ReceiptNum, ReceiveNum, null);
    }

    @Override
    public Response cancelReservebyRCV(String CorpNum, String ReceiptNum, String ReceiveNum,
                                       String UserID) throws PopbillException {
        if (ReceiptNum == null || ReceiptNum.isEmpty())
            throw new PopbillException(-99999999, "접수번호가 입력되지 않았습니다.");
        if (ReceiveNum == null || ReceiveNum.isEmpty())
            throw new PopbillException(-99999999, "수신번호가 입력되지 않았습니다.");

        String PostData = toJsonString(ReceiveNum);

        return httppost("/Message/" + ReceiptNum + "/Cancel", CorpNum, PostData, UserID,
                Response.class);
    }

    @Override
    public Response cancelReserveRNbyRCV(String CorpNum, String RequestNum, String ReceiveNum)
            throws PopbillException {
        return cancelReserveRNbyRCV(CorpNum, RequestNum, ReceiveNum, null);
    }

    @Override
    public Response cancelReserveRNbyRCV(String CorpNum, String RequestNum, String ReceiveNum,
                                         String UserID) throws PopbillException {
        if (RequestNum == null || RequestNum.isEmpty())
            throw new PopbillException(-99999999, "전송요청번호가 입력되지 않았습니다.");
        if (ReceiveNum == null || ReceiveNum.isEmpty())
            throw new PopbillException(-99999999, "수신번호가 입력되지 않았습니다.");

        String PostData = toJsonString(ReceiveNum);

        return httppost("/Message/Cancel/" + RequestNum, CorpNum, PostData, UserID, Response.class);
    }

}
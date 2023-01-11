/*
 * Copyright 2006-2014 linkhub.co.kr, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0.txt
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */
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

        UnitCostResponse response = httpget("/Message/UnitCost?Type=" + MsgType.name(), CorpNum, null, UnitCostResponse.class);

        return response.unitCost;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.MessageService#getURL(java.lang.String,
     * java.lang.String)
     */
    @Override
    public String getURL(String CorpNum, String TOGO) throws PopbillException {
        return getURL(CorpNum, null, TOGO);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.MessageService#getURL(java.lang.String,
     * java.lang.String, java.lang.String)
     */
    @Override
    public String getURL(String CorpNum, String UserID, String TOGO) throws PopbillException {

        URLResponse response = httpget("/Message/?TG=" + TOGO, CorpNum, UserID, URLResponse.class);

        return response.url;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.MessageService#sendSMS(java.lang.String,
     * java.lang.String, java.lang.String, java.lang.String, java.lang.String,
     * java.util.Date, java.lang.String)
     */
    @Override
    public String sendSMS(String CorpNum, String sender, String receiver, String receiverName, String content, Date reserveDT, String UserID)
            throws PopbillException {
        Message message = new Message();

        message.setSender(sender);
        message.setReceiver(receiver);
        message.setReceiverName(receiverName);
        message.setContent(content);

        return sendSMS(CorpNum, new Message[] { message }, reserveDT, UserID);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.MessageService#sendSMS(java.lang.String,
     * java.lang.String, java.lang.String, java.lang.String, java.lang.String,
     * java.util.Date, java.lang.Boolean, java.lang.String)
     */
    @Override
    public String sendSMS(String CorpNum, String sender, String receiver, String receiverName, String content, Date reserveDT, Boolean adsYN,
            String UserID) throws PopbillException {
        Message message = new Message();

        message.setSender(sender);
        message.setReceiver(receiver);
        message.setReceiverName(receiverName);
        message.setContent(content);

        return sendSMS(CorpNum, null, null, new Message[] { message }, reserveDT, adsYN, UserID);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.MessageService#sendSMS(java.lang.String,
     * com.popbill.api.message.Message[], java.util.Date, java.lang.String)
     */
    @Override
    public String sendSMS(String CorpNum, Message[] Messages, Date reserveDT, String UserID) throws PopbillException {
        return sendSMS(CorpNum, null, null, Messages, reserveDT, UserID);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.MessageService#sendSMS(java.lang.String,
     * java.lang.String, java.lang.String, com.popbill.api.message.Message[],
     * java.util.Date, java.lang.String)
     */
    @Override
    public String sendSMS(String CorpNum, String sender, String content, Message[] Messages, Date reserveDT, String UserID) throws PopbillException {
        return sendSMS(CorpNum, sender, content, Messages, reserveDT, false, UserID);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.MessageService#sendSMS(java.lang.String,
     * java.lang.String, java.lang.String, com.popbill.api.message.Message[],
     * java.util.Date, java.lang.Boolean, java.lang.String)
     */
    @Override
    public String sendSMS(String CorpNum, String sender, String content, Message[] Messages, Date reserveDT, Boolean adsYN, String UserID)
            throws PopbillException {
        return sendMessage(MessageType.SMS, CorpNum, sender, null, null, content, Messages, reserveDT, adsYN, UserID, null);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.MessageService#sendSMS(java.lang.String,
     * java.lang.String, java.lang.String, java.lang.String,
     * com.popbill.api.message.Message[], java.util.Date, java.lang.Boolean,
     * java.lang.String)
     */
    @Override
    public String sendSMS(String CorpNum, String sender, String senderName, String content, Message[] Messages, Date reserveDT, Boolean adsYN,
            String UserID) throws PopbillException {
        return sendMessage(MessageType.SMS, CorpNum, sender, senderName, null, content, Messages, reserveDT, adsYN, UserID, null);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.MessageService#sendSMS(java.lang.String,
     * java.lang.String, java.lang.String, java.lang.String, java.lang.String,
     * java.util.Date, java.lang.String, java.lang.String)
     */
    @Override
    public String sendSMS(String CorpNum, String sender, String receiver, String receiverName, String content, Date reserveDT, String UserID,
            String requestNum) throws PopbillException {
        Message message = new Message();

        message.setSender(sender);
        message.setReceiver(receiver);
        message.setReceiverName(receiverName);
        message.setContent(content);

        return sendSMS(CorpNum, new Message[] { message }, reserveDT, UserID, requestNum);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.MessageService#sendSMS(java.lang.String,
     * java.lang.String, java.lang.String, java.lang.String, java.lang.String,
     * java.util.Date, java.lang.Boolean, java.lang.String, java.lang.String)
     */
    @Override
    public String sendSMS(String CorpNum, String sender, String receiver, String receiverName, String content, Date reserveDT, Boolean adsYN,
            String UserID, String requestNum) throws PopbillException {
        Message message = new Message();

        message.setSender(sender);
        message.setReceiver(receiver);
        message.setReceiverName(receiverName);
        message.setContent(content);

        return sendSMS(CorpNum, null, null, new Message[] { message }, reserveDT, adsYN, UserID, requestNum);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.MessageService#sendSMS(java.lang.String,
     * com.popbill.api.message.Message[], java.util.Date, java.lang.String,
     * java.lang.String)
     */
    @Override
    public String sendSMS(String CorpNum, Message[] Messages, Date reserveDT, String UserID, String requestNum) throws PopbillException {
        return sendSMS(CorpNum, null, null, Messages, reserveDT, UserID, requestNum);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.MessageService#sendSMS(java.lang.String,
     * java.lang.String, java.lang.String, com.popbill.api.message.Message[],
     * java.util.Date, java.lang.String, java.lang.String)
     */
    @Override
    public String sendSMS(String CorpNum, String sender, String content, Message[] Messages, Date reserveDT, String UserID, String requestNum)
            throws PopbillException {
        return sendSMS(CorpNum, sender, content, Messages, reserveDT, false, UserID, requestNum);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.MessageService#sendSMS(java.lang.String,
     * java.lang.String, java.lang.String, com.popbill.api.message.Message[],
     * java.util.Date, java.lang.Boolean, java.lang.String, java.lang.String)
     */
    @Override
    public String sendSMS(String CorpNum, String sender, String content, Message[] Messages, Date reserveDT, Boolean adsYN, String UserID,
            String requestNum) throws PopbillException {
        return sendMessage(MessageType.SMS, CorpNum, sender, null, null, content, Messages, reserveDT, adsYN, UserID, requestNum);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.MessageService#sendSMS(java.lang.String,
     * java.lang.String, java.lang.String, java.lang.String,
     * com.popbill.api.message.Message[], java.util.Date, java.lang.Boolean,
     * java.lang.String, java.lang.String)
     */
    @Override
    public String sendSMS(String CorpNum, String sender, String senderName, String content, Message[] Messages, Date reserveDT, Boolean adsYN,
            String UserID, String requestNum) throws PopbillException {
        return sendMessage(MessageType.SMS, CorpNum, sender, senderName, null, content, Messages, reserveDT, adsYN, UserID, requestNum);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.MessageService#sendLMS(java.lang.String,
     * java.lang.String, java.lang.String, java.lang.String, java.lang.String,
     * java.lang.String, java.util.Date, java.lang.String)
     */
    @Override
    public String sendLMS(String CorpNum, String sender, String receiver, String receiverName, String subject, String content, Date reserveDT,
            String UserID) throws PopbillException {
        Message message = new Message();

        message.setSender(sender);
        message.setReceiver(receiver);
        message.setReceiverName(receiverName);
        message.setContent(content);
        message.setSubject(subject);

        return sendLMS(CorpNum, new Message[] { message }, reserveDT, UserID);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.MessageService#sendLMS(java.lang.String,
     * java.lang.String, java.lang.String, java.lang.String, java.lang.String,
     * java.lang.String, java.util.Date, java.lang.Boolean, java.lang.String)
     */
    @Override
    public String sendLMS(String CorpNum, String sender, String receiver, String receiverName, String subject, String content, Date reserveDT,
            Boolean adsYN, String UserID) throws PopbillException {
        Message message = new Message();

        message.setSender(sender);
        message.setReceiver(receiver);
        message.setReceiverName(receiverName);
        message.setContent(content);
        message.setSubject(subject);

        return sendLMS(CorpNum, null, null, null, new Message[] { message }, reserveDT, adsYN, UserID);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.MessageService#sendLMS(java.lang.String,
     * com.popbill.api.message.Message[], java.util.Date, java.lang.String)
     */
    @Override
    public String sendLMS(String CorpNum, Message[] Messages, Date reserveDT, String UserID) throws PopbillException {
        return sendLMS(CorpNum, null, null, null, Messages, reserveDT, UserID);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.MessageService#sendLMS(java.lang.String,
     * java.lang.String, java.lang.String, java.lang.String,
     * com.popbill.api.message.Message[], java.util.Date, java.lang.String)
     */
    @Override
    public String sendLMS(String CorpNum, String sender, String subject, String content, Message[] Messages, Date reserveDT, String UserID)
            throws PopbillException {
        return sendLMS(CorpNum, sender, subject, content, Messages, reserveDT, false, UserID);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.MessageService#sendLMS(java.lang.String,
     * java.lang.String, java.lang.String, java.lang.String,
     * com.popbill.api.message.Message[], java.util.Date, java.lang.Boolean,
     * java.lang.String)
     */
    @Override
    public String sendLMS(String CorpNum, String sender, String subject, String content, Message[] Messages, Date reserveDT, Boolean adsYN,
            String UserID) throws PopbillException {
        return sendMessage(MessageType.LMS, CorpNum, sender, null, subject, content, Messages, reserveDT, adsYN, UserID, null);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.MessageService#sendLMS(java.lang.String,
     * java.lang.String, java.lang.String, java.lang.String, java.lang.String,
     * com.popbill.api.message.Message[], java.util.Date, java.lang.Boolean,
     * java.lang.String)
     */
    @Override
    public String sendLMS(String CorpNum, String sender, String senderName, String subject, String content, Message[] Messages, Date reserveDT,
            Boolean adsYN, String UserID) throws PopbillException {
        return sendMessage(MessageType.LMS, CorpNum, sender, senderName, subject, content, Messages, reserveDT, adsYN, UserID, null);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.MessageService#sendLMS(java.lang.String,
     * java.lang.String, java.lang.String, java.lang.String, java.lang.String,
     * java.lang.String, java.util.Date, java.lang.String, java.lang.String)
     */
    @Override
    public String sendLMS(String CorpNum, String sender, String receiver, String receiverName, String subject, String content, Date reserveDT,
            String UserID, String requestNum) throws PopbillException {
        Message message = new Message();

        message.setSender(sender);
        message.setReceiver(receiver);
        message.setReceiverName(receiverName);
        message.setContent(content);
        message.setSubject(subject);

        return sendLMS(CorpNum, new Message[] { message }, reserveDT, UserID, requestNum);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.MessageService#sendLMS(java.lang.String,
     * java.lang.String, java.lang.String, java.lang.String, java.lang.String,
     * java.lang.String, java.util.Date, java.lang.Boolean, java.lang.String,
     * java.lang.String)
     */
    @Override
    public String sendLMS(String CorpNum, String sender, String receiver, String receiverName, String subject, String content, Date reserveDT,
            Boolean adsYN, String UserID, String requestNum) throws PopbillException {
        Message message = new Message();

        message.setSender(sender);
        message.setReceiver(receiver);
        message.setReceiverName(receiverName);
        message.setContent(content);
        message.setSubject(subject);

        return sendLMS(CorpNum, null, null, null, new Message[] { message }, reserveDT, adsYN, UserID, requestNum);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.MessageService#sendLMS(java.lang.String,
     * com.popbill.api.message.Message[], java.util.Date, java.lang.String,
     * java.lang.String)
     */
    @Override
    public String sendLMS(String CorpNum, Message[] Messages, Date reserveDT, String UserID, String requestNum) throws PopbillException {
        return sendLMS(CorpNum, null, null, null, Messages, reserveDT, UserID, requestNum);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.MessageService#sendLMS(java.lang.String,
     * java.lang.String, java.lang.String, java.lang.String,
     * com.popbill.api.message.Message[], java.util.Date, java.lang.String,
     * java.lang.String)
     */
    @Override
    public String sendLMS(String CorpNum, String sender, String subject, String content, Message[] Messages, Date reserveDT, String UserID,
            String requestNum) throws PopbillException {
        return sendLMS(CorpNum, sender, subject, content, Messages, reserveDT, false, UserID, requestNum);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.MessageService#sendLMS(java.lang.String,
     * java.lang.String, java.lang.String, java.lang.String,
     * com.popbill.api.message.Message[], java.util.Date, java.lang.Boolean,
     * java.lang.String, java.lang.String)
     */
    @Override
    public String sendLMS(String CorpNum, String sender, String subject, String content, Message[] Messages, Date reserveDT, Boolean adsYN,
            String UserID, String requestNum) throws PopbillException {
        return sendMessage(MessageType.LMS, CorpNum, sender, null, subject, content, Messages, reserveDT, adsYN, UserID, requestNum);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.MessageService#sendLMS(java.lang.String,
     * java.lang.String, java.lang.String, java.lang.String, java.lang.String,
     * com.popbill.api.message.Message[], java.util.Date, java.lang.Boolean,
     * java.lang.String, java.lang.String)
     */
    @Override
    public String sendLMS(String CorpNum, String sender, String senderName, String subject, String content, Message[] Messages, Date reserveDT,
            Boolean adsYN, String UserID, String requestNum) throws PopbillException {
        return sendMessage(MessageType.LMS, CorpNum, sender, senderName, subject, content, Messages, reserveDT, adsYN, UserID, requestNum);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.MessageService#sendXMS(java.lang.String,
     * java.lang.String, java.lang.String, java.lang.String, java.lang.String,
     * java.lang.String, java.util.Date, java.lang.String)
     */
    @Override
    public String sendXMS(String CorpNum, String sender, String receiver, String receiverName, String subject, String content, Date reserveDT,
            String UserID) throws PopbillException {
        Message message = new Message();

        message.setSender(sender);
        message.setReceiver(receiver);
        message.setReceiverName(receiverName);
        message.setContent(content);
        message.setSubject(subject);

        return sendXMS(CorpNum, new Message[] { message }, reserveDT, UserID);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.MessageService#sendXMS(java.lang.String,
     * java.lang.String, java.lang.String, java.lang.String, java.lang.String,
     * java.lang.String, java.util.Date, java.lang.String)
     */
    @Override
    public String sendXMS(String CorpNum, String sender, String receiver, String receiverName, String subject, String content, Date reserveDT,
            Boolean adsYN, String UserID) throws PopbillException {
        Message message = new Message();

        message.setSender(sender);
        message.setReceiver(receiver);
        message.setReceiverName(receiverName);
        message.setContent(content);
        message.setSubject(subject);

        return sendXMS(CorpNum, null, null, null, new Message[] { message }, reserveDT, adsYN, UserID);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.MessageService#sendXMS(java.lang.String,
     * com.popbill.api.message.Message[], java.util.Date, java.lang.String)
     */
    @Override
    public String sendXMS(String CorpNum, Message[] Messages, Date reserveDT, String UserID) throws PopbillException {
        return sendXMS(CorpNum, null, null, null, Messages, reserveDT, UserID);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.MessageService#sendXMS(java.lang.String,
     * java.lang.String, java.lang.String, java.lang.String,
     * com.popbill.api.message.Message[], java.util.Date, java.lang.String)
     */
    @Override
    public String sendXMS(String CorpNum, String sender, String subject, String content, Message[] Messages, Date reserveDT, String UserID)
            throws PopbillException {
        return sendXMS(CorpNum, sender, subject, content, Messages, reserveDT, false, UserID);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.MessageService#sendXMS(java.lang.String,
     * java.lang.String, java.lang.String, java.lang.String,
     * com.popbill.api.message.Message[], java.util.Date, java.lang.Boolean,
     * java.lang.String)
     */
    @Override
    public String sendXMS(String CorpNum, String sender, String subject, String content, Message[] Messages, Date reserveDT, Boolean adsYN,
            String UserID) throws PopbillException {
        return sendMessage(MessageType.XMS, CorpNum, sender, null, subject, content, Messages, reserveDT, adsYN, UserID, null);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.MessageService#sendXMS(java.lang.String,
     * java.lang.String, java.lang.String, java.lang.String, java.lang.String,
     * com.popbill.api.message.Message[], java.util.Date, java.lang.Boolean,
     * java.lang.String)
     */
    @Override
    public String sendXMS(String CorpNum, String sender, String senderName, String subject, String content, Message[] Messages, Date reserveDT,
            Boolean adsYN, String UserID) throws PopbillException {
        return sendMessage(MessageType.XMS, CorpNum, sender, senderName, subject, content, Messages, reserveDT, adsYN, UserID, null);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.MessageService#sendXMS(java.lang.String,
     * java.lang.String, java.lang.String, java.lang.String, java.lang.String,
     * java.lang.String, java.util.Date, java.lang.String)
     */
    @Override
    public String sendXMS(String CorpNum, String sender, String receiver, String receiverName, String subject, String content, Date reserveDT,
            String UserID, String requestNum) throws PopbillException {
        Message message = new Message();

        message.setSender(sender);
        message.setReceiver(receiver);
        message.setReceiverName(receiverName);
        message.setContent(content);
        message.setSubject(subject);

        return sendXMS(CorpNum, new Message[] { message }, reserveDT, UserID, requestNum);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.MessageService#sendXMS(java.lang.String,
     * java.lang.String, java.lang.String, java.lang.String, java.lang.String,
     * java.lang.String, java.util.Date, java.lang.Boolean, java.lang.String)
     */
    @Override
    public String sendXMS(String CorpNum, String sender, String receiver, String receiverName, String subject, String content, Date reserveDT,
            Boolean adsYN, String UserID, String requestNum) throws PopbillException {
        Message message = new Message();

        message.setSender(sender);
        message.setReceiver(receiver);
        message.setReceiverName(receiverName);
        message.setContent(content);
        message.setSubject(subject);

        return sendXMS(CorpNum, null, null, null, new Message[] { message }, reserveDT, adsYN, UserID, requestNum);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.MessageService#sendXMS(java.lang.String,
     * com.popbill.api.message.Message[], java.util.Date, java.lang.String,
     * java.lang.String)
     */
    @Override
    public String sendXMS(String CorpNum, Message[] Messages, Date reserveDT, String UserID, String requestNum) throws PopbillException {
        return sendXMS(CorpNum, null, null, null, Messages, reserveDT, UserID, requestNum);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.MessageService#sendXMS(java.lang.String,
     * java.lang.String, java.lang.String, java.lang.String,
     * com.popbill.api.message.Message[], java.util.Date, java.lang.String,
     * java.lang.String)
     */
    @Override
    public String sendXMS(String CorpNum, String sender, String subject, String content, Message[] Messages, Date reserveDT, String UserID,
            String requestNum) throws PopbillException {
        return sendXMS(CorpNum, sender, subject, content, Messages, reserveDT, false, UserID, requestNum);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.MessageService#sendXMS(java.lang.String,
     * java.lang.String, java.lang.String, java.lang.String,
     * com.popbill.api.message.Message[], java.util.Date, java.lang.Boolean,
     * java.lang.String, java.lang.String)
     */
    @Override
    public String sendXMS(String CorpNum, String sender, String subject, String content, Message[] Messages, Date reserveDT, Boolean adsYN,
            String UserID, String requestNum) throws PopbillException {
        return sendMessage(MessageType.XMS, CorpNum, sender, null, subject, content, Messages, reserveDT, adsYN, UserID, requestNum);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.MessageService#sendXMS(java.lang.String,
     * java.lang.String, java.lang.String, java.lang.String, java.lang.String,
     * com.popbill.api.message.Message[], java.util.Date, java.lang.Boolean,
     * java.lang.String, java.lang.String)
     */
    @Override
    public String sendXMS(String CorpNum, String sender, String senderName, String subject, String content, Message[] Messages, Date reserveDT,
            Boolean adsYN, String UserID, String requestNum) throws PopbillException {
        return sendMessage(MessageType.XMS, CorpNum, sender, senderName, subject, content, Messages, reserveDT, adsYN, UserID, requestNum);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.MessageService#getMessages(java.lang.String,
     * java.lang.String)
     */
    @Override
    public SentMessage[] getMessages(String CorpNum, String receiptNum) throws PopbillException {
        if (receiptNum == null)
            throw new PopbillException(-99999999, "접수번호가 입력되지 않았습니다.");

        return httpget("/Message/" + receiptNum, CorpNum, null, SentMessage[].class);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.MessageService#getMessages(java.lang.String,
     * java.lang.String, java.lang.String)
     */
    @Override
    public SentMessage[] getMessages(String CorpNum, String receiptNum, String userID) throws PopbillException {
        if (receiptNum == null)
            throw new PopbillException(-99999999, "접수번호가 입력되지 않았습니다.");

        return httpget("/Message/" + receiptNum, CorpNum, userID, SentMessage[].class);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.MessageService#getMessagesRN(java.lang.String,
     * java.lang.String)
     */
    @Override
    public SentMessage[] getMessagesRN(String CorpNum, String requestNum) throws PopbillException {
        if (requestNum == null)
            throw new PopbillException(-99999999, "전송요청번호가 입력되지 않았습니다.");

        return httpget("/Message/Get/" + requestNum, CorpNum, null, SentMessage[].class);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.MessageService#getMessagesRN(java.lang.String,
     * java.lang.String, java.lang.String)
     */
    @Override
    public SentMessage[] getMessagesRN(String CorpNum, String requestNum, String userID) throws PopbillException {
        if (requestNum == null)
            throw new PopbillException(-99999999, "전송요청번호가 입력되지 않았습니다.");

        return httpget("/Message/Get/" + requestNum, CorpNum, userID, SentMessage[].class);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.MessageService#getStates(java.lang.String,
     * java.lang.String[])
     */
    @Override
    public MessageBriefInfo[] getStates(String corpNum, String[] receiptNumList) throws PopbillException {
        return getStates(corpNum, receiptNumList, null);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.MessageService#getStates(java.lang.String,
     * java.lang.String[], java.lang.String)
     */
    @Override
    public MessageBriefInfo[] getStates(String corpNum, String[] receiptNumList, String userID) throws PopbillException {
        if (receiptNumList == null || receiptNumList.length == 0)
            throw new PopbillException(-99999999, "접수번호 목록이 입력되지 않았습니다.");

        String PostData = toJsonString(receiptNumList);

        return httppost("/Message/States", corpNum, PostData, userID, MessageBriefInfo[].class);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.MessageService#cancelReserve(java.lang.String,
     * java.lang.String)
     */
    @Override
    public Response cancelReserve(String CorpNum, String receiptNum) throws PopbillException {
        return cancelReserve(CorpNum, receiptNum, null);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.MessageService#cancelReserve(java.lang.String,
     * java.lang.String, java.lang.String)
     */
    @Override
    public Response cancelReserve(String CorpNum, String receiptNum, String UserID) throws PopbillException {
        if (receiptNum == null)
            throw new PopbillException(-99999999, "접수번호가 입력되지 않았습니다.");

        return httpget("/Message/" + receiptNum + "/Cancel", CorpNum, UserID, Response.class);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.MessageService#cancelReserveRN(java.lang.String,
     * java.lang.String)
     */
    @Override
    public Response cancelReserveRN(String CorpNum, String requestNum) throws PopbillException {
        return cancelReserveRN(CorpNum, requestNum, null);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.MessageService#cancelReserveRN(java.lang.String,
     * java.lang.String, java.lang.String)
     */
    @Override
    public Response cancelReserveRN(String CorpNum, String requestNum, String UserID) throws PopbillException {
        if (requestNum == null)
            throw new PopbillException(-99999999, "전송요청번호가 입력되지 않았습니다.");

        return httpget("/Message/Cancel/" + requestNum, CorpNum, UserID, Response.class);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.MessageService#sendMMS(java.lang.String,
     * java.lang.String, java.lang.String, java.lang.String, java.lang.String,
     * java.lang.String, java.io.File, java.util.Date, java.lang.String)
     */
    @Override
    public String sendMMS(String CorpNum, String sender, String receiver, String receiverName, String subject, String content, File file,
            Date reserveDT, String UserID) throws PopbillException {

        Message message = new Message();

        message.setSender(sender);
        message.setReceiver(receiver);
        message.setReceiverName(receiverName);
        message.setContent(content);
        message.setSubject(subject);

        return sendMMS(CorpNum, new Message[] { message }, file, reserveDT, UserID);
    }

    @Override
    public String sendMMS(String CorpNum, String sender, String receiver, String receiverName, String subject, String content, File file,
            Date reserveDT, Boolean adsYN, String UserID) throws PopbillException {

        Message message = new Message();

        message.setSender(sender);
        message.setReceiver(receiver);
        message.setReceiverName(receiverName);
        message.setContent(content);
        message.setSubject(subject);

        return sendMMS(CorpNum, null, null, null, new Message[] { message }, file, reserveDT, adsYN, UserID);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.MessageService#sendMMS(java.lang.String,
     * com.popbill.api.message.Message[], java.io.File, java.util.Date,
     * java.lang.String)
     */
    @Override
    public String sendMMS(String CorpNum, Message[] Messages, File file, Date reserveDT, String UserID) throws PopbillException {

        return sendMMS(CorpNum, null, null, null, Messages, file, reserveDT, UserID);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.MessageService#sendMMS(java.lang.String,
     * java.lang.String, java.lang.String, java.lang.String,
     * com.popbill.api.message.Message[], java.io.File, java.util.Date,
     * java.lang.String)
     */
    @Override
    public String sendMMS(String CorpNum, String sender, String subject, String content, Message[] Messages, File file, Date reserveDT, String UserID)
            throws PopbillException {

        return sendMMS(CorpNum, sender, subject, content, Messages, file, reserveDT, false, UserID);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.MessageService#sendMMS(java.lang.String,
     * java.lang.String, java.lang.String, java.lang.String,
     * com.popbill.api.message.Message[], java.io.File, java.util.Date,
     * java.lang.Boolean, java.lang.String)
     */
    @Override
    public String sendMMS(String CorpNum, String sender, String subject, String content, Message[] Messages, File file, Date reserveDT, Boolean adsYN,
            String UserID) throws PopbillException {
        return sendMMS(CorpNum, sender, null, subject, content, Messages, file, reserveDT, adsYN, UserID);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.MessageService#sendMMS(java.lang.String,
     * java.lang.String, java.lang.String, java.lang.String, java.lang.String,
     * com.popbill.api.message.Message[], java.io.File, java.util.Date,
     * java.lang.Boolean, java.lang.String)
     */
    @Override
    public String sendMMS(String CorpNum, String sender, String senderName, String subject, String content, Message[] Messages, File file,
            Date reserveDT, Boolean adsYN, String UserID) throws PopbillException {
        if (Messages == null || Messages.length == 0)
            throw new PopbillException(-99999999, "전송할 메시지가 입력되지 않았습니다.");

        SendRequest request = new SendRequest();
        request.snd = sender;

        request.content = content;
        request.subject = subject;

        if (senderName != null)
            request.sndnm = senderName;

        if (adsYN) {
            request.adsYN = true;
        } else {
            request.adsYN = false;
        }

        if (reserveDT != null)
            request.sndDT = new SimpleDateFormat("yyyyMMddHHmmss", Locale.KOREA).format(reserveDT);

        request.msgs = Messages;

        String PostData = toJsonString(request);

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

        ReceiptResponse response = httppostFiles("/MMS", CorpNum, PostData, uploadFiles, UserID, ReceiptResponse.class);

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
     * @see com.popbill.api.MessageService#sendMMS(java.lang.String,
     * java.lang.String, java.lang.String, java.lang.String, java.lang.String,
     * java.lang.String, java.io.File, java.util.Date, java.lang.String)
     */
    @Override
    public String sendMMS(String CorpNum, String sender, String receiver, String receiverName, String subject, String content, File file,
            Date reserveDT, String UserID, String requestNum) throws PopbillException {

        Message message = new Message();

        message.setSender(sender);
        message.setReceiver(receiver);
        message.setReceiverName(receiverName);
        message.setContent(content);
        message.setSubject(subject);

        return sendMMS(CorpNum, new Message[] { message }, file, reserveDT, UserID, requestNum);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.MessageService#sendMMS(java.lang.String,
     * java.lang.String, java.lang.String, java.lang.String, java.lang.String,
     * java.lang.String, java.io.File, java.util.Date, java.lang.Boolean,
     * java.lang.String)
     */
    @Override
    public String sendMMS(String CorpNum, String sender, String receiver, String receiverName, String subject, String content, File file,
            Date reserveDT, Boolean adsYN, String UserID, String requestNum) throws PopbillException {

        Message message = new Message();

        message.setSender(sender);
        message.setReceiver(receiver);
        message.setReceiverName(receiverName);
        message.setContent(content);
        message.setSubject(subject);

        return sendMMS(CorpNum, null, null, null, new Message[] { message }, file, reserveDT, adsYN, UserID, requestNum);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.MessageService#sendMMS(java.lang.String,
     * com.popbill.api.message.Message[], java.io.File, java.util.Date,
     * java.lang.String)
     */
    @Override
    public String sendMMS(String CorpNum, Message[] Messages, File file, Date reserveDT, String UserID, String requestNum) throws PopbillException {

        return sendMMS(CorpNum, null, null, null, Messages, file, reserveDT, UserID, requestNum);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.MessageService#sendMMS(java.lang.String,
     * java.lang.String, java.lang.String, java.lang.String,
     * com.popbill.api.message.Message[], java.io.File, java.util.Date,
     * java.lang.String)
     */
    @Override
    public String sendMMS(String CorpNum, String sender, String subject, String content, Message[] Messages, File file, Date reserveDT, String UserID,
            String requestNum) throws PopbillException {

        return sendMMS(CorpNum, sender, subject, content, Messages, file, reserveDT, false, UserID, requestNum);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.MessageService#sendMMS(java.lang.String,
     * java.lang.String, java.lang.String, java.lang.String,
     * com.popbill.api.message.Message[], java.io.File, java.util.Date,
     * java.lang.Boolean, java.lang.String)
     */
    @Override
    public String sendMMS(String CorpNum, String sender, String subject, String content, Message[] Messages, File file, Date reserveDT, Boolean adsYN,
            String UserID, String requestNum) throws PopbillException {
        return sendMMS(CorpNum, sender, null, subject, content, Messages, file, reserveDT, adsYN, UserID, requestNum);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.MessageService#sendMMS(java.lang.String,
     * java.lang.String, java.lang.String, java.lang.String, java.lang.String,
     * com.popbill.api.message.Message[], java.io.File, java.util.Date,
     * java.lang.Boolean, java.lang.String)
     */
    @Override
    public String sendMMS(String CorpNum, String sender, String senderName, String subject, String content, Message[] Messages, File file,
            Date reserveDT, Boolean adsYN, String UserID, String requestNum) throws PopbillException {
        if (Messages == null || Messages.length == 0)
            throw new PopbillException(-99999999, "전송할 메시지가 입력되지 않았습니다.");

        SendRequest request = new SendRequest();
        request.snd = sender;

        request.content = content;
        request.subject = subject;

        if (senderName != null)
            request.sndnm = senderName;

        if (adsYN) {
            request.adsYN = true;
        } else {
            request.adsYN = false;
        }

        if (reserveDT != null)
            request.sndDT = new SimpleDateFormat("yyyyMMddHHmmss", Locale.KOREA).format(reserveDT);

        request.msgs = Messages;

        if (requestNum != null)
            request.requestNum = requestNum;

        String PostData = toJsonString(request);

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

        ReceiptResponse response = httppostFiles("/MMS", CorpNum, PostData, uploadFiles, UserID, ReceiptResponse.class);

        for (UploadFile f : uploadFiles) {
            if (f.fileData != null)
                try {
                    f.fileData.close();
                } catch (IOException e) {
                }
        }

        return response.receiptNum;
    }

    private String sendMessage(MessageType MsgType, String CorpNum, String sender, String senderName, String subject, String content,
            Message[] Messages, Date reserveDT, Boolean adsYN, String UserID, String requestNum) throws PopbillException {
        if (MsgType == null)
            throw new PopbillException(-99999999, "메시지 유형이 입력되지 않았습니다.");
        if (CorpNum == null || CorpNum.isEmpty())
            throw new PopbillException(-99999999, "회원 사업자번호가 입력되지 않았습니다.");

        if (Messages == null || Messages.length == 0)
            throw new PopbillException(-99999999, "전송할 메시지가 입력되지 않았습니다.");

        SendRequest request = new SendRequest();
        request.snd = sender;
        request.content = content;
        request.subject = subject;

        if (senderName != null)
            request.sndnm = senderName;

        if (adsYN) {
            request.adsYN = true;
        } else {
            request.adsYN = false;
        }

        if (reserveDT != null)
            request.sndDT = new SimpleDateFormat("yyyyMMddHHmmss", Locale.KOREA).format(reserveDT);

        request.msgs = Messages;

        if (requestNum != null)
            request.requestNum = requestNum;

        String PostData = toJsonString(request);

        ReceiptResponse response = httppost("/" + MsgType.name(), CorpNum, PostData, UserID, ReceiptResponse.class);

        return response.receiptNum;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.MessageService#search(java.lang.String,
     * java.lang.String, java.lang.String, java.lang.String[], java.lang.String[],
     * java.lang.Boolean, java.lang.Boolean, int, int, java.lang.String)
     */
    @Override
    public MSGSearchResult search(String CorpNum, String SDate, String EDate, String[] State, String[] Item, Boolean ReserveYN, Boolean SenderYN,
            int Page, int PerPage, String Order) throws PopbillException {
        return search(CorpNum, SDate, EDate, State, Item, ReserveYN, SenderYN, Page, PerPage, Order, null);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.MessageService#search(java.lang.String,
     * java.lang.String, java.lang.String, java.lang.String[], java.lang.String[],
     * java.lang.Boolean, java.lang.Boolean, int, int, java.lang.String)
     */
    @Override
    public MSGSearchResult search(String CorpNum, String SDate, String EDate, String[] State, String[] Item, Boolean ReserveYN, Boolean SenderYN,
            int Page, int PerPage, String Order, String QString) throws PopbillException {
        if (SDate == null)
            throw new PopbillException(-99999999, "시작일자가 입력되지 않았습니다.");
        if (EDate == null)
            throw new PopbillException(-99999999, "종료일자가 입력되지 않았습니다.");

        String uri = "/Message/Search?SDate=" + SDate;
        uri += "&EDate=" + EDate;
        uri += "&State=" + Arrays.toString(State).replaceAll("\\[|\\]|\\s", "");

        uri += "&Item=" + Arrays.toString(Item).replaceAll("\\[|\\]|\\s", "");

        if (ReserveYN) {
            uri += "&ReserveYN=1";
        } else {
            uri += "&ReserveYN=0";
        }

        if (SenderYN) {
            uri += "&SenderYN=1";
        } else {
            uri += "&SenderYN=0";
        }

        uri += "&Page=" + Integer.toString(Page);
        uri += "&PerPage=" + Integer.toString(PerPage);
        uri += "&Order=" + Order;

        if (QString != null && QString != "") {
            try {
                uri += "&QString=" + URLEncoder.encode(QString, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                throw new PopbillException(-99999999, "검색어(QString) 인코딩 오류");
            }
        }

        return httpget(uri, CorpNum, null, MSGSearchResult.class);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.MessageService#getAutoDenyList(java.lang.String)
     */
    @Override
    public AutoDeny[] getAutoDenyList(String CorpNum) throws PopbillException {
        return httpget("/Message/Denied", CorpNum, null, AutoDeny[].class);
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.MessageService#checkAutoDenyNumber(java.lang.String)
     */
	@Override
	public AutoDeny checkAutoDenyNumber(String CorpNum) throws PopbillException {
		return httpget("/Message/AutoDenyNumberInfo", CorpNum, null, AutoDeny.class);
	}

	/*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.MessageService#getChargeInfo(java.lang.String,
     * com.popbill.api.message.MessageType)
     */
    @Override
    public ChargeInfo getChargeInfo(String CorpNum, MessageType MsgType) throws PopbillException {
        return httpget("/Message/ChargeInfo?Type=" + MsgType.name(), CorpNum, null, ChargeInfo.class);
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
    public SenderNumber[] getSenderNumberList(String CorpNum, String UserID) throws PopbillException {
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
     * @see com.popbill.api.MessageService#checkSenderNumber(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public Response checkSenderNumber(String CorpNum, String SenderNumber, String UserID) throws PopbillException {
        if (SenderNumber == null || SenderNumber.equals(""))
            throw new PopbillException(-99999999, "발신번호가 입력되지 않았습니다.");
        return httpget("/Message/CheckSenderNumber/"+SenderNumber, CorpNum, UserID, Response.class);
    }

    protected class SendRequest {
        public String snd;
        public String sndnm;
        public String content;
        public String subject;
        public String sndDT;
        public Boolean adsYN;
        public String requestNum;

        public Message[] msgs;
    }

    protected class ReceiptResponse {
        public String receiptNum;
    }

    @Override
    public Response cancelReservebyRCV(String CorpNum, String receiptNum, String receiveNum) throws PopbillException {
        return cancelReservebyRCV(CorpNum, receiptNum, receiveNum, null);
    }

    @Override
    public Response cancelReservebyRCV(String CorpNum, String receiptNum, String receiveNum, String UserID)
            throws PopbillException {
        if (receiptNum == null || receiptNum.equals(""))
            throw new PopbillException(-99999999, "접수번호가 입력되지 않았습니다.");
        if (receiveNum == null || receiveNum.equals(""))
            throw new PopbillException(-99999999, "수신번호가 입력되지 않았습니다.");
        
        String PostData = toJsonString(receiveNum);

        return httppost("/Message/" + receiptNum + "/Cancel", CorpNum, PostData, UserID, Response.class);
    }

    @Override
    public Response cancelReserveRNbyRCV(String CorpNum, String requestNum, String receiveNum) throws PopbillException {
        return cancelReserveRNbyRCV(CorpNum, requestNum, receiveNum, null);
    }

    @Override
    public Response cancelReserveRNbyRCV(String CorpNum, String requestNum, String receiveNum, String UserID)
            throws PopbillException {
        if (requestNum == null || requestNum.equals(""))
            throw new PopbillException(-99999999, "전송요청번호가 입력되지 않았습니다.");
        if (receiveNum == null || receiveNum.equals(""))
            throw new PopbillException(-99999999, "수신번호가 입력되지 않았습니다.");
        
        String PostData = toJsonString(receiveNum);

        return httppost("/Message/Cancel/" +requestNum, CorpNum, PostData, UserID, Response.class);
    }

}
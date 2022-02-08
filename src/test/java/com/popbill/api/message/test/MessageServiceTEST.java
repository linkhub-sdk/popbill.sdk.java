package com.popbill.api.message.test;

import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import com.popbill.api.ChargeInfo;
import com.popbill.api.MessageService;
import com.popbill.api.PopbillException;
import com.popbill.api.Response;
import com.popbill.api.message.AutoDeny;
import com.popbill.api.message.MSGSearchResult;
import com.popbill.api.message.Message;
import com.popbill.api.message.MessageBriefInfo;
import com.popbill.api.message.MessageServiceImp;
import com.popbill.api.message.MessageType;
import com.popbill.api.message.SentMessage;
import com.popbill.api.message.SenderNumber;

public class MessageServiceTEST {

    private final String testLinkID = "TESTER";
    private final String testSecretKey = "SwWxqU+0TErBXy/9TVjIPEnI0VTUMMSQZtJf3Ed8q3I=";

    private MessageService messageService;
    
    public MessageServiceTEST() {
        MessageServiceImp service = new MessageServiceImp();

        service.setLinkID(testLinkID);
        service.setSecretKey(testSecretKey);
        service.setTest(true);
        
        messageService = service;
    }
    
    @Test
    public void sendSMS_01_TEST() throws PopbillException {
        Date reserveDT = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        try {
            reserveDT = format.parse("20200901180000");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        String receiptNum = messageService.sendSMS("1234567890","070", "010-111-222",
                   "1수신자명","SMS 문자 01 테스트입니다.",reserveDT, 
                       "testkorea");
        
        assertNotNull(receiptNum);
        
        System.out.println(receiptNum);
    }    
    
    @Test
    public void sendSMS_02_TEST() throws PopbillException {
        
        String receiptNum = messageService.sendSMS("1234567890","010-7175-4819", "010-111-222",
                   "2수신자명","SMS 문자 02 테스트입니다.",null, 
                       true, "testkorea");
        
        assertNotNull(receiptNum);
        
        System.out.println(receiptNum);
    }    

    @Test
    public void sendSMS_03_TEST() throws PopbillException {
        
        Message[] Messages = new Message[3];
        
        Message message0 = new Message();
        message0.setSender("010-7175-4819");
        message0.setSubject("제목_1");
        message0.setContent("SMS 문자 03 테스트입니다_01");
        message0.setReceiver("010123123");
        message0.setReceiverName("수신자명1");
        Messages[0] = message0;
        
        Message message1 = new Message();
        message1.setSender("010-7175-4819");
        message1.setReceiver("010123123");
        Messages[1] = message1;
        
        Message message2 = new Message();
        message2.setSender("010-7175-4819");
        message2.setSubject("제목_3");
        message2.setContent("SMS 문자 03 테스트입니다_03");
        message2.setReceiver("010123123");
        message2.setReceiverName("수신자명3");
        Messages[2] = message2;                
        
        String receiptNum = messageService.sendSMS("1234567890", Messages, null, 
                       "testkorea");
        
        assertNotNull(receiptNum);
        
        System.out.println(receiptNum);
    }    
    
    @Test
    public void sendSMS_04_TEST() throws PopbillException {
        
        Message[] Messages = new Message[3];
        
        Message message0 = new Message();
        message0.setSender("010-7175-4819");
        message0.setSubject("제목_1");
        message0.setContent("SMS 문자 04 테스트입니다 01");
        message0.setReceiver("010123123");
        message0.setReceiverName("수신자명1");
        Messages[0] = message0;
        
        Message message1 = new Message();
        message1.setSender("010-7175-4819");
        message1.setReceiver("010123123");
        Messages[1] = message1;
        
        Message message2 = new Message();
        message2.setSender("010-7175-4819");
        message2.setSubject("제목_3");
        message2.setContent("SMS 문자 04 테스트입니다 03");
        message2.setReceiver("010123123");
        message2.setReceiverName("수신자명3");
        Messages[2] = message2;                
        
        String receiptNum = messageService.sendSMS("1234567890","010-7175-4819", "SMS 문자 04 테스트입니다",
                Messages, null, "testkorea");
        
        assertNotNull(receiptNum);
        
        System.out.println(receiptNum);
    }    
    
    @Test
    public void sendSMS_05_TEST() throws PopbillException {
        
        Message[] Messages = new Message[3];
        
        Message message0 = new Message();
        message0.setSender("010-7175-4819");
        message0.setSubject("제목_1");
        message0.setContent("SMS 문자 05 테스트입니다 01");
        message0.setReceiver("010123123");
        message0.setReceiverName("수신자명1");
        Messages[0] = message0;
        
        Message message1 = new Message();
        message1.setSender("010-7175-4819");
        message1.setReceiver("010123123");
        Messages[1] = message1;
        
        Message message2 = new Message();
        message2.setSender("010-7175-4819");
        message2.setSubject("제목_3");
        message2.setContent("SMS 문자 05 테스트입니다 03");
        message2.setReceiver("010123123");
        message2.setReceiverName("수신자명3");
        Messages[2] = message2;            
        
        String receiptNum = messageService.sendSMS("1234567890","010-7175-4819", "SMS 문자 05 테스트입니다",
                Messages, null, false, "testkorea");
        
        assertNotNull(receiptNum);
        
        System.out.println(receiptNum);
    }    
    
    @Test
    public void sendSMS_06_TEST() throws PopbillException {
        
        Message[] Messages = new Message[3];
        
        Message message0 = new Message();
        message0.setSender("010-7175-4819");
        message0.setSubject("제목_1");
        message0.setContent("SMS 문자 06 테스트입니다 01");
        message0.setReceiver("010123123");
        message0.setReceiverName("수신자명1");
        Messages[0] = message0;
        
        Message message1 = new Message();
        message1.setSender("010-7175-4819");
        message1.setReceiver("010123123");
        Messages[1] = message1;
        
        Message message2 = new Message();
        message2.setSender("010-7175-4819");
        message2.setSubject("제목_3");
        message2.setContent("SMS 문자 06 테스트입니다 02");
        message2.setReceiver("010123123");
        message2.setReceiverName("수신자명3");
        Messages[2] = message2;            
        
        String receiptNum = messageService.sendSMS("1234567890","010-7175-4819", "발신자명06",
                "SMS 문자 06 테스트입니다", Messages, null, true, "testkorea"      );
        
        assertNotNull(receiptNum);
        
        System.out.println(receiptNum);
    }    
    
    @Test
    public void sendSMS_07_TEST() throws PopbillException {
        Date reserveDT = new Date();
        reserveDT.setDate(reserveDT.getDate() + 15);
        String receiptNum = messageService.sendSMS("1234567890","010-7175-4819", "010-111-222",
                   "수신자명07","SMS 문자 07 테스트입니다",reserveDT, "testkorea", "20180813155254");
        
        assertNotNull(receiptNum);
        
        System.out.println(receiptNum);
    }    
    
    @Test
    public void sendSMS_08_TEST() throws PopbillException {
        Date reserveDT = new Date();
        reserveDT.setDate(reserveDT.getDate() + 1);
        String receiptNum = messageService.sendSMS("1234567890","010-7175-4819", "010-111-222",
                   "수신자명08","SMS 문자  08 테스트입니다",reserveDT, 
                       true, "testkorea", "20180723_SMS_38");
        
        assertNotNull(receiptNum);
        
        System.out.println(receiptNum);
    }    
    
    @Test
    public void sendSMS_09_TEST() throws PopbillException {
        
        Message[] Messages = new Message[3];
        
        Message message0 = new Message();
        message0.setSender("010-7175-4819");
        message0.setSubject("제목_1");
        message0.setContent("SMS 문자 09  테스트입니다 01");
        message0.setReceiver("010123123");
        message0.setReceiverName("수신자명1");
        Messages[0] = message0;
        
        Message message1 = new Message();
        message1.setSender("010-7175-4819");
        message1.setReceiver("010123123");
        Messages[1] = message1;
        
        Message message2 = new Message();
        message2.setSender("010-7175-4819");
        message2.setSubject("제목_3");
        message2.setContent("SMS 문자 09  테스트입니다 03");
        message2.setReceiver("010123123");
        message2.setReceiverName("수신자명3");
        Messages[2] = message2;                
        
        String receiptNum = messageService.sendSMS("1234567890", Messages, null,
                "testkorea", "20180723_SMS_09");
        
        assertNotNull(receiptNum);
        
        System.out.println(receiptNum);
    }    
    
    @Test
    public void sendSMS_10_TEST() throws PopbillException {
        
        Message[] Messages = new Message[3];
        
        Message message0 = new Message();
        message0.setSender("010-7175-4819");
        message0.setSubject("제목_1");
        message0.setContent("SMS 문자 10  테스트입니다 01");
        message0.setReceiver("010123123");
        message0.setReceiverName("수신자명1");
        Messages[0] = message0;
        
        Message message1 = new Message();
        message1.setSender("010-7175-4819");
        message1.setReceiver("010123123");
        Messages[1] = message1;
        
        Message message2 = new Message();
        message2.setSender("010-7175-4819");
        message2.setSubject("제목_3");
        message2.setContent("SMS 문자 10  테스트입니다 03");
        message2.setReceiver("010123123");
        message2.setReceiverName("수신자명3");
        Messages[2] = message2;            
        
        String receiptNum = messageService.sendSMS("1234567890","010-7175-4819", "SMS 문자 10  테스트입니다",
                Messages, null, "testkorea", "20180723_SMS_10");
        
        assertNotNull(receiptNum);
        
        System.out.println(receiptNum);
    }
    
    @Test
    public void sendSMS_11_TEST() throws PopbillException {
        
        Message[] Messages = new Message[3];
        
        Message message0 = new Message();
        message0.setSender("010-7175-4819");
        message0.setSubject("제목_1");
        message0.setContent("SMS 문자 11  테스트입니다 01");
        message0.setReceiver("010123123");
        message0.setReceiverName("수신자명1");
        Messages[0] = message0;
        
        Message message1 = new Message();
        message1.setSender("010-7175-4819");
        message1.setReceiver("010123123");
        Messages[1] = message1;
        
        Message message2 = new Message();
        message2.setSender("010-7175-4819");
        message2.setSubject("제목_3");
        message2.setContent("SMS 문자 11  테스트입니다 03");
        message2.setReceiver("010123123");
        message2.setReceiverName("수신자명3");
        Messages[2] = message2;            
        
        String receiptNum = messageService.sendSMS("1234567890","010-7175-4819", "SMS 문자 11  테스트입니다",
                Messages, null, false, "testkorea", "20180723_SMS_11");
        
        assertNotNull(receiptNum);
        
        System.out.println(receiptNum);
    }    
    
    @Test
    public void sendSMS_12_TEST() throws PopbillException {
        
        Message[] Messages = new Message[3];
        
        Message message0 = new Message();
        message0.setSender("010-7175-4819");
        message0.setSubject("제목_1");
        message0.setContent("SMS 문자 12  테스트입니다 01");
        message0.setReceiver("010123123");
        message0.setReceiverName("수신자명1");
        Messages[0] = message0;
        
        Message message1 = new Message();
        message1.setSender("010-7175-4819");
        message1.setReceiver("010123123");
        Messages[1] = message1;
        
        Message message2 = new Message();
        message2.setSender("010-7175-4819");
        message2.setSubject("제목_3");
        message2.setContent("SMS 문자 12  테스트입니다 03");
        message2.setReceiver("010123123");
        message2.setReceiverName("수신자명3");
        Messages[2] = message2;            
        
        String receiptNum = messageService.sendSMS("1234567890","010-7175-4819", "발신자명 12",
                "SMS 문자 12 테스트입니다", Messages, null, false, "testkorea", "20180723_SMS_12");
        
        assertNotNull(receiptNum);
        
        System.out.println(receiptNum);
    }        
    
    
    @Test
    public void sendLMS_01_TEST() throws PopbillException {
        
        String receiptNum = messageService.sendLMS("1234567890","010-7175-4819", "010-111-222",
                   "1수신자명", "LMS SUBJECT 01", "LMS 문자 01 테스트입니다.", null, "testkorea");
        
        assertNotNull(receiptNum);
        
        System.out.println(receiptNum);
    }    
    
    @Test
    public void sendLMS_02_TEST() throws PopbillException {
        
        String receiptNum = messageService.sendLMS("1234567890","010-7175-4819", "010-111-222",
                   "2수신자명","LMS SUBJECT 02", "LMS 문자 02 테스트입니다.",null, 
                       true, "testkorea");
        
        assertNotNull(receiptNum);
        
        System.out.println(receiptNum);
    }    

    @Test
    public void sendLMS_03_TEST() throws PopbillException {
        
        Message[] Messages = new Message[3];
        
        Message message0 = new Message();
        message0.setSender("010-7175-4819");
        message0.setSubject("제목_1");
        message0.setContent("LMS 문자 03 테스트입니다_01");
        message0.setReceiver("010123123");
        message0.setReceiverName("수신자명1");
        Messages[0] = message0;
        
        Message message1 = new Message();
        message1.setSender("010-7175-4819");
        message1.setReceiver("010123123");
        Messages[1] = message1;
        
        Message message2 = new Message();
        message2.setSender("010-7175-4819");
        message2.setSubject("제목_3");
        message2.setContent("LMS 문자 03 테스트입니다_03");
        message2.setReceiver("010123123");
        message2.setReceiverName("수신자명3");
        Messages[2] = message2;                
        
        String receiptNum = messageService.sendLMS("1234567890", Messages, null, 
                       "testkorea");
        
        assertNotNull(receiptNum);
        
        System.out.println(receiptNum);
    }    
    
    @Test
    public void sendLMS_04_TEST() throws PopbillException {
        
        Message[] Messages = new Message[3];
        
        Message message0 = new Message();
        message0.setSender("010-7175-4819");
        message0.setSubject("제목_1");
        message0.setContent("SMS 문자 04 테스트입니다 01");
        message0.setReceiver("010123123");
        message0.setReceiverName("수신자명1");
        Messages[0] = message0;
        
        Message message1 = new Message();
        message1.setSender("010-7175-4819");
        message1.setReceiver("010123123");
        Messages[1] = message1;
        
        Message message2 = new Message();
        message2.setSender("010-7175-4819");
        message2.setSubject("제목_3");
        message2.setContent("SMS 문자 04 테스트입니다 03");
        message2.setReceiver("010123123");
        message2.setReceiverName("수신자명3");
        Messages[2] = message2;                
        
        String receiptNum = messageService.sendLMS("1234567890","010-7175-4819","LMS SUBJECT 04", 
                "SMS 문자 04 테스트입니다",
                Messages, null, "testkorea");
        
        assertNotNull(receiptNum);
        
        System.out.println(receiptNum);
    }    
    
    @Test
    public void sendLMS_05_TEST() throws PopbillException {
        
        Message[] Messages = new Message[3];
        
        Message message0 = new Message();
        message0.setSender("010-7175-4819");
        message0.setSubject("제목_1");
        message0.setContent("LMS 문자 05 테스트입니다 01");
        message0.setReceiver("010123123");
        message0.setReceiverName("수신자명1");
        Messages[0] = message0;
        
        Message message1 = new Message();
        message1.setSender("010-7175-4819");
        message1.setReceiver("010123123");
        Messages[1] = message1;
        
        Message message2 = new Message();
        message2.setSender("010-7175-4819");
        message2.setSubject("제목_3");
        message2.setContent("LMS 문자 05 테스트입니다 03");
        message2.setReceiver("010123123");
        message2.setReceiverName("수신자명3");
        Messages[2] = message2;            
        
        String receiptNum = messageService.sendLMS("1234567890","010-7175-4819", "LMS SUBJECT 05", 
                "LMS 문자 05 테스트입니다",
                Messages, null, false, "testkorea");
        
        assertNotNull(receiptNum);
        
        System.out.println(receiptNum);
    }    
    
    @Test
    public void sendLMS_06_TEST() throws PopbillException {
        
        Message[] Messages = new Message[3];
        
        Message message0 = new Message();
        message0.setSender("010-7175-4819");
        message0.setSubject("제목_1");
        message0.setContent("SMS 문자 06 테스트입니다 01");
        message0.setReceiver("010123123");
        message0.setReceiverName("수신자명1");
        Messages[0] = message0;
        
        Message message1 = new Message();
        message1.setSender("010-7175-4819");
        message1.setReceiver("010123123");
        Messages[1] = message1;
        
        Message message2 = new Message();
        message2.setSender("010-7175-4819");
        message2.setSubject("제목_3");
        message2.setContent("SMS 문자 06 테스트입니다 02");
        message2.setReceiver("010123123");
        message2.setReceiverName("수신자명3");
        Messages[2] = message2;            
        
        String receiptNum = messageService.sendLMS("1234567890","010-7175-4819", "발신자명06",
                "LMS SUBJECT 06", "LMS 문자 06 테스트입니다", Messages, null, true, "testkorea"      );
        
        assertNotNull(receiptNum);
        
        System.out.println(receiptNum);
    }    
    
    @Test
    public void sendLMS_07_TEST() throws PopbillException {
        
        String receiptNum = messageService.sendLMS("1234567890","010-7175-4819", "010-111-222",
                   "수신자명07","LMS SUBJECT 07", "LMS 문자 07 테스트입니다",null, "testkorea", "20180723_LMS_07");
        
        assertNotNull(receiptNum);
        
        System.out.println(receiptNum);
    }    
    
    @Test
    public void sendLMS_08_TEST() throws PopbillException {
        
        String receiptNum = messageService.sendLMS("1234567890","010-7175-4819", "010-111-222",
                   "수신자명08","LMS SUBJECT 08", "SMS 문자  08 테스트입니다",null, 
                       true, "testkorea", "20180723_LMS_08");
        
        assertNotNull(receiptNum);
        
        System.out.println(receiptNum);
    }    
    
    @Test
    public void sendLMS_09_TEST() throws PopbillException {
        
        Message[] Messages = new Message[3];
        
        Message message0 = new Message();
        message0.setSender("010-7175-4819");
        message0.setSubject("제목_1");
        message0.setContent("LMS 문자 09  테스트입니다 01");
        message0.setReceiver("010123123");
        message0.setReceiverName("수신자명1");
        Messages[0] = message0;
        
        Message message1 = new Message();
        message1.setSender("010-7175-4819");
        message1.setReceiver("010123123");
        Messages[1] = message1;
        
        Message message2 = new Message();
        message2.setSender("010-7175-4819");
        message2.setSubject("제목_3");
        message2.setContent("LMS 문자 09  테스트입니다 03");
        message2.setReceiver("010123123");
        message2.setReceiverName("수신자명3");
        Messages[2] = message2;                
        
        String receiptNum = messageService.sendLMS("1234567890", Messages, null,
                "testkorea", "20180723_LMS_09");
        
        assertNotNull(receiptNum);
        
        System.out.println(receiptNum);
    }    
    
    @Test
    public void sendLMS_10_TEST() throws PopbillException {
        
        Message[] Messages = new Message[3];
        
        Message message0 = new Message();
        message0.setSender("010-7175-4819");
        message0.setSubject("제목_1");
        message0.setContent("LMS 문자 10  테스트입니다 01");
        message0.setReceiver("010123123");
        message0.setReceiverName("수신자명1");
        Messages[0] = message0;
        
        Message message1 = new Message();
        message1.setSender("010-7175-4819");
        message1.setReceiver("010123123");
        Messages[1] = message1;
        
        Message message2 = new Message();
        message2.setSender("010-7175-4819");
        message2.setSubject("제목_3");
        message2.setContent("LMS 문자 10  테스트입니다 03");
        message2.setReceiver("010123123");
        message2.setReceiverName("수신자명3");
        Messages[2] = message2;            
        
        String receiptNum = messageService.sendLMS("1234567890","010-7175-4819", "LMS SUBJECT 10", "LMS 문자 10  테스트입니다",
                Messages, null, "testkorea", "20180723_LMS_10");
        
        assertNotNull(receiptNum);
        
        System.out.println(receiptNum);
    }
    
    @Test
    public void sendLMS_11_TEST() throws PopbillException {
        
        Message[] Messages = new Message[3];
        
        Message message0 = new Message();
        message0.setSender("010-7175-4819");
        message0.setSubject("제목_1");
        message0.setContent("SMS 문자 11  테스트입니다 01");
        message0.setReceiver("010123123");
        message0.setReceiverName("수신자명1");
        Messages[0] = message0;
        
        Message message1 = new Message();
        message1.setSender("010-7175-4819");
        message1.setReceiver("010123123");
        Messages[1] = message1;
        
        Message message2 = new Message();
        message2.setSender("010-7175-4819");
        message2.setSubject("제목_3");
        message2.setContent("SMS 문자 11  테스트입니다 03");
        message2.setReceiver("010123123");
        message2.setReceiverName("수신자명3");
        Messages[2] = message2;            
        
        String receiptNum = messageService.sendLMS("1234567890","010-7175-4819", "LMS SUBJECT 11",  "LMS 문자 11  테스트입니다",
                Messages, null, false, "testkorea", "20180723_LMS_11");
        
        assertNotNull(receiptNum);
        
        System.out.println(receiptNum);
    }    
    
    @Test
    public void sendLMS_12_TEST() throws PopbillException {
        
        Message[] Messages = new Message[3];
        
        Message message0 = new Message();
        message0.setSender("010-7175-4819");
        message0.setSubject("제목_1");
        message0.setContent("LMS 문자 12  테스트입니다 01");
        message0.setReceiver("010123123");
        message0.setReceiverName("수신자명1");
        Messages[0] = message0;
        
        Message message1 = new Message();
        message1.setSender("010-7175-4819");
        message1.setReceiver("010123123");
        Messages[1] = message1;
        
        Message message2 = new Message();
        message2.setSender("010-7175-4819");
        message2.setSubject("제목_3");
        message2.setContent("LMS 문자 12  테스트입니다 03");
        message2.setReceiver("010123123");
        message2.setReceiverName("수신자명3");
        Messages[2] = message2;            
        
        String receiptNum = messageService.sendLMS("1234567890","010-7175-4819", "발신자명 12", "LMS SUBJECT12",
                "LMS 문자 12 테스트입니다", Messages, null, false, "testkorea", "20180723_LMS_12");
        
        assertNotNull(receiptNum);
        
        System.out.println(receiptNum);
    }            
    

    @Test
    public void sendMMS_01_TEST() throws PopbillException {
        File file = new File("/Users/김은혜/Desktop/산타할부지.jpg");        
        
        String receiptNum = messageService.sendMMS("1234567890","010-7175-4819", "010-111-222",
                   "1수신자명", "MMS SUBJECT 01", "MMS 문자 01 테스트입니다.", file, null, "testkorea");
        
        assertNotNull(receiptNum);
        
        System.out.println(receiptNum);
    }    
    
    @Test
    public void sendMMS_02_TEST() throws PopbillException {
        File file = new File("/Users/김은혜/Desktop/산타할부지.jpg");
        String receiptNum = messageService.sendMMS("1234567890","010-7175-4819", "010-111-222",
                   "2수신자명","MMS SUBJECT 02", "MMS 문자 02 테스트입니다.", file,  null, 
                       true, "testkorea");
        
        assertNotNull(receiptNum);
        
        System.out.println(receiptNum);
    }    

    @Test
    public void sendMMS_03_TEST() throws PopbillException {
        File file = new File("/Users/김은혜/Desktop/산타할부지.jpg");
        Message[] Messages = new Message[3];
        
        Message message0 = new Message();
        message0.setSender("010-7175-4819");
        message0.setSubject("제목_1");
        message0.setContent("MMS 문자 03 테스트입니다_01");
        message0.setReceiver("010123123");
        message0.setReceiverName("수신자명1");
        Messages[0] = message0;
        
        Message message1 = new Message();
        message1.setSender("010-7175-4819");
        message1.setReceiver("010123123");
        Messages[1] = message1;
        
        Message message2 = new Message();
        message2.setSender("010-7175-4819");
        message2.setSubject("제목_3");
        message2.setContent("MMS 문자 03 테스트입니다_03");
        message2.setReceiver("010123123");
        message2.setReceiverName("수신자명3");
        Messages[2] = message2;                
        
        String receiptNum = messageService.sendMMS("1234567890", Messages, file,  null, 
                       "testkorea");
        
        assertNotNull(receiptNum);
        
        System.out.println(receiptNum);
    }    
    
    @Test
    public void sendMMS_04_TEST() throws PopbillException {
        File file = new File("/Users/김은혜/Desktop/산타할부지.jpg");
        Message[] Messages = new Message[3];
        
        Message message0 = new Message();
        message0.setSender("010-7175-4819");
        message0.setSubject("제목_1");
        message0.setContent("SMS 문자 04 테스트입니다 01");
        message0.setReceiver("010123123");
        message0.setReceiverName("수신자명1");
        Messages[0] = message0;
        
        Message message1 = new Message();
        message1.setSender("010-7175-4819");
        message1.setReceiver("010123123");
        Messages[1] = message1;
        
        Message message2 = new Message();
        message2.setSender("010-7175-4819");
        message2.setSubject("제목_3");
        message2.setContent("SMS 문자 04 테스트입니다 03");
        message2.setReceiver("010123123");
        message2.setReceiverName("수신자명3");
        Messages[2] = message2;                
        
        String receiptNum = messageService.sendMMS("1234567890","010-7175-4819","MMS SUBJECT 04", 
                "MMS 문자 04 테스트입니다",
                Messages, file,  null, "testkorea");
        
        assertNotNull(receiptNum);
        
        System.out.println(receiptNum);
    }    
    
    @Test
    public void sendMMS_05_TEST() throws PopbillException {
        File file = new File("/Users/김은혜/Desktop/산타할부지.jpg");
        Message[] Messages = new Message[3];
        
        Message message0 = new Message();
        message0.setSender("010-7175-4819");
        message0.setSubject("제목_1");
        message0.setContent("MMS 문자 05 테스트입니다 01");
        message0.setReceiver("010123123");
        message0.setReceiverName("수신자명1");
        Messages[0] = message0;
        
        Message message1 = new Message();
        message1.setSender("010-7175-4819");
        message1.setReceiver("010123123");
        Messages[1] = message1;
        
        Message message2 = new Message();
        message2.setSender("010-7175-4819");
        message2.setSubject("제목_3");
        message2.setContent("MMS 문자 05 테스트입니다 03");
        message2.setReceiver("010123123");
        message2.setReceiverName("수신자명3");
        Messages[2] = message2;            
        
        String receiptNum = messageService.sendMMS("1234567890","010-7175-4819", "MMS SUBJECT 05", 
                "MMS 문자 05 테스트입니다",
                Messages, file,  null, false, "testkorea");
        
        assertNotNull(receiptNum);
        
        System.out.println(receiptNum);
    }    
    
    @Test
    public void sendMMS_06_TEST() throws PopbillException {
        File file = new File("/Users/김은혜/Desktop/산타할부지.jpg");
        Message[] Messages = new Message[3];
        
        Message message0 = new Message();
        message0.setSender("010-7175-4819");
        message0.setSubject("제목_1");
        message0.setContent("MMS 문자 06 테스트입니다 01");
        message0.setReceiver("010123123");
        message0.setReceiverName("수신자명1");
        Messages[0] = message0;
        
        Message message1 = new Message();
        message1.setSender("010-7175-4819");
        message1.setReceiver("010123123");
        Messages[1] = message1;
        
        Message message2 = new Message();
        message2.setSender("010-7175-4819");
        message2.setSubject("제목_3");
        message2.setContent("MMS 문자 06 테스트입니다 02");
        message2.setReceiver("010123123");
        message2.setReceiverName("수신자명3");
        Messages[2] = message2;            
        
        String receiptNum = messageService.sendMMS("1234567890","010-7175-4819", "발신자명06",
                "MMS SUBJECT 06", "MMS 문자 06 테스트입니다", Messages, file,  null, true, "testkorea"      );
        
        assertNotNull(receiptNum);
        
        System.out.println(receiptNum);
    }    
    
    @Test
    public void sendMMS_07_TEST() throws PopbillException {
        File file = new File("/Users/김은혜/Desktop/산타할부지.jpg");
        String receiptNum = messageService.sendMMS("1234567890","010-7175-4819", "010-111-222",
                   "수신자명07","MMS SUBJECT 07", "MMS 문자 07 테스트입니다", file, null, "testkorea", "20180723_MMS_17");
        
        assertNotNull(receiptNum);
        
        System.out.println(receiptNum);
    }    
    
    @Test
    public void sendMMS_08_TEST() throws PopbillException {
        File file = new File("/Users/김은혜/Desktop/산타할부지.jpg");
        String receiptNum = messageService.sendMMS("1234567890","010-7175-4819", "010-111-222",
                   "수신자명08","MMS SUBJECT 08", "MMS 문자  08 테스트입니다",file,  null, 
                       true, "testkorea", "20180723_MMS_18");
        
        assertNotNull(receiptNum);
        
        System.out.println(receiptNum);
    }    
    
    @Test
    public void sendMMS_09_TEST() throws PopbillException {
        File file = new File("/Users/김은혜/Desktop/산타할부지.jpg");
        Message[] Messages = new Message[3];
        
        Message message0 = new Message();
        message0.setSender("010-7175-4819");
        message0.setSubject("제목_1");
        message0.setContent("MMS 문자 09  테스트입니다 01");
        message0.setReceiver("010123123");
        message0.setReceiverName("수신자명1");
        Messages[0] = message0;
        
        Message message1 = new Message();
        message1.setSender("010-7175-4819");
        message1.setReceiver("010123123");
        Messages[1] = message1;
        
        Message message2 = new Message();
        message2.setSender("010-7175-4819");
        message2.setSubject("제목_3");
        message2.setContent("MMS 문자 09  테스트입니다 03");
        message2.setReceiver("010123123");
        message2.setReceiverName("수신자명3");
        Messages[2] = message2;                
        
        String receiptNum = messageService.sendMMS("1234567890", Messages, file, null,
                "testkorea", "20180723_MMS_19");
        
        assertNotNull(receiptNum);
        
        System.out.println(receiptNum);
    }    
    
    @Test
    public void sendMMS_10_TEST() throws PopbillException {
        File file = new File("/Users/김은혜/Desktop/산타할부지.jpg");
        Message[] Messages = new Message[3];
        
        Message message0 = new Message();
        message0.setSender("010-7175-4819");
        message0.setSubject("제목_1");
        message0.setContent("MMS 문자 10  테스트입니다 01");
        message0.setReceiver("010123123");
        message0.setReceiverName("수신자명1");
        Messages[0] = message0;
        
        Message message1 = new Message();
        message1.setSender("010-7175-4819");
        message1.setReceiver("010123123");
        Messages[1] = message1;
        
        Message message2 = new Message();
        message2.setSender("010-7175-4819");
        message2.setSubject("제목_3");
        message2.setContent("MMS 문자 10  테스트입니다 03");
        message2.setReceiver("010123123");
        message2.setReceiverName("수신자명3");
        Messages[2] = message2;            
        
        String receiptNum = messageService.sendMMS("1234567890","010-7175-4819", "MMS SUBJECT 10", "MMS 문자 10  테스트입니다",
                Messages, file, null, "testkorea", "20180723_MMS_20");
        
        assertNotNull(receiptNum);
        
        System.out.println(receiptNum);
    }
    
    @Test
    public void sendMMS_11_TEST() throws PopbillException {
        File file = new File("/Users/김은혜/Desktop/산타할부지.jpg");
        Message[] Messages = new Message[3];
        
        Message message0 = new Message();
        message0.setSender("010-7175-4819");
        message0.setSubject("제목_1");
        message0.setContent("MMS 문자 11  테스트입니다 01");
        message0.setReceiver("010123123");
        message0.setReceiverName("수신자명1");
        Messages[0] = message0;
        
        Message message1 = new Message();
        message1.setSender("010-7175-4819");
        message1.setReceiver("010123123");
        Messages[1] = message1;
        
        Message message2 = new Message();
        message2.setSender("010-7175-4819");
        message2.setSubject("제목_3");
        message2.setContent("MMS 문자 11  테스트입니다 03");
        message2.setReceiver("010123123");
        message2.setReceiverName("수신자명3");
        Messages[2] = message2;            
        
        String receiptNum = messageService.sendMMS("1234567890","010-7175-4819", "MMS SUBJECT 11",  "MMS 문자 11  테스트입니다",
                Messages, file, null, false, "testkorea", "20180723_MMS_21");
        
        assertNotNull(receiptNum);
        
        System.out.println(receiptNum);
    }    
    
    @Test
    public void sendMMS_12_TEST() throws PopbillException {
        Date reserveDT = new Date();
        reserveDT.setDate(reserveDT.getDate() + 1);        
        File file = new File("/Users/김은혜/Desktop/산타할부지.jpg");
        Message[] Messages = new Message[3];
        
        Message message0 = new Message();
        message0.setSender("010-7175-4819");
        message0.setSubject("제목_1");
        message0.setContent("MMS 문자 12  테스트입니다 01");
        message0.setReceiver("010123123");
        message0.setReceiverName("수신자명1");
        Messages[0] = message0;
        
        Message message1 = new Message();
        message1.setSender("010-7175-4819");
        message1.setReceiver("010123123");
        Messages[1] = message1;
        
        Message message2 = new Message();
        message2.setSender("010-7175-4819");
        message2.setSubject("제목_3");
        message2.setContent("MMS 문자 12  테스트입니다 03");
        message2.setReceiver("010123123");
        message2.setReceiverName("수신자명3");
        Messages[2] = message2;            
        
        String receiptNum = messageService.sendMMS("1234567890","010-7175-4819", "발신자명 12", "MMS SUBJECT12",
                "MMS 문자 12 테스트입니다", Messages, file, reserveDT, false, "testkorea", "20180723_MMS_32");
        
        assertNotNull(receiptNum);
        
        System.out.println(receiptNum);
    }    
    
    @Test
    public void sendXMS_01_TEST() throws PopbillException {
        
        String receiptNum = messageService.sendXMS("1234567890","010-7175-4819", "010-111-222",
                   "1수신자명", "XMS SUBJECT 01", "XMS 문자 01 테스트입니다.", null, "testkorea");
        
        assertNotNull(receiptNum);
        
        System.out.println(receiptNum);
    }    
    
    @Test
    public void sendXMS_02_TEST() throws PopbillException {
        
        String receiptNum = messageService.sendXMS("1234567890","010-7175-4819", "010-111-222",
                   "2수신자명","XMS SUBJECT 02", "XMS 문자 02 테스트입니다.",null, 
                       true, "testkorea");
        
        assertNotNull(receiptNum);
        
        System.out.println(receiptNum);
    }    

    @Test
    public void sendXMS_03_TEST() throws PopbillException {
        
        Message[] Messages = new Message[3];
        
        Message message0 = new Message();
        message0.setSender("010-7175-4819");
        message0.setSubject("제목_1");
        message0.setContent("XMS 문자 03 테스트입니다_01");
        message0.setReceiver("010123123");
        message0.setReceiverName("수신자명1");
        Messages[0] = message0;
        
        Message message1 = new Message();
        message1.setSender("010-7175-4819");
        message1.setReceiver("010123123");
        Messages[1] = message1;
        
        Message message2 = new Message();
        message2.setSender("010-7175-4819");
        message2.setSubject("제목_3");
        message2.setContent("XMS 문자 03 테스트입니다_03");
        message2.setReceiver("010123123");
        message2.setReceiverName("수신자명3");
        Messages[2] = message2;                
        
        String receiptNum = messageService.sendXMS("1234567890", Messages, null, 
                       "testkorea");
        
        assertNotNull(receiptNum);
        
        System.out.println(receiptNum);
    }    
    
    @Test
    public void sendXMS_04_TEST() throws PopbillException {
        
        Message[] Messages = new Message[3];
        
        Message message0 = new Message();
        message0.setSender("010-7175-4819");
        message0.setSubject("제목_1");
        message0.setContent("SMS 문자 04 테스트입니다 01");
        message0.setReceiver("010123123");
        message0.setReceiverName("수신자명1");
        Messages[0] = message0;
        
        Message message1 = new Message();
        message1.setSender("010-7175-4819");
        message1.setReceiver("010123123");
        Messages[1] = message1;
        
        Message message2 = new Message();
        message2.setSender("010-7175-4819");
        message2.setSubject("제목_3");
        message2.setContent("SMS 문자 04 테스트입니다 03");
        message2.setReceiver("010123123");
        message2.setReceiverName("수신자명3");
        Messages[2] = message2;                
        
        String receiptNum = messageService.sendXMS("1234567890","010-7175-4819","XMS SUBJECT 04", 
                "SMS 문자 04 테스트입니다",
                Messages, null, "testkorea");
        
        assertNotNull(receiptNum);
        
        System.out.println(receiptNum);
    }    
    
    @Test
    public void sendXMS_05_TEST() throws PopbillException {
        
        Message[] Messages = new Message[3];
        
        Message message0 = new Message();
        message0.setSender("010-7175-4819");
        message0.setSubject("제목_1");
        message0.setContent("XMS 문자 05 테스트입니다 01");
        message0.setReceiver("010123123");
        message0.setReceiverName("수신자명1");
        Messages[0] = message0;
        
        Message message1 = new Message();
        message1.setSender("010-7175-4819");
        message1.setReceiver("010123123");
        Messages[1] = message1;
        
        Message message2 = new Message();
        message2.setSender("010-7175-4819");
        message2.setSubject("제목_3");
        message2.setContent("XMS 문자 05 테스트입니다 03");
        message2.setReceiver("010123123");
        message2.setReceiverName("수신자명3");
        Messages[2] = message2;            
        
        String receiptNum = messageService.sendXMS("1234567890","010-7175-4819", "XMS SUBJECT 05", 
                "XMS 문자 05 테스트입니다",
                Messages, null, false, "testkorea");
        
        assertNotNull(receiptNum);
        
        System.out.println(receiptNum);
    }    
    
    @Test
    public void sendXMS_06_TEST() throws PopbillException {
        
        Message[] Messages = new Message[3];
        
        Message message0 = new Message();
        message0.setSender("010-7175-4819");
        message0.setSubject("제목_1");
        message0.setContent("SMS 문자 06 테스트입니다 01");
        message0.setReceiver("010123123");
        message0.setReceiverName("수신자명1");
        Messages[0] = message0;
        
        Message message1 = new Message();
        message1.setSender("010-7175-4819");
        message1.setReceiver("010123123");
        Messages[1] = message1;
        
        Message message2 = new Message();
        message2.setSender("010-7175-4819");
        message2.setSubject("제목_3");
        message2.setContent("SMS 문자 06 테스트입니다 02");
        message2.setReceiver("010123123");
        message2.setReceiverName("수신자명3");
        Messages[2] = message2;            
        
        String receiptNum = messageService.sendXMS("1234567890","010-7175-4819", "발신자명06",
                "XMS SUBJECT 06", "XMS 문자 06 테스트입니다", Messages, null, true, "testkorea"      );
        
        assertNotNull(receiptNum);
        
        System.out.println(receiptNum);
    }    
    
    @Test
    public void sendXMS_07_TEST() throws PopbillException {
        
        String receiptNum = messageService.sendXMS("1234567890","010-7175-4819", "010-111-222",
                   "수신자명07","XMS SUBJECT 07", "XMS 문자 07 테스트입니다",null, "testkorea", "20180723_XMS_07");
        
        assertNotNull(receiptNum);
        
        System.out.println(receiptNum);
    }    
    
    @Test
    public void sendXMS_08_TEST() throws PopbillException {
        
        String receiptNum = messageService.sendXMS("1234567890","010-7175-4819", "010-111-222",
                   "수신자명08","XMS SUBJECT 08", "SMS 문자  08 테스트입니다",null, 
                       true, "testkorea", "20180723_XMS_08");
        
        assertNotNull(receiptNum);
        
        System.out.println(receiptNum);
    }    
    
    @Test
    public void sendXMS_09_TEST() throws PopbillException {
        
        Message[] Messages = new Message[3];
        
        Message message0 = new Message();
        message0.setSender("010-7175-4819");
        message0.setSubject("제목_1");
        message0.setContent("XMS 문자 09  테스트입니다 01");
        message0.setReceiver("010123123");
        message0.setReceiverName("수신자명1");
        Messages[0] = message0;
        
        Message message1 = new Message();
        message1.setSender("010-7175-4819");
        message1.setReceiver("010123123");
        Messages[1] = message1;
        
        Message message2 = new Message();
        message2.setSender("010-7175-4819");
        message2.setSubject("제목_3");
        message2.setContent("XMS 문자 09  테스트입니다 03");
        message2.setReceiver("010123123");
        message2.setReceiverName("수신자명3");
        Messages[2] = message2;                
        
        String receiptNum = messageService.sendXMS("1234567890", Messages, null,
                "testkorea", "20180723_XMS_09");
        
        assertNotNull(receiptNum);
        
        System.out.println(receiptNum);
    }    
    
    @Test
    public void sendXMS_10_TEST() throws PopbillException {
        
        Message[] Messages = new Message[3];
        
        Message message0 = new Message();
        message0.setSender("010-7175-4819");
        message0.setSubject("제목_1");
        message0.setContent("XMS 문자 10  테스트입니다 01");
        message0.setReceiver("010123123");
        message0.setReceiverName("수신자명1");
        Messages[0] = message0;
        
        Message message1 = new Message();
        message1.setSender("010-7175-4819");
        message1.setReceiver("010123123");
        Messages[1] = message1;
        
        Message message2 = new Message();
        message2.setSender("010-7175-4819");
        message2.setSubject("제목_3");
        message2.setContent("XMS 문자 10  테스트입니다 03");
        message2.setReceiver("010123123");
        message2.setReceiverName("수신자명3");
        Messages[2] = message2;            
        
        String receiptNum = messageService.sendXMS("1234567890","010-7175-4819", "XMS SUBJECT 10", "XMS 문자 10  테스트입니다",
                Messages, null, "testkorea", "20180723_XMS_10");
        
        assertNotNull(receiptNum);
        
        System.out.println(receiptNum);
    }
    
    @Test
    public void sendXMS_11_TEST() throws PopbillException {
        
        Message[] Messages = new Message[3];
        
        Message message0 = new Message();
        message0.setSender("010-7175-4819");
        message0.setSubject("제목_1");
        message0.setContent("SMS 문자 11  테스트입니다 01");
        message0.setReceiver("010123123");
        message0.setReceiverName("수신자명1");
        Messages[0] = message0;
        
        Message message1 = new Message();
        message1.setSender("010-7175-4819");
        message1.setReceiver("010123123");
        Messages[1] = message1;
        
        Message message2 = new Message();
        message2.setSender("010-7175-4819");
        message2.setSubject("제목_3");
        message2.setContent("SMS 문자 11  테스트입니다 03");
        message2.setReceiver("010123123");
        message2.setReceiverName("수신자명3");
        Messages[2] = message2;            
        
        String receiptNum = messageService.sendXMS("1234567890","010-7175-4819", "XMS SUBJECT 11",  "XMS 문자 11  테스트입니다",
                Messages, null, false, "testkorea", "20180723_XMS_11");
        
        assertNotNull(receiptNum);
        
        System.out.println(receiptNum);
    }    
    
    @Test
    public void sendXMS_12_TEST() throws PopbillException {
        Date reserveDT = new Date();
        reserveDT.setDate(reserveDT.getDate() + 1);
        Message[] Messages = new Message[3];
        
        Message message0 = new Message();
        message0.setSender("010-7175-4819");
        message0.setSubject("제목_1");
        message0.setContent("XMS 문자 12  테스트입니다 01");
        message0.setReceiver("010123123");
        message0.setReceiverName("수신자명1");
        Messages[0] = message0;
        
        Message message1 = new Message();
        message1.setSender("010-7175-4819");
        message1.setReceiver("010123123");
        Messages[1] = message1;
        
        Message message2 = new Message();
        message2.setSender("010-7175-4819");
        message2.setSubject("제목_3");
        message2.setContent("XMS 문자 12  테스트입니다 03");
        message2.setReceiver("010123123");
        message2.setReceiverName("수신자명3");
        Messages[2] = message2;            
        
        String receiptNum = messageService.sendXMS("1234567890","010-7175-4819", "발신자명 12", "XMS SUBJECT12",
                "XMS 문자 12 테스트입니다", Messages, reserveDT, false, "testkorea", "20180723_XMS_22");
        
        assertNotNull(receiptNum);
        
        System.out.println(receiptNum);
    }            
    
    
    @Test
    public void getChargeInfo_TEST() throws PopbillException {
        
        ChargeInfo chrgInfo = messageService.getChargeInfo("1234567890",MessageType.SMS);
        
        assertNotNull(chrgInfo);
        
        System.out.println(chrgInfo.getChargeMethod());
        System.out.println(chrgInfo.getRateSystem());
        System.out.println(chrgInfo.getUnitCost());
    }
    
    @Test
    public void getUnitCost_TEST() throws PopbillException {
        
        float UnitCost = messageService.getUnitCost("1231212312",MessageType.SMS);

        System.out.println(UnitCost);
    }
    
    @Test
    public void getURL_TEST() throws PopbillException {
        
        String url = messageService.getURL("1234567890", "BOX");

        assertNotNull(url);
        System.out.println(url);
    }
    
    
    @Test
    public void sendSMS_Reserve_15Minute_TEST() throws PopbillException {
        
        Date ReserveDT = addMinutes(new Date(),15);
        
        String receiptNum = messageService.sendSMS("1231212312","070-7510-6766","010-000-111","테스트","단문문자메시지 내용",ReserveDT,"userid");
        
        assertNotNull(receiptNum);
        
        System.out.println(receiptNum);
        
    }
    @Test
    public void sendSMS_Single_TEST() throws PopbillException {
        
        String receiptNum = messageService.sendSMS("1234567890","010-7175-4819", "010-111-222","수신자명","111단문문자메시지 내용_광고아닙니다.",null, true, "testkorea", "20180716_15");
        
        assertNotNull(receiptNum);
        
        System.out.println(receiptNum);
    }
    
    
    
    @Test
    public void sendLMS_Single_TEST() throws PopbillException {
        Date reserveDT = new Date();
        reserveDT.setDate(reserveDT.getDate() + 1);
        
//        String receiptNum = messageService.sendLMS("1234567890","010-7175-4819","010-111-222","수신자명~!!","test장문메시지 제목","222장문문자메시지 내용. 장문문자메시지의 내용은 2000byte까지입니다. 이거슨 광고가 아닙니다.",null, "testkorea", "20180717_01");
//        String receiptNum = messageService.sendLMS("1234567890","010-7175-4819","010-111-222","2번째 수신자","장문메시지 제목","2번째 수신자에게 보내는 장문문자메시지 내용. 장문문자메시지의 내용은 2000byte까지입니다. 이거슨 광고가 아닙니다.",
//                null, true, "testkorea", "20180717_02");
        Message[] Messages = new Message[3];
        
        Message message0 = new Message();
        message0.setSender("010-7175-4819");
        message0.setSubject("제목_1");
        message0.setContent("내용_1");
        message0.setReceiver("010123123");
        message0.setReceiverName("수신자명1");
        Messages[0] = message0;
        
        Message message1 = new Message();
        message1.setSender("010-7175-4819");
        message1.setReceiver("010123123");
        Messages[1] = message1;
        
        Message message2 = new Message();
        message2.setSender("010-7175-4819");
        message2.setSubject("제목_3");
        message2.setContent("내용_3");
        message2.setReceiver("010123123");
        message2.setReceiverName("수신자명3");
        Messages[2] = message2;        
//        String receiptNum = messageService.sendLMS("1234567890", Messages, null, "testkorea", "20180717_03");
//        
//        String receiptNum = messageService.sendLMS("1234567890", "sender~", "subject~~~~~~~", "content~~~~~~~야호야호야호야호", Messages, reserveDT, "testkorea", "20180717_03");
//        String receiptNum = messageService.sendLMS("1234567890", "sender~", "subject~~~~~~~", "content~~~~~~~야호야호야호야호", Messages, null, true, "testkorea", "20180717_04");
        String receiptNum = messageService.sendLMS("1234567890", "sender~", "발신자명><", "subject~~~~~~~", "content~~~~~~~야호야호야호야호", Messages, reserveDT, false, "testkorea", "20180717_05");
        
        assertNotNull(receiptNum);        
        System.out.println(receiptNum);
        
    }
    
    @Test
    public void getMessages_TEST() throws PopbillException {
        
        SentMessage[] messages = messageService.getMessages("1234567890", "018081315000000061");
        
        assertNotNull(messages);
        
        int i=0;
        for(i=0; i<=messages.length; i++){
            System.out.println(messages[i].getSubject()); 
            System.out.println(messages[i].getContent()); 
            System.out.println(messages[i].getSendNum()); 
            System.out.println(messages[i].getSenderName()); 
            System.out.println(messages[i].getReceiveNum()); 
            System.out.println(messages[i].getReceiveName()); 
            System.out.println(messages[i].getReceiptDT()); 
            System.out.println(messages[i].getSendDT()); 
            System.out.println(messages[i].getResultDT()); 
            System.out.println("ReserveDT:"+messages[i].getReserveDT());
            System.out.println(messages[i].getState()); 
            System.out.println(messages[i].getResult()); 
            System.out.println(messages[i].getMessageType()); 
            System.out.println(messages[i].getTranNet()); 
            System.out.println(messages[i].getReceiptNum()); 
            System.out.println(messages[i].getRequestNum()); 
        }
            
    }        
    
    @Test
    public void getMessagesRN_TEST() throws PopbillException {
        SentMessage[] messages = messageService.getMessagesRN("1234567890", "20180813155254");
        
        assertNotNull(messages);
        
        int i=0;
        for(i=0; i<=messages.length; i++){
            System.out.println(messages[i].getSubject()); 
            System.out.println(messages[i].getContent()); 
            System.out.println(messages[i].getSendNum()); 
            System.out.println(messages[i].getSenderName()); 
            System.out.println(messages[i].getReceiveNum()); 
            System.out.println(messages[i].getReceiveName()); 
            System.out.println(messages[i].getReceiptDT()); 
            System.out.println(messages[i].getSendDT()); 
            System.out.println(messages[i].getResultDT());
            System.out.println("ReserveDT:"+messages[i].getReserveDT());
            System.out.println(messages[i].getState()); 
            System.out.println(messages[i].getResult()); 
            System.out.println(messages[i].getMessageType()); 
            System.out.println(messages[i].getTranNet()); 
            System.out.println(messages[i].getReceiptNum()); 
            System.out.println(messages[i].getRequestNum()); 
        }
    }

    @Test
    public void cancelReserve_TEST() throws PopbillException {
        String receiptNum = "020090117000000007";
        
        Response response = messageService.cancelReserve("1234567890", receiptNum);
        assertNotNull(response);
        
        System.out.println(response.getMessage());
        
    }
    
    @Test
    public void cancelReserveRN_TEST() throws PopbillException {
        String requestNum = "20180723_MMS_32";
        
        Response response = messageService.cancelReserveRN("1234567890", requestNum);
        assertNotNull(response);
        
        System.out.println(response.getMessage());
        
    }    
    
    
    @Test
    public void sendXMS_Single_TEST() throws PopbillException {
        Date reserveDT = new Date();
        reserveDT.setDate(reserveDT.getDate() + 1);
        
        Message[] Messages = new Message[3];
        
        Message message0 = new Message();
        message0.setSender("010-7175-4819");
        message0.setSubject("제목_1");
        message0.setContent("내용_1");
        message0.setReceiver("010123123");
        message0.setReceiverName("수신자명1");
        Messages[0] = message0;
        
        Message message1 = new Message();
        message1.setSender("010-7175-4819");
        message1.setSubject("제목_2");
        message1.setContent("내용_2 ㅎㅎㅎㅎㅎ~~~");
        message1.setSenderName("안녕하십니까? 발신자");
        message1.setReceiver("010123123");
        message1.setReceiverName("수신자명1");
        Messages[1] = message1;
        
        Message message2 = new Message();
        message2.setSender("010-7175-4819");
        message2.setSubject("제목_3");
        message2.setContent("내용_3");
        message2.setReceiver("010123123");
        message2.setReceiverName("수신자명3");
        Messages[2] = message2;        
        
        
//        String receiptNum = messageService.sendXMS("1234567890","010-7175-4819","010-111-222","수신자명!!!!","장문메시지 제목","메시지 길이에 따라 90Byte 이하는 단문, 이상은 장문으로 전송 이것도 광고는 아닙ㄴ다.", reserveDT, "testkorea", "20180717_20");
//        String receiptNum = messageService.sendXMS("1234567890","010-7175-4819","010-111-222","수신자명!!!!","XML메시지 제목","테스트는 꼼꼼히 해야됩니다.메시지 길이에 따라 90Byte 이하는 단문, 이상은 장문으로 전송 이것도 광고는 아닙ㄴ다.", reserveDT, false, "testkorea", "20180717_21");
//        String receiptNum = messageService.sendXMS("1234567890", Messages, reserveDT, "testkorea", "20180717_22");
//        String receiptNum = messageService.sendXMS("1234567890","sender~~1", "subject~~2", "content~~3", Messages, null, "testkorea", "20180717_23");
//        String receiptNum = messageService.sendXMS("1234567890","sender~~1", "subject~~2", "content~~3", Messages, reserveDT, true, "testkorea", "20180717_24");
        String receiptNum = messageService.sendXMS("1234567890","sender~~1","나는야~발신자!", "subject~~2", "content~~3", Messages, reserveDT, true, "testkorea", "20180717_25");
        
        assertNotNull(receiptNum);
        
        System.out.println(receiptNum);
        
    }
    
    @Test
    public void sendMMS_Single_TEST() throws PopbillException {
        Date reserveDT = new Date();
        reserveDT.setDate(reserveDT.getDate() + 1);
        
        File file = new File("/Users/김은혜/Desktop/산타할부지.jpg");
        
//        String receiptNum = messageService.sendMMS("1234567890", "010-7175-4819", "010-111-222", "test~수신자명", "MMS 메시지 제목", "메시지내용_광고아닙니다!!!!!구리구리", file, reserveDT, "testkorea", "20180717_11");
//        String receiptNum = messageService.sendMMS("1234567890", "010-7175-4819", "010-111-222", "test~수신자명", "MMS 메시지 제목", "광고일수도있고 아닐수도있고 알약알약알약", file, reserveDT, true, "testkorea", "20180717_12");

        Message[] Messages = new Message[3];
        
        Message message0 = new Message();
        message0.setSender("010-7175-4819");
        message0.setSubject("제목_1");
        message0.setContent("내용_1");
        message0.setReceiver("010123123");
        message0.setReceiverName("수신자명1");
        Messages[0] = message0;
        
        Message message1 = new Message();
        message1.setSender("010-7175-4819");
        message1.setReceiver("010123123");
        Messages[1] = message1;
        
        Message message2 = new Message();
        message2.setSender("010-7175-4819");
        message2.setSubject("제목_3");
        message2.setContent("내용_3");
        message2.setReceiver("010123123");
        message2.setReceiverName("수신자명3");
        Messages[2] = message2;        

//        String receiptNum = messageService.sendMMS("1234567890", Messages, file, reserveDT, "testkorea", "20180717_13");
//        String receiptNum = messageService.sendMMS("1234567890", "sender~", "message 제목입니다.", "LMS메시지 내용을 적는곳인데 LMS메시지는 이천바이트까지 작성이 가능합니다. 2000바이트는 한글 1000자를 입력할 수 있습니다.", Messages, file, reserveDT, true, "testkorea", "20180717_15");
        String receiptNum = messageService.sendMMS("1234567890", "sender~", "수신자..4달러 ", "message 제목입니다.", "LMS메시지 내용을 적는곳인데 LMS메시지는 이천바이트까지 작성이 가능합니다. 2000바이트는 한글 1000자를 입력할 수 있습니다.", Messages, file, reserveDT, true, "testkorea", "20180717_16");
        
        assertNotNull(receiptNum);        
        System.out.println(receiptNum);
    }
    
    @Test
    public void sendSMS_Multi_2_TEST() throws PopbillException {
        
        Message[] Messages = new Message[3];
                
        Message message0 = new Message();
        message0.setSender("010-7175-4819");
        message0.setReceiver("010123123");
        message0.setReceiverName("수신자명1");
        Messages[0] = message0;
        
        Message message1 = new Message();
        message1.setSender("010-7175-4819");
        message1.setReceiver("010123123");
        message1.setReceiverName("수신자명2");
        Messages[1] = message1;
        
        Message message2 = new Message();
        message2.setSender("010-7175-4819");
        message2.setReceiver("010123123");
        message2.setReceiverName("수신자명3");
        Messages[2] = message2;
        
        
        Date today = new Date();
        today.setDate(today.getDate() + 1);
        System.out.println(today);
        
        String receiptNum = messageService.sendSMS("1234567890", "01071754819", "테스트발신자명!!!", "안녕하십니까 메시지 내용입니다. 테스트테스트!!!", Messages, today, false, "testkorea");
        
        
        assertNotNull(receiptNum);
        
        System.out.println(receiptNum);
    }    
    @Test
    public void sendSMS_Multi_TEST() throws PopbillException {
        
        Message[] Messages = new Message[3];
                
        Message message0 = new Message();
        message0.setSender("010-7175-4819");
        message0.setReceiver("010123123");
        message0.setReceiverName("수신자명1");
        message0.setSubject("JAVA MMS 개별 메시지 제목");
        message0.setContent("111_SMS 메시지 내용");        
        Messages[0] = message0;
        
        Message message1 = new Message();
        message1.setSender("010-7175-4819");
        message1.setReceiver("010123123");
        message1.setReceiverName("수신자명2");
        message1.setSubject("JAVA MMS 개별 메시지 제목");
        message1.setContent("222_SMS 메시지 내용");        
        Messages[1] = message1;
        
        Message message2 = new Message();
        message2.setSender("010-7175-4819");
        message2.setReceiver("010123123");
        message2.setReceiverName("수신자명3");
        message2.setSubject("JAVA MMS 개별 메시지 제목");
        message2.setContent("333_SMS 메시지 내용");        
        Messages[2] = message2;
        
        String receiptNum = messageService.sendSMS("1234567890", Messages, null, "testkorea", "20180716_19");
        
        
        assertNotNull(receiptNum);
        
        System.out.println(receiptNum);
    }        
    
    @Test
    public void sendMMS_Multi_TEST() throws PopbillException {
        
        File file = new File("C:/test2.jpg");
        Message[] Messages = new Message[2];
                
        Message message = new Message();

        message.setSender("07075103710");
        message.setReceiver("010123123");
        message.setReceiverName("수신자명");
        message.setSubject("JAVA MMS 개별 메시지 제목");
        message.setContent("MMS 메시지 내용");
        
        Messages[0] = message;
        Messages[1] = message;
        
        String receiptNum = messageService.sendMMS("1234567890", Messages, file, null, "testkorea");
        
        
        assertNotNull(receiptNum);
        
        System.out.println(receiptNum);
    }
    
    
    public static Date addMinutes(Date date, int minutes)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, minutes); //minus number would decrement the days
        return cal.getTime();
    }
    
    @Test
    public void search_TEST() throws PopbillException{
        String SDate = "20220119";
        String EDate = "20220208";
        String[] State = {"1","2","3","4"};
        String[] Item = {"SMS", "LMS", "MMS"};
        Boolean ReserveYN = false;
        Boolean SenderYN = true;
        int Page = 1;
        int PerPage = 50;
        String Order = "D";
        String QString = "발행자 상호_수정";
        
        MSGSearchResult response = messageService.search("1234567890", SDate, EDate, State, Item, ReserveYN, SenderYN, Page, PerPage, Order, QString);
        
        assertNotNull(response);
        
        System.out.println(response.getTotal() );
    }
    
    @Test
    public void sendSMS_adsYN_TEST() throws PopbillException {
        
        String receiptNum = messageService.sendSMS("1234567890","070-7510-3710","010123123","테스트","단문문자메시지 내용",null,true, "testkorea");
        
        assertNotNull(receiptNum);
        
        System.out.println(receiptNum);
        
    }
    
    @Test
    public void sendLMS_adsYN_TEST() throws PopbillException {
        
        String receiptNum = messageService.sendLMS("1234567890","070-7510-3710","010123123","수신자명","테스트","장문메시지 내용",null,true, "testkorea");
        
        assertNotNull(receiptNum);
        
        System.out.println(receiptNum);
        
    }
    
    @Test
    public void sendXMS_adsYN_TEST() throws PopbillException {
        
        String receiptNum = messageService.sendXMS("1234567890","070-7510-3710","010123213","수신자명","테스트","장문메시지 내용",null,true, "testkorea");
        
        assertNotNull(receiptNum);
        
        System.out.println(receiptNum);
        
    }
    
    @Test
    public void sendSMS_Multi_adsYN_TEST() throws PopbillException {
        
        Message[] Messages = new Message[2];
                
        Message message = new Message();

        message.setSender("07075103710");
        message.setReceiver("010123123");
        message.setReceiverName("수신자명");
        message.setSubject("JAVA SMS 개별 메시지 제목");
        message.setContent("SMS 메시지 내용");
        
        Messages[0] = message;
        Messages[1] = message;
        
        String receiptNum = messageService.sendSMS("1234567890", null, null, Messages, null,true, "testkorea");
        
        
        assertNotNull(receiptNum);
        
        System.out.println(receiptNum);
    }
    
    @Test
    public void sendLMS_Multi_adsYN_TEST() throws PopbillException {
        
        Message[] Messages = new Message[2];
                
        Message message = new Message();

        message.setSender("07075103710");
        message.setReceiver("010111222");
        message.setReceiverName("수신자명");
        message.setSubject("JAVA LMS 개별 메시지 제목");
        message.setContent("LMS 메시지 내용");
        
        Messages[0] = message;
        Messages[1] = message;
        
        String receiptNum = messageService.sendLMS("1234567890",null, null, null, Messages, null,true, "testkorea");
        
        
        assertNotNull(receiptNum);
        
        System.out.println(receiptNum);
    }
    
    @Test
    public void sendMMS_Single_adsYN_TEST() throws PopbillException {
        
        File file = new File("/Users/John/Desktop/test.jpg");
        
        String receiptNum = messageService.sendMMS("1234567890", "07075103710", "010123123", "수신자명", "JAVA MMS 동보 메시지 제목", "메시지내용", file, null, true, "testkorea");
        
        assertNotNull(receiptNum);
        
        System.out.println(receiptNum);
    }
    
    @Test
    public void sendMMS_multi_adsYN_TEST() throws PopbillException{
        Message[] Messages = new Message[2];
                
        Message message = new Message();
        
        message.setSender("07075103710");
        message.setReceiver("010123123");
        message.setReceiverName("수신자명");
        message.setSubject("JAVA LMS 개별 메시지 제목");
        message.setContent("LMS 메시지 내용");
        
        Messages[0] = message;
        Messages[1] = message;
        
        File file = new File("/Users/John/Documents/test.jpg");
        
        String receiptNum = messageService.sendMMS("1234567890", null, null, null, Messages, file, null, true, "testkorea");
        assertNotNull(receiptNum);
        System.out.println(receiptNum);
    }
    
    @Test
    public void getAutoDenyList_TEST() throws PopbillException{
        AutoDeny[] response = messageService.getAutoDenyList("1234567890");
        assertNotNull(response);
        System.out.println(response[0].getNumber() + " " + response[0].getRegDT());
        System.out.println(response[1].getNumber() + " " + response[1].getRegDT());
    }
    
    @Test
    public void getSenderNumberList_TEST() throws PopbillException {
        
        SenderNumber[] listInfo = messageService.getSenderNumberList("1234567890", "testkorea");
        
        int i;
        for (i=0; i<listInfo.length; i++){
            System.out.println(listInfo[i].getNumber());
            System.out.println(listInfo[i].getState());
            System.out.println(listInfo[i].getRepresentYN());
            System.out.println(listInfo[i].getMemo());
        }
    }
    
    @Test
    public void getStates_TEST() throws PopbillException {
        String[] ReceiptNumList = new String[] {"018071909000000004", "asdfasdf", "018071909000000003", "018071909000000002", "018071711000000032"};        
        MessageBriefInfo[] listInfo = messageService.getStates("1234567890", ReceiptNumList, "testkorea");
        int i;
        for (i=0; i<listInfo.length; i++){
            System.out.println("rNum : " + listInfo[i].getrNum());
            System.out.println("sn : " + listInfo[i].getSn());
            System.out.println("Stat : " + listInfo[i].getStat());
            System.out.println("rlt : " + listInfo[i].getRlt());
            System.out.println("sDT : " + listInfo[i].getsDT());
            System.out.println("rDT : " + listInfo[i].getrDT());
            System.out.println("net : " + listInfo[i].getNet());
            System.out.println("srt : " + listInfo[i].getSrt());
            System.out.println("==========");
        }
    }

    @Test
    public void GetSenderNumberMgtURL_TEST() throws PopbillException {

        String url = messageService.getSenderNumberMgtURL("1234567890","testkorea");

        System.out.println(url);
    }

    @Test
    public void GetSentListURL_TEST() throws PopbillException {

        String url = messageService.getSentListURL("1234567890","testkorea");

        System.out.println(url);
    }

    @Test
    public void checkSenderNumber_TEST() throws PopbillException {
        Response response = messageService.checkSenderNumber("1234567890", "070-4304-2991");
        assertNotNull(response);
        
        System.out.println(response.getCode());
        System.out.println(response.getMessage());
    }

//private Date addMinutes(Date date, int i) {
//    // TODO Auto-generated method stub
//    return null;
//}
}
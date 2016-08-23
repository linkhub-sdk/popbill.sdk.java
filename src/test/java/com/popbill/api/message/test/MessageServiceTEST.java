package com.popbill.api.message.test;

import static org.junit.Assert.assertNotNull;

import java.io.File;
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
import com.popbill.api.message.MessageServiceImp;
import com.popbill.api.message.MessageType;
import com.popbill.api.message.SentMessage;

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
		
		String url = messageService.getURL("1231212312", "userid", "BOX");

		assertNotNull(url);
		System.out.println(url);
	}
	
	@Test
	public void sendSMS_Single_TEST() throws PopbillException {
		
		String receiptNum = messageService.sendSMS("1234567890","070-7510-6766", "010-111-5117","테스트","단문문자메시지 내용",null,"testkorea");
		
		assertNotNull(receiptNum);
		
		System.out.println(receiptNum);
	}
	
	@Test
	public void sendSMS_Reserve_15Minute_TEST() throws PopbillException {
		
		Date ReserveDT = addMinutes(new Date(),15);
		
		String receiptNum = messageService.sendSMS("1231212312","070-7510-6766","010-000-111","테스트","단문문자메시지 내용",ReserveDT,"userid");
		
		assertNotNull(receiptNum);
		
		System.out.println(receiptNum);
		
	}
	
	@Test
	public void cancelReserve_TEST() throws PopbillException {
		
		String receiptNum = "014100911000000006";
		
		Response response = messageService.cancelReserve("1231212312", receiptNum, "userid");
		assertNotNull(response);
		
		System.out.println(response.getMessage());
		
	}
	@Test
	public void sendLMS_Single_TEST() throws PopbillException {
		
		String receiptNum = messageService.sendLMS("1234567890","070-7510-6766","010-111-222","테스트","장문메시지 제목","장문문자메시지 내용. 장문문자메시지의 내용은 2000byte까지입니다.",null,"testkorea");
		
		assertNotNull(receiptNum);
		
		System.out.println(receiptNum);
		
	}
	
	@Test
	public void sendXMS_Single_TEST() throws PopbillException {
		
		String receiptNum = messageService.sendXMS("1234567890","070-7510-6766","010-111-222","테스트","장문메시지 제목","메시지 길이에 따라 90Byte 이하는 단문, 이상은 장문으로 전송",null,"testkorea");
		
		assertNotNull(receiptNum);
		
		System.out.println(receiptNum);
		
	}
	
	@Test
	public void sendMMS_Single_TEST() throws PopbillException {
		
		File file = new File("/Users/John/Desktop/test.jpg");
		
		String receiptNum = messageService.sendMMS("1234567890", "07075103710", "010123123", "수신자명", "JAVA MMS 동보 메시지 제목", "메시지내용", file, null, "testkorea");
		
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
	
	@Test
	public void getMessages_TEST() throws PopbillException {
		SentMessage[] messages = messageService.getMessages("1234567890", "016011515000000009");
		
		assertNotNull(messages);
		
		System.out.println(messages.length);
		System.out.println(messages[0].getSendResult() + " "+messages[0].getReceiptDT());		
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
		String SDate = "20151201";
		String EDate = "20160115";
		String[] State = {"1","2","3","4"};
		String[] Item = {"SMS", "LMS", "MMS"};
		Boolean ReserveYN = false;
		Boolean SenderYN = false;
		int Page = 1;
		int PerPage = 50;
		String Order = "A";
		
		MSGSearchResult response = messageService.search("1234567890", SDate, EDate, State, Item, ReserveYN, SenderYN, Page, PerPage, Order);
		
		assertNotNull(response);
		
		System.out.println(response.getTotal() + " " + response.getList().get(0).getTranNet() +" "+response.getList().get(0).getReceiptDT());
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
}
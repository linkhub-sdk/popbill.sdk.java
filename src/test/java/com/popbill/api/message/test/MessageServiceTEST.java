package com.popbill.api.message.test;

import static org.junit.Assert.assertNotNull;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import com.popbill.api.MessageService;
import com.popbill.api.PopbillException;
import com.popbill.api.Response;
import com.popbill.api.message.MessageServiceImp;
import com.popbill.api.message.MessageType;
import com.popbill.api.message.SentMessage;

public class MessageServiceTEST {

	private final String testLinkID = "TESTER";
	private final String testSecretKey = "mYFt3NFG9RKUbbeyr7YbUjAt60iQphNVM6EuNRj5VfQ=";

	private MessageService messageService;
	
	public MessageServiceTEST() {
		MessageServiceImp service = new MessageServiceImp();

		service.setLinkID(testLinkID);
		service.setSecretKey(testSecretKey);
		service.setTest(true);
		
		messageService = service;
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
		
		String receiptNum = messageService.sendSMS("1231212312","070-7510-6766","010-4168-0206","테스트","단문문자메시지 내용",null,"userid");
		
		assertNotNull(receiptNum);
		
		System.out.println(receiptNum);
		
	}
	
	@Test
	public void sendSMS_Reserve_15Minute_TEST() throws PopbillException {
		
		Date ReserveDT = addMinutes(new Date(),15);
		
		String receiptNum = messageService.sendSMS("1231212312","070-7510-6766","010-4168-0206","테스트","단문문자메시지 내용",ReserveDT,"userid");
		
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
		
		String receiptNum = messageService.sendLMS("1231212312","070-7510-6766","010-4168-0206","테스트","장문메시지 제목","장문문자메시지 내용. 장문문자메시지의 내용은 2000byte까지입니다.",null,"userid");
		
		assertNotNull(receiptNum);
		
		System.out.println(receiptNum);
		
	}
	
	@Test
	public void sendXMS_Single_TEST() throws PopbillException {
		
		String receiptNum = messageService.sendXMS("1231212312","070-7510-6766","010-4168-0206","테스트","장문메시지 제목","메시지 길이에 따라 90Byte 이하는 단문, 이상은 장문으로 전송",null,"userid");
		
		assertNotNull(receiptNum);
		
		System.out.println(receiptNum);
		
	}
	
	@Test
	public void getMessages_TEST() throws PopbillException {
		SentMessage[] messages = messageService.getMessages("1231212312", "014100911000000008");
		
		assertNotNull(messages);
		
		System.out.println(messages.length);
		System.out.println(messages[0].getMessageType());		
	}
	public static Date addMinutes(Date date, int minutes)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, minutes); //minus number would decrement the days
        return cal.getTime();
    }
}

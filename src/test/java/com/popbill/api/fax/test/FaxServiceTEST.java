package com.popbill.api.fax.test;

import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import com.popbill.api.ChargeInfo;
import com.popbill.api.FaxService;
import com.popbill.api.PopbillException;
import com.popbill.api.Response;
import com.popbill.api.fax.FAXSearchResult;
import com.popbill.api.fax.FaxResult;
import com.popbill.api.fax.FaxServiceImp;
import com.popbill.api.fax.Receiver;

public class FaxServiceTEST {

	private final String testLinkID = "TESTER";
	private final String testSecretKey = "SwWxqU+0TErBXy/9TVjIPEnI0VTUMMSQZtJf3Ed8q3I=";

	private FaxService faxService;
	
	public FaxServiceTEST() {
		FaxServiceImp service = new FaxServiceImp();

		service.setLinkID(testLinkID);
		service.setSecretKey(testSecretKey);
		service.setTest(true);
		
		faxService = service;
	}
	
	@Test
	public void getChargeInfo_TEST() throws PopbillException {
		
		ChargeInfo chrgInfo = faxService.getChargeInfo("1234567890");

		System.out.println(chrgInfo.getChargeMethod());
		System.out.println(chrgInfo.getRateSystem());
		System.out.println(chrgInfo.getUnitCost());
	}
	
	@Test
	public void getUnitCost_TEST() throws PopbillException {
		
		float UnitCost = faxService.getUnitCost("1234567890");

		System.out.println(UnitCost);
	}
	
	@Test
	public void getURL_TEST() throws PopbillException {
		
		String url = faxService.getURL("1234567890", "BOX");

		assertNotNull(url);
		System.out.println(url);
	}
	
	@Test
	public void sendFAX_Single_TEST() throws PopbillException {
		
		File file = new File("/Users/John/Desktop/test.jpg");
		
		String receiptNum = faxService.sendFAX("1234567890", "070-4304-2991", "070-111-222","수신자명칭",file, null);
		
		assertNotNull(receiptNum);
		
		System.out.println(receiptNum);
		
		FaxResult[] results = faxService.getFaxResult("1234567890", receiptNum);
		
		assertNotNull(results);
		
		System.out.println(results[0].getFileNames()[0] + " "+results[0].getSenderName());
	}
	@Test
	public void resendFAX_Single_TEST() throws PopbillException {

		String testCorpNum = "1234567890";
		String orgReceiptNum = "017021716071900001";
		String senderNum = "07043042991";
		String senderName = "발신자명";
		String receiveNum = "070111222";
		String receiveName = "수신자명";
		
		String receiptNum = faxService.resendFAX(testCorpNum, orgReceiptNum, senderNum, senderName, receiveNum, receiveName, null, null);
		
		System.out.println(receiptNum);
	}
	
	@Test
	public void resendFAX_Multi_TEST() throws PopbillException {

		String testCorpNum = "1234567890";
		String orgReceiptNum = "017021716071900001";
		String senderNum = "07043042991";
		String senderName = "발신자명";
		
		Receiver receiver1 = new Receiver();
		receiver1.setReceiveName("수신자명");
		receiver1.setReceiveNum("070111222");
		
		Receiver receiver2 = new Receiver();
		receiver2.setReceiveName("수신자명");
		receiver2.setReceiveNum("070111222");
		
		Receiver[] receivers = new Receiver[] {receiver1, receiver2};
		
		String receiptNum = faxService.resendFAX(testCorpNum, orgReceiptNum, senderNum, senderName, receivers, null);
		
		assertNotNull(receiptNum);
		
		System.out.println(receiptNum);
		
		FaxResult[] results = faxService.getFaxResult("1234567890", receiptNum);
		
		assertNotNull(results);
		
		System.out.println(results[0].getFileNames()[0] + " "+results[0].getSenderName());
	}
	
	@Test
	public void sendFAX_MultiFile_TEST() throws PopbillException {
		
		File file1 = new File("/Users/John/Desktop/test.jpg");
		
		String receiptNum = faxService.sendFAX("1234567890", "02-6442-9700","111-2222-3333","수신자명칭",new File[]{file1}, null);
		
		assertNotNull(receiptNum);
		
		
		FaxResult[] results = faxService.getFaxResult("1234567890", receiptNum);
		
		assertNotNull(results);
		
		System.out.println(results[0].getFileNames()[0] + " "+results[0].getSenderName());
	}
	
	@Test
	public void getFaxResult_TEST() throws PopbillException {
		
		String receiptNum = "015122413083700001";
		
		FaxResult[] results = faxService.getFaxResult("1234567890", receiptNum);
		
		assertNotNull(results);
		
		System.out.println(results[0].getFileNames()[1] + " "+results[0].getReceiptDT());
		
	}
	
	@Test
	public void cancelReserve_TEST() throws PopbillException {
		
		String receiptNum = "017030613315600002";
		
		Response response = faxService.cancelReserve("1234567890", receiptNum);
		
		assertNotNull(response);
		
		System.out.println(response.getMessage());
	}
	
	@Test
	public void search_TEST() throws PopbillException{
		String SDate = "20160801";
		String EDate = "20160831";
		String[] State = {"1","2","3","4"};
		Boolean ReserveYN = false;
		Boolean SenderOnlyYN = false;
		int Page = 1;
		int PerPage = 10;
		String Order = "D";
		FAXSearchResult response = faxService.search("1234567890", SDate, EDate, State, ReserveYN, SenderOnlyYN, Page, PerPage, Order);
		
		assertNotNull(response);
		System.out.println(response.getTotal()+" "+response.getList().get(0).getReceiptDT()+" "+response.getList().get(0).getSenderName());
	}
	
	public static Date addMinutes(Date date, int minutes)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, minutes); //minus number would decrement the days
        return cal.getTime();
    }
}

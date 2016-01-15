package com.popbill.api.fax.test;

import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import com.popbill.api.FaxService;
import com.popbill.api.PopbillException;
import com.popbill.api.Response;
import com.popbill.api.fax.FaxResult;
import com.popbill.api.fax.FaxServiceImp;

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
	public void getUnitCost_TEST() throws PopbillException {
		
		float UnitCost = faxService.getUnitCost("1231212312");

		System.out.println(UnitCost);
	}
	
	@Test
	public void getURL_TEST() throws PopbillException {
		
		String url = faxService.getURL("1231212312", "userid", "BOX");

		assertNotNull(url);
		System.out.println(url);
	}
	
	@Test
	public void sendFAX_Single_TEST() throws PopbillException {
		
		File file = new File("/Users/cream/Desktop/test.jpg");
		
		String receiptNum = faxService.sendFAX("1231212312", "02-6442-9700","111-2222-3333","수신자명칭",file, null,"userid");
		
		assertNotNull(receiptNum);
		
		System.out.println(receiptNum);
	}
	
	@Test
	public void sendFAX_MultiFile_TEST() throws PopbillException {
		
		File file1 = new File("/Users/cream/Desktop/test.jpg");
		File file2 = new File("/Users/cream/Desktop/사업자등록증.jpg");
		
		String receiptNum = faxService.sendFAX("1231212312", "02-6442-9700","111-2222-3333","수신자명칭",new File[]{file1,file2}, null,"userid");
		
		assertNotNull(receiptNum);
		
		System.out.println(receiptNum);
	}
	
	@Test
	public void getFaxResult_TEST() throws PopbillException {
		
		String receiptNum = "015122413083700001";
		
		FaxResult[] results = faxService.getFaxResult("1234567890", receiptNum);
		
		assertNotNull(results);
		
		System.out.println(results[0].getFileNames()[1]);
		
	}
	
	@Test
	public void cancelReserve_TEST() throws PopbillException {
		
		String receiptNum = "014101010184200001";
		
		Response response = faxService.cancelReserve("1231212312", receiptNum,"userid");
		
		assertNotNull(response);
		
		System.out.println(response.getMessage());
		
	}
	
	public static Date addMinutes(Date date, int minutes)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, minutes); //minus number would decrement the days
        return cal.getTime();
    }
}

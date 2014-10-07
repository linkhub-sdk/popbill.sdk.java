package com.popbill.api.test;


import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.popbill.api.JoinForm;
import com.popbill.api.PopbillException;
import com.popbill.api.Response;
import com.popbill.api.taxinvoice.TaxinvoiceService;

public class BaseServiceTEST {

	private final String testLinkID = "TESTER";
	private final String testSecretKey ="PSVh7+mTe1b2av+PeZEiDdwuAXq2LU1JeiMfWFhbL00=";
	
	@Test
	public void getPopbillURL_TEST() throws PopbillException {
	
		TaxinvoiceService taxinvoiceService = new TaxinvoiceService();
		
		taxinvoiceService.setLinkID(testLinkID);
		taxinvoiceService.setSecretKey(testSecretKey);
		taxinvoiceService.setTest(true);
		
		String url = taxinvoiceService.getPopbillURL("1231212312", "userid", "LOGIN");
		
		assertNotNull(url);
		
		System.out.println(url);

	}
	
	@Test
	public void joinMember_TEST() throws PopbillException {
		
		TaxinvoiceService taxinvoiceService = new TaxinvoiceService();
		
		taxinvoiceService.setLinkID(testLinkID);
		taxinvoiceService.setSecretKey(testSecretKey);
		taxinvoiceService.setTest(true);
		
		JoinForm joinInfo = new JoinForm();
		
		 joinInfo.setLinkID(testLinkID);
         joinInfo.setCorpNum("1231212312");          //사업자번호 "-" 제외
         joinInfo.setCEOName("대표자성명");
         joinInfo.setCorpName("상호");
         joinInfo.setAddr("주소");
         joinInfo.setZipCode("500-100");
         joinInfo.setBizType("업태");
         joinInfo.setBizClass("업종");
         joinInfo.setID("userid");                   //6자 이상 20자 미만
         joinInfo.setPWD("pwd_must_be_long_enough"); //6자 이상 20자 미만
         joinInfo.setContactName("담당자명");
         joinInfo.setContactTEL("02-999-9999");
         joinInfo.setContactHP("010-1234-5678");
         joinInfo.setContactFAX("02-999-9998");
         joinInfo.setContactEmail("test@test.com");
		
		Response response = taxinvoiceService.joinMember(joinInfo);
		
		assertNotNull(response);
		
		System.out.println(response.getMessage());
	}
	
	@Test
	public void getBalance_TEST() throws PopbillException {
		

		TaxinvoiceService taxinvoiceService = new TaxinvoiceService();
		
		taxinvoiceService.setLinkID(testLinkID);
		taxinvoiceService.setSecretKey(testSecretKey);
		taxinvoiceService.setTest(true);
		
		double balance = taxinvoiceService.getBalance("1231212312");
		
		System.out.println(balance);
	}
	
	@Test
	public void getPartnerBalance_TEST() throws PopbillException {
		

		TaxinvoiceService taxinvoiceService = new TaxinvoiceService();
		
		taxinvoiceService.setLinkID(testLinkID);
		taxinvoiceService.setSecretKey(testSecretKey);
		taxinvoiceService.setTest(true);
		
		double balance = taxinvoiceService.getPartnerBalance("1231212312");
		
		System.out.println(balance);
	}
	
}

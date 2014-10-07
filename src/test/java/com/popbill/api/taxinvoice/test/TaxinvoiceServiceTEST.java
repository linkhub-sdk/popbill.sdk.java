package com.popbill.api.taxinvoice.test;


import static org.junit.Assert.assertNotNull;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;

import com.popbill.api.AttachedFile;
import com.popbill.api.PopbillException;
import com.popbill.api.Response;
import com.popbill.api.taxinvoice.EmailPublicKey;
import com.popbill.api.taxinvoice.MgtKeyType;
import com.popbill.api.taxinvoice.Taxinvoice;
import com.popbill.api.taxinvoice.TaxinvoiceDetail;
import com.popbill.api.taxinvoice.TaxinvoiceInfo;
import com.popbill.api.taxinvoice.TaxinvoiceLog;
import com.popbill.api.taxinvoice.TaxinvoiceService;

public class TaxinvoiceServiceTEST {

	private final String testLinkID = "TESTER";
	private final String testSecretKey ="PSVh7+mTe1b2av+PeZEiDdwuAXq2LU1JeiMfWFhbL00=";
	
	@Test
	public void getUnitCost_TEST() throws PopbillException {
		TaxinvoiceService taxinvoiceService = new TaxinvoiceService();
		
		taxinvoiceService.setLinkID(testLinkID);
		taxinvoiceService.setSecretKey(testSecretKey);
		taxinvoiceService.setTest(true);
		
		float UnitCost = taxinvoiceService.getUnitCost("1231212312");
		
		System.out.println(UnitCost);
	}
	
	@Test
	public void getPopbillURL_TEST() throws PopbillException {
	
		TaxinvoiceService taxinvoiceService = new TaxinvoiceService();
		
		taxinvoiceService.setLinkID(testLinkID);
		taxinvoiceService.setSecretKey(testSecretKey);
		taxinvoiceService.setTest(true);
		
		EmailPublicKey[] resultList = taxinvoiceService.getEmailPublicKeys("1231212312");
		
		
		assertNotNull(resultList);
	}

	@Test
	public void getCertificateExpireDate_TEST() throws PopbillException {
		TaxinvoiceService taxinvoiceService = new TaxinvoiceService();
		
		taxinvoiceService.setLinkID(testLinkID);
		taxinvoiceService.setSecretKey(testSecretKey);
		taxinvoiceService.setTest(true);
		
		Date ExpireDate = taxinvoiceService.getCertificateExpireDate("4108600477");
		
		assertNotNull(ExpireDate);
		
		System.out.println(ExpireDate);
	}
	
	@Test
	public void getURL_TEST() throws PopbillException {
		TaxinvoiceService taxinvoiceService = new TaxinvoiceService();
		
		taxinvoiceService.setLinkID(testLinkID);
		taxinvoiceService.setSecretKey(testSecretKey);
		taxinvoiceService.setTest(true);
		
		String url = taxinvoiceService.getURL("1231212312", "userid", "LOGIN");
		
		assertNotNull(url);
		System.out.println(url);
	}
	
	@Test
	public void checkMgtKeyInUse_TEST() throws PopbillException {
		TaxinvoiceService taxinvoiceService = new TaxinvoiceService();
		
		taxinvoiceService.setLinkID(testLinkID);
		taxinvoiceService.setSecretKey(testSecretKey);
		taxinvoiceService.setTest(true);	
		
		boolean useYN = taxinvoiceService.checkMgtKeyInUse("1231212312", MgtKeyType.SELL, "1234");
		
		System.out.println(useYN);
	}
	
	
	@Test
	public void register_TEST() throws PopbillException {
		TaxinvoiceService taxinvoiceService = new TaxinvoiceService();
		
		taxinvoiceService.setLinkID(testLinkID);
		taxinvoiceService.setSecretKey(testSecretKey);
		taxinvoiceService.setTest(true);
		
		Taxinvoice taxinvoice = new Taxinvoice();
		
		taxinvoice.setWriteDate("20141002");                      //필수, 기재상 작성일자
        taxinvoice.setChargeDirection("정과금");                  //필수, {정과금, 역과금}
        taxinvoice.setIssueType("정발행");                        //필수, {정발행, 역발행, 위수탁}
        taxinvoice.setPurposeType("영수");                        //필수, {영수, 청구}
        taxinvoice.setIssueTiming("직접발행");                    //필수, {직접발행, 승인시자동발행}
        taxinvoice.setTaxType("과세");                            //필수, {과세, 영세, 면세}


        taxinvoice.setInvoicerCorpNum("1231212312");
        taxinvoice.setInvoicerTaxRegID("");                       //종사업자 식별번호. 필요시 기재. 형식은 숫자 4자리.
        taxinvoice.setInvoicerCorpName("공급자 상호");
        taxinvoice.setInvoicerMgtKey("1234");                         //공급자 발행까지 API로 발행하고자 할경우 정발행과 동일한 형태로 추가 기재.
        taxinvoice.setInvoicerCEOName("공급자 대표자 성명");
        taxinvoice.setInvoicerAddr("공급자 주소");
        taxinvoice.setInvoicerBizClass("공급자 업종");
        taxinvoice.setInvoicerBizType("공급자 업태,업태2");
        taxinvoice.setInvoicerContactName("공급자 담당자명");
        taxinvoice.setInvoicerEmail("test@test.com");
        taxinvoice.setInvoicerTEL("070-7070-0707");
        taxinvoice.setInvoicerHP("010-000-2222");
        taxinvoice.setInvoicerSMSSendYN(true);                    //발행시 문자발송기능 사용시 활용

        taxinvoice.setInvoiceeType("사업자");
        taxinvoice.setInvoiceeCorpNum("8888888888");
        taxinvoice.setInvoiceeCorpName("공급받는자 상호");
        taxinvoice.setInvoiceeMgtKey("");            //문서관리번호 1~24자리까지 공급받는자 사업자번호별 중복없는 고유번호 할당
        taxinvoice.setInvoiceeCEOName("공급받는자 대표자 성명");
        taxinvoice.setInvoiceeAddr("공급받는자 주소");
        taxinvoice.setInvoiceeBizClass("공급받는자 업종");
        taxinvoice.setInvoiceeBizType("공급받는자 업태");
        taxinvoice.setInvoiceeContactName1("공급받는자 담당자명");
        taxinvoice.setInvoiceeEmail1("test@invoicee.com");

        taxinvoice.setSupplyCostTotal("100000");                  //필수 공급가액 합계"
        taxinvoice.setTaxTotal("10000");                          //필수 세액 합계
        taxinvoice.setTotalAmount("110000");                      //필수 합계금액.  공급가액 + 세액

        taxinvoice.setModifyCode(null);                           //수정세금계산서 작성시 1~6까지 선택기재.
        taxinvoice.setOriginalTaxinvoiceKey("");                  //수정세금계산서 작성시 원본세금계산서의 ItemKey기재. ItemKey는 문서확인.
        taxinvoice.setSerialNum("123");
        taxinvoice.setCash("");                                   //현금
        taxinvoice.setChkBill("");                                //수표
        taxinvoice.setNote("");                                   //어음
        taxinvoice.setCredit("");                                 //외상미수금
        taxinvoice.setRemark1("비고1");
        taxinvoice.setRemark2("비고2");
        taxinvoice.setRemark3("비고3");
        taxinvoice.setKwon((short) 1);
        taxinvoice.setHo((short) 1);

        taxinvoice.setBusinessLicenseYN(false);                   //사업자등록증 이미지 첨부시 설정.
        taxinvoice.setBankBookYN(false);                          //통장사본 이미지 첨부시 설정.
     
        taxinvoice.setDetailList(new ArrayList<TaxinvoiceDetail>());

        TaxinvoiceDetail detail = new TaxinvoiceDetail();

        detail.setSerialNum((short) 1);                                   //일련번호
        detail.setPurchaseDT("20140319");                         //거래일자
        detail.setItemName("품목명");
        detail.setSpec("규격");
        detail.setQty("1");                                       //수량
        detail.setUnitCost("100000");                             //단가
        detail.setSupplyCost("100000");                           //공급가액
        detail.setTax("10000");                                   //세액
        detail.setRemark("품목비고");

        taxinvoice.getDetailList().add(detail);

        detail = new TaxinvoiceDetail();

        detail.setSerialNum((short) 2);       
        detail.setItemName("품목명");

        taxinvoice.getDetailList().add(detail);
		
		
		Response response = taxinvoiceService.register("1231212312", taxinvoice);
		
		assertNotNull(response);
		
		System.out.println(response.getMessage());
	}
	
	@Test
	public void update_TEST() throws PopbillException {
		TaxinvoiceService taxinvoiceService = new TaxinvoiceService();
		
		taxinvoiceService.setLinkID(testLinkID);
		taxinvoiceService.setSecretKey(testSecretKey);
		taxinvoiceService.setTest(true);
		
		Taxinvoice taxinvoice = new Taxinvoice();
		
		taxinvoice.setWriteDate("20141002");                      //필수, 기재상 작성일자
        taxinvoice.setChargeDirection("정과금");                  //필수, {정과금, 역과금}
        taxinvoice.setIssueType("정발행");                        //필수, {정발행, 역발행, 위수탁}
        taxinvoice.setPurposeType("영수");                        //필수, {영수, 청구}
        taxinvoice.setIssueTiming("직접발행");                    //필수, {직접발행, 승인시자동발행}
        taxinvoice.setTaxType("과세");                            //필수, {과세, 영세, 면세}


        taxinvoice.setInvoicerCorpNum("1231212312");
        taxinvoice.setInvoicerTaxRegID("");                       //종사업자 식별번호. 필요시 기재. 형식은 숫자 4자리.
        taxinvoice.setInvoicerCorpName("공급자 상호_수정");
        taxinvoice.setInvoicerMgtKey("1234");                         //공급자 발행까지 API로 발행하고자 할경우 정발행과 동일한 형태로 추가 기재.
        taxinvoice.setInvoicerCEOName("공급자 대표자 성명");
        taxinvoice.setInvoicerAddr("공급자 주소");
        taxinvoice.setInvoicerBizClass("공급자 업종");
        taxinvoice.setInvoicerBizType("공급자 업태,업태2");
        taxinvoice.setInvoicerContactName("공급자 담당자명");
        taxinvoice.setInvoicerEmail("test@test.com");
        taxinvoice.setInvoicerTEL("070-7070-0707");
        taxinvoice.setInvoicerHP("010-000-2222");
        taxinvoice.setInvoicerSMSSendYN(true);                    //발행시 문자발송기능 사용시 활용

        taxinvoice.setInvoiceeType("사업자");
        taxinvoice.setInvoiceeCorpNum("8888888888");
        taxinvoice.setInvoiceeCorpName("공급받는자 상호");
        taxinvoice.setInvoiceeMgtKey("");            //문서관리번호 1~24자리까지 공급받는자 사업자번호별 중복없는 고유번호 할당
        taxinvoice.setInvoiceeCEOName("공급받는자 대표자 성명");
        taxinvoice.setInvoiceeAddr("공급받는자 주소");
        taxinvoice.setInvoiceeBizClass("공급받는자 업종");
        taxinvoice.setInvoiceeBizType("공급받는자 업태");
        taxinvoice.setInvoiceeContactName1("공급받는자 담당자명");
        taxinvoice.setInvoiceeEmail1("test@invoicee.com");

        taxinvoice.setSupplyCostTotal("100000");                  //필수 공급가액 합계"
        taxinvoice.setTaxTotal("10000");                          //필수 세액 합계
        taxinvoice.setTotalAmount("110000");                      //필수 합계금액.  공급가액 + 세액

        taxinvoice.setModifyCode(null);                           //수정세금계산서 작성시 1~6까지 선택기재.
        taxinvoice.setOriginalTaxinvoiceKey("");                  //수정세금계산서 작성시 원본세금계산서의 ItemKey기재. ItemKey는 문서확인.
        taxinvoice.setSerialNum("123");
        taxinvoice.setCash("");                                   //현금
        taxinvoice.setChkBill("");                                //수표
        taxinvoice.setNote("");                                   //어음
        taxinvoice.setCredit("");                                 //외상미수금
        taxinvoice.setRemark1("비고1");
        taxinvoice.setRemark2("비고2");
        taxinvoice.setRemark3("비고3");
        taxinvoice.setKwon((short) 1);
        taxinvoice.setHo((short) 1);

        taxinvoice.setBusinessLicenseYN(false);                   //사업자등록증 이미지 첨부시 설정.
        taxinvoice.setBankBookYN(false);                          //통장사본 이미지 첨부시 설정.
      
        taxinvoice.setDetailList(new ArrayList<TaxinvoiceDetail>());

        TaxinvoiceDetail detail = new TaxinvoiceDetail();

        detail.setSerialNum((short) 1);                                   //일련번호
        detail.setPurchaseDT("20140319");                         //거래일자
        detail.setItemName("품목명");
        detail.setSpec("규격");
        detail.setQty("1");                                       //수량
        detail.setUnitCost("100000");                             //단가
        detail.setSupplyCost("100000");                           //공급가액
        detail.setTax("10000");                                   //세액
        detail.setRemark("품목비고");

        taxinvoice.getDetailList().add(detail);

        detail = new TaxinvoiceDetail();

        detail.setSerialNum((short) 2);       
        detail.setItemName("품목명");

        taxinvoice.getDetailList().add(detail);
		
		
		Response response = taxinvoiceService.update("1231212312",MgtKeyType.SELL,"1234", taxinvoice);
		
		assertNotNull(response);
		
		System.out.println(response.getMessage());
	}
	
	@Test
	public void delete_TEST() throws PopbillException {
		TaxinvoiceService taxinvoiceService = new TaxinvoiceService();
		
		taxinvoiceService.setLinkID(testLinkID);
		taxinvoiceService.setSecretKey(testSecretKey);
		taxinvoiceService.setTest(true);
		
		Response response = taxinvoiceService.delete("1231212312", MgtKeyType.SELL, "1234");
		
		assertNotNull(response);
		
		System.out.println(response.getMessage());
	}
	
	@Test
	public void send_TEST() throws PopbillException {
	TaxinvoiceService taxinvoiceService = new TaxinvoiceService();
		
		taxinvoiceService.setLinkID(testLinkID);
		taxinvoiceService.setSecretKey(testSecretKey);
		taxinvoiceService.setTest(true);
		
		Response response = taxinvoiceService.send("1231212312", MgtKeyType.SELL, "1234","발행예정 메모","userid");
		
		assertNotNull(response);
		
		System.out.println(response.getMessage());
		
	}
	
	@Test
	public void cancelSend_TEST() throws PopbillException {
	TaxinvoiceService taxinvoiceService = new TaxinvoiceService();
		
		taxinvoiceService.setLinkID(testLinkID);
		taxinvoiceService.setSecretKey(testSecretKey);
		taxinvoiceService.setTest(true);
		
		Response response = taxinvoiceService.cancelSend("1231212312", MgtKeyType.SELL, "1234","발행예정 취소 메모","userid");
		
		assertNotNull(response);
		
		System.out.println(response.getMessage());
		
	}
	
	@Test
	public void accept_TEST() throws PopbillException {
	TaxinvoiceService taxinvoiceService = new TaxinvoiceService();
		
		taxinvoiceService.setLinkID(testLinkID);
		taxinvoiceService.setSecretKey(testSecretKey);
		taxinvoiceService.setTest(true);
		
		Response response = taxinvoiceService.accept("1231212312", MgtKeyType.BUY, "1234","승인 메모","userid");
		
		assertNotNull(response);
		
		System.out.println(response.getMessage());
		
	}
	
	@Test
	public void deny_TEST() throws PopbillException {
	TaxinvoiceService taxinvoiceService = new TaxinvoiceService();
		
		taxinvoiceService.setLinkID(testLinkID);
		taxinvoiceService.setSecretKey(testSecretKey);
		taxinvoiceService.setTest(true);
		
		Response response = taxinvoiceService.deny("1231212312", MgtKeyType.BUY, "1234","거부 메모","userid");
		
		assertNotNull(response);
		
		System.out.println(response.getMessage());
		
	}
	
	@Test
	public void issue_TEST() throws PopbillException {
	TaxinvoiceService taxinvoiceService = new TaxinvoiceService();
		
		taxinvoiceService.setLinkID(testLinkID);
		taxinvoiceService.setSecretKey(testSecretKey);
		taxinvoiceService.setTest(true);
		
		Response response = taxinvoiceService.issue("1231212312", MgtKeyType.SELL, "1234","발행메모",false,"userid");
		
		assertNotNull(response);
		
		System.out.println(response.getMessage());
		
	}
	
	@Test
	public void cancelIssue_TEST() throws PopbillException {
	TaxinvoiceService taxinvoiceService = new TaxinvoiceService();
		
		taxinvoiceService.setLinkID(testLinkID);
		taxinvoiceService.setSecretKey(testSecretKey);
		taxinvoiceService.setTest(true);
		
		Response response = taxinvoiceService.cancelIssue("1231212312", MgtKeyType.SELL, "1234","발행 취소 메모");
		
		assertNotNull(response);
		
		System.out.println(response.getMessage());
		
	}
	
	@Test
	public void request_TEST() throws PopbillException {
	TaxinvoiceService taxinvoiceService = new TaxinvoiceService();
		
		taxinvoiceService.setLinkID(testLinkID);
		taxinvoiceService.setSecretKey(testSecretKey);
		taxinvoiceService.setTest(true);
		
		Response response = taxinvoiceService.request("1231212312", MgtKeyType.BUY, "1234","역)발행요청 메모");
		
		assertNotNull(response);
		
		System.out.println(response.getMessage());
		
	}
	
	@Test
	public void cancelRequest_TEST() throws PopbillException {
	TaxinvoiceService taxinvoiceService = new TaxinvoiceService();
		
		taxinvoiceService.setLinkID(testLinkID);
		taxinvoiceService.setSecretKey(testSecretKey);
		taxinvoiceService.setTest(true);
		
		Response response = taxinvoiceService.cancelRequest("1231212312", MgtKeyType.BUY, "1234","역)발행요청 취소 메모");
		
		assertNotNull(response);
		
		System.out.println(response.getMessage());
		
	}
	
	@Test
	public void refuse_TEST() throws PopbillException {
	TaxinvoiceService taxinvoiceService = new TaxinvoiceService();
		
		taxinvoiceService.setLinkID(testLinkID);
		taxinvoiceService.setSecretKey(testSecretKey);
		taxinvoiceService.setTest(true);
		
		Response response = taxinvoiceService.refuse("1231212312", MgtKeyType.SELL, "1234","역)발행요청 거부 메모");
		
		assertNotNull(response);
		
		System.out.println(response.getMessage());
		
	}
	
	@Test
	public void sendToNTS_TEST() throws PopbillException {
	TaxinvoiceService taxinvoiceService = new TaxinvoiceService();
		
		taxinvoiceService.setLinkID(testLinkID);
		taxinvoiceService.setSecretKey(testSecretKey);
		taxinvoiceService.setTest(true);
		
		Response response = taxinvoiceService.sendToNTS("1231212312", MgtKeyType.SELL, "1234");
		
		assertNotNull(response);
		
		System.out.println(response.getMessage());
		
	}
	
	@Test
	public void sendEmail_TEST() throws PopbillException {
	TaxinvoiceService taxinvoiceService = new TaxinvoiceService();
		
		taxinvoiceService.setLinkID(testLinkID);
		taxinvoiceService.setSecretKey(testSecretKey);
		taxinvoiceService.setTest(true);
		
		Response response = taxinvoiceService.sendEmail("1231212312", MgtKeyType.SELL, "1234","test@test.com");
		
		assertNotNull(response);
		
		System.out.println(response.getMessage());
		
	}
	
	@Test
	public void sendSMS_TEST() throws PopbillException {
	TaxinvoiceService taxinvoiceService = new TaxinvoiceService();
		
		taxinvoiceService.setLinkID(testLinkID);
		taxinvoiceService.setSecretKey(testSecretKey);
		taxinvoiceService.setTest(true);
		
		Response response = taxinvoiceService.sendSMS("1231212312", MgtKeyType.SELL, "1234","11122224444","11133334444","문자메시지 내용");
		
		assertNotNull(response);
		
		System.out.println(response.getMessage());
		
	}
	
	@Test
	public void sendFAX_TEST() throws PopbillException {
	TaxinvoiceService taxinvoiceService = new TaxinvoiceService();
		
		taxinvoiceService.setLinkID(testLinkID);
		taxinvoiceService.setSecretKey(testSecretKey);
		taxinvoiceService.setTest(true);
		
		Response response = taxinvoiceService.sendFAX("1231212312", MgtKeyType.SELL, "1234","11122224444","11133334444");
		
		assertNotNull(response);
		
		System.out.println(response.getMessage());
		
	}
	
	@Test
	public void getInfo_TEST() throws PopbillException {
		TaxinvoiceService taxinvoiceService = new TaxinvoiceService();
		
		taxinvoiceService.setLinkID(testLinkID);
		taxinvoiceService.setSecretKey(testSecretKey);
		taxinvoiceService.setTest(true);
		
		TaxinvoiceInfo taxinvoiceInfo = taxinvoiceService.getInfo("1231212312", MgtKeyType.SELL, "1234");
		
		assertNotNull(taxinvoiceInfo);
		System.out.println(taxinvoiceInfo);
	}
	
	@Test
	public void getDetailInfo_TEST() throws PopbillException {
		TaxinvoiceService taxinvoiceService = new TaxinvoiceService();
		
		taxinvoiceService.setLinkID(testLinkID);
		taxinvoiceService.setSecretKey(testSecretKey);
		taxinvoiceService.setTest(true);
		
		Taxinvoice taxinvoice = taxinvoiceService.getDetailInfo("1231212312", MgtKeyType.SELL, "1234");
		
		assertNotNull(taxinvoice);
		System.out.println(taxinvoice);
	}
	
	@Test
	public void getLogs_TEST() throws PopbillException {
		TaxinvoiceService taxinvoiceService = new TaxinvoiceService();
		
		taxinvoiceService.setLinkID(testLinkID);
		taxinvoiceService.setSecretKey(testSecretKey);
		taxinvoiceService.setTest(true);
		
		TaxinvoiceLog[] logs = taxinvoiceService.getLogs("1231212312", MgtKeyType.SELL, "1234");
		
		assertNotNull(logs);
		System.out.println(logs.length);
	}
	
	@Test
	public void getPopUpURL_TEST() throws PopbillException {
		TaxinvoiceService taxinvoiceService = new TaxinvoiceService();
		
		taxinvoiceService.setLinkID(testLinkID);
		taxinvoiceService.setSecretKey(testSecretKey);
		taxinvoiceService.setTest(true);
		
		String url = taxinvoiceService.getPopUpURL("1231212312", MgtKeyType.SELL, "1234","userid");
		
		assertNotNull(url);
		System.out.println(url);
		
	}
	
	@Test
	public void getPrintURL_TEST() throws PopbillException {
		TaxinvoiceService taxinvoiceService = new TaxinvoiceService();
		
		taxinvoiceService.setLinkID(testLinkID);
		taxinvoiceService.setSecretKey(testSecretKey);
		taxinvoiceService.setTest(true);
		
		String url = taxinvoiceService.getPrintURL("1231212312", MgtKeyType.SELL, "1234","userid");
		
		assertNotNull(url);
		System.out.println(url);
	}
	
	@Test
	public void getEPrintURL_TEST() throws PopbillException {
		TaxinvoiceService taxinvoiceService = new TaxinvoiceService();
		
		taxinvoiceService.setLinkID(testLinkID);
		taxinvoiceService.setSecretKey(testSecretKey);
		taxinvoiceService.setTest(true);
		
		String url = taxinvoiceService.getEPrintURL("1231212312", MgtKeyType.SELL, "1234","userid");
		
		assertNotNull(url);
		System.out.println(url);
	}
	

	@Test
	public void getMailURL_TEST() throws PopbillException {
		TaxinvoiceService taxinvoiceService = new TaxinvoiceService();
		
		taxinvoiceService.setLinkID(testLinkID);
		taxinvoiceService.setSecretKey(testSecretKey);
		taxinvoiceService.setTest(true);
		
		String url = taxinvoiceService.getMailURL("1231212312", MgtKeyType.SELL, "1234","userid");
		
		assertNotNull(url);
		System.out.println(url);
	}
	
	@Test
	public void getMassPrintURL_TEST() throws PopbillException {
		TaxinvoiceService taxinvoiceService = new TaxinvoiceService();
		
		taxinvoiceService.setLinkID(testLinkID);
		taxinvoiceService.setSecretKey(testSecretKey);
		taxinvoiceService.setTest(true);
		
		String[] MgtKeyList = new String[] { "1234","123","12345"};
		
		
		String url = taxinvoiceService.getMassPrintURL("1231212312", MgtKeyType.SELL, MgtKeyList,"userid");
	
		assertNotNull(url);
		System.out.println(url);
	}
	
	@Test
	public void getInfos_TEST() throws PopbillException {
		TaxinvoiceService taxinvoiceService = new TaxinvoiceService();
		
		taxinvoiceService.setLinkID(testLinkID);
		taxinvoiceService.setSecretKey(testSecretKey);
		taxinvoiceService.setTest(true);
		
		String[] MgtKeyList = new String[] { "1234","123","12345"};
		
		
		TaxinvoiceInfo[] infoList = taxinvoiceService.getInfos("1231212312", MgtKeyType.SELL, MgtKeyList);
	
		assertNotNull(infoList);
		System.out.println(infoList.length);
	}
	
	@Test
	public void attachFile_TEST() throws IOException, PopbillException {
		
		TaxinvoiceService taxinvoiceService = new TaxinvoiceService();
		
		taxinvoiceService.setLinkID(testLinkID);
		taxinvoiceService.setSecretKey(testSecretKey);
		taxinvoiceService.setTest(true);
		
		InputStream FileData = new FileInputStream("/Users/cream/Desktop/test.jpg");
		
		Response response = taxinvoiceService.attachFile("1231212312", MgtKeyType.SELL, "1234", "대붕역풍비.jpg", FileData, "userid");
		
		FileData.close();
		
		assertNotNull(response);
		
		System.out.println(response.getMessage());
	}
	
	@Test
	public void getFiles_TEST() throws PopbillException {
		TaxinvoiceService taxinvoiceService = new TaxinvoiceService();
		
		taxinvoiceService.setLinkID(testLinkID);
		taxinvoiceService.setSecretKey(testSecretKey);
		taxinvoiceService.setTest(true);
		
		AttachedFile[] files = taxinvoiceService.getFiles("1231212312", MgtKeyType.SELL, "1234");
		
		assertNotNull(files);
		
		System.out.println(files.length);
	}
	
	@Test
	public void deleteFile_TEST() throws PopbillException {
		TaxinvoiceService taxinvoiceService = new TaxinvoiceService();
		
		taxinvoiceService.setLinkID(testLinkID);
		taxinvoiceService.setSecretKey(testSecretKey);
		taxinvoiceService.setTest(true);
		
		String FileID = "56A00CC6-F54C-45F5-A497-C1E5927EFA43.PBF";
		
		Response response = taxinvoiceService.deleteFile("1231212312", MgtKeyType.SELL, "1234", FileID, "userid");
		
		assertNotNull(response);
		
		System.out.println(response.getMessage());
	
	}
	
}

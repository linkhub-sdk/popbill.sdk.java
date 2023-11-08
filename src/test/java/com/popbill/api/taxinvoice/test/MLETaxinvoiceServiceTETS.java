package com.popbill.api.taxinvoice.test;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.popbill.api.BulkResponse;
import com.popbill.api.IssueResponse;
import com.popbill.api.PopbillException;
import com.popbill.api.TaxinvoiceService;
import com.popbill.api.taxinvoice.Taxinvoice;
import com.popbill.api.taxinvoice.TaxinvoiceAddContact;
import com.popbill.api.taxinvoice.TaxinvoiceDetail;
import com.popbill.api.taxinvoice.TaxinvoiceServiceImp;

public class MLETaxinvoiceServiceTETS {
	
	private final String testLinkID = "TESTER";
    private final String testSecretKey = "SwWxqU+0TErBXy/9TVjIPEnI0VTUMMSQZtJf3Ed8q3I=";


	    private TaxinvoiceService taxinvoiceService;

	    public MLETaxinvoiceServiceTETS() {
	        TaxinvoiceServiceImp service = new TaxinvoiceServiceImp();

	        service.setLinkID(testLinkID);
	        service.setSecretKey(testSecretKey);
	        service.setTest(true);

	        service.setUseLocalTimeYN(false);
	        service.setUseGAIP(false);

	        taxinvoiceService = service;
	    }

	

	    @Test
	        public void RegistIssue_TEST() throws PopbillException {
	    		try {
	    			
		    		
		            Taxinvoice taxinvoice = new Taxinvoice();
		
		            taxinvoice.setWriteDate("20231025"); // 필수, 기재상 작성일자
		            taxinvoice.setChargeDirection("정과금"); // 필수, {정과금, 역과금}
		            taxinvoice.setIssueType("정발행"); // 필수, {정발행, 역발행, 위수탁}
		            taxinvoice.setPurposeType("영수"); // 필수, {영수, 청구}
		            taxinvoice.setIssueTiming("직접발행"); // 필수, {직접발행, 승인시자동발행}
		            taxinvoice.setTaxType("과세"); // 필수, {과세, 영세, 면세}
		            taxinvoice.setInvoicerCorpNum("1234567890");
		            taxinvoice.setInvoicerTaxRegID(""); // 종사업자 식별번호. 필요시 기재. 형식은 숫자 4자리.
		            taxinvoice.setInvoicerCorpName("Seller Name of Company");
		            taxinvoice.setInvoicerMgtKey("20231025-06"); // 공급자 발행까지 API로 발행하고자 할경우 정발행과
		            taxinvoice.setInvoicerCEOName("Seller Name of Representative");
		            taxinvoice.setInvoicerAddr("Seller Company Address");
		            taxinvoice.setInvoicerBizClass("Seller Business Item");
		            taxinvoice.setInvoicerBizType("Seller Business Type");
		            taxinvoice.setInvoicerContactName("Seller Name of the person in charge");
		            taxinvoice.setInvoicerEmail("seller@email.com");
		            taxinvoice.setInvoicerTEL("070-7070-0707");
		            taxinvoice.setInvoicerSMSSendYN(false); // 발행시 문자발송기능 사용시 활용
		
		            taxinvoice.setInvoiceeType("사업자");
		            taxinvoice.setInvoiceeCorpNum("8888888888");
		            taxinvoice.setInvoiceeCorpName("Buyer Name of Company");
		            taxinvoice.setInvoiceeMgtKey(null); // 문서관리번호 1~24자리까지 공급받는자 사업자번호별 중복없는
		            taxinvoice.setInvoiceeCEOName("Buyer Name of Representative");
		            taxinvoice.setInvoiceeAddr("Buyer Company Address");
		            taxinvoice.setInvoiceeBizClass("Buyer Business Item");
		            taxinvoice.setInvoiceeBizType("Buyer Business Type");
		            taxinvoice.setInvoiceeContactName1("Buyer Name of the person in charge");
		            taxinvoice.setInvoiceeEmail1("buyer@email.com");
		            taxinvoice.setInvoiceeTEL1("070-7070-3456");
		            taxinvoice.setSupplyCostTotal("200000"); // 필수 공급가액 합계"
		            taxinvoice.setTaxTotal("20000"); // 필수 세액 합계
		            taxinvoice.setTotalAmount("220000"); // 필수 합계금액. 공급가액 + 세액
		            taxinvoice.setSerialNum("");
		            taxinvoice.setCash(""); // 현금
		            taxinvoice.setChkBill(""); // 수표
		            taxinvoice.setNote(""); // 어음
		            taxinvoice.setCredit(""); // 외상미수금
		            taxinvoice.setRemark1("remark 01");
		            taxinvoice.setRemark2("remark 02");
		            taxinvoice.setRemark3("remark 03");
		            taxinvoice.setKwon((short) 1);
		            taxinvoice.setHo((short) 1);
		            taxinvoice.setBusinessLicenseYN(false); // 사업자등록증 이미지 첨부시 설정.
		            taxinvoice.setBankBookYN(false); // 통장사본 이미지 첨부시 설정.
		
		            taxinvoice.setDetailList(new ArrayList<TaxinvoiceDetail>());
		
		            TaxinvoiceDetail detail = new TaxinvoiceDetail();
		
		            detail.setSerialNum((short) 1); // 일련번호
		            detail.setPurchaseDT("20231023"); // 거래일자
		            detail.setItemName("ItemName01");
		            detail.setSpec("spec01");
		            detail.setQty("1"); // 수량
		            detail.setUnitCost("100000"); // 단가
		            detail.setSupplyCost("100000"); // 공급가액
		            detail.setTax("10000"); // 세액
		            detail.setRemark("품목비고");
		            taxinvoice.getDetailList().add(detail);
		
		            detail = new TaxinvoiceDetail();
		            detail.setSerialNum((short) 2);
		            detail.setPurchaseDT("20231023"); // 거래일자
		            detail.setItemName("ItemName02");
		            detail.setSpec("spec02");
		            detail.setQty("1"); // 수량
		            detail.setUnitCost("100000"); // 단가
		            detail.setSupplyCost("100000"); // 공급가액
		            detail.setTax("10000"); // 세액
		            detail.setRemark("품목비고");
		            taxinvoice.getDetailList().add(detail);
		
		            taxinvoice.setAddContactList(new ArrayList<TaxinvoiceAddContact>());
		
		            TaxinvoiceAddContact contact = new TaxinvoiceAddContact();
		            contact.setSerialNum((short)1);
		            contact.setContactName("Additional Contactor 01");
		            contact.setEmail("addcontact01@test.com");
		
		            taxinvoice.getAddContactList().add(contact);
		
		            contact = new TaxinvoiceAddContact();
		            contact.setSerialNum((short)2);
		            contact.setContactName("Additional Contactor 02");
		            contact.setEmail("addcontact02@test.com");
		
		            taxinvoice.getAddContactList().add(contact);
		            
		
		            IssueResponse response = taxinvoiceService.registIssue("1234567890", taxinvoice, false, "memo", false, null, "Custom Email Title", "");
		
		            assertNotNull(response);
		
		            System.out.println("응답코드 : "+response.getCode());
		            System.out.println("응답메시지 : "+response.getMessage());
		            System.out.println("국세청승인코드 : "+response.getNtsConfirmNum());
		            
		            System.out.println("["+response.getCode()+"] "+response.getMessage()+" : "+response.getNtsConfirmNum());
	    		} catch(PopbillException pe) {
	    			System.out.println("오류코드 : " + pe.getCode());
	    			System.out.println("오류메시지 : " + pe.getMessage());
	    		}
	        }

	    @Test
	        public void BulkSubmit_TEST() throws PopbillException {

	            List<Taxinvoice> bulkTx = new ArrayList<Taxinvoice>();
	            String SubmitID = "20231025-000002";

	            for(int i=0; i<2; i++) {

	                Taxinvoice taxinvoice = new Taxinvoice();

	                taxinvoice.setWriteDate("20231025"); // 필수, 기재상 작성일자
	                taxinvoice.setChargeDirection("정과금"); // 필수, {정과금, 역과금}
	                taxinvoice.setIssueType("정발행"); // 필수, {정발행, 역발행, 위수탁}
	                taxinvoice.setPurposeType("영수"); // 필수, {영수, 청구}
	                taxinvoice.setIssueTiming("직접발행"); // 필수, {직접발행, 승인시자동발행}
	                taxinvoice.setTaxType("과세"); // 필수, {과세, 영세, 면세}
	                taxinvoice.setInvoicerCorpNum("1234567890");
	                taxinvoice.setInvoicerTaxRegID(""); // 종사업자 식별번호. 필요시 기재. 형식은 숫자 4자리.
	                taxinvoice.setInvoicerCorpName("Seller Name of Company");
	                taxinvoice.setInvoicerMgtKey(SubmitID+"-"+ String.valueOf(i)); // 공급자 발행까지 API로 발행하고자 할경우 정발행과
	                taxinvoice.setInvoicerCEOName("Seller Name of Representative");
	                taxinvoice.setInvoicerAddr("Seller Company Address");
	                taxinvoice.setInvoicerBizClass("Seller Business Item");
	                taxinvoice.setInvoicerBizType("Seller Business Type");
	                taxinvoice.setInvoicerContactName("");
	                taxinvoice.setInvoicerEmail("seller@email.com");
	                taxinvoice.setInvoicerTEL("070-7070-0707");
	                taxinvoice.setInvoicerSMSSendYN(false); // 발행시 문자발송기능 사용시 활용

	                taxinvoice.setInvoiceeType("사업자");
	                taxinvoice.setInvoiceeCorpNum("8888888888");
	                taxinvoice.setInvoiceeCorpName("공급받는자 ASDASDWEQZXC");
	                taxinvoice.setInvoiceeMgtKey(null); // 문서관리번호 1~24자리까지 공급받는자 사업자번호별 중복없는
	                taxinvoice.setInvoiceeCEOName("대표명");
	                taxinvoice.setInvoiceeAddr("공받주 주소");
	                taxinvoice.setInvoiceeBizClass("종목종목");
	                taxinvoice.setInvoiceeBizType("업태");
	                taxinvoice.setInvoiceeContactName1("담당자 이이름");
	                taxinvoice.setInvoiceeEmail1("buyer@email.com");
	                taxinvoice.setInvoiceeTEL1("070-7070-3456");
	                taxinvoice.setSupplyCostTotal("200000"); // 필수 공급가액 합계"
	                taxinvoice.setTaxTotal("20000"); // 필수 세액 합계
	                taxinvoice.setTotalAmount("220000"); // 필수 합계금액. 공급가액 + 세액
	                taxinvoice.setSerialNum("112342");
	                taxinvoice.setCash(""); // 현금
	                taxinvoice.setChkBill(""); // 수표
	                taxinvoice.setNote(""); // 어음
	                taxinvoice.setCredit(""); // 외상미수금
	                taxinvoice.setRemark1("remark 01");
	                taxinvoice.setRemark2("remark 02");
	                taxinvoice.setRemark3("remark 03");
	                taxinvoice.setKwon((short) 1);
	                taxinvoice.setHo((short) 1);
	                taxinvoice.setBusinessLicenseYN(false); // 사업자등록증 이미지 첨부시 설정.
	                taxinvoice.setBankBookYN(false); // 통장사본 이미지 첨부시 설정.

	                taxinvoice.setDetailList(new ArrayList<TaxinvoiceDetail>());

	                TaxinvoiceDetail detail = new TaxinvoiceDetail();

	                detail.setSerialNum((short) 1); // 일련번호
	                detail.setPurchaseDT("20231023"); // 거래일자
	                detail.setItemName("ItemName01");
	                detail.setSpec("spec01");
	                detail.setQty("1"); // 수량
	                detail.setUnitCost("100000"); // 단가
	                detail.setSupplyCost("100000"); // 공급가액
	                detail.setTax("10000"); // 세액
	                detail.setRemark("품목비고");
	                taxinvoice.getDetailList().add(detail);

	                detail = new TaxinvoiceDetail();
	                detail.setSerialNum((short) 2);
	                detail.setPurchaseDT("20231023"); // 거래일자
	                detail.setItemName("ItemName02");
	                detail.setSpec("spec02");
	                detail.setQty("1"); // 수량
	                detail.setUnitCost("100000"); // 단가
	                detail.setSupplyCost("100000"); // 공급가액
	                detail.setTax("10000"); // 세액
	                detail.setRemark("품목비고");
	                taxinvoice.getDetailList().add(detail);

	                taxinvoice.setAddContactList(new ArrayList<TaxinvoiceAddContact>());

	                TaxinvoiceAddContact contact = new TaxinvoiceAddContact();
	                contact.setSerialNum((short)1);
	                contact.setContactName("Additional Contactor 01");
	                contact.setEmail("addcontact01@test.com");

	                taxinvoice.getAddContactList().add(contact);

	                contact = new TaxinvoiceAddContact();
	                contact.setSerialNum((short)2);
	                contact.setContactName("Additional Contactor 02");
	                contact.setEmail("addcontact02@test.com");

	                taxinvoice.getAddContactList().add(contact);

	                bulkTx.add(taxinvoice);
	            }

	            BulkResponse response = taxinvoiceService.bulkSubmit("1234567890", SubmitID, bulkTx, false, "");

	            assertNotNull(response);

	            System.out.println("["+response.getCode()+"] "+response.getMessage()+" : "+response.getReceiptID());
	    
       }
	   
}

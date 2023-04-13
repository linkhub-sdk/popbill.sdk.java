package com.popbill.api.taxinvoice.test;

import static org.junit.Assert.assertNotNull;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.popbill.api.AttachedFile;
import com.popbill.api.BulkResponse;
import com.popbill.api.ChargeInfo;
import com.popbill.api.EmailSendConfig;
import com.popbill.api.IssueResponse;
import com.popbill.api.PopbillException;
import com.popbill.api.Response;
import com.popbill.api.TaxinvoiceCertificate;
import com.popbill.api.TaxinvoiceService;
import com.popbill.api.taxinvoice.BulkTaxinvoiceResult;
import com.popbill.api.taxinvoice.EmailPublicKey;
import com.popbill.api.taxinvoice.MgtKeyType;
import com.popbill.api.taxinvoice.TISearchResult;
import com.popbill.api.taxinvoice.Taxinvoice;
import com.popbill.api.taxinvoice.TaxinvoiceAddContact;
import com.popbill.api.taxinvoice.TaxinvoiceDetail;
import com.popbill.api.taxinvoice.TaxinvoiceInfo;
import com.popbill.api.taxinvoice.TaxinvoiceLog;
import com.popbill.api.taxinvoice.TaxinvoiceServiceImp;
import com.popbill.api.taxinvoice.TaxinvoiceXML;

public class TaxinvoiceServiceTEST {

    private final String testLinkID = "TESTER";
    private final String testSecretKey = "SwWxqU+0TErBXy/9TVjIPEnI0VTUMMSQZtJf3Ed8q3I=";
    
    
    private TaxinvoiceService taxinvoiceService;
    
    public TaxinvoiceServiceTEST() {
        TaxinvoiceServiceImp service = new TaxinvoiceServiceImp();

        service.setLinkID(testLinkID);
        service.setSecretKey(testSecretKey);
        service.setTest(true);

        service.setUseLocalTimeYN(false);
        service.setUseGAIP(true);
        taxinvoiceService = service;
    }
    
    @Test
    public void checkMgtKeyInUse_TEST() throws PopbillException {
            
        boolean useYN = taxinvoiceService.checkMgtKeyInUse("1234567890",
                MgtKeyType.SELL, "안녕하세용");
    
        System.out.println(useYN);
    }

    @Test
        public void RegistIssue_TEST() throws PopbillException {
            Taxinvoice taxinvoice = new Taxinvoice();
    
            taxinvoice.setWriteDate("20210706"); // 필수, 기재상 작성일자
            taxinvoice.setChargeDirection("정과금"); // 필수, {정과금, 역과금}
            taxinvoice.setIssueType("정발행"); // 필수, {정발행, 역발행, 위수탁}
            taxinvoice.setPurposeType("영수"); // 필수, {영수, 청구}
            taxinvoice.setIssueTiming("직접발행"); // 필수, {직접발행, 승인시자동발행}
            taxinvoice.setTaxType("과세"); // 필수, {과세, 영세, 면세}
            taxinvoice.setInvoicerCorpNum("1234567890");
            taxinvoice.setInvoicerTaxRegID(""); // 종사업자 식별번호. 필요시 기재. 형식은 숫자 4자리.
            taxinvoice.setInvoicerCorpName("Seller Name of Company");
            taxinvoice.setInvoicerMgtKey("20210706-002"); // 공급자 발행까지 API로 발행하고자 할경우 정발행과
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
    //        taxinvoice.setModifyCode((short)1); // 수정세금계산서 작성시 1~6까지 선택기재.
    //        taxinvoice.setOrgNTSConfirmNum("20191008888888880000003e"); // 수정세금계산서 작성시 원본세금계산서의 ItemKey기재. ItemKey는 문서확인.
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
            detail.setPurchaseDT("20210705"); // 거래일자
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
            detail.setPurchaseDT("20210705"); // 거래일자
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
    
            System.out.println("["+response.getCode()+"] "+response.getMessage()+" : "+response.getNtsConfirmNum());
        }

    @Test
        public void BulkSubmit_TEST() throws PopbillException {
            
            List<Taxinvoice> bulkTx = new ArrayList<Taxinvoice>();
            List<Taxinvoice> bulkTx2 = new ArrayList<Taxinvoice>();
            String SubmitID = "20220930-TEST";
            
            for(int j=0; j<1; j++) {
                //SubmitID += String.valueOf(j);
                bulkTx = new ArrayList<Taxinvoice>();
            for(int i=0; i<20; i++) {
                
                Taxinvoice taxinvoice = new Taxinvoice();
    
                taxinvoice.setWriteDate("20211217"); // 필수, 기재상 작성일자
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
                taxinvoice.setInvoicerContactName("asd!@#@@#(*&$!)$*!(+__)+_)?><<\"\"dasd\"dsdasaㅁ＠▲＊＝");
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
    //            taxinvoice.setModifyCode((short)1); // 수정세금계산서 작성시 1~6까지 선택기재.
    //            taxinvoice.setOrgNTSConfirmNum("20191008888888880000003e"); // 수정세금계산서 작성시 원본세금계산서의 ItemKey기재. ItemKey는 문서확인.
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
                detail.setPurchaseDT("20210105"); // 거래일자
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
                detail.setPurchaseDT("20210105"); // 거래일자
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
                bulkTx2.add(taxinvoice);
            }
            
            BulkResponse response = taxinvoiceService.bulkSubmit("1234567890", SubmitID, bulkTx, false, "");
            
            assertNotNull(response);
    
            System.out.println("["+response.getCode()+"] "+response.getMessage()+" : "+response.getReceiptID());
            }
        }

    @Test
    public void getBulkResult_TEST() throws PopbillException {
    
        BulkTaxinvoiceResult bulkResult = taxinvoiceService.getBulkResult("1234567890", "20220930-TEST", "");
        
        assertNotNull(bulkResult);
        System.out.println(bulkResult.getReceiptDT());
        
        System.out.println(bulkResult.getCode());
        System.out.println(bulkResult.getMessage());
        System.out.println(bulkResult.getReceiptID());
        System.out.println(bulkResult.getSubmitID());
        System.out.println(bulkResult.getSubmitCount());
        System.out.println(bulkResult.getSuccessCount());
        System.out.println(bulkResult.getFailCount());
        System.out.println(bulkResult.getTxState());
        System.out.println(bulkResult.getTxStartDT());
        System.out.println(bulkResult.getTxEndDT());
        System.out.println(bulkResult.getTxResultCode());
        
        System.out.println();
        
        for(int i=0; i< bulkResult.getIssueResult().size(); i++) {
            
            String n1 = bulkResult.getIssueResult().get(i).getInvoicerMgtKey();
            long n2 = bulkResult.getIssueResult().get(i).getCode();
            String n3 = bulkResult.getIssueResult().get(i).getMessage();
            String n4 = bulkResult.getIssueResult().get(i).getNtsconfirmNum();
            String n5 = bulkResult.getIssueResult().get(i).getTrusteeMgtKey();
            String n6 = bulkResult.getIssueResult().get(i).getIssueDT();
            System.out.println(n1+ " " + String.valueOf(n2) +" "+n3+" | "+n4+" | "+n5);
        }        
    }

    @Test
    public void register_TEST() throws PopbillException {
        
        Taxinvoice taxinvoice = new Taxinvoice();
    
        taxinvoice.setWriteDate("20230412"); // 필수, 기재상 작성일자
        taxinvoice.setChargeDirection("정과금"); // 필수, {정과금, 역과금}
        taxinvoice.setIssueType("정발행"); // 필수, {정발행, 역발행, 위수탁}
        taxinvoice.setPurposeType("영수"); // 필수, {영수, 청구}
        taxinvoice.setIssueTiming("직접발행"); // 필수, {직접발행, 승인시자동발행}
        taxinvoice.setTaxType("과세"); // 필수, {과세, 영세, 면세}
    
        taxinvoice.setInvoicerCorpNum("1234567890");
        taxinvoice.setInvoicerTaxRegID(""); // 종사업자 식별번호. 필요시 기재. 형식은 숫자 4자리.
        taxinvoice.setInvoicerCorpName("공급자 상호");
        taxinvoice.setInvoicerMgtKey("20230412-10"); // 공급자 발행까지 API로 발행하고자 할경우 정발행과
                                                // 동일한 형태로 추가 기재.
        taxinvoice.setInvoicerCEOName("공급자 대표자 성명");
        taxinvoice.setInvoicerAddr("공급자 주소");
        taxinvoice.setInvoicerBizClass("공급자 업종");
        taxinvoice.setInvoicerBizType("공급자 업태,업태2");
        taxinvoice.setInvoicerContactName("공급자 담당자명");
        taxinvoice.setInvoicerEmail("test@test.com");
        taxinvoice.setInvoicerTEL("070-7070-0707");
        taxinvoice.setInvoicerHP("010-000-2222");
        taxinvoice.setInvoicerSMSSendYN(true); // 발행시 문자발송기능 사용시 활용
    
        taxinvoice.setInvoiceeType("사업자");
        taxinvoice.setInvoiceeCorpNum("8888888888");
        taxinvoice.setInvoiceeCorpName("공급받는자 상호");
        taxinvoice.setInvoiceeMgtKey(""); // 문서관리번호 1~24자리까지 공급받는자 사업자번호별 중복없는
                                            // 고유번호 할당
        taxinvoice.setInvoiceeCEOName("공급받는자 대표자 성명");
        taxinvoice.setInvoiceeAddr("공급받는자 주소");
        taxinvoice.setInvoiceeBizClass("공급받는자 업종");
        taxinvoice.setInvoiceeBizType("공급받는자 업태");
        taxinvoice.setInvoiceeContactName1("공급받는자 담당자명");
        taxinvoice.setInvoiceeEmail1("test@invoicee.com");
    
        taxinvoice.setSupplyCostTotal("100000"); // 필수 공급가액 합계"
        taxinvoice.setTaxTotal("10000"); // 필수 세액 합계
        taxinvoice.setTotalAmount("110000"); // 필수 합계금액. 공급가액 + 세액
    
        taxinvoice.setModifyCode(null); // 수정세금계산서 작성시 1~6까지 선택기재.
        taxinvoice.setOriginalTaxinvoiceKey(""); // 수정세금계산서 작성시 원본세금계산서의
                                                    // ItemKey기재. ItemKey는 문서확인.
        taxinvoice.setSerialNum("123");
        taxinvoice.setCash(""); // 현금
        taxinvoice.setChkBill(""); // 수표
        taxinvoice.setNote(""); // 어음
        taxinvoice.setCredit(""); // 외상미수금
        taxinvoice.setRemark1("비고1");
        taxinvoice.setRemark2("비고2");
        taxinvoice.setRemark3("비고3");
        taxinvoice.setKwon((short) 1);
        taxinvoice.setHo((short) 1);
    
        taxinvoice.setBusinessLicenseYN(false); // 사업자등록증 이미지 첨부시 설정.
        taxinvoice.setBankBookYN(false); // 통장사본 이미지 첨부시 설정.
    
        taxinvoice.setDetailList(new ArrayList<TaxinvoiceDetail>());
    
        TaxinvoiceDetail detail = new TaxinvoiceDetail();
    
        detail.setSerialNum((short) 1); // 일련번호
        detail.setPurchaseDT("20140319"); // 거래일자
        detail.setItemName("품목명");
        detail.setSpec("규격");
        detail.setQty("1"); // 수량
        detail.setUnitCost("100000"); // 단가
        detail.setSupplyCost("100000"); // 공급가액
        detail.setTax("10000"); // 세액
        detail.setRemark("품목비고");
    
        taxinvoice.getDetailList().add(detail);
    
        detail = new TaxinvoiceDetail();
    
        detail.setSerialNum((short) 2);
        detail.setItemName("품목명");
    
        taxinvoice.getDetailList().add(detail);
    
        Response response = taxinvoiceService
                .register("1234567890", taxinvoice, "", true);
    
        assertNotNull(response);
    
        System.out.println(response.getMessage());
    }

    @Test
    public void update_TEST() throws PopbillException {
        
        Taxinvoice taxinvoice = new Taxinvoice();
    
        taxinvoice.setWriteDate("20141002"); // 필수, 기재상 작성일자
        taxinvoice.setChargeDirection("정과금"); // 필수, {정과금, 역과금}
        taxinvoice.setIssueType("정발행"); // 필수, {정발행, 역발행, 위수탁}
        taxinvoice.setPurposeType("영수"); // 필수, {영수, 청구}
        taxinvoice.setIssueTiming("직접발행"); // 필수, {직접발행, 승인시자동발행}
        taxinvoice.setTaxType("과세"); // 필수, {과세, 영세, 면세}
    
        taxinvoice.setInvoicerCorpNum("1231212312");
        taxinvoice.setInvoicerTaxRegID(""); // 종사업자 식별번호. 필요시 기재. 형식은 숫자 4자리.
        taxinvoice.setInvoicerCorpName("공급자 상호_수정");
        taxinvoice.setInvoicerMgtKey("1234"); // 공급자 발행까지 API로 발행하고자 할경우 정발행과
                                                // 동일한 형태로 추가 기재.
        taxinvoice.setInvoicerCEOName("공급자 대표자 성명");
        taxinvoice.setInvoicerAddr("공급자 주소");
        taxinvoice.setInvoicerBizClass("공급자 업종");
        taxinvoice.setInvoicerBizType("공급자 업태,업태2");
        taxinvoice.setInvoicerContactName("공급자 담당자명");
        taxinvoice.setInvoicerEmail("test@test.com");
        taxinvoice.setInvoicerTEL("070-7070-0707");
        taxinvoice.setInvoicerHP("010-000-2222");
        taxinvoice.setInvoicerSMSSendYN(true); // 발행시 문자발송기능 사용시 활용
    
        taxinvoice.setInvoiceeType("사업자");
        taxinvoice.setInvoiceeCorpNum("8888888888");
        taxinvoice.setInvoiceeCorpName("공급받는자 상호");
        taxinvoice.setInvoiceeMgtKey(""); // 문서관리번호 1~24자리까지 공급받는자 사업자번호별 중복없는
                                            // 고유번호 할당
        taxinvoice.setInvoiceeCEOName("공급받는자 대표자 성명");
        taxinvoice.setInvoiceeAddr("공급받는자 주소");
        taxinvoice.setInvoiceeBizClass("공급받는자 업종");
        taxinvoice.setInvoiceeBizType("공급받는자 업태");
        taxinvoice.setInvoiceeContactName1("공급받는자 담당자명");
        taxinvoice.setInvoiceeEmail1("test@invoicee.com");
    
        taxinvoice.setSupplyCostTotal("100000"); // 필수 공급가액 합계"
        taxinvoice.setTaxTotal("10000"); // 필수 세액 합계
        taxinvoice.setTotalAmount("110000"); // 필수 합계금액. 공급가액 + 세액
    
        taxinvoice.setModifyCode(null); // 수정세금계산서 작성시 1~6까지 선택기재.
        taxinvoice.setOriginalTaxinvoiceKey(""); // 수정세금계산서 작성시 원본세금계산서의
                                                    // ItemKey기재. ItemKey는 문서확인.
        taxinvoice.setSerialNum("123");
        taxinvoice.setCash(""); // 현금
        taxinvoice.setChkBill(""); // 수표
        taxinvoice.setNote(""); // 어음
        taxinvoice.setCredit(""); // 외상미수금
        taxinvoice.setRemark1("비고1");
        taxinvoice.setRemark2("비고2");
        taxinvoice.setRemark3("비고3");
        taxinvoice.setKwon((short) 1);
        taxinvoice.setHo((short) 1);
    
        taxinvoice.setBusinessLicenseYN(false); // 사업자등록증 이미지 첨부시 설정.
        taxinvoice.setBankBookYN(false); // 통장사본 이미지 첨부시 설정.
    
        taxinvoice.setDetailList(new ArrayList<TaxinvoiceDetail>());
    
        TaxinvoiceDetail detail = new TaxinvoiceDetail();
    
        detail.setSerialNum((short) 1); // 일련번호
        detail.setPurchaseDT("20140319"); // 거래일자
        detail.setItemName("품목명");
        detail.setSpec("규격");
        detail.setQty("1"); // 수량
        detail.setUnitCost("100000"); // 단가
        detail.setSupplyCost("100000"); // 공급가액
        detail.setTax("10000"); // 세액
        detail.setRemark("품목비고");
    
        taxinvoice.getDetailList().add(detail);
    
        detail = new TaxinvoiceDetail();
    
        detail.setSerialNum((short) 2);
        detail.setItemName("품목명");
    
        taxinvoice.getDetailList().add(detail);
    
        Response response = taxinvoiceService.update("1231212312",
                MgtKeyType.SELL, "1234", taxinvoice);
    
        assertNotNull(response);
    
        System.out.println(response.getMessage());
    }

    @Test
    public void issue_TEST() throws PopbillException {
        
        IssueResponse response = taxinvoiceService.issue("1234567890",
                MgtKeyType.SELL, "20190226-09", "발행메모", false, "testkorea");
    
        assertNotNull(response);
    
        System.out.println(response.getMessage() + " " + response.getNtsConfirmNum());
    
    }

    @Test
    public void cancelIssue_TEST() throws PopbillException {
    
        Response response = taxinvoiceService.cancelIssue("6798700433",
                MgtKeyType.SELL, "20201229-001-99", "cancelIssue memo");
    
        assertNotNull(response);
    
        System.out.println(response.getMessage());
    
    }

    @Test
    public void RegistRequest_TEST() throws PopbillException {
    
        Taxinvoice taxinvoice = new Taxinvoice();
    
        taxinvoice.setWriteDate("20200825"); // 필수, 기재상 작성일자
        taxinvoice.setChargeDirection("정과금"); // 필수, {정과금, 역과금}
        taxinvoice.setIssueType("역발행"); // 필수, {정발행, 역발행, 위수탁}
        taxinvoice.setPurposeType("영수"); // 필수, {영수, 청구}
        taxinvoice.setIssueTiming("직접발행"); // 필수, {직접발행, 승인시자동발행}
        taxinvoice.setTaxType("과세"); // 필수, {과세, 영세, 면세}
        taxinvoice.setInvoicerCorpNum("8888888888");
        taxinvoice.setInvoicerTaxRegID(""); // 종사업자 식별번호. 필요시 기재. 형식은 숫자 4자리.
        taxinvoice.setInvoicerCorpName("공급자 상호");
        taxinvoice.setInvoicerMgtKey("20181112-007"); // 공급자 발행까지 API로 발행하고자 할경우 정발행과
        taxinvoice.setInvoicerCEOName("공급자 대표자 성명");
        taxinvoice.setInvoicerAddr("공급자 주소");
        taxinvoice.setInvoicerBizClass("공급자 업종");
        taxinvoice.setInvoicerBizType("공급자 업태,업태2");
        taxinvoice.setInvoicerContactName("공급자 담당자명");
        taxinvoice.setInvoicerEmail("test@test.com");
        taxinvoice.setInvoicerTEL("070-7070-0707");
        taxinvoice.setInvoicerHP("010-000-2222");
        taxinvoice.setInvoicerSMSSendYN(false); // 발행시 문자발송기능 사용시 활용
        taxinvoice.setInvoiceeType("사업자");
        taxinvoice.setInvoiceeCorpNum("1234567890");
        taxinvoice.setInvoiceeCorpName("공급받는자 상호");
        taxinvoice.setInvoiceeMgtKey("20181112-007"); // 문서관리번호 1~24자리까지 공급받는자 사업자번호별 중복없는
        taxinvoice.setInvoiceeCEOName("공급받는자 대표자 성명");
        taxinvoice.setInvoiceeAddr("공급받는자 주소");
        taxinvoice.setInvoiceeBizClass("공급받는자 업종");
        taxinvoice.setInvoiceeBizType("공급받는자 업태");
        taxinvoice.setInvoiceeContactName1("공급받는자 담당자명");
        taxinvoice.setInvoiceeEmail1("test@naver.com");
        taxinvoice.setSupplyCostTotal("100000"); // 필수 공급가액 합계"
        taxinvoice.setTaxTotal("10000"); // 필수 세액 합계
        taxinvoice.setTotalAmount("110000"); // 필수 합계금액. 공급가액 + 세액
        taxinvoice.setModifyCode(null); // 수정세금계산서 작성시 1~6까지 선택기재.
        taxinvoice.setOriginalTaxinvoiceKey(""); // 수정세금계산서 작성시 원본세금계산서의 ItemKey기재. ItemKey는 문서확인.
        taxinvoice.setSerialNum("123");
        taxinvoice.setCash(""); // 현금
        taxinvoice.setChkBill(""); // 수표
        taxinvoice.setNote(""); // 어음
        taxinvoice.setCredit(""); // 외상미수금
        taxinvoice.setRemark1("비고1");
        taxinvoice.setRemark2("비고2");
        taxinvoice.setRemark3("비고3");
        taxinvoice.setKwon((short) 1);
        taxinvoice.setHo((short) 1);
        taxinvoice.setBusinessLicenseYN(false); // 사업자등록증 이미지 첨부시 설정.
        taxinvoice.setBankBookYN(false); // 통장사본 이미지 첨부시 설정.
    
        Response response = taxinvoiceService.registRequest("1234567890", taxinvoice, "역발행 즉시요청");
    
        assertNotNull(response);
        
        System.out.println(response.getCode());
    }

    @Test
    public void request_TEST() throws PopbillException {
    
        Response response = taxinvoiceService.request("1234567890",
                MgtKeyType.BUY, "20181112-a002", "역)발행요청 메모");
    
        assertNotNull(response);
    
        System.out.println(response.getMessage());
    
    }

    @Test
    public void cancelRequest_TEST() throws PopbillException {
    
        Response response = taxinvoiceService.cancelRequest("1231212312",
                MgtKeyType.BUY, "1234", "역)발행요청 취소 메모");
    
        assertNotNull(response);
    
        System.out.println(response.getMessage());
    
    }

    @Test
    public void refuse_TEST() throws PopbillException {
    
        Response response = taxinvoiceService.refuse("1231212312",
                MgtKeyType.SELL, "1234", "역)발행요청 거부 메모");
    
        assertNotNull(response);
    
        System.out.println(response.getMessage());
    
    }

    @Test
    public void delete_TEST() throws PopbillException {
        TaxinvoiceServiceImp taxinvoiceService = new TaxinvoiceServiceImp();
    
        taxinvoiceService.setLinkID(testLinkID);
        taxinvoiceService.setSecretKey(testSecretKey);
        taxinvoiceService.setTest(true);
    
        Response response = taxinvoiceService.delete("1234567890",
                MgtKeyType.SELL, "20201126-004");
    
        assertNotNull(response);
    
        System.out.println(response.getMessage());
    }

    @Test
    public void sendToNTS_TEST() throws PopbillException {
    
        Response response = taxinvoiceService.sendToNTS("1234567890",
                MgtKeyType.SELL, "20201126-002d");
    
        assertNotNull(response);
    
        System.out.println(response.getMessage());
    
    }

    @Test
    public void getInfo_TEST() throws PopbillException {
    
        TaxinvoiceInfo taxinvoiceInfo = taxinvoiceService.getInfo("1234567890",
                MgtKeyType.SELL, "20201126-003d");
    
        assertNotNull(taxinvoiceInfo);
        System.out.println("[getInfo API] - "+taxinvoiceInfo.getCloseDownState()+ " "+taxinvoiceInfo.getInterOPYN());
    }

    @Test
        public void getInfos_TEST() throws PopbillException {
            
            String[] MgtKeyList = new String[] { "20210105-001" };
    
            TaxinvoiceInfo[] infoList = taxinvoiceService.getInfos("1234567890",
                    MgtKeyType.SELL, MgtKeyList);
    
            assertNotNull(infoList);
            System.out.println("휴폐업 상태 " + infoList[0].getCloseDownState() + " | 휴폐업 일자 " + infoList[0].getCloseDownStateDate());
            System.out.println(infoList[0].getInvoiceeCorpNum());
            System.out.println(infoList[1].getInvoiceeCorpNum());
            System.out.println(infoList[2].getInvoiceeCorpNum());
            System.out.println(infoList[3].getInvoiceeCorpNum());
    }

    @Test
    public void getDetailInfo_TEST() throws PopbillException {
            Taxinvoice taxinvoice = taxinvoiceService.getDetailInfo("1234567890",
                MgtKeyType.SELL, "20201126-001d");
    
        assertNotNull(taxinvoice);
        System.out.println(taxinvoice.getCloseDownState() + " | " + taxinvoice.getCloseDownStateDate());
    }

    @Test
    public void getXML_TEST() throws PopbillException {
            TaxinvoiceXML taxinvoiceXML = taxinvoiceService.getXML("1234567890",
                MgtKeyType.SELL, "20220920-002");
    
        assertNotNull(taxinvoiceXML);
        System.out.println(taxinvoiceXML.getCode() + " | " + taxinvoiceXML.getMessage());
        System.out.println(taxinvoiceXML.getRetObject());
    }

    @Test
    public void search_TEST() throws PopbillException {
        TISearchResult response = new TISearchResult();
        
        String DType = "W";
        String SDate = "20220126";
        String EDate = "20220208";
        String[] State = {"3**", "6**"};
        String[] Type = {"N", "M","Z"};
        String[] TaxType = {"T","N","Z"};
        String[] IssueType = {"N","R","T"};
        Boolean LateOnly = false;
        String TaxRegIDType = "S";
        String TaxRegID = "";
        String TaxRegIDYN = "";
        String QString = "8888888888";
        int Page = 1;
        int PerPage = 50;
        String Order = "D";
        String interOPYN = "";
        String[] RegType = {"P", "H"};
        String[] CloseDownState = {"N", "0", "1", "2", "3"};
        String MgtKey = "";
        
        response = taxinvoiceService.Search("1234567890", MgtKeyType.SELL, DType, 
                SDate, EDate, State, Type, TaxType, IssueType, LateOnly, TaxRegIDType, TaxRegID, TaxRegIDYN,
                QString, Page, PerPage, Order, interOPYN, RegType, CloseDownState, MgtKey);
        
        assertNotNull(response);
        
        System.out.println("Search API - " + response.getTotal());
        
    }

    @Test
    public void getLogs_TEST() throws PopbillException {
    
        TaxinvoiceLog[] logs = taxinvoiceService.getLogs("1234567890",
                MgtKeyType.SELL, "20161221-03");
    
        assertNotNull(logs);
        System.out.println(logs.length);
    }

    @Test
    public void getURL_TEST() throws PopbillException {
        
        String url = taxinvoiceService.getURL("1234567890", "SWBOX");
    
        assertNotNull(url);
        System.out.println(url);
    }

    @Test
    public void getPopUpURL_TEST() throws PopbillException {
        String url = taxinvoiceService.getPopUpURL("1234567890",
                MgtKeyType.SELL, "20170303-02");
    
        assertNotNull(url);
        System.out.println(url);
    
    }

    @Test
    public void getVIEWURL_TEST() throws PopbillException {
    
        String url = taxinvoiceService.getViewURL("1234567890",
                MgtKeyType.SELL, "20190226-05");
    
        assertNotNull(url);
        System.out.println(url);
    }

    @Test
    public void getPrintURL_TEST() throws PopbillException {
    
        String url = taxinvoiceService.getPrintURL("1234567890",
                MgtKeyType.SELL, "20200824-001");
    
        assertNotNull(url);
        System.out.println(url);
    }

    @Test
    public void getOldPrintURL_TEST() throws PopbillException {
        
        String url = taxinvoiceService.getOldPrintURL("1234567890",
                MgtKeyType.SELL, "20200824-001", "testkorea");
        
        assertNotNull(url);
        System.out.println(url);
    }

    @Test
    public void getEPrintURL_TEST() throws PopbillException {
    
        String url = taxinvoiceService.getEPrintURL("1234567890",
                MgtKeyType.SELL, "20200824-001");
    
        assertNotNull(url);
        System.out.println(url);
    }

    @Test
    public void getMassPrintURL_TEST() throws PopbillException {
    
        String[] MgtKeyList = new String[] { "20170303-02", "20170303-01", "12345" };
    
        String url = taxinvoiceService.getMassPrintURL("1234567890",
                MgtKeyType.SELL, MgtKeyList);
    
        assertNotNull(url);
        System.out.println(url);
    }

    @Test
    public void getMailURL_TEST() throws PopbillException {
    
        String url = taxinvoiceService.getMailURL("1234567890",
                MgtKeyType.SELL, "20200824-001");
    
        assertNotNull(url);
        System.out.println(url);
    }

    @Test
    public void getPDFURL_TEST() throws PopbillException {
    
        String url = taxinvoiceService.getPDFURL("1234567890",
                MgtKeyType.SELL, "20200728-002");
    
        assertNotNull(url);
        System.out.println(url);
    }

    @Test
    public void GetAccessURL_TEST() throws PopbillException {
    
        String url = taxinvoiceService.getAccessURL("1234567890","testkorea");
    
        System.out.println(url);
    }

    @Test
    public void GetSealURL_TEST() throws PopbillException {
        
        String url = taxinvoiceService.getSealURL("1234567890","testkorea");
    
        System.out.println(url);
    }

    @Test
    public void attachFile_TEST() throws IOException, PopbillException {
    
    
        InputStream FileData = new FileInputStream(
                "/Users/John/Desktop/test.jpg");
    
        Response response = taxinvoiceService.attachFile("1234567890",
                MgtKeyType.SELL, "20170303-02", "test.jpg", FileData);
    
        FileData.close();
    
        assertNotNull(response);
    
        System.out.println(response.getMessage());
    }

    @Test
    public void deleteFile_TEST() throws PopbillException {
        
        String FileID = "56A00CC6-F54C-45F5-A497-C1E5927EFA43.PBF";
    
        Response response = taxinvoiceService.deleteFile("1234567890",
                MgtKeyType.SELL, "20170303-02", FileID);
    
        assertNotNull(response);
    
        System.out.println(response.getMessage());
    
    }

    @Test
    public void getFiles_TEST() throws PopbillException {
    
        AttachedFile[] files = taxinvoiceService.getFiles("1234567890",
                MgtKeyType.SELL, "20200824-001");
    
        assertNotNull(files);
    
        System.out.println(files.length);
    }

    @Test
    public void sendEmail_TEST() throws PopbillException {
    
        Response response = taxinvoiceService.sendEmail("1234567890",
                MgtKeyType.SELL, "20201126-001d", "resend@email.com");
    
        assertNotNull(response);
    
        System.out.println(response.getMessage());
    
    }

    @Test
    public void sendSMS_TEST() throws PopbillException {
    
        Response response = taxinvoiceService.sendSMS("1231212312",
                MgtKeyType.SELL, "1234", "11122224444", "11133334444",
                "문자메시지 내용");
    
        assertNotNull(response);
    
        System.out.println(response.getMessage());
    
    }

    @Test
    public void sendFAX_TEST() throws PopbillException {
    
        Response response = taxinvoiceService.sendFAX("1231212312",
                MgtKeyType.SELL, "1234", "11122224444", "11133334444");
    
        assertNotNull(response);
    
        System.out.println(response.getMessage());
    
    }

    @Test
    public void attchStatement_TEST() throws PopbillException {
        int itemCode = 121;
        String MgtKey = "20160115-05";
        
        Response response = null;
        
        response = taxinvoiceService.attachStatement("1234567890", MgtKeyType.SELL, "20160116-01", itemCode, MgtKey);
    
        assertNotNull(response);
        
        System.out.println("["+response.getCode() +"] " + response.getMessage());
    }

    @Test
    public void detachStatement_TEST() throws PopbillException {
        int itemCode = 121;
        String MgtKey = "20160115-05";
        
        Response response = null;
        
        response = taxinvoiceService.detachStatement("1234567890", MgtKeyType.SELL, "20160116-01", itemCode, MgtKey);
    
        assertNotNull(response);
        
        System.out.println("["+response.getCode() +"] " + response.getMessage());
    }

    @Test
    public void getEmailPublicKeys_TEST() throws PopbillException {
    
        
        EmailPublicKey[] resultList = taxinvoiceService
                .getEmailPublicKeys("1231212312");
    
        assertNotNull(resultList);
    }

    @Test
    public void assignMgtKey_TEST() throws PopbillException{
        
        String CorpNum = "1234567890";
        String ItemKey = "018072314105200001";
        String MgtKey = "20180724_01";
    
        Response response = taxinvoiceService.assignMgtKey(CorpNum, MgtKeyType.SELL, ItemKey, MgtKey, "testkorea");
        assertNotNull(response);
    
        System.out.println("["+response.getCode() +"] " + response.getMessage());
    }

    @Test
    public void listEmailConfig_TEST() throws PopbillException{
        
        EmailSendConfig[] configList = taxinvoiceService.listEmailConfig("1234567890");
        
        assertNotNull(configList);
        
        for(int i=0; i< configList.length; i++){
    
            System.out.print(configList[i].getEmailType()+ " "+ configList[i].getSendYN());
            System.out.println();
        }
    }

    @Test
    public void updateEmailConfig_TEST() throws PopbillException{
        
        String CorpNum = "1234567890";
        String EmailType = "TAX_DENY";
        Boolean SendYN = true;
        String UserID = "testkorea";
        
        Response response = taxinvoiceService.updateEmailConfig(CorpNum, EmailType, SendYN, UserID);
        assertNotNull(response);
        
        System.out.println("["+response.getCode() +"] " + response.getMessage());
    }

    @Test
    public void getSendToNTSConfig_TEST() throws PopbillException {
        
        
        boolean ntsConfig = taxinvoiceService.getSendToNTSConfig("1234567890");
        
        assertNotNull(ntsConfig);
        System.out.println(ntsConfig);
    }

    @Test
    public void GetTaxCertURL_TEST() throws PopbillException {
    
        String url = taxinvoiceService.getTaxCertURL("1234567890","testkorea");
    
        System.out.println(url);
    }

    @Test
    public void getCertificateExpireDate_TEST() throws PopbillException {
        
        Date ExpireDate = taxinvoiceService
                .getCertificateExpireDate("4108600477");
    
        assertNotNull(ExpireDate);
    
        System.out.println(ExpireDate);
    }

    @Test
    public void checkCertValidation() throws PopbillException{
        
        String corpNum = "6798700433";
         
        Response response = taxinvoiceService.checkCertValidation(corpNum);
        assertNotNull(response);
        
        System.out.println("["+response.getCode() +"] " + response.getMessage());
        
    }

    @Test
    public void getTaxCertInfo() throws PopbillException {
        
        TaxinvoiceCertificate certInfo= taxinvoiceService.getTaxCertInfo("1234567890", "testkorea");
    
        System.out.println(certInfo.getRegDT());
        System.out.println(certInfo.getExpireDT());
        System.out.println(certInfo.getIssuerDN());
        System.out.println(certInfo.getSubjectDN());
        System.out.println(certInfo.getIssuerName());
        System.out.println(certInfo.getOid());
        System.out.println(certInfo.getRegContactName());
        System.out.println(certInfo.getRegContactID());
    }

    @Test
    public void GetChargeURL_TEST() throws PopbillException {
    
        String url = taxinvoiceService.getChargeURL("1234567890","testkorea");
    
        System.out.println(url);
    }

    @Test
    public void GetPaymentURL_TEST() throws PopbillException {
        
        String url = taxinvoiceService.getPaymentURL("1234567890", "testkorea");
        
        System.out.println(url);
    }

    @Test
    public void GetUseHistorURL_TEST() throws PopbillException {
        
        String url = taxinvoiceService.getUseHistoryURL("1234567890", null);
        
        System.out.println(url);
    }

    @Test
    public void getPartnerURL_TEST() throws PopbillException{
        
        String url = taxinvoiceService.getPartnerURL("1234567890", "CHRG");
        assertNotNull(url);
        
        System.out.println(url);
    }

    @Test
    public void getUnitCost_TEST() throws PopbillException {
        
        float UnitCost = taxinvoiceService.getUnitCost("1231212312");
    
        System.out.println(UnitCost);
    }

    @Test
    public void getChargeInfo_TEST() throws PopbillException {
        
        ChargeInfo chrgInfo = taxinvoiceService.getChargeInfo("6798700433");

        System.out.println(chrgInfo.getChargeMethod());
        System.out.println(chrgInfo.getUnitCost());
        System.out.println(chrgInfo.getRateSystem());
    }

}

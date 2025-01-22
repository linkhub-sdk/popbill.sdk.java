package com.popbill.api.httaxinvoice.test;

import static org.junit.Assert.assertNotNull;

import java.util.Date;

import org.junit.Test;

import com.popbill.api.ChargeInfo;
import com.popbill.api.FlatRateState;
import com.popbill.api.HTTaxinvoiceService;
import com.popbill.api.PopbillException;
import com.popbill.api.Response;
import com.popbill.api.hometax.HTTaxinvoice;
import com.popbill.api.hometax.HTTaxinvoiceJobState;
import com.popbill.api.hometax.HTTaxinvoiceSearchResult;
import com.popbill.api.hometax.HTTaxinvoiceServiceImp;
import com.popbill.api.hometax.HTTaxinvoiceSummary;
import com.popbill.api.hometax.HTTaxinvoiceXMLResponse;
import com.popbill.api.hometax.QueryType;

public class HTTaxinvoiceServiceTEST {
    
    private final String testLinkID = "TESTER";
    private final String testSecretKey = "SwWxqU+0TErBXy/9TVjIPEnI0VTUMMSQZtJf3Ed8q3I=";

    private HTTaxinvoiceService hometaxTIService;
    
    public HTTaxinvoiceServiceTEST() {
        HTTaxinvoiceServiceImp service = new HTTaxinvoiceServiceImp();

        service.setLinkID(testLinkID);
        service.setSecretKey(testSecretKey);
        service.setTest(true);
        
        hometaxTIService = service;
    }
    
    @Test
    public void getFlatRatePopUpURL_TEST() throws PopbillException {
        String url = hometaxTIService.getFlatRatePopUpURL("1234567890", "testkorea");
        
        assertNotNull(url);
        
        System.out.println("\n\n======== getFlatRatePopUpURL Response ========");
        
        System.out.println(url);
    }
    
    
    
    @Test
    public void getChargeInfo_TEST() throws PopbillException {
        ChargeInfo chrgInfo = hometaxTIService.getChargeInfo("1234567890");
        
        assertNotNull(chrgInfo);
        
        System.out.println("\n\n======== getChargeInfo Response ========");
        
        System.out.println(chrgInfo.getChargeMethod());
        System.out.println(chrgInfo.getRateSystem());
        System.out.println(chrgInfo.getUnitCost());
    }
    
    @Test
    public void getRequestJob_TEST() throws PopbillException {
        
        String DType = "S";
        String SDate = "20241201";
        String EDate = "20241231";
        
        String jobID = hometaxTIService.requestJob("1234567890", QueryType.SELL, DType, SDate, EDate);
        
        assertNotNull(jobID);
        System.out.println("\n\n======== requestJob Response ========");
        System.out.println(jobID);
    } 
    
    @Test
    public void getJobState_TEST() throws PopbillException {
        
        HTTaxinvoiceJobState jobState = hometaxTIService.getJobState("1234567890", "020090116000000001");
        
        assertNotNull(jobState);
        
        System.out.println("\n\n======== getJobState Response ========");
        System.out.println(jobState.getJobID());
        System.out.println(jobState.getJobState());
        System.out.println(jobState.getQueryType());
        System.out.println(jobState.getQueryDateType());
        System.out.println(jobState.getQueryStDate());
        System.out.println(jobState.getQueryEnDate());
        System.out.println(jobState.getErrorCode());
        System.out.println(jobState.getErrorReason());
        System.out.println(jobState.getJobStartDT());
        System.out.println(jobState.getJobEndDT());
        System.out.println(jobState.getCollectCount());
        System.out.println(jobState.getRegDT());
    }    
    
    @Test
    public void listActiveJob_TEST() throws PopbillException {
        HTTaxinvoiceJobState[] jobList = hometaxTIService.listActiveJob("1234567890", "testkorea");
        assertNotNull(jobList);
        
        System.out.println("\n\n======== listActvieJob Response ========");
        
        for ( int i=0; i < jobList.length; i++ ) {
            System.out.println("\n======== ["+(i+1)+"] listActvieJob Response ========");
            System.out.println(jobList[i].getJobID());
            System.out.println(jobList[i].getJobState());
            System.out.println(jobList[i].getQueryType());
            System.out.println(jobList[i].getQueryDateType());
            System.out.println(jobList[i].getQueryStDate());
            System.out.println(jobList[i].getQueryEnDate());
            System.out.println(jobList[i].getErrorCode());
            System.out.println(jobList[i].getErrorReason());
            System.out.println(jobList[i].getJobStartDT());
            System.out.println(jobList[i].getJobEndDT());
            System.out.println(jobList[i].getCollectCount());
            System.out.println(jobList[i].getRegDT());
        }
    }
    
    @Test
    public void search_TEST() throws PopbillException{
        String[] Type = {"N", "M"};
        String[] TaxType = {"T", "Z", "N"};
        String[] PurposeType = {"R", "C", "N"};
        String TaxRegIDType = "";
        String TaxRegID = "";
        String TaxRegIDYN = "";
        Integer Page = 1;
        Integer PerPage = 10;
        String Order = "D";
        
        HTTaxinvoiceSearchResult result = hometaxTIService.search("1234567890", "019101414000000001", Type, TaxType, 
                PurposeType, TaxRegIDYN, TaxRegIDType, TaxRegID, Page, PerPage, Order, "", "123");
        
        assertNotNull ( result ) ;
        
        System.out.println("\n\n======== Search Result ========");
        System.out.println(result.getCode());
        System.out.println(result.getMessage());
        System.out.println(result.getPageCount());
        System.out.println(result.getPageNum());
        System.out.println(result.getPerPage());
        System.out.println(result.getTotal());
        
        for ( int i=0; i<result.getList().size(); i++ ) {
            System.out.println("\n========["+ (i+1) +"] Search Result List Detail ========");
            System.out.println(result.getList().get(i).getInvoiceType());
            System.out.println(result.getList().get(i).getNtsconfirmNum());
            System.out.println(result.getList().get(i).getWriteDate());
            System.out.println(result.getList().get(i).getIssueDate());
            System.out.println(result.getList().get(i).getSendDate());
            System.out.println(result.getList().get(i).getTaxType());
            System.out.println(result.getList().get(i).getPurposeType());
            System.out.println(result.getList().get(i).getSupplyCostTotal());
            System.out.println(result.getList().get(i).getTaxTotal());
            System.out.println(result.getList().get(i).getTotalAmount());
            System.out.println(result.getList().get(i).getRemark1());
            System.out.println(result.getList().get(i).getRemark());
            System.out.println(result.getList().get(i).getModifyYN());
            System.out.println(result.getList().get(i).getOrgNTSConfirmNum());
            System.out.println(result.getList().get(i).getPurchaseDate());
            System.out.println(result.getList().get(i).getItemName());
            System.out.println(result.getList().get(i).getSpec());
            System.out.println(result.getList().get(i).getQty());
            System.out.println(result.getList().get(i).getUnitCost());
            System.out.println(result.getList().get(i).getSupplyCost());
            System.out.println(result.getList().get(i).getTax());
            System.out.println(result.getList().get(i).getInvoicerCorpNum());
            System.out.println(result.getList().get(i).getInvoicerTaxRegID());
            System.out.println(result.getList().get(i).getInvoicerCorpName());
            System.out.println(result.getList().get(i).getInvoicerCEOName());
            System.out.println(result.getList().get(i).getInvoicerEmail());
            System.out.println(result.getList().get(i).getInvoiceeCorpNum());
            System.out.println(result.getList().get(i).getInvoiceeType());
            System.out.println(result.getList().get(i).getInvoiceeTaxRegID());
            System.out.println(result.getList().get(i).getInvoiceeCorpName());
            System.out.println(result.getList().get(i).getInvoiceeCEOName());
            System.out.println(result.getList().get(i).getInvoiceeEmail1());
            System.out.println(result.getList().get(i).getInvoiceeEmail2());
            System.out.println(result.getList().get(i).getTrusteeCorpNum());
            System.out.println(result.getList().get(i).getTrusteeTaxRegID());
            System.out.println(result.getList().get(i).getTrusteeCorpName());
            System.out.println(result.getList().get(i).getTrusteeCEOName());
            System.out.println(result.getList().get(i).getTrusteeEmail());    
        }
    }
    
    @Test
    public void summary_TEST() throws PopbillException{
        String[] Type = {"N","M"};
        String[] TaxType = {"T", "Z", "N"};
        String[] PurposeType = {"R","C", "N"};
        String TaxRegIDType = "";
        String TaxRegID = "";
        String TaxRegIDYN = "";
        
        HTTaxinvoiceSummary result = hometaxTIService.summary("1234567890", "019101414000000001", Type, TaxType, 
                PurposeType, TaxRegIDYN, TaxRegIDType, TaxRegID, "", "");
        
        assertNotNull(result);
        
        System.out.println("\n\n======== Summary Result ========");
        System.out.println(result.getCount());
        System.out.println(result.getSupplyCostTotal());
        System.out.println(result.getTaxTotal());
        System.out.println(result.getAmountTotal());
        
    }
    
    @Test
    public void getTaxinvoice_TEST() throws PopbillException {
        String NTSConfirmNum = "20160615410000290000020d"; // 국세청 승인번호 
        HTTaxinvoice taxinvoiceInfo = hometaxTIService.getTaxinvoice("1234567890", NTSConfirmNum, "testkorea");
        
        assertNotNull(taxinvoiceInfo);
        
        System.out.println("\n\n======== GetTaxinvoice Result ========");
        System.out.println(taxinvoiceInfo.getWriteDate());
        System.out.println(taxinvoiceInfo.getIssueDT());
        System.out.println(taxinvoiceInfo.getInvoiceType());
        System.out.println(taxinvoiceInfo.getTaxType());
        System.out.println(taxinvoiceInfo.getInvoicerCorpNum());
        System.out.println(taxinvoiceInfo.getInvoicerMgtKey());
        System.out.println(taxinvoiceInfo.getInvoicerTaxRegID());
        System.out.println(taxinvoiceInfo.getInvoicerCorpName());
        System.out.println(taxinvoiceInfo.getInvoicerCEOName());
        System.out.println(taxinvoiceInfo.getInvoicerAddr());
        System.out.println(taxinvoiceInfo.getInvoicerBizClass());
        System.out.println(taxinvoiceInfo.getInvoicerBizType());
        System.out.println(taxinvoiceInfo.getInvoicerContactName());
        System.out.println(taxinvoiceInfo.getInvoicerDeptName());
        System.out.println(taxinvoiceInfo.getInvoicerTEL());
        System.out.println(taxinvoiceInfo.getInvoicerEmail());
        System.out.println("\n------invoicee INfo------");
        System.out.println(taxinvoiceInfo.getInvoiceeCorpNum());
        System.out.println(taxinvoiceInfo.getInvoiceeType());
        System.out.println(taxinvoiceInfo.getInvoiceeMgtKey());
        System.out.println(taxinvoiceInfo.getInvoiceeTaxRegID());
        System.out.println(taxinvoiceInfo.getInvoiceeCorpName());
        System.out.println(taxinvoiceInfo.getInvoiceeCEOName());
        System.out.println(taxinvoiceInfo.getInvoiceeAddr());
        System.out.println(taxinvoiceInfo.getInvoiceeBizType());
        System.out.println(taxinvoiceInfo.getInvoiceeBizClass());
        System.out.println(taxinvoiceInfo.getInvoiceeContactName1());
        System.out.println(taxinvoiceInfo.getInvoiceeDeptName1());
        System.out.println(taxinvoiceInfo.getInvoiceeTEL1());
        System.out.println(taxinvoiceInfo.getInvoiceeEmail1());
        System.out.println(taxinvoiceInfo.getInvoiceeContactName2());
        System.out.println(taxinvoiceInfo.getInvoiceeDeptName2());
        System.out.println(taxinvoiceInfo.getInvoiceeTEL2());
        System.out.println(taxinvoiceInfo.getInvoiceeEmail2());
        System.out.println("\n-------trustee info-------");
        System.out.println(taxinvoiceInfo.getTrusteeCorpNum());
        System.out.println(taxinvoiceInfo.getTrusteeMgtKey());
        System.out.println(taxinvoiceInfo.getTrusteeTaxRegID());
        System.out.println(taxinvoiceInfo.getTrusteeCorpName());
        System.out.println(taxinvoiceInfo.getTrusteeCEOName());
        System.out.println(taxinvoiceInfo.getTrusteeAddr());
        System.out.println(taxinvoiceInfo.getTrusteeBizType());
        System.out.println(taxinvoiceInfo.getTrusteeBizClass());
        System.out.println(taxinvoiceInfo.getTrusteeContactName());
        System.out.println(taxinvoiceInfo.getTrusteeDeptName());
        System.out.println(taxinvoiceInfo.getTrusteeTEL());
        System.out.println(taxinvoiceInfo.getTrusteeEmail());
        System.out.println("\n-------taxinvoice info-------");
        System.out.println(taxinvoiceInfo.getSupplyCostTotal());
        System.out.println(taxinvoiceInfo.getTaxTotal());
        System.out.println(taxinvoiceInfo.getTotalAmount());
        System.out.println(taxinvoiceInfo.getModifyCode());
        System.out.println(taxinvoiceInfo.getOrgNTSConfirmNum());
        System.out.println(taxinvoiceInfo.getPurposeType());
        System.out.println(taxinvoiceInfo.getSerialNum());
        System.out.println(taxinvoiceInfo.getCash());
        System.out.println(taxinvoiceInfo.getChkBill());
        System.out.println(taxinvoiceInfo.getCredit());
        System.out.println(taxinvoiceInfo.getNote());
        System.out.println(taxinvoiceInfo.getRemark1());
        System.out.println(taxinvoiceInfo.getRemark2());
        System.out.println(taxinvoiceInfo.getRemark3());
        System.out.println(taxinvoiceInfo.getNtsconfirmNum());
        System.out.println("\n------detailList info------");
        System.out.println(taxinvoiceInfo.getDetailList().get(0).getSerialNum());
        System.out.println(taxinvoiceInfo.getDetailList().get(0).getPurchaseDT());
        System.out.println(taxinvoiceInfo.getDetailList().get(0).getItemName());
        System.out.println(taxinvoiceInfo.getDetailList().get(0).getSpec());
        System.out.println(taxinvoiceInfo.getDetailList().get(0).getQty());
        System.out.println(taxinvoiceInfo.getDetailList().get(0).getUnitCost());
        System.out.println(taxinvoiceInfo.getDetailList().get(0).getSupplyCost());
        System.out.println(taxinvoiceInfo.getDetailList().get(0).getTax());
        System.out.println(taxinvoiceInfo.getDetailList().get(0).getRemark());    
    }
    
    
    @Test
    public void getTaxinvoiceXML_TEST() throws PopbillException {
        String NTSConfirmNum = "2016061541000029000001ee"; // 국세청 승인번호 
        HTTaxinvoiceXMLResponse xmlInfo = hometaxTIService.getXML("1234567890", NTSConfirmNum, "testkorea");
        
        assertNotNull(xmlInfo);
        
        System.out.println(xmlInfo.getResultCode());
        System.out.println(xmlInfo.getRetObject());
        System.out.println(xmlInfo.getMessage());
    }
    
    
    @Test
    public void getFlatRateState_TEST() throws PopbillException {
        FlatRateState rateInfo = hometaxTIService.getFlatRateState("1234567890", "testkorea");
        
        assertNotNull(rateInfo);
        
        System.out.println("\n\n======== GetFlatRateState ========");
        System.out.println(rateInfo.getReferenceID());
        System.out.println(rateInfo.getContractDT());
        System.out.println(rateInfo.getBaseDate());
        System.out.println(rateInfo.getUseEndDate());
        System.out.println(rateInfo.getState());
        System.out.println(rateInfo.getCloseRequestYN());
        System.out.println(rateInfo.getUseRestrictYN());
        System.out.println(rateInfo.getCloseOnExpired());
        System.out.println(rateInfo.getUnPaidYN());
    }
    @Test
    public void getCertificatePopUpURL_TEST() throws PopbillException {
        String url = hometaxTIService.getCertificatePopUpURL("1234567890", "testkorea");
        
        assertNotNull(url);
        
        System.out.println("\n\n======== GetCertificatePopUPURL ========");
        System.out.println(url);
    }
    
    @Test
    public void getCertificateExpireDate_TEST() throws PopbillException {
        Date ExpireDate = hometaxTIService.getCertificateExpireDate("1234567890");
        assertNotNull(ExpireDate);
        System.out.println("\n\n======== CertificateExprieDate ========");
        System.out.println(ExpireDate);
    }
    
    @Test
    public void checkCertValidation_TEST() throws PopbillException{
        String corpNum = "1234567890";
        
        Response response = hometaxTIService.checkCertValidation(corpNum);
        assertNotNull(response);
        
        System.out.println("["+response.getCode() +"] " + response.getMessage());
        
    }
    
    @Test
    public void registDeptUser_TEST() throws PopbillException{
        String corpNum = "1234567890";
        String deptUserID = "test_id";
        String deptUserPWD = "test_pwd";
        
        Response response = hometaxTIService.registDeptUser(corpNum, deptUserID, deptUserPWD);
        assertNotNull(response);
        
        System.out.println("["+response.getCode() +"] " + response.getMessage());        
    }
    
    @Test
    public void checkDeptUser_TEST() throws PopbillException{
        String corpNum = "1234567890";
        
        Response response = hometaxTIService.checkDeptUser(corpNum);
        assertNotNull(response);
        
        System.out.println("["+response.getCode() +"] " + response.getMessage());        
    }
    
    @Test
    public void checkLoginDeptUser_TEST() throws PopbillException{
        String corpNum = "1234567890";
        
        Response response = hometaxTIService.checkLoginDeptUser(corpNum);
        assertNotNull(response);
        
        System.out.println("["+response.getCode() +"] " + response.getMessage());        
    }
    
    @Test
    public void deleteDeptUser_TEST() throws PopbillException{
        String corpNum = "1234567890";
        
        Response response = hometaxTIService.deleteDeptUser(corpNum);
        assertNotNull(response);
        
        System.out.println("["+response.getCode() +"] " + response.getMessage());        
    }
    
    @Test
    public void getPrintURL_TEST() throws PopbillException {
        String url = hometaxTIService.getPrintURL("1234567890", "201907054100001141339517");
        
        assertNotNull(url);
        
        System.out.println("\n\n======== getPrintURL Response ========");
        
        System.out.println(url);
    }
}


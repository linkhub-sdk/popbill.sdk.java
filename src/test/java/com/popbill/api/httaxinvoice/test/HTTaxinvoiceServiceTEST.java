package com.popbill.api.httaxinvoice.test;

import static org.junit.Assert.assertNotNull;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import com.popbill.api.ChargeInfo;
import com.popbill.api.FlatRateState;
import com.popbill.api.HTTaxinvoiceService;
import com.popbill.api.PopbillException;
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
		
		String DType = "W";
		String SDate = "20160601";
		String EDate = "20160615";
		
		String jobID = hometaxTIService.requestJob("4108600477", QueryType.SELL, DType, SDate, EDate, "innopost");
		
		assertNotNull(jobID);
		System.out.println("\n\n======== requestJob Response ========");
		System.out.println(jobID);
	}
	
	@Test
	public void getJobState_TEST() throws PopbillException {
		
		HTTaxinvoiceJobState jobState = hometaxTIService.getJobState("4108600477", "016061515000000002", "innoposttest");
		
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
		HTTaxinvoiceJobState[] jobList = hometaxTIService.listActiveJob("4108600477", "innoposttest");
		assertNotNull(jobList);
		
		System.out.println("\n\n======== listActvieJob Response ========");
		
		for ( int i=0; i < jobList.length; i++ ) {
			System.out.println(jobList[i].getJobID());
		}
	}
	
	@Test
	public void search_TEST() throws PopbillException{
		String[] Type = {"N","M"};
		String[] TaxType = {"T", "Z", "N"};
		String[] PurposeType = {"R","C", "N"};
		String TaxRegIDType = "S";
		String[] TaxRegID = {"",""};
		Boolean TaxRegIDYN = false;
		Integer Page = 1;
		Integer PerPage = 50;
		String Order = "D";
		
		HTTaxinvoiceSearchResult result = hometaxTIService.search("4108600477", "016061515000000002", Type, TaxType, 
				PurposeType, TaxRegIDYN, TaxRegIDType, TaxRegID, Page, PerPage, Order);
		
		assertNotNull(result);
		
		System.out.println("\n\n======== Search Result ========");
		System.out.println(result.getCode());
		System.out.println(result.getMessage());
		System.out.println(result.getPageCount());
		System.out.println(result.getPageNum());
		System.out.println(result.getPerPage());
		System.out.println(result.getTotal());
		
		for(int i=0; i<result.getList().size(); i++){
			System.out.println(i +" "+result.getList().get(i).getInvoiceeCorpName()+" "+result.getList().get(i).getModifyYN());	
		}
	}
	
	@Test
	public void summary_TEST() throws PopbillException{
		String[] Type = {"N","M"};
		String[] TaxType = {"T", "Z", "N"};
		String[] PurposeType = {"R","C", "N"};
		String TaxRegIDType = "S";
		String[] TaxRegID = {"",""};
		Boolean TaxRegIDYN = false;
		
		HTTaxinvoiceSummary result = hometaxTIService.summary("4108600477", "016061515000000002", Type, TaxType, 
				PurposeType, TaxRegIDYN, TaxRegIDType, TaxRegID);
		
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
		HTTaxinvoice taxinvoiceInfo = hometaxTIService.getTaxinvoice("4108600477", NTSConfirmNum, "innoposttest");
		
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
		System.out.println("\n-------taxinvoice info-------");
		System.out.println(taxinvoiceInfo.getTaxTotal());
		System.out.println(taxinvoiceInfo.getSupplyCostTotal());
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
		HTTaxinvoiceXMLResponse xmlInfo = hometaxTIService.getXML("4108600477", NTSConfirmNum, "innoposttest");
		
		assertNotNull(xmlInfo);
		
		System.out.println(xmlInfo.getResultCode());
		System.out.println(xmlInfo.getRetObject());
		System.out.println(xmlInfo.getMessage());
	}
	
	
	@Test
	public void getFlatRateState_TEST() throws PopbillException {
		FlatRateState rateInfo = hometaxTIService.getFlatRateState("4108600477", "innoposttest");
		
		assertNotNull(rateInfo);
		
		System.out.println("\n\n======== GetFlatRateState ========");
		System.out.println(rateInfo.getReferenceID());
		System.out.println(rateInfo.getContraDT());
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
		String url = hometaxTIService.getCertificatePopUpURL("4108600477", "innoposttest");
		
		assertNotNull(url);
		
		System.out.println("\n\n======== GetCertificatePopUPURL ========");
		System.out.println(url);
	}	
	
	@Test
	public void getCertificateExpireDate_TEST() throws PopbillException {
		Date ExpireDate = hometaxTIService.getCertificateExpireDate("4108600477");
		assertNotNull(ExpireDate);
		
		System.out.println(ExpireDate);
	}

}


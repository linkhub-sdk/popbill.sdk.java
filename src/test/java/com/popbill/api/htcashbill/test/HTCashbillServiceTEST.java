package com.popbill.api.htcashbill.test;

import static org.junit.Assert.assertNotNull;

import com.popbill.api.*;
import com.popbill.api.hometax.*;
import org.junit.Test;

import java.util.Date;

public class HTCashbillServiceTEST {
	private final String testLinkID = "TESTER";
	private final String testSecretKey = "SwWxqU+0TErBXy/9TVjIPEnI0VTUMMSQZtJf3Ed8q3I=";
	
	private HTCashbillService htCashbillService;
	
	public HTCashbillServiceTEST() {
		HTCashbillServiceImp service = new HTCashbillServiceImp();

		service.setLinkID(testLinkID);
		service.setSecretKey(testSecretKey);
		service.setTest(true);
		
		htCashbillService = service;
	}
	
	@Test
	public void getChargeInfo_TEST() throws PopbillException {
		ChargeInfo chrgInfo = htCashbillService.getChargeInfo("1234567890");

		assertNotNull(chrgInfo);

		System.out.println("\n\n======== getChargeInfo Response ========");

		System.out.println(chrgInfo.getChargeMethod());
		System.out.println(chrgInfo.getRateSystem());
		System.out.println(chrgInfo.getUnitCost());
	}

	@Test
	public void getRequestJob_TEST() throws PopbillException {

		String SDate = "20180101";
		String EDate = "20181231";

		String jobID = htCashbillService.requestJob("1234567890", QueryType.BUY, SDate, EDate);

		assertNotNull(jobID);
		System.out.println("\n\n======== requestJob Response ========");
		System.out.println(jobID);

	}

	@Test
	public void getJobState_TEST() throws PopbillException {

		String JobID = "016061711000000001";
		String UserID = "innoposttest";

		HTCashbillJobState jobState = htCashbillService.getJobState("1234567890", JobID, UserID);

		assertNotNull(jobState);
		System.out.println("\n\n======== GetJobState ========");
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
		String CorpNum = "1234567890";
		String UserID = "innoposttest";

		HTCashbillJobState[] jobList = htCashbillService.listActiveJob(CorpNum, UserID);

		assertNotNull(jobList);

		for ( int i=0; i < jobList.length; i++ ) {
			System.out.println("\n\n========["+(i+1)+"] ListActiveJob ========");
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
	public void search_TEST() throws PopbillException {
		String CorpNum = "6798700433";
		String JobID = "018092920000000001";
		String[] TypeUsage = {"P", "C"};
		String[] TradeType = {"N", "C"};
		Integer Page = 1;
		Integer PerPage = 10;
		String Order = "D";
		String UserID = "";

		HTCashbillSearchResult result = htCashbillService.search(CorpNum, JobID, TypeUsage, TradeType, Page, PerPage, Order, UserID);

		assertNotNull(result);

		System.out.println("\n\n======== Search Result ========");
		System.out.println(result.getCode());
		System.out.println(result.getMessage());
		System.out.println(result.getPageCount());
		System.out.println(result.getPageNum());
		System.out.println(result.getPerPage());
		System.out.println(result.getTotal());

		for ( int i=0; i<result.getList().size(); i++ ) {
			System.out.println("\n========["+(i+1)+"] Search Result ========");
			System.out.println(result.getList().get(i).getInvoiceType());
			System.out.println(result.getList().get(i).getNtsconfirmNum());
			System.out.println(result.getList().get(i).getTradeDate());
			System.out.println(result.getList().get(i).getTradeDT());
			System.out.println(result.getList().get(i).getTradeUsage());
			System.out.println(result.getList().get(i).getTradeType());
			System.out.println(result.getList().get(i).getSupplyCost());
			System.out.println(result.getList().get(i).getTax());
			System.out.println(result.getList().get(i).getServiceFee());
			System.out.println(result.getList().get(i).getTotalAmount());
			System.out.println(result.getList().get(i).getFranchiseCorpNum());
			System.out.println(result.getList().get(i).getFranchiseCorpName());
			System.out.println(result.getList().get(i).getFranchiseCorpType());
			System.out.println(result.getList().get(i).getIdentityNumType());
			System.out.println(result.getList().get(i).getIdentityNum());
			System.out.println(result.getList().get(i).getCustomerName());
			System.out.println(result.getList().get(i).getCardOwnerName());
			System.out.println(result.getList().get(i).getDeductionType());
		}
	}

	@Test
	public void summary_TEST() throws PopbillException {
		String CorpNum = "1234567890";
		String JobID = "016061713000000003";
		String[] TypeUsage = {"P","C"};
		String[] TradeType = {"N", "C"};

		String UserID = "innoposttest";

		HTCashbillSummary result = htCashbillService.summary(CorpNum, JobID, TypeUsage, TradeType, UserID);

		assertNotNull(result);
		System.out.println("\n\n======== Summary Result ========");
		System.out.println(result.getCount());
		System.out.println(result.getSupplyCostTotal());
		System.out.println(result.getTaxTotal());
		System.out.println(result.getServiceFeeTotal());
		System.out.println(result.getAmountTotal());
	}

	@Test
	public void getFlatRatePopUpURL_TEST() throws PopbillException {
		String url = htCashbillService.getFlatRatePopUpURL("1234567890", "innoposttest");

		assertNotNull(url);

		System.out.println("\n\n======== getFlatRatePopUpURL Response ========");

		System.out.println(url);
	}


	@Test
	public void getFlatRateState_TEST() throws PopbillException {
		FlatRateState rateInfo = htCashbillService.getFlatRateState("1234567890", "innoposttest");

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
		String url = htCashbillService.getCertificatePopUpURL("1234567890", "innoposttest");

		assertNotNull(url);

		System.out.println("\n\n======== getCertificatePopUpURL Response ========");

		System.out.println(url);
	}

	@Test
	public void getCertificateExpireDate_TEST() throws PopbillException {
		Date ExpireDate = htCashbillService.getCertificateExpireDate("1234567890");
		assertNotNull(ExpireDate);
		System.out.println("\n\n======== Certificate Expire d ========");
		System.out.println(ExpireDate);
	}
	

	@Test
	public void checkCertValidation_TEST() throws PopbillException{
		String corpNum = "1234567890";
		
		Response response = htCashbillService.checkCertValidation(corpNum);
		assertNotNull(response);
		
		System.out.println("["+response.getCode() +"] " + response.getMessage());
		
	}
	
	@Test
	public void registDeptUser_TEST() throws PopbillException{
		String corpNum = "1234567890";
		String deptUserID = "linkhub0002";
		String deptUserPWD = "linkhub8536!";
		
		Response response = htCashbillService.registDeptUser(corpNum, deptUserID, deptUserPWD);
		assertNotNull(response);
		
		System.out.println("["+response.getCode() +"] " + response.getMessage());		
	}
	
	@Test
	public void checkDeptUser_TEST() throws PopbillException{
		String corpNum = "1234567890";
		
		Response response = htCashbillService.checkDeptUser(corpNum);
		assertNotNull(response);
		
		System.out.println("["+response.getCode() +"] " + response.getMessage());		
	}
	
	@Test
	public void checkLoginDeptUser_TEST() throws PopbillException{
		String corpNum = "1234567890";
		
		Response response = htCashbillService.checkLoginDeptUser(corpNum);
		assertNotNull(response);
		
		System.out.println("["+response.getCode() +"] " + response.getMessage());		
	}
	
	@Test
	public void deleteDeptUser_TEST() throws PopbillException{
		String corpNum = "1234567890";
		
		Response response = htCashbillService.deleteDeptUser(corpNum);
		assertNotNull(response);
		
		System.out.println("["+response.getCode() +"] " + response.getMessage());		
	}		
//	
}

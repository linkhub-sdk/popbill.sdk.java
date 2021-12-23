package com.popbill.api.accountcheck.test;

import org.junit.Test;

import com.popbill.api.AccountCheckInfo;
import com.popbill.api.AccountCheckService;
import com.popbill.api.ChargeInfo;
import com.popbill.api.DepositorCheckInfo;
import com.popbill.api.PopbillException;
import com.popbill.api.accountcheck.AccountCheckServiceImp;

public class AccountCheckServiceTEST {
	
	private final String testLinkID = "TESTER";
	private final String testSecretKey = "SwWxqU+0TErBXy/9TVjIPEnI0VTUMMSQZtJf3Ed8q3I=";

	private AccountCheckService accountCheckService;
	
	public AccountCheckServiceTEST() {
		AccountCheckServiceImp service = new AccountCheckServiceImp();

		service.setLinkID(testLinkID);
		service.setSecretKey(testSecretKey);
		service.setTest(true);
		
		accountCheckService = service;
	}
	
	@Test
	public void getChargeInfo_TEST() throws PopbillException {
		
		ChargeInfo chrgInfo = accountCheckService.getChargeInfo("1234567890");

		System.out.println(chrgInfo.getChargeMethod());
		System.out.println(chrgInfo.getRateSystem());
		System.out.println(chrgInfo.getUnitCost());
	
	}	
	
	@Test
	public void getUnitCost_TEST() throws PopbillException {
		
		float UnitCost = accountCheckService.getUnitCost("1234567890");

		System.out.println(UnitCost);
	}
	
	@Test
	public void getChargeInfo_TEST_성명() throws PopbillException {
		
		ChargeInfo chrgInfo = accountCheckService.getChargeInfo("1234567890","성명");
		
		System.out.println(chrgInfo.getChargeMethod());
		System.out.println(chrgInfo.getRateSystem());
		System.out.println(chrgInfo.getUnitCost());
		
	}	
	
	@Test
	public void getUnitCost_TEST_성명() throws PopbillException {
		
		float UnitCost = accountCheckService.getUnitCost("1234567890","성명");
		
		System.out.println(UnitCost);
	}

	@Test
	public void CheckAccountInfo() throws PopbillException {
		
		AccountCheckInfo state = accountCheckService.CheckAccountInfo("1234567890", "12345", "1012051447401");
		
		System.out.println(state.getAccountName());
		System.out.println(state.getAccountNumber());
		System.out.println(state.getBankCode());
		System.out.println(state.getCheckDate());
		System.out.println(state.getResultCode());
		System.out.println(state.getResultMessage());
		
	}

	@Test
	public void getChargeInfo_TEST_실명() throws PopbillException {
		
		ChargeInfo chrgInfo = accountCheckService.getChargeInfo("1234567890","실명");

		System.out.println(chrgInfo.getChargeMethod());
		System.out.println(chrgInfo.getRateSystem());
		System.out.println(chrgInfo.getUnitCost());
	
	}	
	
	@Test
	public void getUnitCost_TEST_실명() throws PopbillException {
		
		float UnitCost = accountCheckService.getUnitCost("1234567890","실명");

		System.out.println(UnitCost);
	}

	@Test
	public void CheckDepositorInfo() throws PopbillException {
		
		DepositorCheckInfo state = accountCheckService.CheckDepositorInfo("1234567890", "12345", "1234567890","P","881211");
		
		System.out.println(state.getAccountName());
		System.out.println(state.getAccountNumber());
		System.out.println(state.getBankCode());
		System.out.println(state.getCheckDate());
		System.out.println(state.getIdentityNum());
		System.out.println(state.getIdentityNumType());
		System.out.println(state.getResultCode());
		System.out.println(state.getResultMessage());
		
	}
}



package com.popbill.api.closedown.test;

import java.io.IOException;

import org.junit.Test;

import com.popbill.api.ChargeInfo;
import com.popbill.api.CloseDownService;
import com.popbill.api.CorpState;
import com.popbill.api.PopbillException;
import com.popbill.api.Response;
import com.popbill.api.closedown.CloseDownServiceImp;

public class CloseDownServiceTEST {

	private final String testLinkID = "TESTER";
	private final String testSecretKey = "SwWxqU+0TErBXy/9TVjIPEnI0VTUMMSQZtJf3Ed8q3I=";

	private CloseDownService closeDownService;
	
	public CloseDownServiceTEST() {
		CloseDownServiceImp service = new CloseDownServiceImp();

		service.setLinkID(testLinkID);
		service.setSecretKey(testSecretKey);
		service.setTest(true);
//		service.setAuthURL("http://{ProxyIP}:{Port}");
//		service.setServiceURL("http://{ProxyIP}:{Port}");
//		service.setTestServiceURL("http://{ProxyIP}:{Port}");
		closeDownService = service;
	}
	@Test
	public void getChargeInfo_TEST() throws PopbillException {
		
		ChargeInfo chrgInfo = closeDownService.getChargeInfo("1234567890");

		System.out.println(chrgInfo.getChargeMethod());
		System.out.println(chrgInfo.getRateSystem());
		System.out.println(chrgInfo.getUnitCost());
	
	}	
	
	@Test
	public void getUnitCost_TEST() throws PopbillException {
		
		float UnitCost = closeDownService.getUnitCost("1231212312");

		System.out.println(UnitCost);
	}

	@Test
	public void CheckCorpNum() throws PopbillException {
		
		CorpState state = null;
		
		try {
			state = closeDownService.CheckCorpNum("1234567890", "169-09-01387");
		} catch (PopbillException e) {
			System.out.println(e.getCode());
			System.out.println(e.getMessage());
		}
		
		
		System.out.println(state.getState());
		System.out.println(state.getTypeDate());
		System.out.println(state.getType());
		System.out.println(state.getTaxType());
		
	}
	
	@Test
	public void CheckCorpNums() throws PopbillException, IOException {
		
		String[] CorpNumList = new String[] {"169-09-01387","401-03-94930"};
		
		CorpState[] state =closeDownService.CheckCorpNum("1234567890",CorpNumList);
		
		
		System.out.println(state[0].getState());
		System.out.println(state[0].getTypeDate());
		System.out.println(state[0].getType());
		System.out.println(state[0].getTaxType());
		
		System.out.println(state[1].getState());
		System.out.println(state[1].getTypeDate());
		System.out.println(state[1].getType());
		System.out.println(state[1].getTaxType());
	}
	
	@Test
	public void CheckIsMember() throws PopbillException {
		
		Response response =  closeDownService.checkIsMember("1234567890", "TESTER");
		
		System.out.println(response.getCode());
	}
}

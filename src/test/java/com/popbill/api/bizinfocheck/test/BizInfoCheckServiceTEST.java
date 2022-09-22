package com.popbill.api.bizinfocheck.test;

import org.junit.Test;

import com.popbill.api.BizInfoCheck;
import com.popbill.api.BizInfoCheckService;
import com.popbill.api.ChargeInfo;
import com.popbill.api.PopbillException;
import com.popbill.api.Response;
import com.popbill.api.bizinfocheck.BizInfoCheckServiceImp;

public class BizInfoCheckServiceTEST {
	
    private final String testLinkID = "TESTER";
    private final String testSecretKey = "SwWxqU+0TErBXy/9TVjIPEnI0VTUMMSQZtJf3Ed8q3I=";

    private BizInfoCheckService bizInfoService;
    
    public BizInfoCheckServiceTEST() {
        BizInfoCheckServiceImp service = new BizInfoCheckServiceImp();

        service.setLinkID(testLinkID);
        service.setSecretKey(testSecretKey);
        service.setTest(true);
        
        bizInfoService = service;
    }
    @Test
    public void getChargeInfo_TEST() throws PopbillException {
        
        ChargeInfo chrgInfo = bizInfoService.getChargeInfo("1234567890");

        System.out.println(chrgInfo.getChargeMethod());
        System.out.println(chrgInfo.getRateSystem());
        System.out.println(chrgInfo.getUnitCost());
    
    }    
    
    @Test
    public void getUnitCost_TEST() throws PopbillException {
        
        float UnitCost = bizInfoService.getUnitCost("1231212312");

        System.out.println(UnitCost);
    }

    @Test
    public void CheckCorpNum() throws PopbillException {
        try {
            BizInfoCheck state = bizInfoService.CheckBizInfo("1234567890", "6798700433");
            
            System.out.println(state.getCorpNum());
            System.out.println(state.getCheckDT());
            System.out.println(state.getCorpName());
            System.out.println(state.getCorpCode());
            System.out.println(state.getCorpScaleCode());
            System.out.println(state.getPersonCorpCode());
            System.out.println(state.getHeadOfficeCode());
            System.out.println(state.getIndustryCode());
            System.out.println(state.getCompanyRegNum());
            System.out.println(state.getEstablishDate());
            System.out.println(state.getEstablishCode());
            System.out.println(state.getCEOName());
            System.out.println(state.getWorkPlaceCode());
            System.out.println(state.getAddrCode());
            System.out.println(state.getZipCode());
            System.out.println(state.getAddr());
            System.out.println(state.getAddrDetail());
            System.out.println(state.getEnAddr());
            System.out.println(state.getBizClass());
            System.out.println(state.getBizType());
            System.out.println(state.getResult());
            System.out.println(state.getResultMessage());
            System.out.println(state.getCloseDownType());
            System.out.println(state.getCloseDownTypeDate());
            System.out.println(state.getCloseDownState());
            System.out.println(state.getCloseDownStateDate());
        } catch (PopbillException e) {
            System.out.println(e.getCode());
            System.out.println(e.getMessage());
        }
    }
    
    @Test
    public void CheckCorpNum2() throws PopbillException {
        try {
            BizInfoCheck state = bizInfoService.CheckBizInfo("1234567890", "6798700433","testkorea");
            
            System.out.println(state.getCorpNum());
            System.out.println(state.getCheckDT());
            System.out.println(state.getCorpName());
            System.out.println(state.getCorpCode());
            System.out.println(state.getCorpScaleCode());
            System.out.println(state.getPersonCorpCode());
            System.out.println(state.getHeadOfficeCode());
            System.out.println(state.getIndustryCode());
            System.out.println(state.getCompanyRegNum());
            System.out.println(state.getEstablishDate());
            System.out.println(state.getEstablishCode());
            System.out.println(state.getCEOName());
            System.out.println(state.getWorkPlaceCode());
            System.out.println(state.getAddrCode());
            System.out.println(state.getZipCode());
            System.out.println(state.getAddr());
            System.out.println(state.getAddrDetail());
            System.out.println(state.getEnAddr());
            System.out.println(state.getBizClass());
            System.out.println(state.getBizType());
            System.out.println(state.getResult());
            System.out.println(state.getResultMessage());
            System.out.println(state.getCloseDownType());
            System.out.println(state.getCloseDownTypeDate());
            System.out.println(state.getCloseDownState());
            System.out.println(state.getCloseDownStateDate());
        } catch (PopbillException e) {
            System.out.println(e.getCode());
            System.out.println(e.getMessage());
        }
    }
    
    @Test
    public void CheckIsMember() throws PopbillException {
        
        Response response =  bizInfoService.checkIsMember("1234567890", "TESTER");
        
        System.out.println(response.getCode());
    }
}

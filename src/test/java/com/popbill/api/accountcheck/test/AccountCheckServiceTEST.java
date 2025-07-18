package com.popbill.api.accountcheck.test;

import org.junit.Test;

import com.popbill.api.AccountCheckInfo;
import com.popbill.api.AccountCheckService;
import com.popbill.api.ChargeInfo;
import com.popbill.api.DepositorCheckInfo;
import com.popbill.api.PopbillException;
import com.popbill.api.RefundHistory;
import com.popbill.api.Response;
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

        ChargeInfo chrgInfo = accountCheckService.getChargeInfo("1234567890", "성명");

        System.out.println(chrgInfo.getChargeMethod());
        System.out.println(chrgInfo.getRateSystem());
        System.out.println(chrgInfo.getUnitCost());

    }

    @Test
    public void getUnitCost_TEST_성명() throws PopbillException {

        float UnitCost = accountCheckService.getUnitCost("1234567890", "성명");

        System.out.println(UnitCost);
    }

    @Test
    public void CheckAccountInfo() throws PopbillException {

        AccountCheckInfo state =
                accountCheckService.CheckAccountInfo("1234567890", "0034", "611121245511");

        System.out.println(state.getAccountName());
        System.out.println(state.getAccountNumber());
        System.out.println(state.getBankCode());
        System.out.println(state.getCheckDate());
        System.out.println(state.getResult());
        System.out.println(state.getResultCode());
        System.out.println(state.getResultMessage());
        System.out.println(state.getCheckDT());

    }

    @Test
    public void getChargeInfo_TEST_실명() throws PopbillException {

        ChargeInfo chrgInfo = accountCheckService.getChargeInfo("1234567890", "실명");

        System.out.println(chrgInfo.getChargeMethod());
        System.out.println(chrgInfo.getRateSystem());
        System.out.println(chrgInfo.getUnitCost());

    }

    @Test
    public void getUnitCost_TEST_실명() throws PopbillException {

        float UnitCost = accountCheckService.getUnitCost("1234567890", "실명");

        System.out.println(UnitCost);
    }

    @Test
    public void CheckDepositorInfo() throws PopbillException {

        DepositorCheckInfo state = accountCheckService.CheckDepositorInfo("1234567890", "1245",
                "1234567890", "P", "881211");

        System.out.println(state.getAccountName());
        System.out.println(state.getAccountNumber());
        System.out.println(state.getBankCode());
        System.out.println(state.getCheckDate());
        System.out.println(state.getIdentityNum());
        System.out.println(state.getIdentityNumType());
        System.out.println(state.getResult());
        System.out.println(state.getResultCode());
        System.out.println(state.getResultMessage());
        System.out.println(state.getCheckDT());

    }

    @Test
    public void QuitMemberTest() throws PopbillException {

        String CorpNum = "0000007006";
        String QuitReason = "(테스트) 회원 탈퇴 테스트";
        String UserID = "test_7006";
        Response response = accountCheckService.quitMember(CorpNum, QuitReason, UserID);

        System.out.println("Code: " + response.getCode());
        System.out.println("Message: " + response.getMessage());
    }

    @Test
    public void QuitMemberTest_wo_UserID() throws PopbillException {
        String CorpNum = "0000007007";
        String QuitReason = "(테스트) 회원 탈퇴 테스트";
        Response response = accountCheckService.quitMember(CorpNum, QuitReason);

        System.out.println("Code: " + response.getCode());
        System.out.println("Message: " + response.getMessage());
    }

    @Test
    public void GetRefundInfoTest() throws PopbillException {

        String CorpNum = "1234567890";
        String RefundCode = "023040000017";
        String UserID = "testkorea";
        RefundHistory refundHistory =
                accountCheckService.getRefundInfo(CorpNum, RefundCode, UserID);
        System.out.println("reqDT: " + refundHistory.getReqDT());
        System.out.println("requestPoint: " + refundHistory.getRequestPoint());
        System.out.println("accountBank: " + refundHistory.getAccountBank());
        System.out.println("accountNum: " + refundHistory.getAccountNum());
        System.out.println("accountName: " + refundHistory.getAccountName());
        System.out.println("state: " + refundHistory.getState());
        System.out.println("reason: " + refundHistory.getReason());
    }

    @Test
    public void GetRefundInfoTest_wo_RefundCode() throws PopbillException {

        String CorpNum = "1234567890";
        String RefundCode = "023040000017";
        String UserID = "testkorea";
        RefundHistory refundHistory =
                accountCheckService.getRefundInfo(CorpNum, RefundCode, UserID);
        System.out.println("reqDT: " + refundHistory.getReqDT());
        System.out.println("requestPoint: " + refundHistory.getRequestPoint());
        System.out.println("accountBank: " + refundHistory.getAccountBank());
        System.out.println("accountNum: " + refundHistory.getAccountNum());
        System.out.println("accountName: " + refundHistory.getAccountName());
        System.out.println("state: " + refundHistory.getState());
        System.out.println("reason: " + refundHistory.getReason());
    }

    @Test
    public void GetRefundInfoTest_wo_UserID() throws PopbillException {
        String CorpNum = "1234567890";
        String RefundCode = "023040000017";
        RefundHistory refundHistory = accountCheckService.getRefundInfo(CorpNum, RefundCode);

        System.out.println("reqDT: " + refundHistory.getReqDT());
        System.out.println("requestPoint: " + refundHistory.getRequestPoint());
        System.out.println("accountBank: " + refundHistory.getAccountBank());
        System.out.println("accountNum: " + refundHistory.getAccountNum());
        System.out.println("accountName: " + refundHistory.getAccountName());
        System.out.println("state: " + refundHistory.getState());
        System.out.println("reason: " + refundHistory.getReason());
    }

    @Test
    public void GetRefundableBalanceTest() throws PopbillException {
        String CorpNum = "1234567890";
        String UserID = "testkorea";
        double balance = accountCheckService.getRefundableBalance(CorpNum, UserID);
        System.out.println("balance: " + balance);
    }

    @Test
    public void GetRefundableBalanceTest_wo_UserID() throws PopbillException {
        String CorpNum = "1234567890";
        double balance = accountCheckService.getRefundableBalance(CorpNum);
        System.out.println("balance: " + balance);
    }

}



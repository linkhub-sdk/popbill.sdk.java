package com.popbill.api.easyfin.test;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.popbill.api.EasyFinBankService;
import com.popbill.api.FlatRateState;
import com.popbill.api.PopbillException;
import com.popbill.api.Response;
import com.popbill.api.easyfin.EasyFinBankAccount;
import com.popbill.api.easyfin.EasyFinBankAccountForm;
import com.popbill.api.easyfin.EasyFinBankJobState;
import com.popbill.api.easyfin.EasyFinBankSearchResult;
import com.popbill.api.easyfin.EasyFinBankServiceImp;
import com.popbill.api.easyfin.EasyFinBankSummary;

public class EasyFinBankServiceTEST {
    private final String testLinkID = "TESTER";
    private final String testSecretKey = "SwWxqU+0TErBXy/9TVjIPEnI0VTUMMSQZtJf3Ed8q3I=";

    private EasyFinBankService easyFinBankService;
    
    public EasyFinBankServiceTEST() {
        EasyFinBankServiceImp service = new EasyFinBankServiceImp();

        service.setLinkID(testLinkID);
        service.setSecretKey(testSecretKey);
        service.setTest(true);
        
        easyFinBankService = service;
    }
    
    
    @Test 
    public void registBankAccount() throws PopbillException{
        
        EasyFinBankAccountForm bankInfo = new EasyFinBankAccountForm();
        bankInfo.setAccountName("별칭");
        bankInfo.setAccountNumber("1012051447401");
        bankInfo.setAccountPWD("");
        bankInfo.setAccountType("법인");
        bankInfo.setBankCode("12345");
        bankInfo.setIdentityNumber("6798700433");
        
        
        Response response = easyFinBankService.registBankAccount("1234567890", bankInfo);
        
        assertNotNull(response);
        
        System.out.println("\n\n======== registBankAccount Response ========");
        
        System.out.println(response.getCode());
        System.out.println(response.getMessage());
    }
    
    @Test 
    public void revokeCloseBankAccount() throws PopbillException{
        
        String BankCode = "0020";
        String AccountNumber = "10020875456";
        
        Response response = easyFinBankService.revokeCloseBankAccount("1234567890", BankCode, AccountNumber );
        
        assertNotNull(response);
        
        System.out.println("\n\n======== revokeCloseBankAccount Response ========");
        
        System.out.println(response.getCode());
        System.out.println(response.getMessage());
    }
    
    @Test 
    public void closeBankAccount() throws PopbillException{
        
        String BankCode = "0020";
        String AccountNumber = "1002057875456";
        String CloseType = "중도";
        
        Response response = easyFinBankService.closeBankAccount("1234567890", BankCode, AccountNumber, CloseType);
        
        assertNotNull(response);
        
        System.out.println("\n\n======== closeBankAccount Response ========");
        
        System.out.println(response.getCode());
        System.out.println(response.getMessage());
    }
    
    @Test 
    public void updateBankAccount() throws PopbillException{
        
        EasyFinBankAccountForm bankInfo = new EasyFinBankAccountForm();
        
        bankInfo.setBankCode("0020");
        bankInfo.setAccountNumber("1002057875456");
        bankInfo.setAccountPWD("");
        bankInfo.setAccountName("별칭_1234");
        bankInfo.setMemo("memo1356");
        
        Response response = easyFinBankService.updateBankAccount("1234567890", bankInfo);
        
        assertNotNull(response);
        
        System.out.println("\n\n======== updateBankAccount Response ========");
        
        System.out.println(response.getCode());
        System.out.println(response.getMessage());
    }
    
    @Test
    public void deleteBankAccount() throws PopbillException{
        
        String bankCode = "0032";
        String AccountNumber = "";
        
        Response response = easyFinBankService.deleteBankAccount("1234567890", bankCode, AccountNumber, "testkorea");
        
        assertNotNull(response);
        System.out.println(response.getCode());
        System.out.println(response.getMessage());
    }
    
    @Test 
    public void getBankAccountMgtURL() throws PopbillException{
        String url = easyFinBankService.getBankAccountMgtURL("1234567890", "testkorea");
        
        assertNotNull(url);
        
        System.out.println("\n\n======== getBankAccountMgtURL Response ========");
        
        System.out.println(url);
    }
    
    
    @Test
    public void getBankAccountInfo() throws PopbillException {
        EasyFinBankAccount accountList = easyFinBankService.getBankAccountInfo("1234567890", "0020", "1002057875456");
        assertNotNull(accountList);
        
        System.out.println("\n\n======== getBankAccountInfo Response ========");
        
        if( accountList != null) {
        
            System.out.println(accountList.getAccountNumber());
            System.out.println(accountList.getAccountName());
            System.out.println(accountList.getBankCode());
            System.out.println(accountList.getAccountType());
            System.out.println(accountList.getState());
            System.out.println(accountList.getRegDT());
            System.out.println(accountList.getMemo());
            System.out.println(accountList.getContractDT());
            System.out.println(accountList.getBaseDate());
            System.out.println(accountList.getUseEndDate());
            System.out.println(accountList.getContractState());
            System.out.println(accountList.getCloseRequestYN());
            System.out.println(accountList.getUseRestrictYN());
            System.out.println(accountList.getUnPaidYN());
            
        }
    }
    
    @Test
    public void listBankAccount() throws PopbillException {
        EasyFinBankAccount[] accountList = easyFinBankService.listBankAccount("1234567890", "testkorea");
        assertNotNull(accountList);
        
        System.out.println("\n\n======== listBankAccount Response ========");
        
        for ( int i=0; i < accountList.length; i++ ) {
            System.out.println("\n======== ["+(i+1)+"] listBankAccount Response ========");
            System.out.println(accountList[i].getAccountNumber());
            System.out.println(accountList[i].getAccountName());
            System.out.println(accountList[i].getBankCode());
            System.out.println(accountList[i].getAccountType());
            System.out.println(accountList[i].getState());
            System.out.println(accountList[i].getRegDT());
            System.out.println(accountList[i].getMemo());
            System.out.println(accountList[i].getContractDT());
            System.out.println(accountList[i].getBaseDate());
            System.out.println(accountList[i].getUseEndDate());
            System.out.println(accountList[i].getContractState());
            System.out.println(accountList[i].getCloseRequestYN());
            System.out.println(accountList[i].getUseRestrictYN());
            System.out.println(accountList[i].getUnPaidYN());
        }
    }
    
    @Test
    public void requestJob() throws PopbillException {
        String AccountNumber = "74620246488";
        String BankCode = "0023";
        String SDate = "20210505";
        String EDate = "20210506";
        String jobID = easyFinBankService.requestJob("1234567890", BankCode, AccountNumber, SDate, EDate);
        
        assertNotNull(jobID);
        System.out.println("\n\n======== requestJob Response ========");
        System.out.println(jobID);
                
    }
    
    
    @Test
    public void getJobState() throws PopbillException {
        EasyFinBankJobState jobState = easyFinBankService.getJobState("1234567890", "021050616000000050");
        
        assertNotNull(jobState);
        
        System.out.println("\n\n======== getJobState Response ========");
        System.out.println(jobState.getJobID());
        System.out.println(jobState.getJobState());
        System.out.println(jobState.getStartDate());
        System.out.println(jobState.getEndDate());
        System.out.println(jobState.getErrorCode());
        System.out.println(jobState.getErrorReason());
        System.out.println(jobState.getJobStartDT());
        System.out.println(jobState.getJobEndDT());
        System.out.println(jobState.getRegDT());
    }
    
    @Test
    public void listActiveJob() throws PopbillException {
        EasyFinBankJobState[] listJobState = easyFinBankService.listActiveJob("1234567890");
        
        assertNotNull(listJobState);
        for ( int i=0; i < listJobState.length; i++ ) {
            System.out.println("\n======== ["+(i+1)+"] listActvieJob Response ========");
            System.out.println(listJobState[i].getJobID());
            System.out.println(listJobState[i].getJobState());
            System.out.println(listJobState[i].getStartDate());
            System.out.println(listJobState[i].getEndDate());
            System.out.println(listJobState[i].getErrorCode());
            System.out.println(listJobState[i].getErrorReason());
            System.out.println(listJobState[i].getJobStartDT());
            System.out.println(listJobState[i].getJobEndDT());
            System.out.println(listJobState[i].getRegDT());
        }
    }
    
    @Test
    public void search() throws PopbillException{
        String[] TradeType = {"I","O"};
        String SearchString = "";
        Integer Page = 1;
        Integer PerPage = 10;
        String Order = "D";
        
        EasyFinBankSearchResult result = easyFinBankService.search("1234567890", "020080711000000001", TradeType, SearchString, Page, PerPage, Order);
        
        
        assertNotNull ( result ) ;
        
        System.out.println("\n\n======== Search Result ========");
        System.out.println(result.getCode());
        System.out.println(result.getMessage());
        System.out.println(result.getPageCount());
        System.out.println(result.getPageNum());
        System.out.println(result.getPerPage());
        System.out.println(result.getTotal());
        System.out.println(result.getLastScrapDT());
        
        for ( int i=0; i<result.getList().size(); i++ ) {
            System.out.println("\n========["+ (i+1) +"] Search Result List Detail ========");
            System.out.println(result.getList().get(i).getTid());
            System.out.println(result.getList().get(i).getAccountID());
            System.out.println(result.getList().get(i).getTrdate());
            System.out.println(result.getList().get(i).getTrserial());
            System.out.println(result.getList().get(i).getTrdt());
            System.out.println(result.getList().get(i).getAccIn());
            System.out.println(result.getList().get(i).getAccOut());
            System.out.println(result.getList().get(i).getBalance());
            System.out.println(result.getList().get(i).getRemark1());
            System.out.println(result.getList().get(i).getRemark2());
            System.out.println(result.getList().get(i).getRemark3());
            System.out.println(result.getList().get(i).getRemark4());
            System.out.println(result.getList().get(i).getRegDT());
            System.out.println(result.getList().get(i).getMemo());
        }
    }
    
    @Test
    public void summary() throws PopbillException{
        String[] TradeType = {"I","O"};
        String SearchString = "";
        
        EasyFinBankSummary result = easyFinBankService.summary("1234567890", "019121811000000004", TradeType, SearchString);
        
        
        assertNotNull ( result ) ;
        
        System.out.println("\n\n======== summary Result ========");
        System.out.println(result.getCount());
        System.out.println(result.getCntAccIn());
        System.out.println(result.getCntAccOut());
        System.out.println(result.getTotalAccIn());
        System.out.println(result.getTotalAccOut());
        
    }
    
    @Test 
    public void saveMemo() throws PopbillException {
        String Memo = "wow";
        String TID = "01912181100000000120191210000003";
        
        Response result =  easyFinBankService.saveMemo("1234567890", TID, Memo);
        
        assertNotNull ( result ) ;
    }
    
    @Test
    public void GetFlatRatePopUpURL() throws PopbillException {
        String url = easyFinBankService.getFlatRatePopUpURL("1234567890", "testkorea");
        
        assertNotNull(url);
        
        System.out.println("\n\n======== GetFlatRatePopUpURL Response ========");
        
        System.out.println(url);
    }
    
    @Test
    public void GetFlatRateState() throws PopbillException {
        
        String BankCode = "0048";
        String AccountNumber = "131020538645";
        
        FlatRateState rateInfo = easyFinBankService.getFlatRateState("1234567890", BankCode, AccountNumber);
        
        assertNotNull(rateInfo);
        
        System.out.println("\n\n======== GetFlatRateState ========");
        System.out.println(rateInfo.getReferenceID());
        System.out.println(rateInfo.getContractDT());
        System.out.println(rateInfo.getUseEndDate());
        System.out.println(rateInfo.getBaseDate());
        System.out.println(rateInfo.getState());
        System.out.println(rateInfo.getCloseRequestYN());
        System.out.println(rateInfo.getUseRestrictYN());
        System.out.println(rateInfo.getCloseOnExpired());
        System.out.println(rateInfo.getUnPaidYN());
    }
}
















package com.popbill.api.statement.test;

import static org.junit.Assert.assertNotNull;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.popbill.api.AttachedFile;
import com.popbill.api.ChargeInfo;
import com.popbill.api.EmailSendConfig;
import com.popbill.api.PopbillException;
import com.popbill.api.Response;
import com.popbill.api.SMTIssueResponse;
import com.popbill.api.StatementService;
import com.popbill.api.statement.Statement;
import com.popbill.api.statement.StatementDetail;
import com.popbill.api.statement.StatementInfo;
import com.popbill.api.statement.StatementLog;
import com.popbill.api.statement.StatementServiceImp;
import com.popbill.api.statement.StmtSearchResult;

public class StatementServiceTEST {
    private final String testLinkID = "TESTER";
    private final String testSecretKey = "SwWxqU+0TErBXy/9TVjIPEnI0VTUMMSQZtJf3Ed8q3I=";
    
    private StatementService statementService;
    
    public StatementServiceTEST(){
        StatementServiceImp service = new StatementServiceImp();
        
        service.setLinkID(testLinkID);
        service.setSecretKey(testSecretKey);
        service.setTest(true);    
        
        statementService = service;
    }
    
    @Test
    public void getChargeInfo_TEST() throws PopbillException {
        ChargeInfo chrgInfo = statementService.getChargeInfo("1234567890", 121);
        
        assertNotNull(chrgInfo);
        
        System.out.println(chrgInfo.getChargeMethod());
        System.out.println(chrgInfo.getRateSystem());
        System.out.println(chrgInfo.getUnitCost());
        
    }
    
    @Test
    public void getUnitCost_TEST() throws PopbillException {
        float UnitCost = statementService.getUnitCost("1234567890", 121);
        System.out.println(UnitCost);
    }
    
    @Test
    public void checkMgtKeyInUse_TEST() throws PopbillException{
        boolean useYN = statementService.checkMgtKeyInUse("1234567890", 121, "201503016-01");
        System.out.println(useYN);
    }
    
    @Test
    public void getURL_TEST() throws PopbillException{
        String url = statementService.getURL("1234567890", "testkorea", "TBOX");
        assertNotNull(url);
        System.out.println(url);
    }
    
    @Test 
    public void register_TEST() throws PopbillException{
        
        Statement statement = new Statement();
        
        statement.setWriteDate("20170306");
        statement.setPurposeType("영수");
        statement.setTaxType("과세");
        statement.setFormCode("");
        statement.setItemCode((short) 121);
        statement.setMgtKey("20230112-MVC007");
        
        statement.setSenderCorpNum("1234567890");
        statement.setSenderCorpName("공급자 상호");
        statement.setSenderAddr("공급자 주소");
        statement.setSenderCEOName("공급자 대표자 성명");
        statement.setSenderTaxRegID("");
        statement.setSenderBizClass("업종");
        statement.setSenderBizType("업태");
        statement.setSenderContactName("공급자 담당자명");
        statement.setSenderEmail("test@test.com");
        statement.setSenderTEL("070-7070-0707");
        
        statement.setSenderHP("010-000-2222");
        statement.setReceiverCorpNum("8888888888");
        statement.setReceiverCorpName("공급받는자 상호");
        statement.setReceiverCEOName("공급받는자 대표자 성명");
        statement.setReceiverAddr("공급받는자 주소");
        statement.setReceiverBizClass("공급받는자 업종");
        statement.setReceiverBizType("공급받는자 업태");
        statement.setReceiverContactName("공급받는자 담당자명");
        statement.setReceiverEmail("lshk955@naver.com");

        statement.setSupplyCostTotal("200000");         //필수 공급가액 합계
        statement.setTaxTotal("20000");                 //필수 세액 합계
        statement.setTotalAmount("220000");             //필수 합계금액.  공급가액 + 세액

        statement.setSerialNum("123");                 //기재상 일련번호 항목
        statement.setRemark1("비고1");
        statement.setRemark2("비고2");
        statement.setRemark3("비고3");

        statement.setBusinessLicenseYN(false); //사업자등록증 이미지 첨부시 설정.
        statement.setBankBookYN(false);         //통장사본 이미지 첨부시 설정.
        
                
        Map<String, String> propertyBag = new HashMap<String, String>();
        
        propertyBag.put("Balance", "15000");
        propertyBag.put("Deposit", "5000");
        propertyBag.put("CBalance", "20000");
        
        statement.setDetailList(new ArrayList<StatementDetail>());
        StatementDetail detail = new StatementDetail();
        detail.setSerialNum((short) 1);
        detail.setItemName("품명");
        detail.setPurchaseDT("20150317");
        detail.setQty("1");
        detail.setSupplyCost("200000");
        detail.setTax("20000");
        
        statement.getDetailList().add(detail);
        
        statement.setPropertyBag(propertyBag);
        Response response = statementService.register("1234567890", statement);
        System.out.println(response.getMessage());
        
        assertNotNull(response);
        
    }
    
    @Test
    public void attachFile_TEST() throws PopbillException, IOException {
        InputStream FileData = new FileInputStream(
                "/Users/John/Desktop/test.jpg");
        
        Response response = statementService.attachFile("1234567890", 121, "20170306-03", "test.jpg", FileData);
        
        FileData.close();
        
        assertNotNull(response);
        
        System.out.println(response.getMessage());    
    }
    
    @Test 
    public void getFiles_TEST() throws PopbillException {
        
        AttachedFile[] files = statementService.getFiles("1234567890", 121, "20200818-001");
        
        assertNotNull(files);
        
        System.out.println(files.length+" "+files[0].getAttachedFile());
        
        Response response = statementService.deleteFile("1234567890", 121, "20150318-01", files[0].getAttachedFile(), "testkorea");
        
        System.out.println(response.getMessage());
    }
    
    @Test
    public void deleteFiles_TEST() throws PopbillException {
        
        String FileID = "10223612-BAE6-491A-9496-62705E978DA5.PBF";
        
        Response response = statementService.deleteFile("1234567890", 121, "20170306-03", FileID);
        
        assertNotNull(response);
        
        System.out.println(response.getMessage());
        
    }
    
    @Test
    public void update_TEST() throws PopbillException{
        
        Statement statement = new Statement();
        
        statement.setWriteDate("20150318");
        statement.setPurposeType("영수");
        statement.setTaxType("과세");
        statement.setFormCode("");
        statement.setItemCode((short) 121);
        statement.setMgtKey("20150318-01");
        statement.setSenderCorpNum("1234567890");
        statement.setSenderCorpName("공급자 상호_수정");
        statement.setSenderAddr("공급자 주소_수정");
        statement.setSenderCEOName("공급자 대표자 성명");
        statement.setSenderTaxRegID("");
        statement.setSenderBizClass("업종");
        statement.setSenderBizType("업태");
        statement.setSenderContactName("공급자 담당자명");
        statement.setSenderEmail("test@test.com");
        statement.setSenderTEL("070-7070-0707");
        statement.setSenderHP("010-000-2222");
        
        statement.setReceiverCorpNum("8888888888");
        statement.setReceiverCorpName("공급받는자 상호");
        statement.setReceiverCEOName("공급받는자 대표자 성명");
        statement.setReceiverAddr("공급받는자 주소");
        statement.setReceiverBizClass("공급받는자 업종");
        statement.setReceiverBizType("공급받는자 업태");
        statement.setReceiverContactName("공급받는자 담당자명");
        statement.setReceiverEmail("test@receiver.com");

        statement.setSupplyCostTotal("200000");         //필수 공급가액 합계
        statement.setTaxTotal("20000");                 //필수 세액 합계
        statement.setTotalAmount("220000");             //필수 합계금액.  공급가액 + 세액

        statement.setSerialNum("123");                 //기재상 일련번호 항목
        statement.setRemark1("비고1");
        statement.setRemark2("비고2");
        statement.setRemark3("비고3");

        statement.setBusinessLicenseYN(false); //사업자등록증 이미지 첨부시 설정.
        statement.setBankBookYN(false);         //통장사본 이미지 첨부시 설정.
        
        
        statement.setDetailList(new ArrayList<StatementDetail>());
        StatementDetail detail = new StatementDetail();
        detail.setSerialNum((short) 1);
        detail.setItemName("품명");
        detail.setPurchaseDT("20150317");
        detail.setQty("1");
        detail.setSupplyCost("200000");
        detail.setTax("20000");
        
        statement.getDetailList().add(detail);
        
        Map<String, String> propertyBag = new HashMap<String, String>();
        propertyBag.put("Balance", "15000");
        propertyBag.put("Deposit", "5000");
        propertyBag.put("CBalance", "20000");
        
        statement.setPropertyBag(propertyBag);
        
        Response response = statementService.update("1234567890", 121, "20150318-01", statement, "testkorea");
        
        System.out.println(response.getMessage());
                
    }
    
    @Test
    public void issue_TEST() throws PopbillException{
        Response response = statementService.issue("1234567890", 121, "20230112-MVC007", "발행메모", "테스트메일", null);
        
        System.out.println(response.getMessage());
    }
    

    
    @Test
    public void sendEmail_TEST() throws PopbillException {
        Response response = statementService.sendEmail("1234567890", 121, "20150318-01", "test@test.com", null);
        System.out.println(response.getMessage());
    }
    
    @Test
    public void sendSMS_TEST() throws PopbillException {
        Response response = statementService.sendSMS("1234567890", 121, "20150318-01", "07043042991", "010111222", "문자메시지 테스트");
        
        System.out.println(response.getMessage());
    }
    
    @Test
    public void sendFAX_TEST() throws PopbillException {
        Response response = statementService.sendFAX("1234567890", 121, "20170306-03", "07043042991", "010111222");
        
        System.out.println(response.getMessage());
    }
    
    @Test
    public void getDetailInfo_TEST() throws PopbillException {
        Statement statement = statementService.getDetailInfo("1234567890", 121, "2019015-01");
        
        assertNotNull(statement);
        System.out.println(statement.getPropertyBag());
        System.out.println(statement.getDetailList().get(0).getSpare20());
    }
    @Test
    public void getInfo_TEST() throws PopbillException {
        StatementInfo statementInfo = statementService.getInfo("1234567890", 121, "20160112-01");
        
        assertNotNull(statementInfo);
        System.out.println("Get Info : " +statementInfo.getInvoiceNum()+" "+statementInfo.getStateCode()+" "+statementInfo.isSenderPrintYN());
    }
    @Test
    public void getInfos_TEST() throws PopbillException {
        
        String[] MgtKeyList = new String[]{"20150316-01","20150317-01","20150318-01"};
        
        StatementInfo[] infoList = statementService.getInfos("1234567890", 121, MgtKeyList);
        
        assertNotNull(infoList);
        System.out.println(infoList.length);
    }
    
    @Test
    public void getLogs_TEST() throws PopbillException {
        StatementLog[] logs = statementService.getLogs("1234567890", 121, "20150318-01");
        assertNotNull(logs);
        
        System.out.println(logs.length+" "+logs[0].getLog());
    }
    
    @Test
    public void getPopUpURL_TEST() throws PopbillException {
        String url = statementService.getPopUpURL("1234567890", 121, "20170306-01");
        
        assertNotNull(url);
        
        System.out.println(url);
    }
    
    @Test
    public void getPrintURL_TEST() throws PopbillException {
        String url = statementService.getPrintURL("1234567890", 121, "20170306-01");
        
        assertNotNull(url);
        
        System.out.println(url);
    }
    
    @Test
    public void getEPrintURL_TEST() throws PopbillException {
        String url = statementService.getEPrintURL("1234567890", 121, "20170306-01");
        
        assertNotNull(url);
        
        System.out.println(url);
    }
    
    @Test
    public void getMailURL_TEST() throws PopbillException {
        String url = statementService.getMailURL("1234567890", 121, "20170306-02");
        
        assertNotNull(url);
        
        System.out.println(url);
    }
    
    @Test
    public void getMassPrintURL_TEST() throws PopbillException {
        String[] MgtKeyList = new String[] {"20150317-01","20150316-06","20150316-04"};
        
        String url = statementService.getMassPrintURL("1234567890", 121, MgtKeyList);
        
        assertNotNull(url);
        
        System.out.println(url);
    }
    
    @Test
    public void getSealURL_TEST() throws PopbillException {
        String url = statementService.getSealURL("1234567890", "testkorea");
        
        assertNotNull(url);
        
        System.out.println(url);
    }
    
    @Test
    public void cancel_TEST() throws PopbillException {
        Response response = statementService.cancel("1234567890", 121, "20150318-01", "발행취소메모");
        
        System.out.println(response.getMessage());
    }
    
    @Test
    public void delete_TEST() throws PopbillException{
        
        Response response = statementService.delete("1234567890", 121, "20150318-01");
        
        System.out.println(response.getMessage());
    }
    
    
    @Test
    public void FAXSend_TEST() throws PopbillException{
        Statement statement = new Statement();
        
        statement.setWriteDate("20170306");
        statement.setPurposeType("영수");
        statement.setTaxType("과세");
        statement.setFormCode("");
        statement.setItemCode((short) 121);
        statement.setMgtKey("20170306-06");
        statement.setSenderCorpNum("1234567890");
        statement.setSenderCorpName("공급자 상호");
        statement.setSenderAddr("공급자 주소");
        statement.setSenderCEOName("공급자 대표자 성명");
        statement.setSenderTaxRegID("");
        statement.setSenderBizClass("업종");
        statement.setSenderBizType("업태");
        statement.setSenderContactName("공급자 담당자명");
        statement.setSenderEmail("test@test.com");
        statement.setSenderTEL("070-7070-0707");
        
        statement.setSenderHP("010-000-2222");
        statement.setReceiverCorpNum("8888888888");
        statement.setReceiverCorpName("공급받는자 상호");
        statement.setReceiverCEOName("공급받는자 대표자 성명");
        statement.setReceiverAddr("공급받는자 주소");
        statement.setReceiverBizClass("공급받는자 업종");
        statement.setReceiverBizType("공급받는자 업태");
        statement.setReceiverContactName("공급받는자 담당자명");
        statement.setReceiverEmail("test@receiver.com");

        statement.setSupplyCostTotal("200000");         //필수 공급가액 합계
        statement.setTaxTotal("20000");                 //필수 세액 합계
        statement.setTotalAmount("220000");             //필수 합계금액.  공급가액 + 세액

        statement.setSerialNum("123");                 //기재상 일련번호 항목
        statement.setRemark1("비고1");
        statement.setRemark2("비고2");
        statement.setRemark3("비고3");

        statement.setBusinessLicenseYN(false); //사업자등록증 이미지 첨부시 설정.
        statement.setBankBookYN(false);         //통장사본 이미지 첨부시 설정.
        
                
        Map<String, String> propertyBag = new HashMap<String, String>();
        
        propertyBag.put("Balance", "15000");
        propertyBag.put("Deposit", "5000");
        propertyBag.put("CBalance", "20000");
        
        statement.setDetailList(new ArrayList<StatementDetail>());
        StatementDetail detail = new StatementDetail();
        detail.setSerialNum((short) 1);
        detail.setItemName("품명");
        detail.setPurchaseDT("20150317");
        detail.setQty("1");
        detail.setSupplyCost("200000");
        detail.setTax("20000");
        
        statement.getDetailList().add(detail);
        
        statement.setPropertyBag(propertyBag);
        String response = statementService.FAXSend("1234567890", statement, "07043042991", "070111222");
        System.out.println(response);
        
        assertNotNull(response);
    }
    
    @Test 
    public void RegistIssue_TEST() throws PopbillException{
        Statement statement = new Statement();
        
        statement.setWriteDate("20210625");
        statement.setPurposeType("영수");
        statement.setTaxType("과세");
        statement.setFormCode("");
        statement.setItemCode((short) 121);
        statement.setMgtKey("20210625-JAVA015");
        statement.setSenderCorpNum("1234567890");
        statement.setSenderCorpName("공급자 상호");
        statement.setSenderAddr("공급자 주소");
        statement.setSenderCEOName("공급자 대표자 성명");
        statement.setSenderTaxRegID("");
        statement.setSenderBizClass("업종");
        statement.setSenderBizType("업태");
        statement.setSenderContactName("공급자 담당자명");
        statement.setSenderEmail("test@test.com");
        statement.setSenderTEL("070-7070-0707");
        
        statement.setSenderHP("010-000-2222");
        statement.setReceiverCorpNum("8888888888");
        statement.setReceiverCorpName("JAVA 상호");
        statement.setReceiverCEOName("공급받는자 대표자 성명");
        statement.setReceiverAddr("공급받는자 주소");
        statement.setReceiverBizClass("공급받는자 업종");
        statement.setReceiverBizType("공급받는자 업태");
        statement.setReceiverContactName("공급받는자 담당자명");
        statement.setReceiverEmail("");

        statement.setSupplyCostTotal("200000");         //필수 공급가액 합계
        statement.setTaxTotal("20000");                 //필수 세액 합계
        statement.setTotalAmount("220000");             //필수 합계금액.  공급가액 + 세액

        statement.setSerialNum("123");                 //기재상 일련번호 항목
        statement.setRemark1("비고1");
        statement.setRemark2("비고2");
        statement.setRemark3("비고3");

        statement.setBusinessLicenseYN(false); //사업자등록증 이미지 첨부시 설정.
        statement.setBankBookYN(false);         //통장사본 이미지 첨부시 설정.
//        statement.setAutoacceptYN(false);
                
        Map<String, String> propertyBag = new HashMap<String, String>();
        
        propertyBag.put("Balance", "15000");
        propertyBag.put("Deposit", "5000");
        propertyBag.put("CBalance", "20000");
        
        statement.setDetailList(new ArrayList<StatementDetail>());
        StatementDetail detail = new StatementDetail();
        detail.setSerialNum((short) 1);
        detail.setItemName("품명");
        detail.setPurchaseDT("20150317");
        detail.setQty("1");
        detail.setSupplyCost("200000");
        detail.setTax("20000");
        detail.setSpare1("20000");
        detail.setSpare2("20000");
        detail.setSpare3("20000");
        detail.setSpare4("20000");
        detail.setSpare5("20000");
        detail.setSpare6("20000");
        detail.setSpare7("20000");
        detail.setSpare8("20000");
        detail.setSpare9("20000");
        detail.setSpare10("30000");
        detail.setSpare11("30000");
        detail.setSpare12("30000");
        detail.setSpare13("30000");
        detail.setSpare14("30000");
        detail.setSpare15("30000");
        detail.setSpare16("30000");
        detail.setSpare17("30000");
        detail.setSpare18("30000");
        detail.setSpare19("30000");
        detail.setSpare20("30000");
        
        statement.getDetailList().add(detail);
        
        statement.setPropertyBag(propertyBag);
        
        SMTIssueResponse response = statementService.registIssue("1234567890", statement, "전자명세서 즉시발행 메모", "", "메일 제목 테스트");
        System.out.println("[" + response.getCode() + "] " + "[" + response.getMessage() +"] "+ "["+response.getInvoiceNum() + "]");
        
        assertNotNull(response);
    }
    
    @Test
    public void attachStatement_TEST() throws PopbillException {
        int ItemCode = 121;
        String MgtKey = "20160115-05";
        int SubItemCode = 121;
        String SubMgtKey = "20160115-06";
        
        Response response = statementService.attachStatement("1234567890", ItemCode, MgtKey, SubItemCode, SubMgtKey);
        assertNotNull(response);
        
        System.out.println("["+response.getCode()+"] "+ response.getMessage());
        
    }
    
    @Test
    public void deachStatement_TEST() throws PopbillException {
        int ItemCode = 121;
        String MgtKey = "20160115-05";
        int SubItemCode = 121;
        String SubMgtKey = "20160115-06";
        
        Response response = statementService.detachStatement("1234567890", ItemCode, MgtKey, SubItemCode, SubMgtKey);
        assertNotNull(response);
        
        System.out.println("["+response.getCode()+"] "+ response.getMessage());
    }

    @Test
    public void Search_TEST() throws PopbillException {
        String CorpNum = "1234567890";
        String DType = "W";
        String SDate = "20241201";
        String EDate = "20241231";
        String[] State = {"2**", "3**"};
        int[] ItemCode = {121,122,123,124,125,126};
        int Page = 1;
        int PerPage = 50;
        String QString = "";
        String Order = "D";
        
        StmtSearchResult response = statementService.search(CorpNum, DType, SDate, EDate, State, ItemCode, QString, Page, PerPage, Order);
        
        assertNotNull(response);
        System.out.println(response.getList().size());
        
        for (int i=0 ;i < response.getList().size(); i++){
            System.out.println(response.getMessage()+" "+response.getList().get(i).getReceiverCorpNum()+ " "+response.getList().get(i).getReceiverCorpName());
        }
    }
    
    @Test
    public void listEmailConfig_TEST() throws PopbillException{
        
        EmailSendConfig[] configList = statementService.listEmailConfig("1234567890");
        
        assertNotNull(configList);
        
        for(int i=0; i< configList.length; i++){

            System.out.print(configList[i].getEmailType()+ " "+ configList[i].getSendYN());
            System.out.println();
        }
    }
    
    @Test
    public void updateEmailConfig_TEST() throws PopbillException{
        
        String CorpNum = "1234567890";
        String EmailType = "SMT_CANCEL";
        Boolean SendYN = true;
        String UserID = "testkorea";
        
        Response response = statementService.updateEmailConfig(CorpNum, EmailType, SendYN, UserID);
        assertNotNull(response);
        
        System.out.println("["+response.getCode() +"] " + response.getMessage());
    }    
}

















    
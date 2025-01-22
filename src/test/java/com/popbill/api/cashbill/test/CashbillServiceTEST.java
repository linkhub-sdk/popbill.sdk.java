package com.popbill.api.cashbill.test;

import com.popbill.api.BulkResponse;
import com.popbill.api.CBIssueResponse;
import com.popbill.api.CashbillService;
import com.popbill.api.ChargeInfo;
import com.popbill.api.EmailSendConfig;
import com.popbill.api.PopbillException;
import com.popbill.api.Response;
import com.popbill.api.cashbill.BulkCashbillIssueResult;
import com.popbill.api.cashbill.BulkCashbillResult;
import com.popbill.api.cashbill.CBSearchResult;
import com.popbill.api.cashbill.Cashbill;
import com.popbill.api.cashbill.CashbillInfo;
import com.popbill.api.cashbill.CashbillServiceImp;
import org.junit.Test;

import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class CashbillServiceTEST {
    private final String testLinkID = "TESTER";
    private final String testSecretKey = "SwWxqU+0TErBXy/9TVjIPEnI0VTUMMSQZtJf3Ed8q3I=";

    private CashbillService cashbillService;

    private final SecureRandom srand = new SecureRandom();

    public CashbillServiceTEST() {
        CashbillServiceImp service = new CashbillServiceImp();

        service.setLinkID(testLinkID);
        service.setSecretKey(testSecretKey);
        service.setTest(true);
        service.setUseLocalTimeYN(false);
        cashbillService = service;
    }

    @Test
    public void RegistIssueCN_TEST() throws PopbillException {

        Cashbill cashbill = new Cashbill();

        cashbill.setConfirmNum("K09000001");
        cashbill.setMgtKey("20240123_test_001" );
        cashbill.setTradeType("승인거래");
        cashbill.setTradeOpt("일반");
        cashbill.setFranchiseCorpNum("1234567890");
        cashbill.setFranchiseTaxRegID("0001");
        cashbill.setFranchiseCorpName("발행자 상호");
        cashbill.setFranchiseCEOName("발행자 대표자");
        cashbill.setFranchiseAddr("발행자 주소");
        cashbill.setFranchiseTEL("07075103710");

        cashbill.setIdentityNum("0100001234");
        cashbill.setCustomerName("고객명");
        cashbill.setItemName("상품명");
        cashbill.setOrderNumber("주문번호");
        cashbill.setEmail("code@test.co.kr");
        cashbill.setHp("01011112222");
        cashbill.setFax("07075103710");
        cashbill.setServiceFee("0");
        cashbill.setSupplyCost("10000");
        cashbill.setTax("1000");
        cashbill.setTotalAmount("11000");
        cashbill.setTradeUsage("소득공제용");
        cashbill.setTaxationType("과세");
        cashbill.setSmssendYN(false);

        CBIssueResponse response = cashbillService.registIssueCN("1234567890", cashbill, "메모 입니다. Getinfo [StateMemo] 필드", "", "메일 제목 테스트");
        assertNotNull(response);
        System.out.println("[" + response.getCode() + "] " + response.getMessage());
        System.out.println(response.getConfirmNum() + ", " + response.getTradeDate());
    }

    @Test
    public void bulkSubmitCN_TEST() throws PopbillException {
        List<Cashbill> cashbillList = new ArrayList<Cashbill>();
        String submitID = "20240123_JAVA_001";

        for (int i = 0; i < 5; i++) {
            Cashbill cashbill = new Cashbill();

            StringBuilder suffix = new StringBuilder();
            for (int j = 0; j < 5; j++) {
                suffix.append("").append(srand.nextInt(10));
            }

            cashbill.setConfirmNum("K090" + suffix);
            cashbill.setMgtKey("20240123_test_00" + i);
            cashbill.setTradeType("승인거래");
            cashbill.setTradeOpt("도서공연");
            cashbill.setFranchiseCorpNum("1234567890");
            cashbill.setFranchiseCorpName("발행자 상호");
            cashbill.setFranchiseCEOName("발행자 대표자");
            cashbill.setIdentityNum("0100001234");
            cashbill.setCustomerName("");
            cashbill.setEmail("test@test.com");
            cashbill.setItemName("상품명");
            cashbill.setOrderNumber("주문번호");
            cashbill.setServiceFee("0");
            cashbill.setSupplyCost("5000");
            cashbill.setTax("5000");
            cashbill.setTotalAmount("10000");
            cashbill.setTradeUsage("소득공제용");
            cashbill.setTaxationType("과세");
            cashbill.setSmssendYN(false);

            cashbillList.add(cashbill);
        }

        BulkResponse bulkResponse = cashbillService.bulkSubmitCN("1234567890", submitID, cashbillList);


        System.out.println(bulkResponse.getCode());
        System.out.println(bulkResponse.getMessage());
        System.out.println(bulkResponse.getReceiptID());

        assertNotNull(bulkResponse);
        assertEquals(bulkResponse.getCode(), 1);

    }

    @Test
    public void getChargeInfo_TEST() throws PopbillException {
        ChargeInfo chrgInfo = cashbillService.getChargeInfo("1234567890");

        assertNotNull(chrgInfo);
        System.out.println(chrgInfo.getChargeMethod());
        System.out.println(chrgInfo.getRateSystem());
        System.out.println(chrgInfo.getUnitCost());
    }

    @Test
    public void getUnitCost_TEST() throws PopbillException {
        float UnitCost = cashbillService.getUnitCost("1234567890");

        assertNotNull(UnitCost);
        System.out.println(UnitCost);
    }


    @Test
    public void getURL_TEST() throws PopbillException {
        String url = cashbillService.getURL("1234567890", "TBOX");
        assertNotNull(url);
        System.out.println(url);
    }

    @Test
    public void checkMgtKeyInUse_TEST() throws PopbillException {
        boolean useYN = cashbillService.checkMgtKeyInUse("1234567890", "20150318-02");
        assertNotNull(useYN);
        System.out.println(useYN);
    }

    @Test
    public void cancelIssue_TEST() throws PopbillException {

        Response response = cashbillService.cancelIssue("1234567890", "20150318-02", "발행취소 메모", "testkorea");
        assertNotNull(response);
        System.out.println(response.getMessage());
    }


    @Test
    public void sendEmail_TEST() throws PopbillException {

        Response response = cashbillService.sendEmail("1234567890", "20150317-01", "test@test.com", "testkorea");
        assertNotNull(response);
        System.out.println(response.getMessage());

    }

    @Test
    public void sendSMS_TEST() throws PopbillException {

        Response response = cashbillService.sendSMS("1234567890", "20150317-01", "07075103710", "010111222", "현금영수증 문자전송 테스트", "testkorea");
        assertNotNull(response);
        System.out.println(response.getMessage());

    }

    @Test
    public void sendFAX_TEST() throws PopbillException {

        Response response = cashbillService.sendFAX("1234567890", "20150317-01", "07075103710", "010111222", "testkorea");
        assertNotNull(response);
        System.out.println(response.getMessage());
    }

    @Test
    public void getDetailInfo_TEST() throws PopbillException {

        Cashbill cashbill = cashbillService.getDetailInfo("1234567890", "20211223JAVA02");


        assertNotNull(cashbill);
        System.out.println("Detail Info : " + cashbill.getFranchiseTaxRegID());

    }

    @Test
    public void getInfo_TEST() throws PopbillException {
        CashbillInfo cashbillInfo = cashbillService.getInfo("1234567890", "20180813160709");
        System.out.println(cashbillInfo.getIssueDT());
        assertNotNull(cashbillInfo);
        System.out.println("Get INfo : " + cashbillInfo.getMgtKey() + " " + cashbillInfo.getStateMemo());

    }

    @Test
    public void getInfos_TEST() throws PopbillException {
        String[] MgtKeyList = {"20150317-01", "20150317-02", "20150317-03"};

        CashbillInfo[] infoList = cashbillService.getInfos("1234567890", MgtKeyList);

        assertNotNull(infoList);
        System.out.println(infoList.length);
    }

    @Test
    public void assignMgtKey_TEST() throws PopbillException {

        String CorpNum = "1234567890";
        String ItemKey = "020080711515000001";
        String MgtKey = "20200807-01";

        Response response = cashbillService.assignMgtKey(CorpNum, ItemKey, MgtKey, "testkorea");
        assertNotNull(response);

        System.out.println("[" + response.getCode() + "] " + response.getMessage());
    }

    @Test
    public void getViewURL_TEST() throws PopbillException {
        String url = cashbillService.getViewURL("1234567890", "20200806-03", "testkorea");
        assertNotNull(url);
        System.out.println(url);
    }

    @Test
    public void getPDFURL_TEST() throws PopbillException {
        String url = cashbillService.getPDFURL("1234567890", "20200806-03");
        assertNotNull(url);
        System.out.println(url);
    }

    @Test
    public void getPrintURL_TEST() throws PopbillException {
        String url = cashbillService.getPrintURL("1234567890", "20170306-05");
        assertNotNull(url);
        System.out.println(url);
    }

    @Test
    public void getEPrintURL_TEST() throws PopbillException {
        String url = cashbillService.getEPrintURL("1234567890", "20170306-05");
        assertNotNull(url);
        System.out.println(url);
    }

    @Test
    public void getMassPrintURL_TEST() throws PopbillException {
        String[] MgtKeyList = {"20150317-01", "20150317-02", "20150317-03"};
        String url = cashbillService.getMassPrintURL("1234567890", MgtKeyList);

        assertNotNull(url);
        System.out.println(url);
    }

    @Test
    public void delete_TEST() throws PopbillException {
        Response response = cashbillService.delete("1234567890", "20150318-02", "testkorea");
        assertNotNull(response);
        System.out.println(response.getMessage());
    }

    @Test
    public void regiter() throws Exception {

        Cashbill cashbill = new Cashbill();

        cashbill.setMgtKey("20221104JAVA03");
        cashbill.setTradeType("승인거래");
        cashbill.setTradeDT("20221104000000");
        cashbill.setTradeOpt("일반");
        cashbill.setFranchiseCorpNum("1234567890");
        cashbill.setFranchiseTaxRegID("0001");
        cashbill.setFranchiseCorpName("발행자 상호");
        cashbill.setFranchiseCEOName("발행자 대표자");
        cashbill.setFranchiseAddr("발행자 주소");
        cashbill.setFranchiseTEL("07075103710");

        cashbill.setIdentityNum("0100001234");
        cashbill.setCustomerName("고객명");
        cashbill.setItemName("상품명");
        cashbill.setOrderNumber("주문번호");
        cashbill.setEmail("code@test.co.kr");
        cashbill.setHp("01011112222");
        cashbill.setFax("07075103710");
        cashbill.setServiceFee("0");
        cashbill.setSupplyCost("10000");
        cashbill.setTax("1000");
        cashbill.setTotalAmount("11000");
        cashbill.setTradeUsage("소득공제용");
        cashbill.setTaxationType("과세");
        cashbill.setSmssendYN(false);

        Response response = cashbillService.register("1234567890", cashbill);
        assertNotNull(response);
        System.out.println("[" + response.getCode() + "] " + response.getMessage());
    }

    @Test
    public void issue() throws Exception {
        CBIssueResponse response = cashbillService.issue("1234567890", "20221104JAVA03", "메모 입니다. Getinfo [StateMemo] 필드");

        System.out.println("[" + response.getCode() + "] " + response.getMessage());
        System.out.println(response.getConfirmNum() + ", " + response.getTradeDate());
    }

    @Test
    public void registIssue_TEST() throws PopbillException {

        Cashbill cashbill = new Cashbill();

        cashbill.setMgtKey("20221104JAVA02");
        cashbill.setTradeType("승인거래");
        cashbill.setTradeDT("20221104000000");
        cashbill.setTradeOpt("일반");
        cashbill.setFranchiseCorpNum("1234567890");
        cashbill.setFranchiseTaxRegID("0001");
        cashbill.setFranchiseCorpName("발행자 상호");
        cashbill.setFranchiseCEOName("발행자 대표자");
        cashbill.setFranchiseAddr("발행자 주소");
        cashbill.setFranchiseTEL("07075103710");

        cashbill.setIdentityNum("0100001234");
        cashbill.setCustomerName("고객명");
        cashbill.setItemName("상품명");
        cashbill.setOrderNumber("주문번호");
        cashbill.setEmail("code@test.co.kr");
        cashbill.setHp("01011112222");
        cashbill.setFax("07075103710");
        cashbill.setServiceFee("0");
        cashbill.setSupplyCost("10000");
        cashbill.setTax("1000");
        cashbill.setTotalAmount("11000");
        cashbill.setTradeUsage("소득공제용");
        cashbill.setTaxationType("과세");
        cashbill.setSmssendYN(false);

        CBIssueResponse response = cashbillService.registIssue("1234567890", cashbill, "메모 입니다. Getinfo [StateMemo] 필드", "", "메일 제목 테스트");
        assertNotNull(response);
        System.out.println("[" + response.getCode() + "] " + response.getMessage());
        System.out.println(response.getConfirmNum() + ", " + response.getTradeDate());
    }


    @Test
    public void bulkSubmit_TEST() throws PopbillException {
        try {
            List<Cashbill> cashbillList = new ArrayList<Cashbill>();
            String submitID = "20221104-JAVA3";

            for (int i = 0; i < 5; i++) {
                Cashbill cashbill = new Cashbill();

                cashbill.setMgtKey("20221104-" + i);
                cashbill.setTradeType("승인거래");
                cashbill.setTradeOpt("도서공연");
                cashbill.setFranchiseCorpNum("1234567890");
                cashbill.setFranchiseCorpName("발행자 상호");
                cashbill.setFranchiseCEOName("발행자 대표자");
                cashbill.setIdentityNum("0100001234");
                cashbill.setCustomerName("");
                cashbill.setEmail("test@test.com");
                cashbill.setItemName("상품명");
                cashbill.setOrderNumber("주문번호");
                cashbill.setServiceFee("0");
                cashbill.setSupplyCost("5000");
                cashbill.setTax("5000");
                cashbill.setTotalAmount("10000");
                cashbill.setTradeUsage("소득공제용");
                cashbill.setTaxationType("과세");
                cashbill.setSmssendYN(false);

                cashbillList.add(cashbill);
            }

            BulkResponse bulkResponse = cashbillService.bulkSubmit("1234567890", submitID, cashbillList);

            System.out.println(bulkResponse.getCode());
            System.out.println(bulkResponse.getMessage());
            System.out.println(bulkResponse.getReceiptID());
        } catch (PopbillException e) {
            // TODO Auto-generated catch block
            System.out.println(e.getCode() + "\t" + e.getMessage());
        }
    }

    @Test
    public void getBulkResult_TEST() throws PopbillException {
        BulkCashbillResult bulkCashbillResult = cashbillService.getBulkResult("1234567890", "20221104-JAVA3");
        System.out.println(bulkCashbillResult.getCode());
        System.out.println(bulkCashbillResult.getMessage());
        System.out.println(bulkCashbillResult.getSubmitID());
        System.out.println(bulkCashbillResult.getSubmitCount());
        System.out.println(bulkCashbillResult.getSuccessCount());
        System.out.println(bulkCashbillResult.getFailCount());
        System.out.println(bulkCashbillResult.getTxState());
        System.out.println(bulkCashbillResult.getTxStartDT());
        System.out.println(bulkCashbillResult.getTxEndDT());
        System.out.println(bulkCashbillResult.getReceiptDT());
        System.out.println(bulkCashbillResult.getReceiptID());
        for (int i = 0; i < bulkCashbillResult.getIssueResult().size(); i++) {
            BulkCashbillIssueResult bulkCashbillIssueResult = bulkCashbillResult.getIssueResult().get(i);
            System.out.print(bulkCashbillIssueResult.getMgtKey() + "\t");
            System.out.print(bulkCashbillIssueResult.getCode() + "\t");
            System.out.print(bulkCashbillIssueResult.getMessage() + "\t");
            System.out.print(bulkCashbillIssueResult.getConfirmNum() + "\t");
            System.out.print(bulkCashbillIssueResult.getTradeDate() + "\t");
            System.out.print(bulkCashbillIssueResult.getIssueDT() + "\n");
        }
    }

    @Test
    public void search_TEST() throws PopbillException {
        String CorpNum = "1234567890";
        String DType = "T";
        String SDate = "20241201";
        String EDate = "20241231";

        String[] State = {"100", "2**", "3**", "4**"};
        String[] TradeType = {"N", "C"};
        String[] TradeUsage = {"P", "C"};
        String[] TradeOpt = {"N", "B", "T"};
        String[] TaxationType = {"T", "N"};
        String QString = "";
        String FranchiseTaxRegID = null;

        int Page = 1;
        int PerPage = 20;
        String Order = "D";

        CBSearchResult response = cashbillService.search(CorpNum, DType, SDate, EDate, State, TradeType, TradeUsage, TradeOpt, TaxationType, QString, Page, PerPage, Order, FranchiseTaxRegID);

        assertNotNull(response);

        for (int i = 0; i < response.getList().size(); i++) {
            System.out.println(response.getList().get(0).getTotalAmount() + " " + response.getList().get(i).getIdentityNum());
        }
    }

    @Test
    public void search_TradeOpt_TEST() throws PopbillException {
        String CorpNum = "1234567890";
        String DType = "T";
        String SDate = "20180813";
        String EDate = "20180813";

        String[] State = {"100", "2**", "3**", "4**"};
        String[] TradeType = {"N", "C"};
        String[] TradeUsage = {"P", "C"};
        String[] TradeOpt = {"N", "B", "T"};
        String[] TaxationType = {"T", "N"};
        String QString = "";

        int Page = 1;
        int PerPage = 20;
        String Order = "D";

        CBSearchResult response = cashbillService.search(CorpNum, DType, SDate, EDate, State, TradeType, TradeUsage, TradeOpt, TaxationType, QString, Page, PerPage, Order);

        assertNotNull(response);

        for (int i = 0; i < response.getList().size(); i++) {
            System.out.println(response.getList().get(i).getTradeOpt() + " " + response.getList().get(i).getIdentityNum() + " " + response.getList().get(i).getStateMemo());
        }
    }


    @Test
    public void revokeRegistIssue01_TEST() throws PopbillException {
        String CorpNum = "1234567890";
        String mgtKey = "20221104-94";
        String orgConfirmNum = "TB0000122";
        String orgTradeDate = "20221103";

        CBIssueResponse response = cashbillService.revokeRegistIssue(CorpNum, mgtKey, orgConfirmNum, orgTradeDate);

        assertNotNull(response);

        System.out.println(response.getMessage());
    }

    @Test
    public void revokeRegistIssue02_TEST() throws PopbillException {
        String CorpNum = "1234567890";
        String mgtKey = "20191014-02";
        String orgConfirmNum = "820116333";
        String orgTradeDate = "20170711";
        Boolean smssendYN = true;

        CBIssueResponse response = cashbillService.revokeRegistIssue(CorpNum, mgtKey, orgConfirmNum, orgTradeDate, smssendYN, "memo", "", "메일제목 테스트");

        assertNotNull(response);

        System.out.println(response.getMessage());
        System.out.println(response.getCode());
        System.out.println(response.getConfirmNum());
        System.out.println(response.getTradeDate());
    }

    @Test
    public void revokeRegistIssue03_TEST() throws PopbillException {
        String CorpNum = "1234567890";
        String mgtKey = "20210812-Revoke002";
        String orgConfirmNum = "133367419";
        String orgTradeDate = "20171112";
        Boolean smssendYN = true;
        String memo = "취소현금영수증 즉시발행";
        Boolean isPartCancel = true;
        Integer cancelType = 1;
        String supplyCost = "10000";
        String tax = "1000";
        String serviceFee = "0";
        String totalAmount = "11000";
        String userID = "testkorea";

        CBIssueResponse response = cashbillService.revokeRegistIssue(CorpNum, mgtKey, orgConfirmNum, orgTradeDate, smssendYN, memo, isPartCancel, cancelType, supplyCost, tax, serviceFee, totalAmount, userID);

        assertNotNull(response);

        System.out.println(response.getMessage());
        System.out.println(response.getCode());
        System.out.println(response.getConfirmNum());
        System.out.println(response.getTradeDate());
    }

    @Test
    public void revokeRegistIssue04_TEST() throws PopbillException {
        String CorpNum = "1234567890";
        String mgtKey = "20221104-Revoke002";
        String orgConfirmNum = "TB0000122";
        String orgTradeDate = "20221103";
        Boolean smssendYN = true;
        String memo = "취소현금영수증 즉시발행";
        String tradeDT = "";

        CBIssueResponse response = cashbillService.revokeRegistIssue(CorpNum, mgtKey, orgConfirmNum, orgTradeDate, smssendYN, memo, null, null, null, null, null, null, null, null, tradeDT);

        assertNotNull(response);

        System.out.println(response.getMessage());
        System.out.println(response.getCode());
        System.out.println(response.getConfirmNum());
        System.out.println(response.getTradeDate());
    }


    @Test
    public void listEmailConfig_TEST() throws PopbillException {

        EmailSendConfig[] configList = cashbillService.listEmailConfig("1234567890");

        assertNotNull(configList);

        for (int i = 0; i < configList.length; i++) {

            System.out.print(configList[i].getEmailType() + " " + configList[i].getSendYN());
            System.out.println();
        }
    }

    @Test
    public void updateEmailConfig_TEST() throws PopbillException {

        String CorpNum = "1234567890";
        String EmailType = "CSH_ISSUE";
        Boolean SendYN = true;
        String UserID = "testkorea";

        Response response = cashbillService.updateEmailConfig(CorpNum, EmailType, SendYN, UserID);
        assertNotNull(response);

        System.out.println("[" + response.getCode() + "] " + response.getMessage());
    }

    @Test
    public void GetAccessURL_TEST() throws PopbillException {

        String url = cashbillService.getAccessURL("1234567890", "testkorea");

        System.out.println(url);
    }

    @Test
    public void GetChargeURL_TEST() throws PopbillException {

        String url = cashbillService.getChargeURL("1234567890", "testkorea");

        System.out.println(url);
    }




    @Test
    public void RegistIssueCN_TEST_FAIL_EMPTY_ConfirmNum() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String today = sdf.format(new Date());
        String testCorpNum = "6798700433";

        PopbillException pe = null;
        try {
            Cashbill cashbill = new Cashbill();

            cashbill.setMgtKey(today + "_test_" + srand.nextInt());
            cashbill.setTradeType("승인거래");
            cashbill.setTradeOpt("일반");
            cashbill.setFranchiseCorpNum(testCorpNum);
            cashbill.setFranchiseTaxRegID("0001");
            cashbill.setFranchiseCorpName("발행자 상호");
            cashbill.setFranchiseCEOName("발행자 대표자");
            cashbill.setFranchiseAddr("발행자 주소");
            cashbill.setFranchiseTEL("07075103710");
            cashbill.setConfirmNum("");

            cashbill.setIdentityNum("0100001234");
            cashbill.setCustomerName("고객명");
            cashbill.setItemName("상품명");
            cashbill.setOrderNumber("주문번호");
            cashbill.setEmail("code@test.co.kr");
            cashbill.setHp("01011112222");
            cashbill.setFax("07075103710");
            cashbill.setServiceFee("0");
            cashbill.setSupplyCost("10000");
            cashbill.setTax("1000");
            cashbill.setTotalAmount("11000");
            cashbill.setTradeUsage("소득공제용");
            cashbill.setTaxationType("과세");
            cashbill.setSmssendYN(false);
            cashbillService.registIssueCN(testCorpNum, cashbill, "메모 입니다. Getinfo [StateMemo] 필드", "", "메일 제목 테스트");
        } catch (PopbillException e) {
            pe = e;
            System.out.println("code: " + e.getCode() + " / message: " +e.getMessage());
        }

        assertNotNull(pe);
        assertEquals(pe.getCode(), -14010036);
        assertEquals(pe.getMessage(), "현금영수증 승인번호(ConfirmNum)가 입력되지 않았습니다.");

    }

    @Test
    public void RegistIssueCN_TEST_FAIL_InvalidConfirmNum() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String today = sdf.format(new Date());

        String testCorpNum = "6798700433";

        PopbillException pe = null;
        try {
            Cashbill cashbill = new Cashbill();

            cashbill.setMgtKey(today + "_t_" + srand.nextInt());
            cashbill.setTradeType("승인거래");
            cashbill.setTradeOpt("일반");
            cashbill.setFranchiseCorpNum(testCorpNum);
            cashbill.setFranchiseTaxRegID("0001");
            cashbill.setFranchiseCorpName("발행자 상호");
            cashbill.setFranchiseCEOName("발행자 대표자");
            cashbill.setFranchiseAddr("발행자 주소");
            cashbill.setFranchiseTEL("07075103710");
            cashbill.setConfirmNum("K07000001");

            cashbill.setIdentityNum("0100001234");
            cashbill.setCustomerName("고객명");
            cashbill.setItemName("상품명");
            cashbill.setOrderNumber("주문번호");
            cashbill.setEmail("code@test.co.kr");
            cashbill.setHp("01011112222");
            cashbill.setFax("07075103710");
            cashbill.setServiceFee("0");
            cashbill.setSupplyCost("10000");
            cashbill.setTax("1000");
            cashbill.setTotalAmount("11000");
            cashbill.setTradeUsage("소득공제용");
            cashbill.setTaxationType("과세");
            cashbill.setSmssendYN(false);
            cashbillService.registIssueCN(testCorpNum, cashbill, "메모 입니다. Getinfo [StateMemo] 필드", "", "메일 제목 테스트");
        } catch (PopbillException e) {
            pe = e;
            System.out.println("code: " + e.getCode() + " / message: " +e.getMessage());
        }

        assertNotNull(pe);
        assertEquals(-14001087,pe.getCode());
        assertEquals("현금영수증 국세청승인번호(ConfirmNum)가 유효하지 않습니다.",pe.getMessage());

    }




    @Test
    public void bulkSubmitCN_TEST_EMPTY_ConfirmNum() throws PopbillException {
        PopbillException pe = null;
        String SubmitID = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            String today = sdf.format(new Date());

            List<Cashbill> cashbillList = new ArrayList<Cashbill>();
            SubmitID = today + "-JAVA" + srand.nextInt();

            for (int i = 0; i < 1; i++) {
                String mgtKey = today + "_t-" + i + srand.nextInt();

                Cashbill cashbill = new Cashbill();

                cashbill.setMgtKey(mgtKey);
                cashbill.setTradeType("승인거래");
                cashbill.setTradeOpt("도서공연");
                cashbill.setFranchiseCorpNum("1234567890");
                cashbill.setFranchiseCorpName("발행자 상호");
                cashbill.setFranchiseCEOName("발행자 대표자");
                cashbill.setIdentityNum("0100001234");
                cashbill.setCustomerName("");
                cashbill.setEmail("test@test.com");
                cashbill.setItemName("상품명");
                cashbill.setOrderNumber("주문번호");
                cashbill.setServiceFee("0");
                cashbill.setSupplyCost("5000");
                cashbill.setTax("5000");
                cashbill.setTotalAmount("10000");
                cashbill.setTradeUsage("소득공제용");
                cashbill.setTaxationType("과세");
                cashbill.setSmssendYN(false);
                cashbill.setConfirmNum(null);

                cashbillList.add(cashbill);
            }

            BulkResponse bulkResponse = cashbillService.bulkSubmitCN("1234567890", SubmitID, cashbillList);

            System.out.println("접수 결과 - code: " + bulkResponse.getCode() + " / Message: " + bulkResponse.getMessage());
            System.out.println("접수 번호: " + bulkResponse.getReceiptID());
        } catch (PopbillException e) {
            pe = e;
            System.out.println("code: " + e.getCode() + " / message: " +e.getMessage());
        }

        assertNotNull(pe);
        assertEquals(-14010036,pe.getCode());
        assertEquals("현금영수증 승인번호(ConfirmNum)가 입력되지 않았습니다",pe.getMessage());
    }

    @Test
    public void bulkSubmitCN_TEST_FAIL_InvalidConfirmNum() throws PopbillException {
        PopbillException pe = null;
        String SubmitID = "";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            String today = sdf.format(new Date());

            List<Cashbill> cashbillList = new ArrayList<Cashbill>();
            SubmitID = today + "-JAVA" + srand.nextInt();

            for (int i = 0; i < 1; i++) {
                Cashbill cashbill = new Cashbill();
                String mgtKey = today + "_t-" + i + srand.nextInt();
                System.out.println("MgtKey: " + mgtKey);

                cashbill.setConfirmNum("K08000001");
                cashbill.setMgtKey(mgtKey);
                cashbill.setTradeType("승인거래");
                cashbill.setTradeOpt("도서공연");
                cashbill.setFranchiseCorpNum("1234567890");
                cashbill.setFranchiseCorpName("발행자 상호");
                cashbill.setFranchiseCEOName("발행자 대표자");
                cashbill.setIdentityNum("0100001234");
                cashbill.setCustomerName("");
                cashbill.setEmail("test@test.com");
                cashbill.setItemName("상품명");
                cashbill.setOrderNumber("주문번호");
                cashbill.setServiceFee("0");
                cashbill.setSupplyCost("5000");
                cashbill.setTax("5000");
                cashbill.setTotalAmount("10000");
                cashbill.setTradeUsage("소득공제용");
                cashbill.setTaxationType("과세");
                cashbill.setSmssendYN(false);

                cashbillList.add(cashbill);
            }

            BulkResponse bulkResponse = cashbillService.bulkSubmitCN("1234567890", SubmitID, cashbillList);

            System.out.println(bulkResponse.getCode());
            System.out.println(bulkResponse.getMessage());
            System.out.println(bulkResponse.getReceiptID());
        } catch (PopbillException e) {
            throw e;
        }

        BulkCashbillResult result = null;
        do {
            result = cashbillService.getBulkResult("1234567890", SubmitID);
        } while (result.getTxState() != 2);

        assertFalse(result == null);
        assertEquals(result.getTxState(), 2);

        for (BulkCashbillIssueResult rr : result.getIssueResult()) {
            System.out.println("[RESULT] code: " + rr.getCode() + ", message: " + rr.getMessage());
            assertEquals(rr.getCode(), -14001087);
            assertEquals(rr.getMessage(), "현금영수증 국세청승인번호(ConfirmNum)가 유효하지 않습니다.");
        }

    }
}
package com.popbill.api.cashbill.test;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.popbill.api.CashbillService;
import com.popbill.api.ChargeInfo;
import com.popbill.api.EmailSendConfig;
import com.popbill.api.PopbillException;
import com.popbill.api.Response;
import com.popbill.api.cashbill.CBSearchResult;
import com.popbill.api.cashbill.Cashbill;
import com.popbill.api.cashbill.CashbillInfo;
import com.popbill.api.cashbill.CashbillLog;
import com.popbill.api.cashbill.CashbillServiceImp;

public class CashbillServiceTEST {
	private final String testLinkID = "TESTER";
	private final String testSecretKey = "SwWxqU+0TErBXy/9TVjIPEnI0VTUMMSQZtJf3Ed8q3I=";
	
	private CashbillService cashbillService;
	
	public CashbillServiceTEST(){
		CashbillServiceImp service = new CashbillServiceImp();
		
		service.setLinkID(testLinkID);
		service.setSecretKey(testSecretKey);
		service.setTest(true);
		
		cashbillService = service;
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
	public void register_TEST() throws PopbillException {
		
		Cashbill cashbill = new Cashbill();
		
		cashbill.setMgtKey("20180813154637");
		cashbill.setTradeType("승인거래");
		cashbill.setTradeOpt("도서공연");
		cashbill.setFranchiseCorpNum("1234567890");
		cashbill.setFranchiseCorpName("발행자 상호");
		cashbill.setFranchiseCEOName("발행자 대표자");
		cashbill.setFranchiseAddr("발행자 주소");
		cashbill.setFranchiseTEL("07075103710");
		cashbill.setIdentityNum("01041680206");
		cashbill.setCustomerName("고객명");
		cashbill.setItemName("상품명");
		cashbill.setOrderNumber("주문번호");
		cashbill.setEmail("test@test.com");
		cashbill.setHp("01043245117");
		cashbill.setFax("07075103710");
		cashbill.setServiceFee("0");
		cashbill.setSupplyCost("5000");
		cashbill.setTax("5000");
		cashbill.setTotalAmount("10000");
		cashbill.setTradeUsage("소득공제용");
		cashbill.setTaxationType("과세");
		cashbill.setSmssendYN(false);
		
		Response response = cashbillService.register("1234567890", cashbill);
		assertNotNull(response);
		System.out.println(response.getMessage());
	}
	
	@Test
	public void update_TEST() throws PopbillException {
		
		Cashbill cashbill = new Cashbill();
		
		cashbill.setMgtKey("20150317-01");
		cashbill.setTradeType("승인거래");
		cashbill.setFranchiseCorpNum("1234567890");
		cashbill.setFranchiseCorpName("발행자 상호_수정");
		cashbill.setFranchiseCEOName("발행자 대표자_수정");
		cashbill.setFranchiseAddr("발행자 주소");
		cashbill.setFranchiseTEL("07075103710");
		cashbill.setIdentityNum("01043245117");
		cashbill.setCustomerName("고개명");
		cashbill.setItemName("상품명");
		cashbill.setOrderNumber("주문번호");
		cashbill.setEmail("test@test.com");
		cashbill.setHp("01043245117");
		cashbill.setFax("07075103710");
		cashbill.setServiceFee("0");
		cashbill.setSupplyCost("10000");
		cashbill.setTax("1000");
		cashbill.setTotalAmount("11000");
		cashbill.setTradeUsage("소득공제용");
		cashbill.setTaxationType("과세");
		cashbill.setSmssendYN(false);
		
		Response response = cashbillService.update("1234567890", "20150318-02", cashbill, "testkorea");
		assertNotNull(response);
		System.out.println(response.getMessage());
	}
	
	@Test
	public void issue_TEST() throws PopbillException {
		
		Response response = cashbillService.issue("1234567890", "20150318-02", "발행메모",	"testkorea");
		assertNotNull(response);
		System.out.println(response.getMessage());
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
		
		Response response = cashbillService.sendFAX("1234567890", "20150317-01", "07075103710","010111222", "testkorea");
		assertNotNull(response);
		System.out.println(response.getMessage());
	}
	
	@Test
	public void getDetailInfo_TEST() throws PopbillException {
		
		Cashbill cashbill = cashbillService.getDetailInfo("1234567890", "20180813160709");
		assertNotNull(cashbill);
		System.out.println("Detail Info : "+ cashbill.getMgtKey());

	}
	
	@Test
	public void getInfo_TEST() throws PopbillException {
		CashbillInfo cashbillInfo = cashbillService.getInfo("1234567890", "20180813160709");
		assertNotNull(cashbillInfo);
		System.out.println("Get INfo : "+ cashbillInfo.getMgtKey()+" "+cashbillInfo.getStateMemo());
		
	}
	
	@Test
	public void getInfos_TEST() throws PopbillException {
		String[] MgtKeyList = {"20150317-01","20150317-02", "20150317-03"};
		
		CashbillInfo[] infoList = cashbillService.getInfos("1234567890", MgtKeyList);
		
		assertNotNull(infoList);
		System.out.println(infoList.length);
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
		String[] MgtKeyList = {"20150317-01","20150317-02","20150317-03"};
		String url = cashbillService.getMassPrintURL("1234567890", MgtKeyList);
		
		assertNotNull(url);
		System.out.println(url);
	}
	
	@Test
	public void getLogs_TEST() throws PopbillException {
		CashbillLog[] logList = cashbillService.getLogs("1234567890", "20150318-02");
		for(int i=0; i<logList.length; i++){
			System.out.println(logList[i].getProcMemo());
		}
	}
	
	@Test
	public void delete_TEST() throws PopbillException {
		Response response = cashbillService.delete("1234567890", "20150318-02", "testkorea");
		assertNotNull(response);
		System.out.println(response.getMessage());
	}
	
	@Test
	public void registIssue_TEST() throws PopbillException {
		
		Cashbill cashbill = new Cashbill();
		
		cashbill.setMgtKey("20180813160709");
		cashbill.setTradeType("승인거래");
		cashbill.setTradeOpt("대중교통");
		cashbill.setFranchiseCorpNum("1234567890");
		cashbill.setFranchiseCorpName("발행자 상호");
		cashbill.setFranchiseCEOName("발행자 대표자");
		cashbill.setFranchiseAddr("발행자 주소");
		cashbill.setFranchiseTEL("07075103710");
		
		cashbill.setIdentityNum("01043245117");
		cashbill.setCustomerName("고객명");
		cashbill.setItemName("상품명");
		cashbill.setOrderNumber("주문번호");
		cashbill.setEmail("test@test.com");
		cashbill.setHp("01043245117");
		cashbill.setFax("07075103710");
		cashbill.setServiceFee("0");
		cashbill.setSupplyCost("10000");
		cashbill.setTax("1000");
		cashbill.setTotalAmount("11000");
		cashbill.setTradeUsage("소득공제용");
		cashbill.setTaxationType("과세");
		cashbill.setSmssendYN(false);
		
		Response response = cashbillService.registIssue("1234567890", cashbill, "메모 입니다. Getinfo [StateMemo] 필드");
		assertNotNull(response);
		System.out.println("[" + response.getCode() + "] "+ response.getMessage());
	}
	
	@Test
    public void search_TEST() throws PopbillException{
        String CorpNum = "1234567890";
        String DType = "T";
        String SDate = "20160701";
        String EDate = "20160831";

        String[] State = {"100", "2**", "3**", "4**"};
        String[] TradeType = {"N", "C"};
        String[] TradeUsage = {"P", "C"};
        String[] TaxationType = {"T", "N"};
        String QString = "0100001234";

        int Page = 1;
        int PerPage = 20;
        String Order = "D";

        CBSearchResult response = cashbillService.search(CorpNum, DType, SDate, EDate, State, TradeType, TradeUsage, TaxationType, QString, Page, PerPage, Order);

        assertNotNull(response);

        for (int i=0; i<response.getList().size(); i++){
            System.out.println(response.getList().get(0).getTotalAmount()+" " +response.getList().get(i).getIdentityNum());
        }
    }

    @Test
    public void search_TradeOpt_TEST() throws PopbillException{
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

        for (int i=0; i<response.getList().size(); i++){
            System.out.println(response.getList().get(i).getTradeOpt()+" " +response.getList().get(i).getIdentityNum()+" " +response.getList().get(i).getStateMemo());
        }
    }


	@Test
	public void revokeRegistIssue01_TEST() throws PopbillException{
		String CorpNum = "1234567890";
		String mgtKey = "20170816-04";
		String orgConfirmNum = "820116333";
		String orgTradeDate = "20170711";
		
		Response response = cashbillService.revokeRegistIssue(CorpNum, mgtKey, orgConfirmNum, orgTradeDate);
		
		assertNotNull(response);
		
		System.out.println(response.getMessage());
	}
	
	@Test
	public void revokeRegistIssue02_TEST() throws PopbillException{
		String CorpNum = "1234567890";
		String mgtKey = "20170816-05";
		String orgConfirmNum = "820116333";
		String orgTradeDate = "20170711";
		Boolean smssendYN = true;
		
		Response response = cashbillService.revokeRegistIssue(CorpNum, mgtKey, orgConfirmNum, orgTradeDate, smssendYN);
		
		assertNotNull(response);
		
		System.out.println(response.getMessage());
	}
	
	@Test
	public void revokeRegistIssue03_TEST() throws PopbillException{
		String CorpNum = "1234567890";
		String mgtKey = "20171114-17";
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
		
		Response response = cashbillService.revokeRegistIssue(CorpNum, mgtKey, orgConfirmNum, 
				orgTradeDate, smssendYN, memo, isPartCancel, cancelType, supplyCost, 
				tax, serviceFee, totalAmount, userID);
		
		assertNotNull(response);
		
		System.out.println(response.getMessage());
	}
	
	@Test
	public void revokeRegister01_TEST() throws PopbillException{
		String CorpNum = "1234567890";
		String mgtKey = "20171114-13";
		String orgConfirmNum = "133367419";
		String orgTradeDate = "20171112";
		Boolean smssendYN = false; 
		Boolean isPartCancel = true;
		Integer cancelType = 1;
		String supplyCost = "10000";
		String tax = "1000";
		String serviceFee = "0";
		String totalAmount = "11000";
		
		Response response = cashbillService.revokeRegister(CorpNum, mgtKey, orgConfirmNum,
				orgTradeDate, smssendYN, isPartCancel, cancelType, supplyCost, 
				tax, serviceFee, totalAmount, "testkorea");
		
		assertNotNull(response);
		
		System.out.println(response.getMessage());
	}
	
	@Test
	public void revokeRegister02_TEST() throws PopbillException{
		String CorpNum = "1234567890";
		String mgtKey = "20170816-12";
		String orgConfirmNum = "820116333";
		String orgTradeDate = "20170711";
		Boolean smssendYN = true;
		
		Response response = cashbillService.revokeRegister(CorpNum, mgtKey, orgConfirmNum, orgTradeDate, smssendYN);
		
		assertNotNull(response);
		
		System.out.println(response.getMessage());
	}
	
	@Test
	public void revokeRegister03_TEST() throws PopbillException{
		String CorpNum = "1234567890";
		String mgtKey = "20170816-13";
		String orgConfirmNum = "820116333";
		String orgTradeDate = "20170711";
		Boolean smssendYN = true;
		String userID = "testkorea";
		
		Response response = cashbillService.revokeRegister(CorpNum, mgtKey, orgConfirmNum, orgTradeDate, smssendYN, userID);
		
		assertNotNull(response);
		
		System.out.println(response.getMessage());
	}
	
	@Test
	public void listEmailConfig_TEST() throws PopbillException{
		
		EmailSendConfig[] configList = cashbillService.listEmailConfig("1234567890");
		
		assertNotNull(configList);
		
		for(int i=0; i< configList.length; i++){

			System.out.print(configList[i].getEmailType()+ " "+ configList[i].getSendYN());
			System.out.println();
		}
	}
	
	@Test
	public void updateEmailConfig_TEST() throws PopbillException{
		
		String CorpNum = "1234567890";
		String EmailType = "CSH_ISSUE";
		Boolean SendYN = true;
		String UserID = "testkorea";
		
		Response response = cashbillService.updateEmailConfig(CorpNum, EmailType, SendYN, UserID);
		assertNotNull(response);
		
		System.out.println("["+response.getCode() +"] " + response.getMessage());
	}
}


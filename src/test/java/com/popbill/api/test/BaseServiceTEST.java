package com.popbill.api.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.junit.Test;

import com.popbill.api.ContactInfo;
import com.popbill.api.CorpInfo;
import com.popbill.api.JoinForm;
import com.popbill.api.PaymentForm;
import com.popbill.api.PaymentHistory;
import com.popbill.api.PaymentResponse;
import com.popbill.api.PopbillException;
import com.popbill.api.RefundHistory;
import com.popbill.api.Response;
import com.popbill.api.TaxinvoiceService;
import com.popbill.api.taxinvoice.TaxinvoiceServiceImp;

import kr.co.linkhub.auth.LinkhubException;
import kr.co.linkhub.auth.MemberPointDetail;
import kr.co.linkhub.auth.Token;
import kr.co.linkhub.auth.TokenBuilder;

public class BaseServiceTEST {

	private final String testLinkID = "TESTER";
    private final String testSecretKey = "SwWxqU+0TErBXy/9TVjIPEnI0VTUMMSQZtJf3Ed8q3I=";

    private TaxinvoiceService taxinvoiceService;

    public BaseServiceTEST() {
        TaxinvoiceServiceImp service = new TaxinvoiceServiceImp();


        service.setLinkID(testLinkID);
        service.setSecretKey(testSecretKey);
        service.setTest(true);

        taxinvoiceService = service;
    }

    @Test
    public void getListContact_TEST() throws PopbillException {
        ContactInfo[] contactList = taxinvoiceService.listContact("1234567890","testkorea");

        System.out.println(contactList.length);

        for(int i=0; i< contactList.length; i++){

        System.out.println("id : " + contactList[i].getId());
        System.out.println("personName : " + contactList[i].getPersonName());
        System.out.println("email : " + contactList[i].getEmail());
        System.out.println("hp : " + contactList[i].getHp());
        System.out.println("fax : " + contactList[i].getFax());
        System.out.println("tel : " + contactList[i].getTel());
        System.out.println("regDT : " + contactList[i].getRegDT());
        System.out.println("searchAllAllowYN : " + contactList[i].getSearchAllAllowYN());
        System.out.println("mgrYN : " + contactList[i].getMgrYN());
        System.out.println("state : " + contactList[i].getState());
        System.out.println("searchRole :" + contactList[i].getSearchRole());
        System.out.println("=======================");
        }

    }

    @Test
    public void getPopbillURL_TEST() throws PopbillException {

        String url = taxinvoiceService.getPopbillURL("1234567890", "testkorea",    "LOGIN");

        assertNotNull(url);

        System.out.println(url);

    }

    @Test
    public void joinMember_TEST() throws PopbillException {

        JoinForm joinInfo = new JoinForm();
        joinInfo.setLinkID(testLinkID);
        joinInfo.setCorpNum("0000009998"); // 사업자번호 "-" 제외
        joinInfo.setCEOName("대표자성명");
        joinInfo.setCorpName("상호");
        joinInfo.setAddr("주소");
        joinInfo.setZipCode("500-100");
        joinInfo.setBizType("업태");
        joinInfo.setBizClass("업종");
        joinInfo.setID("JAVA_JoinMember_PasswordTEST01"); // 6자 이상 20자 미만
        joinInfo.setPWD("pwd_must_be_long_enough"); // 6자 이상 20자 미만
        joinInfo.setPassword("qwe123!@#");
        joinInfo.setContactName("담당자명");
        joinInfo.setContactTEL("02-999-9999");
        joinInfo.setContactHP("010-1234-5678");
        joinInfo.setContactFAX("02-999-9998");
        joinInfo.setContactEmail("test@test.com");

        Response response = taxinvoiceService.joinMember(joinInfo);

        assertNotNull(response);

        System.out.println(response.getMessage());
    }

    @Test
    public void getBalance_TEST() throws PopbillException {


        double balance = taxinvoiceService.getBalance("1234567890");

        System.out.println(balance);
    }
    
    @Test
    public void getPointInfo_TEST() throws PopbillException {
    	
    	try {
    		MemberPointDetail balance = taxinvoiceService.getPointInfo("1234567890");

            System.out.println("통합포인트 : "+balance.getTotalPoint());
            System.out.println("결제포인트 : "+balance.getChargePoint());
            System.out.println("보너스포인트 : "+balance.getBonusPoint());
            
    	} catch (PopbillException pe) {
    		System.out.println(pe.getCode());
    		System.out.println(pe.getMessage());
    	}
    	
        
    }

    @Test
    public void getPartnerBalance_TEST() throws PopbillException {

        double balance = taxinvoiceService.getPartnerBalance("1234567890");

        System.out.println(balance);
    }



    @Test
    public void getUTCTime_TEST() throws PopbillException, LinkhubException{
        TokenBuilder tokenBuilder;
        Token token = null;
        Boolean expired;

        tokenBuilder = TokenBuilder
                .newInstance(testLinkID, testSecretKey)
                .ServiceID("POPBILL_TEST")
                .addScope("member")
                .addScope("110");

        try {
            token = tokenBuilder.build("1234567890", "");
        } catch (LinkhubException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        Date expiration = null;

        Date UTCTime = null;

        SimpleDateFormat format = new SimpleDateFormat(
                "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        format.setTimeZone(TimeZone.getTimeZone("UTC"));

        SimpleDateFormat format2 = new SimpleDateFormat(
                "yyyy-MM-dd'T'HH:mm:ss'Z'");
        format2.setTimeZone(TimeZone.getTimeZone("UTC"));

        try {
            UTCTime = format2.parse("2016-01-18T14:05:37Z");
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            expiration = format.parse(token.getExpiration());
            expired = expiration.before(UTCTime);
            System.out.println(token.getExpiration()+ " "+UTCTime+ " "+ expired);
            assertNotNull(expiration);

        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Test
    public void httpGET_GZIP_TEST() throws PopbillException, LinkhubException{
        TokenBuilder tokenBuilder;
        Token token = null;
        HttpURLConnection httpURLConnection;

        String corpNum = "1234567890";

        tokenBuilder = TokenBuilder
                .newInstance(testLinkID, testSecretKey)
                .ServiceID("POPBILL_TEST")
                .addScope("member")
                .addScope("110");

        token = tokenBuilder.build(corpNum, "");

        try {
            URL uri = new URL("https://popbill_test.linkhub.co.kr/Taxinvoice/EmailPublicKeys");
            httpURLConnection = (HttpURLConnection) uri.openConnection();
        } catch (Exception e) {
            throw new PopbillException(-99999999, "팝빌 API 서버 접속 실패", e);
        }


        httpURLConnection.setRequestProperty("Authorization", "Bearer "
                    + token.getSession_token());


        httpURLConnection.setRequestProperty("x-pb-version".toLowerCase(),
                "1.0");

        httpURLConnection.setRequestProperty("x-pb-userid", "testkorea");

        httpURLConnection.setRequestProperty("Accept-Encoding",
                "gzip");

        assertEquals("gzip",httpURLConnection.getContentEncoding());
    }

    @Test
    public void UpdateContact_TEST() throws PopbillException {
        ContactInfo contInfo = new ContactInfo();

        contInfo.setPersonName("김인숙 부장");
        contInfo.setTel("031-989-0687");
        contInfo.setHp("031-989-0687");
        contInfo.setEmail("kisttc@nate.com");
        contInfo.setFax("");
        contInfo.setSearchAllAllowYN(true);
        contInfo.setMgrYN(true);
        contInfo.setState(1);

        Response response = taxinvoiceService.updateContact("1378162966", contInfo, "submall1385");

        assertNotNull(response);

        System.out.println(response.getMessage());
    }

    @Test
    public void RegistContact_TEST() throws PopbillException{
        ContactInfo contInfo = new ContactInfo();

        contInfo.setId("JAVA_ResgistContact_Password001");
        contInfo.setPwd("qwe123!@#");
        contInfo.setPassword("qwe123!@#");
        contInfo.setPersonName("자바");
        contInfo.setTel("02-1234-1234");
        contInfo.setHp("010-1234-1234");
        contInfo.setFax("070-7510-3710");
        contInfo.setEmail("test1234@test.com");
        contInfo.setSearchAllAllowYN(true);
        contInfo.setMgrYN(false);
        contInfo.setSearchRole(2);

        Response response = taxinvoiceService.registContact("1234567890", contInfo, "testkorea");

        assertNotNull(response);

        System.out.println(response.getMessage());
    }

    @Test
    public void CheckID_TEST() throws PopbillException{
        Response response = taxinvoiceService.checkID("testkorea");
        assertNotNull(response);
        System.out.println("["+response.getCode() + "] " + response.getMessage());
    }


    @Test
    public void UpdateCorpInfo_TEST() throws PopbillException{
        CorpInfo corpInfo = new CorpInfo();
        corpInfo.setAddr("광주광역시 서구 천변좌로 268");
        corpInfo.setBizClass("업종테스트");
        corpInfo.setBizType("업태테스트");
        corpInfo.setCeoname("대표자성명 설정 테스트");
        corpInfo.setCorpName("상호 테스트");

        Response response = taxinvoiceService.updateCorpInfo("1234567890", corpInfo, "testkorea");
        assertNotNull(response);
        System.out.println("["+response.getCode()+"] "+response.getMessage());
    }

    @Test
    public void GetCorpInfo_TEST() throws PopbillException{
        CorpInfo corpInfo = taxinvoiceService.getCorpInfo("1234567890","testkorea");

        assertNotNull(corpInfo);

        System.out.println(corpInfo.getBizClass());
        System.out.println(corpInfo.getAddr());
        System.out.println(corpInfo.getBizType());
        System.out.println(corpInfo.getCeoname());
        System.out.println(corpInfo.getCorpName());

    }

    @Test
    public void getContactInfo_Test() throws PopbillException{
        ContactInfo contactInfo = taxinvoiceService.getContactInfo("1234567890", "JAVARegistContact01", "testkorea");

        assertNotNull(contactInfo);

        System.out.println(contactInfo.getState());
        System.out.println(contactInfo.getId());
        System.out.println(contactInfo.getPersonName());
        System.out.println(contactInfo.getClass());
        System.out.println(contactInfo.getEmail());
        System.out.println(contactInfo.getFax());
        System.out.println(contactInfo.getHp());
        System.out.println(contactInfo.getRegDT());
        System.out.println(contactInfo.getMgrYN());
        System.out.println(contactInfo.getSearchRole());
        System.out.println(contactInfo.getSearchAllAllowYN());
    }

    @Test
    public void getPaymentRequest() throws PopbillException {
    	PaymentForm paymentForm = new PaymentForm();

    	paymentForm.setSettlerName("담당자명");
    	paymentForm.setSettlerEmail("test@test.com");
    	paymentForm.setNotifyHP("01022223333");
    	paymentForm.setPaymentName("홍길동");
    	paymentForm.setSettleCost("10000");

    	PaymentResponse response = taxinvoiceService.paymentRequest("1234567890", paymentForm, "testkorea");
    	assertNotNull(response);

    	System.out.println(response.getCode());
    	System.out.println(response.getMessage());
    	System.out.println(response.getSettleCode());
    }

    @Test
    public void getSettleResultTest() throws PopbillException {
        String corpNum = "1234567890";
        String settleCode = "202303070000000052";
        PaymentHistory result = taxinvoiceService.getSettleResult(corpNum, settleCode);

        System.out.println(result.toString());
    }
    

    
    @Test
    public void getRefundInfoTest() throws PopbillException{
    	try {
    		String corpNum = "1234567890";
        	String refundCode = "023040000007";
        	
        	RefundHistory result = taxinvoiceService.getRefundInfo(corpNum, refundCode);
        	
        	System.out.println(result.getState());
        	System.out.println(result.getAccountBank());
        	System.out.println(result.getAccountName());
        	System.out.println(result.getAccountNum());
        	System.out.println(result.getReason());
    	} catch(PopbillException e) {
    		System.out.println(e.getCode());
    		System.out.println(e.getMessage());
    	}
    	
    	
    }
    
    @Test
    public void getRefundableBalance() throws PopbillException{
    	try {
    		String corpNum = "1234567890";
        	
        	double refundableBalance = taxinvoiceService.getRefundableBalance(corpNum);
        	
        	System.out.println(refundableBalance);
        	
    	} catch(PopbillException e) {
    		System.out.println(e.getCode());
    		System.out.println(e.getMessage());
    	}
    	
    	
    }
    
    @Test
    public void quitReqeustTest() throws PopbillException{
    	String corpNum = "8814000517";
    	String quitReason = "탈퇴사유java sdk";
    	
    	try {
    		Response rst = taxinvoiceService.quitMember(corpNum, quitReason);
    	    
        	System.out.println(rst.getCode());
        	System.out.println(rst.getMessage());
    	} catch(PopbillException e) {
    		System.out.println(e.getCode());
    		System.out.println(e.getMessage());
    	}
    	
    }
}


package com.popbill.api.kako.test;

import static org.junit.Assert.assertNotNull;

import java.io.File;

import org.junit.Test;

import com.popbill.api.ChargeInfo;
import com.popbill.api.KakaoService;
import com.popbill.api.PopbillException;
import com.popbill.api.Response;
import com.popbill.api.kakao.ATSTemplate;
import com.popbill.api.kakao.KakaoButton;
import com.popbill.api.kakao.KakaoReceiver;
import com.popbill.api.kakao.KakaoSearchResult;
import com.popbill.api.kakao.KakaoSentInfo;
import com.popbill.api.kakao.KakaoServiceImp;
import com.popbill.api.kakao.KakaoType;
import com.popbill.api.kakao.PlusFriendID;
import com.popbill.api.kakao.SenderNumber;

public class KakaoServiceTest {
	private final String testLinkID = "TESTER";
	private final String testSecretKey = "SwWxqU+0TErBXy/9TVjIPEnI0VTUMMSQZtJf3Ed8q3I=";

	private KakaoService kakaoService;
	
	public KakaoServiceTest() {
		KakaoServiceImp service = new KakaoServiceImp();

		service.setLinkID(testLinkID);
		service.setSecretKey(testSecretKey);
		service.setTest(true);
		
		kakaoService = service;
	}
	
	@Test
	public void getChargeInfo_TEST() throws PopbillException {
		
		ChargeInfo chrgInfo = kakaoService.getChargeInfo("1234567890", KakaoType.FMS);
		
		System.out.println(chrgInfo.getChargeMethod());
		System.out.println(chrgInfo.getRateSystem());
		System.out.println(chrgInfo.getUnitCost());
	}
	
	@Test
	public void getUnitCost_TEST() throws PopbillException {
		float UnitCost = kakaoService.getUnitCost("1234567890",KakaoType.FMS);
		System.out.println(UnitCost);
	}
	
	@Test
	public void getURL_TEST() throws PopbillException {
		
		String url =  kakaoService.getURL("1234567890", "PLUSFRIEND", "testkorea");

		assertNotNull(url);
		System.out.println(url);
	}
	
	@Test
	public void getSenderNumberList_TEST() throws PopbillException {
		
		SenderNumber[] listInfo = kakaoService.getSenderNumberList("1234567890");
		
		int i;
		for (i=0; i<listInfo.length; i++){
			System.out.println(listInfo[i].getNumber());
			System.out.println(listInfo[i].getState());
			System.out.println(listInfo[i].getRepresentYN());
		}
	}
	
	@Test
	public void listPlusFriend_TEST() throws PopbillException {
		
		PlusFriendID[] friendList = kakaoService.listPlusFriendID("1234567890");
		
		int i;
		for (i=0; i<friendList.length; i++){
			System.out.println(friendList[i].getPlusFriendID());
			System.out.println(friendList[i].getPlusFriendName());
			System.out.println(friendList[i].getRegDT());
		}
	}
	
	@Test
	public void listATSTempalte_TEST() throws PopbillException {
		
		ATSTemplate[] templateList = kakaoService.listATSTemplate("1234567890");
		
		int i;
		for (i=0; i<templateList.length; i++){
			System.out.println(templateList[i].getTemplateCode());
			System.out.println(templateList[i].getTemplateName());
			System.out.println(templateList[i].getTemplate());
			System.out.println(templateList[i].getPlusFriendID());
		}
	}
	
	@Test
	public void sendATS_Single_TEST() throws PopbillException {
		String testCorpNum = "1234567890";
		
		String templateCode = "018020000001";
		
		String senderNum = "07043042993";
		
		String content = "[테스트] 테스트 템플릿입니다.d";
				
		String altContent = "대체문자 내용";
		
		String altSendType = "C";
		
		String receiverNum = "01043245117";
		
		String receiverName = "수신자명";
		
		String sndDT = "";
				
		
		String receiptNum = kakaoService.sendATS(testCorpNum, templateCode, senderNum, content, altContent, altSendType, 
				receiverNum, receiverName, sndDT);
		
		assertNotNull(receiptNum);
		
		System.out.println(receiptNum);
	}
	
	@Test
	public void sendATS_same_TEST() throws PopbillException {
		String testCorpNum = "1234567890";
		
		String templateCode = "018020000001";
		
		String senderNum = "07043042993";
		
		String content = "[테스트] 테스트 템플릿입니다";
				
		String altContent = "대체문자 내용";
		
		String altSendType = "C";
		
		KakaoReceiver[] receivers = new KakaoReceiver[2];
		
		KakaoReceiver message = new KakaoReceiver();
		message.setReceiverNum("01043245117");
		message.setReceiverName("수신자명");
		receivers[0] = message;
		receivers[1] = message;
		
		String sndDT = "";
		
		String receiptNum = kakaoService.sendATS(testCorpNum, templateCode, senderNum, content, altContent, altSendType, receivers, sndDT);
		
		assertNotNull(receiptNum);
		
		System.out.println(receiptNum);
	}
	
	@Test
	public void sendATS_multi_TEST() throws PopbillException {
		String testCorpNum = "1234567890";
		
		String templateCode = "018020000001";
		
		String senderNum = "07043042993";
		
		String altSendType = "C";
		
		KakaoReceiver[] receivers = new KakaoReceiver[2];
		
		KakaoReceiver message = new KakaoReceiver();
		message.setReceiverNum("01043245117");
		message.setReceiverName("수신자명");
		message.setMessage("[테스트] 테스트 템플릿입니다");
		message.setAltMessage("대체문자 내용");
		receivers[0] = message;
		
		message = new KakaoReceiver();
		message.setReceiverNum("01043245117");
		message.setReceiverName("수신자명");
		message.setMessage("[테스트] 테스트 템플릿입니다요");
		message.setAltMessage("대체문자 내용");
		receivers[1] = message;
		
		String sndDT = "";
		
		String receiptNum = kakaoService.sendATS(testCorpNum, templateCode, senderNum, altSendType, receivers, sndDT);
		
		assertNotNull(receiptNum);
		
		System.out.println(receiptNum);
	}
	
	@Test
	public void sendFTS_single_TEST() throws PopbillException {
		String testCorpNum = "1234567890";
		String plusFriendID = "@팝빌";
		String senderNum = "07043042993";
		String content = "친구톡 메시지 내용";
		String altContent = "대체문자 내용";
		String altSendType = "C";
		String receiverNum = "01043245117";
		String receiverName = "수신자명";
		String sndDT = "";
		Boolean adsYN = false;
		
		KakaoButton[] btns = new KakaoButton[2];
		KakaoButton button = new KakaoButton();
		button.setN("버튼명");
		button.setT("WL");
		button.setU1("http://www.popbill.com");
		button.setU2("http://test.popbill.com");
		btns[0] = button;
		
		button = new KakaoButton();
		button.setN("버튼명2");
		button.setT("WL");
		button.setU1("http://www.popbill.com");
		button.setU2("http://test.popbill.com");
		btns[1] = button;
		
		String receiptNum = kakaoService.sendFTS(testCorpNum, plusFriendID, senderNum, content, 
				altContent, altSendType, btns, receiverNum, receiverName, sndDT, adsYN);
		
		assertNotNull(receiptNum);
		
		System.out.println(receiptNum);
	}
	
	@Test
	public void sendFTS_same_TEST() throws PopbillException {
		String testCorpNum = "1234567890";
		String plusFriendID = "@팝빌";
		String senderNum = "07043042993";
		String content = "친구톡 메시지 내용";
		String altContent = "대체문자 내용";
		String altSendType = "C";
		String sndDT = "";
		Boolean adsYN = false;
		
		KakaoButton[] btns = new KakaoButton[2];
		KakaoButton button = new KakaoButton();
		button.setN("버튼명");
		button.setT("WL");
		button.setU1("http://www.popbill.com");
		button.setU2("http://test.popbill.com");
		btns[0] = button;
		
		button = new KakaoButton();
		button.setN("버튼명2");
		button.setT("WL");
		button.setU1("http://www.popbill.com");
		button.setU2("http://test.popbill.com");
		btns[1] = button;
		
		KakaoReceiver[] receivers = new KakaoReceiver[2];
		
		KakaoReceiver message = new KakaoReceiver();
		message.setReceiverNum("01043245117");
		message.setReceiverName("수신자명");
		receivers[0] = message;
		
		message = new KakaoReceiver();
		message.setReceiverNum("01043245117");
		message.setReceiverName("수신자명");
		receivers[1] = message;

		String receiptNum = kakaoService.sendFTS(testCorpNum, plusFriendID, senderNum, content, 
				altContent, altSendType, receivers, btns, sndDT, adsYN);
		
		assertNotNull(receiptNum);
		
		System.out.println(receiptNum);
	}
	
	@Test
	public void sendFTS_multi_TEST() throws PopbillException {
		String testCorpNum = "1234567890";
		String plusFriendID = "@팝빌";
		String senderNum = "07043042993";
		String altSendType = "C";
		String sndDT = "";
		Boolean adsYN = false;
		
		KakaoButton[] btns = new KakaoButton[2];
		KakaoButton button = new KakaoButton();
		button.setN("버튼명");
		button.setT("WL");
		button.setU1("http://www.popbill.com");
		button.setU2("http://test.popbill.com");
		btns[0] = button;
		
		button = new KakaoButton();
		button.setN("버튼명2");
		button.setT("WL");
		button.setU1("http://www.popbill.com");
		button.setU2("http://test.popbill.com");
		btns[1] = button;
		
		KakaoReceiver[] receivers = new KakaoReceiver[2];
		
		KakaoReceiver message = new KakaoReceiver();
		message.setReceiverNum("01043245117");
		message.setReceiverName("수신자명");
		message.setMessage("[테스트] 테스트 템플릿입니다요1");
		message.setAltMessage("대체문자 내용");
		receivers[0] = message;
		
		message = new KakaoReceiver();
		message.setReceiverNum("01043245117");
		message.setReceiverName("수신자명");
		message.setMessage("[테스트] 테스트 템플릿입니다요2");
		message.setAltMessage("대체문자 내용");
		receivers[1] = message;

		String receiptNum = kakaoService.sendFTS(testCorpNum, plusFriendID, senderNum, altSendType, 
				receivers, btns, sndDT, adsYN);
		
		assertNotNull(receiptNum);
		
		System.out.println(receiptNum);
	}
	
	@Test
	public void sendFMS_single_TEST() throws PopbillException {
		String testCorpNum = "1234567890";
		String plusFriendID = "@팝빌";
		String senderNum = "07043042993";
		String content = "친구톡 메시지 내용";
		String altContent = "대체문자 내용";
		String altSendType = "C";
		String receiverNum = "01043245117";
		String receiverName = "수신자명";
		String sndDT = "";
		Boolean adsYN = false;
		
		KakaoButton[] btns = new KakaoButton[2];
		KakaoButton button = new KakaoButton();
		button.setN("버튼명");
		button.setT("WL");
		button.setU1("http://www.popbill.com");
		button.setU2("http://test.popbill.com");
		btns[0] = button;
		
		button = new KakaoButton();
		button.setN("버튼명2");
		button.setT("WL");
		button.setU1("http://www.popbill.com");
		button.setU2("http://test.popbill.com");
		btns[1] = button;
		
		File file = new File("/Users/John/Desktop/tmp/test03.jpg");
		
		String imageURL = "http://test.popbill.com";
		
		String receiptNum = kakaoService.sendFMS(testCorpNum, plusFriendID, senderNum, content, 
				altContent, altSendType, btns, receiverNum, receiverName, sndDT, adsYN, file, imageURL);
		
		assertNotNull(receiptNum);
		
		System.out.println(receiptNum);
	}
	
	@Test
	public void sendFMS_same_TEST() throws PopbillException {
		String testCorpNum = "1234567890";
		String plusFriendID = "@팝빌";
		String senderNum = "07043042993";
		String content = "친구톡 메시지 내용";
		String altContent = "대체문자 내용";
		String altSendType = "C";
		String sndDT = "";
		Boolean adsYN = false;
		
		KakaoButton[] btns = new KakaoButton[2];
		KakaoButton button = new KakaoButton();
		button.setN("버튼명");
		button.setT("WL");
		button.setU1("http://www.popbill.com");
		button.setU2("http://test.popbill.com");
		btns[0] = button;
		
		button = new KakaoButton();
		button.setN("버튼명2");
		button.setT("WL");
		button.setU1("http://www.popbill.com");
		button.setU2("http://test.popbill.com");
		btns[1] = button;
		
		KakaoReceiver[] receivers = new KakaoReceiver[2];
		
		KakaoReceiver message = new KakaoReceiver();
		message.setReceiverNum("01043245117");
		message.setReceiverName("수신자명");
		receivers[0] = message;
		
		message = new KakaoReceiver();
		message.setReceiverNum("01043245117");
		message.setReceiverName("수신자명");
		receivers[1] = message;
		
		File file = new File("/Users/John/Desktop/tmp/test03.jpg");
		
		String imageURL = "http://test.popbill.com";

		String receiptNum = kakaoService.sendFMS(testCorpNum, plusFriendID, senderNum, content, 
				altContent, altSendType, receivers, btns, sndDT, adsYN, file, imageURL);
		
		assertNotNull(receiptNum);
		
		System.out.println(receiptNum);
	}
	
	@Test
	public void sendFMS_multi_TEST() throws PopbillException {
		String testCorpNum = "1234567890";
		String plusFriendID = "@팝빌";
		String senderNum = "07043042993";
		String altSendType = "A";
		String sndDT = "20180305200000";
		Boolean adsYN = true;
		
		KakaoButton[] btns = new KakaoButton[2];
		KakaoButton button = new KakaoButton();
		button.setN("버튼명");
		button.setT("WL");
		button.setU1("http://www.popbill.com");
		button.setU2("http://test.popbill.com");
		btns[0] = button;
		
		button = new KakaoButton();
		button.setN("버튼명2");
		button.setT("WL");
		button.setU1("http://www.popbill.com");
		button.setU2("http://test.popbill.com");
		btns[1] = button;
		
		KakaoReceiver[] receivers = new KakaoReceiver[2];
		
		KakaoReceiver message = new KakaoReceiver();
		message.setReceiverNum("01043245117");
		message.setReceiverName("수신자명");
		message.setMessage("[테스트] 테스트 템플릿입니다요1");
		message.setAltMessage("대체문자 내용");
		receivers[0] = message;
		
		message = new KakaoReceiver();
		message.setReceiverNum("01043245117");
		message.setReceiverName("수신자명");
		message.setMessage("[테스트] 테스트 템플릿입니다요2");
		message.setAltMessage("대체문자 내용");
		receivers[1] = message;
		
		File file = new File("/Users/John/Desktop/tmp/test03.jpg");
		
		String imageURL = "http://test.popbill.com";

		String receiptNum = kakaoService.sendFMS(testCorpNum, plusFriendID, senderNum, altSendType, 
				receivers, btns, sndDT, adsYN, file, imageURL);
		
		assertNotNull(receiptNum);
		
		System.out.println(receiptNum);
	}
	
	@Test
	public void cancelReserve_TEST() throws PopbillException {
		
		String receiptNum = "018030516465900001";
		
		Response response = kakaoService.cancelReserve("1234567890", receiptNum);
		assertNotNull(response);
		
		System.out.println(response.getMessage());
	}
	
	@Test
	public void getMessage_TEST() throws PopbillException {
		
		String receiptNum = "018030516492300001";
		
		KakaoSentInfo result = kakaoService.getMessages("1234567890", receiptNum);
		
		assertNotNull(result);
		
		System.out.println("contentType : " + result.getContentType());
		System.out.println("templateCode : " + result.getTemplateCode());
		System.out.println("plusFriendID : " + result.getPlusFriendID());
		System.out.println("sendNum : " + result.getSendNum());
		System.out.println("altContent : " + result.getAltContent());
		System.out.println("altSendType : " + result.getAltSendType());
		System.out.println("reserveDT : " + result.getReserveDT());
		System.out.println("adsYN : " + result.getAdsYN());
		System.out.println("imageURL : " + result.getImageURL());
		System.out.println("sendCnt : " + result.getSendCnt());
		System.out.println("successCnte : " + result.getSuccessCnt());
		System.out.println("failCnt : " + result.getFailCnt());
		System.out.println("altCnt : " + result.getAltCnt());
		System.out.println("cancelCnt : " + result.getCancelCnt());		
		
		for (int i=0; i<result.getMsgs().size(); i++)
		{
			System.out.println("state : " + result.getMsgs().get(i).getState());
			System.out.println("sendDT : " + result.getMsgs().get(i).getSendDT());
			System.out.println("receiveNum : " + result.getMsgs().get(i).getReceiveNum());
			System.out.println("receiveName : " + result.getMsgs().get(i).getReceiveName());
			System.out.println("content : " + result.getMsgs().get(i).getContent());
			System.out.println("result : " + result.getMsgs().get(i).getResult());
			System.out.println("resultDT : " + result.getMsgs().get(i).getResultDT());
			System.out.println("altContent : " + result.getMsgs().get(i).getAltContent());
			System.out.println("altContentType : " + result.getMsgs().get(i).getAltContentType());
			System.out.println("altSendDT : " + result.getMsgs().get(i).getAltSendDT());
			System.out.println("altResult : " + result.getMsgs().get(i).getAltResult());
			System.out.println("altResultDT : " + result.getMsgs().get(i).getAltResultDT());
		}
	}
	
	@Test 
	public void search_TEST() throws PopbillException {
		
		String SDate = "20180305";
		String EDate = "20180305";
		String[] State = {"0", "1","2","3","4", "5"};
		String[] Item = {"ATS", "FTS", "FMS"};
		String ReserveYN = "";
		Boolean SenderYN = false;
		int Page = 1;
		int PerPage = 3;
		String Order = "D";
		
		KakaoSearchResult result = kakaoService.search("1234567890", SDate, EDate, State, Item, ReserveYN, SenderYN, Page, PerPage, Order);
		
		assertNotNull(result);
		
		System.out.println("code : " + result.getCode());
		System.out.println("message : " + result.getMessage());
		System.out.println("total : " + result.getTotal());
		System.out.println("perPage : " + result.getPerPage());
		System.out.println("pageNum : " + result.getPageNum());
		System.out.println("pageCount : " + result.getPageCount());
				
		
		for (int i=0; i<result.getList().size(); i++)
		{
			System.out.println("****************************************************");
			System.out.println("state : " + result.getList().get(i).getState());
			System.out.println("sendDT : " + result.getList().get(i).getSendDT());
			System.out.println("receiveNum : " + result.getList().get(i).getReceiveNum());
			System.out.println("receiveName : " + result.getList().get(i).getReceiveName());
			System.out.println("content : " + result.getList().get(i).getContent());
			System.out.println("result : " + result.getList().get(i).getResult());
			System.out.println("resultDT : " + result.getList().get(i).getResultDT());
			System.out.println("altContent : " + result.getList().get(i).getAltContent());
			System.out.println("altContentType : " + result.getList().get(i).getAltContentType());
			System.out.println("altSendDT : " + result.getList().get(i).getAltSendDT());
			System.out.println("altResult : " + result.getList().get(i).getAltResult());
			System.out.println("altResultDT : " + result.getList().get(i).getAltResultDT());
			System.out.println("reserveDT : " + result.getList().get(i).getReserveDT());
		}
		
	}
	
	
	
	
}

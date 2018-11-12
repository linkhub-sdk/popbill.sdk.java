/*
 * Copyright 2006-2014 linkhub.co.kr, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0.txt
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package com.popbill.api;

import java.io.File;

import com.popbill.api.kakao.ATSTemplate;
import com.popbill.api.kakao.KakaoButton;
import com.popbill.api.kakao.KakaoReceiver;
import com.popbill.api.kakao.KakaoSearchResult;
import com.popbill.api.kakao.KakaoSentInfo;
import com.popbill.api.kakao.KakaoType;
import com.popbill.api.kakao.PlusFriendID;
import com.popbill.api.kakao.SenderNumber;

/**
 * 
 * @author JeongYooHan (code@linkhub.co.kr)
 * 
 */
public interface KakaoService extends BaseService {
	public float getUnitCost(String CorpNum, KakaoType KakaoType) 
		throws PopbillException;
	
	public String getURL(String CorpNum, String TOGO, String UserID)
		throws PopbillException;
		
	public ChargeInfo getChargeInfo(String CorpNum, KakaoType kakaoType) 
		throws PopbillException;
	
	public SenderNumber[] getSenderNumberList(String CorpNum) 
			throws PopbillException;
	
	public SenderNumber[] getSenderNumberList(String CorpNum, String UserID) 
			throws PopbillException;
	
	public PlusFriendID[] listPlusFriendID(String CorpNum)
		throws PopbillException;
	
	public PlusFriendID[] listPlusFriendID(String CorpNum, String UserID)
			throws PopbillException;
	
	public ATSTemplate[] listATSTemplate(String CorpNum)
		throws PopbillException;
	
	public ATSTemplate[] listATSTemplate(String CorpNum, String UserID)
		throws PopbillException;
	
	
	/**
	 * 알림톡 단건전송 
	 * 
	 * @param CorpNum
	 *            연동회원 사업자번호
	 * @param templateCode
	 *            알림톡 템플릿코드
	 * @param senderNum
	 *            발신번호
	 * @param content
	 *            알림톡 내용
	 * @param altContent
	 *            대체문자 내용
	 * @param altSendType
	 *            대체문자 유형
	 * @param receiverNum
	 *            수신번호
	 * @param receiverName
	 *            수신자명
	 * @param sndDT
	 *            예약일시
	 * @param UserID
	 *            연동회원 아이디
	 * @param requestNum
	 *            전송요청번호
	 * @return receiptNum 접수번호
	 * @throws PopbillException
	 */
	public String sendATS(String CorpNum, String templateCode, String senderNum, String content, String altContent, String altSendType, String receiverNum, String receiverName, String sndDT)
		throws PopbillException;
	
	public String sendATS(String CorpNum, String templateCode, String senderNum, String content, String altContent, String altSendType, String receiverNum, String receiverName, String sndDT, String UserID)
			throws PopbillException;
	
	public String sendATS(String CorpNum, String templateCode, String senderNum, String content, String altContent, String altSendType, String receiverNum, String receiverName, String sndDT, String UserID, String requestNum)
			throws PopbillException;

	/**
	 * 알림톡 동일내용 대량전송
	 * 
	 * @param CorpNum
	 *            연동회원 사업자번호
	 * @param templateCode
	 *            알림톡 템플릿코드
	 * @param senderNum
	 *            발신번호
	 * @param altSendType
	 *            대체문자 유형
	 * @param Receivers
	 *            동보수신자 배열
	 * @param sndDT
	 *            예약일시
	 * @param UserID
	 *            연동회원 아이디
	 * @param requestNum
	 *            전송요청번호
	 * @return receiptNum 접수번호
	 * @throws PopbillException
	 */
	public String sendATS(String CorpNum, String templateCode, String senderNum, String altSendType, KakaoReceiver[] Receivers, String sndDT)
			throws PopbillException;
	
	public String sendATS(String CorpNum, String templateCode, String senderNum, String altSendType, KakaoReceiver[] Receivers, String sndDT, String UserID)
			throws PopbillException;

	public String sendATS(String CorpNum, String templateCode, String senderNum, String altSendType, KakaoReceiver[] Receivers, String sndDT, String UserID, String requestNum)
			throws PopbillException;
	
	/**
	 * 알림톡 개별내용 대량전송
	 * 
	 * @param CorpNum
	 *            연동회원 사업자번호
	 * @param templateCode
	 *            알림톡 템플릿코드
	 * @param senderNum
	 *            발신번호
	 * @param content
	 *            알림톡 내용
	 * @param altContent
	 *            대체문자 내용
	 * @param altSendType
	 *            대체문자 유형
	 * @param Receivers
	 *            동보수신자 배열
	 * @param sndDT
	 *            예약일시
	 * @param UserID
	 *            연동회원 아이디
	 * @param requestNum
	 *            전송요청번호
	 * @return receiptNum 접수번호
	 * @throws PopbillException
	 */
	public String sendATS(String CorpNum, String templateCode, String senderNum, String content, String altContent, String altSendType, KakaoReceiver[] Receivers, String sndDT)
		throws PopbillException;
	
	public String sendATS(String CorpNum, String templateCode, String senderNum, String content, String altContent, String altSendType, KakaoReceiver[] Receivers, String sndDT, String UserID)
		throws PopbillException;
	
	public String sendATS(String CorpNum, String templateCode, String senderNum, String content, String altContent, String altSendType, KakaoReceiver[] Receivers, String sndDT, String UserID, String requestNum)
			throws PopbillException;
	
	/**
	 * 친구톡 단건전송
	 * 
	 * @param CorpNum
	 *            연동회원 사업자번호
	 * @param plusFriendID
	 *            플러스친구 아이디
	 * @param senderNum
	 *            발신번호
	 * @param content
	 *            친구톡 내용
	 * @param altContent
	 *            대체문자 내용
	 * @param altSendType
	 *            대체문자 유형
	 * @param Buttons
	 *            버튼 목록
	 * @param receiverNum
	 *            수신번호
	 * @param receiverName
	 *            수신자명
	 * @param sndDT
	 *            예약일시
	 * @param adsYN
	 *            광고 전송여부
	 * @param UserID
	 *            연동회원 아이디
	 * @param requestNum
	 *            전송요청번호
	 * @return receiptNum 접수번호
	 * @throws PopbillException
	 */
	public String sendFTS(String CorpNum, String plusFriendID, String senderNum, String content, String altContent, String altSendType, KakaoButton[] Buttons, String receiverNum, String receiverName, String sndDT, Boolean adsYN)
		throws PopbillException;
	
	public String sendFTS(String CorpNum, String plusFriendID, String senderNum, String content, String altContent, String altSendType, KakaoButton[] Buttons, String receiverNum, String receiverName, String sndDT, Boolean adsYN, String UserID)
			throws PopbillException;
	
	public String sendFTS(String CorpNum, String plusFriendID, String senderNum, String content, String altContent, String altSendType, KakaoButton[] Buttons, String receiverNum, String receiverName, String sndDT, Boolean adsYN, String UserID, String requestNum)
			throws PopbillException;	
	
	/**
	 * 친구톡 동일내용 대량전송
	 * 
	 * @param CorpNum
	 *            연동회원 사업자번호
	 * @param plusFriendID
	 *            플러스친구 아이디
	 * @param senderNum
	 *            발신번호
	 * @param content
	 *            친구톡 내용
	 * @param altContent
	 *            대체문자 내용
	 * @param altSendType
	 *            대체문자 유형
	 * @param Receivers
	 *            동보수신자 배열
	 * @param Buttons
	 *            버튼 목록
	 * @param sndDT
	 *            예약일시
	 * @param adsYN
	 *            광고 전송여부
	 * @param UserID
	 *            연동회원 아이디
	 * @param requestNum
	 *            전송요청번호
	 * @return receiptNum 접수번호
	 * @throws PopbillException
	 */
	public String sendFTS(String CorpNum, String plusFriendID, String senderNum, String content, String altContent, String altSendType, KakaoReceiver[] Receivers, KakaoButton[] Buttons, String sndDT, Boolean adsYN)
		throws PopbillException;
	
	public String sendFTS(String CorpNum, String plusFriendID, String senderNum, String content, String altContent, String altSendType, KakaoReceiver[] Receivers, KakaoButton[] Buttons, String sndDT, Boolean adsYN, String UserID)
		throws PopbillException;
	
	public String sendFTS(String CorpNum, String plusFriendID, String senderNum, String content, String altContent, String altSendType, KakaoReceiver[] Receivers, KakaoButton[] Buttons, String sndDT, Boolean adsYN, String UserID, String requestNum)
			throws PopbillException;

	/**
	 * 친구톡 개별내용 대량전송
	 * 
	 * @param CorpNum
	 *            연동회원 사업자번호
	 * @param plusFriendID
	 *            플러스친구 아이디
	 * @param senderNum
	 *            발신번호
	 * @param altSendType
	 *            대체문자 유형
	 * @param Receivers
	 *            동보수신자 배열
	 * @param Buttons
	 *            버튼 목록
	 * @param sndDT
	 *            예약일시
	 * @param adsYN
	 *            광고 전송여부
	 * @param UserID
	 *            연동회원 아이디
	 * @param requestNum
	 *            전송요청번호
	 * @return receiptNum 접수번호
	 * @throws PopbillException
	 */
	public String sendFTS(String CorpNum, String plusFriendID, String senderNum, String altSendType, KakaoReceiver[] Receivers, KakaoButton[] Buttons, String sndDT, Boolean adsYN)
			throws PopbillException;
	
	public String sendFTS(String CorpNum, String plusFriendID, String senderNum, String altSendType, KakaoReceiver[] Receivers, KakaoButton[] Buttons, String sndDT, Boolean adsYN, String UserID)
			throws PopbillException;
	public String sendFTS(String CorpNum, String plusFriendID, String senderNum, String altSendType, KakaoReceiver[] Receivers, KakaoButton[] Buttons, String sndDT, Boolean adsYN, String UserID, String requestNum)
			throws PopbillException;
	
	/**
	 * 친구톡 이미지 단건전송
	 * 
	 * @param CorpNum
	 *            연동회원 사업자번호
	 * @param plusFriendID
	 *            플러스친구 아이디
	 * @param senderNum
	 *            발신번호
	 * @param content
	 *            친구톡 내용
	 * @param altContent
	 *            대체문자 내용
	 * @param altSendType
	 *            대체문자 유형
	 * @param Buttons
	 *            버튼 목록
	 * @param receiverNum
	 *            수신번호
	 * @param receiverName
	 *            수신자명
	 * @param sndDT
	 *            예약일시
	 * @param adsYN
	 *            광고 전송여부
	 * @param file
	 *            첨부파일 위치
	 * @param imageURL
	 *            친구톡 이미지 링크 URL
	 * @param UserID
	 *            연동회원 아이디
	 * @param requestNum
	 *            전송요청번호
	 * @return receiptNum 접수번호
	 * @throws PopbillException
	 */
	public String sendFMS(String CorpNum, String plusFriendID, String senderNum, String content, String altContent, String altSendType, KakaoButton[] Buttons, String receiverNum, String receiverName, String sndDT, Boolean adsYN, File file, String imageURL)
			throws PopbillException;
		
	public String sendFMS(String CorpNum, String plusFriendID, String senderNum, String content, String altContent, String altSendType, KakaoButton[] Buttons, String receiverNum, String receiverName, String sndDT, Boolean adsYN, File file, String imageURL, String UserID)
			throws PopbillException;
	
	public String sendFMS(String CorpNum, String plusFriendID, String senderNum, String content, String altContent, String altSendType, KakaoButton[] Buttons, String receiverNum, String receiverName, String sndDT, Boolean adsYN, File file, String imageURL, String UserID, String requestNum)
			throws PopbillException;
	
	/**
	 * 친구톡 이미지 동일내용 대량전송
	 * 
	 * @param CorpNum
	 *            연동회원 사업자번호
	 * @param plusFriendID
	 *            플러스친구 아이디
	 * @param senderNum
	 *            발신번호
	 * @param content
	 *            친구톡 내용
	 * @param altContent
	 *            대체문자 내용
	 * @param altSendType
	 *            대체문자 유형
	 * @param Receivers
	 *            동보수신자 배열
	 * @param Buttons
	 *            버튼 목록
	 * @param sndDT
	 *            예약일시
	 * @param adsYN
	 *            광고 전송여부
	 * @param file
	 *            첨부파일 위치
	 * @param imageURL
	 *            친구톡 이미지 링크 URL
	 * @param UserID
	 *            연동회원 아이디
	 * @param requestNum
	 *            전송요청번호
	 * @return receiptNum 접수번호
	 * @throws PopbillException
	 */
	public String sendFMS(String CorpNum, String plusFriendID, String senderNum, String content, String altContent, String altSendType, KakaoReceiver[] Receivers, KakaoButton[] Buttons, String sndDT, Boolean adsYN, File file, String imageURL)
			throws PopbillException;
	
	public String sendFMS(String CorpNum, String plusFriendID, String senderNum, String content, String altContent, String altSendType, KakaoReceiver[] Receivers, KakaoButton[] Buttons, String sndDT, Boolean adsYN, File file, String imageURL, String UserID)
			throws PopbillException;
	
	public String sendFMS(String CorpNum, String plusFriendID, String senderNum, String content, String altContent, String altSendType, KakaoReceiver[] Receivers, KakaoButton[] Buttons, String sndDT, Boolean adsYN, File file, String imageURL, String UserID, String requestNum)
			throws PopbillException;
	
	/**
	 * 친구톡 이미지 개별내용 대량전송
	 * 
	 * @param CorpNum
	 *            연동회원 사업자번호
	 * @param plusFriendID
	 *            플러스친구 아이디
	 * @param senderNum
	 *            발신번호
	 * @param altSendType
	 *            대체문자 유형
	 * @param Receivers
	 *            동보수신자 배열
	 * @param Buttons
	 *            버튼 목록
	 * @param sndDT
	 *            예약일시
	 * @param adsYN
	 *            광고 전송여부
	 * @param file
	 *            첨부파일 위치
	 * @param imageURL
	 *            친구톡 이미지 링크 URL
	 * @param UserID
	 *            연동회원 아이디
	 * @param requestNum
	 *            전송요청번호
	 * @return receiptNum 접수번호
	 * @throws PopbillException
	 */
	public String sendFMS(String CorpNum, String plusFriendID, String senderNum, String altSendType, KakaoReceiver[] Receivers, KakaoButton[] Buttons, String sndDT, Boolean adsYN, File file, String imageURL)
			throws PopbillException;
	
	public String sendFMS(String CorpNum, String plusFriendID, String senderNum, String altSendType, KakaoReceiver[] Receivers, KakaoButton[] Buttons, String sndDT, Boolean adsYN, File file, String imageURL, String UserID)
			throws PopbillException;
	
	public String sendFMS(String CorpNum, String plusFriendID, String senderNum, String altSendType, KakaoReceiver[] Receivers, KakaoButton[] Buttons, String sndDT, Boolean adsYN, File file, String imageURL, String UserID, String requestNum)
			throws PopbillException;
	
	/**
	 * 카카오톡 전송내역 상세조회
	 * 
	 * @param CorpNum
	 *            연동회원 사업자번호
	 * @param receiptNum
	 *            접수번호
	 * @param UserID
	 *            연동회원 아이디
	 * @return 카카오톡 전송내역 (see com.popbill.api.kakao.KakaoSentInfo)
	 * @throws PopbillException
	 */
	public KakaoSentInfo getMessages(String CorpNum, String receiptNum) 
			throws PopbillException;
	
	public KakaoSentInfo getMessages(String CorpNum, String receiptNum, String UserID) 
			throws PopbillException;
	
	/**
	 * 카카오톡 전송내역 상세조회
	 * 
	 * @param CorpNum
	 *            연동회원 사업자번호
	 * @param requestNum
	 *            전송요청번호
	 * @param UserID
	 *            연동회원 아이디
	 * @return 카카오톡 전송내역 (see com.popbill.api.kakao.KakaoSentInfo)
	 * @throws PopbillException
	 */
	public KakaoSentInfo getMessagesRN(String CorpNum, String requestNum) 
			throws PopbillException;

	public KakaoSentInfo getMessagesRN(String CorpNum, String requestNum, String UserID) 
			throws PopbillException;
	
	/**
	 * 전송내역 목록조회
	 * 
	 * @param CorpNum
	 *            연동회원 사업자번호
	 * @param SDate
	 *            시작일자
	 * @param EDate
	 *            종료일자
	 * @param State
	 *            전송상태
	 * @param Item
	 *            검색대상
	 * @param ReserveYN
	 *            예약여부
	 * @param SenderYN
	 *            개인조회여부
	 * @param Page
	 *            페이지 번호
	 * @param PerPage
	 *            페이지당 검색개수
	 * @param Order
	 *            정렬방향
	 * @param UserID
	 *            연동회원 아이디
	 * @param QString
	 *            조회검색어 / 수신자명 기재 
	 * @return 카카오톡 목록조회 결과 (see com.popbill.api.kakao.KakaoSearchResult) 
	 * @throws PopbillException
	 */
	public KakaoSearchResult search(String CorpNum, String SDate, String EDate, String[] State, String[] Item, String ReserveYN, Boolean SenderYN, int Page, int PerPage, String Order)
			throws PopbillException;
	
	public KakaoSearchResult search(String CorpNum, String SDate, String EDate, String[] State, String[] Item, String ReserveYN, Boolean SenderYN, int Page, int PerPage, String Order, String UserID)
			throws PopbillException;

	public KakaoSearchResult search(String CorpNum, String SDate, String EDate, String[] State, String[] Item, String ReserveYN, Boolean SenderYN, int Page, int PerPage, String Order, String UserID, String QString)
			throws PopbillException;

	/**
	 * 예약전송 취소
	 * @param CorpNum
	 *            연동회원 사업자번호
	 * @param receiptNum
	 *            접수번호
	 * @param UserID
	 *            연동회원 아이디
	 * @return Response 응답
	 * @throws PopbillException
	 */
	public Response cancelReserve(String CorpNum, String receiptNum) 
			throws PopbillException;
		
	public Response cancelReserve(String CorpNum, String receiptNum, String UserID) 
			throws PopbillException;
	
	/**
	 * 예약전송 취소
	 * @param CorpNum
	 *            연동회원 사업자번호
	 * @param requestNum
	 *            전송요청번호
	 * @param UserID
	 *            연동회원 아이디
	 * @return Response 응답
	 * @throws PopbillException
	 */
	public Response cancelReserveRN(String CorpNum, String requestNum) 
			throws PopbillException;
		
	public Response cancelReserveRN(String CorpNum, String requestNum, String UserID) 
			throws PopbillException;


	/**
	 * 플러스친구 계정관리 팝업 URL을 반환
	 *
	 * @param CorpNum
	 * 			연동회원 사업자번호
	 * @param UserID
	 * 			연동회원 유저아이디
	 * @return 팝빌 URL (AccessToken값 포함. Token값은 응답후 30초까지만 유효함)
	 * @throws PopbillException
	 */
	public String GetPlusFriendMgtURL(String CorpNum, String UserID) throws PopbillException;


	/**
	 * 발신번호 관리 팝업 URL을 반환
	 *
	 * @param CorpNum
	 * 			연동회원 사업자번호
	 * @param UserID
	 * 			연동회원 유저아이디
	 * @return 팝빌 URL (AccessToken값 포함. Token값은 응답후 30초까지만 유효함)
	 * @throws PopbillException
	 */
	public String GetSenderNumberMgtURL(String CorpNum, String UserID) throws PopbillException;


	/**
	 * 알림톡 템플릿관리 팝업 URL을 반환
	 *
	 * @param CorpNum
	 * 			연동회원 사업자번호
	 * @param UserID
	 * 			연동회원 유저아이디
	 * @return 팝빌 URL (AccessToken값 포함. Token값은 응답후 30초까지만 유효함)
	 * @throws PopbillException
	 */
	public String GetATSTemplateMgtURL(String CorpNum, String UserID) throws PopbillException;


	/**
	 *  카카오톡 전송내역 팝업 URL을 반환
	 *
	 * @param CorpNum
	 * 			연동회원 사업자번호
	 * @param UserID
	 * 			연동회원 유저아이디
	 * @return 팝빌 URL (AccessToken값 포함. Token값은 응답후 30초까지만 유효함)
	 * @throws PopbillException
	 */
	public String GetSentListURL(String CorpNum, String UserID) throws PopbillException;

}
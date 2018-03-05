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
	
	public Response cancelReserve(String CorpNum, String receiptNum) 
		throws PopbillException;
	
	public Response cancelReserve(String CorpNum, String receiptNum, String UserID) 
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
	 * @param templateCode
	 * @param senderNum
	 * @param content
	 * @param altContent
	 * @param altSendType
	 * @param receiverNum
	 * @param receiverName
	 * @param sndDT
	 * @return
	 * @throws PopbillException
	 */
	public String sendATS(String CorpNum, String templateCode, String senderNum, String content, String altContent, String altSendType, String receiverNum, String receiverName, String sndDT)
		throws PopbillException;
	
	public String sendATS(String CorpNum, String templateCode, String senderNum, String content, String altContent, String altSendType, String receiverNum, String receiverName, String sndDT, String UserID)
			throws PopbillException;
	
	/**
	 * 알림톡 동일내용 대량전송
	 * 
	 * @param CorpNum
	 * @param templateCode
	 * @param senderNum
	 * @param altSendType
	 * @param Receivers
	 * @param sndDT
	 * @return
	 * @throws PopbillException
	 */
	public String sendATS(String CorpNum, String templateCode, String senderNum, String altSendType, KakaoReceiver[] Receivers, String sndDT)
			throws PopbillException;
	
	public String sendATS(String CorpNum, String templateCode, String senderNum, String altSendType, KakaoReceiver[] Receivers, String sndDT, String UserID)
			throws PopbillException;
	
	/**
	 * 알림톡 개별내용 대량전송
	 * 
	 * @param CorpNum
	 * @param templateCode
	 * @param senderNum
	 * @param content
	 * @param altContent
	 * @param altSendType
	 * @param Receivers
	 * @param sndDT
	 * @return
	 * @throws PopbillException
	 */
	public String sendATS(String CorpNum, String templateCode, String senderNum, String content, String altContent, String altSendType, KakaoReceiver[] Receivers, String sndDT)
		throws PopbillException;
	
	public String sendATS(String CorpNum, String templateCode, String senderNum, String content, String altContent, String altSendType, KakaoReceiver[] Receivers, String sndDT, String UserID)
		throws PopbillException;
	
	
	/**
	 * 친구톡 단건전송
	 * @param CorpNum
	 * @param plusFriendID
	 * @param senderNum
	 * @param content
	 * @param altContent
	 * @param altSendType
	 * @param Buttons
	 * @param receiverNum
	 * @param receiverName
	 * @param sndDT
	 * @param adsYN
	 * @return
	 * @throws PopbillException
	 */
	public String sendFTS(String CorpNum, String plusFriendID, String senderNum, String content, String altContent, String altSendType, KakaoButton[] Buttons, String receiverNum, String receiverName, String sndDT, Boolean adsYN)
		throws PopbillException;
	
	public String sendFTS(String CorpNum, String plusFriendID, String senderNum, String content, String altContent, String altSendType, KakaoButton[] Buttons, String receiverNum, String receiverName, String sndDT, Boolean adsYN, String UserID)
			throws PopbillException;
	
	
	
	/**
	 * 친구톡 동일내용 대량전송
	 * @param CorpNum
	 * @param plusFriendID
	 * @param senderNum
	 * @param content
	 * @param altContent
	 * @param altSendType
	 * @param Receivers
	 * @param Buttons
	 * @param sndDT
	 * @param adsYN
	 * @return
	 * @throws PopbillException
	 */
	public String sendFTS(String CorpNum, String plusFriendID, String senderNum, String content, String altContent, String altSendType, KakaoReceiver[] Receivers, KakaoButton[] Buttons, String sndDT, Boolean adsYN)
		throws PopbillException;
	
	public String sendFTS(String CorpNum, String plusFriendID, String senderNum, String content, String altContent, String altSendType, KakaoReceiver[] Receivers, KakaoButton[] Buttons, String sndDT, Boolean adsYN, String UserID)
		throws PopbillException;
	
	/**
	 * 친구톡 개별내용 대량전송
	 * @param CorpNum
	 * @param plusFriendID
	 * @param senderNum
	 * @param altSendType
	 * @param Receivers
	 * @param Buttons
	 * @param sndDT
	 * @param adsYN
	 * @return
	 * @throws PopbillException
	 */
	public String sendFTS(String CorpNum, String plusFriendID, String senderNum, String altSendType, KakaoReceiver[] Receivers, KakaoButton[] Buttons, String sndDT, Boolean adsYN)
			throws PopbillException;
	
	public String sendFTS(String CorpNum, String plusFriendID, String senderNum, String altSendType, KakaoReceiver[] Receivers, KakaoButton[] Buttons, String sndDT, Boolean adsYN, String UserID)
			throws PopbillException;
	
	
	/**
	 * 친구톡 이미지 단건전송
	 * 
	 * @param CorpNum
	 * @param plusFriendID
	 * @param senderNum
	 * @param content
	 * @param altContent
	 * @param altSendType
	 * @param Buttons
	 * @param receiverNum
	 * @param receiverName
	 * @param sndDT
	 * @param adsYN
	 * @param file
	 * @param imageURL
	 * @return
	 * @throws PopbillException
	 */
	public String sendFMS(String CorpNum, String plusFriendID, String senderNum, String content, String altContent, String altSendType, KakaoButton[] Buttons, String receiverNum, String receiverName, String sndDT, Boolean adsYN, File file, String imageURL)
			throws PopbillException;
		
	public String sendFMS(String CorpNum, String plusFriendID, String senderNum, String content, String altContent, String altSendType, KakaoButton[] Buttons, String receiverNum, String receiverName, String sndDT, Boolean adsYN, File file, String imageURL, String UserID)
			throws PopbillException;
	
	
	/**
	 * 친구톡 이미지 동일내용 대량전송
	 * 
	 * @param CorpNum
	 * @param plusFriendID
	 * @param senderNum
	 * @param content
	 * @param altContent
	 * @param altSendType
	 * @param Receivers
	 * @param Buttons
	 * @param sndDT
	 * @param adsYN
	 * @param file
	 * @param imageURL
	 * @return
	 * @throws PopbillException
	 */
	public String sendFMS(String CorpNum, String plusFriendID, String senderNum, String content, String altContent, String altSendType, KakaoReceiver[] Receivers, KakaoButton[] Buttons, String sndDT, Boolean adsYN, File file, String imageURL)
			throws PopbillException;
	
	public String sendFMS(String CorpNum, String plusFriendID, String senderNum, String content, String altContent, String altSendType, KakaoReceiver[] Receivers, KakaoButton[] Buttons, String sndDT, Boolean adsYN, File file, String imageURL, String UserID)
			throws PopbillException;
	
	
	/**
	 * 친구톡 이미지 개별내용 대량전송
	 * 
	 * @param CorpNum
	 * @param plusFriendID
	 * @param senderNum
	 * @param altSendType
	 * @param Receivers
	 * @param Buttons
	 * @param sndDT
	 * @param adsYN
	 * @param file
	 * @param imageURL
	 * @return
	 * @throws PopbillException
	 */
	public String sendFMS(String CorpNum, String plusFriendID, String senderNum, String altSendType, KakaoReceiver[] Receivers, KakaoButton[] Buttons, String sndDT, Boolean adsYN, File file, String imageURL)
			throws PopbillException;
	
	public String sendFMS(String CorpNum, String plusFriendID, String senderNum, String altSendType, KakaoReceiver[] Receivers, KakaoButton[] Buttons, String sndDT, Boolean adsYN, File file, String imageURL, String UserID)
			throws PopbillException;
	
	/**
	 * 카카오톡 전송내역 상세조회
	 * @param CorpNum
	 * @param receiptNum
	 * @return
	 * @throws PopbillException
	 */
	public KakaoSentInfo getMessages(String CorpNum, String receiptNum) 
			throws PopbillException;
	
	public KakaoSentInfo getMessages(String CorpNum, String receiptNum, String UserID) 
			throws PopbillException;
	
	
	/**
	 * 전송내역 목록조회
	 * @param CorpNum
	 * @param SDate
	 * @param EDate
	 * @param State
	 * @param Item
	 * @param ReserveYN
	 * @param SenderYN
	 * @param Page
	 * @param PerPage
	 * @param Order
	 * @return
	 * @throws PopbillException
	 */
	public KakaoSearchResult search(String CorpNum, String SDate, String EDate, String[] State, String[] Item, String ReserveYN, Boolean SenderYN, int Page, int PerPage, String Order)
			throws PopbillException;
	
	public KakaoSearchResult search(String CorpNum, String SDate, String EDate, String[] State, String[] Item, String ReserveYN, Boolean SenderYN, int Page, int PerPage, String Order, String UserID)
			throws PopbillException;
	
	
}

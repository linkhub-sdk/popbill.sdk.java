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
import java.util.Date;

import com.popbill.api.fax.FAXSearchResult;
import com.popbill.api.fax.FaxResult;
import com.popbill.api.fax.Receiver;
import com.popbill.api.fax.SenderNumber;

/**
 * Fax Service Interface.
 * 
 * @author KimSeongjun
 * @version 1.0.0
 */
public interface FaxService extends BaseService {

	/**
	 * 회원의 팩스 전송단가 확인
	 * 
	 * @param corpNum
	 *            연동회원 사업자번호
	 * @return 단가 (ex. 55.0)
	 * @throws PopbillException
	 */
	public float getUnitCost(String corpNum) throws PopbillException;

	/**
	 * 팝빌 팩스전송 관련 URL 확인. 반환한 url은 30초이내에 브라우져에 표시하여야 함.
	 * 
	 * @param corpNum
	 *            연동회원 사업자번호.
	 * @param TOGO
	 *            지정값. (BOX : 팩스 전송 내역 조회 팝업)
	 * @return 팝빌 URL (AccessToken값 포함. Token값은 응답후 30초까지만 유효함)
	 * @throws PopbillException
	 */
	public String getURL(String corpNum, String TOGO)
			throws PopbillException;
	
	/**
	 * 팝빌 팩스전송 관련 URL 확인. 반환한 url은 30초이내에 브라우져에 표시하여야 함.
	 * 
	 * @param corpNum
	 *            연동회원 사업자번호.
	 * @param userID
	 *            연동회원의 회원아이디
	 * @param TOGO
	 *            지정값. (BOX : 팩스 전송 내역 조회 팝업)
	 * @return 팝빌 URL (AccessToken값 포함. Token값은 응답후 30초까지만 유효함)
	 * @throws PopbillException
	 */
	public String getURL(String corpNum, String userID, String TOGO)
			throws PopbillException;

	/**
	 * 팩스전송 단일파일 단일 수신자.
	 * 
	 * @param corpNum
	 *            연동회원 사업자번호
	 * @param sendNum
	 *            발신번호
	 * @param receiveNum
	 *            수신번호
	 * @param receiveName
	 *            수신자 명칭
	 * @param file
	 *            전송파일
	 * @param reserveDT
	 *            예약일시, (형태 yyyyMMddHHmmss)
	 * @return receiptNum 접수번호
	 * @throws PopbillException
	 */
	public String sendFAX(String corpNum, String sendNum, String receiveNum,
			String receiveName, File file, Date reserveDT)
			throws PopbillException;
	
	/**
	 * 팩스전송 단일파일 단일 수신자. (광고팩스)
	 * 
	 * @param corpNum
	 *            연동회원 사업자번호
	 * @param sendNum
	 *            발신번호
	 * @param receiveNum
	 *            수신번호
	 * @param receiveName
	 *            수신자 명칭
	 * @param file
	 *            전송파일
	 * @param reserveDT
	 *            예약일시, (형태 yyyyMMddHHmmss)
	 * @param adsYN
	 * 			  광고팩스 전송여부
	 * @return receiptNum 접수번호
	 * @throws PopbillException
	 */
	public String sendFAX(String corpNum, String sendNum, String receiveNum,
			String receiveName, File file, Date reserveDT, Boolean adsYN)
			throws PopbillException;
	
	/**
	 * 팩스전송 단일파일 단일 수신자. (광고팩스)
	 * 
	 * @param corpNum
	 *            연동회원 사업자번호
	 * @param sendNum
	 *            발신번호
	 * @param receiveNum
	 *            수신번호
	 * @param receiveName
	 *            수신자 명칭
	 * @param file
	 *            전송파일
	 * @param reserveDT
	 *            예약일시, (형태 yyyyMMddHHmmss)
	 * @param adsYN
	 * 			  광고팩스 전송여부
	 * @param requestNum
	 * 			  전송요청번호
	 * @return receiptNum 접수번호
	 * @throws PopbillException
	 */
	public String sendFAX(String corpNum, String sendNum, String receiveNum,
			String receiveName, File file, Date reserveDT, Boolean adsYN, String requestNum)
			throws PopbillException;
	
	/**
	 * 팩스전송 단일파일 단일 수신자.
	 * 
	 * @param corpNum
	 *            연동회원 사업자번호
	 * @param sendNum
	 *            발신번호
	 * @param receiveNum
	 *            수신번호
	 * @param receiveName
	 *            수신자 명칭
	 * @param file
	 *            전송파일
	 * @param reserveDT
	 *            예약일시, (형태 yyyyMMddHHmmss)
	 * @param userID
	 *            연동회원 아이디
	 * @return receiptNum 접수번호
	 * @throws PopbillException
	 */
	public String sendFAX(String corpNum, String sendNum, String receiveNum,
			String receiveName, File file, Date reserveDT, String userID)
			throws PopbillException;
	
	
	/**
	 * 팩스전송 단일파일 단일 수신자 (광고팩스).
	 * 
	 * @param corpNum
	 *            연동회원 사업자번호
	 * @param sendNum
	 *            발신번호
	 * @param receiveNum
	 *            수신번호
	 * @param receiveName
	 *            수신자 명칭
	 * @param file
	 *            전송파일
	 * @param reserveDT
	 *            예약일시, (형태 yyyyMMddHHmmss)
	 * @param userID
	 *            연동회원 아이디
	 * @param adsYN
	 * 			  광고팩스 전송여부
	 * @return receiptNum 접수번호
	 * @throws PopbillException
	 */
	public String sendFAX(String corpNum, String sendNum, String receiveNum,
			String receiveName, File file, Date reserveDT, String userID, Boolean adsYN)
			throws PopbillException;
	
	/**
	 * 팩스전송 단일파일 단일 수신자 (팩스제목)
	 * 
	 * @param corpNum
	 *            연동회원 사업자번호
	 * @param sendNum
	 *            발신번호
	 * @param receiveNum
	 *            수신번호
	 * @param receiveName
	 *            수신자 명칭
	 * @param file
	 *            전송파일
	 * @param reserveDT
	 *            예약일시, (형태 yyyyMMddHHmmss)
	 * @param userID
	 *            연동회원 아이디
	 * @param adsYN
	 * 			  광고팩스 전송여부
	 * @param title
	 * 			  팩스 제목
	 * @return receiptNum 접수번호
	 * @throws PopbillException
	 */
	public String sendFAX(String corpNum, String sendNum, String receiveNum,
			String receiveName, File file, Date reserveDT, String userID, Boolean adsYN,
			String title)
			throws PopbillException;
	
	/**
	 * 팩스전송 단일파일 단일 수신자 (팩스제목)
	 * 
	 * @param corpNum
	 *            연동회원 사업자번호
	 * @param sendNum
	 *            발신번호
	 * @param receiveNum
	 *            수신번호
	 * @param receiveName
	 *            수신자 명칭
	 * @param file
	 *            전송파일
	 * @param reserveDT
	 *            예약일시, (형태 yyyyMMddHHmmss)
	 * @param userID
	 *            연동회원 아이디
	 * @param adsYN
	 * 			  광고팩스 전송여부
	 * @param title
	 * 			  팩스 제목
	 * @param requestNum
	 * 			  전송요청번호
	 * @return receiptNum 접수번호
	 * @throws PopbillException
	 */
	public String sendFAX(String corpNum, String sendNum, String receiveNum,
			String receiveName, File file, Date reserveDT, String userID, Boolean adsYN,
			String title, String requestNum)
			throws PopbillException;

	/**
	 * 팩스전송 단일파일 동보전송.
	 * 
	 * @param corpNum
	 *            연동회원 사업자번호
	 * @param sendNum
	 *            발신번호
	 * @param receivers
	 *            동보수신자 배열
	 * @param file
	 *            전송파일
	 * @param reserveDT
	 *            예약일시, (형태 yyyyMMddHHmmss)
	 * @return receiptNum 접수번호
	 * @throws PopbillException
	 */
	public String sendFAX(String corpNum, String sendNum, Receiver[] receivers,
			File file, Date reserveDT) throws PopbillException;
	
	/**
	 * 팩스전송 단일파일 동보전송. (광고팩스)
	 * 
	 * @param corpNum
	 *            연동회원 사업자번호
	 * @param sendNum
	 *            발신번호
	 * @param receivers
	 *            동보수신자 배열
	 * @param file
	 *            전송파일
	 * @param reserveDT
	 *            예약일시, (형태 yyyyMMddHHmmss)
	 * @param adsYN
	 * 			  광고팩스 전송여부
	 * @return receiptNum 접수번호
	 * @throws PopbillException
	 */
	public String sendFAX(String corpNum, String sendNum, Receiver[] receivers,
			File file, Date reserveDT, Boolean adsYN) throws PopbillException;	
	
	/**
	 * 팩스전송 단일파일 동보전송. (팩스제목)
	 * 
	 * @param corpNum
	 *            연동회원 사업자번호
	 * @param sendNum
	 *            발신번호
	 * @param receivers
	 *            동보수신자 배열
	 * @param file
	 *            전송파일
	 * @param reserveDT
	 *            예약일시, (형태 yyyyMMddHHmmss)
	 * @param adsYN
	 * 			  광고팩스 전송여부
	 * @param title
	 * 			  팩스제목
	 * @return receiptNum 접수번호
	 * @throws PopbillException
	 */
	public String sendFAX(String corpNum, String sendNum, Receiver[] receivers,
			File file, Date reserveDT, Boolean adsYN, String title) throws PopbillException;	
	
	/**
	 * 팩스전송 단일파일 동보전송. (팩스제목)
	 * 
	 * @param corpNum
	 *            연동회원 사업자번호
	 * @param sendNum
	 *            발신번호
	 * @param receivers
	 *            동보수신자 배열
	 * @param file
	 *            전송파일
	 * @param reserveDT
	 *            예약일시, (형태 yyyyMMddHHmmss)
	 * @param adsYN
	 * 			  광고팩스 전송여부
	 * @param title
	 * 			  팩스제목
	 * @param requestNum
	 * 			  전송요청번호
	 * @return receiptNum 접수번호
	 * @throws PopbillException
	 */
	public String sendFAX(String corpNum, String sendNum, Receiver[] receivers,
			File file, Date reserveDT, Boolean adsYN, String title, String requestNum) throws PopbillException;	

	/**
	 * 팩스전송 단일파일 동보전송.
	 * 
	 * @param corpNum
	 *            연동회원 사업자번호
	 * @param sendNum
	 *            발신번호
	 * @param receivers
	 *            동보수신자 배열
	 * @param file
	 *            전송파일
	 * @param reserveDT
	 *            예약일시, (형태 yyyyMMddHHmmss)
	 * @param userID
	 *            연동회원 아이디
	 * @return receiptNum 접수번호
	 * @throws PopbillException
	 */
	public String sendFAX(String corpNum, String sendNum, Receiver[] receivers,
			File file, Date reserveDT, String userID) throws PopbillException;
		
	/**
	 * 팩스전송 단일파일 동보전송. (팩스제목)
	 * 
	 * @param corpNum
	 *            연동회원 사업자번호
	 * @param sendNum
	 *            발신번호
	 * @param receivers
	 *            동보수신자 배열
	 * @param file
	 *            전송파일
	 * @param reserveDT
	 *            예약일시, (형태 yyyyMMddHHmmss)
	 * @param userID
	 *            연동회원 아이디
	 * @param title 
	 * 			  팩스제목
	 * @return receiptNum 접수번호
	 * @throws PopbillException
	 */
	public String sendFAX(String corpNum, String sendNum, Receiver[] receivers,
			File file, Date reserveDT, String userID, String title) throws PopbillException;

	/**
	 * 팩스전송 단일파일 동보전송. (팩스제목)
	 * 
	 * @param corpNum
	 *            연동회원 사업자번호
	 * @param sendNum
	 *            발신번호
	 * @param receivers
	 *            동보수신자 배열
	 * @param file
	 *            전송파일
	 * @param reserveDT
	 *            예약일시, (형태 yyyyMMddHHmmss)
	 * @param userID
	 *            연동회원 아이디
	 * @param title 
	 * 			  팩스제목
	 * @param requestNum 
	 * 			  전송요청번호
	 * @return receiptNum 접수번호
	 * @throws PopbillException
	 */
	public String sendFAX(String corpNum, String sendNum, Receiver[] receivers,
			File file, Date reserveDT, String userID, String title, String requestNum) throws PopbillException;

	/**
	 * 팩스전송 단일파일 동보전송. (광고팩스)
	 * 
	 * @param corpNum
	 *            연동회원 사업자번호
	 * @param sendNum
	 *            발신번호
	 * @param receivers
	 *            동보수신자 배열
	 * @param file
	 *            전송파일
	 * @param reserveDT
	 *            예약일시, (형태 yyyyMMddHHmmss)
	 * @param userID
	 *            연동회원 아이디
	 * @param adsYN
	 * 			  광고팩스 전송여부
	 * @return receiptNum 접수번호
	 * @throws PopbillException
	 */
	public String sendFAX(String corpNum, String sendNum, Receiver[] receivers,
			File file, Date reserveDT, String userID, Boolean adsYN) throws PopbillException;	
	
	/**
	 * 팩스전송 단일파일 동보전송. (팩스제목)
	 * 
	 * @param corpNum
	 *            연동회원 사업자번호
	 * @param sendNum
	 *            발신번호
	 * @param receivers
	 *            동보수신자 배열
	 * @param file
	 *            전송파일
	 * @param reserveDT
	 *            예약일시, (형태 yyyyMMddHHmmss)
	 * @param userID
	 *            연동회원 아이디
	 * @param adsYN
	 * 			  광고팩스 전송여부
	 * @param title 
	 * 			  팩스제목
	 * @return receiptNum 접수번호
	 * @throws PopbillException
	 */
	public String sendFAX(String corpNum, String sendNum, Receiver[] receivers,
			File file, Date reserveDT, String userID, Boolean adsYN, String title)
					throws PopbillException;	
	
	/**
	 * 팩스전송 단일파일 동보전송. (팩스제목)
	 * 
	 * @param CorpNum
	 *            연동회원 사업자번호
	 * @param sendNum
	 *            발신번호
	 * @param receivers
	 *            동보수신자 배열
	 * @param file
	 *            전송파일
	 * @param reserveDT
	 *            예약일시, (형태 yyyyMMddHHmmss)
	 * @param userID
	 *            연동회원 아이디
	 * @param adsYN
	 * 			  광고팩스 전송여부
	 * @param title 
	 * 			  팩스제목
	 * @param requestNum 
	 * 			  전송요청번호
	 * @return receiptNum 접수번호
	 * @throws PopbillException
	 */
	public String sendFAX(String CorpNum, String sendNum, Receiver[] receivers,
			File file, Date reserveDT, String userID, Boolean adsYN, String title, String requestNum)
					throws PopbillException;	

	/**
	 * 팩스전송 다중파일(최대5개) 단일 수신자.
	 * 
	 * @param corpNum
	 *            연동회원 사업자번호
	 * @param sendNum
	 *            발신번호
	 * @param receiveNum
	 *            수신번호
	 * @param receiveName
	 *            수신자 명칭
	 * @param files
	 *            전송파일 Stream 배열
	 * @param reserveDT
	 *            예약일시, (형태 yyyyMMddHHmmss)
	 * @return receiptNum 접수번호
	 * @throws PopbillException
	 */
	public String sendFAX(String corpNum, String sendNum, String receiveNum,
			String receiveName, File[] files, Date reserveDT)
			throws PopbillException;

	/**
	 * 팩스전송 다중파일(최대5개) 단일 수신자.
	 * 
	 * @param corpNum
	 *            연동회원 사업자번호
	 * @param sendNum
	 *            발신번호
	 * @param receiveNum
	 *            수신번호
	 * @param receiveName
	 *            수신자 명칭
	 * @param files
	 *            전송파일 Stream 배열
	 * @param reserveDT
	 *            예약일시, (형태 yyyyMMddHHmmss)
	 * @param adsYN
	 * 			  광고팩스 전송여부
	 * @return receiptNum 접수번호
	 * @throws PopbillException
	 */
	public String sendFAX(String corpNum, String sendNum, String receiveNum,
			String receiveName, File[] files, Date reserveDT, Boolean adsYN)
			throws PopbillException;
	
	/**
	 * 팩스전송 다중파일(최대5개) 단일 수신자.
	 * 
	 * @param corpNum
	 *            연동회원 사업자번호
	 * @param sendNum
	 *            발신번호
	 * @param receiveNum
	 *            수신번호
	 * @param receiveName
	 *            수신자 명칭
	 * @param files
	 *            전송파일 Stream 배열
	 * @param reserveDT
	 *            예약일시, (형태 yyyyMMddHHmmss)
	 * @param adsYN
	 * 			  광고팩스 전송여부
	 * @param title 
	 * 			팩스제목
	 * @return receiptNum 접수번호
	 * @throws PopbillException
	 */
	public String sendFAX(String corpNum, String sendNum, String receiveNum,
			String receiveName, File[] files, Date reserveDT, Boolean adsYN, String title)
			throws PopbillException;
	
	/**
	 * 팩스전송 다중파일(최대5개) 단일 수신자.
	 * 
	 * @param corpNum
	 *            연동회원 사업자번호
	 * @param sendNum
	 *            발신번호
	 * @param receiveNum
	 *            수신번호
	 * @param receiveName
	 *            수신자 명칭
	 * @param files
	 *            전송파일 Stream 배열
	 * @param reserveDT
	 *            예약일시, (형태 yyyyMMddHHmmss)
	 * @param adsYN
	 * 			  광고팩스 전송여부
	 * @param title 
	 * 			팩스제목
	 * @param requestNum 
	 * 			전송요청번호
	 * @return receiptNum 접수번호
	 * @throws PopbillException
	 */
	public String sendFAX(String corpNum, String sendNum, String receiveNum,
			String receiveName, File[] files, Date reserveDT, Boolean adsYN, String title, String requestNum)
			throws PopbillException;

	/**
	 * 팩스전송 다중파일(최대5개) 단일 수신자.
	 * 
	 * @param corpNum
	 *            연동회원 사업자번호
	 * @param sendNum
	 *            발신번호
	 * @param receiveNum
	 *            수신번호
	 * @param receiveName
	 *            수신자 명칭
	 * @param files
	 *            전송파일 Stream 배열
	 * @param reserveDT
	 *            예약일시, (형태 yyyyMMddHHmmss)
	 * @param userID
	 *            연동회원 아이디
	 * @return receiptNum 접수번호
	 * @throws PopbillException
	 */
	public String sendFAX(String corpNum, String sendNum, String receiveNum,
			String receiveName, File[] files, Date reserveDT, String userID)
			throws PopbillException;
	
	/**
	 * 팩스전송 다중파일(최대5개) 단일 수신자.
	 * 
	 * @param corpNum
	 *            연동회원 사업자번호
	 * @param sendNum
	 *            발신번호
	 * @param receiveNum
	 *            수신번호
	 * @param receiveName
	 *            수신자 명칭
	 * @param files
	 *            전송파일 Stream 배열
	 * @param reserveDT
	 *            예약일시, (형태 yyyyMMddHHmmss)
	 * @param userID
	 *            연동회원 아이디
	 * @param title
	 * 			  팩스제목
	 * @return receiptNum 접수번호
	 * @throws PopbillException
	 */
	public String sendFAX(String corpNum, String sendNum, String receiveNum,
			String receiveName, File[] files, Date reserveDT, String userID,
			String title) throws PopbillException;
	
	/**
	 * 팩스전송 다중파일(최대5개) 단일 수신자.
	 * 
	 * @param corpNum
	 *            연동회원 사업자번호
	 * @param sendNum
	 *            발신번호
	 * @param receiveNum
	 *            수신번호
	 * @param receiveName
	 *            수신자 명칭
	 * @param files
	 *            전송파일 Stream 배열
	 * @param reserveDT
	 *            예약일시, (형태 yyyyMMddHHmmss)
	 * @param userID
	 *            연동회원 아이디
	 * @param title
	 * 			  팩스제목
	 * @param requestNum
	 * 			  전송요청번호
	 * @return receiptNum 접수번호
	 * @throws PopbillException
	 */
	public String sendFAX(String corpNum, String sendNum, String receiveNum,
			String receiveName, File[] files, Date reserveDT, String userID,
			String title, String requestNum) throws PopbillException;

	/**
	 * 팩스전송 다중파일(최대5개) 단일 수신자. (광고팩스)
	 * 
	 * @param corpNum
	 *            연동회원 사업자번호
	 * @param sendNum
	 *            발신번호
	 * @param receiveNum
	 *            수신번호
	 * @param receiveName
	 *            수신자 명칭
	 * @param files
	 *            전송파일 Stream 배열
	 * @param reserveDT
	 *            예약일시, (형태 yyyyMMddHHmmss)
	 * @param userID
	 *            연동회원 아이디
	 * @param adsYN
	 * 			  광고팩스 전송여부
	 * @return receiptNum 접수번호
	 * @throws PopbillException
	 */
	public String sendFAX(String corpNum, String sendNum, String receiveNum,
			String receiveName, File[] files, Date reserveDT, String userID,
			Boolean adsYN) throws PopbillException;	
	
	/**
	 * 팩스전송 다중파일(최대5개) 단일 수신자. (팩스제목)
	 * 
	 * @param corpNum
	 *            연동회원 사업자번호
	 * @param sendNum
	 *            발신번호
	 * @param receiveNum
	 *            수신번호
	 * @param receiveName
	 *            수신자 명칭
	 * @param files
	 *            전송파일 Stream 배열
	 * @param reserveDT
	 *            예약일시, (형태 yyyyMMddHHmmss)
	 * @param userID
	 *            연동회원 아이디
	 * @param adsYN
	 * 			  광고팩스 전송여부
	 * @param title 
	 * 			팩스제목
	 * @return receiptNum 접수번호
	 * @throws PopbillException
	 */
	public String sendFAX(String corpNum, String sendNum, String receiveNum,
			String receiveName, File[] files, Date reserveDT, String userID,
			Boolean adsYN, String title) throws PopbillException;	
	
	/**
	 * 팩스전송 다중파일(최대5개) 단일 수신자. (팩스제목)
	 * 
	 * @param corpNum
	 *            연동회원 사업자번호
	 * @param sendNum
	 *            발신번호
	 * @param receiveNum
	 *            수신번호
	 * @param receiveName
	 *            수신자 명칭
	 * @param files
	 *            전송파일 Stream 배열
	 * @param reserveDT
	 *            예약일시, (형태 yyyyMMddHHmmss)
	 * @param userID
	 *            연동회원 아이디
	 * @param adsYN
	 * 			  광고팩스 전송여부
	 * @param title 
	 * 			팩스제목
	 * @param requestNum 
	 * 			전송요청번호
	 * @return receiptNum 접수번호
	 * @throws PopbillException
	 */
	public String sendFAX(String corpNum, String sendNum, String receiveNum,
			String receiveName, File[] files, Date reserveDT, String userID,
			Boolean adsYN, String title, String requestNum) throws PopbillException;	

	/**
	 * 팩스전송 다중파일(최대5개) 동보전송
	 * 
	 * @param corpNum
	 *            연동회원 사업자번호
	 * @param sendNum
	 *            발신번호
	 * @param receivers
	 *            동보수신자 배열
	 * @param files
	 *            전송파일 Stream 배열
	 * @param reserveDT
	 *            예약일시, (형태 yyyyMMddHHmmss)
	 * @param userID
	 *            연동회원 아이디
	 * @return receiptNum 접수번호
	 * @throws PopbillException
	 */
	public String sendFAX(String corpNum, String sendNum, Receiver[] receivers,
			File[] files, Date reserveDT, String userID)
			throws PopbillException;
	
	/**
	 * 팩스전송 다중파일(최대5개) 동보전송 - 팩스제목
	 * 
	 * @param corpNum
	 *            연동회원 사업자번호
	 * @param sendNum
	 *            발신번호
	 * @param receivers
	 *            동보수신자 배열
	 * @param files
	 *            전송파일 Stream 배열
	 * @param reserveDT
	 *            예약일시, (형태 yyyyMMddHHmmss)
	 * @param userID
	 *            연동회원 아이디
	 * @param title
	 * 		  	  팩스제목
	 * @return receiptNum 접수번호
	 * @throws PopbillException
	 */
	public String sendFAX(String corpNum, String sendNum, Receiver[] receivers,
			File[] files, Date reserveDT, String userID, String title)
			throws PopbillException;

	/**
	 * 팩스전송 다중파일(최대5개) 동보전송 - 팩스제목
	 * 
	 * @param corpNum
	 *            연동회원 사업자번호
	 * @param sendNum
	 *            발신번호
	 * @param receivers
	 *            동보수신자 배열
	 * @param files
	 *            전송파일 Stream 배열
	 * @param reserveDT
	 *            예약일시, (형태 yyyyMMddHHmmss)
	 * @param userID
	 *            연동회원 아이디
	 * @param title
	 * 		  	  팩스제목
	 * @param requestNum
	 * 		  	  전송요청번호
	 * @return receiptNum 접수번호
	 * @throws PopbillException
	 */
	public String sendFAX(String corpNum, String sendNum, Receiver[] receivers,
			File[] files, Date reserveDT, String userID, String title, String requestNum)
			throws PopbillException;

	/**
	 * 팩스전송 다중파일(최대5개) 동보전송 (광고팩스)
	 * 
	 * @param corpNum
	 *            연동회원 사업자번호
	 * @param sendNum
	 *            발신번호
	 * @param receivers
	 *            동보수신자 배열
	 * @param files
	 *            전송파일 Stream 배열
	 * @param reserveDT
	 *            예약일시, (형태 yyyyMMddHHmmss)
	 * @param userID
	 *            연동회원 아이디
	 * @param adsYN
	 * 			  광고팩스 전송여부
	 * @return receiptNum 접수번호
	 * @throws PopbillException
	 */
	public String sendFAX(String corpNum, String sendNum, Receiver[] receivers,
			File[] files, Date reserveDT, String userID, Boolean adsYN)
			throws PopbillException;	
	
	/**
	 * 팩스전송 다중파일(최대5개) 동보전송 (팩스제목)
	 * 
	 * @param corpNum
	 *            연동회원 사업자번호
	 * @param sendNum
	 *            발신번호
	 * @param receivers
	 *            동보수신자 배열
	 * @param files
	 *            전송파일 Stream 배열
	 * @param reserveDT
	 *            예약일시, (형태 yyyyMMddHHmmss)
	 * @param userID
	 *            연동회원 아이디
	 * @param adsYN
	 * 			  광고팩스 전송여부
	 * @param title
	 * 			팩스제목
	 * @return receiptNum 접수번호
	 * @throws PopbillException
	 */
	public String sendFAX(String corpNum, String sendNum, Receiver[] receivers,
			File[] files, Date reserveDT, String userID, Boolean adsYN, String title)
			throws PopbillException;
	
	/**
	 * 팩스전송 다중파일(최대5개) 동보전송 (팩스제목)
	 * 
	 * @param corpNum
	 *            연동회원 사업자번호
	 * @param sendNum
	 *            발신번호
	 * @param receivers
	 *            동보수신자 배열
	 * @param files
	 *            전송파일 Stream 배열
	 * @param reserveDT
	 *            예약일시, (형태 yyyyMMddHHmmss)
	 * @param userID
	 *            연동회원 아이디
	 * @param adsYN
	 * 			  광고팩스 전송여부
	 * @param title
	 * 			팩스제목
	 * @param requestNum
	 * 			전송요청번호
	 * @return receiptNum 접수번호
	 * @throws PopbillException
	 */
	public String sendFAX(String corpNum, String sendNum, Receiver[] receivers,
			File[] files, Date reserveDT, String userID, Boolean adsYN, String title, String requestNum)
			throws PopbillException;

	/**
	 * 팩스전송 다중파일(최대5개) 동보전송
	 * 
	 * @param corpNum
	 *            연동회원 사업자번호
	 * @param sendNum
	 *            발신번호
	 * @param senderName
	 * 			  발신자명
	 * @param receivers
	 *            동보수신자 배열
	 * @param files
	 *            전송파일 Stream 배열
	 * @param reserveDT
	 *            예약일시, (형태 yyyyMMddHHmmss)
	 * @return receiptNum 접수번호
	 * @throws PopbillException
	 */
	public String sendFAX(String corpNum, String sendNum, String senderName, 
			Receiver[] receivers, File[] files, Date reserveDT) throws PopbillException;
	
	/**
	 * 팩스전송 다중파일(최대5개) 동보전송 (광고팩스)
	 * 
	 * @param corpNum
	 *            연동회원 사업자번호
	 * @param sendNum
	 *            발신번호
	 * @param senderName
	 * 			  발신자명
	 * @param receivers
	 *            동보수신자 배열
	 * @param files
	 *            전송파일 Stream 배열
	 * @param reserveDT
	 *            예약일시, (형태 yyyyMMddHHmmss)
	 * @param adsYN
	 * 			  광고팩스 전송여부
	 * @return receiptNum 접수번호
	 * @throws PopbillException
	 */
	public String sendFAX(String corpNum, String sendNum, String senderName, 
			Receiver[] receivers, File[] files, Date reserveDT, Boolean adsYN)
			throws PopbillException;
	
	/**
	 * 팩스전송 다중파일(최대5개) 동보전송 (광고팩스)
	 * 
	 * @param corpNum
	 *            연동회원 사업자번호
	 * @param sendNum
	 *            발신번호
	 * @param senderName
	 * 			  발신자명
	 * @param receivers
	 *            동보수신자 배열
	 * @param files
	 *            전송파일 Stream 배열
	 * @param reserveDT
	 *            예약일시, (형태 yyyyMMddHHmmss)
	 * @param adsYN
	 * 			  광고팩스 전송여부
	 * @param requestNum
	 * 			  전송요청번호
	 * @return receiptNum 접수번호
	 * @throws PopbillException
	 */
	public String sendFAX(String corpNum, String sendNum, String senderName, 
			Receiver[] receivers, File[] files, Date reserveDT, Boolean adsYN, String requestNum)
			throws PopbillException;

	/**
	 * 팩스전송 다중파일(최대5개) 동보전송
	 * 
	 * @param corpNum
	 *            연동회원 사업자번호
	 * @param sendNum
	 *            발신번호
	 * @param senderName
	 * 			  발신자명
	 * @param receivers
	 *            동보수신자 배열
	 * @param files
	 *            전송파일 Stream 배열
	 * @param reserveDT
	 *            예약일시, (형태 yyyyMMddHHmmss)
	 * @param userID
	 *            연동회원 아이디
	 * @return receiptNum 접수번호
	 * @throws PopbillException
	 */
	public String sendFAX(String corpNum, String sendNum, String senderName, Receiver[] receivers,
			File[] files, Date reserveDT, String userID)
			throws PopbillException;
	
	/**
	 * 팩스전송 다중파일(최대5개) 동보전송 - 팩스제목
	 * 
	 * @param corpNum
	 *            연동회원 사업자번호
	 * @param sendNum
	 *            발신번호
	 * @param senderName
	 * 			  발신자명
	 * @param receivers
	 *            동보수신자 배열
	 * @param files
	 *            전송파일 Stream 배열
	 * @param reserveDT
	 *            예약일시, (형태 yyyyMMddHHmmss)
	 * @param userID
	 *            연동회원 아이디
	 * @param title
	 * 			팩스제목
	 * @return receiptNum 접수번호
	 * @throws PopbillException
	 */
	public String sendFAX(String corpNum, String sendNum, String senderName, Receiver[] receivers,
			File[] files, Date reserveDT, String userID, String title)
			throws PopbillException;
	
	/**
	 * 팩스전송 다중파일(최대5개) 동보전송 - 팩스제목
	 * 
	 * @param corpNum
	 *            연동회원 사업자번호
	 * @param sendNum
	 *            발신번호
	 * @param senderName
	 * 			  발신자명
	 * @param receivers
	 *            동보수신자 배열
	 * @param files
	 *            전송파일 Stream 배열
	 * @param reserveDT
	 *            예약일시, (형태 yyyyMMddHHmmss)
	 * @param userID
	 *            연동회원 아이디
	 * @param title
	 * 			팩스제목
	 * @param requestNum
	 * 			전송요청번호
	 * @return receiptNum 접수번호
	 * @throws PopbillException
	 */
	public String sendFAX(String corpNum, String sendNum, String senderName, Receiver[] receivers,
			File[] files, Date reserveDT, String userID, String title, String requestNum)
			throws PopbillException;

	/**
	 * 팩스전송 다중파일(최대5개) 동보전송 (광고)
	 * 
	 * @param corpNum
	 *            연동회원 사업자번호
	 * @param sendNum
	 *            발신번호
	 * @param senderName
	 * 			  발신자명
	 * @param receivers
	 *            동보수신자 배열
	 * @param files
	 *            전송파일 Stream 배열
	 * @param reserveDT
	 *            예약일시, (형태 yyyyMMddHHmmss)
	 * @param userID
	 *            연동회원 아이디
	 * @param adsYN
	 * 			  광고팩스 전송여부            
	 * @return receiptNum 접수번호
	 * @throws PopbillException
	 */
	public String sendFAX(String corpNum, String sendNum, String senderName, Receiver[] receivers,
			File[] files, Date reserveDT, String userID, Boolean adsYN)
			throws PopbillException;
	
	/**
	 * 팩스전송 다중파일(최대5개) 동보전송 (팩스제목)
	 * 
	 * @param corpNum
	 *            연동회원 사업자번호
	 * @param sendNum
	 *            발신번호
	 * @param senderName
	 * 			  발신자명
	 * @param receivers
	 *            동보수신자 배열
	 * @param files
	 *            전송파일 Stream 배열
	 * @param reserveDT
	 *            예약일시, (형태 yyyyMMddHHmmss)
	 * @param userID
	 *            연동회원 아이디
	 * @param adsYN
	 * 			  광고팩스 전송여부     
	 * @param title
	 * 			팩스제목       
	 * @return receiptNum 접수번호
	 * @throws PopbillException
	 */
	public String sendFAX(String corpNum, String sendNum, String senderName, Receiver[] receivers,
			File[] files, Date reserveDT, String userID, Boolean adsYN, String title)
			throws PopbillException;
	
	/**
	 * 팩스전송 다중파일(최대5개) 동보전송 (팩스제목)
	 * 
	 * @param corpNum
	 *            연동회원 사업자번호
	 * @param sendNum
	 *            발신번호
	 * @param senderName
	 * 			  발신자명
	 * @param receivers
	 *            동보수신자 배열
	 * @param files
	 *            전송파일 Stream 배열
	 * @param reserveDT
	 *            예약일시, (형태 yyyyMMddHHmmss)
	 * @param userID
	 *            연동회원 아이디
	 * @param adsYN
	 * 			  광고팩스 전송여부     
	 * @param title
	 * 			팩스제목       
	 * @param requestNum
	 * 			전송요청번호  
	 * @return receiptNum 접수번호
	 * @throws PopbillException
	 */
	public String sendFAX(String corpNum, String sendNum, String senderName, Receiver[] receivers,
			File[] files, Date reserveDT, String userID, Boolean adsYN, String title, String requestNum)
			throws PopbillException;

	/**
	 * 팩스전송 동보 재전송
	 * 
	 * @param corpNum
	 *            연동회원 사업자번호
	 * @param receiptNum
	 * 			  팩스 접수번호
	 * @param sendNum
	 *            발신번호
	 * @param senderName
	 * 			  발신자명           
	 * @param receiveNum
	 *            수신번호
	 * @param receiveName
	 *            수신자 명칭
	 * @param reserveDT
	 *            예약일시, (형태 yyyyMMddHHmmss)
	 * @param userID
	 *            연동회원 아이디
	 * @return receiptNum 접수번호
	 * @throws PopbillException
	 */
	public String resendFAX(String corpNum, String receiptNum, String sendNum, String senderName,
			String receiveNum, String receiveName, Date reserveDT, String userID)
			throws PopbillException;
	
	/**
	 * 팩스전송 동보 재전송
	 * 
	 * @param corpNum
	 *            연동회원 사업자번호
	 * @param receiptNum
	 * 			  팩스 접수번호
	 * @param sendNum
	 *            발신번호
	 * @param senderName
	 * 			  발신자명           
	 * @param receiveNum
	 *            수신번호
	 * @param receiveName
	 *            수신자 명칭
	 * @param reserveDT
	 *            예약일시, (형태 yyyyMMddHHmmss)
	 * @param userID
	 *            연동회원 아이디
	 * @param title
	 * 			팩스 제목
	 * @return receiptNum 접수번호
	 * @throws PopbillException
	 */
	public String resendFAX(String corpNum, String receiptNum, String sendNum, String senderName,
			String receiveNum, String receiveName, Date reserveDT, String userID, String title)
			throws PopbillException;
	
	/**
	 * 팩스전송 동보 재전송
	 * 
	 * @param corpNum
	 *            연동회원 사업자번호
	 * @param receiptNum
	 * 			  팩스 접수번호
	 * @param sendNum
	 *            발신번호
	 * @param senderName
	 * 			  발신자명           
	 * @param receiveNum
	 *            수신번호
	 * @param receiveName
	 *            수신자 명칭
	 * @param reserveDT
	 *            예약일시, (형태 yyyyMMddHHmmss)
	 * @param userID
	 *            연동회원 아이디
	 * @param title
	 * 			팩스 제목
	 * @param requestNum
	 * 			전송요청번호
	 * @return receiptNum 접수번호
	 * @throws PopbillException
	 */
	public String resendFAX(String corpNum, String receiptNum, String sendNum, String senderName,
			String receiveNum, String receiveName, Date reserveDT, String userID, String title, String requestNum)
			throws PopbillException;

	/**
	 * 팩스전송 동보 재전송
	 * 
	 * @param corpNum
	 *            연동회원 사업자번호
	 * @param receiptNum
	 *            팩스 접수번호
	 * @param sendNum
	 *            발신번호
	 * @param senderName
	 * 			  발신자명
	 * @param receivers
	 *            수신자정보 배열
	 * @param reserveDT
	 *            예약일시, (형태 yyyyMMddHHmmss)
	 * @return receiptNum 접수번호
	 * @throws PopbillException
	 */
	public String resendFAX(String corpNum, String receiptNum, String sendNum, 
			String senderName, Receiver[] receivers, Date reserveDT)
			throws PopbillException;
	
		
	/**
	 * 팩스전송 동보 재전송
	 * 
	 * @param corpNum
	 *            연동회원 사업자번호
	 * @param receiptNum
	 *            팩스 접수번호
	 * @param sendNum
	 *            발신번호
	 * @param senderName
	 * 			  발신자명
	 * @param receivers
	 *            수신자정보 배열
	 * @param reserveDT
	 *            예약일시, (형태 yyyyMMddHHmmss)
	 * @param userID
	 *            연동회원 아이디
	 * @return receiptNum 접수번호
	 * @throws PopbillException
	 */
	public String resendFAX(String corpNum, String receiptNum, String sendNum, 
			String senderName, Receiver[] receivers, Date reserveDT, String userID)
			throws PopbillException;
	
	
	/**
	 * 팩스전송 동보 재전송
	 * 
	 * @param corpNum
	 *            연동회원 사업자번호
	 * @param receiptNum
	 *            팩스 접수번호
	 * @param sendNum
	 *            발신번호
	 * @param senderName
	 * 			  발신자명
	 * @param receivers
	 *            수신자정보 배열
	 * @param reserveDT
	 *            예약일시, (형태 yyyyMMddHHmmss)
	 * @param userID
	 *            연동회원 아이디
	 * @param title
	 * 			팩스제목
	 * @return receiptNum 접수번호
	 * @throws PopbillException
	 */
	public String resendFAX(String corpNum, String receiptNum, String sendNum, 
			String senderName, Receiver[] receivers, Date reserveDT, String userID, String title)
			throws PopbillException;
	
	/**
	 * 팩스전송 동보 재전송
	 * 
	 * @param corpNum
	 *            연동회원 사업자번호
	 * @param receiptNum
	 *            팩스 접수번호
	 * @param sendNum
	 *            발신번호
	 * @param senderName
	 * 			  발신자명
	 * @param receivers
	 *            수신자정보 배열
	 * @param reserveDT
	 *            예약일시, (형태 yyyyMMddHHmmss)
	 * @param userID
	 *            연동회원 아이디
	 * @param title
	 * 			팩스제목
	 * @param requestNum
	 * 			전송요청번호
	 * @return receiptNum 접수번호
	 * @throws PopbillException
	 */
	public String resendFAX(String corpNum, String receiptNum, String sendNum, 
			String senderName, Receiver[] receivers, Date reserveDT, String userID, String title, String requestNum)
			throws PopbillException;

	/**
	 * 팩스전송 동보 재전송
	 * 
	 * @param corpNum
	 *            연동회원 사업자번호
	 * @param requestNum
	 * 			  재전송 팩스의 전송요청번호
	 * @param sendNum
	 *            발신번호
	 * @param senderName
	 * 			  발신자명           
	 * @param receiveNum
	 *            수신번호
	 * @param receiveName
	 *            수신자 명칭
	 * @param reserveDT
	 *            예약일시, (형태 yyyyMMddHHmmss)
	 * @param userID
	 *            연동회원 아이디
	 * @param title
	 * 			팩스 제목
	 * @param originalFAXrequestNum
	 * 			원본 팩스 전송시 할당한 전송요청번호
	 * @return receiptNum 접수번호
	 * @throws PopbillException
	 */
	public String resendFAXRN(String corpNum, String reqeustNum, String sendNum, String senderName,
			String receiveNum, String receiveName, Date reserveDT, String userID, String title,
			String originalFAXrequestNum)
			throws PopbillException;

	/**
	 * 팩스전송 동보 재전송
	 * 
	 * @param corpNum
	 *            연동회원 사업자번호
	 * @param requestNum
	 *             재전송 팩스의 전송요청번호
	 * @param sendNum
	 *            발신번호
	 * @param senderName
	 * 			  발신자명
	 * @param receivers
	 *            수신자정보 배열
	 * @param reserveDT
	 *            예약일시, (형태 yyyyMMddHHmmss)
	 * @param userID
	 *            연동회원 아이디
	 * @param title
	 * 			팩스제목
	 * @param originalFAXrequestNum
	 * 			원본 팩스 전송시 할당한 전송요청번호
	 * @return receiptNum 접수번호
	 * @throws PopbillException
	 */
	public String resendFAXRN(String corpNum, String requestNum, String sendNum, 
			String senderName, Receiver[] receivers, Date reserveDT, String userID, String title,
			String originalFAXrequestNum)
			throws PopbillException;	
	
	/**
	 * 전송결과 확인
	 * 
	 * @param corpNum
	 *            연동회원 사업자번호
	 * @param receiptNum
	 *            팩스전송 접수번호
	 * @return FaxResult 배열. (see com.popbill.api.fax.FaxResult)
	 * @throws PopbillException
	 */
	public FaxResult[] getFaxResult(String corpNum, String receiptNum)
			throws PopbillException;

	/**
	 * 전송결과 확인
	 * 
	 * @param corpNum
	 *            연동회원 사업자번호
	 * @param requestNum
	 *            전송요청번호
	 * @return FaxResult 배열. (see com.popbill.api.fax.FaxResult)
	 * @throws PopbillException
	 */
	public FaxResult[] getFaxResultRN(String corpNum, String requestNum)
			throws PopbillException;

	/**
	 * 예약건 전송 취소.(예약시간 10분전 까지만 가능)
	 * 
	 * @param corpNum
	 *            연동회원 사업자번호
	 * @param receiptNum
	 *            팩스전송 접수번호
	 * @return Response 응답
	 * @throws PopbillException
	 */
	public Response cancelReserve(String corpNum, String receiptNum) throws PopbillException;
	
	
	/**
	 * 예약건 전송 취소.(예약시간 10분전 까지만 가능)
	 * 
	 * @param corpNum
	 *            연동회원 사업자번호
	 * @param receiptNum
	 *            팩스전송 접수번호
	 * @param userID
	 *            연동회원 아이디
	 * @return Response 응답
	 * @throws PopbillException
	 */
	public Response cancelReserve(String corpNum, String receiptNum,
			String userID) throws PopbillException;
	
	/**
	 * 예약건 전송 취소.(예약시간 10분전 까지만 가능)
	 * 
	 * @param corpNum
	 *            연동회원 사업자번호
	 * @param requestNum
	 *            전송요청번호
	 * @return Response 응답
	 * @throws PopbillException
	 */
	public Response cancelReserveRN(String corpNum, String reqeustNum) throws PopbillException;
	
	
	/**
	 * 예약건 전송 취소.(예약시간 10분전 까지만 가능)
	 * 
	 * @param corpNum
	 *            연동회원 사업자번호
	 * @param requestNum
	 *            전송요청번호
	 * @param userID
	 *            연동회원 아이디
	 * @return Response 응답
	 * @throws PopbillException
	 */
	public Response cancelReserveRN(String corpNum, String reqeustNum,
			String userID) throws PopbillException;
	
	/**
	 * 
	 * @param corpNum
	 * 			연동회원 사업자번호 
	 * @param sDate
	 * 			시작일자 (yyyyMMdd)
	 * @param eDate
	 * 			종료일자 (yyyyMMdd)
	 * @param state
	 * 			전송상태 
	 * @param reserveYN
	 * 			예약전송여부 
	 * @param senderOnlyYN
	 * 			개인조회 여부 
	 * @param page
	 * 			페이지 번호, 기본값 1
	 * @param perPage
	 * 			페이지당 목록갯수, 기본값 500, 최대값 1000
	 * @param order
	 * 			정렬방향, D-내림차순, A-오름차순 
	 * @return 팩스전송내역. see com.popbill.api.fax.FAXSearchResult
	 * @throws PopbillException
	 */
	public FAXSearchResult search(String corpNum, String sDate, String eDate,
			String[] state, Boolean reserveYN, Boolean senderOnlyYN, 
			int page, int perPage, String order) throws PopbillException;

	/**
	 * 
	 * @param corpNum
	 * 			연동회원 사업자번호 
	 * @param sDate
	 * 			시작일자 (yyyyMMdd)
	 * @param eDate
	 * 			종료일자 (yyyyMMdd)
	 * @param state
	 * 			전송상태 
	 * @param reserveYN
	 * 			예약전송여부 
	 * @param senderOnlyYN
	 * 			개인조회 여부 
	 * @param page
	 * 			페이지 번호, 기본값 1
	 * @param perPage
	 * 			페이지당 목록갯수, 기본값 500, 최대값 1000
	 * @param order
	 * 			정렬방향, D-내림차순, A-오름차순 
	 * @param qString
	 * 			 조회검색어. 발신자명 또는 수신자명 기재
	 * @return 팩스전송내역. see com.popbill.api.fax.FAXSearchResult
	 * @throws PopbillException
	 */
	public FAXSearchResult search(String corpNum, String sDate, String eDate,
			String[] state, Boolean reserveYN, Boolean senderOnlyYN, 
			int page, int perPage, String order, String qString) throws PopbillException;	
	
	/**
	 * 과금정보 조회
	 * 
	 * @param corpNum
	 * 			연동회원 사업자번호 
	 * @return 과금정보. see.com.popbill.api.ChargeInfo
	 * @throws PopbillException
	 */
	public ChargeInfo getChargeInfo(String corpNum) throws PopbillException;
	
	/**
	 * 등록된 발신번호 목록 확인 
	 * 
	 * @param corpNum
	 * 			연동회원 사업자번호
	 * @return 발신번호 목록  
	 * @throws PopbillException
	 */
	public SenderNumber[] getSenderNumberList(String corpNum) throws PopbillException;
	
	/**
	 * 등록된 발신번호 목록 확인 
	 * 
	 * @param corpNum
	 * 			연동회원 사업자번호
	 * @param userID 
	 * 			연동회원 아이디
	 * @return 발신번호 목록  
	 * @throws PopbillException
	 */
	public SenderNumber[] getSenderNumberList(String corpNum, String userID) throws PopbillException;
	
}
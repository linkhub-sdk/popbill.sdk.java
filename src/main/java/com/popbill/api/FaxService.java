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
	 * @param CorpNum
	 *            연동회원 사업자번호
	 * @return 단가 (ex. 55.0)
	 * @throws PopbillException
	 */
	public float getUnitCost(String CorpNum) throws PopbillException;

	/**
	 * 팝빌 팩스전송 관련 URL 확인. 반환한 url은 30초이내에 브라우져에 표시하여야 함.
	 * 
	 * @param CorpNum
	 *            연동회원 사업자번호.
	 * @param TOGO
	 *            지정값. (BOX : 팩스 전송 내역 조회 팝업)
	 * @return 팝빌 URL (AccessToken값 포함. Token값은 응답후 30초까지만 유효함)
	 * @throws PopbillException
	 */
	public String getURL(String CorpNum, String TOGO)
			throws PopbillException;
	
	/**
	 * 팝빌 팩스전송 관련 URL 확인. 반환한 url은 30초이내에 브라우져에 표시하여야 함.
	 * 
	 * @param CorpNum
	 *            연동회원 사업자번호.
	 * @param UserID
	 *            연동회원의 회원아이디
	 * @param TOGO
	 *            지정값. (BOX : 팩스 전송 내역 조회 팝업)
	 * @return 팝빌 URL (AccessToken값 포함. Token값은 응답후 30초까지만 유효함)
	 * @throws PopbillException
	 */
	public String getURL(String CorpNum, String UserID, String TOGO)
			throws PopbillException;

	/**
	 * 팩스전송 단일파일 단일 수신자.
	 * 
	 * @param CorpNum
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
	public String sendFAX(String CorpNum, String sendNum, String receiveNum,
			String receiveName, File file, Date reserveDT)
			throws PopbillException;
	
	/**
	 * 팩스전송 단일파일 단일 수신자. (광고팩스)
	 * 
	 * @param CorpNum
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
	public String sendFAX(String CorpNum, String sendNum, String receiveNum,
			String receiveName, File file, Date reserveDT, Boolean adsYN)
			throws PopbillException;
	
	/**
	 * 팩스전송 단일파일 단일 수신자.
	 * 
	 * @param CorpNum
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
	 * @param UserID
	 *            연동회원 아이디
	 * @return receiptNum 접수번호
	 * @throws PopbillException
	 */
	public String sendFAX(String CorpNum, String sendNum, String receiveNum,
			String receiveName, File file, Date reserveDT, String UserID)
			throws PopbillException;
	
	
	/**
	 * 팩스전송 단일파일 단일 수신자 (광고팩스).
	 * 
	 * @param CorpNum
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
	 * @param UserID
	 *            연동회원 아이디
	 * @param adsYN
	 * 			  광고팩스 전송여부
	 * @return receiptNum 접수번호
	 * @throws PopbillException
	 */
	public String sendFAX(String CorpNum, String sendNum, String receiveNum,
			String receiveName, File file, Date reserveDT, String UserID, Boolean adsYN)
			throws PopbillException;

	/**
	 * 팩스전송 단일파일 동보전송.
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
	 * @return receiptNum 접수번호
	 * @throws PopbillException
	 */
	public String sendFAX(String CorpNum, String sendNum, Receiver[] receivers,
			File file, Date reserveDT) throws PopbillException;
	
	/**
	 * 팩스전송 단일파일 동보전송. (광고팩스)
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
	 * @param adsYN
	 * 			  광고팩스 전송여부
	 * @return receiptNum 접수번호
	 * @throws PopbillException
	 */
	public String sendFAX(String CorpNum, String sendNum, Receiver[] receivers,
			File file, Date reserveDT, Boolean adsYN) throws PopbillException;	
	
	/**
	 * 팩스전송 단일파일 동보전송.
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
	 * @param UserID
	 *            연동회원 아이디
	 * @return receiptNum 접수번호
	 * @throws PopbillException
	 */
	public String sendFAX(String CorpNum, String sendNum, Receiver[] receivers,
			File file, Date reserveDT, String UserID) throws PopbillException;

	/**
	 * 팩스전송 단일파일 동보전송. (광고팩스)
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
	 * @param UserID
	 *            연동회원 아이디
	 * @param adsYN
	 * 			  광고팩스 전송여부
	 * @return receiptNum 접수번호
	 * @throws PopbillException
	 */
	public String sendFAX(String CorpNum, String sendNum, Receiver[] receivers,
			File file, Date reserveDT, String UserID, Boolean adsYN) throws PopbillException;	
	
	/**
	 * 팩스전송 다중파일(최대5개) 단일 수신자.
	 * 
	 * @param CorpNum
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
	public String sendFAX(String CorpNum, String sendNum, String receiveNum,
			String receiveName, File[] files, Date reserveDT)
			throws PopbillException;

	/**
	 * 팩스전송 다중파일(최대5개) 단일 수신자.
	 * 
	 * @param CorpNum
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
	public String sendFAX(String CorpNum, String sendNum, String receiveNum,
			String receiveName, File[] files, Date reserveDT, Boolean adsYN)
			throws PopbillException;	
	
	/**
	 * 팩스전송 다중파일(최대5개) 단일 수신자.
	 * 
	 * @param CorpNum
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
	 * @param UserID
	 *            연동회원 아이디
	 * @return receiptNum 접수번호
	 * @throws PopbillException
	 */
	public String sendFAX(String CorpNum, String sendNum, String receiveNum,
			String receiveName, File[] files, Date reserveDT, String UserID)
			throws PopbillException;
	
	/**
	 * 팩스전송 다중파일(최대5개) 단일 수신자. (광고팩스)
	 * 
	 * @param CorpNum
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
	 * @param UserID
	 *            연동회원 아이디
	 * @param adsYN
	 * 			  광고팩스 전송여부
	 * @return receiptNum 접수번호
	 * @throws PopbillException
	 */
	public String sendFAX(String CorpNum, String sendNum, String receiveNum,
			String receiveName, File[] files, Date reserveDT, String UserID,
			Boolean adsYN) throws PopbillException;	
	
	/**
	 * 팩스전송 다중파일(최대5개) 동보전송
	 * 
	 * @param CorpNum
	 *            연동회원 사업자번호
	 * @param sendNum
	 *            발신번호
	 * @param receivers
	 *            동보수신자 배열
	 * @param files
	 *            전송파일 Stream 배열
	 * @param reserveDT
	 *            예약일시, (형태 yyyyMMddHHmmss)
	 * @param UserID
	 *            연동회원 아이디
	 * @return receiptNum 접수번호
	 * @throws PopbillException
	 */
	public String sendFAX(String CorpNum, String sendNum, Receiver[] receivers,
			File[] files, Date reserveDT, String UserID)
			throws PopbillException;

	/**
	 * 팩스전송 다중파일(최대5개) 동보전송 (광고팩스)
	 * 
	 * @param CorpNum
	 *            연동회원 사업자번호
	 * @param sendNum
	 *            발신번호
	 * @param receivers
	 *            동보수신자 배열
	 * @param files
	 *            전송파일 Stream 배열
	 * @param reserveDT
	 *            예약일시, (형태 yyyyMMddHHmmss)
	 * @param UserID
	 *            연동회원 아이디
	 * @param adsYN
	 * 			  광고팩스 전송여부
	 * @return receiptNum 접수번호
	 * @throws PopbillException
	 */
	public String sendFAX(String CorpNum, String sendNum, Receiver[] receivers,
			File[] files, Date reserveDT, String UserID, Boolean adsYN)
			throws PopbillException;	
	
	/**
	 * 팩스전송 다중파일(최대5개) 동보전송
	 * 
	 * @param CorpNum
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
	public String sendFAX(String CorpNum, String sendNum, String senderName, 
			Receiver[] receivers, File[] files, Date reserveDT) throws PopbillException;
	
	/**
	 * 팩스전송 다중파일(최대5개) 동보전송 (광고팩스)
	 * 
	 * @param CorpNum
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
	public String sendFAX(String CorpNum, String sendNum, String senderName, 
			Receiver[] receivers, File[] files, Date reserveDT, Boolean adsYN)
			throws PopbillException;
	
	/**
	 * 팩스전송 다중파일(최대5개) 동보전송
	 * 
	 * @param CorpNum
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
	 * @param UserID
	 *            연동회원 아이디
	 * @return receiptNum 접수번호
	 * @throws PopbillException
	 */
	public String sendFAX(String CorpNum, String sendNum, String senderName, Receiver[] receivers,
			File[] files, Date reserveDT, String UserID)
			throws PopbillException;
	
	/**
	 * 팩스전송 다중파일(최대5개) 동보전송 (광고)
	 * 
	 * @param CorpNum
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
	 * @param UserID
	 *            연동회원 아이디
	 * @param adsYN
	 * 			  광고팩스 전송여부            
	 * @return receiptNum 접수번호
	 * @throws PopbillException
	 */
	public String sendFAX(String CorpNum, String sendNum, String senderName, Receiver[] receivers,
			File[] files, Date reserveDT, String UserID, Boolean adsYN)
			throws PopbillException;
	
	
	
	
	
		
	/**
	 * 팩스전송 동보 재전송
	 * 
	 * @param CorpNum
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
	 * @param UserID
	 *            연동회원 아이디
	 * @return receiptNum 접수번호
	 * @throws PopbillException
	 */
	public String resendFAX(String CorpNum, String receiptNum, String sendNum, String senderName,
			String receiveNum, String receiveName, Date reserveDT, String UserID)
			throws PopbillException;
	
	/**
	 * 팩스전송 동보 재전송
	 * 
	 * @param CorpNum
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
	public String resendFAX(String CorpNum, String receiptNum, String sendNum, 
			String senderName, Receiver[] receivers, Date reserveDT)
			throws PopbillException;
	
	/**
	 * 팩스전송 동보 재전송
	 * 
	 * @param CorpNum
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
	 * @param UserID
	 *            연동회원 아이디
	 * @return receiptNum 접수번호
	 * @throws PopbillException
	 */
	public String resendFAX(String CorpNum, String receiptNum, String sendNum, 
			String senderName, Receiver[] receivers, Date reserveDT, String UserID)
			throws PopbillException;
	

	/**
	 * 전송결과 확인
	 * 
	 * @param CorpNum
	 *            연동회원 사업자번호
	 * @param receiptNum
	 *            팩스전송 접수번호
	 * @return FaxResult 배열. (see com.popbill.api.fax.FaxResult)
	 * @throws PopbillException
	 */
	public FaxResult[] getFaxResult(String CorpNum, String receiptNum)
			throws PopbillException;

	/**
	 * 예약건 전송 취소.(예약시간 10분전 까지만 가능)
	 * 
	 * @param CorpNum
	 *            연동회원 사업자번호
	 * @param receiptNum
	 *            팩스전송 접수번호
	 * @return Response 응답
	 * @throws PopbillException
	 */
	public Response cancelReserve(String CorpNum, String receiptNum) throws PopbillException;
	
	
	/**
	 * 예약건 전송 취소.(예약시간 10분전 까지만 가능)
	 * 
	 * @param CorpNum
	 *            연동회원 사업자번호
	 * @param receiptNum
	 *            팩스전송 접수번호
	 * @param UserID
	 *            연동회원 아이디
	 * @return Response 응답
	 * @throws PopbillException
	 */
	public Response cancelReserve(String CorpNum, String receiptNum,
			String UserID) throws PopbillException;
	
	/**
	 * 
	 * @param CorpNum
	 * 			연동회원 사업자번호 
	 * @param SDate
	 * 			시작일자 (yyyyMMdd)
	 * @param EDate
	 * 			종료일자 (yyyyMMdd)
	 * @param State
	 * 			전송상태 
	 * @param ReserveYN
	 * 			예약전송여부 
	 * @param SenderOnlyYN
	 * 			개인조회 여부 
	 * @param Page
	 * 			페이지 번호, 기본값 1
	 * @param PerPage
	 * 			페이지당 목록갯수, 기본값 500, 최대값 1000
	 * @param Order
	 * 			정렬방향, D-내림차순, A-오름차순 
	 * @return 팩스전송내역. see com.popbill.api.fax.FAXSearchResult
	 * @throws PopbillException
	 */
	public FAXSearchResult search(String CorpNum, String SDate, String EDate,
			String[] State, Boolean ReserveYN, Boolean SenderOnlyYN, 
			int Page, int PerPage, String Order) throws PopbillException;
	
	/**
	 * 과금정보 조회
	 * 
	 * @param CorpNum
	 * 			연동회원 사업자번호 
	 * @return 과금정보. see.com.popbill.api.ChargeInfo
	 * @throws PopbillException
	 */
	public ChargeInfo getChargeInfo(String CorpNum) throws PopbillException;
	
	/**
	 * 등록된 발신번호 목록 확인 
	 * 
	 * @param CorpNum
	 * 			연동회원 사업자번호
	 * @return 발신번호 목록  
	 * @throws PopbillException
	 */
	public SenderNumber[] getSenderNumberList(String CorpNum) throws PopbillException;
	
	/**
	 * 등록된 발신번호 목록 확인 
	 * 
	 * @param CorpNum
	 * 			연동회원 사업자번호
	 * @param UserID 
	 * 			연동회원 아이디
	 * @return 발신번호 목록  
	 * @throws PopbillException
	 */
	public SenderNumber[] getSenderNumberList(String CorpNum, String UserID) throws PopbillException;
	
}
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

import com.popbill.api.cashbill.CBSearchResult;
import com.popbill.api.cashbill.Cashbill;
import com.popbill.api.cashbill.CashbillInfo;
import com.popbill.api.cashbill.CashbillLog;

/**
 * Cashbill Service Interface.
 * 
 * @author JeongYoHan
 * @version 1.0.0
 */

public interface CashbillService extends BaseService{

	/**
	 * 팝빌 현금영수증 관련 URL 확인, 반환된 URL은 30초 이내에 브라우저에 표시해야 함
	 * @param CorpNum
	 * 			연동회원 사업자번호
	 * @param TOGO
	 * 			지정값(TBOX : 임시문서함 URL, SBOX : 발행 문서함 URL)
	 * @return 팝빌URL(AccessToken값 포함, Token값은 응답후 30초까지만 유효함)
	 * @throws PopbillException
	 */
	public String getURL(String CorpNum, String TOGO)
			throws PopbillException;
	
	/**
	 * 팝빌 현금영수증 관련 URL 확인, 반환된 URL은 30초 이내에 브라우저에 표시해야 함
	 * @param CorpNum
	 * 			연동회원 사업자번호
	 * @param UserID
	 * 			연동회원의 회원아이디
	 * @param TOGO
	 * 			지정값(TBOX : 임시문서함 URL, SBOX : 발행 문서함 URL)
	 * @return 팝빌URL(AccessToken값 포함, Token값은 응답후 30초까지만 유효함)
	 * @throws PopbillException
	 */
	public String getURL(String CorpNum, String UserID, String TOGO)
			throws PopbillException;
	
	/**
	 * 현금영수증 발행단가 확인
	 * @param CorpNum
	 * 			연동회원 사업자번호
	 * @return 단가( ex. 10.0)
	 * @throws PopbillException
	 */
	public float getUnitCost(String CorpNum) 
			throws PopbillException;

	/**
	 * 문서관리번호 사용여부 확인
	 * @param Corpnum
	 * 			연동회원 사업자번호
	 * @param MgtKey
	 * 			문서관리번호
	 * @return 사용여부, true - 사용중 / false - 미사용
	 * @throws PopbillException
	 */
	public boolean checkMgtKeyInUse(String Corpnum, String MgtKey) 
			throws PopbillException;
	
	/**
	 * 현금영수증 1건 임시저장
	 * @param CorpNum
	 * 			연동회원 사업자번호
	 * @param cashbill
	 * 			현금영수증 정보.(see. com.popbill.api.cashbill.Cashbill)
	 * @return Response
	 * @throws PopbillException
	 */
	public Response register(String CorpNum, Cashbill cashbill)
			throws PopbillException;
	
	/**
	 * 현금영수증 1건 임시저장
	 * @param CorpNum
	 * 			연동회원 사업자번호
	 * @param cashbill
	 * 			현금영수증 정보. (see. com.popbill.api.cashbill.Cashbill)
	 * @param UserID
	 * 			연동회원아이디
	 * @return Response
	 * @throws PopbillException
	 */
	public Response register(String CorpNum, Cashbill cashbill, 
			String UserID) throws PopbillException;
	
	/**
	 * 취소현금영수증 1건 임시저장
	 * @param CorpNum
	 * 			팝빌 연동회원 사업자번호
	 * @param mgtKey
	 * 			취소현금영수증 문서관리번호
	 * @param orgConfirmNum
	 * 			원본현금영수증 승인번호
	 * @param orgTradeDate
	 * 			원본현금영수증 거래일자
	 * @return Response
	 * @throws PopbillException
	 */	
	public Response revokeRegister(String CorpNum, String mgtKey, String orgConfirmNum,
			String orgTradeDate) throws PopbillException;
	
	/**
	 * 취소현금영수증 1건 임시저장
	 * @param CorpNum
	 * 			팝빌 연동회원 사업자번호
	 * @param mgtKey
	 * 			취소현금영수증 문서관리번호
	 * @param orgConfirmNum
	 * 			원본현금영수증 승인번호
	 * @param orgTradeDate
	 * 			원본현금영수증 거래일자
	 * @param smssendYN
	 * 			발행안내 문자전송여부
	 * @return Response
	 * @throws PopbillException
	 */
	public Response revokeRegister(String CorpNum, String mgtKey, String orgConfirmNum,
			String orgTradeDate, Boolean smssendYN) throws PopbillException;
	
	/**
	 * 취소현금영수증 1건 임시저장
	 * @param CorpNum
	 * 			팝빌연동회원 사업자번호
	 * @param mgtKey
	 * 			취소현금영수증 문서관리번호
	 * @param orgConfirmNum
	 * 			원본현금영수증 승인번호
	 * @param orgTradeDate
	 * 			원본현금영수증 거래일자
	 * @param smssendYN
	 * 			발행안내 문자전송여부
	 * @param UserID
	 * 			팝빌연동회원 아이디
	 * @return Response
	 * @throws PopbillException
	 */
	public Response revokeRegister(String CorpNum, String mgtKey, String orgConfirmNum,
			String orgTradeDate, Boolean smssendYN, String UserID) throws PopbillException;
	
	/**
	 * 취소현금영수증 1건 임시저장
	 * @param CorpNum
	 * 			팝빌연동회원 사업자번호
	 * @param mgtKey
	 * 			취소현금영수증 문서관리번호
	 * @param orgConfirmNum
	 * 			원본현금영수증 승인번호
	 * @param orgTradeDate
	 * 			원본현금영수증 거래일자
	 * @param smssendYN
	 * 			발행안내 문자전송여부
	 * @param isPartCancel
	 * 			부분취소여부
	 * @param cancelType
	 * 			취소사유
	 * @param supplyCost
	 * 			[취소] 공급가액
	 * @param tax
	 * 			[취소] 세액
	 * @param serviceFee
	 * 			[취소] 봉사료
	 * @param totalAmount 
	 * 			[취소] 합계금액
	 * @return Response
	 * @throws PopbillException
	 */
	public Response revokeRegister(String CorpNum, String mgtKey, String orgConfirmNum,
			String orgTradeDate, Boolean smssendYN, Boolean isPartCancel, Integer cancelType, 
			String supplyCost, String tax, String serviceFee, String totalAmount) throws PopbillException;

	
	/**
	 * 취소현금영수증 1건 임시저장
	 * @param CorpNum
	 * 			팝빌연동회원 사업자번호
	 * @param mgtKey
	 * 			취소현금영수증 문서관리번호
	 * @param orgConfirmNum
	 * 			원본현금영수증 승인번호
	 * @param orgTradeDate
	 * 			원본현금영수증 거래일자
	 * @param smssendYN
	 * 			발행안내 문자전송여부
	 * @param isPartCancel
	 * 			부분취소여부
	 * @param cancelType
	 * 			취소사유
	 * @param supplyCost
	 * 			[취소] 공급가액
	 * @param tax
	 * 			[취소] 세액
	 * @param serviceFee
	 * 			[취소] 봉사료
	 * @param totalAmount 
	 * 			[취소] 합계금액
	 * @param UserID
	 * 			팝빌연동회원 아이디 
	 * @return Response
	 * @throws PopbillException
	 */
	public Response revokeRegister(String CorpNum, String mgtKey, String orgConfirmNum,
			String orgTradeDate, Boolean smssendYN, Boolean isPartCancel, Integer cancelType, 
			String supplyCost, String tax, String serviceFee, String totalAmount, String UserID) throws PopbillException;	
	
	
	/**
	 * 취소현금영수증 1건 즉시발행
	 * 
	 * @param CorpNum
	 * 			팝빌연동회원 사업자번호
	 * @param mgtKey
	 * 			취소현금영수증 문서관리번호
	 * @param orgConfirmNum
	 * 			원본현금영수증 승인번호
	 * @param orgTradeDate
	 * 			원본현금영수증 거래일자
	 * @return Response
	 * @throws PopbillException
	 */
	public Response revokeRegistIssue(String CorpNum, String mgtKey, String orgConfirmNum, 
			String orgTradeDate) throws PopbillException;
	
	/**
	 * 취소현금영수증 1건 즉시발행
	 * 
	 * @param CorpNum
	 * 			팝빌연동회원 사업자번호
	 * @param mgtKey
	 * 			취소현금영수증 문서관리번호
	 * @param orgConfirmNum
	 * 			원본현금영수증 승인번호
	 * @param orgTradeDate
	 * 			원본현금영수증 거래일자
	 * @param smssendYN
	 * 			발행안내 문자전송여부
	 * @return Response
	 * @throws PopbillException
	 */
	public Response revokeRegistIssue(String CorpNum, String mgtKey, String orgConfirmNum, 
			String orgTradeDate, Boolean smssendYN) throws PopbillException;
	/**
	 * 취소현금영수증 1건 즉시발행
	 * 
	 * @param CorpNum
	 * 			팝빌연동회원 사업자번호
	 * @param mgtKey
	 * 			취소현금영수증 문서관리번호
	 * @param orgConfirmNum
	 * 			원본현금영수증 승인번호
	 * @param orgTradeDate
	 * 			원본현금영수증 거래일자
	 * @param smssendYN
	 * 			발행안내 문자전송여부
	 * @param memo
	 * 			메모
	 * @return Response
	 * @throws PopbillException
	 */
	public Response revokeRegistIssue(String CorpNum, String mgtKey, String orgConfirmNum, 
			String orgTradeDate, Boolean smssendYN, String memo) throws PopbillException;
	
	/**
	 * 취소현금영수증 1건 즉시발행
	 * 
	 * @param CorpNum
	 * 			팝빌연동회원 사업자번호
	 * @param mgtKey
	 * 			취소현금영수증 문서관리번호
	 * @param orgConfirmNum
	 * 			원본현금영수증 승인번호
	 * @param orgTradeDate
	 * 			원본현금영수증 거래일자
	 * @param smssendYN
	 * 			발행안내 문자전송여부
	 * @param memo
	 * 			메모
	 * @param userID
	 * 			팝빌연동회원 아이디
	 * @return Response
	 * @throws PopbillException
	 */
	public Response revokeRegistIssue(String CorpNum, String mgtKey, String orgConfirmNum, 
			String orgTradeDate, Boolean smssendYN, String memo, String userID) throws PopbillException;
	
	
	/**
	 * 취소현금영수증 1건 즉시발행
	 * 
	 * @param CorpNum
	 * 			팝빌연동회원 사업자번호
	 * @param mgtKey
	 * 			취소현금영수증 문서관리번호
	 * @param orgConfirmNum
	 * 			원본현금영수증 승인번호
	 * @param orgTradeDate
	 * 			원본현금영수증 거래일자
	 * @param smssendYN
	 * 			발행안내 문자전송여부
	 * @param memo
	 * 			메모
	 * @param isPartCancel
	 * 			부분취소여부
	 * @param cancelType
	 * 			취소사유
	 * @param supplyCost
	 * 			[취소] 공급가액
	 * @param tax
	 * 			[취소] 세액
	 * @param serviceFee
	 * 			[취소] 봉사료
	 * @param totalAmount 
	 * 			[취소] 합계금액
	 * @return Response
	 * @throws PopbillException
	 */
	public Response revokeRegistIssue(String CorpNum, String mgtKey, String orgConfirmNum, 
			String orgTradeDate, Boolean smssendYN, String memo, Boolean isPartCancel,
			Integer cancelType, String supplyCost, String tax, String serviceFee,
			String totalAmount) throws PopbillException;
	
	/**
	 * 취소현금영수증 1건 즉시발행
	 * 
	 * @param CorpNum
	 * 			팝빌연동회원 사업자번호
	 * @param mgtKey
	 * 			취소현금영수증 문서관리번호
	 * @param orgConfirmNum
	 * 			원본현금영수증 승인번호
	 * @param orgTradeDate
	 * 			원본현금영수증 거래일자
	 * @param smssendYN
	 * 			발행안내 문자전송여부
	 * @param memo
	 * 			메모
	 * @param isPartCancel
	 * 			부분취소여부
	 * @param cancelType
	 * 			취소사유
	 * @param supplyCost
	 * 			[취소] 공급가액
	 * @param tax
	 * 			[취소] 세액
	 * @param serviceFee
	 * 			[취소] 봉사료
	 * @param totalAmount 
	 * 			[취소] 합계금액
	 * @param UserID
	 * 			팝빌회원 아이디
	 * @return Response
	 * @throws PopbillException
	 */
	public Response revokeRegistIssue(String CorpNum, String mgtKey, String orgConfirmNum, 
			String orgTradeDate, Boolean smssendYN, String memo, Boolean isPartCancel,
			Integer cancelType, String supplyCost, String tax, String serviceFee,
			String totalAmount, String UserID) throws PopbillException;	
	
	/**
	 * 임시저장된 현금영수증 수정
	 * @param CorpNum
	 * 			연동회원 사업자번호
	 * @param MgtKey
	 * 			문서관리번호
	 * @param cashbill
	 * 			현금영수증 정보. (see. com.popbill.api.cashbill.Cashbill)
	 * @return Response
	 * @throws PopbillException
	 */
	public Response update(String CorpNum, String MgtKey, 
			Cashbill cashbill) throws PopbillException;
	
	/**
	 * 임시저장된 현금영수증 수정
	 * @param CorpNum
	 * 			연동회원 사업자번호
	 * @param MgtKey
	 * 			문서관리번호
	 * @param cashbill
	 * 			현금영수증 정보. (see. com.popbill.api.cashbill.Cashbill)
	 * @param UserID
	 * 			연동회원 아이디
	 * @return Response
	 * @throws PopbillException
	 */
	public Response update(String CorpNum, String MgtKey, 
			Cashbill cashbill, String UserID) throws PopbillException;
		
	/**
	 * 현금영수증 삭제
	 * @param CorpNum
	 * 			연동회원 사업자번호
	 * @param MgtKey
	 * 			문서관리번호
	 * @return Response
	 * @throws PopbillException
	 */
	public Response delete(String CorpNum, String MgtKey)
			throws PopbillException;
	
	/**
	 * 현금영수증 삭제
	 * @param CorpNum
	 * 			연동회원 사업자번호
	 * @param MgtKey
	 * 			문서관리번호
	 * @param UserID
	 * 			연동회원아이디
	 * @return Response
	 * @throws PopbillException
	 */
	public Response delete(String CorpNum, String MgtKey,
			String UserID) throws PopbillException;

	/**
	 * 현금영수증 발행
	 * @param CorpNum
	 * 			연동회원 사업자번호
	 * @param MgtKey
	 * 			문서관리번호
	 * @param Memo
	 * 			처리 메모
	 * @return Response
	 * @throws PopbillException
	 */
	public Response issue(String CorpNum, String MgtKey,
			String Memo) throws PopbillException;
	
	/**
	 * 현금영수증 발행
	 * @param CorpNum
	 * 			연동회원 사업자번호
	 * @param MgtKey
	 * 			문서관리번호
	 * @param Memo
	 * 			처리 메모
	 * @param UserID
	 * 			연동회원아이디
	 * @return Response
	 * @throws PopbillException
	 */
	public Response issue(String CorpNum, String MgtKey,
			String Memo, String UserID) throws PopbillException;
	
	/**
	 * 현금영수증 발행취소
	 * @param CorpNum
	 * 			연동회원 사업자번호
	 * @param MgtKey
	 * 			문서관리번호
	 * @param Memo
	 * 			처리메모
	 * @return Response
	 * @throws PopbillException
	 */
	public Response cancelIssue(String CorpNum, String MgtKey, 
			String Memo) throws PopbillException;
	
	/**
	 * 현금영수증 발행취소
	 * @param CorpNum
	 * 			연동회원 사업자번호
	 * @param MgtKey
	 * 			문서관리번호
	 * @param Memo
	 * 			처리메모
	 * @param UserID
	 * 			연동회원아이디
	 * @return Response
	 * @throws PopbillException
	 */
	public Response cancelIssue(String CorpNum, String MgtKey, 
			String Memo, String UserID) throws PopbillException;
	
	/**
	 * 현금영수증 알림메일 전송
	 * @param CorpNum
	 * 			연동회원 사업자번호
	 * @param MgtKey
	 * 			문서관리번호
	 * @param Receiver
	 * 			수신 이메일주소
	 * @return Response
	 * @throws PopbillException
	 */
	public Response sendEmail(String CorpNum, String MgtKey,
			String Receiver) throws PopbillException;
	
	/**
	 * 현금영수증 알림메일 전송
	 * @param CorpNum
	 * 			연동회원 사업자번호
	 * @param MgtKey
	 * 			문서관리번호
	 * @param Receiver
	 * 			수신 이메일주소
	 * @param UserID
	 * 			연동회원아이디
	 * @return Response
	 * @throws PopbillException
	 */
	public Response sendEmail(String CorpNum, String MgtKey,
			String Receiver, String UserID) throws PopbillException;

	/**
	 * 알림문자 전송
	 * @param CorpNum
	 * 			연동회원 사업자번호
	 * @param MgtKey
	 * 			문서관리번호
	 * @param Sender
	 * 			발신번호
	 * @param Receiver
	 * 			수신번호
	 * @param Contents
	 * 			메시지내용
	 * @return Response
	 * @throws PopbillException
	 */
	public Response sendSMS(String CorpNum, String MgtKey, String Sender, 
			String Receiver, String Contents) 
			throws PopbillException;
	
	
	/**
	 * 알림문자 전송
	 * @param CorpNum
	 * 			연동회원 사업자번호
	 * @param MgtKey
	 * 			문서관리번호
	 * @param Sender
	 * 			발신번호
	 * @param Receiver
	 * 			수신번호
	 * @param Contents
	 * 			메시지내용
	 * @param UserID
	 * 			연동회원 아이디
	 * @return Response
	 * @throws PopbillException
	 */
	public Response sendSMS(String CorpNum, String MgtKey, String Sender, 
			String Receiver, String Contents, String UserID) 
			throws PopbillException;
	
	/**
	 * 현금영수증 팩스 전송
	 * @param CorpNum
	 * 			연동회원 사업자번호
	 * @param MgtKey
	 * 			문서관리번호
	 * @param Sender
	 * 			발신번호
	 * @param Receiver
	 * 			수신팩스번호
	 * @return Response
	 * @throws PopbillException
	 */
	public Response sendFAX(String CorpNum, String MgtKey, String Sender,
			String Receiver) throws PopbillException;
	
	/**
	 * 현금영수증 팩스 전송
	 * @param CorpNum
	 * 			연동회원 사업자번호
	 * @param MgtKey
	 * 			문서관리번호
	 * @param Sender
	 * 			발신번호
	 * @param Receiver
	 * 			수신팩스번호
	 * @param UserID
	 * 			연동회원아이디
	 * @return Response
	 * @throws PopbillException
	 */
	public Response sendFAX(String CorpNum, String MgtKey, String Sender,
			String Receiver, String UserID) throws PopbillException;
	
	
	/**
	 * 현금영수증 상세내역 조회
	 * @param CorpNum
	 * 			연동회원 사업자번호
	 * @param MgtKey
	 * 			문서관리번호
	 * @return Cashbill
	 * @throws PopbillException
	 */
	public Cashbill getDetailInfo(String CorpNum, String MgtKey)
			throws PopbillException;
	
	/**
	 * 현금영수증 요약/상태 정보 조회
	 * @param CorpNum
	 * 			연동회원 사업자번호
	 * @param MgtKey
	 * 			문서관리번호
	 * @return CashbillInfo
	 * @throws PopbillException
	 */
	public CashbillInfo getInfo(String CorpNum, String MgtKey)
			throws PopbillException;
	
	
	/**
	 * 현금영수증 요약/상태 정보 대량 조회( 최대 1000건)
	 * @param CorpNum
	 * 			연동회원 사업자번
	 * @param MgtKeyList
	 * 			문서관리번호 배열
	 * @return CashbillInfo 배열
	 * @throws PopbillException
	 */
	public CashbillInfo[] getInfos(String CorpNum, String[] MgtKeyList)
			throws PopbillException;
	
	/**
	 * 현금영수증 문서 이력 조회
	 * @param CorpNum
	 * 			연동회원 사업자번호
	 * @param MgtKey
	 * 			문서관리번호
	 * @return CashbillLog 배열
	 * @throws PopbillException
	 */
	public CashbillLog[] getLogs(String CorpNum, String MgtKey)
			throws PopbillException;

	/**
	 * 현금영수증 인쇄 URL
	 * @param CorpNum
	 * 			연동회원 사업자번호
	 * @param MgtKey
	 * 			문서관리번호
	 * @return 팝빌 url
	 * @throws PopbillException
	 */
	public String getPrintURL(String CorpNum, String MgtKey) throws PopbillException;
	
	/**
	 * 현금영수증 인쇄 URL
	 * @param CorpNum
	 * 			연동회원 사업자번호
	 * @param MgtKey
	 * 			문서관리번호
	 * @param UserID
	 * 			연동회원 아이디
	 * @return 팝빌 url
	 * @throws PopbillException
	 */
	public String getPrintURL(String CorpNum, String MgtKey, 
			String UserID) throws PopbillException;
	
	
	/**
	 * 현금영수증 인쇄 URL(공급받는자용)
	 * @param CorpNum
	 * 			연동회원 사업자번호
	 * @param MgtKey
	 * 			문서관리번호
	 * @return 팝빌 URL
	 * @throws PopbillException
	 */
	public String getEPrintURL(String CorpNum, String MgtKey) throws PopbillException;
	
	/**
	 * 현금영수증 인쇄 URL(공급받는자용)
	 * @param CorpNum
	 * 			연동회원 사업자번호
	 * @param MgtKey
	 * 			문서관리번호
	 * @param UserID
	 * 			연동회원 아이디
	 * @return 팝빌 URL
	 * @throws PopbillException
	 */
	public String getEPrintURL(String CorpNum, String MgtKey, 
			String UserID) throws PopbillException;
	
	
	/**
	 * 다량 현금영수증 인쇄 URL 
	 * @param CorpNum
	 * 			연동회원  사업자번호
	 * @param MgtKeyList
	 * 			문서관리번호 배열
	 * @return 팝빌 URL
	 * @throws PopbillException
	 */
	public String getMassPrintURL(String CorpNum, String[] MgtKeyList) throws PopbillException;
	
	
	/**
	 * 다량 현금영수증 인쇄 URL 
	 * @param CorpNum
	 * 			연동회원  사업자번호
	 * @param MgtKeyList
	 * 			문서관리번호 배열
	 * @param UserID
	 * 			연동회원 아이디
	 * @return 팝빌 URL
	 * @throws PopbillException
	 */
	public String getMassPrintURL(String CorpNum, String[] MgtKeyList, 
			String UserID) throws PopbillException;
	
	/**
	 * 공급받는자 메일링크 URL 
	 * @param CorpNum
	 *  		연동회원 사업자번호
	 * @param MgtKey
	 * 			문서관리번호
	 * @return 팝빌 URL
	 * @throws PopbillException
	 */
	public String getMailURL(String CorpNum, String MgtKey) throws PopbillException;
	
	/**
	 * 공급받는자 메일링크 URL 
	 * @param CorpNum
	 *  		연동회원 사업자번호
	 * @param MgtKey
	 * 			문서관리번호
	 * @param UserID
	 * 			연동회원 아이디
	 * @return 팝빌 URL
	 * @throws PopbillException
	 */
	public String getMailURL(String CorpNum, String MgtKey,
			String UserID) throws PopbillException;
	
	
	/**
	 * 현금영수증 내용 보기 팝업 URL
	 * @param CorpNum
	 * 			연동회원 사업자번호
	 * @param MgtKey
	 * 			문서관리번호
	 * @return 팝빌 URL
	 * @throws PopbillException
	 */
	public String getPopUpURL(String CorpNum, String MgtKey) throws PopbillException;
	
	/**
	 * 현금영수증 내용 보기 팝업 URL
	 * @param CorpNum
	 * 			연동회원 사업자번호
	 * @param MgtKey
	 * 			문서관리번호
	 * @param UserID
	 * 			연동회원 아이디
	 * @return 팝빌 URL
	 * @throws PopbillException
	 */
	public String getPopUpURL(String CorpNum, String MgtKey, 
			String UserID) throws PopbillException;
	
	
	/**
	 * 현금영수증 즉시발행 
	 * 
	 * @param CorpNum
	 * 			연동회원 사업자번호 
	 * @param cashbill
	 * 			현금영수증 정보. (see com.popbill.api.cashbill.Cashbill)
	 * @return Response 응답. 
	 * @throws PopbillException
	 */
	public Response registIssue(String CorpNum, Cashbill cashbill)
			throws PopbillException;
	
	
	/**
	 * 현금영수증 즉시발행 
	 * 
	 * @param CorpNum
	 * 			연동회원 사업자번호 
	 * @param cashbill
	 * 			현금영수증 정보. (see com.popbill.api.cashbill.Cashbill)
	 * @param Memo
	 * 			메모
	 * @return Response 응답. 
	 * @throws PopbillException
	 */
	public Response registIssue(String CorpNum, Cashbill cashbill, String Memo)
			throws PopbillException;
	
	/**
	 * 현금영수증 즉시발행 
	 * 
	 * @param CorpNum
	 * 			연동회원 사업자번호 
	 * @param cashbill
	 * 			현금영수증 정보. (see com.popbill.api.cashbill.Cashbill) 
	 * @param Memo
	 * 			메모 
	 * @param UserID
	 * 			연동회원 아이디 
	 * @return Response 응답. 
	 * @throws PopbillException
	 */
	public Response registIssue(String CorpNum, Cashbill cashbill, String Memo,
			String UserID) throws PopbillException;
	

	/**
	 * 현금영수증 목록 조회 
	 * 
	 * @param CorpNum
	 * 			연동회원 사업자번호 
	 * @param DType
	 * 			검색일자 유형, R-등록일자, W-작성일자, I-발행일자 
	 * @param SDate
	 * 			시작일자 (yyyyMMdd) 
	 * @param EDate
	 * 			종료일자 (yyyyMMdd) 
	 * @param State
	 * 			현금영수증 상태코드 배열 
	 * @param TradeType
	 * 			현금영수증 형태 배열, N-일반현금영수증, C-취소현금영수증
	 * @param TradeUsage
	 * 			거래용도 배열, P-소득공제용, C-지출증빙용
	 * @param TaxationType
	 * 			과세형태 배열, T-과세, N-비과세 
	 * @param Page
	 * 			페이지번호, 기본값 1 
	 * @param PerPage
	 * 			페이지당 검색개수, 기본값 500, 최대 1000
	 * @return 현금영수증 목록조회 결과. (see com.popbill.api.cashbill.CBSearchResult)
	 * @throws PopbillException
	 */
	public CBSearchResult search(String CorpNum, String DType, String SDate, 
			String EDate, String[] State, String[] TradeType, String[] TradeUsage,
			String[] TaxationType, int Page, int PerPage, String Order) throws PopbillException;
	
	/**
	 * 현금영수증 목록 조회 
	 * 
	 * @param CorpNum
	 * 			연동회원 사업자번호 
	 * @param DType
	 * 			검색일자 유형, R-등록일자, W-작성일자, I-발행일자 
	 * @param SDate
	 * 			시작일자 (yyyyMMdd) 
	 * @param EDate
	 * 			종료일자 (yyyyMMdd) 
	 * @param State
	 * 			현금영수증 상태코드 배열 
	 * @param TradeType
	 * 			현금영수증 형태 배열, N-일반현금영수증, C-취소현금영수증
	 * @param TradeUsage
	 * 			거래용도 배열, P-소득공제용, C-지출증빙용
	 * @param TaxationType
	 * 			과세형태 배열, T-과세, N-비과세 
	 * @param QString
	 * 			식별번호, 휴대폰번호, 주민등록번호, 사업자등록번호 입력
	 * @param Page
	 * 			페이지번호, 기본값 1 
	 * @param PerPage
	 * 			페이지당 검색개수, 기본값 500, 최대 1000
	 * @return 현금영수증 목록조회 결과. (see com.popbill.api.cashbill.CBSearchResult)
	 * @throws PopbillException
	 */
	public CBSearchResult search(String CorpNum, String DType, String SDate, 
			String EDate, String[] State, String[] TradeType, String[] TradeUsage,
			String[] TaxationType, String QString, int Page, int PerPage, String Order) throws PopbillException;
	/**
	 *  과금정보 확인
	 *  
	 * @param CorpNum
	 * 			연동회원 사업자번호
	 * @return ChargeInfo 과금정보.
	 * @throws PopbillException
	 */
	public ChargeInfo getChargeInfo(String CorpNum) throws PopbillException;
	
	/**
	 * 
	 * @param CorpNum
	 * @param EmailType
	 * @param SendYN
	 * @return
	 * @throws PopbillException
	 */
	public Response updateEmailConfig(String CorpNum, String EmailType, Boolean SendYN)
			throws PopbillException;
	
	
	/**
	 *  알림메일 전송설정 수정
	 *  
	 * @param CorpNum
	 * 			연동회원 사업자번호
	 * @param EmailType
	 * 			메일전송유형
	 * @param SendYN
	 * 			전송 여부 (True = 전송, False = 미전송)
	 * @param UserID
	 * 			팝빌회원 아이디 
	 * @return Response.
	 * @throws PopbillException
	 */
	public Response updateEmailConfig(String CorpNum, String EmailType, Boolean SendYN, 
			String UserID) throws PopbillException;
	
	
	
	/**
	 * 
	 * @param CorpNum
	 * @return
	 * @throws PopbillException
	 */
	public EmailSendConfig[] listEmailConfig(String CorpNum) throws PopbillException;	
	
	/**
	 *  알림메일 전송목록 조회
	 *  
	 * @param CorpNum
	 * 			연동회원 사업자번호
	 * @param UserID
	 * 			팝빌회원 아이디 
	 * @return EmailSendConfig 메일전송정보.
	 * @throws PopbillException
	 */
	public EmailSendConfig[] listEmailConfig(String CorpNum, String UserID) throws PopbillException;	
}

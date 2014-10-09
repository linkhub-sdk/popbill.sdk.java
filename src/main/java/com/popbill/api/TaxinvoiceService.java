/*
 * Copyright 2006-2014 innopost.com, Inc. or its affiliates. All Rights Reserved.
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

import java.io.InputStream;
import java.util.Date;

import com.popbill.api.taxinvoice.EmailPublicKey;
import com.popbill.api.taxinvoice.MgtKeyType;
import com.popbill.api.taxinvoice.Taxinvoice;
import com.popbill.api.taxinvoice.TaxinvoiceInfo;
import com.popbill.api.taxinvoice.TaxinvoiceLog;

/**
 * Taxinvoice Service Interface.
 * 
 * @author KimSeongjun
 * @version 1.0.0
 */
public interface TaxinvoiceService extends BaseService {

	/**
	 * 회원의 세금계산서 발행단가 확인
	 * 
	 * @param CorpNum
	 *            연동회원 사업자번호
	 * @return 단가 (ex. 200.0)
	 * @throws PopbillException
	 */
	public float getUnitCost(String CorpNum) throws PopbillException;

	/**
	 * 연동회원이 등록한 공인인증서의 만료일시 확인.
	 * 
	 * @param CorpNum
	 *            연동회원 사업자번호
	 * @return 만료일시
	 * @throws PopbillException
	 */
	public Date getCertificateExpireDate(String CorpNum)
			throws PopbillException;

	/**
	 * 팝빌 세금계산서 관련 URL 확인. 반환한 url은 30초이내에 브라우져에 표시하여야 함.
	 * 
	 * @param CorpNum
	 *            연동회원 사업자번호.
	 * @param UserID
	 *            연동회원의 회원아이디
	 * @param TOGO
	 *            지정값. (TBOX : 연동문서함, SBOX : 매출보관함, PBOX : 매입보관함, WRITE : 매출작성)
	 * @return 팝빌 URL (AccessToken값 포함. Token값은 응답후 30초까지만 유효함)
	 * @throws PopbillException
	 */
	public String getURL(String CorpNum, String UserID, String TOGO)
			throws PopbillException;

	/**
	 * 연동관리번호 등록여부 확인.
	 * 
	 * @param CorpNum
	 *            연동회원 사업자번호
	 * @param KeyType
	 *            연동관리번호 종류
	 * @param MgtKey
	 *            연동관리번호
	 * @return 사용여부 true : 사용중 , false : 미사용중.
	 * @throws PopbillException
	 */
	public boolean checkMgtKeyInUse(String CorpNum,
			MgtKeyType KeyType, String MgtKey) throws PopbillException;

	/**
	 * 대량사업자간 Email 유통을 위한 국세청인증번호와 이메일 주소 반환.
	 * 
	 * @param CorpNum
	 *            연동회원 사업자번호
	 * @return EmailPublicKey 배열 반환
	 * @throws PopbillException
	 */
	public EmailPublicKey[] getEmailPublicKeys(String CorpNum)
			throws PopbillException;

	/**
	 * 세금계산서 1건 임시저장.
	 * 
	 * @param CorpNum
	 *            연동회원 사업자번호
	 * @param taxinvoice
	 *            세금계산서 정보.(see com.popbill.api.taxinvoice.Taxinvoice)
	 * @return Response 응답.
	 * @throws PopbillException
	 */
	public Response register(String CorpNum, Taxinvoice taxinvoice)
			throws PopbillException;

	/**
	 * 세금계산서 1건 임시저장.
	 * 
	 * @param CorpNum
	 *            연동회원 사업자번호
	 * @param taxinvoice
	 *            세금계산서 정보. (see com.popbill.api.taxinvoice.Taxinvoice)
	 * @param UserID
	 *            회원아이디
	 * @return Response 응답.
	 * @throws PopbillException
	 */
	public Response register(String CorpNum, Taxinvoice taxinvoice,
			String UserID) throws PopbillException;

	/**
	 * 세금계산서 1건 임시저장.
	 * 
	 * @param CorpNum
	 *            연동회원 사업자번호
	 * @param taxinvoice
	 *            세금계산서 정보. (see com.popbill.api.taxinvoice.Taxinvoice)
	 * @param UserID
	 *            회원아이디
	 * @param writeSpecification
	 *            거래명세서 동시작성여부. 기본값 : false
	 * @return Response 응답.
	 * @throws PopbillException
	 */
	public Response register(String CorpNum, Taxinvoice taxinvoice,
			String UserID, boolean writeSpecification) throws PopbillException;

	/**
	 * 임시저장된 세금계산서 정보 수정.
	 * 
	 * @param CorpNum
	 *            연동회원 사업자번호.
	 * @param KeyType
	 *            연동관리번호 종류.
	 * @param MgtKey
	 *            연동관리번호
	 * @param taxinvoice
	 *            세금계산서 정보. (see com.popbill.api.taxinvoice.Taxinvoice)
	 * @return Response 응답
	 * @throws PopbillException
	 */
	public Response update(String CorpNum, MgtKeyType KeyType,
			String MgtKey, Taxinvoice taxinvoice) throws PopbillException;

	/**
	 * 임시저장된 세금계산서 정보 수정.
	 * 
	 * @param CorpNum
	 *            연동회원 사업자번호.
	 * @param KeyType
	 *            연동관리번호 종류.
	 * @param MgtKey
	 *            연동관리번호
	 * @param taxinvoice
	 *            세금계산서 정보. (see com.popbill.api.taxinvoice.Taxinvoice)
	 * @param UserID
	 *            회원 아이디.
	 * @return Response 응답
	 * @throws PopbillException
	 */
	public Response update(String CorpNum, MgtKeyType KeyType,
			String MgtKey, Taxinvoice taxinvoice, String UserID)
			throws PopbillException;

	/**
	 * 세금계산서 삭제. 삭제가능한 건만 삭제처리되고, 불가능한 건은 PopbillException 발생함.
	 * 
	 * @param CorpNum
	 *            연동회원 사업자번호
	 * @param KeyType
	 *            연동관리번호 종류
	 * @param MgtKey
	 *            연동관리번호
	 * @return Response 응답.
	 * @throws PopbillException
	 */
	public Response delete(String CorpNum, MgtKeyType KeyType,
			String MgtKey) throws PopbillException;

	/**
	 * 세금계산서 삭제. 삭제가능한 건만 삭제처리되고, 불가능한 건은 PopbillException 발생함.
	 * 
	 * @param CorpNum
	 *            연동회원 사업자번호
	 * @param KeyType
	 *            연동관리번호 종류
	 * @param MgtKey
	 *            연동관리번호
	 * @param UserID
	 *            회원아이디
	 * @return Response 응답.
	 * @throws PopbillException
	 */
	public Response delete(String CorpNum, MgtKeyType KeyType,
			String MgtKey, String UserID) throws PopbillException;

	/**
	 * 정발행 세금계산서 발행예정 처리.
	 * 
	 * @param CorpNum
	 *            연동회원 사업자번호
	 * @param KeyType
	 *            연동관리번호 종류
	 * @param MgtKey
	 *            연동관리번호
	 * @param Memo
	 *            처리 메모
	 * @return Response 응답
	 * @throws PopbillException
	 */
	public Response send(String CorpNum, MgtKeyType KeyType,
			String MgtKey, String Memo) throws PopbillException;

	/**
	 * 정발행 세금계산서 발행예정 처리.
	 * 
	 * @param CorpNum
	 *            연동회원 사업자번호
	 * @param KeyType
	 *            연동관리번호 종류
	 * @param MgtKey
	 *            연동관리번호
	 * @param Memo
	 *            처리 메모
	 * @param UserID
	 *            회원 아이디
	 * @return Response 응답
	 * @throws PopbillException
	 */
	public Response send(String CorpNum, MgtKeyType KeyType,
			String MgtKey, String Memo, String UserID) throws PopbillException;

	/**
	 * 발행예정 세금계산서의 취소 처리.
	 * 
	 * @param CorpNum
	 *            연동회원 사업자번호
	 * @param KeyType
	 *            연동관리번호 종류
	 * @param MgtKey
	 *            연동관리번호
	 * @param Memo
	 *            처리 메모
	 * @return Response 응답
	 * @throws PopbillException
	 */
	public Response cancelSend(String CorpNum, MgtKeyType KeyType,
			String MgtKey, String Memo) throws PopbillException;

	/**
	 * 발행예정 세금계산서의 취소 처리.
	 * 
	 * @param CorpNum
	 *            연동회원 사업자번호
	 * @param KeyType
	 *            연동관리번호 종류
	 * @param MgtKey
	 *            연동관리번호
	 * @param Memo
	 *            처리 메모
	 * @param UserID
	 *            회원 아이디
	 * @return Response 응답
	 * @throws PopbillException
	 */
	public Response cancelSend(String CorpNum, MgtKeyType KeyType,
			String MgtKey, String Memo, String UserID) throws PopbillException;

	/**
	 * 발행예정 세금계산서의 공급받는자의 승인 처리.
	 * 
	 * @param CorpNum
	 *            연동회원 사업자번호
	 * @param KeyType
	 *            연동관리번호 종류
	 * @param MgtKey
	 *            연동관리번호
	 * @param Memo
	 *            처리 메모
	 * @return Response 응답
	 * @throws PopbillException
	 */
	public Response accept(String CorpNum, MgtKeyType KeyType,
			String MgtKey, String Memo) throws PopbillException;

	/**
	 * 발행예정 세금계산서의 공급받는자의 승인 처리.
	 * 
	 * @param CorpNum
	 *            연동회원 사업자번호
	 * @param KeyType
	 *            연동관리번호 종류
	 * @param MgtKey
	 *            연동관리번호
	 * @param Memo
	 *            처리 메모
	 * @param UserID
	 *            회원 아이디
	 * @return Response 응답
	 * @throws PopbillException
	 */
	public Response accept(String CorpNum, MgtKeyType KeyType,
			String MgtKey, String Memo, String UserID) throws PopbillException;

	/**
	 * 발행예정 세금계산서의 공급받는자의 거부 처리.
	 * 
	 * @param CorpNum
	 *            연동회원 사업자번호
	 * @param KeyType
	 *            연동관리번호 종류
	 * @param MgtKey
	 *            연동관리번호
	 * @param Memo
	 *            처리 메모
	 * @return Response 응답
	 * @throws PopbillException
	 */
	public Response deny(String CorpNum, MgtKeyType KeyType,
			String MgtKey, String Memo) throws PopbillException;

	/**
	 * 발행예정 세금계산서의 공급받는자의 거부 처리.
	 * 
	 * @param CorpNum
	 *            연동회원 사업자번호
	 * @param KeyType
	 *            연동관리번호 종류
	 * @param MgtKey
	 *            연동관리번호
	 * @param Memo
	 *            처리 메모
	 * @param UserID
	 *            회원 아이디
	 * @return Response 응답
	 * @throws PopbillException
	 */
	public Response deny(String CorpNum, MgtKeyType KeyType,
			String MgtKey, String Memo, String UserID) throws PopbillException;

	/**
	 * 세금계산서 발행.
	 * 
	 * @param CorpNum
	 *            연동회원 사업자번호
	 * @param KeyType
	 *            연동관리번호 종류
	 * @param MgtKey
	 *            연동관리번호
	 * @param Memo
	 *            처리 메모
	 * @return Response 응답.
	 * @throws PopbillException
	 */
	public Response issue(String CorpNum, MgtKeyType KeyType,
			String MgtKey, String Memo) throws PopbillException;

	/**
	 * 세금계산서 발행.
	 * 
	 * @param CorpNum
	 *            연동회원 사업자번호
	 * @param KeyType
	 *            연동관리번호 종류
	 * @param MgtKey
	 *            연동관리번호
	 * @param Memo
	 *            처리 메모
	 * @param UserID
	 *            회원 아이디
	 * @return Response 응답.
	 * @throws PopbillException
	 */
	public Response issue(String CorpNum, MgtKeyType KeyType,
			String MgtKey, String Memo, String UserID) throws PopbillException;

	/**
	 * 세금계산서 발행.
	 * 
	 * @param CorpNum
	 *            연동회원 사업자번호
	 * @param KeyType
	 *            연동관리번호 종류
	 * @param MgtKey
	 *            연동관리번호
	 * @param Memo
	 *            처리 메모
	 * @param ForceIssue
	 *            지연발행시 강재발행.
	 * @param UserID
	 *            회원 아이디
	 * @return Response 응답.
	 * @throws PopbillException
	 */
	public Response issue(String CorpNum, MgtKeyType KeyType,
			String MgtKey, String Memo, boolean ForceIssue, String UserID)
			throws PopbillException;

	/**
	 * 세금계산서 발행.
	 * 
	 * @param CorpNum
	 *            연동회원 사업자번호
	 * @param KeyType
	 *            연동관리번호 종류
	 * @param MgtKey
	 *            연동관리번호
	 * @param Memo
	 *            처리 메모
	 * @param EmailSubject
	 *            발행시 전달 이메일의 제목설정.
	 * @param ForceIssue
	 *            지연발행시 강재발행.
	 * @param UserID
	 *            회원 아이디
	 * @return Response 응답.
	 * @throws PopbillException
	 */
	public Response issue(String CorpNum, MgtKeyType KeyType,
			String MgtKey, String Memo, String EmailSubject,
			boolean ForceIssue, String UserID) throws PopbillException;

	/**
	 * 세금계산서 발행취소 처리.
	 * 
	 * @param CorpNum
	 *            연동회원 사업자번호
	 * @param KeyType
	 *            연동관리번호 종류
	 * @param MgtKey
	 *            연동관리번호
	 * @param Memo
	 *            처리 메모
	 * @return Response 응답.
	 * @throws PopbillException
	 */
	public Response cancelIssue(String CorpNum, MgtKeyType KeyType,
			String MgtKey, String Memo) throws PopbillException;

	/**
	 * 세금계산서 발행취소 처리.
	 * 
	 * @param CorpNum
	 *            연동회원 사업자번호
	 * @param KeyType
	 *            연동관리번호 종류
	 * @param MgtKey
	 *            연동관리번호
	 * @param Memo
	 *            처리 메모
	 * @param UserID
	 *            회원아이디
	 * @return Response 응답.
	 * @throws PopbillException
	 */
	public Response cancelIssue(String CorpNum, MgtKeyType KeyType,
			String MgtKey, String Memo, String UserID) throws PopbillException;

	/**
	 * 역발행 세금계산서의 역)발행요청 처리.
	 * 
	 * @param CorpNum
	 *            연동회원 사업자번호
	 * @param KeyType
	 *            연동관리번호 종류
	 * @param MgtKey
	 *            연동관리번호
	 * @param Memo
	 *            처리 메모
	 * @return Response 응답.
	 * @throws PopbillException
	 */
	public Response request(String CorpNum, MgtKeyType KeyType,
			String MgtKey, String Memo) throws PopbillException;

	/**
	 * 역발행 세금계산서의 역)발행요청 처리.
	 * 
	 * @param CorpNum
	 *            연동회원 사업자번호
	 * @param KeyType
	 *            연동관리번호 종류
	 * @param MgtKey
	 *            연동관리번호
	 * @param Memo
	 *            처리 메모
	 * @param UserID
	 *            회원 아이디
	 * @return Response 응답.
	 * @throws PopbillException
	 */
	public Response request(String CorpNum, MgtKeyType KeyType,
			String MgtKey, String Memo, String UserID) throws PopbillException;

	/**
	 * 역발행 세금계산서의 공급자의 발행거부 처리.
	 * 
	 * @param CorpNum
	 *            연동회원 사업자번호
	 * @param KeyType
	 *            연동관리번호 종류
	 * @param MgtKey
	 *            연동관리번호
	 * @param Memo
	 *            처리 메모
	 * @return Response 응답.
	 * @throws PopbillException
	 */
	public Response refuse(String CorpNum, MgtKeyType KeyType,
			String MgtKey, String Memo) throws PopbillException;

	/**
	 * 역발행 세금계산서의 공급자의 발행거부 처리.
	 * 
	 * @param CorpNum
	 *            연동회원 사업자번호
	 * @param KeyType
	 *            연동관리번호 종류
	 * @param MgtKey
	 *            연동관리번호
	 * @param Memo
	 *            처리 메모
	 * @param UserID
	 *            회원 아이디
	 * @return Response 응답.
	 * @throws PopbillException
	 */
	public Response refuse(String CorpNum, MgtKeyType KeyType,
			String MgtKey, String Memo, String UserID) throws PopbillException;

	/**
	 * 역발행 세금계산서의 역)발행요청 취소 처리.
	 * 
	 * @param CorpNum
	 *            연동회원 사업자번호
	 * @param KeyType
	 *            연동관리번호 종류
	 * @param MgtKey
	 *            연동관리번호
	 * @param Memo
	 *            처리 메모
	 * @return Response 응답.
	 * @throws PopbillException
	 */
	public Response cancelRequest(String CorpNum, MgtKeyType KeyType,
			String MgtKey, String Memo) throws PopbillException;

	/**
	 * 역발행 세금계산서의 역)발행요청 취소 처리.
	 * 
	 * @param CorpNum
	 *            연동회원 사업자번호
	 * @param KeyType
	 *            연동관리번호 종류
	 * @param MgtKey
	 *            연동관리번호
	 * @param Memo
	 *            처리 메모
	 * @param UserID
	 *            회원 아이디
	 * @return Response 응답.
	 * @throws PopbillException
	 */
	public Response cancelRequest(String CorpNum, MgtKeyType KeyType,
			String MgtKey, String Memo, String UserID) throws PopbillException;

	/**
	 * 발행된 세금계산서의 국세청 즉시 전송 요청.
	 * 
	 * @param CorpNum
	 *            연동회원 사업자번호
	 * @param KeyType
	 *            연동관리번호 종류
	 * @param MgtKey
	 *            연동관리번호
	 * @return Response 응답
	 * @throws PopbillException
	 */
	public Response sendToNTS(String CorpNum, MgtKeyType KeyType,
			String MgtKey) throws PopbillException;

	/**
	 * 발행된 세금계산서의 국세청 즉시 전송 요청.
	 * 
	 * @param CorpNum
	 *            연동회원 사업자번호
	 * @param KeyType
	 *            연동관리번호 종류
	 * @param MgtKey
	 *            연동관리번호
	 * @param UserID
	 *            회원 아이
	 * @return Response 응답
	 * @throws PopbillException
	 */
	public Response sendToNTS(String CorpNum, MgtKeyType KeyType,
			String MgtKey, String UserID) throws PopbillException;

	/**
	 * 이메일 재전송 요청.
	 * 
	 * @param CorpNum
	 *            연동회원 사업자번호
	 * @param KeyType
	 *            연동관리번호 종류
	 * @param MgtKey
	 *            연동관리번호
	 * @param Receiver
	 *            수신이메일 주소
	 * @return Response 응답
	 * @throws PopbillException
	 */
	public Response sendEmail(String CorpNum, MgtKeyType KeyType,
			String MgtKey, String Receiver) throws PopbillException;

	/**
	 * 이메일 재전송 요청.
	 * 
	 * @param CorpNum
	 *            연동회원 사업자번호
	 * @param KeyType
	 *            연동관리번호 종류
	 * @param MgtKey
	 *            연동관리번호
	 * @param Receiver
	 *            수신이메일 주소
	 * @param UserID
	 *            회원 아이디
	 * @return Response 응답
	 * @throws PopbillException
	 */
	public Response sendEmail(String CorpNum, MgtKeyType KeyType,
			String MgtKey, String Receiver, String UserID)
			throws PopbillException;

	/**
	 * 문자 재전송 요청
	 * 
	 * @param CorpNum
	 *            연동회원 사업자번호
	 * @param KeyType
	 *            연동관리번호 종류
	 * @param MgtKey
	 *            연동관리번호
	 * @param Sender
	 *            발신자번호
	 * @param Receiver
	 *            수신자번호
	 * @param Contents
	 *            문자메시지 내용
	 * @return Response 응답
	 * @throws PopbillException
	 */
	public Response sendSMS(String CorpNum, MgtKeyType KeyType,
			String MgtKey, String Sender, String Receiver, String Contents)
			throws PopbillException;

	/**
	 * 문자 재전송 요청
	 * 
	 * @param CorpNum
	 *            연동회원 사업자번호
	 * @param KeyType
	 *            연동관리번호 종류
	 * @param MgtKey
	 *            연동관리번호
	 * @param Sender
	 *            발신자번호
	 * @param Receiver
	 *            수신자번호
	 * @param Contents
	 *            문자메시지 내용
	 * @param UserID
	 *            회원 아이디
	 * @return Response 응답
	 * @throws PopbillException
	 */
	public Response sendSMS(String CorpNum, MgtKeyType KeyType,
			String MgtKey, String Sender, String Receiver, String Contents,
			String UserID) throws PopbillException;

	/**
	 * 팩스 전송 요청
	 * 
	 * @param CorpNum
	 *            연동회원 사업자번호
	 * @param KeyType
	 *            연동관리번호 종류
	 * @param MgtKey
	 *            연동관리번호
	 * @param Sender
	 *            발신자 번호
	 * @param Receiver
	 *            수신자 번호
	 * @return Response response
	 * @throws PopbillException
	 */
	public Response sendFAX(String CorpNum, MgtKeyType KeyType,
			String MgtKey, String Sender, String Receiver)
			throws PopbillException;

	/**
	 * 팩스 전송 요청
	 * 
	 * @param CorpNum
	 *            연동회원 사업자번호
	 * @param KeyType
	 *            연동관리번호 종류
	 * @param MgtKey
	 *            연동관리번호
	 * @param Sender
	 *            발신자 번호
	 * @param Receiver
	 *            수신자 번호
	 * @param UserID
	 *            회원 아이디
	 * @return Response response
	 * @throws PopbillException
	 */
	public Response sendFAX(String CorpNum, MgtKeyType KeyType,
			String MgtKey, String Sender, String Receiver, String UserID)
			throws PopbillException;

	/**
	 * 세금계산서 상세정보 확인.
	 * 
	 * @param CorpNum
	 *            연동회원 사업자번호
	 * @param KeyType
	 *            연동관리번호 종류
	 * @param MgtKey
	 *            연동관리번호
	 * @return Response 응답
	 * @throws PopbillException
	 */
	public Taxinvoice getDetailInfo(String CorpNum,
			MgtKeyType KeyType, String MgtKey) throws PopbillException;

	/**
	 * 세금계산서 상태정보 확인.
	 * 
	 * @param CorpNum
	 *            연동회원 사업자번호
	 * @param KeyType
	 *            연동관리번호 종류
	 * @param MgtKey
	 *            연동관리번호
	 * @return Response 응답
	 * @throws PopbillException
	 */
	public TaxinvoiceInfo getInfo(String CorpNum, MgtKeyType KeyType,
			String MgtKey) throws PopbillException;

	/**
	 * 세금계산서 상태정보 대량(최대 1000건) 확인.
	 * 
	 * @param CorpNum
	 *            연동회원 사업자번호
	 * @param KeyType
	 *            연동관리번호 종류
	 * @param MgtKeyList
	 *            연동관리번호 목록
	 * @return TaxinvoiceInfo 배열.
	 * @throws PopbillException
	 */
	public TaxinvoiceInfo[] getInfos(String CorpNum,
			MgtKeyType KeyType, String[] MgtKeyList) throws PopbillException;

	/**
	 * 세금계산서 문서이력 확인.
	 * 
	 * @param CorpNum
	 *            연동회원 사업자번호
	 * @param KeyType
	 *            연동관리번호 종류
	 * @param MgtKey
	 *            연동관리번호
	 * @return TaxinvoiceLog 배열.
	 * @throws PopbillException
	 */
	public TaxinvoiceLog[] getLogs(String CorpNum, MgtKeyType KeyType,
			String MgtKey) throws PopbillException;

	/**
	 * 세금계산서 팝빌화면 팝업 URL 확인.
	 * 
	 * @param CorpNum
	 *            연동회원 사업자번호
	 * @param KeyType
	 *            연동관리번호 종류
	 * @param MgtKey
	 *            연동관리번호
	 * @param UserID
	 *            회원 아이디(필수)
	 * @return 팝빌 URL.
	 * @throws PopbillException
	 */
	public String getPopUpURL(String CorpNum, MgtKeyType KeyType,
			String MgtKey, String UserID) throws PopbillException;

	/**
	 * 세금계산서 이메일의 링크 URL 확인.
	 * 
	 * @param CorpNum
	 *            연동회원 사업자번호
	 * @param KeyType
	 *            연동관리번호 종류
	 * @param MgtKey
	 *            연동관리번호
	 * @param UserID
	 *            회원 아이디(필수)
	 * @return 팝빌 URL.
	 * @throws PopbillException
	 */
	public String getMailURL(String CorpNum, MgtKeyType KeyType,
			String MgtKey, String UserID) throws PopbillException;

	/**
	 * 세금계산서 팝빌 인쇄화면 URL 확인.
	 * 
	 * @param CorpNum
	 *            연동회원 사업자번호
	 * @param KeyType
	 *            연동관리번호 종류
	 * @param MgtKey
	 *            연동관리번호
	 * @param UserID
	 *            회원 아이디(필수)
	 * @return 팝빌 URL.
	 * @throws PopbillException
	 */
	public String getPrintURL(String CorpNum, MgtKeyType KeyType,
			String MgtKey, String UserID) throws PopbillException;

	/**
	 * 세금계산서 팝빌 인쇄화면(공급받는자요) URL 확인.
	 * 
	 * @param CorpNum
	 *            연동회원 사업자번호
	 * @param KeyType
	 *            연동관리번호 종류
	 * @param MgtKey
	 *            연동관리번호
	 * @param UserID
	 *            회원 아이디(필수)
	 * @return 팝빌 URL.
	 * @throws PopbillException
	 */
	public String getEPrintURL(String CorpNum, MgtKeyType KeyType,
			String MgtKey, String UserID) throws PopbillException;

	/**
	 * 다량 인쇄 URL 확인.
	 * 
	 * @param CorpNum
	 *            연동회원 사업자번호
	 * @param KeyType
	 *            연동관리번호 종류
	 * @param MgtKeyList
	 *            연동관리번호 목록
	 * @param UserID
	 *            회원 아이디(필수)
	 * @return 팝빌 URL
	 * @throws PopbillException
	 */
	public String getMassPrintURL(String CorpNum, MgtKeyType KeyType,
			String[] MgtKeyList, String UserID) throws PopbillException;

	/**
	 * 임시저장 세금계산서에 첨부파일 추가.
	 * 
	 * @param CorpNum
	 *            연동회원 사업자번호
	 * @param KeyType
	 *            연동관리번호 종류
	 * @param MgtKey
	 *            연동관리번호
	 * @param DisplayName
	 *            파일 표시명(ex. "통장사본.jpg")
	 * @param FileData
	 *            파일 스트림.
	 * @return Response 응답.
	 * @throws PopbillException
	 */
	public Response attachFile(String CorpNum, MgtKeyType KeyType,
			String MgtKey, String DisplayName, InputStream FileData)
			throws PopbillException;

	/**
	 * 임시저장 세금계산서에 첨부파일 추가.
	 * 
	 * @param CorpNum
	 *            연동회원 사업자번호
	 * @param KeyType
	 *            연동관리번호 종류
	 * @param MgtKey
	 *            연동관리번호
	 * @param DisplayName
	 *            파일 표시명(ex. "통장사본.jpg")
	 * @param FileData
	 *            파일 스트림.
	 * @param UserID
	 *            회원 아이디
	 * @return Response 응답.
	 * @throws PopbillException
	 */
	public Response attachFile(String CorpNum, MgtKeyType KeyType,
			String MgtKey, String DisplayName, InputStream FileData,
			String UserID) throws PopbillException;

	/**
	 * 첨부파일 목록 확인.
	 * 
	 * @param CorpNum
	 *            연동회원 사업자번호
	 * @param KeyType
	 *            연동관리번호 종류
	 * @param MgtKey
	 *            연동관리번호
	 * @return AttachedFile 배열.
	 * @throws PopbillException
	 */
	public AttachedFile[] getFiles(String CorpNum, MgtKeyType KeyType,
			String MgtKey) throws PopbillException;

	/**
	 * 첨부파일 삭제.
	 * 
	 * @param CorpNum
	 *            연동회원 사업자번호
	 * @param KeyType
	 *            연동관리번호 종류
	 * @param MgtKey
	 *            연동관리번호
	 * @param FileID
	 *            파일아이디. (AttachedFile.getAttachedFile() 로 확인된 문자열)
	 * @return Response 응답.
	 * @throws PopbillException
	 */
	public Response deleteFile(String CorpNum, MgtKeyType KeyType,
			String MgtKey, String FileID) throws PopbillException;

	/**
	 * 첨부파일 삭제.
	 * 
	 * @param CorpNum
	 *            연동회원 사업자번호
	 * @param KeyType
	 *            연동관리번호 종류
	 * @param MgtKey
	 *            연동관리번호
	 * @param FileID
	 *            파일아이디. (AttachedFile.getAttachedFile() 로 확인된 문자열)
	 * @param UserID
	 *            회원 아이디
	 * @return Response 응답.
	 * @throws PopbillException
	 */
	public Response deleteFile(String CorpNum, MgtKeyType KeyType,
			String MgtKey, String FileID, String UserID)
			throws PopbillException;

}
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
package com.popbill.api.taxinvoice;

import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.popbill.api.AttachedFile;
import com.popbill.api.BaseService;
import com.popbill.api.PopbillException;
import com.popbill.api.Response;

/**
 * Taxinvoice Service Proxy Class. Implementation of Popbill Taxinvoice API.
 * 
 * @author KimSeongjun
 * @see http://www.popbill.com
 * @version 1.0.0
 */
public class TaxinvoiceService extends BaseService {

	@Override
	protected List<String> getScopes() {
		return Arrays.asList("110");
	}

	/**
	 * 회원의 세금계산서 발행단가 확인
	 * 
	 * @param CorpNum
	 *            연동회원 사업자번호
	 * @return 단가 (ex. 200.0)
	 * @throws PopbillException
	 */
	public float getUnitCost(String CorpNum) throws PopbillException {
		UnitCostResponse response = httpget("/Taxinvoice?cfg=UNITCOST",
				CorpNum, null, UnitCostResponse.class);

		return response.unitCost;
	}

	/**
	 * 연동회원이 등록한 공인인증서의 만료일시 확인.
	 * 
	 * @param CorpNum
	 *            연동회원 사업자번호
	 * @return 만료일시
	 * @throws PopbillException
	 */
	public Date getCertificateExpireDate(String CorpNum)
			throws PopbillException {

		CertResponse response = httpget("/Taxinvoice?cfg=CERT", CorpNum, null,
				CertResponse.class);

		try {
			return new SimpleDateFormat("yyyyMMddHHmmss")
					.parse(response.certificateExpiration);
		} catch (ParseException e) {
			throw new PopbillException(-99999999, "날자형식 포맷변환 실패["
					+ response.certificateExpiration + "]", e);
		}

	}

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
			throws PopbillException {

		URLResponse response = httpget("/Taxinvoice?TG=" + TOGO, CorpNum,
				UserID, URLResponse.class);

		return response.url;
	}

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
	public boolean checkMgtKeyInUse(String CorpNum, MgtKeyType KeyType,
			String MgtKey) throws PopbillException {

		if (KeyType == null)
			throw new PopbillException(-99999999, "관리번호형태가 입력되지 않았습니다.");
		if (MgtKey == null || MgtKey.isEmpty())
			throw new PopbillException(-99999999, "관리번호가 입력되지 않았습니다.");

		try {
			TaxinvoiceInfo info = httpget("/Taxinvoice/" + KeyType.name() + "/"
					+ MgtKey, CorpNum, null, TaxinvoiceInfo.class);

			return (info.getItemKey() == null || info.getItemKey().isEmpty()) == false;
		} catch (PopbillException PE) {
			if (PE.getCode() == -11000005)
				return false;
			throw PE;
		}
	}

	/**
	 * 대량사업자간 Email 유통을 위한 국세청인증번호와 이메일 주소 반환.
	 * 
	 * @param CorpNum
	 *            연동회원 사업자번호
	 * @return EmailPublicKey 배열 반환
	 * @throws PopbillException
	 */
	public EmailPublicKey[] getEmailPublicKeys(String CorpNum)
			throws PopbillException {

		return httpget("/Taxinvoice/EmailPublicKeys", CorpNum, null,
				EmailPublicKey[].class);
	}

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
			throws PopbillException {
		return register(CorpNum, taxinvoice, null);
	}

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
			String UserID) throws PopbillException {
		return register(CorpNum, taxinvoice, UserID, false);
	}

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
			String UserID, boolean writeSpecification) throws PopbillException {
		if (taxinvoice == null)
			throw new PopbillException(-99999999, "세금계산서 정보가 입력되지 않았습니다.");

		String PostData = toJsonString(taxinvoice);

		if (writeSpecification) {
			PostData = "{\"writeSpecification\":true," + PostData.substring(1);
		}

		return httppost("/Taxinvoice", CorpNum, PostData, UserID,
				Response.class);
	}

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
	public Response update(String CorpNum, MgtKeyType KeyType, String MgtKey,
			Taxinvoice taxinvoice) throws PopbillException {
		return update(CorpNum, KeyType, MgtKey, taxinvoice, null);

	}

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
	public Response update(String CorpNum, MgtKeyType KeyType, String MgtKey,
			Taxinvoice taxinvoice, String UserID) throws PopbillException {
		if (KeyType == null)
			throw new PopbillException(-99999999, "관리번호형태가 입력되지 않았습니다.");
		if (MgtKey == null || MgtKey.isEmpty())
			throw new PopbillException(-99999999, "관리번호가 입력되지 않았습니다.");
		if (taxinvoice == null)
			throw new PopbillException(-99999999, "세금계산서 정보가 입력되지 않았습니다.");

		String PostData = toJsonString(taxinvoice);

		return httppost("/Taxinvoice/" + KeyType.name() + "/" + MgtKey,
				CorpNum, PostData, UserID, "PATCH", Response.class);
	}

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
	public Response delete(String CorpNum, MgtKeyType KeyType, String MgtKey)
			throws PopbillException {
		return delete(CorpNum, KeyType, MgtKey, null);
	}

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
	public Response delete(String CorpNum, MgtKeyType KeyType, String MgtKey,
			String UserID) throws PopbillException {
		if (KeyType == null)
			throw new PopbillException(-99999999, "관리번호형태가 입력되지 않았습니다.");
		if (MgtKey == null || MgtKey.isEmpty())
			throw new PopbillException(-99999999, "관리번호가 입력되지 않았습니다.");

		return httppost("/Taxinvoice/" + KeyType.name() + "/" + MgtKey,
				CorpNum, null, UserID, "DELETE", Response.class);
	}

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
	public Response send(String CorpNum, MgtKeyType KeyType, String MgtKey,
			String Memo) throws PopbillException {
		return send(CorpNum, KeyType, MgtKey, Memo, null);
	}

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
	public Response send(String CorpNum, MgtKeyType KeyType, String MgtKey,
			String Memo, String UserID) throws PopbillException {
		if (KeyType == null)
			throw new PopbillException(-99999999, "관리번호형태가 입력되지 않았습니다.");
		if (MgtKey == null || MgtKey.isEmpty())
			throw new PopbillException(-99999999, "관리번호가 입력되지 않았습니다.");

		String PostData = toJsonString(new MemoRequest(Memo));

		return httppost("/Taxinvoice/" + KeyType.name() + "/" + MgtKey,
				CorpNum, PostData, UserID, "SEND", Response.class);
	}

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
			String MgtKey, String Memo) throws PopbillException {
		return cancelSend(CorpNum, KeyType, MgtKey, Memo, null);

	}

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
			String MgtKey, String Memo, String UserID) throws PopbillException {
		if (KeyType == null)
			throw new PopbillException(-99999999, "관리번호형태가 입력되지 않았습니다.");
		if (MgtKey == null || MgtKey.isEmpty())
			throw new PopbillException(-99999999, "관리번호가 입력되지 않았습니다.");

		String PostData = toJsonString(new MemoRequest(Memo));

		return httppost("/Taxinvoice/" + KeyType.name() + "/" + MgtKey,
				CorpNum, PostData, UserID, "CANCELSEND", Response.class);
	}

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
	public Response accept(String CorpNum, MgtKeyType KeyType, String MgtKey,
			String Memo) throws PopbillException {
		return accept(CorpNum, KeyType, MgtKey, Memo, null);
	}

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
	public Response accept(String CorpNum, MgtKeyType KeyType, String MgtKey,
			String Memo, String UserID) throws PopbillException {
		if (KeyType == null)
			throw new PopbillException(-99999999, "관리번호형태가 입력되지 않았습니다.");
		if (MgtKey == null || MgtKey.isEmpty())
			throw new PopbillException(-99999999, "관리번호가 입력되지 않았습니다.");

		String PostData = toJsonString(new MemoRequest(Memo));

		return httppost("/Taxinvoice/" + KeyType.name() + "/" + MgtKey,
				CorpNum, PostData, UserID, "ACCEPT", Response.class);
	}

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
	public Response deny(String CorpNum, MgtKeyType KeyType, String MgtKey,
			String Memo) throws PopbillException {
		return deny(CorpNum, KeyType, MgtKey, Memo, null);
	}

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
	public Response deny(String CorpNum, MgtKeyType KeyType, String MgtKey,
			String Memo, String UserID) throws PopbillException {
		if (KeyType == null)
			throw new PopbillException(-99999999, "관리번호형태가 입력되지 않았습니다.");
		if (MgtKey == null || MgtKey.isEmpty())
			throw new PopbillException(-99999999, "관리번호가 입력되지 않았습니다.");

		String PostData = toJsonString(new MemoRequest(Memo));

		return httppost("/Taxinvoice/" + KeyType.name() + "/" + MgtKey,
				CorpNum, PostData, UserID, "DENY", Response.class);
	}

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
	public Response issue(String CorpNum, MgtKeyType KeyType, String MgtKey,
			String Memo) throws PopbillException {
		return issue(CorpNum, KeyType, MgtKey, Memo, null);
	}

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
	public Response issue(String CorpNum, MgtKeyType KeyType, String MgtKey,
			String Memo, String UserID) throws PopbillException {
		return issue(CorpNum, KeyType, MgtKey, Memo, null, false, UserID);
	}

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
	public Response issue(String CorpNum, MgtKeyType KeyType, String MgtKey,
			String Memo, boolean ForceIssue, String UserID)
			throws PopbillException {
		return issue(CorpNum, KeyType, MgtKey, Memo, null, ForceIssue, UserID);
	}

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
	public Response issue(String CorpNum, MgtKeyType KeyType, String MgtKey,
			String Memo, String EmailSubject, boolean ForceIssue, String UserID)
			throws PopbillException {
		if (KeyType == null)
			throw new PopbillException(-99999999, "관리번호형태가 입력되지 않았습니다.");
		if (MgtKey == null || MgtKey.isEmpty())
			throw new PopbillException(-99999999, "관리번호가 입력되지 않았습니다.");

		IssueRequest request = new IssueRequest();
		request.memo = Memo;
		request.emailSubject = EmailSubject;
		request.forceIssue = ForceIssue;

		String PostData = toJsonString(request);

		return httppost("/Taxinvoice/" + KeyType.name() + "/" + MgtKey,
				CorpNum, PostData, UserID, "ISSUE", Response.class);
	}

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
			String MgtKey, String Memo) throws PopbillException {
		return cancelIssue(CorpNum, KeyType, MgtKey, Memo, null);

	}

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
			String MgtKey, String Memo, String UserID) throws PopbillException {
		if (KeyType == null)
			throw new PopbillException(-99999999, "관리번호형태가 입력되지 않았습니다.");
		if (MgtKey == null || MgtKey.isEmpty())
			throw new PopbillException(-99999999, "관리번호가 입력되지 않았습니다.");

		String PostData = toJsonString(new MemoRequest(Memo));

		return httppost("/Taxinvoice/" + KeyType.name() + "/" + MgtKey,
				CorpNum, PostData, UserID, "CANCELISSUE", Response.class);
	}

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
	public Response request(String CorpNum, MgtKeyType KeyType, String MgtKey,
			String Memo) throws PopbillException {
		return request(CorpNum, KeyType, MgtKey, Memo, null);

	}

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
	public Response request(String CorpNum, MgtKeyType KeyType, String MgtKey,
			String Memo, String UserID) throws PopbillException {
		if (KeyType == null)
			throw new PopbillException(-99999999, "관리번호형태가 입력되지 않았습니다.");
		if (MgtKey == null || MgtKey.isEmpty())
			throw new PopbillException(-99999999, "관리번호가 입력되지 않았습니다.");

		String PostData = toJsonString(new MemoRequest(Memo));

		return httppost("/Taxinvoice/" + KeyType.name() + "/" + MgtKey,
				CorpNum, PostData, UserID, "REQUEST", Response.class);
	}

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
	public Response refuse(String CorpNum, MgtKeyType KeyType, String MgtKey,
			String Memo) throws PopbillException {
		return refuse(CorpNum, KeyType, MgtKey, Memo, null);

	}

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
	public Response refuse(String CorpNum, MgtKeyType KeyType, String MgtKey,
			String Memo, String UserID) throws PopbillException {
		if (KeyType == null)
			throw new PopbillException(-99999999, "관리번호형태가 입력되지 않았습니다.");
		if (MgtKey == null || MgtKey.isEmpty())
			throw new PopbillException(-99999999, "관리번호가 입력되지 않았습니다.");

		String PostData = toJsonString(new MemoRequest(Memo));

		return httppost("/Taxinvoice/" + KeyType.name() + "/" + MgtKey,
				CorpNum, PostData, UserID, "REFUSE", Response.class);
	}

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
			String MgtKey, String Memo) throws PopbillException {
		return cancelRequest(CorpNum, KeyType, MgtKey, Memo, null);

	}

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
			String MgtKey, String Memo, String UserID) throws PopbillException {
		if (KeyType == null)
			throw new PopbillException(-99999999, "관리번호형태가 입력되지 않았습니다.");
		if (MgtKey == null || MgtKey.isEmpty())
			throw new PopbillException(-99999999, "관리번호가 입력되지 않았습니다.");

		String PostData = toJsonString(new MemoRequest(Memo));

		return httppost("/Taxinvoice/" + KeyType.name() + "/" + MgtKey,
				CorpNum, PostData, UserID, "CANCELREQUEST", Response.class);
	}

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
	public Response sendToNTS(String CorpNum, MgtKeyType KeyType, String MgtKey)
			throws PopbillException {
		return sendToNTS(CorpNum, KeyType, MgtKey, null);
	}

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
			String MgtKey, String UserID) throws PopbillException {
		if (KeyType == null)
			throw new PopbillException(-99999999, "관리번호형태가 입력되지 않았습니다.");
		if (MgtKey == null || MgtKey.isEmpty())
			throw new PopbillException(-99999999, "관리번호가 입력되지 않았습니다.");

		return httppost("/Taxinvoice/" + KeyType.name() + "/" + MgtKey,
				CorpNum, null, UserID, "NTS", Response.class);
	}

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
			String MgtKey, String Receiver) throws PopbillException {
		return sendEmail(CorpNum, KeyType, MgtKey, Receiver, null);
	}

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
			throws PopbillException {
		if (KeyType == null)
			throw new PopbillException(-99999999, "관리번호형태가 입력되지 않았습니다.");
		if (MgtKey == null || MgtKey.isEmpty())
			throw new PopbillException(-99999999, "관리번호가 입력되지 않았습니다.");

		ResendRequest request = new ResendRequest();

		request.receiver = Receiver;

		String PostData = toJsonString(request);

		return httppost("/Taxinvoice/" + KeyType.name() + "/" + MgtKey,
				CorpNum, PostData, UserID, "EMAIL", Response.class);
	}

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
	public Response sendSMS(String CorpNum, MgtKeyType KeyType, String MgtKey,
			String Sender, String Receiver, String Contents)
			throws PopbillException {
		return sendSMS(CorpNum, KeyType, MgtKey, Sender, Receiver, Contents,
				null);
	}

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
	public Response sendSMS(String CorpNum, MgtKeyType KeyType, String MgtKey,
			String Sender, String Receiver, String Contents, String UserID)
			throws PopbillException {
		if (KeyType == null)
			throw new PopbillException(-99999999, "관리번호형태가 입력되지 않았습니다.");
		if (MgtKey == null || MgtKey.isEmpty())
			throw new PopbillException(-99999999, "관리번호가 입력되지 않았습니다.");

		ResendRequest request = new ResendRequest();

		request.sender = Sender;
		request.receiver = Receiver;
		request.contents = Contents;

		String PostData = toJsonString(request);

		return httppost("/Taxinvoice/" + KeyType.name() + "/" + MgtKey,
				CorpNum, PostData, UserID, "SMS", Response.class);
	}

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
	public Response sendFAX(String CorpNum, MgtKeyType KeyType, String MgtKey,
			String Sender, String Receiver) throws PopbillException {
		return sendFAX(CorpNum, KeyType, MgtKey, Sender, Receiver, null);
	}

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
	public Response sendFAX(String CorpNum, MgtKeyType KeyType, String MgtKey,
			String Sender, String Receiver, String UserID)
			throws PopbillException {
		if (KeyType == null)
			throw new PopbillException(-99999999, "관리번호형태가 입력되지 않았습니다.");
		if (MgtKey == null || MgtKey.isEmpty())
			throw new PopbillException(-99999999, "관리번호가 입력되지 않았습니다.");

		ResendRequest request = new ResendRequest();

		request.sender = Sender;
		request.receiver = Receiver;

		String PostData = toJsonString(request);

		return httppost("/Taxinvoice/" + KeyType.name() + "/" + MgtKey,
				CorpNum, PostData, UserID, "FAX", Response.class);
	}

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
	public Taxinvoice getDetailInfo(String CorpNum, MgtKeyType KeyType,
			String MgtKey) throws PopbillException {
		if (KeyType == null)
			throw new PopbillException(-99999999, "관리번호형태가 입력되지 않았습니다.");
		if (MgtKey == null || MgtKey.isEmpty())
			throw new PopbillException(-99999999, "관리번호가 입력되지 않았습니다.");

		return httpget("/Taxinvoice/" + KeyType.name() + "/" + MgtKey
				+ "?Detail", CorpNum, null, Taxinvoice.class);
	}

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
			String MgtKey) throws PopbillException {
		if (KeyType == null)
			throw new PopbillException(-99999999, "관리번호형태가 입력되지 않았습니다.");
		if (MgtKey == null || MgtKey.isEmpty())
			throw new PopbillException(-99999999, "관리번호가 입력되지 않았습니다.");

		return httpget("/Taxinvoice/" + KeyType.name() + "/" + MgtKey, CorpNum,
				null, TaxinvoiceInfo.class);
	}

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
	public TaxinvoiceInfo[] getInfos(String CorpNum, MgtKeyType KeyType,
			String[] MgtKeyList) throws PopbillException {
		if (KeyType == null)
			throw new PopbillException(-99999999, "관리번호형태가 입력되지 않았습니다.");
		if (MgtKeyList == null || MgtKeyList.length == 0)
			throw new PopbillException(-99999999, "관리번호 목록이 입력되지 않았습니다.");

		String PostData = toJsonString(MgtKeyList);

		return httppost("/Taxinvoice/" + KeyType.name(), CorpNum, PostData,
				null, TaxinvoiceInfo[].class);
	}

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
			String MgtKey) throws PopbillException {
		if (KeyType == null)
			throw new PopbillException(-99999999, "관리번호형태가 입력되지 않았습니다.");
		if (MgtKey == null || MgtKey.isEmpty())
			throw new PopbillException(-99999999, "관리번호가 입력되지 않았습니다.");

		return httpget(
				"/Taxinvoice/" + KeyType.name() + "/" + MgtKey + "/Logs",
				CorpNum, null, TaxinvoiceLog[].class);
	}

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
			String MgtKey, String UserID) throws PopbillException {
		if (KeyType == null)
			throw new PopbillException(-99999999, "관리번호형태가 입력되지 않았습니다.");
		if (MgtKey == null || MgtKey.isEmpty())
			throw new PopbillException(-99999999, "관리번호가 입력되지 않았습니다.");

		URLResponse response = httpget("/Taxinvoice/" + KeyType.name() + "/"
				+ MgtKey + "?TG=POPUP", CorpNum, UserID, URLResponse.class);

		return response.url;
	}

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
	public String getMailURL(String CorpNum, MgtKeyType KeyType, String MgtKey,
			String UserID) throws PopbillException {
		if (KeyType == null)
			throw new PopbillException(-99999999, "관리번호형태가 입력되지 않았습니다.");
		if (MgtKey == null || MgtKey.isEmpty())
			throw new PopbillException(-99999999, "관리번호가 입력되지 않았습니다.");

		URLResponse response = httpget("/Taxinvoice/" + KeyType.name() + "/"
				+ MgtKey + "?TG=MAIL", CorpNum, UserID, URLResponse.class);

		return response.url;
	}

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
			String MgtKey, String UserID) throws PopbillException {
		if (KeyType == null)
			throw new PopbillException(-99999999, "관리번호형태가 입력되지 않았습니다.");
		if (MgtKey == null || MgtKey.isEmpty())
			throw new PopbillException(-99999999, "관리번호가 입력되지 않았습니다.");

		URLResponse response = httpget("/Taxinvoice/" + KeyType.name() + "/"
				+ MgtKey + "?TG=PRINT", CorpNum, UserID, URLResponse.class);

		return response.url;
	}

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
			String MgtKey, String UserID) throws PopbillException {
		if (KeyType == null)
			throw new PopbillException(-99999999, "관리번호형태가 입력되지 않았습니다.");
		if (MgtKey == null || MgtKey.isEmpty())
			throw new PopbillException(-99999999, "관리번호가 입력되지 않았습니다.");

		URLResponse response = httpget("/Taxinvoice/" + KeyType.name() + "/"
				+ MgtKey + "?TG=EPRINT", CorpNum, UserID, URLResponse.class);

		return response.url;
	}

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
			String[] MgtKeyList, String UserID) throws PopbillException {
		if (KeyType == null)
			throw new PopbillException(-99999999, "관리번호형태가 입력되지 않았습니다.");
		if (MgtKeyList == null || MgtKeyList.length == 0)
			throw new PopbillException(-99999999, "관리번호 목록이 입력되지 않았습니다.");

		String PostData = toJsonString(MgtKeyList);

		URLResponse response = httppost("/Taxinvoice/" + KeyType.name()
				+ "?Print", CorpNum, PostData, UserID, URLResponse.class);

		return response.url;
	}

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
			throws PopbillException {
		return attachFile(CorpNum, KeyType, MgtKey, DisplayName, FileData, null);
	}

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
			String UserID) throws PopbillException {
		if (KeyType == null)
			throw new PopbillException(-99999999, "관리번호형태가 입력되지 않았습니다.");
		if (MgtKey == null || MgtKey.isEmpty())
			throw new PopbillException(-99999999, "관리번호가 입력되지 않았습니다.");
		if (DisplayName == null || DisplayName.isEmpty())
			throw new PopbillException(-99999999, "파일 표시명이 입력되지 않았습니다.");
		if (FileData == null)
			throw new PopbillException(-99999999, "파일 스트림이 입력되지 않았습니다.");

		List<UploadFile> files = new ArrayList<BaseService.UploadFile>();
		UploadFile file = new UploadFile();
		file.fileName = DisplayName;
		file.fieldName = "Filedata";
		file.fileData = FileData;

		files.add(file);

		return httppostFiles("/Taxinvoice/" + KeyType.name() + "/" + MgtKey
				+ "/Files", CorpNum, null, files, UserID, Response.class);

	}

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
			String MgtKey) throws PopbillException {
		if (KeyType == null)
			throw new PopbillException(-99999999, "관리번호형태가 입력되지 않았습니다.");
		if (MgtKey == null || MgtKey.isEmpty())
			throw new PopbillException(-99999999, "관리번호가 입력되지 않았습니다.");

		return httpget("/Taxinvoice/" + KeyType.name() + "/" + MgtKey
				+ "/Files", CorpNum, null, AttachedFile[].class);
	}

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
			String MgtKey, String FileID) throws PopbillException {
		return deleteFile(CorpNum, KeyType, MgtKey, FileID, null);
	}

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
			throws PopbillException {
		if (KeyType == null)
			throw new PopbillException(-99999999, "관리번호형태가 입력되지 않았습니다.");
		if (MgtKey == null || MgtKey.isEmpty())
			throw new PopbillException(-99999999, "관리번호가 입력되지 않았습니다.");
		if (FileID == null || FileID.isEmpty())
			throw new PopbillException(-99999999, "파일아이디가 입력되지 않았습니다.");

		return httppost("/Taxinvoice/" + KeyType.name() + "/" + MgtKey
				+ "/Files/" + FileID, CorpNum, null, UserID, "DELETE",
				Response.class);
	}

	protected class UnitCostResponse {
		public float unitCost;
	}

	protected class CertResponse {
		public String certificateExpiration;
	}

	protected class MemoRequest {
		public MemoRequest(String memo) {
			this.memo = memo;
		}

		public String memo;
	}

	protected class IssueRequest {
		public String memo;
		public String emailSubject;
		public boolean forceIssue = false;
	}

	protected class ResendRequest {
		public String receiver;
		public String sender = null;
		public String contents = null;
	}

}

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

import java.util.Date;

import com.popbill.api.hometax.HTTaxinvoice;
import com.popbill.api.hometax.HTTaxinvoiceJobState;
import com.popbill.api.hometax.HTTaxinvoiceSearchResult;
import com.popbill.api.hometax.HTTaxinvoiceSummary;
import com.popbill.api.hometax.HTTaxinvoiceXMLResponse;
import com.popbill.api.hometax.QueryType;

/**
 * Hometax Taxinvoice Service Interface.
 * 
 * @author JeongYoHan
 * @version 1.0.0
 */

public interface HTTaxinvoiceService extends BaseService{
	
	/**
	 * 정액제 신청 URL
	 * @param CorpNum
	 * 			연동회원 사업자번호 
	 * @param UserID
	 * 			연동회원 아이디 
	 * @return 정액제 신청 url
	 * @throws PopbillException
	 */
	public String getFlatRatePopUpURL(String CorpNum, String UserID)
			throws PopbillException;
	
	/**
	 * 과금정보 확인 
	 * 
	 * @param CorpNum
	 * 			연동회원 사업자번호 
	 * @return 과금정보 (see. com.popbill.api.ChargeInfo)
	 * @throws PopbillException
	 */
	public ChargeInfo getChargeInfo(String CorpNum) throws PopbillException;
	
	
	/**
	 * 홈택스 전자(세금)계산서 수집 요청 
	 * 
	 * @param CorpNum
	 * 			연동회원 사업자번호 
	 * @param queryType
	 * 			수집유형, 매출, 매입, 수탁 중 반환 
	 * @return 작업아이디(jobID)
	 * @throws PopbillException
	 */
	public String requestJob(String CorpNum, QueryType queryType, String DType, String SDate, String EDate) throws PopbillException;
	

	/**
	 * 홈택스 전자(세금)계산서 수집 요청
	 * 
	 * @param CorpNum
	 * 			연동회원 사업자번호 
	 * @param queryType
	 * 			수집유형, 매출,매입,수탁 중 반환
	 * @param UserID
	 * 			연동회원 아이디 
	 * @return 작업아이디(jobID)
	 * @throws PopbillException
	 */
	public String requestJob(String CorpNum, QueryType queryType, String DType, String SDate, String EDate, String UserID) throws PopbillException;
	
	/**
	 * 수집 상태 확인 
	 * 
	 * @param CorpNum
	 * 			연동회원 사업자번호 
	 * @param JobID
	 * 			수집요청시 반환받은 작업아이디 
	 * @return 수집 상태 (see. com.popbill.api.httaxinvoice.HTTaxinvoiceJobState)
	 * @throws PopbillException
	 */
	public HTTaxinvoiceJobState getJobState(String CorpNum, String JobID) throws PopbillException;
	
	/**
	 * 수집 상태 확인 
	 * 
	 * @param CorpNum
	 * 			연동회원 사업자번호 
	 * @param JobId
	 * 			수집요청시 반환받은 작업아이디 
	 * @param UserID
	 * 			연동회원 아이디 
	 * @return 수집 상태 (see. com.popbill.api.httaxinvoice.HTTaxinvoiceJobState)
	 * @throws PopbillException
	 */
	public HTTaxinvoiceJobState getJobState(String CorpNum, String JobID, String UserID) throws PopbillException;
	
	/**
	 * 수집 상태 목록 확인 
	 * 
	 * @param CorpNum
	 * 			연동회원 사업자번호 
	 * @return 수집상태 배열 (see. com.popbill.api.httaxinvoice.HTTaxinvoiceJobState)
	 * @throws PopbillException
	 */
	public HTTaxinvoiceJobState[] listActiveJob(String CorpNum) throws PopbillException;
	
	
	/**
	 * 수집 상태 확인 
	 * 
	 * @param CorpNum
	 * 			연동회원 사업자번호 
	 * @param UserID
	 * 			연동회원 아이디 
	 * @return 수집 상태 (see. com.popbill.api.httaxinvoice.HTTaxinvoiceJobState)
	 * @throws PopbillException
	 */
	public HTTaxinvoiceJobState[] listActiveJob(String CorpNum, String UserID) throws PopbillException;
	
	/**
	 * 수집 결과 조회 
	 * 
	 * @param CorpNum
	 * 			연동회원 사업자번호 
	 * @param JobID
	 * 			작업 아이디 
	 * @param Type
	 * 			문서형태 배열 
	 * @param TaxType
	 * 			과세형태 배열 
	 * @param PurposeType
	 * 			영수/청구 배열
	 * @param TaxRegIDYN
	 * 			종사업장 유무  
	 * @param TaxRegIDType
	 * 			종사업장번호 사업자 유형 
	 * @param TaxRegID
	 * 			종사업장번호, 다수 기재시 콤마(",")로 구분하여 구성
	 * @param Page
	 * 			페이지 번호 
	 * @param PerPage
	 * 			페이지당 검색개수 
	 * @param Order
	 * 			정렬방향 
	 * @return 수집 결과 (see. com.popbill.api.httaxinvoice.HTTaxinvoiceSearchResult)
	 */
	public HTTaxinvoiceSearchResult search(String CorpNum, String JobID, String[] Type, 
			String[] TaxType, String[] PurposeType, String TaxRegIDYN, String TaxRegIDType,
			String TaxRegID, Integer Page, Integer PerPage, String Order) throws PopbillException;
	
	/**
	 * 수집 결과 조회 
	 * 
	 * @param CorpNum
	 * 			연동회원 사업자번호 
	 * @param JobID
	 * 			작업 아이디 
	 * @param Type
	 * 			문서형태 배열 
	 * @param TaxType
	 * 			과세형태 배열 
	 * @param PurposeType
	 * 			영수/청구 배열
	 * @param TaxRegIDYN
	 * 			종사업장 유무  
	 * @param TaxRegIDType
	 * 			종사업장번호 사업자 유형 
	 * @param TaxRegID
	 * 			종사업장번호, 다수 기재시 콤마(",")로 구분하여 구성
	 * @param Page
	 * 			페이지 번호 
	 * @param PerPage
	 * 			페이지당 검색개수 
	 * @param Order
	 * 			정렬방향 
	 * @param UserID
	 * 			연동회원 아이디 
	 * @return 수집 결과 (see. com.popbill.api.httaxinvoice.HTTaxinvoiceSearchResult)
	 */
	public HTTaxinvoiceSearchResult search(String CorpNum, String JobID, String[] Type, 
			String[] TaxType, String[] PurposeType, String TaxRegIDYN, String TaxRegIDType,
			String TaxRegID, Integer Page, Integer PerPage, String Order, String UserID) throws PopbillException;	
	
	/**
	 * 수집 결과 요약정보 조회 
	 * 
	 * @param CorpNum
	 * 			연동회원 사업자번호 
	 * @param JobID
	 * 			수집요청시 반환받은 작업아이디 
	 * @param Type
	 * 			문서형태 배열 
	 * @param TaxType
	 * 			과세형태 배열 
	 * @param PurposeType
	 * 			영수/청구 배열 
	 * @param TaxRegIDYN
	 * 			종사업장 유무 
	 * @param TaxRegIDType
	 * 			종사업장번호 사업자 유형 
	 * @param TaxRegID
	 * 			종사업장번호, 다수 기재시 콤마(",")로 구분하여 구성
	 * @return 수집 결과 요약정보 (see. com.popbill.api.httaxinvoice.HTTaxinvoiceSearchSummary)
	 * @throws PopbillException
	 */
	public HTTaxinvoiceSummary summary(String CorpNum, String JobID, String[] Type, 
			String[] TaxType, String[] PurposeType, String TaxRegIDYN, String TaxRegIDType,
			String TaxRegID) throws PopbillException;
	
	/**
	 * 수집 결과 요약정보 조회 
	 * 
	 * @param CorpNum
	 * 			연동회원 사업자번호 
	 * @param JobID
	 * 			수집요청시 반환받은 작업아이디 
	 * @param Type
	 * 			문서형태 배열 
	 * @param TaxType
	 * 			과세형태 배열 
	 * @param PurposeType
	 * 			영수/청구 배열 
	 * @param TaxRegIDYN
	 * 			종사업장 유무 
	 * @param TaxRegIDType
	 * 			종사업장번호 사업자 유형 
	 * @param TaxRegID
	 * 			종사업장번호, 다수 기재시 콤마(",")로 구분하여 구성
	 * @param UserID
	 * 			연동회원 아이디 
	 * @return 수집 결과 요약정보 (see. com.popbill.api.httaxinvoice.HTTaxinvoiceSearchSummary)
	 * @throws PopbillException
	 */
	public HTTaxinvoiceSummary summary(String CorpNum, String JobID, String[] Type, 
			String[] TaxType, String[] PurposeType, String TaxRegIDYN, String TaxRegIDType,
			String TaxRegID, String UserID) throws PopbillException;
	/**
	 * 상세정보 확인 - JSON 
	 * 
	 * @param CorpNum
	 * 			연동회원 사업자번호 
	 * @param NTSConfirmNum
	 * 			국세청승인번호 
	 * @return  전자(세금)계산서 상세정보 (see. com.popbill.api.httaxinvoice.HTTaxinvoice)
	 * @throws PopbillException
	 */
	public HTTaxinvoice getTaxinvoice(String CorpNum, String NTSConfirmNum) throws PopbillException;
	
	/**
	 * 상세정보 확인 - JSON 
	 * 
	 * @param CorpNum
	 * 			연동회원 사업자번호 
	 * @param NTSConfirmNum
	 * 			국세청승인번호 
	 * @param UserID
	 * 			연동회원 아이디 
	 * @return  전자(세금)계산서 상세정보 (see. com.popbill.api.httaxinvoice.HTTaxinvoice)
	 * @throws PopbillException
	 */
	public HTTaxinvoice getTaxinvoice(String CorpNum, String NTSConfirmNum, String UserID) throws PopbillException;
	
	
	/**
	 * 상세정보 확인 - XML
	 * 
	 * @param CorpNum
	 * 			 연동회원 사업자번호 
	 * @param NTSConfirmNum
	 * 			국세청 승인번호 
	 * @return 전자(세금)계산서 XML 정보 (see. com.popbill.api.httaxinvoiceHTTaxinvoiceXMLResponse)
	 * @throws PopbillException
	 */
	public HTTaxinvoiceXMLResponse getXML(String CorpNum, String NTSConfirmNum) throws PopbillException;
	
	
	/**
	 * 상세정보 확인 - XML
	 * 
	 * @param CorpNum
	 * 			연동회원 사업자번호 
	 * @param NTSConfirmNum
	 * 			국세청승인번호 
	 * @param UserID
	 * 			연동회원 아이디 
	 * @return 전자(세금)계산서 XML 정보 (see. com.popbill.api.httaxinvoiceHTTaxinvoiceXMLResponse)
	 * @throws PopbillException
	 */
	public HTTaxinvoiceXMLResponse getXML(String CorpNum, String NTSConfirmNum, String UserID) throws PopbillException;
	
	/**
	 * 정액제 서비스 상태 확인 
	 * 
	 * @param CorpNum
	 * 			연동회원 사업자번호 
	 * @return 정액제 서비스 상태 (see. com.popbill.api.FlatRateState)
	 * @throws PopbillException
	 */
	public FlatRateState getFlatRateState(String CorpNum) throws PopbillException;
	
	/**
	 * 정액제 서비스 상태 확인 
	 * 
	 * @param CorpNum
	 * 			연동회원 사업자번호 
	 * @param UserID
	 * 			연동회원 아이디 
	 * @return 정액제 서비스 상태 (see. com.popbill.api.FlatRateState)
	 * @throws PopbillException
	 */
	public FlatRateState getFlatRateState(String CorpNum, String UserID) throws PopbillException;
	
	/**
	 * 홈택스연계 공인인증서 등록 URL 확인 
	 * 
	 * @param CorpNum
	 * 			연동회원 사업자번호 
	 * @param UserID
	 * 			연동회원 아이디 
	 * @return 홈택스연계 공인인증서 등록 URL
	 * @throws PopbillException
	 */
	public String getCertificatePopUpURL(String CorpNum, String UserID)
			throws PopbillException;
	
	/**
	 * 홈택스연계 공인인증서 만료일시 확인
	 * 
	 * @param CorpNum
	 * 			연동회원 사업자번호. 
	 * @return 만료일시 
	 * @throws PopbillException
	 */
	public Date getCertificateExpireDate(String CorpNum)
			throws PopbillException;
}

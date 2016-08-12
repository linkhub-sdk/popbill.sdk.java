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
package com.popbill.api.hometax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.popbill.api.BaseServiceImp;
import com.popbill.api.ChargeInfo;
import com.popbill.api.FlatRateState;
import com.popbill.api.HTTaxinvoiceService;
import com.popbill.api.PopbillException;

/**
 * Implementation of Popbill HomeTax Taxinvoice Service Interface
 * @author JeongYoHan
 * @version 1.0.0
 * @see com.popbill.api.HTTaxinvoiceService
 *
 */
public class HTTaxinvoiceServiceImp extends BaseServiceImp implements HTTaxinvoiceService {

	@Override
	protected List<String> getScopes() {
		return Arrays.asList("111");
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.popbill.api.HomeTaxTaxinvoiceService#getFlatRatePopUpURL(java.lang.String, java.lang.String)
	 */
	@Override
	public String getFlatRatePopUpURL(String CorpNum, String UserID) throws PopbillException {
		URLResponse response = httpget("/HomeTax/Taxinvoice?TG=CHRG", CorpNum, UserID, URLResponse.class);
		
		return response.url;
	}

	/*
	 * (non-Javadoc)
	 * @see com.popbill.api.HomeTaxTaxinvoiceService#getChargeInfo(java.lang.String)
	 */
	@Override
	public ChargeInfo getChargeInfo(String CorpNum) throws PopbillException {
		// TODO Auto-generated method stub
		return httpget("/HomeTax/Taxinvoice/ChargeInfo", CorpNum, null, ChargeInfo.class);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.popbill.api.HTTaxinvoiceService#requestJob(java.lang.String, com.popbill.api.httaxinvoice.QueryType, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public String requestJob(String CorpNum, QueryType queryType, String DType, String SDate, String EDate)
			throws PopbillException {
		return requestJob(CorpNum, queryType, DType, SDate, EDate, null);
	}

	/*
	 * (non-Javadoc)
	 * @see com.popbill.api.HTTaxinvoiceService#requestJob(java.lang.String, com.popbill.api.httaxinvoice.QueryType, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public String requestJob(String CorpNum, QueryType queryType, String DType, String SDate, String EDate, String UserID)
			throws PopbillException {
		
		if (DType == null || DType.isEmpty())
			throw new PopbillException(-99999999, "검색일자 유형이 입력되지 않았습니다.");
		if (SDate == null || SDate.isEmpty())
			throw new PopbillException(-99999999, "시작일자가 입력되지 않았습니다.");
		if (EDate == null || EDate.isEmpty())
			throw new PopbillException(-99999999, "종료일자가 입력되지 않았습니다.");
		
		JobIDResponse response = httppost("/HomeTax/Taxinvoice/"+queryType.name() 
				+"?DType=" + DType + "&SDate=" + SDate +"&EDate=" + EDate ,CorpNum, null, UserID, JobIDResponse.class);
		
		return response.jobID;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.popbill.api.HTTaxinvoiceService#getJobState(java.lang.String, java.lang.String)
	 */
	@Override
	public HTTaxinvoiceJobState getJobState(String CorpNum, String JobID)
			throws PopbillException {
		return  getJobState(CorpNum, JobID, null);
	}

	/*
	 * (non-Javadoc)
	 * @see com.popbill.api.HTTaxinvoiceService#getJobState(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public HTTaxinvoiceJobState getJobState(String CorpNum, String JobID,
			String UserID) throws PopbillException {
		if (JobID.length() != 18)
			throw new PopbillException(-99999999, "작업아이디가 올바르지 않았습니다.");
		return httpget("/HomeTax/Taxinvoice/" + JobID + "/State",CorpNum, UserID, HTTaxinvoiceJobState.class);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.popbill.api.HTTaxinvoiceService#ListActiveJob(java.lang.String)
	 */
	@Override
	public HTTaxinvoiceJobState[] listActiveJob(String CorpNum)
			throws PopbillException {
		return listActiveJob(CorpNum, null);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.popbill.api.HTTaxinvoiceService#ListActiveJob(java.lang.String, java.lang.String)
	 */
	@Override
	public HTTaxinvoiceJobState[] listActiveJob(String CorpNum, String UserID)
			throws PopbillException {
		return httpget("/HomeTax/Taxinvoice/JobList", CorpNum, UserID, HTTaxinvoiceJobState[].class);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.popbill.api.HTTaxinvoiceService#Search(java.lang.String, java.lang.String, java.lang.String[], java.lang.String[], java.lang.String[], java.lang.Boolean, java.lang.String, java.lang.String[], java.lang.Integer, java.lang.Integer, java.lang.String)
	 */
	@Override
	public HTTaxinvoiceSearchResult search(String CorpNum, String JobID,
			String[] Type, String[] TaxType, String[] PurposeType,
			String TaxRegIDYN, String TaxRegIDType, String TaxRegID,
			Integer Page, Integer PerPage, String Order) throws PopbillException{

		return search(CorpNum, JobID, Type, TaxType, PurposeType, TaxRegIDYN, 
				TaxRegIDType, TaxRegID, Page, PerPage, Order, null);
	}

	/*
	 * (non-Javadoc)
	 * @see com.popbill.api.HTTaxinvoiceService#Search(java.lang.String, java.lang.String, java.lang.String[], java.lang.String[], java.lang.String[], java.lang.Boolean, java.lang.String, java.lang.String[], java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.String)
	 */
	@Override
	public HTTaxinvoiceSearchResult search(String CorpNum, String JobID,
			String[] Type, String[] TaxType, String[] PurposeType,
			String TaxRegIDYN, String TaxRegIDType, String TaxRegID,
			Integer Page, Integer PerPage, String Order, String UserID) throws PopbillException {
		
		if (JobID.length() != 18)
			throw new PopbillException(-99999999, "작업아이디가 올바르지 않습니다.");
		
		String uri = "/HomeTax/Taxinvoice/"+JobID;
		
		uri += "?Type=" + Arrays.toString(Type)
				.replaceAll("\\[|\\]|\\s", "");
		uri += "&TaxType=" + Arrays.toString(TaxType)
				.replaceAll("\\[|\\]|\\s", "");
		uri += "&PurposeType=" + Arrays.toString(PurposeType)
				.replaceAll("\\[|\\]|\\s", "");
		
		if ( TaxRegIDType != "" && TaxRegIDType != null)
			uri += "&TaxRegIDType=" + TaxRegIDType;
		
		if ( TaxRegIDYN != "" && TaxRegIDYN != null)
			uri += "&TaxRegIDType=" + TaxRegIDYN;
		
		if ( TaxRegID != "" && TaxRegIDYN != null) 
			uri += "&TaxRegID=" + TaxRegID;
				
		uri += "&Page=" + Integer.toString(Page);
		uri += "&PerPage=" + Integer.toString(PerPage);
		uri += "&Order=" + Order;
		
		return httpget(uri, CorpNum, UserID, HTTaxinvoiceSearchResult.class);
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see com.popbill.api.HTTaxinvoiceService#Summary(java.lang.String, java.lang.String, java.lang.String[], java.lang.String[], java.lang.String[], java.lang.Boolean, java.lang.String, java.lang.String[])
	 */
	@Override
	public HTTaxinvoiceSummary summary(String CorpNum, String JobID,
			String[] Type, String[] TaxType, String[] PurposeType,
			String TaxRegIDYN, String TaxRegIDType, String TaxRegID)
			throws PopbillException {
		return summary(CorpNum, JobID, Type, TaxType, PurposeType, TaxRegIDYN, TaxRegIDType, TaxRegID, null);
	}

	
	/*
	 * (non-Javadoc)
	 * @see com.popbill.api.HTTaxinvoiceService#Summary(java.lang.String, java.lang.String, java.lang.String[], java.lang.String[], java.lang.String[], java.lang.Boolean, java.lang.String, java.lang.String[], java.lang.String)
	 */
	@Override
	public HTTaxinvoiceSummary summary(String CorpNum, String JobID,
			String[] Type, String[] TaxType, String[] PurposeType,
			String TaxRegIDYN, String TaxRegIDType, String TaxRegID,
			String UserID) throws PopbillException {
		
		if (JobID.length() != 18)
			throw new PopbillException(-99999999, "작업아이디가 올바르지 않았습니다.");
		
		String uri = "/HomeTax/Taxinvoice/" + JobID + "/Summary";
		
		uri += "?Type=" + Arrays.toString(Type)
				.replaceAll("\\[|\\]|\\s", "");
		uri += "&TaxType=" + Arrays.toString(TaxType)
				.replaceAll("\\[|\\]|\\s", "");
		uri += "&PurposeType=" + Arrays.toString(PurposeType)
				.replaceAll("\\[|\\]|\\s", "");
		
		if ( TaxRegIDType != "" && TaxRegIDType != null)
			uri += "&TaxRegIDType=" + TaxRegIDType;
		
		if ( TaxRegIDYN != "" && TaxRegIDYN != null)
			uri += "&TaxRegIDYN=" + TaxRegIDType;
		
		if ( TaxRegID != "" && TaxRegID != null)
			uri += "&TaxRegID=" + TaxRegID;
							
		return httpget(uri, CorpNum, UserID, HTTaxinvoiceSummary.class);
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see com.popbill.api.HTTaxinvoiceService#GetTaxinvoice(java.lang.String, java.lang.String)
	 */
	@Override
	public HTTaxinvoice getTaxinvoice(String CorpNum, String NTSConfirmNum)
			throws PopbillException {
		return getTaxinvoice(CorpNum, NTSConfirmNum, null);
	}

	
	/*
	 * (non-Javadoc)
	 * @see com.popbill.api.HTTaxinvoiceService#GetTaxinvoice(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public HTTaxinvoice getTaxinvoice(String CorpNum, String NTSConfirmNum,
			String UserID) throws PopbillException {
		
		if ( NTSConfirmNum.length() != 24 )
			throw new PopbillException(-99999999, "국세청승인번호가 올바르지 않았습니다.");
		
		return httpget("/HomeTax/Taxinvoice/" +NTSConfirmNum, CorpNum, UserID, HTTaxinvoice.class);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.popbill.api.HTTaxinvoiceService#GetXML(java.lang.String, java.lang.String)
	 */
	@Override
	public HTTaxinvoiceXMLResponse getXML(String CorpNum, String NTSConfirmNum)
			throws PopbillException {
		return getXML(CorpNum, NTSConfirmNum, null);
	}

	/*
	 * (non-Javadoc)
	 * @see com.popbill.api.HTTaxinvoiceService#GetXML(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public HTTaxinvoiceXMLResponse getXML(String CorpNum, String NTSConfirmNum,
			String UserID) throws PopbillException {
		if ( NTSConfirmNum.length() != 24 )
			throw new PopbillException(-99999999, "국세청승인번호가 올바르지 않았습니다.");
		return httpget("/HomeTax/Taxinvoice/" +NTSConfirmNum+"?T=xml", CorpNum, UserID, HTTaxinvoiceXMLResponse.class);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.popbill.api.HTTaxinvoiceService#GetFlatRateState(java.lang.String)
	 */
	@Override
	public FlatRateState getFlatRateState(String CorpNum)
			throws PopbillException {
		return getFlatRateState(CorpNum, null);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.popbill.api.HTTaxinvoiceService#GetFlatRateState(java.lang.String, java.lang.String)
	 */
	@Override
	public FlatRateState getFlatRateState(String CorpNum, String UserID)
			throws PopbillException {
		return httpget("/HomeTax/Taxinvoice/Contract", CorpNum, UserID, FlatRateState.class);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.popbill.api.HTTaxinvoiceService#getCertificatePopUpURl(java.lang.String, java.lang.String)
	 */
	@Override
	public String getCertificatePopUpURL(String CorpNum, String UserID)
			throws PopbillException {
		
		URLResponse response = httpget("/HomeTax/Taxinvoice?TG=CERT", CorpNum, UserID, URLResponse.class);
		
		return response.url;
	}

	/*
	 * (non-Javadoc)
	 * @see com.popbill.api.HTTaxinvoiceService#getCertificateExpireDate(java.lang.String)
	 */
	@Override
	public Date getCertificateExpireDate(String CorpNum)
			throws PopbillException {
		
		CertResponse response = httpget("/HomeTax/Taxinvoice/CertInfo", CorpNum, null,
				CertResponse.class);
		
		try {
			return new SimpleDateFormat("yyyyMMddHHmmss")
					.parse(response.certificateExpiration);
		} catch (ParseException e) {
			throw new PopbillException(-99999999, "날자형식 포맷변환 실패["
					+ response.certificateExpiration + "]", e);
		}
		
	}
	
		
	protected class JobIDResponse {
		public String jobID;
	}
	
	protected class CertResponse {
		public String certificateExpiration;
	}

}

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

import java.io.Serializable;
import java.util.List;

/**
 * Class for HomeTax Taxinvoice Deatil Information
 * 
 * @author JeongYoHan
 * @version 1.0.0
 */
public class HTTaxinvoice implements Serializable {
	private static final long serialVersionUID = 2090626401571944998L;
	
	private String writeDate;
	private String issueDT;
	private Short invoiceType;
	private String taxType;
	private String invoicerCorpNum;
	private String invoicerMgtKey;
	private String invoicerTaxRegID;
	private String invoicerCorpName;
	private String invoicerCEOName;
	private String invoicerAddr;
	private String invoicerBizClass;
	private String invoicerBizType;
	private String invoicerContactName;
	private String invoicerDeptName;
	private String invoicerTEL;
	private String invoicerEmail;
	
	private String invoiceeCorpNum;
	private String invoiceeType;
	private String invoiceeMgtKey;
	private String invoiceeTaxRegID;
	private String invoiceeCorpName;
	private String invoiceeCEOName;
	private String invoiceeAddr;
	private String invoiceeBizType;
	private String invoiceeBizClass;
	private String invoiceeContactName1;
	private String invoiceeDeptName1;
	private String invoiceeTEL1;
	private String invoiceeEmail1;
	private String invoiceeContactName2;
	private String invoiceeDeptName2;
	private String invoiceeTEL2;
	private String invoiceeEmail2;
	
	private String trusteeCorpNum;
	private String trusteeMgtKey;
	private String trusteeTaxRegID;
	private String trusteeCorpName;
	private String trusteeCEOName;
	private String trusteeAddr;
	private String trusteeBizType;
	private String trusteeBizClass;
	private String trusteeContactName;
	private String trusteeDeptName;
	private String trusteeTEL;
	private String trusteeEmail;
	
	private String taxTotal;
	private String supplyCostTotal;
	private String totalAmount;
	private Short modifyCode;
	private String orgNTSConfirmNum;
	private String purposeType;
	private String serialNum;
	private String cash;
	private String chkBill;
	private String credit;
	private String note;
	private String remark1;
	private String remark2;
	private String remark3;
	private String ntsconfirmNum;
	
	private List<HTTaxinvoiceDetail> detailList;
	
	/**
	 * 작성일자 확인 
	 * 
	 * @return 작성일자 
	 */
	public String getWriteDate() {
		return writeDate;
	}
	
	/**
	 * 발행일시 확인 
	 * 
	 * @return 발행일시 
	 */
	public String getIssueDT() {
		return issueDT;
	}

	/**
	 * 전자(세금)계산서 종류 확인 
	 * 
	 * @return 전자(세금)계산서 종류코드 
	 */
	public Short getInvoiceType() {
		return invoiceType;
	}

	/**
	 * 과세형태 확인 
	 * 
	 * @return 과세형태 
	 */
	public String getTaxType() {
		return taxType;
	}

	/**
	 * 공급자 사업자번호 확인
	 * 
	 * @return 공급자 사업자번호 
	 */
	public String getInvoicerCorpNum() {
		return invoicerCorpNum;
	}

	/**
	 * 공급자 문서관리번호 확인  
	 * 
	 * @return 공급자 문서관리번호 
	 */
	public String getInvoicerMgtKey() {
		return invoicerMgtKey;
	}

	/**
	 * 공급자 종사업장번호 확인 
	 * 
	 * @return 공급자 종사업장번호 
	 */
	public String getInvoicerTaxRegID() {
		return invoicerTaxRegID;
	}
	
	/**
	 * 공급자 상호 확인 
	 * 
	 * @return 공급자 상호 
	 */
	public String getInvoicerCorpName() {
		return invoicerCorpName;
	}

	/**
	 * 공급자 대표자 성명 확인 
	 * 
	 * @return 공급자 대표자 성명
	 */
	public String getInvoicerCEOName() {
		return invoicerCEOName;
	}

	/**
	 * 공급자 주소 확인  
	 * 
	 * @return 공급자 주소 
	 */
	public String getInvoicerAddr() {
		return invoicerAddr;
	}

	/** 
	 * 공급자 종목 확인 
	 * 
	 * @return 공급자 종목 
	 */
	public String getInvoicerBizClass() {
		return invoicerBizClass;
	}

	/**
	 * 공급자 업태 확인 
	 * 
	 * @return 공급자 업태
	 */
	public String getInvoicerBizType() {
		return invoicerBizType;
	}

	/**
	 * 공급자 담당자 성명 확인 
	 * 
	 * @return 공급자 담당자 성명 
	 */
	public String getInvoicerContactName() {
		return invoicerContactName;
	}

	/**
	 * 공급자 담당자 부서명 확인 
	 * 
	 * @return 공급자 담당자 부서명 
	 */
	public String getInvoicerDeptName() {
		return invoicerDeptName;
	}

	/**
	 * 공급자 담당자 연락처 확인
	 * 
	 * @return 공급자 담당자 연락처 
	 */
	public String getInvoicerTEL() {
		return invoicerTEL;
	}

	/**
	 * 공급자 담당자 이메일 
	 * 
	 * @return 공급자 담당자 이메일 
	 */
	public String getInvoicerEmail() {
		return invoicerEmail;
	}

	
	/**
	 * 공급받는자 사업자번호 확인 
	 * 
	 * @return 공급받는자 사업자번호 
	 */
	public String getInvoiceeCorpNum() {
		return invoiceeCorpNum;
	}

	/**
	 * 공급받는자 구분 확인 
	 * 
	 * @return 공급받는자 구분 
	 */
	public String getInvoiceeType() {
		return invoiceeType;
	}

	/**
	 * 공급받는자 문서 관리번호 확인 
	 * 
	 * @return 공급받는자 문서 관리번호 
	 */
	public String getInvoiceeMgtKey() {
		return invoiceeMgtKey;
	}
	
	/**
	 * 공급받는자 종사업장번호 확인 
	 * 
	 * @return 공급받는자 종사업장번호 
	 */
	public String getInvoiceeTaxRegID() {
		return invoiceeTaxRegID;
	}

	/**
	 * 공급받는자 상호 확인 
	 * 
	 * @return 공급받는자 상호 
	 */
	public String getInvoiceeCorpName() {
		return invoiceeCorpName;
	}

	/**
	 * 공급받는자 대표자 성명 확인
	 * 
	 * @return 공급받는자 대표자 성명 
	 */
	public String getInvoiceeCEOName() {
		return invoiceeCEOName;
	}
	
	/**
	 * 공급받는자 주소 확인 
	 * 
	 * @return 공급받는자 주소 
	 */
	public String getInvoiceeAddr() {
		return invoiceeAddr;
	}

	/**
	 * 공급받는자 업태 확인 
	 * 
	 * @return 공급받는자 업태 
	 */
	public String getInvoiceeBizType() {
		return invoiceeBizType;
	}

	/** 
	 * 공급받는자 종목 확인 
	 * 
	 * @return 공급받는자 종목 
	 */
	public String getInvoiceeBizClass() {
		return invoiceeBizClass;
	}
	
	/**
	 * 공급받는자 주)담당자 성명 확인
	 *  
	 * @return 공급받는자 주)담당자 성명 
	 */
	public String getInvoiceeContactName1() {
		return invoiceeContactName1;
	}

	/**
	 * 공급받는자 주)담당자 부서 확인
	 * 
	 * @return 공급받는자 주)담당자 부서 
	 */
	public String getInvoiceeDeptName1() {
		return invoiceeDeptName1;
	}

	/**
	 * 공급받는자 주)담당자 연락처 확인 
	 * 
	 * @return 공급받는자 주)담당자 연락처 
	 */
	public String getInvoiceeTEL1() {
		return invoiceeTEL1;
	}

	/**
	 * 공급받는자 주)담당자 이메일 확인 
	 * 
	 * @return 공급받는자 주)담당자 이메일 
	 */
	public String getInvoiceeEmail1() {
		return invoiceeEmail1;
	}
	
	/**
	 * 공급받는자 부)담당자 성명 확인 
	 * 
	 * @return 공급받는자 부)담당자 성명 
	 */
	public String getInvoiceeContactName2() {
		return invoiceeContactName2;
	}

	/**
	 * 공급받는자 부)담당자 부서명 확인
	 * 
	 * @return 공급받는자 부)담당자 부서명 
	 */
	public String getInvoiceeDeptName2() {
		return invoiceeDeptName2;
	}

	/**
	 * 공급받는자 부)담당자 연락처 확인 
	 * 
	 * @return 공급받는자 부)담당자 연락처 
	 */
	public String getInvoiceeTEL2() {
		return invoiceeTEL2;
	}
	
	/**
	 * 공급받는자 부)담당자 이메일 확인 
	 * 
	 * @return 공급받는자 부)담당자 이메일 
	 */
	public String getInvoiceeEmail2() {
		return invoiceeEmail2;
	}

	/**
	 * 수탁자 사업자번호 확인 
	 * 
	 * @return 수탁자 사업자번호 
	 */
	public String getTrusteeCorpNum() {
		return trusteeCorpNum;
	}

	/**
	 * 수탁자 문서관리번호 확인 
	 * 
	 * @return 수탁자 문서관리번호 
	 */
	public String getTrusteeMgtKey() {
		return trusteeMgtKey;
	}

	/**
	 * 수탁자 종사업장번호 확인
	 * 
	 * @return 수탁자 종사업장번호 
	 */
	public String getTrusteeTaxRegID() {
		return trusteeTaxRegID;
	}

	/**
	 * 수탁자 상호 확인
	 * 
	 * @return 수탁자 상호 
	 */
	public String getTrusteeCorpName() {
		return trusteeCorpName;
	}

	/** 
	 * 수탁자 대표자 성명 확인
	 * 
	 * @return 수탁자 대표자 성명 
	 */
	public String getTrusteeCEOName() {
		return trusteeCEOName;
	}

	/**
	 * 수탁자 주소 확인 
	 * 
	 * @return 수탁자 주소 
	 */
	public String getTrusteeAddr() {
		return trusteeAddr;
	}

	/**
	 * 수탁자 업태 확인 
	 * 
	 * @return 수탁자 업태 
	 */
	public String getTrusteeBizType() {
		return trusteeBizType;
	}

	/**
	 * 수탁자 종목 확인
	 * 
	 * @return 수탁자 종목 
	 */
	public String getTrusteeBizClass() {
		return trusteeBizClass;
	}

	/** 
	 * 수탁자 담당자 성명 확인 
	 * 
	 * @return 수탁자 담당자 성명 
	 */
	public String getTrusteeContactName() {
		return trusteeContactName;
	}

	/**
	 * 수탁자 담당자 부서명 확인 
	 * 
	 * @return 수탁자 담당자 부서명 
	 */
	public String getTrusteeDeptName() {
		return trusteeDeptName;
	}

	/**
	 * 수탁자 담당자 연락처 확인 
	 * 
	 * @return 수탁자 담당자 연락처 
	 */
	public String getTrusteeTEL() {
		return trusteeTEL;
	}
	
	/** 
	 * 수탁자 담당자 이메일 확인 
	 * 
	 * @return 수탁자 담당자 이메일 
	 */
	public String getTrusteeEmail() {
		return trusteeEmail;
	}

	/**
	 * 세액 합계 확인 
	 * 
	 * @return 세액 합계 
	 */
	public String getTaxTotal() {
		return taxTotal;
	}

	/**
	 * 공급가액 합계 확인 
	 * 
	 * @return 공급가액 합계 
	 */
	public String getSupplyCostTotal() {
		return supplyCostTotal;
	}

	/**
	 * 합계금액 확인 
	 * 
	 * @return 합계금액 
	 */
	public String getTotalAmount() {
		return totalAmount;
	}

	/**
	 * 수정사유코드 확인 
	 * 
	 * @return 수정사유코드 
	 */
	public Short getModifyCode() {
		return modifyCode;
	}

	/** 
	 * 원본 국세청 승인번호 확인
	 *  
	 * @return 원본 국세청 승인번호 
	 */
	public String getOrgNTSConfirmNum() {
		return orgNTSConfirmNum;
	}

	/**
	 * 영수/청구 확인
	 * 
	 * @return 영수/청구 중 반환 
	 */
	public String getPurposeType() {
		return purposeType;
	}

	/**
	 * 기재상 일련번호 확인
	 * 
	 * @return 기재상 일련번호 
	 */
	public String getSerialNum() {
		return serialNum;
	}

	/**
	 * 기재상 현금 확인
	 * 
	 * @return 기재상 현금 
	 */
	public String getCash() {
		return cash;
	}

	/** 
	 * 기재상 수표 확인 
	 * 
	 * @return 기재상 수표 
	 */
	public String getChkBill() {
		return chkBill;
	}

	/**
	 * 기재상 외상 확인
	 * 
	 * @return 기재상 외상 
	 */
	public String getCredit() {
		return credit;
	}
	
	/**
	 * 기재상 어음 확인
	 * 
	 * @return 기재상 어음 
	 */
	public String getNote() {
		return note;
	}

	/**
	 * 비고1 확인 
	 * 
	 * @return 비고1
	 */
	public String getRemark1() {
		return remark1;
	}

	/**
	 * 비고2 확인
	 * 
	 * @return 비고2
	 */
	public String getRemark2() {
		return remark2;
	}

	/**
	 * 비고3 확인
	 * 
	 * @return 비고3
	 */
	public String getRemark3() {
		return remark3;
	}

	/**
	 * 국세청승인번호 확인 
	 * 
	 * @return 국세청승인번호 
	 */
	public String getNtsconfirmNum() {
		return ntsconfirmNum;
	}

	/**
	 * 상세항목 정보 확인 
	 * 
	 * @return 상세항목 정보 (see com.popbill.api.httaxinvoice.HTTaxinvoiceDetail)
	 */
	public List<HTTaxinvoiceDetail> getDetailList() {
		return detailList;
	}
}

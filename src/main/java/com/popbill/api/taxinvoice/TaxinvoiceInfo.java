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
package com.popbill.api.taxinvoice;

/**
 * Class for Taxinvoice' outline information.
 * 
 * @author KimSeongjun
 * @version 1.0.0
 */
public class TaxinvoiceInfo {
	private Integer closeDownState;
	private String closeDownStateDate;

	private String itemKey;
	private String taxType;
	private String writeDate;
	private String regDT;

	private String invoicerCorpName;
	private String invoicerCorpNum;
	private String invoicerMgtKey;
	private boolean invoicerPrintYN;
	private String invoiceeCorpName;
	private String invoiceeCorpNum;
	private String invoiceeMgtKey;
	private boolean invoiceePrintYN;
	private String trusteeCorpName;
	private String trusteeCorpNum;
	private String trusteeMgtKey;
	private boolean trusteePrintYN;

	private String supplyCostTotal;
	private String taxTotal;
	private String purposeType;
	private Integer modifyCode;
	private String issueType;

	private String issueDT;
	private String preIssueDT;

	private Integer stateCode;
	private String stateDT;

	private boolean openYN;
	private String openDT;
	private String ntsresult;
	private String ntsconfirmNum;
	private String ntssendDT;
	private String ntsresultDT;
	private String ntssendErrCode;

	private String stateMemo;
	private String lateIssueYN;
	
	private boolean interOPYN;

	/**
	 * 팝빌번호 확인
	 * 
	 * @return 팝빌번호
	 */
	public String getItemKey() {
		return itemKey;
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
	 * 작성일자 확인
	 * 
	 * @return 작성일자
	 */
	public String getWriteDate() {
		return writeDate;
	}

	/**
	 * 등록일시 확인
	 * 
	 * @return 등록일시
	 */
	public String getRegDT() {
		return regDT;
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
	 * 공급자 사업자번호 확인
	 * 
	 * @return 공급자 사업자번호
	 */
	public String getInvoicerCorpNum() {
		return invoicerCorpNum;
	}

	/**
	 * 공급자 연동관리번호 확인
	 * 
	 * @return 공급자 연동관리번호
	 */
	public String getInvoicerMgtKey() {
		return invoicerMgtKey;
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
	 * 공급받는자 사업자번호 확인
	 * 
	 * @return 공급받는자 사업자번호
	 */
	public String getInvoiceeCorpNum() {
		return invoiceeCorpNum;
	}

	/**
	 * 공급받는자 연동관리번호 확인
	 * 
	 * @return 공급받는자 연동관리번호
	 */
	public String getInvoiceeMgtKey() {
		return invoiceeMgtKey;
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
	 * 수탁자 사업자번호 확인
	 * 
	 * @return 수탁자 사업자번호
	 */
	public String getTrusteeCorpNum() {
		return trusteeCorpNum;
	}

	/**
	 * 수탁자 연동관리번호 확인
	 * 
	 * @return 수탁자 연동관리번호
	 */
	public String getTrusteeMgtKey() {
		return trusteeMgtKey;
	}

	/**
	 * 공급가액 확인
	 * 
	 * @return 공급가액
	 */
	public String getSupplyCostTotal() {
		return supplyCostTotal;
	}

	/**
	 * 세액 확인
	 * 
	 * @return 세액
	 */
	public String getTaxTotal() {
		return taxTotal;
	}

	/**
	 * 영수/청구 여부 확인
	 * 
	 * @return 영수/청구 구분
	 */
	public String getPurposeType() {
		return purposeType;
	}

	/**
	 * 수정사유코드 확인
	 * 
	 * @return 수정사유코드
	 */
	public Integer getModifyCode() {
		return modifyCode;
	}

	/**
	 * 발행형태 확인
	 * 
	 * @return 발행형태
	 */
	public String getIssueType() {
		return issueType;
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
	 * 발행예정일시 확인
	 * 
	 * @return 발행예정일시
	 */
	public String getPreIssueDT() {
		return preIssueDT;
	}

	/**
	 * 상태코드 확인
	 * 
	 * @return 상태코드
	 */
	public Integer getStateCode() {
		return stateCode;
	}

	/**
	 * 상태변경 일시 확인
	 * 
	 * @return 상태변경일시
	 */
	public String getStateDT() {
		return stateDT;
	}

	/**
	 * 개봉여부 확인
	 * 
	 * @return 개봉여부
	 */
	public boolean isOpenYN() {
		return openYN;
	}

	/**
	 * 개봉일시 확인
	 * 
	 * @return 개봉일시
	 */
	public String getOpenDT() {
		return openDT;
	}

	/**
	 * 국세청 전송 결과 확인
	 * 
	 * @return 국세청 전송 결과
	 */
	public String getNTSResult() {
		return ntsresult;
	}

	/**
	 * 국세청 승인번호 확인
	 * 
	 * @return 국세청 승인번호
	 */
	public String getNTSConfirmNum() {
		return ntsconfirmNum;
	}

	/**
	 * 국세청 전송일시 확인
	 * 
	 * @return 국세청 전송일시 확인
	 */
	public String getNTSSendDT() {
		return ntssendDT;
	}

	/**
	 * 국세청 결과수신일시 확인
	 * 
	 * @return 국세청 결과 수신일시
	 */
	public String getNTSResultDT() {
		return ntsresultDT;
	}

	/**
	 * 국세청 반환 오류코드 확인
	 * 
	 * @return 국세청 반환 오류코드
	 */
	public String getNTSSendErrCode() {
		return ntssendErrCode;
	}

	/**
	 * 상태 메모 확인
	 * 
	 * @return 상태 메모
	 */
	public String getStateMemo() {
		return stateMemo;
	}
	
	/**
	 * 세금계산서 지연발행 여부 확인 
	 * 
	 * @return 지연발행 여부 
	 */
	public String getLateIssueYN(){
		return lateIssueYN;
	}

	/**
	 * 공급자 인쇄여부 확인 
	 * 
	 * @return 공급자 인쇄여부 
	 */
	public boolean isInvoicerPrintYN() {
		return invoicerPrintYN;
	}
	

	/**
	 * 공급받는자 인쇄여부 확인
	 * 
	 * @return 공급받는자 인쇄여부
	 * 
	 */
	public boolean isInvoiceePrintYN() {
		return invoiceePrintYN;
	}

	/**
	 * 수탁자 인쇄여부 확인 
	 * 
	 * @return 수탁자 인쇄여부 
	 */
	public boolean isTrusteePrintYN() {
		return trusteePrintYN;
	}
	
	/**
	 * 세금계산서 발행시 공급받는자 휴폐업상태정보
	 * 
	 * @return 휴폐업 상태
	 */
	public Integer getCloseDownState() {
		return closeDownState;
	}
	
	/**
	 * 세금계산서 발행시 공급받는자 휴폐업일자
	 * 
	 * @return 공급받는자 휴폐업일자
	 */
	public String getCloseDownStateDate() {
		return closeDownStateDate;
	}
	
	/**
	 * 세금계산서 연동문서 여부 확인
	 * 
	 * @return true(연동문서)/false(일반문서)
	 * @return
	 */
	public boolean getInterOPYN() {
		return interOPYN;
	}
}

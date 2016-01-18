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

package com.popbill.api.statement;

/**
 * Class for Statement' outline information.
 * 
 * @author JeongYoHan
 * @version 1.0.0
 */
public class StatementInfo {
	private String itemKey;
	private Integer itemCode;
	private String invoiceNum;
	private String mgtKey;
	
	private int stateCode;
	private String taxType;
	private String purposeType;
	
	private String writeDate;
	private String senderCorpName;
	private String senderCorpNum;
	private boolean senderPrintYN;
	private String receiverCorpName;
	private String receiverCorpNum;
	private boolean receiverPrintYN;
	
	private String supplyCostTotal;
	private String taxTotal;
	private String issueDT;
	private String stateDT;
	private boolean openYN;
	private String openDT;
	private String stateMemo;
	private String regDT;

	/**
	 * 팝빌 관리번호 확인
	 * 
	 * @return 팝빌 관리번호
	 */
	public String getItemKey() {
		return itemKey;
	}

	/**
	 * 명세서 코드 확인
	 * 
	 * 121 : 거래명세서
	 * 122 : 청구서
	 * 123 : 견적서
	 * 124 : 발주서
	 * 125 : 입금표
	 * 126 : 영수증
	 * 
	 * @return 명세서 코드
	 */
	public Integer getItemCode() {
		return itemCode;
	}

	/**
	 * 명세서 코드 설정
	 * 
	 * 121 : 거래명세서
	 * 122 : 청구서
	 * 123 : 견적서
	 * 124 : 발주서
	 * 125 : 입금표
	 * 126 : 영수증
	 * 
	 * @param itemCode
	 * 		    명세서 코드
	 */

	public String getInvoiceNum() {
		return invoiceNum;
	}

	/**
	 * 문서관리번호 확인
	 * 
	 * @return 문서관리번호
	 */
	public String getMgtKey() {
		return mgtKey;
	}


	/**
	 * 문서 상태코드 확인
	 * 
	 * @return 문서상태코드
	 */
	public int getStateCode() {
		return stateCode;
	}

	/**
	 * 세금형태 확인
	 * 
	 * @return 세금형태
	 */
	public String getTaxType() {
		return taxType;
	}

	/**
	 *  영수/청구 확인
	 *  
	 * @return 영수/청구
	 */
	public String getPurposeType() {
		return purposeType;
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
	 * 발신자 상호 확인
	 * 
	 * @return 발신자 상호
	 */
	public String getSenderCorpName() {
		return senderCorpName;
	}

	
	/**
	 * 발신자 사업자 등록번호 확인
	 * 
	 * @return 발신자 사업자 등록번호
	 */

	public String getSenderCorpNum() {
		return senderCorpNum;
	}


	/**
	 *  수신자 상호명 확인
	 *  
	 * @return 수신자 상호 
	 */

	public String getReceiverCorpName() {
		return receiverCorpName;
	}

	/**
	 * 수신자 사업자 등록번호 확인 
	 * 
	 * @return 수신자 사업자 등록번호 확인
	 */

	public String getReceiverCorpNum() {
		return receiverCorpNum;
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
	 * 세액 합계 확인
	 * 
	 * @return 세액합계
	 */
	public String getTaxTotal() {
		return taxTotal;
	}
	/**
	 *  발행일시 확인
	 *  
	 * @return 발행일시
	 */
	public String getIssueDT() {
		return issueDT;
	}

	/**
	 * 문서 상태코드 확인
	 * 
	 * @return 문서 상태코드
	 */
	public String getStateDT() {
		return stateDT;
	}

	/**
	 * 개봉여부 확인
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
	 * 상태메모 확인
	 * 
	 * @return 상태메모
	 */
	public String getStateMemo() {
		return stateMemo;
	}


	/**
	 * 등록일시 확인
	 * 
	 * @return 등록일시
	 *
	 */
	public String getRegDT() {
		return regDT;
	}

	/**
	 * 발신자 인쇄여부 확인 
	 * 
	 * @return 발신자 인쇄여부 
	 */
	public boolean isSenderPrintYN() {
		return senderPrintYN;
	}

	/**
	 * 수신자 인쇄여부 확인 
	 * 
	 * @return 수신자 인쇄여부 
	 */
	public boolean isReceiverPrintYN() {
		return receiverPrintYN;
	}

	/**
	 * @param senderPrintYN the senderPrintYN to set
	 */
	public void setSenderPrintYN(boolean senderPrintYN) {
		this.senderPrintYN = senderPrintYN;
	}

	/**
	 * @param receiverPrintYN the receiverPrintYN to set
	 */
	public void setReceiverPrintYN(boolean receiverPrintYN) {
		this.receiverPrintYN = receiverPrintYN;
	}

}

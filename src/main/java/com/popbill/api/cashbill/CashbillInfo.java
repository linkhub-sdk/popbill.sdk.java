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
package com.popbill.api.cashbill;

/**
 * Class for Cashbill' outline information.
 *
 * @author JeongYoHan
 * @version 1.0.0
 */

public class CashbillInfo {
	private String itemKey;
	private String mgtKey;
	private String tradeDate;
	private String issueDT;
	private String customerName;

	private String itemName;
	private String identityNum;
	private String taxationType;
	private String totalAmount;
	private String tradeUsage;
	private String tradeOpt;
	private String tradeType;

	private int stateCode;

	private String stateDT;
	private boolean printYN;

	private String confirmNum;
	private String orgTradeDate;
	private String orgConfirmNum;

	private String ntssendDT;
	private String ntsresult;
	private String ntsresultDT;
	private String ntsresultCode;
	private String ntsresultMessage;
	private String regDT;
	private String stateMemo;


	/**
	 * 팝빌번호 확인
	 *
	 * @return the itemKey
	 */
	public String getItemKey() {
		return itemKey;
	}

	/**
	 * 문서번호 확인
	 *
	 * @return the mgtKey
	 */
	public String getMgtKey() {
		return mgtKey;
	}
	/**
	 * 거래일자 확인
	 *
	 * @return the tradeDate
	 */
	public String getTradeDate() {
		return tradeDate;
	}

	/**
	 * 발행일시 확인
	 *
	 * @return the issueDT
	 */
	public String getIssueDT() {
		return issueDT;
	}

	/**
	 * 고객명 확인
	 *
	 * @return the customerName
	 */
	public String getCustomerName() {
		return customerName;
	}

	/**
	 * 품명 확인
	 *
	 * @return the itemName
	 */
	public String getItemName() {
		return itemName;
	}

	/**
	 * 거래처 식별번호 확인
	 *
	 * @return the identityNum
	 */
	public String getIdentityNum() {
		return identityNum;
	}

	/**
	 * 과세형태 확인
	 *
	 * @return the taxationType
	 */
	public String getTaxationType() {
		return taxationType;
	}

	/**
	 * 거래금액 확인
	 *
	 * @return the totalAmount
	 */
	public String getTotalAmount() {
		return totalAmount;
	}

	/**
	 * 거래 구분 확인
	 * @return the tradeUsage
	 */
	public String getTradeUsage() {
		return tradeUsage;
	}

	/**
	 * 거래 유형 확인
	 * @return the tradeOpt
	 */
	public String getTradeOpt() {
		return tradeOpt;
	}

	/**
	 * 현금영수증 형태 확인
	 *
	 * @return the tradeType
	 */
	public String getTradeType() {
		return tradeType;
	}

	/**
	 * 상태코드 확인
	 *
	 * @return the stateCode
	 */
	public int getStateCode() {
		return stateCode;
	}

	/**
	 * 상태변경 일시 확인
	 *
	 * @return the stateDT
	 */
	public String getStateDT() {
		return stateDT;
	}

	/**
	 * 인쇄여부 확인
	 *
	 * @return the printYN
	 */
	public boolean isPrintYN() {
		return printYN;
	}

	/**
	 * 국세청 승인번호
	 *
	 * @return the confirmNum
	 */
	public String getConfirmNum() {
		return confirmNum;
	}

	/**
	 * 원본 현금영수증 거래일자
	 *
	 * @return the orgTradeDate
	 */
	public String getOrgTradeDate() {
		return orgTradeDate;
	}

	/**
	 * 원본 현금영수증 국새청 승인번호
	 *
	 * @return the orgConfirmNum
	 */
	public String getOrgConfirmNum() {
		return orgConfirmNum;
	}
	/**
	 * 국세청 전송일시 확인
	 *
	 * @return the ntssendDT
	 */
	public String getNtssendDT() {
		return ntssendDT;
	}
	/**
	 * 국세청 전송결과 확인
	 *
	 * @return the ntsresult
	 */
	public String getNtsresult() {
		return ntsresult;
	}
	/**
	 * 국세청 처리결과 수신일시
	 *
	 * @return the ntsresultDT
	 */
	public String getNtsresultDT() {
		return ntsresultDT;
	}
	/**
	 * 국세청 처리결과 상태코드
	 *
	 * @return the ntsresultCode
	 */
	public String getNtsresultCode() {
		return ntsresultCode;
	}
	/**
	 * 국세청 처리결과 메시지
	 *
	 * @return the ntsresultMessage
	 */
	public String getNtsresultMessage() {
		return ntsresultMessage;
	}
	/**
	 * 등록일시 확인
	 *
	 * @return the regDT
	 */
	public String getRegDT() {
		return regDT;
	}

	/**
	 * 상태메모 확인
	 *
	 * @return the stateMemo
	 */
	public String getStateMemo() {
		return stateMemo;
	}
}

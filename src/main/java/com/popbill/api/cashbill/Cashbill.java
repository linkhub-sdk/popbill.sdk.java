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

import java.io.Serializable;

/**
 * Class for Cashbill Information.
 * 
 * @author JeongYoHan
 * @version 1.0.0
 */
public class Cashbill implements Serializable {	
	private static final long serialVersionUID = 1162308900637310379L;
	
	private String memo;
	private String mgtKey;
	private String tradeDate;
	private String tradeUsage;
	private String tradeType;
	
	private String taxationType;
	private String supplyCost;
	private String tax;
	private String serviceFee;
	private String totalAmount;
	
	private String franchiseCorpNum;
	private String franchiseCorpName;
	private String franchiseCEOName;
	private String franchiseAddr;
	private String franchiseTEL;
	
	private String identityNum;
	private String customerName;
	private String itemName;
	private String orderNumber;
	
	private String email;
	private String hp;
	private String fax;
	private boolean faxsendYN;
	private boolean smssendYN;
	
	private String confirmNum;
	private String orgConfirmNum;
	private String orgTradeDate;
	
	private Integer cancelType;
	

	/**
	 * 문서관리번호 확인 
	 * 
	 * @return the mgtKey
	 */
	public String getMgtKey() {
		return mgtKey;
	}
	
	/**
	 * 문서관리번호 설정
	 * 
	 * @param mgtKey the mgtKey to set
	 */
	public void setMgtKey(String mgtKey) {
		this.mgtKey = mgtKey;
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
	 * 거래유형 확인
	 * 
	 * @return the tradeUsage
	 */
	public String getTradeUsage() {
		return tradeUsage;
	}
	
	/**
	 * 거래유형 설정
	 * 
	 * @param tradeUsage the tradeUsage to set
	 */
	public void setTradeUsage(String tradeUsage) {
		this.tradeUsage = tradeUsage;
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
	 * 현금영수증 형태 설정
	 * 
	 * @param tradeType the tradeType to set
	 */
	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
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
	 * 과세형태 설정
	 * 
	 * @param taxationType the taxationType to set
	 */
	public void setTaxationType(String taxationType) {
		this.taxationType = taxationType;
	}
	
	/**
	 * 공급가액 확인
	 * 
	 * @return the supplyCost
	 */
	public String getSupplyCost() {
		return supplyCost;
	}
	
	/**
	 * 공급가액 설정
	 * 
	 * @param supplyCost the supplyCost to set
	 */
	public void setSupplyCost(String supplyCost) {
		this.supplyCost = supplyCost;
	}
	
	/**
	 * 세액 확인
	 * 
	 * @return the tax
	 */
	public String getTax() {
		return tax;
	}
	
	/**
	 * 세액 설정
	 * 
	 * @param tax the tax to set
	 */
	public void setTax(String tax) {
		this.tax = tax;
	}
	
	/**
	 * 볼사료 확인
	 * 
	 * @return the serviceFee
	 */
	public String getServiceFee() {
		return serviceFee;
	}
	
	/**
	 * 봉사료 설정
	 * 
	 * @param serviceFee the serviceFee to set
	 */
	public void setServiceFee(String serviceFee) {
		this.serviceFee = serviceFee;
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
	 * 거래금액 설정
	 * 
	 * @param totalAmount the totalAmount to set
	 */
	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	/**
	 * 가맹점 사업자번호 확인
	 * 
	 * @return the franchiseCropNum
	 */
	public String getFranchiseCorpNum() {
		return franchiseCorpNum;
	}
	
	/**
	 * 가맹점 사업자번호 설정
	 * 
	 * @param franchiseCorpNum the franchiseCorpNum to set
	 */
	public void setFranchiseCorpNum(String franchiseCropNum) {
		this.franchiseCorpNum = franchiseCropNum;
	}
	
	/**
	 * 가맹점 상호 확인
	 * 
	 * @return the franchiseCorpName
	 */
	public String getFranchiseCorpName() {
		return franchiseCorpName;
	}
	
	/**
	 * 가맹점 상호 설정
	 * 
	 * @param franchiseCorpName the franchiseCorpName to set
	 */
	public void setFranchiseCorpName(String franchiseCorpName) {
		this.franchiseCorpName = franchiseCorpName;
	}
	
	/**
	 * 가맹점 대표자 성명 확인
	 * 
	 * @return the franchiseCEOName
	 */
	public String getFranchiseCEOName() {
		return franchiseCEOName;
	}
	
	/**
	 * 가맹점 대표자 성명 설정
	 * 
	 * @param franchiseCEOName the franchiseCEOName to set
	 */
	public void setFranchiseCEOName(String franchiseCEOName) {
		this.franchiseCEOName = franchiseCEOName;
	}
	
	/**
	 * 가맹점 주소 확인
	 * 
	 * @return the franchiseAddr
	 */
	public String getFranchiseAddr() {
		return franchiseAddr;
	}
	
	/**
	 * 가맹점 주소 설정
	 * 
	 * @param franchiseAddr the franchiseAddr to set
	 */
	public void setFranchiseAddr(String franchiseAddr) {
		this.franchiseAddr = franchiseAddr;
	}
	
	/**
	 * 가명잼 전화번호 확인
	 * 
	 * @return the franchiseTEL
	 */
	public String getFranchiseTEL() {
		return franchiseTEL;
	}
	
	/**
	 * 가맹점 전화번호 설정
	 * 
	 * @param franchiseTEL the franchiseTEL to set
	 */
	public void setFranchiseTEL(String franchiseTEL) {
		this.franchiseTEL = franchiseTEL;
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
	 * 거래처 식별번호 설정
	 * 
	 * @param identityNum the identityNum to set
	 */
	public void setIdentityNum(String identityNum) {
		this.identityNum = identityNum;
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
	 * 고객명 설정
	 * 
	 * @param customerName the customerName to set
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	/**
	 * 상품명 확인
	 * 
	 * @return the itemName
	 */
	public String getItemName() {
		return itemName;
	}
	
	/**
	 * 상품명 설정
	 * 
	 * @param itemName the itemName to set
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
	/**
	 * 가맹점 주문번호 확인
	 * 
	 * @return the orderNumber
	 */
	public String getOrderNumber() {
		return orderNumber;
	}
	
	/**
	 * 가맹점 주문번호 설정
	 * 
	 * @param orderNumber the orderNumber to set
	 */
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	
	/**
	 * 거래처 이메일 확인
	 * 
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * 거래처 이메일 설정
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * 거래처 휴대전화번호 확인
	 * 
	 * @return the hp
	 */
	public String getHp() {
		return hp;
	}
	
	/**
	 * 거래처 휴대전화번호 설정
	 * 
	 * @param hp the hp to set
	 */
	public void setHp(String hp) {
		this.hp = hp;
	}
	
	/**
	 * 거래처 팩스번호 확인
	 * 
	 * @return the fax
	 */
	public String getFax() {
		return fax;
	}
	
	/**
	 * 거래처 팩스번호 설정
	 * @param fax the fax to set
	 */
	public void setFax(String fax) {
		this.fax = fax;
	}
	
	/**
	 * 팩스 전송여부 확인
	 * 
	 * @return the faxsendYN
	 */
	public boolean isFaxsendYN() {
		return faxsendYN;
	}
	
	/**
	 * 팩스 전송여부 설정
	 * 
	 * @param faxsendYN the faxsendYN to set
	 */
	public void setFaxsendYN(boolean faxsendYN) {
		this.faxsendYN = faxsendYN;
	}
	
	/**
	 * 문자 전송여부 확인
	 * 
	 * @return the smssendYN
	 */
	public boolean isSmssendYN() {
		return smssendYN;
	}
	
	/**
	 * 문자 전송여부 설정
	 * 
	 * @param smssendYN the smssendYN to set
	 */
	public void setSmssendYN(boolean smssendYN) {
		this.smssendYN = smssendYN;
	}
	
	/**
	 * @return the confirmNum
	 */
	public String getConfirmNum() {
		return confirmNum;
	}
	
	/**
	 * @return the orgConfirmNum
	 */
	public String getOrgConfirmNum() {
		return orgConfirmNum;
	}
	
	/**
	 * 원본 현금영수증 국세청 승인번호 설정
	 * 
	 * @param orgConfirmNum
	 */
	public void setOrgConfirmNum(String orgConfirmNum) {
		this.orgConfirmNum = orgConfirmNum;
	}

	/**
	 * 즉시발행 메모 설정 
	 * 
	 * @param memo
	 * 			메모 
	 */
	public void setMemo(String memo) {
		this.memo = memo;
	}

	/**
	 * 즉시발행 메모 
	 * 
	 * @return 메모 
	 */
	public String getMemo() {
		return memo;
	}
		
	
	/*
	 * 원본현금영수증 거래일자
	 * 
	 * @return 원본 현금영수증 거래일자.yyyy-mm-dd
	 */
	public String getOrgTradeDate() {
		return orgTradeDate;
	}
	
	
	/*
	 * 원본현금영수증 거래일자
	 * 
	 * @param orgTradeDate
	 */
	public void setOrgTradeDate(String orgTradeDate) {
		this.orgTradeDate = orgTradeDate;
	}

	/*
	 * 취소사유, 1-거래취소, 2-오류발급취소, 3-기타 
	 */
	public Integer getCancelType() {
		return cancelType;
	}

	/*
	 * 취소사유, 1-거래취소, 2-오류발급취소, 3-기타
	 * 
	 * @return 취소사유 
	 */
	public void setCancelType(Integer cancelType) {
		this.cancelType = cancelType;
	}
}

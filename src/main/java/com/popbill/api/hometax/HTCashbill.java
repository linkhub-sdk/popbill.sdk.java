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
package com.popbill.api.hometax;

import java.io.Serializable;

/**
 * Class for HomeTax Cashbill Detail Information
 *  
 * @author JeongYoHan
 * @version 1.0.0
 */
public class HTCashbill implements Serializable{
	private static final long serialVersionUID = -6376987841681626780L;
	
	private String ntsconfirmNum;
	private String tradeDT;
	private String tradeUsage;
	private String tradeType;
	
	private String supplyCost;
	private String tax;
	private String serviceFee;
	private String totalAmount;
	
	private String franchiseCorpNum;
	private String franchiseCorpName;
	private Short franchiseCorpType;
	
	private Short identityNumType;
	private String identityNum;
	private String customerName;
	private String cardOwnerName;
	private Short deductionType;

	/**
	 * 국세청승인번호 확인 
	 * 
	 * @return 국세청승인번호 
	 */
	public String getNtsconfirmNum() {
		return ntsconfirmNum;
	}
	
	/**
	 * 거래일시 확인 
	 * 
	 * @return 거래일시 
	 */
	public String getTradeDT() {
		return tradeDT;
	}
	
	/**
	 * 거래유형 확인 
	 * 
	 * @return 거래유형, (소득공제용/지출증빙용) 중 반환 
	 */
	public String getTradeUsage() {
		return tradeUsage;
	}
	
	/**
	 * 현금영수증 형태 확인 
	 * 
	 * @return 현금영수증 형태, (승인거래/취소거래) 중 반환 
	 */
	public String getTradeType() {
		return tradeType;
	}
	
	/**
	 * 공급가액 확인
	 * 
	 * @return 공급가액 
	 */
	public String getSupplyCost() {
		return supplyCost;
	}
	
	/**
	 * 세액 확인
	 * 
	 * @return 세액 
	 */
	public String getTax() {
		return tax;
	}
	
	/**
	 * 봉사료 확인 
	 * 
	 * @return 봉사료 
	 */
	public String getServiceFee() {
		return serviceFee;
	}
	
	/**
	 * 거래금액 확인
	 * 
	 * @return 거래금액 
	 */
	public String getTotalAmount() {
		return totalAmount;
	}
	
	/**
	 * 발행자 사업자번호 확인 
	 * 
	 * @return 발행자 사업자번호 
	 */
	public String getFranchiseCorpNum() {
		return franchiseCorpNum;
	}
	
	/**
	 * 발행자 상호 확인 
	 * 
	 * @return 발행자 상호 
	 */
	public String getFranchiseCorpName() {
		return franchiseCorpName;
	}
	
	/**
	 * 발행자 사업자유형 확인 
	 * 
	 * @return 발행자 사업자유형 
	 */
	public Short getFranchiseCorpType() {
		return franchiseCorpType;
	}
	
	/**
	 * 식별번호유형 확인 
	 * 
	 * @return 식별번호유형, 1-일반과세자, 2-간이과세자, 5-법인과세
	 */
	public Short getIdentityNumType() {
		return identityNumType;
	}
	
	/**
	 * 거래처 식별번호 확인 
	 * 
	 * @return 거래처 식별번호 
	 */
	public String getIdentityNum() {
		return identityNum;
	}
	
	/**
	 * 고객명 확인 
	 * 
	 * @return 고객명 
	 */
	public String getCustomerName() {
		return customerName;
	}
	
	/**
	 * 카드소유자명 확인
	 * 
	 * @return 카드소유자명 
	 */
	public String getCardOwnerName() {
		return cardOwnerName;
	}
	
	/**
	 * 공제유형 확인
	 * 
	 * @return 공제유형 1,2-공제 / 3,4-불공제 
	 */
	public Short getDeductionType() {
		return deductionType;
	}
}

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

/**
 * Class for FlatRate Statement.
 * 
 * @author JeongYohan
 * @version 1.0.0
 */

public class FlatRateState {
	private String referenceID;
	private String contractDT;
	private Short baseDate;
	private String useEndDate;
	private Short state;
	private Boolean closeRequestYN;
	private Boolean useRestrictYN;
	private Boolean closeOnExpired;
	private Boolean unPaidYN;
	
	/**
	 * 연동회원 사업자번호 확인 
	 * 
	 * @return 연동회원 사업자번호 
	 */
	public String getReferenceID() {
		return referenceID;
	}
	
	/**
	 * 정액제 서비스 시작일시 확인  
	 * 
	 * @return 정액제 서비스 시작일시 (yyyyMmddHHmmss)
	 */
	public String getContraDT() {
		return contractDT;
	}
	
	/**
	 * 자동연장 결제일 확인 
	 * 
	 * @return 자동연장 결제일, 5/15/25 중 반환 
	 */
	public Short getBaseDate() {
		return baseDate;
	}
	
	/**
	 * 정액제 서비스 종료일 확인
	 * 
	 * @return 정액제 서비스 종료일 (yyyyMMdd)
	 */
	public String getUseEndDate() {
		return useEndDate;
	}
	
	/**
	 * 정액제 서비스 상태 확인 
	 * 
	 * @return 정액제 서비스 상태, 1-사용, 2-해제 
	 */
	public Short getState() {
		return state;
	}
	
	/**
	 * 정액제 서비스 해지신청 여부 확인
	 * 
	 * @return 정액제 서비스 해지신청 여부 
	 */
	public Boolean getCloseRequestYN() {
		return closeRequestYN;
	}
	
	/**
	 * 정액제 서비스 사용제한 여부 확인 
	 * 
	 * @return 정액제 서비스 사용제한 여부 
	 */
	public Boolean getUseRestrictYN() {
		return useRestrictYN;
	}
	
	/**
	 * 정액제 서비스 만료 시 해지 여부 확인  
	 * 
	 * @return 정액제 서비스 만료 시 해지 여부 
	 */
	public Boolean getCloseOnExpired() {
		return closeOnExpired;
	}
	
	/**
	 * 미수금 보유 여부 확인 
	 * 
	 * @return 미수금 보유 여부 
	 */
	public Boolean getUnPaidYN() {
		return unPaidYN;
	}
}

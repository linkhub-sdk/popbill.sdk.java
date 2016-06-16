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
 * Class for Charge Information.
 * 
 * @author JeongYohan
 * @version 1.0.0
 */

public class ChargeInfo {
	private String unitCost;
	private String chargeMethod;
	private String rateSystem;
	
	/**
	 * 단가확인, 종량제 - 발행단가, 정액제 - 월정액 요금 반환 
	 *  
	 * @return 발행단가, 월정액요
	 */
	public String getUnitCost() {
		return unitCost;
	}
	
	/**
	 * 과금유형 확인
	 * 
	 * @return "일반", "연동", "파트너" 중 반환 
	 */
	public String getChargeMethod() {
		return chargeMethod;
	}
	
	/**
	 * 과금제도 확인
	 * 
	 * @return "정액제", "종량제" 중 반환
	 */
	public String getRateSystem() {
		return rateSystem;
	}
}

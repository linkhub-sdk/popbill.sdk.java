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

/**
 * Class For HTTaxinvoice Search Summary
 * 
 * @author JeongYoHan
 * @version 1.0.0
 */

public class HTTaxinvoiceSummary {
	private long count;
	private long supplyCostTotal;
	private long taxTotal;
	private long amountTotal;
	
	/**
	 * 수집결과건수 확인 
	 * 
	 * @return 수집결과건수 
	 */
	public long getCount() {
		return count;
	}
	
	/**
	 * 공급가액 합계 확인 
	 * 
	 * @return 공급가액 합계 
	 */
	public long getSupplyCostTotal() {
		return supplyCostTotal;
	}
	
	/**
	 * 세액 합계 확인 
	 * 
	 * @return 세액 합계 
	 */
	public long getTaxTotal() {
		return taxTotal;
	}
	
	/**
	 * 합계 금액 확인 
	 * 
	 * @return 합계 금액 
	 */
	public long getAmountTotal() {
		return amountTotal;
	}
}

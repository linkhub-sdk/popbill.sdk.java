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

/**
 * Class for HomeTax Taxinvoice Deatil Information
 * 
 * @author JeongYoHan
 * @version 1.0.0
 */
public class HTTaxinvoiceDetail implements Serializable {
	private static final long serialVersionUID = 2090626401571944998L;

	private Short serialNum;
	private String purchaseDT;
	private String itemName;
	private String spec;
	private String qty;
	private String unitCost;
	private String supplyCost;
	private String tax;
	private String remark;

	/**
	 * return 일련번호
	 * 
	 * @return 일련번호
	 */
	public Short getSerialNum() {
		return serialNum;
	}

	/**
	 * Sets 일련번호, Starts by 1
	 * 
	 * @param serialNum
	 *            일련번호
	 */
	public void setSerialNum(Short serialNum) {
		this.serialNum = serialNum;
	}

	/**
	 * return 거래일자, formated by 'yyyyMMdd'
	 * 
	 * @return 거래일자
	 */
	public String getPurchaseDT() {
		return purchaseDT;
	}

	/**
	 * Sets 거래일자, Formated by 'yyyyMMdd'
	 * 
	 * @param purchaseDT
	 *            거래일자, formated by 'yyyyMMdd'
	 */
	public void setPurchaseDT(String purchaseDT) {
		this.purchaseDT = purchaseDT;
	}

	/**
	 * returns 품목
	 * 
	 * @return 품목
	 */
	public String getItemName() {
		return itemName;
	}

	/**
	 * Sets 품목
	 * 
	 * @param itemName
	 *            품목
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	/**
	 * returns 규격
	 * 
	 * @return 규격.
	 */
	public String getSpec() {
		return spec;
	}

	/**
	 * Sets 규격.
	 * 
	 * @param spec
	 *            규격
	 */
	public void setSpec(String spec) {
		this.spec = spec;
	}

	/**
	 * returns 수량. numeric only.
	 * 
	 * @return 수량.
	 */
	public String getQty() {
		return qty;
	}

	/**
	 * Sets 수량. Numeric Only.
	 * 
	 * @param qty
	 *            수
	 */
	public void setQty(String qty) {
		this.qty = qty;
	}

	/**
	 * returns 단가. Numeric Only.
	 * 
	 * @return 단가.
	 */
	public String getUnitCost() {
		return unitCost;
	}

	/**
	 * Sets 단가. Numeric Only.
	 * 
	 * @param unitCost
	 *            단가
	 */
	public void setUnitCost(String unitCost) {
		this.unitCost = unitCost;
	}

	/**
	 * return 공급가액 Numeric Only.
	 * 
	 * @return 공급가액
	 */
	public String getSupplyCost() {
		return supplyCost;
	}

	/**
	 * Sets 공급가액 Numeric Only.
	 * 
	 * @param supplyCost
	 *            공급가액
	 */
	public void setSupplyCost(String supplyCost) {
		this.supplyCost = supplyCost;
	}

	/**
	 * returns 세액. Numeric Only.
	 * 
	 * @return 세액
	 */
	public String getTax() {
		return tax;
	}

	/**
	 * Sets 세액, Numeric Only.
	 * 
	 * @param tax
	 *            세액
	 */
	public void setTax(String tax) {
		this.tax = tax;
	}

	/**
	 * returns 비고
	 * 
	 * @return 비고
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * Sets 비고.
	 * 
	 * @param remark
	 *            비고
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
}

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
package com.popbill.api.statement;

import java.io.Serializable;

/**
 * Class for Statement's DetailList.
 * 
 * @author JeongYoHan
 * @version 1.0.0
 */
public class StatementDetail implements Serializable {
	private static final long serialVersionUID = -8680336228045406574L;
	
	private Short serialNum;
	private String purchaseDT;
	private String itemName;
	private String spec;
	private String unit;
	private String qty;
	private String unitCost;
	private String supplyCost;
	private String tax;
	private String remark;
	private String spare1;
	private String spare2;
	private String spare3;
	private String spare4;
	private String spare5;
	private String spare6;
	private String spare7;
	private String spare8;
	private String spare9;
	private String spare10;
	private String spare11;
	private String spare12;
	private String spare13;
	private String spare14;
	private String spare15;
	private String spare16;
	private String spare17;
	private String spare18;
	private String spare19;
	private String spare20;
	
	/**
	 * return 일련번호
	 * 
	 * @return 일련번호
	 */
	public Short getSerialNum() {
		return serialNum;
	}
	
	/**
	 * Sets 일련번호, 1부터 순차기재
	 * 
	 * @param serialNum
	 * 			일련번호
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
 	 * Sets 거래일자, formated by 'yyyyMMdd'
 	 * 
 	 * @param purchaseDT
 	 * 			거래일자, formated by 'yyyyMmdd'
	 * 
	 */
	public void setPurchaseDT(String purchaseDT) {
		this.purchaseDT = purchaseDT;
	}
	/**
 	 * return 품목
 	 * 
 	 *  @return 품목
	 */
	public String getItemName() {
		return itemName;
	}
	/**
 	 * Sets 품목 
 	 * 
	 * @param itemName
	 *			품목 
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
	/**
	 * return 규격
	 * 
	 * @return 규격
	 * 
	 */
	public String getSpec() {
		return spec;
	}
	/**
	 * Sets 규격
	 * 
	 * @param spec
	 * 		  	규격
	 */
	
	public void setSpec(String spec) {
		this.spec = spec;
	}
	/**
	 * return 단위
	 * 
	 * @return 단위
	 * 
	 */
	public String getUnit() {
		return unit;
	}
	
	/**
	 * Sets 단위
	 * 
	 * @param 단위
	 * 		  
	 */
	public void setUnit(String unit) {
		this.unit = unit;
	}
	/**
	 * return 수량 
	 * 
	 * @return 수량 
	 * 
	 */
	public String getQty() {
		return qty;
	}
	
	/**
	 * Sets 수량
	 * 
	 * @param 수량
	 * 		  
	 */
	public void setQty(String qty) {
		this.qty = qty;
	}
	/**
	 * return 수량
	 * 
	 * @return 수량
	 * 
	 */
	public String getUnitCost() {
		return unitCost;
	}
	
	/**
	 * Sets 단가
	 * 
	 * @param 단가
	 * 		  
	 */
	public void setUnitCost(String unitCost) {
		this.unitCost = unitCost;
	}
	/**
	 * return 단가
	 * 
	 * @return 단가
	 * 
	 */
	public String getSupplyCost() {
		return supplyCost;
	}
	
	/**
	 * Sets 공급가액
	 * 
	 * @param 공급가액
	 * 		  
	 */
	public void setSupplyCost(String supplyCost) {
		this.supplyCost = supplyCost;
	}
	/**
	 * return 공급가액
	 * 
	 * @return 공급가액
	 * 
	 */
	public String getTax() {
		return tax;
	}
	
	/**
	 * Sets 세액
	 * 
	 * @param 세액
	 * 		  
	 */
	public void setTax(String tax) {
		this.tax = tax;
	}
	/**
	 * return 세액
	 * 
	 * @return 세액
	 * 
	 */
	public String getRemark() {
		return remark;
	}
	
	/**
	 * Sets 비고
	 * 
	 * @param 비고
	 * 		  
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * return 비고
	 * 
	 * @return 비고
	 * 
	 */
	public String getSpare1() {
		return spare1;
	}
	
	/**
	 * Sets 여분1 
	 * 
	 * @param 여분1
	 * 		  
	 */
	public void setSpare1(String spare1) {
		this.spare1 = spare1;
	}
	/**
	 * return 여분1
	 * 
	 * @return 여분1
	 * 
	 */
	public String getSpare2() {
		return spare2;
	}
	
	/**
	 * Sets 여분2
	 * 
	 * @param 여분2
	 * 		  
	 */
	public void setSpare2(String spare2) {
		this.spare2 = spare2;
	}
	/**
	 * return 여분2
	 * 
	 * @return 여분2
	 * 
	 */
	public String getSpare3() {
		return spare3;
	}	
	/**
	 * Sets 여분3
	 * 
	 * @param 여분3
	 * 		  
	 */
	public void setSpare3(String spare3) {
		this.spare3 = spare3;
	}
	/**
	 * return 여분4
	 * 
	 * @return 여분4
	 * 
	 */
	public String getSpare4() {
		return spare4;
	}	
	/**
	 * Sets 여분4
	 * 
	 * @param 여분4
	 * 		  
	 */
	public void setSpare4(String spare4) {
		this.spare4 = spare4;
	}
	/**
	 * return 여분5
	 * 
	 * @return 여분5
	 * 
	 */
	public String getSpare5() {
		return spare5;
	}	
	/**
	 * Sets 여분5
	 * 
	 * @param 여분5
	 * 		  
	 */
	public void setSpare5(String spare5) {
		this.spare5 = spare5;
	}

	public String getSpare6() {
		return spare6;
	}

	public void setSpare6(String spare6) {
		this.spare6 = spare6;
	}

	public String getSpare7() {
		return spare7;
	}

	public void setSpare7(String spare7) {
		this.spare7 = spare7;
	}

	public String getSpare8() {
		return spare8;
	}

	public void setSpare8(String spare8) {
		this.spare8 = spare8;
	}

	public String getSpare9() {
		return spare9;
	}

	public void setSpare9(String spare9) {
		this.spare9 = spare9;
	}

	public String getSpare10() {
		return spare10;
	}

	public void setSpare10(String spare10) {
		this.spare10 = spare10;
	}

	public String getSpare11() {
		return spare11;
	}

	public void setSpare11(String spare11) {
		this.spare11 = spare11;
	}

	public String getSpare12() {
		return spare12;
	}

	public void setSpare12(String spare12) {
		this.spare12 = spare12;
	}

	public String getSpare13() {
		return spare13;
	}

	public void setSpare13(String spare13) {
		this.spare13 = spare13;
	}

	public String getSpare14() {
		return spare14;
	}

	public void setSpare14(String spare14) {
		this.spare14 = spare14;
	}

	public String getSpare15() {
		return spare15;
	}

	public void setSpare15(String spare15) {
		this.spare15 = spare15;
	}

	public String getSpare16() {
		return spare16;
	}

	public void setSpare16(String spare16) {
		this.spare16 = spare16;
	}

	public String getSpare17() {
		return spare17;
	}

	public void setSpare17(String spare17) {
		this.spare17 = spare17;
	}

	public String getSpare18() {
		return spare18;
	}

	public void setSpare18(String spare18) {
		this.spare18 = spare18;
	}

	public String getSpare19() {
		return spare19;
	}

	public void setSpare19(String spare19) {
		this.spare19 = spare19;
	}

	public String getSpare20() {
		return spare20;
	}

	public void setSpare20(String spare20) {
		this.spare20 = spare20;
	}
}

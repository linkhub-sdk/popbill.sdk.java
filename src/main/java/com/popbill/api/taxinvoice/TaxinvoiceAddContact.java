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
package com.popbill.api.taxinvoice;

import java.io.Serializable;

/**
 * Class for TaxinvoiceAddContact which means 추가담당자.
 * 
 * @author KimSeongjun
 * @see http://www.popbill.com
 * @version 1.0.0
 */
public class TaxinvoiceAddContact implements Serializable {
	private static final long serialVersionUID = 752955567955826460L;

	private int serialNum;
	private String email;
	private String contactName;

	/**
	 * returns 추가담당자 이메일주소
	 * 
	 * @return 추가담당자 이메일주소
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets 추가담당자 이메일주소
	 * 
	 * @param email
	 *            추가담당자 이메일주소
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * returns 추가담당자 성명
	 * 
	 * @return 추가담당자 성명
	 */
	public String getContactName() {
		return contactName;
	}

	/**
	 * Sets 추가담당자 성명
	 * 
	 * @param contactName
	 *            추가담당자 성명
	 */
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	/**
	 * returns 일련번호
	 * 
	 * @return 일련번호
	 */
	public int getSerialNum() {
		return serialNum;
	}

	/**
	 * Sets 일련번호, Starts by 1
	 * 
	 * @param serialNum
	 *            일련번호
	 */
	public void setSerialNum(int serialNum) {
		this.serialNum = serialNum;
	}

}

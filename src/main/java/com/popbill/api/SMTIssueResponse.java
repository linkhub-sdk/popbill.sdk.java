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
package com.popbill.api;

public class SMTIssueResponse {
	private long code;
	private String message;
	private String invoiceNum;

	/**
	 * returns Response Code.
	 * 
	 * @return code of response.
	 */
	public long getCode() {
		return code;
	}

	/**
	 * returns Response Message.
	 * 
	 * @return message of response.
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * returns invoiceNum
	 * 
	 * @return invoiceNum of statement
	 */
	public String getInvoiceNum() {
		return invoiceNum;
	}
	
}

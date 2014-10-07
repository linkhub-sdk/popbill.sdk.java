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

/**
 * Class for Email and publickKey can be used for InterOperation.
 * @author KimSeongjun
 * @see http://www.popbill.com
 * @version 1.0.0
 */
public class EmailPublicKey {

	private String confirmNum;
	private String email;
	
	/**
	 * returns 국세청 인증번호
	 * @return 국세청 인증번호
	 */
	public String getConfirmNum() {
		return confirmNum;
	}
	/**
	 * returns 유통이메일 주소
	 * @return 유통이메일 주소
	 */
	public String getEmail() {
		return email;
	}
}

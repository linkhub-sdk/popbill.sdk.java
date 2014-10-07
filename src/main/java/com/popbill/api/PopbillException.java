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

import kr.co.linkhub.auth.LinkhubException;

/**
 * Popbill Operation Exception.
 * 
 * @author KimSeongjun
 * @see http://www.popbill.com
 * @version 1.0.0
 */
public class PopbillException extends Exception {
	private static final long serialVersionUID = 1L;

	private long code;

	public PopbillException(LinkhubException linkhubException) {
		super(linkhubException.getMessage(), linkhubException);
		this.code = linkhubException.getCode();
	}

	public PopbillException(long code, String Message) {
		super(Message);
		this.code = code;
	}

	public PopbillException(long code, String Message, Throwable innerException) {
		super(Message, innerException);
		this.code = code;
	}

	/**
	 * Return Popbill's result Error code. (ex. -11010009) In case of -99999999,
	 * check the getMessage() for detail.
	 * 
	 * @return error code.
	 */
	public long getCode() {
		return code;
	}

}

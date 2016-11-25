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
package com.popbill.api.message;

/**
 * Enum for MessageType.
 * 
 * @author KimSeonjun
 * @version 1.0.0
 */
public enum MessageType {
	/**
	 * 단문
	 */
	SMS,
	/**
	 * 장문
	 */
	LMS,
	/**
	 * 단/장문
	 */
	XMS,
	/**
	 * MMS
	 */
	MMS
}

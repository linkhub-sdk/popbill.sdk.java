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
package com.popbill.api.kakao;

import java.io.Serializable;

/**
 * Class for SenderNum to list.
 * 
 * @author JeongYohan
 * @version 1.0.0
 */
public class SenderNumber implements Serializable {

	private static final long serialVersionUID = -8140988807131053917L;
	
	private String number;
	private Boolean representYN;
	private Integer state;
	private String memo;
	
	/**
	 *  등록된 발신번호
	 *  
	 * @return 발신번호
	 */
	public String getNumber() {
		return number;
	}
	
	/**
	 * 대표번호 지정여부
	 * 
	 * @return 대표번호 지정여부 (True, False)
	 */
	public Boolean getRepresentYN() {
		return representYN;
	}
	
	/**
	 * 발신번호 등록상태
	 * 0 - 대기 
	 * 1 - 승인
	 * 2 - 취소
	 * 
	 * @return 등록상태코드(0, 1, 2)
	 */
	public Integer getState() {
		return state;
	}

	public String getMemo() {
		return memo;
	}

}

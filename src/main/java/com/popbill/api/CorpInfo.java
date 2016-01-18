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
 * Class for Corporation Information.
 * 
 * @author JeongYohan
 * @version 1.0.0
 */

public class CorpInfo {
	private String ceoname;
	private String corpName;
	private String addr;
	private String bizType;
	private String bizClass;
	
	/**
	 * 대표자성명 확인
	 * 
	 * @return 대표자성명 
	 */
	public String getCeoname() {
		return ceoname;
	}
	
	/**
	 * 대표자성명 설정 
	 * 
	 * @param ceoname
	 * 		대표자성명, 최대 30자 
	 */
	public void setCeoname(String ceoname) {
		this.ceoname = ceoname;
	}
	
	/**
	 * 상호 확인 
	 * 
	 * @return 상호 
	 */
	public String getCorpName() {
		return corpName;
	}
	
	/**
	 * 상호 설정 
	 * 
	 * @param corpName
	 * 		상호, 최대 70자 
	 */
	public void setCorpName(String corpName) {
		this.corpName = corpName;
	}
	
	/**
	 * 주소 확인 
	 * 
	 * @return 주소 
	 */
	public String getAddr() {
		return addr;
	}
	
	/**
	 * 주소 설정 
	 * 
	 * @param addr
	 * 		주소, 최대 300자 
	 */
	public void setAddr(String addr) {
		this.addr = addr;
	}
	
	/**
	 * 업태 확인 
	 * 
	 * @return 업태 
	 */
	public String getBizType() {
		return bizType;
	}
	
	/**
	 * 업태 설정 
	 * @param bizType
	 * 		업태, 최대길이 40자 
	 */
	public void setBizType(String bizType) {
		this.bizType = bizType;
	}
	
	/**
	 * 업종 확인
	 * 
	 * @return 업종 
	 */
	public String getBizClass() {
		return bizClass;
	}
	
	/**
	 * 업종 설정 
	 * 
	 * @param bizClass
	 * 		업종, 최대 40자 
	 */
	public void setBizClass(String bizClass) {
		this.bizClass = bizClass;
	}
	
}

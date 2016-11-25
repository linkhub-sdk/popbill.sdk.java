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

/**
 * Class for Contactor Information.
 * 
 * @author JeongYohan
 * @version 1.0.0
 */

public class ContactInfo {
	private String id;
	private String pwd;
	private String email;
	private String hp;
	private String personName;
	private Boolean searchAllAllowYN;
	private String tel;
	private String fax;
	private Boolean mgrYN;
	private String regDT;
	
	/**
	 * 담당자 아이디 확인
	 * 
	 * @return 담당자 아이디
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * 담당자 아이디 설정
	 * 
	 * @param id 
	 *		담당자아이디(6자 이상, 20자미만)
	 */	
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * 담당자 비밀번호 설정 
	 * 
	 * @param pwd
	 *		담당자비밀번호 (6자 이상, 20자미만)
	 */	
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	/**
	 * 담당자 이메일 확인
	 * 
	 * @return 담당자 이메일 
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * 담당자 이메일 설정 
	 * 
	 * @param email 
	 *		담당자 이메일
	 */	
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * 담당자 휴대폰번호 확인 
	 * 
	 * @return 담당자 휴대폰번호 
	 */
	public String getHp() {
		return hp;
	}
	
	/**
	 * 담당자 휴대폰번호 설정  
	 * 
	 * @param hp 
	 * 		담당자 휴대폰번호 
	 */
	public void setHp(String hp) {
		this.hp = hp;
	}
	
	/**
	 * 담당자명 확인 
	 * 
	 * @return 담당자명 
	 */
	public String getPersonName() {
		return personName;
	}
	
	/**
	 * 담당자명 설정 
	 * 
	 * @param personName 
	 * 			담당자명 
	 */
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	
	/**
	 * 회사조회 권한 확인 
	 * 
	 * @return 회사조회 권한여부  
	 */
	public Boolean getSearchAllAllowYN() {
		return searchAllAllowYN;
	}
	
	/**
	 * 회사조회 권한 설정 
	 * 
	 * @param searchAllAllowYN
	 * 			회사조회 권한여부  
	 */
	public void setSearchAllAllowYN(Boolean searchAllAllowYN) {
		this.searchAllAllowYN = searchAllAllowYN;
	}
	
	/**
	 * 담당자 연락처 확인 
	 * 
	 * @return 담당자 연락처 
	 */
	public String getTel() {
		return tel;
	}
	
	/**
	 * 담당자 연락처 설정 
	 * 
	 * @param tel
	 * 		담당자 연락처 
	 */
	public void setTel(String tel) {
		this.tel = tel;
	}
	
	/**
	 * 담당자 팩스번호 확인 
	 * 
	 * @return 담당자 팩스번호 
	 */
	public String getFax() {
		return fax;
	}
	
	/**
	 * 담당자 팩스번호 설정 
	 * 
	 * @param fax
	 * 		담당자 팩스번호 
	 */
	public void setFax(String fax) {
		this.fax = fax;
	}
	
	/**
	 * 관리자 권한여부 확인 
	 * 
	 * @return 관리자 권한여부  
	 */
	public Boolean getMgrYN() {
		return mgrYN;
	}

	/**
	 * 관리자 권한여부 설정 
	 * 
	 * @param mgrYN
	 * 		관리자 권한여부
	 */
	public void setMgrYN(Boolean mgrYN) {
		this.mgrYN = mgrYN;
	}
	
	/**
	 * 담당자 등록시 확인 
	 * 
	 * @return 담당자 등록일시 
	 */
	public String getRegDT() {
		return regDT;
	}

	/**
	 * 
	 * @return
	 */
	public String getPwd() {
		return pwd;
	}	
}

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
 * CloseDown Service Interface.
 * 
 * @author KimSeongjun
 * @version 1.0.0
 */
public interface CloseDownService extends BaseService {

	/**
	 * 회원의 휴폐업 조회단가 확인
	 * 
	 * @param CorpNum
	 *            연동회원 사업자번호
	 * @return 단가 (ex. 55.0)
	 * @throws PopbillException
	 */
	public float getUnitCost(String CorpNum) throws PopbillException;

	/**
	 * 휴폐업상태 조회
	 * @param MemberCorpNum 연동회원 사업자번호
	 * @param CheckCorpNum 휴폐업을 조회하고자 하는 사업자번호
	 * @return 휴폐업상태정보
	 */
	CorpState CheckCorpNum(String MemberCorpNum, String CheckCorpNum) throws PopbillException;	
	
	/**
	 * 휴폐업상태 다량 조회
	 * @param MemberCorpNum 연동회원 사업자번호
	 * @param CorpNumList 조회하고자 하는 사업자번호 목록.
	 * @return 휴폐업상태정보 목록
	 */
	CorpState[] CheckCorpNum(String MemberCorpNum, String[] CorpNumList) throws PopbillException;
}

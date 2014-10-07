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
 * Popbill BaserService Interface.
 * 
 * @author KimSeongjun
 * @version 1.0.0
 */
public interface BaseService {

	/**
	 * 연동회원 잔여 포인트 확인.
	 * 
	 * @param CorpNum
	 *            Member's CorpNum
	 * @return Member's RemainPoint
	 * @throws PopbillException
	 */
	public abstract double getBalance(String CorpNum) throws PopbillException;

	/**
	 * 연동 파트너의 잔여포인트 확인
	 * 
	 * @param CorpNum
	 *            Member's CorpNum
	 * @return Partner's RemainPoint
	 * @throws PopbillException
	 */
	public abstract double getPartnerBalance(String CorpNum)
			throws PopbillException;

	/**
	 * 연동회원 가입요청.
	 * 
	 * @param joinInfo
	 *            Join Information. see JoinForm
	 * @return Response of Join Process.
	 * @throws PopbillException
	 * @see com.popbill.api.JoinForm
	 */
	public abstract Response joinMember(JoinForm joinInfo)
			throws PopbillException;

	/**
	 * 팝빌의 SSO 접근 URL 요청.
	 * 
	 * @param CorpNum
	 *            Member's CorpNum
	 * @param UserID
	 *            UserID to login.
	 * @param TOGO
	 *            Word to wanna go.
	 * @return Popbill' url with token.
	 * @throws PopbillException
	 */
	public abstract String getPopbillURL(String CorpNum, String UserID,
			String TOGO) throws PopbillException;

	/**
	 * 연동회원의 가입여부 확인.
	 * 
	 * @param CorpNum
	 *            CorpNum to Check.
	 * @param LinkID
	 *            LinkId to Check.
	 * @return CorpNum is Member or not.
	 * @throws PopbillException
	 */
	public abstract Response checkIsMember(String CorpNum, String LinkID)
			throws PopbillException;

}
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
	 * @param TOGO
	 *            Word to wanna go.
	 * @return Popbill' url with token.
	 * @throws PopbillException
	 */
	public abstract String getPopbillURL(String CorpNum, String TOGO) 
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
	 * 파트너 관리자 팝업 URL 요청
	 * 
	 * @param CorpNum
	 * 			팝빌회원 사업자번호
	 * @param TOGO
	 * 			CHRG(포인트충전), LOGIN(로그인)
	 * @return	파트너관리자 URL
	 * @throws PopbillException
	 */
	public abstract String getPartnerURL(String CorpNum, String TOGO) throws PopbillException;
	

	/**
	 * 연동회원의 가입여부 확인.
	 * 
	 * @param CorpNum
	 *            CorpNum to Check.
	 * @param UserID
	 *            USerID to Check.
	 * @return CorpNum is Member or not.
	 * @throws PopbillException
	 */
	public abstract Response checkIsMember(String CorpNum, String LinkID)
			throws PopbillException;
	
	/**
	 * 담당자 정보확인.
	 * 
	 * @param CorpNum
	 *            CorpNum to Check.
	 * @param ContactID
	 *            ContactID to Check.
	 * @return 
	 * @throws PopbillException
	 */
    public abstract ContactInfo getContactInfo(String CorpNum, String contactID)
    		throws PopbillException;
    
    /**
	 * 담당자 정보확인.
	 * 
	 * @param CorpNum
	 *            CorpNum to Check.
	 * @param ContactID
	 *            ContactID to Check.
	 * @param UserID
	 *            UserID to Check.
	 * @return 
	 * @throws PopbillException
	 */
    public abstract ContactInfo getContactInfo(String CorpNum, String contactID, String UserID)
    		throws PopbillException;
	
	/**
	 * 담당자 목록조회
	 * 
	 * @param CorpNum
	 * 			Member's CorpNum
	 * @return Contact member list
	 * @throws PopbillException
	 */
	public abstract ContactInfo[] listContact(String CorpNum) 
			throws PopbillException;
	
	/**
	 * 담당자 목록조회
	 * 
	 * @param CorpNum
	 * 			Member's CorpNum
	 * @param UserID
	 * 			UserID to Check
	 * @return Contact member list
	 * @throws PopbillException
	 */
	public abstract ContactInfo[] listContact(String CorpNum, String UserID) 
			throws PopbillException;
	
	/**
	 * 담당자 정보수정 
	 * 
	 * @param CorpNum
	 * 			Member's CorpNum
	 * @param ContactInfo
	 * 			Contact's Information. see ContactInfo
	 * @param UserID
	 * 			UserID to Check
	 * @return Contact Info Update success or fail.
	 * @throws PopbillException
	 *  
	 */
	public abstract Response updateContact(String CorpNum, ContactInfo contactInfo, String UserID)
			throws PopbillException;

	/**
	 * 담당차 등록 
	 * 
	 * @param CorpNum
	 * 			Member's CorpNum
	 * @param ContactInfo
	 * 			Contact's Infomation. see ContactInfo
	 * @return Response of RegistContact process.
	 * @thorws PopbillException
	 */
	public abstract Response registContact(String CorpNum, ContactInfo contactInfo)
			throws PopbillException;
	
	/**
	 * 담당차 등록 
	 * 
	 * @param CorpNum
	 * 			Member's CorpNum
	 * @param ContactInfo
	 * 			Contact's Infomation. see ContactInfo
	 * @param UserID
	 * 			Member's UserID
	 * @return Response of RegistContact process.
	 * @thorws PopbillException
	 */
	public abstract Response registContact(String CorpNum, ContactInfo contactInfo, String UserID)
			throws PopbillException;
	
	/**
	 * 팝빌회원 아이디 중복체크 
	 * 
	 * @param id
	 * @return Response of ID is using 
	 * @throws PopbillException
	 */
	public abstract Response checkID(String id)
			throws PopbillException;

	/**
	 * 회사정보 확인
	 * 
	 * @param CorpNum
	 * 			Member's CorpNum
	 * @return CorpInfo. see CorpInfo
	 * @throws PopbillException
	 */
	public abstract CorpInfo getCorpInfo(String CorpNum)
		throws PopbillException;	
	
	/**
	 * 회사정보 확인
	 * 
	 * @param CorpNum
	 * 			Member's CorpNum
	 * @param UserID
	 * 			Member's UserID
	 * @return CorpInfo. see CorpInfo
	 * @throws PopbillException
	 */
	public abstract CorpInfo getCorpInfo(String CorpNum, String UserID)
		throws PopbillException;

	/**
	 * 회사정보 수정 
	 * 
	 * @param CorpNum
	 * 			Member's CorpNum
	 * @param corpInfo
	 * 			CorpInfo. see CorpInfo
	 * @return CorpInfo's Information updating success or not.
	 * @throws PopbillException
	 */
	public abstract Response updateCorpInfo(String CorpNum, CorpInfo corpInfo)
			throws PopbillException;	
	
	/**
	 * 회사정보 수정 
	 * 
	 * @param CorpNum
	 * 			Member's CorpNum
	 * @param corpInfo
	 * 			CorpInfo. see CorpInfo
	 * @param UserID
	 * 			Member's UserID
	 * @return CorpInfo's Information updating success or not.
	 * @throws PopbillException
	 */
	public abstract Response updateCorpInfo(String CorpNum, CorpInfo corpInfo, String UserID)
			throws PopbillException;

	/**
	 *  팝빌 로그인 URL
	 *
	 * @param CorpNum
	 * 			연동회원 사업자번호
	 * @param UserID
	 * 			연동회원 유저아이디
	 * @return 팝빌 URL (AccessToken값 포함. Token값은 응답후 30초까지만 유효함)
	 * @throws PopbillException
	 */
	public abstract String getAccessURL(String CorpNum, String UserID) throws PopbillException;


	/**
	 *  연동회원 포인트충전 팝업 URL
	 *
	 * @param CorpNum
	 * 			연동회원 사업자번호
	 * @param UserID
	 * 			연동회원 유저아이디
	 * @return 팝빌 URL (AccessToken값 포함. Token값은 응답후 30초까지만 유효함)
	 * @throws PopbillException
	 */
	public abstract String getChargeURL(String CorpNum, String UserID) throws PopbillException;

}
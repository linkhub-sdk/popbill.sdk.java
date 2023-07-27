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

import kr.co.linkhub.auth.MemberPointDetail;

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
    
    public abstract MemberPointDetail getPointInfo(String CorpNum) throws PopbillException;

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
     *          팝빌회원 사업자번호
     * @param TOGO
     *          CHRG(포인트충전), LOGIN(로그인)
     * @return  파트너관리자 URL
     * @throws PopbillException
     */
    public abstract String getPartnerURL(String CorpNum, String TOGO) throws PopbillException;
    
    /**
     * 포인트 사용내역 확인
     * 
     * @param CorpNum 사업자번호
     * @param SDate 조회 기간의 시작일자
     * @param EDate 조회 기간의 종료일자
     * @param Page 목록 페이지번호 (기본값 1)
     * @param PerPage 페이지당 표시할 목록 개수 (기본값 500, 최대 1000)
     * @param Order 거래일자를 기준으로 하는 목록 정렬 방향
     * @return
     * @throws PopbillException
     */
    public abstract UseHistoryResult getUseHistory(String CorpNum, String SDate, String EDate, Integer Page, Integer PerPage, String Order) throws PopbillException;
    
    /**
     * 포인트 사용내역 확인
     * 
     * @param CorpNum 사업자번호
     * @param SDate 조회 기간의 시작일자
     * @param EDate 조회 기간의 종료일자
     * @param Page 목록 페이지번호 (기본값 1)
     * @param PerPage 페이지당 표시할 목록 개수 (기본값 500, 최대 1000)
     * @param Order 거래일자를 기준으로 하는 목록 정렬 방향
     * @param UserID 팝빌회원 아이디
     * @return
     * @throws PopbillException
     */
    public abstract UseHistoryResult getUseHistory(String CorpNum, String SDate, String EDate, Integer Page, Integer PerPage, String Order, String UserID) throws PopbillException;

    /**
     * 포인트 결제내역 확인.
     * 
     * @param CorpNum 사업자번호
     * @param SDate 조회 기간의 시작일자
     * @param EDate 조회 기간의 종료일자
     * @param Page 목록 페이지번호 (기본값 1)
     * @param PerPage 페이지당 표시할 목록 개수 (기본값 500, 최대 1000)
     * @return
     * @throws PopbillException
     */
    public abstract PaymentHistoryResult getPaymentHistory(String CorpNum, String SDate, String EDate, Integer Page, Integer PerPage) throws PopbillException;

    /**
     * 포인트 결제내역 확인.
     * 
     * @param CorpNum 사업자번호
     * @param SDate 조회 기간의 시작일자
     * @param EDate 조회 기간의 종료일자
     * @param Page 목록 페이지번호 (기본값 1)
     * @param PerPage 페이지당 표시할 목록 개수 (기본값 500, 최대 1000)
     * @param UserID 팝빌회원 아이디
     * @return
     * @throws PopbillException
     */
    public abstract PaymentHistoryResult getPaymentHistory(String CorpNum, String SDate, String EDate, Integer Page, Integer PerPage, String UserID) throws PopbillException;

    /**
     * 환분신청 내역 확인.
     * 
     * @param CorpNum 사업자번호
     * @param Page 목록 페이지번호 (기본값 1)
     * @param PerPage 페이지당 표시할 목록 개수 (기본값 500, 최대 1000)
     * @return
     * @throws PopbillException
     */
    public abstract RefundHistoryResult getRefundHistory(String CorpNum, Integer Page, Integer PerPage) throws PopbillException;

    /**
     * 환분신청 내역 확인.
     * 
     * @param CorpNum 사업자번호
     * @param Page 목록 페이지번호 (기본값 1)
     * @param PerPage 페이지당 표시할 목록 개수 (기본값 500, 최대 1000)
     * @param UserID 팝빌회원 아이디
     * @return
     * @throws PopbillException
     */
    public abstract RefundHistoryResult getRefundHistory(String CorpNum, Integer Page, Integer PerPage, String UserID) throws PopbillException;

    /**
     * 환불 신청.
     * 
     * @param CorpNum 사업자번호
     * @param refundForm 환불 신청 정보
     * @return Response
     * @throws PopbillException
     */
    public abstract RefundResponse refund(String CorpNum, RefundForm refundForm) throws PopbillException;

    /**
     * 환불 신청.
     * 
     * @param CorpNum 사업자번호
     * @param refundForm 환불 신청 정보
     * @param UserID 팝빌회원 아이디
     * @return Response
     * @throws PopbillException
     */
    public abstract RefundResponse refund(String CorpNum, RefundForm refundForm, String UserID) throws PopbillException;
    
    
    /**
     * 환불신청 상태확인
     * 
     * @param CorpNum 사업자번호 
     * @param RefundCode 환불코드
     * @return RefundHistory
     * @throws PopbillException
     */
    public abstract RefundHistory getRefundInfo(String CorpNum, String RefundCode) throws PopbillException;
    
    /**
     * 환불신청 상태확인
     * 
     * @param CorpNum 사업자번호 
     * @param RefundCode 환불코드 
     * @param UserID 회원아이디
     * @return RefundHistory
     * @throws PopbillException
     */
    public abstract RefundHistory getRefundInfo(String CorpNum, String RefundCode, String UserID) throws PopbillException;
    /**
     * 무통장 입금신청.
     * 
     * @param CorpNum 사업자번호
     * @param paymentForm 무통장입금 신청 정보
     * @return Payment
     * @throws PopbillException
     */
    public abstract PaymentResponse paymentRequest(String CorpNum, PaymentForm paymentForm) throws PopbillException;

    /**
     * 무통장 입금신청.
     * 
     * @param CorpNum 사업자번호
     * @param paymentForm 무통장입금 신청 정보
     * @param UserID 팝빌회원 아이디
     * @return Payment
     * @throws PopbillException
     */
    public abstract PaymentResponse paymentRequest(String CorpNum, PaymentForm paymentForm, String UserID) throws PopbillException;

    /**
     * 무통장 입금신청 정보확인.
     * 
     * @param CorpNum 사업자번호
     * @param settleCode 정산코드
     * @return SettleResult
     * @throws PopbillException
     */
    public abstract PaymentHistory getSettleResult(String CorpNum, String settleCode) throws PopbillException;

    /**
     * 무통장 입금신청 정보확인.
     * 
     * @param CorpNum 사업자번호
     * @param settleCode 정산코드
     * @param UserID 팝빌회원 아이디
     * @return SettleResult
     * @throws PopbillException
     */
    public abstract PaymentHistory getSettleResult(String CorpNum, String settleCode, String UserID) throws PopbillException;
    
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
     *          Member's CorpNum
     * @return Contact member list
     * @throws PopbillException
     */
    public abstract ContactInfo[] listContact(String CorpNum) 
            throws PopbillException;
    
    /**
     * 담당자 목록조회
     * 
     * @param CorpNum
     *          Member's CorpNum
     * @param UserID
     *          UserID to Check
     * @return Contact member list
     * @throws PopbillException
     */
    public abstract ContactInfo[] listContact(String CorpNum, String UserID) 
            throws PopbillException;
    
    /**
     * 담당자 정보수정 
     * 
     * @param CorpNum
     *          Member's CorpNum
     * @param ContactInfo
     *          Contact's Information. see ContactInfo
     * @param UserID
     *          UserID to Check
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
     *          Member's CorpNum
     * @param ContactInfo
     *          Contact's Infomation. see ContactInfo
     * @return Response of RegistContact process.
     * @thorws PopbillException
     */
    public abstract Response registContact(String CorpNum, ContactInfo contactInfo)
            throws PopbillException;
    
    /**
     * 담당차 등록 
     * 
     * @param CorpNum
     *          Member's CorpNum
     * @param ContactInfo
     *          Contact's Infomation. see ContactInfo
     * @param UserID
     *          Member's UserID
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
     *          Member's CorpNum
     * @return CorpInfo. see CorpInfo
     * @throws PopbillException
     */
    public abstract CorpInfo getCorpInfo(String CorpNum)
        throws PopbillException;    
    
    /**
     * 회사정보 확인
     * 
     * @param CorpNum
     *          Member's CorpNum
     * @param UserID
     *          Member's UserID
     * @return CorpInfo. see CorpInfo
     * @throws PopbillException
     */
    public abstract CorpInfo getCorpInfo(String CorpNum, String UserID)
        throws PopbillException;

    /**
     * 회사정보 수정 
     * 
     * @param CorpNum
     *          Member's CorpNum
     * @param corpInfo
     *          CorpInfo. see CorpInfo
     * @return CorpInfo's Information updating success or not.
     * @throws PopbillException
     */
    public abstract Response updateCorpInfo(String CorpNum, CorpInfo corpInfo)
            throws PopbillException;    
    
    /**
     * 회사정보 수정 
     * 
     * @param CorpNum
     *          Member's CorpNum
     * @param corpInfo
     *          CorpInfo. see CorpInfo
     * @param UserID
     *          Member's UserID
     * @return CorpInfo's Information updating success or not.
     * @throws PopbillException
     */
    public abstract Response updateCorpInfo(String CorpNum, CorpInfo corpInfo, String UserID)
            throws PopbillException;

    /**
     *  팝빌 로그인 URL
     *
     * @param CorpNum
     *          연동회원 사업자번호
     * @param UserID
     *          연동회원 유저아이디
     * @return 팝빌 URL (AccessToken값 포함. Token값은 응답후 30초까지만 유효함)
     * @throws PopbillException
     */
    public abstract String getAccessURL(String CorpNum, String UserID) throws PopbillException;


    /**
     *  연동회원 포인트충전 팝업 URL
     *
     * @param CorpNum
     *          연동회원 사업자번호
     * @param UserID
     *          연동회원 유저아이디
     * @return 팝빌 URL (AccessToken값 포함. Token값은 응답후 30초까지만 유효함)
     * @throws PopbillException
     */
    public abstract String getChargeURL(String CorpNum, String UserID) throws PopbillException;
    
    /**
     *  연동회원 포인트 결재내역 팝업 URL
     *
     * @param CorpNum
     *          연동회원 사업자번호
     * @param UserID
     *          연동회원 유저아이디
     * @return 팝빌 URL (AccessToken값 포함. Token값은 응답후 30초까지만 유효함)
     * @throws PopbillException
     */
    public abstract String getPaymentURL(String CorpNum, String UserID) throws PopbillException;
    
    /**
     *  연동회원 포인트 사용내역 팝업 URL
     *
     * @param CorpNum
     *          연동회원 사업자번호
     * @param UserID
     *          연동회원 유저아이디
     * @return 팝빌 URL (AccessToken값 포함. Token값은 응답후 30초까지만 유효함)
     * @throws PopbillException
     */
    public abstract String getUseHistoryURL(String CorpNum, String UserID) throws PopbillException;
    
    /**
     *  회원탈퇴
     * @param CorpNum 연동회원 사업자번호
     * @param quitReason 탈퇴사유
     * @return 
     * @throws PopbillException
     */
    public abstract Response quitMember(String CorpNum, String quitReason) throws PopbillException;
    
    /**
     * 회원탈퇴
     * @param CorpNum 연동회원 사업자번호
     * @param quitReason 탈퇴사유
     * @param UserID 연동회원 아이디
     * @return
     * @throws PopbillException
     */
    public abstract Response quitMember(String CorpNum, String quitReason, String UserID) throws PopbillException;

    /**
     * 환불가능포인트 확인
     *  
     * @param CorpNum 연동회원 사업자번호
     * @return
     * @throws PopbillException
     */
    public abstract double getRefundableBalance(String CorpNum) throws PopbillException;
    
    /**
     * 환불가능포인트 확인
     * 
     * @param CorpNum 연동회원 사업자번호
     * @return
     * @throws PopbillException
     */
	public abstract double getRefundableBalance(String CorpNum, String UserID) throws PopbillException;

	
}
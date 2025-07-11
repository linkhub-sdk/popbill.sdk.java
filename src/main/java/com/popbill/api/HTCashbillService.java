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

import java.util.Date;

import com.popbill.api.hometax.HTCashbillJobState;
import com.popbill.api.hometax.HTCashbillSearchResult;
import com.popbill.api.hometax.HTCashbillSummary;
import com.popbill.api.hometax.QueryType;

/**
 * Hometax Cashbill Service Interface
 * 
 * @author JeongYoHan
 * @version 1.0.0
 *
 */
public interface HTCashbillService extends BaseService{

    /**
     * 과금정보 확인 
     * 
     * @param CorpNum
     *          팝빌회원 사업자번호
     * @return 과금정보 (see. com.popbill.api.ChargeInfo)
     * @throws PopbillException
     */
    public ChargeInfo getChargeInfo(String CorpNum) throws PopbillException;

    /**
     * 과금정보 확인
     *
     * @param CorpNum
     *          팝빌회원 사업자번호
     * @param UserID
     *          팝빌회원 아이디
     * @return 과금정보 (see. com.popbill.api.ChargeInfo)
     * @throws PopbillException
     */
    public ChargeInfo getChargeInfo(String CorpNum, String UserID) throws PopbillException;
    
    /**
     * 수집 요청 
     * 
     * @param CorpNum
     *          팝빌회원 사업자번호
     * @param QueryType
     *          현금영수증 유형, SELL-매출 현금영수증, BUY-매입 현금영수증 
     * @param SDate
     *          시작일자 (yyyyMMdd)
     * @param EDate
     *          종료일자 (yyyyMMdd)
     * @return 작업아이디 (jobID)
     * @throws PopbillException
     */
    public String requestJob(String CorpNum, QueryType QueryType, String SDate, String EDate) throws PopbillException;
    
    
    /**
     * 수집 요청 
     * 
     * @param CorpNum
     *          팝빌회원 사업자번호
     * @param QueryType
     *          현금영수증 유형, SELL-매출 현금영수증, BUY-매입 현금영수증 
     * @param SDate
     *          시작일자 (yyyyMMdd)
     * @param EDate
     *          종료일자 (yyyyMMdd)
     * @param UserID
     *          팝빌회원 아이디
     * @return 작업아이디 (jobID)
     * @throws PopbillException
     */
    public String requestJob(String CorpNum, QueryType QueryType, String SDate, String EDate, String UserID) throws PopbillException;
    
    
    /**
     * 수집 상태 확인 
     * 
     * @param CorpNum
     *          팝빌회원 사업자번호
     * @param JobID
     *          수집요청시 반환받은 작업아이디 
     * @return 수집 상태 (see. com.popbill.api.htcashbill.HTCashbillJobState)
     * @throws PopbillException
     */
    public HTCashbillJobState getJobState(String CorpNum, String JobID) throws PopbillException;
    
    /**
     * 수집 상태 확인 
     * 
     * @param CorpNum
     *          팝빌회원 사업자번호
     * @param JobID
     *          수집요청시 반환받은 작업아이디 
     * @param UserID
     *          팝빌회원 아이디
     * @return 수집 상태 (see. com.popbill.api.htcashbill.HTCashbillJobState)
     * @throws PopbillException
     */
    public HTCashbillJobState getJobState(String CorpNum, String JobID, String UserID) throws PopbillException;
    
    /**
     * 수집 상태 목록 확인 
     * 
     * @param CorpNum
     *          팝빌회원 사업자번호
     * @return 수집상태 배열 (see. com.popbill.api.htcashbill.HTCashbillJobState)
     * @throws PopbillException
     */
    public HTCashbillJobState[] listActiveJob(String CorpNum) throws PopbillException;
    
    /**
     * 수집 상태 목록 확인 
     * 
     * @param CorpNum
     *          팝빌회원 사업자번호
     * @param UserID 
     *          팝빌회원 아이디
     * @return 수집상태 배열 (see. com.popbill.api.htcashbill.HTCashbillJobState)
     * @throws PopbillException
     */
    public HTCashbillJobState[] listActiveJob(String CorpNum, String UserID) throws PopbillException;
    
    /**
     * 수집 결과 조회 
     * 
     * @param CorpNum
     *          팝빌회원 사업자번호
     * @param JobID
     *          작업 아이디 
     * @param TradeUsage
     *          거래용도 배열, P-소득공제용, C-지출증빙용
     * @param TradeType
     *          현금영수증 형태 배열, N-일반현금영수증, C-취소현금영수증
     * @param Page
     *          페이지 번호 
     * @param PerPage
     *          페이지당 검색개수
     * @param Order
     *          정렬방향 
     * @return 수집 결과 (see. com.popbill.api.htcashbill.HTCashbillSearchResult)
     * @throws PopbillException
     */
    public HTCashbillSearchResult search(String CorpNum, String JobID, String[] TradeUsage, 
            String[] TradeType, Integer Page, Integer PerPage, String Order) throws PopbillException;
    
    /**
     * 수집 결과 조회 
     * 
     * @param CorpNum
     *          팝빌회원 사업자번호
     * @param JobID
     *          작업 아이디 
     * @param TradeUsage
     *          거래용도 배열, P-소득공제용, C-지출증빙용
     * @param TradeType
     *          현금영수증 형태 배열, N-일반현금영수증, C-취소현금영수증
     * @param Page
     *          페이지 번호 
     * @param PerPage
     *          페이지당 검색개수
     * @param Order
     *          정렬방향 
     * @param UserID
     *          팝빌회원 아이디
     * @return 수집 결과 (see. com.popbill.api.htcashbill.HTCashbillSearchResult)
     * @throws PopbillException
     */
    public HTCashbillSearchResult search(String CorpNum, String JobID, String[] TradeUsage, 
            String[] TradeType, Integer Page, Integer PerPage, String Order, String UserID) throws PopbillException;
    
    /**
     * 수집 결과 요약정보 조회 
     * 
     * @param CorpNum
     *          팝빌회원 사업자번호
     * @param JobID
     *          작업 아이디 
     * @param TradeUsage
     *          거래용도 배열, P-소득공제용, C-지출증빙용
     * @param TradeType
     *          현금영수증 형태 배열, N-일반현금영수증, C-취소현금영수증
     * @return 수집 결과 (see. com.popbill.api.htcashbil.HTCashbillSummary)
     * @throws PopbillException
     */
    public HTCashbillSummary summary(String CorpNum, String JobID, String[] TradeUsage, 
            String[] TradeType) throws PopbillException;
    
    /**
     * 수집 결과 요약정보 조회 
     * 
     * @param CorpNum
     *          팝빌회원 사업자번호
     * @param JobID
     *          작업 아이디 
     * @param TradeUsage
     *          거래용도 배열, P-소득공제용, C-지출증빙용
     * @param TradeType
     *          현금영수증 형태 배열, N-일반현금영수증, C-취소현금영수증
     * @param UserID
     *          팝빌회원 아이디
     * @return 수집 결과 (see. com.popbill.api.htcashbil.HTCashbillSummary)
     * @throws PopbillException
     */
    public HTCashbillSummary summary(String CorpNum, String JobID, String[] TradeUsage, 
            String[] TradeType, String UserID) throws PopbillException;
    
    /**
     * 정액제 신청 URL
     * 
     * @param CorpNum
     *          팝빌회원 사업자번호
     * @return 정액제 신청 url
     * @throws PopbillException
     */
    public String getFlatRatePopUpURL(String CorpNum)
            throws PopbillException;
    
    /**
     * 정액제 신청 URL
     * 
     * @param CorpNum
     *          팝빌회원 사업자번호
     * @param UserID
     *          팝빌회원 아이디
     * @return 정액제 신청 url
     * @throws PopbillException
     */
    public String getFlatRatePopUpURL(String CorpNum, String UserID)
            throws PopbillException;
    
    /**
     * 홈택스 현금영수증 공인인증서 등록 URL
     * 
     * @param CorpNum
     *          팝빌회원 사업자번호
     * @return 홈택스 공인인증서 등록 URL
     * @throws PopbillException
     */
    public String getCertificatePopUpURL(String CorpNum)
            throws PopbillException;
    
    /**
     * 홈택스 현금영수증 공인인증서 등록 URL
     * 
     * @param CorpNum
     *          팝빌회원 사업자번호
     * @param UserID
     *          팝빌회원 아이디
     * @return 홈택스 공인인증서 등록 URL
     * @throws PopbillException
     */
    public String getCertificatePopUpURL(String CorpNum, String UserID)
            throws PopbillException;
    
    /**
     * 정액제 서비스 상태 확인 
     * 
     * @param CorpNum
     *          팝빌회원 사업자번호
     * @return 정액제 서비스 상태 (see. com.popbill.api.FlatRateState)
     * @throws PopbillException
     */
    public FlatRateState getFlatRateState(String CorpNum) throws PopbillException;
    
    /**
     * 정액제 서비스 상태 확인 
     * 
     * @param CorpNum
     *          팝빌회원 사업자번호
     * @param UserID
     *          팝빌회원 아이디
     * @return 정액제 서비스 상태 (see. com.popbill.api.FlatRateState)
     * @throws PopbillException
     */
    public FlatRateState getFlatRateState(String CorpNum, String UserID) throws PopbillException;
    
    /**
     * 홈택스연계 공인인증서 만료일시 확인 
     * 
     * @param CorpNum
     *          팝빌회원 사업자번호
     * @return 만료일시
     * @throws PopbillException
     */
    public Date getCertificateExpireDate(String CorpNum) throws PopbillException;

    /**
     * 홈택스연계 공인인증서 만료일시 확인
     *
     * @param CorpNum
     *          팝빌회원 사업자번호
     * @param UserID
     *          팝빌회원 아이디
     * @return 만료일시
     * @throws PopbillException
     */
    public Date getCertificateExpireDate(String CorpNum, String UserID) throws PopbillException;

    /**
     * 홈택스 공인인증서 로그인 테스트
     * 
     * @param CorpNum
     *          팝빌회원 사업자번호
     * @return Response
     * @throws PopbillException
     */
    public Response checkCertValidation(String CorpNum) throws PopbillException;

    /**
     * 홈택스 공인인증서 로그인 테스트
     *
     * @param CorpNum
     *          팝빌회원 사업자번호
     * @param UserID
     *          팝빌회원 아이디
     * @return Response
     * @throws PopbillException
     */
    public Response checkCertValidation(String CorpNum, String UserID) throws PopbillException;
    
    /**
     * 부서사용자 계정등록
     * 
     * @param CorpNum
     *          팝빌회원 사업자번호
     * @param DeptUserID
     *          홈택스 부서사용자 계정 아이디
     * @param DeptUserPWD
     *          홈택스 부서사용자 계정 비밀번호
     * @return Response
     * @throws PopbillException
     */
    public Response registDeptUser(String CorpNum, String DeptUserID, String DeptUserPWD) throws PopbillException;

    /**
     * 부서사용자 계정등록
     *
     * @param CorpNum
     *          팝빌회원 사업자번호
     * @param DeptUserID
     *          홈택스 부서사용자 계정 아이디
     * @param DeptUserPWD
     *          홈택스 부서사용자 계정 비밀번호
     * @param UserID
     *          팝빌회원 아이디
     * @return Response
     * @throws PopbillException
     */
    public Response registDeptUser(String CorpNum, String DeptUserID, String DeptUserPWD, String UserID) throws PopbillException;

    /**
     * 부서사용자 등록정보 확인
     * 
     * @param CorpNum
     *          팝빌회원 사업자번호
     * @return Response
     * @throws PopbillException
     */
    public Response checkDeptUser(String CorpNum) throws PopbillException;

    /**
     * 부서사용자 등록정보 확인
     *
     * @param CorpNum
     *          팝빌회원 사업자번호
     * @param UserID
     *          팝빌회원 아이디
     * @return Response
     * @throws PopbillException
     */
    public Response checkDeptUser(String CorpNum, String UserID) throws PopbillException;
    
    /**
     * 부서사용자 로그인 테스트
     * 
     * @param CorpNum
     *          팝빌회원 사업자번호
     * @return Response
     * @throws PopbillException
     */
    public Response checkLoginDeptUser(String CorpNum) throws PopbillException;

    /**
     * 부서사용자 로그인 테스트
     *
     * @param CorpNum
     *          팝빌회원 사업자번호
     * @param UserID
     *          팝빌회원 아이디
     * @return Response
     * @throws PopbillException
     */
    public Response checkLoginDeptUser(String CorpNum, String UserID) throws PopbillException;
    
    /**
     * 부서사용자 등록정보 삭제
     * 
     * @param CorpNum
     *          팝빌회원 사업자번호
     * @return Response
     * @throws PopbillException
     */
    public Response deleteDeptUser(String CorpNum) throws PopbillException;

    /**
     * 부서사용자 등록정보 삭제
     *
     * @param CorpNum 팝빌회원 사업자번호
     * @param UserID 팝빌회원 아이디
     * @return Response
     * @throws PopbillException
     */
    public Response deleteDeptUser(String CorpNum, String UserID) throws PopbillException;
}
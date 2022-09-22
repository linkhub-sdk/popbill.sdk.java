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
 * BizInfoCheck Service Interface.
 * 
 * @author shchoi
 * @version 1.0.0
 */
public interface BizInfoCheckService extends BaseService {

    /**
     * 회원의 기업정보 조회단가 확인
     * 
     * @param CorpNum
     *          연동회원 사업자번호
     * @return 단가 (ex. 55.0)
     * @throws PopbillException
     */
    public float getUnitCost(String CorpNum) throws PopbillException;

    /**
     * 기업정보 조회
     * 
     * @param MemberCorpNum
     *          연동회원 사업자번호
     * @param CheckCorpNum
     *          휴폐업을 조회하고자 하는 사업자번호
     * @return 기업정보정보
     */
    BizInfoCheck CheckBizInfo(String MemberCorpNum, String CheckCorpNum) throws PopbillException;
    
    /**
     * 기업정보 조회
     * 
     * @param MemberCorpNum
     *          연동회원 사업자번호
     * @param CheckCorpNum
     *          휴폐업을 조회하고자 하는 사업자번호
     * @param UserID
     *          연동회원아이디
     * @return 기업정보정보
     */
    BizInfoCheck CheckBizInfo(String MemberCorpNum, String CheckCorpNum, String UserId) throws PopbillException;

    /**
     * 과금정보 확인
     * 
     * @param CorpNum
     *          연동회원 사업자번호
     * @return 과금정보 (see. com.popbill.api.ChargeInfo)
     * @throws PopbillException
     */
    public ChargeInfo getChargeInfo(String CorpNum) throws PopbillException;
}

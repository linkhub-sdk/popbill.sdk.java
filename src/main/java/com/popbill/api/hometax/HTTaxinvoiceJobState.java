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
package com.popbill.api.hometax;

/**
 * Class for HomeTax Taxinvoice Job State
 * 
 * @author JeongYoHan
 * @version 1.0.0
 */
public class HTTaxinvoiceJobState {
    private String jobID;
    private String jobState;
    private String queryType;
    private String queryDateType;
    private String queryStDate;
    private String queryEnDate;
    private long errorCode;
    private String errorReason;
    private String jobStartDT;
    private String jobEndDT;
    private Integer collectCount;
    private String regDT;

    /**
     * 작업아이디 확인
     * 
     * @return 작업아이디
     */
    public String getJobID() {
        return jobID;
    }

    /**
     * 수집상태 확인
     * 
     * @return 1-대기, 2-진행, 3-완료
     */
    public String getJobState() {
        return jobState;
    }

    /**
     * 수집유형 확인
     * 
     * @return "매출", "매입", "수탁" 중 반환
     */
    public String getQueryType() {
        return queryType;
    }

    /**
     * 일자유형 확인
     * 
     * @return "WriteDate"-작성일자, "IssueDate"-발행일자, "SendDate"-전송일자 중 반환
     */
    public String getQueryDateType() {
        return queryDateType;
    }

    /**
     * 시작일자 확인
     * 
     * @return 시작일자 (yyyyMMdd)
     */
    public String getQueryStDate() {
        return queryStDate;
    }

    /**
     * 종료일자 확인
     * 
     * @return 종료일자 (yyyyMMdd)
     */
    public String getQueryEnDate() {
        return queryEnDate;
    }

    /**
     * 오류코드 확인
     * 
     * @return 1-성공 기타-오류코드 반환
     */
    public long getErrorCode() {
        return errorCode;
    }

    /**
     * 오류메시지 확인
     * 
     * @return 오류메시지
     */
    public String getErrorReason() {
        return errorReason;
    }

    /**
     * 수집작업 시작일시 확인
     * 
     * @return 수집작업 시작일시 (yyyyMMddHHmmss)
     */
    public String getJobStartDT() {
        return jobStartDT;
    }

    /**
     * 수집작업 종료일시 확인
     * 
     * @return 수집작업 종료일시 (yyyyMMddHHmmss)
     */
    public String getJobEndDT() {
        return jobEndDT;
    }

    /**
     * 수집개수 확인
     * 
     * @return 수집개수
     */
    public Integer getCollectCount() {
        return collectCount;
    }

    /**
     * 수집 요청일시 확인
     * 
     * @return 수집요청일시 (yyyyMMddHHmmss)
     */
    public String getRegDT() {
        return regDT;
    }

}

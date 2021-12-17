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

import java.io.Serializable;
import java.util.List;

public class HTCashbillSearchResult implements Serializable {
    private static final long serialVersionUID = 5690643844058117725L;

    private long code;
    private long total;
    private long perPage;
    private long pageNum;
    private long pageCount;
    private String message;
    private List<HTCashbill> list;

    /**
     * 응답코드 확인
     * 
     * @return 응답코드
     */
    public long getCode() {
        return code;
    }

    /**
     * 총 검색결과 건수 확인
     * 
     * @return 총 검색결과 건수
     */
    public long getTotal() {
        return total;
    }

    /**
     * 페이지당 검색 건수 확인
     * 
     * @return 페이지당 검색 건수
     */
    public long getPerPage() {
        return perPage;
    }

    /**
     * 페이지 번호 확인
     * 
     * @return 페이지 번호
     */
    public long getPageNum() {
        return pageNum;
    }

    /**
     * 페이지 개수 확인
     * 
     * @return 페이지 개수
     */
    public long getPageCount() {
        return pageCount;
    }

    /**
     * 응답 메시지 확인
     * 
     * @return 응답 메시지
     */
    public String getMessage() {
        return message;
    }

    /**
     * 전자(세금)계산서 요약 정보 확인
     * 
     * @return 전자(세금)계산서 요약 정보
     */
    public List<HTCashbill> getList() {
        return list;
    }

}

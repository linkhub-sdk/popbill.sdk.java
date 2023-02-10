package com.popbill.api.fax;

import java.io.Serializable;
import java.util.List;

/**
 * Class for FAX Search Information.
 * 
 * @author JeongYohan
 * @version 1.0.0
 */

public class FAXSearchResult implements Serializable {
    private static final long serialVersionUID = 5237615431260290014L;

    private String code;
    private String total;
    private String perPage;
    private String pageNum;
    private String pageCount;
    private String message;

    private List<FaxResult> list;

    /**
     * 응답코드 확인
     * 
     * @return 응답코드
     */
    public String getCode() {
        return code;
    }

    /**
     * 전체 검색개수 확인
     * 
     * @return 전체 검색개수
     */
    public String getTotal() {
        return total;
    }

    /**
     * 페이지당 목록개수 확인
     * 
     * @return 페이지당 목록개수
     */
    public String getPerPage() {
        return perPage;
    }

    /**
     * 페이지번호 확인
     * 
     * @return 페이지번호
     */
    public String getPageNum() {
        return pageNum;
    }

    /**
     * 전체 페이지수 확인
     * 
     * @return 전체 페이지수
     */
    public String getPageCount() {
        return pageCount;
    }

    /**
     * 응답메시지 확인
     * 
     * @return 응답메시지
     */
    public String getMessage() {
        return message;
    }

    /**
     * 팩스전송내역 리스트 확인
     * 
     * @return 팩스전송내역 리스트
     */
    public List<FaxResult> getList() {
        return list;
    }
}

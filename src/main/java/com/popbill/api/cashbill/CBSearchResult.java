package com.popbill.api.cashbill;

import java.io.Serializable;
import java.util.List;

/**
 * Class for Statement Search Information.
 * 
 * @author JeongYohan
 * @version 1.0.0
 */

public class CBSearchResult implements Serializable {
    private static final long serialVersionUID = 2412169938369808210L;

    private String code;
    private String total;
    private String perPage;
    private String pageNum;
    private String pageCount;
    private String message;

    private List<CashbillInfo> list;

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
     * 페이지 개수 확인
     * 
     * @return 패이지 개수
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
     * 현금영수증정보 리스트 확인
     * 
     * @return 현금영수증정보 리스트
     */
    public List<CashbillInfo> getList() {
        return list;
    }

}

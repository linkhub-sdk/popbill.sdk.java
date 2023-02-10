package com.popbill.api.statement;

import java.io.Serializable;
import java.util.List;

/**
 * Class for Statement Search Information.
 * 
 * @author JeongYohan
 * @version 1.0.0
 */
public class StmtSearchResult implements Serializable {
    private static final long serialVersionUID = 6031168488060894405L;

    private String code;
    private String total;
    private String perPage;
    private String pageNum;
    private String pageCount;
    private String message;

    private List<StatementInfo> list;

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
     * 페이지개수 확인
     * 
     * @return 페이지개수
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
     * 전자명세서정보 리스트 확인
     * 
     * @return 전자명세서 정보리스트
     */
    public List<StatementInfo> getList() {
        return list;
    }
}

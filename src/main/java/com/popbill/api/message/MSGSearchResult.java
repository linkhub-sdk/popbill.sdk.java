package com.popbill.api.message;

import java.io.Serializable;
import java.util.List;

/**
 * Class for Message Search Information.
 * 
 * @author JeongYohan
 * @version 1.0.0
 */
public class MSGSearchResult implements Serializable {

    private static final long serialVersionUID = 2108299769673244915L;

    private String code;
    private String total;
    private String perPage;
    private String pageNum;
    private String pageCount;
    private String message;

    private List<SentMessage> list;

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
     * @return 페이지 번호
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
     * 문자전송내역 목록 확인
     * 
     * @return 문자전송내역 목록
     */
    public List<SentMessage> getList() {
        return list;
    }

}

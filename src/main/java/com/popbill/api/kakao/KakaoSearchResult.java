package com.popbill.api.kakao;

import java.io.Serializable;
import java.util.List;

public class KakaoSearchResult implements Serializable {

    private static final long serialVersionUID = -4293105731827416266L;

    private Integer code;
    private String message;
    private Integer total;
    private Integer perPage;
    private Integer pageNum;
    private Integer pageCount;
    private List<KakaoSentDetail> list;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getPerPage() {
        return perPage;
    }

    public void setPerPage(Integer perPage) {
        this.perPage = perPage;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public List<KakaoSentDetail> getList() {
        return list;
    }

    public void setList(List<KakaoSentDetail> list) {
        this.list = list;
    }

}

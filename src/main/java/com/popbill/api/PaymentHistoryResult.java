package com.popbill.api;

import java.util.List;

public class PaymentHistoryResult {
    private Long code;
    private Long total;
    private Long perPage;
    private Long pageNum;
    private Long pageCount;
    private List<PaymentHistory> list;

    public Long getCode() {
        return code;
    }
    public Long getTotal() {
        return total;
    }
    public Long getPerPage() {
        return perPage;
    }
    public Long getPageNum() {
        return pageNum;
    }
    public Long getPageCount() {
        return pageCount;
    }
    public List<PaymentHistory> getList() {
        return list;
    }
}

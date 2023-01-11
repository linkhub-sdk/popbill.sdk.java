package com.popbill.api.easyfin;

import java.io.Serializable;
import java.util.List;

public class EasyFinBankSearchResult implements Serializable {

    private static final long serialVersionUID = 263141664323499482L;

    private long code;
    private long total;
    private long perPage;
    private long pageNum;
    private long pageCount;
    private String message;
    private String lastScrapDT;
    private String balance;

    private List<EasyFinBankSearchDetail> list;

    public long getCode() {
        return code;
    }

    public long getTotal() {
        return total;
    }

    public long getPerPage() {
        return perPage;
    }

    public long getPageNum() {
        return pageNum;
    }

    public long getPageCount() {
        return pageCount;
    }

    public String getMessage() {
        return message;
    }

    public List<EasyFinBankSearchDetail> getList() {
        return list;
    }

    public String getLastScrapDT() {
        return lastScrapDT;
    }

	public String getBalance() {
		return balance;
	}
}

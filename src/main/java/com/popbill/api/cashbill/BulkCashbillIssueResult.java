package com.popbill.api.cashbill;

public class BulkCashbillIssueResult {
    private String mgtKey;
    private long code;
    private String message;
    private String confirmNum;
    private String tradeDate;

    public String getMgtKey() {
        return mgtKey;
    }
    public long getCode() {
        return code;
    }
    public String getMessage() {
        return message;
    }
    public String getConfirmNum() {
        return confirmNum;
    }
    public String getTradeDate() {
        return tradeDate;
    }
}

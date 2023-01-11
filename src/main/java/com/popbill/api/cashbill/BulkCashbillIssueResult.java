package com.popbill.api.cashbill;

public class BulkCashbillIssueResult {
    private String mgtKey;
    private long code;
    private String message;
    private String confirmNum;
    private String tradeDate;
    private String issueDT;

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
    public String getIssueDT() {
        return issueDT;
    }
}

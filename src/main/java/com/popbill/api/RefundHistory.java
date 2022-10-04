package com.popbill.api;

public class RefundHistory {
    private String reqDT;
    private String requestPoint;
    private String accountBank;
    private String accountNum;
    private String accountName;
    private int state;
    private String reason;

    public String getReqDT() {
        return reqDT;
    }
    public String getRequestPoint() {
        return requestPoint;
    }
    public String getAccountBank() {
        return accountBank;
    }
    public String getAccountNum() {
        return accountNum;
    }
    public String getAccountName() {
        return accountName;
    }
    public int getState() {
        return state;
    }
    public String getReason() {
        return reason;
    }
}

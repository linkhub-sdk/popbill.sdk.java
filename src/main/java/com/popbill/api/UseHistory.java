package com.popbill.api;

public class UseHistory {
    private Integer itemCode;
    private int txType;
    private String txPoint;
    private String balance;
    private String txDT;
    private String serviceUserID;
    private String userID;
    private String userName;

    public Integer getItemCode() {
        return itemCode;
    }
    public int getTxType() {
        return txType;
    }
    public String getTxPoint() {
        return txPoint;
    }
    public String getBalance() {
        return balance;
    }
    public String getTxDT() {
        return txDT;
    }
    public String getServiceUserID() {
        return serviceUserID;
    }
    public String getUserID() {
        return userID;
    }
    public String getUserName() {
        return userName;
    }
}

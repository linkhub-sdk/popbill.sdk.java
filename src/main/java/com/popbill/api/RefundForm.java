package com.popbill.api;

import com.google.gson.annotations.SerializedName;

public class RefundForm {
    @SerializedName("contactname")
    private String contactName;
    @SerializedName("tel")
    private String tel;
    @SerializedName("requestpoint")
    private String requestPoint;
    @SerializedName("accountbank")
    private String accountBank;
    @SerializedName("accountnum")
    private String accountNum;
    @SerializedName("accountname")
    private String accountName;
    @SerializedName("reason")
    private String reason;

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }
    public void setTel(String tel) {
        this.tel = tel;
    }
    public void setRequestPoint(String requestPoint) {
        this.requestPoint = requestPoint;
    }
    public void setAccountBank(String accountBank) {
        this.accountBank = accountBank;
    }
    public void setAccountNum(String accountNum) {
        this.accountNum = accountNum;
    }
    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }
    public void setReason(String reason) {
        this.reason = reason;
    }
}

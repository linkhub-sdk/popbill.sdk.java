package com.popbill.api;

import java.io.Serializable;

public class DepositorCheckInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    private String bankCode;
    private String accountNumber;
    private String accountName;
    private String checkDate;
    private String resultCode;
    private String resultMessage;
    private String identityNum;
    private String identityNumType;

    public String getBankCode() {
        return bankCode;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountName() {
        return accountName;
    }

    public String getCheckDate() {
        return checkDate;
    }

    public String getResultCode() {
        return resultCode;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public String getIdentityNum() {
        return identityNum;
    }

    public String getIdentityNumType() {
        return identityNumType;
    }
}

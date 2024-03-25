package com.popbill.api.easyfin;

public class UpdateEasyFinBankAccountForm {
    private String AccountPWD;
    private String AccountName;

    private String BankID;
    private String FastID;
    private String FastPWD;
    private String Memo;

    public String getAccountPWD() {
        return AccountPWD;
    }

    public void setAccountPWD(String accountPWD) {
        AccountPWD = accountPWD;
    }

    public String getAccountName() {
        return AccountName;
    }

    public void setAccountName(String accountName) {
        AccountName = accountName;
    }

    public String getBankID() {
        return BankID;
    }

    public void setBankID(String bankID) {
        BankID = bankID;
    }

    public String getFastID() {
        return FastID;
    }

    public void setFastID(String fastID) {
        FastID = fastID;
    }

    public String getFastPWD() {
        return FastPWD;
    }

    public void setFastPWD(String fastPWD) {
        FastPWD = fastPWD;
    }

    public String getMemo() {
        return Memo;
    }

    public void setMemo(String memo) {
        Memo = memo;
    }
}
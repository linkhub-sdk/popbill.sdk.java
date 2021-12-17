package com.popbill.api;

public class IssueResponse {
    private long code;
    private String message;
    private String ntsConfirmNum;

    /**
     * returns Response Code.
     * 
     * @return code of response.
     */
    public long getCode() {
        return code;
    }

    /**
     * returns Response Message.
     * 
     * @return message of response.
     */
    public String getMessage() {
        return message;
    }

    /**
     * returns ntsconfirmNum
     * 
     * @return ntsconfirmNum of taxinvoice
     */
    public String getNtsConfirmNum() {
        return ntsConfirmNum;
    }

}
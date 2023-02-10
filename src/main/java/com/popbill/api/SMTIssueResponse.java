package com.popbill.api;

public class SMTIssueResponse {
    private long code;
    private String message;
    private String invoiceNum;

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
     * returns invoiceNum
     * 
     * @return invoiceNum of statement
     */
    public String getInvoiceNum() {
        return invoiceNum;
    }
    
}

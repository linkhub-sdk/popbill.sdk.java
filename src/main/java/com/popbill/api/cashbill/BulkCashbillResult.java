package com.popbill.api.cashbill;

import java.util.List;


public class BulkCashbillResult {

    private long code;
    private String message;
    private String receiptID;
    private String receiptDT;
    private String submitID;
    private long submitCount;
    private long successCount;
    private long failCount;
    private long txState;
    private String txStartDT;
    private String txEndDT;
    private long txResultCode;
    private List<BulkCashbillIssueResult> issueResult;
    
    public long getCode() {
        return code;
    }
    public String getMessage() {
        return message;
    }
    public String getReceiptID() {
        return receiptID;
    }
    public String getReceiptDT() {
        return receiptDT;
    }
    public String getSubmitID() {
        return submitID;
    }
    public long getSubmitCount() {
        return submitCount;
    }
    public long getSuccessCount() {
        return successCount;
    }
    public long getFailCount() {
        return failCount;
    }
    public long getTxState() {
        return txState;
    }
    public String getTxStartDT() {
        return txStartDT;
    }
    public String getTxEndDT() {
        return txEndDT;
    }
    public long getTxResultCode() {
        return txResultCode;
    }
    public List<BulkCashbillIssueResult> getIssueResult() {
        return issueResult;
    }

}

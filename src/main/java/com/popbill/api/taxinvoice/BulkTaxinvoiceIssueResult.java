package com.popbill.api.taxinvoice;

import java.io.Serializable;

public class BulkTaxinvoiceIssueResult implements Serializable {

    private static final long serialVersionUID = 9134904621403596348L;

    private String invoicerMgtKey;
    private String trusteeMgtKey;
    private long code;
    private String message;
    private String issueDT;
    private String ntsconfirmNum;

    public String getNtsconfirmNum() {
        return ntsconfirmNum;
    }

    public String getInvoicerMgtKey() {
        return invoicerMgtKey;
    }

    public String getTrusteeMgtKey() {
        return trusteeMgtKey;
    }

    public long getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getIssueDT() {
        return issueDT;
    }
}

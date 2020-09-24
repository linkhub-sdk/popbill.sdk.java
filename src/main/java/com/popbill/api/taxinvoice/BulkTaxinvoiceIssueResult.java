package com.popbill.api.taxinvoice;

import java.io.Serializable;

public class BulkTaxinvoiceIssueResult implements Serializable {
	
	private static final long serialVersionUID = 9134904621403596348L;
	
	private String invoicerMgtKey;
	private String trusteeMgtKey;
	private long code;
	private String confirmNumber;
	
	public String getInvoicerMgtKey() {
		return invoicerMgtKey;
	}
	public String getTrusteeMgtKey() {
		return trusteeMgtKey;
	}
	public long getCode() {
		return code;
	}
	public String getConfirmNumber() {
		return confirmNumber;
	}
}

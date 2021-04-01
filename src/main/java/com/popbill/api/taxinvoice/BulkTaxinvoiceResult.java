package com.popbill.api.taxinvoice;

import java.io.Serializable;
import java.util.List;

public class BulkTaxinvoiceResult implements Serializable {

	private static final long serialVersionUID = -4224469288700675685L;
	
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
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public long getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
	
	private List<BulkTaxinvoiceIssueResult> issueResult;

	public String getReceiptID() {
		return receiptID;
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

	public List<BulkTaxinvoiceIssueResult> getIssueResult() {
		return issueResult;
	}

	public String getReceiptDT() {
		return receiptDT;
	}
	
	
}

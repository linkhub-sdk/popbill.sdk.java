package com.popbill.api.taxinvoice;

import java.io.Serializable;
import java.util.List;

public class BulkTaxinvoiceResultDetail implements Serializable{

	private static final long serialVersionUID = -4890773202084236614L;
	
	private String receiptID;
	private String submitID;
	private long submitCount;
	private long successCount;
	private long failCount;
	private long txState;
	private String txStartDT;
	private String txEndDT;
	private long txResultCode;
	
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

	
	
}

package com.popbill.api.taxinvoice;

import java.io.Serializable;

public class BulkTaxinvoiceResult implements Serializable {

	private static final long serialVersionUID = -4224469288700675685L;
	
	private long ResultCode;
	private String Message;
	
	private BulkTaxinvoiceResultDetail retObject;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public long getResultCode() {
		return ResultCode;
	}

	public String getMessage() {
		return Message;
	}

	public BulkTaxinvoiceResultDetail getRetObject() {
		return retObject;
	}
	
	
}

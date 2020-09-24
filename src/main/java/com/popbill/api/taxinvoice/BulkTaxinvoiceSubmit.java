package com.popbill.api.taxinvoice;

import java.io.Serializable;
import java.util.List;

public class BulkTaxinvoiceSubmit implements Serializable {

	private static final long serialVersionUID = 28408701207021469L;
	
	private boolean forceIssue;
	
	private List<Taxinvoice> invoices;

	public boolean isForceIssue() {
		return forceIssue;
	}

	public void setForceIssue(boolean forceIssue) {
		this.forceIssue = forceIssue;
	}

	public List<Taxinvoice> getInvoices() {
		return invoices;
	}

	public void setInvoices(List<Taxinvoice> invoices) {
		this.invoices = invoices;
	}
}

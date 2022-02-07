package com.popbill.api;

public class TaxinvoiceCertificate {
    
    private String regDT;
    private String expireDT;
    private String issuerDN;
    private String subjectDN;
    private String issuerName;
    private String oid;
    
    public String getRegDT() {
        return regDT;
    }
    public String getExpireDT() {
        return expireDT;
    }
    public String getIssuerDN() {
        return issuerDN;
    }
    public String getSubjectDN() {
        return subjectDN;
    }
    public String getIssuerName() {
        return issuerName;
    }
    public String getOid() {
        return oid;
    }
    
}

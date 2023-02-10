package com.popbill.api.taxinvoice;

/**
 * Class for Taxinvoice's XML.
 * 
 * @author Linkhub Dev TEAM
 * @version 1.0.0
 */
public class TaxinvoiceXML {
    private Integer code;
    private String message;
    private String retObject;
    
    public Integer getCode() {
        return code;
    }
    public void setCode(Integer code) {
        this.code = code;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String getRetObject() {
        return retObject;
    }
    public void setRetObject(String retObject) {
        this.retObject = retObject;
    }
}

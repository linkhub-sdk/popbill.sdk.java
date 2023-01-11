package com.popbill.api;

public class PaymentForm {

    private String settlerName;
    
    private String settlerEmail;
    
    private String notifyHP;
    
    private String paymentName;
    
    private String settleCost;

    public void setSettlerName(String settlerName) {
        this.settlerName = settlerName;
    }

    public void setSettlerEmail(String settlerEmail) {
        this.settlerEmail = settlerEmail;
    }

    public void setNotifyHP(String notifyHP) {
        this.notifyHP = notifyHP;
    }

    public void setPaymentName(String paymentName) {
        this.paymentName = paymentName;
    }

    public void setSettleCost(String settleCost) {
        this.settleCost = settleCost;
    }
}

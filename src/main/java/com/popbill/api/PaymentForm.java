package com.popbill.api;

public class PaymentForm {

    private String settlerName;

    private String settlerEmail;

    private String settlerHP;

    private String paymentName;

    private String settleCost;

    public void setSettlerName(String settlerName) {
        this.settlerName = settlerName;
    }

    public void setSettlerEmail(String settlerEmail) {
        this.settlerEmail = settlerEmail;
    }

    public void setSettlerHP(String settlerHP) {
        this.settlerHP = settlerHP;
    }

    public void setPaymentName(String paymentName) {
        this.paymentName = paymentName;
    }

    public void setSettleCost(String settleCost) {
        this.settleCost = settleCost;
    }
}

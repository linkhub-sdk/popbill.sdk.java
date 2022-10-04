package com.popbill.api;

public class PaymentHistory {

    private String productType;
    private String productName;
    private String settleType;
    private String settlerName;
    private String settlerEmail;
    private String settleCost;
    private String settlePoint;
    private int settleState;
    private String regDT;
    private String stateDT;

    public String getProductType() {
        return productType;
    }
    public String getProductName() {
        return productName;
    }
    public String getSettleType() {
        return settleType;
    }
    public String getSettlerName() {
        return settlerName;
    }
    public String getSettlerEmail() {
        return settlerEmail;
    }
    public String getSettleCost() {
        return settleCost;
    }
    public String getSettlePoint() {
        return settlePoint;
    }
    public int getSettleState() {
        return settleState;
    }
    public String getRegDT() {
        return regDT;
    }
    public String getStateDT() {
        return stateDT;
    }

}

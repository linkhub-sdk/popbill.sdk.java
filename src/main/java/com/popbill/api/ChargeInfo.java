package com.popbill.api;

/**
 * Class for Charge Information.
 * 
 * @author JeongYohan
 * @version 1.0.0
 */

public class ChargeInfo {
    private String unitCost;
    private String chargeMethod;
    private String rateSystem;
    
    /**
     * 단가확인, 종량제 - 발행단가, 정액제 - 월정액 요금 반환 
     *  
     * @return 발행단가, 월정액요
     */
    public String getUnitCost() {
        return unitCost;
    }
    
    /**
     * 과금유형 확인
     * 
     * @return "일반", "연동", "파트너" 중 반환 
     */
    public String getChargeMethod() {
        return chargeMethod;
    }
    
    /**
     * 과금제도 확인
     * 
     * @return "정액제", "종량제" 중 반환
     */
    public String getRateSystem() {
        return rateSystem;
    }
}

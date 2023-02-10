package com.popbill.api.hometax;

/**
 * Class For HTCashbill Search Summary
 * 
 * @author JeongYoHan
 * @version 1.0.0
 *
 */
public class HTCashbillSummary {
    private long count;
    private long supplyCostTotal;
    private long taxTotal;
    private long serviceFeeTotal;
    private long amountTotal;

    /**
     * 수집 결과 건수 확인
     * 
     * @return 수집 결과 건수
     */
    public long getCount() {
        return count;
    }

    /**
     * 공급가액 합계 확인
     * 
     * @return 공급가액 합계
     */
    public long getSupplyCostTotal() {
        return supplyCostTotal;
    }

    /**
     * 세액 합계 확인
     * 
     * @return 세액 합계
     */
    public long getTaxTotal() {
        return taxTotal;
    }

    /**
     * 봉사료 합계 확인
     * 
     * @return 봉사료 합계
     */
    public long getServiceFeeTotal() {
        return serviceFeeTotal;
    }

    /**
     * 합계 금액 확인
     * 
     * @return 합계 금액
     */
    public long getAmountTotal() {
        return amountTotal;
    }

}

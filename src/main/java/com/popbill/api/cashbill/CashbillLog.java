package com.popbill.api.cashbill;

/**
 * Class for CashbillLog Information.
 * 
 * @author JeongYoHan
 * @version 1.0.0
 */
public class CashbillLog {
    private Integer docLogType;
    private String log;
    private String procType;
    private String procMemo;
    private String regDT;
    private String ip;

    /**
     * 문서기록 형태 확인
     * 
     * @return the docLogTpye
     */
    public Integer getDocLogType() {
        return docLogType;
    }

    /**
     * 기록 확인
     * 
     * @return the log
     */
    public String getLog() {
        return log;
    }

    /**
     * 처리형태 확인
     * 
     * @return the procType
     */
    public String getProcType() {
        return procType;
    }

    /**
     * 처리시 메모 확인
     * 
     * @return the procMeme
     */
    public String getProcMemo() {
        return procMemo;
    }

    /**
     * 등록일시 확인
     * 
     * @return the regDT
     */
    public String getRegDT() {
        return regDT;
    }

    /**
     * 처리자 IP 확인
     * 
     * @return the ip
     */
    public String getIp() {
        return ip;
    }

}

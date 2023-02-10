package com.popbill.api.taxinvoice;

/**
 * Class for TaxinvoiceLog Information.
 * 
 * @author KimSeongjun
 * @version 1.0.0
 */
public class TaxinvoiceLog {

    private Integer docLogType;
    private String log;
    private String procType;
    private String procCorpName;
    private String procContactName;
    private String procMemo;
    private String regDT;
    private String ip;

    /**
     * returns DocLogType(문서기록형태)
     * 
     * @return DocLogType
     */
    public Integer getDocLogType() {
        return docLogType;
    }

    /**
     * returns Log(기록)
     * 
     * @return Log
     */
    public String getLog() {
        return log;
    }

    /**
     * returns ProcType(처리형태)
     * 
     * @return ProcType
     */
    public String getProcType() {
        return procType;
    }

    /**
     * returns ProcCorpName(처리회사명)
     * 
     * @return ProcCorpName
     */
    public String getProcCorpName() {
        return procCorpName;
    }

    /**
     * returns ProcContactName(처리담당자명)
     * 
     * @return ProcContactName
     */
    public String getProcContactName() {
        return procContactName;
    }

    /**
     * returns ProcMemo(처리시 메모)
     * 
     * @return ProcMemo
     */
    public String getProcMemo() {
        return procMemo;
    }

    /**
     * returns RegDT(등록일시)
     * 
     * @return RegDT
     */
    public String getRegDT() {
        return regDT;
    }

    /**
     * returns IP(처리자 IP)
     * 
     * @return IP
     */
    public String getIP() {
        return ip;
    }
}

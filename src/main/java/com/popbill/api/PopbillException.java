package com.popbill.api;

import kr.co.linkhub.auth.LinkhubException;

/**
 * Popbill Operation Exception.
 * 
 * @author KimSeongjun
 * @version 1.0.0
 */
public class PopbillException extends Exception {
    private static final long serialVersionUID = 1L;

    private long code;

    public PopbillException(LinkhubException linkhubException) {
        super(linkhubException.getMessage(), linkhubException);
        this.code = linkhubException.getCode();
    }

    public PopbillException(long code, String Message) {
        super(Message);
        this.code = code;
    }

    public PopbillException(long code, String Message, Throwable innerException) {
        super(Message, innerException);
        this.code = code;
    }

    /**
     * Return Popbill's result Error code. (ex. -11010009) In case of -99999999,
     * check the getMessage() for detail.
     * 
     * @return error code.
     */
    public long getCode() {
        return code;
    }

}

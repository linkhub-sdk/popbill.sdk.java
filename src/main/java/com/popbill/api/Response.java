package com.popbill.api;

/**
 * Base Response of Popbill Service.
 * 
 * @author KimSeongjun
 * @version 1.0.0
 */
public class Response {
    private long code;
    private String message;

    /**
     * returns Response Code.
     * 
     * @return code of response.
     */
    public long getCode() {
        return code;
    }

    /**
     * returns Response Message.
     * 
     * @return message of response.
     */
    public String getMessage() {
        return message;
    }

}

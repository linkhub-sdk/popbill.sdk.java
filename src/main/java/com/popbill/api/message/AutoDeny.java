package com.popbill.api.message;

import java.io.Serializable;

/**
 * Class for AutoDeny Information.
 * 
 * @author JeongYohan
 * @version 1.0.0
 */

public class AutoDeny implements Serializable {
    private static final long serialVersionUID = -730974577212506133L;

    private String number;
    private String regDT;

    /**
     * 080수신거부번호 확인
     * 
     * @return 수신거부번호
     */
    public String getNumber() {
        return number;
    }

    /**
     * 080수신거부 등록일시 확인
     * 
     * @return 등록일시
     */
    public String getRegDT() {
        return regDT;
    }

}

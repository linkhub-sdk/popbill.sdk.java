package com.popbill.api.message;

import java.io.Serializable;

/**
 * Class for SenderNum to list.
 * 
 * @author JeongYohan
 * @version 1.0.0
 */
public class SenderNumber implements Serializable {

    private static final long serialVersionUID = -8140988807131053917L;

    private String number;
    private Boolean representYN;
    private Integer state;
    private String memo;

    /**
     * 등록된 발신번호
     * 
     * @return 발신번호
     */
    public String getNumber() {
        return number;
    }

    /**
     * 대표번호 지정여부
     * 
     * @return 대표번호 지정여부 (True, False)
     */
    public Boolean getRepresentYN() {
        return representYN;
    }

    /**
     * 발신번호 등록상태
     * 0 - 대기
     * 1 - 승인
     * 2 - 취소
     * 
     * @return 등록상태코드(0, 1, 2)
     */
    public Integer getState() {
        return state;
    }

    public String getMemo() {
        return memo;
    }

}

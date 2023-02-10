package com.popbill.api.fax;

import java.io.Serializable;

/**
 * 팩스 발신번호 목록을 위한 class.
 * 
 * @author JeongYohan
 * @version 1.0.0
 */
public class SenderNumber implements Serializable {

    private static final long serialVersionUID = 389767663855602179L;

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

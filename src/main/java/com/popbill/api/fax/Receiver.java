package com.popbill.api.fax;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

/**
 * 동보전송시 수신자 목록 구성을 위한 class.
 * 
 * @author KimSeongjun
 * @version 1.0.0
 */
public class Receiver implements Serializable {
    private static final long serialVersionUID = 7734339056495610540L;

    @SerializedName("rcv")
    private String receiveNum;
    @SerializedName("rcvnm")
    private String receiveName;
    @SerializedName("interOPRefKey")
    private String interOPRefKey;

    /**
     * 수신번호 확인
     * 
     * @return 수신번호
     */
    public String getReceiveNum() {
        return receiveNum;
    }

    /**
     * 수신번호 설정
     * 
     * @param receiveNum 
     *          수신번호
     */
    public void setReceiveNum(String receiveNum) {
        this.receiveNum = receiveNum;
    }

    /**
     * 수신자 명칭 확인
     * 
     * @return 수신자 명칭
     */
    public String getReceiveName() {
        return receiveName;
    }

    /**
     * 수신자 명칭 설정
     * 
     * @param receiveName 
     *          수신자 명칭
     */
    public void setReceiveName(String receiveName) {
        this.receiveName = receiveName;
    }

    /**
     * 파트너 지정 키 확인.
     * 
     * @return 파트너 지정 키
     */
    public String getInterOPRefKey() {
        return interOPRefKey;
    }

    /**
     * 파트너 지정 키 설정.
     * 
     * @param interOPRefKey
     *          파트너 지정 키
     */
    public void setInterOPRefKey(String interOPRefKey) {
        this.interOPRefKey = interOPRefKey;
    }

}

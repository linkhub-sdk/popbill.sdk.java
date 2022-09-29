/*
 * Copyright 2006-2014 linkhub.co.kr, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0.txt
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */
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

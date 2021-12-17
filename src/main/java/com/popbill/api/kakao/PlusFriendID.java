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
package com.popbill.api.kakao;

import java.io.Serializable;

/**
 * Class for AutoDeny Information.
 * 
 * @author JeongYohan
 * @version 1.0.0
 */

public class PlusFriendID implements Serializable {

    private static final long serialVersionUID = 3199331662598320029L;

    private String plusFriendID;
    private String plusFriendName;
    private String regDT;

    public String getPlusFriendID() {
        return plusFriendID;
    }

    public void setPlusFriendID(String plusFriendID) {
        this.plusFriendID = plusFriendID;
    }

    public String getPlusFriendName() {
        return plusFriendName;
    }

    public void setPlusFriendName(String plusFriendName) {
        this.plusFriendName = plusFriendName;
    }

    public String getRegDT() {
        return regDT;
    }

    public void setRegDT(String regDT) {
        this.regDT = regDT;
    }

}

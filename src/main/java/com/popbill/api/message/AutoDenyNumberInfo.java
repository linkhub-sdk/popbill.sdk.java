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
package com.popbill.api.message;

import java.io.Serializable;

/**
 * Class for AutoDeny Information.
 * 
 * @author JeongYohan
 * @version 1.0.0
 */

public class AutoDenyNumberInfo implements Serializable {
    private static final long serialVersionUID = -730974577212506133L;

    private String number;
    private String smsdenyNumber;
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
     * 080수신거부번호 확인
     * 
     * @return 수신거부번호
     */
    public String getSmsdenyNumber() {
    	return smsdenyNumber;
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

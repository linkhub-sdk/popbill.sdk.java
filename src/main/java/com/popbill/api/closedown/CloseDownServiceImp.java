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
package com.popbill.api.closedown;

import java.util.Arrays;
import java.util.List;

import com.popbill.api.BaseServiceImp;
import com.popbill.api.ChargeInfo;
import com.popbill.api.CloseDownService;
import com.popbill.api.CorpState;
import com.popbill.api.PopbillException;

/**
 * Implementation of Popbill CloseDownService Interface
 * 
 * @author KimSeongjun
 * @version 1.0.0
 * @see com.popbill.api.CloseDownService
 */
public class CloseDownServiceImp extends BaseServiceImp implements CloseDownService {

    @Override
    protected List<String> getScopes() {
        return Arrays.asList("170");
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.CloseDownService#getUnitCost
     */
    @Override
    public float getUnitCost(String CorpNum) throws PopbillException {
        UnitCostResponse response = httpget("/CloseDown/UnitCost", CorpNum, null, UnitCostResponse.class);

        return response.unitCost;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.CloseDownService#CheckCorpNum
     */
    @Override
    public CorpState CheckCorpNum(String MemberCorpNum, String CheckCorpNum) throws PopbillException {
        return httpget("/CloseDown?CN=" + CheckCorpNum, MemberCorpNum, null, CorpState.class);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.CloseDownService#CheckCorpNum
     */
    @Override
    public CorpState[] CheckCorpNum(String MemberCorpNum, String[] CorpNumList) throws PopbillException {

        String PostData = toJsonString(CorpNumList);

        return httppost("/CloseDown", MemberCorpNum, PostData, null, CorpState[].class);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.CloseDownService#getChargeInfo(java.lang.String)
     */
    @Override
    public ChargeInfo getChargeInfo(String CorpNum) throws PopbillException {
        return httpget("/CloseDown/ChargeInfo", CorpNum, null, ChargeInfo.class);
    }
}

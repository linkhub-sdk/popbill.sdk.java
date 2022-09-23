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
package com.popbill.api.bizinfocheck;

import java.util.Arrays;
import java.util.List;

import com.popbill.api.BaseServiceImp;
import com.popbill.api.BizCheckInfo;
import com.popbill.api.BizInfoCheckService;
import com.popbill.api.ChargeInfo;
import com.popbill.api.PopbillException;

/**
 * Implementation of Popbill BizInfoCheckService Interface
 * 
 * @author shchoi
 * @version 1.0.0
 * @see com.popbill.api.BizInfoCheckService
 */
public class BizInfoCheckServiceImp extends BaseServiceImp implements BizInfoCheckService {

    @Override
    protected List<String> getScopes() {
        return Arrays.asList("171");
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.BizInfoCheckService#getUnitCost(java.lang.String)
     */
    @Override
    public float getUnitCost(String CorpNum) throws PopbillException {
        UnitCostResponse response = httpget("/BizInfo/UnitCost", CorpNum, null, UnitCostResponse.class);

        return response.unitCost;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.BizInfoCheckService#CheckBizInfo(java.lang.String, java.lang.String)
     */
    @Override
    public BizCheckInfo CheckBizInfo(String MemberCorpNum, String CheckCorpNum) throws PopbillException {
        return httpget("/BizInfo/Check?CN=" + CheckCorpNum, MemberCorpNum, null, BizCheckInfo.class);
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.BizInfoCheckService#CheckBizInfo(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public BizCheckInfo CheckBizInfo(String MemberCorpNum, String CheckCorpNum, String UserId) throws PopbillException {
        return httpget("/BizInfo/Check?CN=" + CheckCorpNum, MemberCorpNum, UserId, BizCheckInfo.class);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.BizInfoCheckService#getChargeInfo(java.lang.String)
     */
    @Override
    public ChargeInfo getChargeInfo(String CorpNum) throws PopbillException {
        return httpget("/BizInfo/ChargeInfo", CorpNum, null, ChargeInfo.class);
    }
}

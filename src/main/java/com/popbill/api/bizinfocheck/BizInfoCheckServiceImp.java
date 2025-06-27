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
    public BizCheckInfo CheckBizInfo(String CorpNum, String CheckCorpNum) throws PopbillException {
        return CheckBizInfo(CorpNum, CheckCorpNum, null);
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.BizInfoCheckService#CheckBizInfo(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public BizCheckInfo CheckBizInfo(String CorpNum, String CheckCorpNum, String UserId) throws PopbillException {
        return httpget("/BizInfo/Check?CN=" + CheckCorpNum, CorpNum, UserId, BizCheckInfo.class);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.BizInfoCheckService#getChargeInfo(java.lang.String)
     */
    @Override
    public ChargeInfo getChargeInfo(String CorpNum) throws PopbillException {
        return getChargeInfo(CorpNum, null);
    }

    @Override
    public ChargeInfo getChargeInfo(String CorpNum, String UserID) throws PopbillException {
        return httpget("/BizInfo/ChargeInfo", CorpNum, UserID, ChargeInfo.class);
    }
}
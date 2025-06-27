package com.popbill.api.closedown;

import com.popbill.api.BaseServiceImp;
import com.popbill.api.ChargeInfo;
import com.popbill.api.CloseDownService;
import com.popbill.api.CorpState;
import com.popbill.api.PopbillException;

import java.util.Arrays;
import java.util.List;

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
    public CorpState CheckCorpNum(String CorpNum, String CheckCorpNum) throws PopbillException {
        return CheckCorpNum(CorpNum, CheckCorpNum, null);
    }

    @Override
    public CorpState CheckCorpNum(String CorpNum, String CheckCorpNum, String UserID) throws PopbillException {
        return httpget("/CloseDown?CN=" + CheckCorpNum, CorpNum, UserID, CorpState.class);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.CloseDownService#CheckCorpNum
     */
    @Override
    public CorpState[] CheckCorpNum(String CorpNum, String[] CorpNumList) throws PopbillException {
        return CheckCorpNum(CorpNum, CorpNumList, null);
    }

    @Override
    public CorpState[] CheckCorpNum(String CorpNum, String[] CorpNumList, String UserID) throws PopbillException {

        String PostData = toJsonString(CorpNumList);

        return httppost("/CloseDown", CorpNum, PostData, UserID, CorpState[].class);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.CloseDownService#getChargeInfo(java.lang.String)
     */
    @Override
    public ChargeInfo getChargeInfo(String CorpNum) throws PopbillException {
        return getChargeInfo(CorpNum, null);
    }

    @Override
    public ChargeInfo getChargeInfo(String CorpNum, String UserID) throws PopbillException {
        return httpget("/CloseDown/ChargeInfo", CorpNum, UserID, ChargeInfo.class);
    }
}
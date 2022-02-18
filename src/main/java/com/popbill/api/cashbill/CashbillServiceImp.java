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
package com.popbill.api.cashbill;

import java.util.Arrays;
import java.util.List;

import com.popbill.api.BaseServiceImp;
import com.popbill.api.BulkResponse;
import com.popbill.api.CashbillService;
import com.popbill.api.ChargeInfo;
import com.popbill.api.EmailSendConfig;
import com.popbill.api.PopbillException;
import com.popbill.api.Response;
import com.popbill.api.CBIssueResponse;

/**
 *  Implementation of Popbill CashbillService Interface
 *
 * @author JeongYoHan
 * @version 1.0.0
 * @see com.popbill.api.CashbillService
 */
public class CashbillServiceImp extends BaseServiceImp implements CashbillService{

    @Override
    protected List<String> getScopes(){
        return Arrays.asList("140");
    }

    /*
     * (non-Javadoc)
     * @see com.popbill.api.CashbillService#getURL(java.lang.String, java.lang.String)
     */
    @Override
    public String getURL(String CorpNum, String TOGO)
            throws PopbillException {

        return getURL(CorpNum, null, TOGO);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.CashbillService#getURL(java.lang.String, java.lang.String,  java.lang.String)
     */
    @Override
    public String getURL(String CorpNum, String UserID, String TOGO)
            throws PopbillException {

        URLResponse response = httpget("/Cashbill?TG=" + TOGO,
                CorpNum, UserID, URLResponse.class);

        return response.url;
    }

    /* (non-Javadoc)
     * @see com.popbill.api.CashbillService#getUnitCost(java.lang.String)
     */
    @Override
    public float getUnitCost(String CorpNum) throws PopbillException {

        UnitCostResponse response = httpget("/Cashbill?cfg=UNITCOST",
                CorpNum,null, UnitCostResponse.class);

        return response.unitCost;

    }

    /* (non-Javadoc)
     * @see com.popbill.api.CashbillService#checkMgtKeyInuse(java.lang.String, java.lang.String)
     */
    @Override
    public boolean checkMgtKeyInUse(String CorpNum, String MgtKey)
            throws PopbillException {
        if (MgtKey == null || MgtKey.isEmpty())
            throw new PopbillException(-99999999, "문서번호가 입력되지 않았습니다.");

        try{
            CashbillInfo info = httpget("/Cashbill/" + MgtKey, CorpNum, null, CashbillInfo.class);

            return (info.getItemKey() == null || info.getItemKey().isEmpty()) == false;

            } catch(PopbillException PE){
                if(PE.getCode() == -14000003)
                    return false;
                throw PE;
            }
    }

    /* (non-Javadoc)
     * @see com.popbill.api.CashbillService#register(java.lang.String, com.popbill.api.cashbill.Cashbill)
     */
    @Override
    public Response register(String CorpNum, Cashbill cashbill)
            throws PopbillException {
        return register(CorpNum, cashbill, null);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.CashbillService#register(java.lang.String, com.popbill.api.cashbill.Cashbill, java.lang.String)
     */
    @Override
    public Response register(String CorpNum, Cashbill cashbill, String UserID)
            throws PopbillException {
        if (cashbill == null)
            throw new PopbillException(-99999999, "현금영수증정보가 입력되지 않았습니다.");

        String PostData = toJsonString(cashbill);

        return httppost("/Cashbill",CorpNum, PostData,
                UserID, Response.class);

    }
    /* (non-Javadoc)
     * @see com.popbill.api.CashbillService#update(java.lang.String, java.lang.String, com.popbill.api.cashbill.Cashbill)
     */
    @Override
    public Response update(String CorpNum, String MgtKey, Cashbill cashbill)
            throws PopbillException {
        if (MgtKey == null || MgtKey.isEmpty())
            throw new PopbillException(-99999999, "문서번호가 입력되지 않았습니다.");

        return update(CorpNum, MgtKey, cashbill, null);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.CashbillService#update(java.lang.String, java.lang.String, com.popbill.api.cashbill.Cashbill, java.lang.String)
     */
    @Override
    public Response update(String CorpNum, String MgtKey, Cashbill cashbill,
            String UserID) throws PopbillException {
        if (MgtKey == null || MgtKey.isEmpty())
            throw new PopbillException(-99999999, "문서번호가 입력되지 않았습니다.");

        String PostData = toJsonString(cashbill);

        return httppost("/Cashbill/"+MgtKey, CorpNum,
                PostData, UserID, "PATCH", Response.class);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.CashbillService#delete(java.lang.String, java.lang.String)
     */
    @Override
    public Response delete(String CorpNum, String MgtKey)
            throws PopbillException {
        if (MgtKey == null || MgtKey.isEmpty())
            throw new PopbillException(-99999999, "문서번호가 입력되지 않았습니다.");

        return delete(CorpNum, MgtKey, null);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.CashbillService#delete(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public Response delete(String CorpNum, String MgtKey, String UserID)
            throws PopbillException {
        if (MgtKey == null || MgtKey.isEmpty())
            throw new PopbillException(-99999999, "문서번호가 입력되지 않았습니다.");

        return httppost("/Cashbill/"+MgtKey, CorpNum, null,
                UserID, "DELETE", Response.class);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.CashbillService#issue(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public CBIssueResponse issue(String CorpNum, String MgtKey, String Memo)
            throws PopbillException {
        if (MgtKey == null || MgtKey.isEmpty())
            throw new PopbillException(-99999999, "문서번호가 입력되지 않았습니다.");

        return issue(CorpNum, MgtKey, Memo, null);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.CashbillService#issue(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public CBIssueResponse issue(String CorpNum, String MgtKey, String Memo,
            String UserID) throws PopbillException {
        if (MgtKey == null || MgtKey.isEmpty())
            throw new PopbillException(-99999999, "문서번호가 입력되지 않았습니다.");

        String PostData = toJsonString(new MemoRequest(Memo));

        return httppost("/Cashbill/" + MgtKey, CorpNum, PostData,
                UserID, "ISSUE", CBIssueResponse.class);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.CashbillService#cancelIssue(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public Response cancelIssue(String CorpNum, String MgtKey, String Memo)
             throws PopbillException {
        if (MgtKey == null || MgtKey.isEmpty())
            throw new PopbillException(-99999999, "문서번호가 입력되지 않았습니다.");
        return cancelIssue(CorpNum, MgtKey, Memo, null);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.CashbillService#cancelIssue(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public Response cancelIssue(String CorpNum, String MgtKey, String Memo,
            String UserID) throws PopbillException {
        if (MgtKey == null || MgtKey.isEmpty())
            throw new PopbillException(-99999999, "문서번호가 입력되지 않았습니다.");

        String PostData = toJsonString(new MemoRequest(Memo));

        return httppost("/Cashbill/" + MgtKey, CorpNum, PostData,
                UserID, "CANCELISSUE", Response.class);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.CashbillService#sendEmail(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public Response sendEmail(String CorpNum, String MgtKey, String Receiver)
             throws PopbillException {
        if (MgtKey == null || MgtKey.isEmpty())
            throw new PopbillException(-99999999, "문서번호가 입력되지 않았습니다.");

        return sendEmail(CorpNum, MgtKey, Receiver, null);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.CashbillService#sendEmail(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public Response sendEmail(String CorpNum, String MgtKey, String Receiver,
            String UserID) throws PopbillException {
        if (MgtKey == null || MgtKey.isEmpty())
            throw new PopbillException(-99999999, "문서번호가 입력되지 않았습니다.");

        ResendRequest request  = new ResendRequest();

        request.receiver = Receiver;

        String PostData = toJsonString(request);

        return httppost("/Cashbill/" + MgtKey, CorpNum, PostData,
                UserID, "EMAIL", Response.class);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.CashbillService#sendSMS(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public Response sendSMS(String CorpNum, String MgtKey, String Sender,
            String Receiver, String Contents)
            throws PopbillException {
        if (MgtKey == null || MgtKey.isEmpty())
            throw new PopbillException(-99999999, "문서번호가 입력되지 않았습니다.");

        return sendSMS(CorpNum, MgtKey, Sender, Receiver, Contents, null);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.CashbillService#sendSMS(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public Response sendSMS(String CorpNum, String MgtKey, String Sender,
            String Receiver, String Contents, String UserID)
            throws PopbillException {
        if (MgtKey == null || MgtKey.isEmpty())
            throw new PopbillException(-99999999, "문서번호가 입력되지 않았습니다.");

        ResendRequest request = new ResendRequest();

        request.sender = Sender;
        request.receiver = Receiver;
        request.contents = Contents;

        String PostData = toJsonString(request);

        return httppost("/Cashbill/" + MgtKey, CorpNum, PostData,
                UserID, "SMS", Response.class);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.CashbillService#sendFAX(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public Response sendFAX(String CorpNum, String MgtKey, String Sender,
            String Receiver) throws PopbillException {
        if (MgtKey == null || MgtKey.isEmpty())
            throw new PopbillException(-99999999, "문서번호가 입력되지 않았습니다.");

        return sendFAX(CorpNum, MgtKey, Sender, Receiver, null);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.CashbillService#sendFAX(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public Response sendFAX(String CorpNum, String MgtKey, String Sender,
            String Receiver, String UserID) throws PopbillException {
        if (MgtKey == null || MgtKey.isEmpty())
            throw new PopbillException(-99999999, "문서번호가 입력되지 않았습니다.");

        ResendRequest request = new ResendRequest();

        request.sender = Sender;
        request.receiver = Receiver;

        String PostData = toJsonString(request);

        return httppost("/Cashbill/" + MgtKey, CorpNum,
                PostData, UserID, "FAX", Response.class);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.CashbillService#getDetailInfo(java.lang.String, java.lang.String)
     */
    @Override
    public Cashbill getDetailInfo(String CorpNum, String MgtKey)
            throws PopbillException {
        if (MgtKey == null || MgtKey.isEmpty())
            throw new PopbillException(-99999999, "문서번호가 입력되지 않았습니다.");

        return httpget("/Cashbill/" + MgtKey + "?Detail", CorpNum,
                null, Cashbill.class);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.CashbillService#getInfo(java.lang.String, java.lang.String)
     */
    @Override
    public CashbillInfo getInfo(String CorpNum, String MgtKey)
            throws PopbillException {
        if (MgtKey == null || MgtKey.isEmpty())
            throw new PopbillException(-99999999, "문서번호가 입력되지 않았습니다.");

        return httpget("/Cashbill/" + MgtKey, CorpNum,
                null, CashbillInfo.class);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.CashbillService#getInfos(java.lang.String, java.lang.String[])
     */
    @Override
    public CashbillInfo[] getInfos(String CorpNum, String[] MgtKeyList)
            throws PopbillException {
        if (MgtKeyList == null || MgtKeyList.length == 0)
            throw new PopbillException(-99999999, "문서번호배열이 입력되지 않았습니다.");

        String PostData = toJsonString(MgtKeyList);

        return httppost("/Cashbill/States", CorpNum, PostData,
                null, CashbillInfo[].class);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.CashbillService#getLogs(java.lang.String, java.lang.String)
     */
    @Override
    public CashbillLog[] getLogs(String CorpNum, String MgtKey)
            throws PopbillException {
        if (MgtKey == null || MgtKey.isEmpty())
            throw new PopbillException(-99999999, "문서번호가 입력되지 않았습니다.");

        return httpget("/Cashbill/" + MgtKey +"/Logs", CorpNum, null, CashbillLog[].class);
    }
    
    @Override
    public Response assignMgtKey(String corpNum, String itemKey,
                                 String mgtKey) throws PopbillException {

        return assignMgtKey(corpNum,  itemKey, mgtKey, null);
    }

    @Override
    public Response assignMgtKey(String corpNum, String itemKey,
                                 String mgtKey, String userID) throws PopbillException {
        if (itemKey == null || itemKey.isEmpty())
            throw new PopbillException(-99999999, "아이템키(ItemKey)가 입력되지 않았습니다.");

        if (mgtKey == null || mgtKey.isEmpty())
            throw new PopbillException(-99999999, "문서번호(MgtKey)가 입력되지 않았습니다.");

        String PostData = "MgtKey=" + mgtKey;

        return httppost("/Cashbill/" + itemKey ,
                corpNum, PostData, userID, "", Response.class, "application/x-www-form-urlencoded; charset=utf-8");
    }

    @Override
    public String getPDFURL(String CorpNum, String MgtKey)
            throws PopbillException {

        return getPDFURL(CorpNum, MgtKey, null);
    }
    
    @Override
    public String getPDFURL(String CorpNum, String MgtKey, String UserID)
            throws PopbillException {
        if (MgtKey == null || MgtKey.isEmpty())
            throw new PopbillException(-99999999, "문서번호가 입력되지 않았습니다.");

        URLResponse response = httpget("/Cashbill/" + MgtKey + "?TG=PDF",
                CorpNum, UserID, URLResponse.class);

        return response.url;
    }
    
    /* (non-Javadoc)
     * @see com.popbill.api.CashbillService#getPDF(java.lang.String, java.lang.String)
     */
    @Override
    public byte[] getPDF(String CorpNum, String MgtKey)
            throws PopbillException {
        
        return getPDF(CorpNum, MgtKey, null);
    }
    
    /* (non-Javadoc)
     * @see com.popbill.api.CashbillService#getPDF(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public byte[] getPDF(String CorpNum, String MgtKey, String UserID)
            throws PopbillException {
        if (MgtKey == null || MgtKey.isEmpty())
            throw new PopbillException(-99999999, "문서번호가 입력되지 않았습니다.");
        
        byte[] result = httpget("/Cashbill/" + MgtKey + "?PDF",
                CorpNum, UserID, byte[].class);
        
        return result;
    }
    
    
    /*
     * (non-Javadoc)
     * @see com.popbill.api.CashbillService#getPrintURL(java.lang.String, java.lang.String)
     */
    @Override
    public String getPrintURL(String CorpNum, String MgtKey)
            throws PopbillException {

        return getPrintURL(CorpNum, MgtKey, null);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.CashbillService#getPrintURL(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public String getPrintURL(String CorpNum, String MgtKey, String UserID)
            throws PopbillException {
        if (MgtKey == null || MgtKey.isEmpty())
            throw new PopbillException(-99999999, "문서번호가 입력되지 않았습니다.");

        URLResponse response = httpget("/Cashbill/" + MgtKey + "?TG=PRINT",
                CorpNum, UserID, URLResponse.class);

        return response.url;
    }

    /*
     * (non-Javadoc)
     * @see com.popbill.api.CashbillService#getEPrintURL(java.lang.String, java.lang.String)
     */
    @Override
    public String getEPrintURL(String CorpNum, String MgtKey)
            throws PopbillException {

        return getEPrintURL(CorpNum, MgtKey, null);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.CashbillService#getEPrintURL(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public String getEPrintURL(String CorpNum, String MgtKey, String UserID)
            throws PopbillException {
        if (MgtKey == null || MgtKey.isEmpty())
            throw new PopbillException(-99999999, "문서번호가 입력되지 않았습니다.");

        URLResponse response = httpget("/Cashbill/" + MgtKey + "?TG=EPRINT",
                CorpNum, UserID, URLResponse.class);

        return response.url;
    }

    /*
     * (non-Javadoc)
     * @see com.popbill.api.CashbillService#getMassPrintURL(java.lang.String, java.lang.String[])
     */
    @Override
    public String getMassPrintURL(String CorpNum, String[] MgtKeyList) throws PopbillException {
        return getMassPrintURL(CorpNum, MgtKeyList, null);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.CashbillService#getMassPrintURL(java.lang.String, java.lang.String[], java.lang.String)
     */
    @Override
    public String getMassPrintURL(String CorpNum, String[] MgtKeyList,
            String UserID) throws PopbillException {
        if (MgtKeyList == null || MgtKeyList.length == 0)
            throw new PopbillException(-99999999, "문서번호배열이 입력되지 않았습니다.");

        String PostData = toJsonString(MgtKeyList);

        URLResponse response = httppost("/Cashbill/Prints", CorpNum,
                PostData, UserID, URLResponse.class);

        return response.url;
    }

    /*
     * (non-Javadoc)
     * @see com.popbill.api.CashbillService#getMailURL(java.lang.String, java.lang.String)
     */
    @Override
    public String getMailURL(String CorpNum, String MgtKey)
            throws PopbillException {

        return getMailURL(CorpNum, MgtKey, null);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.CashbillService#getMailURL(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public String getMailURL(String CorpNum, String MgtKey, String UserID)
            throws PopbillException {
        if (MgtKey == null || MgtKey.isEmpty())
            throw new PopbillException(-99999999, "문서번호가 입력되지 않았습니다.");

        URLResponse response = httpget("/Cashbill/" + MgtKey + "?TG=MAIL",
                CorpNum, UserID, URLResponse.class);

        return response.url;
    }

    /*
     * (non-Javadoc)
     * @see com.popbill.api.CashbillService#getPopUpURL(java.lang.String, java.lang.String)
     */
    @Override
    public String getPopUpURL(String CorpNum, String MgtKey)
            throws PopbillException {

        return getPopUpURL(CorpNum, MgtKey, null);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.CashbillService#getPopUpURL(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public String getPopUpURL(String CorpNum, String MgtKey, String UserID)
            throws PopbillException {
        if (MgtKey == null || MgtKey.isEmpty())
            throw new PopbillException(-99999999, "문서번호가 입력되지 않았습니다.");

        URLResponse response = httpget("/Cashbill/" + MgtKey + "?TG=POPUP",
                CorpNum, UserID, URLResponse.class);

        return response.url;
    }
    
    /* (non-Javadoc)
     * @see com.popbill.api.CashbillService#getViewURL(java.lang.String, java.lang.String)
     */
    @Override
    public String getViewURL(String CorpNum, String MgtKey)
            throws PopbillException {

        return getViewURL(CorpNum, MgtKey, null);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.CashbillService#getViewURL(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public String getViewURL(String CorpNum, String MgtKey, String UserID)
            throws PopbillException {
        if (MgtKey == null || MgtKey.isEmpty())
            throw new PopbillException(-99999999, "문서번호가 입력되지 않았습니다.");

        URLResponse response = httpget("/Cashbill/" + MgtKey + "?TG=VIEW",
                CorpNum, UserID, URLResponse.class);

        return response.url;
    }

    @Override
    public CBIssueResponse registIssue(String CorpNum, Cashbill cashbill) throws PopbillException{
        return registIssue(CorpNum, cashbill, null, null);
    }

    /*
     * (non-Javadoc)
     * @see com.popbill.api.CashbillService#registIssue(java.lang.String, com.popbill.api.cashbill.Cashbill, java.lang.String)
     */
    @Override
    public CBIssueResponse registIssue(String CorpNum, Cashbill cashbill, String memo)
            throws PopbillException {
        return registIssue(CorpNum, cashbill, memo, null);
    }
    /* (non-Javadoc)
     * @see com.popbill.api.CashbillService#registIssue(java.lang.String, com.popbill.api.cashbill.Cashbill,
     *              java.lang.String, java.lang.String)
     */
    @Override
    public CBIssueResponse registIssue(String CorpNum, Cashbill cashbill, String memo,
            String UserID) throws PopbillException {
        
        return registIssue(CorpNum, cashbill, memo, null, null);
    }
    
    @Override
    public CBIssueResponse registIssue(String CorpNum, Cashbill cashbill, String memo, String UserID, 
             String emailSubject) throws PopbillException {
        if (cashbill == null)
            throw new PopbillException(-99999999, "현금영수증정보가 입력되지 않았습니다.");
        if (memo != null)
            cashbill.setMemo(memo);
        
        if (emailSubject != null)
            cashbill.setEmailSubject(emailSubject);

        String PostData = toJsonString(cashbill);
        

        return httppost("/Cashbill", CorpNum, PostData,
                UserID, "ISSUE", CBIssueResponse.class);
    }

    @Override
    public BulkResponse bulkSubmit(String CorpNum, String SubmitID, List<Cashbill> cashbillList) throws PopbillException {

        return bulkSubmit(CorpNum, SubmitID, cashbillList, null);

    }

    @Override
    public BulkResponse bulkSubmit(String CorpNum, String SubmitID, List<Cashbill> cashbillList,
            String UserID) throws PopbillException {
        if (SubmitID == null || SubmitID.equals(""))
            throw new PopbillException(-99999999, "제출아이디(SubmitID)가 입력되지 않았습니다.");

        if (cashbillList == null) {
            throw new PopbillException(-99999999, "현금영수증 정보가 입력되지 않았습니다.");
        }

        BulkCashbillSubmit cs = new BulkCashbillSubmit();
        cs.setCashList(cashbillList);
        String PostData = toJsonString(cs);

        return httpBulkPost("/Cashbill", CorpNum, SubmitID, PostData, UserID, "BULKISSUE", BulkResponse.class);
    }

    @Override
    public BulkCashbillResult getBulkResult(String CorpNum, String SubmitID) throws PopbillException {

        return getBulkResult(CorpNum, SubmitID, null);
    }
    
    @Override
    public BulkCashbillResult getBulkResult(String CorpNum, String SubmitID, String UserID) throws PopbillException {

        if (SubmitID == null)
            throw new PopbillException(-99999999, "제출아이디(SubmitID)가 입력되지 않았습니다.");
        return httpget("/Cashbill/BULK/" + SubmitID + "/State", CorpNum, UserID, BulkCashbillResult.class);
    }
    

    /*
     * (non-Javadoc)
     * @see com.popbill.api.CashbillService#search(java.lang.String, java.lang.String, java.lang.String, 
     *                                              java.lang.String, java.lang.String[], java.lang.String[], java.lang.String[],
     *                                              java.lang.String[], int, int, java.lang.String)
     */
    public CBSearchResult search(String CorpNum, String DType, String SDate,
                                 String EDate, String[] State, String[] TradeType,
                                 String[] TradeUsage, String[] TaxationType, int Page,
                                 int PerPage, String Order) throws PopbillException {
        return search(CorpNum, DType, SDate, EDate, State, TradeType, TradeUsage, null, TaxationType, null, Page, PerPage, Order, null);
    }

    /*
     * (non-Javadoc)
     * @see com.popbill.api.CashbillService#search(java.lang.String, java.lang.String, java.lang.String,
     *                                              java.lang.String, java.lang.String[], java.lang.String[], java.lang.String[],
     *                                              java.lang.String[], java.lang.String, int, int, java.lang.String)
     */
    @Override
    public CBSearchResult search(String CorpNum, String DType, String SDate,
                                 String EDate, String[] State, String[] TradeType,
                                 String[] TradeUsage, String[] TaxationType, String QString, int Page,
                                 int PerPage, String Order) throws PopbillException {
        return search(CorpNum, DType, SDate, EDate, State, TradeType, TradeUsage, null, TaxationType, QString, Page, PerPage, Order, null);
    }

    /*
     * (non-Javadoc)
     * @see com.popbill.api.CashbillService#search(java.lang.String, java.lang.String, java.lang.String,
     *                                              java.lang.String, java.lang.String[], java.lang.String[], java.lang.String[],
     *                                              java.lang.String[], java.lang.String[], java.lang.String, int, int, java.lang.String)
     */
    @Override
    public CBSearchResult search(String CorpNum, String DType, String SDate, String EDate,
                                 String[] State, String[] TradeType, String[] TradeUsage, String[] TradeOpt, String[] TaxationType,
                                 String QString, int Page, int PerPage, String Order) throws PopbillException {
        return search(CorpNum, DType, SDate, EDate, State, TradeType, TradeUsage, TradeOpt, TaxationType, QString, Page, PerPage, Order, null);
    }

    /*
     * (non-Javadoc)
     * @see com.popbill.api.CashbillService#search(java.lang.String, java.lang.String, java.lang.String,
     *                                              java.lang.String, java.lang.String[], java.lang.String[], java.lang.String[],
     *                                              java.lang.String[], java.lang.String[], java.lang.String, int, int, java.lang.String,
     *                                              java.lang.String)
     */
    @Override
    public CBSearchResult search(String CorpNum, String DType, String SDate, String EDate,
                                 String[] State, String[] TradeType, String[] TradeUsage, String[] TradeOpt, String[] TaxationType,
                                 String QString, int Page, int PerPage, String Order, String FranchiseTaxRegID) throws PopbillException {
        if (DType == null || DType.isEmpty())
            throw new PopbillException(-99999999, "검색일자유형이  입력되지 않았습니다.");
        if (SDate == null || SDate.isEmpty())
            throw new PopbillException(-99999999, "시작일자가 입력되지 않았습니다.");
        if (EDate == null || EDate.isEmpty())
            throw new PopbillException(-99999999, "종료일자가 입력되지 않았습니다.");

        String uri = "/Cashbill/Search?DType=" + DType;

        uri += "&SDate=" + SDate;
        uri += "&EDate=" + EDate;
        uri += "&State=" + Arrays.toString(State)
                .replaceAll("\\[|\\]|\\s", "");
        uri += "&TradeType=" + Arrays.toString(TradeType)
                .replaceAll("\\[|\\]|\\s", "");
        uri += "&TradeUsage=" + Arrays.toString(TradeUsage)
                .replaceAll("\\[|\\]|\\s", "");
        if (TradeOpt != null){
            uri += "&TradeOpt=" + Arrays.toString(TradeOpt)
                    .replaceAll("\\[|\\]|\\s", "");
        }
        uri += "&TaxationType=" + Arrays.toString(TaxationType)
                .replaceAll("\\[|\\]|\\s", "");
        if (QString != null && QString != "")
            uri += "&QString=" + QString;
        uri += "&Page=" + Integer.toString(Page);
        uri += "&PerPage="+ Integer.toString(PerPage);
        uri += "&Order=" + Order;
        if (FranchiseTaxRegID != null && FranchiseTaxRegID != "") {
            uri += "&FranchiseTaxRegID=" + FranchiseTaxRegID;
        }

        return httpget(uri, CorpNum, null, CBSearchResult.class);
    }

    /*
     * (non-Javadoc)
     * @see com.popbill.api.CashbillService#getChargeInfo(java.lang.String)
     */
    @Override
    public ChargeInfo getChargeInfo(String CorpNum) throws PopbillException {
        return httpget("/Cashbill/ChargeInfo", CorpNum, null, ChargeInfo.class);
    }

    @Override
    public Response revokeRegister(String CorpNum, String mgtKey,
            String orgConfirmNum, String orgTradeDate) throws PopbillException {

        return revokeRegister(CorpNum, mgtKey, orgConfirmNum, orgTradeDate, false, false,
                null, null, null, null, null, null);
    }

    @Override
    public Response revokeRegister(String CorpNum, String mgtKey,
            String orgConfirmNum, String orgTradeDate, Boolean smssendYN)
            throws PopbillException {

        return revokeRegister(CorpNum, mgtKey, orgConfirmNum, orgTradeDate, smssendYN,
                false, null, null, null, null, null, null);
    }

    @Override
    public Response revokeRegister(String CorpNum, String mgtKey,
            String orgConfirmNum, String orgTradeDate, Boolean smssendYN,
            String UserID) throws PopbillException {

        return revokeRegister(CorpNum, mgtKey, orgConfirmNum, orgTradeDate, smssendYN,
                false, null, null, null, null, null, UserID);
    }


    @Override
    public Response revokeRegister(String CorpNum, String mgtKey, String orgConfirmNum,
            String orgTradeDate, Boolean smssendYN, Boolean isPartCancel, Integer cancelType,
            String supplyCost, String tax, String serviceFee, String totalAmount) throws PopbillException {

        return revokeRegister(CorpNum, mgtKey, orgConfirmNum, orgTradeDate, smssendYN, isPartCancel,
                cancelType, supplyCost, tax, serviceFee, totalAmount, null);
    }


    @Override
    public Response revokeRegister(String CorpNum, String mgtKey,
            String orgConfirmNum, String orgTradeDate, Boolean smssendYN, Boolean isPartCancel,
            Integer cancelType, String supplyCost, String tax, String serviceFee, String totalAmount,
            String UserID) throws PopbillException {

        if (mgtKey == null)
            throw new PopbillException(-99999999, "취소현금영수증 문서번호가 입력되지 않았습니다.");

        RevokeRequest request = new RevokeRequest();

        request.mgtKey = mgtKey;
        request.orgConfirmNum = orgConfirmNum;
        request.orgTradeDate = orgTradeDate;
        request.smssendYN = smssendYN;
        request.isPartCancel = isPartCancel;
        request.cancelType = cancelType;
        request.supplyCost = supplyCost;
        request.tax = tax;
        request.serviceFee = serviceFee;
        request.totalAmount = totalAmount;

        String PostData = toJsonString(request);

        return httppost("/Cashbill", CorpNum, PostData,
                UserID, "REVOKE", Response.class);
    }

    @Override
    public CBIssueResponse revokeRegistIssue(String CorpNum, String mgtKey,
            String orgConfirmNum, String orgTradeDate) throws PopbillException {

        return revokeRegistIssue(CorpNum, mgtKey, orgConfirmNum, orgTradeDate, false, null,
                false, null, null, null, null, null, null);
    }

    @Override
    public CBIssueResponse revokeRegistIssue(String CorpNum, String mgtKey,
            String orgConfirmNum, String orgTradeDate, Boolean smssendYN)
            throws PopbillException {

        return revokeRegistIssue(CorpNum, mgtKey, orgConfirmNum, orgTradeDate, smssendYN, null,
                false, null, null, null, null, null, null);
    }

    @Override
    public CBIssueResponse revokeRegistIssue(String CorpNum, String mgtKey,
            String orgConfirmNum, String orgTradeDate, Boolean smssendYN,
            String memo) throws PopbillException {

        return revokeRegistIssue(CorpNum, mgtKey, orgConfirmNum, orgTradeDate, smssendYN, memo,
                false, null, null, null, null, null, null);
    }

    @Override
    public CBIssueResponse revokeRegistIssue(String CorpNum, String mgtKey,
            String orgConfirmNum, String orgTradeDate, Boolean smssendYN,
            String memo, String userID) throws PopbillException {

        return revokeRegistIssue(CorpNum, mgtKey, orgConfirmNum, orgTradeDate, smssendYN, memo,
                false, null, null, null, null, null, userID, null);
    }
    
    @Override
    public CBIssueResponse revokeRegistIssue(String CorpNum, String mgtKey,
            String orgConfirmNum, String orgTradeDate, Boolean smssendYN,
            String memo, String userID, String emailSubject) throws PopbillException {

        return revokeRegistIssue(CorpNum, mgtKey, orgConfirmNum, orgTradeDate, smssendYN, memo,
                false, null, null, null, null, null, userID, emailSubject);
    }

    @Override
    public CBIssueResponse revokeRegistIssue(String CorpNum, String mgtKey, String orgConfirmNum,
            String orgTradeDate, Boolean smssendYN, String memo,
            Boolean isPartCancel, Integer cancelType, String supplyCost,
            String tax, String serviceFee, String totalAmount) throws PopbillException {

        return revokeRegistIssue(CorpNum, mgtKey, orgConfirmNum, orgTradeDate, smssendYN, memo,
                isPartCancel, cancelType, supplyCost, tax, serviceFee, totalAmount, null);
    }

    @Override
    public CBIssueResponse revokeRegistIssue(String CorpNum, String mgtKey, String orgConfirmNum,
            String orgTradeDate, Boolean smssendYN, String memo,
            Boolean isPartCancel, Integer cancelType, String supplyCost,
            String tax, String serviceFee, String totalAmount, String userID) throws PopbillException {

        return revokeRegistIssue(CorpNum, mgtKey, orgConfirmNum, orgTradeDate, smssendYN, memo,
                isPartCancel, cancelType, supplyCost, tax, serviceFee, totalAmount, null, null);
    }
    
    @Override
    public CBIssueResponse revokeRegistIssue(String CorpNum, String mgtKey, String orgConfirmNum,
            String orgTradeDate, Boolean smssendYN, String memo,
            Boolean isPartCancel, Integer cancelType, String supplyCost,
            String tax, String serviceFee, String totalAmount, String userID, String emailSubject) throws PopbillException {

        if (mgtKey == null)
            throw new PopbillException(-99999999, "취소현금영수증 문서번호가 입력되지 않았습니다.");
        
        RevokeRequest request = new RevokeRequest();
        
        if (emailSubject != null) request.emailSubject = emailSubject;

        request.mgtKey = mgtKey;
        request.orgConfirmNum = orgConfirmNum;
        request.orgTradeDate = orgTradeDate;
        request.smssendYN = smssendYN;
        request.memo = memo;
        request.isPartCancel = isPartCancel;
        request.cancelType = cancelType;
        request.supplyCost = supplyCost;
        request.serviceFee = serviceFee;
        request.tax = tax;
        request.totalAmount = totalAmount;

        String PostData = toJsonString(request);

        return httppost("/Cashbill", CorpNum, PostData,
                userID, "REVOKEISSUE", CBIssueResponse.class);
    }
    

    /*
     * (non-Javadoc)
     * @see com.popbill.api.CashbillService#updateEmailConfig(java.lang.String, java.lang.String, java.lang.Boolean)
     */
    @Override
    public Response updateEmailConfig(String CorpNum, String EmailType, Boolean SendYN) throws PopbillException {
        return updateEmailConfig(CorpNum, EmailType, SendYN, null);
    }

    /*
     * (non-Javadoc)
     * @see com.popbill.api.CashbillService#updateEmailConfig(java.lang.String, java.lang.String, java.lang.Boolean, java.lang.String)
     */
    @Override
    public Response updateEmailConfig(String CorpNum, String EmailType, Boolean SendYN, String UserID)
            throws PopbillException {
        if (SendYN == null)
            throw new PopbillException(-99999999, "메일전송여부(SendYN)가 입력되지 않았습니다.");
        if (EmailType == null || EmailType.isEmpty())
            throw new PopbillException(-99999999, "메일전송유형(EmailType)이 입력되지 않았습니다.");

        return httppost("/Cashbill/EmailSendConfig?EmailType=" + EmailType + "&SendYN=" + String.valueOf(SendYN),
                CorpNum, null, UserID, "", Response.class);
    }

    /*
     * (non-Javadoc)
     * @see com.popbill.api.CashbillService#listEmailConfig(java.lang.String)
     */
    @Override
    public EmailSendConfig[] listEmailConfig(String CorpNum) throws PopbillException {
        return httpget("/Cashbill/EmailSendConfig", CorpNum, null, EmailSendConfig[].class);
    }

    /*
     * (non-Javadoc)
     * @see com.popbill.api.CashbillService#listEmailConfig(java.lang.String, java.lang.String)
     */
    @Override
    public EmailSendConfig[] listEmailConfig(String CorpNum, String UserID) throws PopbillException {
        return httpget("/Cashbill/EmailSendConfig", CorpNum, UserID, EmailSendConfig[].class);
    }

    protected class MemoRequest {
        public MemoRequest(String memo){
            this.memo = memo;
        }

        public String memo;
    }

    protected class ResendRequest {
        public String receiver;
        public String sender = null;
        public String contents = null;
    }

    protected class RevokeRequest {
        public String mgtKey;
        public String orgTradeDate;
        public String orgConfirmNum;
        public Boolean smssendYN;
        public String memo;
        public Boolean isPartCancel;
        public Integer cancelType;
        public String supplyCost;
        public String tax;
        public String serviceFee;
        public String totalAmount;
        public String emailSubject;
    }
}

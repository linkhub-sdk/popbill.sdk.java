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
package com.popbill.api.taxinvoice;

import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.popbill.api.AttachedFile;
import com.popbill.api.BaseServiceImp;
import com.popbill.api.ChargeInfo;
import com.popbill.api.PopbillException;
import com.popbill.api.Response;
import com.popbill.api.TaxinvoiceService;
import com.popbill.api.EmailSendConfig;
import com.popbill.api.IssueResponse;

/**
 * Implementation of Popbill TaxinvoiceService Interface
 *
 * @author KimSeongjun
 * @version 1.0.0
 * @see com.popbill.api.TaxinvoiceService
 */
public class TaxinvoiceServiceImp extends BaseServiceImp implements TaxinvoiceService {

    @Override
    protected List<String> getScopes() {
        return Arrays.asList("110");
    }

    /* (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#getUnitCost(java.lang.String)
     */
    @Override
    public float getUnitCost(String CorpNum) throws PopbillException {
        UnitCostResponse response = httpget("/Taxinvoice?cfg=UNITCOST",
                CorpNum, null, UnitCostResponse.class);

        return response.unitCost;
    }

    /* (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#getCertificateExpireDate(java.lang.String)
     */
    @Override
    public Date getCertificateExpireDate(String CorpNum)
            throws PopbillException {

        CertResponse response = httpget("/Taxinvoice?cfg=CERT", CorpNum, null,
                CertResponse.class);

        try {
            return new SimpleDateFormat("yyyyMMddHHmmss")
                    .parse(response.certificateExpiration);
        } catch (ParseException e) {
            throw new PopbillException(-99999999, "날자형식 포맷변환 실패["
                    + response.certificateExpiration + "]", e);
        }
    }
    
    /*
     * (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#getURL(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public String getURL(String CorpNum, String TOGO)
            throws PopbillException {

        return getURL(CorpNum, null, TOGO);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#getURL(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public String getURL(String CorpNum, String UserID, String TOGO)
            throws PopbillException {

        URLResponse response = httpget("/Taxinvoice?TG=" + TOGO, CorpNum,
                UserID, URLResponse.class);

        return response.url;
    }

    /* (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#checkMgtKeyInUse(java.lang.String, com.popbill.api.taxinvoice.MgtKeyType, java.lang.String)
     */
    @Override
    public boolean checkMgtKeyInUse(String CorpNum, MgtKeyType KeyType,
                                    String MgtKey) throws PopbillException {

        if (KeyType == null)
            throw new PopbillException(-99999999, "문서번호 형태가 입력되지 않았습니다.");
        if (MgtKey == null || MgtKey.isEmpty())
            throw new PopbillException(-99999999, "문서번호가 입력되지 않았습니다.");

        try {
            TaxinvoiceInfo info = httpget("/Taxinvoice/" + KeyType.name() + "/"
                    + MgtKey, CorpNum, null, TaxinvoiceInfo.class);

            return (info.getItemKey() == null || info.getItemKey().isEmpty()) == false;
        } catch (PopbillException PE) {
            if (PE.getCode() == -11000005)
                return false;
            throw PE;
        }
    }

    /* (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#getEmailPublicKeys(java.lang.String)
     */
    @Override
    public EmailPublicKey[] getEmailPublicKeys(String CorpNum)
            throws PopbillException {

        return httpget("/Taxinvoice/EmailPublicKeys", CorpNum, null,
                EmailPublicKey[].class);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#register(java.lang.String, com.popbill.api.taxinvoice.Taxinvoice)
     */
    @Override
    public Response register(String CorpNum, Taxinvoice taxinvoice)
            throws PopbillException {
        return register(CorpNum, taxinvoice, null);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#register(java.lang.String, com.popbill.api.taxinvoice.Taxinvoice, java.lang.String)
     */
    @Override
    public Response register(String CorpNum, Taxinvoice taxinvoice,
                             String UserID) throws PopbillException {
        return register(CorpNum, taxinvoice, UserID, false);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#register(java.lang.String, com.popbill.api.taxinvoice.Taxinvoice, java.lang.String, boolean)
     */
    @Override
    public Response register(String CorpNum, Taxinvoice taxinvoice,
                             String UserID, boolean writeSpecification) throws PopbillException {
        if (taxinvoice == null)
            throw new PopbillException(-99999999, "세금계산서 정보가 입력되지 않았습니다.");

        String PostData = toJsonString(taxinvoice);

        if (writeSpecification) {
            PostData = "{\"writeSpecification\":true," + PostData.substring(1);
        }

        return httppost("/Taxinvoice", CorpNum, PostData, UserID,
                Response.class);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#update(java.lang.String, com.popbill.api.taxinvoice.MgtKeyType, java.lang.String, com.popbill.api.taxinvoice.Taxinvoice)
     */
    @Override
    public Response update(String CorpNum, MgtKeyType KeyType, String MgtKey,
                           Taxinvoice taxinvoice) throws PopbillException {
        return update(CorpNum, KeyType, MgtKey, taxinvoice, null);

    }

    /* (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#update(java.lang.String, com.popbill.api.taxinvoice.MgtKeyType, java.lang.String, com.popbill.api.taxinvoice.Taxinvoice, java.lang.String)
     */
    @Override
    public Response update(String CorpNum, MgtKeyType KeyType, String MgtKey,
                           Taxinvoice taxinvoice, String UserID) throws PopbillException {
        if (KeyType == null)
            throw new PopbillException(-99999999, "문서번호 형태가 입력되지 않았습니다.");
        if (MgtKey == null || MgtKey.isEmpty())
            throw new PopbillException(-99999999, "문서번호가 입력되지 않았습니다.");
        if (taxinvoice == null)
            throw new PopbillException(-99999999, "세금계산서 정보가 입력되지 않았습니다.");

        String PostData = toJsonString(taxinvoice);

        return httppost("/Taxinvoice/" + KeyType.name() + "/" + MgtKey,
                CorpNum, PostData, UserID, "PATCH", Response.class);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#delete(java.lang.String, com.popbill.api.taxinvoice.MgtKeyType, java.lang.String)
     */
    @Override
    public Response delete(String CorpNum, MgtKeyType KeyType, String MgtKey)
            throws PopbillException {
        return delete(CorpNum, KeyType, MgtKey, null);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#delete(java.lang.String, com.popbill.api.taxinvoice.MgtKeyType, java.lang.String, java.lang.String)
     */
    @Override
    public Response delete(String CorpNum, MgtKeyType KeyType, String MgtKey,
                           String UserID) throws PopbillException {
        if (KeyType == null)
            throw new PopbillException(-99999999, "문서번호 형태가 입력되지 않았습니다.");
        if (MgtKey == null || MgtKey.isEmpty())
            throw new PopbillException(-99999999, "문서번호가 입력되지 않았습니다.");

        return httppost("/Taxinvoice/" + KeyType.name() + "/" + MgtKey,
                CorpNum, null, UserID, "DELETE", Response.class);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#send(java.lang.String, com.popbill.api.taxinvoice.MgtKeyType, java.lang.String, java.lang.String)
     */
    @Override
    public Response send(String CorpNum, MgtKeyType KeyType, String MgtKey,
                         String Memo) throws PopbillException {
        return send(CorpNum, KeyType, MgtKey, Memo, null);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#send(java.lang.String, com.popbill.api.taxinvoice.MgtKeyType, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public Response send(String CorpNum, MgtKeyType KeyType, String MgtKey,
                         String Memo, String UserID) throws PopbillException {
        return send(CorpNum, KeyType, MgtKey, Memo, null, UserID);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#send(java.lang.String, com.popbill.api.taxinvoice.MgtKeyType, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public Response send(String CorpNum, MgtKeyType KeyType, String MgtKey,
                         String Memo, String EmailSubject, String UserID) throws PopbillException {
        if (KeyType == null)
            throw new PopbillException(-99999999, "문서번호 형태가 입력되지 않았습니다.");
        if (MgtKey == null || MgtKey.isEmpty())
            throw new PopbillException(-99999999, "문서번호가 입력되지 않았습니다.");

        SendRequest request = new SendRequest();
        request.memo = Memo;
        request.emailSubject = EmailSubject;

        String PostData = toJsonString(request);

        return httppost("/Taxinvoice/" + KeyType.name() + "/" + MgtKey,
                CorpNum, PostData, UserID, "SEND", Response.class);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#cancelSend(java.lang.String, com.popbill.api.taxinvoice.MgtKeyType, java.lang.String, java.lang.String)
     */
    @Override
    public Response cancelSend(String CorpNum, MgtKeyType KeyType,
                               String MgtKey, String Memo) throws PopbillException {
        return cancelSend(CorpNum, KeyType, MgtKey, Memo, null);

    }

    /* (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#cancelSend(java.lang.String, com.popbill.api.taxinvoice.MgtKeyType, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public Response cancelSend(String CorpNum, MgtKeyType KeyType,
                               String MgtKey, String Memo, String UserID) throws PopbillException {
        if (KeyType == null)
            throw new PopbillException(-99999999, "문서번호 형태가 입력되지 않았습니다.");
        if (MgtKey == null || MgtKey.isEmpty())
            throw new PopbillException(-99999999, "문서번호가 입력되지 않았습니다.");

        String PostData = toJsonString(new MemoRequest(Memo));

        return httppost("/Taxinvoice/" + KeyType.name() + "/" + MgtKey,
                CorpNum, PostData, UserID, "CANCELSEND", Response.class);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#accept(java.lang.String, com.popbill.api.taxinvoice.MgtKeyType, java.lang.String, java.lang.String)
     */
    @Override
    public Response accept(String CorpNum, MgtKeyType KeyType, String MgtKey,
                           String Memo) throws PopbillException {
        return accept(CorpNum, KeyType, MgtKey, Memo, null);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#accept(java.lang.String, com.popbill.api.taxinvoice.MgtKeyType, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public Response accept(String CorpNum, MgtKeyType KeyType, String MgtKey,
                           String Memo, String UserID) throws PopbillException {
        if (KeyType == null)
            throw new PopbillException(-99999999, "문서번호 형태가 입력되지 않았습니다.");
        if (MgtKey == null || MgtKey.isEmpty())
            throw new PopbillException(-99999999, "문서번호가 입력되지 않았습니다.");

        String PostData = toJsonString(new MemoRequest(Memo));

        return httppost("/Taxinvoice/" + KeyType.name() + "/" + MgtKey,
                CorpNum, PostData, UserID, "ACCEPT", Response.class);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#deny(java.lang.String, com.popbill.api.taxinvoice.MgtKeyType, java.lang.String, java.lang.String)
     */
    @Override
    public Response deny(String CorpNum, MgtKeyType KeyType, String MgtKey,
                         String Memo) throws PopbillException {
        return deny(CorpNum, KeyType, MgtKey, Memo, null);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#deny(java.lang.String, com.popbill.api.taxinvoice.MgtKeyType, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public Response deny(String CorpNum, MgtKeyType KeyType, String MgtKey,
                         String Memo, String UserID) throws PopbillException {
        if (KeyType == null)
            throw new PopbillException(-99999999, "문서번호 형태가 입력되지 않았습니다.");
        if (MgtKey == null || MgtKey.isEmpty())
            throw new PopbillException(-99999999, "문서번호가 입력되지 않았습니다.");

        String PostData = toJsonString(new MemoRequest(Memo));

        return httppost("/Taxinvoice/" + KeyType.name() + "/" + MgtKey,
                CorpNum, PostData, UserID, "DENY", Response.class);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#issue(java.lang.String, com.popbill.api.taxinvoice.MgtKeyType, java.lang.String, java.lang.String)
     */
    @Override
    public IssueResponse issue(String CorpNum, MgtKeyType KeyType, String MgtKey,
                          String Memo) throws PopbillException {
        return issue(CorpNum, KeyType, MgtKey, Memo, null);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#issue(java.lang.String, com.popbill.api.taxinvoice.MgtKeyType, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public IssueResponse issue(String CorpNum, MgtKeyType KeyType, String MgtKey,
                          String Memo, String UserID) throws PopbillException {
        return issue(CorpNum, KeyType, MgtKey, Memo, null, false, UserID);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#issue(java.lang.String, com.popbill.api.taxinvoice.MgtKeyType, java.lang.String, java.lang.String, boolean, java.lang.String)
     */
    @Override
    public IssueResponse issue(String CorpNum, MgtKeyType KeyType, String MgtKey,
                          String Memo, boolean ForceIssue, String UserID)
            throws PopbillException {
        return issue(CorpNum, KeyType, MgtKey, Memo, null, ForceIssue, UserID);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#issue(java.lang.String, com.popbill.api.taxinvoice.MgtKeyType, java.lang.String, java.lang.String, java.lang.String, boolean, java.lang.String)
     */
    @Override
    public IssueResponse issue(String CorpNum, MgtKeyType KeyType, String MgtKey,
                          String Memo, String EmailSubject, boolean ForceIssue, String UserID)
            throws PopbillException {
        if (KeyType == null)
            throw new PopbillException(-99999999, "문서번호 형태가 입력되지 않았습니다.");
        if (MgtKey == null || MgtKey.isEmpty())
            throw new PopbillException(-99999999, "문서번호가 입력되지 않았습니다.");

        IssueRequest request = new IssueRequest();
        request.memo = Memo;
        request.emailSubject = EmailSubject;
        request.forceIssue = ForceIssue;

        String PostData = toJsonString(request);

        return httppost("/Taxinvoice/" + KeyType.name() + "/" + MgtKey,
                CorpNum, PostData, UserID, "ISSUE", IssueResponse.class);
    }
    
   

    /* (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#cancelIssue(java.lang.String, com.popbill.api.taxinvoice.MgtKeyType, java.lang.String, java.lang.String)
     */
    @Override
    public Response cancelIssue(String CorpNum, MgtKeyType KeyType,
                                String MgtKey, String Memo) throws PopbillException {
        return cancelIssue(CorpNum, KeyType, MgtKey, Memo, null);

    }

    /* (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#cancelIssue(java.lang.String, com.popbill.api.taxinvoice.MgtKeyType, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public Response cancelIssue(String CorpNum, MgtKeyType KeyType,
                                String MgtKey, String Memo, String UserID) throws PopbillException {
        if (KeyType == null)
            throw new PopbillException(-99999999, "문서번호 형태가 입력되지 않았습니다.");
        if (MgtKey == null || MgtKey.isEmpty())
            throw new PopbillException(-99999999, "문서번호가 입력되지 않았습니다.");

        String PostData = toJsonString(new MemoRequest(Memo));

        return httppost("/Taxinvoice/" + KeyType.name() + "/" + MgtKey,
                CorpNum, PostData, UserID, "CANCELISSUE", Response.class);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#request(java.lang.String, com.popbill.api.taxinvoice.MgtKeyType, java.lang.String, java.lang.String)
     */
    @Override
    public Response request(String CorpNum, MgtKeyType KeyType, String MgtKey,
                            String Memo) throws PopbillException {
        return request(CorpNum, KeyType, MgtKey, Memo, null);

    }

    /* (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#request(java.lang.String, com.popbill.api.taxinvoice.MgtKeyType, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public Response request(String CorpNum, MgtKeyType KeyType, String MgtKey,
                            String Memo, String UserID) throws PopbillException {
        if (KeyType == null)
            throw new PopbillException(-99999999, "문서번호 형태가 입력되지 않았습니다.");
        if (MgtKey == null || MgtKey.isEmpty())
            throw new PopbillException(-99999999, "문서번호가 입력되지 않았습니다.");

        String PostData = toJsonString(new MemoRequest(Memo));

        return httppost("/Taxinvoice/" + KeyType.name() + "/" + MgtKey,
                CorpNum, PostData, UserID, "REQUEST", Response.class);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#refuse(java.lang.String, com.popbill.api.taxinvoice.MgtKeyType, java.lang.String, java.lang.String)
     */
    @Override
    public Response refuse(String CorpNum, MgtKeyType KeyType, String MgtKey,
                           String Memo) throws PopbillException {
        return refuse(CorpNum, KeyType, MgtKey, Memo, null);

    }

    /* (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#refuse(java.lang.String, com.popbill.api.taxinvoice.MgtKeyType, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public Response refuse(String CorpNum, MgtKeyType KeyType, String MgtKey,
                           String Memo, String UserID) throws PopbillException {
        if (KeyType == null)
            throw new PopbillException(-99999999, "문서번호 형태가 입력되지 않았습니다.");
        if (MgtKey == null || MgtKey.isEmpty())
            throw new PopbillException(-99999999, "문서번호가 입력되지 않았습니다.");

        String PostData = toJsonString(new MemoRequest(Memo));

        return httppost("/Taxinvoice/" + KeyType.name() + "/" + MgtKey,
                CorpNum, PostData, UserID, "REFUSE", Response.class);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#cancelRequest(java.lang.String, com.popbill.api.taxinvoice.MgtKeyType, java.lang.String, java.lang.String)
     */
    @Override
    public Response cancelRequest(String CorpNum, MgtKeyType KeyType,
                                  String MgtKey, String Memo) throws PopbillException {
        return cancelRequest(CorpNum, KeyType, MgtKey, Memo, null);

    }

    /* (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#cancelRequest(java.lang.String, com.popbill.api.taxinvoice.MgtKeyType, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public Response cancelRequest(String CorpNum, MgtKeyType KeyType,
                                  String MgtKey, String Memo, String UserID) throws PopbillException {
        if (KeyType == null)
            throw new PopbillException(-99999999, "문서번호 형태가 입력되지 않았습니다.");
        if (MgtKey == null || MgtKey.isEmpty())
            throw new PopbillException(-99999999, "문서번호가 입력되지 않았습니다.");

        String PostData = toJsonString(new MemoRequest(Memo));

        return httppost("/Taxinvoice/" + KeyType.name() + "/" + MgtKey,
                CorpNum, PostData, UserID, "CANCELREQUEST", Response.class);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#sendToNTS(java.lang.String, com.popbill.api.taxinvoice.MgtKeyType, java.lang.String)
     */
    @Override
    public Response sendToNTS(String CorpNum, MgtKeyType KeyType, String MgtKey)
            throws PopbillException {
        return sendToNTS(CorpNum, KeyType, MgtKey, null);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#sendToNTS(java.lang.String, com.popbill.api.taxinvoice.MgtKeyType, java.lang.String, java.lang.String)
     */
    @Override
    public Response sendToNTS(String CorpNum, MgtKeyType KeyType,
                              String MgtKey, String UserID) throws PopbillException {
        if (KeyType == null)
            throw new PopbillException(-99999999, "문서번호 형태가 입력되지 않았습니다.");
        if (MgtKey == null || MgtKey.isEmpty())
            throw new PopbillException(-99999999, "문서번호가 입력되지 않았습니다.");

        return httppost("/Taxinvoice/" + KeyType.name() + "/" + MgtKey,
                CorpNum, null, UserID, "NTS", Response.class);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#sendEmail(java.lang.String, com.popbill.api.taxinvoice.MgtKeyType, java.lang.String, java.lang.String)
     */
    @Override
    public Response sendEmail(String CorpNum, MgtKeyType KeyType,
                              String MgtKey, String Receiver) throws PopbillException {
        return sendEmail(CorpNum, KeyType, MgtKey, Receiver, null);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#sendEmail(java.lang.String, com.popbill.api.taxinvoice.MgtKeyType, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public Response sendEmail(String CorpNum, MgtKeyType KeyType,
                              String MgtKey, String Receiver, String UserID)
            throws PopbillException {
        if (KeyType == null)
            throw new PopbillException(-99999999, "문서번호 형태가 입력되지 않았습니다.");
        if (MgtKey == null || MgtKey.isEmpty())
            throw new PopbillException(-99999999, "문서번호가 입력되지 않았습니다.");

        ResendRequest request = new ResendRequest();

        request.receiver = Receiver;

        String PostData = toJsonString(request);

        return httppost("/Taxinvoice/" + KeyType.name() + "/" + MgtKey,
                CorpNum, PostData, UserID, "EMAIL", Response.class);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#sendSMS(java.lang.String, com.popbill.api.taxinvoice.MgtKeyType, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public Response sendSMS(String CorpNum, MgtKeyType KeyType, String MgtKey,
                            String Sender, String Receiver, String Contents)
            throws PopbillException {
        return sendSMS(CorpNum, KeyType, MgtKey, Sender, Receiver, Contents,
                null);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#sendSMS(java.lang.String, com.popbill.api.taxinvoice.MgtKeyType, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public Response sendSMS(String CorpNum, MgtKeyType KeyType, String MgtKey,
                            String Sender, String Receiver, String Contents, String UserID)
            throws PopbillException {
        if (KeyType == null)
            throw new PopbillException(-99999999, "문서번호 형태가 입력되지 않았습니다.");
        if (MgtKey == null || MgtKey.isEmpty())
            throw new PopbillException(-99999999, "문서번호가 입력되지 않았습니다.");

        ResendRequest request = new ResendRequest();

        request.sender = Sender;
        request.receiver = Receiver;
        request.contents = Contents;

        String PostData = toJsonString(request);

        return httppost("/Taxinvoice/" + KeyType.name() + "/" + MgtKey,
                CorpNum, PostData, UserID, "SMS", Response.class);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#sendFAX(java.lang.String, com.popbill.api.taxinvoice.MgtKeyType, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public Response sendFAX(String CorpNum, MgtKeyType KeyType, String MgtKey,
                            String Sender, String Receiver) throws PopbillException {
        return sendFAX(CorpNum, KeyType, MgtKey, Sender, Receiver, null);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#sendFAX(java.lang.String, com.popbill.api.taxinvoice.MgtKeyType, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public Response sendFAX(String CorpNum, MgtKeyType KeyType, String MgtKey,
                            String Sender, String Receiver, String UserID)
            throws PopbillException {
        if (KeyType == null)
            throw new PopbillException(-99999999, "문서번호 형태가 입력되지 않았습니다.");
        if (MgtKey == null || MgtKey.isEmpty())
            throw new PopbillException(-99999999, "문서번호가 입력되지 않았습니다.");

        ResendRequest request = new ResendRequest();

        request.sender = Sender;
        request.receiver = Receiver;

        String PostData = toJsonString(request);

        return httppost("/Taxinvoice/" + KeyType.name() + "/" + MgtKey,
                CorpNum, PostData, UserID, "FAX", Response.class);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#getDetailInfo(java.lang.String, com.popbill.api.taxinvoice.MgtKeyType, java.lang.String)
     */
    @Override
    public Taxinvoice getDetailInfo(String CorpNum, MgtKeyType KeyType,
                                    String MgtKey) throws PopbillException {
        if (KeyType == null)
            throw new PopbillException(-99999999, "문서번호 형태가 입력되지 않았습니다.");
        if (MgtKey == null || MgtKey.isEmpty())
            throw new PopbillException(-99999999, "문서번호가 입력되지 않았습니다.");

        return httpget("/Taxinvoice/" + KeyType.name() + "/" + MgtKey
                + "?Detail", CorpNum, null, Taxinvoice.class);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#getInfo(java.lang.String, com.popbill.api.taxinvoice.MgtKeyType, java.lang.String)
     */
    @Override
    public TaxinvoiceInfo getInfo(String CorpNum, MgtKeyType KeyType,
                                  String MgtKey) throws PopbillException {
        if (KeyType == null)
            throw new PopbillException(-99999999, "문서번호 형태가 입력되지 않았습니다.");
        if (MgtKey == null || MgtKey.isEmpty())
            throw new PopbillException(-99999999, "문서번호가 입력되지 않았습니다.");

        return httpget("/Taxinvoice/" + KeyType.name() + "/" + MgtKey, CorpNum,
                null, TaxinvoiceInfo.class);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#getInfos(java.lang.String, com.popbill.api.taxinvoice.MgtKeyType, java.lang.String[])
     */
    @Override
    public TaxinvoiceInfo[] getInfos(String CorpNum, MgtKeyType KeyType,
                                     String[] MgtKeyList) throws PopbillException {
        if (KeyType == null)
            throw new PopbillException(-99999999, "문서번호 형태가 입력되지 않았습니다.");
        if (MgtKeyList == null || MgtKeyList.length == 0)
            throw new PopbillException(-99999999, "문서번호 목록이 입력되지 않았습니다.");

        String PostData = toJsonString(MgtKeyList);

        return httppost("/Taxinvoice/" + KeyType.name(), CorpNum, PostData,
                null, TaxinvoiceInfo[].class);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#getLogs(java.lang.String, com.popbill.api.taxinvoice.MgtKeyType, java.lang.String)
     */
    @Override
    public TaxinvoiceLog[] getLogs(String CorpNum, MgtKeyType KeyType,
                                   String MgtKey) throws PopbillException {
        if (KeyType == null)
            throw new PopbillException(-99999999, "문서번호 형태가 입력되지 않았습니다.");
        if (MgtKey == null || MgtKey.isEmpty())
            throw new PopbillException(-99999999, "문서번호가 입력되지 않았습니다.");

        return httpget(
                "/Taxinvoice/" + KeyType.name() + "/" + MgtKey + "/Logs",
                CorpNum, null, TaxinvoiceLog[].class);
    }

    /*
     * (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#getPopUpURL(java.lang.String, com.popbill.api.taxinvoice.MgtKeyType, java.lang.String)
     */
    @Override
    public String getPopUpURL(String CorpNum, MgtKeyType KeyType,
                              String MgtKey) throws PopbillException {
        return getPopUpURL(CorpNum, KeyType, MgtKey, null);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#getPopUpURL(java.lang.String, com.popbill.api.taxinvoice.MgtKeyType, java.lang.String, java.lang.String)
     */
    @Override
    public String getPopUpURL(String CorpNum, MgtKeyType KeyType,
                              String MgtKey, String UserID) throws PopbillException {
        if (KeyType == null)
            throw new PopbillException(-99999999, "문서번호 형태가 입력되지 않았습니다.");
        if (MgtKey == null || MgtKey.isEmpty())
            throw new PopbillException(-99999999, "문서번호가 입력되지 않았습니다.");

        URLResponse response = httpget("/Taxinvoice/" + KeyType.name() + "/"
                + MgtKey + "?TG=POPUP", CorpNum, UserID, URLResponse.class);

        return response.url;
    }

    /*
     * (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#getMailURL(java.lang.String, com.popbill.api.taxinvoice.MgtKeyType, java.lang.String)
     */
    @Override
    public String getMailURL(String CorpNum, MgtKeyType KeyType, String MgtKey) throws PopbillException {
        if (KeyType == null)
            throw new PopbillException(-99999999, "문서번호 형태가 입력되지 않았습니다.");
        if (MgtKey == null || MgtKey.isEmpty())
            throw new PopbillException(-99999999, "문서번호가 입력되지 않았습니다.");

        return getMailURL(CorpNum, KeyType, MgtKey, null);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#getMailURL(java.lang.String, com.popbill.api.taxinvoice.MgtKeyType, java.lang.String, java.lang.String)
     */
    @Override
    public String getMailURL(String CorpNum, MgtKeyType KeyType, String MgtKey,
                             String UserID) throws PopbillException {
        if (KeyType == null)
            throw new PopbillException(-99999999, "문서번호 형태가 입력되지 않았습니다.");
        if (MgtKey == null || MgtKey.isEmpty())
            throw new PopbillException(-99999999, "문서번호가 입력되지 않았습니다.");

        URLResponse response = httpget("/Taxinvoice/" + KeyType.name() + "/"
                + MgtKey + "?TG=MAIL", CorpNum, UserID, URLResponse.class);

        return response.url;
    }
    
    /* (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#getPDFURL(java.lang.String, com.popbill.api.taxinvoice.MgtKeyType, java.lang.String)
     */
    @Override
    public String getPDFURL(String CorpNum, MgtKeyType KeyType,
                              String MgtKey) throws PopbillException {

        return getPDFURL(CorpNum, KeyType, MgtKey, null);
    }
    
    /* (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#getPDFURL(java.lang.String, com.popbill.api.taxinvoice.MgtKeyType, java.lang.String, java.lang.String)
     */
    @Override
    public String getPDFURL(String CorpNum, MgtKeyType KeyType,
                              String MgtKey, String UserID) throws PopbillException {
        if (KeyType == null)
            throw new PopbillException(-99999999, "문서번호 형태가 입력되지 않았습니다.");
        if (MgtKey == null || MgtKey.isEmpty())
            throw new PopbillException(-99999999, "문서번호가 입력되지 않았습니다.");

        URLResponse response = httpget("/Taxinvoice/" + KeyType.name() + "/"
                + MgtKey + "?TG=PDF", CorpNum, UserID, URLResponse.class);

        return response.url;
    }
    
    /*
     * (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#getPDF(java.lang.String, com.popbill.api.taxinvoice.MgtKeyType, java.lang.String)
     */
    @Override
    public byte[] getPDF(String CorpNum, MgtKeyType KeyType,
    					 String MgtKey) throws PopbillException {
    	
    	return getPDF(CorpNum, KeyType, MgtKey, null);
    }
    
    /*
     * (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#getPDF(java.lang.String, com.popbill.api.taxinvoice.MgtKeyType, java.lang.String, java.lang.String)
     */
    @Override
    public byte[] getPDF(String CorpNum, MgtKeyType KeyType,
    					 String MgtKey, String UserID) throws PopbillException {
    	  if (KeyType == null)
              throw new PopbillException(-99999999, "문서번호 형태가 입력되지 않았습니다.");
          if (MgtKey == null || MgtKey.isEmpty())
              throw new PopbillException(-99999999, "문서번호가 입력되지 않았습니다.");
    	
         byte[] result = httpget("/Taxinvoice/" + KeyType.name() + "/"
        		 + MgtKey + "?PDF", CorpNum, UserID, byte[].class);
          
    	return result;
    }

    /*
     * (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#getPrintURL(java.lang.String, com.popbill.api.taxinvoice.MgtKeyType, java.lang.String)
     */
    @Override
    public String getPrintURL(String CorpNum, MgtKeyType KeyType,
                              String MgtKey) throws PopbillException {

        return getPrintURL(CorpNum, KeyType, MgtKey, null);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#getPrintURL(java.lang.String, com.popbill.api.taxinvoice.MgtKeyType, java.lang.String, java.lang.String)
     */
    @Override
    public String getPrintURL(String CorpNum, MgtKeyType KeyType,
                              String MgtKey, String UserID) throws PopbillException {
        if (KeyType == null)
            throw new PopbillException(-99999999, "문서번호 형태가 입력되지 않았습니다.");
        if (MgtKey == null || MgtKey.isEmpty())
            throw new PopbillException(-99999999, "문서번호가 입력되지 않았습니다.");

        URLResponse response = httpget("/Taxinvoice/" + KeyType.name() + "/"
                + MgtKey + "?TG=PRINT", CorpNum, UserID, URLResponse.class);

        return response.url;
    }
    
    /*
     * (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#getOldPrintURL(java.lang.String, com.popbill.api.taxinvoice.MgtKeyType, java.lang.String)
     */
    @Override
    public String getOldPrintURL(String CorpNum, MgtKeyType KeyType,
    							 String MgtKey) throws PopbillException {
    	return getOldPrintURL(CorpNum, KeyType, MgtKey, null);
    }
    
    /* (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#getOldPrintURL(java.lang.String, com.popbill.api.taxinvoice.MgtKeyType, java.lang.String, java.lang.String)
     */
    @Override
    public String getOldPrintURL(String CorpNum, MgtKeyType KeyType,
    							 String MgtKey, String UserID) throws PopbillException {
    	if (KeyType == null)
    		throw new PopbillException(-99999999, "문서번호 형태가 입력되지 않았습니다.");
    	if (MgtKey == null || MgtKey.isEmpty())
    		throw new PopbillException(-99999999, "문서번호가 입력되지 않았습니다.");
    	
    	URLResponse response = httpget("/Taxinvoice/" + KeyType.name() + "/"
    			+ MgtKey + "?TG=PRINTOLD", CorpNum, UserID, URLResponse.class);
    	
    	return response.url;
    }
    
    /*
     * (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#getViewURL(java.lang.String, com.popbill.api.taxinvoice.MgtKeyType, java.lang.String)
     */
    @Override
	public String getViewURL(String CorpNum, MgtKeyType KeyType, String MgtKey) throws PopbillException {
		return getViewURL(CorpNum, KeyType, MgtKey, null); 
	}
    
    /*
     * (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#getViewURL(java.lang.String, com.popbill.api.taxinvoice.MgtKeyType, java.lang.String, java.lang.String)
     */
    @Override
	public String getViewURL(String CorpNum, MgtKeyType KeyType, String MgtKey, String UserID) throws PopbillException {
    	 if (KeyType == null)
             throw new PopbillException(-99999999, "문서번호 형태가 입력되지 않았습니다.");
         if (MgtKey == null || MgtKey.isEmpty())
             throw new PopbillException(-99999999, "문서번호가 입력되지 않았습니다.");

         URLResponse response = httpget("/Taxinvoice/" + KeyType.name() + "/"
                 + MgtKey + "?TG=VIEW", CorpNum, UserID, URLResponse.class);

         return response.url;
	}

    /*
     * (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#getEPrintURL(java.lang.String, com.popbill.api.taxinvoice.MgtKeyType, java.lang.String)
     */
    @Override
    public String getEPrintURL(String CorpNum, MgtKeyType KeyType,
                               String MgtKey) throws PopbillException {
        return getEPrintURL(CorpNum, KeyType, MgtKey, null);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#getEPrintURL(java.lang.String, com.popbill.api.taxinvoice.MgtKeyType, java.lang.String, java.lang.String)
     */
    @Override
    public String getEPrintURL(String CorpNum, MgtKeyType KeyType,
                               String MgtKey, String UserID) throws PopbillException {
        if (KeyType == null)
            throw new PopbillException(-99999999, "문서번호 형태가 입력되지 않았습니다.");
        if (MgtKey == null || MgtKey.isEmpty())
            throw new PopbillException(-99999999, "문서번호가 입력되지 않았습니다.");

        URLResponse response = httpget("/Taxinvoice/" + KeyType.name() + "/"
                + MgtKey + "?TG=EPRINT", CorpNum, UserID, URLResponse.class);

        return response.url;
    }

    /*
     * (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#getMassPrintURL(java.lang.String, com.popbill.api.taxinvoice.MgtKeyType, java.lang.String[])
     */
    @Override
    public String getMassPrintURL(String CorpNum, MgtKeyType KeyType,
                                  String[] MgtKeyList) throws PopbillException {

        return getMassPrintURL(CorpNum, KeyType, MgtKeyList, null);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#getMassPrintURL(java.lang.String, com.popbill.api.taxinvoice.MgtKeyType, java.lang.String[], java.lang.String)
     */
    @Override
    public String getMassPrintURL(String CorpNum, MgtKeyType KeyType,
                                  String[] MgtKeyList, String UserID) throws PopbillException {
        if (KeyType == null)
            throw new PopbillException(-99999999, "문서번호 형태가 입력되지 않았습니다.");
        if (MgtKeyList == null || MgtKeyList.length == 0)
            throw new PopbillException(-99999999, "문서번호 목록이 입력되지 않았습니다.");

        String PostData = toJsonString(MgtKeyList);

        URLResponse response = httppost("/Taxinvoice/" + KeyType.name()
                + "?Print", CorpNum, PostData, UserID, URLResponse.class);

        return response.url;
    }

    /* (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#attachFile(java.lang.String, com.popbill.api.taxinvoice.MgtKeyType, java.lang.String, java.lang.String, java.io.InputStream)
     */
    @Override
    public Response attachFile(String CorpNum, MgtKeyType KeyType,
                               String MgtKey, String DisplayName, InputStream FileData)
            throws PopbillException {
        return attachFile(CorpNum, KeyType, MgtKey, DisplayName, FileData, null);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#attachFile(java.lang.String, com.popbill.api.taxinvoice.MgtKeyType, java.lang.String, java.lang.String, java.io.InputStream, java.lang.String)
     */
    @Override
    public Response attachFile(String CorpNum, MgtKeyType KeyType,
                               String MgtKey, String DisplayName, InputStream FileData,
                               String UserID) throws PopbillException {
        if (KeyType == null)
            throw new PopbillException(-99999999, "문서번호 형태가 입력되지 않았습니다.");
        if (MgtKey == null || MgtKey.isEmpty())
            throw new PopbillException(-99999999, "문서번호가 입력되지 않았습니다.");
        if (DisplayName == null || DisplayName.isEmpty())
            throw new PopbillException(-99999999, "파일 표시명이 입력되지 않았습니다.");
        if (FileData == null)
            throw new PopbillException(-99999999, "파일 스트림이 입력되지 않았습니다.");

        List<UploadFile> files = new ArrayList<BaseServiceImp.UploadFile>();
        UploadFile file = new UploadFile();
        file.fileName = DisplayName;
        file.fieldName = "Filedata";
        file.fileData = FileData;

        files.add(file);

        return httppostFiles("/Taxinvoice/" + KeyType.name() + "/" + MgtKey
                + "/Files", CorpNum, null, files, UserID, Response.class);

    }

    /* (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#getFiles(java.lang.String, com.popbill.api.taxinvoice.MgtKeyType, java.lang.String)
     */
    @Override
    public AttachedFile[] getFiles(String CorpNum, MgtKeyType KeyType,
                                   String MgtKey) throws PopbillException {
        if (KeyType == null)
            throw new PopbillException(-99999999, "문서번호 형태가 입력되지 않았습니다.");
        if (MgtKey == null || MgtKey.isEmpty())
            throw new PopbillException(-99999999, "문서번호가 입력되지 않았습니다.");

        return httpget("/Taxinvoice/" + KeyType.name() + "/" + MgtKey
                + "/Files", CorpNum, null, AttachedFile[].class);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#deleteFile(java.lang.String, com.popbill.api.taxinvoice.MgtKeyType, java.lang.String, java.lang.String)
     */
    @Override
    public Response deleteFile(String CorpNum, MgtKeyType KeyType,
                               String MgtKey, String FileID) throws PopbillException {
        return deleteFile(CorpNum, KeyType, MgtKey, FileID, null);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#deleteFile(java.lang.String, com.popbill.api.taxinvoice.MgtKeyType, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public Response deleteFile(String CorpNum, MgtKeyType KeyType,
                               String MgtKey, String FileID, String UserID)
            throws PopbillException {
        if (KeyType == null)
            throw new PopbillException(-99999999, "문서번호 형태가 입력되지 않았습니다.");
        if (MgtKey == null || MgtKey.isEmpty())
            throw new PopbillException(-99999999, "문서번호가 입력되지 않았습니다.");
        if (FileID == null || FileID.isEmpty())
            throw new PopbillException(-99999999, "파일아이디가 입력되지 않았습니다.");

        return httppost("/Taxinvoice/" + KeyType.name() + "/" + MgtKey
                        + "/Files/" + FileID, CorpNum, null, UserID, "DELETE",
                Response.class);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#Search(java.lang.String, com.popbill.api.taxinvoice.MgtKeyType, java.lang.String,
     * 													java.lang.String, java.lang.String, java.lang.String[], java.lang.String[],
     * 													boolean, integer, integer)
     */
    @Override
    public TISearchResult Search(String CorpNum, MgtKeyType KeyType, String DType,
                                 String SDate, String EDate, String[] State, String[] Type, String[] TaxType,
                                 Boolean LateOnly, Integer Page, Integer PerPage, String Order) throws PopbillException {

        return Search(CorpNum, KeyType, DType, SDate, EDate, State, Type, TaxType, null, LateOnly,
                null, null, null, null, Page, PerPage, Order, null, null, null, null);
    }

    /*
     * (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#Search(java.lang.String, com.popbill.api.taxinvoice.MgtKeyType, java.lang.String, java.lang.String, java.lang.String, java.lang.String[], java.lang.String[], java.lang.String[], java.lang.Boolean, java.lang.String, java.lang.String, java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.String)
     */
    @Override
    public TISearchResult Search(String CorpNum, MgtKeyType KeyType, String DType,
                                 String SDate, String EDate, String[] State, String[] Type, String[] TaxType,
                                 Boolean LateOnly, String TaxRegIDType, String TaxRegID, String TaxRegIDYN,
                                 Integer Page, Integer PerPage, String Order) throws PopbillException {

        return Search(CorpNum, KeyType, DType, SDate, EDate, State, Type, TaxType, null, LateOnly,
                TaxRegIDType, TaxRegID, TaxRegIDYN, null, Page, PerPage, Order, null , null, null, null);
    }

    /*
     * (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#Search(java.lang.String, com.popbill.api.taxinvoice.MgtKeyType, java.lang.String, java.lang.String, java.lang.String, java.lang.String[], java.lang.String[], java.lang.String[], java.lang.Boolean, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.String)
     */
    @Override
    public TISearchResult Search(String CorpNum, MgtKeyType KeyType,
                                 String DType, String SDate, String EDate, String[] State,
                                 String[] Type, String[] TaxType, Boolean LateOnly,
                                 String TaxRegIDType, String TaxRegID, String TaxRegIDYN,
                                 String QString, Integer Page, Integer PerPage, String Order)
            throws PopbillException {
        return Search(CorpNum, KeyType, DType, SDate, EDate, State, Type, TaxType, null, LateOnly,
                TaxRegIDType, TaxRegID, TaxRegIDYN, QString, Page, PerPage, Order, null);
    }

    @Override
    public TISearchResult Search(String CorpNum, MgtKeyType KeyType,
                                 String DType, String SDate, String EDate, String[] State,
                                 String[] Type, String[] TaxType, Boolean LateOnly,
                                 String TaxRegIDType, String TaxRegID, String TaxRegIDYN,
                                 String QString, Integer Page, Integer PerPage, String Order, String InterOPYN)
            throws PopbillException {

        return Search(CorpNum, KeyType, DType, SDate, EDate, State, Type, TaxType, null, LateOnly,
                TaxRegIDType, TaxRegID, TaxRegIDYN, QString, Page, PerPage, Order, InterOPYN, null, null, null);
    }

    /*
     * (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#Search(java.lang.String, com.popbill.api.taxinvoice.MgtKeyType, java.lang.String, java.lang.String, java.lang.String, java.lang.String[], java.lang.String[], java.lang.String[], java.lang.String[], java.lang.Boolean, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.String)
     */
    @Override
    public TISearchResult Search(String CorpNum, MgtKeyType KeyType,
                                 String DType, String SDate, String EDate, String[] State,
                                 String[] Type, String[] TaxType, String[] IssueType, Boolean LateOnly,
                                 String TaxRegIDType, String TaxRegID, String TaxRegIDYN,
                                 String QString, Integer Page, Integer PerPage, String Order, String InterOPYN)
            throws PopbillException {
    	 return Search(CorpNum, KeyType, DType, SDate, EDate, State, Type, TaxType, null, LateOnly,
                 TaxRegIDType, TaxRegID, TaxRegIDYN, QString, Page, PerPage, Order, InterOPYN, null, null, null);
    }
    
    @Override
    public TISearchResult Search(String CorpNum, MgtKeyType KeyType,
                                 String DType, String SDate, String EDate, String[] State,
                                 String[] Type, String[] TaxType, String[] IssueType, Boolean LateOnly,
                                 String TaxRegIDType, String TaxRegID, String TaxRegIDYN,
                                 String QString, Integer Page, Integer PerPage, String Order, String InterOPYN, String[] RegType)
            throws PopbillException {

    	return Search(CorpNum, KeyType, DType, SDate, EDate, State, Type, TaxType, null, LateOnly,
                TaxRegIDType, TaxRegID, TaxRegIDYN, QString, Page, PerPage, Order, InterOPYN, null, null, null);
    }
    
    @Override
    public TISearchResult Search(String CorpNum, MgtKeyType KeyType,
                                 String DType, String SDate, String EDate, String[] State,
                                 String[] Type, String[] TaxType, String[] IssueType, Boolean LateOnly,
                                 String TaxRegIDType, String TaxRegID, String TaxRegIDYN,
                                 String QString, Integer Page, Integer PerPage, String Order, String InterOPYN, String[] RegType,
                                 String[] CloseDownState, String MgtKey)
            throws PopbillException {

        if (KeyType == null)
            throw new PopbillException(-99999999, "문서번호 형태가 입력되지 않았습니다.");
        if (DType == null || DType.isEmpty())
            throw new PopbillException(-99999999, "검색일자 유형이 입력되지 않았습니다.");
        if (SDate == null || SDate.isEmpty())
            throw new PopbillException(-99999999, "시작일자가 입력되지 않았습니다.");
        if (EDate == null || EDate.isEmpty())
            throw new PopbillException(-99999999, "종료일자가 입력되지 않았습니다.");

        String uri = "/Taxinvoice/" + KeyType;
        uri += "?DType=" + DType;
        uri += "&SDate=" + SDate;
        uri += "&EDate=" + EDate;
        uri += "&State=" + Arrays.toString(State)
                .replaceAll("\\[|\\]|\\s", "");
        uri += "&Type=" + Arrays.toString(Type)
                .replaceAll("\\[|\\]|\\s", "");
        uri += "&TaxType=" + Arrays.toString(TaxType)
                .replaceAll("\\[|\\]|\\s", "");

        if (IssueType != null) {
            uri += "&IssueType=" + Arrays.toString(IssueType)
                    .replaceAll("\\[|\\]|\\s", "");
        }
        
        if (RegType != null) {
            uri += "&RegType=" + Arrays.toString(RegType)
                    .replaceAll("\\[|\\]|\\s", "");
        }
        
        if (CloseDownState != null) {
            uri += "&CloseDownState=" + Arrays.toString(CloseDownState)
                    .replaceAll("\\[|\\]|\\s", "");
        }

        if (LateOnly != null) {
            if (LateOnly) {
                uri += "&LateOnly=1";
            } else {
                uri += "&LateOnly=0";
            }
        }

        if (TaxRegIDType != null && TaxRegIDType != "") {
            uri += "&TaxRegIDType=" + TaxRegIDType;
        }

        if (TaxRegID != null && TaxRegID != "") {
            uri += "&TaxRegID=" + TaxRegID;
        }

        if (TaxRegIDYN != null && TaxRegIDYN != "") {
            uri += "&TaxRegIDYN=" + TaxRegIDYN;
        }

        if (InterOPYN != null && InterOPYN != "") {
            uri += "&InterOPYN=" + InterOPYN;
        }

        if (QString != null && QString != "") {
            uri += "&QString=" + QString;
        }
        
        if (MgtKey != null && MgtKey != "") {
            uri += "&MgtKey=" + MgtKey;
        }

        uri += "&Page=" + Integer.toString(Page);
        uri += "&PerPage=" + Integer.toString(PerPage);
        uri += "&Order=" + Order;

        return httpget(uri, CorpNum, null, TISearchResult.class);
    }
    
    

    /* (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#registIssue(java.lang.String, com.popbill.api.Taxinvoice, Boolean)
     */
    @Override
    public IssueResponse registIssue(String CorpNum, Taxinvoice taxinvoice,
                                Boolean WriteSpecification) throws PopbillException {

        return registIssue(CorpNum, taxinvoice, WriteSpecification, null, false, null, null, null);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#registIssue(java.lang.String, com.popbill.api.Taxinvoice, String, Boolean)
     */
    @Override
    public IssueResponse registIssue(String CorpNum, Taxinvoice taxinvoice,
                                String Memo, Boolean ForceIssue) throws PopbillException {

        return registIssue(CorpNum, taxinvoice, false, Memo, ForceIssue, null, null, null);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#registIssue(java.lang.String, com.popbill.api.Taxinvoice, Boolean, java.lang.string,
     * 														Boolean, java.lang.String)
     */
    @Override
    public IssueResponse registIssue(String CorpNum, Taxinvoice taxinvoice,
                                Boolean WriteSpecification, String Memo, Boolean ForceIssue,
                                String DealInvoiceKey) throws PopbillException {

        return registIssue(CorpNum, taxinvoice, WriteSpecification, Memo, ForceIssue, DealInvoiceKey, null, null);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#registIssue(java.lang.String, com.popbill.api.Taxinvoice, Boolean, java.lang.string,
     * 														Boolean, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public IssueResponse registIssue(String CorpNum, Taxinvoice taxinvoice,
                                Boolean WriteSpecification, String Memo, Boolean ForceIssue,
                                String DealInvoiceKey, String EmailSubject, String UserID)
            throws PopbillException {

        if (taxinvoice == null)
            throw new PopbillException(-99999999, "세금계산서 정보가 입력되지 않았습니다.");

        if (WriteSpecification)
            taxinvoice.setWriteSpecification(true);

        if (Memo != null)
            taxinvoice.setMemo(Memo);

        if (ForceIssue)
            taxinvoice.setForceIssue(true);


        if (DealInvoiceKey != null)
            taxinvoice.setDealInvoiceMgtKey(DealInvoiceKey);

        if (EmailSubject != null)
            taxinvoice.setEmailSubject(EmailSubject);

        String PostData = toJsonString(taxinvoice);


        return httppost("/Taxinvoice", CorpNum, PostData,
                UserID, "ISSUE", IssueResponse.class);
    }
    
    /*
    
    /*
     * (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#attachStatement(java.lang.String, com.popbill.api.taxinvoice.MgtKeyType, java.lang.String, int, java.lang.String)
     */
    @Override
    public Response attachStatement(String CorpNum, MgtKeyType KeyType, String MgtKey,
                                    int SubItemCode, String SubMgtKey) throws PopbillException {

        DocRequest request = new DocRequest();
        request.ItemCode = Integer.toString(SubItemCode);
        request.MgtKey = SubMgtKey;

        String PostData = toJsonString(request);

        return httppost("/Taxinvoice/" + KeyType.name() + "/" + MgtKey + "/AttachStmt",
                CorpNum, PostData, null, "", Response.class);
    }

    /*
     * (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#detachStatement(java.lang.String, com.popbill.api.taxinvoice.MgtKeyType, java.lang.String, int, java.lang.String)
     */
    @Override
    public Response detachStatement(String CorpNum, MgtKeyType KeyType, String MgtKey,
                                    int SubItemCode, String SubMgtKey) throws PopbillException {

        DocRequest request = new DocRequest();
        request.ItemCode = Integer.toString(SubItemCode);
        request.MgtKey = SubMgtKey;

        String PostData = toJsonString(request);

        return httppost("/Taxinvoice/" + KeyType.name() + "/" + MgtKey + "/DetachStmt",
                CorpNum, PostData, null, "", Response.class);
    }

    /*
     * (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#getChargeInfo(java.lang.String)
     */
    @Override
    public ChargeInfo getChargeInfo(String CorpNum) throws PopbillException {
        return httpget("/Taxinvoice/ChargeInfo", CorpNum, null, ChargeInfo.class);
    }

    /*
     * (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#updateEmailConfig(java.lang.String, java.lang.String, java.lang.Boolean)
     */
    @Override
    public Response updateEmailConfig(String CorpNum, String EmailType, Boolean SendYN) throws PopbillException {
        return updateEmailConfig(CorpNum, EmailType, SendYN, null);
    }

    /*
     * (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#updateEmailConfig(java.lang.String, java.lang.String, java.lang.Boolean, java.lang.String)
     */
    @Override
    public Response updateEmailConfig(String CorpNum, String EmailType, Boolean SendYN, String UserID)
            throws PopbillException {
        if (SendYN == null)
            throw new PopbillException(-99999999, "메일전송여부(SendYN)가 입력되지 않았습니다.");
        if (EmailType == null || EmailType.isEmpty())
            throw new PopbillException(-99999999, "메일전송유형(EmailType)이 입력되지 않았습니다.");

        return httppost("/Taxinvoice/EmailSendConfig?EmailType=" + EmailType + "&SendYN=" + String.valueOf(SendYN),
                CorpNum, null, UserID, "", Response.class);
    }

    /*
     * (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#listEmailConfig(java.lang.String)
     */
    @Override
    public EmailSendConfig[] listEmailConfig(String CorpNum) throws PopbillException {
        return httpget("/Taxinvoice/EmailSendConfig", CorpNum, null, EmailSendConfig[].class);
    }

    /*
     * (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#listEmailConfig(java.lang.String, java.lang.String)
     */
    @Override
    public EmailSendConfig[] listEmailConfig(String CorpNum, String UserID) throws PopbillException {
        return httpget("/Taxinvoice/EmailSendConfig", CorpNum, UserID, EmailSendConfig[].class);
    }

    /*
     * (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#assignMgtKey(java.lang.String, com.popbill.api.taxinvoice.MgtKeyType, java.lang.String, java.lang.String)
     */
    public Response assignMgtKey(String corpNum, MgtKeyType keyType, String itemKey,
                                 String mgtKey) throws PopbillException {

        return assignMgtKey(corpNum, keyType, itemKey, mgtKey, null);
    }

    /*
     * (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#assignMgtKey(java.lang.String, com.popbill.api.taxinvoice.MgtKeyType, java.lang.String, java.lang.String, java.lang.String)
     */
    public Response assignMgtKey(String corpNum, MgtKeyType keyType, String itemKey,
                                 String mgtKey, String userID) throws PopbillException {
        if (itemKey == null || itemKey.isEmpty())
            throw new PopbillException(-99999999, "아이템키(ItemKey)가 입력되지 않았습니다.");

        if (mgtKey == null || mgtKey.isEmpty())
            throw new PopbillException(-99999999, "문서번호(MgtKey)가 입력되지 않았습니다.");

        String PostData = "MgtKey=" + mgtKey;

        return httppost("/Taxinvoice/" + itemKey + "/" + keyType.name(),
                corpNum, PostData, userID, "", Response.class, "application/x-www-form-urlencoded; charset=utf-8");
    }

    /*
     * (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#checkCertValidation(java.lang.String)
     */
    public Response checkCertValidation(String CorpNum) throws PopbillException {
        if (CorpNum == null || CorpNum.isEmpty())
            throw new PopbillException(-99999999, "연동회원 사업자번호(CorpNum)가 입력되지 않았습니다.");

        return httpget("/Taxinvoice/CertCheck", CorpNum, null, Response.class);
    }

    @Override
    public String getSealURL(String CorpNum, String UserID) throws PopbillException {

        URLResponse response = httpget("/?TG=SEAL", CorpNum, UserID, URLResponse.class);

        return response.url;
    }


    @Override
    public String getTaxCertURL(String CorpNum, String UserID) throws PopbillException {

        URLResponse response = httpget("/?TG=CERT", CorpNum, UserID, URLResponse.class);

        return response.url;
    }



    @Override
    public Response registRequest(String CorpNum, Taxinvoice taxinvoice, String Memo) throws PopbillException {
        return registRequest(CorpNum, taxinvoice, Memo,"");
    }


    @Override
    public Response registRequest(String CorpNum, Taxinvoice taxinvoice, String Memo, String UserID) throws PopbillException {

        if (taxinvoice == null)
            throw new PopbillException(-99999999, "세금계산서 정보가 입력되지 않았습니다.");

        if (Memo != null)
            taxinvoice.setMemo(Memo);

        String PostData = toJsonString(taxinvoice);

        return httppost("/Taxinvoice", CorpNum, PostData, UserID, "REQUEST", Response.class);
    }


    protected class CertResponse {
        public String certificateExpiration;
    }

    protected class MemoRequest {
        public MemoRequest(String memo) {
            this.memo = memo;
        }

        public String memo;
    }

    protected class SendRequest {
        public String memo;
        public String emailSubject;
    }

    protected class IssueRequest {
        public String memo;
        public String emailSubject;
        public boolean forceIssue = false;
    }

    protected class ResendRequest {
        public String receiver;
        public String sender = null;
        public String contents = null;
    }

    protected class DocRequest {
        public String ItemCode;
        public String MgtKey;
    }

	

	

	

	
}

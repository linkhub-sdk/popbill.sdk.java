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

import com.popbill.api.AttachedFile;
import com.popbill.api.BaseServiceImp;
import com.popbill.api.BulkResponse;
import com.popbill.api.ChargeInfo;
import com.popbill.api.EmailSendConfig;
import com.popbill.api.Encryptor;
import com.popbill.api.IssueResponse;
import com.popbill.api.PopbillException;
import com.popbill.api.Response;
import com.popbill.api.TaxinvoiceCertificate;
import com.popbill.api.TaxinvoiceService;
import com.popbill.api.util.ValidationUtils;
import kr.co.linkhub.auth.Base64;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

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
     * @see com.popbill.api.TaxinvoiceService#checkMgtKeyInUse(java.lang.String, com.popbill.api.taxinvoice.MgtKeyType, java.lang.String)
     */
    @Override
    public boolean checkMgtKeyInUse(String CorpNum, MgtKeyType KeyType, String MgtKey) throws PopbillException {
        return checkMgtKeyInUse(CorpNum, KeyType, MgtKey, null);
    }

    @Override
    public boolean checkMgtKeyInUse(String CorpNum, MgtKeyType KeyType, String MgtKey, String UserID) throws PopbillException {
        if (KeyType == null)
            throw new PopbillException(-99999999, "문서번호 유형이 입력되지 않았습니다.");

        if (ValidationUtils.isNullOrEmpty(MgtKey))
            throw new PopbillException(-99999999, "문서번호가 입력되지 않았습니다.");

        try {
            TaxinvoiceInfo info = httpget("/Taxinvoice/" + KeyType.name() + "/" + MgtKey, CorpNum, UserID, TaxinvoiceInfo.class);

            return !ValidationUtils.isNullOrEmpty(info.getItemKey());
        } catch (PopbillException PE) {
            if (PE.getCode() == -11000005)
                return false;
            throw PE;
        }
    }

    /* (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#registIssue(java.lang.String, com.popbill.api.Taxinvoice, Boolean)
     */
    @Override
    public IssueResponse registIssue(String CorpNum, Taxinvoice Taxinvoice, Boolean WriteSpecification) throws PopbillException {

        return registIssue(CorpNum, Taxinvoice, WriteSpecification, null, false, null, null, null);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#registIssue(java.lang.String, com.popbill.api.Taxinvoice, String, Boolean)
     */
    @Override
    public IssueResponse registIssue(String CorpNum, Taxinvoice Taxinvoice, String Memo, Boolean ForceIssue) throws PopbillException {

        return registIssue(CorpNum, Taxinvoice, false, Memo, ForceIssue, null, null, null);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#registIssue(java.lang.String, com.popbill.api.Taxinvoice, Boolean, java.lang.string,
     *                                                         Boolean, java.lang.String)
     */
    @Override
    public IssueResponse registIssue(String CorpNum, Taxinvoice Taxinvoice, Boolean WriteSpecification, String Memo, Boolean ForceIssue,
                                     String DealInvoiceKey) throws PopbillException {

        return registIssue(CorpNum, Taxinvoice, WriteSpecification, Memo, ForceIssue, DealInvoiceKey, null, null);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#registIssue(java.lang.String, com.popbill.api.Taxinvoice, Boolean, java.lang.string,
     *                                                         Boolean, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public IssueResponse registIssue(String CorpNum, Taxinvoice Taxinvoice, Boolean WriteSpecification, String Memo, Boolean ForceIssue,
                                     String DealInvoiceKey, String EmailSubject, String UserID) throws PopbillException {

        if (Taxinvoice == null)
            throw new PopbillException(-99999999, "전자세금계산서 정보가 입력되지 않았습니다.");

        if (WriteSpecification == null)
            Taxinvoice.setWriteSpecification(false);
        else
            Taxinvoice.setWriteSpecification(WriteSpecification);

        if (Memo != null)
            Taxinvoice.setMemo(Memo);

        if (ForceIssue == null)
            Taxinvoice.setForceIssue(false);
        else
            Taxinvoice.setForceIssue(ForceIssue);

        if (DealInvoiceKey != null)
            Taxinvoice.setDealInvoiceMgtKey(DealInvoiceKey);

        if (EmailSubject != null)
            Taxinvoice.setEmailSubject(EmailSubject);

        String PostData = toJsonString(Taxinvoice);

        return httppost("/Taxinvoice", CorpNum, PostData, UserID, "ISSUE", IssueResponse.class);
    }


    @Override
    public Response registTaxCert(String CorpNum, String CertPublicKey, String CertPrivateKey, String CertCipher) throws PopbillException {

        return registTaxCert(CorpNum, CertPublicKey, CertPrivateKey, CertCipher, null);
    }


    @Override
    public Response registTaxCert(String CorpNum, String CertPublicKey, String CertPrivateKey, String CertCipher, String UserID) throws PopbillException {
        if (ValidationUtils.isNullOrEmpty(CertPublicKey))
            throw new PopbillException(-99999999, "공동인증서 공개키가 입력되지 않았습니다.");

        if (ValidationUtils.isNullOrEmpty(CertPrivateKey))
            throw new PopbillException(-99999999, "공동인증서 개인키가 입력되지 않았습니다.");

        if (ValidationUtils.isNullOrEmpty(CertCipher))
            throw new PopbillException(-99999999, "공동인증서 비밀번호가 입력되지 않았습니다.");

        Encryptor pb_enc;

        TaxCertRequest request;

        try {
            pb_enc = Encryptor.getInstance(getMleKeyID(), getMleKeyName(), getMlePublicKey());
            request = new TaxCertRequest();

            request.certPublicKey = Base64.encode(pb_enc.Encrypt(Base64.decode(CertPublicKey)));
            request.certPrivateKey = Base64.encode(pb_enc.Encrypt(Base64.decode(CertPrivateKey)));

            request.certCipher = Base64.encode(pb_enc.Encrypt(CertCipher.getBytes(Charset.forName("UTF-8"))));

        } catch (PopbillException PE) {
            throw PE;
        } catch (Exception E) {
            throw new PopbillException(-99999999, "공동인증서 암호화가 실패 되었습니다.", E);
        }

        String PostData = toJsonString(request);

        return httppost("/Taxinvoice/Certificate", CorpNum, PostData, UserID, null, Response.class);
    }

    @Override
    public Response registTaxCertPFX(String CorpNum, String CertPFX, String CertCipher) throws PopbillException {

        return registTaxCertPFX(CorpNum, CertPFX, CertCipher, null);
    }

    @Override
    public Response registTaxCertPFX(String CorpNum, String CertPFX, String CertCipher, String UserID) throws PopbillException {

        if (ValidationUtils.isNullOrEmpty(CertPFX))
            throw new PopbillException(-99999999, "공동인증서 PFX 파일 정보가 입력되지 않았습니다.");

        if (ValidationUtils.isNullOrEmpty(CertCipher))
            throw new PopbillException(-99999999, "공동인증서 PFX 파일의 비밀번호가 입력되지 않았습니다.");

        Encryptor enc;

        CertPFXRequest request;

        try {
            enc = Encryptor.getInstance(getMleKeyID(), getMleKeyName(), getMlePublicKey());
            request = new CertPFXRequest();

            request.pfx = Base64.encode(enc.Encrypt(Base64.decode(CertPFX)));
            request.password = Base64.encode(enc.Encrypt(CertCipher.getBytes(Charset.forName("UTF-8"))));

        } catch (PopbillException PE) {
            throw PE;
        } catch (Exception E) {
            throw new PopbillException(-99999999, "공동인증서 암호화가 실패 되었습니다.", E);
        }

        String PostData = toJsonString(request);

        return httppost("/Taxinvoice/CertificatePFX", CorpNum, PostData, UserID, null, Response.class);
    }

    @Override
    public IssueResponse registIssueMLE(String CorpNum, Taxinvoice Taxinvoice, Boolean WriteSpecification, String Memo, Boolean ForceIssue,
                                        String DealInvoiceKey, String EmailSubject, String UserID) throws PopbillException {

        if (Taxinvoice == null)
            throw new PopbillException(-99999999, "전자세금계산서 정보가 입력되지 않았습니다.");

        if (WriteSpecification)
            Taxinvoice.setWriteSpecification(true);

        if (Memo != null)
            Taxinvoice.setMemo(Memo);

        if (ForceIssue)
            Taxinvoice.setForceIssue(true);

        if (DealInvoiceKey != null)
            Taxinvoice.setDealInvoiceMgtKey(DealInvoiceKey);

        if (EmailSubject != null)
            Taxinvoice.setEmailSubject(EmailSubject);

        String PostData = toJsonString(Taxinvoice);

        return httppost("/Taxinvoice", CorpNum, PostData, UserID, "ISSUE", IssueResponse.class, "application/lh-encrypted");
    }

    @Override
    public BulkResponse bulkSubmit(String CorpNum, String SubmitID, List<Taxinvoice> TaxinvoiceList, Boolean ForceIssue) throws PopbillException {

        return bulkSubmit(CorpNum, SubmitID, TaxinvoiceList, ForceIssue, null);

    }


    @Override
    public BulkResponse bulkSubmit(String CorpNum, String SubmitID, List<Taxinvoice> TaxinvoiceList, Boolean ForceIssue, String UserID)
            throws PopbillException {

        BulkTaxinvoiceSubmit tx = new BulkTaxinvoiceSubmit();

        if (ForceIssue == null)
            tx.setForceIssue(false);
        else
            tx.setForceIssue(ForceIssue);
        tx.setInvoices(TaxinvoiceList);
        String PostData = toJsonString(tx);

        return httpBulkPost("/Taxinvoice", CorpNum, SubmitID, PostData, UserID, "BULKISSUE", BulkResponse.class);
    }

    @Override
    public BulkResponse bulkSubmitMLE(String CorpNum, String SubmitID, List<Taxinvoice> TaxinvoiceList, Boolean ForceIssue, String UserID)
            throws PopbillException {

        BulkTaxinvoiceSubmit tx = new BulkTaxinvoiceSubmit();
        if (ForceIssue == null)
            tx.setForceIssue(false);
        else
            tx.setForceIssue(ForceIssue);
        tx.setInvoices(TaxinvoiceList);
        String PostData = toJsonString(tx);

        return httpBulkPost("/Taxinvoice", CorpNum, SubmitID, PostData, UserID, "BULKISSUE", BulkResponse.class, "application/lh-encrypted");
    }

    @Override
    public BulkTaxinvoiceResult getBulkResult(String CorpNum, String SubmitID) throws PopbillException {

        return getBulkResult(CorpNum, SubmitID, null);
    }

    @Override
    public BulkTaxinvoiceResult getBulkResult(String CorpNum, String SubmitID, String UserID) throws PopbillException {
        if (ValidationUtils.isNullOrEmpty(SubmitID))
            throw new PopbillException(-99999999, "제출아이디가 입력되지 않았습니다.");

        return httpget("/Taxinvoice/BULK/" + SubmitID + "/State", CorpNum, UserID, BulkTaxinvoiceResult.class);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#register(java.lang.String, com.popbill.api.Taxinvoice.Taxinvoice)
     */
    @Override
    public Response register(String CorpNum, Taxinvoice Taxinvoice) throws PopbillException {
        return register(CorpNum, Taxinvoice, null);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#register(java.lang.String, com.popbill.api.Taxinvoice.Taxinvoice, java.lang.String)
     */
    @Override
    public Response register(String CorpNum, Taxinvoice Taxinvoice, String UserID) throws PopbillException {
        return register(CorpNum, Taxinvoice, UserID, false);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#register(java.lang.String, com.popbill.api.Taxinvoice.Taxinvoice, java.lang.String, boolean)
     */
    @Override
    public Response register(String CorpNum, Taxinvoice Taxinvoice, String UserID, Boolean WriteSpecification) throws PopbillException {

        if (Taxinvoice == null)
            throw new PopbillException(-99999999, "전자세금계산서 정보가 입력되지 않았습니다.");

        String PostData = toJsonString(Taxinvoice);

        if (WriteSpecification != null && WriteSpecification) {
            PostData = "{\"writeSpecification\":true," + PostData.substring(1);
        }

        return httppost("/Taxinvoice", CorpNum, PostData, UserID, Response.class);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#update(java.lang.String, com.popbill.api.Taxinvoice.MgtKeyType, java.lang.String, com.popbill.api.Taxinvoice.Taxinvoice)
     */
    @Override
    public Response update(String CorpNum, MgtKeyType KeyType, String MgtKey, Taxinvoice Taxinvoice) throws PopbillException {
        return update(CorpNum, KeyType, MgtKey, Taxinvoice, null);

    }

    /* (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#update(java.lang.String, com.popbill.api.Taxinvoice.MgtKeyType, java.lang.String, com.popbill.api.Taxinvoice.Taxinvoice, java.lang.String)
     */
    @Override
    public Response update(String CorpNum, MgtKeyType KeyType, String MgtKey, Taxinvoice Taxinvoice, String UserID) throws PopbillException {
        if (KeyType == null)
            throw new PopbillException(-99999999, "문서번호 유형이 입력되지 않았습니다.");

        if (ValidationUtils.isNullOrEmpty(MgtKey))
            throw new PopbillException(-99999999, "문서번호가 입력되지 않았습니다.");

        String PostData = toJsonString(Taxinvoice);

        return httppost("/Taxinvoice/" + KeyType.name() + "/" + MgtKey, CorpNum, PostData, UserID, "PATCH", Response.class);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#issue(java.lang.String, com.popbill.api.taxinvoice.MgtKeyType, java.lang.String, java.lang.String)
     */
    @Override
    public IssueResponse issue(String CorpNum, MgtKeyType KeyType, String MgtKey, String Memo) throws PopbillException {
        return issue(CorpNum, KeyType, MgtKey, Memo, null);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#issue(java.lang.String, com.popbill.api.taxinvoice.MgtKeyType, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public IssueResponse issue(String CorpNum, MgtKeyType KeyType, String MgtKey, String Memo, String UserID) throws PopbillException {
        return issue(CorpNum, KeyType, MgtKey, Memo, null, false, UserID);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#issue(java.lang.String, com.popbill.api.taxinvoice.MgtKeyType, java.lang.String, java.lang.String, boolean, java.lang.String)
     */
    @Override
    public IssueResponse issue(String CorpNum, MgtKeyType KeyType, String MgtKey, String Memo, Boolean ForceIssue, String UserID)
            throws PopbillException {
        return issue(CorpNum, KeyType, MgtKey, Memo, null, ForceIssue, UserID);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#issue(java.lang.String, com.popbill.api.taxinvoice.MgtKeyType, java.lang.String, java.lang.String, java.lang.String, boolean, java.lang.String)
     */
    @Override
    public IssueResponse issue(String CorpNum, MgtKeyType KeyType, String MgtKey, String Memo, String EmailSubject, Boolean ForceIssue, String UserID)
            throws PopbillException {
        if (KeyType == null)
            throw new PopbillException(-99999999, "문서번호 유형이 입력되지 않았습니다.");
        
        if (ValidationUtils.isNullOrEmpty(MgtKey))
            throw new PopbillException(-99999999, "문서번호가 입력되지 않았습니다.");

        IssueRequest request = new IssueRequest();
        request.memo = Memo;
        request.emailSubject = EmailSubject;
        if (ForceIssue == null)
            request.forceIssue = false;
        else
            request.forceIssue = ForceIssue;

        String PostData = toJsonString(request);

        return httppost("/Taxinvoice/" + KeyType.name() + "/" + MgtKey, CorpNum, PostData, UserID, "ISSUE", IssueResponse.class);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#cancelIssue(java.lang.String, com.popbill.api.taxinvoice.MgtKeyType, java.lang.String, java.lang.String)
     */
    @Override
    public Response cancelIssue(String CorpNum, MgtKeyType KeyType, String MgtKey, String Memo) throws PopbillException {
        return cancelIssue(CorpNum, KeyType, MgtKey, Memo, null);

    }

    /* (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#cancelIssue(java.lang.String, com.popbill.api.taxinvoice.MgtKeyType, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public Response cancelIssue(String CorpNum, MgtKeyType KeyType, String MgtKey, String Memo, String UserID) throws PopbillException {
        if (KeyType == null)
            throw new PopbillException(-99999999, "문서번호 유형이 입력되지 않았습니다.");

        if (ValidationUtils.isNullOrEmpty(MgtKey))
            throw new PopbillException(-99999999, "문서번호가 입력되지 않았습니다.");

        String PostData = toJsonString(new MemoRequest(Memo));

        return httppost("/Taxinvoice/" + KeyType.name() + "/" + MgtKey, CorpNum, PostData, UserID, "CANCELISSUE", Response.class);
    }

    @Override
    public Response registRequest(String CorpNum, Taxinvoice Taxinvoice, String Memo) throws PopbillException {
        return registRequest(CorpNum, Taxinvoice, Memo, "");
    }

    @Override
    public Response registRequest(String CorpNum, Taxinvoice Taxinvoice, String Memo, String UserID) throws PopbillException {

        if (Taxinvoice == null)
            throw new PopbillException(-99999999, "전자세금계산서 정보가 입력되지 않았습니다.");

        if (Memo != null)
            Taxinvoice.setMemo(Memo);

        String PostData = toJsonString(Taxinvoice);

        return httppost("/Taxinvoice", CorpNum, PostData, UserID, "REQUEST", Response.class);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#request(java.lang.String, com.popbill.api.taxinvoice.MgtKeyType, java.lang.String, java.lang.String)
     */
    @Override
    public Response request(String CorpNum, MgtKeyType KeyType, String MgtKey, String Memo) throws PopbillException {
        return request(CorpNum, KeyType, MgtKey, Memo, null);

    }

    /* (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#request(java.lang.String, com.popbill.api.taxinvoice.MgtKeyType, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public Response request(String CorpNum, MgtKeyType KeyType, String MgtKey, String Memo, String UserID) throws PopbillException {
        if (KeyType == null)
            throw new PopbillException(-99999999, "문서번호 유형이 입력되지 않았습니다.");

        if (ValidationUtils.isNullOrEmpty(MgtKey))
            throw new PopbillException(-99999999, "문서번호가 입력되지 않았습니다.");

        String PostData = toJsonString(new MemoRequest(Memo));

        return httppost("/Taxinvoice/" + KeyType.name() + "/" + MgtKey, CorpNum, PostData, UserID, "REQUEST", Response.class);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#cancelRequest(java.lang.String, com.popbill.api.taxinvoice.MgtKeyType, java.lang.String, java.lang.String)
     */
    @Override
    public Response cancelRequest(String CorpNum, MgtKeyType KeyType, String MgtKey, String Memo) throws PopbillException {
        return cancelRequest(CorpNum, KeyType, MgtKey, Memo, null);

    }

    /* (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#cancelRequest(java.lang.String, com.popbill.api.taxinvoice.MgtKeyType, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public Response cancelRequest(String CorpNum, MgtKeyType KeyType, String MgtKey, String Memo, String UserID) throws PopbillException {
        if (KeyType == null)
            throw new PopbillException(-99999999, "문서번호 유형이 입력되지 않았습니다.");

        if (ValidationUtils.isNullOrEmpty(MgtKey))
            throw new PopbillException(-99999999, "문서번호가 입력되지 않았습니다.");

        String PostData = toJsonString(new MemoRequest(Memo));

        return httppost("/Taxinvoice/" + KeyType.name() + "/" + MgtKey, CorpNum, PostData, UserID, "CANCELREQUEST", Response.class);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#refuse(java.lang.String, com.popbill.api.taxinvoice.MgtKeyType, java.lang.String, java.lang.String)
     */
    @Override
    public Response refuse(String CorpNum, MgtKeyType KeyType, String MgtKey, String Memo) throws PopbillException {
        return refuse(CorpNum, KeyType, MgtKey, Memo, null);

    }

    /* (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#refuse(java.lang.String, com.popbill.api.taxinvoice.MgtKeyType, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public Response refuse(String CorpNum, MgtKeyType KeyType, String MgtKey, String Memo, String UserID) throws PopbillException {
        if (KeyType == null)
            throw new PopbillException(-99999999, "문서번호 유형이 입력되지 않았습니다.");

        if (ValidationUtils.isNullOrEmpty(MgtKey))
            throw new PopbillException(-99999999, "문서번호가 입력되지 않았습니다.");

        String PostData = toJsonString(new MemoRequest(Memo));

        return httppost("/Taxinvoice/" + KeyType.name() + "/" + MgtKey, CorpNum, PostData, UserID, "REFUSE", Response.class);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#delete(java.lang.String, com.popbill.api.taxinvoice.MgtKeyType, java.lang.String)
     */
    @Override
    public Response delete(String CorpNum, MgtKeyType KeyType, String MgtKey) throws PopbillException {
        return delete(CorpNum, KeyType, MgtKey, null);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#delete(java.lang.String, com.popbill.api.taxinvoice.MgtKeyType, java.lang.String, java.lang.String)
     */
    @Override
    public Response delete(String CorpNum, MgtKeyType KeyType, String MgtKey, String UserID) throws PopbillException {
        if (KeyType == null)
            throw new PopbillException(-99999999, "문서번호 유형이 입력되지 않았습니다.");

        if (ValidationUtils.isNullOrEmpty(MgtKey))
            throw new PopbillException(-99999999, "문서번호가 입력되지 않았습니다.");

        return httppost("/Taxinvoice/" + KeyType.name() + "/" + MgtKey, CorpNum, null, UserID, "DELETE", Response.class);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#sendToNTS(java.lang.String, com.popbill.api.taxinvoice.MgtKeyType, java.lang.String)
     */
    @Override
    public Response sendToNTS(String CorpNum, MgtKeyType KeyType, String MgtKey) throws PopbillException {
        return sendToNTS(CorpNum, KeyType, MgtKey, null);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#sendToNTS(java.lang.String, com.popbill.api.taxinvoice.MgtKeyType, java.lang.String, java.lang.String)
     */
    @Override
    public Response sendToNTS(String CorpNum, MgtKeyType KeyType, String MgtKey, String UserID) throws PopbillException {
        if (KeyType == null)
            throw new PopbillException(-99999999, "문서번호 유형이 입력되지 않았습니다.");

        if (ValidationUtils.isNullOrEmpty(MgtKey))
            throw new PopbillException(-99999999, "문서번호가 입력되지 않았습니다.");

        return httppost("/Taxinvoice/" + KeyType.name() + "/" + MgtKey, CorpNum, null, UserID, "NTS", Response.class);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#getInfo(java.lang.String, com.popbill.api.taxinvoice.MgtKeyType, java.lang.String)
     */
    @Override
    public TaxinvoiceInfo getInfo(String CorpNum, MgtKeyType KeyType, String MgtKey) throws PopbillException {
        return getInfo(CorpNum, KeyType, MgtKey, "");
    }


    @Override
    public TaxinvoiceInfo getInfo(String CorpNum, MgtKeyType KeyType, String MgtKey, String UserID) throws PopbillException {
        if (KeyType == null)
            throw new PopbillException(-99999999, "문서번호 유형이 입력되지 않았습니다.");

        if (ValidationUtils.isNullOrEmpty(MgtKey))
            throw new PopbillException(-99999999, "문서번호가 입력되지 않았습니다.");

        return httpget("/Taxinvoice/" + KeyType.name() + "/" + MgtKey, CorpNum, UserID, TaxinvoiceInfo.class);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#getInfos(java.lang.String, com.popbill.api.taxinvoice.MgtKeyType, java.lang.String[])
     */
    @Override
    public TaxinvoiceInfo[] getInfos(String CorpNum, MgtKeyType KeyType, String[] MgtKeyList) throws PopbillException {
        return getInfos(CorpNum, KeyType, MgtKeyList, "");
    }

    @Override
    public TaxinvoiceInfo[] getInfos(String CorpNum, MgtKeyType KeyType, String[] MgtKeyList, String UserID) throws PopbillException {
        if (KeyType == null)
            throw new PopbillException(-99999999, "문서번호 유형이 입력되지 않았습니다.");

        if (ValidationUtils.isNullOrEmpty(MgtKeyList))
            throw new PopbillException(-99999999, "문서번호 목록이 입력되지 않았습니다.");

        String PostData = toJsonString(MgtKeyList);

        return httppost("/Taxinvoice/" + KeyType.name(), CorpNum, PostData, UserID, TaxinvoiceInfo[].class);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#getDetailInfo(java.lang.String, com.popbill.api.taxinvoice.MgtKeyType, java.lang.String)
     */
    @Override
    public Taxinvoice getDetailInfo(String CorpNum, MgtKeyType KeyType, String MgtKey) throws PopbillException {
        return getDetailInfo(CorpNum, KeyType, MgtKey, null);
    }

    @Override
    public Taxinvoice getDetailInfo(String CorpNum, MgtKeyType KeyType, String MgtKey, String UserID) throws PopbillException {
        if (KeyType == null)
            throw new PopbillException(-99999999, "문서번호 유형이 입력되지 않았습니다.");

        if (ValidationUtils.isNullOrEmpty(MgtKey))
            throw new PopbillException(-99999999, "문서번호가 입력되지 않았습니다.");

        return httpget("/Taxinvoice/" + KeyType.name() + "/" + MgtKey + "?Detail", CorpNum, UserID, Taxinvoice.class);
    }

    @Override
    public TaxinvoiceXML getXML(String CorpNum, MgtKeyType KeyType, String MgtKey) throws PopbillException {
        return getXML(CorpNum, KeyType, MgtKey, null);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#getXML(java.lang.String, com.popbill.api.taxinvoice.MgtKeyType, java.lang.String)
     */
    @Override
    public TaxinvoiceXML getXML(String CorpNum, MgtKeyType KeyType, String MgtKey, String UserID) throws PopbillException {
        if (KeyType == null)
            throw new PopbillException(-99999999, "문서번호 유형이 입력되지 않았습니다.");

        if (ValidationUtils.isNullOrEmpty(MgtKey))
            throw new PopbillException(-99999999, "문서번호가 입력되지 않았습니다.");

        return httpget("/Taxinvoice/" + KeyType.name() + "/" + MgtKey + "?XML", CorpNum, UserID, TaxinvoiceXML.class);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#Search(java.lang.String, com.popbill.api.taxinvoice.MgtKeyType, java.lang.String,
     *                                                     java.lang.String, java.lang.String, java.lang.String[], java.lang.String[],
     *                                                     boolean, integer, integer)
     */
    @Override
    public TISearchResult Search(String CorpNum, MgtKeyType KeyType, String DType, String SDate, String EDate, String[] State, String[] Type,
                                 String[] TaxType, Boolean LateOnly, Integer Page, Integer PerPage, String Order) throws PopbillException {

        return Search(CorpNum, KeyType, DType, SDate, EDate, State, Type, TaxType, null, LateOnly, null, null, null, null, Page, PerPage, Order, null,
                null, null, null, null);
    }

    /*
     * (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#Search(java.lang.String, com.popbill.api.taxinvoice.MgtKeyType, java.lang.String, java.lang.String, java.lang.String, java.lang.String[], java.lang.String[], java.lang.String[], java.lang.Boolean, java.lang.String, java.lang.String, java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.String)
     */
    @Override
    public TISearchResult Search(String CorpNum, MgtKeyType KeyType, String DType, String SDate, String EDate, String[] State, String[] Type,
                                 String[] TaxType, Boolean LateOnly, String TaxRegIDType, String TaxRegID, String TaxRegIDYN, Integer Page, Integer PerPage, String Order)
            throws PopbillException {

        return Search(CorpNum, KeyType, DType, SDate, EDate, State, Type, TaxType, null, LateOnly, TaxRegIDType, TaxRegID, TaxRegIDYN, null, Page,
                PerPage, Order, null, null, null, null, null);
    }

    /*
     * (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#Search(java.lang.String, com.popbill.api.taxinvoice.MgtKeyType, java.lang.String, java.lang.String, java.lang.String, java.lang.String[], java.lang.String[], java.lang.String[], java.lang.Boolean, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.String)
     */
    @Override
    public TISearchResult Search(String CorpNum, MgtKeyType KeyType, String DType, String SDate, String EDate, String[] State, String[] Type,
                                 String[] TaxType, Boolean LateOnly, String TaxRegIDType, String TaxRegID, String TaxRegIDYN, String QString, Integer Page,
                                 Integer PerPage, String Order) throws PopbillException {
        return Search(CorpNum, KeyType, DType, SDate, EDate, State, Type, TaxType, null, LateOnly, TaxRegIDType, TaxRegID, TaxRegIDYN, QString, Page,
                PerPage, Order, null);
    }

    @Override
    public TISearchResult Search(String CorpNum, MgtKeyType KeyType, String DType, String SDate, String EDate, String[] State, String[] Type,
                                 String[] TaxType, Boolean LateOnly, String TaxRegIDType, String TaxRegID, String TaxRegIDYN, String QString, Integer Page,
                                 Integer PerPage, String Order, String InterOPYN) throws PopbillException {

        return Search(CorpNum, KeyType, DType, SDate, EDate, State, Type, TaxType, null, LateOnly, TaxRegIDType, TaxRegID, TaxRegIDYN, QString, Page,
                PerPage, Order, InterOPYN, null, null, null, null);
    }

    /*
     * (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#Search(java.lang.String, com.popbill.api.taxinvoice.MgtKeyType, java.lang.String, java.lang.String, java.lang.String, java.lang.String[], java.lang.String[], java.lang.String[], java.lang.String[], java.lang.Boolean, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.String)
     */
    @Override
    public TISearchResult Search(String CorpNum, MgtKeyType KeyType, String DType, String SDate, String EDate, String[] State, String[] Type,
                                 String[] TaxType, String[] IssueType, Boolean LateOnly, String TaxRegIDType, String TaxRegID, String TaxRegIDYN, String QString,
                                 Integer Page, Integer PerPage, String Order, String InterOPYN) throws PopbillException {
        return Search(CorpNum, KeyType, DType, SDate, EDate, State, Type, TaxType, IssueType, LateOnly, TaxRegIDType, TaxRegID, TaxRegIDYN, QString, Page,
                PerPage, Order, InterOPYN, null, null, null, null);
    }

    @Override
    public TISearchResult Search(String CorpNum, MgtKeyType KeyType, String DType, String SDate, String EDate, String[] State, String[] Type,
                                 String[] TaxType, String[] IssueType, Boolean LateOnly, String TaxRegIDType, String TaxRegID, String TaxRegIDYN, String QString,
                                 Integer Page, Integer PerPage, String Order, String InterOPYN, String[] RegType) throws PopbillException {

        return Search(CorpNum, KeyType, DType, SDate, EDate, State, Type, TaxType, IssueType, LateOnly, TaxRegIDType, TaxRegID, TaxRegIDYN, QString, Page,
                PerPage, Order, InterOPYN, RegType, null, null, null);
    }

    @Override
    public TISearchResult Search(String CorpNum, MgtKeyType KeyType, String DType, String SDate, String EDate, String[] State, String[] Type,
                                 String[] TaxType, String[] IssueType, Boolean LateOnly, String TaxRegIDType, String TaxRegID, String TaxRegIDYN, String QString,
                                 Integer Page, Integer PerPage, String Order, String InterOPYN, String[] RegType, String[] CloseDownState, String MgtKey, String UserID)
            throws PopbillException {

        if (KeyType == null)
            throw new PopbillException(-99999999, "문서번호 유형이 입력되지 않았습니다.");

        String uri = "/Taxinvoice/" + KeyType;
        uri += "?DType=" + DType;
        uri += "&SDate=" + SDate;
        uri += "&EDate=" + EDate;

        if (!ValidationUtils.isNullOrEmpty(State))
            uri += "&State=" + ValidationUtils.replaceInvalidUriChars(State);

        if (!ValidationUtils.isNullOrEmpty(Type))
            uri += "&Type=" + ValidationUtils.replaceInvalidUriChars(Type);

        if (!ValidationUtils.isNullOrEmpty(TaxType))
            uri += "&TaxType=" + ValidationUtils.replaceInvalidUriChars(TaxType);

        if (!ValidationUtils.isNullOrEmpty(IssueType))
            uri += "&IssueType=" + ValidationUtils.replaceInvalidUriChars(IssueType);

        if (LateOnly != null) {
            if (LateOnly) {
                uri += "&LateOnly=1";
            } else {
                uri += "&LateOnly=0";
            }
        }

        if (!ValidationUtils.isNullOrEmpty(TaxRegIDType))
            uri += "&TaxRegIDType=" + TaxRegIDType;

        if (!ValidationUtils.isNullOrEmpty(TaxRegID))
            uri += "&TaxRegID=" + TaxRegID;

        if (!ValidationUtils.isNullOrEmpty(TaxRegIDYN))
            uri += "&TaxRegIDYN=" + TaxRegIDYN;

        if (!ValidationUtils.isNullOrEmpty(QString)) {
            try {
                uri += "&QString=" + URLEncoder.encode(QString, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                throw new PopbillException(-99999999, "검색어 인코딩이 실패 되었습니다.");
            }
        }

        if (Page != null && Page > 0)
            uri += "&Page=" + Integer.toString(Page);

        if(PerPage != null && PerPage > 0 && PerPage <= 1000)
            uri += "&PerPage="+ Integer.toString(PerPage);

        if (Order != null && (Order.equals("D") || Order.equals("A")))
            uri += "&Order=" + Order;

        if (!ValidationUtils.isNullOrEmpty(InterOPYN))
            uri += "&InterOPYN=" + InterOPYN;

        if (!ValidationUtils.isNullOrEmpty(RegType))
            uri += "&RegType=" + ValidationUtils.replaceInvalidUriChars(RegType);

        if (!ValidationUtils.isNullOrEmpty(CloseDownState))
            uri += "&CloseDownState=" + ValidationUtils.replaceInvalidUriChars(CloseDownState);

        if (!ValidationUtils.isNullOrEmpty(MgtKey))
            uri += "&MgtKey=" + MgtKey;

        return httpget(uri, CorpNum, UserID, TISearchResult.class);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#getLogs(java.lang.String, com.popbill.api.taxinvoice.MgtKeyType, java.lang.String)
     */
    @Override
    public TaxinvoiceLog[] getLogs(String CorpNum, MgtKeyType KeyType, String MgtKey) throws PopbillException {
        return getLogs(CorpNum, KeyType, MgtKey, "");
    }

    @Override
    public TaxinvoiceLog[] getLogs(String CorpNum, MgtKeyType KeyType, String MgtKey, String UserID) throws PopbillException {
        if (KeyType == null)
            throw new PopbillException(-99999999, "문서번호 유형이 입력되지 않았습니다.");

        if (ValidationUtils.isNullOrEmpty(MgtKey))
            throw new PopbillException(-99999999, "문서번호가 입력되지 않았습니다.");

        return httpget("/Taxinvoice/" + KeyType.name() + "/" + MgtKey + "/Logs", CorpNum, UserID, TaxinvoiceLog[].class);
    }

    /*
     * (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#getURL(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public String getURL(String CorpNum, String TOGO) throws PopbillException {

        return getURL(CorpNum, null, TOGO);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#getURL(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public String getURL(String CorpNum, String UserID, String TOGO) throws PopbillException {

        URLResponse response = httpget("/Taxinvoice?TG=" + TOGO, CorpNum, UserID, URLResponse.class);

        return response.url;
    }

    /*
     * (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#getPopUpURL(java.lang.String, com.popbill.api.taxinvoice.MgtKeyType, java.lang.String)
     */
    @Override
    public String getPopUpURL(String CorpNum, MgtKeyType KeyType, String MgtKey) throws PopbillException {
        return getPopUpURL(CorpNum, KeyType, MgtKey, null);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#getPopUpURL(java.lang.String, com.popbill.api.taxinvoice.MgtKeyType, java.lang.String, java.lang.String)
     */
    @Override
    public String getPopUpURL(String CorpNum, MgtKeyType KeyType, String MgtKey, String UserID) throws PopbillException {
        if (KeyType == null)
            throw new PopbillException(-99999999, "문서번호 유형이 입력되지 않았습니다.");

        if (ValidationUtils.isNullOrEmpty(MgtKey))
            throw new PopbillException(-99999999, "문서번호가 입력되지 않았습니다.");

        URLResponse response = httpget("/Taxinvoice/" + KeyType.name() + "/" + MgtKey + "?TG=POPUP", CorpNum, UserID, URLResponse.class);

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
            throw new PopbillException(-99999999, "문서번호 유형이 입력되지 않았습니다.");

        if (ValidationUtils.isNullOrEmpty(MgtKey))
            throw new PopbillException(-99999999, "문서번호가 입력되지 않았습니다.");

        URLResponse response = httpget("/Taxinvoice/" + KeyType.name() + "/" + MgtKey + "?TG=VIEW", CorpNum, UserID, URLResponse.class);

        return response.url;
    }

    /*
     * (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#getPrintURL(java.lang.String, com.popbill.api.taxinvoice.MgtKeyType, java.lang.String)
     */
    @Override
    public String getPrintURL(String CorpNum, MgtKeyType KeyType, String MgtKey) throws PopbillException {

        return getPrintURL(CorpNum, KeyType, MgtKey, null);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#getPrintURL(java.lang.String, com.popbill.api.taxinvoice.MgtKeyType, java.lang.String, java.lang.String)
     */
    @Override
    public String getPrintURL(String CorpNum, MgtKeyType KeyType, String MgtKey, String UserID) throws PopbillException {
        if (KeyType == null)
            throw new PopbillException(-99999999, "문서번호 유형이 입력되지 않았습니다.");

        if (ValidationUtils.isNullOrEmpty(MgtKey))
            throw new PopbillException(-99999999, "문서번호가 입력되지 않았습니다.");

        URLResponse response = httpget("/Taxinvoice/" + KeyType.name() + "/" + MgtKey + "?TG=PRINT", CorpNum, UserID, URLResponse.class);

        return response.url;
    }

    /*
     * (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#getOldPrintURL(java.lang.String, com.popbill.api.taxinvoice.MgtKeyType, java.lang.String)
     */
    @Override
    public String getOldPrintURL(String CorpNum, MgtKeyType KeyType, String MgtKey) throws PopbillException {
        return getOldPrintURL(CorpNum, KeyType, MgtKey, null);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#getOldPrintURL(java.lang.String, com.popbill.api.taxinvoice.MgtKeyType, java.lang.String, java.lang.String)
     */
    @Override
    public String getOldPrintURL(String CorpNum, MgtKeyType KeyType, String MgtKey, String UserID) throws PopbillException {
        if (KeyType == null)
            throw new PopbillException(-99999999, "문서번호 유형이 입력되지 않았습니다.");

        if (ValidationUtils.isNullOrEmpty(MgtKey))
            throw new PopbillException(-99999999, "문서번호가 입력되지 않았습니다.");

        URLResponse response = httpget("/Taxinvoice/" + KeyType.name() + "/" + MgtKey + "?TG=PRINTOLD", CorpNum, UserID, URLResponse.class);

        return response.url;
    }

    /*
     * (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#getEPrintURL(java.lang.String, com.popbill.api.taxinvoice.MgtKeyType, java.lang.String)
     */
    @Override
    public String getEPrintURL(String CorpNum, MgtKeyType KeyType, String MgtKey) throws PopbillException {
        return getEPrintURL(CorpNum, KeyType, MgtKey, null);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#getEPrintURL(java.lang.String, com.popbill.api.taxinvoice.MgtKeyType, java.lang.String, java.lang.String)
     */
    @Override
    public String getEPrintURL(String CorpNum, MgtKeyType KeyType, String MgtKey, String UserID) throws PopbillException {
        if (KeyType == null)
            throw new PopbillException(-99999999, "문서번호 유형이 입력되지 않았습니다.");

        if (ValidationUtils.isNullOrEmpty(MgtKey))
            throw new PopbillException(-99999999, "문서번호가 입력되지 않았습니다.");

        URLResponse response = httpget("/Taxinvoice/" + KeyType.name() + "/" + MgtKey + "?TG=EPRINT", CorpNum, UserID, URLResponse.class);

        return response.url;
    }

    /*
     * (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#getMassPrintURL(java.lang.String, com.popbill.api.taxinvoice.MgtKeyType, java.lang.String[])
     */
    @Override
    public String getMassPrintURL(String CorpNum, MgtKeyType KeyType, String[] MgtKeyList) throws PopbillException {

        return getMassPrintURL(CorpNum, KeyType, MgtKeyList, null);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#getMassPrintURL(java.lang.String, com.popbill.api.taxinvoice.MgtKeyType, java.lang.String[], java.lang.String)
     */
    @Override
    public String getMassPrintURL(String CorpNum, MgtKeyType KeyType, String[] MgtKeyList, String UserID) throws PopbillException {
        if (KeyType == null)
            throw new PopbillException(-99999999, "문서번호 유형이 입력되지 않았습니다.");

        if (ValidationUtils.isNullOrEmpty(MgtKeyList))
            throw new PopbillException(-99999999, "문서번호 목록이 입력되지 않았습니다.");

        String PostData = toJsonString(MgtKeyList);

        URLResponse response = httppost("/Taxinvoice/" + KeyType.name() + "?Print", CorpNum, PostData, UserID, URLResponse.class);

        return response.url;
    }

    /*
     * (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#getMailURL(java.lang.String, com.popbill.api.taxinvoice.MgtKeyType, java.lang.String)
     */
    @Override
    public String getMailURL(String CorpNum, MgtKeyType KeyType, String MgtKey) throws PopbillException {
        return getMailURL(CorpNum, KeyType, MgtKey, null);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#getMailURL(java.lang.String, com.popbill.api.taxinvoice.MgtKeyType, java.lang.String, java.lang.String)
     */
    @Override
    public String getMailURL(String CorpNum, MgtKeyType KeyType, String MgtKey, String UserID) throws PopbillException {
        if (KeyType == null)
            throw new PopbillException(-99999999, "문서번호 유형이 입력되지 않았습니다.");

        if (ValidationUtils.isNullOrEmpty(MgtKey))
            throw new PopbillException(-99999999, "문서번호가 입력되지 않았습니다.");

        URLResponse response = httpget("/Taxinvoice/" + KeyType.name() + "/" + MgtKey + "?TG=MAIL", CorpNum, UserID, URLResponse.class);

        return response.url;
    }

    /* (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#getPDFURL(java.lang.String, com.popbill.api.taxinvoice.MgtKeyType, java.lang.String)
     */
    @Override
    public String getPDFURL(String CorpNum, MgtKeyType KeyType, String MgtKey) throws PopbillException {

        return getPDFURL(CorpNum, KeyType, MgtKey, null);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#getPDFURL(java.lang.String, com.popbill.api.taxinvoice.MgtKeyType, java.lang.String, java.lang.String)
     */
    @Override
    public String getPDFURL(String CorpNum, MgtKeyType KeyType, String MgtKey, String UserID) throws PopbillException {
        if (KeyType == null)
            throw new PopbillException(-99999999, "문서번호 유형이 입력되지 않았습니다.");

        if (ValidationUtils.isNullOrEmpty(MgtKey))
            throw new PopbillException(-99999999, "문서번호가 입력되지 않았습니다.");

        URLResponse response = httpget("/Taxinvoice/" + KeyType.name() + "/" + MgtKey + "?TG=PDF", CorpNum, UserID, URLResponse.class);

        return response.url;
    }

    @Override
    public String getSealURL(String CorpNum, String UserID) throws PopbillException {

        URLResponse response = httpget("/Member?TG=SEAL", CorpNum, UserID, URLResponse.class);

        return response.url;
    }

    /* (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#attachFile(java.lang.String, com.popbill.api.taxinvoice.MgtKeyType, java.lang.String, java.lang.String, java.io.InputStream)
     */
    @Override
    public Response attachFile(String CorpNum, MgtKeyType KeyType, String MgtKey, String DisplayName, InputStream FileData) throws PopbillException {
        return attachFile(CorpNum, KeyType, MgtKey, DisplayName, FileData, null);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#attachFile(java.lang.String, com.popbill.api.taxinvoice.MgtKeyType, java.lang.String, java.lang.String, java.io.InputStream, java.lang.String)
     */
    @Override
    public Response attachFile(String CorpNum, MgtKeyType KeyType, String MgtKey, String DisplayName, InputStream FileData, String UserID)
            throws PopbillException {
        if (KeyType == null)
            throw new PopbillException(-99999999, "문서번호 유형이 입력되지 않았습니다.");

        if (ValidationUtils.isNullOrEmpty(MgtKey))
            throw new PopbillException(-99999999, "문서번호가 입력되지 않았습니다.");

        if (ValidationUtils.isNullOrEmpty(DisplayName))
            throw new PopbillException(-99999999, "파일명이 입력되지 않았습니다.");

        if (FileData == null)
            throw new PopbillException(-99999999, "파일 데이터가 입력되지 않았습니다.");

        List<UploadFile> files = new ArrayList<BaseServiceImp.UploadFile>();
        UploadFile file = new UploadFile();
        file.fileName = DisplayName;
        file.fieldName = "Filedata";
        file.fileData = FileData;

        files.add(file);

        return httppostFiles("/Taxinvoice/" + KeyType.name() + "/" + MgtKey + "/Files", CorpNum, null, files, UserID, Response.class);

    }

    /* (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#deleteFile(java.lang.String, com.popbill.api.taxinvoice.MgtKeyType, java.lang.String, java.lang.String)
     */
    @Override
    public Response deleteFile(String CorpNum, MgtKeyType KeyType, String MgtKey, String FileID) throws PopbillException {
        return deleteFile(CorpNum, KeyType, MgtKey, FileID, null);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#deleteFile(java.lang.String, com.popbill.api.taxinvoice.MgtKeyType, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public Response deleteFile(String CorpNum, MgtKeyType KeyType, String MgtKey, String FileID, String UserID) throws PopbillException {
        if (KeyType == null)
            throw new PopbillException(-99999999, "문서번호 유형이 입력되지 않았습니다.");

        if (ValidationUtils.isNullOrEmpty(MgtKey))
            throw new PopbillException(-99999999, "문서번호가 입력되지 않았습니다.");

        if (ValidationUtils.isNullOrEmpty(FileID))
            throw new PopbillException(-99999999, "파일 식별번호가 입력되지 않았습니다.");

        return httppost("/Taxinvoice/" + KeyType.name() + "/" + MgtKey + "/Files/" + FileID, CorpNum, null, UserID, "DELETE", Response.class);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#getFiles(java.lang.String, com.popbill.api.taxinvoice.MgtKeyType, java.lang.String)
     */
    @Override
    public AttachedFile[] getFiles(String CorpNum, MgtKeyType KeyType, String MgtKey) throws PopbillException {
        return getFiles(CorpNum, KeyType, MgtKey, "");
    }

    @Override
    public AttachedFile[] getFiles(String CorpNum, MgtKeyType KeyType, String MgtKey, String UserID) throws PopbillException {
        if (KeyType == null)
            throw new PopbillException(-99999999, "문서번호 유형이 입력되지 않았습니다.");

        if (ValidationUtils.isNullOrEmpty(MgtKey))
            throw new PopbillException(-99999999, "문서번호가 입력되지 않았습니다.");

        return httpget("/Taxinvoice/" + KeyType.name() + "/" + MgtKey + "/Files", CorpNum, UserID, AttachedFile[].class);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#sendEmail(java.lang.String, com.popbill.api.taxinvoice.MgtKeyType, java.lang.String, java.lang.String)
     */
    @Override
    public Response sendEmail(String CorpNum, MgtKeyType KeyType, String MgtKey, String Receiver) throws PopbillException {
        return sendEmail(CorpNum, KeyType, MgtKey, Receiver, null);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#sendEmail(java.lang.String, com.popbill.api.taxinvoice.MgtKeyType, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public Response sendEmail(String CorpNum, MgtKeyType KeyType, String MgtKey, String Receiver, String UserID) throws PopbillException {
        if (KeyType == null)
            throw new PopbillException(-99999999, "문서번호 유형이 입력되지 않았습니다.");

        if (ValidationUtils.isNullOrEmpty(MgtKey))
            throw new PopbillException(-99999999, "문서번호가 입력되지 않았습니다.");

        ResendRequest request = new ResendRequest();

        request.receiver = Receiver;

        String PostData = toJsonString(request);

        return httppost("/Taxinvoice/" + KeyType.name() + "/" + MgtKey, CorpNum, PostData, UserID, "EMAIL", Response.class);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#sendSMS(java.lang.String, com.popbill.api.taxinvoice.MgtKeyType, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public Response sendSMS(String CorpNum, MgtKeyType KeyType, String MgtKey, String Sender, String Receiver, String Contents)
            throws PopbillException {
        return sendSMS(CorpNum, KeyType, MgtKey, Sender, Receiver, Contents, null);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#sendSMS(java.lang.String, com.popbill.api.taxinvoice.MgtKeyType, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public Response sendSMS(String CorpNum, MgtKeyType KeyType, String MgtKey, String Sender, String Receiver, String Contents, String UserID)
            throws PopbillException {
        if (KeyType == null)
            throw new PopbillException(-99999999, "문서번호 유형이 입력되지 않았습니다.");

        if (ValidationUtils.isNullOrEmpty(MgtKey))
            throw new PopbillException(-99999999, "문서번호가 입력되지 않았습니다.");

        ResendRequest request = new ResendRequest();

        request.sender = Sender;
        request.receiver = Receiver;
        request.contents = Contents;

        String PostData = toJsonString(request);

        return httppost("/Taxinvoice/" + KeyType.name() + "/" + MgtKey, CorpNum, PostData, UserID, "SMS", Response.class);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#sendFAX(java.lang.String, com.popbill.api.taxinvoice.MgtKeyType, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public Response sendFAX(String CorpNum, MgtKeyType KeyType, String MgtKey, String Sender, String Receiver) throws PopbillException {
        return sendFAX(CorpNum, KeyType, MgtKey, Sender, Receiver, null);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#sendFAX(java.lang.String, com.popbill.api.taxinvoice.MgtKeyType, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public Response sendFAX(String CorpNum, MgtKeyType KeyType, String MgtKey, String Sender, String Receiver, String UserID)
            throws PopbillException {
        if (KeyType == null)
            throw new PopbillException(-99999999, "문서번호 유형이 입력되지 않았습니다.");

        if (ValidationUtils.isNullOrEmpty(MgtKey))
            throw new PopbillException(-99999999, "문서번호가 입력되지 않았습니다.");

        ResendRequest request = new ResendRequest();

        request.sender = Sender;
        request.receiver = Receiver;

        String PostData = toJsonString(request);

        return httppost("/Taxinvoice/" + KeyType.name() + "/" + MgtKey, CorpNum, PostData, UserID, "FAX", Response.class);
    }

    /*

    /*
     * (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#attachStatement(java.lang.String, com.popbill.api.taxinvoice.MgtKeyType, java.lang.String, int, java.lang.String)
     */
    @Override
    public Response attachStatement(String CorpNum, MgtKeyType KeyType, String MgtKey, int SubItemCode, String SubMgtKey) throws PopbillException {
        return attachStatement(CorpNum, KeyType, MgtKey, SubItemCode, SubMgtKey, "");
    }

    @Override
    public Response attachStatement(String CorpNum, MgtKeyType KeyType, String MgtKey, int SubItemCode, String SubMgtKey, String UserID) throws PopbillException {

        if (KeyType == null)
            throw new PopbillException(-99999999, "문서번호 유형이 입력되지 않았습니다.");

        if (ValidationUtils.isNullOrEmpty(MgtKey))
            throw new PopbillException(-99999999, "문서번호가 입력되지 않았습니다.");

        DocRequest request = new DocRequest();
        request.ItemCode = Integer.toString(SubItemCode);
        request.MgtKey = SubMgtKey;

        String PostData = toJsonString(request);

        return httppost("/Taxinvoice/" + KeyType.name() + "/" + MgtKey + "/AttachStmt", CorpNum, PostData, UserID, "", Response.class);
    }

    /*
     * (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#detachStatement(java.lang.String, com.popbill.api.taxinvoice.MgtKeyType, java.lang.String, int, java.lang.String)
     */
    @Override
    public Response detachStatement(String CorpNum, MgtKeyType KeyType, String MgtKey, int SubItemCode, String SubMgtKey) throws PopbillException {
        return detachStatement(CorpNum, KeyType, MgtKey, SubItemCode, SubMgtKey, "");
    }

    @Override
    public Response detachStatement(String CorpNum, MgtKeyType KeyType, String MgtKey, int SubItemCode, String SubMgtKey, String UserID) throws PopbillException {

        if (KeyType == null)
            throw new PopbillException(-99999999, "문서번호 유형이 입력되지 않았습니다.");

        if (ValidationUtils.isNullOrEmpty(MgtKey))
            throw new PopbillException(-99999999, "문서번호가 입력되지 않았습니다.");

        DocRequest request = new DocRequest();
        request.ItemCode = Integer.toString(SubItemCode);
        request.MgtKey = SubMgtKey;

        String PostData = toJsonString(request);

        return httppost("/Taxinvoice/" + KeyType.name() + "/" + MgtKey + "/DetachStmt", CorpNum, PostData, UserID, "", Response.class);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#getEmailPublicKeys(java.lang.String)
     */
    @Override
    public EmailPublicKey[] getEmailPublicKeys(String CorpNum) throws PopbillException {

        return httpget("/Taxinvoice/EmailPublicKeys", CorpNum, null, EmailPublicKey[].class);
    }

    /*
     * (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#assignMgtKey(java.lang.String, com.popbill.api.taxinvoice.MgtKeyType, java.lang.String, java.lang.String)
     */
    public Response assignMgtKey(String CorpNum, MgtKeyType KeyType, String ItemKey, String MgtKey) throws PopbillException {

        return assignMgtKey(CorpNum, KeyType, ItemKey, MgtKey, null);
    }

    /*
     * (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#assignMgtKey(java.lang.String, com.popbill.api.taxinvoice.MgtKeyType, java.lang.String, java.lang.String, java.lang.String)
     */
    public Response assignMgtKey(String CorpNum, MgtKeyType KeyType, String ItemKey, String MgtKey, String UserID) throws PopbillException {
        if (KeyType == null)
            throw new PopbillException(-99999999, "문서번호 유형이 입력되지 않았습니다.");

        if (ValidationUtils.isNullOrEmpty(ItemKey))
            throw new PopbillException(-99999999, "팝빌에서 할당한 식별번호가 입력되지 않았습니다.");

        String PostData = "MgtKey=" + MgtKey;

        return httppost("/Taxinvoice/" + ItemKey + "/" + KeyType.name(), CorpNum, PostData, UserID, "", Response.class,
                "application/x-www-form-urlencoded; charset=utf-8");
    }

    /*
     * (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#listEmailConfig(java.lang.String)
     */
    @Override
    public EmailSendConfig[] listEmailConfig(String CorpNum) throws PopbillException {
        return listEmailConfig(CorpNum, null);
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
    public Response updateEmailConfig(String CorpNum, String EmailType, Boolean SendYN, String UserID) throws PopbillException {
        if (SendYN == null)
            throw new PopbillException(-99999999, "메일 전송 여부가 입력되지 않았습니다.");

        return httppost("/Taxinvoice/EmailSendConfig?EmailType=" + EmailType + "&SendYN=" + String.valueOf(SendYN), CorpNum, null, UserID, "",
                Response.class);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#getSendToNTSConfig(java.lang.String)
     */
    @Override
    public boolean getSendToNTSConfig(String CorpNum) throws PopbillException {

        return getSendToNTSConfig(CorpNum, null);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#getSendToNTSConfig(java.lang.String, java.lang.String)
     */
    @Override
    public boolean getSendToNTSConfig(String CorpNum, String UserID) throws PopbillException {

        return httpget("/Taxinvoice/SendToNTSConfig", CorpNum, UserID, SendToNTSConfig.class).getSendToNTS();
    }

    @Override
    public String getTaxCertURL(String CorpNum, String UserID) throws PopbillException {

        URLResponse response = httpget("/Member?TG=CERT", CorpNum, UserID, URLResponse.class);

        return response.url;
    }

    /* (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#getCertificateExpireDate(java.lang.String)
     */
    @Override
    public Date getCertificateExpireDate(String CorpNum) throws PopbillException {
        return getCertificateExpireDate(CorpNum, null);
    }

    @Override
    public Date getCertificateExpireDate(String CorpNum, String UserID) throws PopbillException {

        CertResponse response = httpget("/Taxinvoice?cfg=CERT", CorpNum, UserID, CertResponse.class);

        try {
            return new SimpleDateFormat("yyyyMMddHHmmss").parse(response.certificateExpiration);
        } catch (ParseException e) {
            throw new PopbillException(-99999999, "날자형식 포맷변환 실패[" + response.certificateExpiration + "]", e);
        }
    }

    /*
     * (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#checkCertValidation(java.lang.String)
     */
    @Override
    public Response checkCertValidation(String CorpNum) throws PopbillException {
        return checkCertValidation(CorpNum, null);
    }

    @Override
    public Response checkCertValidation(String CorpNum, String UserID) throws PopbillException {
        return httpget("/Taxinvoice/CertCheck", CorpNum, UserID, Response.class);
    }

    @Override
    public TaxinvoiceCertificate getTaxCertInfo(String CorpNum) throws PopbillException {

        return getTaxCertInfo(CorpNum, null);
    }

    @Override
    public TaxinvoiceCertificate getTaxCertInfo(String CorpNum, String UserID) throws PopbillException {

        return httpget("/Taxinvoice/Certificate", CorpNum, UserID, TaxinvoiceCertificate.class);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#getUnitCost(java.lang.String)
     */
    @Override
    public float getUnitCost(String CorpNum) throws PopbillException {
        return getUnitCost(CorpNum, null);
    }

    @Override
    public float getUnitCost(String CorpNum, String UserID) throws PopbillException {
        UnitCostResponse response = httpget("/Taxinvoice?cfg=UNITCOST", CorpNum, UserID, UnitCostResponse.class);

        return response.unitCost;
    }

    /*
     * (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#getChargeInfo(java.lang.String)
     */
    @Override
    public ChargeInfo getChargeInfo(String CorpNum) throws PopbillException {
        return getChargeInfo(CorpNum, null);
    }

    /*
     * (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#getChargeInfo(java.lang.String)
     */
    @Override
    public ChargeInfo getChargeInfo(String CorpNum, String UserID) throws PopbillException {
        return httpget("/Taxinvoice/ChargeInfo", CorpNum, UserID, ChargeInfo.class);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#send(java.lang.String, com.popbill.api.taxinvoice.MgtKeyType, java.lang.String, java.lang.String)
     */
    @Override
    public Response send(String CorpNum, MgtKeyType KeyType, String MgtKey, String Memo) throws PopbillException {
        return send(CorpNum, KeyType, MgtKey, Memo, null);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#send(java.lang.String, com.popbill.api.taxinvoice.MgtKeyType, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public Response send(String CorpNum, MgtKeyType KeyType, String MgtKey, String Memo, String UserID) throws PopbillException {
        return send(CorpNum, KeyType, MgtKey, Memo, null, UserID);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#send(java.lang.String, com.popbill.api.taxinvoice.MgtKeyType, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public Response send(String CorpNum, MgtKeyType KeyType, String MgtKey, String Memo, String EmailSubject, String UserID) throws PopbillException {
        if (KeyType == null)
            throw new PopbillException(-99999999, "문서번호 유형이 입력되지 않았습니다.");

        if (ValidationUtils.isNullOrEmpty(MgtKey))
            throw new PopbillException(-99999999, "문서번호가 입력되지 않았습니다.");

        SendRequest request = new SendRequest();
        request.memo = Memo;
        request.emailSubject = EmailSubject;

        String PostData = toJsonString(request);

        return httppost("/Taxinvoice/" + KeyType.name() + "/" + MgtKey, CorpNum, PostData, UserID, "SEND", Response.class);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#cancelSend(java.lang.String, com.popbill.api.taxinvoice.MgtKeyType, java.lang.String, java.lang.String)
     */
    @Override
    public Response cancelSend(String CorpNum, MgtKeyType KeyType, String MgtKey, String Memo) throws PopbillException {
        return cancelSend(CorpNum, KeyType, MgtKey, Memo, null);

    }

    /* (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#cancelSend(java.lang.String, com.popbill.api.taxinvoice.MgtKeyType, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public Response cancelSend(String CorpNum, MgtKeyType KeyType, String MgtKey, String Memo, String UserID) throws PopbillException {
        if (KeyType == null)
            throw new PopbillException(-99999999, "문서번호 유형이 입력되지 않았습니다.");

        if (ValidationUtils.isNullOrEmpty(MgtKey))
            throw new PopbillException(-99999999, "문서번호가 입력되지 않았습니다.");

        String PostData = toJsonString(new MemoRequest(Memo));

        return httppost("/Taxinvoice/" + KeyType.name() + "/" + MgtKey, CorpNum, PostData, UserID, "CANCELSEND", Response.class);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#accept(java.lang.String, com.popbill.api.taxinvoice.MgtKeyType, java.lang.String, java.lang.String)
     */
    @Override
    public Response accept(String CorpNum, MgtKeyType KeyType, String MgtKey, String Memo) throws PopbillException {
        return accept(CorpNum, KeyType, MgtKey, Memo, null);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#accept(java.lang.String, com.popbill.api.taxinvoice.MgtKeyType, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public Response accept(String CorpNum, MgtKeyType KeyType, String MgtKey, String Memo, String UserID) throws PopbillException {
        if (KeyType == null)
            throw new PopbillException(-99999999, "문서번호 유형이 입력되지 않았습니다.");

        if (ValidationUtils.isNullOrEmpty(MgtKey))
            throw new PopbillException(-99999999, "문서번호가 입력되지 않았습니다.");

        String PostData = toJsonString(new MemoRequest(Memo));

        return httppost("/Taxinvoice/" + KeyType.name() + "/" + MgtKey, CorpNum, PostData, UserID, "ACCEPT", Response.class);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#deny(java.lang.String, com.popbill.api.taxinvoice.MgtKeyType, java.lang.String, java.lang.String)
     */
    @Override
    public Response deny(String CorpNum, MgtKeyType KeyType, String MgtKey, String Memo) throws PopbillException {
        return deny(CorpNum, KeyType, MgtKey, Memo, null);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#deny(java.lang.String, com.popbill.api.taxinvoice.MgtKeyType, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public Response deny(String CorpNum, MgtKeyType KeyType, String MgtKey, String Memo, String UserID) throws PopbillException {
        if (KeyType == null)
            throw new PopbillException(-99999999, "문서번호 유형이 입력되지 않았습니다.");

        if (ValidationUtils.isNullOrEmpty(MgtKey))
            throw new PopbillException(-99999999, "문서번호가 입력되지 않았습니다.");

        String PostData = toJsonString(new MemoRequest(Memo));

        return httppost("/Taxinvoice/" + KeyType.name() + "/" + MgtKey, CorpNum, PostData, UserID, "DENY", Response.class);
    }

    /*
     * (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#getPDF(java.lang.String, com.popbill.api.taxinvoice.MgtKeyType, java.lang.String)
     */
    @Override
    public byte[] getPDF(String CorpNum, MgtKeyType KeyType, String MgtKey) throws PopbillException {

        return getPDF(CorpNum, KeyType, MgtKey, null);
    }

    /*
     * (non-Javadoc)
     * @see com.popbill.api.TaxinvoiceService#getPDF(java.lang.String, com.popbill.api.taxinvoice.MgtKeyType, java.lang.String, java.lang.String)
     */
    @Override
    public byte[] getPDF(String CorpNum, MgtKeyType KeyType, String MgtKey, String UserID) throws PopbillException {
        if (KeyType == null)
            throw new PopbillException(-99999999, "문서번호 유형이 입력되지 않았습니다.");

        if (ValidationUtils.isNullOrEmpty(MgtKey))
            throw new PopbillException(-99999999, "문서번호가 입력되지 않았습니다.");

        byte[] result = httpget("/Taxinvoice/" + KeyType.name() + "/" + MgtKey + "?PDF", CorpNum, UserID, byte[].class);

        return result;
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

    protected class TaxCertRequest {
        public String certPublicKey;
        public String certPrivateKey;
        public String certCipher;
    }

    protected class CertPFXRequest {
        public String pfx;
        public String password;
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
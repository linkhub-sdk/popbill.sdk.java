package com.popbill.api.statement;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.popbill.api.AttachedFile;
import com.popbill.api.BaseServiceImp;
import com.popbill.api.ChargeInfo;
import com.popbill.api.EmailSendConfig;
import com.popbill.api.PopbillException;
import com.popbill.api.Response;
import com.popbill.api.SMTIssueResponse;
import com.popbill.api.StatementService;
import com.popbill.api.util.ValidationUtils;

/**
 * Implementation of Popbill StatemenService Interface
 * 
 * @author KimSeongjun
 * @version 1.0.0
 * @see com.popbill.api.TaxinvoiceService
 */
public class StatementServiceImp extends BaseServiceImp implements StatementService {

    @Override
    protected List<String> getScopes() {
        return Arrays.asList("121", "122", "123", "124", "125", "126");
    }

    /*
     * (non-Javadoc)
     * @see com.popbill.api.StatementService#getURL(java.lang.String, java.lang.String)
     */
    @Override
    public String getURL(String CorpNum, String TOGO) throws PopbillException {
        return getURL(CorpNum, null, TOGO);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.StatementService#getURL(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public String getURL(String CorpNum, String UserID, String TOGO) throws PopbillException {

        URLResponse response = httpget("/Statement?TG=" + TOGO, CorpNum, UserID, URLResponse.class);

        return response.url;
    }

    /* (non-Javadoc)
     * @see com.popbill.api.StatementService#getUnitCost(java.lang.String, java.number.Integer)
     */
    @Override
    public float getUnitCost(String CorpNum, int ItemCode) throws PopbillException {

        UnitCostResponse response = httpget("/Statement/" + ItemCode + "?cfg=UNITCOST", CorpNum, null, UnitCostResponse.class);

        return response.unitCost;
    }

    /* (non-Javadoc)
     * @see com.popbill.api.StatementService#checkMgtKeyInUse(java.lang.String, java.number.Integer, java.lang.String)
     */
    @Override
    public boolean checkMgtKeyInUse(String CorpNum, int ItemCode, String MgtKey) throws PopbillException {
        return checkMgtKeyInUse(CorpNum, ItemCode, MgtKey, null);
    }

    @Override
    public boolean checkMgtKeyInUse(String CorpNum, int ItemCode, String MgtKey, String UserID) throws PopbillException {
        if (ValidationUtils.isNullOrEmpty(MgtKey))
            throw new PopbillException(-99999999, "문서번호가 입력되지 않았습니다.");

        try {
            StatementInfo info = httpget("/Statement/" + ItemCode + "/" + MgtKey, CorpNum, UserID, StatementInfo.class);

            return !ValidationUtils.isNullOrEmpty(info.getItemKey());

        } catch (PopbillException PE) {
            if (PE.getCode() == -12000004)
                return false;
            throw PE;
        }
    }

    /* (non-Javadoc)
     * @see com.popbill.api.StatementService#register(java.lang.String, com.popbill.api.statement.Statement)
     */
    @Override
    public Response register(String CorpNum, Statement statement) throws PopbillException {
        return register(CorpNum, statement, null);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.StatementService#register(java.lang.String, com.popbill.api.statement.Statement, java.lang.String)
     */
    @Override
    public Response register(String CorpNum, Statement statement, String UserID) throws PopbillException {

        String PostData = toJsonString(statement);

        return httppost("/Statement", CorpNum, PostData, UserID, Response.class);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.StatementService#update(java.lang.String, java.number.Integer, java.lang.String, com.popbill.api.statement.Statement)
     */
    @Override
    public Response update(String CorpNum, int ItemCode, String MgtKey, Statement statement) throws PopbillException {

        return update(CorpNum, ItemCode, MgtKey, statement, null);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.StatementService#update(java.lang.String, java.number.Integer, java.lang.String, com.popbill.api.statement.Statement, java.lang.String)
     */
    @Override
    public Response update(String CorpNum, int ItemCode, String MgtKey, Statement statement, String UserID) throws PopbillException {
        if (ValidationUtils.isNullOrEmpty(MgtKey))
            throw new PopbillException(-99999999, "문서번호가 입력되지 않았습니다.");

        String PostData = toJsonString(statement);

        return httppost("/Statement/" + ItemCode + "/" + MgtKey, CorpNum, PostData, UserID, "PATCH", Response.class);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.StatementService#delete(java.lang.String, java.number.Integer, java.lang.String)
     */
    @Override
    public Response delete(String CorpNum, int ItemCode, String MgtKey) throws PopbillException {

        return delete(CorpNum, ItemCode, MgtKey, null);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.StatementService#delete(java.lang.String, java.number.Integer, java.lang.String, java.lang.String)
     */
    @Override
    public Response delete(String CorpNum, int ItemCode, String MgtKey, String UserID) throws PopbillException {
        if (ValidationUtils.isNullOrEmpty(MgtKey))
            throw new PopbillException(-99999999, "문서번호가 입력되지 않았습니다.");

        return httppost("/Statement/" + ItemCode + "/" + MgtKey, CorpNum, null, UserID, "DELETE", Response.class);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.StatementService#issue(java.lang.String, java.number.Integer, java.lang.String, java.lang.String)
     */
    @Override
    public Response issue(String CorpNum, int ItemCode, String MgtKey, String Memo) throws PopbillException {
        return issue(CorpNum, ItemCode, MgtKey, Memo, null, null);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.StatementService#issue(java.lang.String, java.number.Integer, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public Response issue(String CorpNum, int ItemCode, String MgtKey, String Memo, String UserID) throws PopbillException {
        return issue(CorpNum, ItemCode, MgtKey, Memo, null, UserID);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.StatementService#issue(java.lang.String, java.number.Integer, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public Response issue(String CorpNum, int ItemCode, String MgtKey, String Memo, String EmailSubject, String UserID) throws PopbillException {
        if (ValidationUtils.isNullOrEmpty(MgtKey))
            throw new PopbillException(-99999999, "문서번호가 입력되지 않았습니다.");

        MemoRequest request = new MemoRequest();
        request.memo = Memo;
        request.emailSubject = EmailSubject;
        
        String PostData = toJsonString(request);

        return httppost("/Statement/" + ItemCode + "/" + MgtKey, CorpNum, PostData, UserID, "ISSUE", Response.class);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.StatementService#cancel(java.lang.String, java.number.Integer, java.lang.String, java.lang.String)
     */
    @Override
    public Response cancel(String CorpNum, int ItemCode, String MgtKey, String Memo) throws PopbillException {

        return cancel(CorpNum, ItemCode, MgtKey, Memo, null);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.StatementService#cancel(java.lang.String, java.number.Integer, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public Response cancel(String CorpNum, int ItemCode, String MgtKey, String Memo, String UserID) throws PopbillException {
        if (ValidationUtils.isNullOrEmpty(MgtKey))
            throw new PopbillException(-99999999, "문서번호가 입력되지 않았습니다.");
        
        MemoRequest request = new MemoRequest();
        request.memo = Memo;

        String PostData = toJsonString(request);

        return httppost("/Statement/" + ItemCode + "/" + MgtKey, CorpNum, PostData, UserID, "CANCEL", Response.class);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.StatementService#sendEmail(java.lang.String, java.number.Integer, java.lang.String, java.lang.String)
     */
    @Override
    public Response sendEmail(String CorpNum, int ItemCode, String MgtKey, String Receiver) throws PopbillException {

        return sendEmail(CorpNum, ItemCode, MgtKey, Receiver, null);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.StatementService#sendEmail(java.lang.String, java.number.Integer, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public Response sendEmail(String CorpNum, int ItemCode, String MgtKey, String Receiver, String UserID) throws PopbillException {
        if (ValidationUtils.isNullOrEmpty(MgtKey))
            throw new PopbillException(-99999999, "문서번호가 입력되지 않았습니다.");

        ResendRequest request = new ResendRequest();

        request.receiver = Receiver;

        String PostData = toJsonString(request);

        return httppost("/Statement/" + ItemCode + "/" + MgtKey, CorpNum, PostData, UserID, "EMAIL", Response.class);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.StatementService#sendSMS(java.lang.String, java.number.Integer, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public Response sendSMS(String CorpNum, int ItemCode, String MgtKey, String Sender, String Receiver, String Contents) throws PopbillException {

        return sendSMS(CorpNum, ItemCode, MgtKey, Sender, Receiver, Contents, null);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.StatementService#sendSMS(java.lang.String, java.number.Integer, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public Response sendSMS(String CorpNum, int ItemCode, String MgtKey, String Sender, String Receiver, String Contents, String UserID)
            throws PopbillException {
        if (ValidationUtils.isNullOrEmpty(MgtKey))
            throw new PopbillException(-99999999, "문서번호가 입력되지 않았습니다.");

        ResendRequest request = new ResendRequest();

        request.sender = Sender;
        request.receiver = Receiver;
        request.contents = Contents;

        String PostData = toJsonString(request);

        return httppost("/Statement/" + ItemCode + "/" + MgtKey, CorpNum, PostData, UserID, "SMS", Response.class);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.StatementService#sendFAX(java.lang.String, java.number.Integer, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public Response sendFAX(String CorpNum, int ItemCode, String MgtKey, String Sender, String Receiver) throws PopbillException {

        return sendFAX(CorpNum, ItemCode, MgtKey, Sender, Receiver, null);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.StatementService#sendFAX(java.lang.String, java.number.Integer, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public Response sendFAX(String CorpNum, int ItemCode, String MgtKey, String Sender, String Receiver, String UserID) throws PopbillException {
        if (ValidationUtils.isNullOrEmpty(MgtKey))
            throw new PopbillException(-99999999, "문서번호가 입력되지 않았습니다.");

        ResendRequest request = new ResendRequest();

        request.sender = Sender;
        request.receiver = Receiver;

        String PostData = toJsonString(request);

        return httppost("/Statement/" + ItemCode + "/" + MgtKey, CorpNum, PostData, UserID, "FAX", Response.class);
    }

    /*
     * (non-Javadoc)
     * @see com.popbill.api.StatementService#getDetailInfo(java.lang.String, int, java.lang.String)
     */
    @Override
    public Statement getDetailInfo(String CorpNum, int ItemCode, String MgtKey) throws PopbillException {

        return getDetailInfo(CorpNum, ItemCode, MgtKey, null);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.StatementService#getDetailInfo(java.lang.String, java.number.Integer, java.lang.String, java.lang.String)
     */
    @Override
    public Statement getDetailInfo(String CorpNum, int ItemCode, String MgtKey, String UserID) throws PopbillException {
        if (ValidationUtils.isNullOrEmpty(MgtKey))
            throw new PopbillException(-99999999, "문서번호가 입력되지 않았습니다.");

        return httpget("/Statement/" + ItemCode + "/" + MgtKey + "?Detail", CorpNum, null, Statement.class);

    }

    /* (non-Javadoc)
     * @see com.popbill.api.StatementService#getInfo(java.lang.String, java.number.Integer, java.lang.String, java.lang.String)
     */
    @Override
    public StatementInfo getInfo(String CorpNum, int ItemCode, String MgtKey) throws PopbillException {
        return getInfo(CorpNum, ItemCode, MgtKey, null);
    }

    @Override
    public StatementInfo getInfo(String CorpNum, int ItemCode, String MgtKey, String UserID) throws PopbillException {
        if (ValidationUtils.isNullOrEmpty(MgtKey))
            throw new PopbillException(-99999999, "문서번호가 입력되지 않았습니다.");

        return httpget("/Statement/" + ItemCode + "/" + MgtKey, CorpNum, UserID, StatementInfo.class);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.StatementService#getInfos(java.lang.String, java.number.Integer, java.lang.String, java.lang.String[])
     */
    @Override
    public StatementInfo[] getInfos(String CorpNum, int ItemCode, String[] MgtKeyList) throws PopbillException {

        return getInfos(CorpNum, ItemCode, MgtKeyList, null);
    }

    @Override
    public StatementInfo[] getInfos(String CorpNum, int ItemCode, String[] MgtKeyList, String UserID) throws PopbillException {
        if (ValidationUtils.isNullOrEmpty(MgtKeyList))
            throw new PopbillException(-99999999, "문서번호 목록이 입력되지 않았습니다.");

        String PostData = toJsonString(MgtKeyList);

        return httppost("/Statement/" + ItemCode, CorpNum, PostData, UserID, StatementInfo[].class);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.StatementService#getLogs(java.lang.String, java.number.Integer, java.lang.String, java.lang.String)
     */
    @Override
    public StatementLog[] getLogs(String CorpNum, int ItemCode, String MgtKey) throws PopbillException {

        return getLogs(CorpNum, ItemCode, MgtKey, null);
    }

    @Override
    public StatementLog[] getLogs(String CorpNum, int ItemCode, String MgtKey, String UserID) throws PopbillException {
        if (ValidationUtils.isNullOrEmpty(MgtKey))
            throw new PopbillException(-99999999, "문서번호가 입력되지 않았습니다.");

        return httpget("/Statement/" + ItemCode + "/" + MgtKey + "/Logs", CorpNum, UserID, StatementLog[].class);
    }

    /*
     * (non-Javadoc)
     * @see com.popbill.api.StatementService#getPopUpURL(java.lang.String, int, java.lang.String)
     */
    @Override
    public String getPopUpURL(String CorpNum, int ItemCode, String MgtKey) throws PopbillException {

        return getPopUpURL(CorpNum, ItemCode, MgtKey, null);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.StatementService#getPopUpURL(java.lang.String, java.number.Integer, java.lang.String, java.lang.String)
     */
    @Override
    public String getPopUpURL(String CorpNum, int ItemCode, String MgtKey, String UserID) throws PopbillException {
        if (ValidationUtils.isNullOrEmpty(MgtKey))
            throw new PopbillException(-99999999, "문서번호가 입력되지 않았습니다.");

        URLResponse response = httpget("/Statement/" + ItemCode + "/" + MgtKey + "?TG=POPUP", CorpNum, UserID, URLResponse.class);

        return response.url;
    }

    @Override
    public String getViewURL(String CorpNum, int ItemCode, String MgtKey) throws PopbillException {
        return getViewURL(CorpNum, ItemCode, MgtKey, null);
    }

    @Override
    public String getViewURL(String CorpNum, int ItemCode, String MgtKey, String UserID) throws PopbillException {
        if (ValidationUtils.isNullOrEmpty(MgtKey))
            throw new PopbillException(-99999999, "문서번호가 입력되지 않았습니다.");

        URLResponse response = httpget("/Statement/" + ItemCode + "/" + MgtKey + "?TG=VIEW", CorpNum, UserID, URLResponse.class);

        return response.url;
    }

    /*
     * (non-Javadoc)
     * @see com.popbill.api.StatementService#getPrintURL(java.lang.String, int, java.lang.String)
     */
    @Override
    public String getPrintURL(String CorpNum, int ItemCode, String MgtKey) throws PopbillException {
        return getPrintURL(CorpNum, ItemCode, MgtKey, null);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.StatementService#getPrintURL(java.lang.String, java.number.Integer, java.lang.String, java.lang.String)
     */
    @Override
    public String getPrintURL(String CorpNum, int ItemCode, String MgtKey, String UserID) throws PopbillException {
        if (ValidationUtils.isNullOrEmpty(MgtKey))
            throw new PopbillException(-99999999, "문서번호가 입력되지 않았습니다.");

        URLResponse response = httpget("/Statement/" + ItemCode + "/" + MgtKey + "?TG=PRINT", CorpNum, UserID, URLResponse.class);

        return response.url;
    }

    /*
     * (non-Javadoc)
     * @see com.popbill.api.StatementService#getEPrintURL(java.lang.String, int, java.lang.String)
     */
    @Override
    public String getEPrintURL(String CorpNum, int ItemCode, String MgtKey) throws PopbillException {
        return getEPrintURL(CorpNum, ItemCode, MgtKey, null);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.StatementService#getEPrintURL(java.lang.String, java.number.Integer, java.lang.String, java.lang.String)
     */
    @Override
    public String getEPrintURL(String CorpNum, int ItemCode, String MgtKey, String UserID) throws PopbillException {
        if (ValidationUtils.isNullOrEmpty(MgtKey))
            throw new PopbillException(-99999999, "문서번호가 입력되지 않았습니다.");

        URLResponse response = httpget("/Statement/" + ItemCode + "/" + MgtKey + "?TG=EPRINT", CorpNum, UserID, URLResponse.class);

        return response.url;
    }

    /*
     * (non-Javadoc)
     * @see com.popbill.api.StatementService#getMailURL(java.lang.String, int, java.lang.String)
     */
    @Override
    public String getMailURL(String CorpNum, int ItemCode, String MgtKey) throws PopbillException {
        return getMailURL(CorpNum, ItemCode, MgtKey, null);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.StatementService#getMailURL(java.lang.String, java.number.Integer, java.lang.String, java.lang.String)
     */
    @Override
    public String getMailURL(String CorpNum, int ItemCode, String MgtKey, String UserID) throws PopbillException {
        if (ValidationUtils.isNullOrEmpty(MgtKey))
            throw new PopbillException(-99999999, "문서번호가 입력되지 않았습니다.");

        URLResponse response = httpget("/Statement/" + ItemCode + "/" + MgtKey + "?TG=MAIL", CorpNum, UserID, URLResponse.class);

        return response.url;
    }

    /*
     * (non-Javadoc)
     * @see com.popbill.api.StatementService#getMassPrintURL(java.lang.String, int, java.lang.String[])
     */
    @Override
    public String getMassPrintURL(String CorpNum, int ItemCode, String[] MgtKeyList) throws PopbillException {
        return getMassPrintURL(CorpNum, ItemCode, MgtKeyList, null);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.StatementService#getMassPrintURL(java.lang.String, java.number.Integer, java.lang.String, java.lang.String)
     */
    @Override
    public String getMassPrintURL(String CorpNum, int ItemCode, String[] MgtKeyList, String UserID) throws PopbillException {
        if (ValidationUtils.isNullOrEmpty(MgtKeyList))
            throw new PopbillException(-99999999, "문서번호 목록이 입력되지 않았습니다.");

        String PostData = toJsonString(MgtKeyList);

        URLResponse response = httppost("/Statement/" + ItemCode + "?Print", CorpNum, PostData, UserID, URLResponse.class);

        return response.url;
    }

    /* (non-Javadoc)
     * @see com.popbill.api.StatementService#getSealURL(java.lang.String, java.lang.String)
     */
    @Override
    public String getSealURL(String CorpNum, String UserID) throws PopbillException {

        URLResponse response = httpget("/Member?TG=SEAL", CorpNum, UserID, URLResponse.class);

        return response.url;
    }

    /*
     * (non-Javadoc)
     * @see com.popbill.api.StatementService#attachFile(java.lang.String, int, java.lang.String, java.lang.String, java.io.InputStream)
     */
    @Override
    public Response attachFile(String CorpNum, int ItemCode, String MgtKey, String DisplayName, InputStream FileData) throws PopbillException {

        return attachFile(CorpNum, ItemCode, MgtKey, DisplayName, FileData, null);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.StatementService#attachFile(java.lang.String, java.number.Integer, java.lang.String, ,java.io.InputStream, java.lang.String)
     */
    @Override
    public Response attachFile(String CorpNum, int ItemCode, String MgtKey, String DisplayName, InputStream FileData, String UserID)
            throws PopbillException {
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

        return httppostFiles("/Statement/" + ItemCode + "/" + MgtKey + "/Files", CorpNum, null, files, UserID, Response.class);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.StatementService#getFiles(java.lang.String, java.number.Integer, java.lang.String)
     */
    @Override
    public AttachedFile[] getFiles(String CorpNum, int ItemCode, String MgtKey) throws PopbillException {

        return getFiles(CorpNum, ItemCode, MgtKey, null);
    }

    @Override
    public AttachedFile[] getFiles(String CorpNum, int ItemCode, String MgtKey, String UserID) throws PopbillException {
        if (ValidationUtils.isNullOrEmpty(MgtKey))
            throw new PopbillException(-99999999, "문서번호가 입력되지 않았습니다.");

        return httpget("/Statement/" + ItemCode + "/" + MgtKey + "/Files", CorpNum, UserID, AttachedFile[].class);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.StatementService#deleteFile(java.lang.String, java.number.Integer, java.lang.String, java.lang.String)
     */
    @Override
    public Response deleteFile(String CorpNum, int ItemCode, String MgtKey, String FileID) throws PopbillException {

        return deleteFile(CorpNum, ItemCode, MgtKey, FileID, null);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.StatementService#deleteFile(java.lang.String, java.number.Integer, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public Response deleteFile(String CorpNum, int ItemCode, String MgtKey, String FileID, String UserID) throws PopbillException {
        if (ValidationUtils.isNullOrEmpty(MgtKey))
            throw new PopbillException(-99999999, "문서번호가 입력되지 않았습니다.");

        if (ValidationUtils.isNullOrEmpty(FileID))
            throw new PopbillException(-99999999, "파일 식별번호가 입력되지 않았습니다.");

        return httppost("/Statement/" + ItemCode + "/" + MgtKey + "/Files/" + FileID, CorpNum, null, UserID, "DELETE", Response.class);
    }

    @Override
    public String FAXSend(String CorpNum, Statement statement, String sendNum, String receiveNum) throws PopbillException {

        return FAXSend(CorpNum, statement, sendNum, receiveNum, null);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.StatementService#FAXSend(java.lang.String, com.popbill.api.statement.Statement, java.lang.String)
     */
    @Override
    public String FAXSend(String CorpNum, Statement statement, String sendNum, String receiveNum, String UserID) throws PopbillException {
        if (statement == null)
            throw new PopbillException(-99999999, "전자명세서 정보가 입력되지 않았습니다.");

        statement.setSendNum(sendNum);
        statement.setReceiveNum(receiveNum);

        String PostData = toJsonString(statement);

        ReceiptResponse response = httppost("/Statement", CorpNum, PostData, UserID, "FAX", ReceiptResponse.class);

        return response.receiptNum;
    }

    /* (non-Javadoc)
     * @see com.popbill.api.StatementService#registIssue(java.lang.String, com.popbill.api.statement.Statement)
     */
    @Override
    public SMTIssueResponse registIssue(String CorpNum, Statement statement) throws PopbillException {

        return registIssue(CorpNum, statement, null, null, null);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.StatementService#registIssue(java.lang.String, com.popbill.api.statement.Statement, 
     *                 java.lang.String)
     */
    @Override
    public SMTIssueResponse registIssue(String CorpNum, Statement statement, String memo) throws PopbillException {

        return registIssue(CorpNum, statement, memo, null, null);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.StatementService#registIssue(java.lang.String, com.popbill.api.statement.Statement, 
     *                 java.lang.String, java.lang.String)
     */
    @Override
    public SMTIssueResponse registIssue(String CorpNum, Statement statement, String memo, String UserID) throws PopbillException {

        return registIssue(CorpNum, statement, memo, UserID, null);
    }

    @Override
    public SMTIssueResponse registIssue(String CorpNum, Statement statement, String memo, String UserID, String emailSubject)
            throws PopbillException {

        if (statement == null)
            throw new PopbillException(-99999999, "전자명세서 정보가 입력되지 않았습니다.");

        if (memo != null)
            statement.setMemo(memo);

        if (emailSubject != null)
            statement.setEmailSubject(emailSubject);

        String PostData = toJsonString(statement);

        return httppost("/Statement", CorpNum, PostData, UserID, "ISSUE", SMTIssueResponse.class);
    }

    /*
     * (non-Javadoc)
     * @see com.popbill.api.StatementService#attachStatement(java.lang.String, int, java.lang.String, int, java.lang.String)
     */
    @Override
    public Response attachStatement(String CorpNum, int ItemCode, String MgtKey, int SubItemCode, String SubMgtKey) throws PopbillException {

        return attachStatement(CorpNum, ItemCode, MgtKey, SubItemCode, SubMgtKey, null);
    }

    @Override
    public Response attachStatement(String CorpNum, int ItemCode, String MgtKey, int SubItemCode, String SubMgtKey, String UserID) throws PopbillException {
        if (ValidationUtils.isNullOrEmpty(MgtKey))
            throw new PopbillException(-99999999, "문서번호가 입력되지 않았습니다.");

        DocRequest request = new DocRequest();
        request.ItemCode = SubItemCode;
        request.MgtKey = SubMgtKey;

        String PostData = toJsonString(request);

        return httppost("/Statement/" + ItemCode + "/" + MgtKey + "/AttachStmt/", CorpNum, PostData, UserID, Response.class);
    }

    /*
     * (non-Javadoc)
     * @see com.popbill.api.StatementService#detachStatement(java.lang.String, int, java.lang.String, int, java.lang.String)
     */
    @Override
    public Response detachStatement(String CorpNum, int ItemCode, String MgtKey, int SubItemCode, String SubMgtKey) throws PopbillException {

        return detachStatement(CorpNum, ItemCode, MgtKey, SubItemCode, SubMgtKey, null);
    }

    @Override
    public Response detachStatement(String CorpNum, int ItemCode, String MgtKey, int SubItemCode, String SubMgtKey, String UserID) throws PopbillException {
        if (ValidationUtils.isNullOrEmpty(MgtKey))
            throw new PopbillException(-99999999, "문서번호가 입력되지 않았습니다.");

        DocRequest request = new DocRequest();
        request.ItemCode = SubItemCode;
        request.MgtKey = SubMgtKey;

        String PostData = toJsonString(request);

        return httppost("/Statement/" + ItemCode + "/" + MgtKey + "/DetachStmt/", CorpNum, PostData, UserID, Response.class);
    }

    @Override
    public StmtSearchResult search(String CorpNum, String DType, String SDate, String EDate, String[] State, int[] ItemCode, Integer Page, Integer PerPage,
            String Order) throws PopbillException {

        return search(CorpNum, DType, SDate, EDate, State, ItemCode, "", Page, PerPage, Order);
    }

    @Override
    public StmtSearchResult search(String CorpNum, String DType, String SDate, String EDate, String[] State, int[] ItemCode, String QString, Integer Page,
            Integer PerPage, String Order) throws PopbillException {

        return search(CorpNum, DType, SDate, EDate, State, ItemCode, QString, Page, PerPage, Order, null);
    }

    @Override
    public StmtSearchResult search(String CorpNum, String DType, String SDate, String EDate, String[] State, int[] ItemCode, String QString, Integer Page, Integer PerPage, String Order, String UserID) throws PopbillException {
        String uri = "/Statement/Search?DType=" + DType;
        uri += "&SDate=" + SDate;
        uri += "&EDate=" + EDate;

        if (!ValidationUtils.isNullOrEmpty(State))
            uri += "&State=" + ValidationUtils.replaceInvalidUriChars(State);

        if (ItemCode != null)
            uri += "&ItemCode=" + ValidationUtils.replaceInvalidUriChars(ItemCode);

        if (!ValidationUtils.isNullOrEmpty(QString)) {
            try {
                uri += "&QString=" + URLEncoder.encode(QString, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                throw new PopbillException(-99999999, "검색어 인코딩이 실패 되었습니다.");
            }
        }

        if (Page != null && Page > 0)
            uri += "&Page=" + Integer.toString(Page);

        if (PerPage != null && PerPage > 0 && PerPage <= 1000)
            uri += "&PerPage=" + Integer.toString(PerPage);

        if (Order != null && (Order.equals("D") || Order.equals("A")))
            uri += "&Order=" + Order;

        return httpget(uri, CorpNum, UserID, StmtSearchResult.class);
    }

    /*
     * (non-Javadoc)
     * @see com.popbill.api.StatementService#getChargeInfo(java.lang.String, int)
     */
    @Override
    public ChargeInfo getChargeInfo(String CorpNum, int ItemCode) throws PopbillException {

        return getChargeInfo(CorpNum, ItemCode, null);
    }

    @Override
    public ChargeInfo getChargeInfo(String CorpNum, int ItemCode, String UserID) throws PopbillException {
        return httpget("/Statement/ChargeInfo/" + Integer.toString(ItemCode), CorpNum, UserID, ChargeInfo.class);
    }

    /*
     * (non-Javadoc)
     * @see com.popbill.api.StatementService#updateEmailConfig(java.lang.String, java.lang.String, java.lang.Boolean)
     */
    @Override
    public Response updateEmailConfig(String CorpNum, String EmailType, Boolean SendYN) throws PopbillException {

        return updateEmailConfig(CorpNum, EmailType, SendYN, null);
    }

    /*
     * (non-Javadoc)
     * @see com.popbill.api.StatementService#updateEmailConfig(java.lang.String, java.lang.String, java.lang.Boolean, java.lang.String)
     */
    @Override
    public Response updateEmailConfig(String CorpNum, String EmailType, Boolean SendYN, String UserID) throws PopbillException {
        if (SendYN == null)
            throw new PopbillException(-99999999, "메일 전송 여부가 입력되지 않았습니다.");

        return httppost("/Statement/EmailSendConfig?EmailType=" + EmailType + "&SendYN=" + String.valueOf(SendYN), CorpNum, null, UserID, "",
                Response.class);
    }

    /*
     * (non-Javadoc)
     * @see com.popbill.api.StatementService#listEmailConfig(java.lang.String)
     */
    @Override
    public EmailSendConfig[] listEmailConfig(String CorpNum) throws PopbillException {
        return httpget("/Statement/EmailSendConfig", CorpNum, null, EmailSendConfig[].class);
    }

    /*
     * (non-Javadoc)
     * @see com.popbill.api.StatementService#listEmailConfig(java.lang.String, java.lang.String)
     */
    @Override
    public EmailSendConfig[] listEmailConfig(String CorpNum, String UserID) throws PopbillException {
        return httpget("/Statement/EmailSendConfig", CorpNum, UserID, EmailSendConfig[].class);
    }

    protected class MemoRequest {
    	public String memo;
        public String emailSubject;
    }

    protected class ResendRequest {
        public String receiver;
        public String sender = null;
        public String contents = null;
    }

    protected class ReceiptResponse {
        public String receiptNum;
    }

    protected class DocRequest {
        public int ItemCode;
        public String MgtKey;
    }

}
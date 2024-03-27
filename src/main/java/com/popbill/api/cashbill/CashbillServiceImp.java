package com.popbill.api.cashbill;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
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
        return checkMgtKeyInUse(CorpNum, MgtKey, null);
    }

    @Override
    public boolean checkMgtKeyInUse(String CorpNum, String MgtKey, String UserID) throws PopbillException {
        if (MgtKey == null || MgtKey.isEmpty())
            throw new PopbillException(-99999999, "문서번호가 입력되지 않았습니다.");

        try{
            CashbillInfo info = httpget("/Cashbill/" + MgtKey, CorpNum, UserID, CashbillInfo.class);

            return (info.getItemKey() == null || info.getItemKey().isEmpty()) == false;

            } catch(PopbillException PE){
                if(PE.getCode() == -14000003)
                    return false;
                throw PE;
            }
    }

    /* (non-Javadoc)
     * @see com.popbill.api.CashbillService#register(java.lang.String, com.popbill.api.Cashbill.Cashbill)
     */
    @Override
    public Response register(String CorpNum, Cashbill Cashbill)
            throws PopbillException {
        return register(CorpNum, Cashbill, null);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.CashbillService#register(java.lang.String, com.popbill.api.Cashbill.Cashbill, java.lang.String)
     */
    @Override
    public Response register(String CorpNum, Cashbill Cashbill, String UserID)
            throws PopbillException {
        if (Cashbill == null)
            throw new PopbillException(-99999999, "현금영수증정보가 입력되지 않았습니다.");

        String PostData = toJsonString(Cashbill);

        return httppost("/Cashbill",CorpNum, PostData,
                UserID, Response.class);

    }
    /* (non-Javadoc)
     * @see com.popbill.api.CashbillService#update(java.lang.String, java.lang.String, com.popbill.api.Cashbill.Cashbill)
     */
    @Override
    public Response update(String CorpNum, String MgtKey, Cashbill Cashbill)
            throws PopbillException {
        if (MgtKey == null || MgtKey.isEmpty())
            throw new PopbillException(-99999999, "문서번호가 입력되지 않았습니다.");

        return update(CorpNum, MgtKey, Cashbill, null);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.CashbillService#update(java.lang.String, java.lang.String, com.popbill.api.Cashbill.Cashbill, java.lang.String)
     */
    @Override
    public Response update(String CorpNum, String MgtKey, Cashbill Cashbill,
            String UserID) throws PopbillException {
        if (MgtKey == null || MgtKey.isEmpty())
            throw new PopbillException(-99999999, "문서번호가 입력되지 않았습니다.");

        String PostData = toJsonString(Cashbill);

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
        return getDetailInfo(CorpNum, MgtKey, null);
    }

    @Override
    public Cashbill getDetailInfo(String CorpNum, String MgtKey, String UserID) throws PopbillException {
        if (MgtKey == null || MgtKey.isEmpty())
            throw new PopbillException(-99999999, "문서번호가 입력되지 않았습니다.");

        return httpget("/Cashbill/" + MgtKey + "?Detail", CorpNum,
                UserID, Cashbill.class);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.CashbillService#getInfo(java.lang.String, java.lang.String)
     */
    @Override
    public CashbillInfo getInfo(String CorpNum, String MgtKey)
            throws PopbillException {
        return getInfo(CorpNum, MgtKey, null);
    }

    @Override
    public CashbillInfo getInfo(String CorpNum, String MgtKey, String UserID) throws PopbillException {
        if (MgtKey == null || MgtKey.isEmpty())
            throw new PopbillException(-99999999, "문서번호가 입력되지 않았습니다.");

        return httpget("/Cashbill/" + MgtKey, CorpNum,
                UserID, CashbillInfo.class);
    }

    /* (non-Javadoc)
     * @see com.popbill.api.CashbillService#getInfos(java.lang.String, java.lang.String[])
     */
    @Override
    public CashbillInfo[] getInfos(String CorpNum, String[] MgtKeyList)
            throws PopbillException {
        return getInfos(CorpNum, MgtKeyList, null);
    }

    @Override
    public CashbillInfo[] getInfos(String CorpNum, String[] MgtKeyList, String UserID) throws PopbillException {
        if (MgtKeyList == null || MgtKeyList.length == 0)
            throw new PopbillException(-99999999, "문서번호배열이 입력되지 않았습니다.");

        String PostData = toJsonString(MgtKeyList);

        return httppost("/Cashbill/States", CorpNum, PostData,
                UserID, CashbillInfo[].class);
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
    public Response assignMgtKey(String CorpNum, String ItemKey,
                                 String MgtKey) throws PopbillException {

        return assignMgtKey(CorpNum,  ItemKey, MgtKey, null);
    }

    @Override
    public Response assignMgtKey(String CorpNum, String ItemKey,
                                 String MgtKey, String UserID) throws PopbillException {
        if (ItemKey == null || ItemKey.isEmpty())
            throw new PopbillException(-99999999, "아이템키(ItemKey)가 입력되지 않았습니다.");

        if (MgtKey == null || MgtKey.isEmpty())
            throw new PopbillException(-99999999, "문서번호(MgtKey)가 입력되지 않았습니다.");

        String PostData = "MgtKey=" + MgtKey;

        return httppost("/Cashbill/" + ItemKey ,
                CorpNum, PostData, UserID, "", Response.class, "application/x-www-form-urlencoded; charset=utf-8");
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
    public CBIssueResponse registIssue(String CorpNum, Cashbill Cashbill) throws PopbillException{
        return registIssue(CorpNum, Cashbill, null, null);
    }

    /*
     * (non-Javadoc)
     * @see com.popbill.api.CashbillService#registIssue(java.lang.String, com.popbill.api.Cashbill.Cashbill, java.lang.String)
     */
    @Override
    public CBIssueResponse registIssue(String CorpNum, Cashbill Cashbill, String Memo)
            throws PopbillException {
        return registIssue(CorpNum, Cashbill, Memo, null);
    }
    /* (non-Javadoc)
     * @see com.popbill.api.CashbillService#registIssue(java.lang.String, com.popbill.api.Cashbill.Cashbill,
     *              java.lang.String, java.lang.String)
     */
    @Override
    public CBIssueResponse registIssue(String CorpNum, Cashbill Cashbill, String Memo,
            String UserID) throws PopbillException {
        
        return registIssue(CorpNum, Cashbill, Memo, null, null);
    }
    
    @Override
    public CBIssueResponse registIssue(String CorpNum, Cashbill Cashbill, String Memo, String UserID,
             String emailSubject) throws PopbillException {
        if (Cashbill == null)
            throw new PopbillException(-99999999, "현금영수증정보가 입력되지 않았습니다.");
        if (Memo != null)
            Cashbill.setMemo(Memo);
        
        if (emailSubject != null)
            Cashbill.setEmailSubject(emailSubject);

        String PostData = toJsonString(Cashbill);
        

        return httppost("/Cashbill", CorpNum, PostData,
                UserID, "ISSUE", CBIssueResponse.class);
    }

    @Override
    public BulkResponse bulkSubmit(String CorpNum, String SubmitID, List<Cashbill> CashbillList) throws PopbillException {
        return bulkSubmit(CorpNum, SubmitID, CashbillList, null);

    }

    @Override
    public BulkResponse bulkSubmit(String CorpNum, String SubmitID, List<Cashbill> CashbillList,
            String UserID) throws PopbillException {
        if (SubmitID == null || SubmitID.equals(""))
            throw new PopbillException(-99999999, "제출아이디(SubmitID)가 입력되지 않았습니다.");

        if (CashbillList == null) {
            throw new PopbillException(-99999999, "현금영수증 정보가 입력되지 않았습니다.");
        }

        BulkCashbillSubmit cs = new BulkCashbillSubmit();
        cs.setCashList(CashbillList);
        String PostData = toJsonString(cs);

        return httpBulkPost("/Cashbill", CorpNum, SubmitID, PostData, UserID, "BULKISSUE", BulkResponse.class);
    }

    @Override
    public CBIssueResponse registIssueCN(String CorpNum, Cashbill Cashbill) throws PopbillException {
        return registIssueCN(CorpNum, Cashbill, null, null);
    }

    @Override
    public CBIssueResponse registIssueCN(String CorpNum, Cashbill Cashbill, String Memo) throws PopbillException {
        return registIssueCN(CorpNum, Cashbill, Memo, null);
    }

    @Override
    public CBIssueResponse registIssueCN(String CorpNum, Cashbill Cashbill, String Memo, String UserID) throws PopbillException {
        return registIssueCN(CorpNum, Cashbill, Memo, UserID, null);
    }

    @Override
    public CBIssueResponse registIssueCN(String CorpNum, Cashbill Cashbill, String Memo, String UserID, String EmailSubject) throws PopbillException {
        if (Cashbill == null)
            throw new PopbillException(-99999999, "현금영수증정보가 입력되지 않았습니다.");
        if (Memo != null)
            Cashbill.setMemo(Memo);

        if (EmailSubject != null)
            Cashbill.setEmailSubject(EmailSubject);

        String PostData = toJsonString(Cashbill);


        return httppost("/Cashbill", CorpNum, PostData,
                UserID, "ISSUECN", CBIssueResponse.class);
    }

    @Override
    public BulkResponse bulkSubmitCN(String CorpNum, String SubmitID, List<Cashbill> CashbillList) throws PopbillException {
        return bulkSubmitCN(CorpNum, SubmitID, CashbillList, null);
    }

    @Override
    public BulkResponse bulkSubmitCN(String CorpNum, String SubmitID, List<Cashbill> CashbillList, String UserID) throws PopbillException {
            if (SubmitID == null || SubmitID.equals(""))
                throw new PopbillException(-99999999, "제출아이디(SubmitID)가 입력되지 않았습니다.");

            if (CashbillList == null) {
                throw new PopbillException(-99999999, "현금영수증 정보가 입력되지 않았습니다.");
            }

            BulkCashbillSubmit cs = new BulkCashbillSubmit();
            cs.setCashList(CashbillList);
            String PostData = toJsonString(cs);

            return httpBulkPost("/Cashbill", CorpNum, SubmitID, PostData, UserID, "BULKISSUECN", BulkResponse.class);
    }


    @Override
    public BulkCashbillResult getBulkResult(String CorpNum, String SubmitID) throws PopbillException {

        return getBulkResult(CorpNum, SubmitID, null);
    }
    
    @Override
    public BulkCashbillResult getBulkResult(String CorpNum, String SubmitID, String UserID) throws PopbillException {

        if (SubmitID == null || SubmitID.equals(""))
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
                                 String[] TradeUsage, String[] TaxationType, Integer Page,
                                 Integer PerPage, String Order) throws PopbillException {
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
                                 String[] TradeUsage, String[] TaxationType, String QString, Integer Page,
                                 Integer PerPage, String Order) throws PopbillException {
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
                                 String QString, Integer Page, Integer PerPage, String Order) throws PopbillException {
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
                                 String QString, Integer Page, Integer PerPage, String Order, String FranchiseTaxRegID) throws PopbillException {
        return search(CorpNum, DType, SDate, EDate, State, TradeType, TradeUsage, TradeOpt, TaxationType, QString, Page, PerPage, Order, FranchiseTaxRegID, null);
    }

    @Override
    public CBSearchResult search(String CorpNum, String DType, String SDate, String EDate, String[] State, String[] TradeType, String[] TradeUsage, String[] TradeOpt, String[] TaxationType, String QString, Integer Page, Integer PerPage, String Order, String FranchiseTaxRegID, String UserID) throws PopbillException {
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

        if (QString != null && !QString.isEmpty()) {
            try {
                uri += "&QString=" + URLEncoder.encode(QString, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                throw new PopbillException(-99999999, "검색어(QString) 인코딩 오류");
            }
        }

        if (Page != null && Page > 0)
            uri += "&Page=" + Integer.toString(Page);
        if(PerPage != null && PerPage > 0 && PerPage <= 1000)
            uri += "&PerPage="+ Integer.toString(PerPage);
        if (Order != null && (Order.equals("D") || Order.equals("A")))
            uri += "&Order=" + Order;
        if (FranchiseTaxRegID != null && !FranchiseTaxRegID.isEmpty()) {
            uri += "&FranchiseTaxRegID=" + FranchiseTaxRegID;
        }

        return httpget(uri, CorpNum, UserID, CBSearchResult.class);
    }

    /*
     * (non-Javadoc)
     * @see com.popbill.api.CashbillService#getChargeInfo(java.lang.String)
     */
    @Override
    public ChargeInfo getChargeInfo(String CorpNum) throws PopbillException {
        return getChargeInfo(CorpNum, null);
    }

    @Override
    public ChargeInfo getChargeInfo(String CorpNum, String UserID) throws PopbillException {
        return httpget("/Cashbill/ChargeInfo", CorpNum, UserID, ChargeInfo.class);
    }

    @Override
    public Response revokeRegister(String CorpNum, String MgtKey,
            String OrgConfirmNum, String OrgTradeDate) throws PopbillException {

        return revokeRegister(CorpNum, MgtKey, OrgConfirmNum, OrgTradeDate, false, false,
                null, null, null, null, null, null);
    }

    @Override
    public Response revokeRegister(String CorpNum, String MgtKey,
            String OrgConfirmNum, String OrgTradeDate, Boolean SMSSendYN)
            throws PopbillException {

        return revokeRegister(CorpNum, MgtKey, OrgConfirmNum, OrgTradeDate, SMSSendYN,
                false, null, null, null, null, null, null);
    }

    @Override
    public Response revokeRegister(String CorpNum, String MgtKey,
            String OrgConfirmNum, String OrgTradeDate, Boolean SMSSendYN,
            String UserID) throws PopbillException {

        return revokeRegister(CorpNum, MgtKey, OrgConfirmNum, OrgTradeDate, SMSSendYN,
                false, null, null, null, null, null, UserID);
    }


    @Override
    public Response revokeRegister(String CorpNum, String MgtKey, String OrgConfirmNum,
            String OrgTradeDate, Boolean SMSSendYN, Boolean IsPartCancel, Integer CancelType,
            String SupplyCost, String Tax, String ServiceFee, String TotalAmount) throws PopbillException {

        return revokeRegister(CorpNum, MgtKey, OrgConfirmNum, OrgTradeDate, SMSSendYN, IsPartCancel,
                CancelType, SupplyCost, Tax, ServiceFee, TotalAmount, null);
    }


    @Override
    public Response revokeRegister(String CorpNum, String MgtKey,
            String OrgConfirmNum, String OrgTradeDate, Boolean SMSSendYN, Boolean IsPartCancel,
            Integer CancelType, String SupplyCost, String Tax, String ServiceFee, String TotalAmount,
            String UserID) throws PopbillException {

        if (MgtKey == null)
            throw new PopbillException(-99999999, "취소현금영수증 문서번호가 입력되지 않았습니다.");

        RevokeRequest request = new RevokeRequest();

        request.mgtKey = MgtKey;
        request.orgConfirmNum = OrgConfirmNum;
        request.orgTradeDate = OrgTradeDate;
        request.smssendYN = SMSSendYN;
        request.isPartCancel = IsPartCancel;
        request.cancelType = CancelType;
        request.supplyCost = SupplyCost;
        request.tax = Tax;
        request.serviceFee = ServiceFee;
        request.totalAmount = TotalAmount;

        String PostData = toJsonString(request);

        return httppost("/Cashbill", CorpNum, PostData,
                UserID, "REVOKE", Response.class);
    }

    @Override
    public CBIssueResponse revokeRegistIssue(String CorpNum, String MgtKey,
            String OrgConfirmNum, String OrgTradeDate) throws PopbillException {

        return revokeRegistIssue(CorpNum, MgtKey, OrgConfirmNum, OrgTradeDate, false, null,
                false, null, null, null, null, null, null, null, null);
    }

    @Override
    public CBIssueResponse revokeRegistIssue(String CorpNum, String MgtKey,
            String OrgConfirmNum, String OrgTradeDate, Boolean SMSSendYN)
            throws PopbillException {

        return revokeRegistIssue(CorpNum, MgtKey, OrgConfirmNum, OrgTradeDate, SMSSendYN, null,
                false, null, null, null, null, null, null, null, null);
    }

    @Override
    public CBIssueResponse revokeRegistIssue(String CorpNum, String MgtKey,
            String OrgConfirmNum, String OrgTradeDate, Boolean SMSSendYN,
            String Memo) throws PopbillException {

        return revokeRegistIssue(CorpNum, MgtKey, OrgConfirmNum, OrgTradeDate, SMSSendYN, Memo,
                false, null, null, null, null, null, null, null, null);
    }

    @Override
    public CBIssueResponse revokeRegistIssue(String CorpNum, String MgtKey,
            String OrgConfirmNum, String OrgTradeDate, Boolean SMSSendYN,
            String Memo, String UserID) throws PopbillException {

        return revokeRegistIssue(CorpNum, MgtKey, OrgConfirmNum, OrgTradeDate, SMSSendYN, Memo,
                false, null, null, null, null, null, UserID, null, null);
    }
    
    @Override
    public CBIssueResponse revokeRegistIssue(String CorpNum, String MgtKey,
            String OrgConfirmNum, String OrgTradeDate, Boolean SMSSendYN,
            String Memo, String UserID, String EmailSubject) throws PopbillException {

        return revokeRegistIssue(CorpNum, MgtKey, OrgConfirmNum, OrgTradeDate, SMSSendYN, Memo,
                false, null, null, null, null, null, UserID, EmailSubject, null);
    }

    @Override
    public CBIssueResponse revokeRegistIssue(String CorpNum, String MgtKey, String OrgConfirmNum,
            String OrgTradeDate, Boolean SMSSendYN, String Memo,
            Boolean IsPartCancel, Integer CancelType, String SupplyCost,
            String Tax, String ServiceFee, String TotalAmount) throws PopbillException {

        return revokeRegistIssue(CorpNum, MgtKey, OrgConfirmNum, OrgTradeDate, SMSSendYN, Memo,
                IsPartCancel, CancelType, SupplyCost, Tax, ServiceFee, TotalAmount, null, null, null);
    }

    @Override
    public CBIssueResponse revokeRegistIssue(String CorpNum, String MgtKey, String OrgConfirmNum,
            String OrgTradeDate, Boolean SMSSendYN, String Memo,
            Boolean IsPartCancel, Integer CancelType, String SupplyCost,
            String Tax, String ServiceFee, String TotalAmount, String UserID) throws PopbillException {

        return revokeRegistIssue(CorpNum, MgtKey, OrgConfirmNum, OrgTradeDate, SMSSendYN, Memo,
                IsPartCancel, CancelType, SupplyCost, Tax, ServiceFee, TotalAmount, null, null, null);
    }
    
    @Override
    public CBIssueResponse revokeRegistIssue(String CorpNum, String MgtKey, String OrgConfirmNum,
            String OrgTradeDate, Boolean SMSSendYN, String Memo,
            Boolean IsPartCancel, Integer CancelType, String SupplyCost,
            String Tax, String ServiceFee, String TotalAmount, String UserID, String EmailSubject) throws PopbillException {

        return revokeRegistIssue(CorpNum, MgtKey, OrgConfirmNum, OrgTradeDate, SMSSendYN, Memo,
                IsPartCancel, CancelType, SupplyCost, Tax, ServiceFee, TotalAmount, null, null, null);
    }
    
    @Override
    public CBIssueResponse revokeRegistIssue(String CorpNum, String MgtKey, String OrgConfirmNum,
            String OrgTradeDate, Boolean SMSSendYN, String Memo,
            Boolean IsPartCancel, Integer CancelType, String SupplyCost,
            String Tax, String ServiceFee, String TotalAmount, String UserID, String EmailSubject, String TradeDT) throws PopbillException {
        
        if (MgtKey == null)
            throw new PopbillException(-99999999, "취소현금영수증 문서번호가 입력되지 않았습니다.");
        
        RevokeRequest request = new RevokeRequest();
        
        if (EmailSubject != null) request.emailSubject = EmailSubject;
        
        request.mgtKey = MgtKey;
        request.orgConfirmNum = OrgConfirmNum;
        request.orgTradeDate = OrgTradeDate;
        request.smssendYN = SMSSendYN;
        request.memo = Memo;
        request.isPartCancel = IsPartCancel;
        request.cancelType = CancelType;
        request.supplyCost = SupplyCost;
        request.serviceFee = ServiceFee;
        request.tax = Tax;
        request.totalAmount = TotalAmount;
        request.tradeDT = TradeDT;
        
        String PostData = toJsonString(request);
        
        return httppost("/Cashbill", CorpNum, PostData,
                UserID, "REVOKEISSUE", CBIssueResponse.class);
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
        public String tradeDT;
    }
}
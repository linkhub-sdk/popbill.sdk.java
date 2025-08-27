package com.popbill.api.hometax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.popbill.api.BaseServiceImp;
import com.popbill.api.ChargeInfo;
import com.popbill.api.FlatRateState;
import com.popbill.api.HTCashbillService;
import com.popbill.api.PopbillException;
import com.popbill.api.Response;
import com.popbill.api.util.ValidationUtils;

/**
 * Implementation of Popbill Hometax Cashbill Service Interface
 * 
 * @author JeongYoHan
 * @version 1.0.0
 * @see com.popbill.api.HTCashbillService
 *
 */
public class HTCashbillServiceImp extends BaseServiceImp implements HTCashbillService {

    @Override
    protected List<String> getScopes() {
        return Arrays.asList("141");
    }

    /*
     * (non-Javadoc)
     * @see com.popbill.api.HTCashbillService#getChargeInfo(java.lang.String)
     */
    @Override
    public ChargeInfo getChargeInfo(String CorpNum) throws PopbillException {
        return getChargeInfo(CorpNum, null);
    }

    @Override
    public ChargeInfo getChargeInfo(String CorpNum, String UserID) throws PopbillException {
        return httpget("/HomeTax/Cashbill/ChargeInfo", CorpNum, UserID, ChargeInfo.class);
    }

    /*
     * (non-Javadoc)
     * @see com.popbill.api.HTCashbillService#requestJob(java.lang.String, com.popbill.api.hometaxcashbill.QueryType, java.lang.String, java.lang.String)
     */
    @Override
    public String requestJob(String CorpNum, QueryType QueryType, String SDate, String EDate) throws PopbillException {
        return requestJob(CorpNum, QueryType, SDate, EDate, null);
    }

    /*
     * (non-Javadoc)
     * @see com.popbill.api.HTCashbillService#requestJob(java.lang.String, com.popbill.api.hometaxcashbill.QueryType, java.lang.String, java.lang.String, java.lang.String)
     */
    public String requestJob(String CorpNum, QueryType queryType, String SDate, String EDate, String UserID) throws PopbillException {
        if (queryType == null)
            throw new PopbillException(-99999999, "현금영수증 유형이 입력되지 않았습니다.");

        JobIDResponse response = httppost("/HomeTax/Cashbill/" + queryType.name() + "?SDate=" + SDate + "&EDate=" + EDate, CorpNum, null, UserID, JobIDResponse.class);

        return response.jobID;
    }

    /*
     * (non-Javadoc)
     * @see com.popbill.api.HTCashbillService#getJobState(java.lang.String, java.lang.String)
     */
    @Override
    public HTCashbillJobState getJobState(String CorpNum, String JobID) throws PopbillException {
        return getJobState(CorpNum, JobID, null);
    }

    /*
     * (non-Javadoc)
     * @see com.popbill.api.HTCashbillService#getJobState(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public HTCashbillJobState getJobState(String CorpNum, String JobID, String UserID) throws PopbillException {
        if (ValidationUtils.isNullOrEmpty(JobID))
            throw new PopbillException(-99999999, "작업아이디가 입력되지 않았습니다.");

        return httpget("/HomeTax/Cashbill/" + JobID + "/State", CorpNum, UserID, HTCashbillJobState.class);
    }

    /*
     * (non-Javadoc)
     * @see com.popbill.api.HTCashbillService#listActiveJob(java.lang.String)
     */
    @Override
    public HTCashbillJobState[] listActiveJob(String CorpNum) throws PopbillException {
        return listActiveJob(CorpNum, null);
    }

    /*
     * (non-Javadoc)
     * @see com.popbill.api.HTCashbillService#listActvieJob(java.lang.String, java.lang.String)
     */
    @Override
    public HTCashbillJobState[] listActiveJob(String CorpNum, String UserID) throws PopbillException {
        return httpget("/HomeTax/Cashbill/JobList", CorpNum, UserID, HTCashbillJobState[].class);
    }

    /**
     * 
     */
    @Override
    public HTCashbillSearchResult search(String CorpNum, String JobID, String[] TradeUsage, String[] TradeType, Integer Page, Integer PerPage, String Order) throws PopbillException {
        return search(CorpNum, JobID, TradeUsage, TradeType, Page, PerPage, Order, null);
    }

    @Override
    public HTCashbillSearchResult search(String CorpNum, String JobID, String[] TradeUsage, String[] TradeType, Integer Page, Integer PerPage, String Order, String UserID) throws PopbillException {
        if (ValidationUtils.isNullOrEmpty(JobID))
            throw new PopbillException(-99999999, "작업아이디가 입력되지 않았습니다.");

        String uri = "/HomeTax/Cashbill/" + JobID + "?TradeUsage=";

        if (!ValidationUtils.isNullOrEmpty(TradeUsage))
            uri += ValidationUtils.replaceInvalidUriChars(TradeUsage);

        if (!ValidationUtils.isNullOrEmpty(TradeType))
            uri += "&TradeType=" + ValidationUtils.replaceInvalidUriChars(TradeType);

        if (Page != null && Page > 0)
            uri += "&Page=" + Integer.toString(Page);

        if (PerPage != null && PerPage > 0 && PerPage <= 1000)
            uri += "&PerPage=" + Integer.toString(PerPage);

        if (Order != null && (Order.equals("D") || Order.equals("A")))
            uri += "&Order=" + Order;

        return httpget(uri, CorpNum, UserID, HTCashbillSearchResult.class);
    }

    /*
     * (non-Javadoc)
     * @see com.popbill.api.HTCashbillService#Summary(java.lang.String, java.lang.String, java.lang.String[], java.lang.String[])
     */
    @Override
    public HTCashbillSummary summary(String CorpNum, String JobID, String[] TradeUsage, String[] TradeType) throws PopbillException {
        return summary(CorpNum, JobID, TradeUsage, TradeType, null);
    }

    /*
     * (non-Javadoc)
     * @see com.popbill.api.HTCashbillService#Summary(java.lang.String, java.lang.String, java.lang.String[], java.lang.String[], java.lang.String)
     */
    @Override
    public HTCashbillSummary summary(String CorpNum, String JobID, String[] TradeUsage, String[] TradeType, String UserID) throws PopbillException {
        if (ValidationUtils.isNullOrEmpty(JobID))
            throw new PopbillException(-99999999, "작업아이디가 입력되지 않았습니다.");

        String uri = "/HomeTax/Cashbill/" + JobID + "/Summary" + "?TradeUsage=";

        if (!ValidationUtils.isNullOrEmpty(TradeUsage))
            uri += ValidationUtils.replaceInvalidUriChars(TradeUsage);

        if (!ValidationUtils.isNullOrEmpty(TradeType))
            uri += "&TradeType=" + ValidationUtils.replaceInvalidUriChars(TradeType);

        return httpget(uri, CorpNum, UserID, HTCashbillSummary.class);
    }

    /*
     * (non-Javadoc)
     * @see com.popbill.api.HTCashbillService#getFlatRatePopUpURL(java.lang.String)
     */
    @Override
    public String getFlatRatePopUpURL(String CorpNum) throws PopbillException {
        return getFlatRatePopUpURL(CorpNum, null);
    }

    /*
     * (non-Javadoc)
     * @see com.popbill.api.HTCashbillService#getFlatRatePopUpURL(java.lang.String, java.lang.String)
     */
    @Override
    public String getFlatRatePopUpURL(String CorpNum, String UserID) throws PopbillException {
        URLResponse response = httpget("/HomeTax/Cashbill?TG=CHRG", CorpNum, UserID, URLResponse.class);

        return response.url;
    }

    /*
     * (non-Javadoc)
     * @see com.popbill.api.HTCashbillService#getCertificatePopUpURL(java.lang.String)
     */
    @Override
    public String getCertificatePopUpURL(String CorpNum) throws PopbillException {
        return getCertificatePopUpURL(CorpNum, null);
    }

    /*
     * (non-Javadoc)
     * @see com.popbill.api.HTCashbillService#getCertificatePopUpURL(java.lang.String, java.lang.String)
     */
    @Override
    public String getCertificatePopUpURL(String CorpNum, String UserID) throws PopbillException {
        URLResponse response = httpget("/HomeTax/Cashbill?TG=CERT", CorpNum, UserID, URLResponse.class);

        return response.url;
    }

    /*
     * (non-Javadoc)
     * @see com.popbill.api.HTCashbillService#getFlatRateState(java.lang.String)
     */
    @Override
    public FlatRateState getFlatRateState(String CorpNum) throws PopbillException {

        return getFlatRateState(CorpNum, null);
    }

    /*
     * (non-Javadoc)
     * @see com.popbill.api.HTCashbillService#getFlatRateState(java.lang.String, java.lang.String)
     */
    @Override
    public FlatRateState getFlatRateState(String CorpNum, String UserID) throws PopbillException {

        return httpget("/HomeTax/Cashbill/Contract", CorpNum, UserID, FlatRateState.class);
    }

    /*
     * (non-Javadoc)
     * @see com.popbill.api.HTCashbillService#getCertificateExpireDate(java.lang.String)
     */
    @Override
    public Date getCertificateExpireDate(String CorpNum) throws PopbillException {
        return getCertificateExpireDate(CorpNum, null);
    }

    @Override
    public Date getCertificateExpireDate(String CorpNum, String UserID) throws PopbillException {
        CertResponse response = httpget("/HomeTax/Cashbill/CertInfo", CorpNum, UserID, CertResponse.class);

        try {
            return new SimpleDateFormat("yyyyMMddHHmmss").parse(response.certificateExpiration);
        } catch (ParseException e) {
            throw new PopbillException(-99999999, "날자형식 포맷변환 실패[" + response.certificateExpiration + "]", e);
        }
    }

    /*
     * (non-Javadoc)
     * @see com.popbill.api.HTCashbillService#checkCertValidation(java.lang.String)
     */
    public Response checkCertValidation(String CorpNum) throws PopbillException {
        return checkCertValidation(CorpNum, null);
    }

    @Override
    public Response checkCertValidation(String CorpNum, String UserID) throws PopbillException {
        return httpget("/HomeTax/Cashbill/CertCheck", CorpNum, UserID, Response.class);
    }

    /*
     * (non-Javadoc)
     * @see com.popbill.api.HTCashbillService#registDeptUser(java.lang.String, java.lang.String, java.lang.String)
     */
    public Response registDeptUser(String CorpNum, String DeptUserID, String DeptUserPWD) throws PopbillException {
        return registDeptUser(CorpNum, DeptUserID, DeptUserPWD,null,null);
    }

    /*
     * (non-Javadoc)
     * @see com.popbill.api.HTTaxinvoiceService#registDeptUser(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    public Response registDeptUser(String CorpNum, String DeptUserID, String DeptUserPWD, String UserID) throws PopbillException {
        return registDeptUser(CorpNum, DeptUserID, DeptUserPWD, null, UserID);
    }

    @Override
    public Response registDeptUser(String CorpNum, String DeptUserID, String DeptUserPWD, String IdentityNum, String UserID) throws PopbillException {
        DeptRequest request = new DeptRequest();
        request.id = DeptUserID;
        request.pwd = DeptUserPWD;
        request.secAuth = IdentityNum;

        String PostData = toJsonString(request);

        return httppost("/HomeTax/Cashbill/DeptUser", CorpNum, PostData, UserID, Response.class);
    }

    /*
     * (non-Javadoc)
     * @see com.popbill.api.HTCashbillService#checkDeptUser(java.lang.String, java.lang.String)
     */
    public Response checkDeptUser(String CorpNum) throws PopbillException {
        return checkDeptUser(CorpNum, null);
    }

    @Override
    public Response checkDeptUser(String CorpNum, String UserID) throws PopbillException {
        return httpget("/HomeTax/Cashbill/DeptUser", CorpNum, UserID, Response.class);
    }

    /*
     * (non-Javadoc)
     * @see com.popbill.api.HTCashbillService#checkLoginDeptUser(java.lang.String)
     */
    public Response checkLoginDeptUser(String CorpNum) throws PopbillException {
        return checkLoginDeptUser(CorpNum, null);
    }

    @Override
    public Response checkLoginDeptUser(String CorpNum, String UserID) throws PopbillException {
        return httpget("/HomeTax/Cashbill/DeptUser/Check", CorpNum, UserID, Response.class);
    }

    /*
     * (non-Javadoc)
     * @see com.popbill.api.HTCashbillService#deleteDeptUser(java.lang.String)
     */
    public Response deleteDeptUser(String CorpNum) throws PopbillException {
        return deleteDeptUser(CorpNum, null);
    }

    @Override
    public Response deleteDeptUser(String CorpNum, String UserID) throws PopbillException {
        return httppost("/HomeTax/Cashbill/DeptUser", CorpNum, null, UserID, "DELETE", Response.class);
    }

    protected class JobIDResponse {
        public String jobID;
    }

    protected class CertResponse {
        public String certificateExpiration;
    }

    protected class DeptRequest {
        public String id;
        public String pwd;
        public String secAuth;
    }
}
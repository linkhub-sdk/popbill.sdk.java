package com.popbill.api.hometax;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.popbill.api.BaseServiceImp;
import com.popbill.api.ChargeInfo;
import com.popbill.api.FlatRateState;
import com.popbill.api.HTTaxinvoiceService;
import com.popbill.api.PopbillException;
import com.popbill.api.Response;
import com.popbill.api.util.ValidationUtils;

/**
 * Implementation of Popbill HomeTax Taxinvoice Service Interface
 * 
 * @author JeongYoHan
 * @version 1.0.0
 * @see com.popbill.api.HTTaxinvoiceService
 *
 */
public class HTTaxinvoiceServiceImp extends BaseServiceImp implements HTTaxinvoiceService {

    @Override
    protected List<String> getScopes() {
        return Arrays.asList("111");
    }

    /*
     * (non-Javadoc)
     * @see com.popbill.api.HTTaxinvoiceService#getFlatRatePopUpURL(java.lang.String)
     */
    @Override
    public String getFlatRatePopUpURL(String CorpNum) throws PopbillException {

        return getFlatRatePopUpURL(CorpNum, null);
    }

    /*
     * (non-Javadoc)
     * @see com.popbill.api.HomeTaxTaxinvoiceService#getFlatRatePopUpURL(java.lang.String, java.lang.String)
     */
    @Override
    public String getFlatRatePopUpURL(String CorpNum, String UserID) throws PopbillException {
        URLResponse response = httpget("/HomeTax/Taxinvoice?TG=CHRG", CorpNum, UserID, URLResponse.class);

        return response.url;
    }

    /*
     * (non-Javadoc)
     * @see com.popbill.api.HomeTaxTaxinvoiceService#getChargeInfo(java.lang.String)
     */
    @Override
    public ChargeInfo getChargeInfo(String CorpNum) throws PopbillException {
        return getChargeInfo(CorpNum, null);
    }

    @Override
    public ChargeInfo getChargeInfo(String CorpNum, String UserID) throws PopbillException {
        return httpget("/HomeTax/Taxinvoice/ChargeInfo", CorpNum, UserID, ChargeInfo.class);
    }

    /*
     * (non-Javadoc)
     * @see com.popbill.api.HTTaxinvoiceService#requestJob(java.lang.String, com.popbill.api.httaxinvoice.QueryType, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public String requestJob(String CorpNum, QueryType queryType, String DType, String SDate, String EDate)
            throws PopbillException {

        return requestJob(CorpNum, queryType, DType, SDate, EDate, null);
    }

    /*
     * (non-Javadoc)
     * @see com.popbill.api.HTTaxinvoiceService#requestJob(java.lang.String, com.popbill.api.httaxinvoice.QueryType, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public String requestJob(String CorpNum, QueryType queryType, String DType, String SDate, String EDate, String UserID)
            throws PopbillException {

        if (queryType == null)
            throw new PopbillException(-99999999, "전자세금계산서 유형이 입력되지 않았습니다.");

        JobIDResponse response = httppost(
                "/HomeTax/Taxinvoice/" + queryType.name() + "?DType=" + DType + "&SDate=" + SDate + "&EDate=" + EDate, CorpNum,
                null, UserID, JobIDResponse.class);

        return response.jobID;
    }

    /*
     * (non-Javadoc)
     * @see com.popbill.api.HTTaxinvoiceService#getJobState(java.lang.String, java.lang.String)
     */
    @Override
    public HTTaxinvoiceJobState getJobState(String CorpNum, String JobID) throws PopbillException {

        return getJobState(CorpNum, JobID, null);
    }

    /*
     * (non-Javadoc)
     * @see com.popbill.api.HTTaxinvoiceService#getJobState(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public HTTaxinvoiceJobState getJobState(String CorpNum, String JobID, String UserID) throws PopbillException {
        if (ValidationUtils.isNullOrEmpty(JobID))
            throw new PopbillException(-99999999, "작업아이디가 입력되지 않았습니다.");

        return httpget("/HomeTax/Taxinvoice/" + JobID + "/State", CorpNum, UserID, HTTaxinvoiceJobState.class);
    }

    /*
     * (non-Javadoc)
     * @see com.popbill.api.HTTaxinvoiceService#ListActiveJob(java.lang.String)
     */
    @Override
    public HTTaxinvoiceJobState[] listActiveJob(String CorpNum) throws PopbillException {

        return listActiveJob(CorpNum, null);
    }

    /*
     * (non-Javadoc)
     * @see com.popbill.api.HTTaxinvoiceService#ListActiveJob(java.lang.String, java.lang.String)
     */
    @Override
    public HTTaxinvoiceJobState[] listActiveJob(String CorpNum, String UserID) throws PopbillException {

        return httpget("/HomeTax/Taxinvoice/JobList", CorpNum, UserID, HTTaxinvoiceJobState[].class);
    }

    /*
     * (non-Javadoc)
     * @see com.popbill.api.HTTaxinvoiceService#Search(java.lang.String, java.lang.String, java.lang.String[], java.lang.String[], java.lang.String[], java.lang.Boolean, java.lang.String, java.lang.String[], java.lang.Integer, java.lang.Integer, java.lang.String)
     */
    @Override
    public HTTaxinvoiceSearchResult search(String CorpNum, String JobID, String[] Type, String[] TaxType, String[] PurposeType,
            String TaxRegIDYN, String TaxRegIDType, String TaxRegID, Integer Page, Integer PerPage, String Order)
            throws PopbillException {

        return search(CorpNum, JobID, Type, TaxType, PurposeType, TaxRegIDYN, TaxRegIDType, TaxRegID, Page, PerPage, Order,
                null);
    }

    /*
     * (non-Javadoc)
     * @see com.popbill.api.HTTaxinvoiceService#Search(java.lang.String, java.lang.String, java.lang.String[], java.lang.String[], java.lang.String[], java.lang.Boolean, java.lang.String, java.lang.String[], java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.String)
     */
    @Override
    public HTTaxinvoiceSearchResult search(String CorpNum, String JobID, String[] Type, String[] TaxType, String[] PurposeType,
            String TaxRegIDYN, String TaxRegIDType, String TaxRegID, Integer Page, Integer PerPage, String Order, String UserID)
            throws PopbillException {

        return search(CorpNum, JobID, Type, TaxType, PurposeType, TaxRegIDYN, TaxRegIDType, TaxRegID, Page, PerPage, Order,
                UserID, null);
    }

    /*
     * (non-Javadoc)
     * @see com.popbill.api.HTTaxinvoiceService#search(java.lang.String, java.lang.String, java.lang.String[], java.lang.String[], java.lang.String[], java.lang.String, java.lang.String, java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.String)
     */
    @Override
    public HTTaxinvoiceSearchResult search(String CorpNum, String JobID, String[] Type, String[] TaxType, String[] PurposeType,
            String TaxRegIDYN, String TaxRegIDType, String TaxRegID, Integer Page, Integer PerPage, String Order, String UserID,
            String SearchString) throws PopbillException {

        if (ValidationUtils.isNullOrEmpty(JobID))
            throw new PopbillException(-99999999, "작업아이디가 입력되지 않았습니다.");

        String uri = "/HomeTax/Taxinvoice/" + JobID + "?Type=";

        if (!ValidationUtils.isNullOrEmpty(Type))
            uri += ValidationUtils.replaceInvalidUriChars(Type);

        if (!ValidationUtils.isNullOrEmpty(TaxType))
            uri += "&TaxType=" + ValidationUtils.replaceInvalidUriChars(TaxType);

        if (!ValidationUtils.isNullOrEmpty(PurposeType))
            uri += "&PurposeType=" + ValidationUtils.replaceInvalidUriChars(PurposeType);

        if (!ValidationUtils.isNullOrEmpty(TaxRegIDYN))
            uri += "&TaxRegIDYN=" + TaxRegIDYN;

        if (!ValidationUtils.isNullOrEmpty(TaxRegIDType))
            uri += "&TaxRegIDType=" + TaxRegIDType;

        if (!ValidationUtils.isNullOrEmpty(TaxRegID))
            uri += "&TaxRegID=" + TaxRegID;

        if (Page != null && Page > 0)
            uri += "&Page=" + Integer.toString(Page);

        if (PerPage != null && PerPage > 0 && PerPage <= 1000)
            uri += "&PerPage=" + Integer.toString(PerPage);

        if (Order != null && (Order.equals("D") || Order.equals("A")))
            uri += "&Order=" + Order;

        if (!ValidationUtils.isNullOrEmpty(SearchString)) {
            try {
                uri += "&SearchString=" + URLEncoder.encode(SearchString, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                throw new PopbillException(-99999999, "검색어 인코딩이 실패 되었습니다.");
            }
        }

        return httpget(uri, CorpNum, UserID, HTTaxinvoiceSearchResult.class);
    }

    /*
     * (non-Javadoc)
     * @see com.popbill.api.HTTaxinvoiceService#Summary(java.lang.String, java.lang.String, java.lang.String[], java.lang.String[], java.lang.String[], java.lang.Boolean, java.lang.String, java.lang.String[])
     */
    @Override
    public HTTaxinvoiceSummary summary(String CorpNum, String JobID, String[] Type, String[] TaxType, String[] PurposeType,
            String TaxRegIDYN, String TaxRegIDType, String TaxRegID) throws PopbillException {
        return summary(CorpNum, JobID, Type, TaxType, PurposeType, TaxRegIDYN, TaxRegIDType, TaxRegID, null);
    }

    /*
     * (non-Javadoc)
     * @see com.popbill.api.HTTaxinvoiceService#Summary(java.lang.String, java.lang.String, java.lang.String[], java.lang.String[], java.lang.String[], java.lang.Boolean, java.lang.String, java.lang.String[], java.lang.String)
     */
    @Override
    public HTTaxinvoiceSummary summary(String CorpNum, String JobID, String[] Type, String[] TaxType, String[] PurposeType,
            String TaxRegIDYN, String TaxRegIDType, String TaxRegID, String UserID) throws PopbillException {
        return summary(CorpNum, JobID, Type, TaxType, PurposeType, TaxRegIDYN, TaxRegIDType, TaxRegID, UserID, null);
    }

    @Override
    public HTTaxinvoiceSummary summary(String CorpNum, String JobID, String[] Type, String[] TaxType, String[] PurposeType,
            String TaxRegIDYN, String TaxRegIDType, String TaxRegID, String UserID, String SearchString)
            throws PopbillException {

        if (ValidationUtils.isNullOrEmpty(JobID))
            throw new PopbillException(-99999999, "작업아이디가 입력되지 않았습니다.");

        String uri = "/HomeTax/Taxinvoice/" + JobID + "/Summary" + "?Type=";

        if (!ValidationUtils.isNullOrEmpty(Type))
            uri += ValidationUtils.replaceInvalidUriChars(Type);

        if (!ValidationUtils.isNullOrEmpty(TaxType))
            uri += "&TaxType=" + ValidationUtils.replaceInvalidUriChars(TaxType);

        if (!ValidationUtils.isNullOrEmpty(PurposeType))
            uri += "&PurposeType=" + ValidationUtils.replaceInvalidUriChars(PurposeType);

        if (!ValidationUtils.isNullOrEmpty(TaxRegIDYN))
            uri += "&TaxRegIDYN=" + TaxRegIDYN;

        if (!ValidationUtils.isNullOrEmpty(TaxRegIDType))
            uri += "&TaxRegIDType=" + TaxRegIDType;

        if (!ValidationUtils.isNullOrEmpty(TaxRegID))
            uri += "&TaxRegID=" + TaxRegID;

        if (!ValidationUtils.isNullOrEmpty(SearchString)) {
            try {
                uri += "&SearchString=" + URLEncoder.encode(SearchString, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                throw new PopbillException(-99999999, "검색어 인코딩이 실패 되었습니다.");
            }
        }

        return httpget(uri, CorpNum, UserID, HTTaxinvoiceSummary.class);
    }

    /*
     * (non-Javadoc)
     * @see com.popbill.api.HTTaxinvoiceService#GetTaxinvoice(java.lang.String, java.lang.String)
     */
    @Override
    public HTTaxinvoice getTaxinvoice(String CorpNum, String NTSConfirmNum) throws PopbillException {
        return getTaxinvoice(CorpNum, NTSConfirmNum, null);
    }

    /*
     * (non-Javadoc)
     * @see com.popbill.api.HTTaxinvoiceService#GetTaxinvoice(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public HTTaxinvoice getTaxinvoice(String CorpNum, String NTSConfirmNum, String UserID) throws PopbillException {
        if (ValidationUtils.isNullOrEmpty(NTSConfirmNum))
            throw new PopbillException(-99999999, "국세청승인번호가 입력되지 않았습니다.");

        return httpget("/HomeTax/Taxinvoice/" + NTSConfirmNum, CorpNum, UserID, HTTaxinvoice.class);
    }

    /*
     * (non-Javadoc)
     * @see com.popbill.api.HTTaxinvoiceService#GetXML(java.lang.String, java.lang.String)
     */
    @Override
    public HTTaxinvoiceXMLResponse getXML(String CorpNum, String NTSConfirmNum) throws PopbillException {
        return getXML(CorpNum, NTSConfirmNum, null);
    }

    /*
     * (non-Javadoc)
     * @see com.popbill.api.HTTaxinvoiceService#GetXML(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public HTTaxinvoiceXMLResponse getXML(String CorpNum, String NTSConfirmNum, String UserID) throws PopbillException {
        if (ValidationUtils.isNullOrEmpty(NTSConfirmNum))
            throw new PopbillException(-99999999, "국세청승인번호가 입력되지 않았습니다.");

        return httpget("/HomeTax/Taxinvoice/" + NTSConfirmNum + "?T=xml", CorpNum, UserID, HTTaxinvoiceXMLResponse.class);
    }

    /*
     * (non-Javadoc)
     * @see com.popbill.api.HTTaxinvoiceService#GetFlatRateState(java.lang.String)
     */
    @Override
    public FlatRateState getFlatRateState(String CorpNum) throws PopbillException {
        return getFlatRateState(CorpNum, null);
    }

    /*
     * (non-Javadoc)
     * @see com.popbill.api.HTTaxinvoiceService#GetFlatRateState(java.lang.String, java.lang.String)
     */
    @Override
    public FlatRateState getFlatRateState(String CorpNum, String UserID) throws PopbillException {
        return httpget("/HomeTax/Taxinvoice/Contract", CorpNum, UserID, FlatRateState.class);
    }

    /*
     * (non-Javadoc)
     * @see com.popbill.api.HTTaxinvoiceService#getCertificatePopUpURL(java.lang.String)
     */
    @Override
    public String getCertificatePopUpURL(String CorpNum) throws PopbillException {

        return getCertificatePopUpURL(CorpNum, null);
    }

    /*
     * (non-Javadoc)
     * @see com.popbill.api.HTTaxinvoiceService#getCertificatePopUpURl(java.lang.String, java.lang.String)
     */
    @Override
    public String getCertificatePopUpURL(String CorpNum, String UserID) throws PopbillException {

        URLResponse response = httpget("/HomeTax/Taxinvoice?TG=CERT", CorpNum, UserID, URLResponse.class);

        return response.url;
    }

    /*
     * (non-Javadoc)
     * @see com.popbill.api.HTTaxinvoiceService#getCertificateExpireDate(java.lang.String)
     */
    @Override
    public Date getCertificateExpireDate(String CorpNum) throws PopbillException {
        return getCertificateExpireDate(CorpNum, null);
    }

    @Override
    public Date getCertificateExpireDate(String CorpNum, String UserID) throws PopbillException {
        CertResponse response = httpget("/HomeTax/Taxinvoice/CertInfo", CorpNum, UserID, CertResponse.class);

        try {
            return new SimpleDateFormat("yyyyMMddHHmmss").parse(response.certificateExpiration);
        } catch (ParseException e) {
            throw new PopbillException(-99999999, "날자형식 포맷변환 실패[" + response.certificateExpiration + "]", e);
        }
    }

    /*
     * (non-Javadoc)
     * @see com.popbill.api.HTTaxinvoiceService#getPopUpURL(java.lang.String, java.lang.String)
     */
    @Override
    public String getPopUpURL(String CorpNum, String NTSConfirmNum) throws PopbillException {
        if (ValidationUtils.isNullOrEmpty(NTSConfirmNum))
            throw new PopbillException(-99999999, "국세청승인번호가 입력되지 않았습니다.");

        URLResponse response = httpget("/HomeTax/Taxinvoice/" + NTSConfirmNum + "/PopUp", CorpNum, null, URLResponse.class);

        return response.url;
    }

    /*
     * (non-Javadoc)
     * @see com.popbill.api.HTTaxinvoiceService#getPopUpURL(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public String getPopUpURL(String CorpNum, String NTSConfirmNum, String UserID) throws PopbillException {
        if (ValidationUtils.isNullOrEmpty(NTSConfirmNum))
            throw new PopbillException(-99999999, "국세청승인번호가 입력되지 않았습니다.");

        URLResponse response = httpget("/HomeTax/Taxinvoice/" + NTSConfirmNum + "/PopUp", CorpNum, UserID, URLResponse.class);

        return response.url;
    }

    @Override
    public String getPrintURL(String CorpNum, String NTSConfirmNum) throws PopbillException {
        return getPrintURL(CorpNum, NTSConfirmNum, null);
    }

    @Override
    public String getPrintURL(String CorpNum, String NTSConfirmNum, String UserID) throws PopbillException {
        if (ValidationUtils.isNullOrEmpty(NTSConfirmNum))
            throw new PopbillException(-99999999, "국세청승인번호가 입력되지 않았습니다.");

        URLResponse response = httpget("/HomeTax/Taxinvoice/" + NTSConfirmNum + "/Print", CorpNum, UserID, URLResponse.class);
        return response.url;
    }

    /*
     * (non-Javadoc)
     * @see com.popbill.api.HTTaxinvoiceService#checkCertValidation(java.lang.String)
     */
    public Response checkCertValidation(String CorpNum) throws PopbillException {
        return checkCertValidation(CorpNum, null);
    }

    @Override
    public Response checkCertValidation(String CorpNum, String UserID) throws PopbillException {
        return httpget("/HomeTax/Taxinvoice/CertCheck", CorpNum, UserID, Response.class);
    }

    /*
     * (non-Javadoc)
     * @see com.popbill.api.HTTaxinvoiceService#registDeptUser(java.lang.String, java.lang.String, java.lang.String)
     */
    public Response registDeptUser(String CorpNum, String DeptUserID, String DeptUserPWD) throws PopbillException {
        return registDeptUser(CorpNum, DeptUserID, DeptUserPWD, null, null);
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

        return httppost("/HomeTax/Taxinvoice/DeptUser", CorpNum, PostData, UserID, Response.class);
    }

    /*
     * (non-Javadoc)
     * @see com.popbill.api.HTTaxinvoiceService#checkDeptUser(java.lang.String, java.lang.String)
     */
    public Response checkDeptUser(String CorpNum) throws PopbillException {
        return checkDeptUser(CorpNum, null);
    }

    @Override
    public Response checkDeptUser(String CorpNum, String UserID) throws PopbillException {
        return httpget("/HomeTax/Taxinvoice/DeptUser", CorpNum, UserID, Response.class);
    }

    /*
     * (non-Javadoc)
     * @see com.popbill.api.HTTaxinvoiceService#checkLoginDeptUser(java.lang.String)
     */
    public Response checkLoginDeptUser(String CorpNum) throws PopbillException {
        return checkLoginDeptUser(CorpNum, null);
    }

    @Override
    public Response checkLoginDeptUser(String CorpNum, String UserID) throws PopbillException {
        return httpget("/HomeTax/Taxinvoice/DeptUser/Check", CorpNum, UserID, Response.class);
    }

    /*
     * (non-Javadoc)
     * @see com.popbill.api.HTTaxinvoiceService#deleteDeptUser(java.lang.String)
     */
    public Response deleteDeptUser(String CorpNum) throws PopbillException {
        return deleteDeptUser(CorpNum, null);
    }

    @Override
    public Response deleteDeptUser(String CorpNum, String UserID) throws PopbillException {
        return httppost("/HomeTax/Taxinvoice/DeptUser", CorpNum, null, UserID, "DELETE", Response.class);
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
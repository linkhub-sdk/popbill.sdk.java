package com.popbill.api;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.ProtocolException;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.URL;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.zip.GZIPInputStream;

import com.google.gson.Gson;

import com.popbill.api.util.ValidationUtils;
import kr.co.linkhub.auth.Base64;
import kr.co.linkhub.auth.LinkhubException;
import kr.co.linkhub.auth.MemberPointDetail;
import kr.co.linkhub.auth.Token;
import kr.co.linkhub.auth.TokenBuilder;

/**
 * Abstract class for Popbill Services.
 *
 * @author KimSeongjun
 * @version 1.0.0
 */
public abstract class BaseServiceImp implements BaseService {

    private static String ServiceID_REAL = "POPBILL";
    private static String ServiceID_TEST = "POPBILL_TEST";
    private static final String Auth_Static_URL = "https://static-auth.linkhub.co.kr";
    private static final String Auth_GA_URL = "https://ga-auth.linkhub.co.kr";
    private static final String ServiceURL_REAL = "https://popbill.linkhub.co.kr";
    private static final String ServiceURL_TEST = "https://popbill-test.linkhub.co.kr";
    private static final String ServiceURL_Static_REAL = "https://static-popbill.linkhub.co.kr";
    private static final String ServiceURL_Static_TEST = "https://static-popbill-test.linkhub.co.kr";
    private static final String ServiceURL_GA_REAL = "https://ga-popbill.linkhub.co.kr";
    private static final String ServiceURL_GA_TEST = "https://ga-popbill-test.linkhub.co.kr";
    private static final String[] apiHeaderList = {"Authorization", "Accept-Encoding", "Connection", "Content-Type", "Content-Length", "X-HTTP-Method-Override"};

    private final String APIVersion = "1.0";
    private String ServiceURL = null;
    private String TestServiceURL = null;
    private String AuthURL = null;
    private String ProxyIP = null;
    private Integer ProxyPort = null;

    private TokenBuilder tokenBuilder;

    private boolean isTest;
    private Map<String, String> customHeader;
    private boolean isIPRestrictOnOff;
    private boolean useStaticIP;
    private boolean useGAIP;
    private boolean useLocalTimeYN;
    private String linkID;
    private String secretKey;
    private final Gson _gsonParser = new Gson();

    private String mleKeyID;
    private String mleKeyName;
    private String mlePublicKey;

    private final Map<String, Token> tokenTable = new HashMap<String, Token>();

    public Map<String, String> getCustomHeader() {
        return customHeader;
    }

    public void setCustomHeader(Map<String, String> customHeader) {
        this.customHeader = customHeader;
    }

    public void setMleKeyID(String mleKeyID) {
        this.mleKeyID = mleKeyID;
    }

    public void setMleKeyName(String mleKeyName) {
        this.mleKeyName = mleKeyName;
    }

    public void setMlePublicKey(String mlePublicKey) {
        this.mlePublicKey = mlePublicKey;
    }

    public String getMleKeyID() {
        return mleKeyID;
    }

    public String getMleKeyName() {
        return mleKeyName;
    }

    public String getMlePublicKey() {
        return mlePublicKey;
    }

    /**
     * IP 제한 여부 기본값 True
     */
    public BaseServiceImp() {
        isIPRestrictOnOff = true;
        useStaticIP = false;
        useGAIP = false;
        useLocalTimeYN = true;
    }

    /**
     * 테스트모드 확인. 기본값은 false.
     *
     * @return is Test or not.
     */
    public boolean isTest() {
        return isTest;
    }


    public boolean isIPRestrictOnOff() {
        return isIPRestrictOnOff;
    }

    public boolean isUseStaticIP() {
        return useStaticIP;
    }

    public boolean isUseLocalTimeYN() {
        return useLocalTimeYN;
    }

    /**
     * v1.37.0 추가
     */
    public boolean isUseGAIP() {
        return useGAIP;
    }

    /**
     * 테스트 모드 설정.
     *
     * @param isTest Test or not.
     */
    public void setTest(boolean isTest) {
        this.isTest = isTest;
    }

    /**
     * IP 제한 해제.
     *
     * @param IsIPRestrictOnOff
     */
    public void setIPRestrictOnOff(boolean IsIPRestrictOnOff) {
        this.isIPRestrictOnOff = IsIPRestrictOnOff;
    }

    public void setUseStaticIP(boolean UseStaticIP) {
        this.useStaticIP = UseStaticIP;
    }

    /**
     * v1.37.0 추가
     */
    public void setUseGAIP(boolean UseGAIP) {
        this.useGAIP = UseGAIP;
    }

    /**
     * Local 타임 사용 여부.
     *
     * @param UseLocalTimeYN
     */
    public void setUseLocalTimeYN(boolean UseLocalTimeYN) {
        this.useLocalTimeYN = UseLocalTimeYN;
    }

    protected String getLinkID() {
        return linkID;
    }

    /**
     * 링크아이디 설정. (링크허브에서 발급)
     *
     * @param LinkID
     */
    public void setLinkID(String LinkID) {
        this.linkID = LinkID;
    }

    /**
     * Proxy 인증 URL 설정.
     *
     * @param AuthURL
     */
    public void setAuthURL(String AuthURL) {
        this.AuthURL = AuthURL;
    }

    /**
     * Proxy 운영기 URL 설정.
     *
     * @param ServiceURL
     */
    public void setServiceURL(String ServiceURL) {
        this.ServiceURL = ServiceURL;
    }

    public void setProxyIP(String proxyIP) {
        this.ProxyIP = proxyIP;
    }

    public void setProxyPort(Integer proxyPort) {
        this.ProxyPort = proxyPort;
    }

    /**
     * Proxy 테스트기 URL 설정.
     *
     * @param TestServiceURL
     */
    public void setTestServiceURL(String TestServiceURL) {
        this.TestServiceURL = TestServiceURL;
    }

    protected String getSecretKey() {
        return secretKey;
    }

    /**
     * 비밀키 설정 (Issued by Linkhub)
     *
     * @param SecretKey SecretKey.
     */
    public void setSecretKey(String SecretKey) {
        this.secretKey = SecretKey;
    }

    protected String getServiceID() {
        return isTest ? ServiceID_TEST : ServiceID_REAL;
    }

    public void setServiceID(String serviceID) {
        ServiceID_TEST = serviceID;
        ServiceID_REAL = serviceID;
    }

    protected String getServiceURL() {

        // SetServiceURL 우선.
        if (isTest) {
            if (TestServiceURL != null)
                return TestServiceURL;
        } else {
            if (ServiceURL != null)
                return ServiceURL;
        }

        // ServiceURL null 인경우 useStaticIP 체크
        if (useGAIP) {
            return isTest ? ServiceURL_GA_TEST : ServiceURL_GA_REAL;
        } else if (useStaticIP) {
            return isTest ? ServiceURL_Static_TEST : ServiceURL_Static_REAL;
        } else {
            return isTest ? ServiceURL_TEST : ServiceURL_REAL;
        }

    }

    private TokenBuilder getTokenbuilder() {
        if (this.tokenBuilder == null) {
            tokenBuilder = TokenBuilder.newInstance(getLinkID(), getSecretKey())
                    .ServiceID(isTest ? ServiceID_TEST : ServiceID_REAL)
                    .addScope("member")
                    .useLocalTimeYN(useLocalTimeYN);
            if (AuthURL != null) {
                tokenBuilder.setServiceURL(AuthURL);
            } else {
                // AuthURL 이 null이고, useGAIP 가 true일때. ga-auth 호출.
                if (useGAIP) {
                    tokenBuilder.setServiceURL(Auth_GA_URL);
                } else if (useStaticIP) {
                    tokenBuilder.setServiceURL(Auth_Static_URL);
                }
            }

            if (ProxyIP != null && ProxyPort != null) {
                tokenBuilder.setProxyIP(ProxyIP);
                tokenBuilder.setProxyPort(ProxyPort);
            }

            if (this.customHeader != null && this.customHeader.size() > 0) {
                tokenBuilder.addCustomHeader(this.customHeader);
            }

            for (String scope : getScopes())
                tokenBuilder.addScope(scope);
        }

        return tokenBuilder;
    }

    private String getSessionToken(String CorpNum, String ForwardIP) throws PopbillException {
        if (ValidationUtils.isNullOrEmpty(CorpNum))
            throw new PopbillException(-99999999, "팝빌회원 사업자번호가 입력되지 않았습니다.");

        Token token = null;
        Date UTCTime = null;

        if (tokenTable.containsKey(CorpNum))
            token = tokenTable.get(CorpNum);

        boolean expired = true;

        if (token != null) {

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            format.setTimeZone(TimeZone.getTimeZone("UTC"));

            SimpleDateFormat subFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
            subFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

            try {

                Date expiration = format.parse(token.getExpiration());
                UTCTime = subFormat.parse(getTokenbuilder().getTime());
                expired = expiration.before(UTCTime);

            } catch (LinkhubException le) {
                throw new PopbillException(le);
            } catch (ParseException e) {
            }
        }

        if (expired) {
            if (tokenTable.containsKey(CorpNum))
                tokenTable.remove(CorpNum);

            try {

                // IP 제한 On/Off 기능 추가 - 2019/01/31
                if (isIPRestrictOnOff) {
                    token = getTokenbuilder().build(CorpNum, ForwardIP);
                } else {
                    token = getTokenbuilder().build(CorpNum, "*");
                }

                tokenTable.put(CorpNum, token);
            } catch (LinkhubException le) {
                throw new PopbillException(le);
            }
        }

        return token.getSession_token();
    }

    /*
     * (non-Javadoc)
     *
     * @see com.popbill.api.BaseService#getPartnerURL(java.lang.String,
     * java.lang.String)
     */
    public String getPartnerURL(String CorpNum, String TOGO) throws PopbillException {
        try {
            return getTokenbuilder().getPartnerURL(this.getSessionToken(CorpNum, null), TOGO);
        } catch (LinkhubException le) {
            throw new PopbillException(le);
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see com.popbill.api.BaseService#getBalance(java.lang.String)
     */
    @Override
    public double getBalance(String CorpNum) throws PopbillException {
        try {
            return getTokenbuilder().getBalance(this.getSessionToken(CorpNum, null));
        } catch (LinkhubException le) {
            throw new PopbillException(le);
        }
    }


    public MemberPointDetail getPointInfo(String CorpNum) throws PopbillException {
        try {
            return getTokenbuilder().getBalanceDetail(this.getSessionToken(CorpNum, null));
        } catch (LinkhubException le) {
            throw new PopbillException(le);
        }
    }


    /*
     * (non-Javadoc)
     *
     * @see com.popbill.api.BaseService#getPartnerBalance(java.lang.String)
     */
    @Override
    public double getPartnerBalance(String CorpNum) throws PopbillException {
        try {
            return getTokenbuilder().getPartnerBalance(this.getSessionToken(CorpNum, null));
        } catch (LinkhubException le) {
            throw new PopbillException(le);
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see com.popbill.api.BaseService#joinMember(com.popbill.api.JoinForm)
     */
    @Override
    public Response joinMember(JoinForm JoinInfo) throws PopbillException {
        String postData = toJsonString(JoinInfo);

        return httppost("/Join", null, postData, null, Response.class);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.popbill.api.BaseService#getPopbillURL(java.lang.String,
     * java.lang.String, java.lang.String)
     */
    @Override
    public String getPopbillURL(String CorpNum, String TOGO) throws PopbillException {
        return getPopbillURL(CorpNum, null, TOGO);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.popbill.api.BaseService#getPopbillURL(java.lang.String,
     * java.lang.String, java.lang.String)
     */
    @Override
    public String getPopbillURL(String CorpNum, String UserID, String TOGO) throws PopbillException {
        URLResponse response = httpget("/Member?TG=" + TOGO, CorpNum, UserID, URLResponse.class);
        return response.url;
    }

    @Override
    public UseHistoryResult getUseHistory(String CorpNum, String SDate, String EDate, Integer Page, Integer PerPage, String Order) throws PopbillException {
        return getUseHistory(CorpNum, SDate, EDate, Page, PerPage, Order, null);
    }

    @Override
    public UseHistoryResult getUseHistory(String CorpNum, String SDate, String EDate, Integer Page, Integer PerPage, String Order, String UserID) throws PopbillException {
        String url = "/UseHistory";

        if (!ValidationUtils.isNullOrEmpty(SDate))
            url += "?SDate=" + SDate;

        if (!ValidationUtils.isNullOrEmpty(EDate))
            url += "&EDate=" + EDate;

        if (Page != null)
            url += "&Page=" + Page;

        if (PerPage != null)
            url += "&PerPage=" + PerPage;

        if (!ValidationUtils.isNullOrEmpty(Order))
            url += "&Order=" + Order;

        return httpget(url, CorpNum, UserID, UseHistoryResult.class);
    }

    @Override
    public PaymentHistoryResult getPaymentHistory(String CorpNum, String SDate, String EDate, Integer Page, Integer PerPage) throws PopbillException {
        return getPaymentHistory(CorpNum, SDate, EDate, Page, PerPage, null);
    }

    @Override
    public PaymentHistoryResult getPaymentHistory(String CorpNum, String SDate, String EDate, Integer Page, Integer PerPage, String UserID) throws PopbillException {
        String url = "/PaymentHistory";
        if (!ValidationUtils.isNullOrEmpty(SDate))
            url += "?SDate=" + SDate;

        if (!ValidationUtils.isNullOrEmpty(EDate))
            url += "&EDate=" + EDate;

        if (Page != null)
            url += "&Page=" + Page;

        if (PerPage != null)
            url += "&PerPage=" + PerPage;

        return httpget(url, CorpNum, UserID, PaymentHistoryResult.class);
    }

    @Override
    public RefundHistoryResult getRefundHistory(String CorpNum, Integer Page, Integer PerPage) throws PopbillException {
        return getRefundHistory(CorpNum, Page, PerPage, null);
    }

    @Override
    public RefundHistoryResult getRefundHistory(String CorpNum, Integer Page, Integer PerPage, String UserID) throws PopbillException {
        String url = "/RefundHistory";

        if (Page != null)
            url += "?Page=" + Page;

        if (PerPage != null)
            url += "&PerPage=" + PerPage;

        return httpget(url, CorpNum, UserID, RefundHistoryResult.class);
    }

    @Override
    public RefundResponse refund(String CorpNum, RefundForm RefundForm) throws PopbillException {
        return refund(CorpNum, RefundForm, null);
    }

    @Override
    public RefundResponse refund(String CorpNum, RefundForm RefundForm, String UserID) throws PopbillException {
        String postData = toJsonString(RefundForm);
        return httppost("/Refund", CorpNum, postData, UserID, RefundResponse.class);
    }

    @Override
    public RefundHistory getRefundInfo(String CorpNum, String RefundCode) throws PopbillException {
        return getRefundInfo(CorpNum, RefundCode, null);
    }

    @Override
    public RefundHistory getRefundInfo(String CorpNum, String RefundCode, String UserID) throws PopbillException {
        if (ValidationUtils.isNullOrEmpty(RefundCode))
            throw new PopbillException(-99999999, "환불코드가 입력되지 않았습니다.");

        return httpget("/Refund/" + RefundCode, CorpNum, UserID, RefundHistory.class);
    }


    @Override
    public double getRefundableBalance(String CorpNum) throws PopbillException {
        return getRefundableBalance(CorpNum, null);
    }


    @Override
    public double getRefundableBalance(String CorpNum, String UserID) throws PopbillException {
        RefundableBalance rfn = httpget("/RefundPoint", CorpNum, UserID, RefundableBalance.class);
        return rfn.getRefundableBalance();
    }

    @Override
    public PaymentResponse paymentRequest(String CorpNum, PaymentForm PaymentForm) throws PopbillException {
        return paymentRequest(CorpNum, PaymentForm, null);
    }

    @Override
    public PaymentResponse paymentRequest(String CorpNum, PaymentForm PaymentForm, String UserID) throws PopbillException {
        String postData = toJsonString(PaymentForm);
        return httppost("/Payment", CorpNum, postData, UserID, PaymentResponse.class);
    }

    @Override
    public PaymentHistory getSettleResult(String CorpNum, String SettleCode) throws PopbillException {
        return getSettleResult(CorpNum, SettleCode, null);
    }

    @Override
    public PaymentHistory getSettleResult(String CorpNum, String SettleCode, String UserID) throws PopbillException {
        if (ValidationUtils.isNullOrEmpty(SettleCode))
            throw new PopbillException(-99999999, "정산코드가 입력되지 않았습니다.");

        return httpget("/Payment/" + SettleCode, CorpNum, null, PaymentHistory.class);
    }

    ;

    /*
     * (non-Javadoc)
     *
     * @see com.popbill.api.BaseService#checkIsMember(java.lang.String,
     * java.lang.String)
     */
    @Override
    public Response checkIsMember(String CorpNum, String LinkID) throws PopbillException {

        return httpget("/Join?CorpNum=" + CorpNum + "&LID=" + LinkID, null, null, Response.class);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.popbill.api.BaseService#getContactInfo(java.lang.String,
     * java.lang.String)
     */
    @Override
    public ContactInfo getContactInfo(String CorpNum, String ContactID) throws PopbillException {

        return getContactInfo(CorpNum, ContactID, null);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.popbill.api.BaseService#getContactInfo(java.lang.String,
     * java.lang.String, java.lang.String)
     */
    @Override
    public ContactInfo getContactInfo(String CorpNum, String ContactID, String UserID) throws PopbillException {
        String postData = "{'id' :" + "'" + ContactID + "'}";

        return httppost("/Contact", CorpNum, postData, UserID, ContactInfo.class);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.popbill.api.BaseService#listContact(java.lang.String,
     * java.lang.String)
     */
    @Override
    public ContactInfo[] listContact(String CorpNum) throws PopbillException {

        return httpget("/IDs", CorpNum, null, ContactInfo[].class);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.popbill.api.BaseService#listContact(java.lang.String,
     * java.lang.String)
     */
    @Override
    public ContactInfo[] listContact(String CorpNum, String UserID) throws PopbillException {

        return httpget("/IDs", CorpNum, UserID, ContactInfo[].class);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.popbill.api.BaseService#updateContactMember(java.lang.String,
     * com.popbill.api.ContactInfo, java.lang.String)
     */
    @Override
    public Response updateContact(String CorpNum, ContactInfo contactInfo, String UserID) throws PopbillException {
        String postData = toJsonString(contactInfo);

        return httppost("/IDs", CorpNum, postData, UserID, Response.class);
    }

    @Override
    public Response deleteContact(String CorpNum, String ContactID, String UserID) throws PopbillException {
        String uri = "/Contact/Delete";

        uri += "?ContactID=" + ContactID;

        return httppost(uri, CorpNum, null, UserID, Response.class);
    }

    @Override
    public Response quitMember(String CorpNum, String quitReason) throws PopbillException {

        return quitMember(CorpNum, quitReason, null);
    }

    @Override
    public Response quitMember(String CorpNum, String quitReason, String UserID) throws PopbillException {
        String postData = "{'quitReason' :" + "'" + quitReason + "'}";
        Response quitResponse = httppost("/QuitRequest", CorpNum, postData, UserID, Response.class);

        if (quitResponse.getCode() == 1) {
            // 토큰 테이블 캐시 삭제
            tokenTable.remove(CorpNum);
        }
        return quitResponse;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.popbill.api.BaseService#registContact(java.lang.String,
     * com.popbill.api.ContactInfo, java.lang.String)
     */
    @Override
    public Response registContact(String CorpNum, ContactInfo contactInfo) throws PopbillException {
        String postData = toJsonString(contactInfo);

        return httppost("/IDs/New", CorpNum, postData, null, Response.class);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.popbill.api.BaseService#registContactMember(java.lang.String,
     * com.popbill.api.ContactInfo, java.lang.String)
     */
    @Override
    public Response registContact(String CorpNum, ContactInfo contactInfo, String UserID) throws PopbillException {
        String postData = toJsonString(contactInfo);

        return httppost("/IDs/New", CorpNum, postData, UserID, Response.class);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.popbill.api.BaseService#checkID(java.lang.String)
     */
    @Override
    public Response checkID(String CheckID) throws PopbillException {

        return httpget("/IDCheck?ID=" + CheckID, null, null, Response.class);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.popbill.api.BaseService#getCorpInfo(java.lang.String,
     * java.lang.String)
     */
    @Override
    public CorpInfo getCorpInfo(String CorpNum) throws PopbillException {

        return httpget("/CorpInfo", CorpNum, null, CorpInfo.class);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.popbill.api.BaseService#getCorpInfo(java.lang.String,
     * java.lang.String)
     */
    @Override
    public CorpInfo getCorpInfo(String CorpNum, String UserID) throws PopbillException {

        return httpget("/CorpInfo", CorpNum, UserID, CorpInfo.class);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.popbill.api.BaseService#updateCorpInfo(java.lang.String,
     * com.popbill.api.CorpInfo, java.lang.String)
     */
    @Override
    public Response updateCorpInfo(String CorpNum, CorpInfo corpInfo) throws PopbillException {
        String postData = toJsonString(corpInfo);

        return httppost("/CorpInfo", CorpNum, postData, null, Response.class);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.popbill.api.BaseService#updateCorpInfo(java.lang.String,
     * com.popbill.api.CorpInfo, java.lang.String)
     */
    @Override
    public Response updateCorpInfo(String CorpNum, CorpInfo corpInfo, String UserID) throws PopbillException {
        String postData = toJsonString(corpInfo);

        return httppost("/CorpInfo", CorpNum, postData, UserID, Response.class);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.popbill.api.BaseService#getAccessURL(java.lang.String,
     * java.lang.String)
     */
    @Override
    public String getAccessURL(String CorpNum, String UserID) throws PopbillException {

        URLResponse response = httpget("/Member?TG=LOGIN", CorpNum, UserID, URLResponse.class);

        return response.url;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.popbill.api.BaseService#getChargeURL(java.lang.String,
     * java.lang.String)
     */
    @Override
    public String getChargeURL(String CorpNum, String UserID) throws PopbillException {

        URLResponse response = httpget("/Member?TG=CHRG", CorpNum, UserID, URLResponse.class);

        return response.url;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.popbill.api.BaseService#getPaymentURL(java.lang.String,
     * java.lang.String)
     */
    @Override
    public String getPaymentURL(String CorpNum, String UserID) throws PopbillException {

        URLResponse response = httpget("/Member?TG=PAYMENT", CorpNum, UserID, URLResponse.class);

        return response.url;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.popbill.api.BaseService#getUseHistoryURL(java.lang.String,
     * java.lang.String)
     */
    @Override
    public String getUseHistoryURL(String CorpNum, String UserID) throws PopbillException {

        URLResponse response = httpget("/Member?TG=USEHISTORY", CorpNum, UserID, URLResponse.class);

        return response.url;
    }

    /**
     * Convert Object to Json String.
     *
     * @param Graph
     * @return jsonString
     */
    protected String toJsonString(Object Graph) {
        return _gsonParser.toJson(Graph);
    }

    /**
     * Convert JsonString to Object of Clazz
     *
     * @param json
     * @param clazz
     * @return Object of Clazz
     */
    protected <T> T fromJsonString(String json, Class<T> clazz) {
        return _gsonParser.fromJson(json, clazz);
    }

    /**
     * @param url
     * @param CorpNum
     * @param PostData
     * @param UserID
     * @param clazz
     * @return returned object
     * @throws PopbillException
     */
    protected <T> T httppost(String url, String CorpNum, String PostData, String UserID, Class<T> clazz)
            throws PopbillException {
        return httppost(url, CorpNum, PostData, UserID, null, clazz);
    }

    /**
     * @param url
     * @param CorpNum
     * @param PostData
     * @param UserID
     * @param Action
     * @param clazz
     * @return returned object
     * @throws PopbillException
     */
    protected <T> T httppost(String url, String CorpNum, String PostData, String UserID, String Action, Class<T> clazz)
            throws PopbillException {
        return httppost(url, CorpNum, PostData, UserID, Action, clazz, null);
    }

    private static byte[] encryptSHA1(String input) {
        try {

            MessageDigest md = MessageDigest.getInstance("SHA-1");

            byte[] messageDigest = md.digest(input.getBytes(Charset.forName("UTF-8")));

            return messageDigest;

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    protected <T> T httpBulkPost(String url, String CorpNum, String SubmitID, String PostData, String UserID,
                                 String Action, Class<T> clazz) throws PopbillException {

        return httpBulkPost(url, CorpNum, SubmitID, PostData, UserID, Action, clazz, null);

    }

    protected <T> T httpBulkPost(String url, String CorpNum, String SubmitID, String PostData, String UserID,
                                 String Action, Class<T> clazz, String ContentType) throws PopbillException {

        HttpURLConnection httpURLConnection;
        try {
            URL uri = new URL(getServiceURL() + url);

            if (ProxyIP != null && ProxyPort != null) {
                Proxy prx = new Proxy(Type.HTTP, new InetSocketAddress(ProxyIP, ProxyPort));
                httpURLConnection = (HttpURLConnection) uri.openConnection(prx);
            } else {
                httpURLConnection = (HttpURLConnection) uri.openConnection();
            }

        } catch (Exception e) {
            throw new PopbillException(-99999999, "팝빌 API 서버 접속 실패", e);
        }

        httpURLConnection.setConnectTimeout(10 * 1000);
        httpURLConnection.setReadTimeout(180 * 1000);

        if (CorpNum != null && CorpNum.isEmpty() == false) {
            httpURLConnection.setRequestProperty("Authorization", "Bearer " + getSessionToken(CorpNum, null));
        }

        httpURLConnection.setRequestProperty("x-pb-message-digest", Base64.encode(encryptSHA1(PostData)));

        httpURLConnection.setRequestProperty("x-pb-submit-id", SubmitID);

        httpURLConnection.setRequestProperty("x-pb-version".toLowerCase(), APIVersion);

        if (Action != null && Action.isEmpty() == false) {
            httpURLConnection.setRequestProperty("X-HTTP-Method-Override", Action);
        }

        if (ContentType != null && ContentType.isEmpty() == false) {
            httpURLConnection.setRequestProperty("Content-Type", ContentType);
        } else {
            httpURLConnection.setRequestProperty("Content-Type", "application/json; charset=utf8");
        }

        httpURLConnection.setRequestProperty("Accept-Encoding", "gzip");

        if (UserID != null && UserID.isEmpty() == false) {
            httpURLConnection.setRequestProperty("x-pb-userid", UserID);
        }

        checkCustomHeaderValidation(httpURLConnection);

        try {
            httpURLConnection.setRequestMethod("POST");
        } catch (ProtocolException e1) {
        }

        httpURLConnection.setUseCaches(false);
        httpURLConnection.setDoOutput(true);

        if ((PostData == null || PostData.isEmpty()) == false) {

            byte[] btPostData = PostData.getBytes(Charset.forName("UTF-8"));

            if ("application/lh-encrypted".equalsIgnoreCase(ContentType)) {

                try {
                    Encryptor pb_enc = Encryptor.getInstance(this.mleKeyID, this.mleKeyName, this.mlePublicKey);
                    btPostData = pb_enc.Encrypt(btPostData);
                } catch (PopbillException PE) {
                    throw PE;
                } catch (Exception E) {
                    throw new PopbillException(-99999999, "Message Level Encryption is failed.", E);
                }
            }

            httpURLConnection.setRequestProperty("Content-Length", String.valueOf(btPostData.length));

            DataOutputStream output = null;

            try {
                output = new DataOutputStream(httpURLConnection.getOutputStream());
                output.write(btPostData);
                output.flush();
            } catch (Exception e) {
                throw new PopbillException(-99999999, "Fail to POST data to Server.", e);
            } finally {
                try {
                    if (output != null) {
                        output.close();
                    }
                } catch (IOException e1) {
                    throw new PopbillException(-99999999, "Popbill httppost func DataOutputStream close() Exception", e1);
                }
            }
        }

        String Result = parseResponse(httpURLConnection);

        return fromJsonString(Result, clazz);
    }

    /**
     * @param url
     * @param CorpNum
     * @param PostData
     * @param UserID
     * @param Action
     * @param clazz
     * @param ContentType
     * @return
     * @throws PopbillException
     */
    protected <T> T httppost(String url, String CorpNum, String PostData, String UserID, String Action, Class<T> clazz,
                             String ContentType) throws PopbillException {

        HttpURLConnection httpURLConnection;
        try {
            URL uri = new URL(getServiceURL() + url);

            if (ProxyIP != null && ProxyPort != null) {
                Proxy prx = new Proxy(Type.HTTP, new InetSocketAddress(ProxyIP, ProxyPort));
                httpURLConnection = (HttpURLConnection) uri.openConnection(prx);
            } else {
                httpURLConnection = (HttpURLConnection) uri.openConnection();
            }

        } catch (Exception e) {
            throw new PopbillException(-99999999, "팝빌 API 서버 접속 실패", e);
        }

        httpURLConnection.setConnectTimeout(10 * 1000);
        httpURLConnection.setReadTimeout(180 * 1000);

        if (CorpNum != null && CorpNum.isEmpty() == false) {
            httpURLConnection.setRequestProperty("Authorization", "Bearer " + getSessionToken(CorpNum, null));
        }

        httpURLConnection.setRequestProperty("x-pb-version".toLowerCase(), APIVersion);

        if (Action != null && Action.isEmpty() == false) {
            httpURLConnection.setRequestProperty("X-HTTP-Method-Override", Action);
        }

        if (ContentType != null && ContentType.isEmpty() == false) {
            httpURLConnection.setRequestProperty("Content-Type", ContentType);
        } else {
            httpURLConnection.setRequestProperty("Content-Type", "application/json; charset=utf8");
        }

        httpURLConnection.setRequestProperty("Accept-Encoding", "gzip");

        if (UserID != null && UserID.isEmpty() == false) {
            httpURLConnection.setRequestProperty("x-pb-userid", UserID);
        }

        checkCustomHeaderValidation(httpURLConnection);

        try {
            httpURLConnection.setRequestMethod("POST");
        } catch (ProtocolException e1) {
        }

        httpURLConnection.setUseCaches(false);
        httpURLConnection.setDoOutput(true);

        if ((PostData == null || PostData.isEmpty()) == false) {

            byte[] btPostData = PostData.getBytes(Charset.forName("UTF-8"));

            if ("application/lh-encrypted".equalsIgnoreCase(ContentType)) {

                try {
                    Encryptor pb_enc = Encryptor.getInstance(this.mleKeyID, this.mleKeyName, this.mlePublicKey);
                    btPostData = pb_enc.Encrypt(btPostData);
                } catch (PopbillException PE) {
                    throw PE;
                } catch (Exception E) {
                    throw new PopbillException(-99999999, "Message Level Encryption is failed.", E);
                }
            }

            httpURLConnection.setRequestProperty("Content-Length", String.valueOf(btPostData.length));

            DataOutputStream output = null;

            try {
                output = new DataOutputStream(httpURLConnection.getOutputStream());
                output.write(btPostData);
                output.flush();
            } catch (Exception e) {
                throw new PopbillException(-99999999, "Fail to POST data to Server.", e);
            } finally {
                try {
                    if (output != null) {
                        output.close();
                    }
                } catch (IOException e1) {
                    throw new PopbillException(-99999999, "Popbill httppost func DataOutputStream close() Exception",
                            e1);
                }
            }
        }

        String Result = parseResponse(httpURLConnection);

        return fromJsonString(Result, clazz);
    }

    private static final String boundary = "--u489jwe98j3498j394r23450--";
    private static final String CRLF = "\r\n";

    /**
     * @param url
     * @param CorpNum
     * @param form
     * @param files
     * @param UserID
     * @param clazz
     * @return returned object
     * @throws PopbillException
     */
    protected <T> T httppostFiles(String url, String CorpNum, String form, List<UploadFile> files, String UserID,
                                  Class<T> clazz) throws PopbillException {

        HttpURLConnection httpURLConnection;
        try {
            URL uri = new URL(getServiceURL() + url);

            if (ProxyIP != null && ProxyPort != null) {
                Proxy prx = new Proxy(Type.HTTP, new InetSocketAddress(ProxyIP, ProxyPort));
                httpURLConnection = (HttpURLConnection) uri.openConnection(prx);
            } else {
                httpURLConnection = (HttpURLConnection) uri.openConnection();
            }

        } catch (Exception e) {
            throw new PopbillException(-99999999, "팝빌 API 서버 접속 실패", e);
        }

        httpURLConnection.setConnectTimeout(10 * 1000);
        httpURLConnection.setReadTimeout(180 * 1000);

        if (CorpNum != null && CorpNum.isEmpty() == false) {
            httpURLConnection.setRequestProperty("Authorization", "Bearer " + getSessionToken(CorpNum, null));
        }

        httpURLConnection.setRequestProperty("x-pb-version".toLowerCase(), APIVersion);
        httpURLConnection.setRequestProperty("Connection", "Keep-Alive");
        httpURLConnection.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);

        if (UserID != null && UserID.isEmpty() == false) {
            httpURLConnection.setRequestProperty("x-pb-userid", UserID);
        }

        httpURLConnection.setRequestProperty("Accept-Encoding", "gzip");

        checkCustomHeaderValidation(httpURLConnection);

        try {
            httpURLConnection.setRequestMethod("POST");
        } catch (ProtocolException e1) {

        }

        httpURLConnection.setUseCaches(false);
        httpURLConnection.setDoOutput(true);

        DataOutputStream output = null;

        try {

            output = new DataOutputStream(httpURLConnection.getOutputStream());

            if ((form == null || form.isEmpty()) == false) {
                String formBody = "--" + boundary + CRLF;
                formBody += "content-disposition: form-data; name=\"form\"" + CRLF;
                formBody += "content-type: Application/json; charset=utf-8" + CRLF + CRLF;
                formBody += form + "\r\n";
                byte[] btFormBody = formBody.getBytes(Charset.forName("UTF-8"));

                output.write(btFormBody);
            }

            for (UploadFile f : files) {
                String fileHeader = "--" + boundary + CRLF;
                fileHeader += "content-disposition: form-data; name=\"" + f.fieldName + "\"; filename=\"" + f.fileName
                        + "\"" + CRLF;
                fileHeader += "content-type: Application/octet-stream" + CRLF + CRLF;

                byte[] btFileHeader = fileHeader.getBytes(Charset.forName("UTF-8"));

                output.write(btFileHeader);

                byte[] buffer = new byte[32768];
                int read;
                while ((read = f.fileData.read(buffer, 0, buffer.length)) > 0) {
                    output.write(buffer, 0, read);
                }

                output.write(CRLF.getBytes(Charset.forName("UTF-8")));
            }

            String boundaryFooter = "--" + boundary + "--" + CRLF;
            byte[] btboundaryFooter = boundaryFooter.getBytes(Charset.forName("UTF-8"));

            output.write(btboundaryFooter);
            output.flush();
        } catch (Exception e) {
            throw new PopbillException(-99999999, "Fail to POST data to Server.", e);
        } finally {
            try {
                if (output != null) {
                    output.close();
                }
            } catch (IOException e1) {
                throw new PopbillException(-99999999, "Popbill httppostFiles func DataOutputStream close() Exception",
                        e1);
            }
        }

        String Result = parseResponse(httpURLConnection);

        return fromJsonString(Result, clazz);
    }

    private void checkCustomHeaderValidation(HttpURLConnection httpURLConnection) throws PopbillException {
        if (getCustomHeader() != null && getCustomHeader().size() > 0) {
            for (String customHeader : getCustomHeader().keySet()) {
                for (String apiHeader : apiHeaderList) {
                    if (customHeader.toLowerCase().equals(apiHeader.toLowerCase())) {
                        throw new PopbillException(-99999999, "허용되지 않은 Custom Header 입니다." + "[" + customHeader + "]");
                    } else if ("x-pb".equals(customHeader.toLowerCase().substring(0, 4)) || "x-lh".equals(customHeader.toLowerCase().substring(0, 4))
                            || "x-bc".equals(customHeader.toLowerCase().substring(0, 4))) {
                        throw new PopbillException(-99999999, "허용되지 않은 Custom Header 입니다." + "[" + customHeader + "]");
                    }
                }

                httpURLConnection.setRequestProperty(customHeader, getCustomHeader().get(customHeader));
            }
        }
    }


    /**
     * @param url
     * @param CorpNum
     * @param UserID
     * @param clazz
     * @return returned object
     * @throws PopbillException
     */
    protected <T> T httpget(String url, String CorpNum, String UserID, Class<T> clazz) throws PopbillException {
        HttpURLConnection httpURLConnection;

        try {
            URL uri = new URL(getServiceURL() + url);

            if (ProxyIP != null && ProxyPort != null) {
                Proxy prx = new Proxy(Type.HTTP, new InetSocketAddress(ProxyIP, ProxyPort));
                httpURLConnection = (HttpURLConnection) uri.openConnection(prx);
            } else {
                httpURLConnection = (HttpURLConnection) uri.openConnection();
            }

        } catch (Exception e) {
            throw new PopbillException(-99999999, "팝빌 API 서버 접속 실패", e);
        }

        if (!ValidationUtils.isNullOrEmpty(CorpNum)) {
            httpURLConnection.setRequestProperty("Authorization", "Bearer " + getSessionToken(CorpNum, null));
        }

        httpURLConnection.setConnectTimeout(10 * 1000);
        httpURLConnection.setReadTimeout(180 * 1000);

        httpURLConnection.setRequestProperty("x-pb-version".toLowerCase(), APIVersion);

        if (!ValidationUtils.isNullOrEmpty(UserID)) {
            httpURLConnection.setRequestProperty("x-pb-userid", UserID);
        }

        httpURLConnection.setRequestProperty("Accept-Encoding", "gzip");

        checkCustomHeaderValidation(httpURLConnection);

        if (httpURLConnection.getContentType()
                .toLowerCase()
                .equals("application/pdf;charset=utf-8")) {

            byte[] ResultArray = parseResponseByte(httpURLConnection);

            return clazz.cast(ResultArray);

        } else {

            String Result = parseResponse(httpURLConnection);

            return fromJsonString(Result, clazz);
        }

    }

    protected abstract List<String> getScopes();

    private class ErrorResponse {

        private long code;
        private String message;

        public long getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }

    }

    protected class UnitCostResponse {

        public float unitCost;

    }

    protected class UploadFile {
        public UploadFile() {
        }

        public String fieldName;
        public String fileName;
        public InputStream fileData;
    }

    protected class URLResponse {
        public String url;
    }

    private static String fromStream(InputStream input) throws PopbillException {
        InputStreamReader is = null;
        BufferedReader br = null;
        StringBuilder sb = null;

        try {
            is = new InputStreamReader(input, Charset.forName("UTF-8"));
            br = new BufferedReader(is);
            sb = new StringBuilder();

            String read = br.readLine();

            while (read != null) {
                sb.append(read);
                read = br.readLine();
            }

        } catch (IOException e) {
            throw new PopbillException(-99999999, "Popbill fromStream func Exception", e);
        } finally {
            try {
                if (br != null)
                    br.close();
                if (is != null)
                    is.close();
            } catch (IOException e) {
                throw new PopbillException(-99999999, "Popbill fromStream func finally close Exception", e);
            }
        }

        return sb.toString();
    }

    private static String fromGzipStream(InputStream input) throws PopbillException {
        GZIPInputStream zipReader = null;
        InputStreamReader is = null;
        BufferedReader br = null;
        StringBuilder sb = null;

        try {
            zipReader = new GZIPInputStream(input);
            is = new InputStreamReader(zipReader, "UTF-8");
            br = new BufferedReader(is);
            sb = new StringBuilder();

            String read = br.readLine();

            while (read != null) {
                sb.append(read);
                read = br.readLine();
            }
        } catch (IOException e) {
            throw new PopbillException(-99999999, "Popbill fromGzipStream func Exception", e);
        } finally {
            try {
                if (br != null)
                    br.close();
                if (is != null)
                    is.close();
                if (zipReader != null)
                    zipReader.close();
            } catch (IOException e) {
                throw new PopbillException(-99999999, "Popbill fromGzipStream func finally close Exception", e);
            }
        }

        return sb.toString();
    }

    private String parseResponse(HttpURLConnection httpURLConnection) throws PopbillException {

        String result = "";
        InputStream input = null;
        PopbillException exception = null;

        try {
            input = httpURLConnection.getInputStream();

            if (null != httpURLConnection.getContentEncoding()
                    && httpURLConnection.getContentEncoding()
                    .equals("gzip")) {
                result = fromGzipStream(input);
            } else {
                result = fromStream(input);
            }
        } catch (IOException e) {
            InputStream errorIs = null;
            ErrorResponse error = null;

            try {
                errorIs = httpURLConnection.getErrorStream();

                if (null != httpURLConnection.getContentEncoding()
                        && httpURLConnection.getContentEncoding()
                        .equals("gzip")) {
                    result = fromGzipStream(errorIs);
                } else {
                    result = fromStream(errorIs);
                }

                error = fromJsonString(result, ErrorResponse.class);
            } catch (Exception ignored) {

            } finally {
                try {
                    if (errorIs != null) {
                        errorIs.close();
                    }
                } catch (IOException e1) {
                    throw new PopbillException(-99999999, "Popbill parseResponse func InputStream close() Exception",
                            e1);
                }
            }

            if (error == null) {
                exception = new PopbillException(-99999999, "Fail to receive data from Server.", e);
            } else {
                exception = new PopbillException(error.getCode(), error.getMessage());
            }
        } finally {
            try {
                if (input != null) {
                    input.close();
                }
            } catch (IOException e2) {
                throw new PopbillException(-99999999, "Popbill parseResponse func InputStream close() Exception", e2);
            }
        }

        if (exception != null)
            throw exception;

        return result;
    }

    private byte[] parseResponseByte(HttpURLConnection httpURLConnection) throws PopbillException {

        byte[] result = null;
        InputStream input = null;
        PopbillException exception = null;
        String errorResult = null;

        try {
            input = httpURLConnection.getInputStream();
            if (null != httpURLConnection.getContentEncoding()
                    && httpURLConnection.getContentEncoding()
                    .equals("gzip")) {
                result = fromGzipStreamByte(input);
            } else {
                result = fromStreamByte(input);
            }
        } catch (IOException e) {
            InputStream errorIs = null;
            ErrorResponse error = null;
            try {
                errorIs = httpURLConnection.getErrorStream();

                if (null != httpURLConnection.getContentEncoding()
                        && httpURLConnection.getContentEncoding()
                        .equals("gzip")) {
                    errorResult = fromGzipStream(errorIs);
                } else {
                    errorResult = fromStream(errorIs);
                }

                error = fromJsonString(errorResult, ErrorResponse.class);

            } catch (Exception ignored) {

            } finally {
                try {
                    if (errorIs != null) {
                        errorIs.close();
                    }
                } catch (IOException e1) {
                    throw new PopbillException(-99999999,
                            "Popbill parseResponseByte func InputStream close() Exception", e1);
                }
            }

            if (error == null) {
                exception = new PopbillException(-99999999, "Fail to receive data from Server.", e);
            } else {
                exception = new PopbillException(error.getCode(), error.getMessage());
            }
        } finally {
            try {
                if (input != null) {
                    input.close();
                }
            } catch (IOException e2) {
                throw new PopbillException(-99999999, "Popbill parseResponseByte func InputStream close() Exception",
                        e2);
            }
        }

        if (exception != null)
            throw exception;

        return result;
    }

    private byte[] fromStreamByte(InputStream input) throws PopbillException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        byte[] buffer = new byte[1024];

        int nRead = 0;

        try {
            while ((nRead = input.read(buffer)) > 0) {
                byteArrayOutputStream.write(buffer, 0, nRead);
            }
        } catch (IOException e) {
            throw new PopbillException(-99999999, "Popbill fromStreamByte func Exception", e);
        } finally {
            try {
                if (byteArrayOutputStream != null)
                    byteArrayOutputStream.close();
            } catch (IOException e) {
                throw new PopbillException(-99999999, "Popbill fromStreamByte func finally close Exception", e);
            }

        }

        byte[] Result = byteArrayOutputStream.toByteArray();

        return Result;
    }

    private byte[] fromGzipStreamByte(InputStream input) throws PopbillException {
        GZIPInputStream zipReader = null;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        byte[] buffer = new byte[1024];

        int nRead = 0;

        try {
            zipReader = new GZIPInputStream(input);

            while ((nRead = zipReader.read(buffer)) > 0) {
                byteArrayOutputStream.write(buffer, 0, nRead);
            }

        } catch (IOException e) {
            throw new PopbillException(-99999999, "Popbill fromGzipStreamByte func Exception", e);
        } finally {
            try {
                if (zipReader != null)
                    zipReader.close();
                if (byteArrayOutputStream != null)
                    byteArrayOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
                throw new PopbillException(-99999999, "Popbill fromGzipStreamByte func finally close Exception", e);
            }
        }

        byte[] Result = byteArrayOutputStream.toByteArray();

        return Result;
    }

}
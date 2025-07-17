package com.popbill.api.easyfin;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;

import com.popbill.api.BaseServiceImp;
import com.popbill.api.ChargeInfo;
import com.popbill.api.EasyFinBankService;
import com.popbill.api.FlatRateState;
import com.popbill.api.PopbillException;
import com.popbill.api.Response;
import com.popbill.api.util.ValidationUtils;

public class EasyFinBankServiceImp extends BaseServiceImp implements EasyFinBankService {

    @Override
    protected List<String> getScopes() {

        return Arrays.asList("180");
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.popbill.api.EasyFinBankService#getBankAccountMgtURL(java.lang.String)
     */
    @Override
    public String getBankAccountMgtURL(String CorpNum) throws PopbillException {

        return getBankAccountMgtURL(CorpNum, null);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.popbill.api.EasyFinBankService#getBankAccountMgtURL(java.lang.String,
     * java.lang.String)
     */
    @Override
    public String getBankAccountMgtURL(String CorpNum, String UserID) throws PopbillException {
        URLResponse response = httpget("/EasyFin/Bank?TG=BankAccount", CorpNum, UserID, URLResponse.class);

        return response.url;
    }

    @Override
    public Response registBankAccount(String CorpNum, EasyFinBankAccountForm AccountForm) throws PopbillException {

        return registBankAccount(CorpNum, AccountForm, null);
    }

    @Override
    public Response registBankAccount(String CorpNum, EasyFinBankAccountForm AccountForm, String UserID)
            throws PopbillException {

        if (AccountForm == null)
            throw new PopbillException(-99999999, "계좌 정보가 입력되지 않았습니다.");

        String uri = "/EasyFin/Bank/BankAccount/Regist";

        if (AccountForm.getUsePeriod() != null)
            uri += "?UsePeriod=" + AccountForm.getUsePeriod();

        String PostData = toJsonString(AccountForm);

        return httppost(uri, CorpNum, PostData, UserID, Response.class);
    }

    @Override
    public Response closeBankAccount(String CorpNum, String BankCode, String AccountNumber, String CloseType)
            throws PopbillException {

        return closeBankAccount(CorpNum, BankCode, AccountNumber, CloseType, null);
    }

    @Override
    public Response closeBankAccount(String CorpNum, String BankCode, String AccountNumber, String CloseType,
                                     String UserID) throws PopbillException {
        String uri = "/EasyFin/Bank/BankAccount/Close";

        uri += "?BankCode=" + BankCode;
        uri += "&AccountNumber=" + AccountNumber;
        uri += "&CloseType=" + CloseType;

        return httppost(uri, CorpNum, null, UserID, Response.class);
    }

    @Override
    public Response revokeCloseBankAccount(String CorpNum, String BankCode, String AccountNumber)
            throws PopbillException {
        return revokeCloseBankAccount(CorpNum, BankCode, AccountNumber, null);
    }

    @Override
    public Response revokeCloseBankAccount(String CorpNum, String BankCode, String AccountNumber, String UserID)
            throws PopbillException {
        String uri = "/EasyFin/Bank/BankAccount/RevokeClose";

        uri += "?BankCode=" + BankCode;
        uri += "&AccountNumber=" + AccountNumber;

        return httppost(uri, CorpNum, null, UserID, Response.class);
    }

    @Override
    public Response deleteBankAccount(String CorpNum, String BankCode, String AccountNumber) throws PopbillException {

        return deleteBankAccount(CorpNum, BankCode, AccountNumber, null);
    }

    @Override
    public Response deleteBankAccount(String CorpNum, String BankCode, String AccountNumber, String UserID)
            throws PopbillException {
        if (ValidationUtils.isNullOrEmpty(BankCode))
            throw new PopbillException(-99999999, "기관코드가 입력되지 않았습니다.");

        if (ValidationUtils.isNullOrEmpty(AccountNumber))
            throw new PopbillException(-99999999, "계좌번호가 입력되지 않았습니다.");

        String uri = "/EasyFin/Bank/BankAccount/Delete";

        String PostData = "{'BankCode':" + BankCode + ", 'AccountNumber':" + AccountNumber + "}";

        return httppost(uri, CorpNum, PostData, UserID, Response.class);
    }

    @Override
    public Response updateBankAccount(String CorpNum, EasyFinBankAccountForm AccountForm) throws PopbillException {
        return updateBankAccount(CorpNum, AccountForm, null);
    }

    @Override
    public Response updateBankAccount(String CorpNum, EasyFinBankAccountForm AccountForm, String UserID)
            throws PopbillException {

        if (AccountForm == null)
            throw new PopbillException(-99999999, "계좌 정보가 입력되지 않았습니다.");

        if (ValidationUtils.isNullOrEmpty(AccountForm.getBankCode()))
            throw new PopbillException(-99999999, "기관코드가 입력되지 않았습니다.");

        if (ValidationUtils.isNullOrEmpty(AccountForm.getAccountNumber()))
            throw new PopbillException(-99999999, "계좌번호가 입력되지 않았습니다.");

        String uri = "/EasyFin/Bank/BankAccount/" + AccountForm.getBankCode() + "/" + AccountForm.getAccountNumber() + "/Update";

        String PostData = toJsonString(AccountForm);

        return httppost(uri, CorpNum, PostData, UserID, Response.class);
    }

    @Override
    public Response updateBankAccount(String CorpNum, String BankCode, String AccountNumber, UpdateEasyFinBankAccountForm BankAccountInfo) throws PopbillException {
        return updateBankAccount(CorpNum, BankCode, AccountNumber, BankAccountInfo, null);
    }

    @Override
    public Response updateBankAccount(String CorpNum, String BankCode, String AccountNumber, UpdateEasyFinBankAccountForm BankAccountInfo, String UserID) throws PopbillException {

        if(ValidationUtils.isNullOrEmpty(BankCode))
            throw new PopbillException(-99999999, "기관코드가 입력되지 않았습니다.");

        if(ValidationUtils.isNullOrEmpty(AccountNumber))
            throw new PopbillException(-99999999, "계좌번호가 입력되지 않았습니다.");

        String uri = "/EasyFin/Bank/BankAccount/" + BankCode + "/" + AccountNumber + "/Update";

        String PostData = toJsonString(BankAccountInfo);

        return httppost(uri, CorpNum, PostData, UserID, Response.class);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.popbill.api.EasyFinBankService#getBankAccountInfo(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public EasyFinBankAccount getBankAccountInfo(String CorpNum, String BankCode, String AccountNumber)
            throws PopbillException {

        return getBankAccountInfo(CorpNum, BankCode, AccountNumber, null);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.popbill.api.EasyFinBankService#getBankAccountInfo(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public EasyFinBankAccount getBankAccountInfo(String CorpNum, String BankCode, String AccountNumber, String UserID)
            throws PopbillException {
        if (ValidationUtils.isNullOrEmpty(BankCode))
            throw new PopbillException(-99999999, "기관코드가 입력되지 않았습니다.");

        if (ValidationUtils.isNullOrEmpty(AccountNumber))
            throw new PopbillException(-99999999, "계좌번호가 입력되지 않았습니다.");

        return httpget("/EasyFin/Bank/BankAccount/" + BankCode + "/" + AccountNumber, CorpNum, UserID,
                EasyFinBankAccount.class);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.popbill.api.EasyFinBankService#listBankAccount(java.lang.String)
     */
    @Override
    public EasyFinBankAccount[] listBankAccount(String CorpNum) throws PopbillException {
        return listBankAccount(CorpNum, null);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.popbill.api.EasyFinBankService#listBankAccount(java.lang.String, java.lang.String)
     */
    @Override
    public EasyFinBankAccount[] listBankAccount(String CorpNum, String UserID) throws PopbillException {
        return httpget("/EasyFin/Bank/ListBankAccount", CorpNum, UserID, EasyFinBankAccount[].class);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.popbill.api.EasyFinBankService#requestJob(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public String requestJob(String CorpNum, String BankCode, String AccountNumber, String SDate, String EDate)
            throws PopbillException {
        return requestJob(CorpNum, BankCode, AccountNumber, SDate, EDate, null);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.popbill.api.EasyFinBankService#requestJob(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public String requestJob(String CorpNum, String BankCode, String AccountNumber, String SDate, String EDate,
                             String UserID) throws PopbillException {
        JobIDResponse response = httppost("/EasyFin/Bank/BankAccount?AccountNumber=" + AccountNumber + "&BankCode="
                + BankCode + "&SDate=" + SDate + "&EDate=" + EDate, CorpNum, null, UserID, JobIDResponse.class);

        return response.jobID;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.popbill.api.EasyFinBankService#getJobState(java.lang.String, java.lang.String)
     */
    @Override
    public EasyFinBankJobState getJobState(String CorpNum, String JobID) throws PopbillException {
        return getJobState(CorpNum, JobID, null);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.popbill.api.EasyFinBankService#getJobState(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public EasyFinBankJobState getJobState(String CorpNum, String JobID, String UserID) throws PopbillException {
        if (ValidationUtils.isNullOrEmpty(JobID))
            throw new PopbillException(-99999999, "작업아이디가 입력되지 않았습니다.");

        return httpget("/EasyFin/Bank/" + JobID + "/State", CorpNum, UserID, EasyFinBankJobState.class);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.popbill.api.EasyFinBankService#listActiveJob(java.lang.String)
     */
    @Override
    public EasyFinBankJobState[] listActiveJob(String CorpNum) throws PopbillException {
        return listActiveJob(CorpNum, null);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.popbill.api.EasyFinBankService#listActiveJob(java.lang.String, java.lang.String)
     */
    @Override
    public EasyFinBankJobState[] listActiveJob(String CorpNum, String UserID) throws PopbillException {
        return httpget("/EasyFin/Bank/JobList", CorpNum, UserID, EasyFinBankJobState[].class);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.popbill.api.EasyFinBankService#search(java.lang.String, java.lang.String, java.lang.String[], java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.String)
     */
    @Override
    public EasyFinBankSearchResult search(String CorpNum, String JobID, String[] TradeType, String SearchString,
                                          Integer Page, Integer PerPage, String Order) throws PopbillException {
        return search(CorpNum, JobID, TradeType, SearchString, Page, PerPage, Order, null);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.popbill.api.EasyFinBankService#search(java.lang.String, java.lang.String, java.lang.String[], java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.String)
     */
    @Override
    public EasyFinBankSearchResult search(String CorpNum, String JobID, String[] TradeType, String SearchString,
                                          Integer Page, Integer PerPage, String Order, String UserID) throws PopbillException {

        if (ValidationUtils.isNullOrEmpty(JobID))
            throw new PopbillException(-99999999, "작업아이디가 입력되지 않았습니다.");

        String uri = "/EasyFin/Bank/" + JobID + "?TradeType=";

        if (!ValidationUtils.isNullOrEmpty(TradeType))
            uri += ValidationUtils.replaceInvalidUriChars(TradeType);

        if (!ValidationUtils.isNullOrEmpty(SearchString)) {
            try {
                uri += "&SearchString=" + URLEncoder.encode(SearchString, "UTF-8");
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

        return httpget(uri, CorpNum, UserID, EasyFinBankSearchResult.class);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.popbill.api.EasyFinBankService#summary(java.lang.String, java.lang.String, java.lang.String[], java.lang.String)
     */
    @Override
    public EasyFinBankSummary summary(String CorpNum, String JobID, String[] TradeType, String SearchString)
            throws PopbillException {
        return summary(CorpNum, JobID, TradeType, SearchString, null);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.popbill.api.EasyFinBankService#summary(java.lang.String, java.lang.String, java.lang.String[], java.lang.String, java.lang.String)
     */
    @Override
    public EasyFinBankSummary summary(String CorpNum, String JobID, String[] TradeType, String SearchString,
                                      String UserID) throws PopbillException {
        if (ValidationUtils.isNullOrEmpty(JobID))
            throw new PopbillException(-99999999, "작업아이디가 입력되지 않았습니다.");

        String uri = "/EasyFin/Bank/" + JobID + "/Summary" + "?TradeType=";

        if (!ValidationUtils.isNullOrEmpty(TradeType))
            uri += ValidationUtils.replaceInvalidUriChars(TradeType);

        if (!ValidationUtils.isNullOrEmpty(SearchString)) {
            try {
                uri += "&SearchString=" + URLEncoder.encode(SearchString, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                throw new PopbillException(-99999999, "검색어 인코딩이 실패 되었습니다.");
            }
        }

        return httpget(uri, CorpNum, UserID, EasyFinBankSummary.class);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.popbill.api.EasyFinBankService#saveMemo(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public Response saveMemo(String CorpNum, String TID, String Memo) throws PopbillException {
        return saveMemo(CorpNum, TID, Memo, null);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.popbill.api.EasyFinBankService#saveMemo(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public Response saveMemo(String CorpNum, String TID, String Memo, String UserID) throws PopbillException {
        String uri = "/EasyFin/Bank/SaveMemo";

        uri += "?TID=" + TID;

        if (!ValidationUtils.isNullOrEmpty(Memo)) {
            try {
                uri += "&Memo=" + URLEncoder.encode(Memo, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                throw new PopbillException(-99999999, "메모 인코딩이 실패 되었습니다.");
            }
        }

        return httppost(uri, CorpNum, null, UserID, Response.class);

    }

    /*
     * (non-Javadoc)
     *
     * @see com.popbill.api.EasyFinBankService#getFlatRateState(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public FlatRateState getFlatRateState(String CorpNum, String BankCode, String AccountNumber)
            throws PopbillException {
        return getFlatRateState(CorpNum, BankCode, AccountNumber, null);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.popbill.api.EasyFinBankService#getFlatRateState(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public FlatRateState getFlatRateState(String CorpNum, String BankCode, String AccountNumber, String UserID)
            throws PopbillException {
        if (ValidationUtils.isNullOrEmpty(BankCode))
            throw new PopbillException(-99999999, "기관코드가 입력되지 않았습니다.");

        if (ValidationUtils.isNullOrEmpty(AccountNumber))
            throw new PopbillException(-99999999, "계좌번호가 입력되지 않았습니다.");

        return httpget("/EasyFin/Bank/Contract/" + BankCode + "/" + AccountNumber, CorpNum, UserID, FlatRateState.class);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.popbill.api.EasyFinBankService#getFlatRatePopUpURL(java.lang.String)
     */
    @Override
    public String getFlatRatePopUpURL(String CorpNum) throws PopbillException {

        return getFlatRatePopUpURL(CorpNum, null);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.popbill.api.EasyFinBankService#getFlatRatePopUpURL(java.lang.String, java.lang.String)
     */
    @Override
    public String getFlatRatePopUpURL(String CorpNum, String UserID) throws PopbillException {

        URLResponse response = httpget("/EasyFin/Bank?TG=CHRG", CorpNum, UserID, URLResponse.class);

        return response.url;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.popbill.api.EasyFinBankService#getChargeInfo(java.lang.String)
     */
    @Override
    public ChargeInfo getChargeInfo(String CorpNum) throws PopbillException {

        return getChargeInfo(CorpNum, null);
    }

    @Override
    public ChargeInfo getChargeInfo(String CorpNum, String UserID) throws PopbillException {
        return httpget("/EasyFin/Bank/ChargeInfo", CorpNum, UserID, ChargeInfo.class);
    }

    protected class JobIDResponse {
        public String jobID;
    }

    protected class TIDMemoRequest {
        public String tID;
        public String memo;
    }

}
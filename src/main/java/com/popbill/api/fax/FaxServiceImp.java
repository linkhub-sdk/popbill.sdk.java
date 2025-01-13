package com.popbill.api.fax;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.popbill.api.BaseServiceImp;
import com.popbill.api.ChargeInfo;
import com.popbill.api.FaxService;
import com.popbill.api.FaxUploadFile;
import com.popbill.api.PopbillException;
import com.popbill.api.Response;

/**
 * Implementation of Popbill FaxService Interface
 * 
 * @author KimSeongjun
 * @version 1.0.0
 * @see com.popbill.api.FaxService
 */
public class FaxServiceImp extends BaseServiceImp implements FaxService {

    @Override
    protected List<String> getScopes() {
        return Arrays.asList("160", "161");
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.FaxService#getUnitCost
     */
    @Override
    public float getUnitCost(String CorpNum) throws PopbillException {
        return getUnitCost(CorpNum, null, null);
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.FaxService#getUnitCost
     */
    @Override
    public float getUnitCost(String CorpNum, String ReceiveNumType) throws PopbillException {
        return getUnitCost(CorpNum, ReceiveNumType, null);
    }

    @Override
    public float getUnitCost(String CorpNum, String ReceiveNumType, String UserID) throws PopbillException {
        UnitCostResponse response = httpget("/FAX/UnitCost?receiveNumType=" + ReceiveNumType, CorpNum, UserID, UnitCostResponse.class);
        return response.unitCost;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.FaxService#getURL(java.lang.String, java.lang.String)
     */
    @Override
    public String getURL(String CorpNum, String TOGO) throws PopbillException {
        return getURL(CorpNum, null, TOGO);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.FaxService#getURL
     */
    @Override
    public String getURL(String CorpNum, String UserID, String TOGO) throws PopbillException {

        URLResponse response = httpget("/FAX/?TG=" + TOGO, CorpNum, UserID, URLResponse.class);

        return response.url;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.FaxService#sendFAX(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.io.File, java.util.Date)
     */
    @Override
    public String sendFAX(String CorpNum, String SendNum, String ReceiveNum, String ReceiveName, File File, Date ReserveDT) throws PopbillException {

        Receiver receiver = new Receiver();
        receiver.setReceiveNum(ReceiveNum);
        receiver.setReceiveName(ReceiveName);

        return requestFax(CorpNum, SendNum, null, new Receiver[] { receiver }, new File[] { File }, ReserveDT, null, null, null, null);
    }

    @Override
    public String sendFAXBinary(String CorpNum, String SendNum, String ReceiveNum, String ReceiveName, FaxUploadFile File, Date ReserveDT)
            throws PopbillException {

        Receiver receiver = new Receiver();
        receiver.setReceiveNum(ReceiveNum);
        receiver.setReceiveName(ReceiveName);

        return requestFaxBinary(CorpNum, SendNum, null, new Receiver[] { receiver }, new FaxUploadFile[] { File }, ReserveDT, null, null, null, null);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.FaxService#sendFAX(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.io.File, java.util.Date, java.lang.Boolean)
     */
    @Override
    public String sendFAX(String CorpNum, String SendNum, String ReceiveNum, String ReceiveName, File File, Date ReserveDT, Boolean AdsYN)
            throws PopbillException {

        Receiver receiver = null;

        receiver = new Receiver();
        receiver.setReceiveNum(ReceiveNum);
        receiver.setReceiveName(ReceiveName);

        return requestFax(CorpNum, SendNum, null, new Receiver[] { receiver }, new File[] { File }, ReserveDT, null, AdsYN, null, null);
    }

    @Override
    public String sendFAXBinary(String CorpNum, String SendNum, String ReceiveNum, String ReceiveName, FaxUploadFile File, Date ReserveDT,
            Boolean AdsYN) throws PopbillException {

        Receiver receiver = null;

        receiver = new Receiver();
        receiver.setReceiveNum(ReceiveNum);
        receiver.setReceiveName(ReceiveName);

        return requestFaxBinary(CorpNum, SendNum, null, new Receiver[] { receiver }, new FaxUploadFile[] { File }, ReserveDT, null, AdsYN, null,
                null);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.FaxService#sendFAX(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.io.File, java.util.Date, java.lang.Boolean, java.lang.String)
     */
    @Override
    public String sendFAX(String CorpNum, String SendNum, String ReceiveNum, String ReceiveName, File File, Date ReserveDT, Boolean AdsYN,
            String RequestNum) throws PopbillException {

        Receiver receiver = null;

        receiver = new Receiver();
        receiver.setReceiveNum(ReceiveNum);
        receiver.setReceiveName(ReceiveName);

        return requestFax(CorpNum, SendNum, null, new Receiver[] { receiver }, new File[] { File }, ReserveDT, null, AdsYN, null, RequestNum);
    }

    @Override
    public String sendFAXBinary(String CorpNum, String SendNum, String ReceiveNum, String ReceiveName, FaxUploadFile File, Date ReserveDT,
            Boolean AdsYN, String RequestNum) throws PopbillException {

        Receiver receiver = null;

        receiver = new Receiver();
        receiver.setReceiveNum(ReceiveNum);
        receiver.setReceiveName(ReceiveName);

        return requestFaxBinary(CorpNum, SendNum, null, new Receiver[] { receiver }, new FaxUploadFile[] { File }, ReserveDT, null, AdsYN, null,
                RequestNum);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.FaxService#sendFAX(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.io.File, java.util.Date, java.lang.String)
     */
    @Override
    public String sendFAX(String CorpNum, String SendNum, String ReceiveNum, String ReceiveName, File File, Date ReserveDT, String UserID)
            throws PopbillException {

        Receiver receiver = new Receiver();
        receiver.setReceiveNum(ReceiveNum);
        receiver.setReceiveName(ReceiveName);

        return requestFax(CorpNum, SendNum, null, new Receiver[] { receiver }, new File[] { File }, ReserveDT, UserID, null, null, null);
    }

    @Override
    public String sendFAXBinary(String CorpNum, String SendNum, String ReceiveNum, String ReceiveName, FaxUploadFile File, Date ReserveDT,
            String UserID) throws PopbillException {

        Receiver receiver = new Receiver();
        receiver.setReceiveNum(ReceiveNum);
        receiver.setReceiveName(ReceiveName);

        return requestFaxBinary(CorpNum, SendNum, null, new Receiver[] { receiver }, new FaxUploadFile[] { File }, ReserveDT, UserID, null, null,
                null);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.FaxService#sendFAX(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.io.File, java.util.Date, java.lang.String, java.lang.Boolean)
     */
    @Override
    public String sendFAX(String CorpNum, String SendNum, String ReceiveNum, String ReceiveName, File File, Date ReserveDT, String UserID,
            Boolean AdsYN) throws PopbillException {

        Receiver receiver = new Receiver();
        receiver.setReceiveNum(ReceiveNum);
        receiver.setReceiveName(ReceiveName);

        return requestFax(CorpNum, SendNum, null, new Receiver[] { receiver }, new File[] { File }, ReserveDT, UserID, AdsYN, null, null);
    }

    @Override
    public String sendFAXBinary(String CorpNum, String SendNum, String ReceiveNum, String ReceiveName, FaxUploadFile File, Date ReserveDT,
            String UserID, Boolean AdsYN) throws PopbillException {

        Receiver receiver = new Receiver();
        receiver.setReceiveNum(ReceiveNum);
        receiver.setReceiveName(ReceiveName);

        return requestFaxBinary(CorpNum, SendNum, null, new Receiver[] { receiver }, new FaxUploadFile[] { File }, ReserveDT, UserID, AdsYN, null,
                null);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.FaxService#sendFAX(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.io.File, java.util.Date, java.lang.String, java.lang.Boolean, java.lang.String)
     */
    @Override
    public String sendFAX(String CorpNum, String SendNum, String ReceiveNum, String ReceiveName, File File, Date ReserveDT, String UserID,
            Boolean AdsYN, String Title) throws PopbillException {

        Receiver receiver = new Receiver();
        receiver.setReceiveNum(ReceiveNum);
        receiver.setReceiveName(ReceiveName);

        return requestFax(CorpNum, SendNum, null, new Receiver[] { receiver }, new File[] { File }, ReserveDT, UserID, AdsYN, Title, null);
    }

    @Override
    public String sendFAXBinary(String CorpNum, String SendNum, String ReceiveNum, String ReceiveName, FaxUploadFile File, Date ReserveDT,
            String UserID, Boolean AdsYN, String Title) throws PopbillException {

        Receiver receiver = new Receiver();
        receiver.setReceiveNum(ReceiveNum);
        receiver.setReceiveName(ReceiveName);

        return requestFaxBinary(CorpNum, SendNum, null, new Receiver[] { receiver }, new FaxUploadFile[] { File }, ReserveDT, UserID, AdsYN, Title,
                null);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.FaxService#sendFAX(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.io.File, java.util.Date, java.lang.String, java.lang.Boolean, java.lang.String, java.lang.String)
     */
    @Override
    public String sendFAX(String CorpNum, String SendNum, String ReceiveNum, String ReceiveName, File File, Date ReserveDT, String UserID,
            Boolean AdsYN, String Title, String RequestNum) throws PopbillException {

        Receiver receiver = new Receiver();
        receiver.setReceiveNum(ReceiveNum);
        receiver.setReceiveName(ReceiveName);

        return requestFax(CorpNum, SendNum, null, new Receiver[] { receiver }, new File[] { File }, ReserveDT, UserID, AdsYN, Title, RequestNum);
    }

    @Override
    public String sendFAXBinary(String CorpNum, String SendNum, String ReceiveNum, String ReceiveName, FaxUploadFile File, Date ReserveDT,
            String UserID, Boolean AdsYN, String Title, String RequestNum) throws PopbillException {

        Receiver receiver = new Receiver();
        receiver.setReceiveNum(ReceiveNum);
        receiver.setReceiveName(ReceiveName);

        return requestFaxBinary(CorpNum, SendNum, null, new Receiver[] { receiver }, new FaxUploadFile[] { File }, ReserveDT, UserID, AdsYN, Title,
                RequestNum);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.FaxService#sendFAX(java.lang.String, java.lang.String, com.popbill.api.fax.Receiver[], java.io.File, java.util.Date)
     */
    @Override
    public String sendFAX(String CorpNum, String SendNum, Receiver[] Receivers, File File, Date ReserveDT) throws PopbillException {

        return requestFax(CorpNum, SendNum, null, Receivers, new File[] { File }, ReserveDT, null, null, null, null);
    }

    @Override
    public String sendFAXBinary(String CorpNum, String SendNum, Receiver[] Receivers, FaxUploadFile File, Date ReserveDT) throws PopbillException {

        return requestFaxBinary(CorpNum, SendNum, null, Receivers, new FaxUploadFile[] { File }, ReserveDT, null, null, null, null);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.FaxService#sendFAX(java.lang.String, java.lang.String, com.popbill.api.fax.Receiver[], java.io.File, java.util.Date, java.lang.Boolean)
     */
    @Override
    public String sendFAX(String CorpNum, String SendNum, Receiver[] Receivers, File File, Date ReserveDT, Boolean AdsYN) throws PopbillException {

        return requestFax(CorpNum, SendNum, null, Receivers, new File[] { File }, ReserveDT, null, AdsYN, null, null);
    }

    @Override
    public String sendFAXBinary(String CorpNum, String SendNum, Receiver[] Receivers, FaxUploadFile File, Date ReserveDT, Boolean AdsYN)
            throws PopbillException {

        return requestFaxBinary(CorpNum, SendNum, null, Receivers, new FaxUploadFile[] { File }, ReserveDT, null, AdsYN, null, null);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.FaxService#sendFAX(java.lang.String, java.lang.String, com.popbill.api.fax.Receiver[], java.io.File, java.util.Date, java.lang.Boolean, java.lang.String)
     */
    @Override
    public String sendFAX(String CorpNum, String SendNum, Receiver[] Receivers, File File, Date ReserveDT, Boolean AdsYN, String Title)
            throws PopbillException {

        return requestFax(CorpNum, SendNum, null, Receivers, new File[] { File }, ReserveDT, null, AdsYN, Title, null);
    }

    @Override
    public String sendFAXBinary(String CorpNum, String SendNum, Receiver[] Receivers, FaxUploadFile File, Date ReserveDT, Boolean AdsYN, String Title)
            throws PopbillException {

        return requestFaxBinary(CorpNum, SendNum, null, Receivers, new FaxUploadFile[] { File }, ReserveDT, null, AdsYN, Title, null);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.FaxService#sendFAX(java.lang.String, java.lang.String, com.popbill.api.fax.Receiver[], java.io.File, java.util.Date, java.lang.Boolean, java.lang.String, java.lang.String)
     */
    @Override
    public String sendFAX(String CorpNum, String SendNum, Receiver[] Receivers, File File, Date ReserveDT, Boolean AdsYN, String Title,
            String RequestNum) throws PopbillException {

        return requestFax(CorpNum, SendNum, null, Receivers, new File[] { File }, ReserveDT, null, AdsYN, Title, RequestNum);
    }

    @Override
    public String sendFAXBinary(String CorpNum, String SendNum, Receiver[] Receivers, FaxUploadFile File, Date ReserveDT, Boolean AdsYN, String Title,
            String RequestNum) throws PopbillException {

        return requestFaxBinary(CorpNum, SendNum, null, Receivers, new FaxUploadFile[] { File }, ReserveDT, null, AdsYN, Title, RequestNum);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.FaxService#sendFAX(java.lang.String, java.lang.String, com.popbill.api.fax.Receiver[], java.io.File, java.util.Date, java.lang.String)
     */
    @Override
    public String sendFAX(String CorpNum, String SendNum, Receiver[] Receivers, File File, Date ReserveDT, String UserID) throws PopbillException {

        return requestFax(CorpNum, SendNum, null, Receivers, new File[] { File }, ReserveDT, UserID, null, null, null);
    }

    @Override
    public String sendFAXBinary(String CorpNum, String SendNum, Receiver[] Receivers, FaxUploadFile File, Date ReserveDT, String UserID)
            throws PopbillException {

        return requestFaxBinary(CorpNum, SendNum, null, Receivers, new FaxUploadFile[] { File }, ReserveDT, UserID, null, null, null);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.FaxService#sendFAX(java.lang.String, java.lang.String, com.popbill.api.fax.Receiver[], java.io.File, java.util.Date, java.lang.String, java.lang.String)
     */
    @Override
    public String sendFAX(String CorpNum, String SendNum, Receiver[] Receivers, File File, Date ReserveDT, String UserID, String Title)
            throws PopbillException {

        return requestFax(CorpNum, SendNum, null, Receivers, new File[] { File }, ReserveDT, UserID, false, Title, null);
    }

    @Override
    public String sendFAXBinary(String CorpNum, String SendNum, Receiver[] Receivers, FaxUploadFile File, Date ReserveDT, String UserID, String Title)
            throws PopbillException {

        return requestFaxBinary(CorpNum, SendNum, null, Receivers, new FaxUploadFile[] { File }, ReserveDT, UserID, false, Title, null);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.FaxService#sendFAX(java.lang.String, java.lang.String, com.popbill.api.fax.Receiver[], java.io.File, java.util.Date, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public String sendFAX(String CorpNum, String SendNum, Receiver[] Receivers, File File, Date ReserveDT, String UserID, String Title,
            String RequestNum) throws PopbillException {

        return requestFax(CorpNum, SendNum, null, Receivers, new File[] { File }, ReserveDT, UserID, false, Title, RequestNum);
    }

    @Override
    public String sendFAXBinary(String CorpNum, String SendNum, Receiver[] Receivers, FaxUploadFile File, Date ReserveDT, String UserID, String Title,
            String RequestNum) throws PopbillException {

        return requestFaxBinary(CorpNum, SendNum, null, Receivers, new FaxUploadFile[] { File }, ReserveDT, UserID, false, Title, RequestNum);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.FaxService#sendFAX(java.lang.String, java.lang.String, com.popbill.api.fax.Receiver[], java.io.File, java.util.Date, java.lang.String, java.lang.Boolean)
     */
    @Override
    public String sendFAX(String CorpNum, String SendNum, Receiver[] Receivers, File File, Date ReserveDT, String UserID, Boolean AdsYN)
            throws PopbillException {

        return requestFax(CorpNum, SendNum, null, Receivers, new File[] { File }, ReserveDT, UserID, AdsYN, null, null);
    }

    @Override
    public String sendFAXBinary(String CorpNum, String SendNum, Receiver[] Receivers, FaxUploadFile File, Date ReserveDT, String UserID,
            Boolean AdsYN) throws PopbillException {

        return requestFaxBinary(CorpNum, SendNum, null, Receivers, new FaxUploadFile[] { File }, ReserveDT, UserID, AdsYN, null, null);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.FaxService#sendFAX(java.lang.String, java.lang.String, com.popbill.api.fax.Receiver[], java.io.File, java.util.Date, java.lang.String, java.lang.Boolean, java.lang.String)
     */
    @Override
    public String sendFAX(String CorpNum, String SendNum, Receiver[] Receivers, File File, Date ReserveDT, String UserID, Boolean AdsYN, String Title)
            throws PopbillException {

        return requestFax(CorpNum, SendNum, null, Receivers, new File[] { File }, ReserveDT, UserID, AdsYN, Title, null);
    }

    @Override
    public String sendFAXBinary(String CorpNum, String SendNum, Receiver[] Receivers, FaxUploadFile File, Date ReserveDT, String UserID,
            Boolean AdsYN, String Title) throws PopbillException {

        return requestFaxBinary(CorpNum, SendNum, null, Receivers, new FaxUploadFile[] { File }, ReserveDT, UserID, AdsYN, Title, null);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.FaxService#sendFAX(java.lang.String, java.lang.String, com.popbill.api.fax.Receiver[], java.io.File, java.util.Date, java.lang.String, java.lang.Boolean, java.lang.String, java.lang.String)
     */
    @Override
    public String sendFAX(String CorpNum, String SendNum, Receiver[] Receivers, File File, Date ReserveDT, String UserID, Boolean AdsYN, String Title,
            String RequestNum) throws PopbillException {

        return requestFax(CorpNum, SendNum, null, Receivers, new File[] { File }, ReserveDT, UserID, AdsYN, Title, RequestNum);
    }

    @Override
    public String sendFAXBinary(String CorpNum, String SendNum, Receiver[] Receivers, FaxUploadFile File, Date ReserveDT, String UserID,
            Boolean AdsYN, String Title, String RequestNum) throws PopbillException {

        return requestFaxBinary(CorpNum, SendNum, null, Receivers, new FaxUploadFile[] { File }, ReserveDT, UserID, AdsYN, Title, RequestNum);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.FaxService#sendFAX(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.io.File[], java.util.Date)
     */
    @Override
    public String sendFAX(String CorpNum, String SendNum, String ReceiveNum, String ReceiveName, File[] Files, Date ReserveDT)
            throws PopbillException {

        Receiver receiver = new Receiver();
        receiver.setReceiveNum(ReceiveNum);
        receiver.setReceiveName(ReceiveName);

        return requestFax(CorpNum, SendNum, null, new Receiver[] { receiver }, Files, ReserveDT, null, null, null, null);
    }

    @Override
    public String sendFAXBinary(String CorpNum, String SendNum, String ReceiveNum, String ReceiveName, FaxUploadFile[] Files, Date ReserveDT)
            throws PopbillException {

        Receiver receiver = new Receiver();
        receiver.setReceiveNum(ReceiveNum);
        receiver.setReceiveName(ReceiveName);

        return requestFaxBinary(CorpNum, SendNum, null, new Receiver[] { receiver }, Files, ReserveDT, null, null, null, null);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.FaxService#sendFAX(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.io.File[], java.util.Date, java.lang.Boolean)
     */
    @Override
    public String sendFAX(String CorpNum, String SendNum, String ReceiveNum, String ReceiveName, File[] Files, Date ReserveDT, Boolean AdsYN)
            throws PopbillException {

        Receiver receiver = new Receiver();
        receiver.setReceiveNum(ReceiveNum);
        receiver.setReceiveName(ReceiveName);

        return requestFax(CorpNum, SendNum, null, new Receiver[] { receiver }, Files, ReserveDT, null, AdsYN, null, null);
    }

    @Override
    public String sendFAXBinary(String CorpNum, String SendNum, String ReceiveNum, String ReceiveName, FaxUploadFile[] Files, Date ReserveDT,
            Boolean AdsYN) throws PopbillException {

        Receiver receiver = new Receiver();
        receiver.setReceiveNum(ReceiveNum);
        receiver.setReceiveName(ReceiveName);

        return requestFaxBinary(CorpNum, SendNum, null, new Receiver[] { receiver }, Files, ReserveDT, null, AdsYN, null, null);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.FaxService#sendFAX(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.io.File[], java.util.Date, java.lang.Boolean, java.lang.String)
     */
    @Override
    public String sendFAX(String CorpNum, String SendNum, String ReceiveNum, String ReceiveName, File[] Files, Date ReserveDT, Boolean AdsYN,
            String Title) throws PopbillException {

        Receiver receiver = new Receiver();
        receiver.setReceiveNum(ReceiveNum);
        receiver.setReceiveName(ReceiveName);

        return requestFax(CorpNum, SendNum, null, new Receiver[] { receiver }, Files, ReserveDT, null, AdsYN, Title, null);
    }

    @Override
    public String sendFAXBinary(String CorpNum, String SendNum, String ReceiveNum, String ReceiveName, FaxUploadFile[] Files, Date ReserveDT,
            Boolean AdsYN, String Title) throws PopbillException {

        Receiver receiver = new Receiver();
        receiver.setReceiveNum(ReceiveNum);
        receiver.setReceiveName(ReceiveName);

        return requestFaxBinary(CorpNum, SendNum, null, new Receiver[] { receiver }, Files, ReserveDT, null, AdsYN, Title, null);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.FaxService#sendFAX(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.io.File[], java.util.Date, java.lang.Boolean, java.lang.String, java.lang.String)
     */
    @Override
    public String sendFAX(String CorpNum, String SendNum, String ReceiveNum, String ReceiveName, File[] Files, Date ReserveDT, Boolean AdsYN,
            String Title, String RequestNum) throws PopbillException {

        Receiver receiver = new Receiver();
        receiver.setReceiveNum(ReceiveNum);
        receiver.setReceiveName(ReceiveName);

        return requestFax(CorpNum, SendNum, null, new Receiver[] { receiver }, Files, ReserveDT, null, AdsYN, Title, RequestNum);
    }

    @Override
    public String sendFAXBinary(String CorpNum, String SendNum, String ReceiveNum, String ReceiveName, FaxUploadFile[] Files, Date ReserveDT,
            Boolean AdsYN, String Title, String RequestNum) throws PopbillException {

        Receiver receiver = new Receiver();
        receiver.setReceiveNum(ReceiveNum);
        receiver.setReceiveName(ReceiveName);

        return requestFaxBinary(CorpNum, SendNum, null, new Receiver[] { receiver }, Files, ReserveDT, null, AdsYN, Title, RequestNum);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.FaxService#sendFAX(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.io.File[], java.util.Date, java.lang.String)
     */
    @Override
    public String sendFAX(String CorpNum, String SendNum, String ReceiveNum, String ReceiveName, File[] Files, Date ReserveDT, String UserID)
            throws PopbillException {

        Receiver receiver = new Receiver();
        receiver.setReceiveNum(ReceiveNum);
        receiver.setReceiveName(ReceiveName);

        return requestFax(CorpNum, SendNum, null, new Receiver[] { receiver }, Files, ReserveDT, UserID, null, null, null);
    }

    @Override
    public String sendFAXBinary(String CorpNum, String SendNum, String ReceiveNum, String ReceiveName, FaxUploadFile[] Files, Date ReserveDT,
            String UserID) throws PopbillException {

        Receiver receiver = new Receiver();
        receiver.setReceiveNum(ReceiveNum);
        receiver.setReceiveName(ReceiveName);

        return requestFaxBinary(CorpNum, SendNum, null, new Receiver[] { receiver }, Files, ReserveDT, UserID, null, null, null);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.FaxService#sendFAX(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.io.File[], java.util.Date, java.lang.String, java.lang.String)
     */
    @Override
    public String sendFAX(String CorpNum, String SendNum, String ReceiveNum, String ReceiveName, File[] Files, Date ReserveDT, String UserID,
            String Title) throws PopbillException {

        Receiver receiver = new Receiver();
        receiver.setReceiveNum(ReceiveNum);
        receiver.setReceiveName(ReceiveName);

        return requestFax(CorpNum, SendNum, null, new Receiver[] { receiver }, Files, ReserveDT, UserID, false, Title, null);
    }

    @Override
    public String sendFAXBinary(String CorpNum, String SendNum, String ReceiveNum, String ReceiveName, FaxUploadFile[] Files, Date ReserveDT,
            String UserID, String Title) throws PopbillException {

        Receiver receiver = new Receiver();
        receiver.setReceiveNum(ReceiveNum);
        receiver.setReceiveName(ReceiveName);

        return requestFaxBinary(CorpNum, SendNum, null, new Receiver[] { receiver }, Files, ReserveDT, UserID, false, Title, null);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.FaxService#sendFAX(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.io.File[], java.util.Date, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public String sendFAX(String CorpNum, String SendNum, String ReceiveNum, String ReceiveName, File[] Files, Date ReserveDT, String UserID,
            String Title, String RequestNum) throws PopbillException {

        Receiver receiver = new Receiver();
        receiver.setReceiveNum(ReceiveNum);
        receiver.setReceiveName(ReceiveName);

        return requestFax(CorpNum, SendNum, null, new Receiver[] { receiver }, Files, ReserveDT, UserID, false, Title, RequestNum);
    }

    @Override
    public String sendFAXBinary(String CorpNum, String SendNum, String ReceiveNum, String ReceiveName, FaxUploadFile[] Files, Date ReserveDT,
            String UserID, String Title, String RequestNum) throws PopbillException {

        Receiver receiver = new Receiver();
        receiver.setReceiveNum(ReceiveNum);
        receiver.setReceiveName(ReceiveName);

        return requestFaxBinary(CorpNum, SendNum, null, new Receiver[] { receiver }, Files, ReserveDT, UserID, false, Title, RequestNum);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.FaxService#sendFAX(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.io.File[], java.util.Date, java.lang.String, java.lang.Boolean)
     */
    @Override
    public String sendFAX(String CorpNum, String SendNum, String ReceiveNum, String ReceiveName, File[] Files, Date ReserveDT, String UserID,
            Boolean AdsYN) throws PopbillException {

        Receiver receiver = new Receiver();
        receiver.setReceiveNum(ReceiveNum);
        receiver.setReceiveName(ReceiveName);

        return requestFax(CorpNum, SendNum, null, new Receiver[] { receiver }, Files, ReserveDT, UserID, AdsYN, null, null);
    }

    @Override
    public String sendFAXBinary(String CorpNum, String SendNum, String ReceiveNum, String ReceiveName, FaxUploadFile[] Files, Date ReserveDT,
            String UserID, Boolean AdsYN) throws PopbillException {

        Receiver receiver = new Receiver();
        receiver.setReceiveNum(ReceiveNum);
        receiver.setReceiveName(ReceiveName);

        return requestFaxBinary(CorpNum, SendNum, null, new Receiver[] { receiver }, Files, ReserveDT, UserID, AdsYN, null, null);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.FaxService#sendFAX(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.io.File[], java.util.Date, java.lang.String, java.lang.Boolean, java.lang.String)
     */
    @Override
    public String sendFAX(String CorpNum, String SendNum, String ReceiveNum, String ReceiveName, File[] Files, Date ReserveDT, String UserID,
            Boolean AdsYN, String Title) throws PopbillException {

        Receiver receiver = new Receiver();
        receiver.setReceiveNum(ReceiveNum);
        receiver.setReceiveName(ReceiveName);

        return requestFax(CorpNum, SendNum, null, new Receiver[] { receiver }, Files, ReserveDT, UserID, AdsYN, Title, null);
    }

    @Override
    public String sendFAXBinary(String CorpNum, String SendNum, String ReceiveNum, String ReceiveName, FaxUploadFile[] Files, Date ReserveDT,
            String UserID, Boolean AdsYN, String Title) throws PopbillException {

        Receiver receiver = new Receiver();
        receiver.setReceiveNum(ReceiveNum);
        receiver.setReceiveName(ReceiveName);

        return requestFaxBinary(CorpNum, SendNum, null, new Receiver[] { receiver }, Files, ReserveDT, UserID, AdsYN, Title, null);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.FaxService#sendFAX(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.io.File[], java.util.Date, java.lang.String, java.lang.Boolean, java.lang.String, java.lang.String)
     */
    @Override
    public String sendFAX(String CorpNum, String SendNum, String ReceiveNum, String ReceiveName, File[] Files, Date ReserveDT, String UserID,
            Boolean AdsYN, String Title, String RequestNum) throws PopbillException {

        Receiver receiver = new Receiver();
        receiver.setReceiveNum(ReceiveNum);
        receiver.setReceiveName(ReceiveName);

        return requestFax(CorpNum, SendNum, null, new Receiver[] { receiver }, Files, ReserveDT, UserID, AdsYN, Title, RequestNum);
    }

    @Override
    public String sendFAXBinary(String CorpNum, String SendNum, String ReceiveNum, String ReceiveName, FaxUploadFile[] Files, Date ReserveDT,
            String UserID, Boolean AdsYN, String Title, String RequestNum) throws PopbillException {

        Receiver receiver = new Receiver();
        receiver.setReceiveNum(ReceiveNum);
        receiver.setReceiveName(ReceiveName);

        return requestFaxBinary(CorpNum, SendNum, null, new Receiver[] { receiver }, Files, ReserveDT, UserID, AdsYN, Title, RequestNum);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.FaxService#sendFAX(java.lang.String, java.lang.String, com.popbill.api.fax.Receiver[], java.io.File[], java.util.Date, java.lang.String)
     */
    @Override
    public String sendFAX(String CorpNum, String SendNum, Receiver[] Receivers, File[] Files, Date ReserveDT, String UserID) throws PopbillException {

        return requestFax(CorpNum, SendNum, null, Receivers, Files, ReserveDT, UserID, null, null, null);
    }

    @Override
    public String sendFAXBinary(String CorpNum, String SendNum, Receiver[] Receivers, FaxUploadFile[] Files, Date ReserveDT, String UserID)
            throws PopbillException {

        return requestFaxBinary(CorpNum, SendNum, null, Receivers, Files, ReserveDT, UserID, null, null, null);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.FaxService#sendFAX(java.lang.String, java.lang.String, com.popbill.api.fax.Receiver[], java.io.File[], java.util.Date, java.lang.String, java.lang.String)
     */
    @Override
    public String sendFAX(String CorpNum, String SendNum, Receiver[] Receivers, File[] Files, Date ReserveDT, String UserID, String Title)
            throws PopbillException {

        return requestFax(CorpNum, SendNum, null, Receivers, Files, ReserveDT, UserID, false, Title, null);
    }

    @Override
    public String sendFAXBinary(String CorpNum, String SendNum, Receiver[] Receivers, FaxUploadFile[] Files, Date ReserveDT, String UserID,
            String Title) throws PopbillException {

        return requestFaxBinary(CorpNum, SendNum, null, Receivers, Files, ReserveDT, UserID, false, Title, null);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.FaxService#sendFAX(java.lang.String, java.lang.String, com.popbill.api.fax.Receiver[], java.io.File[], java.util.Date, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public String sendFAX(String CorpNum, String SendNum, Receiver[] Receivers, File[] Files, Date ReserveDT, String UserID, String Title,
            String reqestNum) throws PopbillException {

        return requestFax(CorpNum, SendNum, null, Receivers, Files, ReserveDT, UserID, false, Title, reqestNum);
    }

    @Override
    public String sendFAXBinary(String CorpNum, String SendNum, Receiver[] Receivers, FaxUploadFile[] Files, Date ReserveDT, String UserID,
            String Title, String reqestNum) throws PopbillException {

        return requestFaxBinary(CorpNum, SendNum, null, Receivers, Files, ReserveDT, UserID, false, Title, reqestNum);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.FaxService#sendFAX(java.lang.String, java.lang.String, com.popbill.api.fax.Receiver[], java.io.File[], java.util.Date, java.lang.String, java.lang.Boolean)
     */
    @Override
    public String sendFAX(String CorpNum, String SendNum, Receiver[] Receivers, File[] Files, Date ReserveDT, String UserID, Boolean AdsYN)
            throws PopbillException {

        return requestFax(CorpNum, SendNum, null, Receivers, Files, ReserveDT, UserID, AdsYN, null, null);
    }

    @Override
    public String sendFAXBinary(String CorpNum, String SendNum, Receiver[] Receivers, FaxUploadFile[] Files, Date ReserveDT, String UserID,
            Boolean AdsYN) throws PopbillException {

        return requestFaxBinary(CorpNum, SendNum, null, Receivers, Files, ReserveDT, UserID, AdsYN, null, null);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.FaxService#sendFAX(java.lang.String, java.lang.String, com.popbill.api.fax.Receiver[], java.io.File[], java.util.Date, java.lang.String, java.lang.Boolean, java.lang.String)
     */
    @Override
    public String sendFAX(String CorpNum, String SendNum, Receiver[] Receivers, File[] Files, Date ReserveDT, String UserID, Boolean AdsYN,
            String Title) throws PopbillException {

        return requestFax(CorpNum, SendNum, null, Receivers, Files, ReserveDT, UserID, AdsYN, Title, null);
    }

    @Override
    public String sendFAXBinary(String CorpNum, String SendNum, Receiver[] Receivers, FaxUploadFile[] Files, Date ReserveDT, String UserID,
            Boolean AdsYN, String Title) throws PopbillException {

        return requestFaxBinary(CorpNum, SendNum, null, Receivers, Files, ReserveDT, UserID, AdsYN, Title, null);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.FaxService#sendFAX(java.lang.String, java.lang.String, com.popbill.api.fax.Receiver[], java.io.File[], java.util.Date, java.lang.String, java.lang.Boolean, java.lang.String, java.lang.String)
     */
    @Override
    public String sendFAX(String CorpNum, String SendNum, Receiver[] Receivers, File[] Files, Date ReserveDT, String UserID, Boolean AdsYN,
            String Title, String RequestNum) throws PopbillException {

        return requestFax(CorpNum, SendNum, null, Receivers, Files, ReserveDT, UserID, AdsYN, Title, RequestNum);
    }

    @Override
    public String sendFAXBinary(String CorpNum, String SendNum, Receiver[] Receivers, FaxUploadFile[] Files, Date ReserveDT, String UserID,
            Boolean AdsYN, String Title, String RequestNum) throws PopbillException {

        return requestFaxBinary(CorpNum, SendNum, null, Receivers, Files, ReserveDT, UserID, AdsYN, Title, RequestNum);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.FaxService#sendFAX(java.lang.String, java.lang.String, java.lang.String, com.popbill.api.fax.Receiver[], java.io.File[], java.util.Date)
     */
    @Override
    public String sendFAX(String CorpNum, String SendNum, String SenderName, Receiver[] Receivers, File[] Files, Date ReserveDT)
            throws PopbillException {

        return requestFax(CorpNum, SendNum, SenderName, Receivers, Files, ReserveDT, null, null, null, null);
    }

    @Override
    public String sendFAXBinary(String CorpNum, String SendNum, String SenderName, Receiver[] Receivers, FaxUploadFile[] Files, Date ReserveDT)
            throws PopbillException {

        return requestFaxBinary(CorpNum, SendNum, SenderName, Receivers, Files, ReserveDT, null, null, null, null);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.FaxService#sendFAX(java.lang.String, java.lang.String, java.lang.String, com.popbill.api.fax.Receiver[], java.io.File[], java.util.Date, java.lang.Boolean)
     */
    @Override
    public String sendFAX(String CorpNum, String SendNum, String SenderName, Receiver[] Receivers, File[] Files, Date ReserveDT, Boolean AdsYN)
            throws PopbillException {
        return requestFax(CorpNum, SendNum, SenderName, Receivers, Files, ReserveDT, null, AdsYN, null, null);
    }

    @Override
    public String sendFAXBinary(String CorpNum, String SendNum, String SenderName, Receiver[] Receivers, FaxUploadFile[] Files, Date ReserveDT,
            Boolean AdsYN) throws PopbillException {
        return requestFaxBinary(CorpNum, SendNum, SenderName, Receivers, Files, ReserveDT, null, AdsYN, null, null);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.FaxService#sendFAX(java.lang.String, java.lang.String, java.lang.String, com.popbill.api.fax.Receiver[], java.io.File[], java.util.Date, java.lang.Boolean, java.lang.String)
     */
    @Override
    public String sendFAX(String CorpNum, String SendNum, String SenderName, Receiver[] Receivers, File[] Files, Date ReserveDT, Boolean AdsYN,
            String RequestNum) throws PopbillException {
        return requestFax(CorpNum, SendNum, SenderName, Receivers, Files, ReserveDT, null, AdsYN, null, RequestNum);
    }

    @Override
    public String sendFAXBinary(String CorpNum, String SendNum, String SenderName, Receiver[] Receivers, FaxUploadFile[] Files, Date ReserveDT,
            Boolean AdsYN, String RequestNum) throws PopbillException {
        return requestFaxBinary(CorpNum, SendNum, SenderName, Receivers, Files, ReserveDT, null, AdsYN, null, RequestNum);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.FaxService#sendFAX(java.lang.String, java.lang.String, java.lang.String, com.popbill.api.fax.Receiver[], java.io.File[], java.util.Date, java.lang.String)
     */
    @Override
    public String sendFAX(String CorpNum, String SendNum, String SenderName, Receiver[] Receivers, File[] Files, Date ReserveDT, String UserID)
            throws PopbillException {

        return requestFax(CorpNum, SendNum, SenderName, Receivers, Files, ReserveDT, UserID, false, null, null);
    }

    @Override
    public String sendFAXBinary(String CorpNum, String SendNum, String SenderName, Receiver[] Receivers, FaxUploadFile[] Files, Date ReserveDT,
            String UserID) throws PopbillException {

        return requestFaxBinary(CorpNum, SendNum, SenderName, Receivers, Files, ReserveDT, UserID, false, null, null);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.FaxService#sendFAX(java.lang.String, java.lang.String, java.lang.String, com.popbill.api.fax.Receiver[], java.io.File[], java.util.Date, java.lang.String, java.lang.String)
     */
    @Override
    public String sendFAX(String CorpNum, String SendNum, String SenderName, Receiver[] Receivers, File[] Files, Date ReserveDT, String UserID,
            String Title) throws PopbillException {

        return requestFax(CorpNum, SendNum, SenderName, Receivers, Files, ReserveDT, UserID, false, Title, null);
    }

    @Override
    public String sendFAXBinary(String CorpNum, String SendNum, String SenderName, Receiver[] Receivers, FaxUploadFile[] Files, Date ReserveDT,
            String UserID, String Title) throws PopbillException {

        return requestFaxBinary(CorpNum, SendNum, SenderName, Receivers, Files, ReserveDT, UserID, false, Title, null);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.FaxService#sendFAX(java.lang.String, java.lang.String, java.lang.String, com.popbill.api.fax.Receiver[], java.io.File[], java.util.Date, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public String sendFAX(String CorpNum, String SendNum, String SenderName, Receiver[] Receivers, File[] Files, Date ReserveDT, String UserID,
            String Title, String RequestNum) throws PopbillException {

        return requestFax(CorpNum, SendNum, SenderName, Receivers, Files, ReserveDT, UserID, false, Title, RequestNum);
    }

    @Override
    public String sendFAXBinary(String CorpNum, String SendNum, String SenderName, Receiver[] Receivers, FaxUploadFile[] Files, Date ReserveDT,
            String UserID, String Title, String RequestNum) throws PopbillException {

        return requestFaxBinary(CorpNum, SendNum, SenderName, Receivers, Files, ReserveDT, UserID, false, Title, RequestNum);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.FaxService#sendFAX(java.lang.String, java.lang.String, java.lang.String, com.popbill.api.fax.Receiver[], java.io.File[], java.util.Date, java.lang.String, java.lang.Boolean)
     */
    @Override
    public String sendFAX(String CorpNum, String SendNum, String SenderName, Receiver[] Receivers, File[] Files, Date ReserveDT, String UserID,
            Boolean AdsYN) throws PopbillException {

        return requestFax(CorpNum, SendNum, SenderName, Receivers, Files, ReserveDT, UserID, AdsYN, null, null);
    }

    @Override
    public String sendFAXBinary(String CorpNum, String SendNum, String SenderName, Receiver[] Receivers, FaxUploadFile[] Files, Date ReserveDT,
            String UserID, Boolean AdsYN) throws PopbillException {

        return requestFaxBinary(CorpNum, SendNum, SenderName, Receivers, Files, ReserveDT, UserID, AdsYN, null, null);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.FaxService#sendFAX(java.lang.String, java.lang.String, java.lang.String, com.popbill.api.fax.Receiver[], java.io.File[], java.util.Date, java.lang.String, java.lang.Boolean, java.lang.String)
     */
    @Override
    public String sendFAX(String CorpNum, String SendNum, String SenderName, Receiver[] Receivers, File[] Files, Date ReserveDT, String UserID,
            Boolean AdsYN, String Title) throws PopbillException {

        return requestFax(CorpNum, SendNum, SenderName, Receivers, Files, ReserveDT, UserID, AdsYN, Title, null);
    }

    @Override
    public String sendFAXBinary(String CorpNum, String SendNum, String SenderName, Receiver[] Receivers, FaxUploadFile[] Files, Date ReserveDT,
            String UserID, Boolean AdsYN, String Title) throws PopbillException {

        return requestFaxBinary(CorpNum, SendNum, SenderName, Receivers, Files, ReserveDT, UserID, AdsYN, Title, null);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.FaxService#sendFAX(java.lang.String, java.lang.String, java.lang.String, com.popbill.api.fax.Receiver[], java.io.File[], java.util.Date, java.lang.String, java.lang.Boolean, java.lang.String, java.lang.String)
     */
    @Override
    public String sendFAX(String CorpNum, String SendNum, String SenderName, Receiver[] Receivers, File[] Files, Date ReserveDT, String UserID,
            Boolean AdsYN, String Title, String RequestNum) throws PopbillException {

        return requestFax(CorpNum, SendNum, SenderName, Receivers, Files, ReserveDT, UserID, AdsYN, Title, RequestNum);
    }

    private String requestFax(String CorpNum, String SendNum, String SenderName, Receiver[] Receivers, File[] Files, Date ReserveDT, String UserID,
            Boolean AdsYN, String Title, String RequestNum) throws PopbillException {
        if (SendNum == null || SendNum.isEmpty())
            throw new PopbillException(-99999999, "발신번호가 입력되지 않았습니다.");
        if (Receivers == null || Receivers.length == 0)
            throw new PopbillException(-99999999, "수신처 정보가 입력되지 않았습니다.");
        if (Files == null || Files.length == 0)
            throw new PopbillException(-99999999, "발신파일 정보가 입력되지 않았습니다.");
        if (Files.length > 20)
            throw new PopbillException(-99999999, "동보발신 최대 파일갯수는 20개 입니다.");

        SendRequest request = new SendRequest();

        request.snd = SendNum;
        request.rcvs = Receivers;

        if (SenderName != null)
            request.sndnm = SenderName;

        if (ReserveDT != null)
            request.sndDT = new SimpleDateFormat("yyyyMMddHHmmss", Locale.KOREA).format(ReserveDT);

        if (AdsYN != null && AdsYN)
            request.adsYN = true;

        if (Title != null)
            request.title = Title;

        if (RequestNum != null)
            request.requestNum = RequestNum;

        request.fCnt = Files.length;

        String PostData = toJsonString(request);

        List<UploadFile> uploadFiles = new ArrayList<UploadFile>();

        for (File f : Files) {
            UploadFile uf = new UploadFile();
            uf.fieldName = "File";
            uf.fileName = f.getName();
            try {
                uf.fileData = new FileInputStream(f);
            } catch (FileNotFoundException e) {
                throw new PopbillException(-99999999, "전송할 파일을 찾을 수 없습니다.", e);
            }
            uploadFiles.add(uf);
        }

        ReceiptResponse response = httppostFiles("/FAX", CorpNum, PostData, uploadFiles, UserID, ReceiptResponse.class);

        for (UploadFile uf : uploadFiles) {
            if (uf.fileData != null)
                try {
                    uf.fileData.close();
                } catch (IOException e) {
                }
        }

        return response.receiptNum;
    }

    @Override
    public String sendFAXBinary(String CorpNum, String SendNum, String SenderName, Receiver[] Receivers, FaxUploadFile[] Files, Date ReserveDT,
            String UserID, Boolean AdsYN, String Title, String RequestNum) throws PopbillException {

        return requestFaxBinary(CorpNum, SendNum, SenderName, Receivers, Files, ReserveDT, UserID, AdsYN, Title, RequestNum);
    }

    private String requestFaxBinary(String CorpNum, String SendNum, String SenderName, Receiver[] Receivers, FaxUploadFile[] Files, Date ReserveDT,
            String UserID, Boolean AdsYN, String Title, String RequestNum) throws PopbillException {
        if (SendNum == null || SendNum.isEmpty())
            throw new PopbillException(-99999999, "발신번호가 입력되지 않았습니다.");
        if (Receivers == null || Receivers.length == 0)
            throw new PopbillException(-99999999, "수신처 정보가 입력되지 않았습니다.");
        if (Files == null || Files.length == 0)
            throw new PopbillException(-99999999, "발신파일 정보가 입력되지 않았습니다.");
        if (Files.length > 20)
            throw new PopbillException(-99999999, "동보발신 최대 파일갯수는 20개 입니다.");

        SendRequest request = new SendRequest();

        request.snd = SendNum;
        request.rcvs = Receivers;

        if (SenderName != null)
            request.sndnm = SenderName;

        if (ReserveDT != null)
            request.sndDT = new SimpleDateFormat("yyyyMMddHHmmss", Locale.KOREA).format(ReserveDT);

        if (AdsYN != null && AdsYN)
            request.adsYN = true;

        if (Title != null)
            request.title = Title;

        if (RequestNum != null)
            request.requestNum = RequestNum;

        request.fCnt = Files.length;

        String PostData = toJsonString(request);

        List<UploadFile> uploadFiles = new ArrayList<UploadFile>();

        for (FaxUploadFile f : Files) {
            UploadFile uf = new UploadFile();
            uf.fieldName = "File";
            uf.fileName = f.fileName;
            uf.fileData = f.fileData;
            uploadFiles.add(uf);
        }

        ReceiptResponse response = httppostFiles("/FAX", CorpNum, PostData, uploadFiles, UserID, ReceiptResponse.class);

        for (UploadFile uf : uploadFiles) {
            if (uf.fileData != null)
                try {
                    uf.fileData.close();
                } catch (IOException e) {
                }
        }

        return response.receiptNum;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.FaxService#resendFAX(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.util.Date, java.lang.String)
     */
    @Override
    public String resendFAX(String CorpNum, String ReceiptNum, String SendNum, String SenderName, String ReceiveNum, String ReceiveName,
            Date ReserveDT, String UserID) throws PopbillException {

        Receiver receiver = null;

        if ((ReceiveNum != null && !ReceiveNum.isEmpty()) || (ReceiveName != null && !ReceiveName.isEmpty())) {
            receiver = new Receiver();
            receiver.setReceiveNum(ReceiveNum);
            receiver.setReceiveName(ReceiveName);
            return resendFAX(CorpNum, ReceiptNum, SendNum, SenderName, new Receiver[] { receiver }, ReserveDT, UserID, null);
        }
        return resendFAX(CorpNum, ReceiptNum, SendNum, SenderName, null, ReserveDT, UserID, null);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.FaxService#resendFAX(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.util.Date, java.lang.String, java.lang.String)
     */
    @Override
    public String resendFAX(String CorpNum, String ReceiptNum, String SendNum, String SenderName, String ReceiveNum, String ReceiveName,
            Date ReserveDT, String UserID, String Title) throws PopbillException {

        Receiver receiver = null;

        if ((ReceiveNum != null && !ReceiveNum.isEmpty()) || (ReceiveName != null && !ReceiveName.isEmpty())) {
            receiver = new Receiver();
            receiver.setReceiveNum(ReceiveNum);
            receiver.setReceiveName(ReceiveName);

            return resendFAX(CorpNum, ReceiptNum, SendNum, SenderName, new Receiver[] { receiver }, ReserveDT, UserID, Title);
        }
        return resendFAX(CorpNum, ReceiptNum, SendNum, SenderName, null, ReserveDT, UserID, Title);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.FaxService#resendFAX(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.util.Date, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public String resendFAX(String CorpNum, String ReceiptNum, String SendNum, String SenderName, String ReceiveNum, String ReceiveName,
            Date ReserveDT, String UserID, String Title, String RequestNum) throws PopbillException {

        Receiver receiver = null;

        if ((ReceiveNum != null && !ReceiveNum.isEmpty()) || (ReceiveName != null && !ReceiveName.isEmpty())) {
            receiver = new Receiver();
            receiver.setReceiveNum(ReceiveNum);
            receiver.setReceiveName(ReceiveName);

            return resendFAX(CorpNum, ReceiptNum, SendNum, SenderName, new Receiver[] { receiver }, ReserveDT, UserID, Title, RequestNum);
        }
        return resendFAX(CorpNum, ReceiptNum, SendNum, SenderName, null, ReserveDT, UserID, Title, RequestNum);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.FaxService#resendFAX(java.lang.String, java.lang.String, java.lang.String, java.lang.String, com.popbill.api.fax.Receiver[], java.util.Date)
     */
    @Override
    public String resendFAX(String CorpNum, String ReceiptNum, String SendNum, String SenderName, Receiver[] Receivers, Date ReserveDT)
            throws PopbillException {

        return resendFAX(CorpNum, ReceiptNum, SendNum, SenderName, Receivers, ReserveDT, null, null);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.FaxService#resendFAX(java.lang.String, java.lang.String, java.lang.String, java.lang.String, com.popbill.api.fax.Receiver[], java.util.Date, java.lang.String)
     */
    @Override
    public String resendFAX(String CorpNum, String ReceiptNum, String SendNum, String SenderName, Receiver[] Receivers, Date ReserveDT, String UserID)
            throws PopbillException {

        return resendFAX(CorpNum, ReceiptNum, SendNum, SenderName, Receivers, ReserveDT, UserID, null);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.FaxService#resendFAX(java.lang.String, java.lang.String, java.lang.String, java.lang.String, com.popbill.api.fax.Receiver[], java.util.Date, java.lang.String, java.lang.String)
     */
    @Override
    public String resendFAX(String CorpNum, String ReceiptNum, String SendNum, String SenderName, Receiver[] Receivers, Date ReserveDT, String UserID,
            String Title) throws PopbillException {
        return resendFAX(CorpNum, ReceiptNum, SendNum, SenderName, Receivers, ReserveDT, UserID, Title, null);
    }

    /*
     * verride(non-Javadoc)
     * 
     * @see com.popbill.api.FaxService#resendFAX(java.lang.String, java.lang.String, java.lang.String, java.lang.String, com.popbill.api.fax.Receiver[], java.util.Date, java.lang.String, java.lang.String, java.lang.String)
     */
    public String resendFAX(String CorpNum, String ReceiptNum, String SendNum, String SenderName, Receiver[] Receivers, Date ReserveDT, String UserID,
            String Title, String RequestNum) throws PopbillException {

        if (ReceiptNum == null)
            throw new PopbillException(-99999999, "팩스 접수번호(ReceiptNum)가 입력되지 않았습니다.");
        if (ReceiptNum.length() != 18)
            throw new PopbillException(-99999999, "접수번호가 올바르지 않았습니다.");

        SendRequest request = new SendRequest();

        if (SendNum != null)
            request.snd = SendNum;

        if (SenderName != null)
            request.sndnm = SenderName;

        if (Title != null)
            request.title = Title;

        if (Receivers != null)
            request.rcvs = Receivers;

        if (ReserveDT != null)
            request.sndDT = new SimpleDateFormat("yyyyMMddHHmmss", Locale.KOREA).format(ReserveDT);

        if (RequestNum != null)
            request.requestNum = RequestNum;

        String PostData = toJsonString(request);

        ReceiptResponse response = httppost("/FAX/" + ReceiptNum, CorpNum, PostData, UserID, ReceiptResponse.class);

        return response.receiptNum;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.FaxService#resendFAXRN(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.util.Date, java.lang.String, java.lang.String)
     */
    @Override
    public String resendFAXRN(String CorpNum, String RequestNum, String SendNum, String SenderName, String ReceiveNum, String ReceiveName,
            Date ReserveDT, String UserID, String Title, String OrgRequestNum) throws PopbillException {

        Receiver receiver = null;

        if ((ReceiveNum != null && !ReceiveNum.isEmpty()) || (ReceiveName != null && !ReceiveName.isEmpty())) {
            receiver = new Receiver();
            receiver.setReceiveNum(ReceiveNum);
            receiver.setReceiveName(ReceiveName);

            return resendFAXRN(CorpNum, RequestNum, SendNum, SenderName, new Receiver[] { receiver }, ReserveDT, UserID, Title,
                    OrgRequestNum);
        }
        return resendFAXRN(CorpNum, RequestNum, SendNum, SenderName, null, ReserveDT, UserID, Title, OrgRequestNum);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.FaxService#resendFAXRN(java.lang.String, java.lang.String, java.lang.String, java.lang.String, com.popbill.api.fax.Receiver[], java.util.Date, java.lang.String, java.lang.String)
     */
    @Override
    public String resendFAXRN(String CorpNum, String RequestNum, String SendNum, String SenderName, Receiver[] Receivers, Date ReserveDT,
            String UserID, String Title, String OrgRequestNum) throws PopbillException {

        if (RequestNum == null)
            throw new PopbillException(-99999999, "재전송 팩스의 전송요청번호(RequestNum)가 입력되지 않았습니다.");

        if (OrgRequestNum == null)
            throw new PopbillException(-99999999, "원본 팩스 전송시 할당한 전송요청번호(OrgRequestNum)가 입력되지 않았습니다.");

        SendRequest request = new SendRequest();

        if (SendNum != null)
            request.snd = SendNum;

        if (SenderName != null)
            request.sndnm = SenderName;

        if (Title != null)
            request.title = Title;

        if (Receivers != null)
            request.rcvs = Receivers;

        if (ReserveDT != null)
            request.sndDT = new SimpleDateFormat("yyyyMMddHHmmss", Locale.KOREA).format(ReserveDT);

        if (RequestNum != null)
            request.requestNum = RequestNum;

        String PostData = toJsonString(request);

        ReceiptResponse response = httppost("/FAX/Resend/" + OrgRequestNum, CorpNum, PostData, UserID, ReceiptResponse.class);

        return response.receiptNum;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.FaxService#getFaxResult(java.lang.String, java.lang.String)
     */
    @Override
    public FaxResult[] getFaxResult(String CorpNum, String ReceiptNum) throws PopbillException {
        return getFaxResult(CorpNum, ReceiptNum, null);
    }

    @Override
    public FaxResult[] getFaxResult(String CorpNum, String ReceiptNum, String UserID) throws PopbillException {
        if (ReceiptNum == null)
            throw new PopbillException(-99999999, "접수번호가 입력되지 않았습니다.");
        if (ReceiptNum.length() != 18)
            throw new PopbillException(-99999999, "접수번호가 올바르지 않았습니다.");

        return httpget("/FAX/" + ReceiptNum, CorpNum, UserID, FaxResult[].class);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.FaxService#getFaxResultRN(java.lang.String, java.lang.String)
     */
    @Override
    public FaxResult[] getFaxResultRN(String CorpNum, String RequestNum) throws PopbillException {
        return getFaxResultRN(CorpNum, RequestNum, null);
    }

    @Override
    public FaxResult[] getFaxResultRN(String CorpNum, String RequestNum, String UserID) throws PopbillException {

        if (RequestNum == null)
            throw new PopbillException(-99999999, "전송요청번호가 입력되지 않았습니다.");

        return httpget("/FAX/Get/" + RequestNum, CorpNum, UserID, FaxResult[].class);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.FaxService#cancelReserve(java.lang.String, java.lang.String)
     */
    @Override
    public Response cancelReserve(String CorpNum, String ReceiptNum) throws PopbillException {
        return cancelReserve(CorpNum, ReceiptNum, null);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.FaxService#cancelReserve(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public Response cancelReserve(String CorpNum, String ReceiptNum, String UserID) throws PopbillException {
        if (ReceiptNum == null)
            throw new PopbillException(-99999999, "접수번호가 입력되지 않았습니다.");
        if (ReceiptNum.length() != 18)
            throw new PopbillException(-99999999, "접수번호가 올바르지 않았습니다.");

        return httpget("/FAX/" + ReceiptNum + "/Cancel", CorpNum, UserID, Response.class);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.FaxService#cancelReserveRN(java.lang.String, java.lang.String)
     */
    @Override
    public Response cancelReserveRN(String CorpNum, String RequestNum) throws PopbillException {
        return cancelReserveRN(CorpNum, RequestNum, null);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.FaxService#cancelReserveRN(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public Response cancelReserveRN(String CorpNum, String RequestNum, String UserID) throws PopbillException {
        if (RequestNum == null)
            throw new PopbillException(-99999999, "전송요청번호가 입력되지 않았습니다.");

        return httpget("/FAX/Cancel/" + RequestNum, CorpNum, UserID, Response.class);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.FaxService#search(java.lang.String, java.lang.String, java.lang.String, java.lang.String[], java.lang.Boolean, java.lang.Boolean, java.lang.Integer, java.lang.Integer, java.lang.String)
     */
    @Override
    public FAXSearchResult search(String CorpNum, String SDate, String EDate, String[] State, Boolean ReserveYN, Boolean SenderOnly, Integer Page,
            Integer PerPage, String Order) throws PopbillException {
        return search(CorpNum, SDate, EDate, State, ReserveYN, SenderOnly, Page, PerPage, Order, null);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.FaxService#search(java.lang.String, java.lang.String, java.lang.String, java.lang.String[], java.lang.Boolean, java.lang.Boolean, java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.String)
     */
    @Override
    public FAXSearchResult search(String CorpNum, String SDate, String EDate, String[] State, Boolean ReserveYN, Boolean SenderOnly, Integer Page,
                                  Integer PerPage, String Order, String QString) throws PopbillException {
        return search(CorpNum, SDate, EDate, State, ReserveYN, SenderOnly, Page, PerPage, Order, QString, null);
    }

    @Override
    public FAXSearchResult search(String CorpNum, String SDate, String EDate, String[] State, Boolean ReserveYN, Boolean SenderOnly, Integer Page, Integer PerPage, String Order, String QString, String UserID) throws PopbillException {
        if (SDate == null)
            throw new PopbillException(-99999999, "시작일자가 입력되지 않았습니다.");
        if (EDate == null)
            throw new PopbillException(-99999999, "종료일자가 입력되지 않았습니다.");

        String uri = "/FAX/Search?SDate=" + SDate;
        uri += "&EDate=" + EDate;

        if (State != null)
            uri += "&State=" + Arrays.toString(State == null ? new String[]{} : State).replaceAll("\\[|\\]|\\s", "");
        if (ReserveYN != null)
            uri += "&ReserveYN=" + ReserveYN;
        if (SenderOnly != null)
            uri += "&SenderOnly=" + SenderOnly;
        if (Page != null && Page > 0)
            uri += "&Page=" + Integer.toString(Page);
        if (PerPage != null && PerPage > 0 && PerPage <= 1000)
            uri += "&PerPage=" + Integer.toString(PerPage);
        if (Order != null && (Order.equals("D") || Order.equals("A")))
            uri += "&Order=" + Order;
        if (QString != null && !QString.isEmpty()) {
            try {
                uri += "&QString=" + URLEncoder.encode(QString, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                throw new PopbillException(-99999999, "검색어(QString) 인코딩 오류");
            }
        }

        return httpget(uri, CorpNum, UserID, FAXSearchResult.class);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.FaxService#getChargeInfo(java.lang.String)
     */
    @Override
    public ChargeInfo getChargeInfo(String CorpNum) throws PopbillException {
        return httpget("/FAX/ChargeInfo", CorpNum, null, ChargeInfo.class);
    }
    
    
    @Override
    public ChargeInfo getChargeInfo(String CorpNum, String ReceiveNumType) throws PopbillException {
        return getChargeInfo(CorpNum, ReceiveNumType, null);
    }

    @Override
    public ChargeInfo getChargeInfo(String CorpNum, String ReceiveNumType, String UserID) throws PopbillException {
        return httpget("/FAX/ChargeInfo?receiveNumType=" + ReceiveNumType, CorpNum, UserID, ChargeInfo.class);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.FaxService#getSenderNumberList(java.lang.String)
     */
    @Override
    public SenderNumber[] getSenderNumberList(String CorpNum) throws PopbillException {
        return getSenderNumberList(CorpNum, null);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.FaxService#getSenderNumberList(java.lang.String, java.lang.String)
     */
    @Override
    public SenderNumber[] getSenderNumberList(String CorpNum, String UserID) throws PopbillException {

        return httpget("/FAX/SenderNumber", CorpNum, UserID, SenderNumber[].class);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.FaxService#getPreviewURL(java.lang.String, java.lang.String)
     */
    @Override
    public String getPreviewURL(String CorpNum, String ReceiptNum) throws PopbillException {
        return getPreviewURL(CorpNum, ReceiptNum, null);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.FaxService#getPreviewURL(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public String getPreviewURL(String CorpNum, String ReceiptNum, String UserID) throws PopbillException {
        if (ReceiptNum == null || ReceiptNum.isEmpty())
            throw new PopbillException(-99999999, "접수번호가 입력되지 않았습니다.");
        if (ReceiptNum.length() != 18)
            throw new PopbillException(-99999999, "접수번호가 올바르지 않았습니다.");

        URLResponse response = httpget("/FAX/Preview/" + ReceiptNum, CorpNum, UserID, URLResponse.class);

        return response.url;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.FaxService#getSenderNumberMgtURL(java.lang.String, java.lang.String)
     */
    @Override
    public String getSenderNumberMgtURL(String CorpNum, String UserID) throws PopbillException {

        URLResponse response = httpget("/FAX/?TG=SENDER", CorpNum, UserID, URLResponse.class);

        return response.url;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.FaxService#getSentListURL(java.lang.String, java.lang.String)
     */
    @Override
    public String getSentListURL(String CorpNum, String UserID) throws PopbillException {

        URLResponse response = httpget("/FAX/?TG=BOX", CorpNum, UserID, URLResponse.class);

        return response.url;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.FaxService#checkSenderNumber(java.lang.String, java.lang.String)
     */
    @Override
    public Response checkSenderNumber(String CorpNum, String SenderNumber) throws PopbillException {
        return checkSenderNumber(CorpNum, SenderNumber, null);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.popbill.api.FaxService#checkSenderNumber(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public Response checkSenderNumber(String CorpNum, String SenderNumber, String UserID) throws PopbillException {
        if (SenderNumber == null || SenderNumber.isEmpty())
            throw new PopbillException(-99999999, "발신번호가 입력되지 않았습니다.");
        return httpget("/FAX/CheckSenderNumber/"+SenderNumber, CorpNum, UserID, Response.class);
    }

    protected class SendRequest {
        public String snd;
        public String sndnm;
        public String sndDT;
        public String title;
        public Integer fCnt;
        public Boolean adsYN;
        public String requestNum;
        public Receiver[] rcvs;
    }

    protected class ReceiptResponse {
        public String receiptNum;
    }

}
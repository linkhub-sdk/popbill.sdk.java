package com.popbill.api.kakao;

import java.io.Serializable;
import java.util.List;

public class KakaoSentInfo implements Serializable {
    private static final long serialVersionUID = 9029967973747556601L;

    private String contentType;
    private String templateCode;
    private String plusFriendID;
    private String sendNum;
    private String altSubject;
    private String altContent;
    private String altSendType;
    private String reserveDT;
    private Boolean adsYN;
    private String imageURL;
    private String sendCnt;
    private String successCnt;
    private String failCnt;
    private String altCnt;
    private String cancelCnt;
    private List<KakaoSentDetail> msgs;
    private List<KakaoButton> btns;

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getTemplateCode() {
        return templateCode;
    }

    public void setTemplateCode(String templateCode) {
        this.templateCode = templateCode;
    }

    public String getPlusFriendID() {
        return plusFriendID;
    }

    public void setPlusFriendID(String plusFriendID) {
        this.plusFriendID = plusFriendID;
    }

    public String getSendNum() {
        return sendNum;
    }

    public void setSendNum(String sendNum) {
        this.sendNum = sendNum;
    }

    public String getAltSubject() {
        return altSubject;
    }

    public void setAltSubject(String altSubject) {
        this.altSubject = altSubject;
    }

    public String getAltContent() {
        return altContent;
    }

    public void setAltContent(String altContent) {
        this.altContent = altContent;
    }

    public String getAltSendType() {
        return altSendType;
    }

    public void setAltSendType(String altSendType) {
        this.altSendType = altSendType;
    }

    public String getReserveDT() {
        return reserveDT;
    }

    public void setReserveDT(String reserveDT) {
        this.reserveDT = reserveDT;
    }

    public Boolean getAdsYN() {
        return adsYN;
    }

    public void setAdsYN(Boolean adsYN) {
        this.adsYN = adsYN;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getSendCnt() {
        return sendCnt;
    }

    public void setSendCnt(String sendCnt) {
        this.sendCnt = sendCnt;
    }

    public String getSuccessCnt() {
        return successCnt;
    }

    public void setSuccessCnt(String successCnt) {
        this.successCnt = successCnt;
    }

    public String getFailCnt() {
        return failCnt;
    }

    public void setFailCnt(String failCnt) {
        this.failCnt = failCnt;
    }

    public String getAltCnt() {
        return altCnt;
    }

    public void setAltCnt(String altCnt) {
        this.altCnt = altCnt;
    }

    public String getCancelCnt() {
        return cancelCnt;
    }

    public void setCancelCnt(String cancelCnt) {
        this.cancelCnt = cancelCnt;
    }

    public List<KakaoSentDetail> getMsgs() {
        return msgs;
    }

    public void setMsgs(List<KakaoSentDetail> msgs) {
        this.msgs = msgs;
    }

    public List<KakaoButton> getBtns() {
        return btns;
    }

    public void setBtns(List<KakaoButton> btns) {
        this.btns = btns;
    }
}

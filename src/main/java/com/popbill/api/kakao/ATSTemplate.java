package com.popbill.api.kakao;

import java.io.Serializable;
import java.util.List;

public class ATSTemplate implements Serializable {

    private static final long serialVersionUID = -5297991073128871215L;

    private String templateCode;
    private String templateName;
    private String template;
    private String plusFriendID;
    private String ads;
    private String appendix;
    private List<KakaoButton> btns;
    private Boolean secureYN;
    private String state;
    private String stateDT;

    public String getTemplateCode() {
        return templateCode;
    }

    public void setTemplateCode(String templateCode) {
        this.templateCode = templateCode;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getPlusFriendID() {
        return plusFriendID;
    }

    public void setPlusFriendID(String plusFriendID) {
        this.plusFriendID = plusFriendID;
    }

    /**
     * v1.37.0 추가
     */
    public String getAds() {
        return ads;
    }

    /**
     * v1.37.0 추가
     */
    public void setAds(String ads) {
        this.ads = ads;
    }

    /**
     * v1.37.0 추가
     */
    public String getAppendix() {
        return appendix;
    }

    /**
     * v1.37.0 추가
     */
    public void setAppendix(String appendix) {
        this.appendix = appendix;
    }

    public List<KakaoButton> getBtns() {
        return btns;
    }

    public void setBtns(List<KakaoButton> btns) {
        this.btns = btns;
    }

    public Boolean getSecureYN() {
        return secureYN;
    }

    public void setSecureYN(Boolean secureYN) {
        this.secureYN = secureYN;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStateDT() {
        return stateDT;
    }

    public void setStateDT(String stateDT) {
        this.stateDT = stateDT;
    }

}

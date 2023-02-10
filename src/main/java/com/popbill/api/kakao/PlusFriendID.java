package com.popbill.api.kakao;

import java.io.Serializable;

/**
 * Class for AutoDeny Information.
 * 
 * @author JeongYohan
 * @version 1.0.0
 */

public class PlusFriendID implements Serializable {

    private static final long serialVersionUID = 3199331662598320029L;

    private String plusFriendID;
    private String plusFriendName;
    private String regDT;
    private String state;
    private String stateDT;

    public String getPlusFriendID() {
        return plusFriendID;
    }

    public void setPlusFriendID(String plusFriendID) {
        this.plusFriendID = plusFriendID;
    }

    public String getPlusFriendName() {
        return plusFriendName;
    }

    public void setPlusFriendName(String plusFriendName) {
        this.plusFriendName = plusFriendName;
    }

    public String getRegDT() {
        return regDT;
    }

    public void setRegDT(String regDT) {
        this.regDT = regDT;
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

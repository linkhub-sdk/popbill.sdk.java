package com.popbill.api.kakao;

import java.io.Serializable;

public class KakaoButton implements Serializable {

    private static final long serialVersionUID = -2238767228371308148L;

    private String n;
    private String t;
    private String u1;
    private String u2;
    private String tg;

    public String getN() {
        return n;
    }

    public void setN(String n) {
        this.n = n;
    }

    public String getT() {
        return t;
    }

    public void setT(String t) {
        this.t = t;
    }

    public String getU1() {
        return u1;
    }

    public void setU1(String u1) {
        this.u1 = u1;
    }

    public String getU2() {
        return u2;
    }

    public void setU2(String u2) {
        this.u2 = u2;
    }

	public String getTg() {
		return tg;
	}

	public void setTg(String tg) {
		this.tg = tg;
	}

}

package com.popbill.api.message;

import java.io.Serializable;

/**
 * Class for Message Search Information.
 * 
 * @author JeongYohan
 * @version 1.0.0
 */
public class MessageBriefInfo implements Serializable {

	private static final long serialVersionUID = -5880243217009063354L;
	
	private String sn;
	private String rNum;
	private String stat;
	private String sDT;
	private String rDT;
	private String rlt;
	private String net;
	private String srt;
	
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	public String getrNum() {
		return rNum;
	}
	public void setrNum(String rNum) {
		this.rNum = rNum;
	}
	public String getStat() {
		return stat;
	}
	public void setStat(String stat) {
		this.stat = stat;
	}
	public String getsDT() {
		return sDT;
	}
	public void setsDT(String sDT) {
		this.sDT = sDT;
	}
	public String getrDT() {
		return rDT;
	}
	public void setrDT(String rDT) {
		this.rDT = rDT;
	}
	public String getRlt() {
		return rlt;
	}
	public void setRlt(String rlt) {
		this.rlt = rlt;
	}
	public String getNet() {
		return net;
	}
	public void setNet(String net) {
		this.net = net;
	}
	public String getSrt() {
		return srt;
	}
	public void setSrt(String srt) {
		this.srt = srt;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}


}

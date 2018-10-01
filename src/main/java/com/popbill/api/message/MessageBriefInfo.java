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

	public String getrNum() {
		return rNum;
	}

	public String getStat() {
		return stat;
	}

	public String getsDT() {
		return sDT;
	}

	public String getrDT() {
		return rDT;
	}

	public String getRlt() {
		return rlt;
	}

	public String getNet() {
		return net;
	}

	public String getSrt() {
		return srt;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


}

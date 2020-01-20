package com.popbill.api.kakao;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class KakaoReceiver implements Serializable{

	private static final long serialVersionUID = 1185486427910449441L;
	
	@SerializedName("rcv")
	private String receiverNum;
	
	@SerializedName("rcvnm")
	private String receiverName;
	
	@SerializedName("msg")
	private String message;
	
	@SerializedName("altmsg")
	private String altMessage;
	
	@SerializedName("btns")
	private List<KakaoButton> btns;

	public String getReceiverNum() {
		return receiverNum;
	}

	public void setReceiverNum(String receiverNum) {
		this.receiverNum = receiverNum;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getAltMessage() {
		return altMessage;
	}

	public void setAltMessage(String altMessage) {
		this.altMessage = altMessage;
	}

	public List<KakaoButton> getBtns() {
		return btns;
	}

	public void setBtns(List<KakaoButton> btns) {
		this.btns = btns;
	}

}


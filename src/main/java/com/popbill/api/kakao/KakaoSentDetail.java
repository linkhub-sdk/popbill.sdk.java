package com.popbill.api.kakao;

import java.io.Serializable;

/**
 * 
 * @author John
 *
 */
public class KakaoSentDetail implements Serializable{
	private static final long serialVersionUID = 9029967973747556601L;
	
	private Integer state;
	private String sendDT;
	private String receiveNum;
	private String receiveName;
	private String content;
	private Integer result;
	private String resultDT;
	private String altContent;
	private Integer altContentType;
	private String altSendDT;
	private Integer altResult;
	private String altResultDT;
	private String reserveDT;
	private Integer contentType;
	private String receiptNum;
	private String requestNum;
	private String interOPRefKey;
	
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getSendDT() {
		return sendDT;
	}
	public void setSendDT(String sendDT) {
		this.sendDT = sendDT;
	}
	public String getReceiveNum() {
		return receiveNum;
	}
	public void setReceiveNum(String receiveNum) {
		this.receiveNum = receiveNum;
	}
	public String getReceiveName() {
		return receiveName;
	}
	public void setReceiveName(String receiveName) {
		this.receiveName = receiveName;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getResult() {
		return result;
	}
	public void setResult(Integer result) {
		this.result = result;
	}
	public String getResultDT() {
		return resultDT;
	}
	public void setResultDT(String resultDT) {
		this.resultDT = resultDT;
	}
	public String getAltContent() {
		return altContent;
	}
	public void setAltContent(String altContent) {
		this.altContent = altContent;
	}
	public Integer getAltContentType() {
		return altContentType;
	}
	public void setAltContentType(Integer altContentType) {
		this.altContentType = altContentType;
	}
	public String getAltSendDT() {
		return altSendDT;
	}
	public void setAltSendDT(String altSendDT) {
		this.altSendDT = altSendDT;
	}
	public Integer getAltResult() {
		return altResult;
	}
	public void setAltResult(Integer altResult) {
		this.altResult = altResult;
	}
	public String getAltResultDT() {
		return altResultDT;
	}
	public void setAltResultDT(String altResultDT) {
		this.altResultDT = altResultDT;
	}
	public Integer getContentType() {
		return contentType;
	}
	public void setContentType(Integer contentType) {
		this.contentType = contentType;
	}
	public String getReserveDT() {
		return reserveDT;
	}
	public void setReserveDT(String reserveDT) {
		this.reserveDT = reserveDT;
	}
	public String getReceiptNum() {
		return receiptNum;
	}
	public void setReceiptNum(String receiptNum) {
		this.receiptNum = receiptNum;
	}
	public String getRequestNum() {
		return requestNum;
	}
	public void setRequestNum(String requestNum) {
		this.requestNum = requestNum;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getInterOPRefKey() {
		return interOPRefKey;
	}
	public void setInterOPRefKey(String interOPRefKey) {
		this.interOPRefKey = interOPRefKey;
	}
}

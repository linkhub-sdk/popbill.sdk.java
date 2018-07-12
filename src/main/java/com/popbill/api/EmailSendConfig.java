package com.popbill.api;

import java.io.Serializable;

/**
 * Class for Corporation Information.
 * 
 * @author KimEunhye 
 * @version 1.0.0
 */

public class EmailSendConfig implements Serializable{
	
	private static final long serialVersionUID = 2470915871054423095L;
	
	private String emailType;
	private Boolean sendYN;
	
	/**
	 * 메일유형 확인
	 * 
	 * @param emailType
	 * 		메일유형 
	 */	
	public String getEmailType() {
		return emailType;
	}
	
	/**
	 * 메일유형 설정 
	 * 
	 * @param emailType
	 * 		메일유형 
	 */	
	public void setEmailType(String emailType) {
		this.emailType = emailType;
	}
	
	/**
	 * 전송여부 확인
	 * 
	 * @param sendYN
	 * 		전송여부 (True-전송, False-미전송)
	 */		
	public Boolean getSendYN() {
		return sendYN;
	}
	
	/**
	 * 전송여부 설정 
	 * 
	 * @param sendYN
	 * 		전송여부 (True-전송, False-미전송)
	 */		
	public void setSendYN(Boolean sendYN) {
		this.sendYN = sendYN;
	}
	
}


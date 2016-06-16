package com.popbill.api.hometax;

import java.io.Serializable;

/**
 * Class for HomeTax Taxinvoice GetXML Response
 * 
 * @author JeongYoHan
 * @version 1.0.0
 */
public class HTTaxinvoiceXMLResponse implements Serializable {
	private static final long serialVersionUID = -3875956756358230185L;
	
	private long ResultCode;
	private String Message;
	private String retObject;
	
	
	/**
	 * 응답 상태코드 확인 
	 * 
	 * @return 1-성공, 기타-오류 
	 */
	public long getResultCode() {
		return ResultCode;
	}
	
	/**
	 * 전자(세금)계산서 국세청승인번호 확인 
	 * 
	 * @return 국세청승인번호 
	 */
	public String getMessage() {
		return Message;
	}
	
	/**
	 * 전자(세금)계산서 국세청승인번호 확인  
	 * 
	 * @return 전자(세금)계산서 국세청승인번호 확인
	 */
	public String getRetObject() {
		return retObject;
	}

}

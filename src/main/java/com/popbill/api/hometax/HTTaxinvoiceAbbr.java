package com.popbill.api.hometax;

import java.io.Serializable;
/**
 * Class for HomeTax Taxinvoice Info
 * 
 * @author JeongYoHan
 * @version 1.0.0
 */
public class HTTaxinvoiceAbbr implements Serializable {
	private static final long serialVersionUID = -7521186169862685752L;
	
	private String ntsconfirmNum;
	private String writeDate;
	private String issueDate;
	private String sendDate;
	private String taxType;
	private String purposeType;
	private Boolean modifyYN;
	
	private String invoicerCorpNum;
	private String invoicerCorpName;
	private String invoicerEmail;
	private String invoicerCEOName;
	private String invoicerTaxRegID;
	
	private String invoiceeCorpNum;
	private String invoiceeType;
	private String invoiceeCorpName;
	private String invoiceeCEOName;
	private String invoiceeEmail1;
	private String invoiceeTaxRegID;
	private String invoiceeEmail2;
	
	private String supplyCostTotal;
	private String taxTotal;
	private String totalAmount;
	private String purchaseDate;
	private String itemName;
	private String supplyCost;
	private String tax;
	private String orgNTSConfirmNum;
	

	/**
	 * 국세청승인번호 확인 
	 *  
	 * @return 국세청승인번호  
	 */
	public String getNtsconfirmNum() {
		return ntsconfirmNum;
	}
	
	/**
	 * 작성일자 확인  
	 * 
	 * @return 작성일자 
	 */
	public String getWriteDate() {
		return writeDate;
	}
	
	/**
	 * 발행일자 확인 
	 * 
	 * @return 발행일자 
	 */
	public String getIssueDate() {
		return issueDate;
	}
	
	/**
	 * 전송일자 확인 
	 * 
	 * @return 전송일자 
	 */
	public String getSendDate() {
		return sendDate;
	}
	
	/**
	 * 과세형태 확인 
	 * 
	 * @return 과세형태, "과세", "영세", "면세" 중 반환 
	 */
	public String getTaxType() {
		return taxType;
	}
	
	/**
	 * 영수/청구 확인 
	 * 
	 * @return "영수", "청구" 중 반환 
	 */
	public String getPurposeType() {
		return purposeType;
	}
	
	/**
	 * 수정 전자(세금)계산서 여부 
	 * 
	 * @return true-수정 전자(세금)계산서, false-일반 전자(세금)계산서  
	 */
	public Boolean getModifyYN() {
		return modifyYN;
	}
	
	/**
	 * 원본 전자(세금)계산서 국세청 승인번호 확인
	 * 
	 * @return 원본 전자(세금)계산서 국세청 승인번호 확인 
	 */
	public String getOrgNTSConfirmNum() {
		return orgNTSConfirmNum;
	}

	/**
	 * 공급자 사업자번호 확인 
	 * 
	 * @return 공급자 사업자번호 
	 */
	public String getInvoicerCorpNum() {
		return invoicerCorpNum;
	}
	
	/**
	 * 공급자 상호 
	 * 
	 * @return 공급자 상호 
	 */
	public String getInvoicerCorpName() {
		return invoicerCorpName;
	}
	
	/**
	 * 공급자 담당자 이메일 확인 
	 * 
	 * @return 공급자 담당자 이메일 
	 */
	public String getInvoicerEmail() {
		return invoicerEmail;
	}
	
	/**
	 * 공급자 대표자 성명 확인  
	 * 
	 * @return 공급자 대표자 성명 
	 */
	public String getInvoicerCEOName() {
		return invoicerCEOName;
	}

	/**
	 * 공급자 종사업장번호 확인  
	 * 
	 * @return 공급자 종사업장번호 확인 
	 */
	public String getInvoicerTaxRegID() {
		return invoicerTaxRegID;
	}

	/**
	 * 공급받는자 사업자번호 확인 
	 * 
	 * @return 공급받는자 사업자번호 
	 */
	public String getInvoiceeCorpNum() {
		return invoiceeCorpNum;
	}
	
	/**
	 * 공급받는자 구분 확인  
	 * 
	 * @return 공급받는자 구분 
	 */
	public String getInvoiceeType() {
		return invoiceeType;
	}
	
	/**
	 * 공급받는자 상호 확인 
	 * 
	 * @return 공급받는자 상호 
	 */
	public String getInvoiceeCorpName() {
		return invoiceeCorpName;
	}
	
	/**
	 * 공급받는자 대표자성명 확인 
	 * 
	 * @return 공급받는자 대표자성명 
	 */
	public String getInvoiceeCEOName() {
		return invoiceeCEOName;
	}
	
	/**
	 * 공급받는자 담당자 이메일 확인 
	 * 
	 * @return 공급받는자 담당자 이메일 
	 */
	public String getInvoiceeEmail1() {
		return invoiceeEmail1;
	}
	
	/**
	 * 공급받는자 종사업장번호 확인 
	 * 
	 * @return 공급받는자 종사업장번호 
	 */
	public String getInvoiceeTaxRegID() {
		return invoiceeTaxRegID;
	}
	
	/**
	 * 대용량 연계사업자 메일주소 확인 
	 * 
	 * @return 대용량 연계사업자 메일주소 
	 */
	public String getInvoiceeEmail2() {
		return invoiceeEmail2;
	}

	/**
	 * 공급가액 확인
	 * 
	 * @return 공급가액 
	 */
	public String getSupplyCostTotal() {
		return supplyCostTotal;
	}
	
	/**
	 * 세액합계 확인 
	 *  
	 * @return 세액합계 
	 */
	public String getTaxTotal() {
		return taxTotal;
	}
	
	/**
	 * 합계금액 확인 
	 * 
	 * @return 합계금액 
	 */
	public String getTotalAmount() {
		return totalAmount;
	}
	
	/**
	 * 품목 구매일자 확인 
	 * 
	 * @return 품목 구매일자 
	 */
	public String getPurchaseDate() {
		return purchaseDate;
	}
	
	/**
	 * 품목명 확인 
	 * 
	 * @return 품목명 
	 */
	public String getItemName() {
		return itemName;
	}
	
	/**
	 * 품목 공급가액 확인 
	 * 
	 * @return 품목 공급가액 
	 */
	public String getSupplyCost() {
		return supplyCost;
	}
	
	/**
	 * 품목 세액 확인  
	 * 
	 * @return 품목 세액 
	 */
	public String getTax() {
		return tax;
	}
	
}

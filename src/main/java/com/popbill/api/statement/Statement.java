/*
 * Copyright 2006-2014 innopost.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0.txt
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */
package com.popbill.api.statement;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class for Statement Information.
 * 
 * @author JeongYoHan
 * @version 1.0.0
 */
public class Statement implements Serializable {

	private static final long serialVersionUID = -7213142041575114213L;
	
	private String sendNum;
	private String receiveNum;
	private String memo;
	
	private Short itemCode;
	private String mgtKey;
	private String formCode;
	private String writeDate;
	private String taxType;
	
	private String senderCorpNum;
	private String senderTaxRegID;
	private String senderCorpName;
	private String senderCEOName;
	private String senderAddr;
	private String senderBizClass;
	private String senderBizType;
	private String senderContactName;
	private String senderDeptName;
	private String senderTEL;
	private String senderHP;
	private String senderEmail;
	private String senderFAX;
	
	private String receiverCorpNum;
	private String receiverTaxRegID;
	private String receiverCorpName;
	private String receiverCEOName;
	private String receiverAddr;
	private String receiverBizClass;
	private String receiverBizType;
	private String receiverContactName;
	private String receiverDeptName;
	private String receiverTEL;
	private String receiverHP;
	private String receiverEmail;
	private String receiverFAX;
	
	private String taxTotal;
	private String supplyCostTotal;
	private String totalAmount;
	private String purposeType;
	private String serialNum;
	private String remark1;
	private String remark2;
	private String remark3;
	
	private boolean businessLicenseYN;
	private boolean bankBookYN;
	private boolean smssendYN;
	private boolean autoacceptYN;
	
	private List<StatementDetail> detailList;
	private Map<String, String> propertyBag = new HashMap<String, String>();
		
	/**
	 * 명세서코드 확인
	 * 121 : 거래명세서
	 * 122 : 청구서
	 * 123 : 견적서
	 * 124 : 발주서
	 * 125 : 입금표
	 * 126 : 영수증
	 * 
	 * @return 명세서 코드
	 */
	public Short getItemCode() {
		return itemCode;
	}
	/**
	 * 명세서코드
	 * 121 : 거래명세서
	 * 122 : 청구서
	 * 123 : 견적서
	 * 124 : 발주서
	 * 125 : 입금표
	 * 126 : 영수증
	 * 
	 * @param itemCode
	 * 			명세서코드
	 */
	public void setItemCode(Short itemCode) {
		this.itemCode = itemCode;
	}
	/**
	 * 문서관리번호 확인
	 * 
	 * @return 문서관리번호
	 */
	public String getMgtKey() {
		return mgtKey;
	}
	/**
	 * 문서관리번호 설정
	 * 
	 * @param mgtKey
	 * 		문서관리번호
	 */
	public void setMgtKey(String mgtKey) {
		this.mgtKey = mgtKey;
	}
	/**
	 * 맞춤 양식코드 확인
	 * 
	 * @return 맞춤양식코드
	 * 			
	 */
	public String getFormCode() {
		return formCode;
	}
	/**
	 * 맞춤양식코드 설정, 미기재시 기본양식
	 * 
	 * @param formCode
	 * 			맞춤양식코드
	 */
	public void setFormCode(String formCode) {
		this.formCode = formCode;
	}
	/**
	 * 작성일자 확인, 형식 yyyyMMdd
	 * 
	 * @return 작성일자
	 */
	public String getWriteDate() {
		return writeDate;
	}
	/**
	 * 작성일자 설정, 형식 yyyyMMdd
	 * 
	 * @param writeDate
	 * 			작성일자
	 */
	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}
	/**
	 * 세금형태, {과세, 영세, 면세}중 기재 
	 * 
	 * @return 세금형태
	 */
	public String getTaxType() {
		return taxType;
	}
	/**
	 * 섹므형태, {과세, 영세, 면세}중 기재
	 * 
	 * @param taxType
	 * 			세금형태
	 */
	public void setTaxType(String taxType) {
		this.taxType = taxType;
	}
	/**
	 * 발신자 사업자번호 확인
	 * 
	 * @return 발신자 사업자번호
	 */
	public String getSenderCorpNum() {
		return senderCorpNum;
	}
	/**
	 * 발신자 사업자번호 설정
	 * 
	 * @param senderCorpNum
	 * 			발신자 사업자번호
	 */
	public void setSenderCorpNum(String senderCorpNum) {
		this.senderCorpNum = senderCorpNum;
	}
	/**
	 * 발신자 종사업장 식별번호 확인
	 * 
	 * @return 발신자 종사업장 식별번호
	 */
	public String getSenderTaxRegID() {
		return senderTaxRegID;
	}
	/**
	 * 발신자 종사업장 식별번호 설정
	 * 
	 * @param senderTaxRegID
	 * 		   발신자 종사업장 식별번호
	 */
	public void setSenderTaxRegID(String senderTaxRegID) {
		this.senderTaxRegID = senderTaxRegID;
	}
	/**
	 * 발신자 상호 확인
	 * 
	 * @return 발신자 상호 확인
	 */
	public String getSenderCorpName() {
		return senderCorpName;
	}
	/**
	 * 발신자 상호 설정
	 *  
	 * @param senderCorpName
	 * 			발신자 상호
	 */
		public void setSenderCorpName(String senderCorpName) {
		this.senderCorpName = senderCorpName;
	}
	/**
	 * 발신자 대표자성명 확인
	 * 
	 * @return 발신자 대표자 성명
	 */
	public String getSenderCEOName() {
		return senderCEOName;
	}
	/**
	 * 발신자 대표자성명 설정
	 * 
	 * @param senderCEOName
	 * 		발신자 대표자 성명 설정
	 */
	public void setSenderCEOName(String senderCEOName) {
		this.senderCEOName = senderCEOName;
	}
	/**
	 * 발신자 주소 확인
	 * 
	 * @return 발신자 주소 
	 */
	public String getSenderAddr() {
		return senderAddr;
	}
	/**
	 * 발신자 주소 설정
	 * 
	 * @param senderAddr
	 *			발신자 주소 
	 */
	public void setSenderAddr(String senderAddr) {
		this.senderAddr = senderAddr;
	}
	/**
	 * 발신자 업종 확인
	 * 
	 * @return 발신자 업태
	 */
	public String getSenderBizClass() {
		return senderBizClass;
	}
	/**
	 * 발신자 업종 설정
	 * 
	 * @param senderBizClass
	 * 			업종
	 */
	public void setSenderBizClass(String senderBizClass) {
		this.senderBizClass = senderBizClass;
	}
	/**
	 * 발신자 업태 확인
	 * 
	 * @return 발신자 업태
	 */
	public String getSenderBizType() {
		return senderBizType;
	}
	/**
	 * 발신자 업태 설정
	 * 
	 * @param senderBizType
	 * 			발신자 업태
	 */
	public void setSenderBizType(String senderBizType) {
		this.senderBizType = senderBizType;
	}
	/**
	 * 발신자 성명 확인
	 * 
	 * @return 발신자 성명
	 */
	public String getSenderContactName() {
		return senderContactName;
	}
	/**
	 * 발신자 성명 설정
	 * 
	 * @param senderContactName
	 * 			발신자 성명 설정
	 */
	public void setSenderContactName(String senderContactName) {
		this.senderContactName = senderContactName;
	}
	/**
	 * 발신자 부서명 확인
	 * 
	 * @return 발신자 부서명
	 */
	public String getSenderDeptName() {
		return senderDeptName;
	}
	/**
	 * 발신자 부서명 설정
	 * 
	 * @param senderDeptName
	 * 			발신자 부서명
	 */
	public void setSenderDeptName(String senderDeptName) {
		this.senderDeptName = senderDeptName;
	}
	/**
	 * 발신자 연락처 확인
	 * 
	 * @return 발신자 연락처
	 */
	public String getSenderTEL() {
		return senderTEL;
	}
	/**
	 * 발신자 연락처 설정
	 * 
	 * @param senderTEL
	 * 		발신자 연락처
	 */
	public void setSenderTEL(String senderTEL) {
		this.senderTEL = senderTEL;
	}
	/**
	 * 발신자 휴대폰번호 확인
	 * @return 발신자 휴대폰번호
	 */
	public String getSenderHP() {
		return senderHP;
	}
	/**
	 * 발신자 휴대폰번호 설정
	 * @param senderHP
	 * 		발신자 휴대폰번호
	 */
	public void setSenderHP(String senderHP) {
		this.senderHP = senderHP;
	}
	/**
	 * 발신자 이메일 확인
	 * @return 발신자 이메일
	 */
	public String getSenderEmail() {
		return senderEmail;
	}
	/**
	 * 발신자 이멩리 설정
	 * @param senderEmail
	 * 			발신자 이메일
	 */
	public void setSenderEmail(String senderEmail) {
		this.senderEmail = senderEmail;
	}
	/**
	 * 발신자 팩스번호 확인
	 * 
	 * @return 발신자 팩스번호
	 */
	public String getSenderFAX() {
		return senderFAX;
	}
	/**
	 * 발신자 팩스번호 설정
	 * @param senderFAX
	 * 			발신자 팩스번호 설정
	 */
	public void setSenderFAX(String senderFAX) {
		this.senderFAX = senderFAX;
	}
	/**
	 * 수신자 상호 확인
	 * 
	 * @return 수신자 상호
	 */
	public String getReceiverCorpNum() {
		return receiverCorpNum;
	}
	/**
	 * 수신자 상호 설정
	 * 
	 * @param receiverCorpNum
	 * 			수신자 상호
	 */
	public void setReceiverCorpNum(String receiverCorpNum) {
		this.receiverCorpNum = receiverCorpNum;
	}
	/**
	 * 수신자 종사업장 식별번호 확인
	 * 
	 * @return 수신자 종사업장 식별번호
	 */
	public String getReceiverTaxRegID() {
		return receiverTaxRegID;
	}
	/**
	 * 수신자 종사업장 식별번호 설정
	 * 
	 * @param receiverTaxRegID
	 * 	  		수신자 종사업장 식별번호
	 */
	public void setReceiverTaxRegID(String receiverTaxRegID) {
		this.receiverTaxRegID = receiverTaxRegID;
	}
	/**
	 * 수신자 상호 확인
	 * 
	 * @return 수신자 상호
	 */
	public String getReceiverCorpName() {
		return receiverCorpName;
	}
	/**
	 * 수신자 상호 설정
	 * 
	 * @param receiverCorpName
	 * 			수신자 상호 
	 */
	public void setReceiverCorpName(String receiverCorpName) {
		this.receiverCorpName = receiverCorpName;
	}
	/**
	 * 수신자 대표자성명 확인
	 * 
	 * @return 수신자 대표자성명
	 */
	public String getReceiverCEOName() {
		return receiverCEOName;
	}
	/**
	 * 수신자 대표자성명 설정
	 * 
	 * @param receiverCEOName
	 * 			수신자 대표자성명
	 */
	public void setReceiverCEOName(String receiverCEOName) {
		this.receiverCEOName = receiverCEOName;
	}
	/**
	 * 수신자 주소 확인
	 * 
	 * @return 수신자 주소
	 */
	public String getReceiverAddr() {
		return receiverAddr;
	}
	/**
	 * 수신자 주소 설정
	 * 
	 * @param receiverAddr
	 * 			수신자 주소
	 */
	public void setReceiverAddr(String receiverAddr) {
		this.receiverAddr = receiverAddr;
	}
	/**
	 * 수신자 업종 확인
	 * 
	 * @return 수신자 업종
	 */
	public String getReceiverBizClass() {
		return receiverBizClass;
	}
	/**
	 * 수신자 업종 설정
	 * 
	 * @param receiverBizClass
	 * 			수신자 업종
	 */
	public void setReceiverBizClass(String receiverBizClass) {
		this.receiverBizClass = receiverBizClass;
	}
	/**
	 * 수신자 업태 확인
	 * 
	 * @return 수신자 업태  
	 */
	public String getReceiverBizType() {
		return receiverBizType;
	}
	/**
	 * 
	 * @param receiverBizType
	 */
	public void setReceiverBizType(String receiverBizType) {
		this.receiverBizType = receiverBizType;
	}
	/**
	 * 수신자 성명 확인
	 * 
	 * @return 수신자 성명
	 */
	public String getReceiverContactName() {
		return receiverContactName;
	}
	/**
	 * 수신자 성명 설정
	 * 
	 * @param receiverContactName
	 * 			수신자 성명
	 */
	public void setReceiverContactName(String receiverContactName) {
		this.receiverContactName = receiverContactName;
	}
	/**
	 * 수신자 부서명 확인
	 * 
	 * @return 수신자 부서명
	 */
	public String getReceiverDeptName() {
		return receiverDeptName;
	}
	/**
	 * 수신자 부서명 설정
	 * 
	 * @param receiverDeptName
	 * 			수신자 부서명
	 */
	public void setReceiverDeptName(String receiverDeptName) {
		this.receiverDeptName = receiverDeptName;
	}
	/**
	 * 수신자 연락처 확인
	 * 
	 * @return 수신자 연락처
	 */
	public String getReceiverTEL() {
		return receiverTEL;
	}
	/**
	 * 수신자 연락처 설정
	 * 
	 * @param receiverTEL
	 * 			수신자 연락처 
	 */
	public void setReceiverTEL(String receiverTEL) {
		this.receiverTEL = receiverTEL;
	}
	/**
	 * 수신자 휴대전화 번호 확인
	 * 
	 * @return 수신자 휴대전화 번호
	 */
	public String getReceiverHP() {
		return receiverHP;
	}
	/**
	 * 수신자 휴대전호 번호 설정
	 * 
	 * @param receiverHP
	 * 		수신자 휴대번호 번호 설정
	 */
	public void setReceiverHP(String receiverHP) {
		this.receiverHP = receiverHP;
	}
	/**
	 * 수신자 이메일 확인
	 * 
	 * @return 수신자 이메일
	 */
	public String getReceiverEmail() {
		return receiverEmail;
	}
	/**
	 * 수신자 이메일 설정
	 * 
	 * @param receiverEmail
	 * 			수신자 이메일
	 */
	public void setReceiverEmail(String receiverEmail) {
		this.receiverEmail = receiverEmail;
	}
	/**
	 * 수신자 팩스번호 확인
	 * 
	 * @return 수신자 팩스번호
	 */
	public String getReceiverFAX() {
		return receiverFAX;
	}
	/**
	 * 수신자 팩스번호 설정
	 * 
	 * @param receiverFAX
	 * 			수신자 팩스번호
	 */
	public void setReceiverFAX(String receiverFAX) {
		this.receiverFAX = receiverFAX;
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
	 * 세액합계 설정
	 * 
	 * @param taxTotal
	 * 			세액합계 
	 */
	public void setTaxTotal(String taxTotal) {
		this.taxTotal = taxTotal;
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
	 * 공급가액 설정
	 * 
	 * @param supplyCostTotal
	 * 			공급가액
	 */
	public void setSupplyCostTotal(String supplyCostTotal) {
		this.supplyCostTotal = supplyCostTotal;
	}
	/**
	 * 합계금액 확인
	 * 
	 * @return	합계금액
	 */
	public String getTotalAmount() {
		return totalAmount;
	}
	/**
	 * 합계금액 설정
	 * 
	 * @param totalAmount
	 * 			합계금액
	 */
	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}
	/**
	 * 영수/청구 확인
	 * 
	 * @return	영수/청구
	 */
	public String getPurposeType() {
		return purposeType;
	}
	/**
	 * 영수/청구 설정, {영수, 청구}중 기재
	 * 
	 * @param purposeType
	 * 			영수,청구
	 */
	public void setPurposeType(String purposeType) {
		this.purposeType = purposeType;
	}
	/**
	 * 일련번호 확인, 기재 상 '일련번호'항목
	 * 
	 * @return 일련번호
	 */
	public String getSerialNum() {
		return serialNum;
	}
	/**
	 * 일련번호 설정, 기재 상 '일련번호'항목
	 * 
	 * @param serialNum
	 * 			일련번호
	 */
	public void setSerialNum(String serialNum) {
		this.serialNum = serialNum;
	}
	/**
	 * 비고1 확인
	 * 
	 * @return 비고1
	 */
	public String getRemark1() {
		return remark1;
	}
	/**
	 * 비고1 설정
	 * 
	 * @param remark1
	 * 			비고1
	 */
	public void setRemark1(String remark1) {
		this.remark1 = remark1;
	}
	/**
	 * 비고2 확인
	 *	 
	 * @return 비고2
	 */
	public String getRemark2() {
		return remark2;
	}
	/**
	 * 비고2 설정
	 * 
	 * @param remark2
	 * 			비고2
	 */
	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}
	/**
	 * 비고3 확인
	 * 
	 * @return 비고3
	 */
	public String getRemark3() {
		return remark3;
	}
	/**
	 * 비고3 설정
	 * 
	 * @param remark3
	 * 			비고3
	 */
	public void setRemark3(String remark3) {
		this.remark3 = remark3;
	}
	/**
	 * 사업자등록증 첨부여부 확인
	 * 
	 * @return 사업자등록증 첨부여부 
	 */
	public boolean getBusinessLicenseYN() {
		return businessLicenseYN;
	}
	/**
	 * 
	 * 사업자등록증 첨부여부 설정
	 * 
	 * @param businessLicenseYN
	 * 			사업자 등록증 첨부여부
	 */
	public void setBusinessLicenseYN(Boolean businessLicenseYN) {
		this.businessLicenseYN = businessLicenseYN;
	}
	/**
	 * 통장사본 첨부여부 확인
	 * 
	 * @return 통장사본 첨부여부
	 */
	public boolean getBankBookYN() {
		return bankBookYN;
	}
	/**
	 * 통장사본 첨부여부 설정
	 * 
	 * @param bankBookYN
	 * 			통장사본 첨부여부
	 */
	public void setBankBookYN(Boolean bankBookYN) {
		this.bankBookYN = bankBookYN;
	}
	/**
	 * 문자 자동전송 여부 확인
	 * 
	 * @return 문자 자동전송 여부
	 */
	public boolean getSmssendYN() {
		return smssendYN;
	}
	/**
	 * 문자 자동전송 여부 설정
	 * 
	 * @param smssendYN
	 * 		문자 자동전송 여부
	 */
	public void setSmssendYN(Boolean smssendYN) {
		this.smssendYN = smssendYN;
	}
	/**
	 * 발행시 자동승인여부 확인
	 * 
	 * @return 발행시 자동승인여부 
	 */
	public boolean getAutoacceptYN() {
		return autoacceptYN;
	}
	/**
	 * 발행시 자동승인여부 설정
	 * 
	 * @param autoacceptYN
	 * 		  발행시 자동승인여부 설정
	 */
	public void setAutoacceptYN(Boolean autoacceptYN) {
		this.autoacceptYN = autoacceptYN;
	}
	/**
	 * 상세항목 리스트 확인
	 * 
	 * @return 상세항목 리스트
	 */
	public List<StatementDetail> getDetailList() {
		return detailList;
	}
	/**
	 * 상세항목 리스트 설정
	 * 
	 * @param detailList
	 * 			상세항목 리스트
	 */
	public void setDetailList(List<StatementDetail> detailList) {
		this.detailList = detailList;
	}
	/**
	 * 추가속성 항목 확인
	 * 
	 * @return 추가속성 항목
	 */
	public Map<String, String> getPropertyBag() {
		return propertyBag;
	}
	/**
	 * 추가속성 항목 설정
	 * 
	 * @param propertyBag
	 * 		      추가속성 항목 
	 * 
	 */
	public void setPropertyBag(Map<String, String> propertyBag) {
		this.propertyBag = propertyBag;
	}
	
	/**
	 * 선팩스전송시 발신번호 설정
	 *  
	 * @param sendNum 
	 * 			발신번호 
	 */
	public void setSendNum(String sendNum) {
		this.sendNum = sendNum;
	}
	
	/**
	 * 선팩스전송시 수신번호 설정 
	 * 
	 * @param receiveNum
	 *			수신팩스번호 
	 */
	public void setReceiveNum(String receiveNum) {
		this.receiveNum = receiveNum;
	}
	
	/**
	 * 즉시발행 메모 설정  
	 * 
	 * @param memo
	 * 			메모 
	 */
	public void setMemo(String memo) {
		this.memo = memo;
	}
}

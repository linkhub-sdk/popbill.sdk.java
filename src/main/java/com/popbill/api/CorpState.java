package com.popbill.api;

import java.io.Serializable;

/**
 * Class for Corporation State Information.
 * 
 * @author KimSeongjun
 * @version 1.0.0
 */
public class CorpState implements Serializable {
	
	private static final long serialVersionUID = -8511669128205982297L;
	private String corpNum;
	private String type;
	private String state;
	private String stateDate;
	private String checkDate;
	private String typeDate;
	private String taxType;
	
	/**
	 * @return 확인한 사업자번호
	 */
	public String getCorpNum() {
		return corpNum;
	}
	/**
	 * @return 사업자 유형
	 * 		null : 알수 없음 또는 등록되지 않는 사업자번호
	 * 		1 : 부가가치세 일반과세자
	 * 		2 : 부가가치세 면세과세자
	 * 		3 : 부가가치세 간이과세자
	 * 		4 : 비영리법인 또는 국가기관, 고유번호가 부여된 단체
	 */
	public String getType() {
		return type;
	}
	/**
	 * @return 휴폐업상태
	 * 		null :알수 없음
	 * 		0 : 등록되지 않은 사업자번호
	 * 		1 : 사업중
	 * 		2 : 폐업
	 * 		3 : 휴업
	 */
	public String getState() {
		return state;
	}
	/**
	 * @return 휴폐업 일자
	 * 		State가 휴/폐업일 경우 해당 휴폐업 일자
	 */
	public String getStateDate() {
		return stateDate;
	}
	/**
	 * @return 확인일자
	 * 		해당 사업자의 상태값을 국세청으로부터 확인한 일자
	 */
	public String getCheckDate() {
		return checkDate;
	}
	
	/**
	 * @return 과세유형 전환일자
	 * 		해당 사업자의 과세유형이 전환된 일자
	 */
	public String getTypeDate() {
		return typeDate;
	}
	/**
	 * @return 사업자 과세유형
	 * 		null : 알수 없음 또는 등록되지 않는 사업자번호
	 * 		10 : 부가가치세 일반과세자
	 * 		20 : 부가가치세 면세과세자
	 * 		30 : 부가가치세 간이과세자
	 * 		31 : 부가가치세 간이과세자 (세금계산서 발급사업자)
	 * 		40 : 비영리법인 또는 국가기관, 고유번호가 부여된 단체
	 */
	public String getTaxType() {
		return taxType;
	}
}
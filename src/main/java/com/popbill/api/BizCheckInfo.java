package com.popbill.api;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

/**
 * Class for Corporation State Information.
 * 
 * @author shchoi
 * @version 1.0.0
 */
public class BizCheckInfo implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 3728703768555159337L;

    private String corpNum;

    private String checkDT;

    private String corpName;

    private Integer corpCode;

    private Integer corpScaleCode;

    private Integer personCorpCode;

    private Integer headOfficeCode;

    private String industryCode;

    private String companyRegNum;

    private String establishDate;

    private Integer establishCode;

    @SerializedName(value = "ceoname")
    private String CEOName;

    private Integer workPlaceCode;

    private Integer addrCode;

    private String zipCode;

    private String addr;

    private String addrDetail;

    private String enAddr;

    private String bizClass;

    private String bizType;

    private Integer result;

    private String resultMessage;

    private Integer closeDownTaxType;

    private String closeDownTaxTypeDate;

    private Integer closeDownState;

    private String closeDownStateDate;

    /**
     * @return 확인한 사업자번호
     */
    public String getCorpNum() {
        return corpNum;
    }

    /**
     * @return 확인일시
     *         기업정보 확인 요청 일시
     */
    public String getCheckDT() {
        return checkDT;
    }

    /**
     * @return 상호
     *         확인된 상호
     */
    public String getCorpName() {
        return corpName;
    }

    /**
     * @return 기업형태코드
     *         100 : 일반
     *         101 : 신설
     *         102 : 외감
     *         103 : 피합병
     *         110 : 거래소(상장)
     *         111 : 거래소(관리)
     *         112 : 코스닥(등록)
     *         113 : 코스닥(관리)
     *         114 : 코넥스
     *         200 : 폐업
     *         201 : 부도
     *         300 : 휴업
     *         900 : 미정의
     *         999 : 기타
     */
    public Integer getCorpCode() {
        return corpCode;
    }

    /**
     * @return 기업규모코드
     *         10 : 대기업
     *         11 : 대기업(금융)
     *         20 : 중견기업
     *         21 : 중견기업(하)
     *         30 : 중소기업
     *         90 : 비영리법인
     *         99 : 기타
     */
    public Integer getCorpScaleCode() {
        return corpScaleCode;
    }

    /**
     * @return 개인법인코드
     *         1 : 개인
     *         2 : 법인
     *         99 : 기타
     */
    public Integer getPersonCorpCode() {
        return personCorpCode;
    }

    /**
     * @return 본점지점코드
     *         1 : 본점
     *         2 : 지점
     *         99 : 기타
     */
    public Integer getHeadOfficeCode() {
        return headOfficeCode;
    }

    /**
     * @return 영문주소
     */
    public String getEnAddr() {
        return enAddr;
    }

    /**
     * @return 산업코드
     *         한국표준사업분류 업종코드
     */
    public String getIndustryCode() {
        return industryCode;
    }

    /**
     * @return 법인번호
     */
    public String getCompanyRegNum() {
        return companyRegNum;
    }

    /**
     * @return 설립일자
     */
    public String getEstablishDate() {
        return establishDate;
    }

    /**
     * @return 설립구분코드
     *         1 : 주식
     *         2 : 합자
     *         3 : 합명
     *         4 : 유한
     *         5 : 조합
     *         6 : 정부투자기관
     *         7 : 개인
     *         8 : 학교
     *         9 : 병원
     *         10 : 단체.협회
     *         11 : 미정의
     *         99 : 기타
     */
    public Integer getEstablishCode() {
        return establishCode;
    }

    /**
     * @return 대표자명
     */
    public String getCEOName() {
        return CEOName;
    }

    /**
     * @return 사업장구분코드
     *         10 : 본사
     *         20 : 공장
     *         30 : 서울사무소
     *         31 : 사무소
     *         32 : 영업소
     *         33 : 지점
     *         34 : 출장소
     *         40 : 연구소
     *         50 : 건설공장
     *         60 : 창고
     *         99 : 기타
     */
    public Integer getWorkPlaceCode() {
        return workPlaceCode;
    }

    /**
     * @return 주소구분코드
     *         1 : 도로명
     *         2 : 지번
     *         3 : 입력(입수)
     *         99 : 기타
     */
    public Integer getAddrCode() {
        return addrCode;
    }

    /**
     * @return 우편번호
     */
    public String getZipCode() {
        return zipCode;
    }

    /**
     * @return 주소
     */
    public String getAddr() {
        return addr;
    }

    /**
     * @return 상세주소
     */
    public String getAddrDetail() {
        return addrDetail;
    }

    /**
     * @return 업종
     */
    public String getBizClass() {
        return bizClass;
    }

    /**
     * @return 업태
     */
    public String getBizType() {
        return bizType;
    }

    /**
     * @return 결과코드
     */
    public Integer getResult() {
        return result;
    }

    /**
     * @return 결과메시지
     */
    public String getResultMessage() {
        return resultMessage;
    }

    /**
     * @return 사업자 과세유형
     *         null : 등록되지 않는 사업자번호
     *         10 : 부가가치세 일반과세자
     *         20 : 부가가치세 면세과세자
     *         30 : 부가가치세 간이과세자
     *         31 : 부가가치세 간이과세자 (세금계산서 발급사업자)
     *         40 : 비영리법인 또는 국가기관, 고유번호가 부여된 단체
     *         99 : 미확인 
     */
    public Integer getCloseDownTaxType() {
        return closeDownTaxType;
    }

    /**
     * @return 과세유형 전환일자
     *         해당 사업자의 과세유형이 전환된 일자
     */
    public String getCloseDownTaxTypeDate() {
        return closeDownTaxTypeDate;
    }

    /**
     * @return 휴폐업상태
     *         0 : 등록되지 않은 사업자번호
     *         1 : 사업중
     *         2 : 폐업
     *         3 : 휴업
     *         9 : 미확인
     */
    public Integer getCloseDownState() {
        return closeDownState;
    }

    /**
     * @return 휴폐업 일자
     *         State가 휴/폐업일 경우 해당 휴폐업 일자
     */
    public String getCloseDownStateDate() {
        return closeDownStateDate;
    }

}
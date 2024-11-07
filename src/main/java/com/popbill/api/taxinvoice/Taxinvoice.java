package com.popbill.api.taxinvoice;

import java.io.Serializable;
import java.util.List;

/**
 * Class for Taxinvoice Information.
 * 
 * @author KimSeongjun
 * @version 1.0.0
 */
public class Taxinvoice implements Serializable {
    private static final long serialVersionUID = -4724810536341548575L;

    private Integer closeDownState;
    private String closeDownStateDate;

    private Boolean writeSpecification;
    private Boolean forceIssue;
    private String memo;
    private String emailSubject;
    private String dealInvoiceMgtKey;

    private String writeDate;
    private String chargeDirection;
    private String issueType;
    private String issueTiming;
    private String taxType;
    private String invoicerCorpNum;
    private String invoicerMgtKey;
    private String invoicerTaxRegID;
    private String invoicerCorpName;
    private String invoicerCEOName;
    private String invoicerAddr;
    private String invoicerBizClass;
    private String invoicerBizType;
    private String invoicerContactName;
    private String invoicerDeptName;
    private String invoicerTEL;
    private String invoicerHP;
    private String invoicerEmail;
    private Boolean invoicerSMSSendYN;
    private String invoiceeCorpNum;
    private String invoiceeType;
    private String invoiceeMgtKey;
    private String invoiceeTaxRegID;
    private String invoiceeCorpName;
    private String invoiceeCEOName;
    private String invoiceeAddr;
    private String invoiceeBizType;
    private String invoiceeBizClass;
    private String invoiceeContactName1;
    private String invoiceeDeptName1;
    private String invoiceeTEL1;
    private String invoiceeHP1;
    private String invoiceeEmail1;
    private String invoiceeContactName2;
    private String invoiceeDeptName2;
    private String invoiceeTEL2;
    private String invoiceeHP2;
    private String invoiceeEmail2;
    private Boolean invoiceeSMSSendYN;
    private String trusteeCorpNum;
    private String trusteeMgtKey;
    private String trusteeTaxRegID;
    private String trusteeCorpName;
    private String trusteeCEOName;
    private String trusteeAddr;
    private String trusteeBizType;
    private String trusteeBizClass;
    private String trusteeContactName;
    private String trusteeDeptName;
    private String trusteeTEL;
    private String trusteeHP;
    private String trusteeEmail;
    private Boolean trusteeSMSSendYN;
    private String taxTotal;
    private String supplyCostTotal;
    private String totalAmount;
    private Short modifyCode;
    private String orgNTSConfirmNum;
    private String purposeType;
    private String serialNum;
    private String cash;
    private String chkBill;
    private String credit;
    private String note;
    private String remark1;
    private String remark2;
    private String remark3;
    private Short kwon;
    private Short ho;
    private Boolean businessLicenseYN;
    private Boolean bankBookYN;
    private String ntsconfirmNum;
    private List<TaxinvoiceDetail> detailList;
    private List<TaxinvoiceAddContact> addContactList;
    private String originalTaxinvoiceKey;
    
    private String certCipher;
    private String certPublicKey;
    private String certPrivateKey;
    

    /**
     * 작성일자 확인
     * 
     * @return 작성일자
     */
    public String getWriteDate() {
        return writeDate;
    }

    /**
     * 작성일자 설정
     * 
     * @param writeDate
     *                  작성일자
     */
    public void setWriteDate(String writeDate) {
        this.writeDate = writeDate;
    }

    /**
     * 과금방향 확인
     * 
     * @return 과금방향
     */
    public String getChargeDirection() {
        return chargeDirection;
    }

    /**
     * 과금방향 설정 ( "정과금" or "역과금")
     * 
     * @param chargeDirection
     *                        과금방향
     */
    public void setChargeDirection(String chargeDirection) {
        this.chargeDirection = chargeDirection;
    }

    /**
     * 발행형태 확인
     * 
     * @return 발행형태
     */
    public String getIssueType() {
        return issueType;
    }

    /**
     * 발행형태 설정 ("정발행","역발행","위수탁")
     * 
     * @param issueType
     *                  발행형태
     */
    public void setIssueType(String issueType) {
        this.issueType = issueType;
    }

    /**
     * 발행시점 확인
     * 
     * @return 발행시점 확인
     */
    public String getIssueTiming() {
        return issueTiming;
    }

    /**
     * 발행시점 설정 ("직접발행","승인시자동발행")
     * 
     * @param issueTiming
     *                    발행시점
     */
    public void setIssueTiming(String issueTiming) {
        this.issueTiming = issueTiming;
    }

    /**
     * 과세형태 확인
     * 
     * @return 과세형태
     */
    public String getTaxType() {
        return taxType;
    }

    /**
     * 과세형태 설정 ("과세" or "영세" or "면세")
     * 
     * @param taxType
     *                과세형태
     */
    public void setTaxType(String taxType) {
        this.taxType = taxType;
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
     * 공급자 사업자번호 설정
     * 
     * @param invoicerCorpNum
     *                        공급자 사업자번호
     */
    public void setInvoicerCorpNum(String invoicerCorpNum) {
        this.invoicerCorpNum = invoicerCorpNum;
    }

    /**
     * 공급자 문서번호 확인
     * 
     * @return 공급자 문서번호
     */
    public String getInvoicerMgtKey() {
        return invoicerMgtKey;
    }

    /**
     * 공급자 문서번호 설정
     * 
     * @param invoicerMgtKey
     *                       공급자 문서번호
     */
    public void setInvoicerMgtKey(String invoicerMgtKey) {
        this.invoicerMgtKey = invoicerMgtKey;
    }

    /**
     * 공급자 종사업장 식별번호 확인
     * 
     * @return 공급자 종사업장 식별번호
     */
    public String getInvoicerTaxRegID() {
        return invoicerTaxRegID;
    }

    /**
     * 공급자 종사업장 식별번호 설정
     * 
     * @param invoicerTaxRegID
     *                         공급자 종사업장 식별번호
     */
    public void setInvoicerTaxRegID(String invoicerTaxRegID) {
        this.invoicerTaxRegID = invoicerTaxRegID;
    }

    /**
     * 공급자 상호 확인
     * 
     * @return 공급자 상호
     */
    public String getInvoicerCorpName() {
        return invoicerCorpName;
    }

    /**
     * 공급자 상호 설정
     * 
     * @param invoicerCorpName
     *                         공급자 상호
     */
    public void setInvoicerCorpName(String invoicerCorpName) {
        this.invoicerCorpName = invoicerCorpName;
    }

    /**
     * 공급자 대표자성명 확인
     * 
     * @return 공급자 대표자 성명
     */
    public String getInvoicerCEOName() {
        return invoicerCEOName;
    }

    /**
     * 공급자 대표자성명 설정
     * 
     * @param invoicerCEOName
     *                        공급자 대표자 성명
     */
    public void setInvoicerCEOName(String invoicerCEOName) {
        this.invoicerCEOName = invoicerCEOName;
    }

    /**
     * 공급자 주소 확인
     * 
     * @return 공급자 주소
     */
    public String getInvoicerAddr() {
        return invoicerAddr;
    }

    /**
     * 공급자 주소 설정
     * 
     * @param invoicerAddr
     *                     공급자 주소
     */
    public void setInvoicerAddr(String invoicerAddr) {
        this.invoicerAddr = invoicerAddr;
    }

    /**
     * 공급자 업종 확인
     * 
     * @return 공급자 업종
     */
    public String getInvoicerBizClass() {
        return invoicerBizClass;
    }

    /**
     * 공급자 업종 설정
     * 
     * @param invoicerBizClass
     *                         공급자 업종
     */
    public void setInvoicerBizClass(String invoicerBizClass) {
        this.invoicerBizClass = invoicerBizClass;
    }

    /**
     * 공급자 업태설정
     * 
     * @return 공급자 업태
     */
    public String getInvoicerBizType() {
        return invoicerBizType;
    }

    /**
     * 공급자 업태 설정
     * 
     * @param invoicerBizType
     *                        공급자 업태
     */
    public void setInvoicerBizType(String invoicerBizType) {
        this.invoicerBizType = invoicerBizType;
    }

    /**
     * 공급자 담당자 성명 확인
     * 
     * @return 공급자 담당자 성명
     */
    public String getInvoicerContactName() {
        return invoicerContactName;
    }

    /**
     * 공급자 담당자 성명 설정
     * 
     * @param invoicerContactName
     *                            공급자 담당자 성명
     */
    public void setInvoicerContactName(String invoicerContactName) {
        this.invoicerContactName = invoicerContactName;
    }

    /**
     * 공급자 부서명 확인
     * 
     * @return 공급자 부서명
     */
    public String getInvoicerDeptName() {
        return invoicerDeptName;
    }

    /**
     * 공급자 부서명 설정
     * 
     * @param invoicerDeptName
     *                         공급자 부서명
     */
    public void setInvoicerDeptName(String invoicerDeptName) {
        this.invoicerDeptName = invoicerDeptName;
    }

    /**
     * 공급자 연락처 확인
     * 
     * @return 공급자 연락처
     */
    public String getInvoicerTEL() {
        return invoicerTEL;
    }

    /**
     * 공급자 연락처 설정
     * 
     * @param invoicerTEL
     *                    공급자 연락처
     */
    public void setInvoicerTEL(String invoicerTEL) {
        this.invoicerTEL = invoicerTEL;
    }

    /**
     * 공급자 휴대폰번호 확인
     * 
     * @return 공급자 휴대폰번호
     */
    public String getInvoicerHP() {
        return invoicerHP;
    }

    /**
     * 공급자 휴대폰 번호 설정
     * 
     * @param invoicerHP
     *                   공급자 휴대폰 번호
     */
    public void setInvoicerHP(String invoicerHP) {
        this.invoicerHP = invoicerHP;
    }

    /**
     * 공급자 이메일 확인
     * 
     * @return 공급자 이메일
     */
    public String getInvoicerEmail() {
        return invoicerEmail;
    }

    /**
     * 공급자 이메일 설정
     * 
     * @param invoicerEmail
     *                      공급자 이메일
     */
    public void setInvoicerEmail(String invoicerEmail) {
        this.invoicerEmail = invoicerEmail;
    }

    /**
     * 공급자 발행시 문자전송여부 확인
     * 
     * @return 공급자 문자전송여부
     */
    public Boolean getInvoicerSMSSendYN() {
        return invoicerSMSSendYN;
    }

    /**
     * 공급자 발행시 문자전송여부 설정
     * 
     * @param invoicerSMSSendYN
     *                          공급자 발행시 문자전송여부
     */
    public void setInvoicerSMSSendYN(Boolean invoicerSMSSendYN) {
        this.invoicerSMSSendYN = invoicerSMSSendYN;
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
     * 공급받는자 사업자번호 설정
     * 
     * @param invoiceeCorpNum
     *                        공급받는자 사업자번호
     */
    public void setInvoiceeCorpNum(String invoiceeCorpNum) {
        this.invoiceeCorpNum = invoiceeCorpNum;
    }

    /**
     * 공급받는자 유형 확인
     * 
     * @return 공급받는자 유형
     */
    public String getInvoiceeType() {
        return invoiceeType;
    }

    /**
     * 공급받는자 유형 설정 ("사업자" or "개인" or "외국인")
     * 
     * @param invoiceeType
     *                     공급받는자 유형
     */
    public void setInvoiceeType(String invoiceeType) {
        this.invoiceeType = invoiceeType;
    }

    /**
     * 공급받는자 문서번호 확인
     * 
     * @return 공급받는자 문서번호
     */
    public String getInvoiceeMgtKey() {
        return invoiceeMgtKey;
    }

    /**
     * 공급받는자 문서번호 설정
     * 
     * @param invoiceeMgtKey
     *                       공급받는자 문서번호
     */
    public void setInvoiceeMgtKey(String invoiceeMgtKey) {
        this.invoiceeMgtKey = invoiceeMgtKey;
    }

    /**
     * 공급받는자 종사업장 식별번호 확인
     * 
     * @return 공급받는자 종사업장 식별번호
     */
    public String getInvoiceeTaxRegID() {
        return invoiceeTaxRegID;
    }

    /**
     * 공급받는자 종사업장 식별번호 설정
     * 
     * @param invoiceeTaxRegID
     *                         공급받는자 종사업장 식별번호
     */
    public void setInvoiceeTaxRegID(String invoiceeTaxRegID) {
        this.invoiceeTaxRegID = invoiceeTaxRegID;
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
     * 공급받는자 상호 설정
     * 
     * @param invoiceeCorpName
     *                         공급받는자 상호
     */
    public void setInvoiceeCorpName(String invoiceeCorpName) {
        this.invoiceeCorpName = invoiceeCorpName;
    }

    /**
     * 공급받는자 대표자 성명 확인
     * 
     * @return 공급받는자 대표자 성명
     */
    public String getInvoiceeCEOName() {
        return invoiceeCEOName;
    }

    /**
     * 공급받는자 대표자 성명 설정
     * 
     * @param invoiceeCEOName
     *                        공급받는자 대표자 성명
     */
    public void setInvoiceeCEOName(String invoiceeCEOName) {
        this.invoiceeCEOName = invoiceeCEOName;
    }

    /**
     * 공급받는자 주소 확인
     * 
     * @return 공급받는자 주소
     */
    public String getInvoiceeAddr() {
        return invoiceeAddr;
    }

    /**
     * 공급받는자 주소 설정
     * 
     * @param invoiceeAddr
     *                     공급받는자 주소
     */
    public void setInvoiceeAddr(String invoiceeAddr) {
        this.invoiceeAddr = invoiceeAddr;
    }

    /**
     * 공급받는자 업태 확인
     * 
     * @return 공급받는자 업태
     */
    public String getInvoiceeBizType() {
        return invoiceeBizType;
    }

    /**
     * 공급받는자 업태 설정.
     * 
     * @param invoiceeBizType
     *                        공급받는자 업태
     */
    public void setInvoiceeBizType(String invoiceeBizType) {
        this.invoiceeBizType = invoiceeBizType;
    }

    /**
     * 공급받는자 업종 확인
     * 
     * @return 공급받는자 업종
     */
    public String getInvoiceeBizClass() {
        return invoiceeBizClass;
    }

    /**
     * 공급받는자 업종 설정
     * 
     * @param invoiceeBizClass
     *                         공급받는자 업종
     */
    public void setInvoiceeBizClass(String invoiceeBizClass) {
        this.invoiceeBizClass = invoiceeBizClass;
    }

    /**
     * 공급받는자 담당자1성명 확인
     * 
     * @return 공급받는자 담당자1 성명
     */
    public String getInvoiceeContactName1() {
        return invoiceeContactName1;
    }

    /**
     * 공급받는자 담당자1 성명 설정
     * 
     * @param invoiceeContactName1
     *                             공급받는자 담당자1 성명
     */
    public void setInvoiceeContactName1(String invoiceeContactName1) {
        this.invoiceeContactName1 = invoiceeContactName1;
    }

    /**
     * 공급받는자 담당자1 부서 확인
     * 
     * @return 공급받는자 담당자1 부서
     */
    public String getInvoiceeDeptName1() {
        return invoiceeDeptName1;
    }

    /**
     * 공급받는자 담당자1 부서 설정
     * 
     * @param invoiceeDeptName1
     *                          공급받는자 담당자1 부서
     */
    public void setInvoiceeDeptName1(String invoiceeDeptName1) {
        this.invoiceeDeptName1 = invoiceeDeptName1;
    }

    /**
     * 공급받는자 담당자1 연락처 확인
     * 
     * @return 공급받는자 담당자1 연락처
     */
    public String getInvoiceeTEL1() {
        return invoiceeTEL1;
    }

    /**
     * 공급받는자 담당자1 연락처 설정
     * 
     * @param invoiceeTEL1
     *                     공급받는자 담당자1 연락처
     */
    public void setInvoiceeTEL1(String invoiceeTEL1) {
        this.invoiceeTEL1 = invoiceeTEL1;
    }

    /**
     * 공급받는자 담당자1 휴대전화 확인
     * 
     * @return 공급받는자 담당자1 휴대전화
     */
    public String getInvoiceeHP1() {
        return invoiceeHP1;
    }

    /**
     * 공급받는자 담당자1 휴대전화 설정
     * 
     * @param invoiceeHP1
     *                    공급받는자 담당자1 휴대전화
     */
    public void setInvoiceeHP1(String invoiceeHP1) {
        this.invoiceeHP1 = invoiceeHP1;
    }

    /**
     * 공급받는자 담당자1 이메일주소 확인
     * 
     * @return 공급받는자 담당자1 이메일주소 확인
     */
    public String getInvoiceeEmail1() {
        return invoiceeEmail1;
    }

    /**
     * 공급받는자 담당자1 이메일주소 설정
     * 
     * @param invoiceeEmail1
     *                       공급받는자 담당자1 이메일 주소
     */
    public void setInvoiceeEmail1(String invoiceeEmail1) {
        this.invoiceeEmail1 = invoiceeEmail1;
    }

    /**
     * 공급받는자 담당자2 성명 확인
     * 
     * @return 공급받는자 담당자2 성명
     */
    public String getInvoiceeContactName2() {
        return invoiceeContactName2;
    }

    /**
     * 공급받는자 담당자2 성명 설정
     * 
     * @param invoiceeContactName2
     *                             공급받는자 담당자2 성명
     */
    public void setInvoiceeContactName2(String invoiceeContactName2) {
        this.invoiceeContactName2 = invoiceeContactName2;
    }

    /**
     * 공급받는자 담당자2 부서 확인
     * 
     * @return 공급받는자 담당자2 부서
     */
    public String getInvoiceeDeptName2() {
        return invoiceeDeptName2;
    }

    /**
     * 공급받는자 담당자2 부서 설정
     * 
     * @param invoiceeDeptName2
     *                          공급받는자 담당자2 부서
     */
    public void setInvoiceeDeptName2(String invoiceeDeptName2) {
        this.invoiceeDeptName2 = invoiceeDeptName2;
    }

    /**
     * 공급받는자 담당자2 연락처 확인
     * 
     * @return 공급받는자 담당자2 연락처
     */
    public String getInvoiceeTEL2() {
        return invoiceeTEL2;
    }

    /**
     * 공급받는자 담당자2 연락처 설정
     * 
     * @param invoiceeTEL2
     *                     공급받는자 담당자2 연락처
     */
    public void setInvoiceeTEL2(String invoiceeTEL2) {
        this.invoiceeTEL2 = invoiceeTEL2;
    }

    /**
     * 공급받는자 담당자2 휴대전화 확인
     * 
     * @return 공급받는자 담당자2 휴대전화
     */
    public String getInvoiceeHP2() {
        return invoiceeHP2;
    }

    /**
     * 공급받는자 담당자2 휴대전화 설정
     * 
     * @param invoiceeHP2
     *                    공급받는자 담당자2 휴대전화
     */
    public void setInvoiceeHP2(String invoiceeHP2) {
        this.invoiceeHP2 = invoiceeHP2;
    }

    /**
     * 공급받는자 담당자2 이메일주소 확인
     * 
     * @return 공급받는자 담당자2 이메일주소
     */
    public String getInvoiceeEmail2() {
        return invoiceeEmail2;
    }

    /**
     * 공급받는자 담당자2 이메일주소 설정
     * 
     * @param invoiceeEmail2
     *                       공급받는자 담당자2 이메일주소
     */
    public void setInvoiceeEmail2(String invoiceeEmail2) {
        this.invoiceeEmail2 = invoiceeEmail2;
    }

    /**
     * 공급받는자 역발행요청시 문자전송여부 확인
     * 
     * @return 공급받는자 역발행요청시 문자전송여부
     */
    public Boolean getInvoiceeSMSSendYN() {
        return invoiceeSMSSendYN;
    }

    /**
     * 공급받는자 역발행요청시 문자전송여부 설정
     * 
     * @param invoiceeSMSSendYN
     *                          공급받는자 역발행요청시 문자전송여부
     */
    public void setInvoiceeSMSSendYN(Boolean invoiceeSMSSendYN) {
        this.invoiceeSMSSendYN = invoiceeSMSSendYN;
    }

    /**
     * 수탁자 사업자번호 확인
     * 
     * @return 수탁자 사업자번호
     */
    public String getTrusteeCorpNum() {
        return trusteeCorpNum;
    }

    /**
     * 수탁자 사업자번호 설정
     * 
     * @param trusteeCorpNum
     *                       수탁자 사업자번호
     */
    public void setTrusteeCorpNum(String trusteeCorpNum) {
        this.trusteeCorpNum = trusteeCorpNum;
    }

    /**
     * 수탁자 문서번호 확인
     * 
     * @return 수탁자 문서번호
     */
    public String getTrusteeMgtKey() {
        return trusteeMgtKey;
    }

    /**
     * 수탁자 문서번호 설정
     * 
     * @param trusteeMgtKey
     *                      수탁자 문서번호
     */
    public void setTrusteeMgtKey(String trusteeMgtKey) {
        this.trusteeMgtKey = trusteeMgtKey;
    }

    /**
     * 수탁자 종사업장 식별번호 확인
     * 
     * @return 수탁자 종사업장 식별번호
     */
    public String getTrusteeTaxRegID() {
        return trusteeTaxRegID;
    }

    /**
     * 수탁자 종사업장 식별번호 설정
     * 
     * @param trusteeTaxRegID
     *                        수탁자 종사업장 식별번호 설정
     */
    public void setTrusteeTaxRegID(String trusteeTaxRegID) {
        this.trusteeTaxRegID = trusteeTaxRegID;
    }

    /**
     * 수탁자 상호 확인
     * 
     * @return 수탁자 상호
     */
    public String getTrusteeCorpName() {
        return trusteeCorpName;
    }

    /**
     * 수탁자 상호 설정
     * 
     * @param trusteeCorpName
     *                        수탁자 상호
     */
    public void setTrusteeCorpName(String trusteeCorpName) {
        this.trusteeCorpName = trusteeCorpName;
    }

    /**
     * 수탁자 대표자성명 확인
     * 
     * @return 수탁자 대표자 성명
     */
    public String getTrusteeCEOName() {
        return trusteeCEOName;
    }

    /**
     * 수탁자 대표자 성명 설정
     * 
     * @param trusteeCEOName
     *                       수탁자 대표자 성명
     */
    public void setTrusteeCEOName(String trusteeCEOName) {
        this.trusteeCEOName = trusteeCEOName;
    }

    /**
     * 수탁자 주소 확인
     * 
     * @return 수탁자 주소
     */
    public String getTrusteeAddr() {
        return trusteeAddr;
    }

    /**
     * 수탁자 주소 설정
     * 
     * @param trusteeAddr
     *                    수탁자 주소
     */
    public void setTrusteeAddr(String trusteeAddr) {
        this.trusteeAddr = trusteeAddr;
    }

    /**
     * 수탁자 업태 확인
     * 
     * @return 수탁자 업태
     */
    public String getTrusteeBizType() {
        return trusteeBizType;
    }

    /**
     * 수탁자 업태 설정
     * 
     * @param trusteeBizType
     *                       수탁자 업태
     */
    public void setTrusteeBizType(String trusteeBizType) {
        this.trusteeBizType = trusteeBizType;
    }

    /**
     * 수탁자 업종 확인
     * 
     * @return 수탁자 업종
     */
    public String getTrusteeBizClass() {
        return trusteeBizClass;
    }

    /**
     * 수탁자 업종 설정
     * 
     * @param trusteeBizClass
     *                        수탁자 업종 설정
     */
    public void setTrusteeBizClass(String trusteeBizClass) {
        this.trusteeBizClass = trusteeBizClass;
    }

    /**
     * 수탁자 담당자 성명 확인
     * 
     * @return 수탁자 담당자 성명
     */
    public String getTrusteeContactName() {
        return trusteeContactName;
    }

    /**
     * 수탁자 담당자 성명 설정
     * 
     * @param trusteeContactName
     *                           수탁자 담당자 성명
     */
    public void setTrusteeContactName(String trusteeContactName) {
        this.trusteeContactName = trusteeContactName;
    }

    /**
     * 수탁자 담당자 부서명 확인
     * 
     * @return 수탁자 담당자 부서명
     */
    public String getTrusteeDeptName() {
        return trusteeDeptName;
    }

    /**
     * 수탁자 담당자 부서명 설정
     * 
     * @param trusteeDeptName
     *                        수탁자 담당자 부서명
     */
    public void setTrusteeDeptName(String trusteeDeptName) {
        this.trusteeDeptName = trusteeDeptName;
    }

    /**
     * 수탁자 담당자 연락처 확인
     * 
     * @return 수탁자 담당자 연락처
     */
    public String getTrusteeTEL() {
        return trusteeTEL;
    }

    /**
     * 수탁자 담당자 연락처 설정
     * 
     * @param trusteeTEL
     *                   수탁자 담당자 연락처
     */
    public void setTrusteeTEL(String trusteeTEL) {
        this.trusteeTEL = trusteeTEL;
    }

    /**
     * 수탁자 담당자 휴대전화 확인
     * 
     * @return 수탁자 담당자 휴대전화
     */
    public String getTrusteeHP() {
        return trusteeHP;
    }

    /**
     * 수탁자 담당자 휴대전화 설정
     * 
     * @param trusteeHP
     *                  수탁자 담당자 휴대전화
     */
    public void setTrusteeHP(String trusteeHP) {
        this.trusteeHP = trusteeHP;
    }

    /**
     * 수탁자 담당자 이메일주소 확인
     * 
     * @return 수탁자 담당자 이메일주소
     */
    public String getTrusteeEmail() {
        return trusteeEmail;
    }

    /**
     * 수탁자 담당자 이메일주소 설정
     * 
     * @param trusteeEmail
     *                     수탁자 담당자 이메일주소
     */
    public void setTrusteeEmail(String trusteeEmail) {
        this.trusteeEmail = trusteeEmail;
    }

    /**
     * 수탁자 발행시 문자전송여부 확인
     * 
     * @return 수탁자 발행시 문자전송여부
     */
    public Boolean getTrusteeSMSSendYN() {
        return trusteeSMSSendYN;
    }

    /**
     * 수탁자 발행시 문자전송여부 설정
     * 
     * @param trusteeSMSSendYN
     *                         수탁자 발행시 문자전송여부
     */
    public void setTrusteeSMSSendYN(Boolean trusteeSMSSendYN) {
        this.trusteeSMSSendYN = trusteeSMSSendYN;
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
     *                 세액합계
     */
    public void setTaxTotal(String taxTotal) {
        this.taxTotal = taxTotal;
    }

    /**
     * 공급가액 합계 확인
     * 
     * @return 공급가액 합계
     */
    public String getSupplyCostTotal() {
        return supplyCostTotal;
    }

    /**
     * 공급가액 합계 설정
     * 
     * @param supplyCostTotal
     *                        공급가액 합계
     */
    public void setSupplyCostTotal(String supplyCostTotal) {
        this.supplyCostTotal = supplyCostTotal;
    }

    /**
     * 합계금액(공급가액 합계 + 세액 합계) 확인
     * 
     * @return 합계금액
     */
    public String getTotalAmount() {
        return totalAmount;
    }

    /**
     * 합계금액 설정
     * 
     * @param totalAmount
     *                    합계금액
     */
    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    /**
     * 수정사유코드 확인
     * 
     * @return 수정사유코드
     */
    public Short getModifyCode() {
        return modifyCode;
    }

    /**
     * 수정사유코드 설정 1 : 기재사항의 착오/정정 2 : 공급가액 변동 3 : 환입 4 : 계약의 해지 5 : 내국신용장 사후개설 6
     * : 착오에 의한 이중발행
     * 
     * @param modifyCode
     *                   수정사유코드
     */
    public void setModifyCode(Short modifyCode) {
        this.modifyCode = modifyCode;
    }

    /**
     * 원본 국세청 승인번호 확인
     * 
     * @return 원본 국세청 승인번호
     */
    public String getOrgNTSConfirmNum() {
        return orgNTSConfirmNum;
    }

    /**
     * 영수/청구 여부 확인
     * 
     * @return 영수/청구 여부
     */
    public String getPurposeType() {
        return purposeType;
    }

    /**
     * 영수/청구 여부 설정 ("영수" or "청구")
     * 
     * @param purposeType
     *                    영수/청구 여부
     */
    public void setPurposeType(String purposeType) {
        this.purposeType = purposeType;
    }

    /**
     * 일련번호 확인
     * 
     * @return 일련번호
     */
    public String getSerialNum() {
        return serialNum;
    }

    /**
     * 일련번호 설정
     * 
     * @param serialNum
     *                  일련번호
     */
    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum;
    }

    /**
     * 현금 항목 확인
     * 
     * @return 현금 항목
     */
    public String getCash() {
        return cash;
    }

    /**
     * 현금 항목 설정
     * 
     * @param cash
     *             현금 항목
     */
    public void setCash(String cash) {
        this.cash = cash;
    }

    /**
     * 수표 항목 확인
     * 
     * @return 수표 항목
     */
    public String getChkBill() {
        return chkBill;
    }

    /**
     * 수표 항목 설정
     * 
     * @param chkBill
     *                수표
     */
    public void setChkBill(String chkBill) {
        this.chkBill = chkBill;
    }

    /**
     * 외상 항목 확인
     * 
     * @return 외상 항목
     */
    public String getCredit() {
        return credit;
    }

    /**
     * 외상 항목 설정
     * 
     * @param credit
     *               외상 항목
     */
    public void setCredit(String credit) {
        this.credit = credit;
    }

    /**
     * 어음 항목 확인
     * 
     * @return 어음 항목
     */
    public String getNote() {
        return note;
    }

    /**
     * 어음 항목 설정
     * 
     * @param note
     *             어음 항목
     */
    public void setNote(String note) {
        this.note = note;
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
     *                비고1
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
     *                비고2
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
     *                비고3
     */
    public void setRemark3(String remark3) {
        this.remark3 = remark3;
    }

    /**
     * 권 항목 확인
     * 
     * @return 권 항목
     */
    public Short getKwon() {
        return kwon;
    }

    /**
     * 권 항목 설정
     * 
     * @param kwon
     *             권 항목
     */
    public void setKwon(Short kwon) {
        this.kwon = kwon;
    }

    /**
     * 호 항목 확인
     * 
     * @return 호 항목
     */
    public Short getHo() {
        return ho;
    }

    /**
     * 호 항목 설정
     * 
     * @param ho
     *           호 항목
     */
    public void setHo(Short ho) {
        this.ho = ho;
    }

    /**
     * 사업자등록증 이미지 첨부 여부 확인
     * 
     * @return 사업자등록증 이미지 첨부여부
     */
    public Boolean getBusinessLicenseYN() {
        return businessLicenseYN;
    }

    /**
     * 사업자등록증 이미지 첨부여부 설정
     * 
     * @param businessLicenseYN
     *                          사업자등록증 이미지 첨부여부
     */
    public void setBusinessLicenseYN(Boolean businessLicenseYN) {
        this.businessLicenseYN = businessLicenseYN;
    }

    /**
     * 통장사본 이미지 첨부여부 확인
     * 
     * @return 통장사본 이미지 첨부여부
     */
    public Boolean getBankBookYN() {
        return bankBookYN;
    }

    /**
     * 통장사본 이미지 첨부여부 설정
     * 
     * @param bankBookYN
     *                   통장사본 이미지 첨부여부
     */
    public void setBankBookYN(Boolean bankBookYN) {
        this.bankBookYN = bankBookYN;
    }

    /**
     * 국세청 승인번호 확인
     * 
     * @return 국세청 승인번호
     */
    public String getNTSConfirmNum() {
        return ntsconfirmNum;
    }

    /**
     * 상세항목 리스트 확인
     * 
     * @return 상세항목 리스트
     */
    public List<TaxinvoiceDetail> getDetailList() {
        return detailList;
    }

    /**
     * 상세항목 리스트 설정
     * 
     * @param detailList
     *                   상세항목리스트
     */
    public void setDetailList(List<TaxinvoiceDetail> detailList) {
        this.detailList = detailList;
    }

    /**
     * 추가담당자 목록 확인
     * 
     * @return 추가담당자 목록
     */
    public List<TaxinvoiceAddContact> getAddContactList() {
        return addContactList;
    }

    /**
     * 추가담당자 목록 설정
     * 
     * @param addContactList
     *                       추가담당자 목록
     */
    public void setAddContactList(List<TaxinvoiceAddContact> addContactList) {
        this.addContactList = addContactList;
    }

    /**
     * 원본 세금계산서 팝빌 관리번호 확인
     * 
     * @return 원본 세금계산서 팝빌 관리번호
     */
    public String getOriginalTaxinvoiceKey() {
        return originalTaxinvoiceKey;
    }

    /**
     * 원본세금계산서 팝빌 관리번호 설정 (수정세금계산서 작성시 필수 기재)
     * 
     * @param originalTaxinvoiceKey
     *                              원본세금계산서 팝빌 관리번호
     */
    public void setOriginalTaxinvoiceKey(String originalTaxinvoiceKey) {
        this.originalTaxinvoiceKey = originalTaxinvoiceKey;
    }

    /**
     * 세금계산서 즉시발행시 지연발행 강제여부 설정
     * 
     * @param forceIssue
     *                   지연발행 세금계산서 강제여부
     */
    public void setForceIssue(Boolean forceIssue) {
        this.forceIssue = forceIssue;
    }

    /**
     * 즉시발행시 메모 설정
     * 
     * @param memo
     *             즉시발행 메모
     */
    public void setMemo(String memo) {
        this.memo = memo;
    }

    /**
     * 즉시발행시 안내메일 제목 설정
     * 
     * @param emailSubject
     *                     발행안내메일 제목, 미기재시 기본제목으로 전송
     */
    public void setEmailSubject(String emailSubject) {
        this.emailSubject = emailSubject;
    }

    /**
     * 거래명세서 동시작성시 거래명세서 문서번호 설정
     * 
     * @param dealInvoiceMgtKey
     *                          거래명세서 문서번호
     */
    public void setDealInvoiceMgtKey(String dealInvoiceMgtKey) {
        this.dealInvoiceMgtKey = dealInvoiceMgtKey;
    }

    /**
     * 거래명세서 동시작성 여부 설정
     * 
     * @param writeSpecification
     *                           거래명세서 동시작성여부
     */
    public void setWriteSpecification(Boolean writeSpecification) {
        this.writeSpecification = writeSpecification;
    }

    /**
     * 거래명세서 동시작성 여부
     * 
     * @return 거래명세서 동시작성 여부
     */
    public Boolean getWriteSpecification() {
        return writeSpecification;
    }

    /**
     * 지연발행 강제여부
     * 
     * @return 지연발행 강제여부
     */
    public Boolean getForceIssue() {
        return forceIssue;
    }

    /**
     * 메모
     * 
     * @return 즉시발행 메모
     */
    public String getMemo() {
        return memo;
    }

    /**
     * 발행안내메일 제목
     * 
     * @return 발행안내 메일 제목
     */
    public String getEmailSubject() {
        return emailSubject;
    }

    /**
     * 거래명세서 동시작성시 거래명세서 문서번호
     * 
     * @return 거래명세서 문서번호
     */
    public String getDealInvoiceMgtKey() {
        return dealInvoiceMgtKey;
    }

    /**
     * 세금계산서 발행시 공급받는자 휴폐업상태
     * 
     * @return 휴폐업 상태
     */
    public Integer getCloseDownState() {
        return closeDownState;
    }

    /**
     * 세금계산서 발행시 공급받는자 휴폐업일자
     * 
     * @return 공급받는자 휴폐업일자
     */
    public String getCloseDownStateDate() {
        return closeDownStateDate;
    }

    public void setOrgNTSConfirmNum(String orgNTSConfirmNum) {
        this.orgNTSConfirmNum = orgNTSConfirmNum;
    }

	public String getCertCipher() {
		return certCipher;
	}

	public void setCertCipher(String certCipher) {
		this.certCipher = certCipher;
	}

	public String getCertPublicKey() {
		return certPublicKey;
	}

	public void setCertPublicKey(String certPublicKey) {
		this.certPublicKey = certPublicKey;
	}

	public String getCertPrivateKey() {
		return certPrivateKey;
	}

	public void setCertPrivateKey(String certPrivateKey) {
		this.certPrivateKey = certPrivateKey;
	}
}

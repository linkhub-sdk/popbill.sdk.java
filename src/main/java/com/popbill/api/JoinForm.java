package com.popbill.api;

/**
 * JoinForm Structure. for joinMember().
 * 
 * @author KimSeongjun
 * @version 1.0.0
 */
public class JoinForm {
    private String ID;
    private String PWD;
    private String Password;
    private String LinkID;
    private String CorpNum;
    private String CEOName;
    private String CorpName;
    private String Addr;
    private String ZipCode;
    private String BizType;
    private String BizClass;
    private String ContactName;
    private String ContactTEL;
    private String ContactHP;
    private String ContactFAX;
    private String ContactEmail;

    /**
     * 회원아이디 반환
     * 
     * @return 회원아이디
     */
    public String getID() {
        return ID;
    }

    /**
     * 회원아이디 설정
     * 
     * @param iD
     *      회원아이디, 6자이상 20자미만, 영문(대/소문자), 숫자, '_', '-' 조합으로 구성 
     */
    public void setID(String iD) {
        ID = iD;
    }

    /**
     * 회원 비밀번호 반환.
     * 
     * @return 비밀번호
     */
    public String getPWD() {
        return PWD;
    }

    /**
     * 회원 비밀번호 설정
     * 
     * @param pWD
     *      최대 20자리 
     */
    public void setPWD(String pWD) {
        PWD = pWD;
    }
    
    /**
     * 회원 비밀번호 설정
     * 
     * @param Password
     *      8자 이상 20이하, 영문, 숫자, 특수문자 조합 
     */
    public void setPassword(String password) {
        Password = password;
    }

    /**
     * 링크아이디 반환
     * 
     * @return 링크아이디
     */
    public String getLinkID() {
        return LinkID;
    }

    /**
     * 링크아이디 설정
     * 
     * @param linkID
     *            링크아이디
     */
    public void setLinkID(String linkID) {
        this.LinkID = linkID;
    }

    /**
     * 회원 사업자번호 확인
     * 
     * @return 사업자번호
     */
    public String getCorpNum() {
        return CorpNum;
    }

    /**
     * 사업자번호 설정
     * 
     * @param corpNum
     *            사업자번호
     */
    public void setCorpNum(String corpNum) {
        this.CorpNum = corpNum;
    }

    /**
     * 대표자성명 확인.
     * 
     * @return 대표자 성명
     */
    public String getCEOName() {
        return CEOName;
    }

    /**
     * 대표자성명 설정
     * 
     * @param CEOName
     *        대표자 성명, 최대 30자리
     */
    public void setCEOName(String cEOName) {
        CEOName = cEOName;
    }

    /**
     * 상호 확인
     * 
     * @return 상호
     */
    public String getCorpName() {
        return CorpName;
    }

    /**
     * 상호 설정
     * 
     * @param corpName
     *        상호, 최대 70자리 
     */
    public void setCorpName(String corpName) {
        CorpName = corpName;
    }

    /**
     * 주소 확인
     * 
     * @return 주소
     */
    public String getAddr() {
        return Addr;
    }

    /**
     * 주소 설정
     * 
     * @param addr
     *      주소, 최대 300자리 
     */
    public void setAddr(String addr) {
        Addr = addr;
    }

    /**
     * 우편번호 확인
     * 
     * @return 우편번호
     */
    public String getZipCode() {
        return ZipCode;
    }

    /**
     * 우편번호 설정
     * 
     * @param zipCode
     *        우편번호, 최대 10자리 
     */
    public void setZipCode(String zipCode) {
        ZipCode = zipCode;
    }

    /**
     * 업태 확인
     * 
     * @return 업태
     */
    public String getBizType() {
        return BizType;
    }

    /**
     * 업태 설정
     * 
     * @param bizType
     *       업태, 최대 40자리 
     */
    public void setBizType(String bizType) {
        BizType = bizType;
    }

    /**
     * 업종 확인
     * 
     * @return 업종
     */
    public String getBizClass() {
        return BizClass;
    }

    /**
     * 업종 설정
     * 
     * @param bizClass
     *       업종, 최대 40자리 
     */
    public void setBizClass(String bizClass) {
        BizClass = bizClass;
    }

    /**
     * 담당자명 확인
     * 
     * @return 담당자명
     */
    public String getContactName() {
        return ContactName;
    }

    /**
     * 담당자명 설정
     * 
     * @param contactName
     *        담당자명, 최대 30자리 
     */
    public void setContactName(String contactName) {
        ContactName = contactName;
    }

    /**
     * 담당자 연락처 확인
     * 
     * @return 담당자 연락처 
     */
    public String getContactTEL() {
        return ContactTEL;
    }

    /**
     * 담당자 연락처 설정
     * 
     * @param contactTEL
     *        담당자 연락처, 최대 20자리 
     */
    public void setContactTEL(String contactTEL) {
        ContactTEL = contactTEL;
    }

    /**
     * 담당자 휴대전화 확인
     * 
     * @return 담당자 휴대전화
     */
    public String getContactHP() {
        return ContactHP;
    }

    /**
     * 담당자 휴대전화 설정
     * 
     * @param contactHP
     *        담당자 휴대전화, 최대 20자리 
     */
    public void setContactHP(String contactHP) {
        ContactHP = contactHP;
    }

    /**
     * 담당자 팩스번호 확인
     * 
     * @return 담당자 팩스번호
     */
    public String getContactFAX() {
        return ContactFAX;
    }

    /**
     * 담당자 팩스번호 설정
     * 
     * @param contactFAX
     *        담당자 팩스번호, 최대 20자리 
     */
    public void setContactFAX(String contactFAX) {
        ContactFAX = contactFAX;
    }

    /**
     * 담당자 이메일 확인
     * 
     * @return 담당자 이메일
     */
    public String getContactEmail() {
        return ContactEmail;
    }

    /**
     * 담당자 이메일 설정
     * 
     * @param contactEmail
     *        담당자 이메일, 최대 70자리 
     */
    public void setContactEmail(String contactEmail) {
        ContactEmail = contactEmail;
    }

}

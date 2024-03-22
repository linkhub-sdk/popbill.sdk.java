package com.popbill.api;

/**
 * BizInfoCheck Service Interface.
 *
 * @author shchoi
 * @version 1.0.0
 */
public interface BizInfoCheckService extends BaseService {

    /**
     * 회원의 기업정보 조회단가 확인
     *
     * @param CorpNum 연동회원 사업자번호
     * @return 단가 (ex. 55.0)
     * @throws PopbillException
     */
    public float getUnitCost(String CorpNum) throws PopbillException;

    /**
     * 기업정보 조회
     *
     * @param CorpNum 연동회원 사업자번호
     * @param CheckCorpNum  휴폐업을 조회하고자 하는 사업자번호
     * @return 기업정보정보
     */
    BizCheckInfo CheckBizInfo(String CorpNum, String CheckCorpNum) throws PopbillException;

    /**
     * 기업정보 조회
     *
     * @param CorpNum 연동회원 사업자번호
     * @param CheckCorpNum  휴폐업을 조회하고자 하는 사업자번호
     * @param UserID        연동회원아이디
     * @return 기업정보정보
     */
    BizCheckInfo CheckBizInfo(String CorpNum, String CheckCorpNum, String UserID) throws PopbillException;

    /**
     * 과금정보 확인
     *
     * @param CorpNum 연동회원 사업자번호
     * @return 과금정보 (see. com.popbill.api.ChargeInfo)
     * @throws PopbillException
     */
    public ChargeInfo getChargeInfo(String CorpNum) throws PopbillException;

    /**
     * 과금정보 확인
     *
     * @param CorpNum 연동회원 사업자번호
     * @param UserID  팝빌회원 아이디
     * @return 과금정보 (see. com.popbill.api.ChargeInfo)
     * @throws PopbillException
     */
    public ChargeInfo getChargeInfo(String CorpNum, String UserID) throws PopbillException;
}
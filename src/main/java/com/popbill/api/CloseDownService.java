package com.popbill.api;


/**
 * CloseDown Service Interface.
 * 
 * @author KimSeongjun
 * @version 1.0.0
 */
public interface CloseDownService extends BaseService {

    /**
     * 회원의 사업자등록상태조회 단가 확인
     * 
     * @param CorpNum
     *            팝빌회원 사업자번호
     * @return 단가 (ex. 55.0)
     * @throws PopbillException
     */
    public float getUnitCost(String CorpNum) throws PopbillException;

    public float getUnitCost(String CorpNum, String UserID) throws PopbillException;

    /**
     * 사업자등록상태조회
     * @param CorpNum
     *              팝빌회원 사업자번호
     * @param CheckCorpNum 
     *              사업자등록상태를 조회하고자 하는 사업자번호
     * @return 사업자등록상태정보
     */
    CorpState CheckCorpNum(String CorpNum, String CheckCorpNum) throws PopbillException;

    /**
     * 사업자등록상태조회
     * @param CorpNum
     *              팝빌회원 사업자번호
     * @param CheckCorpNum
     *              사업자등록상태를 조회하고자 하는 사업자번호
     * @param UserID
     *              팝빌회원 아이디
     * @return 사업자등록상태정보
     */
    CorpState CheckCorpNum(String CorpNum, String CheckCorpNum, String UserID) throws PopbillException;

    /**
     * 사업자등록상태 다량 조회
     * @param CorpNum
     *          팝빌회원 사업자번호
     * @param CorpNumList 
     *          사업자등록상태를 조회하고자 하는 사업자번호 목록
     * @return 사업자등록상태정보 목록
     */
    CorpState[] CheckCorpNum(String CorpNum, String[] CorpNumList) throws PopbillException;

    /**
     * 사업자등록상태 다량 조회
     * @param CorpNum
     *          팝빌회원 사업자번호
     * @param CorpNumList
     *          사업자등록상태를 조회하고자 하는 사업자번호 목록
     * @param UserID
     *          팝빌회원 아이디
     * @return 사업자등록상태정보 목록
     */
    CorpState[] CheckCorpNum(String CorpNum, String[] CorpNumList, String UserID) throws PopbillException;

    /**
     * 과금정보 확인
     * @param CorpNum
     *          팝빌회원 사업자번호
     * @return 과금정보 (see. com.popbill.api.ChargeInfo)
     * @throws PopbillException
     */
    public ChargeInfo getChargeInfo(String CorpNum) throws PopbillException;

    /**
     * 과금정보 확인
     * @param CorpNum
     *          팝빌회원 사업자번호
     * @param UserID
     *          팝빌회원 아이디
     * @return 과금정보 (see. com.popbill.api.ChargeInfo)
     * @throws PopbillException
     */
    public ChargeInfo getChargeInfo(String CorpNum, String UserID) throws PopbillException;
}
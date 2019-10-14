/*
 * Copyright 2006-2014 linkhub.co.kr, Inc. or its affiliates. All Rights Reserved.
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
package com.popbill.api;

import java.io.InputStream;

import com.popbill.api.statement.Statement;
import com.popbill.api.statement.StatementInfo;
import com.popbill.api.statement.StatementLog;
import com.popbill.api.statement.StmtSearchResult;

/**
 * Statement Service Interface.
 * 
 * @author JeongYoHan
 * @version 1.0.0
 */
public interface StatementService extends BaseService{

	/**
	 * 팝빌 전자명세서 관련 URL 확인, 반환된 URL은 30초이내에 브라우저에 표시해야 함.
	 * 
	 * @param CorpNum 
	 * 			연동회원 사업자번호
	 * @param TOGO
	 * 			지정값(TBOX : 임시 문서함 URL, SBOX : 발행 문서함 URL)
	 * @return 팝빌 URL(AccessToken값 포함, Token값은 응답후 30초까지만 유효함)
	 * @throws PopbillException
	 */
	public String getURL(String CorpNum, String TOGO)
			throws PopbillException;
	
	/**
	 * 팝빌 전자명세서 관련 URL 확인, 반환된 URL은 30초이내에 브라우저에 표시해야 함.
	 * 
	 * @param CorpNum 
	 * 			연동회원 사업자번호
	 * @param UserID 
	 * 			연동회원의 회원아이디
	 * @param TOGO
	 * 			지정값(TBOX : 임시 문서함 URL, SBOX : 발행 문서함 URL)
	 * @return 팝빌 URL(AccessToken값 포함, Token값은 응답후 30초까지만 유효함)
	 * @throws PopbillException
	 */
	public String getURL(String CorpNum, String UserID, String TOGO)
			throws PopbillException;

	/**
	 * 명세서 발행단가 확인
	 * 
	 * @param CorpNum	
	 * 			연동회원 사업자번호
	 * @param ItemCode
	 * 			명세서 코드
	 * 			121 : 거래명세서
	 * 			122 : 청구서
	 * 			123 : 견적서
	 * 			124 : 발주서
	 * 			125 : 입금표
	 * 			126 : 영수증
	 * 
	 * @return 단가(ex. 200.0)
	 * @throws PopbillException
	 */
	public float getUnitCost(String CorpNum, int ItemCode) 
			throws PopbillException;
	
	
	/**
	 * 문서관리번호 사용여부 확인
	 * 
	 * @param CorpNum
	 * 			연동회원 사업자번호
	 * @param ItemCode	
	 * 			명세서 코드
	 * @param MgtKey
	 * 			문서관리번호
	 * @return 사용여부, true : 사용중 / false : 미사용중
	 * @throws PopbillException
	 * 
	 */

	public boolean checkMgtKeyInUse(String CorpNum, int ItemCode, 
			String MgtKey) throws PopbillException;
	
	/**
	 * 명세서 1건 임시저장
	 * 
	 * @param CorpNum
	 * 			연동회원 사업자번호
	 * @param statement
	 * 			명세서 정보.(see. com.popbill.api.statement.Statement)
	 * @return Response
	 * @throws PopbillException
	 */
	public Response register(String CorpNum, Statement statement)
			throws PopbillException;
	
	/**
	 * 명세서 1건 임시저장
	 * 
	 * @param CorpNum
	 * 			연동회원 사업자번호
	 * @param statement		
	 * 			명세서 정보.(see. com.popbill.api.statement.Statement)
	 * @param UserID
	 * 			연동회원아이디
	 * @return Response 
	 * @throws PopbillException
	 */
	public Response register(String CorpNum, Statement statement, 
			String UserID) throws PopbillException;
	
	/**
	 * 임시저장된 전자명세서 정보 수정
	 * 
	 * @param CorpNum
	 * 			연동회원 사업자번호
	 * @param ItemCode
	 * 			명세서 코드
	 * @param MgtKey
	 * 			문서관리번호
	 * @param statement
	 * 			명세서 정보.(see. com.popbill.api.statement.Statement)
	 * @return Response
	 * @throws PopbillException
	 */
	public Response update(String CorpNum, int ItemCode, 
			String MgtKey, Statement statement) throws PopbillException;
	
	/**
	 * 임시저장된 전자명세서 정보 수정
	 * 
	 * @param CorpNum
	 * 			연동회원 사업자번호
	 * @param ItemCode
	 * 			명세서 코드
	 * @param MgtKey 
	 * 			문서관리번호
	 * @param statement
	 * 			명세서 정보.(see. com.popbill.api.statement.Statement)
	 * @param UserID
	 * 			연동회원아이디
	 * @return Response 
	 * @throws PopbillException
	 */
	public Response update(String CorpNum, int ItemCode, 
			String MgtKey, Statement statement, String UserID) 
			throws PopbillException;
	
	/**
	 * 명세서 삭제, 삭제가능한 건만 삭제처리되고, 삭제 불가능한 상태의 경우 PopbillException 발생함.
	 * 
	 * @param CorpNum
	 * 			연동회원 사업자번호
	 * @param ItemCode
	 * 			명세서 코드
	 * @param MgtKey
	 * 			문서관리번호
	 * 
	 * @return Response 응답
	 * @throws PopbillException
	 */
	public Response delete(String CorpNum, int ItemCode, 
			String MgtKey) throws PopbillException;
	
	/**
	 * 명세서 삭제, 삭제가능한 건만 삭제처리되고, 삭제 불가능한 상태의 경우 PopbillException 발생함.
	 * 
	 * @param CorpNum
	 * 			연동회원 사업자번호
	 * @param ItemCode
	 * 			명세서 코드
	 * @param MgtKey
	 * 			문서관리번호
	 * @param UserID
	 * 			연동회원의 회원아이디
	 * 
	 * @return Response 응답
	 * @throws PopbillException
	 */
	public Response delete(String CorpNum, int ItemCode, 
			String MgtKey, String UserID) throws PopbillException;
	
	/**
	 * 명세서 발행
	 * 
	 * @param CorpNum
	 * 			연동회원 사업자번호
	 * @param ItemCode
	 * 			명세서 코드
	 * @param MgtKey
	 * 			문서관리번호
	 * @param Memo
	 * 			처리 메모
	 * @return Response
	 * @throws PopbillException
	 */
	public Response issue(String CorpNum, int ItemCode,
			String MgtKey, String Memo) throws PopbillException;
	
	/**
	 * 명세서 발행
	 * 
	 * @param CorpNum
	 * 			연동회원 사업자번호
	 * @param ItemCode
	 * 			명세서 코드
	 * @param MgtKey
	 * 			문서관리번호
	 * @param Memo
	 * 			처리 메모
	 * @param UserID
	 * 			연동회원의 회원아이디
	 * @return Response 
	 * @throws PopbillException
	 */
	public Response issue(String CorpNum, int ItemCode,
			String MgtKey, String Memo, String UserID) throws PopbillException;
	
	/**
	 * 명세서 발행
	 * 
	 * @param CorpNum
	 * 			연동회원 사업자번호
	 * @param ItemCode
	 * 			명세서 코드
	 * @param MgtKey
	 * 			문서관리번호
	 * @param Memo
	 * 			처리 메모
	 * @param EmailSubject
	 * 			발행메일 제목, 미기재시 기본양식으로 전송
	 * @param UserID
	 * 			연동 회원아이디
	 * @return Response 
	 * @throws PopbillException
	 */
	public Response issue(String CorpNum, int ItemCode,
			String MgtKey, String Memo, String EmailSubject, 
			String UserID) throws PopbillException;
	
	/**
	 * 명세서 발행취소
	 * 
	 * @param CorpNum
	 * 			연동회원 사업자번호
	 * @param ItemCode
	 * 			명세서 코드
	 * @param MgtKey
	 * 			문서관리번호
	 * @param Memo
	 * 			처리 메모
	 * @return Response 응답.
	 * @throws PopbillException
	 */
	public Response cancel(String CorpNum, int ItemCode,
			String MgtKey, String Memeo)	throws PopbillException;
	
	/**
	 * 명세서 발행취소
	 * 
	 * @param CorpNum
	 * 			연동회원 사업자번호
	 * @param ItemCode
	 * 			명세서 코드
	 * @param MgtKey
	 * 			문서관리번호
	 * @param Memo
	 * 			처리 메모
	 * @param UserID
	 * 			연동회원의 회원아이디
	 * @return Response 응답.
	 * @throws PopbillException
	 */
	public Response cancel(String CorpNum, int ItemCode,
			String MgtKey, String Memo, String UserID)	throws PopbillException;
	
	/**
	 * 
	 *  알림메일 재전송
	 *  
	 *  @param CorpNum
	 *  		연동회원 사업자번호
	 *  @param ItemCode
	 *  		명세서 코드
	 *  @param MgtKey
	 *  		문서관리번호
	 *  @param Receiver
	 *  		수신자이메일주소
	 *  @return Response 응답.
	 *  @throws PopbillException
	 */
	public Response sendEmail(String CorpNum, int ItemCode,
			String MgtKey, String Receiver) throws PopbillException;
	
	/**
	 * 
	 *  알림메일 재전송
	 *  
	 *  @param CorpNum
	 *  		연동회원 사업자번호
	 *  @param ItemCode
	 *  		명세서 코드
	 *  @param MgtKey
	 *  		문서관리번호
	 *  @param Receiver
	 *  		수신자이메일주소
	 *  @param UserID
	 *  		연동회원의 회원아이디
	 *  @return Response 응답.
	 *  @throws PopbillException
	 */
	public Response sendEmail(String CorpNum, int ItemCode,
			String MgtKey, String Receiver, String UserID) throws PopbillException;
	
	/**
	 * 문자 전송 요청
	 * 
	 * @param CorpNum
	 * 			연동회원 사업자번호
	 * @param ItemCode
	 * 			명세서 코드
	 * @param MgtKey
	 * 			문서관리번호
	 * @param Sender
	 * 			발신자 번호
	 * @param Receiver
	 * 			수신자 번호
	 * @param Contents
	 *			메시지 내용, 메시지 길이가 90Byte를 초과하는 경우 길이가 조정되어 전송됨.
	 * @return Response 응답.
	 * @throws PopbillException
	 */
	public Response sendSMS(String CorpNum, int ItemCode,
			String MgtKey, String Sender, String Receiver,
			String Contents) throws PopbillException;
	/**
	 * 문자 전송 요청
	 * 
	 * @param CorpNum
	 * 			연동회원 사업자번호
	 * @param ItemCode
	 * 			명세서 코드
	 * @param MgtKey
	 * 			문서관리번호
	 * @param Sender
	 * 			발신번호
	 * @param Receiver
	 * 			수신번호
	 * @param Contents
	 *			메시지 내용, 메시지 길이가 90Byte를 초과하는 경우 길이가 조정되어 전송됨.
	 * @param UserID
	 * 			연동회원의 회원아이디
	 * @return Response 응답.
	 * @throws PopbillException
	 */
	public Response sendSMS(String CorpNum, int ItemCode,
			String MgtKey, String Sender, String Receiver,
			String Contents, String UserID) throws PopbillException;
	
	/**
	 * 팩스 전송 요청
	 * 
	 * @param CorpNum
	 * 			연동회원 사업자번호
	 * @param ItemCode
	 * 			명세서 코드
	 * @param MgtKey
	 * 			문서관리번호
	 * @Param Sender
	 * 			발신번호
	 * @param Receiver
	 * 			수신팩스번호 
	 * @return Response 응답.
	 * @throws PopbillException
	 */
	public Response sendFAX(String CorpNum, int ItemCode,
			String MgtKey, String Sender, String Receiver) throws PopbillException;
	
	/**
	 * 팩스 전송 요청
	 * 
	 * @param CorpNum
	 * 			연동회원 사업자번호
	 * @param ItemCode
	 * 			명세서 코드
	 * @param MgtKey
	 * 			문서관리번호
	 * @Param Sender
	 * 			발신번호
	 * @param Receiver
	 * 			수신팩스번호
	 * @param UserID
	 * 			연동회원의 회원아이디
	 * @return Response 응답.
	 * @throws PopbillException
	 */
	public Response sendFAX(String CorpNum, int ItemCode,
			String MgtKey, String Sender, String Receiver,
			String UserID) throws PopbillException;

	/**
	 * 명세서 상세정보 확인.
	 * 
	 * @param CorpNum
	 * 			연동회원 사업자번호
	 * @param ItemCode
	 * 			명세서 코드
	 * @param MgtKey
	 * 			문서관리번호
	 * @return Statement
	 * @throws PopbillException
	 * 
	 */
	public Statement getDetailInfo(String CorpNum, int ItemCode,
			String MgtKey) throws PopbillException;
	
	/**
	 * 명세서 상세정보 확인.
	 * 
	 * @param CorpNum
	 * 			연동회원 사업자번호
	 * @param ItemCode
	 * 			명세서 코드
	 * @param MgtKey
	 * 			문서관리번호
	 * @param UserID
	 * 			연동회원의 회원아이디
	 * @return Statement
	 * @throws PopbillException
	 * 
	 */
	public Statement getDetailInfo(String CorpNum, int ItemCode,
			String MgtKey, String UserID) throws PopbillException;
	
	/**
	 * 명세서 상태/요약 정보 확인.
	 * 
	 * @param CorpNum
	 * 			연동회원 사업자번호
	 * @param ItemCode
	 * 			명세서 코드
	 * @param MgtKey
	 * 			문서관리번호
	 * @return StatementInfo
	 * @throws PopbillException
	 */
	public StatementInfo getInfo(String CorpNum, int ItemCode, 
			String MgtKey) throws PopbillException;
	
	/**
	 * 명세서 상태/요약 정보 대량확인 (최대 1000건).
	 * 
	 * @param CorpNum
	 * 			연동회원 사업자번호
	 * @param ItemCode
	 * 			명세서 코드
	 * @param MgtKeyList
	 * 			문서관리번호 목록
	 * @return StatementInfo 배열
	 * @throws PopbillException
	 *  
	 */
	public StatementInfo[] getInfos(String CorpNum, int ItemCode,
			String[] MgtKeyList) throws PopbillException;
	
	
	/**
	 * 명세서 문서이력 확인.
	 * 
	 * @param CorpNum
	 * 			연동회원 사업자번호
	 * @param ItemCode
	 * 			명세서 코드
	 * @param MgtKey
	 * 			문서관리번호
	 * @return StatementLog 배열.
	 * @throws PopbillException
	 */
	public StatementLog[] getLogs(String CorpNum, int ItemCode, 
			String MgtKey) throws PopbillException;

	/**
	 * 명세서 팝빌화면 팝업 URL 확인
	 * 
	 * @param CorpNum
	 * 			연동회원 사업자번호
	 * @param ItemCode
	 * 			명세서 코드
	 * @param MgtKey
	 * 			문서관리번호
	 * @return 팝빌 URL.
	 * @throws PopbillException
	 */
	public String getPopUpURL(String CorpNum, int ItemCode,
			String MgtKey) throws PopbillException;
	
	/**
	 * 명세서 팝빌화면 팝업 URL 확인
	 * 
	 * @param CorpNum
	 * 			연동회원 사업자번호
	 * @param ItemCode
	 * 			명세서 코드
	 * @param MgtKey
	 * 			문서관리번호
	 * @param UserID
	 * 			연동회원의 회원아이디
	 * @return 팝빌 URL.
	 * @throws PopbillException
	 */
	public String getPopUpURL(String CorpNum, int ItemCode,
			String MgtKey, String UserID) throws PopbillException;
	
	/**
	 * 명세서 인쇄 팝업 URL 확인
	 * 
	 * @param CorpNum
	 * 			연동회원 사업자번호
	 * @param ItemCode
	 * 			명세서 코드
	 * @param MgtKey
	 * 			문서관리번호
	 * @return 팝빌 URL
	 * @throws PopbillException 
	 * 
	 */
	public String getPrintURL(String CorpNum, int ItemCode,
			String MgtKey) throws PopbillException;
	
	/**
	 * 명세서 인쇄 팝업 URL 확인
	 * 
	 * @param CorpNum
	 * 			연동회원 사업자번호
	 * @param ItemCode
	 * 			명세서 코드
	 * @param MgtKey
	 * 			문서관리번호
	 * @param UserID
	 * 			연동회원의 회원아이디
	 * 
	 * @return 팝빌 URL
	 * @throws PopbillException 
	 * 
	 */
	public String getPrintURL(String CorpNum, int ItemCode,
			String MgtKey, String UserID) throws PopbillException;
	
	/**
	 * 명세서 인쇄 팝업 (공급받는자용) URL 확인
	 * 
	 * @param CorpNum
	 * 			연동회원 사업자번호
	 * @param ItemCode
	 * 			명세서 코드
	 * @param MgtKey
	 * 			문서관리번호
	 * @return 팝빌 URL
	 * @throws PopbillException
	 */
	public String getEPrintURL(String CorpNum, int ItemCode,
			String MgtKey) throws PopbillException;
	
	/**
	 * 명세서 인쇄 팝업 (공급받는자용) URL 확인
	 * 
	 * @param CorpNum
	 * 			연동회원 사업자번호
	 * @param ItemCode
	 * 			명세서 코드
	 * @param MgtKey
	 * 			문서관리번호
	 * @param UserID
	 * 			연동회원의 회원아이디
	 * @return 팝빌 URL
	 * @throws PopbillException
	 */
	public String getEPrintURL(String CorpNum, int ItemCode,
			String MgtKey, String UserID) throws PopbillException;
	
	/**
	 * 메일(공급받는자) 링크 URL 확인
	 * 
	 * @param CorpNum
	 * 			연동회원 사업자번호
	 * @param ItemCode
	 * 			명세서 코드
	 * @param MgtKey
	 * 			문서관리번호
	 * @return 팝빌 URL
	 * @throws PopbillException 
	 * 
	 */
	public String getMailURL(String CorpNum, int ItemCode, 
			String MgtKey) throws PopbillException;
	
	/**
	 * 메일(공급받는자) 링크 URL 확인
	 * 
	 * @param CorpNum
	 * 			연동회원 사업자번호
	 * @param ItemCode
	 * 			명세서 코드
	 * @param MgtKey
	 * 			문서관리번호
	 * @param UserID
	 * 			연동회원의 회원아이디
	 * @return 팝빌 URL
	 * @throws PopbillException 
	 * 
	 */
	public String getMailURL(String CorpNum, int ItemCode, 
			String MgtKey, String UserID) throws PopbillException;
	
	/**
	 * 다량 인쇄 팝업 URL 확인
	 * 
	 * @param CorpNum
	 * 			연동회원 사업자번호
	 * @param ItemCode
	 * 			명세서 코드
	 * @param MgtKeyList
	 * 			문서관리번호 배열(최대 1000건)
	 * @return 팝빌 URL
	 * @throws PopbillException
	 */
	public String getMassPrintURL(String CorpNum, int ItemCode,
			String[] MgtKeyList) throws PopbillException;
	
	/**
	 * 다량 인쇄 팝업 URL 확인
	 * 
	 * @param CorpNum
	 * 			연동회원 사업자번호
	 * @param ItemCode
	 * 			명세서 코드
	 * @param MgtKeyList
	 * 			문서관리번호 배열(최대 1000건)
	 * @param UserID
	 * 			연동회원의 회원아이디
	 * @return 팝빌 URL
	 * @throws PopbillException
	 */
	public String getMassPrintURL(String CorpNum, int ItemCode,
			String[] MgtKeyList, String UserID) throws PopbillException;
	
	/**
	 * 명세서 파일첨부
	 * 
	 * @param CorpNum
	 * 			연동회원 사업자번호
	 * @param ItemCode
	 * 			명세서 코드
	 * @param MgtKey
	 * 			문서관리번호 
	 * @param DisplayName
	 * 			파일 표시명( ex."통장사본.jpg")
	 * @param FileData
	 * 			파일 스트림.
	 * @return Response
	 * @throws PopbillException
	 */
	public Response attachFile(String CorpNum, int ItemCode, String MgtKey, 
			String DisplayName, InputStream FileData)
			throws PopbillException;
	
	/**
	 * 명세서 파일첨부
	 * 
	 * @param CorpNum
	 * 			연동회원 사업자번호
	 * @param ItemCode
	 * 			명세서 코드
	 * @param MgtKey
	 * 			문서관리번호 
	 * @param DisplayName
	 * 			파일 표시명( ex."통장사본.jpg")
	 * @param FileData
	 * 			파일 스트림.
	 * @param UserID
	 * 			연동회원의 회원아이디
	 * @return Response
	 * @throws PopbillException
	 */
	public Response attachFile(String CorpNum, int ItemCode, String MgtKey, 
			String DisplayName, InputStream FileData, String UserID)
			throws PopbillException;
	
	/**
	 * 첨부파일 목록 확인
	 * 
	 * @param CorpNum 
	 * 			연동회원 사업자번호
	 * @param ItemCode
	 * 			명세서 코드
	 * @param MgtKey
	 * 			문서관리번호
	 * @return AttachedFile 배열
	 * @throws PopbillException
	 * 
	 */
	public AttachedFile[] getFiles(String CorpNum, int ItemCode, 
			String MgtKey) throws PopbillException;
	
	/**
	 * 첨부파일 삭제
	 * 
	 * @param CorpNum
	 * 			연동회원 사업자번호
	 * @param ItemCode
	 * 			명세서 코드
	 * @param MgtKey
	 * 			문서관리번호
	 * @param FileID
	 * 			파일아이디
	 * @return Response
	 * @throws PopbillException 
	 */
	public Response deleteFile(String CorpNum, int ItemCode, 
			String MgtKey, String FileID) 
			throws PopbillException;
	/**
	 * 첨부파일 삭제
	 * 
	 * @param CorpNum
	 * 			연동회원 사업자번호
	 * @param ItemCode
	 * 			명세서 코드
	 * @param MgtKey
	 * 			문서관리번호
	 * @param FileID
	 * 			파일아이디 
	 * @param UserID
	 * 			연동회원의 회원아이디
	 * @return Response 응답.
	 * @throws PopbillException 
	 * 
	 */
	public Response deleteFile(String CorpNum, int ItemCode, 
			String MgtKey, String FileID, String UserID) 
			throws PopbillException;
	
	
	/**
	 * 명세서 1건 선팩스전송
	 * 전자명세서를 발송하지 않고 팩스만 전송.
	 * 
	 * @param CorpNum
	 * 			연동회원 사업자번호
	 * @param statement		
	 * 			명세서 정보.(see. com.popbill.api.statement.Statement)
	 * @return receiptNum 
	 * 			팩스전송 접수번호 
	 * @throws PopbillException
	 */
	public String FAXSend(String CorpNum, Statement statement, String SendNum, String receiveNum) 
			throws PopbillException;
	
	/**
	 * 명세서 1건 선팩스전송 
	 * 전자명세서를 발송하지 않고 팩스만 전송.
	 *  
	 * @param CorpNum
	 * 			연동회원 사업자번호
	 * @param statement		
	 * 			명세서 정보.(see. com.popbill.api.statement.Statement)
	 * @param UserID
	 * 			연동회원 아이디
	 * @return receiptNum 
	 * 			팩스전송 접수번호 
	 * @throws PopbillException
	 */
	public String FAXSend(String CorpNum, Statement statement, 
			String sendNum, String receiveNum,
			String UserID) throws PopbillException;
	
	/**
	 * 명세서 즉시밣행 
	 * 
	 * @param CorpNum
	 * 			연동회원 사업자번호 
	 * @param statement
	 * 			전자명세서 객체. see com.popbill.api.statement
	 * @return Response 
	 * @throws PopbillException
	 */
	public Response registIssue(String CorpNum, Statement statement) 
			throws PopbillException;

	/**
	 * 명세서 즉시발행 
	 * 
	 * @param CorpNum
	 * 			연동회원 사업자번호 
	 * @param statement
	 * 			전자명세서 객체. see com.popbill.api.statement
	 * @param Memo
	 * 			메모  
	 * @return Response 
	 * @throws PopbillException
	 */
	public Response registIssue(String CorpNum, Statement statement,
			String Memo) throws PopbillException;
	
	/**
	 * 명세서 즉시발행 
	 * 
	 * @param CorpNum
	 * 			연동회원 사업자번호 
	 * @param statement
	 * 			전자명세서 객체. see com.popbill.api.statement
	 * @param Memo
	 * 			메모  
	 * @param UserID
	 * 			연동회원 아이디 
	 * @return Response 
	 * @throws PopbillException
	 */
	public Response registIssue(String CorpNum, Statement statement,
			String Memo, String UserID)
			throws PopbillException;
	
	/**
	 * 명세서 즉시발행 
	 * 
	 * @param CorpNum
	 * 			연동회원 사업자번호 
	 * @param statement
	 * 			전자명세서 객체. see com.popbill.api.statement
	 * @param Memo
	 * 			메모  
	 * @param UserID
	 * 			연동회원 아이디 
	 * @return Response 
	 * @throws PopbillException
	 */
	
	public Response registIssue(String CorpNum, Statement statement, String memo, String 
			UserID, String emailSubject)
			throws PopbillException;	
	
	/**
	 * 다른 전자명세서 첨부 
	 * 
	 * @param CorpNum
	 * 			연동회원 사업자번호 
	 * @param ItemCode
	 * 			명세서 코드, 121-명세서, 122-청구서, 123-견적서, 124-발주서, 125-입금표, 126-영수증 
	 * @param MgtKey
	 * 			명세서 관리번호 
	 * @param SubItemCode
	 * 			첨부할 명세서 코드, 121-명세서, 122-청구서, 123-견적서, 124-발주서, 125-입금표, 126-영수증 
	 * @param SubMgtKey
	 * 			첨부할 명세서 관리번호 
	 * @return Response.
	 * @throws PopbillException
	 */
	public Response attachStatement(String CorpNum, int ItemCode, String MgtKey,
			int SubItemCode, String SubMgtKey) throws PopbillException;
	
	
	/**
	 * 다른 전자명세서 첨부해제 
	 * 
	 * @param CorpNum
	 * 			연동회원 사업자번호 
	 * @param ItemCode
	 * 			명세서 코드,  121-명세서, 122-청구서, 123-견적서, 124-발주서, 125-입금표, 126-영수증 
	 * @param MgtKey
	 * 			명세서 관리번호 
	 * @param SubItemCode
	 * 			첨부해제할 명세서 코드, 121-명세서, 122-청구서, 123-견적서, 124-발주서, 125-입금표, 126-영수증 
	 * @param SubMgtKey
	 * 			첨부해제할 명세서 관리번호 
	 * @return Response.
	 * @throws PopbillException
	 */
	public Response detachStatement(String CorpNum, int ItemCode, String MgtKey,
			int SubItemCode, String SubMgtKey) throws PopbillException;
	
	/**
	 * 전자명세서 목록 조회 
	 * 
	 * @param CorpNum
	 * 			연동회원 사업자번호 
	 * @param DType
	 * 			검색일자 유형, R-등록일자, W-작성일자, I-발행일자 
	 * @param SDate
	 * 			시작일자(yyyyMMdd)
	 * @param EDate
	 * 			종료일자(yyyyMMdd)
	 * @param State
	 * 			전자명세서상태 배열 
	 * @param ItemCode
	 * 			전자명세서코드 배열, 121-명세서, 122-청구서, 123-견적서, 124-발주서, 125-입금표, 126-영수증 
	 * @param Page
	 * 			페이지 번호 
	 * @param PerPage
	 * 			페이지당 목록 개수, 기본값 500, 최대 1000 
	 * @param Order
	 * 			정렬방향 
	 * @return 전자명세서 목록조회 결과. see com.popbill.api.statement.StmtSearchResuilt
	 * @throws PopbillException
	 */
	public StmtSearchResult search(String CorpNum, String DType, String SDate,
			String EDate, String[] State, int[] ItemCode, int Page,
			int PerPage, String Order) throws PopbillException;
	
	/**
	 * 전자명세서 목록 조회 
	 * 
	 * @param CorpNum
	 * 			연동회원 사업자번호 
	 * @param DType
	 * 			검색일자 유형, R-등록일자, W-작성일자, I-발행일자 
	 * @param SDate
	 * 			시작일자(yyyyMMdd)
	 * @param EDate
	 * 			종료일자(yyyyMMdd)
	 * @param State
	 * 			전자명세서상태 배열 
	 * @param ItemCode
	 * 			전자명세서코드 배열, 121-명세서, 122-청구서, 123-견적서, 124-발주서, 125-입금표, 126-영수증
	 * @param QString
	 * 			통합검색 키워드, (거래처명 또는 거래처 사업자등록번호 조회) 
	 * @param Page
	 * 			페이지 번호 
	 * @param PerPage
	 * 			페이지당 목록 개수, 기본값 500, 최대 1000 
	 * @param Order
	 * 			정렬방향 
	 * @return 전자명세서 목록조회 결과. see com.popbill.api.statement.StmtSearchResuilt
	 * @throws PopbillException
	 */
	public StmtSearchResult search(String CorpNum, String DType, String SDate,
			String EDate, String[] State, int[] ItemCode, String QString, int Page,
			int PerPage, String Order) throws PopbillException;
	/**
	 * 과금정보 확인
	 * @param CorpNum
	 * 			연동회원 사업자번호 
	 * @param ItemCode
	 * 			전자명세서 코드, 121-명세서, 122-청구서, 123-견적서, 124-발주서, 125-입금표, 126-영수증
	 * @return 과금정보. see com.popbill.api.ChargeInfo
	 * @throws PopbillException
	 */
	public ChargeInfo getChargeInfo(String CorpNum, int ItemCode) throws PopbillException;
	
	/**
	 *  알림메일 전송설정 수정
	 * 
	 * @param CorpNum
	 * 			연동회원 사업자번호
	 * @param EmailType
	 * 			메일전송유형
	 * @param SendYN
	 * 			전송 여부 (True = 전송, False = 미전송)
	 * @return Response.
	 * @throws PopbillException
	 */
	public Response updateEmailConfig(String CorpNum, String EmailType, Boolean SendYN)
			throws PopbillException;
	
	
	/**
	 *  알림메일 전송설정 수정
	 *  
	 * @param CorpNum
	 * 			연동회원 사업자번호
	 * @param EmailType
	 * 			메일전송유형
	 * @param SendYN
	 * 			전송 여부 (True = 전송, False = 미전송)
	 * @param UserID
	 * 			팝빌회원 아이디 
	 * @return Response.
	 * @throws PopbillException
	 */
	public Response updateEmailConfig(String CorpNum, String EmailType, Boolean SendYN, 
			String UserID) throws PopbillException;
	
	
	
	/**
	 * 알림메일 전송목록 조회
	 * 
	 * @param CorpNum
	 * 			연동회원 사업자번호
	 * @return EmailSendConfig 배열.
	 * @throws PopbillException
	 */
	public EmailSendConfig[] listEmailConfig(String CorpNum) throws PopbillException;	
	
	/**
	 *  알림메일 전송목록 조회
	 *  
	 * @param CorpNum
	 * 			연동회원 사업자번호
	 * @param UserID
	 * 			팝빌회원 아이디 
	 * @return EmailSendConfig 배열.
	 * @throws PopbillException
	 */
	public EmailSendConfig[] listEmailConfig(String CorpNum, String UserID) throws PopbillException;

	
}


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
package com.popbill.api;
/**
 * Statement Service Interface.
 * 
 * @author JeongYoHan
 * @version 1.0.0
 */

import java.io.InputStream;

import com.popbill.api.statement.Statement;
import com.popbill.api.statement.StatementInfo;
import com.popbill.api.statement.StatementLog;

public interface StatementService extends BaseService{

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
}





















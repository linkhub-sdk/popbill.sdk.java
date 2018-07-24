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
package com.popbill.api.message;

import java.io.Serializable;

/**
 * Class for Messages Sent State.
 * 
 * @author KimSeongjun
 * @version 1.0.0
 */
public class SentMessage implements Serializable {
	private static final long serialVersionUID = -4418729876803035880L;

	private Integer state;
	private Integer result;
	private String subject;
	private String type;
	private String content;
	private String sendNum;
	private String senderName;
	private String receiveNum;
	private String receiveName;
	private String reserveDT;
	private String sendDT;
	private String resultDT;
	private String sendResult;
	private String tranNet;
	private String receiptDT;
	private String receiptNum;
	private String requestNum;


	/**
	 * 전송상태 확인 0 : 접수 1 : 대기중 2 : 처리중 3 : 완료 4 : 취소
	 * 
	 * @return 전송상태
	 */
	public Integer getState() {
		return state;
	}
	
	
	/**
	 * 이동통신사 전송결과 확인
	 * 
	 * @return 전송결과코드
	 */
	public Integer getResult() {
		return result;
	}

	/**
	 * 메시지 제목 확인
	 * 
	 * @return 메시지 제목
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * 메시지 유형 확인
	 * 
	 * @return 메시지 유형
	 */
	public MessageType getMessageType() {
		return type == null ? null : MessageType.valueOf(type);
	}

	/**
	 * 메시지 내용 확인
	 * 
	 * @return 메시지 내용
	 */
	public String getContent() {
		return content;
	}

	/**
	 * 발신자 번호 확인
	 * 
	 * @return 발신자 번호
	 */
	public String getSendNum() {
		return sendNum;
	}

	/**
	 * 수신자 번호 확인
	 * 
	 * @return 수신자 번호
	 */
	public String getReceiveNum() {
		return receiveNum;
	}

	/**
	 * 수신자 명칭 확인
	 * 
	 * @return 수신자 명칭
	 */
	public String getReceiveName() {
		return receiveName;
	}

	/**
	 * 예약일시 확인
	 * 
	 * @return 예약일시
	 */
	public String getReserveDT() {
		return reserveDT;
	}

	/**
	 * 발신일시 확인
	 * 
	 * @return 발신일시
	 */
	public String getSendDT() {
		return sendDT;
	}

	/**
	 * 전송결과 수신일시 확인
	 * 
	 * @return 전송결과 수신일시 확인
	 */
	public String getResultDT() {
		return resultDT;
	}

	/**
	 * 전송 결과 확인 메뉴얼의 이동통신사 처리결과 상태코드 테이블 참조.
	 * 
	 * @return 전송 결과
	 */
	public String getSendResult() {
		return sendResult;
	}

	/**
	 * 전송처리 이동통신사명 확인 
	 * 
	 * @return 통신사명 
	 */
	public String getTranNet() {
		return tranNet;
	}

	/**
	 * 전송접수일시 확인 
	 * 
	 * @return 접수일시 
	 */
	public String getReceiptDT() {
		return receiptDT;
	}
	
	/**
	 * 발신자명 확인 
	 * 
	 * @return 발신자명 
	 */
	public String getSenderName() {
		return senderName;
	}
	
	/**
	 * 전송요청번호 확인 
	 * 
	 * @return 전송요청번호
	 */
	public String getRequestNum() {
		return requestNum;
	}

	/**
	 * 접수번호 확인 
	 * 
	 * @return 접수번호
	 */
	public String getReceiptNum() {
		return receiptNum;
	}

}

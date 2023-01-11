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

import java.io.File;
import java.util.Date;

import com.popbill.api.message.AutoDeny;
import com.popbill.api.message.MSGSearchResult;
import com.popbill.api.message.Message;
import com.popbill.api.message.MessageBriefInfo;
import com.popbill.api.message.MessageType;
import com.popbill.api.message.SenderNumber;
import com.popbill.api.message.SentMessage;

/**
 * Message Service Interface.
 * 
 * @author KimSeongjun
 * @version 1.0.0
 */
public interface MessageService extends BaseService {

    /**
     * 회원의 문자메시지 전송단가 확인
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param MsgType
     *            메시지 유형
     * @return 단가 (ex. 11.0)
     * @throws PopbillException
     */
    public float getUnitCost(String CorpNum, MessageType MsgType)
            throws PopbillException;
    
    /**
     * 팝빌 문자메시지 관련 URL 확인. 반환한 url은 30초이내에 브라우져에 표시하여야 함.
     * 
     * @param CorpNum
     *            연동회원 사업자번호.
     * @param TOGO
     *            지정값. (BOX : 문자전송 내역 조회 팝업)
     * @return 팝빌 URL (AccessToken값 포함. Token값은 응답후 30초까지만 유효함)
     * @throws PopbillException
     */
    public String getURL(String CorpNum, String TOGO)
            throws PopbillException;
    
    /**
     * 팝빌 문자메시지 관련 URL 확인. 반환한 url은 30초이내에 브라우져에 표시하여야 함.
     * 
     * @param CorpNum
     *            연동회원 사업자번호.
     * @param UserID
     *            연동회원의 회원아이디
     * @param TOGO
     *            지정값. (BOX : 문자전송 내역 조회 팝업)
     * @return 팝빌 URL (AccessToken값 포함. Token값은 응답후 30초까지만 유효함)
     * @throws PopbillException
     */
    public String getURL(String CorpNum, String UserID, String TOGO)
            throws PopbillException;

    /**
     * 단문문자메시지 1건 전송
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param sender
     *            발신자번호
     * @param receiver
     *            수신자번호
     * @param receiverName
     *            수신자명칭
     * @param content
     *            단문메시지 내용 최대 90Byte.
     * @param reserveDT
     *            예약전송시 예약일시.
     * @param UserID
     *            연동회원의 회원아이디
     * @return receiptNum 접수번호.
     * @throws PopbillException
     */
    public String sendSMS(String CorpNum, String sender, String receiver,
            String receiverName, String content, Date reserveDT, String UserID)
            throws PopbillException;
    
    /**
     * 단문문자메시지 1건 전송
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param sender
     *            발신자번호
     * @param receiver
     *            수신자번호
     * @param receiverName
     *            수신자명칭
     * @param content
     *            단문메시지 내용 최대 90Byte.
     * @param reserveDT
     *            예약전송시 예약일시.
     * @param adsYN
     *            광고문자 전송여부 
     * @param UserID
     *            연동회원의 회원아이디
     * @return receiptNum 접수번호.
     * @throws PopbillException
     */
    public String sendSMS(String CorpNum, String sender, String receiver,
            String receiverName, String content, Date reserveDT, Boolean adsYN, String UserID)
            throws PopbillException;

    /**
     * 단문문자메시지 다량 전송. 1회 최대 1000건.
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param Messages
     *            메시지 배열.
     * @param reserveDT
     *            예약일시
     * @param UserID
     *            연동회원 아이디
     * @return receiptNum 접수번호.
     * @throws PopbillException
     */
    public String sendSMS(String CorpNum, Message[] Messages, Date reserveDT,
            String UserID) throws PopbillException;

    /**
     * 단문문자메시지 다량전송. 발신번호, 내용 동보전송. 1회 최대 1000건.
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param sender
     *            동보전송 발신번호
     * @param content
     *            동보전송 단문문자메시지 내용.
     * @param Messages
     *            메시지 배열. 수신자번호, 수신자명칭을 기재. 별도 발신번호와 내용 기재시 해당건만 개별전송.
     * @param reserveDT
     *            예약일시
     * @param UserID
     *            연동회원 아이디
     * @return receiptNum 접수번호.
     * @throws PopbillException
     */
    public String sendSMS(String CorpNum, String sender, String content,
            Message[] Messages, Date reserveDT, String UserID)
            throws PopbillException;
    
    /**
     * 단문문자메시지 다량전송. 발신번호, 내용 동보전송. 1회 최대 1000건.
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param sender
     *            동보전송 발신번호
     * @param content
     *            동보전송 단문문자메시지 내용.
     * @param Messages
     *            메시지 배열. 수신자번호, 수신자명칭을 기재. 별도 발신번호와 내용 기재시 해당건만 개별전송.
     * @param reserveDT
     *            예약일시
     * @param adsYN
     *            광고문자 전송여부
     * @param UserID
     *            연동회원 아이디
     * @return receiptNum 접수번호.
     * @throws PopbillException
     */
    public String sendSMS(String CorpNum, String sender, String content,
            Message[] Messages, Date reserveDT, Boolean adsYN, String UserID)
            throws PopbillException;
    
    /**
     * 단문문자메시지 다량전송. 발신번호, 내용 동보전송. 1회 최대 1000건.
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param sender
     *            동보전송 발신번호
     * @param senderName
     *            동보전송 발신자명
     * @param content
     *            동보전송 단문문자메시지 내용.
     * @param Messages
     *            메시지 배열. 수신자번호, 수신자명칭을 기재. 별도 발신번호와 내용 기재시 해당건만 개별전송.
     * @param reserveDT
     *            예약일시
     * @param adsYN
     *            광고문자 전송여부
     * @param UserID
     *            연동회원 아이디
     * @return receiptNum 접수번호.
     * @throws PopbillException
     */
    public String sendSMS(String CorpNum, String sender, String senderName, String content,
            Message[] Messages, Date reserveDT, Boolean adsYN, String UserID)
            throws PopbillException;

    /**
     * 단문문자메시지 1건 전송
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param sender
     *            발신자번호
     * @param receiver
     *            수신자번호
     * @param receiverName
     *            수신자명칭
     * @param content
     *            단문메시지 내용 최대 90Byte.
     * @param reserveDT
     *            예약전송시 예약일시.
     * @param UserID
     *            연동회원의 회원아이디
     * @param requestNum
     *            전송요청번호
     * @return receiptNum 접수번호.
     * @throws PopbillException
     */
    public String sendSMS(String CorpNum, String sender, String receiver,
            String receiverName, String content, Date reserveDT, String UserID, String requestNum)
            throws PopbillException;
    
    /**
     * 단문문자메시지 1건 전송
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param sender
     *            발신자번호
     * @param receiver
     *            수신자번호
     * @param receiverName
     *            수신자명칭
     * @param content
     *            단문메시지 내용 최대 90Byte.
     * @param reserveDT
     *            예약전송시 예약일시.
     * @param adsYN
     *            광고문자 전송여부 
     * @param UserID
     *            연동회원의 회원아이디
     * @param requestNum
     *            전송요청번호
     * @return receiptNum 접수번호.
     * @throws PopbillException
     */
    public String sendSMS(String CorpNum, String sender, String receiver,
            String receiverName, String content, Date reserveDT, Boolean adsYN, String UserID, String requestNum)
            throws PopbillException;

    /**
     * 단문문자메시지 다량 전송. 1회 최대 1000건.
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param Messages
     *            메시지 배열.
     * @param reserveDT
     *            예약일시
     * @param UserID
     *            연동회원 아이디
     * @param requestNum
     *            전송요청번호
     * @return receiptNum 접수번호.
     * @throws PopbillException
     */
    public String sendSMS(String CorpNum, Message[] Messages, Date reserveDT,
            String UserID, String requestNum) throws PopbillException;

    /**
     * 단문문자메시지 다량전송. 발신번호, 내용 동보전송. 1회 최대 1000건.
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param sender
     *            동보전송 발신번호
     * @param content
     *            동보전송 단문문자메시지 내용.
     * @param Messages
     *            메시지 배열. 수신자번호, 수신자명칭을 기재. 별도 발신번호와 내용 기재시 해당건만 개별전송.
     * @param reserveDT
     *            예약일시
     * @param UserID
     *            연동회원 아이디
     * @param requestNum
     *            전송요청번호
     * @return receiptNum 접수번호.
     * @throws PopbillException
     */
    public String sendSMS(String CorpNum, String sender, String content,
            Message[] Messages, Date reserveDT, String UserID, String requestNum)
            throws PopbillException;
    
    /**
     * 단문문자메시지 다량전송. 발신번호, 내용 동보전송. 1회 최대 1000건.
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param sender
     *            동보전송 발신번호
     * @param content
     *            동보전송 단문문자메시지 내용.
     * @param Messages
     *            메시지 배열. 수신자번호, 수신자명칭을 기재. 별도 발신번호와 내용 기재시 해당건만 개별전송.
     * @param reserveDT
     *            예약일시
     * @param adsYN
     *            광고문자 전송여부
     * @param UserID
     *            연동회원 아이디
     * @param requestNum
     *            전송요청번호
     * @return receiptNum 접수번호.
     * @throws PopbillException
     */
    public String sendSMS(String CorpNum, String sender, String content,
            Message[] Messages, Date reserveDT, Boolean adsYN, String UserID, String requestNum)
            throws PopbillException;
    
    /**
     * 단문문자메시지 다량전송. 발신번호, 내용 동보전송. 1회 최대 1000건.
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param sender
     *            동보전송 발신번호
     * @param senderName
     *            동보전송 발신자명
     * @param content
     *            동보전송 단문문자메시지 내용.
     * @param Messages
     *            메시지 배열. 수신자번호, 수신자명칭을 기재. 별도 발신번호와 내용 기재시 해당건만 개별전송.
     * @param reserveDT
     *            예약일시
     * @param adsYN
     *            광고문자 전송여부
     * @param UserID
     *            연동회원 아이디
     * @param requestNum
     *            전송요청번호
     * @return receiptNum 접수번호.
     * @throws PopbillException
     */
    public String sendSMS(String CorpNum, String sender, String senderName, String content,
            Message[] Messages, Date reserveDT, Boolean adsYN, String UserID, String requestNum)
            throws PopbillException;

    /**
     * 장문문자메시지 1건 전송
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param sender
     *            발신자번호
     * @param receiver
     *            수신자번호
     * @param receiverName
     *            수신자명칭
     * @param subject
     *            장문메시지 제목
     * @param content
     *            장문메시지 내용 최대 2000Byte.
     * @param reserveDT
     *            예약전송시 예약일시.
     * @param UserID
     *            연동회원의 회원아이디
     * @return receiptNum 접수번호.
     * @throws PopbillException
     */
    public String sendLMS(String CorpNum, String sender, String receiver,
            String receiverName, String subject, String content,
            Date reserveDT, String UserID) throws PopbillException;
    
    /**
     * 장문문자메시지 1건 전송
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param sender
     *            발신자번호
     * @param receiver
     *            수신자번호
     * @param receiverName
     *            수신자명칭
     * @param subject
     *            장문메시지 제목
     * @param content
     *            장문메시지 내용 최대 2000Byte.
     * @param reserveDT
     *            예약전송시 예약일시.
     * @param adsYN
     *            광고문자 전송여부 
     * @param UserID
     *            연동회원의 회원아이디
     * @return receiptNum 접수번호.
     * @throws PopbillException
     */
    public String sendLMS(String CorpNum, String sender, String receiver,
            String receiverName, String subject, String content,
            Date reserveDT, Boolean adsYN, String UserID) throws PopbillException;

    /**
     * 장문문자메시지 다량 전송. 1회 최대 1000건.
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param Messages
     *            메시지 배열.
     * @param reserveDT
     *            예약일시
     * @param UserID
     *            연동회원 아이디
     * @return receiptNum 접수번호.
     * @throws PopbillException
     */
    public String sendLMS(String CorpNum, Message[] Messages, Date reserveDT,
            String UserID) throws PopbillException;

    /**
     * 장문문자메시지 다량전송. 발신번호, 내용 동보전송. 1회 최대 1000건.
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param sender
     *            동보전송 발신번호
     * @param subject
     *            동보전송 장문메시지 제목
     * @param content
     *            동보전송 장문메시지 내용.
     * @param Messages
     *            메시지 배열. 수신자번호, 수신자명칭을 기재. 별도 발신번호와 내용 기재시 해당건만 개별전송.
     * @param reserveDT
     *            예약일시
     * @param UserID
     *            연동회원 아이디
     * @return receiptNum 접수번호.
     * @throws PopbillException
     */
    public String sendLMS(String CorpNum, String sender, String subject,
            String content, Message[] Messages, Date reserveDT, String UserID)
            throws PopbillException;
    
    /**
     * 장문문자메시지 다량전송. 발신번호, 내용 동보전송. 1회 최대 1000건.
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param sender
     *            동보전송 발신번호
     * @param subject
     *            동보전송 장문메시지 제목
     * @param content
     *            동보전송 장문메시지 내용.
     * @param Messages
     *            메시지 배열. 수신자번호, 수신자명칭을 기재. 별도 발신번호와 내용 기재시 해당건만 개별전송.
     * @param reserveDT
     *            예약일시
     * @parma adsYN
     *            광고문자 전송여부 
     * @param UserID
     *            연동회원 아이디
     * @return receiptNum 접수번호.
     * @throws PopbillException
     */
    public String sendLMS(String CorpNum, String sender, String subject, String content, 
            Message[] Messages, Date reserveDT, Boolean adsYN, String UserID)
            throws PopbillException;
    
    /**
     * 장문문자메시지 다량전송. 발신번호, 내용 동보전송. 1회 최대 1000건.
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param sender
     *            동보전송 발신번호
     * @param senderName
     *            동보전송 발신자명
     * @param subject
     *            동보전송 장문메시지 제목
     * @param content
     *            동보전송 장문메시지 내용.
     * @param Messages
     *            메시지 배열. 수신자번호, 수신자명칭을 기재. 별도 발신번호와 내용 기재시 해당건만 개별전송.
     * @param reserveDT
     *            예약일시
     * @parma adsYN
     *            광고문자 전송여부 
     * @param UserID
     *            연동회원 아이디
     * @return receiptNum 접수번호.
     * @throws PopbillException
     */
    public String sendLMS(String CorpNum, String sender, String senderName, String subject, String content, 
            Message[] Messages, Date reserveDT, Boolean adsYN, String UserID)
            throws PopbillException;

    /**
     * 장문문자메시지 1건 전송
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param sender
     *            발신자번호
     * @param receiver
     *            수신자번호
     * @param receiverName
     *            수신자명칭
     * @param subject
     *            장문메시지 제목
     * @param content
     *            장문메시지 내용 최대 2000Byte.
     * @param reserveDT
     *            예약전송시 예약일시.
     * @param UserID
     *            연동회원의 회원아이디
     * @param requestNum
     *            전송요청번호
     * @return receiptNum 접수번호.
     * @throws PopbillException
     */
    public String sendLMS(String CorpNum, String sender, String receiver,
            String receiverName, String subject, String content,
            Date reserveDT, String UserID, String requestNum) throws PopbillException;
    
    /**
     * 장문문자메시지 1건 전송
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param sender
     *            발신자번호
     * @param receiver
     *            수신자번호
     * @param receiverName
     *            수신자명칭
     * @param subject
     *            장문메시지 제목
     * @param content
     *            장문메시지 내용 최대 2000Byte.
     * @param reserveDT
     *            예약전송시 예약일시.
     * @param adsYN
     *            광고문자 전송여부 
     * @param UserID
     *            연동회원의 회원아이디
     * @param requestNum
     *            전송요청번호
     * @return receiptNum 접수번호.
     * @throws PopbillException
     */
    public String sendLMS(String CorpNum, String sender, String receiver,
            String receiverName, String subject, String content,
            Date reserveDT, Boolean adsYN, String UserID, String requestNum) throws PopbillException;

    /**
     * 장문문자메시지 다량 전송. 1회 최대 1000건.
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param Messages
     *            메시지 배열.
     * @param reserveDT
     *            예약일시
     * @param UserID
     *            연동회원 아이디
     * @param requestNum
     *            전송요청번호
     * @return receiptNum 접수번호.
     * @throws PopbillException
     */
    public String sendLMS(String CorpNum, Message[] Messages, Date reserveDT,
            String UserID, String requestNum) throws PopbillException;

    /**
     * 장문문자메시지 다량전송. 발신번호, 내용 동보전송. 1회 최대 1000건.
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param sender
     *            동보전송 발신번호
     * @param subject
     *            동보전송 장문메시지 제목
     * @param content
     *            동보전송 장문메시지 내용.
     * @param Messages
     *            메시지 배열. 수신자번호, 수신자명칭을 기재. 별도 발신번호와 내용 기재시 해당건만 개별전송.
     * @param reserveDT
     *            예약일시
     * @param UserID
     *            연동회원 아이디
     * @param requestNum
     *            전송요청번호
     * @return receiptNum 접수번호.
     * @throws PopbillException
     */
    public String sendLMS(String CorpNum, String sender, String subject,
            String content, Message[] Messages, Date reserveDT, String UserID, String requestNum)
            throws PopbillException;
    
    /**
     * 장문문자메시지 다량전송. 발신번호, 내용 동보전송. 1회 최대 1000건.
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param sender
     *            동보전송 발신번호
     * @param subject
     *            동보전송 장문메시지 제목
     * @param content
     *            동보전송 장문메시지 내용.
     * @param Messages
     *            메시지 배열. 수신자번호, 수신자명칭을 기재. 별도 발신번호와 내용 기재시 해당건만 개별전송.
     * @param reserveDT
     *            예약일시
     * @parma adsYN
     *            광고문자 전송여부 
     * @param UserID
     *            연동회원 아이디
     * @param requestNum
     *            전송요청번호
     * @return receiptNum 접수번호.
     * @throws PopbillException
     */
    public String sendLMS(String CorpNum, String sender, String subject, String content, 
            Message[] Messages, Date reserveDT, Boolean adsYN, String UserID, String requestNum)
            throws PopbillException;
    
    /**
     * 장문문자메시지 다량전송. 발신번호, 내용 동보전송. 1회 최대 1000건.
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param sender
     *            동보전송 발신번호
     * @param senderName
     *            동보전송 발신자명
     * @param subject
     *            동보전송 장문메시지 제목
     * @param content
     *            동보전송 장문메시지 내용.
     * @param Messages
     *            메시지 배열. 수신자번호, 수신자명칭을 기재. 별도 발신번호와 내용 기재시 해당건만 개별전송.
     * @param reserveDT
     *            예약일시
     * @parma adsYN
     *            광고문자 전송여부 
     * @param UserID
     *            연동회원 아이디
     * @param requestNum
     *            전송요청번호
     * @return receiptNum 접수번호.
     * @throws PopbillException
     */
    public String sendLMS(String CorpNum, String sender, String senderName, String subject, String content, 
            Message[] Messages, Date reserveDT, Boolean adsYN, String UserID, String requestNum)
            throws PopbillException;
    
    /**
     * 멀티문자메시지 1건 전송
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param sender
     *            발신자번호
     * @param receiver
     *            수신자번호
     * @param receiverName
     *            수신자명칭
     * @param subject
     *            장문메시지 제목
     * @param content
     *            장문메시지 내용 최대 2000Byte.
     * @param file
     *            전송파일 최대크기 300Kbyte
     * @param reserveDT
     *            예약전송시 예약일시.
     * @param UserID
     *            연동회원의 회원아이디
     * @return receiptNum 접수번호.
     * @throws PopbillException
     */
    public String sendMMS(String CorpNum, String sender, String receiver,
            String receiverName, String subject, String content, File file,
            Date reserveDT, String UserID) throws PopbillException;
    
    /**
     * 멀티문자메시지 1건 전송
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param sender
     *            발신자번호
     * @param receiver
     *            수신자번호
     * @param receiverName
     *            수신자명칭
     * @param subject
     *            장문메시지 제목
     * @param content
     *            장문메시지 내용 최대 2000Byte.
     * @param file
     *            전송파일 최대크기 300Kbyte
     * @param reserveDT
     *            예약전송시 예약일시.
     * @param UserID
     *            연동회원의 회원아이디
     * @param adsYN
     *            광고문자 전송여부 
     * @return receiptNum 접수번호.
     * @throws PopbillException
     */
    public String sendMMS(String CorpNum, String sender, String receiver,
            String receiverName, String subject, String content, File file,
            Date reserveDT, Boolean adsYN, String UserID) throws PopbillException;

    /**
     * 멀티 문자메시지 다량 전송. 1회 최대 1000건.
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param Messages
     *            메시지 배열.
     * @param file
     *            전송파일 최대크기 300Kbyte
     * @param reserveDT
     *            예약일시
     * @param UserID
     *            연동회원 아이디
     * @return receiptNum 접수번호.
     * @throws PopbillException
     */
    public String sendMMS(String CorpNum, Message[] Messages, File file, Date reserveDT,
            String UserID) throws PopbillException;

    /**
     * 멀티 문자메시지 다량 전송. 발신번호, 내용 동보전송. 1회 최대 1000건.
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param sender
     *            동보전송 발신번호
     * @param subject
     *            동보전송 장문메시지 제목
     * @param content
     *            동보전송 장문메시지 내용.
     * @param Messages
     *            메시지 배열. 수신자번호, 수신자명칭을 기재. 별도 발신번호와 내용 기재시 해당건만 개별전송.
     * @param file
     *            전송파일 최대크기 300Kbyte
     * @param reserveDT
     *            예약일시
     * @param UserID
     *            연동회원 아이디
     * @return receiptNum 접수번호.
     * @throws PopbillException
     */
    public String sendMMS(String CorpNum, String sender, String subject,
            String content, Message[] Messages, File file, Date reserveDT, String UserID)
            throws PopbillException;
    
    /**
     * 멀티 문자메시지 다량 전송. 발신번호, 내용 동보전송. 1회 최대 1000건.
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param sender
     *            동보전송 발신번호
     * @param subject
     *            동보전송 장문메시지 제목
     * @param content
     *            동보전송 장문메시지 내용.
     * @param Messages
     *            메시지 배열. 수신자번호, 수신자명칭을 기재. 별도 발신번호와 내용 기재시 해당건만 개별전송.
     * @param file
     *            전송파일 최대크기 300Kbyte
     * @param reserveDT
     *            예약일시
     * @param adsYN
     *          광고문자 전송여부 
     * @param UserID
     *            연동회원 아이디
     * @return receiptNum 접수번호.
     * @throws PopbillException
     */
    public String sendMMS(String CorpNum, String sender, String subject, String content, 
            Message[] Messages, File file, Date reserveDT, Boolean adsYN, String UserID)
            throws PopbillException;
    
    /**
     * 멀티 문자메시지 다량 전송. 발신번호, 내용 동보전송. 1회 최대 1000건.
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param sender
     *            동보전송 발신번호
     * @param senderName
     *            동보전송 발신자명
     * @param subject
     *            동보전송 장문메시지 제목
     * @param content
     *            동보전송 장문메시지 내용.
     * @param Messages
     *            메시지 배열. 수신자번호, 수신자명칭을 기재. 별도 발신번호와 내용 기재시 해당건만 개별전송.
     * @param file
     *            전송파일 최대크기 300Kbyte
     * @param reserveDT
     *            예약일시
     * @param adsYN
     *          광고문자 전송여부 
     * @param UserID
     *            연동회원 아이디
     * @return receiptNum 접수번호.
     * @throws PopbillException
     */
    public String sendMMS(String CorpNum, String sender, String senderName, String subject, String content, 
            Message[] Messages, File file, Date reserveDT, Boolean adsYN, String UserID)
            throws PopbillException;

    /**
     * 멀티문자메시지 1건 전송
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param sender
     *            발신자번호
     * @param receiver
     *            수신자번호
     * @param receiverName
     *            수신자명칭
     * @param subject
     *            장문메시지 제목
     * @param content
     *            장문메시지 내용 최대 2000Byte.
     * @param file
     *            전송파일 최대크기 300Kbyte
     * @param reserveDT
     *            예약전송시 예약일시.
     * @param UserID
     *            연동회원의 회원아이디
     * @param requestNum
     *            전송요청번호
     * @return receiptNum 접수번호.
     * @throws PopbillException
     */
    public String sendMMS(String CorpNum, String sender, String receiver,
            String receiverName, String subject, String content, File file,
            Date reserveDT, String UserID, String requestNum) throws PopbillException;
    
    /**
     * 멀티문자메시지 1건 전송
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param sender
     *            발신자번호
     * @param receiver
     *            수신자번호
     * @param receiverName
     *            수신자명칭
     * @param subject
     *            장문메시지 제목
     * @param content
     *            장문메시지 내용 최대 2000Byte.
     * @param file
     *            전송파일 최대크기 300Kbyte
     * @param reserveDT
     *            예약전송시 예약일시.
     * @param UserID
     *            연동회원의 회원아이디
     * @param adsYN
     *            광고문자 전송여부 
     * @param requestNum
     *            전송요청번호
     * @return receiptNum 접수번호.
     * @throws PopbillException
     */
    public String sendMMS(String CorpNum, String sender, String receiver,
            String receiverName, String subject, String content, File file,
            Date reserveDT, Boolean adsYN, String UserID, String requestNum) throws PopbillException;

    /**
     * 멀티 문자메시지 다량 전송. 1회 최대 1000건.
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param Messages
     *            메시지 배열.
     * @param file
     *            전송파일 최대크기 300Kbyte
     * @param reserveDT
     *            예약일시
     * @param UserID
     *            연동회원 아이디
     * @param requestNum
     *            전송요청번호
     * @return receiptNum 접수번호.
     * @throws PopbillException
     */
    public String sendMMS(String CorpNum, Message[] Messages, File file, Date reserveDT,
            String UserID, String requestNum) throws PopbillException;

    /**
     * 멀티 문자메시지 다량 전송. 발신번호, 내용 동보전송. 1회 최대 1000건.
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param sender
     *            동보전송 발신번호
     * @param subject
     *            동보전송 장문메시지 제목
     * @param content
     *            동보전송 장문메시지 내용.
     * @param Messages
     *            메시지 배열. 수신자번호, 수신자명칭을 기재. 별도 발신번호와 내용 기재시 해당건만 개별전송.
     * @param file
     *            전송파일 최대크기 300Kbyte
     * @param reserveDT
     *            예약일시
     * @param UserID
     *            연동회원 아이디
     * @param requestNum
     *            전송요청번호
     * @return receiptNum 접수번호.
     * @throws PopbillException
     */
    public String sendMMS(String CorpNum, String sender, String subject,
            String content, Message[] Messages, File file, Date reserveDT, String UserID, String requestNum)
            throws PopbillException;
    
    /**
     * 멀티 문자메시지 다량 전송. 발신번호, 내용 동보전송. 1회 최대 1000건.
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param sender
     *            동보전송 발신번호
     * @param subject
     *            동보전송 장문메시지 제목
     * @param content
     *            동보전송 장문메시지 내용.
     * @param Messages
     *            메시지 배열. 수신자번호, 수신자명칭을 기재. 별도 발신번호와 내용 기재시 해당건만 개별전송.
     * @param file
     *            전송파일 최대크기 300Kbyte
     * @param reserveDT
     *            예약일시
     * @param adsYN
     *          광고문자 전송여부 
     * @param UserID
     *            연동회원 아이디
     * @param requestNum
     *            전송요청번호
     * @return receiptNum 접수번호.
     * @throws PopbillException
     */
    public String sendMMS(String CorpNum, String sender, String subject, String content, 
            Message[] Messages, File file, Date reserveDT, Boolean adsYN, String UserID, String requestNum)
            throws PopbillException;
    
    /**
     * 멀티 문자메시지 다량 전송. 발신번호, 내용 동보전송. 1회 최대 1000건.
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param sender
     *            동보전송 발신번호
     * @param senderName
     *            동보전송 발신자명
     * @param subject
     *            동보전송 장문메시지 제목
     * @param content
     *            동보전송 장문메시지 내용.
     * @param Messages
     *            메시지 배열. 수신자번호, 수신자명칭을 기재. 별도 발신번호와 내용 기재시 해당건만 개별전송.
     * @param file
     *            전송파일 최대크기 300Kbyte
     * @param reserveDT
     *            예약일시
     * @param adsYN
     *          광고문자 전송여부 
     * @param UserID
     *            연동회원 아이디
     * @param requestNum
     *            전송요청번호
     * @return receiptNum 접수번호.
     * @throws PopbillException
     */
    public String sendMMS(String CorpNum, String sender, String senderName, String subject, String content, 
            Message[] Messages, File file, Date reserveDT, Boolean adsYN, String UserID, String requestNum)
            throws PopbillException;

    /**
     * 단/장문 문자메시지(메시지 길이에 따라 단문/장문을 선택하여 전송) 1건 전송
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param sender
     *            발신자번호
     * @param receiver
     *            수신자번호
     * @param receiverName
     *            수신자명칭
     * @param subject
     *            메시지 제목
     * @param content
     *            메시지 내용
     * @param reserveDT
     *            예약전송시 예약일시.
     * @param UserID
     *            연동회원의 회원아이디
     * @return receiptNum 접수번호.
     * @throws PopbillException
     */
    public String sendXMS(String CorpNum, String sender, String receiver,
            String receiverName, String subject, String content,
            Date reserveDT, String UserID) throws PopbillException;
    
    /**
     * 단/장문 문자메시지(메시지 길이에 따라 단문/장문을 선택하여 전송) 1건 전송
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param sender
     *            발신자번호
     * @param receiver
     *            수신자번호
     * @param receiverName
     *            수신자명칭
     * @param subject
     *            메시지 제목
     * @param content
     *            메시지 내용
     * @param reserveDT
     *            예약전송시 예약일시.
     * @param adsYN
     *            광고문자 전송여부.
     * @param UserID
     *            연동회원의 회원아이디
     * @return receiptNum 접수번호.
     * @throws PopbillException
     */
    public String sendXMS(String CorpNum, String sender, String receiver,
            String receiverName, String subject, String content,
            Date reserveDT, Boolean adsYN, String UserID) throws PopbillException;

    /**
     * 단/장문 문자메시지(메시지 길이에 따라 단문/장문을 선택하여 전송) 다량 전송. 1회 최대 1000건.
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param Messages
     *            메시지 배열.
     * @param reserveDT
     *            예약일시
     * @param UserID
     *            연동회원 아이디
     * @return receiptNum 접수번호.
     * @throws PopbillException
     */
    public String sendXMS(String CorpNum, Message[] Messages, Date reserveDT,
            String UserID) throws PopbillException;

    /**
     * 단/장문 문자메시지(메시지 길이에 따라 단문/장문을 선택하여 전송) 다량전송. 발신번호, 내용 동보전송. 1회 최대 1000건.
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param sender
     *            동보전송 발신번호
     * @param subject
     *            동보전송 메시지 제목
     * @param content
     *            동보전송 메시지 내용.
     * @param Messages
     *            메시지 배열. 수신자번호, 수신자명칭을 기재. 별도 발신번호와 내용 기재시 해당건만 개별전송.
     * @param reserveDT
     *            예약일시
     * @param UserID
     *            연동회원 아이디
     * @return receiptNum 접수번호.
     * @throws PopbillException
     */
    public String sendXMS(String CorpNum, String sender, String subject,
            String content, Message[] Messages, Date reserveDT, String UserID)
            throws PopbillException;
    
    /**
     * 단/장문 문자메시지(메시지 길이에 따라 단문/장문을 선택하여 전송) 다량전송. 발신번호, 내용 동보전송. 1회 최대 1000건.
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param sender
     *            동보전송 발신번호
     * @param subject
     *            동보전송 메시지 제목
     * @param content
     *            동보전송 메시지 내용.
     * @param Messages
     *            메시지 배열. 수신자번호, 수신자명칭을 기재. 별도 발신번호와 내용 기재시 해당건만 개별전송.
     * @param reserveDT
     *            예약일시
     * @param UserID
     *            연동회원 아이디
     * @param tranNet
     *            광고문자 전송여부 
     * @return receiptNum 접수번호.
     * @throws PopbillException
     */
    public String sendXMS(String CorpNum, String sender, String subject,
            String content, Message[] Messages, Date reserveDT, Boolean adsYN, String UserID)
            throws PopbillException;
    
    /**
     * 단/장문 문자메시지(메시지 길이에 따라 단문/장문을 선택하여 전송) 다량전송. 발신번호, 내용 동보전송. 1회 최대 1000건.
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param sender
     *            동보전송 발신번호
     * @param senderName
     *            동보전송 발신자명
     * @param subject
     *            동보전송 메시지 제목
     * @param content
     *            동보전송 메시지 내용.
     * @param Messages
     *            메시지 배열. 수신자번호, 수신자명칭을 기재. 별도 발신번호와 내용 기재시 해당건만 개별전송.
     * @param reserveDT
     *            예약일시
     * @param UserID
     *            연동회원 아이디
     * @param tranNet
     *            광고문자 전송여부 
     * @return receiptNum 접수번호.
     * @throws PopbillException
     */
    public String sendXMS(String CorpNum, String sender, String senderName, String subject,
            String content, Message[] Messages, Date reserveDT, Boolean adsYN, String UserID)
            throws PopbillException;

    /**
     * 단/장문 문자메시지(메시지 길이에 따라 단문/장문을 선택하여 전송) 1건 전송
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param sender
     *            발신자번호
     * @param receiver
     *            수신자번호
     * @param receiverName
     *            수신자명칭
     * @param subject
     *            메시지 제목
     * @param content
     *            메시지 내용
     * @param reserveDT
     *            예약전송시 예약일시.
     * @param UserID
     *            연동회원의 회원아이디
     * @param requestNum
     *            전송요청번호
     * @return receiptNum 접수번호.
     * @throws PopbillException
     */
    public String sendXMS(String CorpNum, String sender, String receiver,
            String receiverName, String subject, String content,
            Date reserveDT, String UserID, String requestNum) throws PopbillException;
    
    /**
     * 단/장문 문자메시지(메시지 길이에 따라 단문/장문을 선택하여 전송) 1건 전송
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param sender
     *            발신자번호
     * @param receiver
     *            수신자번호
     * @param receiverName
     *            수신자명칭
     * @param subject
     *            메시지 제목
     * @param content
     *            메시지 내용
     * @param reserveDT
     *            예약전송시 예약일시.
     * @param adsYN
     *            광고문자 전송여부.
     * @param UserID
     *            연동회원의 회원아이디
     * @param requestNum
     *            전송요청번호
     * @return receiptNum 접수번호.
     * @throws PopbillException
     */
    public String sendXMS(String CorpNum, String sender, String receiver,
            String receiverName, String subject, String content,
            Date reserveDT, Boolean adsYN, String UserID, String requestNum) throws PopbillException;

    /**
     * 단/장문 문자메시지(메시지 길이에 따라 단문/장문을 선택하여 전송) 다량 전송. 1회 최대 1000건.
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param Messages
     *            메시지 배열.
     * @param reserveDT
     *            예약일시
     * @param UserID
     *            연동회원 아이디
     * @param requestNum
     *            전송요청번호
     * @return receiptNum 접수번호.
     * @throws PopbillException
     */
    public String sendXMS(String CorpNum, Message[] Messages, Date reserveDT,
            String UserID, String requestNum) throws PopbillException;

    /**
     * 단/장문 문자메시지(메시지 길이에 따라 단문/장문을 선택하여 전송) 다량전송. 발신번호, 내용 동보전송. 1회 최대 1000건.
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param sender
     *            동보전송 발신번호
     * @param subject
     *            동보전송 메시지 제목
     * @param content
     *            동보전송 메시지 내용.
     * @param Messages
     *            메시지 배열. 수신자번호, 수신자명칭을 기재. 별도 발신번호와 내용 기재시 해당건만 개별전송.
     * @param reserveDT
     *            예약일시
     * @param UserID
     *            연동회원 아이디
     * @param requestNum
     *            전송요청번호
     * @return receiptNum 접수번호.
     * @throws PopbillException
     */
    public String sendXMS(String CorpNum, String sender, String subject,
            String content, Message[] Messages, Date reserveDT, String UserID, String requestNum)
            throws PopbillException;
    
    /**
     * 단/장문 문자메시지(메시지 길이에 따라 단문/장문을 선택하여 전송) 다량전송. 발신번호, 내용 동보전송. 1회 최대 1000건.
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param sender
     *            동보전송 발신번호
     * @param subject
     *            동보전송 메시지 제목
     * @param content
     *            동보전송 메시지 내용.
     * @param Messages
     *            메시지 배열. 수신자번호, 수신자명칭을 기재. 별도 발신번호와 내용 기재시 해당건만 개별전송.
     * @param reserveDT
     *            예약일시
     * @param UserID
     *            연동회원 아이디
     * @param tranNet
     *            광고문자 전송여부 
     * @param requestNum
     *            전송요청번호
     * @return receiptNum 접수번호.
     * @throws PopbillException
     */
    public String sendXMS(String CorpNum, String sender, String subject,
            String content, Message[] Messages, Date reserveDT, Boolean adsYN, String UserID, String requestNum)
            throws PopbillException;
    
    /**
     * 단/장문 문자메시지(메시지 길이에 따라 단문/장문을 선택하여 전송) 다량전송. 발신번호, 내용 동보전송. 1회 최대 1000건.
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param sender
     *            동보전송 발신번호
     * @param senderName
     *            동보전송 발신자명
     * @param subject
     *            동보전송 메시지 제목
     * @param content
     *            동보전송 메시지 내용.
     * @param Messages
     *            메시지 배열. 수신자번호, 수신자명칭을 기재. 별도 발신번호와 내용 기재시 해당건만 개별전송.
     * @param reserveDT
     *            예약일시
     * @param UserID
     *            연동회원 아이디
     * @param tranNet
     *            광고문자 전송여부 
     * @param requestNum
     *            전송요청번호
     * @return receiptNum 접수번호.
     * @throws PopbillException
     */
    public String sendXMS(String CorpNum, String sender, String senderName, String subject,
            String content, Message[] Messages, Date reserveDT, Boolean adsYN, String UserID, String requestNum)
            throws PopbillException;    
    
    /**
     * 전송상태 확인
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param receiptNum
     *            전송시 접수번호.
     * @return SentMessage 배열.
     * @throws PopbillException
     */
    public SentMessage[] getMessages(String CorpNum, String receiptNum)
            throws PopbillException;
    
    /**
     * 전송상태 확인
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param receiptNum
     *            전송시 접수번호.
     * @param userID
     *            연동회원 아이디
     * @return SentMessage 배열.
     * @throws PopbillException
     */
    public SentMessage[] getMessages(String CorpNum, String receiptNum, String userID)
            throws PopbillException;    
    
    /**
     * 전송상태 확인
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param requestNum
     *            전송요청번호
     * @return SentMessage 배열.
     * @throws PopbillException
     */
    public SentMessage[] getMessagesRN(String CorpNum, String requestNum)
            throws PopbillException;
    
    /**
     * 전송상태 확인
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param requestNum
     *            전송요청번호
     * @param userID
     *            연동회원 아이디
     * @return SentMessage 배열.
     * @throws PopbillException
     */
    public SentMessage[] getMessagesRN(String CorpNum, String requestNum, String userID)
            throws PopbillException;        

    /**
     * 전송내역 요약정보 확인
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param receiptNumList
     *            문자전송 접수번호 배열
     * @return MessageBriefInfo 배열
     * @throws PopbillException
     */
    public MessageBriefInfo[] getStates(String corpNum, String[] receiptNumList)
            throws PopbillException;    

    /**
     * 전송내역 요약정보 확인
     * 
     * @param corpNum
     *            연동회원 사업자번호
     * @param receiptNumList
     *            문자전송 접수번호 배열
     * @param userID
     *            연동회원 아이디
     * @return MessageBriefInfo 배열
     * @throws PopbillException
     */
    public MessageBriefInfo[] getStates(String corpNum, String[] receiptNumList, String userID)
            throws PopbillException;    
    
    /**
     * 예약 메시지 전송 취소. 예약시간 기준 10분전의 건만 취소 가능.
     * 
     * @param corpNum
     *            연동회원 사업자번호
     * @param receiptNum
     *            전송시 접수번호.
     * @return Response 응답
     * @throws PopbillException
     */
    public Response cancelReserve(String CorpNum, String receiptNum) throws PopbillException;

    /**
     * 예약 메시지 전송 취소. 예약시간 기준 10분전의 건만 취소 가능.
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param receiptNum
     *            전송시 접수번호.
     * @param UserID
     *            연동회원 아이디
     * @return Response 응답
     * @throws PopbillException
     */
    public Response cancelReserve(String CorpNum, String receiptNum,
            String UserID) throws PopbillException;
    
    /**
     * 예약 메시지 전송 취소. 예약시간 기준 10분전의 건만 취소 가능.
     * 
     * @param corpNum
     *            연동회원 사업자번호
     * @param requestNum
     *            전송요청번호
     * @return Response 응답
     * @throws PopbillException
     */
    public Response cancelReserveRN(String CorpNum, String requestNum) throws PopbillException; 
    
    /**
     * 예약 메시지 전송 취소. 예약시간 기준 10분전의 건만 취소 가능.
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param requestNum
     *            전송요청번호
     * @param UserID
     *            연동회원 아이디
     * @return Response 응답
     * @throws PopbillException
     */
    public Response cancelReserveRN(String CorpNum, String requestNum,
            String UserID) throws PopbillException; 
    
    /**
     * 예약 메시지 전송 취소. 예약시간 기준 10분전의 건만 취소 가능.
     * 
     * @param corpNum
     *            연동회원 사업자번호
     * @param receiptNum
     *            전송시 접수번호.
     * @param receiveNum
     *            전송시 수신번호
     * @return Response 응답
     * @throws PopbillException
     */
    public Response cancelReservebyRCV(String CorpNum, String receiptNum, String receiveNum) throws PopbillException;
    
    /**
     * 예약 메시지 전송 취소. 예약시간 기준 10분전의 건만 취소 가능.
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param receiptNum
     *            전송시 접수번호.
     * @param receiveNum
     *            전송시 수신번호
     * @param UserID
     *            연동회원 아이디
     * @return Response 응답
     * @throws PopbillException
     */
    public Response cancelReservebyRCV(String CorpNum, String receiptNum, String receiveNum, 
            String UserID) throws PopbillException;
    
    /**
     * 예약 메시지 전송 취소. 예약시간 기준 10분전의 건만 취소 가능.
     * 
     * @param corpNum
     *            연동회원 사업자번호
     * @param requestNum
     *            전송시 요청번호
     * @param receiveNum
     *            전송시 수신번호
     * @return Response 응답
     * @throws PopbillException
     */
    public Response cancelReserveRNbyRCV(String CorpNum, String requestNum, String receiveNum) throws PopbillException; 
    
    /**
     * 예약 메시지 전송 취소. 예약시간 기준 10분전의 건만 취소 가능.
     * 
     * @param CorpNum
     *            연동회원 사업자번호
     * @param requestNum
     *            전송시 요청번호
     * @param receiveNum
     *            전송시 수신번호
     * @param UserID
     *            연동회원 아이디
     * @return Response 응답
     * @throws PopbillException
     */
    public Response cancelReserveRNbyRCV(String CorpNum, String requestNum, String receiveNum,
            String UserID) throws PopbillException; 
    
    /**
     * 문자전송내역 조회 
     * 
     * @param CorpNum
     *          연동회원 사업자번호 
     * @param SDate
     *          시작일자(yyyyMMdd)
     * @param EDate
     *          종료일자(yyyyMMdd)
     * @param State
     *          전송상태 배열, 1-대기, 2-성공, 3-실패, 4-취소 
     * @param Item
     *          검색대상 배열, SMS-단문, LMS-장문 ,MMS-포토  
     * @param ReserveYN
     *          예약여부, true-예약전송조회, false-전체조회 
     * @param SenderYN
     *          개인조회여부, true-개인조회, false-전체조회
     * @param Page
     *          페이지번호 
     * @param PerPage
     *          페이지당 검색갯수, 기본값 500, 최대값 1000 
     * @param Order
     *          정렬방향, D-내림차순, A-오름차순
     * @return 전송내역 조회 (see, com.popbill.api.message.MSGSearchResult)
     * @throws PopbillException
     */
    public MSGSearchResult search(String CorpNum, String SDate, String EDate, 
            String[] State, String[] Item, Boolean ReserveYN, Boolean SenderYN, 
            int Page, int PerPage, String Order) throws PopbillException;
    
    /**
     * 문자전송내역 조회 
     * 
     * @param CorpNum
     *          연동회원 사업자번호 
     * @param SDate
     *          시작일자(yyyyMMdd)
     * @param EDate
     *          종료일자(yyyyMMdd)
     * @param State
     *          전송상태 배열, 1-대기, 2-성공, 3-실패, 4-취소 
     * @param Item
     *          검색대상 배열, SMS-단문, LMS-장문 ,MMS-포토  
     * @param ReserveYN
     *          예약여부, true-예약전송조회, false-전체조회 
     * @param SenderYN
     *          개인조회여부, true-개인조회, false-전체조회
     * @param Page
     *          페이지번호 
     * @param PerPage
     *          페이지당 검색갯수, 기본값 500, 최대값 1000 
     * @param Order
     *          정렬방향, D-내림차순, A-오름차순
     * @param QString
     *          조회검색어. 발신자명 또는 수신자명 기재
     * @return 전송내역 조회 (see, com.popbill.api.message.MSGSearchResult)
     * @throws PopbillException
     */
    public MSGSearchResult search(String CorpNum, String SDate, String EDate, 
            String[] State, String[] Item, Boolean ReserveYN, Boolean SenderYN, 
            int Page, int PerPage, String Order, String QString) throws PopbillException;   
    
    /**
     * 080 수신거부목록 확인 
     * 
     * @param CorpNum
     *          연동회원 사업자번호 
     * @return 080수신거부목록 
     * @throws PopbillException
     */
    public AutoDeny[] getAutoDenyList(String CorpNum) throws PopbillException;
    
    /**
     * 080 수신거부 번호 확인
     * 
     * @param CorpNum 연동회원 사업자번호 
     * @return 080수신거부 응답
     * @throws PopbillException
     */
	public AutoDeny checkAutoDenyNumber(String CorpNum) throws PopbillException;
    
	/**
	 * 080 수신거부 번호 확인
	 * 
	 * @param CorpNum 연동회원 사업자번호 
	 * @param UserID 연동회원 유저아이디
	 * @return 080수신거부 응답
	 * @throws PopbillException
	 */
	public AutoDeny checkAutoDenyNumber(String CorpNum, String UserID) throws PopbillException;
    
    /**
     * 과금정보 확인 
     * 
     * @param CorpNum
     *          연동회원 사업자번호 
     * @param MsgType
     *          메시지 유형
     * @return 과금정보 (see, com.popbill.api.ChargeInfo)
     * @throws PopbillException
     */
    public ChargeInfo getChargeInfo(String CorpNum, MessageType MsgType) throws PopbillException;
    

    /**
     * 등록된 발신번호 목록 확인 
     * 
     * @param CorpNum
     *          연동회원 사업자번호
     * @return 발신번호 목록  
     * @throws PopbillException
     */
    public SenderNumber[] getSenderNumberList(String CorpNum) throws PopbillException;

    /**
     * 등록된 발신번호 목록 확인
     *
     * @param CorpNum
     *          연동회원 사업자번호
     * @param UserID
     *          연동회원 아이디
     * @return 발신번호 목록
     * @throws PopbillException
     */
    public SenderNumber[] getSenderNumberList(String CorpNum, String UserID) throws PopbillException;

    /**
     *  문자 발신번호 관리 팝업 URL을 반환
     *
     * @param CorpNum
     *          연동회원 사업자번호
     * @param UserID
     *          연동회원 유저아이디
     * @return 팝빌 URL (AccessToken값 포함. Token값은 응답후 30초까지만 유효함)
     * @throws PopbillException
     */
    public String getSenderNumberMgtURL(String CorpNum, String UserID) throws PopbillException;


    /**
     *  문자 전송내역 팝업 URL을 반환
     *
     * @param CorpNum
     *          연동회원 사업자번호
     * @param UserID
     *          연동회원 유저아이디
     * @return 팝빌 URL (AccessToken값 포함. Token값은 응답후 30초까지만 유효함)
     * @throws PopbillException
     */
    public String getSentListURL(String CorpNum, String UserID) throws PopbillException;


    /**
     *  발신번호 등록여부 확인
     *
     * @param CorpNum
     *          연동회원 사업자번호
     * @param SenderNumber
     *          발신번호
     * @return Response 응답
     * @throws PopbillException
     */
    public Response checkSenderNumber(String CorpNum, String SenderNumber) throws PopbillException;

    /**
     *  발신번호 등록여부 확인
     *
     * @param CorpNum
     *          연동회원 사업자번호
     * @param SenderNumber
     *          발신번호
     * @param UserID
     *          연동회원 유저아이디
     * @return Response 응답
     * @throws PopbillException
     */
    public Response checkSenderNumber(String CorpNum, String SenderNumber, String UserID) throws PopbillException;

}

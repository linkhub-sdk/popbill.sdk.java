package com.popbill.api.message;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

/**
 * Class for Message to send.
 * 
 * @author KimSeongjun
 * @version 1.0.0
 */
public class Message implements Serializable {
    private static final long serialVersionUID = -2994631030498272349L;

    @SerializedName("snd")
    private String sender;
    @SerializedName("sndnm")
    private String senderName;
    @SerializedName("rcv")
    private String receiver;
    @SerializedName("rcvnm")
    private String receiverName;
    @SerializedName("msg")
    private String content;
    @SerializedName("sjt")
    private String subject;
    @SerializedName("interOPRefKey")
    private String interOPRefKey;

    /**
     * 발신자 번호 확인
     * 
     * @return 발신자 번호
     */
    public String getSender() {
        return sender;
    }

    /**
     * 발신자 번호 설정
     * 
     * @param sender
     *               발신자번호
     */
    public void setSender(String sender) {
        this.sender = sender;
    }

    /**
     * 수신자 번호 확인
     * 
     * @return 수신자 번호
     */
    public String getReceiver() {
        return receiver;
    }

    /**
     * 수신자 번호 설정
     * 
     * @param receiver
     *                 수신자 번호
     */
    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    /**
     * 수신자 명칭 확인
     * 
     * @return 수신자 명칭
     */
    public String getReceiverName() {
        return receiverName;
    }

    /**
     * 수신자 명칭 설정
     * 
     * @param receiverName
     *                     수신자 명칭
     */
    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    /**
     * 발신 메시지 내용 확인
     * 
     * @return 발신메시지 내용
     */
    public String getContent() {
        return content;
    }

    /**
     * 발신 메시지 내용 설정
     * 
     * @param content
     *                발신 메시지 내용
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 장문메시지 제목 확인
     * 
     * @return 장문메시지 제목
     */
    public String getSubject() {
        return subject;
    }

    /**
     * 장문메시지 제목 설정
     * 
     * @param subject
     *                장문메시지 제목
     */
    public void setSubject(String subject) {
        this.subject = subject;
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
     * 발신자명 설정
     * 
     * @param senderName
     *                   발신자명
     */
    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    /**
     * 파트너 지정 키 확인
     * 
     * @return 파트너 지정 키.
     */
    public String getInterOPRefKey() {
        return interOPRefKey;
    }

    /**
     * 파트너 지정 키 설정.
     * @param interOPRefKey
     *                      파트너 지정 키.
     */
    public void setInterOPRefKey(String interOPRefKey) {
        this.interOPRefKey = interOPRefKey;
    }

}

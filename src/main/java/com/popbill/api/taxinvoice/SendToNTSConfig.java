package com.popbill.api.taxinvoice;

public class SendToNTSConfig {
	private boolean sendToNTS;
	
	/**
	 *  국세청 전송 설정 확인
	 * 
	 * @return 국세청 전송 설정
	 */
	public boolean getSendToNTS() {
		return this.sendToNTS;
	}
}

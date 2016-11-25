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
package com.popbill.api.statement;

/**
 * Class for StatementLog Information.
 * 
 * @author JeongYoHan
 * @version 1.0.0
 */
public class StatementLog {
	private Integer docLogType;
	private String log;
	private String procType;
	private String procCorpName;
	private String procContactName;
	private String procMemo;
	private String regDT;
	private String ip;
	
	/**
	 * 문서기록 형태 확인
	 * 
	 * @return the docLogTpye
	 */
	public Integer getDocLogType() {
		return docLogType;
	}
	/**
	 * 기록 확인
	 * 
	 * @return the log
	 */
	public String getLog() {
		return log;
	}
	/**
	 * 처리형태 확인
	 * 
	 * @param log the log to set
	 */
	public String getProcType() {
		return procType;
	}
	/**
	 * 처리회사명 확인
	 * 
	 * @return the procCorpName
	 */
	public String getProcCorpName() {
		return procCorpName;
	}
	/**
	 * 처리담당자명 확인
	 * 
	 * @return the procContactName
	 */
	public String getProcContactName() {
		return procContactName;
	}
	/**
	 * 처리시 메모 확인
	 * 
	 * @return the procMeme
	 */
	public String getProcMemo() {
		return procMemo;
	}
	/**
	 * 등록일시 확인
	 * 
	 * @return the regDT
	 */
	public String getRegDT() {
		return regDT;
	}
	/**
	 * 처리자 IP 확인
	 * 
	 * @return the ip
	 */
	public String getIp() {
		return ip;
	}
}

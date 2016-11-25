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
package com.popbill.api.taxinvoice;

/**
 * Class for TaxinvoiceLog Information.
 * 
 * @author KimSeongjun
 * @version 1.0.0
 */
public class TaxinvoiceLog {

	private Integer docLogType;
	private String log;
	private String procType;
	private String procCorpName;
	private String procContactName;
	private String procMemo;
	private String regDT;
	private String ip;

	/**
	 * returns DocLogType(문서기록형태)
	 * 
	 * @return DocLogType
	 */
	public Integer getDocLogType() {
		return docLogType;
	}

	/**
	 * returns Log(기록)
	 * 
	 * @return Log
	 */
	public String getLog() {
		return log;
	}

	/**
	 * returns ProcType(처리형태)
	 * 
	 * @return ProcType
	 */
	public String getProcType() {
		return procType;
	}

	/**
	 * returns ProcCorpName(처리회사명)
	 * 
	 * @return ProcCorpName
	 */
	public String getProcCorpName() {
		return procCorpName;
	}

	/**
	 * returns ProcContactName(처리담당자명)
	 * 
	 * @return ProcContactName
	 */
	public String getProcContactName() {
		return procContactName;
	}

	/**
	 * returns ProcMemo(처리시 메모)
	 * 
	 * @return ProcMemo
	 */
	public String getProcMemo() {
		return procMemo;
	}

	/**
	 * returns RegDT(등록일시)
	 * 
	 * @return RegDT
	 */
	public String getRegDT() {
		return regDT;
	}

	/**
	 * returns IP(처리자 IP)
	 * 
	 * @return IP
	 */
	public String getIP() {
		return ip;
	}
}

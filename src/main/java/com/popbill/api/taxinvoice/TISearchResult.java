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

import java.io.Serializable;
import java.util.List;

/**
 * Class for Taxinvoice Search Information.
 * 
 * @author JeongYohan
 * @version 1.0.0
 */

public class TISearchResult implements Serializable {
	private static final long serialVersionUID = -7380593516190369080L;
	
	private String code;
	private String total;
	private String perPage;
	private String pageNum;
	private String pageCount;
	private String message;
	
	private List<TaxinvoiceInfo> list;

	/**
	 * 응답코드 확인 
	 * 
	 * @return 응답코드 
	 */
	public String getCode() {
		return code;
	}

	/**
	 * 전체검색 개수 확인
	 * 
	 * @return 전체검색 개수 
	 */
	public String getTotal() {
		return total;
	}

	/**
	 * 페이지당목록 개수 확인
	 * @return 페이지당목록 개수
	 */ 
	public String getPerPage() {
		return perPage;
	}

	/**
	 * 페이지 번호 확인
	 * 
	 * @return 페이지 번호
	 */
	public String getPageNum() {
		return pageNum;
	}

	/**
	 * 페이지 개수 확인
	 * 
	 * @return 페이지 개수
	 */
	public String getPageCount() {
		return pageCount;
	}

	/**
	 * 요청응답 메시지 확인
	 * 
	 * @return 응답메시지 
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * 세금계산서 상태정보 리스트
	 * 
	 * @return 세금계산서 상태정보 리스트 
	 */
	public List<TaxinvoiceInfo> getList() {
		return list;
	}	
}

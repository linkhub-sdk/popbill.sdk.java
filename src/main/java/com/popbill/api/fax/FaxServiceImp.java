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
package com.popbill.api.fax;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.popbill.api.BaseServiceImp;
import com.popbill.api.ChargeInfo;
import com.popbill.api.FaxService;
import com.popbill.api.PopbillException;
import com.popbill.api.Response;

/**
 * Implementation of Popbill FaxService Interface
 * 
 * @author KimSeongjun
 * @version 1.0.0
 * @see com.popbill.api.FaxService
 */
public class FaxServiceImp extends BaseServiceImp implements FaxService {

	@Override
	protected List<String> getScopes() {
		return Arrays.asList("160"); 
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.popbill.api.FaxService#getUnitCost
	 */
	@Override
	public float getUnitCost(String CorpNum) throws PopbillException {
		UnitCostResponse response = httpget("/FAX/UnitCost",
				CorpNum, null, UnitCostResponse.class);

		return response.unitCost;
	}

	/*
	 * (non-Javadoc)
	 * @see com.popbill.api.FaxService#getURL(java.lang.String, java.lang.String)
	 */
	@Override
	public String getURL(String CorpNum, String TOGO) throws PopbillException {
		return getURL(CorpNum, null, TOGO);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.popbill.api.FaxService#getURL
	 */
	@Override
	public String getURL(String CorpNum, String UserID, String TOGO)
			throws PopbillException {

		URLResponse response = httpget("/FAX/?TG=" + TOGO, CorpNum,
				UserID, URLResponse.class);

		return response.url;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.popbill.api.FaxService#sendFAX(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.io.File, java.util.Date)
	 */
	@Override
	public String sendFAX(String CorpNum, String sendNum, String receiveNum,
			String receiveName, File file, Date reserveDT)
			throws PopbillException {

		Receiver receiver = new Receiver();
		receiver.setReceiveNum(receiveNum);
		receiver.setReceiveName(receiveName);
		
		return requestFax(CorpNum, sendNum, null, new Receiver[]{receiver}, 
				new File[]{file}, reserveDT, null, null);
	}

	/*
	 * (non-Javadoc)
	 * @see com.popbill.api.FaxService#sendFAX(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.io.File, java.util.Date, java.lang.String)
	 */
	@Override
	public String sendFAX(String CorpNum, String sendNum, String receiveNum,
			String receiveName, File file, Date reserveDT, String UserID)
			throws PopbillException {
		
		Receiver receiver = new Receiver();
		receiver.setReceiveNum(receiveNum);
		receiver.setReceiveName(receiveName);
		
		return requestFax(CorpNum, sendNum, null, new Receiver[]{receiver}, new File[]{file},
				reserveDT, UserID, null);
	}

	/*
	 * (non-Javadoc)
	 * @see com.popbill.api.FaxService#sendFAX(java.lang.String, java.lang.String, com.popbill.api.fax.Receiver[], java.io.File, java.util.Date)
	 */
	@Override
	public String sendFAX(String CorpNum, String sendNum, Receiver[] receivers,
			File file, Date reserveDT) throws PopbillException {

		return requestFax(CorpNum, sendNum, null, receivers, new File[]{file}, 
				reserveDT, null, null);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.popbill.api.FaxService#sendFAX(java.lang.String, java.lang.String, com.popbill.api.fax.Receiver[], java.io.File, java.util.Date, java.lang.String)
	 */
	@Override
	public String sendFAX(String CorpNum, String sendNum, Receiver[] receivers,
			File file, Date reserveDT, String UserID) throws PopbillException {
		
		return requestFax(CorpNum, sendNum, null, receivers, new File[]{file}, 
				reserveDT, UserID, null);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.popbill.api.FaxService#sendFAX(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.io.File[], java.util.Date)
	 */
	@Override
	public String sendFAX(String CorpNum, String sendNum, String receiveNum,
			String receiveName, File[] files, Date reserveDT)
			throws PopbillException {

		Receiver receiver = new Receiver();
		receiver.setReceiveNum(receiveNum);
		receiver.setReceiveName(receiveName);
				
		return requestFax(CorpNum, sendNum, null, new Receiver[]{receiver}, files,
				reserveDT, null, null);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.popbill.api.FaxService#sendFAX(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.io.File[], java.util.Date, java.lang.String)
	 */
	@Override
	public String sendFAX(String CorpNum, String sendNum, String receiveNum,
			String receiveName, File[] files, Date reserveDT, String UserID)
			throws PopbillException {
		
		Receiver receiver = new Receiver();
		receiver.setReceiveNum(receiveNum);
		receiver.setReceiveName(receiveName);
		
		return requestFax(CorpNum, sendNum, null, new Receiver[]{receiver}, files,
				reserveDT, UserID, null);	 
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.popbill.api.FaxService#sendFAX(java.lang.String, java.lang.String, com.popbill.api.fax.Receiver[], java.io.File[], java.util.Date, java.lang.String)
	 */
	@Override
	public String sendFAX(String CorpNum, String sendNum, Receiver[] receivers,
			File[] files, Date reserveDT, String UserID)
			throws PopbillException {
		
		return requestFax(CorpNum, sendNum, null, receivers, files, reserveDT,
				UserID, null);
	}

	/*
	 * (non-Javadoc)
	 * @see com.popbill.api.FaxService#sendFAX(java.lang.String, java.lang.String, java.lang.String, com.popbill.api.fax.Receiver[], java.io.File[], java.util.Date)
	 */
	@Override
	public String sendFAX(String CorpNum, String sendNum, String senderName,
			Receiver[] receivers, File[] files, Date reserveDT)
			throws PopbillException {
	
		return requestFax(CorpNum, sendNum, senderName, receivers, files, reserveDT,
				null, null);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.popbill.api.FaxService#sendFAX(java.lang.String, java.lang.String, java.lang.String, com.popbill.api.fax.Receiver[], java.io.File[], java.util.Date, java.lang.String, java.lang.Boolean)
	 */
	@Override
	public String sendFAX(String CorpNum, String sendNum, String senderName,
			Receiver[] receivers, File[] files, Date reserveDT, String UserID, Boolean adsYN)
			throws PopbillException {

		return requestFax(CorpNum, sendNum, senderName, receivers, files, reserveDT,
				UserID, adsYN);
	}
	
	
	private String requestFax(String CorpNum, String sendNum, String senderName,
			Receiver[] receivers, File[] files, Date reserveDT, String UserID, Boolean adsYN)
				throws PopbillException {
		if(sendNum == null || sendNum.isEmpty()) throw new PopbillException(-99999999,"발신번호가 입력되지 않았습니다.");
		if(receivers == null || receivers.length == 0) throw new PopbillException(-99999999,"수신처 정보가 입력되지 않았습니다.");
		if(files == null || files.length == 0) throw new PopbillException(-99999999,"발신파일 정보가 입력되지 않았습니다.");
		if(files.length > 5) throw new PopbillException(-99999999,"동보발신 최대 파일갯수는 5개 입니다.");
		
		SendRequest request = new SendRequest();
		
		request.snd = sendNum;
		request.rcvs = receivers;
		
		if (senderName != null)
			request.sndnm = senderName;
		
		if (reserveDT != null)
			request.sndDT = new SimpleDateFormat("yyyyMMddHHmmss", Locale.KOREA).format(reserveDT);
		
		if (adsYN != null && adsYN)
			request.adsYN = true;
		
		request.fCnt = files.length;
		
		String PostData = toJsonString(request);
		
		List<UploadFile> uploadFiles = new ArrayList<UploadFile>();
		
		for(File f : files) {
			UploadFile uf = new UploadFile();
			uf.fieldName = "file";
			uf.fileName = f.getName();
			try {
				uf.fileData = new FileInputStream(f);
			} catch (FileNotFoundException e) {
				throw new PopbillException(-99999999,"전송할 파일을 찾을 수 없습니다.",e);
			}
			uploadFiles.add(uf);
		}
		
		ReceiptResponse response = httppostFiles("/FAX", CorpNum, PostData, uploadFiles, UserID, ReceiptResponse.class);
		
		for(UploadFile uf : uploadFiles) {
			if(uf.fileData != null)
				try {
					uf.fileData.close();
				} catch (IOException e) {}
		}
		
		return response.receiptNum;
	}
		
	/*
	 * (non-Javadoc)
	 * @see com.popbill.api.FaxService#resendFAX(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.util.Date, java.lang.String)
	 */
	@Override
	public String resendFAX(String CorpNum, String receiptNum, String sendNum, String senderName,
			String receiveNum, String receiveName, Date reserveDT, String UserID)
			throws PopbillException {
				
		Receiver receiver = null;
		
		if ( !receiveNum.isEmpty() || !receiveName.isEmpty() ) {
			receiver = new Receiver();
			receiver.setReceiveNum(receiveNum);
			receiver.setReceiveName(receiveName);
			return resendFAX(CorpNum,receiptNum, sendNum, senderName, new Receiver[]{receiver},reserveDT,UserID);
		}
		return resendFAX(CorpNum,receiptNum, sendNum, senderName, null, reserveDT, UserID);	
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.popbill.api.FaxService#resendFAX(java.lang.String, java.lang.String, java.lang.String, java.lang.String, com.popbill.api.fax.Receiver[], java.util.Date)
	 */
	@Override
	public String resendFAX(String CorpNum, String receiptNum, String sendNum,
			String senderName, Receiver[] receivers, Date reserveDT) throws PopbillException {
			
		return resendFAX(CorpNum, receiptNum, sendNum, senderName, receivers, reserveDT, null);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.popbill.api.FaxService#resendFAX(java.lang.String, java.lang.String, java.lang.String, java.lang.String, com.popbill.api.fax.Receiver[], java.util.Date, java.lang.String)
	 */
	@Override
	public String resendFAX(String CorpNum, String receiptNum, String sendNum,
			String senderName, Receiver[] receivers, Date reserveDT,
			String UserID) throws PopbillException {
		
		if(receiptNum == null || receiptNum.isEmpty()) throw new PopbillException(-99999999,"팩스 접수번호(receiptNum)가 입력되지 않았습니다.");
		
		SendRequest request = new SendRequest();
		
		if (!sendNum.isEmpty() || sendNum != null) request.snd = sendNum;
		if (!senderName.isEmpty() || senderName != null) request.sndnm = senderName;
		if (reserveDT != null) request.sndDT = new SimpleDateFormat("yyyyMMddHHmmss", Locale.KOREA).format(reserveDT);
		
		if (receivers != null) request.rcvs = receivers;
		
		String PostData = toJsonString(request);
		
		ReceiptResponse response = httppost("/FAX/"+receiptNum, CorpNum, PostData, UserID, ReceiptResponse.class);
		
		return response.receiptNum;
	}
	

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.popbill.api.FaxService#getFaxResult
	 */
	@Override
	public FaxResult[] getFaxResult(String CorpNum, String receiptNum)
			throws PopbillException {
		if (receiptNum == null)
			throw new PopbillException(-99999999, "접수번호가 입력되지 않았습니다.");

		return httpget("/FAX/" + receiptNum, CorpNum, null,
				FaxResult[].class);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.popbill.api.FaxService#cancelReserve(java.lang.String, java.lang.String)
	 */
	@Override
	public Response cancelReserve(String CorpNum, String receiptNum)
			throws PopbillException {
		return cancelReserve(CorpNum, receiptNum, null);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.popbill.api.FaxService#cancelReserve
	 */
	@Override
	public Response cancelReserve(String CorpNum, String receiptNum,String UserID)
			throws PopbillException {
		if (receiptNum == null)
			throw new PopbillException(-99999999, "접수번호가 입력되지 않았습니다.");

		return httpget("/FAX/" + receiptNum + "/Cancel", CorpNum, UserID,
				Response.class);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.popbill.api.FaxService#search(java.lang.String, java.lang.String, java.lang.String, java.lang.String[], java.lang.Boolean, java.lang.Boolean, int, int, java.lang.String)
	 */
	@Override
	public FAXSearchResult search(String CorpNum, String SDate, String EDate,
			String[] State, Boolean ReserveYN, Boolean SenderOnly, int Page,
			int PerPage, String Order) throws PopbillException {
		if (SDate == null)
			throw new PopbillException(-99999999, "시작일자가 입력되지 않았습니다.");
		if (EDate == null)
			throw new PopbillException(-99999999, "종료일자가 입력되지 않았습니다.");
		
		String uri = "/FAX/Search?SDate=" + SDate;
		uri += "&EDate=" + EDate;
		uri += "&State=" + Arrays.toString(State)
				.replaceAll("\\[|\\]|\\s", "");
		
		if (ReserveYN) {
			uri += "&ReserveYN=1";
		} else {
			uri += "&ReserveYN=0";
		}
		
		if (SenderOnly) {
			uri +="&SenderOnly=1";
		} else {
			uri += "&SenderOnly=0";
		}
		
		uri += "&Page=" + Integer.toString(Page);
		uri += "&PerPage=" + Integer.toString(PerPage);
		uri += "&Order=" + Order;
		
		return httpget(uri, CorpNum, null, FAXSearchResult.class);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.popbill.api.FaxService#getChargeInfo(java.lang.String)
	 */
	
	@Override
	public ChargeInfo getChargeInfo(String CorpNum) throws PopbillException {
		return httpget("/FAX/ChargeInfo", CorpNum, null, ChargeInfo.class);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.popbill.api.FaxService#getSenderNumberList(java.lang.String)
	 */
	@Override
	public SenderNumber[] getSenderNumberList(String CorpNum)
			throws PopbillException {
		return getSenderNumberList(CorpNum, null);
	}

	/*
	 * (non-Javadoc)
	 * @see com.popbill.api.FaxService#getSenderNumberList(java.lang.String, java.lang.String)
	 */
	@Override
	public SenderNumber[] getSenderNumberList(String CorpNum, String UserID)
			throws PopbillException {
		
		return httpget("/FAX/SenderNumber", CorpNum, UserID, SenderNumber[].class);
	}

	
	/*
	 * 광고팩스 전송을 위한 adsYN 추가 SendFAX Overloading
	 */
	
	/*
	 * (non-Javadoc)
	 * @see com.popbill.api.FaxService#sendFAX(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.io.File, java.util.Date, java.lang.Boolean)
	 */
	@Override
	public String sendFAX(String CorpNum, String sendNum, String receiveNum,
			String receiveName, File file, Date reserveDT, Boolean adsYN)
			throws PopbillException {
		
		Receiver receiver = null;
				
		receiver = new Receiver();
		receiver.setReceiveNum(receiveNum);
		receiver.setReceiveName(receiveName);
		
		return requestFax(CorpNum, sendNum, null, new Receiver[]{receiver}, 
				new File[]{file}, reserveDT, null, adsYN);
	}

	/*
	 * (non-Javadoc)
	 * @see com.popbill.api.FaxService#sendFAX(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.io.File, java.util.Date, java.lang.String, java.lang.Boolean)
	 */
	@Override
	public String sendFAX(String CorpNum, String sendNum, String receiveNum,
			String receiveName, File file, Date reserveDT, String UserID,
			Boolean adsYN) throws PopbillException {
		
		Receiver receiver = new Receiver();
		receiver.setReceiveNum(receiveNum);
		receiver.setReceiveName(receiveName);
		
		return requestFax(CorpNum, sendNum, null, new Receiver[]{receiver}, 
				new File[]{file}, reserveDT, UserID, adsYN);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.popbill.api.FaxService#sendFAX(java.lang.String, java.lang.String, com.popbill.api.fax.Receiver[], java.io.File, java.util.Date, java.lang.Boolean)
	 */
	@Override
	public String sendFAX(String CorpNum, String sendNum, Receiver[] receivers,
			File file, Date reserveDT, Boolean adsYN) throws PopbillException {

		return requestFax(CorpNum, sendNum, null, receivers, new File[]{file},
				reserveDT, null, adsYN);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.popbill.api.FaxService#sendFAX(java.lang.String, java.lang.String, com.popbill.api.fax.Receiver[], java.io.File, java.util.Date, java.lang.String, java.lang.Boolean)
	 */
	@Override
	public String sendFAX(String CorpNum, String sendNum, Receiver[] receivers,
			File file, Date reserveDT, String UserID, Boolean adsYN)
			throws PopbillException {
		
		return requestFax(CorpNum, sendNum, null, receivers, new File[]{file}, 
				reserveDT, UserID, adsYN);
	}

	/*
	 * (non-Javadoc)
	 * @see com.popbill.api.FaxService#sendFAX(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.io.File[], java.util.Date, java.lang.Boolean)
	 */
	@Override
	public String sendFAX(String CorpNum, String sendNum, String receiveNum,
			String receiveName, File[] files, Date reserveDT, Boolean adsYN)
			throws PopbillException {

		Receiver receiver = new Receiver();
		receiver.setReceiveNum(receiveNum);
		receiver.setReceiveName(receiveName);
				
		return requestFax(CorpNum, sendNum, null, new Receiver[]{receiver}, files,
				reserveDT, null, adsYN);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.popbill.api.FaxService#sendFAX(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.io.File[], java.util.Date, java.lang.String, java.lang.Boolean)
	 */
	@Override
	public String sendFAX(String CorpNum, String sendNum, String receiveNum,
			String receiveName, File[] files, Date reserveDT, String UserID,
			Boolean adsYN) throws PopbillException {
		
		Receiver receiver = new Receiver();
		receiver.setReceiveNum(receiveNum);
		receiver.setReceiveName(receiveName);
				
		return requestFax(CorpNum, sendNum, null, new Receiver[]{receiver}, files, 
				reserveDT, UserID, adsYN);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.popbill.api.FaxService#sendFAX(java.lang.String, java.lang.String, com.popbill.api.fax.Receiver[], java.io.File[], java.util.Date, java.lang.String, java.lang.Boolean)
	 */
	@Override
	public String sendFAX(String CorpNum, String sendNum, Receiver[] receivers,
			File[] files, Date reserveDT, String UserID, Boolean adsYN)
			throws PopbillException {
	
		return requestFax(CorpNum, sendNum, null, receivers, files, reserveDT,
				UserID, adsYN);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.popbill.api.FaxService#sendFAX(java.lang.String, java.lang.String, java.lang.String, com.popbill.api.fax.Receiver[], java.io.File[], java.util.Date, java.lang.Boolean)
	 */
	@Override
	public String sendFAX(String CorpNum, String sendNum, String senderName,
			Receiver[] receivers, File[] files, Date reserveDT, Boolean adsYN)
			throws PopbillException {
		return requestFax(CorpNum, sendNum, senderName, receivers, files, reserveDT,
				null, adsYN);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.popbill.api.FaxService#sendFAX(java.lang.String, java.lang.String, java.lang.String, com.popbill.api.fax.Receiver[], java.io.File[], java.util.Date, java.lang.String)
	 */
	@Override
	public String sendFAX(String CorpNum, String sendNum, String senderName,
			Receiver[] receivers, File[] files, Date reserveDT, String UserID)
			throws PopbillException {
		
		return requestFax(CorpNum, sendNum, senderName, receivers, files, reserveDT,
				UserID, false);
	}

	protected class SendRequest {
		public String snd;
		public String sndnm;
		public String sndDT;
		public Integer fCnt;
		public Boolean adsYN;
		public Receiver[] rcvs;
	}

	protected class ReceiptResponse {
		public String receiptNum;
	}




}
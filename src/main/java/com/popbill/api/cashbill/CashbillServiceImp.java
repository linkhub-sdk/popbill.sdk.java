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
package com.popbill.api.cashbill;

import java.util.Arrays;
import java.util.List;

import com.popbill.api.BaseServiceImp;
import com.popbill.api.CashbillService;
import com.popbill.api.PopbillException;
import com.popbill.api.Response;

/**
 *  Implementation of Popbill CashbillService Interface
 * 
 * @author JeongYoHan
 * @version 1.0.0
 * @see com.popbill.api.CashbillService
 */
public class CashbillServiceImp extends BaseServiceImp implements CashbillService{

	@Override
	protected List<String> getScopes(){
		return Arrays.asList("140");
	}
	
	/* (non-Javadoc)
	 * @see com.popbill.api.CashbillService#getURL(java.lang.String, java.lang.String,  java.lang.String)
	 */
	@Override
	public String getURL(String CorpNum, String UserID, String TOGO)
			throws PopbillException {
		
		URLResponse response = httpget("/Cashbill?TG=" + TOGO, 
				CorpNum, UserID, URLResponse.class);
		
		return response.url;
	}

	/* (non-Javadoc)
	 * @see com.popbill.api.CashbillService#getUnitCost(java.lang.String)
	 */
	@Override
	public float getUnitCost(String CorpNum) throws PopbillException {
		
		UnitCostResponse response = httpget("/Cashbill?cfg=UNITCOST",
				CorpNum,null, UnitCostResponse.class);
		
		return response.unitCost;
		
	}

	/* (non-Javadoc)
	 * @see com.popbill.api.CashbillService#checkMgtKeyInuse(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean checkMgtKeyInUse(String CorpNum, String MgtKey)
			throws PopbillException {
		if (MgtKey == null || MgtKey.isEmpty())
			throw new PopbillException(-99999999, "관리번호가 입력되지 않았습니다.");
		
		try{
			CashbillInfo info = httpget("/Cashbill/" + MgtKey, CorpNum, null, CashbillInfo.class);
			
			return (info.getItemKey() == null || info.getItemKey().isEmpty()) == false;
			
			} catch(PopbillException PE){
				if(PE.getCode() == -14000003)
					return false;
				throw PE;
			}
	}

	/* (non-Javadoc)
	 * @see com.popbill.api.CashbillService#register(java.lang.String, com.popbill.api.cashbill.Cashbill)
	 */
	@Override
	public Response register(String CorpNum, Cashbill cashbill)
			throws PopbillException {
		return register(CorpNum, cashbill, null);
	}
	
	/* (non-Javadoc)
	 * @see com.popbill.api.CashbillService#register(java.lang.String, com.popbill.api.cashbill.Cashbill, java.lang.String)
	 */
	@Override
	public Response register(String CorpNum, Cashbill cashbill, String UserID)
			throws PopbillException {
		if (cashbill == null)
			throw new PopbillException(-99999999, "현금영수증정보가 입력되지 않았습니다.");
		
		String PostData = toJsonString(cashbill);
		
		return httppost("/Cashbill",CorpNum, PostData, 
				UserID, Response.class);
		
	}
	/* (non-Javadoc)
	 * @see com.popbill.api.CashbillService#update(java.lang.String, java.lang.String, com.popbill.api.cashbill.Cashbill)
	 */
	@Override
	public Response update(String CorpNum, String MgtKey, Cashbill cashbill)
			throws PopbillException {
		if (MgtKey == null || MgtKey.isEmpty())
			throw new PopbillException(-99999999, "관리번호가 입력되지 않았습니다.");
		
		return update(CorpNum, MgtKey, cashbill, null);		
	}

	/* (non-Javadoc)
	 * @see com.popbill.api.CashbillService#update(java.lang.String, java.lang.String, com.popbill.api.cashbill.Cashbill, java.lang.String)
	 */
	@Override
	public Response update(String CorpNum, String MgtKey, Cashbill cashbill,
			String UserID) throws PopbillException {
		if (MgtKey == null || MgtKey.isEmpty())
			throw new PopbillException(-99999999, "관리번호가 입력되지 않았습니다.");
		
		String PostData = toJsonString(cashbill);
		
		return httppost("/Cashbill/"+MgtKey, CorpNum, 
				PostData, UserID, "PATCH", Response.class);
	}

	/* (non-Javadoc)
	 * @see com.popbill.api.CashbillService#delete(java.lang.String, java.lang.String)
	 */
	@Override
	public Response delete(String CorpNum, String MgtKey)
			throws PopbillException {
		if (MgtKey == null || MgtKey.isEmpty())
			throw new PopbillException(-99999999, "관리번호가 입력되지 않았습니다.");
		
		return delete(CorpNum, MgtKey, null);
	}
	
	/* (non-Javadoc)
	 * @see com.popbill.api.CashbillService#delete(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public Response delete(String CorpNum, String MgtKey, String UserID)
			throws PopbillException {
		if (MgtKey == null || MgtKey.isEmpty())
			throw new PopbillException(-99999999, "관리번호가 입력되지 않았습니다.");
		
		return httppost("/Cashbill/"+MgtKey, CorpNum, null, 
				UserID, "DELETE", Response.class);
	}
	
	/* (non-Javadoc)
	 * @see com.popbill.api.CashbillService#issue(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public Response issue(String CorpNum, String MgtKey, String Memo)
			throws PopbillException {
		if (MgtKey == null || MgtKey.isEmpty())
			throw new PopbillException(-99999999, "관리번호가 입력되지 않았습니다.");
		
		return issue(CorpNum, MgtKey, Memo, null);
	}
	
	/* (non-Javadoc)
	 * @see com.popbill.api.CashbillService#issue(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public Response issue(String CorpNum, String MgtKey, String Memo,
			String UserID) throws PopbillException {
		if (MgtKey == null || MgtKey.isEmpty())
			throw new PopbillException(-99999999, "관리번호가 입력되지 않았습니다.");
		
		String PostData = toJsonString(new MemoRequest(Memo));
		
		return httppost("/Cashbill/" + MgtKey, CorpNum, PostData, 
				UserID, "ISSUE", Response.class);
	}
	
	/* (non-Javadoc)
	 * @see com.popbill.api.CashbillService#cancelIssue(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public Response cancelIssue(String CorpNum, String MgtKey, String Memo)
			 throws PopbillException {
		if (MgtKey == null || MgtKey.isEmpty())
			throw new PopbillException(-99999999, "관리번호가 입력되지 않았습니다.");
		return cancelIssue(CorpNum, MgtKey, Memo, null);
	}
	
	/* (non-Javadoc)
	 * @see com.popbill.api.CashbillService#cancelIssue(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public Response cancelIssue(String CorpNum, String MgtKey, String Memo,
			String UserID) throws PopbillException {
		if (MgtKey == null || MgtKey.isEmpty())
			throw new PopbillException(-99999999, "관리번호가 입력되지 않았습니다.");
		
		String PostData = toJsonString(new MemoRequest(Memo));
		
		return httppost("/Cashbill/" + MgtKey, CorpNum, PostData,
				UserID, "CANCELISSUE", Response.class);
	}
	
	/* (non-Javadoc)
	 * @see com.popbill.api.CashbillService#sendEmail(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public Response sendEmail(String CorpNum, String MgtKey, String Receiver)
			 throws PopbillException {
		if (MgtKey == null || MgtKey.isEmpty())
			throw new PopbillException(-99999999, "관리번호가 입력되지 않았습니다.");
		
		return sendEmail(CorpNum, MgtKey, Receiver, null);
	}
	
	/* (non-Javadoc)
	 * @see com.popbill.api.CashbillService#sendEmail(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public Response sendEmail(String CorpNum, String MgtKey, String Receiver,
			String UserID) throws PopbillException {
		if (MgtKey == null || MgtKey.isEmpty())
			throw new PopbillException(-99999999, "관리번호가 입력되지 않았습니다.");
		
		ResendRequest request  = new ResendRequest();
		
		request.receiver = Receiver;
		
		String PostData = toJsonString(request);
		
		return httppost("/Cashbill/" + MgtKey, CorpNum, PostData, 
				UserID, "EMAIL", Response.class);
	}

	/* (non-Javadoc)
	 * @see com.popbill.api.CashbillService#sendSMS(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public Response sendSMS(String CorpNum, String MgtKey, String Sender,
			String Receiver, String Contents)
			throws PopbillException {
		if (MgtKey == null || MgtKey.isEmpty())
			throw new PopbillException(-99999999, "관리번호가 입력되지 않았습니다.");
		
		return sendSMS(CorpNum, MgtKey, Sender, Receiver, Contents, null);
	}
	
	/* (non-Javadoc)
	 * @see com.popbill.api.CashbillService#sendSMS(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public Response sendSMS(String CorpNum, String MgtKey, String Sender,
			String Receiver, String Contents, String UserID)
			throws PopbillException {
		if (MgtKey == null || MgtKey.isEmpty())
			throw new PopbillException(-99999999, "관리번호가 입력되지 않았습니다.");
		
		ResendRequest request = new ResendRequest();
		
		request.sender = Sender;
		request.receiver = Receiver;
		request.contents = Contents;
		
		String PostData = toJsonString(request);
		
		return httppost("/Cashbill/" + MgtKey, CorpNum, PostData, 
				UserID, "SMS", Response.class);
	}
	
	/* (non-Javadoc)
	 * @see com.popbill.api.CashbillService#sendFAX(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public Response sendFAX(String CorpNum, String MgtKey, String Sender,
			String Receiver) throws PopbillException {
		if (MgtKey == null || MgtKey.isEmpty())
			throw new PopbillException(-99999999, "관리번호가 입력되지 않았습니다.");
		
		return sendFAX(CorpNum, MgtKey, Sender, Receiver, null);
	}

	/* (non-Javadoc)
	 * @see com.popbill.api.CashbillService#sendFAX(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public Response sendFAX(String CorpNum, String MgtKey, String Sender,
			String Receiver, String UserID) throws PopbillException {
		if (MgtKey == null || MgtKey.isEmpty())
			throw new PopbillException(-99999999, "관리번호가 입력되지 않았습니다.");
		
		ResendRequest request = new ResendRequest();
		
		request.sender = Sender;
		request.receiver = Receiver;
		
		String PostData = toJsonString(request);
		
		return httppost("/Cashbill/" + MgtKey, CorpNum, 
				PostData, UserID, "FAX", Response.class);
	}

	/* (non-Javadoc)
	 * @see com.popbill.api.CashbillService#getDetailInfo(java.lang.String, java.lang.String)
	 */
	@Override
	public Cashbill getDetailInfo(String CorpNum, String MgtKey)
			throws PopbillException {
		if (MgtKey == null || MgtKey.isEmpty())
			throw new PopbillException(-99999999, "관리번호가 입력되지 않았습니다.");
		
		return httpget("/Cashbill/" + MgtKey + "?Detail", CorpNum, 
				null, Cashbill.class);
	}

	/* (non-Javadoc)
	 * @see com.popbill.api.CashbillService#getInfo(java.lang.String, java.lang.String)
	 */
	@Override
	public CashbillInfo getInfo(String CorpNum, String MgtKey)
			throws PopbillException {
		if (MgtKey == null || MgtKey.isEmpty())
			throw new PopbillException(-99999999, "관리번호가 입력되지 않았습니다.");
		
		return httpget("/Cashbill/" + MgtKey, CorpNum, 
				null, CashbillInfo.class);
	}

	/* (non-Javadoc)
	 * @see com.popbill.api.CashbillService#getInfos(java.lang.String, java.lang.String[])
	 */
	@Override
	public CashbillInfo[] getInfos(String CorpNum, String[] MgtKeyList)
			throws PopbillException {
		if (MgtKeyList == null || MgtKeyList.length == 0)
			throw new PopbillException(-99999999, "관리번호배열이 입력되지 않았습니다.");
		
		String PostData = toJsonString(MgtKeyList);
		
		return httppost("/Cashbill/States", CorpNum, PostData, 
				null, CashbillInfo[].class);
	}

	/* (non-Javadoc)
	 * @see com.popbill.api.CashbillService#getLogs(java.lang.String, java.lang.String)
	 */
	@Override
	public CashbillLog[] getLogs(String CorpNum, String MgtKey)
			throws PopbillException {
		if (MgtKey == null || MgtKey.isEmpty())
			throw new PopbillException(-99999999, "관리번호가 입력되지 않았습니다.");
		
		return httpget("/Cashbill/" + MgtKey +"/Logs", CorpNum, null, CashbillLog[].class);
	}
	
	/* (non-Javadoc)
	 * @see com.popbill.api.CashbillService#getPrintURL(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public String getPrintURL(String CorpNum, String MgtKey, String UserID)
			throws PopbillException {
		if (MgtKey == null || MgtKey.isEmpty())
			throw new PopbillException(-99999999, "관리번호가 입력되지 않았습니다.");
		
		URLResponse response = httpget("/Cashbill/" + MgtKey + "?TG=PRINT", 
				CorpNum, UserID, URLResponse.class);
		
		return response.url;
	}
	
	/* (non-Javadoc)
	 * @see com.popbill.api.CashbillService#getEPrintURL(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public String getEPrintURL(String CorpNum, String MgtKey, String UserID)
			throws PopbillException {
		if (MgtKey == null || MgtKey.isEmpty())
			throw new PopbillException(-99999999, "관리번호가 입력되지 않았습니다.");
		
		URLResponse response = httpget("/Cashbill/" + MgtKey + "?TG=EPRINT", 
				CorpNum, UserID, URLResponse.class);
		
		return response.url;
	}
	
	/* (non-Javadoc)
	 * @see com.popbill.api.CashbillService#getMassPrintURL(java.lang.String, java.lang.String[], java.lang.String)
	 */
	@Override
	public String getMassPrintURL(String CorpNum, String[] MgtKeyList,
			String UserID) throws PopbillException {
		if (MgtKeyList == null || MgtKeyList.length == 0)
			throw new PopbillException(-99999999, "관리번호배열이 입력되지 않았습니다.");
		
		String PostData = toJsonString(MgtKeyList);
		
		URLResponse response = httppost("/Cashbill/Prints", CorpNum, 
				PostData, UserID, URLResponse.class);
		
		return response.url;
		
	}
	
	/* (non-Javadoc)
	 * @see com.popbill.api.CashbillService#getMailURL(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public String getMailURL(String CorpNum, String MgtKey, String UserID)
			throws PopbillException {
		if (MgtKey == null || MgtKey.isEmpty())
			throw new PopbillException(-99999999, "관리번호가 입력되지 않았습니다.");
		
		URLResponse response = httpget("/Cashbill/" + MgtKey + "?TG=MAIL", 
				CorpNum, UserID, URLResponse.class);
		
		return response.url;
	}
	
	/* (non-Javadoc)
	 * @see com.popbill.api.CashbillService#getPopUpURL(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public String getPopUpURL(String CorpNum, String MgtKey, String UserID)
			throws PopbillException {
		if (MgtKey == null || MgtKey.isEmpty())
			throw new PopbillException(-99999999, "관리번호가 입력되지 않았습니다.");
		
		URLResponse response = httpget("/Cashbill/" + MgtKey + "?TG=POPUP", 
				CorpNum, UserID, URLResponse.class);
		
		return response.url;
	}
	
	protected class MemoRequest {
		public MemoRequest(String memo){
			this.memo = memo;
		}
		
		public String memo;
	}
	
	protected class ResendRequest {
		public String receiver;
		public String sender = null;
		public String contents = null;
	}

	

	

}

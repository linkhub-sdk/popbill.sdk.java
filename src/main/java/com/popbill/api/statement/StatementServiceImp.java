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
package com.popbill.api.statement;

import java.io.InputStream;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.popbill.api.AttachedFile;
import com.popbill.api.BaseServiceImp;
import com.popbill.api.PopbillException;
import com.popbill.api.Response;
import com.popbill.api.StatementService;

/**
 *  Implementation of Popbill StatemenService Interface
 * 
 * @author KimSeongjun
 * @version 1.0.0
 * @see com.popbill.api.TaxinvoiceService
 */
public class StatementServiceImp extends BaseServiceImp implements StatementService {

	@Override
	protected List<String> getScopes(){
		return Arrays.asList("121","122","123","124","125","126");
	}
	
	/* (non-Javadoc)
	 * @see com.popbill.api.StatementService#getURL(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public String getURL(String CorpNum, String UserID, String TOGO)
			throws PopbillException {

		URLResponse	response = httpget("/Statement?TG=" + TOGO, CorpNum, UserID,URLResponse.class);
		
		return response.url;
	}
	
	/* (non-Javadoc)
	 * @see com.popbill.api.StatementService#getUnitCost(java.lang.String, java.number.Integer)
	 */
	@Override
	public float getUnitCost(String CorpNum, int ItemCode)
			throws PopbillException {
		
		UnitCostResponse response = httpget("/Statement/" + ItemCode + "?cfg=UNITCOST",
				CorpNum, null, UnitCostResponse.class);
		
		return response.unitCost;
	}
	
	/* (non-Javadoc)
	 * @see com.popbill.api.StatementService#checkMgtKeyInUse(java.lang.String, java.number.Integer, java.lang.String)
	 */
	@Override
	public boolean checkMgtKeyInUse(String CorpNum, int ItemCode, String MgtKey)
			throws PopbillException {
		if (MgtKey == null || MgtKey.isEmpty())
			throw new PopbillException(-99999999, "관리번호가 입력되지 않았습니다.");
		
		try{
			StatementInfo info = httpget("/Statement/" + ItemCode + "/" + MgtKey, 
					CorpNum, null, StatementInfo.class);
			
			return (info.getItemKey() == null || info.getItemKey().isEmpty()) == false;
			
			} catch(PopbillException PE){
			if(PE.getCode() == -12000004)
				return false;
			throw PE;
		}
		
	}
	
	/* (non-Javadoc)
	 * @see com.popbill.api.StatementService#register(java.lang.String, com.popbill.api.statement.Statement)
	 */
	@Override
	public Response register(String CorpNum, Statement statement)
			throws PopbillException {
		return register(CorpNum, statement, null);
	}
	
	/* (non-Javadoc)
	 * @see com.popbill.api.StatementService#register(java.lang.String, com.popbill.api.statement.Statement, java.lang.String)
	 */
	@Override
	public Response register(String CorpNum, Statement statement, String UserID)
			throws PopbillException {
		
		String PostData = toJsonString(statement);
		
		return httppost("/Statement", CorpNum, PostData, UserID, Response.class);
	}
		
	/* (non-Javadoc)
	 * @see com.popbill.api.StatementService#update(java.lang.String, java.number.Integer, java.lang.String, com.popbill.api.statement.Statement)
	 */
	@Override
	public Response update(String CorpNum, int ItemCode, String MgtKey,
			Statement statement) throws PopbillException {
		if (MgtKey == null || MgtKey.isEmpty())
			throw new PopbillException(-99999999, "관리번호가 입력되지 않았습니다.");
				
		return update(CorpNum, ItemCode, MgtKey, statement, null);
	}
	
	/* (non-Javadoc)
	 * @see com.popbill.api.StatementService#update(java.lang.String, java.number.Integer, java.lang.String, com.popbill.api.statement.Statement, java.lang.String)
	 */
	@Override
	public Response update(String CorpNum, int ItemCode, String MgtKey,
			Statement statement, String UserID) throws PopbillException {
		if (MgtKey == null || MgtKey.isEmpty())
			throw new PopbillException(-99999999, "관리번호가 입력되지 않았습니다.");
		
		String PostData = toJsonString(statement);
		
		return httppost("/Statement/" + ItemCode + "/" + MgtKey, CorpNum, 
				PostData, UserID, "PATCH", Response.class);
	}

	/* (non-Javadoc)
	 * @see com.popbill.api.StatementService#delete(java.lang.String, java.number.Integer, java.lang.String)
	 */
	@Override
	public Response delete(String CorpNum, int ItemCode, String MgtKey)
			throws PopbillException {
		if (MgtKey == null || MgtKey.isEmpty())
			throw new PopbillException(-99999999, "관리번호가 입력되지 않았습니다.");
		
		return delete(CorpNum, ItemCode, MgtKey, null);
	}
	
	/* (non-Javadoc)
	 * @see com.popbill.api.StatementService#delete(java.lang.String, java.number.Integer, java.lang.String, java.lang.String)
	 */
	@Override
	public Response delete(String CorpNum, int ItemCode, String MgtKey,
			String UserID) throws PopbillException {
		if (MgtKey == null || MgtKey.isEmpty())
			throw new PopbillException(-99999999, "관리번호가 입력되지 않았습니다.");
				
		return httppost("/Statement/" + ItemCode + "/" + MgtKey, CorpNum, 
				null, UserID, "DELETE", Response.class);
	}
	
	/* (non-Javadoc)
	 * @see com.popbill.api.StatementService#issue(java.lang.String, java.number.Integer, java.lang.String, java.lang.String)
	 */
	@Override
	public Response issue(String CorpNum, int ItemCode, String MgtKey,
			String Memo) throws PopbillException {
		return issue(CorpNum, ItemCode, MgtKey, Memo, null, null); 
	}
	
	/* (non-Javadoc)
	 * @see com.popbill.api.StatementService#issue(java.lang.String, java.number.Integer, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public Response issue(String CorpNum, int ItemCode, String MgtKey,
			String Memo, String UserID) throws PopbillException {
		return issue(CorpNum, ItemCode, MgtKey, Memo, null, UserID);
	}
	
	/* (non-Javadoc)
	 * @see com.popbill.api.StatementService#issue(java.lang.String, java.number.Integer, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public Response issue(String CorpNum, int ItemCode, String MgtKey,
			String Memo, String EmailSubject, String UserID)
			throws PopbillException {
		if (MgtKey == null || MgtKey.isEmpty())
			throw new PopbillException(-99999999, "관리번호가 입력되지 않았습니다.");
		
		String PostData = toJsonString(new MemoRequest(Memo));
				
		return httppost("/Statement/" + ItemCode + "/" + MgtKey,
				CorpNum, PostData, UserID, "ISSUE", Response.class);
	}
	
	/* (non-Javadoc)
	 * @see com.popbill.api.StatementService#cancel(java.lang.String, java.number.Integer, java.lang.String, java.lang.String)
	 */
	@Override
	public Response cancel(String CorpNum, int ItemCode, String MgtKey,
			String Memo) throws PopbillException {
		if (MgtKey == null || MgtKey.isEmpty())
			throw new PopbillException(-99999999, "관리번호가 입력되지 않았습니다.");
				
		return cancel(CorpNum, ItemCode, MgtKey, Memo, null);
	}
	
	/* (non-Javadoc)
	 * @see com.popbill.api.StatementService#cancel(java.lang.String, java.number.Integer, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public Response cancel(String CorpNum, int ItemCode, String MgtKey,
			String Memo, String UserID) throws PopbillException {
		if (MgtKey == null || MgtKey.isEmpty())
			throw new PopbillException(-99999999, "관리번호가 입력되지 않았습니다.");
		
		String PostData = toJsonString(new MemoRequest(Memo));
		
		return httppost("/Statement/" + ItemCode + "/" + MgtKey, 
				CorpNum, PostData, UserID, "CANCEL", Response.class);
	}
	
	/* (non-Javadoc)
	 * @see com.popbill.api.StatementService#sendEmail(java.lang.String, java.number.Integer, java.lang.String, java.lang.String)
	 */
	@Override
	public Response sendEmail(String CorpNum, int ItemCode, String MgtKey,
			String Receiver) throws PopbillException {
		if (MgtKey == null || MgtKey.isEmpty())
			throw new PopbillException(-99999999, "관리번호가 입력되지 않았습니다.");
				
		return sendEmail(CorpNum, ItemCode, MgtKey, Receiver, null);
	}
	
	/* (non-Javadoc)
	 * @see com.popbill.api.StatementService#sendEmail(java.lang.String, java.number.Integer, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public Response sendEmail(String CorpNum, int ItemCode, String MgtKey,
			String Receiver, String UserID) throws PopbillException {
		if (MgtKey == null || MgtKey.isEmpty())
			throw new PopbillException(-99999999, "관리번호가 입력되지 않았습니다.");
				
		ResendRequest request = new ResendRequest();
		
		request.receiver = Receiver;
		
		String PostData = toJsonString(request);
		
		return httppost("/Statement/" + ItemCode + "/" + MgtKey, 
				CorpNum, PostData, UserID, "EMAIL", Response.class);
	}
	
	/* (non-Javadoc)
	 * @see com.popbill.api.StatementService#sendSMS(java.lang.String, java.number.Integer, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public Response sendSMS(String CorpNum, int ItemCode, String MgtKey,
			String Sender, String Receiver, String Contents)
			throws PopbillException {
		
		return sendSMS(CorpNum, ItemCode, MgtKey, Sender, Receiver, Contents, null);
	}
	
	/* (non-Javadoc)
	 * @see com.popbill.api.StatementService#sendSMS(java.lang.String, java.number.Integer, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public Response sendSMS(String CorpNum, int ItemCode, String MgtKey,
			String Sender, String Receiver, String Contents, String UserID)
			throws PopbillException {
		if (MgtKey == null || MgtKey.isEmpty())
			throw new PopbillException(-99999999, "관리번호가 입력되지 않았습니다.");
		
		ResendRequest request = new ResendRequest();
		
		request.sender = Sender;
		request.receiver = Receiver;
		request.contents = Contents;
		
		String PostData = toJsonString(request);
		
		return httppost("/Statement/" + ItemCode + "/" + MgtKey, CorpNum, PostData, 
				UserID, "SMS", Response.class);
	}

	/* (non-Javadoc)
	 * @see com.popbill.api.StatementService#sendFAX(java.lang.String, java.number.Integer, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public Response sendFAX(String CorpNum, int ItemCode, String MgtKey,
			String Sender, String Receiver) throws PopbillException {
		return sendFAX(CorpNum, ItemCode, MgtKey, Sender, Receiver, null);
	}
	
	/* (non-Javadoc)
	 * @see com.popbill.api.StatementService#sendFAX(java.lang.String, java.number.Integer, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public Response sendFAX(String CorpNum, int ItemCode, String MgtKey,
			String Sender, String Receiver, String UserID)
			throws PopbillException {
		if (MgtKey == null || MgtKey.isEmpty())
			throw new PopbillException(-99999999, "관리번호가 입력되지 않았습니다.");
		
		ResendRequest request = new ResendRequest();
		
		request.sender = Sender;
		request.receiver = Receiver;
				
		String PostData = toJsonString(request);
		
		return httppost("/Statement/"+ItemCode+"/"+MgtKey, CorpNum, PostData, 
					UserID, "FAX", Response.class);
	}
	
	/* (non-Javadoc)
	 * @see com.popbill.api.StatementService#getDetailInfo(java.lang.String, java.number.Integer, java.lang.String, java.lang.String)
	 */
	@Override
	public Statement getDetailInfo(String CorpNum, int ItemCode, String MgtKey,
			String UserID) throws PopbillException {
		if (MgtKey == null || MgtKey.isEmpty())
			throw new PopbillException(-99999999, "관리번호가 입력되지 않았습니다.");
		
		return httpget("/Statement/" + ItemCode + "/" + MgtKey + "?Detail",
					CorpNum, null, Statement.class);
		
	}
	
	/* (non-Javadoc)
	 * @see com.popbill.api.StatementService#getInfo(java.lang.String, java.number.Integer, java.lang.String, java.lang.String)
	 */
	@Override
	public StatementInfo getInfo(String CorpNum, int ItemCode, String MgtKey)
			throws PopbillException {
		if (MgtKey == null || MgtKey.isEmpty())
			throw new PopbillException(-99999999, "관리번호가 입력되지 않았습니다.");
		
		return httpget("/Statement/" + ItemCode + "/" + MgtKey, 
					CorpNum, null, StatementInfo.class);
	}
	
	/* (non-Javadoc)
	 * @see com.popbill.api.StatementService#getInfos(java.lang.String, java.number.Integer, java.lang.String, java.lang.String[])
	 */
	@Override
	public StatementInfo[] getInfos(String CorpNum, int ItemCode,
			String[] MgtKeyList) throws PopbillException {
		if (MgtKeyList == null)
			throw new PopbillException(-99999999, "관리번호 배열이 입력되지 않았습니다.");
		
		String PostData = toJsonString(MgtKeyList);
		
		return httppost("/Statement/" + ItemCode, CorpNum, 
					PostData, null, StatementInfo[].class);
	}
	
	/* (non-Javadoc)
	 * @see com.popbill.api.StatementService#getLogs(java.lang.String, java.number.Integer, java.lang.String, java.lang.String)
	 */
	@Override
	public StatementLog[] getLogs(String CorpNum, int ItemCode, String MgtKey)
			throws PopbillException {
		if (MgtKey == null || MgtKey.isEmpty())
			throw new PopbillException(-99999999, "관리번호 가 입력되지 않았습니다.");
		
		return httpget("/Statement/" + ItemCode + "/" + MgtKey + "/Logs",
					CorpNum, null, StatementLog[].class);
	}
	
	/* (non-Javadoc)
	 * @see com.popbill.api.StatementService#getPopUpURL(java.lang.String, java.number.Integer, java.lang.String, java.lang.String)
	 */
	@Override
	public String getPopUpURL(String CorpNum, int ItemCode, String MgtKey,
			String UserID) throws PopbillException {
		if (MgtKey == null || MgtKey.isEmpty())
			throw new PopbillException(-99999999, "관리번호 가 입력되지 않았습니다.");
		
		URLResponse response = httpget("/Statement/" + ItemCode + "/" + MgtKey + "?TG=POPUP",
				CorpNum, UserID, URLResponse.class);
		
		return response.url;
	}
	
	/* (non-Javadoc)
	 * @see com.popbill.api.StatementService#getPrintURL(java.lang.String, java.number.Integer, java.lang.String, java.lang.String)
	 */
	@Override
	public String getPrintURL(String CorpNum, int ItemCode, String MgtKey,
			String UserID) throws PopbillException {
		if (MgtKey == null || MgtKey.isEmpty())
			throw new PopbillException(-99999999, "관리번호 가 입력되지 않았습니다.");
		
		URLResponse response = httpget("/Statement/" + ItemCode + "/" + MgtKey + "?TG=PRINT",
				CorpNum, UserID, URLResponse.class);
		
		return response.url;
	}
	
	/* (non-Javadoc)
	 * @see com.popbill.api.StatementService#getEPrintURL(java.lang.String, java.number.Integer, java.lang.String, java.lang.String)
	 */
	@Override
	public String getEPrintURL(String CorpNum, int ItemCode, String MgtKey,
			String UserID) throws PopbillException {
		if (MgtKey == null || MgtKey.isEmpty())
			throw new PopbillException(-99999999, "관리번호 가 입력되지 않았습니다.");
		
		URLResponse response = httpget("/Statement/" + ItemCode +"/" + MgtKey + "?TG=EPRINT",
				CorpNum, UserID, URLResponse.class);
		
		return response.url;
	}
	
	/* (non-Javadoc)
	 * @see com.popbill.api.StatementService#getMailURL(java.lang.String, java.number.Integer, java.lang.String, java.lang.String)
	 */
	@Override
	public String getMailURL(String CorpNum, int ItemCode, String MgtKey,
			String UserID) throws PopbillException {
		if (MgtKey == null || MgtKey.isEmpty())
			throw new PopbillException(-99999999, "관리번호 가 입력되지 않았습니다.");
		
		URLResponse response = httpget("/Statement/" + ItemCode + "/" + MgtKey + "?TG=MAIL",
				CorpNum, UserID, URLResponse.class);
		
		return response.url;
	}
	
	/* (non-Javadoc)
	 * @see com.popbill.api.StatementService#getMassPrintURL(java.lang.String, java.number.Integer, java.lang.String, java.lang.String)
	 */
	@Override
	public String getMassPrintURL(String CorpNum, int ItemCode,
			String[] MgtKeyList, String UserID) throws PopbillException {
		if (MgtKeyList == null)
			throw new PopbillException(-99999999, "관리번호배열이 입력되지 않았습니다.");
		
		String PostData = toJsonString(MgtKeyList);
		
		URLResponse response = httppost("/Statement/" + ItemCode + "?Print",
				CorpNum, PostData, UserID, URLResponse.class);
		
		return response.url;
	}
	/* (non-Javadoc)
	 * @see com.popbill.api.StatementService#attachFile(java.lang.String, java.number.Integer, java.lang.String, ,java.io.InputStream, java.lang.String)
	 */
	@Override
	public Response attachFile(String CorpNum, int ItemCode, String MgtKey,
			String DisplayName, InputStream FileData, String UserID)
			throws PopbillException {
		if (MgtKey == null || MgtKey.isEmpty())
			throw new PopbillException(-99999999, "관리번호가 입력되지 않았습니다.");
		if (DisplayName == null || DisplayName.isEmpty())
			throw new PopbillException(-99999999, "파일 표시명이 입력되지 않았습니다.");
		if (FileData == null)
			throw new PopbillException(-99999999, "파일 스트림이 입력되지 않았습니다.");
		
		List<UploadFile> files = new ArrayList<BaseServiceImp.UploadFile>();
		UploadFile file = new UploadFile();
		file.fileName = DisplayName;
		file.fieldName = "Filedata";
		file.fileData = FileData;

		files.add(file);

		return httppostFiles("/Statement/" + ItemCode + "/" + MgtKey + "/Files",
				CorpNum, null, files, UserID, Response.class);
	}
	
	/* (non-Javadoc)
	 * @see com.popbill.api.StatementService#getFiles(java.lang.String, java.number.Integer, java.lang.String)
	 */
	@Override
	public AttachedFile[] getFiles(String CorpNum, int ItemCode, String MgtKey)
			throws PopbillException {
		if (MgtKey == null || MgtKey.isEmpty())
			throw new PopbillException(-99999999, "관리번호가 입력되지 않았습니다.");
		
		return httpget("/Statement/" + ItemCode+ "/" + MgtKey + "/Files",
				CorpNum, null, AttachedFile[].class);
	}
	
	/* (non-Javadoc)
	 * @see com.popbill.api.StatementService#deleteFile(java.lang.String, java.number.Integer, java.lang.String, java.lang.String)
	 */
	@Override
	public Response deleteFile(String CorpNum, int ItemCode, String MgtKey,
			String FileID) throws PopbillException {
		
		return deleteFile(CorpNum, ItemCode, MgtKey, FileID, null);
	}
	
	/* (non-Javadoc)
	 * @see com.popbill.api.StatementService#deleteFile(java.lang.String, java.number.Integer, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public Response deleteFile(String CorpNum, int ItemCode, String MgtKey,
			String FileID, String UserID) throws PopbillException {
		if (MgtKey == null || MgtKey.isEmpty())
			throw new PopbillException(-99999999, "관리번호가 입력되지 않았습니다.");
		if (FileID == null || FileID.isEmpty())
			throw new PopbillException(-99999999, "파일아이디가 입력되지 않았습니다.");
		
		return httppost("/Statement/" + ItemCode + "/" + MgtKey + "/Files/" + FileID,
				CorpNum, null, UserID, "DELETE", Response.class);
		
	}
		
	protected class MemoRequest {
		public MemoRequest(String memo) {
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
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
	public float getUnitCost(String corpNum) throws PopbillException {
		UnitCostResponse response = httpget("/FAX/UnitCost",
				corpNum, null, UnitCostResponse.class);

		return response.unitCost;
	}

	/*
	 * (non-Javadoc)
	 * @see com.popbill.api.FaxService#getURL(java.lang.String, java.lang.String)
	 */
	@Override
	public String getURL(String corpNum, String TOGO) throws PopbillException {
		return getURL(corpNum, null, TOGO);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.popbill.api.FaxService#getURL
	 */
	@Override
	public String getURL(String corpNum, String userID, String TOGO)
			throws PopbillException {

		URLResponse response = httpget("/FAX/?TG=" + TOGO, corpNum,
				userID, URLResponse.class);

		return response.url;
	}

	/*
	 * (non-Javadoc)
	 * @see com.popbill.api.FaxService#sendFAX(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.io.File, java.util.Date)
	 */
	@Override
	public String sendFAX(String corpNum, String sendNum, String receiveNum,
			String receiveName, File file, Date reserveDT)
			throws PopbillException {

		Receiver receiver = new Receiver();
		receiver.setReceiveNum(receiveNum);
		receiver.setReceiveName(receiveName);
		
		return requestFax(corpNum, sendNum, null, new Receiver[]{receiver}, 
				new File[]{file}, reserveDT, null, null, null, null);
	}

	/*
	 * (non-Javadoc)
	 * @see com.popbill.api.FaxService#sendFAX(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.io.File, java.util.Date, java.lang.Boolean)
	 */
	@Override
	public String sendFAX(String corpNum, String sendNum, String receiveNum,
			String receiveName, File file, Date reserveDT, Boolean adsYN)
			throws PopbillException {
		
		Receiver receiver = null;
				
		receiver = new Receiver();
		receiver.setReceiveNum(receiveNum);
		receiver.setReceiveName(receiveName);
		
		return requestFax(corpNum, sendNum, null, new Receiver[]{receiver}, 
				new File[]{file}, reserveDT, null, adsYN, null, null);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.popbill.api.FaxService#sendFAX(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.io.File, java.util.Date, java.lang.Boolean, java.lang.String)
	 */
	@Override
	public String sendFAX(String corpNum, String sendNum, String receiveNum,
			String receiveName, File file, Date reserveDT, Boolean adsYN, String requestNum)
			throws PopbillException {
		
		Receiver receiver = null;
		
		receiver = new Receiver();
		receiver.setReceiveNum(receiveNum);
		receiver.setReceiveName(receiveName);
		
		return requestFax(corpNum, sendNum, null, new Receiver[]{receiver}, 
				new File[]{file}, reserveDT, null, adsYN, null, requestNum);
	}	

	/*
	 * (non-Javadoc)
	 * @see com.popbill.api.FaxService#sendFAX(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.io.File, java.util.Date, java.lang.String)
	 */
	@Override
	public String sendFAX(String corpNum, String sendNum, String receiveNum,
			String receiveName, File file, Date reserveDT, String userID)
			throws PopbillException {
		
		Receiver receiver = new Receiver();
		receiver.setReceiveNum(receiveNum);
		receiver.setReceiveName(receiveName);
		
		return requestFax(corpNum, sendNum, null, new Receiver[]{receiver}, new File[]{file},
				reserveDT, userID, null, null, null);
	}

	/*
	 * (non-Javadoc)
	 * @see com.popbill.api.FaxService#sendFAX(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.io.File, java.util.Date, java.lang.String, java.lang.Boolean)
	 */
	@Override
	public String sendFAX(String corpNum, String sendNum, String receiveNum,
			String receiveName, File file, Date reserveDT, String userID,
			Boolean adsYN) throws PopbillException {
		
		Receiver receiver = new Receiver();
		receiver.setReceiveNum(receiveNum);
		receiver.setReceiveName(receiveName);
		
		return requestFax(corpNum, sendNum, null, new Receiver[]{receiver}, 
				new File[]{file}, reserveDT, userID, adsYN, null, null);
	}

	/*
	 * (non-Javadoc)
	 * @see com.popbill.api.FaxService#sendFAX(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.io.File, java.util.Date, java.lang.String, java.lang.Boolean, java.lang.String)
	 */
	@Override
	public String sendFAX(String corpNum, String sendNum, String receiveNum,
			String receiveName, File file, Date reserveDT, String userID,
			Boolean adsYN, String title) throws PopbillException {
		
		Receiver receiver = new Receiver();
		receiver.setReceiveNum(receiveNum);
		receiver.setReceiveName(receiveName);
		
		return requestFax(corpNum, sendNum, null, new Receiver[]{receiver}, 
				new File[]{file}, reserveDT, userID, adsYN, title, null);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.popbill.api.FaxService#sendFAX(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.io.File, java.util.Date, java.lang.String, java.lang.Boolean, java.lang.String, java.lang.String)
	 */
	@Override
	public String sendFAX(String corpNum, String sendNum, String receiveNum,
			String receiveName, File file, Date reserveDT, String userID,
			Boolean adsYN, String title, String requestNum) throws PopbillException {
		
		Receiver receiver = new Receiver();
		receiver.setReceiveNum(receiveNum);
		receiver.setReceiveName(receiveName);
		
		return requestFax(corpNum, sendNum, null, new Receiver[]{receiver}, 
				new File[]{file}, reserveDT, userID, adsYN, title, requestNum);
	}	

	/*
	 * (non-Javadoc)
	 * @see com.popbill.api.FaxService#sendFAX(java.lang.String, java.lang.String, com.popbill.api.fax.Receiver[], java.io.File, java.util.Date)
	 */
	@Override
	public String sendFAX(String corpNum, String sendNum, Receiver[] receivers,
			File file, Date reserveDT) throws PopbillException {

		return requestFax(corpNum, sendNum, null, receivers, new File[]{file}, 
				reserveDT, null, null, null, null);
	}

	/*
	 * (non-Javadoc)
	 * @see com.popbill.api.FaxService#sendFAX(java.lang.String, java.lang.String, com.popbill.api.fax.Receiver[], java.io.File, java.util.Date, java.lang.Boolean)
	 */
	@Override
	public String sendFAX(String corpNum, String sendNum, Receiver[] receivers,
			File file, Date reserveDT, Boolean adsYN) throws PopbillException {

		return requestFax(corpNum, sendNum, null, receivers, new File[]{file},
				reserveDT, null, adsYN, null, null);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.popbill.api.FaxService#sendFAX(java.lang.String, java.lang.String, com.popbill.api.fax.Receiver[], java.io.File, java.util.Date, java.lang.Boolean, java.lang.String)
	 */
	@Override
	public String sendFAX(String corpNum, String sendNum, Receiver[] receivers,
			File file, Date reserveDT, Boolean adsYN, String title)
			throws PopbillException {

		return requestFax(corpNum, sendNum, null, receivers, 
				new File[]{file}, reserveDT, null, adsYN, title, null);
	}

	/*
	 * (non-Javadoc)
	 * @see com.popbill.api.FaxService#sendFAX(java.lang.String, java.lang.String, com.popbill.api.fax.Receiver[], java.io.File, java.util.Date, java.lang.Boolean, java.lang.String, java.lang.String)
	 */
	@Override
	public String sendFAX(String corpNum, String sendNum, Receiver[] receivers,
			File file, Date reserveDT, Boolean adsYN, String title, String requestNum)
			throws PopbillException {

		return requestFax(corpNum, sendNum, null, receivers, 
				new File[]{file}, reserveDT, null, adsYN, title, requestNum);
	}	

	/*
	 * (non-Javadoc)
	 * @see com.popbill.api.FaxService#sendFAX(java.lang.String, java.lang.String, com.popbill.api.fax.Receiver[], java.io.File, java.util.Date, java.lang.String)
	 */
	@Override
	public String sendFAX(String corpNum, String sendNum, Receiver[] receivers,
			File file, Date reserveDT, String userID) throws PopbillException {
		
		return requestFax(corpNum, sendNum, null, receivers, new File[]{file}, 
				reserveDT, userID, null, null, null);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.popbill.api.FaxService#sendFAX(java.lang.String, java.lang.String, com.popbill.api.fax.Receiver[], java.io.File, java.util.Date, java.lang.String, java.lang.String)
	 */
	@Override
	public String sendFAX(String corpNum, String sendNum, Receiver[] receivers,
			File file, Date reserveDT, String userID, String title)
			throws PopbillException {

		return requestFax(corpNum, sendNum, null, receivers, 
				new File[]{file}, reserveDT, userID, false, title, null);	
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.popbill.api.FaxService#sendFAX(java.lang.String, java.lang.String, com.popbill.api.fax.Receiver[], java.io.File, java.util.Date, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public String sendFAX(String corpNum, String sendNum, Receiver[] receivers,
			File file, Date reserveDT, String userID, String title, String requestNum)
			throws PopbillException {

		return requestFax(corpNum, sendNum, null, receivers, 
				new File[]{file}, reserveDT, userID, false, title, requestNum);
	}	

	/*
	 * (non-Javadoc)
	 * @see com.popbill.api.FaxService#sendFAX(java.lang.String, java.lang.String, com.popbill.api.fax.Receiver[], java.io.File, java.util.Date, java.lang.String, java.lang.Boolean)
	 */
	@Override
	public String sendFAX(String corpNum, String sendNum, Receiver[] receivers,
			File file, Date reserveDT, String userID, Boolean adsYN)
			throws PopbillException {
		
		return requestFax(corpNum, sendNum, null, receivers, new File[]{file}, 
				reserveDT, userID, adsYN, null, null);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.popbill.api.FaxService#sendFAX(java.lang.String, java.lang.String, com.popbill.api.fax.Receiver[], java.io.File, java.util.Date, java.lang.String, java.lang.Boolean, java.lang.String)
	 */
	@Override
	public String sendFAX(String corpNum, String sendNum, Receiver[] receivers,
			File file, Date reserveDT, String userID, Boolean adsYN,
			String title) throws PopbillException {
		
		return requestFax(corpNum, sendNum, null, receivers, 
				new File[]{file}, reserveDT, userID, adsYN, title, null);
	}

	/*
	 * (non-Javadoc)
	 * @see com.popbill.api.FaxService#sendFAX(java.lang.String, java.lang.String, com.popbill.api.fax.Receiver[], java.io.File, java.util.Date, java.lang.String, java.lang.Boolean, java.lang.String, java.lang.String)
	 */
	@Override
	public String sendFAX(String corpNum, String sendNum, Receiver[] receivers,
			File file, Date reserveDT, String userID, Boolean adsYN,
			String title, String requestNum) throws PopbillException {		
		
		return requestFax(corpNum, sendNum, null, receivers, 
				new File[]{file}, reserveDT, userID, adsYN, title, requestNum);
	}		

	/*
	 * (non-Javadoc)
	 * @see com.popbill.api.FaxService#sendFAX(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.io.File[], java.util.Date)
	 */
	@Override
	public String sendFAX(String corpNum, String sendNum, String receiveNum,
			String receiveName, File[] files, Date reserveDT)
			throws PopbillException {

		Receiver receiver = new Receiver();
		receiver.setReceiveNum(receiveNum);
		receiver.setReceiveName(receiveName);
				
		return requestFax(corpNum, sendNum, null, new Receiver[]{receiver}, files,
				reserveDT, null, null, null, null);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.popbill.api.FaxService#sendFAX(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.io.File[], java.util.Date, java.lang.Boolean)
	 */
	@Override
	public String sendFAX(String corpNum, String sendNum, String receiveNum,
			String receiveName, File[] files, Date reserveDT, Boolean adsYN)
			throws PopbillException {

		Receiver receiver = new Receiver();
		receiver.setReceiveNum(receiveNum);
		receiver.setReceiveName(receiveName);
				
		return requestFax(corpNum, sendNum, null, new Receiver[]{receiver}, files,
				reserveDT, null, adsYN, null, null);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.popbill.api.FaxService#sendFAX(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.io.File[], java.util.Date, java.lang.Boolean, java.lang.String)
	 */
	@Override
	public String sendFAX(String corpNum, String sendNum, String receiveNum,
			String receiveName, File[] files, Date reserveDT, Boolean adsYN,
			String title) throws PopbillException {
		
		Receiver receiver = new Receiver();
		receiver.setReceiveNum(receiveNum);
		receiver.setReceiveName(receiveName);
		
		return requestFax(corpNum, sendNum, null, new Receiver[]{receiver}, 
				files, reserveDT, null, adsYN, title, null);
	}		

	/*
	 * (non-Javadoc)
	 * @see com.popbill.api.FaxService#sendFAX(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.io.File[], java.util.Date, java.lang.Boolean, java.lang.String, java.lang.String)
	 */
	@Override
	public String sendFAX(String corpNum, String sendNum, String receiveNum,
			String receiveName, File[] files, Date reserveDT, Boolean adsYN,
			String title, String requestNum) throws PopbillException {
		
		Receiver receiver = new Receiver();
		receiver.setReceiveNum(receiveNum);
		receiver.setReceiveName(receiveName);
		
		return requestFax(corpNum, sendNum, null, new Receiver[]{receiver}, 
				files, reserveDT, null, adsYN, title, requestNum);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.popbill.api.FaxService#sendFAX(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.io.File[], java.util.Date, java.lang.String)
	 */
	@Override
	public String sendFAX(String corpNum, String sendNum, String receiveNum,
			String receiveName, File[] files, Date reserveDT, String userID)
			throws PopbillException {
		
		Receiver receiver = new Receiver();
		receiver.setReceiveNum(receiveNum);
		receiver.setReceiveName(receiveName);
		
		return requestFax(corpNum, sendNum, null, new Receiver[]{receiver}, files,
				reserveDT, userID, null, null, null);	 
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.popbill.api.FaxService#sendFAX(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.io.File[], java.util.Date, java.lang.String, java.lang.String)
	 */
	@Override
	public String sendFAX(String corpNum, String sendNum, String receiveNum,
			String receiveName, File[] files, Date reserveDT, String userID,
			String title) throws PopbillException {
		
		Receiver receiver = new Receiver();
		receiver.setReceiveNum(receiveNum);
		receiver.setReceiveName(receiveName);
		
		return requestFax(corpNum, sendNum, null, new Receiver[]{receiver}, 
				files, reserveDT, userID, false, title, null);
	}

	/*
	 * (non-Javadoc)
	 * @see com.popbill.api.FaxService#sendFAX(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.io.File[], java.util.Date, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public String sendFAX(String corpNum, String sendNum, String receiveNum,
			String receiveName, File[] files, Date reserveDT, String userID,
			String title, String requestNum) throws PopbillException {
		
		Receiver receiver = new Receiver();
		receiver.setReceiveNum(receiveNum);
		receiver.setReceiveName(receiveName);
		
		return requestFax(corpNum, sendNum, null, new Receiver[]{receiver}, 
				files, reserveDT, userID, false, title, requestNum);
	}	

	/*
	 * (non-Javadoc)
	 * @see com.popbill.api.FaxService#sendFAX(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.io.File[], java.util.Date, java.lang.String, java.lang.Boolean)
	 */
	@Override
	public String sendFAX(String corpNum, String sendNum, String receiveNum,
			String receiveName, File[] files, Date reserveDT, String userID,
			Boolean adsYN) throws PopbillException {
		
		Receiver receiver = new Receiver();
		receiver.setReceiveNum(receiveNum);
		receiver.setReceiveName(receiveName);
				
		return requestFax(corpNum, sendNum, null, new Receiver[]{receiver}, files, 
				reserveDT, userID, adsYN, null, null);
	}	
	
	/*
	 * (non-Javadoc)
	 * @see com.popbill.api.FaxService#sendFAX(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.io.File[], java.util.Date, java.lang.String, java.lang.Boolean, java.lang.String)
	 */
	@Override
	public String sendFAX(String corpNum, String sendNum, String receiveNum,
			String receiveName, File[] files, Date reserveDT, String userID,
			Boolean adsYN, String title) throws PopbillException {

		Receiver receiver = new Receiver();
		receiver.setReceiveNum(receiveNum);
		receiver.setReceiveName(receiveName);
		
		return requestFax(corpNum, sendNum, null, new Receiver[]{receiver}, 
				files, reserveDT, userID, adsYN, title, null);
	}	
	
	/*
	 * (non-Javadoc)
	 * @see com.popbill.api.FaxService#sendFAX(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.io.File[], java.util.Date, java.lang.String, java.lang.Boolean, java.lang.String, java.lang.String)
	 */
	@Override
	public String sendFAX(String corpNum, String sendNum, String receiveNum,
			String receiveName, File[] files, Date reserveDT, String userID,
			Boolean adsYN, String title, String requestNum) throws PopbillException {

		Receiver receiver = new Receiver();
		receiver.setReceiveNum(receiveNum);
		receiver.setReceiveName(receiveName);
		
		return requestFax(corpNum, sendNum, null, new Receiver[]{receiver}, 
				files, reserveDT, userID, adsYN, title, requestNum);
	}	

	/*
	 * (non-Javadoc)
	 * @see com.popbill.api.FaxService#sendFAX(java.lang.String, java.lang.String, com.popbill.api.fax.Receiver[], java.io.File[], java.util.Date, java.lang.String)
	 */
	@Override
	public String sendFAX(String corpNum, String sendNum, Receiver[] receivers,
			File[] files, Date reserveDT, String userID)
			throws PopbillException {
		
		return requestFax(corpNum, sendNum, null, receivers, files, reserveDT,
				userID, null, null, null);
	}

	/*
	 * (non-Javadoc)
	 * @see com.popbill.api.FaxService#sendFAX(java.lang.String, java.lang.String, com.popbill.api.fax.Receiver[], java.io.File[], java.util.Date, java.lang.String, java.lang.String)
	 */
	@Override
	public String sendFAX(String corpNum, String sendNum, Receiver[] receivers,
			File[] files, Date reserveDT, String userID, String title)
			throws PopbillException {
		
		return requestFax(corpNum, sendNum, null, receivers, 
				files, reserveDT, userID, false, title, null);
	}	
	
	/*
	 * (non-Javadoc)
	 * @see com.popbill.api.FaxService#sendFAX(java.lang.String, java.lang.String, com.popbill.api.fax.Receiver[], java.io.File[], java.util.Date, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public String sendFAX(String corpNum, String sendNum, Receiver[] receivers,
			File[] files, Date reserveDT, String userID, String title, String reqestNum)
			throws PopbillException {
		
		return requestFax(corpNum, sendNum, null, receivers, 
				files, reserveDT, userID, false, title, reqestNum);
	}		

	/*
	 * (non-Javadoc)
	 * @see com.popbill.api.FaxService#sendFAX(java.lang.String, java.lang.String, com.popbill.api.fax.Receiver[], java.io.File[], java.util.Date, java.lang.String, java.lang.Boolean)
	 */
	@Override
	public String sendFAX(String corpNum, String sendNum, Receiver[] receivers,
			File[] files, Date reserveDT, String userID, Boolean adsYN)
			throws PopbillException {
	
		return requestFax(corpNum, sendNum, null, receivers, files, reserveDT,
				userID, adsYN, null, null);
	}	
	
	/*
	 * (non-Javadoc)
	 * @see com.popbill.api.FaxService#sendFAX(java.lang.String, java.lang.String, com.popbill.api.fax.Receiver[], java.io.File[], java.util.Date, java.lang.String, java.lang.Boolean, java.lang.String)
	 */
	@Override
	public String sendFAX(String corpNum, String sendNum, Receiver[] receivers,
			File[] files, Date reserveDT, String userID, Boolean adsYN,
			String title) throws PopbillException {
		
		return requestFax(corpNum, sendNum, null, receivers, 
				files, reserveDT, userID, adsYN, title, null);
	}	
	
	/*
	 * (non-Javadoc)
	 * @see com.popbill.api.FaxService#sendFAX(java.lang.String, java.lang.String, com.popbill.api.fax.Receiver[], java.io.File[], java.util.Date, java.lang.String, java.lang.Boolean, java.lang.String, java.lang.String)
	 */
	@Override
	public String sendFAX(String corpNum, String sendNum, Receiver[] receivers,
			File[] files, Date reserveDT, String userID, Boolean adsYN,
			String title, String requestNum) throws PopbillException {
		
		return requestFax(corpNum, sendNum, null, receivers, 
				files, reserveDT, userID, adsYN, title, requestNum);
	}		

	/*
	 * (non-Javadoc)
	 * @see com.popbill.api.FaxService#sendFAX(java.lang.String, java.lang.String, java.lang.String, com.popbill.api.fax.Receiver[], java.io.File[], java.util.Date)
	 */
	@Override
	public String sendFAX(String corpNum, String sendNum, String senderName,
			Receiver[] receivers, File[] files, Date reserveDT)
			throws PopbillException {
	
		return requestFax(corpNum, sendNum, senderName, receivers, files, reserveDT,
				null, null, null, null);
	}

	/*
	 * (non-Javadoc)
	 * @see com.popbill.api.FaxService#sendFAX(java.lang.String, java.lang.String, java.lang.String, com.popbill.api.fax.Receiver[], java.io.File[], java.util.Date, java.lang.Boolean)
	 */
	@Override
	public String sendFAX(String corpNum, String sendNum, String senderName,
			Receiver[] receivers, File[] files, Date reserveDT, Boolean adsYN)
			throws PopbillException {
		return requestFax(corpNum, sendNum, senderName, receivers, files, reserveDT,
				null, adsYN, null, null);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.popbill.api.FaxService#sendFAX(java.lang.String, java.lang.String, java.lang.String, com.popbill.api.fax.Receiver[], java.io.File[], java.util.Date, java.lang.Boolean, java.lang.String)
	 */
	@Override
	public String sendFAX(String corpNum, String sendNum, String senderName,
			Receiver[] receivers, File[] files, Date reserveDT, Boolean adsYN, String requestNum)
			throws PopbillException {
		return requestFax(corpNum, sendNum, senderName, receivers, files, reserveDT,
				null, adsYN, null, requestNum);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.popbill.api.FaxService#sendFAX(java.lang.String, java.lang.String, java.lang.String, com.popbill.api.fax.Receiver[], java.io.File[], java.util.Date, java.lang.String)
	 */
	@Override
	public String sendFAX(String corpNum, String sendNum, String senderName,
			Receiver[] receivers, File[] files, Date reserveDT, String userID)
			throws PopbillException {
		
		return requestFax(corpNum, sendNum, senderName, receivers, files, reserveDT,
				userID, false, null, null);
	}	
	
	/*
	 * (non-Javadoc)
	 * @see com.popbill.api.FaxService#sendFAX(java.lang.String, java.lang.String, java.lang.String, com.popbill.api.fax.Receiver[], java.io.File[], java.util.Date, java.lang.String, java.lang.String)
	 */
	@Override
	public String sendFAX(String corpNum, String sendNum, String senderName,
			Receiver[] receivers, File[] files, Date reserveDT, String userID,
			String title) throws PopbillException {
		
		return requestFax(corpNum, sendNum, senderName, receivers, 
				files, reserveDT, userID, false, title, null);
	}

	/*
	 * (non-Javadoc)
	 * @see com.popbill.api.FaxService#sendFAX(java.lang.String, java.lang.String, java.lang.String, com.popbill.api.fax.Receiver[], java.io.File[], java.util.Date, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public String sendFAX(String corpNum, String sendNum, String senderName,
			Receiver[] receivers, File[] files, Date reserveDT, String userID,
			String title, String requestNum) throws PopbillException {
		
		return requestFax(corpNum, sendNum, senderName, receivers, 
				files, reserveDT, userID, false, title, requestNum);
	}	

	/*
	 * (non-Javadoc)
	 * @see com.popbill.api.FaxService#sendFAX(java.lang.String, java.lang.String, java.lang.String, com.popbill.api.fax.Receiver[], java.io.File[], java.util.Date, java.lang.String, java.lang.Boolean)
	 */
	@Override
	public String sendFAX(String corpNum, String sendNum, String senderName,
			Receiver[] receivers, File[] files, Date reserveDT, String userID, Boolean adsYN)
			throws PopbillException {

		return requestFax(corpNum, sendNum, senderName, receivers, files, reserveDT,
				userID, adsYN, null, null);
	}	
	
	/*
	 * (non-Javadoc)
	 * @see com.popbill.api.FaxService#sendFAX(java.lang.String, java.lang.String, java.lang.String, com.popbill.api.fax.Receiver[], java.io.File[], java.util.Date, java.lang.String, java.lang.Boolean, java.lang.String)
	 */
	@Override
	public String sendFAX(String corpNum, String sendNum, String senderName,
			Receiver[] receivers, File[] files, Date reserveDT, String userID,
			Boolean adsYN, String title) throws PopbillException {

		return requestFax(corpNum, sendNum, senderName, receivers, 
				files, reserveDT, userID, adsYN, title, null);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.popbill.api.FaxService#sendFAX(java.lang.String, java.lang.String, java.lang.String, com.popbill.api.fax.Receiver[], java.io.File[], java.util.Date, java.lang.String, java.lang.Boolean, java.lang.String, java.lang.String)
	 */
	@Override
	public String sendFAX(String corpNum, String sendNum, String senderName,
			Receiver[] receivers, File[] files, Date reserveDT, String userID,
			Boolean adsYN, String title, String requestNum) throws PopbillException {

		return requestFax(corpNum, sendNum, senderName, receivers, 
				files, reserveDT, userID, adsYN, title, requestNum);
	}

	private String requestFax(String corpNum, String sendNum, String senderName,
			Receiver[] receivers, File[] files, Date reserveDT, String userID, Boolean adsYN, String title, String requestNum)
				throws PopbillException {
		if(sendNum == null || sendNum.isEmpty()) throw new PopbillException(-99999999,"발신번호가 입력되지 않았습니다.");
		if(receivers == null || receivers.length == 0) throw new PopbillException(-99999999,"수신처 정보가 입력되지 않았습니다.");
		if(files == null || files.length == 0) throw new PopbillException(-99999999,"발신파일 정보가 입력되지 않았습니다.");
		if(files.length > 20) throw new PopbillException(-99999999,"동보발신 최대 파일갯수는 20개 입니다.");
		
		SendRequest request = new SendRequest();
		
		request.snd = sendNum;
		request.rcvs = receivers;
		
		if (senderName != null)
			request.sndnm = senderName;
		
		if (reserveDT != null)
			request.sndDT = new SimpleDateFormat("yyyyMMddHHmmss", Locale.KOREA).format(reserveDT);
		
		if (adsYN != null && adsYN)
			request.adsYN = true;
		
		if (title != null)
			request.title = title;
		
		if (requestNum != null)
			request.requestNum = requestNum;

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
		
		ReceiptResponse response = httppostFiles("/FAX", corpNum, PostData, uploadFiles, userID, ReceiptResponse.class);
		
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
	public String resendFAX(String corpNum, String receiptNum, String sendNum, String senderName,
			String receiveNum, String receiveName, Date reserveDT, String userID)
			throws PopbillException {
				
		Receiver receiver = null;
		
		if ( !receiveNum.isEmpty() || !receiveName.isEmpty() ) {
			receiver = new Receiver();
			receiver.setReceiveNum(receiveNum);
			receiver.setReceiveName(receiveName);
			return resendFAX(corpNum,receiptNum, sendNum, senderName, new Receiver[]{receiver},reserveDT,userID,null);
		}
		return resendFAX(corpNum,receiptNum, sendNum, senderName, null, reserveDT, userID, null);	
	}

	/*
	 * (non-Javadoc)
	 * @see com.popbill.api.FaxService#resendFAX(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.util.Date, java.lang.String, java.lang.String)
	 */
	@Override
	public String resendFAX(String corpNum, String receiptNum, String sendNum,
			String senderName, String receiveNum, String receiveName,
			Date reserveDT, String userID, String title)
			throws PopbillException {
		
		Receiver receiver = null;
		
		if ( !receiveNum.isEmpty() || !receiveName.isEmpty() ) {
			receiver = new Receiver();
			receiver.setReceiveNum(receiveNum);
			receiver.setReceiveName(receiveName);
			
			return resendFAX(corpNum,receiptNum, sendNum, senderName, new Receiver[]{receiver},reserveDT,userID,title);
		}
		return resendFAX(corpNum,receiptNum, sendNum, senderName, null, reserveDT, userID, title);			
	}	

	/*
	 * (non-Javadoc)
	 * @see com.popbill.api.FaxService#resendFAX(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.util.Date, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public String resendFAX(String corpNum, String receiptNum, String sendNum,
			String senderName, String receiveNum, String receiveName,
			Date reserveDT, String userID, String title, String requestNum)
			throws PopbillException {
		
		Receiver receiver = null;
		
		if ( !receiveNum.isEmpty() || !receiveName.isEmpty() ) {
			receiver = new Receiver();
			receiver.setReceiveNum(receiveNum);
			receiver.setReceiveName(receiveName);
			
			return resendFAX(corpNum,receiptNum, sendNum, senderName, new Receiver[]{receiver}, reserveDT, userID, title, requestNum);
		}
		return resendFAX(corpNum,receiptNum, sendNum, senderName, null, reserveDT, userID, title, requestNum);			
	}		

	/*
	 * (non-Javadoc)
	 * @see com.popbill.api.FaxService#resendFAX(java.lang.String, java.lang.String, java.lang.String, java.lang.String, com.popbill.api.fax.Receiver[], java.util.Date)
	 */
	@Override
	public String resendFAX(String corpNum, String receiptNum, String sendNum,
			String senderName, Receiver[] receivers, Date reserveDT) throws PopbillException {
		
		return resendFAX(corpNum, receiptNum, sendNum, senderName, receivers, reserveDT, null, null);
	}

	/*
	 * (non-Javadoc)
	 * @see com.popbill.api.FaxService#resendFAX(java.lang.String, java.lang.String, java.lang.String, java.lang.String, com.popbill.api.fax.Receiver[], java.util.Date, java.lang.String)
	 */
	@Override
	public String resendFAX(String corpNum, String receiptNum, String sendNum,
			String senderName, Receiver[] receivers, Date reserveDT,
			String userID) throws PopbillException {
		
		return resendFAX(corpNum, receiptNum, sendNum, senderName, receivers, reserveDT, userID, null);
	}

	/*
	 * (non-Javadoc)
	 * @see com.popbill.api.FaxService#resendFAX(java.lang.String, java.lang.String, java.lang.String, java.lang.String, com.popbill.api.fax.Receiver[], java.util.Date, java.lang.String, java.lang.String)
	 */
	@Override
	public String resendFAX(String corpNum, String receiptNum, String sendNum,
			String senderName, Receiver[] receivers, Date reserveDT,
			String userID, String title) throws PopbillException {
		return resendFAX(corpNum, receiptNum, sendNum, senderName, receivers, reserveDT, userID, title, null);
	}

	/*
	 * verride(non-Javadoc)
	 * @see com.popbill.api.FaxService#resendFAX(java.lang.String, java.lang.String, java.lang.String, java.lang.String, com.popbill.api.fax.Receiver[], java.util.Date, java.lang.String, java.lang.String, java.lang.String)
	 */
	public String resendFAX(String corpNum, String receiptNum, String sendNum,
			String senderName, Receiver[] receivers, Date reserveDT,
			String userID, String title, String requestNum) throws PopbillException {
		
		if(receiptNum == null)
			throw new PopbillException(-99999999,"팩스 접수번호(receiptNum)가 입력되지 않았습니다.");
		
		SendRequest request = new SendRequest();
		
		if (sendNum != null)
			request.snd = sendNum;
		
		if ( senderName != null)
			request.sndnm = senderName;
		
		if ( title != null)
			request.title = title;

		if (receivers != null) 
			request.rcvs = receivers;
		
		if (reserveDT != null) 
			request.sndDT = new SimpleDateFormat("yyyyMMddHHmmss", Locale.KOREA).format(reserveDT);
		
		if (requestNum != null) 
			request.requestNum = requestNum;
		
		String PostData = toJsonString(request);
		
		ReceiptResponse response = httppost("/FAX/"+receiptNum, corpNum, PostData, userID, ReceiptResponse.class);
		
		return response.receiptNum;
	}

	/*
	 * (non-Javadoc)
	 * @see com.popbill.api.FaxService#resendFAXRN(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.util.Date, java.lang.String, java.lang.String)
	 */
	@Override
	public String resendFAXRN(String corpNum, String requestNum, String sendNum,
			String senderName, String receiveNum, String receiveName,
			Date reserveDT, String userID, String title, String originalFAXrequestNum)
			throws PopbillException {
		
		Receiver receiver = null;
		
		if ( !receiveNum.isEmpty() || !receiveName.isEmpty() ) {
			receiver = new Receiver();
			receiver.setReceiveNum(receiveNum);
			receiver.setReceiveName(receiveName);
			
			return resendFAXRN(corpNum,requestNum, sendNum, senderName, new Receiver[]{receiver}, reserveDT, userID, title, originalFAXrequestNum);
		}
		return resendFAXRN(corpNum,requestNum, sendNum, senderName, null, reserveDT, userID, title, originalFAXrequestNum);			
	}			

	/*
	 * (non-Javadoc)
	 * @see com.popbill.api.FaxService#resendFAXRN(java.lang.String, java.lang.String, java.lang.String, java.lang.String, com.popbill.api.fax.Receiver[], java.util.Date, java.lang.String, java.lang.String)
	 */
	@Override
	public String resendFAXRN(String corpNum, String requestNum, String sendNum,
			String senderName, Receiver[] receivers, Date reserveDT,
			String userID, String title, String originalFAXrequestNum) throws PopbillException {
		
		if(requestNum == null)
			throw new PopbillException(-99999999,"재전송 팩스의 전송요청번호(requestNum)가 입력되지 않았습니다.");
		
		if(originalFAXrequestNum == null)
			throw new PopbillException(-99999999,"원본 팩스 전송시 할당한 전송요청번호(originalFAXrequestNum)가 입력되지 않았습니다.");
		
		SendRequest request = new SendRequest();
		
		if (sendNum != null)
			request.snd = sendNum;
		
		if ( senderName != null)
			request.sndnm = senderName;
		
		if ( title != null)
			request.title = title;

		if (receivers != null) 
			request.rcvs = receivers;
		
		if (reserveDT != null) 
			request.sndDT = new SimpleDateFormat("yyyyMMddHHmmss", Locale.KOREA).format(reserveDT);
		
		if (requestNum != null) 
			request.requestNum = requestNum;
		
		String PostData = toJsonString(request);
		
		ReceiptResponse response = httppost("/FAX/Resend/"+originalFAXrequestNum, corpNum, PostData, userID, ReceiptResponse.class);
		
		return response.receiptNum;
	}	

	/*
	 * (non-Javadoc)
	 * @see com.popbill.api.FaxService#getFaxResult(java.lang.String, java.lang.String)
	 */
	@Override
	public FaxResult[] getFaxResult(String corpNum, String receiptNum)
			throws PopbillException {
		if (receiptNum == null)
			throw new PopbillException(-99999999, "접수번호가 입력되지 않았습니다.");

		return httpget("/FAX/" + receiptNum, corpNum, null,
				FaxResult[].class);
	}

	/*
	 * (non-Javadoc)
	 * @see com.popbill.api.FaxService#getFaxResultRN(java.lang.String, java.lang.String)
	 */
	@Override
	public FaxResult[] getFaxResultRN(String corpNum, String requestNum)
			throws PopbillException {
		if (requestNum == null)
			throw new PopbillException(-99999999, "전송요청번호가 입력되지 않았습니다.");

		return httpget("/FAX/Get/" + requestNum, corpNum, null,
				FaxResult[].class);
	}	

	/*
	 * (non-Javadoc)
	 * @see com.popbill.api.FaxService#cancelReserve(java.lang.String, java.lang.String)
	 */
	@Override
	public Response cancelReserve(String corpNum, String receiptNum)
			throws PopbillException {
		return cancelReserve(corpNum, receiptNum, null);
	}

	/*
	 * (non-Javadoc)
	 * @see com.popbill.api.FaxService#cancelReserve(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public Response cancelReserve(String corpNum, String receiptNum,String userID)
			throws PopbillException {
		if (receiptNum == null)
			throw new PopbillException(-99999999, "접수번호가 입력되지 않았습니다.");

		return httpget("/FAX/" + receiptNum + "/Cancel", corpNum, userID,
				Response.class);
	}

	/*
	 * (non-Javadoc)
	 * @see com.popbill.api.FaxService#cancelReserveRN(java.lang.String, java.lang.String)
	 */
	@Override
	public Response cancelReserveRN(String corpNum, String requestNum)
			throws PopbillException {
		return cancelReserveRN(corpNum, requestNum, null);
	}

	/*
	 * (non-Javadoc)
	 * @see com.popbill.api.FaxService#cancelReserveRN(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public Response cancelReserveRN(String corpNum, String requestNum,String userID)
			throws PopbillException {
		if (requestNum == null)
			throw new PopbillException(-99999999, "전송요청번호가 입력되지 않았습니다.");

		return httpget("/FAX/Cancel/"+requestNum, corpNum, userID,
				Response.class);
	}	

	/*
	 * (non-Javadoc)
	 * @see com.popbill.api.FaxService#search(java.lang.String, java.lang.String, java.lang.String, java.lang.String[], java.lang.Boolean, java.lang.Boolean, int, int, java.lang.String)
	 */
	@Override
	public FAXSearchResult search(String corpNum, String sDate, String eDate,
			String[] state, Boolean reserveYN, Boolean senderOnly, int page,
			int perPage, String order) throws PopbillException {
		return search(corpNum, sDate, eDate, state, reserveYN, senderOnly, page, perPage, order, null);
	}

	/*
	 * (non-Javadoc)
	 * @see com.popbill.api.FaxService#search(java.lang.String, java.lang.String, java.lang.String, java.lang.String[], java.lang.Boolean, java.lang.Boolean, int, int, java.lang.String, java.lang.String)
	 */
	@Override
	public FAXSearchResult search(String corpNum, String sDate, String eDate,
			String[] state, Boolean reserveYN, Boolean senderOnly, int page,
			int perPage, String order, String qString) throws PopbillException {
		if (sDate == null)
			throw new PopbillException(-99999999, "시작일자가 입력되지 않았습니다.");
		if (eDate == null)
			throw new PopbillException(-99999999, "종료일자가 입력되지 않았습니다.");
		
		String uri = "/FAX/Search?SDate=" + sDate;
		uri += "&EDate=" + eDate;
		uri += "&State=" + Arrays.toString(state)
				.replaceAll("\\[|\\]|\\s", "");
		
		if (reserveYN) {
			uri += "&ReserveYN=1";
		} else {
			uri += "&ReserveYN=0";
		}
		
		if (senderOnly) {
			uri +="&SenderOnly=1";
		} else {
			uri += "&SenderOnly=0";
		}
		
		uri += "&Page=" + Integer.toString(page);
		uri += "&PerPage=" + Integer.toString(perPage);
		uri += "&Order=" + order;
		
		if (qString != null)
			uri += "&QString=" + qString;
		
		return httpget(uri, corpNum, null, FAXSearchResult.class);
	}	
	
	/*
	 * (non-Javadoc)
	 * @see com.popbill.api.FaxService#getChargeInfo(java.lang.String)
	 */
	@Override
	public ChargeInfo getChargeInfo(String corpNum) throws PopbillException {
		return httpget("/FAX/ChargeInfo", corpNum, null, ChargeInfo.class);
	}

	/*
	 * (non-Javadoc)
	 * @see com.popbill.api.FaxService#getSenderNumberList(java.lang.String)
	 */
	@Override
	public SenderNumber[] getSenderNumberList(String corpNum)
			throws PopbillException {
		return getSenderNumberList(corpNum, null);
	}

	/*
	 * (non-Javadoc)
	 * @see com.popbill.api.FaxService#getSenderNumberList(java.lang.String, java.lang.String)
	 */
	@Override
	public SenderNumber[] getSenderNumberList(String corpNum, String userID)
			throws PopbillException {
		
		return httpget("/FAX/SenderNumber", corpNum, userID, SenderNumber[].class);
	}

	@Override
	public String getPreviewURL(String corpNum, String receiptNum) throws PopbillException {
		return getPreviewURL(corpNum, receiptNum, null);
	}

	@Override
	public String getPreviewURL(String corpNum, String receiptNum, String userID) throws PopbillException {
		if (receiptNum == null || receiptNum.equals(""))
			throw new PopbillException(-99999999, "접수번호가 입력되지 않았습니다.");

		URLResponse response =  httpget("/FAX/Preview/"+receiptNum, corpNum, userID, URLResponse.class);

		return response.url;
	}


	protected class SendRequest {
		public String snd;
		public String sndnm;
		public String sndDT;
		public String title;
		public Integer fCnt;
		public Boolean adsYN;
		public String requestNum;
		public Receiver[] rcvs;
	}

	protected class ReceiptResponse {
		public String receiptNum;
	}
}
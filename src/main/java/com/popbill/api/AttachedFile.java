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
package com.popbill.api;

/**
 * Class for Popbill AttachedFile Info .
 * @author KimSeongjun
 * @see http://www.popbill.com
 * @version 1.0.0
 */
public class AttachedFile {

	private Integer serialNum;
	private String attachedFile;
	private String displayName;
	private String regDT;
	
	/**
	 * returns attachedFile's ID used for deleteFile.
	 * @return attachedFile
	 */
	public String getAttachedFile() {
		return attachedFile;
	}
	/**
	 * returns DisplayName.
	 * @return DisplayName.
	 */
	public String getDisplayName() {
		return displayName;
	}
	/**
	 * returns registerd DateTime.
	 * @return regDT
	 */
	public String getRegDT() {
		return regDT;
	}
	/**
	 * returns serialNum
	 * @return serialNum
	 */
	public Integer getSerialNum() {
		return serialNum;
	}
	
}

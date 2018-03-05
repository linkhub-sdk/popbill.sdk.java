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
package com.popbill.api.kakao;

import java.io.Serializable;
import java.util.List;

public class ATSTemplate implements Serializable{

	private static final long serialVersionUID = -5297991073128871215L;
	
	private String templateCode;
	private String templateName;
	private String template;
	private String plusFriendID;
	private List<KakaoButton> btns;
	
	public String getTemplateCode() {
		return templateCode;
	}
	public void setTemplateCode(String templateCode) {
		this.templateCode = templateCode;
	}
	public String getTemplateName() {
		return templateName;
	}
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}
	public String getTemplate() {
		return template;
	}
	public void setTemplate(String template) {
		this.template = template;
	}
	public String getPlusFriendID() {
		return plusFriendID;
	}
	public void setPlusFriendID(String plusFriendID) {
		this.plusFriendID = plusFriendID;
	}
	public List<KakaoButton> getBtns() {
		return btns;
	}
	public void setBtns(List<KakaoButton> btns) {
		this.btns = btns;
	}

}

package com.tq.http;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.NameValuePair;

public class Main {
	public static void main(String[] args) {
		String action = "/mobile/sessionManageMobileManage/login.action";
		String actionProperty = "/mobile/propertyDictMobileManage/findPropertyDictByDomainNames.action";
		String actionUserInfo = "/mobile/userMobileManage/getCurrentLoginUser.action";
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new NameValuePair("userNameForLogin", "raygrid"));
		params.add(new NameValuePair("password", "11111111"));
		
		System.out.println(Request.getInstance().execPost(action, params));
		
		params.clear();
		System.out.println(Request.getInstance().execPost(actionUserInfo, params));
		
		params.add(new NameValuePair("domainNames", "性别，事件性质"));
		params.add(new NameValuePair("separator", "，"));
		
		System.out.println(Request.getInstance().execPost(actionProperty, params));
	}
}

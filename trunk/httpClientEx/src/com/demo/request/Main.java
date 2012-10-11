package com.demo.request;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.demo.request.vo.User;
import com.google.gson.Gson;

public class Main {
	public static void main(String[] args) {
		String actionLogin = "/mobile/sessionManageMobileManage/login.action";
		String actionUserInfo = "/mobile/userMobileManage/getCurrentLoginUser.action";
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("userName", "raygrid"));
		params.add(new BasicNameValuePair("password", "11111111"));
		
		HttpRequest request = new HttpRequest();
		request.execPost(actionLogin, params);
		
		String jsonUser = request.execPost(actionUserInfo, null);
		
		transformJson(jsonUser);
	}
	
	public static void transformJson(String json){
		try{
			User u = new Gson().fromJson(json, User.class);
			System.out.println("UserName is:" + u.getName());
		} catch(Exception e){
			System.out.println(e.getMessage());
		}
	} 
}

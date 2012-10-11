package com.demo.request;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;

public class HttpRequest {
	/**
	 * HOST
	 */
	private final String SCHEME = "http";
	private final String HOST = "opencart.gnway.net";
	private final int PORT = 8033;
	
	private static HttpClient mClient = null;
	private int TIME_CONNECTION_OUT = 10000;
	private int TIME_OUT = 10000;
	
	
	private HttpClient getInstance(){
		if(mClient == null){
			HttpParams httpParams = new BasicHttpParams();
			HttpProtocolParams.setVersion(httpParams, HttpVersion.HTTP_1_1);
			HttpProtocolParams.setContentCharset(httpParams, HTTP.UTF_8);
	    	HttpConnectionParams.setConnectionTimeout(httpParams,TIME_CONNECTION_OUT);
	    	HttpConnectionParams.setSoTimeout(httpParams, TIME_OUT);
	    	/*
	    	 * Cookie
	    	 */
	    	HttpClientParams.setCookiePolicy(httpParams, CookiePolicy.BROWSER_COMPATIBILITY);
	    	 
	    	mClient = new DefaultHttpClient(httpParams);
		}
    	
		return mClient;
	}
	
	public String execPost(String action, List<NameValuePair> params){
		String result = null;
		try{
			if(params == null){
				params = new ArrayList<NameValuePair>();
			}
			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, HTTP.UTF_8);
			HttpPost post = new HttpPost(getURI(action));
			post.setEntity(entity);
			
			for(Cookie c : ((AbstractHttpClient) getInstance()).getCookieStore().getCookies()){
				if(c.getName().equals("sid")){
					System.out.println("******Cookie:" + c.getValue());
				}
			}
			/*
			 * Execute the post request and get the response.
			 */
			HttpResponse response = getInstance().execute(post);
			
			result = getStringFromStream(response.getEntity().getContent());
			System.out.println("_________response:" + result);
			
		} catch (Exception e){
			System.out.println(e.getMessage());
		}
		
		return result;
	}
	
	
	private String getStringFromStream(InputStream ins){
		StringBuilder sb = new StringBuilder();
		String temp = null;
		
		try{
		BufferedReader reader = new BufferedReader(new InputStreamReader(ins, HTTP.UTF_8));
			while((temp = reader.readLine()) != null){
				sb.append(temp + "\n");
			}
		} catch(Exception e){
			System.out.println(e.getMessage());
			return null;
		}
		
		return sb.toString();
	}
	
	public URI getURI(String action){
		URI uri = null;
		try {
			uri = URIUtils.createURI(SCHEME, HOST, PORT, action , null ,null);
		} catch (URISyntaxException e) {
			System.out.println(e.getMessage());
		}
		
		return uri;
	}
	
}

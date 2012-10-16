package com.tq.http;

import java.util.List;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpVersion;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.URI;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;


public class Request {
	private final String SCHEME = "http";
	private final String HOST = "localhost";
	private final int PORT = 8034;
	
	private  HttpClient mClient = null;
	private static Request mRequest = new Request();
	
	private Request(){
		getClient();
	}
	
	public static Request getInstance(){
		return mRequest;
	}
	
	public  HttpClient getClient() {
		if (mClient == null) {
			/**
			 * Set the multi HTTP connection.
			 */
			MultiThreadedHttpConnectionManager connectionManager = new MultiThreadedHttpConnectionManager();
			mClient = new HttpClient(connectionManager);
			mClient.getHttpConnectionManager().getParams().setConnectionTimeout(10000);
			mClient.getParams().setVersion(HttpVersion.HTTP_1_1);
			mClient.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8");
		}
		return mClient;
	}
	
	public String execPost(String action, List<NameValuePair> params){
		try{
			String uri = getURI(action).getURI();
			PostMethod post = new PostMethod(uri);
			if(params != null){
				post.setRequestBody(params.toArray(new NameValuePair[params.size()]));
			}
			
			mClient.executeMethod(post);
			return post.getResponseBodyAsString();
		} catch (Exception e){
			System.out.println(e.getMessage());
		}
		
		return null;
	}
	
	public URI getURI(String action) {
		URI uri = null;
		try {
			uri = new URI(SCHEME, null, HOST, PORT, action);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return uri;
	}
}

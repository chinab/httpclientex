package com.tianque.jsoup.dao.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.tianque.jsoup.util.ApplicationContextUtil;

import cn.sckj.jsoup.exception.MyException;

public class DBManager {
	private static Map<String,String>  configMap = new HashMap<String,String>();
	private static Connection conn = null;
	private static String url = null;
	static{
		try {
			configMap = ApplicationContextUtil.getPropertyForJDBC();
			Class.forName(configMap.get("jdbc.driverClassName"));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static Connection getConn(){
		try {
			if(null == conn){
				 url =configMap.get("jdbc.url");
				 conn =DriverManager.getConnection(url,configMap.get("jdbc.username"),configMap.get("jdbc.password"));
			}
			return conn;
		} catch (SQLException e) {
			new MyException("获取连接出错!");
		}
		return null;
	}
	public static void close(ResultSet rs, PreparedStatement ps, Connection conn) {
		if(null != rs){
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(null != ps){
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
	}

}

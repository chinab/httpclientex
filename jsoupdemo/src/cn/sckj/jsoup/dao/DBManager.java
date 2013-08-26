package cn.sckj.jsoup.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cn.sckj.jsoup.exception.MyException;

public class DBManager {
	private static String connectURL="jdbc:mysql://localhost:3306/test?user=root&password=lixing";
	private static Connection conn = null;
	static{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static Connection getConn(){
		try {
			if(null == conn){
				 conn =DriverManager.getConnection(connectURL);
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

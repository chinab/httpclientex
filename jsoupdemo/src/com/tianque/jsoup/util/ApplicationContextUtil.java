package com.tianque.jsoup.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


public class ApplicationContextUtil {
	private static final Map<String ,String>  map = new HashMap<String,String>();
	public static Map<String,String> getPropertyForJDBC(){
		//加载dbconfig.properties文件，获取所需要的数据库配置
		String path = System.getProperty("user.dir");
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream(path+"/src/dbconfig.properties"));
			map.put("jdbc.url",prop.getProperty("jdbc.url"));
			map.put("jdbc.username",prop.getProperty("jdbc.username"));
			map.put("jdbc.password",prop.getProperty("jdbc.password"));
			map.put("jdbc.driverClassName", prop.getProperty("jdbc.driverClassName"));
			return map;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	} 
}

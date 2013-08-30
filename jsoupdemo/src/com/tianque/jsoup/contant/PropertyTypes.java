package com.tianque.jsoup.contant;

import java.util.ArrayList;
import java.util.List;

public class PropertyTypes {
	public static final String NEW = "news";
	public static final String EDU = "edu";
	public static final String QIWEN = "qiwen";
	public static final String SPORTS = "sports";
	
	public static final String SINA_FORUM = "新浪论坛";
	public static final String SINA_BLOG = "新浪博客";
	public static final String SINA_NEWS = "新浪新闻";
	public static final String SINA_POSTBAR = "新浪贴吧";
	public static final String OTHER = "其他";
	
	public static final String  KEYWORDS = "关键词";
	
	private static final List<String> keyWordsList = new ArrayList<String>();
	
	public static List<String> getKeyWordsList(){
		if(keyWordsList.size()>0){
			return keyWordsList;
		}
		keyWordsList.add("公安");
		keyWordsList.add("腐败");
		keyWordsList.add("强拆");
		keyWordsList.add("城管");
		keyWordsList.add("公务员考试");
		return keyWordsList;
	}
}

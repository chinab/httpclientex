package com.tianque.jsoup.contant;

import java.util.ArrayList;
import java.util.List;

public class PropertyTypes {
	public static final String NEW = "news";
	public static final String EDU = "edu";
	public static final String QIWEN = "qiwen";
	public static final String SPORTS = "sports";
	
	public static final String SINA_FORUM = "������̳";
	public static final String SINA_BLOG = "���˲���";
	public static final String SINA_NEWS = "��������";
	public static final String SINA_POSTBAR = "��������";
	public static final String OTHER = "����";
	
	public static final String  KEYWORDS = "�ؼ���";
	
	private static final List<String> keyWordsList = new ArrayList<String>();
	
	public static List<String> getKeyWordsList(){
		if(keyWordsList.size()>0){
			return keyWordsList;
		}
		keyWordsList.add("����");
		keyWordsList.add("����");
		keyWordsList.add("ǿ��");
		keyWordsList.add("�ǹ�");
		keyWordsList.add("����Ա����");
		return keyWordsList;
	}
}

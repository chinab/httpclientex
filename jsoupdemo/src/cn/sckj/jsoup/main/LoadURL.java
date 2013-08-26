package cn.sckj.jsoup.main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import cn.sckj.jsoup.templete.UrlHandler;
import cn.sckj.jsoup.util.CompilerTool;
import cn.sckj.jsoup.util.ContextLoader;

public class LoadURL {
	private static int drugurl_before_num = 0;
	private static int drugurl_after_num = 0;
	private static int categoryurl_before_num = 0;
	private static int categoryurl_after_num = 0;
	public static void main(String[] args) {
		try {
			//����config.properties�ļ�����ȡ����Ҫ������
			String path = System.getProperty("user.dir");
			Properties prop = new Properties();
			prop.load(new FileInputStream(path+"\\src\\config.properties"));
			
			String indexURL = prop.getProperty("indexURL");
			String categoryURLRegex = prop.getProperty("categoryURLRegex");
			String drugURLRegex = prop.getProperty("drugURLRegex");
			
			String urlHandler_serviceLocation = prop.getProperty("urlHandler_serviceLocation");
			String urlHandler_classpath = prop.getProperty("urlHandler_classpath");
			String urlHandler_className = prop.getProperty("urlHandler_className");

			String categoryurl_cache = prop.getProperty("categoryurl_cache");
			String drugurl_cache = prop.getProperty("drugurl_cache");
			
			List<String> categoryURLList = new ArrayList<String>();
			List<String> drugURLList = new ArrayList<String>();
			
			//�ѱ����categoryurl���ص��ڴ�
			ContextLoader.loadCache(categoryurl_cache, categoryURLList);
			categoryurl_before_num = categoryURLList.size();
			//�ѱ����drugurl���ص��ڴ�
			ContextLoader.loadCache(drugurl_cache, drugURLList);
			drugurl_before_num = drugURLList.size();
			
			//���ñ��빤���࣬����Ҫ���߼��ַ�����̬���ɶ���
			//����url�����࣬������ȡ���е�drugurl
			UrlHandler urlHandler = (UrlHandler) CompilerTool.compile(urlHandler_classpath,urlHandler_serviceLocation,urlHandler_className);
			urlHandler.getCategoryURLList(indexURL, categoryURLList, categoryURLRegex);
			categoryurl_after_num = categoryURLList.size();
			
			if(categoryurl_after_num > categoryurl_before_num){
				List<String> categoryURL_subList = categoryURLList.subList(categoryurl_before_num, categoryurl_after_num);
				if(drugurl_before_num == 0){
					
					//��������categoryurl�浽txt�ļ���
					ContextLoader.saveCache(categoryurl_cache,categoryURL_subList);
					
					for(String str:categoryURLList){
						urlHandler.getDrugURLList(str, drugURLList, drugURLRegex);
					}
					
					//���categoryURLList
					categoryURLList = null;
					
					//���categoryURL_subList
					categoryURL_subList = null;
					
				}else{
					//���categoryURLList
					categoryURLList = null;
					
					//��������categoryurl�浽txt�ļ���
					ContextLoader.saveCache(categoryurl_cache,categoryURL_subList);
					
					for(String str:categoryURL_subList){
						urlHandler.getDrugURLList(str, drugURLList, drugURLRegex);
					}
					//���categoryURL_subList
					categoryURL_subList = null;
				} 
				drugurl_after_num = drugURLList.size();
				if(drugurl_after_num > drugurl_before_num){
					List<String> drugURL_subList = drugURLList.subList(drugurl_before_num, drugurl_after_num);
					
					//���drugURLList
					drugURLList = null;
					
					//��������drugurl�浽txt�ļ���
					ContextLoader.saveCache(drugurl_cache,drugURL_subList);
					
					//���drugURL_subList
					drugURL_subList = null;
				} 
				System.out.println("��ȡ֮ǰ��categoryURL������"+categoryurl_before_num);
				System.out.println("��ȡ֮���categoryURL������"+categoryurl_after_num);
				System.out.println("��ȡ֮ǰ��drugURL������"+drugurl_before_num);
				System.out.println("��ȡ֮ǰ��drugURL������"+drugurl_after_num);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

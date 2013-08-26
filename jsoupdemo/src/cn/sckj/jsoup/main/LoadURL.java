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
			//加载config.properties文件，获取所需要的配置
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
			
			//把保存的categoryurl加载到内存
			ContextLoader.loadCache(categoryurl_cache, categoryURLList);
			categoryurl_before_num = categoryURLList.size();
			//把保存的drugurl加载到内存
			ContextLoader.loadCache(drugurl_cache, drugURLList);
			drugurl_before_num = drugURLList.size();
			
			//调用编译工具类，把需要的逻辑字符串动态生成对象
			//生成url处理类，用来爬取所有的drugurl
			UrlHandler urlHandler = (UrlHandler) CompilerTool.compile(urlHandler_classpath,urlHandler_serviceLocation,urlHandler_className);
			urlHandler.getCategoryURLList(indexURL, categoryURLList, categoryURLRegex);
			categoryurl_after_num = categoryURLList.size();
			
			if(categoryurl_after_num > categoryurl_before_num){
				List<String> categoryURL_subList = categoryURLList.subList(categoryurl_before_num, categoryurl_after_num);
				if(drugurl_before_num == 0){
					
					//将新增的categoryurl存到txt文件中
					ContextLoader.saveCache(categoryurl_cache,categoryURL_subList);
					
					for(String str:categoryURLList){
						urlHandler.getDrugURLList(str, drugURLList, drugURLRegex);
					}
					
					//清空categoryURLList
					categoryURLList = null;
					
					//清空categoryURL_subList
					categoryURL_subList = null;
					
				}else{
					//清空categoryURLList
					categoryURLList = null;
					
					//将新增的categoryurl存到txt文件中
					ContextLoader.saveCache(categoryurl_cache,categoryURL_subList);
					
					for(String str:categoryURL_subList){
						urlHandler.getDrugURLList(str, drugURLList, drugURLRegex);
					}
					//清空categoryURL_subList
					categoryURL_subList = null;
				} 
				drugurl_after_num = drugURLList.size();
				if(drugurl_after_num > drugurl_before_num){
					List<String> drugURL_subList = drugURLList.subList(drugurl_before_num, drugurl_after_num);
					
					//清空drugURLList
					drugURLList = null;
					
					//将新增的drugurl存到txt文件中
					ContextLoader.saveCache(drugurl_cache,drugURL_subList);
					
					//清空drugURL_subList
					drugURL_subList = null;
				} 
				System.out.println("爬取之前的categoryURL个数："+categoryurl_before_num);
				System.out.println("爬取之后的categoryURL个数："+categoryurl_after_num);
				System.out.println("爬取之前的drugURL个数："+drugurl_before_num);
				System.out.println("爬取之前的drugURL个数："+drugurl_after_num);
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

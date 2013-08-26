package cn.sckj.jsoup.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.sckj.jsoup.pojo.Drug;
import cn.sckj.jsoup.pojo.DrugURL;
import cn.sckj.jsoup.service.DrugService;
import cn.sckj.jsoup.service.DrugUrlService;

public class YP900Main {
	public static void main(String[] args) {
		
		//爬取所有药品说明书url，存在new.txt中
		/*String index_Url = "http://www.yp900.com/ypsms/";
		String pageCount_Element_Id = "lbltotalpage";
		String pageUrl_Regex = "^index_[1-9]{1}[0-9]{1,3}.htm$";
		String pageUrl_Str = "index_";
		String drugUrl_Regex = "^sms_[1-9]{1}[0-9]{0,5}.htm$";
		String drugUrl_Str = "sms_";
		String drugUrl_Apprences = "";
		int page = 0;
		Pattern p = Pattern.compile(drugUrl_Regex);
		Matcher m = p.matcher("sms_27666.htm");
		System.out.println(m.matches());
		List<String> page_Url_List = new ArrayList<String>();
		List<String> drug_Url_List = new ArrayList<String>();
		DrugUrlService 	drugUrlService = new DrugUrlService();
		DrugService drugService = new DrugService();
		page = Integer.valueOf(drugUrlService.getByElementId(index_Url,pageCount_Element_Id));
		drugUrlService.getPageUrl(index_Url,pageUrl_Str,page,page_Url_List);
		for(String str : page_Url_List){
			//System.out.println(str);
			drugUrlService.getDrugUrl(str,index_Url,drugUrl_Regex,drug_Url_List);
		}
		for(String str : drug_Url_List){
			System.out.println(str);
		}*/
		
		//从new.txt中拿出url，解析页面，截取数据存放库表
	/*	DrugService drugService = new DrugService();
		InputStream is = DXYMain.class.getResourceAsStream("/new.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String urlStr;
		Drug drug = null;
		try {
			while ((urlStr = br.readLine()) != null) {*/
		Drug drug = null;
		DrugService drugService = new DrugService();
				drug = drugService.htmlParsor2("http://www.yp900.com/ypsms/sms_205014.htm"); 
				drugService.insertDrug(drug); 
		/*	};
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				is.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
		
		
		
	}

}

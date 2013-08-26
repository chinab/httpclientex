package cn.sckj.jsoup.service;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import cn.sckj.jsoup.templete.UrlHandler;

public class DXYService implements UrlHandler {

	@Override
	public void getCategoryURLList(String indexURL, List<String> categoryURLList,String categoryURLRegex) {
			Document doc = null;
			Pattern p = null;
			Matcher m = null;
			String urltemp = null;
			try {
				doc = Jsoup.connect(indexURL).timeout(20000).get();
			} catch (IOException e) {
				e.printStackTrace();
			}
			Elements links = doc.select("a[href]");
			for (Element link : links) {
				urltemp = link.attr("abs:href");
				if(categoryURLList.contains(urltemp)){
					loopPagination(urltemp,categoryURLList);
					continue;
				}else{
					p = Pattern.compile(categoryURLRegex);
					m = p.matcher(urltemp);
					if (m.matches()) {
						categoryURLList.add(urltemp);
						loopPagination(urltemp,categoryURLList); 
						continue;
					}
				}
		}
	}

	//循环得到各种药品的url
	@Override
	public void getDrugURLList(String categoryURL, List<String> drugURLList,String drugURLRegex) {
			Document doc = null;
			Pattern p = null;
			Matcher m = null;
			String urltemp = null;
			try {
				doc = Jsoup.connect(categoryURL).timeout(20000).get();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Elements links = doc.select("div[class=fl] a");
			for (Element link : links) {
				urltemp = link.attr("href");
				if(!drugURLList.contains(urltemp)){
					p = Pattern.compile(drugURLRegex);
					m = p.matcher(urltemp);
					if (m.matches()) {
							drugURLList.add(urltemp);
							continue;
					}
				}
			}
	}
	
	//处理翻页
	private void loopPagination(String url,List<String> categoryURLList){
		Document doc = null;
		String urltemp = "?page=";
		int count = 0;
		try {
			doc = Jsoup.connect(url).timeout(20000).get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Elements elements = doc.select("div[class=pagination] span[class=current]");
		for(Element e : elements){
			String str = e.attr("title");
			count = Integer.valueOf(str.substring(str.lastIndexOf("共")+1, str.lastIndexOf("页")));
			
			for(int i = 2; i <= count; i++){
				if(!categoryURLList.contains(url+urltemp+i)){
					categoryURLList.add(url+urltemp+i);
				}
			}
		}
	} 
}

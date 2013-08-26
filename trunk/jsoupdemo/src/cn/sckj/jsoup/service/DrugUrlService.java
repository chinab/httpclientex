package cn.sckj.jsoup.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class DrugUrlService {
	private String regex_new1 = "^http://db.yaozh.com/instruct/20121225/[0-9]{1,4}.html$";
	private String categoryRegex = "^http://drugs.dxy.cn/category/([0-9]{1,3}).htm$";
	private String drugRegex = "^http://drugs.dxy.cn/drug/([0-9]{5}).htm$";
	private List<String> categoryUrlList = new ArrayList<String>();
	private List<String> drugUrllist = new ArrayList<String>();

	public DrugUrlService() {
		String path = System.getProperty("user.dir");
		try {
			System.setOut(new PrintStream(path + "/new.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 获取categoryUrlList
	public List<String> getCategoryUrlList() {
		return categoryUrlList;
	}

	// 获取drugUrllist
	public List<String> getDrugUrllist() {
		return drugUrllist;
	}

	// 循环得到各种药品分类目录Url
	public void loop(String url) {
		Document doc = null;
		Pattern p = null;
		Matcher m = null;
		String urltemp = null;
		try {
			doc = Jsoup.connect(url).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Elements links = doc.select("a[href]");
		for (Element link : links) {
			urltemp = link.attr("abs:href");
			p = Pattern.compile(categoryRegex);
			m = p.matcher(urltemp);
			if (m.matches()) {
				categoryUrlList.add(urltemp);
				loopDrug(urltemp);
				continue;
			}
		}
	}

	// 循环得到各种药品的url
	public void loopDrug(String url) {
		Document doc = null;
		Pattern p = null;
		Matcher m = null;
		String urltemp = null;
		try {
			doc = Jsoup.connect(url).get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Elements links = doc.select("div[class=fl] a");
		for (Element link : links) {
			urltemp = link.attr("href");
			p = Pattern.compile(drugRegex);
			m = p.matcher(urltemp);
			if (m.matches()) {
				drugUrllist.add(urltemp);
				continue;
			}
		}
	}

	public String getByElementId(String url, String pageCount_Element_Id) {
		Document doc = null;
		try {
			doc = Jsoup.connect(url).timeout(20000).get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Element element = doc.getElementById(pageCount_Element_Id);
		if (element.attr("value").equals("")) {
			return element.text();
		}
		return element.attr("value");
	}

	public void getPageUrl(String index_Url, String pageUrl_Str, int page,
			List<String> page_Url_List) {
		if (page > 0) {
			for (int i = 2; i <= page; i++) {
				page_Url_List.add(index_Url + pageUrl_Str + i + ".htm");
			}
		}
	}

	public void getDrugUrl(String url, String index_Url, String drugUrl_Regex,
			List<String> drug_Url_List) {
		Document doc = null;
		Pattern p = null;
		Matcher m = null;
		String urltemp = null;
		try {
			doc = Jsoup.connect(url).timeout(20000).get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Elements links = doc.select("li a");
		for (Element link : links) {
			urltemp = link.attr("href");
			p = Pattern.compile(drugUrl_Regex);
			m = p.matcher(urltemp);
			if (m.matches()) {
				System.out.println(index_Url + urltemp);
				continue;
			}
		}
	}
	/*//处理翻页
		private void loopPagination(String url){
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
					categoryUrlList.add(url+urltemp+i);
					loopDrug(url+urltemp+i);
				}
			}
		} 
		public void loop2(String start,String url){
			Document doc = null;
			Pattern p = null;
			Matcher m = null;
			try {
				doc = Jsoup.connect(url).get();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Elements links = doc.select("td[class=left] a");
			for (Element link : links) {
				String urltemp = link.attr("href");
				p = Pattern.compile(regex_new1);
				m = p.matcher(start+urltemp);
				if (m.matches()) {
					//System.out.println(start+urltemp);
					loop3(start+urltemp);
					continue;
				} 
			}
		}
		//处理翻页
		public void loop1(String url){
			Document doc = null;
			try {
				doc = Jsoup.connect(url+"instruct.html").timeout(20000).get();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Elements elements = doc.select("div[class=feny] div[class=pages] a");
			for(Element e : elements){
				String str = e.attr("href");
				categoryUrlList.add(url+str);
					//System.out.println(url+str);
					loop2(url,url+str);
				}
			 
		} 
		//处理翻页
			public void loop3(String url){
				Document doc = null;
				try {
					doc = Jsoup.connect(url).timeout(20000).get();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Element element = doc.getElementById("text");
				Elements content = element.getElementsByAttributeValue("class", "content");
				for(Element e : content){
					String str = e.text();
					System.out.println(str);
				}
			}*/

}

package cn.sckj.jsoup.main;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * 抓去百度数据
 */
public class Test {
	public static void main(String[] args) {
		String url="http://www.baidu.com/s?wd=%E4%BD%A0%E5%A5%BD&pn=110&tn=baiduhome_pg&ie=utf-8&f=3&usm=2";
		Document doc = null;
		try {
			doc = Jsoup.connect(url).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Elements links = doc.select("table tr td h3 a");
		for (Element link : links) {
			System.out.println(link.attr("href"));
		}
	}

}

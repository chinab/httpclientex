package com.tianque.jsoup;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.tianque.jsoup.contant.PropertyTypes;
import com.tianque.jsoup.domain.MonitorCategoryTree;
import com.tianque.jsoup.domain.MonitoringResults;
import com.tianque.jsoup.exception.MyException;
import com.tianque.jsoup.service.MonitorCategoryTreeService;
import com.tianque.jsoup.service.MonitoringResultsSerivce;
import com.tianque.jsoup.service.PropertyDictService;
import com.tianque.jsoup.service.PropertyDomainService;
import com.tianque.jsoup.service.impl.MonitorCategoryTreeServiceImpl;
import com.tianque.jsoup.service.impl.MonitoringResultsSerivceImpl;
import com.tianque.jsoup.service.impl.PropertyDictServiceImpl;
import com.tianque.jsoup.service.impl.PropertyDomainServiceImpl;

public class AppMain {

	private static Map<String, MonitoringResults> map = new HashMap<String, MonitoringResults>();
	private static List<String> page = new ArrayList<String>();
	private static int count = 0;

	public static void main(String[] args) {

		MonitorCategoryTreeService monitorCategoryTreeService = new MonitorCategoryTreeServiceImpl();
		PropertyDictService propertyDictService = new PropertyDictServiceImpl();
		PropertyDomainService propertyDomainService = new PropertyDomainServiceImpl();
		MonitoringResultsSerivce monitoringResultsSerivce = new MonitoringResultsSerivceImpl();
		MonitoringResults monitoringResult = null;
		for (int i = 0; i < 10; i++) {
			map.put(String.valueOf(i), new MonitoringResults());
		}
		for (int i = 0; i < 100;) {
			page.add(String.valueOf(i));
			i += 10;
		}
		try {
			List<String> keyWordsList = PropertyTypes.getKeyWordsList();
			for (String keywords : keyWordsList) {

				Long id = propertyDictService
						.findPropertyDictIdByPropertyDomainIdAndDisplayname(
								propertyDomainService
										.getPropertyDomainIdByDomainName(PropertyTypes.KEYWORDS),
								keywords);
				for(String pages : page){
				Document doc = Jsoup
						.connect(
								"http://www.baidu.com/s?wd=site%3A%28sina.cn%29"
										+ keywords+"&pn="+pages)
						.timeout(20000).get();
				
				Elements url = doc.select("#content_left .t");
				for (Element e : url) {
					monitoringResult = map.get(String.valueOf(count));
					monitoringResult.setKeyWordsID(id);
					monitoringResult.setCreateUser("超级管理员");
					monitoringResult.setCreateDate(Calendar.getInstance()
							.getTime());
					monitoringResult.setContentUrl(e.child(0).attr("href"));
					
					monitoringResult.setHotIndex(Long.valueOf("0"));
					monitoringResult.setReprintNumber(Long.valueOf("0"));
					monitoringResult.setHits(Long.valueOf("0"));
					monitoringResult.setReceiptNumber(Long.valueOf("0"));
					count++;
				}
				count = 0;
				
				Elements contents = doc.select("#content_left .c-abstract");
				for (Element e : contents) {
					monitoringResult = map.get(String.valueOf(count));
					monitoringResult.setContentSubject(e.ownText());
					count++;
				}
				count = 0;
				
				Elements others = doc.select("#content_left .f13");
				for (Element e : others) {
					monitoringResult = map.get(String.valueOf(count));
					String b = e.text().substring(0, e.text().indexOf("."));
					System.out.println(b);
					if (b.equals(PropertyTypes.NEW)) {
						MonitorCategoryTree monitorCategoryTree = monitorCategoryTreeService
								.getMonitorCategoryTreeByNodeName(PropertyTypes.SINA_NEWS);
						monitoringResult
								.setMonitorCategoryTreeID(monitorCategoryTree
										.getId());
					} else {
						MonitorCategoryTree monitorCategoryTree = monitorCategoryTreeService
								.getMonitorCategoryTreeByNodeName(PropertyTypes.OTHER);
						monitoringResult
								.setMonitorCategoryTreeID(monitorCategoryTree
										.getId());
					}
					SimpleDateFormat formatDate = new SimpleDateFormat(
							"yyyy-MM-dd");
					Date date;
					try {
						date = formatDate.parse(e
								.text()
								.trim()
								.substring(e.text().lastIndexOf(".") + 2,
										e.text().length() - 7));
					} catch (ParseException e1) {
						new MyException("在爬取" + keywords + "关键词时的第" + count
								+ "条数据出错");
						continue;
					}
					monitoringResult.setPostTime(date);
					count++;
				}
				count = 0;
				
				
				Set<Entry<String, MonitoringResults>> set = map.entrySet();
				Iterator<Entry<String, MonitoringResults>> iterator = set
						.iterator();
				while (iterator.hasNext()) {
					monitoringResultsSerivce.addMonitoringResults(map
							.get(iterator.next().getKey()));
				}
			}
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

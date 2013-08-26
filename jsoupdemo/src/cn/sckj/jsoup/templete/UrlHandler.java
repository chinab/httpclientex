package cn.sckj.jsoup.templete;

import java.util.List;

public interface UrlHandler {
	public void getCategoryURLList(String indexURL , List<String> categoryURLList ,String categoryURLRegex); 
	public void getDrugURLList(String categoryURL , List<String> drugURLList,String drugURLRegex);
}

package cn.sckj.jsoup.main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import cn.sckj.jsoup.dao.DrugDao;
import cn.sckj.jsoup.dao.IDrugDao;
import cn.sckj.jsoup.pojo.Drug;
import cn.sckj.jsoup.templete.HtmlHandler;
import cn.sckj.jsoup.util.CompilerTool;
import cn.sckj.jsoup.util.ContextLoader;

public class LoadData {
	private static IDrugDao dao = new DrugDao();

	public static void main(String[] args) {
		try {
			// ����config.properties�ļ�����ȡ����Ҫ������
			String path = System.getProperty("user.dir");
			Properties prop = new Properties();
			prop.load(new FileInputStream(path + "\\src\\config.properties"));

			String htmlHandler_serviceLocation = prop.getProperty("htmlHandler_serviceLocation");
			String htmlHandler_classpath = prop.getProperty("htmlHandler_classpath");
			String htmlHandler_className = prop.getProperty("htmlHandler_className");
			String drugurl_cache = prop.getProperty("drugurl_cache");
			// ��ȡ��վ����
			String drug_wzmc = prop.getProperty("drug_wzmc");

			List<String> drugURLList = new ArrayList<String>();
			List<String> drug_load_URLList = new ArrayList<String>();

			// �ѱ����drugurl���ص��ڴ�
			ContextLoader.loadCache(drugurl_cache, drugURLList);
			// ���ݿ���д���Ķ�Ӧ��վ��ַ��url���жϸ�load��Щҳ��
			for (String str : drugURLList) {
				if (!dao.checkURL(str, drug_wzmc)) {
					drug_load_URLList.add(str);
				}
			}
			// ���drugURLList
			drugURLList = null;

			// ���ñ��빤���࣬����Ҫ���߼��ַ�����̬���ɶ���
			// ����html���������࣬������ȡdrugurlҳ���ϵ�����
			HtmlHandler htmlHandler = (HtmlHandler) CompilerTool.compile(
					htmlHandler_classpath, htmlHandler_serviceLocation,
					htmlHandler_className);
			for (String str : drug_load_URLList) {
				Drug drug = htmlHandler.htmlParsor(str);
				if(null != drug){
					dao.insertDrug(drug);
				}
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

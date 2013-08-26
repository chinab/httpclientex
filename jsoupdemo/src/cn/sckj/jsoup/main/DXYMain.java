package cn.sckj.jsoup.main;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

import org.eclipse.jetty.util.StringUtil;
import org.jsoup.helper.Validate;

import com.gargoylesoftware.htmlunit.util.StringUtils;

import cn.sckj.jsoup.dao.DrugURLDao;
import cn.sckj.jsoup.dao.IDrugURLDao;
import cn.sckj.jsoup.exception.MyException;
import cn.sckj.jsoup.pojo.Drug;
import cn.sckj.jsoup.pojo.DrugURL;
import cn.sckj.jsoup.service.DrugService;
import cn.sckj.jsoup.service.DrugUrlService;

public class DXYMain {

	public static void main(String[] args) {

		DrugService drugService = new DrugService();

		// 把drugUrl插入到drugURl库表里

		/*IDrugURLDao dao = new DrugURLDao();
		Drug drug = null;
		DrugURL drugURL = null;
		InputStream is = DXYMain.class.getResourceAsStream("/drugurl.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String urlStr;
		try {
			while ((urlStr = br.readLine()) != null) {
				drugURL = new DrugURL();
				drugURL.setURL_ID(urlStr.substring(urlStr.lastIndexOf(".htm") - 5,urlStr.lastIndexOf(".htm")));
				drugURL.setURL_STR(urlStr);
				dao.insertDrug(drugURL);
			};
		} catch (IOException e) {
			e.printStackTrace();
		}*/

		// 把drug插入到drug库表里

		
		  Drug drug = null; InputStream is = DXYMain.class.getResourceAsStream("/drugurl.txt"); 
		  BufferedReader br = new BufferedReader(new InputStreamReader(is)); 
		  String urlStr; 
		  try {
			 while((urlStr = br.readLine())!= null){
				  	drug = drugService.htmlParsor(urlStr); 
				  	drugService.insertDrug(drug); 
				 } 
			 }catch (IOException e) { 
				  
			 }finally{
				  try { 
					  is.close(); 
				  } catch (IOException e) {
					  e.printStackTrace();
				  } 
			 }

		/*
		 * if (drug != null) { drugService.insertDrug(drug); }
		 */


	}
}

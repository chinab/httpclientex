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

public class TestMain {

	public static void main(String[] args) {

		DrugService drugService = new DrugService();

		// 把drugUrl插入到drugURl库表里
		/*
		 * IDrugURLDao dao = new DrugURLDao(); Drug drug = null; DrugURL drugURL
		 * = null; InputStream is =
		 * TestMain.class.getResourceAsStream("/drugurl.txt"); BufferedReader br
		 * = new BufferedReader(new InputStreamReader(is)); String urlStr;
		 * boolean bz = false; try { while((urlStr = br.readLine())!= null){
		 * drugURL = new DrugURL();
		 * drugURL.setURL_ID(urlStr.substring(urlStr.lastIndexOf(".htm")-5,
		 * urlStr.lastIndexOf(".htm"))); drugURL.setURL_STR(urlStr);
		 * dao.insertDrug(drugURL); }; } catch (IOException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */
		
		//把drug插入到drug库表里
		
		/* Drug drug = null; 
		 InputStream is = TestMain.class.getResourceAsStream("/drugurl.txt"); 
		 BufferedReader br = new BufferedReader(new InputStreamReader(is)); 
		 String urlStr;
		 try {
			while((urlStr = br.readLine())!= null){
				 drug = drugService.htmlParsor(urlStr);
				 drugService.insertDrug(drug);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
		}finally{
			try {
				is.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}*/
		 
		
		
		
		/*
		 * if (drug != null) { drugService.insertDrug(drug); }
		 */

		// 提供indexUrl来获取其下面的所有drugUrl 
		DrugUrlService drugUrlService = new DrugUrlService();
		//String[] str = new String[] { "http://drugs.dxy.cn/" };
		String[] str = new String[] { "http://db.yaozh.com/" };
		Validate.isTrue(str.length == 1, "提供URL");
		String url = str[0];
		drugUrlService.loop(url);
		List<String> drugUrlList = drugUrlService.getDrugUrllist();
		for (String drugurl : drugUrlList) {
			System.out.println(drugurl);
		}

	}

}

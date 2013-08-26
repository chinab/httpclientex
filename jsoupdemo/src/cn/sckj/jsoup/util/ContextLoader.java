package cn.sckj.jsoup.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;

public class ContextLoader {

	public static void loadCache(String filename,List<String> list){
		  InputStream is = null;
		  try {
			    is= new FileInputStream(System.getProperty("user.dir")+"\\src\\"+filename);
				BufferedReader br = new BufferedReader(new InputStreamReader(is)); 
			    String urlStr; 
		  		while((urlStr = br.readLine())!= null){
				 	list.add(urlStr);
				} 
			 }catch (IOException e) { 
				e.printStackTrace();
			 }finally{
				  try { 
					  is.close(); 
				  } catch (IOException e) {
					  e.printStackTrace();
				  } 
			 }
	}

	public static void saveCache(String filename, List<String> list) {
		OutputStream os = null;
		  try {
			    os= new FileOutputStream(System.getProperty("user.dir")+"\\src\\"+filename , true);
			    PrintWriter pw = new PrintWriter(os);
			    for(String str : list){
			    	pw.println(str);
			    }
			    pw.flush();
			    pw.close();
			 }catch (IOException e) { 
				e.printStackTrace();
			 }finally{
				  try { 
					  os.close(); 
				  } catch (IOException e) {
					  e.printStackTrace();
				  } 
			 }
	}

}

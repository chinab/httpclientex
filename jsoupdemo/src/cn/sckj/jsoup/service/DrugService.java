package cn.sckj.jsoup.service;

import java.io.IOException;
import java.net.MalformedURLException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import cn.sckj.jsoup.dao.DrugDao;
import cn.sckj.jsoup.exception.MyException;
import cn.sckj.jsoup.pojo.Drug;

public class DrugService {
	private static int DXYID = 869;
	private static int YP900ID = 2000;
	DrugDao dao = null;	
	public DrugService(){
		dao = new DrugDao();
	}
	//丁香园————解析药品页面获取数据,返回Drug实体类
    public  Drug htmlParsor(String urlStr){
    		Drug drug = null;
    		String ypmc_str = null;
			try {
				Document doc;
				try {
					doc = Jsoup.connect(urlStr).timeout(20000).get();
				} catch (MalformedURLException e1) {
					new MyException(urlStr+"url错误!");
					 return null;
				}
				Elements elements = doc.select("span[class=fl]");
				if(elements.size() == 0){
					return null;
				}else{
					drug = new Drug();
					drug.setId(DXYID);
					DXYID++;
					drug.setDRUGID(urlStr.substring(urlStr.lastIndexOf(".htm")-5, urlStr.lastIndexOf(".htm")));
					for(Element e:elements){
							if(e.text().equals("药品名称:")){
							//	arryTemp = e.text().split(regex);
								drug.setYPMC(e.parent().nextElementSibling().text());
								ypmc_str = e.parent().nextElementSibling().text();
								int tymc_index = ypmc_str.indexOf("通用名称：");
								int ywmc_index = ypmc_str.indexOf("英文名称：");
								int spmc_index = ypmc_str.indexOf("商品名称：");
								if(-1 != tymc_index && -1 != ywmc_index){
									drug.setTYM(ypmc_str.substring(tymc_index+5, ywmc_index));
								}
								if(-1 != spmc_index && -1 != ywmc_index){
									drug.setWWM(ypmc_str.substring(ywmc_index+5, spmc_index));
								}
								if(-1 != spmc_index){
									drug.setSPM(ypmc_str.substring(spmc_index+5));
								}
							}
							if(e.text().equals("成份:")){
								drug.setCF(e.parent().nextElementSibling().text());
							}
							if(e.text().equals("适应症:")){
								drug.setSYZ(e.parent().nextElementSibling().text());
							}
							if(e.text().equals("规格:")){
								drug.setGG(e.parent().nextElementSibling().text());
							}
							if(e.text().equals("用法用量:")){
								drug.setYFYL(e.parent().nextElementSibling().text());
							}
							if(e.text().equals("不良反应:")){
								drug.setBLFY(e.parent().nextElementSibling().text());
							}
							if(e.text().equals("禁忌:")){
								drug.setJJ(e.parent().nextElementSibling().text());
							}
							if(e.text().equals("注意事项:")){
								drug.setZYSX(e.parent().nextElementSibling().text());
							}
							if(e.text().equals("孕妇及哺乳期妇女用药:")){
								drug.setYFYY(e.parent().nextElementSibling().text());
							}
							if(e.text().equals("药物相互作用:")){
								drug.setYWXHZY(e.parent().nextElementSibling().text());
							}
							if(e.text().equals("药物过量:")){
								drug.setYWGL(e.parent().nextElementSibling().text());
							}
							if(e.text().equals("药理作用:")){
								drug.setYLZY(e.parent().nextElementSibling().text());
							}
							if(e.text().equals("药代动力学:")){
								drug.setYDDLX(e.parent().nextElementSibling().text());
							}
							if(e.text().equals("性状:")){
								drug.setXZ(e.parent().nextElementSibling().text());
							}
							if(e.text().equals("贮藏:")){
								drug.setZC(e.parent().nextElementSibling().text());
							}
							if(e.text().equals("有效期:")){
								drug.setYXQ(e.parent().nextElementSibling().text());
							}
							if(e.text().equals("批准文号:")){
								drug.setPZWH(e.parent().nextElementSibling().text());
							}
							if(e.text().equals("生产企业:")){
								drug.setSCQY(e.parent().nextElementSibling().text());
							}
							if(e.text().equals("药物分类:")){
								drug.setYWFL(e.parent().nextElementSibling().text());
							}
							if(e.text().equals("包装:")){
								drug.setBZ(e.parent().nextElementSibling().text());
							}
							if(e.text().equals("毒理研究:")){
								drug.setDLYJ(e.parent().nextElementSibling().text());
							}
							if(e.text().equals("执行标准:")){
								drug.setZXBZ(e.parent().nextElementSibling().text());
							}
							if(e.text().equals("核准日期:")){
								drug.setHZRQ(e.parent().nextElementSibling().text());
							}
							if(e.text().equals("儿童用药:")){
								drug.setETYY(e.parent().nextElementSibling().text());
							}
							if(e.text().equals("老年用药:")){
								drug.setLNYY(e.parent().nextElementSibling().text());
							}
							if(e.text().equals("修改日期:")){
								drug.setXGRQ(e.parent().nextElementSibling().text());
							}
							
					}
				}
			}catch(IOException e){
				// TODO Auto-generated catch block
				e.printStackTrace();
				new MyException("爬取数据失败!"+urlStr);
				return null;
			}
			return drug;
    }
    
    //yp900————解析药品页面获取数据,返回 Drug实体类
    public  Drug htmlParsor2(String urlStr){
    	Drug drug = null;
    	String contentTemp = null;
    	String [] arryTemp = null;
		try {
			Document doc;
			try {
				doc = Jsoup.connect(urlStr).timeout(20000).get();
			} catch (MalformedURLException e1) {
				new MyException(urlStr+"url错误!");
				 return null;
			}
			Element summary = doc.getElementById("Summary");
			Element contents = doc.getElementById("Content");
			Elements summary_content = summary.select("li");
			if(summary_content.size() == 0 || contents.text() == null){
				return null;
			}else {
				drug = new Drug();
				drug.setId(YP900ID);
				YP900ID++;
				drug.setDRUGID("00000");
				for(Element e : summary_content){
					contentTemp = e.text();
					arryTemp = contentTemp.split("：");
					if(arryTemp.length > 1){
						if(arryTemp[0].equals("药品名称")){
							drug.setYPMC(arryTemp[1]);
						}
						if(arryTemp[0].equals("生产厂家")){
							drug.setSCQY(arryTemp[1]);
						}
						if(arryTemp[0].equals("批准文号")){
							drug.setPZWH(arryTemp[1]);
						}
						if(arryTemp[0].equals("规 格")){
							drug.setGG(arryTemp[1]);
						}
					}
				}
				String content = contents.text();
				String arry1[] = content.split("【");
				String arry2[] = null;
				for(String str1:arry1){
					arry2= str1.split("】");
					if(arry2.length>1){
						if(arry2[0].equals("性状")){
							drug.setXZ(arry2[1]);
						}
						if(arry2[0].equals("主要成分")){
							drug.setCF(arry2[1]);
						}
						if(arry2[0].equals("适应症")){
							drug.setSYZ(arry2[1]);
						}
						if(arry2[0].equals("用法用量")){
							drug.setYFYL(arry2[1]);
						}
						if(arry2[0].equals("不良反应")){
							drug.setBLFY(arry2[1]);
						}
						if(arry2[0].equals("禁忌")){
							drug.setJJ(arry2[1]);
						}
						if(arry2[0].equals("注意事项")){
							drug.setZYSX(arry2[1]);
						}
						if(arry2[0].equals("孕妇及哺乳期妇女用药")){
							drug.setYFYY(arry2[1]);
						}
						if(arry2[0].equals("儿童用药")){
							drug.setETYY(arry2[1]);
						}
						if(arry2[0].equals("老年患者用药")){
							drug.setLNYY(arry2[1]);
						}
						if(arry2[0].equals("药物相互作用")){
							drug.setYWXHZY(arry2[1]);
						}
						if(arry2[0].equals("药物相互作用")){
							drug.setYWXHZY(arry2[1]);
						}
						if(arry2[0].equals("药物过量")){
							drug.setYWGL(arry2[1]);
						}
						if(arry2[0].equals("药理毒理")){
							drug.setDLYJ(arry2[1]);
						}
						if(arry2[0].equals("药代动力学")){
							drug.setYDDLX(arry2[1]);
						}
						if(arry2[0].equals("贮藏")){
							drug.setZC(arry2[1]);
						}
						if(arry2[0].equals("包装")){
							drug.setBZ(arry2[1]);
						}
						if(arry2[0].equals("有效期")){
							drug.setYXQ(arry2[1]);
						}
					}
				}
			}
						
		}catch(IOException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
			new MyException("爬取数据失败!"+urlStr);
			return null;
		}
		return drug;
    }
    
	//调用dao方法向库表中插入数据
     public int insertDrug(Drug drug){
    	return dao.insertDrug(drug);
    }
}

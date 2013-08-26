package cn.sckj.jsoup.service;

import java.io.IOException;
import java.net.MalformedURLException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import cn.sckj.jsoup.exception.MyException;
import cn.sckj.jsoup.pojo.Drug;
import cn.sckj.jsoup.templete.HtmlHandler;

public class DXYDrugService implements HtmlHandler {

	@Override
	public Drug htmlParsor(String drugURL) {
		Drug drug = null;
		String ypmc_str = null;
		try {
			Document doc;
			try {
				doc = Jsoup.connect(drugURL).timeout(20000).get();
			} catch (MalformedURLException e1) {
				new MyException(drugURL+"url错误!");
				 return null;
			}
			Elements elements = doc.select("span[class=fl]");
			if(elements.size() == 0){
				return null;
			}else{
				drug = new Drug();
				drug.setWZMC("丁香园");
				drug.setWZDZ(drugURL);
				drug.setDRUGID(drugURL.substring(drugURL.lastIndexOf(".htm")-5, drugURL.lastIndexOf(".htm")));
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
			new MyException("爬取数据失败!"+drugURL);
			return null;
		}
		return drug;
	}

}

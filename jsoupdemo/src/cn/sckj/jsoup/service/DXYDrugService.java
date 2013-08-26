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
				new MyException(drugURL+"url����!");
				 return null;
			}
			Elements elements = doc.select("span[class=fl]");
			if(elements.size() == 0){
				return null;
			}else{
				drug = new Drug();
				drug.setWZMC("����԰");
				drug.setWZDZ(drugURL);
				drug.setDRUGID(drugURL.substring(drugURL.lastIndexOf(".htm")-5, drugURL.lastIndexOf(".htm")));
				for(Element e:elements){
						if(e.text().equals("ҩƷ����:")){
						//	arryTemp = e.text().split(regex);
							drug.setYPMC(e.parent().nextElementSibling().text());
							ypmc_str = e.parent().nextElementSibling().text();
							int tymc_index = ypmc_str.indexOf("ͨ�����ƣ�");
							int ywmc_index = ypmc_str.indexOf("Ӣ�����ƣ�");
							int spmc_index = ypmc_str.indexOf("��Ʒ���ƣ�");
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
						if(e.text().equals("�ɷ�:")){
							drug.setCF(e.parent().nextElementSibling().text());
						}
						if(e.text().equals("��Ӧ֢:")){
							drug.setSYZ(e.parent().nextElementSibling().text());
						}
						if(e.text().equals("���:")){
							drug.setGG(e.parent().nextElementSibling().text());
						}
						if(e.text().equals("�÷�����:")){
							drug.setYFYL(e.parent().nextElementSibling().text());
						}
						if(e.text().equals("������Ӧ:")){
							drug.setBLFY(e.parent().nextElementSibling().text());
						}
						if(e.text().equals("����:")){
							drug.setJJ(e.parent().nextElementSibling().text());
						}
						if(e.text().equals("ע������:")){
							drug.setZYSX(e.parent().nextElementSibling().text());
						}
						if(e.text().equals("�и��������ڸ�Ů��ҩ:")){
							drug.setYFYY(e.parent().nextElementSibling().text());
						}
						if(e.text().equals("ҩ���໥����:")){
							drug.setYWXHZY(e.parent().nextElementSibling().text());
						}
						if(e.text().equals("ҩ�����:")){
							drug.setYWGL(e.parent().nextElementSibling().text());
						}
						if(e.text().equals("ҩ������:")){
							drug.setYLZY(e.parent().nextElementSibling().text());
						}
						if(e.text().equals("ҩ������ѧ:")){
							drug.setYDDLX(e.parent().nextElementSibling().text());
						}
						if(e.text().equals("��״:")){
							drug.setXZ(e.parent().nextElementSibling().text());
						}
						if(e.text().equals("����:")){
							drug.setZC(e.parent().nextElementSibling().text());
						}
						if(e.text().equals("��Ч��:")){
							drug.setYXQ(e.parent().nextElementSibling().text());
						}
						if(e.text().equals("��׼�ĺ�:")){
							drug.setPZWH(e.parent().nextElementSibling().text());
						}
						if(e.text().equals("������ҵ:")){
							drug.setSCQY(e.parent().nextElementSibling().text());
						}
						if(e.text().equals("ҩ�����:")){
							drug.setYWFL(e.parent().nextElementSibling().text());
						}
						if(e.text().equals("��װ:")){
							drug.setBZ(e.parent().nextElementSibling().text());
						}
						if(e.text().equals("�����о�:")){
							drug.setDLYJ(e.parent().nextElementSibling().text());
						}
						if(e.text().equals("ִ�б�׼:")){
							drug.setZXBZ(e.parent().nextElementSibling().text());
						}
						if(e.text().equals("��׼����:")){
							drug.setHZRQ(e.parent().nextElementSibling().text());
						}
						if(e.text().equals("��ͯ��ҩ:")){
							drug.setETYY(e.parent().nextElementSibling().text());
						}
						if(e.text().equals("������ҩ:")){
							drug.setLNYY(e.parent().nextElementSibling().text());
						}
						if(e.text().equals("�޸�����:")){
							drug.setXGRQ(e.parent().nextElementSibling().text());
						}
						
				}
			}
		}catch(IOException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
			new MyException("��ȡ����ʧ��!"+drugURL);
			return null;
		}
		return drug;
	}

}

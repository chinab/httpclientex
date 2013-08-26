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
	//����԰������������ҩƷҳ���ȡ����,����Drugʵ����
    public  Drug htmlParsor(String urlStr){
    		Drug drug = null;
    		String ypmc_str = null;
			try {
				Document doc;
				try {
					doc = Jsoup.connect(urlStr).timeout(20000).get();
				} catch (MalformedURLException e1) {
					new MyException(urlStr+"url����!");
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
				new MyException("��ȡ����ʧ��!"+urlStr);
				return null;
			}
			return drug;
    }
    
    //yp900������������ҩƷҳ���ȡ����,���� Drugʵ����
    public  Drug htmlParsor2(String urlStr){
    	Drug drug = null;
    	String contentTemp = null;
    	String [] arryTemp = null;
		try {
			Document doc;
			try {
				doc = Jsoup.connect(urlStr).timeout(20000).get();
			} catch (MalformedURLException e1) {
				new MyException(urlStr+"url����!");
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
					arryTemp = contentTemp.split("��");
					if(arryTemp.length > 1){
						if(arryTemp[0].equals("ҩƷ����")){
							drug.setYPMC(arryTemp[1]);
						}
						if(arryTemp[0].equals("��������")){
							drug.setSCQY(arryTemp[1]);
						}
						if(arryTemp[0].equals("��׼�ĺ�")){
							drug.setPZWH(arryTemp[1]);
						}
						if(arryTemp[0].equals("�� ��")){
							drug.setGG(arryTemp[1]);
						}
					}
				}
				String content = contents.text();
				String arry1[] = content.split("��");
				String arry2[] = null;
				for(String str1:arry1){
					arry2= str1.split("��");
					if(arry2.length>1){
						if(arry2[0].equals("��״")){
							drug.setXZ(arry2[1]);
						}
						if(arry2[0].equals("��Ҫ�ɷ�")){
							drug.setCF(arry2[1]);
						}
						if(arry2[0].equals("��Ӧ֢")){
							drug.setSYZ(arry2[1]);
						}
						if(arry2[0].equals("�÷�����")){
							drug.setYFYL(arry2[1]);
						}
						if(arry2[0].equals("������Ӧ")){
							drug.setBLFY(arry2[1]);
						}
						if(arry2[0].equals("����")){
							drug.setJJ(arry2[1]);
						}
						if(arry2[0].equals("ע������")){
							drug.setZYSX(arry2[1]);
						}
						if(arry2[0].equals("�и��������ڸ�Ů��ҩ")){
							drug.setYFYY(arry2[1]);
						}
						if(arry2[0].equals("��ͯ��ҩ")){
							drug.setETYY(arry2[1]);
						}
						if(arry2[0].equals("���껼����ҩ")){
							drug.setLNYY(arry2[1]);
						}
						if(arry2[0].equals("ҩ���໥����")){
							drug.setYWXHZY(arry2[1]);
						}
						if(arry2[0].equals("ҩ���໥����")){
							drug.setYWXHZY(arry2[1]);
						}
						if(arry2[0].equals("ҩ�����")){
							drug.setYWGL(arry2[1]);
						}
						if(arry2[0].equals("ҩ����")){
							drug.setDLYJ(arry2[1]);
						}
						if(arry2[0].equals("ҩ������ѧ")){
							drug.setYDDLX(arry2[1]);
						}
						if(arry2[0].equals("����")){
							drug.setZC(arry2[1]);
						}
						if(arry2[0].equals("��װ")){
							drug.setBZ(arry2[1]);
						}
						if(arry2[0].equals("��Ч��")){
							drug.setYXQ(arry2[1]);
						}
					}
				}
			}
						
		}catch(IOException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
			new MyException("��ȡ����ʧ��!"+urlStr);
			return null;
		}
		return drug;
    }
    
	//����dao���������в�������
     public int insertDrug(Drug drug){
    	return dao.insertDrug(drug);
    }
}

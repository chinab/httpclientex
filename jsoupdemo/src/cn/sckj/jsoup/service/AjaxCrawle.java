package cn.sckj.jsoup.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.util.List;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.ScriptResult;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlOption;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSelect;

public class AjaxCrawle {

	private static String TARGET_URL = "http://databank.worldbank.org/ddp/home.do";

	public static void main(String[] args)
			throws FailingHttpStatusCodeException, MalformedURLException,
			IOException {
		// ģ��һ�������
		WebClient webClient = new WebClient();
		// ����webClient����ز���
		webClient.setJavaScriptEnabled(true);
		webClient.setCssEnabled(false);
		webClient.setAjaxController(new NicelyResynchronizingAjaxController());
		webClient.setTimeout(35000);
		webClient.setThrowExceptionOnScriptError(false);
		// ģ���������һ��Ŀ����ַ
		HtmlPage rootPage = webClient.getPage(TARGET_URL);
		// ��ȡ��һ�����ݿ�
		HtmlSelect hs = (HtmlSelect) rootPage.getElementById("lstCubes");
		// ��Ҫ��ѡ���һ�����ݿ�
		hs.getOption(0).setSelected(true);
		// ģ����Next��ť����ת���ڶ���ҳ��
		System.out.println("������ת��");
		// ִ�а�ť������js�¼�
		ScriptResult sr = rootPage
				.executeJavaScript("javascript:setCubeData(2,-1,4,��/ddp��);");

		// ��ת���ڶ���ҳ�棬ѡ�����
		HtmlPage countrySelect = (HtmlPage) sr.getNewPage();
		// ��ð���ȫ��������Ϣ��ѡ���ҳ��
		HtmlPage framePage = (HtmlPage) countrySelect
				.getFrameByName("frmTree1").getEnclosedPage();
		// ���selectAll��ť������js�¼�
		framePage
				.executeJavaScript("javascript:TransferListAll('countrylst','countrylstselected','no');SetSelectedCount('countrylstselected','tdcount');");
		// ��ȡNext��ť������js�¼�
		ScriptResult electricityScriptResult = framePage
				.executeJavaScript("javascript:wrapperSetCube('/ddp')");

		System.out.println("������ת��");
		// ��ת����һ��ҳ��electricitySelect
		HtmlPage electricitySelect = (HtmlPage) electricityScriptResult
				.getNewPage();
		// ���electricityѡ���iframe
		HtmlPage electricityFrame = (HtmlPage) electricitySelect
				.getFrameByName("frmTree1").getEnclosedPage();
		// ���ѡ���
		HtmlSelect seriesSelect = (HtmlSelect) electricityFrame
				.getElementById("countrylst");
		// ������е�ѡ�������
		List optionList = seriesSelect.getOptions();
		// ��ָ����ѡ��ѡ��
		((HtmlOption) optionList.get(1)).setSelected(true);
		// ģ����select��ť
		electricityFrame
				.executeJavaScript("javascript:TransferList('countrylst','countrylstselected��,'no');SetSelectedCount('countrylstselected','tdcount');");
		// ��ȡѡ�к������ѡ���
		HtmlSelect electricitySelected = (HtmlSelect) electricityFrame
				.getElementById("countrylstselected");
		List list = electricitySelected.getOptions();
		// ģ����Next��ť����ת��ѡ��ʱ���ҳ��
		ScriptResult timeScriptResult = electricityFrame
				.executeJavaScript("javascript:wrapperSetCube('/ddp')");

		System.out.println("������ת��");
		HtmlPage timeSelectPage = (HtmlPage) timeScriptResult.getNewPage();
		// ��ȡѡ��ʱ���ѡ���
		timeSelectPage = (HtmlPage) timeSelectPage.getFrameByName("rmTree1")
				.getEnclosedPage();
		// ѡ�����е�ʱ��
		timeSelectPage
				.executeJavaScript("javascript:TransferListAll('countrylst','countrylstselected','no');SetSelectedCount('countrylstselected','tdcount');");
		// ���Next��ť
		ScriptResult exportResult = timeSelectPage
				.executeJavaScript("javascript:wrapperSetCube('/ddp')");

		System.out.println("������ת��");
		// ת��exportҳ��
		HtmlPage exportPage = (HtmlPage) exportResult.getNewPage();
		// ���ҳ���ϵ�Export��ť,��������ҳ��
		ScriptResult downResult = exportPage
				.executeJavaScript("javascript:exportData('/ddp' ,'EXT_BULK' ,'WDI_Time=51||WDI_Series=1||WDI_Ctry=244||' );");

		System.out.println("������ת��");
		HtmlPage downLoadPage = (HtmlPage) downResult.getNewPage();
		// ���Excelͼ�꣬��ʼ����
		ScriptResult downLoadResult = downLoadPage
				.executeJavaScript("javascript:exportData('/ddp','BULKEXCEL');");
		// ����Excel�ļ�
		InputStream is = downLoadResult.getNewPage().getWebResponse()
				.getContentAsStream();

		OutputStream fos = new FileOutputStream("d://test.xls");
		byte[] buffer = new byte[1024 * 30];
		int len = -1;
		while ((len = is.read(buffer)) > 0) {
			fos.write(buffer, 0, len);
		}
		fos.close();
		fos.close();
		System.out.println("Success!");
	}
}
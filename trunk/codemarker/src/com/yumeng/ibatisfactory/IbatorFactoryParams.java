package com.yumeng.ibatisfactory;

import java.util.List;

public class IbatorFactoryParams {
	public static String TABLES;
	public static String PACKAGE;
	public static List<String> TEMPLATESLIST;

	public void setTables(String tables) {
		TABLES = tables;
	}

	public void setPag(String pag) {
		PACKAGE = pag;
	}

	public void setTemplateList(List<String> templateList) {
		TEMPLATESLIST = templateList;
	}

}

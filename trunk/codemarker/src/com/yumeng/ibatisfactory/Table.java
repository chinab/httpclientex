package com.yumeng.ibatisfactory;

import java.util.ArrayList;
import java.util.List;

public class Table {
	private String tableName;
	private String className;
	private String pag;
	private String namespace;
	private List<TableField> fields;

	public Table(String tableName,String pag) {
		this.tableName = tableName;
		this.pag = pag;
		this.namespace = tableName.toUpperCase().replaceAll("_", "");
		fields = new ArrayList<TableField>();

		String[] params = tableName.toLowerCase().split("_");
		className = "";
		for (int i = 0; i < params.length; i++) {
			String index = params[i];
			if (index.length() > 1) {
				index = index.substring(0, 1).toUpperCase() + index.substring(1, index.length()).toLowerCase();
			} else {
				index = index.substring(0, 1).toUpperCase();
			}
			className = className + index;
		}
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getNamespace() {
		return namespace;
	}

	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}

	public List<TableField> getFields() {
		return fields;
	}

	public void setFields(List<TableField> fields) {
		this.fields = fields;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getPag() {
		return pag;
	}

	public void setPag(String pag) {
		this.pag = pag;
	}
	

}

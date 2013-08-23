package com.yumeng.ibatisfactory;

public interface TypeMap {
	public String getPropertyType(String columnName,String columnType,Integer precision,Integer scale);

	public String getSql();
}

package com.yumeng.ibatisfactory.oracle;

import java.util.Hashtable;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.yumeng.ibatisfactory.TypeMap;
public class Oracle11SqlTypeMap implements TypeMap{
	
	private Map<String,String> map;
	private Map<String,String> regMap;
	private String sql;
	public Oracle11SqlTypeMap(){
		map=new Hashtable<String,String>();
		map.put("varchar2", "String");
		map.put("nvarchar2", "String");
		map.put("integer","Integer");
		map.put("money", "Double");
		map.put("timestamp", "Date");
		map.put("date", "Date");
		map.put("char", "Boolean");
		map.put("tinyint", "Integer");
		map.put("number", "Integer");
		map.put("numeric", "Double");
		map.put("ntext", "String");
		//键值规则
		regMap=new Hashtable<String,String>();
		regMap.put("ID", "Long");
		regMap.put("\\w*_ID", "Long");
		sql = "select t.column_name as \"name\",t.data_precision as \"precision\",t.data_scale as \"scale\",c.comments as \"remark\",t.data_type \"datatype\""
			+" from user_tab_columns t inner join user_col_comments c on t.table_name= c.table_name and t.column_name= c.column_name"
			+" where t.table_name='{tableName}'";
	}
	@Override
	public String getPropertyType(String columnName,String columnType, Integer precision, Integer scale) {
		if(precision>0)
			return "Double";
		//map查找
		String resultType=map.get(columnType.toLowerCase());
		//正规表达式名字判断过滤
		for(String index:regMap.keySet()){
			Pattern pattern = Pattern.compile(index, Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(columnName);
			if(matcher.matches()){
				resultType=regMap.get(index);
				break;
			}
		}
		//匹配无效默认String
		if (resultType==null)
			resultType="String";
		return resultType;
	}
	@Override
	public String getSql() {
		return sql;
	}
	
}

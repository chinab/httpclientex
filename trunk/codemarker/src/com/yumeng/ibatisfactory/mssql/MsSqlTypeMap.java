package com.yumeng.ibatisfactory.mssql;

import java.util.Hashtable;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.yumeng.ibatisfactory.TypeMap;
public class MsSqlTypeMap implements TypeMap{
	
	private Map<String,String> map;
	private Map<String,String> regMap;
	private String sql;
	public MsSqlTypeMap(){
		map=new Hashtable<String,String>();
		map.put("varchar", "String");
		map.put("nvarchar", "String");
		map.put("int","Integer");
		map.put("money", "Double");
		map.put("datetime", "Date");
		map.put("smallint", "Boolean");
		map.put("tinyint", "Integer");
		map.put("float", "Double");
		map.put("numeric", "Double");
		map.put("ntext", "String");
		//键值规则
		regMap=new Hashtable<String,String>();
		regMap.put("ID", "Long");
		regMap.put("\\w*_ID", "Long");
		sql = "select col.name,col.precision,col.scale,prop.value remark,(select name from sys.systypes where xusertype=system_type_id) datatype from sys.all_objects ob"
			+ " left join sys.all_columns col on ob.object_id=col.object_id"
			+ " left join sys.extended_properties prop on ob.object_id=prop.major_id and col.column_id=prop.minor_id"
			+ " where ob.type in ('U','V') and ob.name='{tableName}' order by column_id asc";
	}
	@Override
	public String getPropertyType(String columnName,String columnType, Integer precision, Integer scale) {
		//map查找
		String resultType=map.get(columnType);
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

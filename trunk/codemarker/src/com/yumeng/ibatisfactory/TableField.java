package com.yumeng.ibatisfactory;

public class TableField {
	private String columnName; // 字段名
	private String columnType; // 字段类型
	private String property; // 属性名
	private String propertyType;// 属性 类型String Boolean Integer Double
	private String procedureName; // setXxxxXxxx getXxxxXxxx
	private int precision; // 精度
	private int scale; // 刻度
	private String remark;//备注信息
	public TableField(String columnName, String columnType, int precision, int scale,String remark,TypeMap typeMap) {
		this.columnName = columnName;
		this.columnType = columnType;
		this.precision = precision;
		this.scale = scale;
		this.remark= remark;
		//以_为单词分隔符制作属性名
		String[] params = columnName.toLowerCase().split("_");
		for (int i=0;i<params.length;i++) {
			String index = params[i];
			if (index.length() > 1) {
				index = index.substring(0, 1).toUpperCase()+index.substring(1,index.length()).toLowerCase();
			} else {
				index = index.substring(0, 1).toUpperCase();
			}
			params[i]=index;
		}
		property=params[0].toLowerCase();
		procedureName=params[0];
		for(int i=1;i<params.length;i++){
			String index=params[i];
			property=property+index;
			procedureName=procedureName+index;
		}
		propertyType=typeMap.getPropertyType(columnName, columnType, precision, scale);
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getPropertyType() {
		return propertyType;
	}

	public void setPropertyType(String propertyType) {
		this.propertyType = propertyType;
	}

	public String getProcedureName() {
		return procedureName;
	}

	public void setProcedureName(String procedureName) {
		this.procedureName = procedureName;
	}

	public String getColumnType() {
		return columnType;
	}

	public void setColumnType(String columnType) {
		this.columnType = columnType;
	}

	public int getPrecision() {
		return precision;
	}

	public void setPrecision(int precision) {
		this.precision = precision;
	}

	public int getScale() {
		return scale;
	}

	public void setScale(int scale) {
		this.scale = scale;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}

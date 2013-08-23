<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE sqlMap PUBLIC "-//ibatis.apache.org//DTD SQL Map 2.0//EN" "http://ibatis.apache.org/dtd/sql-map-2.dtd" >
<#assign beanAlias=table.className?uncap_first>
<sqlMap namespace="${beanAlias}" >
	<#assign resultMap=table.className?uncap_first + "ResultMap">
	<#assign sysFields=["id","deleted","createTime","modifiedTime"]>
	<typeAlias alias="${beanAlias}" type="${table.pag}.domain.${table.className}"/>
	<resultMap id="${resultMap}" class="${beanAlias}" >
  	<#list table.fields as field>
    	<result column="${field.columnName}" property="${field.property}"/>
    </#list>
	</resultMap>
	
	<sql id="ALL_SQL">
        <#list table.fields as field>
        <#if sysFields?seq_index_of(field.property) = -1>
		<isNotEmpty prepend="and" property="${field.property}" >
  			<#if field.propertyType="Date">
			to_char(${field.columnName},'yyyy-mm-dd') = to_char(#${field.property}#,'yyyy-mm-dd')
  			<#else >
			${field.columnName} = #${field.property}#
  			</#if>
		</isNotEmpty>
  		</#if>
	</#list>
	</sql>
  
	<select id="get${table.className}ById" resultMap="${resultMap}" parameterClass="Long" >
		select *
		from ${table.tableName}
		where ID = #value#
	</select>
	
	<delete id="delete${table.className}ById" parameterClass="Long" >
		delete from ${table.tableName}
		where ID = #value# 
	</delete>
	<insert id="add${table.className}" parameterClass="${beanAlias}">	
		<selectKey keyProperty="id" resultClass="java.lang.Long">
			select S_${table.tableName}.NEXTVAL as id from dual
		</selectKey>
		insert into ${table.tableName}
		(
		 <#list table.fields as field><#if field_index != table.fields?size-1>${field.columnName},<#else>${field.columnName}</#if></#list>
		)
		values
		(
		 <#list table.fields as field><#if field_index != table.fields?size-1>#${field.property}#,<#else>#${field.property}#</#if></#list>
		)
			
	</insert>
	<update id="update${table.className}ById" parameterClass="${beanAlias}" >
		update ${table.tableName}
		set 
		<#list table.fields as field>
			<#if field.property != "id" && field_index != table.fields?size-1>
		      ${field.columnName} = #${field.property}#,
		    <#else>
		      ${field.columnName} = #${field.property}#
			</#if>
	 	</#list>
		where ID = #id# 
	</update>
</sqlMap>
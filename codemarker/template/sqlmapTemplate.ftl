<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE sqlMap PUBLIC "-//ibatis.apache.org//DTD SQL Map 2.0//EN" "http://ibatis.apache.org/dtd/sql-map-2.dtd" >
<#assign beanAlias=table.className?uncap_first>
<sqlMap namespace="${beanAlias}" >
	<#assign resultMap=table.className?uncap_first + "ResultMap">
	<#assign sysFields=["id","deleted","createTime","modifiedTime"]>
	<typeAlias alias="${beanAlias}" type="${table.pag}.model.${table.className}"/>
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
			convert(varchar(10),${field.columnName},121) = convert(varchar(10),#${field.property}#,121)
  			<#else >
			${field.columnName} = #${field.property}#
  			</#if>
		</isNotEmpty>
  		</#if>
	</#list>
	</sql>
	
	<select id="SELECT_PAGE" parameterClass="java.util.Map" resultMap="${resultMap}">
		select * from ${table.tableName} where 1=1 and deleted=0
		<include refid="ALL_SQL"/>
		order by create_time desc
	</select>
	  
	<select id="SELECT_COUNT_PAGE" parameterClass="${beanAlias}" resultClass="Integer">
		select count(*) as totalCount from ${table.tableName} where 1=1 and deleted=0
		<include refid="ALL_SQL"/>
	</select>
  
	<select id="SELECT_BY_ALL" resultMap="${resultMap}" parameterClass="${beanAlias}">
  	select * from ${table.tableName}
	where deleted=0
		<include refid="ALL_SQL"/>
		order by create_time desc
	</select>
	
	<select id="SELECT_BY_PK" resultMap="${resultMap}" parameterClass="${beanAlias}" >
		select *
		from ${table.tableName}
		where ID = #id#
	</select>
	
	<delete id="DELETE_BY_PK" parameterClass="${beanAlias}" >
		delete from ${table.tableName}
		where ID = #id# and MODIFIED_TIME=#modifiedTime#
	</delete>
	
	<insert id="INSERT" parameterClass="${beanAlias}" >
		insert into ${table.tableName}
		<dynamic prepend="(" >
		<#list table.fields as field>
			<isNotNull prepend="," property="${field.property}" >
				${field.columnName}
			</isNotNull>
		</#list>
			)
		</dynamic>
		values
		<dynamic prepend="(">
		<#list table.fields as field>
			<isNotNull prepend="," property="${field.property}" >
				#${field.property}#
			</isNotNull>
		</#list>
			)
		</dynamic>
	</insert>
	
	<update id="UPDATE_BY_PK" parameterClass="${beanAlias}" >
		update ${table.tableName}
		set 
		<#list table.fields as field>
			<#if (field.property != "id") && (field.property != "modifiedTime")>
		${field.columnName} = #${field.property}#,
			</#if>
	 	</#list>
		MODIFIED_TIME = #now#
		where ID = #id# and MODIFIED_TIME=#modifiedTime#
	</update>
	
	<select id="SELECT_SEQ" resultClass="Long">
		declare @a int exec NEXT_VAL '${table.tableName}',@a output select @a
	</select>
</sqlMap>
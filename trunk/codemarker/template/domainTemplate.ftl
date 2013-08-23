package ${table.pag}.domain;
import com.tianque.core.base.BaseDomain;
import java.util.Date;

public class ${table.className} extends BaseDomain{
	private static final long serialVersionUID = 1L;
	<#assign sysFields=["id","createUser","createDate","updateUser","updateDate"]>


	<#list table.fields as index>
	<#if sysFields?seq_index_of(index.property) = -1>
	private ${index.propertyType} ${index.property};
	</#if>
	</#list>

	<#list table.fields as index>
	<#if sysFields?seq_index_of(index.property) = -1>
	public void set${index.procedureName}(${index.propertyType} ${index.property}){
		this.${index.property} = ${index.property};
	}
	/**
	 *${index.remark?default("")}
	 */
	public ${index.propertyType} get${index.procedureName}(){
		return this.${index.property};
	}
	</#if>
	</#list>
}
package ${table.pag}.dao;
import ${table.pag}.domain.${table.className};
public interface ${table.className}Dao{
    public ${table.className} get${table.className}ById(Long id);
	public ${table.className} add${table.className}(${table.className} ${table.className?uncap_first});
	public ${table.className} update${table.className}ById(${table.className} ${table.className?uncap_first});	
	public void delete${table.className}ById(Long id) ;	
}

package ${table.pag}.service.impl;
import ${table.pag}.domain.${table.className};
import ${table.pag}.dao.${table.className}Dao;
import ${table.pag}.service.${table.className}Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tianque.core.exception.DAOException;
@Service("${table.className?uncap_first}Service")
@Transactional
public class ${table.className}ServiceImpl implements ${table.className}Service{
    @Autowired
    private ${table.className}Dao ${table.className?uncap_first}Dao;
    public ${table.className} get${table.className}ById(Long id){
         return ${table.className?uncap_first}Dao.get${table.className}ById(id);
    }
	public ${table.className} add${table.className}(${table.className} ${table.className?uncap_first}){
		return ${table.className?uncap_first}Dao.add${table.className}(${table.className?uncap_first});
	}
	public ${table.className} update${table.className}ById(${table.className} ${table.className?uncap_first}){
	   
		return ${table.className?uncap_first}Dao.update${table.className}ById(${table.className?uncap_first}) ;
	}	
	public void delete${table.className}ById(Long id){
	    ${table.className?uncap_first}Dao.delete${table.className}ById(id) ;
	}
}

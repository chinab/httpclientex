package ${table.pag}.dao.impl;
import org.springframework.stereotype.Repository;
import ${table.pag}.domain.${table.className};
import ${table.pag}.dao.${table.className}Dao;
import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.exception.DAOException;
@Repository("${table.className?uncap_first}Dao")
public class ${table.className}DaoImpl extends AbstractBaseDao implements ${table.className}Dao{
    public ${table.className} get${table.className}ById(Long id){
         return (${table.className}) getSqlMapClientTemplate().queryForObject("${table.className?uncap_first}.get${table.className}ById", id);
    }
	public ${table.className} add${table.className}(${table.className} ${table.className?uncap_first}){
		Long id = (Long) getSqlMapClientTemplate().insert("${table.className?uncap_first}.add${table.className}", ${table.className?uncap_first});
		return get${table.className}ById(id);
	}
	public ${table.className} update${table.className}ById(${table.className} ${table.className?uncap_first}){
	    getSqlMapClientTemplate().update("${table.className?uncap_first}.update${table.className}ById",${table.className?uncap_first});
		${table.className?uncap_first} =get${table.className}ById(${table.className?uncap_first}.getId());
		return ${table.className?uncap_first} ;
	}	
	public void delete${table.className}ById(Long id){
	   getSqlMapClientTemplate().delete("${table.className?uncap_first}.delete${table.className}ById", id);
	}
}

package com.tianque.plugin.schoolAround.rentalHouse.dao.impl;
import org.springframework.stereotype.Repository;
import com.tianque.plugin.schoolAround.rentalHouse.domain.SchoolRentalHouse;
import com.tianque.plugin.schoolAround.rentalHouse.dao.SchoolRentalHouseDao;
import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.exception.DAOException;
@Repository("schoolRentalHouseDao")
public class SchoolRentalHouseDaoImpl extends AbstractBaseDao implements SchoolRentalHouseDao{
    public SchoolRentalHouse getSchoolRentalHouseById(Long id){
         return (SchoolRentalHouse) getSqlMapClientTemplate().queryForObject("schoolRentalHouse.getSchoolRentalHouseById", id);
    }
	public SchoolRentalHouse addSchoolRentalHouse(SchoolRentalHouse schoolRentalHouse){
		Long id = (Long) getSqlMapClientTemplate().insert("schoolRentalHouse.addSchoolRentalHouse", schoolRentalHouse);
		return getSchoolRentalHouseById(id);
	}
	public SchoolRentalHouse updateSchoolRentalHouseById(SchoolRentalHouse schoolRentalHouse){
	    getSqlMapClientTemplate().update("schoolRentalHouse.updateSchoolRentalHouseById",schoolRentalHouse);
		schoolRentalHouse =getSchoolRentalHouseById(schoolRentalHouse.getId());
		return schoolRentalHouse ;
	}	
	public void deleteSchoolRentalHouseById(Long id){
	   getSqlMapClientTemplate().delete("schoolRentalHouse.deleteSchoolRentalHouseById", id);
	}
}

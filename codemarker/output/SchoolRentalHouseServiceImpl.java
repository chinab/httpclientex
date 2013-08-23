package com.tianque.plugin.schoolAround.rentalHouse.service.impl;
import com.tianque.plugin.schoolAround.rentalHouse.domain.SchoolRentalHouse;
import com.tianque.plugin.schoolAround.rentalHouse.dao.SchoolRentalHouseDao;
import com.tianque.plugin.schoolAround.rentalHouse.service.SchoolRentalHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tianque.core.exception.DAOException;
@Service("schoolRentalHouseService")
@Transactional
public class SchoolRentalHouseServiceImpl implements SchoolRentalHouseService{
    @Autowired
    private SchoolRentalHouseDao schoolRentalHouseDao;
    public SchoolRentalHouse getSchoolRentalHouseById(Long id){
         return schoolRentalHouseDao.getSchoolRentalHouseById(id);
    }
	public SchoolRentalHouse addSchoolRentalHouse(SchoolRentalHouse schoolRentalHouse){
		return schoolRentalHouseDao.addSchoolRentalHouse(schoolRentalHouse);
	}
	public SchoolRentalHouse updateSchoolRentalHouseById(SchoolRentalHouse schoolRentalHouse){
	   
		return schoolRentalHouseDao.updateSchoolRentalHouseById(schoolRentalHouse) ;
	}	
	public void deleteSchoolRentalHouseById(Long id){
	    schoolRentalHouseDao.deleteSchoolRentalHouseById(id) ;
	}
}

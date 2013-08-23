package com.tianque.plugin.schoolAround.rentalHouse.dao;
import com.tianque.plugin.schoolAround.rentalHouse.domain.SchoolRentalHouse;
public interface SchoolRentalHouseDao{
    public SchoolRentalHouse getSchoolRentalHouseById(Long id);
	public SchoolRentalHouse addSchoolRentalHouse(SchoolRentalHouse schoolRentalHouse);
	public SchoolRentalHouse updateSchoolRentalHouseById(SchoolRentalHouse schoolRentalHouse);	
	public void deleteSchoolRentalHouseById(Long id) ;	
}

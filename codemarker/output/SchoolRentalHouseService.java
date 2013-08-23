package com.tianque.plugin.schoolAround.rentalHouse.service;
import com.tianque.plugin.schoolAround.rentalHouse.domain.SchoolRentalHouse;
public interface SchoolRentalHouseService{
    public SchoolRentalHouse getSchoolRentalHouseById(Long id);
	public SchoolRentalHouse addSchoolRentalHouse(SchoolRentalHouse schoolRentalHouse);
	public SchoolRentalHouse updateSchoolRentalHouseById(SchoolRentalHouse schoolRentalHouse);	
	public void deleteSchoolRentalHouseById(Long id) ;	
}

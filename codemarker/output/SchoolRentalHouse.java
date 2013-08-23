package com.tianque.plugin.schoolAround.rentalHouse.domain;
import com.tianque.core.base.BaseDomain;
import java.util.Date;

public class SchoolRentalHouse extends BaseDomain{
	private static final long serialVersionUID = 1L;


	private Long rentalHouseId;

	public void setRentalHouseId(Long rentalHouseId){
		this.rentalHouseId = rentalHouseId;
	}
	/**
	 *出租房ID
	 */
	public Long getRentalHouseId(){
		return this.rentalHouseId;
	}
}
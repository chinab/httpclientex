package com.tianque.jsoup.service.impl;

import com.tianque.jsoup.dao.PropertyDomainDao;
import com.tianque.jsoup.dao.impl.PropertyDomainDaoImpl;
import com.tianque.jsoup.service.PropertyDomainService;

public class PropertyDomainServiceImpl implements PropertyDomainService{
	private PropertyDomainDao propertyDomainDao = new PropertyDomainDaoImpl();
	@Override
	public Long getPropertyDomainIdByDomainName(String keywords) {
		return propertyDomainDao.getPropertyDomainIdByDomainName(keywords);
	}

}

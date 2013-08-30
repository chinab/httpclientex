package com.tianque.jsoup.service.impl;

import com.tianque.jsoup.dao.PropertyDictDao;
import com.tianque.jsoup.dao.impl.PropertyDictDaoImpl;
import com.tianque.jsoup.service.PropertyDictService;

public class PropertyDictServiceImpl implements PropertyDictService {
	private PropertyDictDao propertyDictDao = new PropertyDictDaoImpl();
	@Override
	public Long findPropertyDictIdByPropertyDomainIdAndDisplayname(Long id,String keywords) {
		return propertyDictDao.findPropertyDictIdByPropertyDomainIdAndDisplayname(id,keywords);
	}


}

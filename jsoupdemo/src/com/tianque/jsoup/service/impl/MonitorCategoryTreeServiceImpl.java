package com.tianque.jsoup.service.impl;

import com.tianque.jsoup.dao.MonitorCategoryTreeDao;
import com.tianque.jsoup.dao.impl.MonitorCategoryTreeDaoImpl;
import com.tianque.jsoup.domain.MonitorCategoryTree;
import com.tianque.jsoup.service.MonitorCategoryTreeService;

public class MonitorCategoryTreeServiceImpl implements
		MonitorCategoryTreeService {
	private MonitorCategoryTreeDao monitorCategoryTreeDao = new MonitorCategoryTreeDaoImpl();
	@Override
	public MonitorCategoryTree getMonitorCategoryTreeByNodeName(String name) {
		return	monitorCategoryTreeDao.getMonitorCategoryTreeByNodeName(name);
	}

}

package com.tianque.jsoup.dao;

import com.tianque.jsoup.domain.MonitorCategoryTree;

public interface MonitorCategoryTreeDao {

	MonitorCategoryTree getMonitorCategoryTreeByNodeName(String name);

}

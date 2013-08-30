package com.tianque.jsoup.service.impl;

import com.tianque.jsoup.dao.MonitoringResultsDao;
import com.tianque.jsoup.dao.impl.MonitoringResultsDaoImpl;
import com.tianque.jsoup.domain.MonitoringResults;
import com.tianque.jsoup.service.MonitoringResultsSerivce;

public class MonitoringResultsSerivceImpl implements MonitoringResultsSerivce {
	MonitoringResultsDao  monitoringResultsDao  =  new MonitoringResultsDaoImpl();
	@Override
	public void addMonitoringResults(MonitoringResults monitoringResult) {
		monitoringResultsDao.addMonitoringResults(monitoringResult);
		
	}

}

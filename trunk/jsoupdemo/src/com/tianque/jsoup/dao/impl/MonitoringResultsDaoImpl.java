package com.tianque.jsoup.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.tianque.jsoup.dao.MonitoringResultsDao;
import com.tianque.jsoup.dao.common.DBManager;
import com.tianque.jsoup.domain.MonitoringResults;

public class MonitoringResultsDaoImpl implements MonitoringResultsDao {
	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	private String sql=null;
	
	@Override
	public void addMonitoringResults(MonitoringResults monitoringResult) {
		sql="insert into monitoringResults(id,monitorCategoryTree,contentSubject,contentUrl,postTime,hotIndex,reprintNumber,hits,receiptNumber,keyWords,createUser,createDate)values("
				+"s_monitoringResults.NEXTVAL,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			conn = DBManager.getConn();
			ps = conn.prepareStatement(sql);
			ps.setLong(1,monitoringResult.getMonitorCategoryTreeID());
			ps.setString(2, monitoringResult.getContentSubject());
			ps.setString(3, monitoringResult.getContentUrl());
			ps.setTimestamp(4,new Timestamp(monitoringResult.getPostTime().getTime()));
			ps.setLong(5, monitoringResult.getHotIndex());
			ps.setLong(6, monitoringResult.getReprintNumber());
			ps.setLong(7, monitoringResult.getHits());
			ps.setLong(8, monitoringResult.getReceiptNumber());
			ps.setLong(9, monitoringResult.getKeyWordsID());
			ps.setString(10,monitoringResult.getCreateUser());
			ps.setTimestamp(11, new Timestamp(monitoringResult.getCreateDate().getTime()));
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBManager.close(rs,ps,conn);
		}

	}

}

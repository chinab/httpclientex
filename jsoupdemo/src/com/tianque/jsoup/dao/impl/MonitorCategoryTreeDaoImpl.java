package com.tianque.jsoup.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.tianque.jsoup.dao.MonitorCategoryTreeDao;
import com.tianque.jsoup.dao.common.DBManager;
import com.tianque.jsoup.domain.MonitorCategoryTree;

public class MonitorCategoryTreeDaoImpl implements MonitorCategoryTreeDao {
	
	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	private String sql=null;
	private MonitorCategoryTree monitorCategoryTree = null;
	@Override
	public MonitorCategoryTree getMonitorCategoryTreeByNodeName(String name) {
			sql="select * from monitorCategoryTree where nodename like ?";
			try {
				conn = DBManager.getConn();
				ps = conn.prepareStatement(sql);
				ps.setString(1, name);
				rs = ps.executeQuery();
				while(rs.next()){
					monitorCategoryTree = new MonitorCategoryTree();
					monitorCategoryTree.setId(rs.getLong("id"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				DBManager.close(rs,ps,conn);
			}
			return monitorCategoryTree;
			
	}


}

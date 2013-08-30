package com.tianque.jsoup.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.tianque.jsoup.dao.PropertyDomainDao;
import com.tianque.jsoup.dao.common.DBManager;

public class PropertyDomainDaoImpl implements PropertyDomainDao {
	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	private String sql=null;
	@Override
	public Long getPropertyDomainIdByDomainName(String keywords) {
		sql="select id from propertydomains where domainname like ?";
		Long id = Long.valueOf("0");
		try {
			conn = DBManager.getConn();
			ps = conn.prepareStatement(sql);
			ps.setString(1, keywords);
			rs = ps.executeQuery();
			while(rs.next()){
			  id = rs.getLong("id");
			}
			return id;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBManager.close(rs,ps,conn);
		}
		return id;
	}

}

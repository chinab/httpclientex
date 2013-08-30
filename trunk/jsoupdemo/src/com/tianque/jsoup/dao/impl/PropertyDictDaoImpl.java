package com.tianque.jsoup.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.tianque.jsoup.dao.PropertyDictDao;
import com.tianque.jsoup.dao.common.DBManager;

public class PropertyDictDaoImpl implements PropertyDictDao {
	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	private String sql=null;
	@Override
	public Long findPropertyDictIdByPropertyDomainIdAndDisplayname(Long id,String keywords) {
		sql="select id from propertydicts where propertydomainid like ? and displayname like ?";
		Long dictId = Long.valueOf("0");
		try {
			conn = DBManager.getConn();
			ps = conn.prepareStatement(sql);
			ps.setLong(1, id);
			ps.setString(2, keywords);
			rs = ps.executeQuery();
			while(rs.next()){
				dictId = rs.getLong("id");
			}
			return dictId;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBManager.close(rs,ps,conn);
		}
		return dictId;
	}

}

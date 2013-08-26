package cn.sckj.jsoup.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cn.sckj.jsoup.exception.MyException;
import cn.sckj.jsoup.pojo.DrugURL;

public class DrugURLDao implements IDrugURLDao {

	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	private String sql=null;
	@Override
	public int insertDrug(DrugURL drugURL) {
			sql=" INSERT INTO drug_url(URL_ID, URL_STR)VALUES(?,?)";
			try {
				conn = DBManager.getConn();
				ps = conn.prepareStatement(sql);
				ps.setString(1, drugURL.getURL_ID());
				ps.setString(2, drugURL.getURL_STR());
				return ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				new MyException("插入DrugURL数据出错!");
			}finally{
				DBManager.close(rs, ps, conn);
			}
			return 0;
	}
}

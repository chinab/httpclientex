package cn.sckj.jsoup.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.PreparedStatement;

import cn.sckj.jsoup.exception.MyException;
import cn.sckj.jsoup.pojo.Drug;

public class DrugDao implements IDrugDao {
	
	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	private String sql=null;
	public int insertDrug(Drug drug) {
		sql="insert into drug(YPMC,CF,SYZ,GG,YFYL,BLFY,JJ,ZYSX,YFYY,"
				+"YWXHZY,YWGL,YLZY,YDDLX,XZ,ZC,YXQ,PZWH,SCQY,YWFL,BZ,ETYY,LNYY,ZXBZ,HZRQ,XGRQ,DRUGID,DLYJ,TYM,WWM,SPM,WZMC,WZDZ)"
				+"values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			conn = DBManager.getConn();
			ps = conn.prepareStatement(sql);
			ps.setString(1,drug.getYPMC());
			ps.setString(2,drug.getCF());
			ps.setString(3,drug.getSYZ());
			ps.setString(4,drug.getGG());
			ps.setString(5,drug.getYFYL());
			ps.setString(6,drug.getBLFY());
			ps.setString(7,drug.getJJ());
			ps.setString(8,drug.getZYSX());
			ps.setString(9,drug.getYFYY());
			ps.setString(10,drug.getYWXHZY());
			ps.setString(11,drug.getYWGL());
			ps.setString(12,drug.getYLZY());
			ps.setString(13,drug.getYDDLX());
			ps.setString(14,drug.getXZ());
			ps.setString(15,drug.getZC());
			ps.setString(16,drug.getYXQ());
			ps.setString(17,drug.getPZWH());
			ps.setString(18,drug.getSCQY());
			ps.setString(19,drug.getYWFL());
			ps.setString(20,drug.getBZ());
			ps.setString(21,drug.getETYY());
			ps.setString(22,drug.getLNYY());
			ps.setString(23,drug.getZXBZ());
			ps.setString(24,drug.getHZRQ());
			ps.setString(25,drug.getXGRQ());
			ps.setString(26,drug.getDRUGID());
			ps.setString(27,drug.getDLYJ());
			ps.setString(28,drug.getTYM());
			ps.setString(29,drug.getWWM());
			ps.setString(30,drug.getSPM());
			ps.setString(31,drug.getWZMC());
			ps.setString(32,drug.getWZDZ());
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			new MyException("插入Drug数据出错!");
		}finally{
			DBManager.close(rs,ps,conn);
		}
		return 0;
	}
	@Override
	public boolean checkURL(String wzdz, String drug_wzmc) {
		sql="select*from drug where WZMC like ? and  WZDZ like ?";
		try {
			conn = DBManager.getConn();
			ps = conn.prepareStatement(sql);
			ps.setString(1, drug_wzmc);
			ps.setString(2, wzdz);
			rs = ps.executeQuery();
			return rs.next();
		}catch (SQLException e) {
			e.printStackTrace();
			new MyException("check数据出错!");
		}finally{
			DBManager.close(rs,ps,conn);
		}
		return false;
	}

	 

}

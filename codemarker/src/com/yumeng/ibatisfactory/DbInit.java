package com.yumeng.ibatisfactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class DbInit {

	public static final String newTableSqlFile = System.getProperty("user.dir")
			+ "/output/newTable.sql";

	public static void main(String[] args) {
		StringBuilder sql = new StringBuilder();
		sql.append("create table user_info (").append("\n");
		sql.append("ID             NUMBER(10) not null,").append("\n");
		sql.append(" USER_NAME      VARCHAR2(30)").append("\n");
		sql.append(")");
		String sqlOutputFile = System.getProperty("user.dir")
				+ "/output/newTable.sql";
		//writeSql(sqlOutputFile, sql.toString());

	}

	public static void deleteNewSqlFile() {
		File file = new File(newTableSqlFile);
		if (file.exists()) {
			file.delete();
		}
		try {
			file.createNewFile();
		} catch (IOException e) {
			System.out.println("新表脚本文件生成失败");
			e.printStackTrace();
		}
	}

	public static void initTable(DataSource dataSource, String tableNames[],
			String tempSqlfileName) {
		Connection con = null;
		Statement st = null;
		try {
			con = dataSource.getConnection();
			st = con.createStatement();
			for (String tableName : tableNames) {
				try {
					st.execute("drop table " + tableName);
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("表" + tableName + "不存在，直接创建");
				}
			}
			List<String> sqls = getSQLs(tempSqlfileName);
			if (sqls != null && sqls.size() > 0) {
				for (String sql : sqls) {
					try {
						st.execute(sql);
					} catch (Exception e) {
						// 可能因为表已经删除执行报错，此种情况继续执行接下来的sql
						// e.printStackTrace();
						continue;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (st != null) {
					st.close();
				}
			} catch (Exception e) {
			}
			try {
				if (con != null) {
					con.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public static List<String> getSQLs(String tempSqlfileName) {
		String filePath = System.getProperty("user.dir") + "/"
				+ tempSqlfileName;
		System.out.println(filePath);
		List<String> sqlList = new ArrayList<String>();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(filePath));
			StringBuilder tmpSql = new StringBuilder();
			String sqlTemp = "";
			while ((sqlTemp = reader.readLine()) != null) {
				if (sqlTemp != null && !"".equals(sqlTemp.trim())) {
					if (sqlTemp.lastIndexOf(";") > 0) {
						tmpSql.append(sqlTemp.substring(0, sqlTemp
								.lastIndexOf(";")));
						sqlList.add(tmpSql.toString());
						tmpSql = new StringBuilder();
					} else {
						tmpSql.append(sqlTemp);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sqlList;
	}

}

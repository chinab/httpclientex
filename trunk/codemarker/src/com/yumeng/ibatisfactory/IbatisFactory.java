package com.yumeng.ibatisfactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;

public class IbatisFactory {
	public static void main(String[] ax) {

		ApplicationContext context = new FileSystemXmlApplicationContext(System.getProperty("user.dir") + "/" + "ibatorfactoryconfig.xml");
		DataSource dataSource = (DataSource) context.getBean("dataSource");
		TypeMap typeMap = (TypeMap) context.getBean("typeMap");

		try {
			// 设置模版
			Configuration cfg = new Configuration();
			cfg.setDirectoryForTemplateLoading(new File(System.getProperty("user.dir")));
			cfg.setObjectWrapper(new DefaultObjectWrapper());

			String[] tableNames = IbatorFactoryParams.TABLES.split(",");
			//初始化sql脚本
			DbInit.initTable(dataSource,tableNames,"temp.sql");
			for (String tableName : tableNames) {
				Table table = new Table(tableName, IbatorFactoryParams.PACKAGE);
				String sql = typeMap.getSql();
				sql = sql.replace("{tableName}", tableName);
				Statement sta = dataSource.getConnection().createStatement();
				ResultSet rs = sta.executeQuery(sql);
				while (rs.next()) {
					TableField field = new TableField(rs.getString("name"), rs.getString("datatype"), rs.getInt("precision"), rs.getInt("scale"), rs
							.getString("remark"), typeMap);
					table.getFields().add(field);
				}

				Map<String, Object> root = new HashMap<String, Object>();
				root.put("table", table);

				// bean 输出
				for (String template : IbatorFactoryParams.TEMPLATESLIST) {
					String[] params = template.split(",");
					String tempFile = params[0];
					String output = System.getProperty("user.dir") + "/" + params[1];
					if (params[2].equals("sqlmap"))
						output = output + "/" + table.getClassName() + ".xml";
					else if (params[2].equals("Dsqlmap"))
						output = output + "/D" + table.getClassName() + ".xml";
					else if (params[2].equals("domain"))
						output = output + "/" + table.getClassName() + ".java";
					else if (params[2].equals("dao"))
						output = output + "/" + table.getClassName() + "Dao.java";
					else if (params[2].equals("daoImpl"))
						output = output + "/" + table.getClassName() + "DaoImpl.java";
					else if (params[2].equals("service"))
						output = output + "/" + table.getClassName() + "Service.java";
					else if (params[2].equals("serviceImpl"))
						output = output + "/" + table.getClassName() + "ServiceImpl.java";
					else
						output = output + "/" + table.getTableName() + params[2];
					generator(tempFile, output, root);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	static private void generator(String templatePath, String outputPath, Map<String, Object> root) {
		// 设置模版
		try {
			Configuration cfg = new Configuration();
			cfg.setDirectoryForTemplateLoading(new File(System.getProperty("user.dir")+"/template"));
			cfg.setObjectWrapper(new DefaultObjectWrapper());
			Template temp = cfg.getTemplate(templatePath);
			String daoPath = outputPath;
			File daoFile = new File(daoPath);
			FileOutputStream daoWriter = new FileOutputStream(daoFile);
			Writer daoOut = new OutputStreamWriter(daoWriter);
			temp.process(root, daoOut);
			daoOut.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

package cn.sckj.jsoup.dao;

import cn.sckj.jsoup.pojo.Drug;

public interface IDrugDao {
	int insertDrug(Drug drug);

	boolean checkURL(String wzdz, String drug_wzmc);
}

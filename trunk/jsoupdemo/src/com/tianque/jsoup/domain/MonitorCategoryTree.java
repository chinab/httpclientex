package com.tianque.jsoup.domain;

import java.util.Date;


public class MonitorCategoryTree {
	private Long id;
	private Long fatherNodeID;
	private Long subNodeCount;
	private Long treeLevel;
	private Long nodeSEQ;
	private String nodeName;
	private String nodeInternalCode;
	private String simplepinyin;
	private String fullpinyin;
	private String remark;
	private String createUser;
	private String updateUser;
	private Date createDate;
	private Date updateDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getFatherNodeID() {
		return fatherNodeID;
	}

	public void setFatherNodeID(Long fatherNodeID) {
		this.fatherNodeID = fatherNodeID;
	}

	public Long getSubNodeCount() {
		return subNodeCount;
	}

	public void setSubNodeCount(Long subNodeCount) {
		this.subNodeCount = subNodeCount;
	}

	public Long getTreeLevel() {
		return treeLevel;
	}

	public void setTreeLevel(Long treeLevel) {
		this.treeLevel = treeLevel;
	}

	public Long getNodeSEQ() {
		return nodeSEQ;
	}

	public void setNodeSEQ(Long nodeSEQ) {
		this.nodeSEQ = nodeSEQ;
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public String getNodeInternalCode() {
		return nodeInternalCode;
	}

	public void setNodeInternalCode(String nodeInternalCode) {
		this.nodeInternalCode = nodeInternalCode;
	}

	public String getSimplepinyin() {
		return simplepinyin;
	}

	public void setSimplepinyin(String simplepinyin) {
		this.simplepinyin = simplepinyin;
	}

	public String getFullpinyin() {
		return fullpinyin;
	}

	public void setFullpinyin(String fullpinyin) {
		this.fullpinyin = fullpinyin;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

}

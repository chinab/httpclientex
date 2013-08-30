package com.tianque.jsoup.domain;

import java.util.Date;

public class MonitoringResults{
	private Long id;
	private String createUser;
	private Date createDate;
	private String updateUser;
	private Date updateDate;
	private Long monitorCategoryTreeID;
	private String contentSubject;
	private String contentUrl;
	private Date postTime;
	private Long hotIndex;
	private Long reprintNumber;
	private Long hits;
	private Long receiptNumber;
	private Long keyWordsID;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Long getMonitorCategoryTreeID() {
		return monitorCategoryTreeID;
	}

	public void setMonitorCategoryTreeID(Long monitorCategoryTreeID) {
		this.monitorCategoryTreeID = monitorCategoryTreeID;
	}

	public Long getKeyWordsID() {
		return keyWordsID;
	}

	public void setKeyWordsID(Long keyWordsID) {
		this.keyWordsID = keyWordsID;
	}

	public String getContentSubject() {
		return contentSubject;
	}

	public void setContentSubject(String contentSubject) {
		this.contentSubject = contentSubject;
	}

	public String getContentUrl() {
		return contentUrl;
	}

	public void setContentUrl(String contentUrl) {
		this.contentUrl = contentUrl;
	}

	public Date getPostTime() {
		return postTime;
	}

	public void setPostTime(Date postTime) {
		this.postTime = postTime;
	}

	public Long getHotIndex() {
		return hotIndex;
	}

	public void setHotIndex(Long hotIndex) {
		this.hotIndex = hotIndex;
	}

	public Long getReprintNumber() {
		return reprintNumber;
	}

	public void setReprintNumber(Long reprintNumber) {
		this.reprintNumber = reprintNumber;
	}

	public Long getHits() {
		return hits;
	}

	public void setHits(Long hits) {
		this.hits = hits;
	}

	public Long getReceiptNumber() {
		return receiptNumber;
	}

	public void setReceiptNumber(Long receiptNumber) {
		this.receiptNumber = receiptNumber;
	}

}

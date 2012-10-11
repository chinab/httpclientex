package com.demo.request.vo;

import java.io.Serializable;


abstract public class BaseDomain implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String createUser;
	private String createDate;
	private String sortField;
	private String order;

	public String getSortField() {
		return sortField;
	}

	public void setSortField(String sortField) {
		this.sortField = sortField;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

 	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

 	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	private String updateUser;
	private String updateDate;

	@Override
	public int hashCode() {
		if (id != null){
			final int prime = 31;
			int result = 1;
			result = prime * (prime *result+getClass().hashCode()) + id.hashCode();
			return result;
		}
		return super.hashCode();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof BaseDomain)) {
			return false;
		}

		if ((getClass().isAssignableFrom(obj.getClass()))
				|| (obj.getClass().isAssignableFrom(getClass()))) {

		} else {
			return false;
		}

		BaseDomain other = (BaseDomain) obj;
		if (other.getId() == null || getId() == null) {
			return false;
		} else {
			if (other.getId().equals(getId())) {
				return true;
			} else {
				return false;
			}
		}
	}

}

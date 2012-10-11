package com.demo.request.vo;

@SuppressWarnings("serial")
public class Organization extends BaseDomain {
	public Organization(Long id){
		setId(id);
	}
	public Organization(){
	}
	
	private Long orgTerritory=0L;
	public Long getOrgTerritory() {
		return orgTerritory;
	}
	public void setOrgTerritory(Long orgTerritory) {
		this.orgTerritory = orgTerritory;
	}

	private String orgName;
	private String contactWay;
	private String departmentNo;
	

	/**
	 * 组织结构内置代码，对于外部不暴露
	 */
	private String orgInternalCode="0001";
	/**
	 * 父组织结�?
	 */
	private Organization parentOrg;
	/**
	 * �?��职能部门
	 */
	private Organization parentFunOrg;
	/**
	 * 子组织结构分配到�?��的编码，依然是内部，不对外暴�?
	 */
	
	private String fullPinyin;
	private String simplePinyin;
	private int maxCode = 0;
	private String createUser;
	private String createDate;
	private String updateUser;
	private String remark;
	private String updateDate;
	private Long subCount;
	private Long seq;
	private Long subCountFun;

	
	
	public String getDepartmentNo() {
		return departmentNo;
	}

	public void setDepartmentNo(String departmentNo) {
		this.departmentNo = departmentNo;
	}

	public Long getSeq() {
		return seq;
	}

	public void setSeq(Long seq) {
		this.seq = seq;
	}

	public Long getSubCount() {
		return subCount;
	}

	public void setSubCount(Long subCount) {
		this.subCount = subCount;
	}

	public Organization getParentOrg() {
		return parentOrg;
	}

	public void setParentOrg(Organization parentOrg) {
		this.parentOrg = parentOrg;
	}

	public String getOrgInternalCode() {
		return orgInternalCode;
	}

	public void setOrgInternalCode(String orgInternalCode) {
		this.orgInternalCode = orgInternalCode;
	}

	public int getMaxCode() {
		return maxCode;
	}

	public void setMaxCode(int maxCode) {
		this.maxCode = maxCode;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getContactWay() {
		return contactWay;
	}

	public void setContactWay(String contactWay) {
		this.contactWay = contactWay;
	}

	public String getFullPinyin() {
		return fullPinyin;
	}

	public void setFullPinyin(String fullPinyin) {
		if (fullPinyin != null && fullPinyin.length() > 30) {
			fullPinyin = fullPinyin.substring(0, 30);
		}
		this.fullPinyin = fullPinyin;
	}

	public String getSimplePinyin() {
		return simplePinyin;
	}

	public void setSimplePinyin(String simplePinyin) {
		if (simplePinyin != null && simplePinyin.length() > 10) {
			simplePinyin = simplePinyin.substring(0, 10);
		}
		this.simplePinyin = simplePinyin;
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

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}


	public Organization getParentFunOrg() {
		return parentFunOrg;
	}

	public void setParentFunOrg(Organization parentFunOrg) {
		this.parentFunOrg = parentFunOrg;
	}
	public Long getSubCountFun() {
		return subCountFun;
	}

	public void setSubCountFun(Long subCountFun) {
		this.subCountFun = subCountFun;
	}
}

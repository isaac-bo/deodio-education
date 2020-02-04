package com.deodio.elearning.persistence.model.customize;

public class UserRegisterBo{

	private String userId;
	
	private String userName;
	
	private String password;
	
	private String companyIndustry;
	
	private short registerType;
	
	private String createId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCompanyIndustry() {
		return companyIndustry;
	}

	public void setCompanyIndustry(String companyIndustry) {
		this.companyIndustry = companyIndustry;
	}

	public short getRegisterType() {
		return registerType;
	}

	public void setRegisterType(short registerType) {
		this.registerType = registerType;
	}

	public String getCreateId() {
		return createId;
	}

	public void setCreateId(String createId) {
		this.createId = createId;
	}

	@Override
	public String toString() {
		return "UserRegisterBo [userId=" + userId + ", userName=" + userName + ", password=" + password
				+ ", companyIndustry=" + companyIndustry + ", registerType=" + registerType + ", createId=" + createId
				+ "]";
	}
	
} 

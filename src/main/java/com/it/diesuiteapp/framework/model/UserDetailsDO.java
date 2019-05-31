package com.it.diesuiteapp.framework.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="USER_DETAILS")
public class UserDetailsDO {
	@Id
	@Column(name="USER_ID",nullable=false)
	private long userId;
	@Column(name = "PWD", nullable = false)
	private String passCode;
	@Column(name = "USER_EMAIL", nullable = false)
	private String userEmail;
	
	@Column(name = "USER_NAME", nullable = false)
	private String userName;
	
	@Column(name = "USER_ADDRESS", nullable = false)
	private String userAddress;
	@Column(name = "USER_MOBILE", nullable = false)
	private String userMobile;
	@Column(name = "CREATED_BY", nullable = true)
	private long createdBy;
	@Column(name = "CREATED_DATE", nullable = false)
	private long createdDate;
	@Column(name = "MODIFIED_BY", nullable = true)
	private long modifiedBy;

	@Column(name = "MODIFIED_DATE", nullable = true)
	private long modifiedDate;
	@Column(name = "VERSION", nullable = false)
	private int version;
	@Column(name = "STATUS", nullable = false)
	private int status;
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Column(name = "DELETED", nullable = false)
	private int deleted;
	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getPassCode() {
		return passCode;
	}

	public void setPassCode(String passCode) {
		this.passCode = passCode;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public String getUsetrMobile() {
		return userMobile;
	}

	public void setUsetrMobile(String usetrMobile) {
		this.userMobile = usetrMobile;
	}

	public long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(long createdBy) {
		this.createdBy = createdBy;
	}

	public long getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(long createdDate) {
		this.createdDate = createdDate;
	}

	public long getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public long getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(long modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public int getDeleted() {
		return deleted;
	}

	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}

	

	
	

}


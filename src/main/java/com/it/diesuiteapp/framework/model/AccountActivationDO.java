package com.it.diesuiteapp.framework.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ACCOUNT_ACTIVATION")
public class AccountActivationDO {
	@Id
	@Column(name = "ADMIN_ID", nullable = false)
	private long adminId;

	@Column(name = "ACTIVATION_CODE", nullable = false)
	private String activationCode;

	@Column(name = "REQUEST_TYPE", nullable = false)
	private int requestType;

	@Column(name = "CREATED_DATE", nullable = true)
	private long createdDate;
	public String getActivationCode() {
		return activationCode;
	}

	public long getAdminId() {
		return adminId;
	}

	public void setAdminId(long adminId) {
		this.adminId = adminId;
	}

	public void setActivationCode(String activationCode) {
		this.activationCode = activationCode;
	}

	public int getRequestType() {
		return requestType;
	}

	public void setRequestType(int requestType) {
		this.requestType = requestType;
	}

	public long getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(long createdDate) {
		this.createdDate = createdDate;
	}
}

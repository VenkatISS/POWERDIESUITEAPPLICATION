package com.it.diesuiteapp.framework.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ADMIN_DETAILS")
public class AdminDetailsDO {
	@Column(name = "ADMIN_NAME", nullable = false)
	private String adminName;

	@Column(name = "ADMIN_MOBILE", nullable = false)
	private String adminMobile;
	@Column(name = "GSTIN_NO", nullable = true)
	private String gstin;
	@Column(name = "ADMIN_ST_OR_UT", nullable = false)
	private int state;
	@Id
	@Column(name = "CREATED_BY", nullable = true)
	private long createdBy;
	@Column(name = "EFFECTIVE_DATE", nullable = true)
	private long effectiveDate;
	@Column(name = "OFFICE_LANDLINE", nullable = true)
	private long ofcLandline;
	@Column(name = "ADMIN_ADDRESS", nullable = true)
	private String adminAddress;
	@Column(name = "CREATED_DATE", nullable = false)
	private long createdDate;
	@Column(name = "MODIFIED_BY", nullable = true)
	private long modifiedBy;

	public String getAdminAddress() {
		return adminAddress;
	}

	public void setAdminAddress(String adminAddress) {
		this.adminAddress = adminAddress;
	}

	@Column(name = "MODIFIED_DATE", nullable = true)
	private long modifiedDate;
	@Column(name = "VERSION", nullable = false)
	private int version;

	@Column(name = "DELETED", nullable = false)
	private int deleted;

	public long getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(long effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getAdminMobile() {
		return adminMobile;
	}

	public void setAdminMobile(String adminMobile) {
		this.adminMobile = adminMobile;
	}

	public String getGstin() {
		return gstin;
	}

	public void setGstin(String gstin) {
		this.gstin = gstin;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(long createdBy) {
		this.createdBy = createdBy;
	}

	public long getOfcLandline() {
		return ofcLandline;
	}

	public void setOfcLandline(long ofcLandline) {
		this.ofcLandline = ofcLandline;
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

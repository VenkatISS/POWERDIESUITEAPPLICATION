package com.it.diesuiteapp.framework.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PRODUCT_DATA")
public class ProductDataDO {

	@Id
	@GeneratedValue
	private long id;
	private int prod_code;
	//private String prod_code;

	private int rawmat_code;
	private int unit_type;
	private int units;
	private String gstp;
//	private String security_deposit;
	private int opening_stock;
	private int current_stock;
	private String purchase_price;
//	private String selling_rate;
	private long effective_date;
	private long created_by;
	private long created_date;
	private long modified_by;
	private long modified_date;
	private int version;
	private int deleted;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	
	public int getProd_code() {
		return prod_code;
	}
	public void setProd_code(int prod_code) {
		this.prod_code = prod_code;
	}
	public int getRawmat_code() {
		return rawmat_code;
	}
	public void setRawmat_code(int rawmat_code) {
		this.rawmat_code = rawmat_code;
	}
	public int getUnit_type() {
		return unit_type;
	}
	public void setUnit_type(int unit_type) {
		this.unit_type = unit_type;
	}
	public int getUnits() {
		return units;
	}
	public void setUnits(int units) {
		this.units = units;
	}
	public String getGstp() {
		return gstp;
	}
	public void setGstp(String gstp) {
		this.gstp = gstp;
	}
	
	public int getOpening_stock() {
		return opening_stock;
	}
	public void setOpening_stock(int opening_stock) {
		this.opening_stock = opening_stock;
	}
	public int getCurrent_stock() {
		return current_stock;
	}
	public void setCurrent_stock(int current_stock) {
		this.current_stock = current_stock;
	}
	public String getPurchase_price() {
		return purchase_price;
	}
	public void setPurchase_price(String purchase_price) {
		this.purchase_price = purchase_price;
	}
	public long getEffective_date() {
		return effective_date;
	}
	public void setEffective_date(long effective_date) {
		this.effective_date = effective_date;
	}
	public long getCreated_by() {
		return created_by;
	}
	public void setCreated_by(long created_by) {
		this.created_by = created_by;
	}
	public long getCreated_date() {
		return created_date;
	}
	public void setCreated_date(long created_date) {
		this.created_date = created_date;
	}
	public long getModified_by() {
		return modified_by;
	}
	public void setModified_by(long modified_by) {
		this.modified_by = modified_by;
	}
	public long getModified_date() {
		return modified_date;
	}
	public void setModified_date(long modified_date) {
		this.modified_date = modified_date;
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

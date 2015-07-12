package com.org.bookur.ads.ws.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="tb_sub_category")
public class SubCategory {
	@Id
	@Column(name="sub_category_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer subCategoryID;
	
	@ManyToOne
	@JoinColumn(name="category_id")
	private Category category;
	
	@Column(name="sub_category_name")
	private String subCategoryName;
	
	@Column(name="sub_category_desc")
	private String subCategoryDesc;
	
	@Column(name="is_active")
	private Integer activeFlag;

	public Integer getSubCategoryID() {
		return subCategoryID;
	}

	public void setSubCategoryID(Integer subCategoryID) {
		this.subCategoryID = subCategoryID;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getSubCategoryName() {
		return subCategoryName;
	}

	public void setSubCategoryName(String subCategoryName) {
		this.subCategoryName = subCategoryName;
	}

	public String getSubCategoryDesc() {
		return subCategoryDesc;
	}

	public void setSubCategoryDesc(String subCategoryDesc) {
		this.subCategoryDesc = subCategoryDesc;
	}

	public Integer getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(Integer activeFlag) {
		this.activeFlag = activeFlag;
	}
}

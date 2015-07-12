/**
 * 
 */
package com.org.bookur.ads.ws.data.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author 355393
 *
 */
@JsonIgnoreProperties({"licenseNumber"})
@Entity
@Table(name="tb_user")
public class Advertisment {
	@Id
	@Column(name="advertisment_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer addID;
	
	@OneToOne
    @JoinColumn(name="sub_category_id")
	private SubCategory subCategory;
	
	@Column(name="pic_count")
	private Integer picCount;
	
	@OneToOne
    @JoinColumn(name="user_id")
	private User user;
	
	@Column(name="created_on")
	private Date createdOn;
	
	@Column(name="available_from")
	private Date availableFrom;
	
	@Column(name="updated_date")
	private Date updatedDate;
	
	@Column(name="is_deleted")
	private Integer deletedFlag;
	
	@Column(name="reason_for_deletion")
	private String deleteNote;
	
	@Column(name="deleted_on",insertable=false,updatable=false)
	private Date deletedOn;
	
	@Column(name="seller_price")
	private String sellerPrice;
	
	@Column(name="percentage_discount")
	private String discountPercentage;
	
	@Column(name="flat_discount")
	private String discountFlat;
	
	@Column(name="length")
	private Integer length;
	
	@Column(name="breadth")
	private Integer breadth;
	
	@Column(name="height")
	private Integer height;
	
	@Column(name="height_from_ground")
	private Integer heightFromGround;
	
	@Column(name="near_by_land_mark")
	private String nearByLandMark;
	
	@Column(name="max_views_per_day")
	private Integer maxviews;
	
	@Column(name="license_number")
	private String licenseNumber;
	
	@Column(name="is_license_verified")
	private boolean licenseVerifired;
	
	@Column(name="is_site_verified")
	private boolean siteVerified;

	public Integer getAddID() {
		return addID;
	}

	public void setAddID(Integer addID) {
		this.addID = addID;
	}

	public SubCategory getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(SubCategory subCategory) {
		this.subCategory = subCategory;
	}

	public Integer getPicCount() {
		return picCount;
	}

	public void setPicCount(Integer picCount) {
		this.picCount = picCount;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Date getAvailableFrom() {
		return availableFrom;
	}

	public void setAvailableFrom(Date availableFrom) {
		this.availableFrom = availableFrom;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Integer getDeletedFlag() {
		return deletedFlag;
	}

	public void setDeletedFlag(Integer deletedFlag) {
		this.deletedFlag = deletedFlag;
	}

	public String getDeleteNote() {
		return deleteNote;
	}

	public void setDeleteNote(String deleteNote) {
		this.deleteNote = deleteNote;
	}

	public Date getDeletedOn() {
		return deletedOn;
	}

	public void setDeletedOn(Date deletedOn) {
		this.deletedOn = deletedOn;
	}

	public String getSellerPrice() {
		return sellerPrice;
	}

	public void setSellerPrice(String sellerPrice) {
		this.sellerPrice = sellerPrice;
	}

	public String getDiscountPercentage() {
		return discountPercentage;
	}

	public void setDiscountPercentage(String discountPercentage) {
		this.discountPercentage = discountPercentage;
	}

	public String getDiscountFlat() {
		return discountFlat;
	}

	public void setDiscountFlat(String discountFlat) {
		this.discountFlat = discountFlat;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public Integer getBreadth() {
		return breadth;
	}

	public void setBreadth(Integer breadth) {
		this.breadth = breadth;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public Integer getHeightFromGround() {
		return heightFromGround;
	}

	public void setHeightFromGround(Integer heightFromGround) {
		this.heightFromGround = heightFromGround;
	}

	public String getNearByLandMark() {
		return nearByLandMark;
	}

	public void setNearByLandMark(String nearByLandMark) {
		this.nearByLandMark = nearByLandMark;
	}

	public Integer getMaxviews() {
		return maxviews;
	}

	public void setMaxviews(Integer maxviews) {
		this.maxviews = maxviews;
	}

	public String getLicenseNumber() {
		return licenseNumber;
	}

	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}

	public boolean isLicenseVerifired() {
		return licenseVerifired;
	}

	public void setLicenseVerifired(boolean licenseVerifired) {
		this.licenseVerifired = licenseVerifired;
	}

	public boolean isSiteVerified() {
		return siteVerified;
	}

	public void setSiteVerified(boolean siteVerified) {
		this.siteVerified = siteVerified;
	}


}

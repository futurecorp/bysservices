package com.org.bookur.ads.ws.data.entity;

import java.util.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * This table is used to store the user information.
 * @author 355393
 *  
 *
 */
@JsonIgnoreProperties({"password","createdOn" })
@Entity
@Table(name="tb_user")
public class User {
	@Id
	@Column(name="user_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userID;
	
	@OneToOne
    @JoinColumn(name="role_id")
	private Role role;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="middle_name")
	private String middleName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="dob")
	private Date dob;
	
	@Column(name="sex")
	private String sex;
	
	@Column(name="username")
	private String userName;
	
	@Column(name="password")
	private String password;
	
	@OneToOne
    @JoinColumn(name="address_id")
	private Address address;
	
	@Column(name="created_on",insertable=false,updatable=false)
	private Date createdOn;
	
	@Column(name="updated_on")
	private Date updatedOn;
	
	@Column(name="incorrect_pwd_attempt")
	private int incorrectPWDAttempt;
	
	@Column(name="blocked_flag")
	private int blocked;

	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
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

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Date getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	public int getIncorrectPWDAttempt() {
		return incorrectPWDAttempt;
	}

	public void setIncorrectPWDAttempt(int incorrectPWDAttempt) {
		this.incorrectPWDAttempt = incorrectPWDAttempt;
	}

	public int getBlocked() {
		return blocked;
	}

	public void setBlocked(int blocked) {
		this.blocked = blocked;
	}
}

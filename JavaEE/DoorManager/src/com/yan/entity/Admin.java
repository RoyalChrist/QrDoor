package com.yan.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Admin entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "admin", catalog = "door")
public class Admin implements java.io.Serializable {

	// Fields

	private Integer id;
	private String loginName;
	private String realName;
	private String mobilephone;
	private String password;

	// Constructors

	/** default constructor */
	public Admin() {
	}

	/** minimal constructor */
	public Admin(String loginName, String realName) {
		this.loginName = loginName;
		this.realName = realName;
	}

	/** full constructor */
	public Admin(String loginName, String realName, String mobilephone,
			String password) {
		this.loginName = loginName;
		this.realName = realName;
		this.mobilephone = mobilephone;
		this.password = password;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "_id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "loginName", nullable = false, length = 50)
	public String getLoginName() {
		return this.loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	@Column(name = "realName", nullable = false, length = 50)
	public String getRealName() {
		return this.realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	@Column(name = "mobilephone", length = 50)
	public String getMobilephone() {
		return this.mobilephone;
	}

	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
	}

	@Column(name = "password", length = 50)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
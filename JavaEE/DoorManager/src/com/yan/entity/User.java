package com.yan.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * User entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "user", catalog = "door")
public class User implements java.io.Serializable {

	// Fields

	private Integer id;
	private String loginName;
	private String realName;
	private String mobilephone;
	private String password;
	private String imei;
	private Set<Relationship> relationships = new HashSet<Relationship>(0);

	// Constructors

	/** default constructor */
	public User() {
	}

	/** full constructor */
	public User(String loginName, String realName, String mobilephone,
			String password, String imei, Set<Relationship> relationships) {
		this.loginName = loginName;
		this.realName = realName;
		this.mobilephone = mobilephone;
		this.password = password;
		this.imei = imei;
		this.relationships = relationships;
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

	@Column(name = "loginName", length = 50)
	public String getLoginName() {
		return this.loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	@Column(name = "realName", length = 50)
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

	@Column(name = "password", length = 100)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "IMEI", length = 50)
	public String getImei() {
		return this.imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
	public Set<Relationship> getRelationships() {
		return this.relationships;
	}

	public void setRelationships(Set<Relationship> relationships) {
		this.relationships = relationships;
	}

}
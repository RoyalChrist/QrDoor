package com.yan.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Qrcode entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "qrcode", catalog = "door")
public class Qrcode implements java.io.Serializable {

	// Fields

	private Integer id;
	private Door door;
	private String code;
	private Integer randnumber;
	private Timestamp createTime;
	private Timestamp expTime;
	private Integer isUsed;
	private String userScan;

	// Constructors

	/** default constructor */
	public Qrcode() {
	}

	/** minimal constructor */
	public Qrcode(Door door, String code) {
		this.door = door;
		this.code = code;
	}

	/** full constructor */
	public Qrcode(Door door, String code, Integer randnumber,
			Timestamp createTime, Timestamp expTime, Integer isUsed,
			String userScan) {
		this.door = door;
		this.code = code;
		this.randnumber = randnumber;
		this.createTime = createTime;
		this.expTime = expTime;
		this.isUsed = isUsed;
		this.userScan = userScan;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "doorId", nullable = false)
	public Door getDoor() {
		return this.door;
	}

	public void setDoor(Door door) {
		this.door = door;
	}

	@Column(name = "code", nullable = false, length = 100)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "randnumber")
	public Integer getRandnumber() {
		return this.randnumber;
	}

	public void setRandnumber(Integer randnumber) {
		this.randnumber = randnumber;
	}

	@Column(name = "createTime", length = 19)
	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	@Column(name = "expTime", length = 19)
	public Timestamp getExpTime() {
		return this.expTime;
	}

	public void setExpTime(Timestamp expTime) {
		this.expTime = expTime;
	}

	@Column(name = "isUsed")
	public Integer getIsUsed() {
		return this.isUsed;
	}

	public void setIsUsed(Integer isUsed) {
		this.isUsed = isUsed;
	}

	@Column(name = "userScan", length = 50)
	public String getUserScan() {
		return this.userScan;
	}

	public void setUserScan(String userScan) {
		this.userScan = userScan;
	}

}
package com.yan.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Log entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "log", catalog = "door")
public class Log implements java.io.Serializable {

	// Fields

	private Integer id;
	private String doorNumber;
	private String loginName;
	private String realName;
	private String record;
	private Timestamp time;

	// Constructors

	/** default constructor */
	public Log() {
	}

	/** minimal constructor */
	public Log(Timestamp time) {
		this.time = time;
	}

	/** full constructor */
	public Log(String doorNumber, String loginName, String realName,
			String record, Timestamp time) {
		this.doorNumber = doorNumber;
		this.loginName = loginName;
		this.realName = realName;
		this.record = record;
		this.time = time;
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

	@Column(name = "doorNumber", length = 20)
	public String getDoorNumber() {
		return this.doorNumber;
	}

	public void setDoorNumber(String doorNumber) {
		this.doorNumber = doorNumber;
	}

	@Column(name = "loginName", length = 20)
	public String getLoginName() {
		return this.loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	@Column(name = "realName", length = 20)
	public String getRealName() {
		return this.realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	@Column(name = "record", length = 100)
	public String getRecord() {
		return this.record;
	}

	public void setRecord(String record) {
		this.record = record;
	}

	@Column(name = "time", nullable = false, length = 19)
	public Timestamp getTime() {
		return this.time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

}
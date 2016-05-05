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
 * Door entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "door", catalog = "door")
public class Door implements java.io.Serializable {

	// Fields

	private Integer id;
	private String doorNumber;
	private String openCode;
	private String nodeNumber;
	private String ip;
	private String port;
	private String info;
	private Set<Relationship> relationships = new HashSet<Relationship>(0);
	private Set<Qrcode> qrcodes = new HashSet<Qrcode>(0);

	// Constructors

	/** default constructor */
	public Door() {
	}

	/** full constructor */
	public Door(String doorNumber, String openCode, String nodeNumber,
			String ip, String port, String info,
			Set<Relationship> relationships, Set<Qrcode> qrcodes) {
		this.doorNumber = doorNumber;
		this.openCode = openCode;
		this.nodeNumber = nodeNumber;
		this.ip = ip;
		this.port = port;
		this.info = info;
		this.relationships = relationships;
		this.qrcodes = qrcodes;
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

	@Column(name = "openCode", length = 100)
	public String getOpenCode() {
		return this.openCode;
	}

	public void setOpenCode(String openCode) {
		this.openCode = openCode;
	}

	@Column(name = "nodeNumber", length = 20)
	public String getNodeNumber() {
		return this.nodeNumber;
	}

	public void setNodeNumber(String nodeNumber) {
		this.nodeNumber = nodeNumber;
	}

	@Column(name = "ip", length = 50)
	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	@Column(name = "port", length = 50)
	public String getPort() {
		return this.port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	@Column(name = "info", length = 65535)
	public String getInfo() {
		return this.info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "door")
	public Set<Relationship> getRelationships() {
		return this.relationships;
	}

	public void setRelationships(Set<Relationship> relationships) {
		this.relationships = relationships;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "door")
	public Set<Qrcode> getQrcodes() {
		return this.qrcodes;
	}

	public void setQrcodes(Set<Qrcode> qrcodes) {
		this.qrcodes = qrcodes;
	}

}
package com.yan.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Relationship entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "relationship", catalog = "door")
public class Relationship implements java.io.Serializable {

	// Fields

	private Integer id;
	private User user;
	private Door door;
	private String info;

	// Constructors

	/** default constructor */
	public Relationship() {
	}

	/** full constructor */
	public Relationship(User user, Door door, String info) {
		this.user = user;
		this.door = door;
		this.info = info;
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
	@JoinColumn(name = "userId")
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "doorId")
	public Door getDoor() {
		return this.door;
	}

	public void setDoor(Door door) {
		this.door = door;
	}

	@Column(name = "info", length = 100)
	public String getInfo() {
		return this.info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

}
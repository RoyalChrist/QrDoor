package com.yan.service;

import java.util.List;

import com.yan.entity.Relationship;
import com.yan.utils.PageBean;

public interface IRelationshipService {

	public abstract void save(Relationship transientInstance);

	public abstract void delete(Relationship persistentInstance);

	public abstract PageBean findAll(int currentPage, int pageSize, String sort,
			String order);

	public abstract PageBean findAll(int currentPage, int pageSize, String param,
			String sort, String order);

	public abstract PageBean findbyloginName(int currentPage, int pageSize,
			String loginName, String sort, String order);

	public abstract PageBean findbydoorNumber(int currentPage, int pageSize,
			String doorNumber, String sort, String order);

	public abstract List findAll();

	public abstract void attachDirty(Relationship instance);

	public abstract Relationship findById(java.lang.Integer id);

}
package com.yan.dao;

import java.util.List;

import com.yan.entity.Relationship;
import com.yan.utils.PageBean;

public interface IRelationshipDAO {

	public abstract void save(Relationship transientInstance);

	public abstract void delete(Relationship persistentInstance);

	public abstract PageBean findAll(PageBean pageBean, String sort,
			String order);

	public abstract PageBean findAll(PageBean pageBean, String param,
			String sort, String order);

	public abstract PageBean findbyloginName(PageBean pageBean,
			String loginName, String sort, String order);

	public abstract PageBean findbydoorNumber(PageBean pageBean,
			String doorNumber, String sort, String order);

	public abstract List findAll();

	public abstract void attachDirty(Relationship instance);
	
	public abstract Relationship findById(java.lang.Integer id);

}
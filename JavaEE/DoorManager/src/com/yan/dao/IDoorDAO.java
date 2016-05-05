package com.yan.dao;

import java.util.List;

import com.yan.entity.Door;
import com.yan.utils.PageBean;

public interface IDoorDAO {
	
	public abstract PageBean findAll(PageBean pageBean, String sort,
			String order);

	public abstract PageBean findAll(PageBean pageBean, String param,
			String sort, String order);

	public abstract void save(Door transientInstance);

	public abstract void delete(Door persistentInstance);

	public abstract Door findById(java.lang.Integer id);

	public abstract List findAll();

	public abstract void attachDirty(Door instance);

}
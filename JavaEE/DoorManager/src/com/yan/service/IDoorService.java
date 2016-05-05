package com.yan.service;

import java.util.List;

import com.yan.entity.Door;
import com.yan.utils.PageBean;

public interface IDoorService {
	
	public abstract PageBean findAll(int currentPage, int pageSize, String sort,
			String order);

	public abstract PageBean findAll(int currentPage, int pageSize, String param,
			String sort, String order);

	public abstract void save(Door transientInstance);

	public abstract void delete(Door persistentInstance);

	public abstract Door findById(java.lang.Integer id);

	public abstract List findAll();

	public abstract void attachDirty(Door instance);

}
package com.yan.service;

import java.util.List;

import com.yan.entity.Log;
import com.yan.utils.PageBean;

public interface ILogService {

	public abstract void save(Log transientInstance);

	public abstract void delete(Log persistentInstance);

	public abstract Log findById(java.lang.Integer id);

	public abstract List findAll();

	public abstract void attachDirty(Log instance);
	
	public abstract PageBean findAll(int currentPage, int pageSize, String sort, String order);

	public abstract PageBean findAll(int currentPage, int pageSize, String param, String sort,
			String order);

}
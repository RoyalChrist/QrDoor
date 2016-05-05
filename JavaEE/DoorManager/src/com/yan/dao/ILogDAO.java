package com.yan.dao;

import java.util.List;

import com.yan.entity.Log;
import com.yan.utils.PageBean;

public interface ILogDAO {

	public abstract PageBean findAll(PageBean pageBean, String sort,
			String order);

	public abstract PageBean findAll(PageBean pageBean, String param,
			String sort, String order);

	public abstract void save(Log transientInstance);

	public abstract void delete(Log persistentInstance);

	public abstract Log findById(java.lang.Integer id);

	public abstract List findAll();

	public abstract void attachDirty(Log instance);

}
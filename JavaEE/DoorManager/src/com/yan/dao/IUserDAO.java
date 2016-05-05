package com.yan.dao;

import java.util.List;

import com.yan.entity.User;
import com.yan.utils.PageBean;

public interface IUserDAO {

	public abstract PageBean findAll(PageBean pageBean, String sort,
			String order);

	public abstract PageBean findAll(PageBean pageBean, String param,
			String sort, String order);

	public abstract void save(User transientInstance);

	public abstract void delete(User persistentInstance);

	public abstract User findById(java.lang.Integer id);

	public abstract List findAll();

	public abstract void attachDirty(User instance);

}
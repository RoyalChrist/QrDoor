package com.yan.service;

import java.util.List;

import com.yan.entity.User;
import com.yan.utils.PageBean;

public interface IUserService {

	public abstract void save(User transientInstance);

	public abstract void delete(User persistentInstance);

	public abstract User findById(java.lang.Integer id);

	public abstract List findAll();

	public abstract void attachDirty(User instance);

	public abstract PageBean findAll(int currentPage, int pageSize, String sort, String order);

	public abstract PageBean findAll(int currentPage, int pageSize, String param, String sort,
			String order);

}
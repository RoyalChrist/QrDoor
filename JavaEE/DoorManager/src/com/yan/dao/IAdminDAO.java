package com.yan.dao;

import java.util.List;

import com.yan.entity.Admin;

public interface IAdminDAO {

	public abstract Admin login(String loginName, String password);

	public abstract void save(Admin transientInstance);

	public abstract void delete(Admin persistentInstance);

	public abstract Admin findById(java.lang.Integer id);

	public abstract List findAll();

	public abstract void attachDirty(Admin instance);

}
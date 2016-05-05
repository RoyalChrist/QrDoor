package com.yan.service.imp;

import java.util.List;

import com.yan.dao.IAdminDAO;
import com.yan.entity.Admin;
import com.yan.service.IAdminService;

public class AdminService implements IAdminService {

	private IAdminDAO dao;

	public IAdminDAO getDao() {
		return dao;
	}

	public void setDao(IAdminDAO dao) {
		this.dao = dao;
	}

	@Override
	public Admin login(String loginName, String password) {
		return dao.login(loginName, password);
	}

	@Override
	public void save(Admin transientInstance) {
		dao.save(transientInstance);
	}

	@Override
	public void delete(Admin persistentInstance) {
		dao.delete(persistentInstance);
	}

	@Override
	public Admin findById(Integer id) {
		return dao.findById(id);
	}

	@Override
	public List findAll() {
		return dao.findAll();
	}

	@Override
	public void attachDirty(Admin instance) {
		dao.attachDirty(instance);
	}

}

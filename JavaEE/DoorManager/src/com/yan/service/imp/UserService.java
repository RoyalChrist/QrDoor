package com.yan.service.imp;

import java.util.List;

import com.yan.dao.IUserDAO;
import com.yan.entity.User;
import com.yan.service.IUserService;
import com.yan.utils.PageBean;

public class UserService implements IUserService {

	private IUserDAO dao;

	@Override
	public void save(User transientInstance) {
		dao.save(transientInstance);
	}

	@Override
	public void delete(User persistentInstance) {
		dao.delete(persistentInstance);
	}

	@Override
	public User findById(Integer id) {
		return dao.findById(id);
	}

	@Override
	public List findAll() {
		return dao.findAll();
	}

	@Override
	public void attachDirty(User instance) {
		dao.attachDirty(instance);
	}

	@Override
	public PageBean findAll(int currentPage, int pageSize, String sort,
			String order) {
		PageBean pageBean = new PageBean(currentPage, pageSize);
		return dao.findAll(pageBean, sort, order);
	}

	@Override
	public PageBean findAll(int currentPage, int pageSize, String param,
			String sort, String order) {
		PageBean pageBean = new PageBean(currentPage, pageSize);
		return dao.findAll(pageBean, param, sort, order);
	}

	public IUserDAO getDao() {
		return dao;
	}

	public void setDao(IUserDAO dao) {
		this.dao = dao;
	}

}

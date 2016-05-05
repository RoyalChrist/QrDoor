package com.yan.service.imp;

import java.util.List;

import com.yan.dao.ILogDAO;
import com.yan.entity.Log;
import com.yan.service.ILogService;
import com.yan.utils.PageBean;

public class LogService implements ILogService {

	private ILogDAO dao;

	@Override
	public void save(Log transientInstance) {
		dao.save(transientInstance);
	}

	@Override
	public void delete(Log persistentInstance) {
		dao.delete(persistentInstance);
	}

	@Override
	public Log findById(Integer id) {
		return dao.findById(id);
	}

	@Override
	public List findAll() {
		return dao.findAll();
	}

	@Override
	public void attachDirty(Log instance) {
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

	public ILogDAO getDao() {
		return dao;
	}

	public void setDao(ILogDAO dao) {
		this.dao = dao;
	}

}

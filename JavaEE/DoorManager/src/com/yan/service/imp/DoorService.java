package com.yan.service.imp;

import java.util.List;

import com.yan.dao.IDoorDAO;
import com.yan.entity.Door;
import com.yan.service.IDoorService;
import com.yan.utils.PageBean;

public class DoorService implements IDoorService {
	private IDoorDAO dao;

	@Override
	public void save(Door transientInstance) {
		dao.save(transientInstance);
	}

	@Override
	public void delete(Door persistentInstance) {
		dao.delete(persistentInstance);
	}

	@Override
	public Door findById(Integer id) {
		return dao.findById(id);
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

	@Override
	public List findAll() {
		return dao.findAll();
	}

	@Override
	public void attachDirty(Door instance) {
		dao.attachDirty(instance);
	}

	public IDoorDAO getDao() {
		return dao;
	}

	public void setDao(IDoorDAO dao) {
		this.dao = dao;
	}

}

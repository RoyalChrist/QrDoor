package com.yan.service.imp;

import java.util.List;

import com.yan.dao.IRelationshipDAO;
import com.yan.entity.Relationship;
import com.yan.service.IRelationshipService;
import com.yan.utils.PageBean;

public class RelationshipService implements IRelationshipService{
	
	protected IRelationshipDAO dao;

	public IRelationshipDAO getDao() {
		return dao;
	}

	public void setDao(IRelationshipDAO dao) {
		this.dao = dao;
	}

	@Override
	public void save(Relationship transientInstance) {
		dao.save(transientInstance);
	}

	@Override
	public void delete(Relationship persistentInstance) {
		dao.delete(persistentInstance);		
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
	public PageBean findbyloginName(int currentPage, int pageSize,
			String loginName, String sort, String order) {
		PageBean pageBean = new PageBean(currentPage, pageSize);
		return dao.findbyloginName(pageBean, loginName, sort, order);
	}

	@Override
	public PageBean findbydoorNumber(int currentPage, int pageSize,
			String doorNumber, String sort, String order) {
		PageBean pageBean = new PageBean(currentPage, pageSize);
		return dao.findbydoorNumber(pageBean, doorNumber, sort, order);
	}

	@Override
	public List findAll() {
		return dao.findAll();
	}

	@Override
	public void attachDirty(Relationship instance) {
		dao.attachDirty(instance);		
	}

	@Override
	public Relationship findById(Integer id) {
		return dao.findById(id);
	}

}

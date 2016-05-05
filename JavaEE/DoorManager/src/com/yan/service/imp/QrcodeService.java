package com.yan.service.imp;

import java.util.List;

import com.yan.dao.IQrcodeDAO;
import com.yan.entity.Qrcode;
import com.yan.service.IQrcodeService;
import com.yan.utils.PageBean;

public class QrcodeService implements IQrcodeService {

	private IQrcodeDAO dao;

	@Override
	public PageBean findAll(int currentPage, int pageSize, String param,
			String sort, String order) {
		PageBean pageBean = new PageBean(currentPage, pageSize);
		return dao.findAll(pageBean, param, sort, order);
	}

	@Override
	public PageBean findAll(int currentPage, int pageSize, String sort,
			String order) {
		PageBean pageBean = new PageBean(currentPage, pageSize);
		return dao.findAll(pageBean, sort, order);
	}

	@Override
	public void save(Qrcode transientInstance) {
		dao.save(transientInstance);
	}

	@Override
	public void delete(Qrcode persistentInstance) {
		dao.delete(persistentInstance);
	}

	@Override
	public Qrcode findById(Integer id) {
		return dao.findById(id);
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
	public void attachDirty(Qrcode instance) {
		dao.attachDirty(instance);
	}

	public IQrcodeDAO getDao() {
		return dao;
	}

	public void setDao(IQrcodeDAO dao) {
		this.dao = dao;
	}

}

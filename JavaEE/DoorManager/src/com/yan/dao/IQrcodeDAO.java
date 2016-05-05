package com.yan.dao;

import java.util.List;

import com.yan.entity.Qrcode;
import com.yan.utils.PageBean;

public interface IQrcodeDAO {

	public abstract PageBean findAll(PageBean pageBean, String param,
			String sort, String order);

	public abstract PageBean findAll(PageBean pageBean, String sort,
			String order);

	public abstract void save(Qrcode transientInstance);

	public abstract void delete(Qrcode persistentInstance);

	public abstract Qrcode findById(java.lang.Integer id);

	public abstract PageBean findbydoorNumber(PageBean pageBean,
			String doorNumber, String sort, String order);

	public abstract List findAll();

	public abstract void attachDirty(Qrcode instance);

}
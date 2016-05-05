package com.yan.service;

import java.util.List;

import com.yan.entity.Qrcode;
import com.yan.utils.PageBean;

public interface IQrcodeService {

	public abstract PageBean findAll(int currentPage, int pageSize, String param,
			String sort, String order);

	public abstract PageBean findAll(int currentPage, int pageSize, String sort,
			String order);

	public abstract void save(Qrcode transientInstance);

	public abstract void delete(Qrcode persistentInstance);

	public abstract Qrcode findById(java.lang.Integer id);

	public abstract PageBean findbydoorNumber(int currentPage, int pageSize,
			String doorNumber, String sort, String order);

	public abstract List findAll();

	public abstract void attachDirty(Qrcode instance);

}
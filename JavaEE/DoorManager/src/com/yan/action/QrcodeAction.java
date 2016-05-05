package com.yan.action;

import java.util.List;

import com.yan.entity.Qrcode;
import com.yan.service.IQrcodeService;
import com.yan.utils.PageBean;

public class QrcodeAction {
	private Integer id;
	private Qrcode qrcode;
	private List<Qrcode> qrcodes;
	private PageBean pageBean;
	private int total;
	private IQrcodeService service;
	private int currentPage = 1;
	private int pageSize = 10;
	private String param = null;
	private String flag = null;
	private String sort = "id";
	private String order = "desc";

	// //////////////////////////////
	public String save() {

		service.save(qrcode);
		return "save";
	}

	public String details() {
		qrcode = service.findById(id);
		return "details";
	}

	public String findbyid() {
		qrcode = service.findById(id);
		return "findbyid";
	}

	public String update() {
		service.attachDirty(qrcode);
		return "update";
	}

	public String delete() {
		qrcode = service.findById(id);
		service.delete(qrcode);
		return "delete";
	}

	public String findall() {

		if (flag == null) {
			pageBean = service.findAll(currentPage, pageSize, sort, order);
		} else if (flag.equals("like")) {
			pageBean = service.findAll(currentPage, pageSize, param, sort,
					order);
		}else if (flag.equals("doornumber")) {
			pageBean = service.findbydoorNumber(currentPage, pageSize, param, sort, order);
		}
		qrcodes = pageBean.getList();
		total = pageBean.getRowCount();
		return "findall";
	}

	// //////////////////////////////////////////////

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public void setPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public void setRows(int pageSize) {
		this.pageSize = pageSize;
	}

	public PageBean getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}

	public IQrcodeService getService() {
		return service;
	}

	public void setService(IQrcodeService service) {
		this.service = service;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Qrcode getQrcode() {
		return qrcode;
	}

	public void setQrcode(Qrcode Qrcode) {
		this.qrcode = Qrcode;
	}

	public List<Qrcode> getRows() {
		return qrcodes;
	}

	public void setQrcodes(List<Qrcode> Qrcodes) {
		this.qrcodes = Qrcodes;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
}

package com.yan.action;

import java.util.List;

import com.yan.entity.Log;
import com.yan.service.ILogService;
import com.yan.utils.PageBean;

public final class LogAction {
	
	private Integer id;
	private Log log;
	private List<Log> logs;
	private int total;
	private ILogService service;
	private PageBean pageBean;
	private int currentPage = 1;
	private int pageSize = 10;
	private String param = null;
	private String flag = null;
	private String sort = "id";
	private String order = "desc";

	// //////////////////////////////
	public String save() {

		service.save(log);
		return "save";
	}

	public String details() {
		log = service.findById(id);
		return "details";
	}

	public String findbyid() {
		log = service.findById(id);
		return "findbyid";
	}

	public String update() {
		service.attachDirty(log);
		return "update";
	}

	public String delete() {
		log = service.findById(id);
		service.delete(log);
		return "delete";
	}

	public String findall() {

		if (flag == null) {
			pageBean = service.findAll(currentPage, pageSize, sort, order);
		} else if (flag.equals("like")) {
			pageBean = service.findAll(currentPage, pageSize, param, sort,
					order);
		}
		logs = pageBean.getList();
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

	public ILogService getService() {
		return service;
	}

	public void setService(ILogService service) {
		this.service = service;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Log getLog() {
		return log;
	}

	public void setLog(Log Log) {
		this.log = Log;
	}

	public List<Log> getRows() {
		return logs;
	}

	public void setLogs(List<Log> Logs) {
		this.logs = Logs;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

}


package com.yan.action;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.yan.entity.Door;
import com.yan.service.IDoorService;
import com.yan.utils.PageBean;

public class DoorAction extends ActionSupport {
	private Integer id;
	private Door door;
	private List<Door> doors;
	private int total;
	private IDoorService service;
	private PageBean pageBean;
	private int currentPage = 1;
	private int pageSize = 10;
	private String param = null;
	private String flag = null;
	private String sort = "id";
	private String order = "desc";

	// //////////////////////////////
	public String save() {

		service.save(door);
		return "save";
	}

	public String details() {
		door = service.findById(id);
		return "details";
	}

	public String findbyid() {
		door = service.findById(id);
		return "findbyid";
	}

	public String update() {
		service.attachDirty(door);
		return "update";
	}

	public String delete() {
		door = service.findById(id);
		service.delete(door);
		return "delete";
	}

	public String findall() {

		if (flag == null) {
			pageBean = service.findAll(currentPage, pageSize, sort, order);
		} else if (flag.equals("like")) {
			pageBean = service.findAll(currentPage, pageSize, param, sort, order);
		}
		doors = pageBean.getList();
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

	public IDoorService getService() {
		return service;
	}

	public void setService(IDoorService service) {
		this.service = service;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Door getDoor() {
		return door;
	}

	public void setDoor(Door Door) {
		this.door = Door;
	}

	public List<Door> getRows() {
		return doors;
	}

	public void setDoors(List<Door> Doors) {
		this.doors = Doors;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

}

package com.yan.action;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.yan.entity.User;
import com.yan.service.IUserService;
import com.yan.utils.PageBean;

public class UserAction extends ActionSupport {
	private Integer id;
	private User user;
	private List<User> users;
	private PageBean pageBean;
	private int total;
	private IUserService service;
	private int currentPage = 1;
	private int pageSize = 10;
	private String param = null;
	private String flag = null;
	private String sort = "id";
	private String order = "desc";

	// //////////////////////////////
	public String save() {

		service.save(user);
		return "save";
	}

	public String details() {
		user = service.findById(id);
		return "details";
	}

	public String findbyid() {
		user = service.findById(id);
		return "findbyid";
	}

	public String update() {
		service.attachDirty(user);
		return "update";
	}

	public String delete() {
		user = service.findById(id);
		service.delete(user);
		return "delete";
	}

	public String findall() {

		if (flag == null) {
			pageBean = service.findAll(currentPage, pageSize, sort, order);
		} else if (flag.equals("like")) {
			pageBean = service.findAll(currentPage, pageSize, param, sort,
					order);
		}
		users = pageBean.getList();
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

	public IUserService getService() {
		return service;
	}

	public void setService(IUserService service) {
		this.service = service;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User User) {
		this.user = User;
	}

	public List<User> getRows() {
		return users;
	}

	public void setUsers(List<User> Users) {
		this.users = Users;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
}

package com.yan.action;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.yan.entity.Relationship;
import com.yan.service.IRelationshipService;
import com.yan.utils.PageBean;

public class RelationshipAction extends ActionSupport {

	private Integer id;
	private Relationship relationship;
	private List<Relationship> relationships;
	private int total;
	private IRelationshipService service;
	private PageBean pageBean;
	private int currentPage = 1;
	private int pageSize = 10;
	private String param = null;
	private String flag = null;
	private String sort = "id";
	private String order = "desc";

	// //////////////////////////////
	public String save() {

		service.save(relationship);
		return "save";
	}

	public String details() {
		relationship = service.findById(id);
		return "details";
	}

	public String findbyid() {
		relationship = service.findById(id);
		return "findbyid";
	}

	public String update() {
		service.attachDirty(relationship);
		return "update";
	}

	public String delete() {
		relationship = service.findById(id);
		service.delete(relationship);
		return "delete";
	}

	public String findall() {

		if (flag == null) {
			pageBean = service.findAll(currentPage, pageSize, sort, order);
		} else if (flag.equals("like")) {
			pageBean = service.findAll(currentPage, pageSize, param, sort,
					order);
		} else if (flag.equals("loginname")) {
			pageBean = service.findbyloginName(currentPage, pageSize, param,
					sort, order);
		} else if (flag.equals("doornumber")) {
			pageBean = service.findbyloginName(currentPage, pageSize, param,
					sort, order);
		}
		relationships = pageBean.getList();
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

	public IRelationshipService getService() {
		return service;
	}

	public void setService(IRelationshipService service) {
		this.service = service;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Relationship getRelationship() {
		return relationship;
	}

	public void setRelationship(Relationship Relationship) {
		this.relationship = Relationship;
	}

	public List<Relationship> getRows() {
		return relationships;
	}

	public void setRelationships(List<Relationship> Relationships) {
		this.relationships = Relationships;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

}

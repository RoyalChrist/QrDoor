package com.yan.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.yan.entity.Admin;
import com.yan.service.IAdminService;
import com.opensymphony.xwork2.ActionSupport;

public class AdminAction extends ActionSupport {
	private Integer id;
	private Admin admin;
	private List<Admin> list;
	private int total;
	private IAdminService service;
	private String code;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String login() throws IOException {

		HttpSession session = ServletActionContext.getRequest().getSession();
		
			admin = service.login(admin.getLoginName(), admin.getPassword());
			if (admin == null) {
				return "error";
			} else {
				session.setAttribute("admin", admin);
				return "success";
			}
	}

	public String loginout() {
		HttpSession session = ServletActionContext.getRequest().getSession();
		session.invalidate();
		return "loginout";

	}

	public String save() {
		service.save(admin);
		return "save";
	}

	public String findbyid() {
		admin = service.findById(id);
		return "findbyid";
	}

	public String update() {
		service.attachDirty(admin);
		return "update";
	}

	public String delete() {
		service.delete(service.findById(id));
		return "delete";
	}

	public String findall() {
		list = service.findAll();
		return "findall";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public List<Admin> getRows() {
		return list;
	}

	public void setList(List<Admin> list) {
		this.list = list;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public IAdminService getService() {
		return service;
	}

	public void setService(IAdminService service) {
		this.service = service;
	}
}

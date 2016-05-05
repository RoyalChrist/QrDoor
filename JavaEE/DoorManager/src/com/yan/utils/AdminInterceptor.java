package com.yan.utils;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.yan.action.AdminAction;

;

public class AdminInterceptor extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation ai) throws Exception {

		if (ai.getAction().getClass() == AdminAction.class) {
			return ai.invoke();
		}

		HttpSession session = ServletActionContext.getRequest().getSession();
		Object object = session.getAttribute("admin");
		if (object == null) {
			return "login";
		} else {
			return ai.invoke();
		}

	}

}

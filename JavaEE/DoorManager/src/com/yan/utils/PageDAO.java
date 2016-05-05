package com.yan.utils;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class PageDAO extends HibernateDaoSupport {

	public PageBean findforpage(String hql, String hqlCount, PageBean pageBean) {

		Session session = getSession();
		session.beginTransaction();
		Query queryCount = session.createQuery(hqlCount);
		Integer count = Integer.valueOf(queryCount.list().get(0).toString());
		pageBean.setRowCount(count);

		Query query = session.createQuery(hql);
		int first = (pageBean.getCurrentPage() - 1) * pageBean.getPageSize();

		query.setFirstResult(first);
		query.setMaxResults(pageBean.getPageSize());

		pageBean.setList(query.list());
		return pageBean;
	}
}

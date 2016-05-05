package com.yan.dao.imp;

import java.util.List;
import java.util.Set;

import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.yan.dao.IUserDAO;
import com.yan.entity.User;
import com.yan.utils.PageBean;
import com.yan.utils.PageDAO;

/**
 * A data access object (DAO) providing persistence and search support for User
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see com.yan.entity.User
 * @author MyEclipse Persistence Tools
 */
public class UserDAO extends PageDAO implements IUserDAO {
	private static final Logger log = LoggerFactory.getLogger(UserDAO.class);
	// property constants
	public static final String LOGIN_NAME = "loginName";
	public static final String REAL_NAME = "realName";
	public static final String MOBILEPHONE = "mobilephone";
	public static final String PASSWORD = "password";
	public static final String IMEI = "imei";

	protected void initDao() {
		// do nothing
	}
	
	/* (non-Javadoc)
	 * @see com.yan.dao.imp.IUserDAO#findAll(com.yan.utils.PageBean, java.lang.String, java.lang.String)
	 */
	@Override
	public PageBean findAll(PageBean pageBean, String sort, String order) {
		String hql = "select u from User as u"
				+ " order by u." + sort + " " + order;
		String hqlCount = "select count(u.id) from User as u";
		return findforpage(hql, hqlCount, pageBean);
	}
	
	/* (non-Javadoc)
	 * @see com.yan.dao.imp.IUserDAO#findAll(com.yan.utils.PageBean, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public PageBean findAll(PageBean pageBean, String param, String sort,
			String order) {
		String hql = "select u from User as u "
				+ "where u.loginName like '%" + param + "%' "
				+ "or u.realName like '%" + param + "%' " 
				+ "or u.mobilephone like '%" + param + "%' "
				+ " order by u." + sort + " " + order;
		String hqlCount = "select count(u.id) from User as u "
				+ "where u.loginName like '%" + param + "%' "
				+ "or u.realName like '%" + param + "%' " 
				+ "or u.mobilephone like '%" + param + "%' ";
		return findforpage(hql, hqlCount, pageBean);
	}

	/* (non-Javadoc)
	 * @see com.yan.dao.imp.IUserDAO#save(com.yan.entity.User)
	 */
	@Override
	public void save(User transientInstance) {
		log.debug("saving User instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.yan.dao.imp.IUserDAO#delete(com.yan.entity.User)
	 */
	@Override
	public void delete(User persistentInstance) {
		log.debug("deleting User instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.yan.dao.imp.IUserDAO#findById(java.lang.Integer)
	 */
	@Override
	public User findById(java.lang.Integer id) {
		log.debug("getting User instance with id: " + id);
		try {
			User instance = (User) getHibernateTemplate().get(
					"com.yan.entity.User", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<User> findByExample(User instance) {
		log.debug("finding User instance by example");
		try {
			List<User> results = (List<User>) getHibernateTemplate()
					.findByExample(instance);
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding User instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from User as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<User> findByLoginName(Object loginName) {
		return findByProperty(LOGIN_NAME, loginName);
	}

	public List<User> findByRealName(Object realName) {
		return findByProperty(REAL_NAME, realName);
	}

	public List<User> findByMobilephone(Object mobilephone) {
		return findByProperty(MOBILEPHONE, mobilephone);
	}

	public List<User> findByPassword(Object password) {
		return findByProperty(PASSWORD, password);
	}

	public List<User> findByImei(Object imei) {
		return findByProperty(IMEI, imei);
	}

	/* (non-Javadoc)
	 * @see com.yan.dao.imp.IUserDAO#findAll()
	 */
	@Override
	public List findAll() {
		log.debug("finding all User instances");
		try {
			String queryString = "from User";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public User merge(User detachedInstance) {
		log.debug("merging User instance");
		try {
			User result = (User) getHibernateTemplate().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.yan.dao.imp.IUserDAO#attachDirty(com.yan.entity.User)
	 */
	@Override
	public void attachDirty(User instance) {
		log.debug("attaching dirty User instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(User instance) {
		log.debug("attaching clean User instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static IUserDAO getFromApplicationContext(ApplicationContext ctx) {
		return (IUserDAO) ctx.getBean("UserDAO");
	}
}
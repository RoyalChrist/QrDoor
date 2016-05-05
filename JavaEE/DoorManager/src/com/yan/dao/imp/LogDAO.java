package com.yan.dao.imp;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.yan.dao.ILogDAO;
import com.yan.entity.Log;
import com.yan.utils.PageBean;
import com.yan.utils.PageDAO;

/**
 * A data access object (DAO) providing persistence and search support for Log
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see com.yan.entity.Log
 * @author MyEclipse Persistence Tools
 */
public class LogDAO extends PageDAO implements ILogDAO {
	private static final Logger log = LoggerFactory.getLogger(LogDAO.class);
	// property constants
	public static final String DOOR_NUMBER = "doorNumber";
	public static final String LOGIN_NAME = "loginName";
	public static final String REAL_NAME = "realName";
	public static final String RECORD = "record";

	protected void initDao() {
		// do nothing
	}
	
	/* (non-Javadoc)
	 * @see com.yan.dao.imp.ILogDAO#findAll(com.yan.utils.PageBean, java.lang.String, java.lang.String)
	 */
	@Override
	public PageBean findAll(PageBean pageBean, String sort, String order) {
		String hql = "select l from Log as l"
				+ " order by l." + sort + " " + order;
		String hqlCount = "select count(l.id) from Log as l";
		return findforpage(hql, hqlCount, pageBean);
	}
	
	/* (non-Javadoc)
	 * @see com.yan.dao.imp.ILogDAO#findAll(com.yan.utils.PageBean, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public PageBean findAll(PageBean pageBean, String param, String sort,
			String order) {
		String hql = "select l from Log as l "
				+ "where l.doorNumber like '%" + param + "%' "
				+ "or l.loginName like '%" + param + "%' " 
				+ "or l.realName like '%" + param + "%' " 
				+ "or l.record like '%"+ param + "%' " 
				+ "or l.time like '%" + param + "%'"
				+ " order by l." + sort + " " + order;
		String hqlCount = "select count(l.id) from Log as l "
				+ "where l.doorNumber like '%" + param + "%' "
				+ "or l.loginName like '%" + param + "%' " 
				+ "or l.realName like '%" + param + "%' " 
				+ "or l.record like '%"+ param + "%' " 
				+ "or l.time like '%" + param + "%'";
		return findforpage(hql, hqlCount, pageBean);
	}

	/* (non-Javadoc)
	 * @see com.yan.dao.imp.ILogDAO#save(com.yan.entity.Log)
	 */
	@Override
	public void save(Log transientInstance) {
		log.debug("saving Log instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.yan.dao.imp.ILogDAO#delete(com.yan.entity.Log)
	 */
	@Override
	public void delete(Log persistentInstance) {
		log.debug("deleting Log instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.yan.dao.imp.ILogDAO#findById(java.lang.Integer)
	 */
	@Override
	public Log findById(java.lang.Integer id) {
		log.debug("getting Log instance with id: " + id);
		try {
			Log instance = (Log) getHibernateTemplate().get(
					"com.yan.entity.Log", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Log> findByExample(Log instance) {
		log.debug("finding Log instance by example");
		try {
			List<Log> results = (List<Log>) getHibernateTemplate()
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
		log.debug("finding Log instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Log as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Log> findByDoorNumber(Object doorNumber) {
		return findByProperty(DOOR_NUMBER, doorNumber);
	}

	public List<Log> findByLoginName(Object loginName) {
		return findByProperty(LOGIN_NAME, loginName);
	}

	public List<Log> findByRealName(Object realName) {
		return findByProperty(REAL_NAME, realName);
	}

	public List<Log> findByRecord(Object record) {
		return findByProperty(RECORD, record);
	}

	/* (non-Javadoc)
	 * @see com.yan.dao.imp.ILogDAO#findAll()
	 */
	@Override
	public List findAll() {
		log.debug("finding all Log instances");
		try {
			String queryString = "from Log";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Log merge(Log detachedInstance) {
		log.debug("merging Log instance");
		try {
			Log result = (Log) getHibernateTemplate().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.yan.dao.imp.ILogDAO#attachDirty(com.yan.entity.Log)
	 */
	@Override
	public void attachDirty(Log instance) {
		log.debug("attaching dirty Log instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Log instance) {
		log.debug("attaching clean Log instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static ILogDAO getFromApplicationContext(ApplicationContext ctx) {
		return (ILogDAO) ctx.getBean("LogDAO");
	}
}
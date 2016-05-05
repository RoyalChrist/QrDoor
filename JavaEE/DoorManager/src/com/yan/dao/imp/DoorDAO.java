package com.yan.dao.imp;

import java.util.List;
import java.util.Set;

import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.yan.dao.IDoorDAO;
import com.yan.entity.Door;
import com.yan.utils.PageBean;
import com.yan.utils.PageDAO;

/**
 * A data access object (DAO) providing persistence and search support for Door
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see com.yan.entity.Door
 * @author MyEclipse Persistence Tools
 */
public class DoorDAO extends PageDAO implements IDoorDAO {
	private static final Logger log = LoggerFactory.getLogger(DoorDAO.class);
	// property constants
	public static final String DOOR_NUMBER = "doorNumber";
	public static final String OPEN_CODE = "openCode";
	public static final String NODE_NUMBER = "nodeNumber";
	public static final String IP = "ip";
	public static final String PORT = "port";
	public static final String INFO = "info";

	protected void initDao() {
		// do nothing
	}
	@Override
	public PageBean findAll(PageBean pageBean, String sort, String order) {
		String hql = "select d from Door as d"
				+ " order by d." + sort + " " + order;
		String hqlCount = "select count(d.id) from Door as d";
		return findforpage(hql, hqlCount, pageBean);
	}
	
	@Override
	public PageBean findAll(PageBean pageBean, String param, String sort,
			String order) {
		String hql = "select d from Door as d"
				+ "where d.doorNumber like '%" + param + "%' "
				+ "or d.openCode like '%" + param + "%' " 
				+ "or d.info like '%"+ param + "%' "
				+ "or d.nodeNumber like '%" + param + "%' "
				+ "or d.ip like '%" + param + "%' "
				+ "or d.port like '%" + param + "%' "
				+ "order by d." + sort + " " + order;
		String hqlCount = "select count(d.id) from Door as d "
				+ "where d.doorNumber like '%" + param + "%' "
				+ "or d.openCode like '%" + param + "%' " 
				+ "or d.info like '%"+ param + "%' "
				+ "or d.nodeNumber like '%" + param + "%' "
				+ "or d.ip like '%" + param + "%' "
				+ "or d.port like '%" + param + "%' ";
		return findforpage(hql, hqlCount, pageBean);
	}
	
	/* (non-Javadoc)
	 * @see com.yan.dao.imp.IDoorDAO#save(com.yan.entity.Door)
	 */
	@Override
	public void save(Door transientInstance) {
		log.debug("saving Door instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.yan.dao.imp.IDoorDAO#delete(com.yan.entity.Door)
	 */
	@Override
	public void delete(Door persistentInstance) {
		log.debug("deleting Door instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.yan.dao.imp.IDoorDAO#findById(java.lang.Integer)
	 */
	@Override
	public Door findById(java.lang.Integer id) {
		log.debug("getting Door instance with id: " + id);
		try {
			Door instance = (Door) getHibernateTemplate().get(
					"com.yan.entity.Door", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Door> findByExample(Door instance) {
		log.debug("finding Door instance by example");
		try {
			List<Door> results = (List<Door>) getHibernateTemplate()
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
		log.debug("finding Door instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Door as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Door> findByDoorNumber(Object doorNumber) {
		return findByProperty(DOOR_NUMBER, doorNumber);
	}

	public List<Door> findByOpenCode(Object openCode) {
		return findByProperty(OPEN_CODE, openCode);
	}

	public List<Door> findByNodeNumber(Object nodeNumber) {
		return findByProperty(NODE_NUMBER, nodeNumber);
	}

	public List<Door> findByIp(Object ip) {
		return findByProperty(IP, ip);
	}

	public List<Door> findByPort(Object port) {
		return findByProperty(PORT, port);
	}

	public List<Door> findByInfo(Object info) {
		return findByProperty(INFO, info);
	}

	/* (non-Javadoc)
	 * @see com.yan.dao.imp.IDoorDAO#findAll()
	 */
	@Override
	public List findAll() {
		log.debug("finding all Door instances");
		try {
			String queryString = "from Door";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Door merge(Door detachedInstance) {
		log.debug("merging Door instance");
		try {
			Door result = (Door) getHibernateTemplate().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.yan.dao.imp.IDoorDAO#attachDirty(com.yan.entity.Door)
	 */
	@Override
	public void attachDirty(Door instance) {
		log.debug("attaching dirty Door instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Door instance) {
		log.debug("attaching clean Door instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static IDoorDAO getFromApplicationContext(ApplicationContext ctx) {
		return (IDoorDAO) ctx.getBean("DoorDAO");
	}
}
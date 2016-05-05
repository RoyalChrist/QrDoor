package com.yan.dao.imp;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.yan.dao.IQrcodeDAO;
import com.yan.entity.Qrcode;
import com.yan.entity.Qrcode;
import com.yan.utils.PageBean;
import com.yan.utils.PageDAO;

/**
 * A data access object (DAO) providing persistence and search support for
 * Qrcode entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.yan.entity.Qrcode
 * @author MyEclipse Persistence Tools
 */
public class QrcodeDAO extends PageDAO implements IQrcodeDAO {
	private static final Logger log = LoggerFactory.getLogger(QrcodeDAO.class);
	// property constants
	public static final String CODE = "code";
	public static final String RANDNUMBER = "randnumber";
	public static final String IS_USED = "isUsed";
	public static final String USER_SCAN = "userScan";

	protected void initDao() {
		// do nothing
	}
	
	/* (non-Javadoc)
	 * @see com.yan.dao.imp.IQrcodeDAO#findAll(com.yan.utils.PageBean, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public PageBean findAll(PageBean pageBean, String param, String sort,
			String order) {
		String hql = "select q from Qrcode as q inner join fetch q.door "
				+ "where q.door.doorNumber like '%" + param + "%' "
				+ "or q.code like '%" + param + "%' " 
				+ "or q.randnumber like '%" + param + "%' " 
				+ "or q.createTime like '%" + param + "%' "
				+ "or q.expTime like '%" + param + "%' "
				+ "or q.isUsed like '%" + param + "%' "
				+ "or q.userScan like '%" + param + "%' "
				+ " order by q." + sort + " " + order;
		String hqlCount = "select count(q.id) from Qrcode as q "
				+ "where q.door.doorNumber like '%" + param + "%' "
				+ "or q.code like '%" + param + "%' " 
				+ "or q.randnumber like '%" + param + "%' " 
				+ "or q.createTime like '%" + param + "%' "
				+ "or q.expTime like '%" + param + "%' "
				+ "or q.userScan like '%" + param + "%' ";
		return findforpage(hql, hqlCount, pageBean);
	}
	
	/* (non-Javadoc)
	 * @see com.yan.dao.imp.IQrcodeDAO#findAll(com.yan.utils.PageBean, java.lang.String, java.lang.String)
	 */
	@Override
	public PageBean findAll(PageBean pageBean, String sort, String order) {
		String hql = "select q from Qrcode as q  inner join fetch q.door"
				+ " order by q." + sort + " " + order;
		String hqlCount = "select count(q.id) from Qrcode as q";
		return findforpage(hql, hqlCount, pageBean);
	}

	/* (non-Javadoc)
	 * @see com.yan.dao.imp.IQrcodeDAO#save(com.yan.entity.Qrcode)
	 */
	@Override
	public void save(Qrcode transientInstance) {
		log.debug("saving Qrcode instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.yan.dao.imp.IQrcodeDAO#delete(com.yan.entity.Qrcode)
	 */
	@Override
	public void delete(Qrcode persistentInstance) {
		log.debug("deleting Qrcode instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.yan.dao.imp.IQrcodeDAO#findById(java.lang.Integer)
	 */
	@Override
	public Qrcode findById(java.lang.Integer id) {
		log.debug("getting Qrcode instance with id: " + id);
		try {
			String hql = "select q from Qrcode as q inner join fetch q.door where q.id="
					+ id;
			@SuppressWarnings("unchecked")
			List<Qrcode> Qrcodes = (List<Qrcode>) getHibernateTemplate().find(hql);
			if (Qrcodes.size() > 0) {
				return Qrcodes.get(0);
			} else {
				return null;
			}
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	/* (non-Javadoc)
	 * @see com.yan.dao.imp.IQrcodeDAO#findbydoorNumber(com.yan.utils.PageBean, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public PageBean findbydoorNumber(PageBean pageBean, String doorNumber,
			String sort, String order) {
		String hql = "select q from Qrcode as q inner join fetch q.door "
				+ "where  q.door.doorNumber = '" + doorNumber + "'"
				+ " order by q." + sort + " " + order;
		String hqlCount = "select count(q.id) from Qrcode as q "
				+ "where q.door.doorNumber = '" + doorNumber + "'";
		return findforpage(hql, hqlCount, pageBean);
	}

	public List<Qrcode> findByExample(Qrcode instance) {
		log.debug("finding Qrcode instance by example");
		try {
			List<Qrcode> results = (List<Qrcode>) getHibernateTemplate()
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
		log.debug("finding Qrcode instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Qrcode as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Qrcode> findByCode(Object code) {
		return findByProperty(CODE, code);
	}

	public List<Qrcode> findByRandnumber(Object randnumber) {
		return findByProperty(RANDNUMBER, randnumber);
	}

	public List<Qrcode> findByIsUsed(Object isUsed) {
		return findByProperty(IS_USED, isUsed);
	}

	public List<Qrcode> findByUserScan(Object userScan) {
		return findByProperty(USER_SCAN, userScan);
	}

	/* (non-Javadoc)
	 * @see com.yan.dao.imp.IQrcodeDAO#findAll()
	 */
	@Override
	public List findAll() {
		log.debug("finding all Qrcode instances");
		try {
			String queryString = "from Qrcode";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Qrcode merge(Qrcode detachedInstance) {
		log.debug("merging Qrcode instance");
		try {
			Qrcode result = (Qrcode) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.yan.dao.imp.IQrcodeDAO#attachDirty(com.yan.entity.Qrcode)
	 */
	@Override
	public void attachDirty(Qrcode instance) {
		log.debug("attaching dirty Qrcode instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Qrcode instance) {
		log.debug("attaching clean Qrcode instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static IQrcodeDAO getFromApplicationContext(ApplicationContext ctx) {
		return (IQrcodeDAO) ctx.getBean("QrcodeDAO");
	}
}
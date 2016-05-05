package com.yan.dao.imp;

import java.util.List;

import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.yan.dao.IRelationshipDAO;
import com.yan.entity.Relationship;
import com.yan.utils.PageBean;
import com.yan.utils.PageDAO;
import com.yan.entity.Relationship;

/**
 * A data access object (DAO) providing persistence and search support for
 * Relationship entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.yan.entity.Relationship
 * @author MyEclipse Persistence Tools
 */
public class RelationshipDAO extends PageDAO implements IRelationshipDAO {
	private static final Logger log = LoggerFactory
			.getLogger(RelationshipDAO.class);
	// property constants
	public static final String INFO = "info";

	protected void initDao() {
		// do nothing
	}

	/* (non-Javadoc)
	 * @see com.yan.dao.imp.IRelationshipDAO#save(com.yan.entity.Relationship)
	 */
	@Override
	public void save(Relationship transientInstance) {
		log.debug("saving Relationship instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.yan.dao.imp.IRelationshipDAO#delete(com.yan.entity.Relationship)
	 */
	@Override
	public void delete(Relationship persistentInstance) {
		log.debug("deleting Relationship instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Relationship findById(java.lang.Integer id) {
		log.debug("getting Relationship instance with id: " + id);
		try {
			//find
			String hql="select r from Relationship as r inner join fetch r.user inner join fetch r.door where r.id="+id;
			List<Relationship> relationships = (List<Relationship>)getHibernateTemplate().find(hql);
			if (relationships.size()>0) {
				return relationships.get(0);
			}else {
				return null;
			}
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	/* (non-Javadoc)
	 * @see com.yan.dao.imp.IRelationshipDAO#findAll(com.yan.utils.PageBean, java.lang.String, java.lang.String)
	 */
	@Override
	public PageBean findAll(PageBean pageBean,String sort,String order) {
		String hql="select r from Relationship as r inner join fetch r.user inner join fetch r.door"+
					" order by r."+sort+" "+order;	
		String hqlCount="select count(r.id) from Relationship as r";
		return	findforpage(hql, hqlCount, pageBean);
	}
	
	/* (non-Javadoc)
	 * @see com.yan.dao.imp.IRelationshipDAO#findAll(com.yan.utils.PageBean, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public PageBean findAll(PageBean pageBean,String param,String sort,String order) {
		String hql="select r from Relationship as r inner join fetch r.user inner join fetch r.door "+
					"where r.user.loginName like '%"+param+"%' "+
					"or r.door.doorNumber like '%"+param+"%' "+
					"or r.info like '%"+param+"%' "+
					" order by r."+sort+" "+order;
		String hqlCount="select count(r.id) from Relationship as r "+
				"where r.user.loginName like '%"+param+"%' "+
				"or r.door.doorNumber like '%"+param+"%' "+
				"or r.info like '%"+param+"%' ";	
		return	findforpage(hql, hqlCount, pageBean);
	}
	
	/* (non-Javadoc)
	 * @see com.yan.dao.imp.IRelationshipDAO#findbyloginName(com.yan.utils.PageBean, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public PageBean findbyloginName(PageBean pageBean,String loginName,String sort,String order) {
		String hql="select r from Relationship as r inner join fetch r.user inner join fetch r.door "+
					"where r.user.loginName = '"+loginName+"'"+
					" order by r."+sort+" "+order;
		String hqlCount="select count(r.id) from Relationship as r "+
				"where r.user.loginName = '"+loginName+"'";	
		return	findforpage(hql, hqlCount, pageBean);
	}

	/* (non-Javadoc)
	 * @see com.yan.dao.imp.IRelationshipDAO#findbydoorNumber(com.yan.utils.PageBean, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public PageBean findbydoorNumber(PageBean pageBean,String doorNumber,String sort,String order) {
		String hql="select r from Relationship as r inner join fetch r.user inner join fetch r.door "+
				"where r.door.doorNumber = '"+doorNumber+"'"+
				" order by r."+sort+" "+order;
		String hqlCount="select count(r.id) from Relationship as r "+
				"where r.door.doorNumber = '"+doorNumber+"'";	
		return	findforpage(hql, hqlCount, pageBean);
	}
	
	public List<Relationship> findByExample(Relationship instance) {
		log.debug("finding Relationship instance by example");
		try {
			List<Relationship> results = (List<Relationship>) getHibernateTemplate()
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
		log.debug("finding Relationship instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Relationship as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Relationship> findByInfo(Object info) {
		return findByProperty(INFO, info);
	}

	/* (non-Javadoc)
	 * @see com.yan.dao.imp.IRelationshipDAO#findAll()
	 */
	@Override
	public List findAll() {
		log.debug("finding all Relationship instances");
		try {
			String queryString = "from Relationship";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Relationship merge(Relationship detachedInstance) {
		log.debug("merging Relationship instance");
		try {
			Relationship result = (Relationship) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.yan.dao.imp.IRelationshipDAO#attachDirty(com.yan.entity.Relationship)
	 */
	@Override
	public void attachDirty(Relationship instance) {
		log.debug("attaching dirty Relationship instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Relationship instance) {
		log.debug("attaching clean Relationship instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static IRelationshipDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (IRelationshipDAO) ctx.getBean("RelationshipDAO");
	}
}
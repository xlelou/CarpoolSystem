package com.ssh.dao.impl;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.ssh.model.Nation;

/**
 * A data access object (DAO) providing persistence and search support for
 * Nation entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.ssh.model.Nation
 * @author MyEclipse Persistence Tools
 */

public class NationDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(NationDAO.class);
	// property constants
	public static final String TYPE = "type";
	public static final String APPLY_ID = "applyId";
	public static final String ROUTER_ID = "routerId";
	public static final String NEED_COUNT = "needCount";
	public static final String IN_COUNT = "inCount";
	public static final String APPLY_COUNT = "applyCount";
	public static final String PRICE = "price";
	public static final String CARTYPE = "cartype";
	public static final String STATUS = "status";
	public static final String STARTDATE = "startdate";
	public static final String STARTTIME = "starttime";
	public static final String VIEWTIME = "viewtime";
	public static final String MESSAGE = "message";

	protected void initDao() {
		// do nothing
	}

	public void save(Nation transientInstance) {
		log.debug("saving Nation instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Nation persistentInstance) {
		log.debug("deleting Nation instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Nation findById(java.lang.Integer id) {
		log.debug("getting Nation instance with id: " + id);
		try {
			Nation instance = (Nation) getHibernateTemplate().get(
					"com.ssh.model.Nation", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Nation instance) {
		log.debug("finding Nation instance by example");
		try {
			List results = getHibernateTemplate().findByExample(instance);
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Nation instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Nation as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByType(Object type) {
		return findByProperty(TYPE, type);
	}

	public List findByApplyId(Object applyId) {
		return findByProperty(APPLY_ID, applyId);
	}

	public List findByRouterId(Object routerId) {
		return findByProperty(ROUTER_ID, routerId);
	}

	public List findByNeedCount(Object needCount) {
		return findByProperty(NEED_COUNT, needCount);
	}

	public List findByInCount(Object inCount) {
		return findByProperty(IN_COUNT, inCount);
	}

	public List findByApplyCount(Object applyCount) {
		return findByProperty(APPLY_COUNT, applyCount);
	}

	public List findByPrice(Object price) {
		return findByProperty(PRICE, price);
	}

	public List findByCartype(Object cartype) {
		return findByProperty(CARTYPE, cartype);
	}

	public List findByStatus(Object status) {
		return findByProperty(STATUS, status);
	}

	public List findByStartdate(Object startdate) {
		return findByProperty(STARTDATE, startdate);
	}

	public List findByStarttime(Object starttime) {
		return findByProperty(STARTTIME, starttime);
	}

	public List findByViewtime(Object viewtime) {
		return findByProperty(VIEWTIME, viewtime);
	}

	public List findByMessage(Object message) {
		return findByProperty(MESSAGE, message);
	}

	public List findAll() {
		log.debug("finding all Nation instances");
		try {
			String queryString = "from Nation";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Nation merge(Nation detachedInstance) {
		log.debug("merging Nation instance");
		try {
			Nation result = (Nation) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Nation instance) {
		log.debug("attaching dirty Nation instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Nation instance) {
		log.debug("attaching clean Nation instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static NationDAO getFromApplicationContext(ApplicationContext ctx) {
		return (NationDAO) ctx.getBean("NationDAO");
	}
}
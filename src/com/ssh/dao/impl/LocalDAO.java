package com.ssh.dao.impl;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.ssh.model.Local;

/**
 * A data access object (DAO) providing persistence and search support for Local
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see com.ssh.model.Local
 * @author MyEclipse Persistence Tools
 */

public class LocalDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(LocalDAO.class);
	// property constants
	public static final String DRIVER_ID = "driverId";
	public static final String ROUTER_ID = "routerId";
	public static final String NEED_COUNT = "needCount";
	public static final String IN_COUNT = "inCount";
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

	public void save(Local transientInstance) {
		log.debug("saving Local instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Local persistentInstance) {
		log.debug("deleting Local instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Local findById(java.lang.Integer id) {
		log.debug("getting Local instance with id: " + id);
		try {
			Local instance = (Local) getHibernateTemplate().get(
					"com.ssh.model.Local", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Local instance) {
		log.debug("finding Local instance by example");
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
		log.debug("finding Local instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Local as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByDriverId(Object driverId) {
		return findByProperty(DRIVER_ID, driverId);
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
		log.debug("finding all Local instances");
		try {
			String queryString = "from Local";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Local merge(Local detachedInstance) {
		log.debug("merging Local instance");
		try {
			Local result = (Local) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Local instance) {
		log.debug("attaching dirty Local instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Local instance) {
		log.debug("attaching clean Local instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static LocalDAO getFromApplicationContext(ApplicationContext ctx) {
		return (LocalDAO) ctx.getBean("LocalDAO");
	}
}
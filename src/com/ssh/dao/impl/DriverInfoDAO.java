package com.ssh.dao.impl;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.ssh.model.DriverInfo;

/**
 * A data access object (DAO) providing persistence and search support for
 * DriverInfo entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.ssh.model.DriverInfo
 * @author MyEclipse Persistence Tools
 */

public class DriverInfoDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(DriverInfoDAO.class);
	// property constants
	public static final String SUCCESS_COUNT = "successCount";
	public static final String SEND_COUNT = "sendCount";
	public static final String SEND_THISMONTH = "sendThismonth";
	public static final String SUPPORT = "support";
	public static final String UNSUPPORT = "unsupport";

	protected void initDao() {
		// do nothing
	}

	public void save(DriverInfo transientInstance) {
		log.debug("saving DriverInfo instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(DriverInfo persistentInstance) {
		log.debug("deleting DriverInfo instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public DriverInfo findById(java.lang.Integer id) {
		log.debug("getting DriverInfo instance with id: " + id);
		try {
			DriverInfo instance = (DriverInfo) getHibernateTemplate().get(
					"com.ssh.model.DriverInfo", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(DriverInfo instance) {
		log.debug("finding DriverInfo instance by example");
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
		log.debug("finding DriverInfo instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from DriverInfo as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findBySuccessCount(Object successCount) {
		return findByProperty(SUCCESS_COUNT, successCount);
	}

	public List findBySendCount(Object sendCount) {
		return findByProperty(SEND_COUNT, sendCount);
	}

	public List findBySendThismonth(Object sendThismonth) {
		return findByProperty(SEND_THISMONTH, sendThismonth);
	}

	public List findBySupport(Object support) {
		return findByProperty(SUPPORT, support);
	}

	public List findByUnsupport(Object unsupport) {
		return findByProperty(UNSUPPORT, unsupport);
	}

	public List findAll() {
		log.debug("finding all DriverInfo instances");
		try {
			String queryString = "from DriverInfo";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public DriverInfo merge(DriverInfo detachedInstance) {
		log.debug("merging DriverInfo instance");
		try {
			DriverInfo result = (DriverInfo) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(DriverInfo instance) {
		log.debug("attaching dirty DriverInfo instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(DriverInfo instance) {
		log.debug("attaching clean DriverInfo instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static DriverInfoDAO getFromApplicationContext(ApplicationContext ctx) {
		return (DriverInfoDAO) ctx.getBean("DriverInfoDAO");
	}
}
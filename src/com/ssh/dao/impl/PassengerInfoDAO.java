package com.ssh.dao.impl;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.ssh.model.PassengerInfo;

/**
 * A data access object (DAO) providing persistence and search support for
 * PassengerInfo entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.ssh.model.PassengerInfo
 * @author MyEclipse Persistence Tools
 */

public class PassengerInfoDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(PassengerInfoDAO.class);
	// property constants
	public static final String TAKE_COUNT = "takeCount";
	public static final String TAKE_THISMONTH = "takeThismonth";
	public static final String APPROVE = "approve";
	public static final String DISAPPROVE = "disapprove";

	protected void initDao() {
		// do nothing
	}

	public void save(PassengerInfo transientInstance) {
		log.debug("saving PassengerInfo instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(PassengerInfo persistentInstance) {
		log.debug("deleting PassengerInfo instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public PassengerInfo findById(java.lang.Integer id) {
		log.debug("getting PassengerInfo instance with id: " + id);
		try {
			PassengerInfo instance = (PassengerInfo) getHibernateTemplate()
					.get("com.ssh.model.PassengerInfo", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(PassengerInfo instance) {
		log.debug("finding PassengerInfo instance by example");
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
		log.debug("finding PassengerInfo instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from PassengerInfo as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByTakeCount(Object takeCount) {
		return findByProperty(TAKE_COUNT, takeCount);
	}

	public List findByTakeThismonth(Object takeThismonth) {
		return findByProperty(TAKE_THISMONTH, takeThismonth);
	}

	public List findByApprove(Object approve) {
		return findByProperty(APPROVE, approve);
	}

	public List findByDisapprove(Object disapprove) {
		return findByProperty(DISAPPROVE, disapprove);
	}

	public List findAll() {
		log.debug("finding all PassengerInfo instances");
		try {
			String queryString = "from PassengerInfo";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public PassengerInfo merge(PassengerInfo detachedInstance) {
		log.debug("merging PassengerInfo instance");
		try {
			PassengerInfo result = (PassengerInfo) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(PassengerInfo instance) {
		log.debug("attaching dirty PassengerInfo instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(PassengerInfo instance) {
		log.debug("attaching clean PassengerInfo instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static PassengerInfoDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (PassengerInfoDAO) ctx.getBean("PassengerInfoDAO");
	}
}
package com.ssh.dao.impl;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.ssh.model.Router;

/**
 * A data access object (DAO) providing persistence and search support for
 * Router entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.ssh.model.Router
 * @author MyEclipse Persistence Tools
 */

public class RouterDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(RouterDAO.class);
	// property constants
	public static final String STARTPOINT = "startpoint";
	public static final String DESTPOINT = "destpoint";
	public static final String STARTCITY = "startcity";
	public static final String DESTCITY = "destcity";
	public static final String PASSBY = "passby";
	public static final String TYPE = "type";

	protected void initDao() {
		// do nothing
	}

	public void save(Router transientInstance) {
		log.debug("saving Router instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Router persistentInstance) {
		log.debug("deleting Router instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Router findById(java.lang.Integer id) {
		log.debug("getting Router instance with id: " + id);
		try {
			Router instance = (Router) getHibernateTemplate().get(
					"com.ssh.model.Router", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Router instance) {
		log.debug("finding Router instance by example");
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
		log.debug("finding Router instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Router as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByStartpoint(Object startpoint) {
		return findByProperty(STARTPOINT, startpoint);
	}

	public List findByDestpoint(Object destpoint) {
		return findByProperty(DESTPOINT, destpoint);
	}

	public List findByStartcity(Object startcity) {
		return findByProperty(STARTCITY, startcity);
	}

	public List findByDestcity(Object destcity) {
		return findByProperty(DESTCITY, destcity);
	}

	public List findByPassby(Object passby) {
		return findByProperty(PASSBY, passby);
	}

	public List findByType(Object type) {
		return findByProperty(TYPE, type);
	}

	public List findAll() {
		log.debug("finding all Router instances");
		try {
			String queryString = "from Router";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Router merge(Router detachedInstance) {
		log.debug("merging Router instance");
		try {
			Router result = (Router) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Router instance) {
		log.debug("attaching dirty Router instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Router instance) {
		log.debug("attaching clean Router instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static RouterDAO getFromApplicationContext(ApplicationContext ctx) {
		return (RouterDAO) ctx.getBean("RouterDAO");
	}
}
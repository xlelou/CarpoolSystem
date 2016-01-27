package com.ssh.dao.impl;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.ssh.model.Message;

/**
 	* A data access object (DAO) providing persistence and search support for Message entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see com.ssh.model.Message
  * @author MyEclipse Persistence Tools 
 */

public class MessageDAO extends HibernateDaoSupport  {
		 private static final Log log = LogFactory.getLog(MessageDAO.class);
		//property constants
	public static final String FROM_ID = "fromId";
	public static final String TO_ID = "toId";
	public static final String STATUS = "status";
	public static final String CREATETIME = "createtime";
	public static final String TITLE = "title";
	public static final String DETAIL = "detail";



	protected void initDao() {
		//do nothing
	}
    
    public void save(Message transientInstance) {
        log.debug("saving Message instance");
        try {
            getHibernateTemplate().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(Message persistentInstance) {
        log.debug("deleting Message instance");
        try {
            getHibernateTemplate().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public Message findById( java.lang.Integer id) {
        log.debug("getting Message instance with id: " + id);
        try {
            Message instance = (Message) getHibernateTemplate()
                    .get("com.ssh.model.Message", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(Message instance) {
        log.debug("finding Message instance by example");
        try {
            List results = getHibernateTemplate().findByExample(instance);
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }    
    
    public List findByProperty(String propertyName, Object value) {
      log.debug("finding Message instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from Message as model where model." 
         						+ propertyName + "= ?";
		 return getHibernateTemplate().find(queryString, value);
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByFromId(Object fromId
	) {
		return findByProperty(FROM_ID, fromId
		);
	}
	
	public List findByToId(Object toId
	) {
		return findByProperty(TO_ID, toId
		);
	}
	
	public List findByStatus(Object status
	) {
		return findByProperty(STATUS, status
		);
	}
	
	public List findByCreatetime(Object createtime
	) {
		return findByProperty(CREATETIME, createtime
		);
	}
	
	public List findByTitle(Object title
	) {
		return findByProperty(TITLE, title
		);
	}
	
	public List findByDetail(Object detail
	) {
		return findByProperty(DETAIL, detail
		);
	}
	

	public List findAll() {
		log.debug("finding all Message instances");
		try {
			String queryString = "from Message";
		 	return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public Message merge(Message detachedInstance) {
        log.debug("merging Message instance");
        try {
            Message result = (Message) getHibernateTemplate()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Message instance) {
        log.debug("attaching dirty Message instance");
        try {
            getHibernateTemplate().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(Message instance) {
        log.debug("attaching clean Message instance");
        try {
            getHibernateTemplate().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

	public static MessageDAO getFromApplicationContext(ApplicationContext ctx) {
    	return (MessageDAO) ctx.getBean("MessageDAO");
	}
}
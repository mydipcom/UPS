package com.bps.dao;

// Generated Oct 29, 2014 11:20:20 AM by Hibernate Tools 3.4.0.CR1

import java.util.List;

import javax.naming.InitialContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;

import com.bps.dto.TpointRuleLog;

/**
 * Home object for domain model class TpointRuleLog.
 * @see com.bps.dto.TpointRuleLog
 * @author Hibernate Tools
 */
public class PointRuleLogDao {

	private static final Log log = LogFactory.getLog(PointRuleLogDao.class);

	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext()
					.lookup("SessionFactory");
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException(
					"Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(TpointRuleLog transientInstance) {
		log.debug("persisting TpointRuleLog instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(TpointRuleLog instance) {
		log.debug("attaching dirty TpointRuleLog instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TpointRuleLog instance) {
		log.debug("attaching clean TpointRuleLog instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(TpointRuleLog persistentInstance) {
		log.debug("deleting TpointRuleLog instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TpointRuleLog merge(TpointRuleLog detachedInstance) {
		log.debug("merging TpointRuleLog instance");
		try {
			TpointRuleLog result = (TpointRuleLog) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public TpointRuleLog findById(java.lang.Integer id) {
		log.debug("getting TpointRuleLog instance with id: " + id);
		try {
			TpointRuleLog instance = (TpointRuleLog) sessionFactory
					.getCurrentSession().get("com.bps.dto.TpointRuleLog", id);
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TpointRuleLog instance) {
		log.debug("finding TpointRuleLog instance by example");
		try {
			List results = sessionFactory.getCurrentSession()
					.createCriteria("com.bps.dto.TpointRuleLog")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}

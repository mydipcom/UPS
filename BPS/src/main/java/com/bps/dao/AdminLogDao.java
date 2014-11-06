package com.bps.dao;

// Generated Oct 29, 2014 11:20:20 AM by Hibernate Tools 3.4.0.CR1

import java.util.List;

import javax.naming.InitialContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;

import com.bps.dto.TadminLog;

/**
 * Home object for domain model class TadminLog.
 * @see com.bps.dto.TadminLog
 * @author Hibernate Tools
 */
public class AdminLogDao {

	private static final Log log = LogFactory.getLog(AdminLogDao.class);

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

	public void persist(TadminLog transientInstance) {
		log.debug("persisting TadminLog instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(TadminLog instance) {
		log.debug("attaching dirty TadminLog instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TadminLog instance) {
		log.debug("attaching clean TadminLog instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(TadminLog persistentInstance) {
		log.debug("deleting TadminLog instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TadminLog merge(TadminLog detachedInstance) {
		log.debug("merging TadminLog instance");
		try {
			TadminLog result = (TadminLog) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public TadminLog findById(java.lang.Integer id) {
		log.debug("getting TadminLog instance with id: " + id);
		try {
			TadminLog instance = (TadminLog) sessionFactory
					.getCurrentSession().get("com.bps.dto.TadminLog", id);
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

	public List findByExample(TadminLog instance) {
		log.debug("finding TadminLog instance by example");
		try {
			List results = sessionFactory.getCurrentSession()
					.createCriteria("com.bps.dto.TadminLog")
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

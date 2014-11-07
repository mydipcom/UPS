package com.bps.dao;

// Generated Oct 29, 2014 11:20:20 AM by Hibernate Tools 3.4.0.CR1

import java.util.List;

import javax.naming.InitialContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bps.dto.TpointRule;

/**
 * Home object for domain model class TpointRule.
 * @see com.bps.dto.TpointRule
 * @author Hibernate Tools
 */
@Repository
public class PointRuleDao {

	private static final Log log = LogFactory.getLog(PointRuleDao.class);

	@Autowired
	private SessionFactory sessionFactory;
	
	public void persist(TpointRule transientInstance) {
		log.debug("persisting TpointRule instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(TpointRule instance) {
		log.debug("attaching dirty TpointRule instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TpointRule instance) {
		log.debug("attaching clean TpointRule instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(TpointRule persistentInstance) {
		log.debug("deleting TpointRule instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TpointRule merge(TpointRule detachedInstance) {
		log.debug("merging TpointRule instance");
		try {
			TpointRule result = (TpointRule) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public TpointRule findById(java.lang.Integer id) {
		log.debug("getting TpointRule instance with id: " + id);
		try {
			TpointRule instance = (TpointRule) sessionFactory
					.getCurrentSession().get("com.bps.dto.TpointRule", id);
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

	public List findByExample(TpointRule instance) {
		log.debug("finding TpointRule instance by example");
		try {
			List results = sessionFactory.getCurrentSession()
					.createCriteria("com.bps.dto.TpointRule")
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

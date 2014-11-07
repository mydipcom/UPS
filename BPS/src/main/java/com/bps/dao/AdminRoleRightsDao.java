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

import com.bps.dto.TadminRoleRights;

/**
 * Home object for domain model class TadminRoleRights.
 * @see com.bps.dto.TadminRoleRights
 * @author Hibernate Tools
 */
@Repository
public class AdminRoleRightsDao {

	private static final Log log = LogFactory
			.getLog(AdminRoleRightsDao.class);
    @Autowired
	private SessionFactory sessionFactory;
    
	public void persist(TadminRoleRights transientInstance) {
		log.debug("persisting TadminRoleRights instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(TadminRoleRights instance) {
		log.debug("attaching dirty TadminRoleRights instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TadminRoleRights instance) {
		log.debug("attaching clean TadminRoleRights instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(TadminRoleRights persistentInstance) {
		log.debug("deleting TadminRoleRights instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TadminRoleRights merge(TadminRoleRights detachedInstance) {
		log.debug("merging TadminRoleRights instance");
		try {
			TadminRoleRights result = (TadminRoleRights) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public TadminRoleRights findById(int id) {
		log.debug("getting TadminRoleRights instance with id: " + id);
		try {
			TadminRoleRights instance = (TadminRoleRights) sessionFactory
					.getCurrentSession().get("com.bps.dto.TadminRoleRights",
							id);
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

	public List findByExample(TadminRoleRights instance) {
		log.debug("finding TadminRoleRights instance by example");
		try {
			List results = sessionFactory.getCurrentSession()
					.createCriteria("com.bps.dto.TadminRoleRights")
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

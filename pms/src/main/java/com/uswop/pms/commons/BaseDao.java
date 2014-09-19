package com.uswop.pms.commons;

import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;


/**
 * 
 * 
 * @author    Phills
 */
public class BaseDao<T> extends HibernateDaoSupport
{    	

    public BaseDao()
    {        
    }

    public void save(T t)
    {
        this.getHibernateTemplate().save(t);
    }
    
    public void create(T t)
    {
        getHibernateTemplate().save(t);
    }

    public void update(T t)
    {
        getHibernateTemplate().update(t);
    }

    public void delete(T t)
    {
        getHibernateTemplate().delete(t);
    }    

    
    public void merge(T t)
    {
        this.getHibernateTemplate().merge(t);
    }

   
    @SuppressWarnings("unchecked")
	public List<T> findByNamedQuery(String hqlName)
    {
        Query query = this.getSessionFactory().openSession().getNamedQuery(hqlName);

        return query.list();
    }
    
    @SuppressWarnings("unchecked")
	public T findByNamedQuery(String hqlName,Object param)
    {
        Query query = this.getSessionFactory().openSession().getNamedQuery(hqlName);
        query.setParameter(0, param);
        return (T)query.uniqueResult();
    }
    
    public void saveByNamedQuery(String hqlName,Object[] params){
    	Query query = this.getSessionFactory().openSession().getNamedQuery(hqlName);
    	for (int i = 0; i < params.length; i++ )
        {
            query.setParameter(i, params[i]);
        }
        query.executeUpdate();
    }
    
    public void updateByNamedQuery(String hqlName,Object[] params){
    	Query query = this.getSessionFactory().openSession().getNamedQuery(hqlName);
    	for (int i = 0; i < params.length; i++ )
        {
            query.setParameter(i, params[i]);
        }
        query.executeUpdate();
    }
    
}

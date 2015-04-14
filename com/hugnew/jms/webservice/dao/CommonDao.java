/**  
* @Title: CommonDao.java 
* @Package 
* @author Martin
* @date 2015-1-20 上午9:36:17 
* @version V1.0
* @Copyright: Copyright (c) Co.Ltd. 2015
*/ 
package com.hugnew.jms.webservice.dao;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

/** 
 * @ClassName: CommonDao 
 * @author Martin
 * @date 2015-1-20 上午9:36:17
 * @version V1.0 
 */
@Service("commonDao")
@Transactional
public class CommonDao {
	private static final Logger logger = Logger.getLogger(CommonDao.class);
	
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	public Session getSession() {
		// 事务必须是开启的(Required)，否则获取不到
		return sessionFactory.getCurrentSession();
	}
	
	/**
	 * 
	* @Title: createCriteria 
	* @param @param entityClass
	* @param @param criterions
	* @param @return
	* @return Criteria
	* @author Martin
	* @date 2015-1-20 上午9:46:28
	* @version V1.0
	 */
	private <T> Criteria createCriteria(Class<T> entityClass, Criterion... criterions) {
		Criteria criteria = getSession().createCriteria(entityClass);
		for (Criterion c : criterions) {
			criteria.add(c);
		}
		return criteria;
	}
	
	public <T> List<T> findByProperty(Class<T> entityClass, String propertyName, Object value) {
		Assert.hasText(propertyName);
		return (List<T>) createCriteria(entityClass, Restrictions.eq(propertyName, value)).list();
	}
	
	public <T> List<T> findByPropertys(Class<T> entityClass, Criterion... criterions) {
		return (List<T>) createCriteria(entityClass, criterions).list();
	}
	
	/**
	 * 根据Id获取对象。
	 */
	public <T> T get(Class<T> entityClass, final Serializable id) {
		return (T) getSession().get(entityClass, id);
	}
	
	/**
	 * 通过hql 查询语句查找对象
	 * 
	 * @param <T>
	 * @param query
	 * @return
	 */
	public <T> List<T> findByQueryString(final String query) {

		Query queryObject = getSession().createQuery(query);
		List<T> list = queryObject.list();
		if (list.size() > 0) {
			getSession().flush();
		}
		return list;

	}
	
	/**
	 * 通过sql查询语句查找对象
	 * 
	 * @param <T>
	 * @param query
	 * @return
	 */
	public <T> List<T> findListbySql(final String sql) {
		Query querys = getSession().createSQLQuery(sql);
		return querys.list();
	}
	
	public <T> List<T> loadAll(final Class<T> entityClass) {
		Criteria criteria = createCriteria(entityClass);
		return criteria.list();
	}
	
	public <T> List<T> findByDetached(DetachedCriteria dc) {
		return dc.getExecutableCriteria(getSession()).list();
	}
	
	/**
	 * 
	 * @Title: findByDetached 
	 * @Description: 如果maxsize没有值，默认最多查询50条记录
	 * @param @param dc
	 * @param @param maxsize
	 * @return List<T>
	 * @author Martin
	 * @date 2015-1-21 下午2:39:27
	 * @version V1.0
	 */
	public <T> List<T> findByDetached(DetachedCriteria dc, Integer maxsize) {
		if(maxsize == null || (int)maxsize==0){
			maxsize = 50;
		}
		Criteria criteria = dc.getExecutableCriteria(getSession());
		criteria.setFirstResult(0);
		criteria.setMaxResults(maxsize);
		return criteria.list();
	}
	
	/**
	 * 根据传入的实体持久化对象
	 */
	public <T> Serializable save(T entity) {
		try {
			Serializable id = getSession().save(entity);
			getSession().flush();
			if (logger.isDebugEnabled()) {
				logger.debug("保存实体成功," + entity.getClass().getName());
			}
			return id;
		} catch (RuntimeException e) {
			logger.error("保存实体异常", e);
			throw e;
		}
	}
	
	public <T> void saveOrUpdate(T entity) {
		try {
			getSession().saveOrUpdate(entity);
			getSession().flush();
			if (logger.isDebugEnabled()) {
				logger.debug("添加或更新成功," + entity.getClass().getName());
			}
		} catch (RuntimeException e) {
			logger.error("添加或更新异常", e);
			throw e;
		}
	}
	
	/**
	 * 根据传入的实体删除对象
	 */
	public <T> void delete(T entity) {
		try {
			getSession().delete(entity);
			getSession().flush();
			if (logger.isDebugEnabled()) {
				logger.debug("删除成功," + entity.getClass().getName());
			}
		} catch (RuntimeException e) {
			logger.error("删除异常", e);
			throw e;
		}
	}
	
	/**
	 * 根据主键删除指定的实体
	 * 
	 * @param <T>
	 * @param pojo
	 */
	public <T> void deleteEntityById(Class entityName, Serializable id) {
		delete(get(entityName, id));
		getSession().flush();
	}
	
}

package com.nbw.common;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.nbw.lucene.domain.Doctxt;

public class GenericDAO<T, PK extends Serializable> extends HibernateDaoSupport {
	static final Log log = LogFactory.getLog(GenericDAO.class);
	protected Class<T> entityClass;
	protected String className;

	@SuppressWarnings("unchecked")
	public GenericDAO() {
		// protected void initDao() {
		this.entityClass = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
		className = entityClass.getSimpleName();
	}

	public boolean delete(T t) {
		log.debug("deleting " + className + " instance");
		boolean success = false;
		try {
			getSession().delete(t);
			log.debug("delete successful");
			success = true;
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
		return success;
	}

	public boolean delete(PK id) {
		log.debug("deleting " + className + " instance");
		boolean success = false;
		try {
			getSession().delete(getSession().load(entityClass, id));
			log.debug("delete successful");
			success = true;
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
		return success;
	}

	public boolean deleteByHQL(String hql, Object... values) {
		return executeHQL(hql, values);
	}

	public boolean executeHQL(String hql, Object... values) {
		log.debug("executing HQL: " + hql);
		boolean success = false;
		try {
			Query queryObject = getSession().createQuery(hql);
			if (values != null && values.length > 0) {
				for (int i = 0; i < values.length; i++) {
					queryObject.setParameter(i, values[i]);
				}
			}
			queryObject.executeUpdate();
			log.debug("execute successful");
			success = true;
		} catch (RuntimeException re) {
			log.error("execute failed", re);
			throw re;
		}
		return success;
	}

	public boolean executeSQL(String sql, Object... values) {
		log.debug("executing HQL: " + sql);
		boolean success = false;
		try {
			SQLQuery queryObject = getSession().createSQLQuery(sql);
			if (values != null && values.length > 0) {
				for (int i = 0; i < values.length; i++) {
					queryObject.setParameter(i, values[i]);
				}
			}
			queryObject.executeUpdate();
			log.debug("execute successful");
			success = true;
		} catch (RuntimeException re) {
			log.error("execute failed", re);
			throw re;
		}
		return success;
	}

	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		log.debug("finding all " + className + " instances");
		try {
			String queryString = "from " + className;
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	public List<T> findAllDocByGid(Integer gid)
	{
		log.debug("finding all " + className + " instances by gid");
		try {
			String queryString = "from Doctxt d where d.gid ="+gid;
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find  failed", re);
			throw re;
		}
		
	}
	public List<?> findByHQL(String hql, Object... values) {
		log.debug("finding HQL: " + hql);
		try {
			Query queryObject = getSession().createQuery(hql);
			if (values != null && values.length > 0) {
				for (int i = 0; i < values.length; i++) {
					queryObject.setParameter(i, values[i]);
				}
			}
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find HQL failed", re);
			throw re;
		}
	}


	
	
	public List<?> findBySQL(String sql, Object... values) {
		log.debug("finding SQL: " + sql);
		try {
			SQLQuery queryObject = getSession().createSQLQuery(sql);
			if (values != null && values.length > 0) {
				for (int i = 0; i < values.length; i++) {
					queryObject.setParameter(i, values[i]);
				}
			}
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find HQL failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public T findByID(PK id) {
		log.debug("getting " + className + " instance with id: " + id);
		try {
			T instance = (T) getSession().get(entityClass, id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<T> findByProperty(String propertyName, Object value) {
		log.debug("finding " + className + " instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from " + className + " as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<T> findPager(int... rowStartIdxAndCount) {
		log.debug("finding pager " + className + " instances");
		try {
			String queryString = "from " + className;
			int rowStartIdx = 0;
			int rowCount = 0;
			if (rowStartIdxAndCount != null && rowStartIdxAndCount.length > 0) {
				rowStartIdx = Math.max(0, rowStartIdxAndCount[0]);
				if (rowStartIdxAndCount.length > 1)
					rowCount = Math.max(0, rowStartIdxAndCount[1]);
			}
			return (List<T>) findPagerByHQL(queryString, rowStartIdx, rowCount);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List<Doctxt> getListbyPmidList(List<String> list)
	{
		
		
		try
		{
			String queryString = " from " + className + " as model where model.pmid in(:list)";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameterList("list", list);
		return 	queryObject.list();
		}
		catch(RuntimeException e)
		{
			throw e;
		}
		
	}
	

	public List<?> findPagerByHQL(String hql, int rowStartIdx, int rowCount,
			Object... values) {
		log.debug("finding pager HQL: " + hql);
		try {
			Query queryObject = getSession().createQuery(hql);
			if (rowStartIdx != -1 && rowCount != -1) {
				queryObject.setFirstResult(rowStartIdx);
				queryObject.setMaxResults(rowCount);
			}
			if (values != null && values.length > 0) {
				for (int i = 0; i < values.length; i++) {
					queryObject.setParameter(i, values[i]);
				}
			}
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find pager HQL failed", re);
			throw re;
		}
	}

	
	
	
	
	
	
	public List<?> findPagerBySQL(String sql, int rowStartIdx, int rowCount) {
		log.debug("finding pager SQL: " + sql);
		try {
			int lastIdx = rowStartIdx + rowCount;
			int startIdx = rowStartIdx + 1;
			String sqlStr = "SELECT * "
					+ "FROM (SELECT a.*, ROWNUM row_num FROM (" + sql
					+ " ) a) b " + "WHERE b.row_num BETWEEN " + startIdx
					+ " AND " + lastIdx;
			Query queryObject = getSession().createSQLQuery(sqlStr);
			
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find pager SQL failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<T> findPagerByProperty(String propertyName, Object value,
			int... rowStartIdxAndCount) {
		log.debug("finding pager " + className + " instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from " + className + " as model where model."
					+ propertyName + "= ?";
			int rowStartIdx = 0;
			int rowCount = 0;
			if (rowStartIdxAndCount != null && rowStartIdxAndCount.length > 0) {
				rowStartIdx = Math.max(0, rowStartIdxAndCount[0]);
				if (rowStartIdxAndCount.length > 1)
					rowCount = Math.max(0, rowStartIdxAndCount[1]);
			}
			return (List<T>) findPagerByHQL(queryString, rowStartIdx, rowCount,
					value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public Class<T> getEntityClass() {
		return entityClass;
	}

	public boolean save(T t) {
		log.debug("saving " + className + " instance");
		boolean success = false;
		try {
			this.getHibernateTemplate().save(t);
			log.debug("save successful");
			success = true;
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		return success;
	}

	public boolean saveOrUpdate(T t) {
		log.debug("saving " + className + " instance");
		boolean success = false;
		try {
			getSession().saveOrUpdate(t);
			log.debug("save successful");
			success = true;
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		return success;
	}

	public <E> E singleResult(Collection<E> results) {
		if (results == null || results.isEmpty())
			return null;
		if (results.size() > 1)
			throw new IllegalArgumentException("the List size is more than 1");
		return results.iterator().next();
	}

	public boolean update(T t) {
		log.debug("updating " + className + " instance");
		boolean success = false;
		try {
			getSession().update(t);
			log.debug("update successful");
			success = true;
		} catch (RuntimeException re) {
			log.error("update failed", re);
			throw re;
		}
		return success;
	}

	public boolean updateByHQL(String hql, Object... values) {
		return executeHQL(hql, values);
	}

	public void attachClean(T t) {
		log.debug("attaching dirty " + className + " instance");
		try {
			getSession().saveOrUpdate(t);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachDirty(T t) {
		log.debug("attaching clean " + className + " instance");
		try {
			getSession().lock(t, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	
	
	
	
	
}

package com.meigu.community.util.persistence.dao;

import java.io.*;
import java.math.BigInteger;
import java.util.*;
import java.util.regex.*;

import org.hibernate.*;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.meigu.community.util.persistence.model.Paginate;

@Repository
@SuppressWarnings({"unchecked"})
public class BaseDao<T> {

	@Autowired
	private SessionFactory sessionFactory;

	protected Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}
	
	/**
	 * 供无web请求时调用该方法,普通web请求使用该方式获取session,调用完记得调用 closeSession(Session session) 方法关闭session
	 * @return
	 */
	public Session openSession(){
		return sessionFactory.openSession();
	}
	/**
	 * 调用 openSession() 后，调用该方法关闭session
	 * @param session
	 */
	public void closeSession(Session session){
		session.close();
	}

	public Serializable save(T o) throws Exception {
		return this.getCurrentSession().save(o);
	}

	public void delete(T o) {
		this.getCurrentSession().delete(o);
	}

	public void update(T o) {
		this.getCurrentSession().update(o);
	}

	public void saveOrUpdate(T o) {
		this.getCurrentSession().saveOrUpdate(o);
	}

	public List<T> find(String hql) {
		return this.getCurrentSession().createQuery(hql).list();
	}

	public List<T> find(String hql, Map<String,?> param) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (param != null && param.size() > 0) {
			for (String name:param.keySet()) {
				q.setParameter(name, param.get(name));
			}
		}
		return q.list();
	}
	
	
	/**	SQL 查询返回实体Bean
	 * @param sql	 sql语句
	 * @param params 参数map
	 * @param clz    映射实体类class
	 */
	public List<T> find(String sql,Map<String,?> params,Class<T> clz) {
		Query  query = this.getCurrentSession().createSQLQuery(sql);
		if (params != null && params.size() > 0) {
			for (String key : params.keySet()) {
				query.setParameter(key, params.get(key));
			}
		}
		return query.setResultTransformer(Transformers.aliasToBean(clz)).list();
	}

	public T get(Class<T> c, Serializable id) {
		return (T) this.getCurrentSession().get(c, id);
	}

	public T get(String hql, Map<String,Object> param) {
		List<T> l = this.find(hql, param);
		if (l != null && l.size() > 0) {
			return l.get(0);
		} else {
			return null;
		}
	}

	public Long count(String hql) {
		return (Long) this.getCurrentSession().createQuery(hql).uniqueResult();
	}

	public Long count(String hql, Map<String, Object> param) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (param != null && param.size() > 0) {
			for (String key:param.keySet()) {
				q.setParameter(key, param.get(key));
			}
		}
		return (Long) q.uniqueResult();
	}

	public Integer executeHql(String hql) {
		return this.getCurrentSession().createQuery(hql).executeUpdate();
	}

	public Integer executeHql(String hql, Map<String, Object> param) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (param != null && param.size() > 0) {
			for (String key:param.keySet()) {
				q.setParameter(key, param.get(key));
			}
		}
		return q.executeUpdate();
	}

	public Paginate query(String hql, Map<String, Object> q, Integer cpage,
			Integer pageSize) {
		
		if(cpage==null || pageSize==null){
			List<T> list=this.find(hql,q);
			if(list == null || list.size() == 0)return new Paginate();
			else return new Paginate(list, list.size(),list.size(), 0);
		}
		
		
		
		Long totalCount = 0l;

		String hql2 = "";
		hql2 = removeOrders(hql);
		hql2 = removeSelect(hql2);

		hql2 = "select count(*) " + hql2;


		Query query = this.getCurrentSession().createQuery(hql2);

		if (null != q) {
			for (Map.Entry<String, Object> k : q.entrySet()) {
				try {
					query.setParameter(k.getKey(), k.getValue());
				} catch (Exception e) {
				}
			}
		}
		totalCount = (Long) query.uniqueResult();

		Query query1 = this.getCurrentSession().createQuery(hql);
		if (q != null) {
			for (Map.Entry<String, Object> k : q.entrySet()) {
				query1.setParameter(k.getKey(), k.getValue());
			}
		}

		List<?> list = query1.setFirstResult((cpage - 1) * pageSize)
				.setMaxResults(pageSize).list();
		return new Paginate(list, Integer.valueOf(totalCount.intValue()),
				Integer.valueOf(pageSize), Integer.valueOf(cpage));
	}

	/**SQL查询分页*/
	public Paginate sqlQuery(String sql,Map<String, Object> params,
				Integer cpage,Integer pageSize,Class<T> clz){
		String 	countSql	="";
		Long	totalCount	=0L;
		if (cpage==null||pageSize==null) {
			List<T> list=this.find(sql, params, clz);
			if (list!=null) {
				return new Paginate(list, list.size(),list.size(), 0);
			}else{
				return new Paginate();
			}
		}else{
			countSql= removeOrders(sql);
			countSql= removeSelect(countSql);
			countSql=String.format("select count(*) %s", countSql);
			Query countQuery = this.getCurrentSession().createSQLQuery(countSql);
			if (params!=null) {
				for (String key : params.keySet()) {
					countQuery.setParameter(key, params.get(key));
				}
			}
			totalCount =((BigInteger)countQuery.uniqueResult()).longValue();
			
			Query listQuery = this.getCurrentSession().createSQLQuery(sql);
			if (params!=null) {
				for (String key : params.keySet()) {
					listQuery.setParameter(key, params.get(key));
				}
			}
			listQuery.setFirstResult((cpage-1)*pageSize).setMaxResults(pageSize);
			listQuery.setResultTransformer(Transformers.aliasToBean(clz));
			List<?> list =listQuery.list();
			return new Paginate(list, Integer.valueOf(totalCount.intValue()),pageSize,cpage);
		}
	}
	
	private static String removeOrders(String hql) {
		Pattern p = Pattern.compile("order\\s*by[\\w|\\W|\\s|\\S]*", 2);
		Matcher m = p.matcher(hql);
		StringBuffer sb = new StringBuffer();
		while (m.find())
			m.appendReplacement(sb, "");

		m.appendTail(sb);

		p = Pattern.compile("group\\s*by[\\w|\\W|\\s|\\S]*", 2);
		m = p.matcher(hql);
		sb = new StringBuffer();
		while (m.find())
			m.appendReplacement(sb, "");

		m.appendTail(sb);

		return sb.toString();
	}

	private static String removeSelect(String hql) {
		int beginPos = hql.toLowerCase().indexOf("from");
		return hql.substring(beginPos);
	}
	
}

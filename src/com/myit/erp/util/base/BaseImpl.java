package com.myit.erp.util.base;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public abstract class BaseImpl<T> extends HibernateDaoSupport {
	private Class<T> cs;

	public BaseImpl() {
		super();
		Type type=this.getClass().getGenericSuperclass();
		Type [] ptype = ((ParameterizedType)type).getActualTypeArguments();
		cs=(Class)ptype[0];
	}
	
	
	public void save(T t){
		this.getHibernateTemplate().save(t);
	}
	public void update(T t){
		this.getHibernateTemplate().update(t);
	}
	public void delete(T t){
		this.getHibernateTemplate().delete(t);
	}
	public List<T> getAll(){
		DetachedCriteria dc = DetachedCriteria.forClass(cs);
		return this.getHibernateTemplate().findByCriteria(dc);
	}
	public T get(Serializable uuid){
		return this.getHibernateTemplate().get(cs, uuid);
	}
	public List<T> getAll(BaseQueryModel model, Integer pageNum,Integer pageCount){
		//System.out.println("6666");
		DetachedCriteria dc = DetachedCriteria.forClass(cs);
		this.doQbc(dc, model);
		return this.getHibernateTemplate().findByCriteria(dc, (pageNum-1)*pageCount, pageCount);
	}
	public Integer getCount(BaseQueryModel model){
		DetachedCriteria dc = DetachedCriteria.forClass(cs);
		dc.setProjection(Projections.rowCount());
		this.doQbc(dc, model);
		List<Long> count = this.getHibernateTemplate().findByCriteria(dc);
		return count.get(0).intValue();
	}
	
	public abstract void doQbc(DetachedCriteria dc,BaseQueryModel model);
}

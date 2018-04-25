package com.myit.erp.auth.res.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.myit.erp.auth.res.dao.dao.ResDao;
import com.myit.erp.auth.res.vo.ResModel;
import com.myit.erp.auth.res.vo.ResQueryModel;
import com.myit.erp.util.base.BaseImpl;
import com.myit.erp.util.base.BaseQueryModel;

public class ResImpl extends BaseImpl<ResModel> implements ResDao{

	public void doQbc(DetachedCriteria dc,BaseQueryModel qm){
		ResQueryModel rqm = (ResQueryModel)qm;
		// TODO 添加自定义查询条件
	}

	@Override
	public List<ResModel> getAllByEmpUuid(Long uuid) {
		// TODO Auto-generated method stub
		String hql="select distinct res from EmpModel em join em.roles rm join rm.reses res where em.uuid=?";
		return this.getHibernateTemplate().find(hql, uuid);
	}

}

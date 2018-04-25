package com.myit.erp.auth.dep.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.myit.erp.auth.dep.dao.dao.DepDao;
import com.myit.erp.auth.dep.vo.DepModel;
import com.myit.erp.auth.dep.vo.DepQueryModel;
import com.myit.erp.util.base.BaseImpl;
import com.myit.erp.util.base.BaseQueryModel;

public class DepDaoImpl extends BaseImpl<DepModel> implements DepDao {

	@Override
	public void doQbc(DetachedCriteria dc, BaseQueryModel model) {
		// TODO Auto-generated method stub
		DepQueryModel dqm = (DepQueryModel)model;
		if(dqm.getName()!=null && dqm.getName().trim().length()>0){
			dc.add(Restrictions.like("name", "%"+dqm.getName().trim()+"%"));
		}
		
		if(dqm.getTele()!=null && dqm.getTele().trim().length()>0){
			dc.add(Restrictions.like("tele", "%"+dqm.getTele().trim()+"%"));
		}
	}

}

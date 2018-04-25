package com.myit.erp.auth.emp.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.myit.erp.auth.emp.dao.dao.EmpDao;
import com.myit.erp.auth.emp.vo.EmpModel;
import com.myit.erp.auth.emp.vo.EmpQueryModel;
import com.myit.erp.util.base.BaseImpl;
import com.myit.erp.util.base.BaseQueryModel;

public class EmpDaoImpl extends BaseImpl<EmpModel> implements EmpDao {

	
	@Override
	public EmpModel getByUserNameAndPwd(String userName, String pwd) {
		String hql ="from EmpModel where userName = ? and pwd = ?";
		List<EmpModel> temp = this.getHibernateTemplate().find(hql,userName,pwd);
		return	temp.size()>0 ? temp.get(0):null; 
	}

	@Override
	public void doQbc(DetachedCriteria dc, BaseQueryModel model) {
		
		EmpQueryModel eqm = (EmpQueryModel)model;
		if(eqm.getUserName()!=null && eqm.getUserName().trim().length()>0){
			dc.add(Restrictions.eq("userName", eqm.getUserName().trim()));
		}
		if(eqm.getName()!=null && eqm.getName().trim().length()>0){
			dc.add(Restrictions.like("name", "%"+eqm.getName().trim()+"%"));
		}
		if(eqm.getTele()!=null && eqm.getTele().trim().length()>0){
			dc.add(Restrictions.like("tele", "%"+eqm.getTele().trim()+"%"));
		}
		if(eqm.getGender()!=null && eqm.getGender()!= -1){
			dc.add(Restrictions.eq("gender", eqm.getGender()));
		}
		if(eqm.getEmail()!=null && eqm.getEmail().trim().length()>0){
			dc.add(Restrictions.like("email", "%"+eqm.getEmail().trim()+"%"));
		}
		
		//birthday
		if(eqm.getBirthday()!=null){
			dc.add(Restrictions.ge("birthday", eqm.getBirthday()));
		}
		//birthday2
		if(eqm.getBirthday2()!=null){
			dc.add(Restrictions.le("birthday", eqm.getBirthday2()+86400000-1));
		}
		
		
	}

	@Override
	public List<EmpModel> getAllByDepUuid(Long uuid) {
		// TODO Auto-generated method stub
		String hql="select e  from EmpModel e where dm.uuid=?";
		return this.getHibernateTemplate().find(hql,uuid);
	}

	



	

}

package com.myit.erp.auth.menu.dao.impl;

import java.util.Arrays;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.myit.erp.auth.menu.dao.dao.MenuDao;
import com.myit.erp.auth.menu.vo.MenuModel;
import com.myit.erp.auth.menu.vo.MenuQueryModel;
import com.myit.erp.util.base.BaseImpl;
import com.myit.erp.util.base.BaseQueryModel;

public class MenuImpl extends BaseImpl<MenuModel> implements MenuDao{

	public void doQbc(DetachedCriteria dc,BaseQueryModel qm){
		MenuQueryModel mqm = (MenuQueryModel)qm;
		// TODO 添加自定义查询条件
		//设置过滤掉系统菜单的条件
				//uuid不等于1
				dc.add(Restrictions.not(Restrictions.eq("uuid", MenuModel.MENU_SYSTEM_MENU_UUID)));
				if(mqm.getName()!=null && mqm.getName().trim().length()>0){
					dc.add(Restrictions.like("name", "%"+mqm.getName().trim()+"%"));
				}
				//mqm.parent.uuid
				if(mqm.getParent()!=null && mqm.getParent().getUuid()!=null && mqm.getParent().getUuid()!=-1){
					dc.add(Restrictions.eq("parent", mqm.getParent()));
				}
	}

	@Override
	public List<MenuModel> getByPuuidIsOneOrZero() {
		// TODO Auto-generated method stub
		String hql="from MenuModel where parent.uuid=? or uuid=?";
		return this.getHibernateTemplate().find(hql,MenuModel.MENU_SYSTEM_MENU_UUID,MenuModel.MENU_SYSTEM_MENU_UUID);
	}

	@Override
	public List<MenuModel> getAllOneLevelByEmpUuid(Long uuid) {
		// TODO Auto-generated method stub
		//menu-->role-->emp
		// emp-> roles-> menues
				// menu -> role -> emp
				String hql = "select distinct  mm  from  MenuModel  mm  inner join mm.roles rs inner join  rs.emps ep where ep.uuid=? and mm.parent.uuid=?";
				return this.getHibernateTemplate().find(hql, uuid,
						MenuModel.MENU_SYSTEM_MENU_UUID);
	}

	@Override
	public List<MenuModel> getByEmpUuidAndPuuid(Long uuid, Long puuid) {
		// TODO Auto-generated method stub
				String hql = "select distinct  mm  from  MenuModel  mm  inner join mm.roles rs inner join  rs.emps ep where ep.uuid=? and mm.parent.uuid=?";

				return this.getHibernateTemplate().find(hql, uuid, puuid);
	}

}

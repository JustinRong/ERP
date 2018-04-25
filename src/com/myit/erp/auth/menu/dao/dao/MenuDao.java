package com.myit.erp.auth.menu.dao.dao;

import java.util.List;

import com.myit.erp.auth.menu.vo.MenuModel;
import com.myit.erp.util.base.BaseDao;

public interface MenuDao extends BaseDao<MenuModel> {
	//获取顶级和一级菜单
	public List<MenuModel> getByPuuidIsOneOrZero();
	
	//根据员工编号取该员工可操作的一级菜单
	public List<MenuModel> getAllOneLevelByEmpUuid(Long uuid);
	
	//根据员工编号取该员工可操作的一级菜单
	public List<MenuModel> getByEmpUuidAndPuuid(Long uuid,Long Puuid);
}

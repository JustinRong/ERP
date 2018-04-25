package com.myit.erp.auth.menu.business.ebi;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.myit.erp.auth.menu.vo.MenuModel;
import com.myit.erp.util.base.BaseEbi;

@Transactional
public interface MenuEbi extends BaseEbi<MenuModel>{
	//获取系统菜单和所有一级菜单
	public List<MenuModel> getAllOneLevel();
	
	//获取指定员工对应的所有操作的一级菜单
	public List<MenuModel> getAllOneLevelByEmp(Long uuid);
	
	//获取指定员工对应的指定一级菜单可操作性的二级菜单
	public List<MenuModel> getByEmpAndPuuid(Long uuid,Long puuid);
	
	//添加员工（附角色）
	public void save(MenuModel mm, Long [] roleUuids);
	
	//更新员工信息（附角色）
	public void update(MenuModel mm, Long [] roleUuids);
}

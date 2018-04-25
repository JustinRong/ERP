package com.myit.erp.auth.menu.business.ebo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.myit.erp.auth.menu.business.ebi.MenuEbi;
import com.myit.erp.auth.menu.dao.dao.MenuDao;
import com.myit.erp.auth.menu.vo.MenuModel;
import com.myit.erp.auth.role.vo.RoleModel;
import com.myit.erp.util.base.BaseQueryModel;

public class MenuEbo implements MenuEbi{
	private MenuDao menuDao;
	public void setMenuDao(MenuDao menuDao) {
		this.menuDao = menuDao;
	}

	public void save(MenuModel mm) {
		menuDao.save(mm);
	}

	public void update(MenuModel mm) {
		menuDao.update(mm);
	}

	public void delete(MenuModel mm) {
		menuDao.delete(mm);
	}

	public MenuModel get(Serializable uuid) {
		return menuDao.get(uuid);
	}

	public List<MenuModel> getAll() {
		return menuDao.getAll();
	}

	public List<MenuModel> getAll(BaseQueryModel qm, Integer pageNum,Integer pageCount) {
		return menuDao.getAll(qm,pageNum,pageCount);
	}

	public Integer getCount(BaseQueryModel qm) {
		return menuDao.getCount(qm);
	}

	@Override
	public List<MenuModel> getAllOneLevel() {
		// TODO Auto-generated method stub
		return menuDao.getByPuuidIsOneOrZero();
	}

	@Override
	public List<MenuModel> getAllOneLevelByEmp(Long uuid) {
		// TODO Auto-generated method stub
		return menuDao.getAllOneLevelByEmpUuid(uuid);
	}

	@Override
	public List<MenuModel> getByEmpAndPuuid(Long uuid, Long puuid) {
		// TODO Auto-generated method stub
		return menuDao.getByEmpUuidAndPuuid(uuid, puuid);
	}

	@Override
	public void save(MenuModel mm, Long[] roleUuids) {
		// TODO Auto-generated method stub
		//array->set->mm
				Set<RoleModel> roles = new HashSet<RoleModel>();
				for(Long uuid:roleUuids){
					RoleModel temp = new RoleModel();
					temp.setUuid(uuid);
					roles.add(temp);
				}
				mm.setRoles(roles);
				menuDao.save(mm);
	}

	@Override
	public void update(MenuModel mm, Long[] roleUuids) {
		// TODO Auto-generated method stub
		MenuModel temp = menuDao.get(mm.getUuid());
		temp.setName(mm.getName());
		temp.setUrl(mm.getUrl());
		
		Set<RoleModel> roles = new HashSet<RoleModel>();
		for(Long uuid:roleUuids){
			RoleModel temp2 = new RoleModel();
			temp2.setUuid(uuid);
			roles.add(temp2);
		}
		temp.setRoles(roles);
	}

}

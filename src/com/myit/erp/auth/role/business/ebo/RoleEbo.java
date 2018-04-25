package com.myit.erp.auth.role.business.ebo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.myit.erp.auth.menu.vo.MenuModel;
import com.myit.erp.auth.res.vo.ResModel;
import com.myit.erp.auth.role.business.ebi.RoleEbi;
import com.myit.erp.auth.role.dao.dao.RoleDao;
import com.myit.erp.auth.role.vo.RoleModel;
import com.myit.erp.util.base.BaseQueryModel;

public class RoleEbo implements RoleEbi{
	private RoleDao roleDao;
	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

	//록폴
	public void save(RoleModel rm) {
		roleDao.save(rm);
	}

	//록폴
	public void update(RoleModel rm) {
		roleDao.update(rm);
	}

	public void delete(RoleModel rm) {
		roleDao.delete(rm);
	}

	public RoleModel get(Serializable uuid) {
		return roleDao.get(uuid);
	}

	public List<RoleModel> getAll() {
		return roleDao.getAll();
	}

	public List<RoleModel> getAll(BaseQueryModel qm, Integer pageNum,Integer pageCount) {
		return roleDao.getAll(qm,pageNum,pageCount);
	}

	public Integer getCount(BaseQueryModel qm) {
		return roleDao.getCount(qm);
	}

	@Override
	public void save(RoleModel model, Long[] resUuids, Long[] menuUuids) {
		// TODO Auto-generated method stub
		Set<ResModel> reses = new HashSet<>();
		for(Long uuid: resUuids){
			ResModel rm = new ResModel();
			rm.setUuid(uuid);
			reses.add(rm);
		}
		model.setReses(reses);
		
		Set<MenuModel> menus = new HashSet<>();
		for(Long uuid: menuUuids){
			MenuModel mm = new MenuModel();
			mm.setUuid(uuid);
			menus.add(mm);
		}
		model.setMenus(menus);
		
		roleDao.save(model);
	}

	@Override
	public void update(RoleModel model, Long[] resUuids, Long[] menuUuids) {
		// TODO Auto-generated method stub
		Set<ResModel> reses = new HashSet<>();
		for(Long uuid: resUuids){
			ResModel rm = new ResModel();
			rm.setUuid(uuid);
			reses.add(rm);
		}
		model.setReses(reses);
		
		Set<MenuModel> menus = new HashSet<>();
		for(Long uuid: menuUuids){
			MenuModel mm = new MenuModel();
			mm.setUuid(uuid);
			menus.add(mm);
		}
		model.setMenus(menus);
		
		roleDao.update(model);
	}

}

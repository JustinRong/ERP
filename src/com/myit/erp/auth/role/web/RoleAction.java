package com.myit.erp.auth.role.web;

import java.util.List;

import com.myit.erp.auth.menu.business.ebi.MenuEbi;
import com.myit.erp.auth.menu.vo.MenuModel;
import com.myit.erp.auth.res.business.ebi.ResEbi;
import com.myit.erp.auth.res.vo.ResModel;
import com.myit.erp.auth.role.business.ebi.RoleEbi;
import com.myit.erp.auth.role.vo.RoleModel;
import com.myit.erp.auth.role.vo.RoleQueryModel;
import com.myit.erp.util.base.BaseAction;

public class RoleAction extends BaseAction{
	public RoleModel rm = new RoleModel();
	public RoleQueryModel rqm = new RoleQueryModel();

	private RoleEbi roleEbi;
	private ResEbi resEbi;
	private MenuEbi menuEbi;
	
	//��Դuuid
	public Long[] resUuids;
	//�˵�uuid
	public Long[] menuUuids;
	
	
	public RoleModel getRm() {
		return rm;
	}

	public void setRm(RoleModel rm) {
		this.rm = rm;
	}

	public RoleQueryModel getRqm() {
		return rqm;
	}

	public void setRqm(RoleQueryModel rqm) {
		this.rqm = rqm;
	}

	public ResEbi getResEbi() {
		return resEbi;
	}

	public void setResEbi(ResEbi resEbi) {
		this.resEbi = resEbi;
	}

	public MenuEbi getMenuEbi() {
		return menuEbi;
	}

	public void setMenuEbi(MenuEbi menuEbi) {
		this.menuEbi = menuEbi;
	}

	public RoleEbi getRoleEbi() {
		return roleEbi;
	}

	public void setRoleEbi(RoleEbi roleEbi) {
		this.roleEbi = roleEbi;
	}

	//�б�
	public String list(){
		setDataTotal(roleEbi.getCount(rqm));
		List<RoleModel> roleList = roleEbi.getAll(rqm,pageNum,pageCount);
		put("roleList", roleList);
		return LIST;
	}

	//�����
	/*public String input(){
		if(rm.getUuid()!=null){
			rm = roleEbi.get(rm.getUuid());
		}
		return INPUT;
	}*/
	public String input(){
		//���ز˵��б�
		List<MenuModel> menuList = menuEbi.getAll();
		put("menuList", menuList);
		//������Դ�б�
		List<ResModel> resList = resEbi.getAll();
		put("resList", resList);
		if(rm.getUuid()!=null){
			rm = roleEbi.get(rm.getUuid());
			//��resUuids��ʼ���������ѡ����Դ
			resUuids = new Long[rm.getReses().size()];
			int i = 0;
			for(ResModel reses : rm.getReses()){
				resUuids[i] = reses.getUuid();
				i++;
			}
			
			//��menuUuids��ʼ���������ѡ�Ĳ˵�
			menuUuids = new Long[rm.getMenus().size()];
			i = 0;
			for(MenuModel menus : rm.getMenus()){
				menuUuids[i] = menus.getUuid();
				i++;
			}
		}
		return INPUT;
	}
	

	//���
	/*public String save(){
		if(rm.getUuid() == null){
			roleEbi.save(rm);
		}else{
			roleEbi.update(rm);
		}
		return TO_LIST;
	}*/
	
	public String save(){
		if(rm.getUuid() == null){
			roleEbi.save(rm, resUuids, menuUuids);
		}else{
			roleEbi.update(rm, resUuids, menuUuids);
		}
		return TO_LIST;
	}

	//ɾ��
	public String delete(){
		roleEbi.delete(rm);
		return TO_LIST;
	}

}

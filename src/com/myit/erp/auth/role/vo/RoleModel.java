package com.myit.erp.auth.role.vo;

import java.util.Set;

import com.myit.erp.auth.emp.vo.EmpModel;
import com.myit.erp.auth.menu.vo.MenuModel;
import com.myit.erp.auth.res.vo.ResModel;

public class RoleModel {
	private Long uuid;
	private String name;
	private String code;
	
	//��ɫ����Դ��Զ�
	private Set<ResModel> reses;
	
	//��ɫ�Բ˵�Ȩ�޶�Զ�
	private Set<MenuModel> menus;
	
	//��ɫ��Ա����Զ�
	private Set<EmpModel> emps;
	
	public Set<ResModel> getReses() {
		return reses;
	}
	public void setReses(Set<ResModel> reses) {
		this.reses = reses;
	}
	public Set<MenuModel> getMenus() {
		return menus;
	}
	public void setMenus(Set<MenuModel> menus) {
		this.menus = menus;
	}
	public Set<EmpModel> getEmps() {
		return emps;
	}
	public void setEmps(Set<EmpModel> emps) {
		this.emps = emps;
	}
	public Long getUuid() {
		return uuid;
	}
	public void setUuid(Long uuid) {
		this.uuid = uuid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	
}

package com.myit.erp.auth.menu.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.myit.erp.auth.menu.business.ebi.MenuEbi;
import com.myit.erp.auth.menu.vo.MenuModel;
import com.myit.erp.auth.menu.vo.MenuQueryModel;
import com.myit.erp.auth.role.business.ebi.RoleEbi;
import com.myit.erp.auth.role.vo.RoleModel;
import com.myit.erp.util.base.BaseAction;

public class MenuAction extends BaseAction{
	public MenuModel mm = new MenuModel();
	public MenuQueryModel mqm = new MenuQueryModel();

	private MenuEbi menuEbi;
	private RoleEbi roleEbi;
	
	public MenuModel getMm() {
		return mm;
	}

	public void setMm(MenuModel mm) {
		this.mm = mm;
	}

	public MenuQueryModel getMqm() {
		return mqm;
	}

	public void setMqm(MenuQueryModel mqm) {
		this.mqm = mqm;
	}

	public RoleEbi getRoleEbi() {
		return roleEbi;
	}

	public void setRoleEbi(RoleEbi roleEbi) {
		this.roleEbi = roleEbi;
	}

	public MenuEbi getMenuEbi() {
		return menuEbi;
	}

	public void setMenuEbi(MenuEbi menuEbi) {
		this.menuEbi = menuEbi;
	}

	//列表
	public String list(){
		List<MenuModel> parentList = menuEbi.getAllOneLevel();
		put("parentList",parentList);
		setDataTotal(menuEbi.getCount(mqm));
		List<MenuModel> menuList = menuEbi.getAll(mqm,pageNum,pageCount);
		System.out.println(mqm.getUuid()+"--"+pageNum+"--"+pageCount);
		System.out.println(menuList.size());
		put("menuList", menuList);
		return LIST;
	}

	//到添加
	public String input(){
		//加载所有角色数据信息
		List<RoleModel> roleList = roleEbi.getAll();
		put("roleList",roleList);
		//加载所有的一级菜单
		List<MenuModel> menuList = menuEbi.getAllOneLevel();
		put("menuList",menuList);
		if(mm.getUuid()!=null){
			mm = menuEbi.get(mm.getUuid());
			//set->array
			roleUuids = new Long[mm.getRoles().size()];
			int i = 0;
			for(RoleModel rm : mm.getRoles()){
				roleUuids[i++] = rm.getUuid();
			}
		}
		return INPUT;
	}

	public Long[] roleUuids;
	//添加
	public String save(){
		if(mm.getUuid() == null){
			menuEbi.save(mm,roleUuids);
		}else{
			menuEbi.update(mm,roleUuids);
		}
		return TO_LIST;
	}

	//删除
	public String delete(){
		menuEbi.delete(mm);
		return TO_LIST;
	}
	
	//显示菜单
	public void showMenu() throws IOException{
		//1.首先获取root参数
		String root = getRequest().getParameter("root");
		//2.判断参数值   source   id
		HttpServletResponse response = getResponse();
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = response.getWriter();
		
		StringBuilder json = new StringBuilder();
		json.append("[");
		System.out.println(root+"------------------");
		if("source".equals(root)){
			//生成一级菜单
			List<MenuModel> menuList = menuEbi.getAllOneLevelByEmp(getLogin().getUuid());
			System.out.println(Arrays.toString(roleUuids)+"------");
			for(MenuModel temp :menuList){
				if(temp.getName().equals("报表中心")) {
					continue;
				}
				else if (temp.getName().equals("销售管理")) {
					continue;
				}else {
				json.append("{\"text\":\"");
				json.append(temp.getName());
				json.append("\",\"hasChildren\":true,\"classes\":\"folder\",\"id\":\"");
				json.append(temp.getUuid());
				json.append("\"},");
				}
			}
			System.out.println(json.toString()+"-----");
		}else{
			//生成二级菜单项
			//获取指定一级菜单的二级菜单项
			Long puuid = new Long(root);
			System.out.println("***********puuid:"+puuid);
			List<MenuModel> menuList = menuEbi.getByEmpAndPuuid(getLogin().getUuid(),puuid);
			for(MenuModel temp :menuList){
				if(temp.getName().equals("采购报表")) {
					continue;
				}else {
				json.append("{\"text\":\"<a class='hei' target='main' href='");
				json.append(temp.getUrl());
				json.append("'>");
				json.append(temp.getName());
				json.append("</a>\",\"hasChildren\":false,\"classes\":\"file\"},");
				}
			}
		}
		
		json.deleteCharAt(json.length()-1);
		json.append("]");
		System.out.println(json.toString()+"----------");
		pw.write(json.toString());
		pw.flush();
	}
	
	
}

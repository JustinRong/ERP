package com.myit.erp.auth.emp.web;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.myit.erp.auth.dep.business.ebi.DepEbi;
import com.myit.erp.auth.dep.vo.DepModel;
import com.myit.erp.auth.emp.business.ebi.EmpEbi;
import com.myit.erp.auth.emp.vo.EmpModel;
import com.myit.erp.auth.emp.vo.EmpQueryModel;
import com.myit.erp.auth.res.business.ebi.ResEbi;
import com.myit.erp.auth.res.vo.ResModel;
import com.myit.erp.auth.role.business.ebi.RoleEbi;
import com.myit.erp.auth.role.vo.RoleModel;
import com.myit.erp.util.base.BaseAction;

public class EmpAction extends BaseAction {
	private EmpModel em = new EmpModel();
	public EmpQueryModel eqm = new EmpQueryModel();
	private String depUuid;

	private EmpEbi empEbi;
	private  DepEbi depEbi;
	private  RoleEbi  roleEbi;
	private  ResEbi  resEbi;
	
	
	public String getDepUuid() {
		return depUuid;
	}

	public void setDepUuid(String depUuid) {
		this.depUuid = depUuid;
	}

	public DepEbi getDepEbi() {
		return depEbi;
	}

	public void setDepEbi(DepEbi depEbi) {
		this.depEbi = depEbi;
	}

	public RoleEbi getRoleEbi() {
		return roleEbi;
	}

	public void setRoleEbi(RoleEbi roleEbi) {
		this.roleEbi = roleEbi;
	}

	public ResEbi getResEbi() {
		return resEbi;
	}

	public void setResEbi(ResEbi resEbi) {
		this.resEbi = resEbi;
	}

	public EmpModel getEm() {
		return em;
	}

	public void setEm(EmpModel em) {
		this.em = em;
	}

	public EmpQueryModel getEqm() {
		return eqm;
	}

	public void setEqm(EmpQueryModel eqm) {
		this.eqm = eqm;
	}

	public EmpEbi getEmpEbi() {
		return empEbi;
	}

	public void setEmpEbi(EmpEbi empEbi) {
		this.empEbi = empEbi;
	}
	//登录
	public String login(){
		HttpServletRequest request = getRequest();
		String loginIp = request.getHeader("x-forwarded-for"); 
		if(loginIp == null || loginIp.length() == 0 || "unknown".equalsIgnoreCase(loginIp)) { 
			loginIp = request.getHeader("Proxy-Client-IP"); 
		} 
		if(loginIp == null || loginIp.length() == 0 || "unknown".equalsIgnoreCase(loginIp)) { 
			loginIp = request.getHeader("WL-Proxy-Client-IP"); 
		} 
		if(loginIp == null || loginIp.length() == 0 || "unknown".equalsIgnoreCase(loginIp)) { 
			loginIp = request.getRemoteAddr(); 
		}
		
		EmpModel loginEm = empEbi.login(em.getUserName(),em.getPwd(),loginIp);
		if(loginEm == null){
			this.addActionError("对不起，用户名密码错误！");
			return "loginFail";
		}else{
			//获取登录的员工可操作的资源
			List<ResModel> resList=resEbi.getAllByEmp(loginEm.getUuid());
			StringBuffer  sb=new StringBuffer();
			for(ResModel r:resList){
				sb.append(r.getUrl()+",");
			}
			String  s=sb.toString().substring(0,sb.length()-1);
			loginEm.setResAll(s);
			System.out.println("-----------aa");
			putSession(EmpModel.EMP_LOGIN_USER_OBJECT_NAME, loginEm);
			System.out.println("-----------bb");
			return "loginSuccess";
		}
	}
	
	
	//列表
		public String list(){
			//查询所有部门
			List<DepModel> depList  =depEbi.getAll();
			this.put("depList", depList);
			
			setDataTotal(empEbi.getCount(eqm));
			List<EmpModel> empList = empEbi.getAll(eqm,pageNum,pageCount);
			put("empList", empList);
			return LIST;
		}

		//到添加
		public String input(){
			//获取所有的角色
			List<RoleModel> roles=roleEbi.getAll();
			this.put("roleList", roles);
			//获取所有部门
			List<DepModel> depList=depEbi.getAll();
			this.put("depList", depList);
			//判断是否是修改
			if(em.getUuid()!=null){
				em=empEbi.get(em.getUuid());
				roleUuids=new Long[em.getRoles().size()];
				int i=0;
				for(Iterator<RoleModel> iter=em.getRoles().iterator();iter.hasNext();){
				roleUuids[i]=iter.next().getUuid();
				i++;
				}
			}
			return INPUT;
		}
		Long  []roleUuids;   //roleUuids

		public Long[] getRoleUuids() {
			return roleUuids;
		}

		public void setRoleUuids(Long[] roleUuids) {
			this.roleUuids = roleUuids;
		}

		//添加
		public String save(){
			if(depUuid!=null){
				System.out.println("--------------"+depUuid);
				em.setDm(new DepModel(Long.parseLong(depUuid)));
			}
			if(em.getUuid() == null){
				System.out.println(em.toString());
				empEbi.save(em,roleUuids);
			}else{
				empEbi.update(em,roleUuids);
			}
			return TO_LIST;
		}

		//删除
		public String delete(){
			empEbi.delete(em);
			return TO_LIST;
		}
	

}

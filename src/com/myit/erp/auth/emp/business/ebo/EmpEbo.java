package com.myit.erp.auth.emp.business.ebo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.myit.erp.auth.emp.business.ebi.EmpEbi;
import com.myit.erp.auth.emp.dao.dao.EmpDao;
import com.myit.erp.auth.emp.vo.EmpModel;
import com.myit.erp.auth.role.vo.RoleModel;
import com.myit.erp.util.base.BaseQueryModel;
import com.myit.erp.util.exception.AppException;
import com.myit.erp.util.format.MD5Utils;

public class EmpEbo implements EmpEbi {
	
	private EmpDao empDao;
	

	/**
	 * @return the empDao
	 */
	public EmpDao getEmpDao() {
		return empDao;
	}

	/**
	 * @param empDao the empDao to set
	 */
	public void setEmpDao(EmpDao empDao) {
		this.empDao = empDao;
	}

	public EmpModel login(String userName, String pwd,String lastLoginIp) {
		//MD5加密
		pwd = MD5Utils.md5(pwd);
		//调用数据层
		EmpModel loginEm = empDao.getByUserNameAndPwd(userName,pwd);
		if(loginEm != null){
			//登录成功
			//添加登录信息
			//登录次数+1
			loginEm.setLoginTimes(loginEm.getLoginTimes()+1);
			//最后登录时间
			loginEm.setLastLoginTime(System.currentTimeMillis());
			//最后登录IP
			loginEm.setLastLoginIp(lastLoginIp);
			//快照更新
		}
		return loginEm;
	}

	
		public void save(EmpModel em) {
			//如果用户名没有输入，那么认定为特殊错误现象
			if(em.getUserName()==null || em.getUserName().trim().length() == 0){
				//认定为错误现象
				throw new AppException("INFO_EMP_USERNAME_IS_EMPTY");
			}
			
			//对密码加密
			em.setPwd(MD5Utils.md5(em.getPwd()));
			//设置默认值
			em.setLastLoginTime(System.currentTimeMillis());
			em.setLastLoginIp("-");
			em.setLoginTimes(0);
			empDao.save(em);
			
		}
		//待实现
		@Override
		public void update(EmpModel em) {
			
		}

		public void delete(EmpModel em) {
			empDao.delete(em);
		}

		public EmpModel get(Serializable uuid) {
			return empDao.get(uuid);
		}

		public List<EmpModel> getAll() {
			return empDao.getAll();
		}

		public List<EmpModel> getAll(BaseQueryModel qm, Integer pageNum,Integer pageCount) {
			return empDao.getAll(qm,pageNum,pageCount);
		}

		public Integer getCount(BaseQueryModel qm) {
			return empDao.getCount(qm);
		}

		@Override
		public void save(EmpModel empModel, Long[] rolesUuids) {
			// TODO Auto-generated method stub
			Set<RoleModel> roles=new HashSet();
			for(Long r:rolesUuids){
				RoleModel  rm=new RoleModel();
				rm.setUuid(r);
				roles.add(rm);
			}
			
			empModel.setRoles(roles);
			empModel.setLastLoginIp("-");
			empModel.setLastLoginTime(System.currentTimeMillis());
			empModel.setLoginTimes(0);
			empModel.setPwd(MD5Utils.md5(empModel.getPwd()));
			System.out.println(empModel.getDm());
			System.out.println(empModel.getName());
			System.out.println(empModel.toString());
			empDao.save(empModel);
			
		}

		@Override
		public void update(EmpModel empModel, Long[] rolesUuids) {

          EmpModel  emp=empDao.get(empModel.getUuid());
          emp.setAddress(empModel.getAddress());
          emp.setDm(empModel.getDm());
          emp.setEmail(empModel.getEmail());
          emp.setGender(empModel.getGender());
          emp.setName(empModel.getName());
          emp.setTele(empModel.getTele());
          
          Set<RoleModel> roles=new HashSet();
			for(Long r:rolesUuids){
				RoleModel  rm=new RoleModel();
				rm.setUuid(r);
				roles.add(rm);
			}
          emp.setRoles(roles);
          empDao.update(emp);
			
		}

		@Override
		public List<EmpModel> getAllByDep(Long uuid) {
			// TODO Auto-generated method stub
			return empDao.getAllByDepUuid(uuid);
		}
		

		

}

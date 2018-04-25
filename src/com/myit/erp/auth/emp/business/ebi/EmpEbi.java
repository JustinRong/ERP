package com.myit.erp.auth.emp.business.ebi;

import java.io.Serializable;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.myit.erp.auth.emp.vo.EmpModel;
import com.myit.erp.util.base.BaseEbi;
import com.myit.erp.util.base.BaseQueryModel;
@Transactional
public interface EmpEbi extends BaseEbi<EmpModel> {

	/**
	 * 根据用户名密码登录
	 * 
	 * @param userName
	 *            用户名
	 * @param pwd
	 *            密码
	 * @param loginIp
	 *            登录IP地址
	 * @return 登录用户信息。如果返回null,表示登录失败。
	 */
	public EmpModel login(String userName, String pwd, String loginIp);

	// 保存
	public void save(EmpModel empModel, Long[] rolesUuids);

	// 修改
	public void update(EmpModel empModel, Long[] rolesUuids);

	// 根据部门查询所有员工
	public List<EmpModel> getAllByDep(Long uuid);

}

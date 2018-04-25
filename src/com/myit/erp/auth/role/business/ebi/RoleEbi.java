package com.myit.erp.auth.role.business.ebi;

import org.springframework.transaction.annotation.Transactional;

import com.myit.erp.auth.role.vo.RoleModel;
import com.myit.erp.util.base.BaseEbi;

@Transactional
public interface RoleEbi extends BaseEbi<RoleModel>{
	//保存角色
	public void save(RoleModel model, Long[] resUuids, Long[] menuUuids);
	
	//修改角色
	public void update(RoleModel model, Long[] resUuids, Long[] menuUuids);
}

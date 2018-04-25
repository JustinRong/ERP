package com.myit.erp.auth.role.business.ebi;

import org.springframework.transaction.annotation.Transactional;

import com.myit.erp.auth.role.vo.RoleModel;
import com.myit.erp.util.base.BaseEbi;

@Transactional
public interface RoleEbi extends BaseEbi<RoleModel>{
	//�����ɫ
	public void save(RoleModel model, Long[] resUuids, Long[] menuUuids);
	
	//�޸Ľ�ɫ
	public void update(RoleModel model, Long[] resUuids, Long[] menuUuids);
}

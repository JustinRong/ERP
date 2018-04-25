package com.myit.erp.auth.emp.dao.dao;

import java.util.List;

import com.myit.erp.auth.emp.vo.EmpModel;
import com.myit.erp.util.base.BaseDao;

public interface EmpDao extends BaseDao<EmpModel> {
	public EmpModel getByUserNameAndPwd(String userName, String pwd);
	//���ݲ��ű�Ż�ȡԱ����Ϣ
	public List<EmpModel> getAllByDepUuid(Long uuid);
}

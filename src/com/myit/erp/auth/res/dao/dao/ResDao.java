package com.myit.erp.auth.res.dao.dao;

import java.util.List;

import com.myit.erp.auth.res.vo.ResModel;
import com.myit.erp.util.base.BaseDao;

public interface ResDao extends BaseDao<ResModel> {
	public List<ResModel> getAllByEmpUuid(Long uuid);
}

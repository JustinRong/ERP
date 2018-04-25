package com.myit.erp.auth.res.business.ebi;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.myit.erp.auth.res.vo.ResModel;
import com.myit.erp.util.base.BaseEbi;

@Transactional
public interface ResEbi extends BaseEbi<ResModel>{
	public List<ResModel> getAllByEmp(Long uuid);
}

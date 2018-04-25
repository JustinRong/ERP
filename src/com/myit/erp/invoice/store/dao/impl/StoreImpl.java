package com.myit.erp.invoice.store.dao.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.myit.erp.invoice.store.dao.dao.StoreDao;
import com.myit.erp.invoice.store.vo.StoreModel;
import com.myit.erp.invoice.store.vo.StoreQueryModel;
import com.myit.erp.util.base.BaseImpl;
import com.myit.erp.util.base.BaseQueryModel;

public class StoreImpl extends BaseImpl<StoreModel> implements StoreDao{

	public void doQbc(DetachedCriteria dc,BaseQueryModel qm){
		StoreQueryModel sqm = (StoreQueryModel)qm;
		// TODO 添加自定义查询条件
	}

}

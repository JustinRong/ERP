package com.myit.erp.invoice.storedetail.dao.dao;

import com.myit.erp.invoice.storedetail.vo.StoreDetailModel;
import com.myit.erp.util.base.BaseDao;

public interface StoreDetailDao extends BaseDao<StoreDetailModel> {
	public StoreDetailModel getBySmAndGm(Long storeUuid, Long uuid);
}

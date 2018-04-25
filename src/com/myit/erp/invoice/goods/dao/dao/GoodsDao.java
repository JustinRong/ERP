package com.myit.erp.invoice.goods.dao.dao;

import java.util.List;

import com.myit.erp.invoice.goods.vo.GoodsModel;
import com.myit.erp.util.base.BaseDao;

public interface GoodsDao extends BaseDao<GoodsModel> {
	public List<GoodsModel> getAllByGtmUuid(Long uuid);
}
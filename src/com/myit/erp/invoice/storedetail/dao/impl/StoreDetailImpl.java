package com.myit.erp.invoice.storedetail.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.myit.erp.invoice.storedetail.dao.dao.StoreDetailDao;
import com.myit.erp.invoice.storedetail.vo.StoreDetailModel;
import com.myit.erp.invoice.storedetail.vo.StoreDetailQueryModel;
import com.myit.erp.util.base.BaseImpl;
import com.myit.erp.util.base.BaseQueryModel;

public class StoreDetailImpl extends BaseImpl<StoreDetailModel> implements StoreDetailDao{

	public void doQbc(DetachedCriteria dc,BaseQueryModel qm){
		StoreDetailQueryModel sqm = (StoreDetailQueryModel)qm;
		// TODO 添加自定义查询条件
	}

	@Override
	public StoreDetailModel getBySmAndGm(Long storeUuid, Long uuid) {
		// TODO Auto-generated method stub
		StoreDetailModel sdm = null;
		String hql = "from StoreDetailModel where sm.uuid=? and gm.uuid=?";
		List<StoreDetailModel> list = this.getHibernateTemplate().find(hql, storeUuid, uuid);
		if(list!=null){
			sdm = list.size()>0?list.get(0):null;
		}
		return null;
	}

}

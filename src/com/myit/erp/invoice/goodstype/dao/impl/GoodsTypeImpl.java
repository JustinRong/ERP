package com.myit.erp.invoice.goodstype.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.myit.erp.invoice.goodstype.dao.dao.GoodsTypeDao;
import com.myit.erp.invoice.goodstype.vo.GoodsTypeModel;
import com.myit.erp.invoice.goodstype.vo.GoodsTypeQueryModel;
import com.myit.erp.util.base.BaseImpl;
import com.myit.erp.util.base.BaseQueryModel;

public class GoodsTypeImpl extends BaseImpl<GoodsTypeModel> implements GoodsTypeDao{

	public void doQbc(DetachedCriteria dc,BaseQueryModel qm){
		GoodsTypeQueryModel gqm = (GoodsTypeQueryModel)qm;
		// TODO ����Զ����ѯ����
	}
	public List<GoodsTypeModel> getAllBySmUuid(Long uuid) {
		String hql = "from GoodsTypeModel where sm.uuid = ?";
		return this.getHibernateTemplate().find(hql,uuid);
	}

	public List<GoodsTypeModel> getAllUnionBySmUuid(Long uuid) {
		//gtm->gm(û�й�ϵ)
		//gm->gtm
		String hql = "select distinct gt from GoodsModel gm join gm.gtm gt where gt.sm.uuid = ?";
		return this.getHibernateTemplate().find(hql,uuid);
	}

}
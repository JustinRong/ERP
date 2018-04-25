package com.myit.erp.invoice.orderdetail.dao.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.myit.erp.invoice.orderdetail.dao.dao.OrderDetailDao;
import com.myit.erp.invoice.orderdetail.vo.OrderDetailModel;
import com.myit.erp.invoice.orderdetail.vo.OrderDetailQueryModel;
import com.myit.erp.util.base.BaseImpl;
import com.myit.erp.util.base.BaseQueryModel;

public class OrderDetailImpl extends BaseImpl<OrderDetailModel> implements OrderDetailDao{

	public void doQbc(DetachedCriteria dc,BaseQueryModel qm){
		OrderDetailQueryModel oqm = (OrderDetailQueryModel)qm;
		// TODO 添加自定义查询条件
	}

}

package com.myit.erp.invoice.orderdetail.business.ebi;

import org.springframework.transaction.annotation.Transactional;

import com.myit.erp.invoice.orderdetail.vo.OrderDetailModel;
import com.myit.erp.util.base.BaseEbi;

@Transactional
public interface OrderDetailEbi extends BaseEbi<OrderDetailModel>{

}

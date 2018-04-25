package com.myit.erp.invoice.supplier.business.ebi;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.myit.erp.invoice.supplier.vo.SupplierModel;
import com.myit.erp.util.base.BaseEbi;

@Transactional
public interface SupplierEbi extends BaseEbi<SupplierModel>{
	/**
	 * 获取具有商品类别信息的供应商信息
	 * @return
	 */
	public List<SupplierModel> getAllUnion();
	/**
	 * .....
	 * @return
	 */
	public List<SupplierModel> getAllUnionTwo();
}
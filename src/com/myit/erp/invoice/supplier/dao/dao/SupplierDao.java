package com.myit.erp.invoice.supplier.dao.dao;

import java.util.List;

import com.myit.erp.invoice.supplier.vo.SupplierModel;
import com.myit.erp.util.base.BaseDao;

public interface SupplierDao extends BaseDao<SupplierModel> {
	public List<SupplierModel> getAllUnion();

	public List<SupplierModel> getAllUnionTwo();
}
package com.myit.erp.invoice.storedetail.business.ebi;

import org.springframework.transaction.annotation.Transactional;

import com.myit.erp.invoice.storedetail.vo.StoreDetailModel;
import com.myit.erp.util.base.BaseEbi;

@Transactional
public interface StoreDetailEbi extends BaseEbi<StoreDetailModel>{

}

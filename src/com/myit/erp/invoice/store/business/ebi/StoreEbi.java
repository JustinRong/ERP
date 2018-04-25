package com.myit.erp.invoice.store.business.ebi;

import org.springframework.transaction.annotation.Transactional;

import com.myit.erp.invoice.store.vo.StoreModel;
import com.myit.erp.util.base.BaseEbi;

@Transactional
public interface StoreEbi extends BaseEbi<StoreModel>{

}

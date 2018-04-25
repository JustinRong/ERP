package com.myit.erp.invoice.operdetail.business.ebi;

import org.springframework.transaction.annotation.Transactional;

import com.myit.erp.invoice.operdetail.vo.OperDetailModel;
import com.myit.erp.util.base.BaseEbi;

@Transactional
public interface OperDetailEbi extends BaseEbi<OperDetailModel>{

}

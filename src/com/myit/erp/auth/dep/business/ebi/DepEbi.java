package com.myit.erp.auth.dep.business.ebi;

import org.springframework.transaction.annotation.Transactional;

import com.myit.erp.auth.dep.vo.DepModel;
import com.myit.erp.util.base.BaseEbi;

//切面:spring内置
//切入点:定义了该注解所在的类或接口中的所有方法
//execution(com.myit.erp.auth.dep.business.ebi.DepEbi.*(..))
@Transactional
public interface DepEbi extends BaseEbi<DepModel> {

}

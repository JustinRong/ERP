package com.myit.erp.auth.dep.business.ebi;

import org.springframework.transaction.annotation.Transactional;

import com.myit.erp.auth.dep.vo.DepModel;
import com.myit.erp.util.base.BaseEbi;

//����:spring����
//�����:�����˸�ע�����ڵ����ӿ��е����з���
//execution(com.myit.erp.auth.dep.business.ebi.DepEbi.*(..))
@Transactional
public interface DepEbi extends BaseEbi<DepModel> {

}

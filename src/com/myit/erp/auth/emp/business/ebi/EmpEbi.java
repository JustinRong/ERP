package com.myit.erp.auth.emp.business.ebi;

import java.io.Serializable;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.myit.erp.auth.emp.vo.EmpModel;
import com.myit.erp.util.base.BaseEbi;
import com.myit.erp.util.base.BaseQueryModel;
@Transactional
public interface EmpEbi extends BaseEbi<EmpModel> {

	/**
	 * �����û��������¼
	 * 
	 * @param userName
	 *            �û���
	 * @param pwd
	 *            ����
	 * @param loginIp
	 *            ��¼IP��ַ
	 * @return ��¼�û���Ϣ���������null,��ʾ��¼ʧ�ܡ�
	 */
	public EmpModel login(String userName, String pwd, String loginIp);

	// ����
	public void save(EmpModel empModel, Long[] rolesUuids);

	// �޸�
	public void update(EmpModel empModel, Long[] rolesUuids);

	// ���ݲ��Ų�ѯ����Ա��
	public List<EmpModel> getAllByDep(Long uuid);

}

package com.myit.erp.auth.menu.business.ebi;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.myit.erp.auth.menu.vo.MenuModel;
import com.myit.erp.util.base.BaseEbi;

@Transactional
public interface MenuEbi extends BaseEbi<MenuModel>{
	//��ȡϵͳ�˵�������һ���˵�
	public List<MenuModel> getAllOneLevel();
	
	//��ȡָ��Ա����Ӧ�����в�����һ���˵�
	public List<MenuModel> getAllOneLevelByEmp(Long uuid);
	
	//��ȡָ��Ա����Ӧ��ָ��һ���˵��ɲ����ԵĶ����˵�
	public List<MenuModel> getByEmpAndPuuid(Long uuid,Long puuid);
	
	//���Ա��������ɫ��
	public void save(MenuModel mm, Long [] roleUuids);
	
	//����Ա����Ϣ������ɫ��
	public void update(MenuModel mm, Long [] roleUuids);
}

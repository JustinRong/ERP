package com.myit.erp.auth.menu.dao.dao;

import java.util.List;

import com.myit.erp.auth.menu.vo.MenuModel;
import com.myit.erp.util.base.BaseDao;

public interface MenuDao extends BaseDao<MenuModel> {
	//��ȡ������һ���˵�
	public List<MenuModel> getByPuuidIsOneOrZero();
	
	//����Ա�����ȡ��Ա���ɲ�����һ���˵�
	public List<MenuModel> getAllOneLevelByEmpUuid(Long uuid);
	
	//����Ա�����ȡ��Ա���ɲ�����һ���˵�
	public List<MenuModel> getByEmpUuidAndPuuid(Long uuid,Long Puuid);
}

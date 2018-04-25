package com.myit.erp.invoice.goodstype.business.ebi;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.myit.erp.invoice.goodstype.vo.GoodsTypeModel;
import com.myit.erp.util.base.BaseEbi;

@Transactional
public interface GoodsTypeEbi extends BaseEbi<GoodsTypeModel>{
	/**
	 * ��ȡָ����Ӧ����Ʒ�����Ϣ
	 * @param uuid ��Ӧ��uuid
	 * @return
	 */
	public List<GoodsTypeModel> getAllBySm(Long uuid);
	/**
	 * ��ȡָ����Ӧ�̶�Ӧ�ľ�����Ʒ��Ϣ�������Ϣ
	 * @param uuid ��Ӧ��uuid
	 * @return
	 */
	public List<GoodsTypeModel> getAllUnionBySm(Long uuid);
}
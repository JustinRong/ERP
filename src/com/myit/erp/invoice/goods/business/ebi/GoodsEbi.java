package com.myit.erp.invoice.goods.business.ebi;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.myit.erp.invoice.goods.vo.GoodsModel;
import com.myit.erp.util.base.BaseEbi;

@Transactional
public interface GoodsEbi extends BaseEbi<GoodsModel>{
	/**
	 * ��ȡָ����Ʒ����Ӧ��������Ʒ��Ϣ
	 * @param uuid ���uuid
	 * @return
	 */
	public List<GoodsModel> getAllByGtm(Long uuid);
}
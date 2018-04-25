package com.myit.erp.invoice.goodstype.business.ebi;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.myit.erp.invoice.goodstype.vo.GoodsTypeModel;
import com.myit.erp.util.base.BaseEbi;

@Transactional
public interface GoodsTypeEbi extends BaseEbi<GoodsTypeModel>{
	/**
	 * 获取指定供应商商品类别信息
	 * @param uuid 供应商uuid
	 * @return
	 */
	public List<GoodsTypeModel> getAllBySm(Long uuid);
	/**
	 * 获取指定供应商对应的具有商品信息的类别信息
	 * @param uuid 供应商uuid
	 * @return
	 */
	public List<GoodsTypeModel> getAllUnionBySm(Long uuid);
}
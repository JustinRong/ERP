package com.myit.erp.invoice.goods.web;

import java.util.List;

import com.myit.erp.invoice.goods.business.ebi.GoodsEbi;
import com.myit.erp.invoice.goods.vo.GoodsModel;
import com.myit.erp.invoice.goods.vo.GoodsQueryModel;
import com.myit.erp.invoice.goodstype.business.ebi.GoodsTypeEbi;
import com.myit.erp.invoice.goodstype.vo.GoodsTypeModel;
import com.myit.erp.invoice.supplier.business.ebi.SupplierEbi;
import com.myit.erp.invoice.supplier.vo.SupplierModel;
import com.myit.erp.util.base.BaseAction;

public class GoodsAction extends BaseAction {
	public GoodsModel gm = new GoodsModel();
	public GoodsQueryModel gqm = new GoodsQueryModel();

	private GoodsEbi goodsEbi;
	private SupplierEbi supplierEbi;
	private GoodsTypeEbi goodsTypeEbi;
	
	private Long smUuid;
	private Long gtmUuid;
	SupplierModel sm;
	GoodsTypeModel gtm;
	
	public Long getGtmUuid() {
		return gtmUuid;
	}

	public void setGtmUuid(Long gtmUuid) {
		this.gtmUuid = gtmUuid;
	}

	public Long getSmUuid() {
		return smUuid;
	}

	public void setSmUuid(Long smUuid) {
		this.smUuid = smUuid;
	}

	public SupplierEbi getSupplierEbi() {
		return supplierEbi;
	}

	public void setSupplierEbi(SupplierEbi supplierEbi) {
		this.supplierEbi = supplierEbi;
	}

	public GoodsTypeEbi getGoodsTypeEbi() {
		return goodsTypeEbi;
	}

	public void setGoodsTypeEbi(GoodsTypeEbi goodsTypeEbi) {
		this.goodsTypeEbi = goodsTypeEbi;
	}

	public void setGoodsEbi(GoodsEbi goodsEbi) {
		this.goodsEbi = goodsEbi;
	}

	// 列表
	public String list() {
		// 加载供应商全信息
		List<SupplierModel> supplierList = supplierEbi.getAll();
		put("supplierList", supplierList);
		setDataTotal(goodsEbi.getCount(gqm));
		List<GoodsModel> goodsList = goodsEbi.getAll(gqm, pageNum, pageCount);
		put("goodsList", goodsList);
		return LIST;
	}

	// 到添加
	public String input() {
		// 加载所有具有类别信息的供应商信息
		List<SupplierModel> supplierList = supplierEbi.getAllUnion();
		put("supplierList", supplierList);
		sm = new SupplierModel();
		sm.setUuid(smUuid);
		System.out.println(smUuid+"-----------");
		gtm = new GoodsTypeModel();
		gtm.setSm(sm);
		gm.setGtm(gtm);
		// 加载第一个供应商对应的所有商品类别信息
		Long supplierUuid = null;
		if (gm.getUuid() != null) {
			gm = goodsEbi.get(gm.getUuid());
			supplierUuid = gm.getGtm().getSm().getUuid();
		} else {
			supplierUuid = supplierList.get(0).getUuid();
		}
		List<GoodsTypeModel> gtmList = goodsTypeEbi.getAllBySm(supplierUuid);
		put("gtmList", gtmList);
		return INPUT;
	}

	// 添加
	public String save() {
		gtm = new GoodsTypeModel();
		gtm.setUuid(gtmUuid);
		gm.setGtm(gtm);
		if (gm.getUuid() == null) {
			goodsEbi.save(gm);
		} else {
			goodsEbi.update(gm);
		}
		return TO_LIST;
	}

	// 删除
	public String delete() {
		goodsEbi.delete(gm);
		return TO_LIST;
	}

}
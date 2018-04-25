package com.myit.erp.invoice.goodstype.web;

import java.util.List;

import com.myit.erp.auth.dep.vo.DepModel;
import com.myit.erp.invoice.goodstype.business.ebi.GoodsTypeEbi;
import com.myit.erp.invoice.goodstype.vo.GoodsTypeModel;
import com.myit.erp.invoice.goodstype.vo.GoodsTypeQueryModel;
import com.myit.erp.invoice.supplier.business.ebi.SupplierEbi;
import com.myit.erp.invoice.supplier.vo.SupplierModel;
import com.myit.erp.util.base.BaseAction;

public class GoodsTypeAction extends BaseAction{
	public GoodsTypeModel gm = new GoodsTypeModel();
	public GoodsTypeQueryModel gqm = new GoodsTypeQueryModel();
	private GoodsTypeEbi goodsTypeEbi;
	private SupplierEbi supplierEbi;
	private Long smUuid;
	SupplierModel sm = null;
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

	public void setGoodsTypeEbi(GoodsTypeEbi goodsTypeEbi) {
		this.goodsTypeEbi = goodsTypeEbi;
	}

	//�б�
	public String list(){
		setDataTotal(goodsTypeEbi.getCount(gqm));
		List<GoodsTypeModel> goodsTypeList = goodsTypeEbi.getAll(gqm,pageNum,pageCount);
		put("goodsTypeList", goodsTypeList);
		return LIST;
	}

	//�����
	public String input(){
		List<SupplierModel> supplierList = supplierEbi.getAll();
		put("supplierList",supplierList);
		if(gm.getUuid()!=null){
			gm = goodsTypeEbi.get(gm.getUuid());
		}
		return INPUT;
	}

	//���
	public String save(){
		System.out.println(smUuid);		
		sm = new SupplierModel();
		sm.setUuid(smUuid);
		gm.setSm(sm);
		if(gm.getUuid() == null){
			goodsTypeEbi.save(gm);
		}else{
			goodsTypeEbi.update(gm);
		}
		return TO_LIST;
	}

	//ɾ��
	public String delete(){
		goodsTypeEbi.delete(gm);
		return TO_LIST;
	}
	
	//----AJAX-----------------------------------------------
		//----AJAX-----------------------------------------------
		//----AJAX-----------------------------------------------
		//----AJAX-----------------------------------------------
		
		//0.����struts-json��Ӧ��jar��
		//1.����struts����result��typeΪjson
		//2.���ö�Ӧaction���ڵ�package�̳���json-default
		//3.��Ҫ���ص������ṩ��Ӧ��get����
		
		public String getAbc(){
			return "12345";
		}
		public DepModel getDm(){
			DepModel dm = new DepModel();
			dm.setUuid(11L);
			dm.setName("haha");
			dm.setTele("119");
			return dm;
		}
		
		private List<GoodsTypeModel> gtmList;
		public List<GoodsTypeModel> getGtmList() {
			return gtmList;
		}
		
		private boolean flag = true;
		public boolean isFlag() {
			return flag;
		}
		//ajax��ȡ��Ӧ�̶�Ӧ�������Ϣ
		public String ajaxGetBySm(){
			//���ݹ�Ӧ�̵�uuid��ȡ��Ӧ�������Ϣ
			sm = new SupplierModel();
			sm.setUuid(smUuid);
			gtmList = goodsTypeEbi.getAllBySm(sm.getUuid());
			flag = gtmList.size() > 0;
			//��ν����ݴ��ݳ�ȥ��JSON��ʽ��
			//ʹ��json������JSONArray
			return "ajaxGetBySm";
		}

}
package com.myit.erp.invoice.storedetail.web;

import java.util.List;

import com.myit.erp.invoice.storedetail.business.ebi.StoreDetailEbi;
import com.myit.erp.invoice.storedetail.vo.StoreDetailModel;
import com.myit.erp.invoice.storedetail.vo.StoreDetailQueryModel;
import com.myit.erp.util.base.BaseAction;

public class StoreDetailAction extends BaseAction{
	public StoreDetailModel sm = new StoreDetailModel();
	public StoreDetailQueryModel sqm = new StoreDetailQueryModel();

	private StoreDetailEbi storeDetailEbi;
	public void setStoreDetailEbi(StoreDetailEbi storeDetailEbi) {
		this.storeDetailEbi = storeDetailEbi;
	}

	//�б�
	public String list(){
		setDataTotal(storeDetailEbi.getCount(sqm));
		List<StoreDetailModel> storeDetailList = storeDetailEbi.getAll(sqm,pageNum,pageCount);
		put("storeDetailList", storeDetailList);
		return LIST;
	}

	//�����
	public String input(){
		if(sm.getUuid()!=null){
			sm = storeDetailEbi.get(sm.getUuid());
		}
		return INPUT;
	}

	//���
	public String save(){
		if(sm.getUuid() == null){
			storeDetailEbi.save(sm);
		}else{
			storeDetailEbi.update(sm);
		}
		return TO_LIST;
	}

	//ɾ��
	public String delete(){
		storeDetailEbi.delete(sm);
		return TO_LIST;
	}

}

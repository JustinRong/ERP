package com.myit.erp.invoice.store.web;

import java.util.List;

import com.myit.erp.invoice.store.business.ebi.StoreEbi;
import com.myit.erp.invoice.store.vo.StoreModel;
import com.myit.erp.invoice.store.vo.StoreQueryModel;
import com.myit.erp.util.base.BaseAction;

public class StoreAction extends BaseAction{
	public StoreModel sm = new StoreModel();
	public StoreQueryModel sqm = new StoreQueryModel();

	private StoreEbi storeEbi;
	public void setStoreEbi(StoreEbi storeEbi) {
		this.storeEbi = storeEbi;
	}

	//�б�
	public String list(){
		setDataTotal(storeEbi.getCount(sqm));
		List<StoreModel> storeList = storeEbi.getAll(sqm,pageNum,pageCount);
		put("storeList", storeList);
		return LIST;
	}

	//�����
	public String input(){
		if(sm.getUuid()!=null){
			sm = storeEbi.get(sm.getUuid());
		}
		return INPUT;
	}

	//���
	public String save(){
		if(sm.getUuid() == null){
			storeEbi.save(sm);
		}else{
			storeEbi.update(sm);
		}
		return TO_LIST;
	}

	//ɾ��
	public String delete(){
		storeEbi.delete(sm);
		return TO_LIST;
	}

}

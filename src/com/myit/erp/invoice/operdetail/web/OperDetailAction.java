package com.myit.erp.invoice.operdetail.web;

import java.util.List;

import com.myit.erp.invoice.operdetail.business.ebi.OperDetailEbi;
import com.myit.erp.invoice.operdetail.vo.OperDetailModel;
import com.myit.erp.invoice.operdetail.vo.OperDetailQueryModel;
import com.myit.erp.util.base.BaseAction;

public class OperDetailAction extends BaseAction{
	public OperDetailModel om = new OperDetailModel();
	public OperDetailQueryModel oqm = new OperDetailQueryModel();

	private OperDetailEbi operDetailEbi;
	public void setOperDetailEbi(OperDetailEbi operDetailEbi) {
		this.operDetailEbi = operDetailEbi;
	}

	//�б�
	public String list(){
		setDataTotal(operDetailEbi.getCount(oqm));
		List<OperDetailModel> operDetailList = operDetailEbi.getAll(oqm,pageNum,pageCount);
		put("operDetailList", operDetailList);
		return LIST;
	}

	//�����
	public String input(){
		if(om.getUuid()!=null){
			om = operDetailEbi.get(om.getUuid());
		}
		return INPUT;
	}

	//���
	public String save(){
		if(om.getUuid() == null){
			operDetailEbi.save(om);
		}else{
			operDetailEbi.update(om);
		}
		return TO_LIST;
	}

	//ɾ��
	public String delete(){
		operDetailEbi.delete(om);
		return TO_LIST;
	}

}

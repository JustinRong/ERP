package com.myit.erp.auth.res.web;

import java.util.List;

import com.myit.erp.auth.res.business.ebi.ResEbi;
import com.myit.erp.auth.res.vo.ResModel;
import com.myit.erp.auth.res.vo.ResQueryModel;
import com.myit.erp.util.base.BaseAction;

public class ResAction extends BaseAction{
	public ResModel rm = new ResModel();
	public ResQueryModel rqm = new ResQueryModel();

	private ResEbi resEbi;
	public void setResEbi(ResEbi resEbi) {
		this.resEbi = resEbi;
	}

	//�б�
	public String list(){
		setDataTotal(resEbi.getCount(rqm));
		List<ResModel> resList = resEbi.getAll(rqm,pageNum,pageCount);
		put("resList", resList);
		return LIST;
	}

	//�����
	public String input(){
		if(rm.getUuid()!=null){
			rm = resEbi.get(rm.getUuid());
		}
		return INPUT;
	}

	//���
	public String save(){
		if(rm.getUuid() == null){
			resEbi.save(rm);
		}else{
			resEbi.update(rm);
		}
		return TO_LIST;
	}

	//ɾ��
	public String delete(){
		resEbi.delete(rm);
		return TO_LIST;
	}

}

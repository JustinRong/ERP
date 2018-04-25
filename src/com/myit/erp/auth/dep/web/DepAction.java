package com.myit.erp.auth.dep.web;

import java.util.List;

import com.myit.erp.auth.dep.business.ebi.DepEbi;
import com.myit.erp.auth.dep.vo.DepModel;
import com.myit.erp.auth.dep.vo.DepQueryModel;
import com.myit.erp.util.base.BaseAction;

public class DepAction extends BaseAction {
	public DepModel dm = new DepModel();
	public DepQueryModel dqm = new DepQueryModel();
	private DepEbi depEbi;
	public DepModel getDm() {
		return dm;
	}
	public void setDm(DepModel dm) {
		this.dm = dm;
	}
	public DepQueryModel getDqm() {
		return dqm;
	}
	public void setDqm(DepQueryModel dqm) {
		this.dqm = dqm;
	}
	public DepEbi getDepEbi() {
		return depEbi;
	}
	public void setDepEbi(DepEbi depEbi) {
		this.depEbi = depEbi;
	}
	
	//跳转到列表页面
	public String list(){
		setDataTotal(depEbi.getCount(dqm));
		List<DepModel> depList = depEbi.getAll(dqm, pageNum, pageCount);
		this.put("depList", depList);
		for(DepModel dm : depList){
			System.out.println(dm.getName());
		}
		return LIST;
	}
	
	//跳转到添加页面
	public String input(){
		if(dm !=null && dm.getUuid()!=null){
			dm = depEbi.get(dm.getUuid());
		}
		return INPUT;
	}
	
	public String save(){
		if(dm!=null && dm.getUuid() == null){
			this.depEbi.save(dm);
		}else{
			this.depEbi.update(dm);
		}
		return TO_LIST;
	}
	
	//删除部门
	public String delete(){
		if(dm!=null && dm.getUuid()!=null)
		this.depEbi.delete(dm);
		return TO_LIST;
	}
}

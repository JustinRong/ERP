package com.myit.erp.auth.dep.business.ebo;

import java.io.Serializable;
import java.util.List;

import com.myit.erp.auth.dep.business.ebi.DepEbi;
import com.myit.erp.auth.dep.dao.dao.DepDao;
import com.myit.erp.auth.dep.vo.DepModel;
import com.myit.erp.util.base.BaseQueryModel;

public class DepEbo implements DepEbi {
	private DepDao depDao;

	public DepDao getDepDao() {
		return depDao;
	}

	public void setDepDao(DepDao depDao) {
		this.depDao = depDao;
	}

	@Override
	public void save(DepModel t) {
		// TODO Auto-generated method stub
		this.depDao.save(t);
	}

	@Override
	public void update(DepModel t) {
		// TODO Auto-generated method stub
		this.depDao.update(t);
	}

	@Override
	public void delete(DepModel t) {
		// TODO Auto-generated method stub
		this.depDao.delete(t);
	}

	@Override
	public List<DepModel> getAll() {
		// TODO Auto-generated method stub
		return this.depDao.getAll();
	}

	@Override
	public DepModel get(Serializable uuid) {
		// TODO Auto-generated method stub
		return this.depDao.get(uuid);
	}

	@Override
	public List<DepModel> getAll(BaseQueryModel model, Integer pageNum, Integer pageCount) {
		// TODO Auto-generated method stub
		return this.depDao.getAll(model, pageNum, pageCount);
	}

	@Override
	public Integer getCount(BaseQueryModel model) {
		// TODO Auto-generated method stub
		return this.depDao.getCount(model);
	}

}

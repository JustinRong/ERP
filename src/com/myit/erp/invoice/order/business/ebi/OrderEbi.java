package com.myit.erp.invoice.order.business.ebi;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.myit.erp.auth.emp.vo.EmpModel;
import com.myit.erp.invoice.order.vo.OrderModel;
import com.myit.erp.invoice.order.vo.OrderQueryModel;
import com.myit.erp.invoice.orderdetail.vo.OrderDetailModel;
import com.myit.erp.util.base.BaseEbi;

@Transactional
public interface OrderEbi extends BaseEbi<OrderModel>{
	/**
	 * 保存采购订单
	 * @param om 订单数据模型（封装了供应商uuid）
	 * @param goodsUuids 商品UUID数组
	 * @param nums	数量数组
	 * @param prices 单价数组
	 * @param creater 制单人
	 */
	public void saveBuyOrder(OrderModel om, Long[] goodsUuids, Integer[] nums,
			Double[] prices, EmpModel creater);
	/**
	 * 获取所有采购订单数据
	 * @param oqm
	 * @param pageNum
	 * @param pageCount
	 * @return
	 */
	public List<OrderModel> getAllBuy(OrderQueryModel oqm, Integer pageNum,
			Integer pageCount);
	
	public int getCountBuyCheck(OrderQueryModel oqm);
	public List<OrderModel> getAllBuyCheck(OrderQueryModel oqm,
			Integer pageNum, Integer pageCount);
	/**
	 * 采购审核通过
	 * @param uuid 被审核订单uuid
	 * @param checker 审核人
	 */
	public void buyCheckPass(Long uuid, EmpModel checker);
	public void buyCheckNoPass(Long uuid, EmpModel login);
	
	//根据查询条件获得任务数量
	public Integer getCountTask(OrderQueryModel oqm);
	
	//根据查询条件获得订单列表
	public List<OrderModel> getAllTask(OrderQueryModel oqm, Integer pageNum, Integer pageCount);
	
	/**
	 * 指派任务
	 * @param uuid 订单uuid
	 * @param completer 跟单人
	 */
	public void assignTask(Long uuid, EmpModel completer);
	
	//根据查询条件获取当前登录人的订单任务数量
	public Integer getCountTask(OrderQueryModel oqm, EmpModel login);
	
	//根据查询条件获得当前登录人的订单列表
	public List<OrderModel> getAllTask(OrderQueryModel oqm, Integer pageNum, Integer pageCount, EmpModel login);

	//结单
	public void endTask(Long uuid);
	
	//根据查询条件获取入库订单列表
	public List<OrderModel> getAllInStore(OrderQueryModel oqm, Integer pageNum, Integer pageCount);
	
	//获取入库订单数量
	public Integer getCountInStore(OrderQueryModel oqm);
	
	/**
	 * 入库操作
	 * @param storeUuid
	 * @param odmUuid
	 * @param num
	 * @param login
	 * @return
	 */
	public OrderDetailModel inGoods(Long storeUuid, Long odmUuid, Integer num, EmpModel login);
	
}

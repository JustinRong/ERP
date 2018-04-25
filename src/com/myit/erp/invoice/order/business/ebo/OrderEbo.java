package com.myit.erp.invoice.order.business.ebo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.myit.erp.auth.emp.vo.EmpModel;
import com.myit.erp.invoice.goods.vo.GoodsModel;
import com.myit.erp.invoice.operdetail.dao.dao.OperDetailDao;
import com.myit.erp.invoice.operdetail.vo.OperDetailModel;
import com.myit.erp.invoice.order.business.ebi.OrderEbi;
import com.myit.erp.invoice.order.dao.dao.OrderDao;
import com.myit.erp.invoice.order.vo.OrderModel;
import com.myit.erp.invoice.order.vo.OrderQueryModel;
import com.myit.erp.invoice.orderdetail.dao.dao.OrderDetailDao;
import com.myit.erp.invoice.orderdetail.vo.OrderDetailModel;
import com.myit.erp.invoice.store.vo.StoreModel;
import com.myit.erp.invoice.storedetail.dao.dao.StoreDetailDao;
import com.myit.erp.invoice.storedetail.vo.StoreDetailModel;
import com.myit.erp.util.base.BaseQueryModel;
import com.myit.erp.util.exception.AppException;
import com.myit.erp.util.num.NumUtil;

public class OrderEbo implements OrderEbi{
	private OrderDao orderDao;
	
	private OrderDetailDao orderDetailDao;
	private StoreDetailDao storeDetailDao;
	private OperDetailDao operDetailDao ;
	
	
	public OrderDetailDao getOrderDetailDao() {
		return orderDetailDao;
	}

	public void setOrderDetailDao(OrderDetailDao orderDetailDao) {
		this.orderDetailDao = orderDetailDao;
	}

	public StoreDetailDao getStoreDetailDao() {
		return storeDetailDao;
	}

	public void setStoreDetailDao(StoreDetailDao storeDetailDao) {
		this.storeDetailDao = storeDetailDao;
	}

	public OperDetailDao getOperDetailDao() {
		return operDetailDao;
	}

	public void setOperDetailDao(OperDetailDao operDetailDao) {
		this.operDetailDao = operDetailDao;
	}

	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	public void save(OrderModel om) {
		orderDao.save(om);
	}

	public void update(OrderModel om) {
		orderDao.update(om);
	}

	public void delete(OrderModel om) {
		orderDao.delete(om);
	}

	public OrderModel get(Serializable uuid) {
		return orderDao.get(uuid);
	}

	public List<OrderModel> getAll() {
		return orderDao.getAll();
	}

	public List<OrderModel> getAll(BaseQueryModel qm, Integer pageNum,Integer pageCount) {
		return orderDao.getAll(qm,pageNum,pageCount);
	}

	public Integer getCount(BaseQueryModel qm) {
		return orderDao.getCount(qm);
	}
	
	//在企业级应用中，业务层方法难度高到低，从1到5，该方法为4
			public void saveBuyOrder(OrderModel om, Long[] goodsUuids, Integer[] nums,Double[] prices, EmpModel creater) {
				//保存订单
				//设置订单号:订单号唯一
				String orderNum = NumUtil.generatorOrderNum();
				om.setOrderNum(orderNum);
				//订单创建时间是当前系统时间
				om.setCreateTime(System.currentTimeMillis());
				//当前保存的是采购订单
				om.setOrderType(OrderModel.ORDER_ORDERTYPE_OF_BUY);
				//新保存的订单的状态是未审核
				om.setType(OrderModel.ORDER_TYPE_OF_BUY_NO_CHECK);
				//制单人
				om.setCreater(creater);
				//对应的供应商（已经封装在了om）
				
				Integer totalNum = 0;
				Double totalPrice = 0.0d;
				Set<OrderDetailModel> odms = new HashSet<OrderDetailModel>();
				for(int i = 0;i<goodsUuids.length;i++){
					//创建订单明细的对象并添加到集合中
					OrderDetailModel odm = new OrderDetailModel();
					//设置订单明细数量
					odm.setNum(nums[i]);
					//设置订单剩余未入库数量值
					odm.setSurplus(nums[i]);
					//设置订单明细单价
					odm.setPrice(prices[i]);
					//设置订单明细的商品
					GoodsModel gm = new GoodsModel();
					gm.setUuid(goodsUuids[i]);
					odm.setGm(gm);
					//设置所属的订单
					odm.setOm(om);
					//将明细对象加入集合
					odms.add(odm);
					
					totalNum += nums[i];
					totalPrice += nums[i]*prices[i];
				}
				//设置订单中对应的所有明细数据
				om.setOdms(odms);
				//设置订单总数量 
				om.setTotalNum(totalNum);
				//设置订单总价值
				om.setTotalPrice(totalPrice);
				
				orderDao.save(om);
			}

			public List<OrderModel> getAllBuy(OrderQueryModel oqm, Integer pageNum,	Integer pageCount) {
				//设置一个固定的条件，订单类别为采购
				oqm.setOrderType(OrderModel.ORDER_ORDERTYPE_OF_BUY);
				return orderDao.getAll(oqm, pageNum, pageCount);
			}
			
			private Integer[] buyCheckOrderTypes = new Integer[]{
					OrderModel.ORDER_ORDERTYPE_OF_BUY,
					OrderModel.ORDER_ORDERTYPE_OF_RETURN_BUY
				};
			public int getCountBuyCheck(OrderQueryModel oqm) {
				
				return orderDao.getCountOrderTypes(oqm,buyCheckOrderTypes);
			}

			public List<OrderModel> getAllBuyCheck(OrderQueryModel oqm,
					Integer pageNum, Integer pageCount) {
				//条件中应该具有一组订单类别为采购或采购退货
				//1.  and (orderType = ???? || orderType = ????)
				//2.  and orderType in (????,????)
				return orderDao.getAllOrderTypes(oqm, pageNum, pageCount,buyCheckOrderTypes);
			}

			public void buyCheckPass(Long uuid, EmpModel checker) {
				//所谓审核实际上是修改业务
				//快照更新
				OrderModel temp = orderDao.get(uuid);
				
				//逻辑校验：比对的数据必须是从数据库中取出的数据，而不能使用页面传递的数据
				if(!temp.getType().equals(OrderModel.ORDER_TYPE_OF_BUY_NO_CHECK)){
					throw new AppException("对不起，请不要进行非法操作！");
				}
				
				//type
				temp.setType(OrderModel.ORDER_TYPE_OF_BUY_CHECK_PASS);
				//审核时间
				temp.setCheckTime(System.currentTimeMillis());
				//审核人
				temp.setChecker(checker);
			}

			public void buyCheckNoPass(Long uuid, EmpModel checker) {
				OrderModel temp = orderDao.get(uuid);
				//逻辑校验
				if(!temp.getType().equals(OrderModel.ORDER_TYPE_OF_BUY_NO_CHECK)){
					throw new AppException("对不起，请不要进行非法操作！");
				}
				temp.setType(OrderModel.ORDER_TYPE_OF_BUY_CHECK_NO_PASS);
				temp.setCheckTime(System.currentTimeMillis());
				temp.setChecker(checker);
			}
			
			
			/*-----------商品运输管理------------------------*/
			private Integer[] taskTypes = new Integer[]{
					OrderModel.ORDER_TYPE_OF_BUY_CHECK_PASS,
					OrderModel.ORDER_TYPE_OF_BUY_BUYING,
					OrderModel.ORDER_TYPE_OF_BUY_IN_STORE,
					OrderModel.ORDER_TYPE_OF_BUY_COMPLETE
				};

			@Override
			public Integer getCountTask(OrderQueryModel oqm) {
				// TODO Auto-generated method stub
				return this.orderDao.getAllTypes(oqm, taskTypes);
			}

			@Override
			public List<OrderModel> getAllTask(OrderQueryModel oqm, Integer pageNum, Integer pageCount) {
				//运输任务必须是已经审核通过的
				return this.orderDao.getAllTypes(oqm, pageNum, pageCount, taskTypes);
			}

			@Override
			public void assignTask(Long uuid, EmpModel completer) {
				// TODO Auto-generated method stub
				OrderModel om = this.orderDao.get(uuid);
				
				//判断是否审核通过
				if(!om.getType().equals(OrderModel.ORDER_TYPE_OF_BUY_CHECK_PASS)){
					throw new AppException("订单审核没通过");
				}
				
				//设置状态为采购中
				om.setType(OrderModel.ORDER_TYPE_OF_BUY_BUYING);
				//设置跟单人
				om.setCompleter(completer);
			}

			@Override
			public Integer getCountTask(OrderQueryModel oqm, EmpModel login) {
				// 设置当前登录人为跟单人
				oqm.setCompleter(login);
				//设置type加强程序的健壮性
				return this.orderDao.getCount(oqm);
			}

			@Override
			public List<OrderModel> getAllTask(OrderQueryModel oqm, Integer pageNum, Integer pageCount,
					EmpModel login) {
				// 设置当前登录人为跟单人
				oqm.setCompleter(login);
				//设置type加强程序的健壮性
				return this.orderDao.getAll(oqm, pageNum, pageCount);
			}

			@Override
			public void endTask(Long uuid) {
				// TODO Auto-generated method stub
				OrderModel om = this.orderDao.get(uuid);
				
				//判断状态是否为采购中
				if(!om.getType().equals(OrderModel.ORDER_TYPE_OF_BUY_BUYING)){
					throw new AppException("订单不在采购中");
				}
				
				//设置状态为入库中
				om.setType(OrderModel.ORDER_TYPE_OF_BUY_IN_STORE);
			}

			@Override
			public List<OrderModel> getAllInStore(OrderQueryModel oqm, Integer pageNum, Integer pageCount) {
				//入库的数据状态必然是正在入库中（采购入库中，销售退货入库中）简单制作一个状态
				oqm.setType(OrderModel.ORDER_TYPE_OF_BUY_IN_STORE);
				return this.orderDao.getAll(oqm, pageNum, pageCount);
			}

			@Override
			public Integer getCountInStore(OrderQueryModel oqm) {
				oqm.setType(OrderModel.ORDER_TYPE_OF_BUY_IN_STORE);
				return orderDao.getCount(oqm);
			}
			
			
			@Override
			public OrderDetailModel inGoods(Long storeUuid, Long odmUuid, Integer num, EmpModel login) {
				// 1.订单明细中的剩余数量要更新
				OrderDetailModel odm = orderDetailDao.get(odmUuid);
				OrderModel om = odm.getOm();
				
				//判断订单是否为入库中状态
				if(!om.getType().equals(OrderModel.ORDER_TYPE_OF_BUY_IN_STORE)){
					throw new AppException("订单没入库");
				}
				
				if(odm.getSurplus()<num){
					throw new AppException("输入数量错误");
				}
				
				//更新订单明细的剩余数量
				odm.setSurplus(odm.getSurplus()-num);
				
				//货物信息需要使用
				GoodsModel gm = odm.getGm();
				StoreModel sm = new StoreModel();
				sm.setUuid(storeUuid);
				
				//2.库存数量要发生变化
				//根据仓库与货物查询
				StoreDetailModel sdm = storeDetailDao.getBySmAndGm(storeUuid, gm.getUuid());
				if(sdm!=null){
					//数据库有记录，修改当前库存数量
					sdm.setNum(sdm.getNum()+num);
				}else{
					//数据库没有记录，添加记录
					sdm = new StoreDetailModel();
					sdm.setGm(gm);
					sdm.setNum(num);
					sdm.setSm(sm);
					storeDetailDao.save(sdm);
				}
				
				//3.数据要求可追踪，记录操作日志
				OperDetailModel opdm = new OperDetailModel();
				opdm.setEm(login);
				opdm.setGm(gm);
				opdm.setNum(num);
				opdm.setOperTime(System.currentTimeMillis());
				opdm.setSm(sm);
				opdm.setType(OperDetailModel.OPER_TYPE_OF_IN);
				operDetailDao.save(opdm);
				
				//4.设置订单状态为入库完毕
				Integer sum = 0;
				for(OrderDetailModel oddm : om.getOdms()){
					sum += oddm.getSurplus();
				}
				if(sum==0){
					//全部入库完毕
					om.setType(OrderModel.ORDER_TYPE_OF_BUY_COMPLETE);
					om.setEndTime(System.currentTimeMillis());
				}
				return odm;
			}
			
		
}

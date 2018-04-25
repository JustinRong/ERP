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
	
	//����ҵ��Ӧ���У�ҵ��㷽���Ѷȸߵ��ͣ���1��5���÷���Ϊ4
			public void saveBuyOrder(OrderModel om, Long[] goodsUuids, Integer[] nums,Double[] prices, EmpModel creater) {
				//���涩��
				//���ö�����:������Ψһ
				String orderNum = NumUtil.generatorOrderNum();
				om.setOrderNum(orderNum);
				//��������ʱ���ǵ�ǰϵͳʱ��
				om.setCreateTime(System.currentTimeMillis());
				//��ǰ������ǲɹ�����
				om.setOrderType(OrderModel.ORDER_ORDERTYPE_OF_BUY);
				//�±���Ķ�����״̬��δ���
				om.setType(OrderModel.ORDER_TYPE_OF_BUY_NO_CHECK);
				//�Ƶ���
				om.setCreater(creater);
				//��Ӧ�Ĺ�Ӧ�̣��Ѿ���װ����om��
				
				Integer totalNum = 0;
				Double totalPrice = 0.0d;
				Set<OrderDetailModel> odms = new HashSet<OrderDetailModel>();
				for(int i = 0;i<goodsUuids.length;i++){
					//����������ϸ�Ķ�����ӵ�������
					OrderDetailModel odm = new OrderDetailModel();
					//���ö�����ϸ����
					odm.setNum(nums[i]);
					//���ö���ʣ��δ�������ֵ
					odm.setSurplus(nums[i]);
					//���ö�����ϸ����
					odm.setPrice(prices[i]);
					//���ö�����ϸ����Ʒ
					GoodsModel gm = new GoodsModel();
					gm.setUuid(goodsUuids[i]);
					odm.setGm(gm);
					//���������Ķ���
					odm.setOm(om);
					//����ϸ������뼯��
					odms.add(odm);
					
					totalNum += nums[i];
					totalPrice += nums[i]*prices[i];
				}
				//���ö����ж�Ӧ��������ϸ����
				om.setOdms(odms);
				//���ö��������� 
				om.setTotalNum(totalNum);
				//���ö����ܼ�ֵ
				om.setTotalPrice(totalPrice);
				
				orderDao.save(om);
			}

			public List<OrderModel> getAllBuy(OrderQueryModel oqm, Integer pageNum,	Integer pageCount) {
				//����һ���̶����������������Ϊ�ɹ�
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
				//������Ӧ�þ���һ�鶩�����Ϊ�ɹ���ɹ��˻�
				//1.  and (orderType = ???? || orderType = ????)
				//2.  and orderType in (????,????)
				return orderDao.getAllOrderTypes(oqm, pageNum, pageCount,buyCheckOrderTypes);
			}

			public void buyCheckPass(Long uuid, EmpModel checker) {
				//��ν���ʵ�������޸�ҵ��
				//���ո���
				OrderModel temp = orderDao.get(uuid);
				
				//�߼�У�飺�ȶԵ����ݱ����Ǵ����ݿ���ȡ�������ݣ�������ʹ��ҳ�洫�ݵ�����
				if(!temp.getType().equals(OrderModel.ORDER_TYPE_OF_BUY_NO_CHECK)){
					throw new AppException("�Բ����벻Ҫ���зǷ�������");
				}
				
				//type
				temp.setType(OrderModel.ORDER_TYPE_OF_BUY_CHECK_PASS);
				//���ʱ��
				temp.setCheckTime(System.currentTimeMillis());
				//�����
				temp.setChecker(checker);
			}

			public void buyCheckNoPass(Long uuid, EmpModel checker) {
				OrderModel temp = orderDao.get(uuid);
				//�߼�У��
				if(!temp.getType().equals(OrderModel.ORDER_TYPE_OF_BUY_NO_CHECK)){
					throw new AppException("�Բ����벻Ҫ���зǷ�������");
				}
				temp.setType(OrderModel.ORDER_TYPE_OF_BUY_CHECK_NO_PASS);
				temp.setCheckTime(System.currentTimeMillis());
				temp.setChecker(checker);
			}
			
			
			/*-----------��Ʒ�������------------------------*/
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
				//��������������Ѿ����ͨ����
				return this.orderDao.getAllTypes(oqm, pageNum, pageCount, taskTypes);
			}

			@Override
			public void assignTask(Long uuid, EmpModel completer) {
				// TODO Auto-generated method stub
				OrderModel om = this.orderDao.get(uuid);
				
				//�ж��Ƿ����ͨ��
				if(!om.getType().equals(OrderModel.ORDER_TYPE_OF_BUY_CHECK_PASS)){
					throw new AppException("�������ûͨ��");
				}
				
				//����״̬Ϊ�ɹ���
				om.setType(OrderModel.ORDER_TYPE_OF_BUY_BUYING);
				//���ø�����
				om.setCompleter(completer);
			}

			@Override
			public Integer getCountTask(OrderQueryModel oqm, EmpModel login) {
				// ���õ�ǰ��¼��Ϊ������
				oqm.setCompleter(login);
				//����type��ǿ����Ľ�׳��
				return this.orderDao.getCount(oqm);
			}

			@Override
			public List<OrderModel> getAllTask(OrderQueryModel oqm, Integer pageNum, Integer pageCount,
					EmpModel login) {
				// ���õ�ǰ��¼��Ϊ������
				oqm.setCompleter(login);
				//����type��ǿ����Ľ�׳��
				return this.orderDao.getAll(oqm, pageNum, pageCount);
			}

			@Override
			public void endTask(Long uuid) {
				// TODO Auto-generated method stub
				OrderModel om = this.orderDao.get(uuid);
				
				//�ж�״̬�Ƿ�Ϊ�ɹ���
				if(!om.getType().equals(OrderModel.ORDER_TYPE_OF_BUY_BUYING)){
					throw new AppException("�������ڲɹ���");
				}
				
				//����״̬Ϊ�����
				om.setType(OrderModel.ORDER_TYPE_OF_BUY_IN_STORE);
			}

			@Override
			public List<OrderModel> getAllInStore(OrderQueryModel oqm, Integer pageNum, Integer pageCount) {
				//��������״̬��Ȼ����������У��ɹ�����У������˻�����У�������һ��״̬
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
				// 1.������ϸ�е�ʣ������Ҫ����
				OrderDetailModel odm = orderDetailDao.get(odmUuid);
				OrderModel om = odm.getOm();
				
				//�ж϶����Ƿ�Ϊ�����״̬
				if(!om.getType().equals(OrderModel.ORDER_TYPE_OF_BUY_IN_STORE)){
					throw new AppException("����û���");
				}
				
				if(odm.getSurplus()<num){
					throw new AppException("������������");
				}
				
				//���¶�����ϸ��ʣ������
				odm.setSurplus(odm.getSurplus()-num);
				
				//������Ϣ��Ҫʹ��
				GoodsModel gm = odm.getGm();
				StoreModel sm = new StoreModel();
				sm.setUuid(storeUuid);
				
				//2.�������Ҫ�����仯
				//���ݲֿ�������ѯ
				StoreDetailModel sdm = storeDetailDao.getBySmAndGm(storeUuid, gm.getUuid());
				if(sdm!=null){
					//���ݿ��м�¼���޸ĵ�ǰ�������
					sdm.setNum(sdm.getNum()+num);
				}else{
					//���ݿ�û�м�¼����Ӽ�¼
					sdm = new StoreDetailModel();
					sdm.setGm(gm);
					sdm.setNum(num);
					sdm.setSm(sm);
					storeDetailDao.save(sdm);
				}
				
				//3.����Ҫ���׷�٣���¼������־
				OperDetailModel opdm = new OperDetailModel();
				opdm.setEm(login);
				opdm.setGm(gm);
				opdm.setNum(num);
				opdm.setOperTime(System.currentTimeMillis());
				opdm.setSm(sm);
				opdm.setType(OperDetailModel.OPER_TYPE_OF_IN);
				operDetailDao.save(opdm);
				
				//4.���ö���״̬Ϊ������
				Integer sum = 0;
				for(OrderDetailModel oddm : om.getOdms()){
					sum += oddm.getSurplus();
				}
				if(sum==0){
					//ȫ��������
					om.setType(OrderModel.ORDER_TYPE_OF_BUY_COMPLETE);
					om.setEndTime(System.currentTimeMillis());
				}
				return odm;
			}
			
		
}

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
	 * ����ɹ�����
	 * @param om ��������ģ�ͣ���װ�˹�Ӧ��uuid��
	 * @param goodsUuids ��ƷUUID����
	 * @param nums	��������
	 * @param prices ��������
	 * @param creater �Ƶ���
	 */
	public void saveBuyOrder(OrderModel om, Long[] goodsUuids, Integer[] nums,
			Double[] prices, EmpModel creater);
	/**
	 * ��ȡ���вɹ���������
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
	 * �ɹ����ͨ��
	 * @param uuid ����˶���uuid
	 * @param checker �����
	 */
	public void buyCheckPass(Long uuid, EmpModel checker);
	public void buyCheckNoPass(Long uuid, EmpModel login);
	
	//���ݲ�ѯ���������������
	public Integer getCountTask(OrderQueryModel oqm);
	
	//���ݲ�ѯ������ö����б�
	public List<OrderModel> getAllTask(OrderQueryModel oqm, Integer pageNum, Integer pageCount);
	
	/**
	 * ָ������
	 * @param uuid ����uuid
	 * @param completer ������
	 */
	public void assignTask(Long uuid, EmpModel completer);
	
	//���ݲ�ѯ������ȡ��ǰ��¼�˵Ķ�����������
	public Integer getCountTask(OrderQueryModel oqm, EmpModel login);
	
	//���ݲ�ѯ������õ�ǰ��¼�˵Ķ����б�
	public List<OrderModel> getAllTask(OrderQueryModel oqm, Integer pageNum, Integer pageCount, EmpModel login);

	//�ᵥ
	public void endTask(Long uuid);
	
	//���ݲ�ѯ������ȡ��ⶩ���б�
	public List<OrderModel> getAllInStore(OrderQueryModel oqm, Integer pageNum, Integer pageCount);
	
	//��ȡ��ⶩ������
	public Integer getCountInStore(OrderQueryModel oqm);
	
	/**
	 * ������
	 * @param storeUuid
	 * @param odmUuid
	 * @param num
	 * @param login
	 * @return
	 */
	public OrderDetailModel inGoods(Long storeUuid, Long odmUuid, Integer num, EmpModel login);
	
}

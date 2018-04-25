package com.myit.erp.invoice.order.web;

import java.util.List;

import com.myit.erp.auth.emp.business.ebi.EmpEbi;
import com.myit.erp.auth.emp.vo.EmpModel;
import com.myit.erp.invoice.goods.business.ebi.GoodsEbi;
import com.myit.erp.invoice.goods.vo.GoodsModel;
import com.myit.erp.invoice.goodstype.business.ebi.GoodsTypeEbi;
import com.myit.erp.invoice.goodstype.vo.GoodsTypeModel;
import com.myit.erp.invoice.order.business.ebi.OrderEbi;
import com.myit.erp.invoice.order.vo.OrderModel;
import com.myit.erp.invoice.order.vo.OrderQueryModel;
import com.myit.erp.invoice.store.business.ebi.StoreEbi;
import com.myit.erp.invoice.store.vo.StoreModel;
import com.myit.erp.invoice.supplier.business.ebi.SupplierEbi;
import com.myit.erp.invoice.supplier.vo.SupplierModel;
import com.myit.erp.util.base.BaseAction;

public class OrderAction extends BaseAction{
	public OrderModel om = new OrderModel();
	public OrderQueryModel oqm = new OrderQueryModel();

	private OrderEbi orderEbi;
	public void setOrderEbi(OrderEbi orderEbi) {
		this.orderEbi = orderEbi;
	}
	private SupplierEbi supplierEbi;
	private GoodsTypeEbi goodsTypeEbi;
	private GoodsEbi goodsEbi;
	private EmpEbi empEbi;
	private StoreEbi storeEbi;
	
	private Long completerUuid;
	
	
	
	

	public Long getCompleterUuid() {
		return completerUuid;
	}

	public void setCompleterUuid(Long completerUuid) {
		this.completerUuid = completerUuid;
	}

	public StoreEbi getStoreEbi() {
		return storeEbi;
	}

	public void setStoreEbi(StoreEbi storeEbi) {
		this.storeEbi = storeEbi;
	}

	public SupplierEbi getSupplierEbi() {
		return supplierEbi;
	}

	public void setSupplierEbi(SupplierEbi supplierEbi) {
		this.supplierEbi = supplierEbi;
	}

	public GoodsTypeEbi getGoodsTypeEbi() {
		return goodsTypeEbi;
	}

	public void setGoodsTypeEbi(GoodsTypeEbi goodsTypeEbi) {
		this.goodsTypeEbi = goodsTypeEbi;
	}

	public GoodsEbi getGoodsEbi() {
		return goodsEbi;
	}

	public void setGoodsEbi(GoodsEbi goodsEbi) {
		this.goodsEbi = goodsEbi;
	}

	public EmpEbi getEmpEbi() {
		return empEbi;
	}

	public void setEmpEbi(EmpEbi empEbi) {
		this.empEbi = empEbi;
	}
	
	//�б�
	public String list(){
		setDataTotal(orderEbi.getCount(oqm));
		List<OrderModel> orderList = orderEbi.getAll(oqm,pageNum,pageCount);
		put("orderList", orderList);
		return LIST;
	}

	//�����
	public String input(){
		if(om.getUuid()!=null){
			om = orderEbi.get(om.getUuid());
		}
		return INPUT;
	}

	//���
	public String save(){
		if(om.getUuid() == null){
			orderEbi.save(om);
		}else{
			orderEbi.update(om);
		}
		return TO_LIST;
	}

	//ɾ��
	public String delete(){
		orderEbi.delete(om);
		return TO_LIST;
	}
	
	//-----------------�ɹ����-------------------------
			public String buyList(){
				setDataTotal(orderEbi.getCount(oqm));
				List<OrderModel> orderList = orderEbi.getAllBuy(oqm,pageNum,pageCount);
				put("orderList", orderList);
				return "buyList";
			}

			//����ɹ�����
			public String buyInput(){
				//���ؾ��������Ϣ�Ĺ�Ӧ����Ϣ�������������Ʒ��Ϣ
				List<SupplierModel> supplierList = supplierEbi.getAllUnionTwo();
				//��һ����Ӧ�̵ľ�����Ʒ���������
				List<GoodsTypeModel> gtmList = goodsTypeEbi.getAllUnionBySm(supplierList.get(0).getUuid());
				//��һ��������Ʒ
				List<GoodsModel> gmList = goodsEbi.getAllByGtm(gtmList.get(0).getUuid());
				//��һ����Ʒ�ļ۸�
				put("supplierList",supplierList);
				put("gtmList",gtmList);
				put("gmList",gmList);
				return "buyInput";
			}
			
			public Long[] goodsUuids;
			public Integer[] nums;
			public Double[] prices;
			//����ɹ�����
			public String buySave(){
				//om.sm.uuid->om
				//��������
				orderEbi.saveBuyOrder(om,goodsUuids,nums,prices,getLogin());
				return "toBuyList";
			}
			
			//�鿴�ɹ�������ϸ
			public String buyDetail(){
				//����om.uuid��ȡ��Ӧ��Ϣ�����ص�����ҳ
				om = orderEbi.get(om.getUuid());
				return "buyDetail";
			}
			
			//-----------------------------------------------------
			//-----------------------------------------------------
			//----------------�ɹ������ؿ�ʼ-------------------------
			//-----------------------------------------------------
			//-----------------------------------------------------
			
			public String buyCheckList(){
				//Ҫ���ض��������б�
				setDataTotal(orderEbi.getCountBuyCheck(oqm));
				List<OrderModel> orderList = orderEbi.getAllBuyCheck(oqm,pageNum,pageCount);
				put("orderList", orderList);
				return "buyCheckList";
			}
			
			public String buyCheckDetail(){
				//����om.uuid��ѯom��ҳ����ʾ
				om = orderEbi.get(om.getUuid());
				return "buyCheckDetail";
			}
			
			//�ɹ����ͨ��
			public String buyCheckPass(){
				//ҵ���
				orderEbi.buyCheckPass(om.getUuid(),getLogin());
				//��ҳ��
				return "toBuyCheckList";
			}
			//�ɹ���˲���
			public String buyCheckNoPass(){
				//ҵ���
				orderEbi.buyCheckNoPass(om.getUuid(),getLogin());
				//��ҳ��
				return "toBuyCheckList";
			}
			
			
			//----�ɹ������ؽ���-------------------------------
	    //----AJAX---------------------------------
			//----AJAX---------------------------------
			//----AJAX---------------------------------
			//----AJAX---------------------------------
			
			public Long supplierUuid;
			public Long gtmUuid;
			public Long gmUuid;
			
			private List<GoodsTypeModel> gtmList;
			private List<GoodsModel> gmList;
			private GoodsModel gm;
			public GoodsModel getGm() {
				return gm;
			}
			public List<GoodsTypeModel> getGtmList() {
				return gtmList;
			}
			public List<GoodsModel> getGmList() {
				return gmList;
			}
			//ajax���ݹ�Ӧ�̵�uuid��ȡ������Ʒ��Ϣ
			public String ajaxGetGtmAndGm(){
				gtmList = goodsTypeEbi.getAllUnionBySm(supplierUuid);
				gmList = goodsEbi.getAllByGtm(gtmList.get(0).getUuid());
				gm = gmList.get(0);
				return "ajaxGetGtmAndGm";
			}
			
			//�Ѿ�ʹ�ù��ģ���Ҫ���˵���Ʒuuid
			public String used;			//'11','22','33','44',
			//ajax���ݹ�Ӧ�̵�uuid��ȡ������Ʒ��Ϣ(�������ݹ��˹���)
			public String ajaxGetGtmAndGm2(){
				gtmList = goodsTypeEbi.getAllUnionBySm(supplierUuid);
				
				//������������е�һ������������Ʒ�Ѿ�ʹ����ϣ���û�н���ɾ�������¸���Ʒ����Ӧ����Ʒ����������ĵ���������û����Ʒ���׳�����Խ���쳣
				//���������ɾ������Ӧ����Ʒ��𼴿�
				//���˵�������Ʒ�Ѿ�ʹ����ϵ���Ʒ���
				Goods:
				for(int i = gtmList.size()-1 ;i>=0;i--){
					List<GoodsModel> gmListTemp = goodsEbi.getAllByGtm(gtmList.get(i).getUuid());
					for(GoodsModel gmTemp : gmListTemp){
						if(!used.contains("'"+gmTemp.getUuid()+"'")){
							//���������ֱ���ж���һ�����
							continue Goods;
						}
					}
					//ѭ��ִ����ϣ�ִ�е��Ĵ˴�������ѭ��û�н���if��䣬�������Ʒ��ʹ�ù�
					gtmList.remove(i);
				}
				//���������е����һ��������û��ʹ�ù�����Ʒ
				
				gmList = goodsEbi.getAllByGtm(gtmList.get(0).getUuid());
				//��ǰ��ȡ����Ʒ��Ϣ��uuid�����ظ��ģ�Ҫ������й���
				//�Լ��Ͻ��е���ɾ������ô���������������
				//��gmList��ȡ�����е�Ԫ�أ������������뱾�δ��ݹ�����used���бȶԣ��ȶ��귢���ظ��ģ�ɾ������������У�
				for(int i = gmList.size()-1 ;i >=0 ;i--){
					Long uuid = gmList.get(i).getUuid();
					//�жϸ�uuid�Ƿ������used��
					if(used.contains("'"+uuid+"'")){
						gmList.remove(i);
					}
				}
				gm = gmList.get(0);
				return "ajaxGetGtmAndGm";
			}
			
			//ajax������Ʒ���uuid��ȡ��Ʒ��Ϣ
			public String ajaxGetGm(){
				gmList = goodsEbi.getAllByGtm(gtmUuid);
				//��������
				for(int i = gmList.size()-1 ;i >=0 ;i--){
					Long uuid = gmList.get(i).getUuid();
					//�жϸ�uuid�Ƿ������used��
					if(used.contains("'"+uuid+"'")){
						gmList.remove(i);
					}
				}
				gm = gmList.get(0);
				return "ajaxGetGm";
			}
			
			//ajax������Ʒuuid��ȡ��Ʒ�۸���Ϣ
			public String ajaxGetPrice(){
				gm = goodsEbi.get(gmUuid);
				return "ajaxGetPrice";
			}
			
			
			//----��Ʒ�����������------------------------------------
			public String taskList(){
				this.setDataTotal(orderEbi.getCountTask(oqm));
				List<OrderModel> orderList = orderEbi.getAll(oqm, pageNum, pageCount);
				this.put("orderList", orderList);
				return "taskList";
			}
			
			public String taskDetail(){
				//�������䲿�ŵ�����Ա����Ϣ
				List<EmpModel> empList = empEbi.getAllByDep(this.getLogin().getDm().getUuid());
				this.put("empList", empList);
				om = orderEbi.get(om.getUuid());
				return "taskDetail";
			}
			
			//ָ������
			public String assignTask(){
				//om�а�������������:om.uuid		om.completer.uuid
				System.out.println("++++++++++"+om.getUuid());
				EmpModel completer = new EmpModel();
				completer.setUuid(completerUuid);
				om.setCompleter(completer);
				orderEbi.assignTask(om.getUuid(), om.getCompleter());
				return "toTaskList";
			}
			
			//��ѯ��ǰ��¼�˵�������������
			public String tasks(){
				//��ǰ��¼�˲�ѯ������ֻ�ܲ�ѯ�Լ����������Բ�ѯ������Ҫ����������������Ϊ��¼��
				//״ֵ̬�Ƿ�Ҫ�����ã���ҵ������Ҫ�������ã�Ŀ����Ϊ�˰�ȫ�ԣ����㲻����Ҳ���ԣ����ݼ�����˰�ȫ���ϣ�
				this.setDataTotal(orderEbi.getCountTask(oqm, this.getLogin()));
				List<OrderModel> orderList = orderEbi.getAllTask(oqm, pageNum, pageCount, getLogin());
				this.put("orderList", orderList);
				return "tasks";
			}
			
			//��ѯ��ǰ��¼�˵�һ������
			public String task(){
				om = orderEbi.get(om.getUuid());
				return "task";
			}
			
			//�����������
			public String endTask(){
				orderEbi.endTask(om.getUuid());
				return "toTasks";
			}
			
			//----������---------------------------------
			public String inStoreList(){
				setDataTotal(orderEbi.getCountInStore(oqm));
				List<OrderModel> orderList = orderEbi.getAllInStore(oqm, pageNum, pageCount);
				put("orderList", orderList);
				return "inStoreList";
			}
			
			//������������ϸҳ��
			public String inStoreDetail(){
				//�������вֿ���Ϣ
				List<StoreModel> storeList = storeEbi.getAll();
				put("storeList", storeList);
				om = orderEbi.get(om.getUuid());
				return "inStoreDetail";
			}
}

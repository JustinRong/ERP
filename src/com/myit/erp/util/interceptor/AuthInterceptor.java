package com.myit.erp.util.interceptor;

import org.apache.struts2.ServletActionContext;

import com.myit.erp.auth.emp.vo.EmpModel;
import com.myit.erp.util.exception.AppException;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;


//Ȩ��У��
public class AuthInterceptor extends AbstractInterceptor {

	//��ǰ��¼����Դ���ط�ʽ����¼ʱ���أ������������¼����Ϣ�У�
	public String intercept(ActionInvocation invocation) throws Exception {
		// TODO Auto-generated method stub
		//1����ȡ���β���
		//2���жϱ��β����Ƿ����ز���
		//3����session�л�ȡ��ǰ��¼����Ϣ
		//4����ȡ��ǰ��¼�˿�ִ�е�������Դ����Դ--��ɫ--Ա����
		//5���жϵ�ǰ��¼�˶�Ӧ�����пɲ�����Դ���Ƿ�������β���
		System.out.println("������Auth������");
		String actionName = invocation.getProxy().getAction().getClass().getName();
		String methodName = invocation.getProxy().getMethod();
		String allName = actionName+"."+methodName;
		System.out.println("--------"+allName);
		String allRes = ServletActionContext.getServletContext().getAttribute("allRes").toString();
		if(!allRes.contains(allName)){
			return invocation.invoke();
		}
		
		EmpModel loginEm = (EmpModel)ActionContext.getContext().getSession().get(EmpModel.EMP_LOGIN_USER_OBJECT_NAME);
		System.out.println("------loginEm"+loginEm.toString());
		if(loginEm.getResAll().contains(allName)){
			return invocation.invoke();
		}
		throw new AppException("�Բ���Ȩ�޲���");
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

}

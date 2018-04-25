package com.myit.erp.util.interceptor;

import com.myit.erp.auth.emp.vo.EmpModel;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;


public class LoginInterceptor extends AbstractInterceptor {

	public String intercept(ActionInvocation invocation) throws Exception {
		
		System.out.println("����������");
		// ִ�г��˵�¼����֮������в�������¼��֤
		//��ȡ���β�������Ϣ
		String actionName = invocation.getProxy().getAction().getClass().getName();//��ȡ����Action��ȫ·��
		String methodName = invocation.getProxy().getMethod();//��ȡ����Action�ķ�������
		String allName = actionName+"."+methodName;//ƴ�շ���·������
		
		String openName = invocation.getProxy().getActionName();//��ȡ����Action���ύ��ʽ����
		
		System.out.println("actionName:"+actionName);
		System.out.println("methodName:"+methodName);
		System.out.println("openName:"+openName);
		
		if("login".equals(openName)){
			return invocation.invoke();
		}
		//com.myit.erp.auth.emp.web.EmpAction.login
		if("com.myit.erp.auth.emp.web.EmpAction.login".equals(allName)){
			return invocation.invoke();
		}
		
		//��ȡ��ǰ��¼����Ϣ
		EmpModel loginEmp = (EmpModel)ActionContext.getContext().getSession().get(EmpModel.EMP_LOGIN_USER_OBJECT_NAME);
		//�����ǰû�е�¼����ת����¼ҳ��
		if(loginEmp == null){
			//��ת����¼ҳ��
			return "noLogin";
		}
		
		
		return invocation.invoke();
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

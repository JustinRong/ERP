package com.myit.erp.util.interceptor;

import com.myit.erp.util.exception.AppException;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class ExceptionInterceptor extends AbstractInterceptor {

	public String intercept(ActionInvocation invocation) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("������E������");
		try {
			return invocation.invoke();
		} catch (AppException e) {
			//��¼��־
			//������־������Ա����
			//����
			ActionSupport as = (ActionSupport) invocation.getAction();
			as.addActionError(as.getText(e.getMessage()));
			return "error";
		} catch (Exception e) {
//			ActionSupport as = (ActionSupport) invocation.getAction();
//			as.addActionError("�Բ��𣬷������ѹرգ�����ϵ����Ա��");
//			return "error";
			//��¼��־
			//������־������Ա����
			//����
			e.printStackTrace();
			return invocation.invoke();
		}
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

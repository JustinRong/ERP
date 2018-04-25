package com.myit.erp.util.interceptor;

import org.apache.struts2.ServletActionContext;

import com.myit.erp.auth.emp.vo.EmpModel;
import com.myit.erp.util.exception.AppException;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;


//权限校验
public class AuthInterceptor extends AbstractInterceptor {

	//当前登录人资源加载方式（登录时加载，并将器放入登录人信息中）
	public String intercept(ActionInvocation invocation) throws Exception {
		// TODO Auto-generated method stub
		//1、获取本次操作
		//2、判断本次操作是否被拦截操作
		//3、从session中获取当前登录人信息
		//4、获取当前登录人可执行的所有资源（资源--角色--员工）
		//5、判断当前登录人对应的所有可操作资源中是否包含本次操作
		System.out.println("调用了Auth拦截器");
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
		throw new AppException("对不起，权限不足");
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

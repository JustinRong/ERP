package com.myit.erp.util.interceptor;

import com.myit.erp.auth.emp.vo.EmpModel;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;


public class LoginInterceptor extends AbstractInterceptor {

	public String intercept(ActionInvocation invocation) throws Exception {
		
		System.out.println("调用拦截器");
		// 执行除了登录操作之外的所有操作做登录认证
		//获取本次操作的信息
		String actionName = invocation.getProxy().getAction().getClass().getName();//获取访问Action的全路径
		String methodName = invocation.getProxy().getMethod();//获取访问Action的方法名称
		String allName = actionName+"."+methodName;//拼凑方法路径名称
		
		String openName = invocation.getProxy().getActionName();//获取访问Action的提交方式名称
		
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
		
		//获取当前登录人信息
		EmpModel loginEmp = (EmpModel)ActionContext.getContext().getSession().get(EmpModel.EMP_LOGIN_USER_OBJECT_NAME);
		//如果当前没有登录，跳转到登录页面
		if(loginEmp == null){
			//跳转到登录页面
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

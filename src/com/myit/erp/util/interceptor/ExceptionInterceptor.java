package com.myit.erp.util.interceptor;

import com.myit.erp.util.exception.AppException;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class ExceptionInterceptor extends AbstractInterceptor {

	public String intercept(ActionInvocation invocation) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("调用了E拦截器");
		try {
			return invocation.invoke();
		} catch (AppException e) {
			//记录日志
			//发送日志到程序员邮箱
			//报警
			ActionSupport as = (ActionSupport) invocation.getAction();
			as.addActionError(as.getText(e.getMessage()));
			return "error";
		} catch (Exception e) {
//			ActionSupport as = (ActionSupport) invocation.getAction();
//			as.addActionError("对不起，服务器已关闭，请联系管理员！");
//			return "error";
			//记录日志
			//发送日志到程序员邮箱
			//报警
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

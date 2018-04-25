package com.myit.erp.util.listener;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.myit.erp.auth.res.business.ebi.ResEbi;
import com.myit.erp.auth.res.vo.ResModel;


public class AllResLoadListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		// TODO Auto-generated method stub
		//读取所有资源信息，放入ServletContext范围
		//使用spring的上下文对象
		System.out.println("---------------6666666666666----------");
		ServletContext context = event.getServletContext();
		WebApplicationContext wc = WebApplicationContextUtils.getWebApplicationContext(context);
		ResEbi resEbi = (ResEbi)wc.getBean("resEbi");
		List<ResModel> resList = resEbi.getAll();
		StringBuffer sb = new StringBuffer();
		for(ResModel rm : resList){
			sb.append(rm.getUrl());
			sb.append(",");
		}
		
		//放入上下文中
		context.setAttribute("allRes", sb.toString());
		
		System.out.println("------"+sb.toString());
		
	}

}

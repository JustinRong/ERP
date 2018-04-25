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
		//��ȡ������Դ��Ϣ������ServletContext��Χ
		//ʹ��spring�������Ķ���
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
		
		//������������
		context.setAttribute("allRes", sb.toString());
		
		System.out.println("------"+sb.toString());
		
	}

}

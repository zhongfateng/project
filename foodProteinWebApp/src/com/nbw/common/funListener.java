package com.nbw.common;

import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.nbw.common.util.AppInfo;

public class funListener implements ServletContextListener{

	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
	}

	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub..
		Timer t= new Timer();
		TaskDelay u= new TaskDelay();
		u.setContext(arg0.getServletContext());
		long m=120000l;
		t.schedule(u, m);
	}

}

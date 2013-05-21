package com.nbw.common;

import java.util.TimerTask;

import javax.servlet.ServletContext;

import com.nbw.common.util.AppInfo;

public class TaskDelay extends TimerTask{

	private ServletContext context;
	
	
	public ServletContext getContext() {
		return context;
	}


	public void setContext(ServletContext context) {
		this.context = context;
	}


	@Override
	public void run() {
		SysParameter sysParam = new SysParameter();
		sysParam.loadFile();

		ServletContext application =this.context;
        
		/**
		 * 把项目的真实路径和绝对路径放入Application的“appInfo”中
		 */
		//获得系统的真实路径
		String realPath = application.getRealPath("");
		//获得系统的绝对路径
		String basePath = "/"+application.getServletContextName() + "/";
		//把系统的真实信息放入Application中
		AppInfo appInfo = new AppInfo();
		appInfo.setRealPath(realPath);
		appInfo.setBasePath(basePath);
		application.setAttribute("appInfo", appInfo);
		
		/**
		 * 把当前皮肤对应的起始目录和css文件分别存入Application的“skinPath”和"cssFile"中
		 */
		String skin ="default";//从配置文件中读取系统的皮肤
		String skinPath =basePath+"resources/skins/"+skin+"/";
		String cssFile =basePath+"resources/skins/"+skin+"/css/style.css";
		String adminCssFile =basePath+"resources/skins/"+skin+"/css/admin_style.css";
		application.setAttribute("skinPath",skinPath);
		application.setAttribute("cssFile",cssFile);
		application.setAttribute("adminCssFile",adminCssFile);
		
	}

}

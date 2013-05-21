package com.nbw.common;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nbw.common.util.AppInfo;

/**
 * 加载系统参数的servlet
 * 
 * 
 * @author 张为锋
 * Jan 4, 2009  4:21:56 PM
 *
 */
public class LoadSysParamServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occure
	 */
	public void init() throws ServletException {
		/**
		 * 加载系统参数
		 */
		SysParameter sysParam = new SysParameter();
		sysParam.loadFile();

		ServletContext application =getServletContext();
        
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
		String csscssFile =basePath+"resources/skins/"+skin+"/css/css.css";
		
		application.setAttribute("skinPath",skinPath);
		application.setAttribute("skinPath_new",skinPath);
		application.setAttribute("cssFile",cssFile);
		application.setAttribute("cssFile_new",cssFile);
		application.setAttribute("adminCssFile",adminCssFile);
		application.setAttribute("adminCssFile_new",adminCssFile);
		application.setAttribute("csscssFile",csscssFile);
}

}

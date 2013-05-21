/**
 * 
 */
package com.nbw.common.util;

import java.io.Serializable;

/**
 * 应用的相关信息。
 * 在登录系统时对该对象复制，放入session
 * 
 * @author 张为锋
 * Jan 8, 2009  11:12:35 AM
 *
 */
public class AppInfo implements Serializable {
	private static final long serialVersionUID = -2856358408698175525L;
	
	private String realPath;//项目的真实路径。例如：“d:\app\NbwExample\”
	private String basePath;//项目的url路径。例如："http://192.168.0.80:7001/NbwExample/"
	
	/**
	 * @return 项目的真实路径。例如：“d:\app\NbwExample\”
	 */
	public String getRealPath() {
		return realPath;
	}
	/**
	 * @param realPath 项目的真实路径。例如：“d:\app\NbwExample\”
	 */
	public void setRealPath(String realPath) {
		this.realPath = realPath;
	}
	/**
	 * @return 项目的url路径。例如："http://192.168.0.80:7001/NbwExample/"
	 */
	public String getBasePath() {
		return basePath;
	}
	/**
	 * @param urlPath 项目的url路径。例如："http://192.168.0.80:7001/NbwExample/"
	 */
	public void setBasePath(String urlPath) {
		this.basePath = urlPath;
	}
}

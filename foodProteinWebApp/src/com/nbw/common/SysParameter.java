/**
 * 
 */
package com.nbw.common;

import java.io.InputStream;
import java.util.Properties;

/**
 * 获得“src/sysParameter.properties”中的系统参数
 * 
 * @author 张为锋 Aug 2, 2008 4:13:15 PM
 * 
 */
public class SysParameter {
	private static java.util.Properties parameterFile = null;

	/**
	 * 加载配置文件中的信息
	 * 
	 * 
	 * @author 张为锋 Aug 2, 2008 4:29:15 PM
	 */
	public void loadFile() {
		if (parameterFile != null)
			return;

		InputStream in = null;
		try {
			in = this.getClass()
					.getResourceAsStream("/sysParameter.properties");
			Properties p = new Properties();
			p.load(in);
			parameterFile = p;
			System.out.println("加载系统参数完成！");
		} catch (Exception e) {
			System.out.println("加载系统参数出错！");
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (Exception e) {
			}
		}
	}

	/**
	 * 获得配置文件中某个参数的值
	 * 
	 * @param paramName
	 *            参数名称
	 * @return 参数值
	 * 
	 * @author 张为锋 Aug 2, 2008 4:26:15 PM
	 */
	public static String getParameter(String propertyName) {
		if(parameterFile == null){new SysParameter().loadFile();}
		return parameterFile.getProperty(propertyName);
	}
}

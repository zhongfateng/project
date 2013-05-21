/**
 * 
 */
package com.nbw.components.skin;

/**
 * 
 * 获取和样式相关的信息
 * @author 张为锋
 * Jan 12, 2009  1:48:00 PM
 *
 */
public class Style {
	/**
	 * 获得某种皮肤的CSS起始路径
	 * @param skin	皮肤的种类
	 * @param basePath	项目起始路径
	 * @return	该种皮肤对应的CSS起始路径
	 * 
	 * @author 张为锋
	 * Jan 12, 2009  2:07:03 PM
	 */
	public static String getSkinPath(String skin,String basePath){
		String cssPath = basePath + "resources/skins/default/";
		
		if (skin!=null&&!"".equals(skin)&&!basePath.equals(null)&&!"".equals(basePath)){
//			if ("default".equals(skin)){
//				cssPath = basePath + "resources/skins/default/";
//			}else if("skin1".equals(skin)){
//				cssPath = basePath + "resources/skins/skin1/";
//			}
			//wyf May 13,2009 10:30修改， 不明白skin1的意义，暂时更改为
			cssPath = basePath + "resources/skins/"+skin+"/";
			//cssPath = basePath + "resources/skins/default/";
		}
		
		return cssPath;
	}
	
	/**
	 * 获得皮肤对应的CSS文件
	 * @param skin 	皮肤的种类
	 * @param basePath	项目起始路径
	 * @return css的完整名称，包括路径和名称
	 * 
	 * @author 张为锋
	 * May 4, 2009  2:50:27 PM
	 */
	public static String getCssFile(String skin,String basePath){
		String cssFile=getSkinPath(skin,basePath)+"css/style.css";
		return cssFile;
	}
	
	/**
	 * 获得皮肤对应的CSS文件
	 * @param skin 	皮肤的种类
	 * @param basePath	项目起始路径
	 * @return 后台css的完整名称，包括路径和名称
	 * 
	 * @author 高冬冬
	 * May 4, 2009  2:50:27 PM
	 */
	public static String getAdminCssFile(String skin,String basePath){
		String adminCssFile=getSkinPath(skin,basePath)+"css/admin_style.css";
		return adminCssFile;
	}
}

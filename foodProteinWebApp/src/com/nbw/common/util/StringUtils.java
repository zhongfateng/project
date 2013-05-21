package com.nbw.common.util;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import com.nbw.common.Constants;


public class StringUtils {
	public static String urlToPortlet(String url,String width,String height){
		String portalPath ="http://www.standards-portal.cn/BzptWeb"; //SysParameter.getParameter("portalPath");
		url=portalPath+"/appmanager/eip/main?_nfpb=true&_pageLabel="+Constants.common_portlet_lable+"&url="+
		    url.replaceAll("&", "'")+"&width="+width+"&height="+height;
		return url;
	}
	
	public static void main(String []args){
	System.out.println(urlToPortlet("/bzptsfgwc.action?m=checkOrder&df=3","938","400"));
	}
	
public static String generateId(){
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String timeStamp = sdf.format(new Date());
		
		String tmp = UUID.randomUUID().toString().replaceAll("-","");
		
		return timeStamp+tmp.substring(0,14);	
				
	}
	/**
	 * 判断对象是否为空，假如为空则返回空字符串，假如不为空则返回toString值
	 * @param value
	 * @return
	 */
	public static String toEmpty(Object value){
		return value!=null?value.toString():"";
	}
	
	/**
	 * string转码
	 * @param value 传过来的值
	 * @param vEncode  原始码
	 * @param mEncode  目标码
	 * @return
	 */
	public static String stringtoIso(String value,String vEncode,String mEncode){
		try {
			return new String(value.getBytes(vEncode),mEncode);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return value;
		}
		
	}
	
	public static String replaceAll(String str, String arg1, String arg2) {
		int index = str.indexOf(arg1);
		String tmp = "";
		while (index != -1) {
			tmp = tmp + str.substring(0, index) + arg2;
			str = str.substring(index + 1);
			index = str.indexOf(arg1);
		}
		tmp = tmp + str;

		return tmp;
	}

	/**
	 * 首字母小写
	 * @param param
	 * @return
	 */
	public static String toFirstLowerCase(String param) {
		if (null == param || "".equals(param))
			return "";
		else
			return param.substring(0, 1).toLowerCase() + param.substring(1);
	}
}

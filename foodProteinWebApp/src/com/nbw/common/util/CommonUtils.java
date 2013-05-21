package com.nbw.common.util;

import java.text.DecimalFormat;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.converters.IntegerConverter;

import com.nbw.common.Constants;

public class CommonUtils {

	public static String ObjectToString(Object obj) {
		IntegerConverter i = new IntegerConverter();
		return obj == null ? "" : i.convert(java.lang.String.class, obj)
				.toString();
	}

	public static String DealString(String dealStr) {
		return dealStr == null ? "" : dealStr;
	}

	public static String DealFloat(Float f) {
		return !"0.0".equals(String.valueOf(f == null ? 0 : f)) ? String
				.valueOf(f == null ? 0 : f) : "";
	}

	public static String DoubleString(Double dou) {
		if (dou == null || "".equals(dou)) {
			return "";
		}
		return String.valueOf(dou);
	}

	public static String LongString(Long lon) {
		if (lon == null || "".equals(lon)) {
			return "";
		}
		return String.valueOf(lon);
	}

	public static Double StringToDouble(String str) {
		double dou = 0;
		try {
			if (null != str && !"".equals(str)) {
				dou = Double.parseDouble(str);
			}
		} catch (NumberFormatException e) {
			dou = 0;
			e.printStackTrace();
		}
		return dou;
	}

	public static Long StringToLong(String str) {
		Long lon = 0l;
		try {
			if (null != str && !"".equals(str)) {
				lon = Long.parseLong(str);
			}
		} catch (NumberFormatException e) {
			lon = 0l;
			e.printStackTrace();
		}
		return lon;
	}

	public static Integer StringToInteger(String str) {
		int inte = 0;
		try {
			if (null != str && !"".equals(str)) {
				inte = Integer.parseInt(str);
			}
		} catch (NumberFormatException e) {
			inte = 0;
			e.printStackTrace();
		}
		return inte;
	}

	/*
	 * dou 要处理的值 spot 保留小数位 0 负数取整，大于0时 保留spot位小数
	 */
	public static String dealSpot(double dou, int spot) {
		String spots = "0";
		if (spot > 0) {
			spots += ".";
			for (int i = 0; i < spot; i++) {
				spots += "0";
			}
		}
		DecimalFormat df = new DecimalFormat(spots);

		return df.format(dou);
	}

	public static String DealInteger(Integer dealInt) {
		return dealInt == null ? "" : dealInt.toString();
	}

	/*
	 * DHTMLXGrid表格控件保存数据时XML串
	 */
	public static String getDhtmlxGridXml(String model, String sid, String tid,
			String text) {

		return Constants.DHTMLXGRID_SAVEXML_PREFIX + "<action type='" + model
				+ "' sid='" + sid + "' tid='" + tid + "'>" + text + "</action>"
				+ Constants.DHTMLXGRID_SAVEXML_SUFFIX;
	}

	/*
	 * 取客户端的IP地址
	 */
	public static String getRemoteAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	/*
	 * 取客户端的IP地址
	 */
	public static String getRemoteIPAddr(HttpServletRequest request) {
		String ip = null;
		Enumeration enu = request.getHeaderNames();
		while (enu.hasMoreElements()) {
			String name = (String) enu.nextElement();
			if (name.equalsIgnoreCase("X-Forwarded-For")) {
				ip = request.getHeader(name);
			} else if (name.equalsIgnoreCase("Proxy-Client-IP")) {
				ip = request.getHeader(name);
			} else if (name.equalsIgnoreCase("WL-Proxy-Client-IP")) {
				ip = request.getHeader(name);
			}
			if ((ip != null) && (ip.length() != 0)) {
				break;
			}
		}
		if ((ip == null) || (ip.length() == 0)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
	
	/*
	 * 设置通用的response  返回值是xml的通用设置
	 * 
	 * 狄巨礼
	 */
	public static HttpServletResponse setCommonXmlResponse(HttpServletResponse response) {
		response.setContentType("text/xml; charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		response.setHeader("Pragma", "No-cache");// HTTP 1.1
		response.setHeader("Cache-Control", "no-cache");// HTTP 1.0
		response.setHeader("Expires", "0");
		return response;
	}
	
	/*
	 * 设置通用的response 返回值是html的通用设置
	 * 
	 * 狄巨礼
	 */
	public static HttpServletResponse setCommonHtmlResponse(HttpServletResponse response) {
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("utf-8");
		response.setHeader("Pragma", "No-cache");// HTTP 1.1
		response.setHeader("Cache-Control", "no-cache");// HTTP 1.0
		response.setHeader("Expires", "0");
		return response;
	}
}

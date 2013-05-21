package com.nbw.common.util.tupu;

/**
 * Program  : PathUtil.java
 * Author   : zhongwf
 * Create   : 2008-8-28 ����04:39:39
 *
 * Copyright 2006 by Embedded Internet Solutions Inc.,
 * All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of Embedded Internet Solutions Inc.("Confidential Information").  
 * You shall not disclose such Confidential Information and shall 
 * use it only in accordance with the terms of the license agreement 
 * you entered into with Embedded Internet Solutions Inc.
 *
 */


import java.net.URL;
import java.util.Properties;

/**
 * 
 * @author zhongwf
 * @version 1.0.0
 * @2008-8-28 ����04:39:39
 */
public class PathUtil {
	private static String KEY = "WEB-INF/classes/";

	private static String getClassesPath() {
		URL path = PathUtil.class.getProtectionDomain().getCodeSource()
				.getLocation();
		String url = path.toString();
		if (url.startsWith("file:"))
			url = url.substring("file:".length());
		return url;
	}

	public static String getSrcRoot() {
		String path = getClassesPath();
		String os = System.getProperty("os.name").trim();
		if (path.indexOf(KEY) > 0) {
			path = path.substring(0, path.indexOf(KEY) + KEY.length());
		}
		return path;
	}

	public static String getWebRoot() {
		String path = getClassesPath();
		if (path.indexOf(KEY) > 0) {
			path = path.substring(0, path.indexOf(KEY));
		}
		return path;
	}

	public static String formateUrl(String url) {
		if (url.startsWith("file:/")) {
			url = url.substring("file:/".length());
		}
		return url;
	}

	public static void main(String[] args) {
		Properties propertie = System.getProperties();
		PathUtil pathUtil = new PathUtil();
		System.out.println(pathUtil.getSrcRoot());
	}
}

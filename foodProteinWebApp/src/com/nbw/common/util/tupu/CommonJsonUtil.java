package com.nbw.common.util.tupu;

/**
 * Program  : QQListJsonUtil.java
 * Author   : zhongwf
 * Create   : 2012-1-25 下午11:32:07
 *
 * Copyright 2008 by iPanel Technologies Ltd.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of iPanel Technologies Ltd.("Confidential Information").  
 * You shall not disclose such Confidential Information and shall 
 * use it only in accordance with the terms of the license agreement 
 * you entered into with iPanel Technologies Ltd.
 *
 */


import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.log4j.Logger;



/**
 * 
 * @author zhongwf
 * @version 1.0.0
 * @2012-1-25 下午11:32:07
 */
public class CommonJsonUtil {
	private static Logger logger = Logger.getLogger(CommonJsonUtil.class);

	public static String filterByParam(String data, String key)
		{
		JSONObject jsObject = JSONObject.fromObject(data);
			data = jsObject.getString(key);
			logger.debug("filter json data=" + data);
		
		return data;
	}

	public static Object toArray(String str, Class cla) {
		JSONArray array = JSONArray.fromObject(str);
		Object object = null;
		try {
			object = JSONArray.toArray(array, cla);
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage()+str);
		}
		return object;
	}

	public static Object toObj(String str, Class cla, Map<String, Class> map) {
		Object object = null;
		try {
			JSONObject jObject = JSONObject.fromObject(str);
			object = JSONObject.toBean(jObject, cla, map);
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage());
		}
		return object;
	}

	public static Object toObj(String str, Class cla) {
		return toObj(str, cla, null);
	}

	public static String toStr(Object obj) {
		return toStr(obj, new String[0]);
	}

	/**
	 * 
	 * @author zhongwf
	 * @create 2012-2-28 下午11:41:03
	 * @since
	 * @param obj
	 * @param excludes
	 *            �?��过滤的参�?
	 * @return
	 */
	public static String toStr(Object obj, String[] excludes) {
		JsonConfig config = new JsonConfig();
		config.setExcludes(excludes);
		return toStr(obj, config);
	}

	public static String toStr(Object[] obj, String[] excludes) {
		JsonConfig config = new JsonConfig();
		config.setExcludes(excludes);
		return toStr(obj, config);
	}

	public static String toStr(Object obj, JsonConfig config) {
		JSONObject array = JSONObject.fromObject(obj, config);
		String data = null;
		try {
			data = array.toString();
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage());
		}
		return data;
	}

	public static String toStr(Object[] objs, JsonConfig config) {
		JSONArray array = JSONArray.fromObject(objs, config);
		String data = null;
		try {
			data = array.toString();
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage());
		}
		return data;
	}

	public static String toStr(Object[] objs) {
		JSONArray array = JSONArray.fromObject(objs);
		String data = null;
		try {
			data = array.toString();
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage());
		}
		return data;
	}

	public static JSONArray filterArray(JSONObject object, String key) {
		JSONArray array = null;
		array = object.getJSONArray(key);
		return array;
	}

	public static JSONArray filterArray(String Str, String key) {
		JSONObject jsonObject = JSONObject.fromObject(Str);

		return filterArray(jsonObject, key);
	}
	public static String searchJsonForStr(String key, String data) {
		Pattern pattern = Pattern.compile("\"" + key + "\":\"([^\"]*)");
		String value = null;
		Matcher matcher = pattern.matcher(data);
		if (matcher.find())
			value = matcher.group(1);
		return value;
	}

	public static String searchJsonForNum(String key, String data) {
		Pattern pattern = Pattern.compile("\"" + key + "\":([^,}\\]]*)");
		String value = null;
		Matcher matcher = pattern.matcher(data);
		if (matcher.find())
			value = matcher.group(1);
		return value;
	}
}

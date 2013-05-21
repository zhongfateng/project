package com.nbw.hygl.util;

import java.util.UUID;

public class VerifyCodeUtil {
	public static String generator(){
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
	}
	/**
	 * 
	 * @param code UUID-UserID格式的验证码
	 * @return userId
	 * 4d0a2b97-bf45-4301-b476-1fe7effc03a5-6
	 */
	public static String parseUserId(String code){
		int index = code.lastIndexOf("-")+1;
		return code.substring(index);
	}
	/**
	 * 
	 * @param code UUID-UserID格式的验证码
	 * @return uuid
	 */
	public static String parseUuid(String code){
		int index = code.lastIndexOf("-");
		return code.substring(0, index);
	}
	
}

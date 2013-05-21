package com.nbw.common.util;

import java.io.IOException;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
/**
 *讲二进制转换成字符串或者将字符串转换成二进制
 *@author Administratoner
 */
public class BinaryToString {
	private static BASE64Encoder encoder = new sun.misc.BASE64Encoder();  
	  private  static BASE64Decoder decoder = new sun.misc.BASE64Decoder();  
	   public static String BinaryToString(byte[] bytes){  
	      
	        try {  
	            return encoder.encodeBuffer(bytes).trim();  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	            return null;  
	        }  
	    }  
	      
	   public static byte[] base64StringToBinary(String base64String){  
	        try {  
	        	return  decoder.decodeBuffer(base64String);  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	            return null;
	        }  
	       
	    }  
}

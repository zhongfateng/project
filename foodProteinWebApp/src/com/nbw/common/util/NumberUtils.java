package com.nbw.common.util;

import java.text.DecimalFormat;

public class NumberUtils {
	   private static DecimalFormat df = new DecimalFormat("0.00"); 
	   
	   /**
	    * 将数字过大的double值格式化
	    * @param value
	    * @return
	    */
	   public static String formatNumber(double value){
		   return df.format(value);
	   }
	
	     /**
	      * 判断数字是否为空且数字是否合法
	      *   by宋英杰
	      * @param value
	      * @return
	      */
         public static double isEmptyAndNumber(String value){
        	 Double reDouble=0.0;
        	 if (value!=null&&!value.trim().equals("")){
        		  if (isNumeric(value)){
        			  reDouble=Double.parseDouble(value);
        		  }
        	 }
        	 return reDouble;
         }
         
         /**
          * 判断一个数字是否是合法的数字包含判断小数
          * apache.common.StringUtils.isNumeric的这个方法如果有小数点就不行
          * @param value
          * @return
          */
         public static boolean isNumeric(String value){
        	 boolean istrue=false;
        	 java.util.regex.Pattern   p1=java.util.regex.Pattern.compile( "^\\d+$"); 
             java.util.regex.Pattern   p2=java.util.regex.Pattern.compile( "^\\d+\\.\\d+$"); 
             java.util.regex.Matcher   m1=p1.matcher(value); 
             java.util.regex.Matcher   m2=p2.matcher(value); 
             if(m1.matches()){ 
            	 istrue=true;
             }else   if(m2.matches()){   
            	 istrue=true;
             }else{ 
            	 istrue=false;
             }
             return istrue;
         }
         
         public static void main(String []args){
        	 //String   str= "123.3e333"; 
             double str=234234234234234.343;
             System.out.println(NumberUtils.formatNumber(str));
         }
}

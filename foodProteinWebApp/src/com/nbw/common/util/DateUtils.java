package com.nbw.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.nbw.common.Constants;

/**
 * 
 * 本类中包括日期转换相关的方法
 * 
 * @author 张为锋 Dec 31, 2008 2:56:33 PM
 * 
 */
public class DateUtils {
	

	/**
	 * 获取现在时间
	 * 
	 * @param format
	 * @return 现在时间
	 */
    public static Date getNowTime() {
        Date currentTime = new Date();
            
        return currentTime;
    }
	  /**
	 * 获取现在时间(yyyy-MM-dd HH:mm:ss)
	 * 
	 * @param format
	 * @return 现在时间
	 */
    public static String getNowTimeStringLong() {           
        return DateToStringLong(getNowTime());
    }
    
	/**
	 * 将Date格式日期转换成字符串格式(yyyy-MM-dd HH:mm:ss)
	 * @param value 需要转换的日期值(Date)
	 * @return 转换成功后的字符串
	 */
    public static String DateToStringLong(Date value) {
       
    	SimpleDateFormat df = new SimpleDateFormat(Constants.DATA_FORMAT_LONG);
    	String str = df.format(value);  
        
        return str;
    }
	
	/**
	 * 把日期字符串转换为具体日期
	 * 
	 * @param dateString
	 *            要转换的日期字符串，格式：yyyy-MM-dd,如果字符串为""，返回null
	 * @return 转换后的日期
	 */
	public static Date stringToDate(String dateString) {
		return stringToDate(dateString, "yyyy-MM-dd");
	}

	/**
	 * 把日期字符串转换为具体日期
	 * 
	 * @param dateString
	 *            要转换的日期字符串
	 * @param fmt
	 *            格式，例如"yyyy-MM-dd"
	 * @return 转换后的日期
	 */
	public static Date stringToDate(String dateString, String fmt) {
		Date tempDate = null;

		if (dateString == null)
			return tempDate;
		if (dateString.equals(""))
			return tempDate;

		SimpleDateFormat dateformat = new SimpleDateFormat(fmt);
		try {
			tempDate = dateformat.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return tempDate;
	}

	/**
	 * 把日期转换为字符串
	 * 
	 * @param date
	 *            要转换的日期
	 * @return 转换后的字符串，格式"yyyy-MM-dd"
	 */
	public static String dateToString(Date date) {
		return dateToString(date, "yyyy-MM-dd");
	}

	/**
	 * 把日期转换为字符串
	 * 
	 * @param date
	 *            要转换的日期
	 * @param fmt
	 *            格式,例如"yyyy-MM-dd"
	 * @return
	 */
	public static String dateToString(Date date, String fmt) {
		String string = "";

		if (date == null)
			return string;

		SimpleDateFormat dateformat = new SimpleDateFormat(fmt);
		string = dateformat.format(date);

		return string;
	}

	/**
	 * 把日期字符串转换为具体日期
	 * 
	 * @param dateString
	 *            要转换的日期字符串，格式：yyyy-MM-dd HH:mm:ss,如果字符串为""，返回null
	 * @return 转换后的日期
	 */
	public static Date stringToDatetime(String dateString) {
		Date tempDate = null;

		if (dateString == null)
			return tempDate;
		if (dateString.equals(""))
			return tempDate;

		SimpleDateFormat dateformat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		try {
			tempDate = dateformat.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return tempDate;
	}

	/**
	 * 把日期转换为字符串
	 * 
	 * @param date
	 *            要转换的日期
	 * @return 转换后的字符串，格式"yyyy-MM-dd HH:mm:ss"
	 */
	public static String datetimeToString(Date date) {
		String string = "";

		if (date == null)
			return string;

		SimpleDateFormat dateformat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		string = dateformat.format(date);

		return string;
	}

	/**
	 * 取得当前时间的字符串
	 * 
	 * @return 当前时间的字符串，格式"yyyy-MM-dd"
	 */

	public static String getToday() {
		String tempString = "";
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			tempString = dateformat.format(new Date());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempString;
	}

	/**
	 * 取得当前年的字符串
	 * 
	 * @return 当前时间的字符串，格式"yyyy"
	 */

	public static String getYear() {
		String tempString = "";
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy");
		try {
			tempString = dateformat.format(new Date());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempString;
	}
	
	/**
	 * 取得当前年月的字符串
	 * 
	 * @return 当前时间的字符串，格式"yyyy-MM"
	 */

	public static String getYearMonth() {
		String tempString = "";
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM");
		try {
			tempString = dateformat.format(new Date());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempString;
	}
	
	/**
	 * 取得当前时间的字符串
	 * 
	 * @return 当前时间的字符串，格式"yyyy-MM-dd HH:mm:ss"
	 */
	public static String getDatetime() {
		String tempString = "";
		SimpleDateFormat dateformat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		try {
			tempString = dateformat.format(new Date());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempString;
	}

	/**
	 * 计算两日期差多少天。第二个日期减第一个日期。可能为负数。
	 * 
	 * @param time1
	 *            yyyy-MM-dd格式
	 * @param time2
	 *            yyyy-MM-dd格式
	 * @return
	 */
	public static Long getDateDiff(String time1, String time2) {
		long diff = 0;
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date date1 = ft.parse(time1);
			Date date2 = ft.parse(time2);
			diff = date2.getTime() - date1.getTime();
			diff = diff / 1000 / 60 / 60 / 24;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return new Long(diff);
	}

	/**
	 * 计算两日期差多少天。第二个日期减第一个日期。可能为负数。
	 * 
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static Long getDateDiff(Date d1, Date d2) {
		long diff = 0;
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
		try {
			d1 = ft.parse(ft.format(d1));
			d2 = ft.parse(ft.format(d2));
			diff = d2.getTime() - d1.getTime();
			diff = diff / 1000 / 60 / 60 / 24;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return new Long(diff);
	}

	public static int getDays(GregorianCalendar g1, GregorianCalendar g2) {
		int elapsed = 0;
		GregorianCalendar gc1, gc2;

		if (g2.after(g1)) {
			gc2 = (GregorianCalendar) g2.clone();
			gc1 = (GregorianCalendar) g1.clone();
		} else {
			gc2 = (GregorianCalendar) g1.clone();
			gc1 = (GregorianCalendar) g2.clone();
		}

		gc1.clear(Calendar.MILLISECOND);
		gc1.clear(Calendar.SECOND);
		gc1.clear(Calendar.MINUTE);
		gc1.clear(Calendar.HOUR_OF_DAY);

		gc2.clear(Calendar.MILLISECOND);
		gc2.clear(Calendar.SECOND);
		gc2.clear(Calendar.MINUTE);
		gc2.clear(Calendar.HOUR_OF_DAY);

		while (gc1.before(gc2)) {
			gc1.add(Calendar.DATE, 1);
			elapsed++;
		}
		return elapsed - 1;
	}
	// 获得本季度
	 public static String getThisSeasonTime(int month){     
	        int array[][] = {{1,2,3},{4,5,6},{7,8,9},{10,11,12}};     
	        int season = 1;     
	        if(month>=1&&month<=3){     
	            season = 1;     
	        }     
	        if(month>=4&&month<=6){     
	            season = 2;     
	        }     
	        if(month>=7&&month<=9){     
	            season = 3;     
	        }     
	        if(month>=10&&month<=12){     
	            season = 4;     
	        }     
	        int start_month = array[season-1][0];     
	        int end_month = array[season-1][2];     
	             
	        Date date = new Date();     
	        SimpleDateFormat   dateFormat   =   new   SimpleDateFormat("yyyy");//可以方便地修改日期格式        
	        String  years  = dateFormat.format(date);        
	        int years_value = Integer.parseInt(years);     
	             
	        int start_days =1;//years+"-"+String.valueOf(start_month)+"-1";//getLastDayOfMonth(years_value,start_month);     
	        int end_days = getLastDayOfMonth(years_value,end_month);     
	        String seasonDate = years_value+"-"+start_month+"-"+start_days+";"+years_value+"-"+end_month+"-"+end_days;     
	        return seasonDate;     
	             
	    }     
	 
	 	/**    
	     * 获取某年某月的最后一天    
	     * @param year 年    
	     * @param month 月    
	     * @return 最后一天    
	     */     
	   private static int getLastDayOfMonth(int year, int month) {     
	         if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8     
	                   || month == 10 || month == 12) {     
	               return 31;     
	         }     
	         if (month == 4 || month == 6 || month == 9 || month == 11) {     
	               return 30;     
	         }     
	         if (month == 2) {     
	               if (isLeapYear(year)) {     
	                   return 29;     
	               } else {     
	                   return 28;     
	               }     
	         }     
	         return 0;     
	   }     
	   
	   /**    
	    * 是否闰年    
	    * @param year 年    
	    * @return     
	    */     
	  public static boolean isLeapYear(int year) {     
	        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);     
	  }
	  
	  /**
	   * 获取年月日日期字符串 例如2009年12月15日
	   * shilei 
	   */
	  public static String getYearMonDay() {
		  Calendar calender = Calendar.getInstance();
		  int year = calender.get(Calendar.YEAR);
		  int month = calender.get(Calendar.MONTH)+1;
		  int day = calender.get(Calendar.DAY_OF_MONTH);
		  return String.valueOf(year)+"年"+String.valueOf(month)+"月"+String.valueOf(day)+"日";
	  }
	  
	  /**
	   * 获取星期
	   * shilei
	   */
	  public static String getDayOfWeek() {
		  Calendar calender = Calendar.getInstance();
		  calender.setTime(new Date());
		  if (1==calender.get(Calendar.DAY_OF_WEEK)) {
			  return "星期日";
		  } else if (2==calender.get(Calendar.DAY_OF_WEEK)) {
			  return "星期一";
		  } else if (3==calender.get(Calendar.DAY_OF_WEEK)) {
			  return "星期二";
		  } else if (4==calender.get(Calendar.DAY_OF_WEEK)) {
			  return "星期三";
		  } else if (5==calender.get(Calendar.DAY_OF_WEEK)) {
			  return "星期四";
		  } else if (6==calender.get(Calendar.DAY_OF_WEEK)) {
			  return "星期五";
		  } else if (7==calender.get(Calendar.DAY_OF_WEEK)) {
			  return "星期六";
		  }
		  return "";
	  }
	  
	  /**
		 * @author 李帅 Nov 26, 2009 PM 取得当月第一天时间的字符串
		 * 
		 * @return 当前时间的字符串，格式"yyyy-MM-dd"
		 */
		public static String getFirstDayOfMonth() {
			String tempString = "";
			SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
			Calendar firstDate = Calendar.getInstance();
			firstDate.set(Calendar.DATE, 1);
			try {
				tempString = dateformat.format(firstDate.getTime());
			} catch (Exception e) {
				e.printStackTrace();
			}
			return tempString;
		}
}

package com.nbw.common.util;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import com.ibm.icu.util.Calendar;

public  class RandomNumber {
	
	public static String randMath()
	{
		Calendar c=Calendar.getInstance();
		Date d = c.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Random r = new Random();
		BigDecimal bd = new BigDecimal(r.nextInt() / 1000);
		String randnum=sdf.format(d)+bd.abs();
        return randnum;		
	}

}

package com.nbw.common;

import java.sql.Timestamp;
import java.util.Date;

/**
 * 定时执行的操作
 * 
 * @author qcy
 * 
 */
public class JobOperating {

	/**
	 * 例子方法 注意方法不能有返回值，参数好像也不能有
	 */
	public void testTime() {
		System.out.println("定时执行的程序"
				+ new Timestamp(new Date().getTime()).toString());
	}

}

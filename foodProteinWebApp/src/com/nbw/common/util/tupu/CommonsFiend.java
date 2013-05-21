/**
 * Program  : CommonsFiend.java
 * Author   : zhuogf
 * Create   : 2006-5-10 15:17:44
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

package com.nbw.common.util.tupu;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 
 * @author zhuogf
 * @version 1.0.0
 * @2006-5-10 15:17:44
 */
public class CommonsFiend {
	/**
	 * Logger for this class
	 */
	static String[] stringsNum = { "0", "1", "2", "3", "4", "5", "6", "7", "8",
			"9", "A", "B", "C", "D", "E", "F" };

	private static CommonsFiend commonsFiend;

	private static SimpleDateFormat format = new SimpleDateFormat(
			"yyyyMMddhhmmssSSS");

	public CommonsFiend() {

	}

	public static CommonsFiend initiate() {
		if (commonsFiend == null) {
			commonsFiend = new CommonsFiend();
		}
		return commonsFiend;
	}

	/**
	 * ��ȡ��������
	 * 
	 * @author zhuogf
	 * @create 2006-5-18 14:08:44
	 * @since
	 * @param len
	 * @return
	 */
	public static String getUniqueId(int len) {
		if (len > 10)
			len = 10;
		return getUniqueId(len, 999999999);
		// return getDynamicIdByTime();
	}

	/**
	 * ���ʱ����������
	 * 
	 * @author kongxiangpeng
	 * @create 2008-4-14 ����09:16:11
	 * @since
	 * @return
	 */
	public synchronized static String getDynamicIdByTime() {
		String time = "";
		// String dynamicId = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSS");
		time = sdf.format(Calendar.getInstance().getTime());
		// dynamicId = time+getRandom(100);
		// while(dynamicId.length()<=14){
		// dynamicId = dynamicId + "0";
		// }
		return time;
	}

	public static String getUniqueId(int length, int maxrandom) {
		String tmpstr = "";

		String thread = format.format(new Date())
				+ Integer.toString(getRandom(maxrandom));
		thread = Integer.toString(thread.hashCode());
		if (thread.indexOf("-") >= 0)
			thread = thread.substring(thread.indexOf("-") + 1);
		if (thread.length() < length) {
			for (int i = thread.length(); i < length; i++)
				tmpstr = tmpstr + "0";

			thread = tmpstr + thread;
		}
		return thread;
	}

	public static int getRandom(int max) {
		return (int) (Math.random() * (double) max);
	}

	/**
	 * ��ȡ��ǰ ��-��-�ա�
	 * 
	 * @author zhuogf
	 * @create 2006-5-18 14:16:20
	 * @since
	 * @return
	 */

	public static String getRandomString(int length) {
		char[] ch = new char[length];
		for (int j = 0; j < length; j++) {
			boolean isflag = true;
			int num = 0;
			while (isflag) {
				num = (int) (Math.random() * 123);
				if (num >= 48 && num <= 57 || num >= 65 && num <= 90
						|| num >= 97 && num <= 122) {

					ch[j] = (char) num;
					break;
				}

			}

		}
		return new String(ch);
	}

	public static String createStbForHZ() {
		String stbId = "11";// ��ʾ����ַ��У����
		for (int i = 0; i < 2; i++) {
			stbId = stbId + (int) (Math.random() * 10);// �ն˲�Ʒ�������֤���
		}
		for (int i = 0; i < 3; i++) {
			stbId = stbId + (int) (Math.random() * 10);// �ն˲�Ʒ���������
		}
		stbId = stbId + "01";// �ն�����
		for (int i = 0; i < 2; i++) {
			stbId = stbId + (int) (Math.random() * 10);// Ӳ���汾��
		}
		for (int i = 0; i < 1; i++) {
			stbId = stbId + (int) (Math.random() * 2);// ��ʾ��12λ�ն˲�Ʒ����ʶ�����ͷ�
		}
		// stbId=stbId+"0";//��ʾ��12λ�ն˲�Ʒ����ʶ�����ͷ�
		for (int i = 0; i < 12; i++) {
			stbId = stbId + stringsNum[(int) (Math.random() * 16)];
		}
		return stbId;
	}

}

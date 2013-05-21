package com.nbw.hygl.util;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import com.nbw.common.SysParameter;

public class EmailUtil {
	
	public static void send(String emailAddr,String msg,String id){
		
		String hr = "<a href=\"http://192.168.100.55:8080/foodProteinWebApp/fmptuser.action?m=frontResPwd&userid="+id+"\">点此激活链接，进入下一步</a>";
		SimpleEmail email = new SimpleEmail();
		email.setHostName(SysParameter.getParameter("mailHost"));
		email.setAuthentication(SysParameter.getParameter("mailName"), SysParameter.getParameter("mailPassword"));
		email.setCharset("UTF-8");
		try {
			email.addTo(emailAddr);
			email.setFrom(SysParameter.getParameter("mailName"));
			email.setSubject("中国食品微生物数据共享平台网站邮箱验证");
			email.setContent(hr, "text/html; charset=utf-8");
			//email.setMsg(msg);
			email.send();
		} catch (EmailException e) {
			e.printStackTrace();
		}
	}

}

package com.nbw.common.util;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.VelocityException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.nbw.common.SpringContextHelper;
import com.nbw.common.SysParameter;

public class MailUtil {

	private static SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日");
	private static Session session;

	private MailUtil() {
		Properties props = System.getProperties();
		// 添加smtp服务器属性
		props.put("mail.smtp.host", SysParameter.getParameter("mailHost"));
		props.put("mail.smtp.auth", "true");
		session = Session.getInstance(props, new Authenticator() {
			@Override
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(SysParameter
						.getParameter("mailName"), SysParameter
						.getParameter("mailPassword"));
			}
		});
	}

	/**
	 * 发送邮件接口
	 * 
	 * @param toMail
	 *            发送到的 Mail
	 * @param msgSubjectStr
	 *            主题
	 * @param msgTextStr
	 *            内容
	 * @param username
	 *            接收邮件的用户名
	 */
	public static void sendMail(String toMail, String msgSubjectStr,
			String msgTextStr, String username) {
		if (toMail == null || "".equals(toMail))
			return;
		if (session == null)
			new MailUtil();
		try {
			synchronized (df) {// 定义邮件信息
				MimeMessage message = new MimeMessage(session);
				message.setFrom(new InternetAddress(SysParameter
						.getParameter("mailName")));
				message.addRecipient(Message.RecipientType.TO,
						new InternetAddress(toMail));
				msgSubjectStr = MimeUtility.encodeText(msgSubjectStr, "UTF-8",
						"B");
				message.setSubject(msgSubjectStr);
				Map<String, String> map = new HashMap<String, String>();
				map.put("content", msgTextStr);
				map.put("username", username);
				map.put("sendTime", df.format(new Date()));
				message.setContent(getMailHtmlStyle(map),
						"text/html;charset=UTF-8");
				// session.getTransport("smtp").send(message);
				// //也可以这样创建Transport对象
				// 发送消息
				Transport.send(message);
				System.out.println("邮件发送成功!");
			}
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 读取发送邮件模板信息
	 * 
	 * @param model
	 *            邮件中的参数
	 * @return
	 */
	public static String getMailHtmlStyle(Map<String, String> model) {
		try {
			return VelocityEngineUtils.mergeTemplateIntoString(
					(VelocityEngine) SpringContextHelper
							.getBean("velocityEngine"), "mail.vm", "UTF-8",
					model);
		} catch (VelocityException e) {
			e.printStackTrace();
		}
		return "";
	}
}

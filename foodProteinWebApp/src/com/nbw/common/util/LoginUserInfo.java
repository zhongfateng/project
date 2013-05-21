/**
 * 
 */
package com.nbw.common.util;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 系统当前登录用户信息，登录后保存在Session中
 * @author 张为锋
 * Dec 31, 2008  2:57:44 PM
 *
 */
public class LoginUserInfo implements Serializable {
	private static final long serialVersionUID = -1902943807756728942L;
	
	private String userID;//用户ID
	private String loginID;//用户的登录ID
	private String userName;//用户姓名
	private String passWord;//用户密码
	private String deptID;//用户部门ID
	private String deptName;//用户部门名称
	private String roles;//用户所属的角色，多个角色之间用","分割，最后一个角色后面不带","。例如："01,02,06"
	private String ip;//用户登录IP地址
	private Date loginTime;//登录时间
	private String userInfoCont;//会员提醒信息数量
	private String jbId;//级别ID
	
	public String getJbId() {
		return jbId;
	}

	public void setJbId(String jbId) {
		this.jbId = jbId;
	}

	/**
	 * @return 会员提醒信息数量
	 */

	public String getUserInfoCont() {
		return userInfoCont;
	}
	
	/**
	 * @param 会员提醒信息数量
	 */
	public void setUserInfoCont(String userInfoCont) {
		this.userInfoCont = userInfoCont;
	}	
	
	/**
	 * @return 用户ID
	 */
	public String getUserID() {
		return userID;
	}
	/**
	 * @param userID 用户ID
	 */
	public void setUserID(String userID) {
		this.userID = userID;
	}
	/**
	 * @return 用户的登录ID
	 */
	public String getLoginID() {
		return loginID;
	}
	/**
	 * @param loginID 用户的登录ID
	 */
	public void setLoginID(String loginID) {
		this.loginID = loginID;
	}
	/**
	 * @return 用户姓名
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName 用户姓名
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return 用户密码
	 */
	public String getPassWord() {
		return passWord;
	}
	/**
	 * @param passWord 用户密码
	 */
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	/**
	 * @return 用户部门ID
	 */
	public String getDeptID() {
		return deptID;
	}
	/**
	 * @param deptID 用户部门ID
	 */
	public void setDeptID(String deptID) {
		this.deptID = deptID;
	}
	/**
	 * @return 用户部门名称
	 */
	public String getDeptName() {
		return deptName;
	}
	/**
	 * @param deptName 用户部门名称
	 */
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	/**
	 * @return 用户所属的角色。
	 * 多个角色之间用","分割，最后一个角色后面不带","。
	 * 例如："01,02,06"
	 */
	public String getRoles() {
		return roles;
	}
	/**
	 * @param roles 用户所属的角色。
	 * 多个角色之间用","分割，最后一个角色后面不带","。
	 * 例如："01,02,06"
	 */
	public void setRoles(String roles) {
		this.roles = roles;
	}
	/**
	 * @return 用户登录IP地址
	 */
	public String getIp() {
		return ip;
	}
	/**
	 * @param ip 用户登录IP地址
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}
	/**
	 * @return 登录时间
	 */
	public Date getLoginTime() {
		return loginTime;
	}
	/**
	 * @param loginTime 登录时间
	 */
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

}

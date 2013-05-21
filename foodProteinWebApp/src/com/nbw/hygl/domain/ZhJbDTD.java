package com.nbw.hygl.domain;

/**
 * @author qinchaoyong 显示账户级别时候显示的 传输对象
 */
public class ZhJbDTD {
	// 账户名称ID
	private String zhID;
	// 账户名称
	private String zhm;
	// 账户级别ID
	private String zhJbID;
	// 账户级别名称
	private String zhJbName;
	// 用户名称
	private String userName;
	// 用户登录名称
	private String loginCode;
	//用户ID
	private String userID;

	public ZhJbDTD() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getZhID() {
		return zhID;
	}

	public void setZhID(String zhID) {
		this.zhID = zhID;
	}

	public String getZhm() {
		return zhm;
	}

	public void setZhm(String zhm) {
		this.zhm = zhm;
	}

	public String getZhJbID() {
		return zhJbID;
	}

	public void setZhJbID(String zhJbID) {
		this.zhJbID = zhJbID;
	}

	public String getZhJbName() {
		return zhJbName;
	}

	public void setZhJbName(String zhJbName) {
		this.zhJbName = zhJbName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getLoginCode() {
		return loginCode;
	}

	public void setLoginCode(String loginCode) {
		this.loginCode = loginCode;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	

}

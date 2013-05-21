package com.nbw.hygl.service;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Random;

import com.nbw.hygl.dao.FmptUserDao;
import com.nbw.sys.dao.SysUsersDAO;
import com.nbw.sys.domain.SysUsers;
import com.nbw.sys.domain.SysUsersDetail;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class FmptUserManager {

	private FmptUserDao fmsptUserDao;
	private SysUsersDAO sysUsersDAO;

	public SysUsersDAO getSysUsersDAO() {
		return sysUsersDAO;
	}

	public void setSysUsersDAO(SysUsersDAO sysUsersDAO) {
		this.sysUsersDAO = sysUsersDAO;
	}
	
	

	/**
	 * 保存对象
	 * 
	 * @param sysUsers
	 */
	public boolean save(SysUsers sysUsers) {
		sysUsers.setCreateTime(new Date());
		return fmsptUserDao.save(sysUsers);
	}

	/**
	 * 判断用户是否存在
	 * 
	 * @param loginCode
	 */
	public boolean isLoginCodeExist(String loginCode) {
		return fmsptUserDao.isLoginCodeExist(loginCode);
	}
	/**
	 * 判断企业用户是否存在
	 * 
	 * @param loginCode
	 */
	public boolean isEELoginCodeExist(String loginCode) {
		return fmsptUserDao.isEELoginCodeExist(loginCode);
	}
	/**
	 * 保存对象 保存用户真实姓名
	 * 
	 * @param obj
	 */
	public void saveOrUpdateObject(Object obj) {
		fmsptUserDao.saveOrUpdateObject(obj);
	}

	public void saveOrUpdateObject(Object obj, String realName) {
		SysUsersDetail sud = (SysUsersDetail) obj;
		SysUsers su = sysUsersDAO.findByID(sud.getId());
		su.setName(realName);
		sysUsersDAO.save(su);
		fmsptUserDao.saveOrUpdateObject(obj);
	}

	/**
	 * 得到该级别下边的所有用户
	 * 
	 * @param obj
	 */
	public List isUserJb(String jbId) {
		return (List) fmsptUserDao.isUserJb(jbId);
	}

	/**
	 * 更新对象
	 * 
	 * @param sysUsers
	 */
	public boolean update(SysUsers sysUsers) {
		return fmsptUserDao.update(sysUsers);
	}

	public SysUsersDetail getBzptUsersDetail(String id) {
		return fmsptUserDao.getBzptUsersDetail(id);
	}

	public FmptUserDao getFmsptUserDao() {
		return fmsptUserDao;
	}

	public void setFmsptUserDao(FmptUserDao fmsptUserDao) {
		this.fmsptUserDao = fmsptUserDao;
	}

}

package com.nbw.hygl.domain;

import java.util.Date;

/**
 * AbstractBzptHyGmjl entity provides the base persistence definition of the
 * BzptHyGmjl entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractBzptHyGmjl implements java.io.Serializable {

	// Fields

	private String id;
	private String bztlId;
	private Date cjsj;
	private Double mrjg;
	private String zffs;
	private String userid;
	private String fid;
	private String sid;
	private String ordernum;
	private Double zk;
	private Double jf;
	private String zyly;
	private String type;
	private String encrypt;
	private Long buycount;
	private String bzname;
	private String firstdownload;
	// Constructors

	/** default constructor */
	public AbstractBzptHyGmjl() {
	}

	/** full constructor */
	public AbstractBzptHyGmjl(String bztlId, Date cjsj, Double mrjg,
			String zffs, String userid, String fid, String sid,
			String ordernum, Double zk, Double jf, String zyly, String type,
			String encrypt, Long buycount, String bzname,String firstdownload) {
		this.bztlId = bztlId;
		this.cjsj = cjsj;
		this.mrjg = mrjg;
		this.zffs = zffs;
		this.userid = userid;
		this.fid = fid;
		this.sid = sid;
		this.ordernum = ordernum;
		this.zk = zk;
		this.jf = jf;
		this.zyly = zyly;
		this.type = type;
		this.encrypt = encrypt;
		this.buycount = buycount;
		this.bzname = bzname;
		this.firstdownload = firstdownload;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBztlId() {
		return this.bztlId;
	}

	public void setBztlId(String bztlId) {
		this.bztlId = bztlId;
	}

	public Date getCjsj() {
		return this.cjsj;
	}

	public void setCjsj(Date cjsj) {
		this.cjsj = cjsj;
	}

	public Double getMrjg() {
		return this.mrjg;
	}

	public void setMrjg(Double mrjg) {
		this.mrjg = mrjg;
	}

	public String getZffs() {
		return this.zffs;
	}

	public void setZffs(String zffs) {
		this.zffs = zffs;
	}

	public String getUserid() {
		return this.userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getFid() {
		return this.fid;
	}

	public void setFid(String fid) {
		this.fid = fid;
	}

	public String getSid() {
		return this.sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getOrdernum() {
		return this.ordernum;
	}

	public void setOrdernum(String ordernum) {
		this.ordernum = ordernum;
	}

	public Double getZk() {
		return this.zk;
	}

	public void setZk(Double zk) {
		this.zk = zk;
	}

	public Double getJf() {
		return this.jf;
	}

	public void setJf(Double jf) {
		this.jf = jf;
	}

	public String getZyly() {
		return this.zyly;
	}

	public void setZyly(String zyly) {
		this.zyly = zyly;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getEncrypt() {
		return this.encrypt;
	}

	public void setEncrypt(String encrypt) {
		this.encrypt = encrypt;
	}

	public Long getBuycount() {
		return this.buycount;
	}

	public void setBuycount(Long buycount) {
		this.buycount = buycount;
	}

	public String getBzname() {
		return this.bzname;
	}

	public void setBzname(String bzname) {
		this.bzname = bzname;
	}
	public String getFirstdownload() {
		return firstdownload;
	}

	public void setFirstdownload(String firstdownload) {
		this.firstdownload = firstdownload;
	}
}
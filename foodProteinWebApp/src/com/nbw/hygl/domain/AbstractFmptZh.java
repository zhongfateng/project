package com.nbw.hygl.domain;


/**
 * AbstractBzptZh entity provides the base persistence definition of the BzptZh
 * entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractFmptZh implements java.io.Serializable {

	// Fields

	private String id;
	private String zhm;
	private Double ptmoney;
	private String glid;
	private int type;

	// Constructors

	public AbstractFmptZh(String id, String zhm, Double ptmoney, String glid,
			int type) {
		super();
		this.id = id;
		this.zhm = zhm;
		this.ptmoney = ptmoney;
		this.glid = glid;
		this.type = type;
	}

	/** default constructor */
	public AbstractFmptZh() {
	}

	/** full constructor */
	

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getZhm() {
		return this.zhm;
	}

	public void setZhm(String zhm) {
		this.zhm = zhm;
	}

	public Double getPtmoney() {
		return this.ptmoney;
	}

	public void setPtmoney(Double ptmoney) {
		this.ptmoney = ptmoney;
	}

	public String getGlid() {
		return this.glid;
	}

	public void setGlid(String glid) {
		this.glid = glid;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}


}
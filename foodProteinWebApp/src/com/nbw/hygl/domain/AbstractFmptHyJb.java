package com.nbw.hygl.domain;

/**
 * AbstractFmptHyJb entity provides the base persistence definition of the
 * FmptHyJb entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractFmptHyJb implements java.io.Serializable {

	// Fields

	private String id;
	private Double jbjf;
	private String jbmc;
	private Double jbzk;
	private Integer validFlag=FmptHyJb.VALIDFLAG_TRUE;

	// Constructors

	/** default constructor */
	public AbstractFmptHyJb() {
	}

	/** full constructor */
	public AbstractFmptHyJb(String id, Double jbjf, String jbmc, Double jbzk,
			Integer validFlag) {
		this.id = id;
		this.jbjf = jbjf;
		this.jbmc = jbmc;
		this.jbzk = jbzk;
		this.validFlag = validFlag;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Double getJbjf() {
		return this.jbjf;
	}

	public void setJbjf(Double jbjf) {
		this.jbjf = jbjf;
	}

	public String getJbmc() {
		return this.jbmc;
	}

	public void setJbmc(String jbmc) {
		this.jbmc = jbmc;
	}

	public Double getJbzk() {
		return this.jbzk;
	}

	public void setJbzk(Double jbzk) {
		this.jbzk = jbzk;
	}

	public Integer getValidFlag() {
		return this.validFlag;
	}

	public void setValidFlag(Integer validFlag) {
		this.validFlag = validFlag;
	}

}
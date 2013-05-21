package com.nbw.sys.domain;

/**
 * AbstractZCcs entity provides the base persistence definition of the ZCcs
 * entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractZCcs implements java.io.Serializable {

	// Fields

	private String id;
	private String ccs72826;
	private String ccs72002;
	private String ccs72110;
	private String ccs72120;
	private String ccs72122;

	// Constructors

	/** default constructor */
	public AbstractZCcs() {
	}

	/** full constructor */
	public AbstractZCcs(String ccs72826, String ccs72002, String ccs72110,
			String ccs72120, String ccs72122) {
		this.ccs72826 = ccs72826;
		this.ccs72002 = ccs72002;
		this.ccs72110 = ccs72110;
		this.ccs72120 = ccs72120;
		this.ccs72122 = ccs72122;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCcs72826() {
		return this.ccs72826;
	}

	public void setCcs72826(String ccs72826) {
		this.ccs72826 = ccs72826;
	}

	public String getCcs72002() {
		return this.ccs72002;
	}

	public void setCcs72002(String ccs72002) {
		this.ccs72002 = ccs72002;
	}

	public String getCcs72110() {
		return this.ccs72110;
	}

	public void setCcs72110(String ccs72110) {
		this.ccs72110 = ccs72110;
	}

	public String getCcs72120() {
		return this.ccs72120;
	}

	public void setCcs72120(String ccs72120) {
		this.ccs72120 = ccs72120;
	}

	public String getCcs72122() {
		return this.ccs72122;
	}

	public void setCcs72122(String ccs72122) {
		this.ccs72122 = ccs72122;
	}

}
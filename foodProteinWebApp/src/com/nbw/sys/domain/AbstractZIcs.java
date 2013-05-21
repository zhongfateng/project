package com.nbw.sys.domain;

/**
 * AbstractZIcs entity provides the base persistence definition of the ZIcs
 * entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractZIcs implements java.io.Serializable {

	// Fields

	private String id;
	private String ics71826;
	private String ics71002;
	private String ics71110;
	private String ics71120;
	private String ics71122;

	// Constructors

	/** default constructor */
	public AbstractZIcs() {
	}

	/** full constructor */
	public AbstractZIcs(String ics71826, String ics71002, String ics71110,
			String ics71120, String ics71122) {
		this.ics71826 = ics71826;
		this.ics71002 = ics71002;
		this.ics71110 = ics71110;
		this.ics71120 = ics71120;
		this.ics71122 = ics71122;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIcs71826() {
		return this.ics71826;
	}

	public void setIcs71826(String ics71826) {
		this.ics71826 = ics71826;
	}

	public String getIcs71002() {
		return this.ics71002;
	}

	public void setIcs71002(String ics71002) {
		this.ics71002 = ics71002;
	}

	public String getIcs71110() {
		return this.ics71110;
	}

	public void setIcs71110(String ics71110) {
		this.ics71110 = ics71110;
	}

	public String getIcs71120() {
		return this.ics71120;
	}

	public void setIcs71120(String ics71120) {
		this.ics71120 = ics71120;
	}

	public String getIcs71122() {
		return this.ics71122;
	}

	public void setIcs71122(String ics71122) {
		this.ics71122 = ics71122;
	}

}
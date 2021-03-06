package com.nbw.sys.domain;

import java.util.Date;

/**
 * SysUsersDetail entity. 
 * @author 狄巨礼 May 17, 2010 09:31:00 AM
 */
public class SysUsersDetail extends AbstractSysUsersDetail implements
		java.io.Serializable {


	/** default constructor */
	public SysUsersDetail() {
	}

	/** minimal constructor */
	public SysUsersDetail(String id, SysUsers sysUsers) {
		super(id, sysUsers);
	}

	/** full constructor */
	public SysUsersDetail(String id, SysUsers sysUsers, String char01,
			String char02, String char03, String char04, String char05,
			String char06, String char07, String char08, String char09,
			String char10, String char11, String char12, String char13,
			String char14, String char15, String char16, String char17,
			String char18, String char19, String char20, String char21,
			String char22, String char23, String char24, String char25,
			String char26, String char27, String char28, String char29,
			String char30, Date date01, Date date02, Date date03, Date date04,
			Date date05, Date date06, Date date07, Date date08, Date date09,
			Date date10, double float01, double float02, double float03,
			double float04, double float05, double float06, double float07,
			double float08, double float09, double float10) {
		super(id, sysUsers, char01, char02, char03, char04, char05, char06,
				char07, char08, char09, char10, char11, char12, char13, char14,
				char15, char16, char17, char18, char19, char20, char21, char22,
				char23, char24, char25, char26, char27, char28, char29, char30,
				date01, date02, date03, date04, date05, date06, date07, date08,
				date09, date10, float01, float02, float03, float04, float05,
				float06, float07, float08, float09, float10);
	}

	public SysUsersDetail(String id) {
		this.setId(id);
	}

}

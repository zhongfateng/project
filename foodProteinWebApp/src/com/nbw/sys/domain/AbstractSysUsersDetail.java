package com.nbw.sys.domain;

import java.util.Date;

/**
 * AbstractSysUsersDetail entity provides the base persistence definition of the
 * SysUsersDetail entity. 
 * @author 狄巨礼 May 17, 2010 09:31:00 AM
 */

public abstract class AbstractSysUsersDetail implements java.io.Serializable {

	// Fields

	private String id;
	private SysUsers sysUsers;
	private String char01;
	private String char02;
	private String char03;
	private String char04;
	private String char05;
	private String char06;
	private String char07;
	private String char08;
	private String char09;
	private String char10;
	private String char11;
	private String char12;
	private String char13;
	private String char14;
	private String char15;
	private String char16;
	private String char17;
	private String char18;
	private String char19;
	private String char20;
	private String char21;
	private String char22;
	private String char23;
	private String char24;
	private String char25;
	private String char26;
	private String char27;
	private String char28;
	private String char29;
	private String char30;
	private Date date01;
	private Date date02;
	private Date date03;
	private Date date04;
	private Date date05;
	private Date date06;
	private Date date07;
	private Date date08;
	private Date date09;
	private Date date10;
	private double float01;
	private double float02;
	private double float03;
	private double float04;
	private double float05;
	private double float06;
	private double float07;
	private double float08;
	private double float09;
	private double float10;

	// Constructors

	/** default constructor */
	public AbstractSysUsersDetail() {
	}

	/** minimal constructor */
	public AbstractSysUsersDetail(String id, SysUsers sysUsers) {
		this.id = id;
		this.sysUsers = sysUsers;
	}

	/** full constructor */
	public AbstractSysUsersDetail(String id, SysUsers sysUsers, String char01,
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
		this.id = id;
		this.sysUsers = sysUsers;
		this.char01 = char01;
		this.char02 = char02;
		this.char03 = char03;
		this.char04 = char04;
		this.char05 = char05;
		this.char06 = char06;
		this.char07 = char07;
		this.char08 = char08;
		this.char09 = char09;
		this.char10 = char10;
		this.char11 = char11;
		this.char12 = char12;
		this.char13 = char13;
		this.char14 = char14;
		this.char15 = char15;
		this.char16 = char16;
		this.char17 = char17;
		this.char18 = char18;
		this.char19 = char19;
		this.char20 = char20;
		this.char21 = char21;
		this.char22 = char22;
		this.char23 = char23;
		this.char24 = char24;
		this.char25 = char25;
		this.char26 = char26;
		this.char27 = char27;
		this.char28 = char28;
		this.char29 = char29;
		this.char30 = char30;
		this.date01 = date01;
		this.date02 = date02;
		this.date03 = date03;
		this.date04 = date04;
		this.date05 = date05;
		this.date06 = date06;
		this.date07 = date07;
		this.date08 = date08;
		this.date09 = date09;
		this.date10 = date10;
		this.float01 = float01;
		this.float02 = float02;
		this.float03 = float03;
		this.float04 = float04;
		this.float05 = float05;
		this.float06 = float06;
		this.float07 = float07;
		this.float08 = float08;
		this.float09 = float09;
		this.float10 = float10;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public SysUsers getSysUsers() {
		return this.sysUsers;
	}

	public void setSysUsers(SysUsers sysUsers) {
		this.sysUsers = sysUsers;
	}

	public String getChar01() {
		return this.char01;
	}

	public void setChar01(String char01) {
		this.char01 = char01;
	}

	public String getChar02() {
		return this.char02;
	}

	public void setChar02(String char02) {
		this.char02 = char02;
	}

	public String getChar03() {
		return this.char03;
	}

	public void setChar03(String char03) {
		this.char03 = char03;
	}

	public String getChar04() {
		return this.char04;
	}

	public void setChar04(String char04) {
		this.char04 = char04;
	}

	public String getChar05() {
		return this.char05;
	}

	public void setChar05(String char05) {
		this.char05 = char05;
	}

	public String getChar06() {
		return this.char06;
	}

	public void setChar06(String char06) {
		this.char06 = char06;
	}

	public String getChar07() {
		return this.char07;
	}

	public void setChar07(String char07) {
		this.char07 = char07;
	}

	public String getChar08() {
		return this.char08;
	}

	public void setChar08(String char08) {
		this.char08 = char08;
	}

	public String getChar09() {
		return this.char09;
	}

	public void setChar09(String char09) {
		this.char09 = char09;
	}

	public String getChar10() {
		return this.char10;
	}

	public void setChar10(String char10) {
		this.char10 = char10;
	}

	public String getChar11() {
		return this.char11;
	}

	public void setChar11(String char11) {
		this.char11 = char11;
	}

	public String getChar12() {
		return this.char12;
	}

	public void setChar12(String char12) {
		this.char12 = char12;
	}

	public String getChar13() {
		return this.char13;
	}

	public void setChar13(String char13) {
		this.char13 = char13;
	}

	public String getChar14() {
		return this.char14;
	}

	public void setChar14(String char14) {
		this.char14 = char14;
	}

	public String getChar15() {
		return this.char15;
	}

	public void setChar15(String char15) {
		this.char15 = char15;
	}

	public String getChar16() {
		return this.char16;
	}

	public void setChar16(String char16) {
		this.char16 = char16;
	}

	public String getChar17() {
		return this.char17;
	}

	public void setChar17(String char17) {
		this.char17 = char17;
	}

	public String getChar18() {
		return this.char18;
	}

	public void setChar18(String char18) {
		this.char18 = char18;
	}

	public String getChar19() {
		return this.char19;
	}

	public void setChar19(String char19) {
		this.char19 = char19;
	}

	public String getChar20() {
		return this.char20;
	}

	public void setChar20(String char20) {
		this.char20 = char20;
	}

	public String getChar21() {
		return this.char21;
	}

	public void setChar21(String char21) {
		this.char21 = char21;
	}

	public String getChar22() {
		return this.char22;
	}

	public void setChar22(String char22) {
		this.char22 = char22;
	}

	public String getChar23() {
		return this.char23;
	}

	public void setChar23(String char23) {
		this.char23 = char23;
	}

	public String getChar24() {
		return this.char24;
	}

	public void setChar24(String char24) {
		this.char24 = char24;
	}

	public String getChar25() {
		return this.char25;
	}

	public void setChar25(String char25) {
		this.char25 = char25;
	}

	public String getChar26() {
		return this.char26;
	}

	public void setChar26(String char26) {
		this.char26 = char26;
	}

	public String getChar27() {
		return this.char27;
	}

	public void setChar27(String char27) {
		this.char27 = char27;
	}

	public String getChar28() {
		return this.char28;
	}

	public void setChar28(String char28) {
		this.char28 = char28;
	}

	public String getChar29() {
		return this.char29;
	}

	public void setChar29(String char29) {
		this.char29 = char29;
	}

	public String getChar30() {
		return this.char30;
	}

	public void setChar30(String char30) {
		this.char30 = char30;
	}

	public Date getDate01() {
		return this.date01;
	}

	public void setDate01(Date date01) {
		this.date01 = date01;
	}

	public Date getDate02() {
		return this.date02;
	}

	public void setDate02(Date date02) {
		this.date02 = date02;
	}

	public Date getDate03() {
		return this.date03;
	}

	public void setDate03(Date date03) {
		this.date03 = date03;
	}

	public Date getDate04() {
		return this.date04;
	}

	public void setDate04(Date date04) {
		this.date04 = date04;
	}

	public Date getDate05() {
		return this.date05;
	}

	public void setDate05(Date date05) {
		this.date05 = date05;
	}

	public Date getDate06() {
		return this.date06;
	}

	public void setDate06(Date date06) {
		this.date06 = date06;
	}

	public Date getDate07() {
		return this.date07;
	}

	public void setDate07(Date date07) {
		this.date07 = date07;
	}

	public Date getDate08() {
		return this.date08;
	}

	public void setDate08(Date date08) {
		this.date08 = date08;
	}

	public Date getDate09() {
		return this.date09;
	}

	public void setDate09(Date date09) {
		this.date09 = date09;
	}

	public Date getDate10() {
		return this.date10;
	}

	public void setDate10(Date date10) {
		this.date10 = date10;
	}

	public double getFloat01() {
		return this.float01;
	}

	public void setFloat01(double float01) {
		this.float01 = float01;
	}

	public double getFloat02() {
		return this.float02;
	}

	public void setFloat02(double float02) {
		this.float02 = float02;
	}

	public double getFloat03() {
		return this.float03;
	}

	public void setFloat03(double float03) {
		this.float03 = float03;
	}

	public double getFloat04() {
		return this.float04;
	}

	public void setFloat04(double float04) {
		this.float04 = float04;
	}

	public double getFloat05() {
		return this.float05;
	}

	public void setFloat05(double float05) {
		this.float05 = float05;
	}

	public double getFloat06() {
		return this.float06;
	}

	public void setFloat06(double float06) {
		this.float06 = float06;
	}

	public double getFloat07() {
		return this.float07;
	}

	public void setFloat07(double float07) {
		this.float07 = float07;
	}

	public double getFloat08() {
		return this.float08;
	}

	public void setFloat08(double float08) {
		this.float08 = float08;
	}

	public double getFloat09() {
		return this.float09;
	}

	public void setFloat09(double float09) {
		this.float09 = float09;
	}

	public double getFloat10() {
		return this.float10;
	}

	public void setFloat10(double float10) {
		this.float10 = float10;
	}

}
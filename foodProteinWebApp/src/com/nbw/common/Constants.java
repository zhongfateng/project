package com.nbw.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Constants {
	//附件上传到Ftp服务器上文件夹名
	public static final String FtpRootGB="/GB/";
	public static final String FtpRootIS="/IS/";
	public static final String FtpRootWTO="/WTO/";
	public static final String FtpRootFULLTEXT="/FULLTEXT/";
	public static final String FtpRootFULLDOC="/DOC/";
	public final static String DATA_FORMAT_LONG="yyyy-MM-dd HH:mm:ss";
	//通用portletlable
    public static String common_portlet_lable="P580012590132271908648";
	//组织机构附属信息字段配置列表
	public static List orgFieldConfig;
	//在线充值支付宝处理代码
	public static String pay_parame_cz="1";
	//在线购买支付宝处理代码
	public static String pay_parame_zf="2";
	//批量购买付宝处理代码
	public static String pay_parame_plgm="3";
	//用户附属信息字段配置列表
	public static List userFieldConfig;
	//用户添加时默认的密码
	public static String defaultPassword = "123456";
	//普通会员ID
	public static String pthyID = "4028821328ce73e30128ce7894750001";
	//外部ID
	public static String WBID = "C4564085C0B00002D12A1C706DF08FF0";
	// 加密解密KEY
	public static final String[] DES_KEYS = new String[3];
	// 字符串分隔符
	public static final String STRING_SEPARATOR = String.valueOf((char) 9999);

	// 系统管理参数
	public static final String SESSION_USER_ID = "loginUserID";
	public static final String SESSION_USER_INFO = "loginUserInfo";
	public static final String SESSION_USER_DETAIL = "loginUserdetail";
	//当前用户的所有有权限的模块列表
	public static final String SESSION_USER_MODULES = "userModules";
	//当前用户有权限的所有模块功能MAP 
	public static final String SESSION_USER_MACTION = "userModuleActions";
	public static final String SESSION_USER_SKIN_PATH = "selfSkinPath";
	public static final String ERROR_DELETE = "删除数据失败！";
	public static final String ERROR_USERNAME_EXIST = "该登录帐号已经存在!";
	public static final String ERROR_CODE_EXIST = "该基本编码信息已经存在!";
	public static final String ERROR_LOGIN = "loginerror";
	public static final String CODE_CONSTANT_ROLETYPE = "r000000000000001";
	//系统登录的链接
	public static final String SYS_LOGIN_URL = "/login.action";

	//从系统通用的选择组件选择结果的分隔符“｜”;
	public static final String SELECT_REGEX = "\\|";

	// Spring FrameworkServlet CONTEXT
	public static final String SERVLET_CONTEXT_PREFIX = "org.springframework.web.servlet.FrameworkServlet.CONTEXT";

	// 默认的树节点
	public static final String DEFAULT_ROOTWZ = "1";
	
	public static final String DEFAULT_ROOT = "ROOT";
	//默认的物种
	public static final String DEFAULT_SPECIES = "234";

	// 评估状态--完成
	public static final String ENERGYAUDITINGSTATE_FINISH = "FINISH";

	// AJAX成功\失败标记
	public static final String DEFAULT_AJAX_SUCCESS = "success";
	public static final String DEFAULT_AJAX_FAILURE = "failure";
  
	// DHTMLXGrid表格控件保存数据时XML串
	public static final String DHTMLXGRID_SAVEXML_PREFIX = "<?xml version='1.0' encoding='UTF-8'?><data>";
	public static final String DHTMLXGRID_SAVEXML_SUFFIX = "</data>";
	public static final String SESSION_USER_DEFAULT_ID = "0000000100128579c7cf";
	public static final String JB_USER_DEFAULT_ID = "0000000100128fd22aca6";
	public static final String NO_USER_DEFAULT_ID = "8a8ad8eb348dbf1001348e8d07c60069";
	public static final String TABLENAME = "BZPT_DATA_C01_51,BZPT_DATA_C02_51,BZPT_DATA_C03_51";
	public static final String BZNUMTABLE = "BZPT_DATA_A01_11,BZPT_DATA_A02_11,BZPT_DATA_A03_11,BZPT_DATA_A04_25,BZPT_DATA_A05_25,BZPT_DATA_A06_25,BZPT_DATA_B01_11,BZPT_DATA_C01_51,BZPT_DATA_C02_51,BZPT_DATA_C03_51,BZPT_DATA_C04_52,BZPT_DATA_C09_56,BZPT_DATA_C10_56,BZPT_DATA_E01_21,BZPT_DATA_E02_21,BZPT_DATA_E03_21,BZPT_DATA_E04_21,BZPT_DATA_E05_21,BZPT_DATA_D01_21,BZPT_DATA_D02_21,BZPT_DATA_D03_21,BZPT_DATA_D04_21,BZPT_DATA_D05_21,BZPT_DATA_D06_21";
	public static final String TABLE630 = "BZPT_DATA_A04_25,BZPT_DATA_C03_51";
	
	static {
		DES_KEYS[0] = "Yh8ua24skSj";
		DES_KEYS[1] = "q24xcR";
		DES_KEYS[2] = "UyxSAN435Ihs";
	}
	//专家服务操作
	public static final String CAOZUO="专家介入";
	//订单钱数
	public static final Double orderMoney=0.1;
	
	//普通会员级别id
	public static final String PUHY_JBID="4028821328ce73e30128ce7894750001";
	
	//国家专家信息一般的游客不能查看的字段；
	public static final HashMap<String, String> zjxx=new HashMap<String, String>(){  
	    {  
	    put("A09_83_102", "A09_83_102");  //工作单位
	    put("A09_83_130", "A09_83_130");  //通信地址
	    put("A09_83_303", "A09_83_303");  //邮政编码
	    put("A09_83_150", "A09_83_150");  //联系电话
	    put("A09_83_160", "A09_83_160");  //传真
	    put("A09_83_180", "A09_83_180");  //E_mail

	    }              
    };  
    
    
    /*
     * 存放国家专家信息列，此对象在BzptMetadataController.frontFindDetail被实例化并放值
     * 只实例化一次
     */
    public static HashMap<String, Map> A09_83 = null;
    /*
     * 存放国际专家信息列，此对象在BzptMetadataController.frontFindDetail被实例化并放值
     * 只实例化一次
     */
    public static HashMap<String, Map> A10_83 = null;
    
    //服务状态
    public static final String SQZT="6,7,8,9";
    //服务审核
    public static final String WFSH="3,2,0";
    //随即数
    public static final String SJS="1333679501359";
}  

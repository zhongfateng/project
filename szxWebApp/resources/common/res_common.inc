<%@page import="com.nbw.common.Constants"%>
<%@taglib uri="/WEB-INF/tld/sys-check" prefix="checktag"%>
<%
	String skins = (String)request.getSession().getAttribute(Constants.SESSION_USER_SKIN_PATH);
	String gridSkin = "";
	if(skins == null)skins="default";
	if(skins.equals("default")) gridSkin = "dhx_skyblue";
%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/skins/<%=skins %>/css/usmp-style.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/component/button/style/button.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/component/button/style/icon.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/component/ymPrompt/skin/qq/ymPrompt.css"/>
<script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/resources/component/uuid.js"></script>
<script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/resources/component/jquery.js"></script>
<script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/resources/component/jquery.form.js"></script>
<script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/resources/component/button/button.js"></script>
<script language="JavaScript" type="text/javascript" src="<%=request.getContextPath()%>/resources/js/util.js"></script>
<script language="JavaScript" type="text/javascript" src="<%=request.getContextPath()%>/resources/js/validate.js"></script>
<script language="JavaScript" type="text/javascript" src="<%=request.getContextPath()%>/resources/component/My97DatePicker/WdatePicker.js"></script>
<script language="JavaScript" type="text/javascript" src="<%=request.getContextPath()%>/resources/component/ymPrompt/ymPrompt.js"></script>
<script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/resources/js/sys/select.js"></script>


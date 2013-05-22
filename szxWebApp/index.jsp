<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="java.util.Date,com.nbw.common.util.LoginUserInfo"%>
<script language="javascript">
    window.location='login.action?m=toQTLogin';
</script>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";

	//模拟登陆，把用户登录信息放到session中
	LoginUserInfo loginUser = new LoginUserInfo();
	loginUser.setDeptID("01");
	loginUser.setDeptName("信息部");
	loginUser.setIp(request.getRemoteAddr());
	loginUser.setLoginID(request.getParameter("loginID"));
	loginUser.setLoginTime(new Date());
	loginUser.setPassWord(request.getParameter("passWord"));
	loginUser.setRoles("01,02,03");
	loginUser.setUserID("0000000200128a8f1155a");
	loginUser.setJbId("4028821328ce73e30128ce7894750001");
	loginUser.setUserName("演示用户");
	loginUser.setUserInfoCont("1");
	request.getSession().setAttribute("loginUser",loginUser);

	//把皮肤的起始路径和对应的css文件放入session
	//	String skin = SysParam.getParam("skin");//获取皮肤风格
	//	String skinPath = Style.getSkinPath(skin, appInfo.getBasePath());//根据皮肤风格获取皮肤路径
	//	request.getSession().setAttribute("skinPath", skinPath);
	//	String cssFile = Style.getCssFile(skin, appInfo.getBasePath());//根据皮肤风格获取css文件
	//	request.getSession().setAttribute("cssFile", cssFile);
%>
<html>
	<link href="${cssFile}" type="text/css" rel="stylesheet">
	<head>
		<base href="<%=basePath%>">
		<title>组件功能演示</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
	</head>

	<body>
		<p>
			当前登录用户：${loginUser.userName}
		</p>
		<br>
		<table class="table_nbw">
			<tr class="tr_nbw">
				<th width="179" class="th_nbw">
					功能
				</th>
				<th class="th_nbw" width=588>
					完成情况
				</th>
			</tr>
			<tr class="tr_nbw">
				<td class="td_nbw">
					<a href="forward.action?url=/WEB-INF/jsp/demo/login/loginUser.jsp"
						target="_blank">获取登录用户信息</a>
				</td>
				<td class="td_nbw" width=588>
					完成
				</td>
			</tr>
			<tr class="tr_even_nbw">
				<td class="td_nbw">
					<a
						href="forward.action?url=/WEB-INF/jsp/demo/datePicker/datePicker.jsp"
						target="_blank">日期选择</a>
				</td>
				<td class="td_nbw" width=588>
					完成
				</td>
			</tr>
			<tr class="tr_nbw">
				<td class="td_nbw">
					<a href="testtt.action?m=loadData" target="_blank">表格显示</a>
				</td>
				<td class="td_nbw" width=588>
					完成(直接观看extremeTable的展示效果)
				</td>
			</tr>
			<tr class="tr_even_nbw">
				<td class="td_nbw">
					<a href="fileUpload.action?m=showFileUpload" target="_blank">文件上传下载</a>
				</td>
				<td class="td_nbw" width=588>
					完成
				</td>
			</tr>
			<tr class="tr_nbw">
				<td class="td_nbw">
					<a href="noActionJsp/adduser.jsp" target="_blank">录入框下拉提示</a>
				</td>
				<td class="td_nbw" width=588>
					未完成
				</td>
			</tr>
			<tr class="tr_even_nbw">
				<td class="td_nbw">
					<a href="testtt.action?m=toAddPage" target="_blank">提示框</a>
				</td>
				<td class="td_nbw" width=588>
					完成 (id录入框 录入 123 点提交 即可看见后台校验，并显示提示框)
				</td>
			</tr>
			<tr class="tr_nbw">
				<td class="td_nbw">
					<a href="jfreechart.action?m=tomain" target="_blank">jfreechart图形显示</a>
				</td>
				<td class="td_nbw" width=588>
					完成
				</td>
			</tr>
			<tr class="tr_even_nbw">
				<td class="td_nbw">
					<a href="jfreechart.action?m=toFusionMain" target="_blank">FusionCharts图形显示</a>
				</td>
				<td class="td_nbw" width=588>
					完成
				</td>
			</tr>
			<tr class="tr_nbw">
				<td class="td_nbw"></td>
				<td class="td_nbw" width=588>
					11
				</td>
			</tr>
			<tr class="tr_even_nbw">
				<td class="td_nbw">
					<a href="tree.action?m=loadTree" target="_blank">树形结构生成</a>
				</td>
				<td class="td_nbw" width=588>
					完成
				</td>
			</tr>
			<tr class="tr_nbw">
				<td class="td_nbw">
					<a href="forward.action?url=/WEB-INF/jsp/demo/md5/md5.jsp"
						target="_blank">Md5加密</a>
				</td>
				<td class="td_nbw" width=588>
					完成
				</td>
			</tr>
			<tr class="tr_even_nbw">
				<td class="td_nbw">
					<a href="xml.action?m=toXMLPage" target="_blank">XML操作</a>
				</td>
				<td class="td_nbw" width=588>
					完成
				</td>
			</tr>
			<tr class="tr_nbw">
				<td class="td_nbw">
					<a href="#" target="_blank">Word操作</a>
				</td>
				<td class="td_nbw" width=588></td>
			</tr>
			<tr class="tr_even_nbw">
				<td class="td_nbw">
					<a href="#" target="_blank">Excel操作</a>
				</td>
				<td class="td_nbw" width=588></td>
			</tr>
			<tr class="tr_nbw">
				<td class="td_nbw">
					<a href="#" target="_blank">轮询</a>
				</td>
				<td class="td_nbw" width=588></td>
			</tr>
			<tr class="tr_even_nbw">
				<td class="td_nbw">
					<a href="#" target="_blank">Web Service的生成及调用</a>
				</td>
				<td class="td_nbw" width=588></td>
			</tr>
			<tr class="tr_nbw">
				<td class="td_nbw">
					<a href="forward.action?url=/WEB-INF/jsp/demo/css/cssExample.jsp"
						target="_blank">CSS效果演示</a>
				</td>
				<td class="td_nbw" width=588>
					完成
				</td>
			</tr>
			<tr class="tr_even_nbw">
				<td class="td_nbw">
					<a href="testtt.action?m=loadData" target="_blank">插件演示</a>
				</td>
				<td class="td_nbw" width=588>
					请参考项目说明下的 操作说明.doc
				</td>
			</tr>
		</table>
		<table class="table_nbw">
			<tr class="tr_nbw">
				<td class="td_nbw">
					<a href="./hyglIndex.jsp" target="_blank">会员管理</a>
				</td>
				<td class="td_nbw" width=349><a href="bzpthytx.action?m=toUserList" target="_blank">会员提醒</a></td>
				<td class="td_nbw" width=349><a href="login.action?m=toLogin" target="_blank">后台管理</a></td>
			</tr>
			<tr class="tr_even_nbw">
				<td class="td_nbw">
					<a href="./sfglIndex.jsp" target="_blank">收费管理</a>
				</td>
				<td width=700 colspan="2" class="td_nbw"><a href="bzptuser.action?m=toUser" target="_blank">用户中心</a></td>
			</tr>
			<tr class="tr_nbw">
				<td class="td_nbw">
					<a href="./LxglIndex.jsp" target="_blank">离线管理</a>
				</td>
				<td width=700 colspan="2" class="td_nbw"><a href="bzptuser.action?m=toIndex" target="_blank">用户注册</a></td>
			</tr>

		</table>
	</body>
</html>

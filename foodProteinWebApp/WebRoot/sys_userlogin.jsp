<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>用户注册</title>
<link href="css.css" rel="stylesheet" type="text/css" />
</head>

<body>
<table width="960" border="0" align="center" cellpadding="0" cellspacing="0" background="<%=path%>/resources/images/sys_login/logo-bg.jpg">
  <tr>
    <td width="36%" height="103">&nbsp;</td>
    <td width="64%"><table width="100%" height="59" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td colspan="3" align="right" class="hei12"><a href="#"  class="hei12">登陆</a> | <a href="#"  class="hei12">注册</a> |<a href="#"  class="hei12">我的CFMPD</a>&nbsp; </td>
      </tr>
      <tr>
        <td width="313" align="right"><select name="select" style="border:solid 1px #CCCCCC">
          <option>所有数据库</option>
        </select></td>
        <td width="235" align="center"><input name="textfield" type="text" value="请输入关键字" size="30"   style="border:solid 1px #CCCCCC"/></td>
        <td width="67" align="center"><label>
          <input type="image"  src="<%=path%>/resources/images/sys_login/sousuo.gif" name="Submit2" value="提交" />
        </label></td>
      </tr>
    </table></td>
  </tr>
</table>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="34" background="<%=path%>/resources/images/sys_login/top-bg.jpg">&nbsp;</td>
  </tr>
</table>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="200" height="71">&nbsp;</td>
    <td height="70"><table width="960" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="39" height="35"><img src="<%=path%>/resources/images/sys_login/leftmenu-bg01.jpg" width="39" height="35" /></td>
        <td background="<%=path%>/resources/images/sys_login/leftmenu-bg02.jpg" class="hei16B">用户登录</td>
        <td width="13"><img src="<%=path%>/resources/images/sys_login/leftmenu-bg03.jpg" width="13" height="35" /></td>
      </tr>
    </table>
      <table width="960" border="0" cellpadding="0" cellspacing="0" style="border:solid; border-color:#d0ced1; border-width:0px 1px 1px 1px; background-image:url(<%=path%>/resources/images/sys_login/lefttext-bg.jpg); background-position:bottom; background-repeat:repeat-x; margin-bottom:5px;">
        <tr>
          <td width="530" valign="top"><table width="80%" border="0" align="center" cellpadding="0" cellspacing="0">
            <tr>
              <td height="70" class="hei14"><label> &nbsp;&nbsp;&nbsp;
                    <input name="radiobutton" type="radio" value="radiobutton" checked="checked" />
                个人用户登录 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <input name="radiobutton" type="radio" value="radiobutton" />
                机构用户登录</label></td>
            </tr>
            <tr>
              <td height="50" class="hei14">&nbsp;&nbsp;&nbsp;用户名：
                <input class="TxtUserNameCssClass" id="TxtUserName22222" 
      maxlength="50" name="TxtUserName22222" />
                &nbsp;</td>
            </tr>
            <tr>
              <td height="50" class="hei14">&nbsp;&nbsp;&nbsp;密&nbsp;&nbsp;码：
                <input class="TxtUserNameCssClass" id="TxtUserName222222" 
      maxlength="50" name="TxtUserName222222" />
                &nbsp;<span class="lanlink"><a href="#">忘记密码</a></span></td>
            </tr>
            <tr>
              <td height="40" class="hei12">&nbsp;&nbsp;&nbsp;&nbsp;
                  <label>
                  <input type="checkbox" name="checkbox3" value="checkbox" />
                    记住我的用户名</label></td>
            </tr>
            <tr>
              <td height="40" class="hei12">&nbsp;&nbsp;&nbsp;&nbsp;
                  <label>
                  <input type="checkbox" name="checkbox32" value="checkbox" />
                    在本机上保持登陆状态，除非我退出</label>              </td>
            </tr>
            <tr>
              <td height="60"><label> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <input type="image" src="<%=path%>/resources/images/sys_login/login.jpg" name="Submit322" value="提交" />
              </label></td>
            </tr>
            <tr>
              <td height="40">&nbsp;</td>
            </tr>
          </table></td>
          <td width="428" valign="middle"><table width="100%" height="261" border="0" cellpadding="0" cellspacing="0">
            <tr>
              <td valign="top" style="border-left:solid 1px #CCCCCC; padding:0px 40px;"><p class="hei16B">还不是平台用户？</p>
                  <p><span class="hei14">现在免费注册成为平台用户，便能立刻享受更加便利和个性化服务。</span><br />
                      <br />
                      <br />
                  </p>
                <p align="center"><img src="<%=path%>/resources/images/sys_login/geren.jpg" /> &nbsp;&nbsp;&nbsp;&nbsp;<img src="<%=path%>/resources/images/sys_login/jigou.jpg" width="134" height="37" /></p></td>
            </tr>
          </table></td>
        </tr>
      </table>
      <table width="960" border="0" cellpadding="0" cellspacing="0" style="border:solid; border-color:#d0ced1; border-width:0px 1px 1px 1px; background-image:url(<%=path%>/resources/images/sys_login/lefttext-bg.jpg); background-position:bottom; background-repeat:repeat-x; margin-bottom:5px;">
        <tr>
          <td width="528" valign="top"><table width="80%" border="0" align="center" cellpadding="0" cellspacing="0">
              <tr>
                <td height="70" class="hei14"><label> &nbsp;&nbsp;&nbsp;
                      <input name="radiobutton" type="radio" value="radiobutton" />
                  个人用户登录 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                  <input name="radiobutton" type="radio" value="radiobutton" checked="checked" />
                  机构用户登录</label></td>
              </tr>
              <tr>
                <td height="50" class="hei16B">机构用户登录</td>
              </tr>
              <tr>
                <td height="50" class="hei14">您好，你的IP为 212.148.145.114 <span class="lanlink"><a href="#"></a></span></td>
              </tr>
              <tr>
                <td height="40" class="hei14">您所在的机构为 <span class="lanlink">***************</span> </td>
              </tr>
              <tr>
                <td height="40" class="hei12">&nbsp;&nbsp;&nbsp;&nbsp;
                    <label>
                    <input type="checkbox" name="checkbox322" value="checkbox" />
                      在本机上保持登陆状态，除非我退出</label>
                </td>
              </tr>
              <tr>
                <td height="60"><label> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                      <input type="image" src="<%=path%>/resources/images/sys_login/login.jpg" name="Submit3222" value="提交" />
                </label></td>
              </tr>
              <tr>
                <td height="40">&nbsp;</td>
              </tr>
          </table></td>
          <td width="430" valign="middle"><table width="100%" height="261" border="0" cellpadding="0" cellspacing="0">
              <tr>
                <td valign="top" style="border-left:solid 1px #CCCCCC; padding:0px 40px;"><p class="hei16B">还不是平台用户？</p>
                    <p><span class="hei14">现在免费注册成为平台用户，便能立刻享受更加便利和个性化服务。</span><br />
                        <br />
                        <br />
                    </p>
                  <p align="center"><img src="<%=path%>/resources/images/sys_login/geren.jpg" /> &nbsp;&nbsp;&nbsp;&nbsp;<img src="<%=path%>/resources/images/sys_login/jigou.jpg" width="134" height="37" /></p></td>
              </tr>
          </table></td>
        </tr>
      </table></td>
  </tr>
</table>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="120" align="center" bgcolor="f7f7f7" class="hei14">&nbsp;地址：北京北三环东路18号中国计量科学研究院  邮编：100013  电话：010-84290315  邮箱：admin@cfmpd.org<br />
    &nbsp;⑥ 版权所有 北京中防昊通科技中心 京ICP证 84290315号 京公网安备  102154441414    2011-2012 CFMPD</td>
  </tr>
</table>
</body>
</html>


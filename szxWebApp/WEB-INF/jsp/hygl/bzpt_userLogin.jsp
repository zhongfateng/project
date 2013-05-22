<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>用户管理-用户登录</title>
<link href="../../../resources/skins/default/style.css" rel="stylesheet" type="text/css" />

</head>

<body>
<table width="961" height="133" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="15">&nbsp;</td>
  </tr>
  <tr>
    <td height="118" background="../../../resources/skins/default/images/hygl/top.jpg">&nbsp;</td>
  </tr>
</table>
<table width="914" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td><img src="../../../resources/skins/default/images/hygl/main_top.jpg" width="914" height="11" /></td>
  </tr>
  <tr>
    <td background="../../../resources/skins/default/images/hygl/main_bg.jpg"><br />
        <br />
          <table width="860" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top:20px">
      <tr>
        <td width="397" valign="top" class="right_line">
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td height="100" valign="top"><img src="../../../resources/skins/default/images/hygl/login_05.gif" width="431" height="82" /></td>
            </tr>
          </table>
            <table width="251" height="108" border="0" align="center" cellpadding="0" cellspacing="0">
              <tr>
                <td height="30" class="menu_p"><img src="../../../resources/skins/default/images/hygl/ico6.jpg" width="15" height="14" />&nbsp;忘记密码怎么办？ </td>
              </tr>
              <tr>
                <td height="30" class="menu_p"><img src="../../../resources/skins/default/images/hygl/ico6.jpg" width="15" height="14" />&nbsp;如何查询的账户和收藏以及消费状态？</td>
              </tr>
              <tr>
                <td height="30" class="menu_p"><img src="../../../resources/skins/default/images/hygl/ico6.jpg" width="15" height="14" />&nbsp;如何安全使用充值卡？</td>
              </tr>
              <tr>
                <td height="30" class="menu_p"><img src="../../../resources/skins/default/images/hygl/ico6.jpg" width="15" height="14" />&nbsp;我该如何充值？</td>
              </tr>
              <tr>
                <td height="30" class="menu_p"><img src="../../../resources/skins/default/images/hygl/ico6.jpg" width="15" height="14" />&nbsp;保护您的密码，请阅读密码安全贴士。</td>
              </tr>
          </table></td>
        <td width="17">&nbsp;</td>
        <td width="386" align="center" valign="top" ><table width="327" border="0" align="center" cellpadding="0" cellspacing="0" background="../../../resources/skins/default/images/hygl/login_02.gif">
            <tr>
              <td width="327" height="7"><img src="../../../resources/skins/default/images/hygl/login_01.gif" /></td>
            </tr>
            <tr>
              <td height="30">&nbsp;<img src="../../../resources/skins/default/images/hygl/login_08.gif" width="170" height="60" /></td>
            </tr>
            <tr>
              <td class="menu_p"><table width="97%" border="0" cellspacing="5" cellpadding="0">
                  <tr>
                    <td width="30%" height="35" align="right" class="menu_p">用户名：</td>
                    <td width="70%" align="left"><label>
                      <input type="text" name="textfield" />
                    </label></td>
                  </tr>
                  <tr>
                    <td height="35" align="right" class="menu_p">密　码：</td>
                    <td align="left" class="div1"><input type="text" name="textfield2" /></td>
                  </tr>
                  <tr>
                    <td height="35" align="right" class="menu_p">验证码：</td>
                    <td align="left"><span class="div2">
                      <input name="textfield3" type="text" size="8" />
                      <img src="../../../resources/skins/default/images/hygl/image.jpg" align="absmiddle" /> <a href="#" class="txt_p">换一张</a></span></td>
                  </tr>
              </table></td>
            </tr>
            <tr>
              <td class="menu_p"><img src="../../../resources/skins/default/images/hygl/login_03.gif" width="327" height="7" /></td>
            </tr>
            <tr>
              <td height="40" class="menu_p"><table align="center" cellspacing="10">
                  <tr>
                    <td height="40" align="right"><a href="#" class="menu_p">忘记密码</a></td>
                    <td align="center"><span class="div3_2">
                      <input name="Input4" type="image" src="../../../resources/skins/default/images/hygl/login_06.gif" />
                    </span></td>
                  </tr>
              </table></td>
            </tr>
            <tr>
              <td height="11" class="menu_p"><img src="../../../resources/skins/default/images/hygl/login_04.gif" width="327" height="11" /></td>
            </tr>
        </table>
          <br /></td>
      </tr>
    </table>
            <br />
            <br />
            <br />
    <br /></td>
  </tr>
  <tr>
    <td><img src="../../../resources/skins/default/images/hygl/main_bottom.jpg" width="914" height="14" /></td>
  </tr>
</table>
<table width="914" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td class="bottom_line">&nbsp;</td>
  </tr>
  <tr>
    <td height="60" align="center">
   <span class="txt_p"><a href="#" class="txt_p">热门话题</a>  |  <a href="#" class="txt_p">联系我们</a>  |  <a href="#" class="txt_p">收藏本站</a>  |  <a href="#" class="txt_p">关于我们</a>  |  <a href="#" class="txt_p">联系我们</a>  |  <a href="#" class="txt_p">网站声明</a><br />
   网站管理：中国国家标准化管理委员会标准信息中心 共建单位：中国标准化研究院    中国标准出版社    中国标准化协会    地址：北京海淀区马甸东路9号 邮编：100088 </span>&nbsp;</td>
  </tr>
</table>
</body>
</html>
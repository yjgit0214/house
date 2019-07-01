﻿<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<!-- saved from url=(0049)http://localhost:8080/HouseRent/page/register.jsp -->
<HTML xmlns="http://www.w3.org/1999/xhtml"><HEAD><TITLE>青鸟租房 - 用户注册</TITLE>
<META content="text/html; charset=utf-8" http-equiv=Content-Type><LINK 
rel=stylesheet type=text/css href="../css/style.css">
<META name=GENERATOR content="MSHTML 8.00.7601.17514">
<script language="JavaScript" src="/admin/js/jquery-1.8.3.js">
</script>
  <script>
  $(function () { //加载事件
     //点击事件
     $("#but1").click(function () {
        //发请求到服务器验证用户名是否存在
             $.post("checkUname",{"username":$("#name").val()},function(data){
                 if(data.result==0)
                     $("#notice").html("用户名可用").css("color","green");
                 else
                     $("#notice").html("用户名已存在").css("color","red");
             },"json");
         });
  });
</script>
</HEAD>
<BODY>
<DIV id=header class=wrap>
<DIV id=logo><IMG src="../images/logo.gif"></DIV></DIV>
<DIV id=regLogin class=wrap>
<DIV class=dialog>
<DL class=clearfix>
  <DT>新用户注册</DT>
  <DD class=past>填写个人信息</DD></DL>
<DIV class=box>
<FORM action="regUser" method="post" name="dd">
<DIV class=infos>
<TABLE class=field>
  <TBODY>
  <TR>
    <TD class=field>用 户 名：</TD>
    <TD><INPUT class=text type=text id="name" name=name><span id="notice"></span><input type="button" value="检查用户名是否存在" id="but1"/> </TD></TR>
  <TR>
    <TD class=field>密　　码：</TD>
    <TD><INPUT class=text type=password name=password></TD></TR>
  <TR>
    <TD class=field>确认密码：</TD>
    <TD><INPUT class=text type=password name=repassword> </TD></TR>
  <TR>
    <TD class=field>电　　话：</TD>
    <TD><INPUT class=text type=text name=telephone> </TD></TR>
  <%--<TR>--%>
    <%--<TD class=field>用户姓名：</TD>--%>
    <%--<TD><INPUT class=text type=text name=username> </TD></TR>--%>
  </TBODY>
</TABLE>
<DIV class=buttons>
<INPUT type="submit" value=立即注册 >
</DIV></DIV></FORM></DIV></DIV></DIV>
<DIV id=footer class=wrap>
<DL>
  <DT>青鸟租房 © 2018 北大青鸟 京ICP证1000001号</DT>
  <DD>关于我们 · 联系方式 · 意见反馈 · 帮助中心</DD></DL></DIV></BODY></HTML>

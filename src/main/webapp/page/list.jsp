<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<!-- saved from url=(0030)http://localhost:8080/House-2/ -->
<HTML xmlns="http://www.w3.org/1999/xhtml"><HEAD><TITLE>青鸟租房 - 首页</TITLE>
<META content="text/html; charset=utf-8" http-equiv=Content-Type>
<LINK rel=stylesheet type=text/css href="../css/style.css">
<META name=GENERATOR content="MSHTML 8.00.7601.17514">
<script language="JavaScript" src="../admin/js/jquery-1.8.3.js"></script>
    <style type="text/css">
        footer{
            width: 100%;
            height:30px;   /* footer的高度一定要是固定值*/
            position:absolute;
            bottom:0px;
            left:450px;
            align-content: center;
        }

    </style>
<script language="JavaScript">
  $(function () {
     //异步加载类型
     $.post("getType",null,function (data) {
         for (var i=0;i<data.length;i++){
             var option=$("<option value="+data[i].id+">"+data[i].name+"</option>");
             $("#typeid").append(option);
         }
                    //设置选中项  ,回显
            if (${condition.tid!=null}){
                $("#typeid").val(tid);
            }
     },"json");

      //异步加载区域
      $.post("getDistrict",null,function (data) {
          for (var i=0;i<data.length;i++){
              var option=$("<option value="+data[i].id+">"+data[i].name+"</option>");
              $("#districtid").append(option);
          }
          //设置选中项  ,回显
          if (${condition.did!=null}){
              $("#districtid").val(did);
          }

      },"json");

      //给区域添加改变事件，显示对应的街道
      $("#districtid").change(function(){
          //获取区域的id,去后台查询对应的街道
          var did=$(this).val();
          //发送异步请求获取街道
          showStreet(did);
      });
  });
  //显示街道
  function showStreet(did){
      $.post("getStreetByDid2",{"did":did},function(data){
          $("#streetid>option").remove(); //清空选项
          for(var i=0;i<data.length;i++){
              //创建一个dom节点
              var option=$("<option value="+data[i].id+">"+data[i].name+"</option>");
              $("#streetid").append(option);  //追加节点
          }
          //设置选中项  ,回显
          if (${condition.sid!=null}){
              $("#streetid").val(sid);
          }
      },"json");
  }
//分页
function goPage(num) {
    $("#showPage").val(num);
    $("#form").submit();
}
</script>
</HEAD>

<BODY>
<DIV id=header class=wrap>
<DIV id=logo><IMG src="../images/logo.gif"></DIV></DIV>
<DIV id=navbar class=wrap align="center">
  <form id=form method=post action=goList>
      <input type="hidden" id="showPage" value="1" name="page">
<br/>
   标题：<INPUT class=text type=text name=title value="${condition.title}">

   开始价格：<input type="text" name="startPrice" value="${condition.startPrice}" size="6">
   结束价格：<input type="text" name="endPrice" value="${condition.endPrice}" size="6">

   区域：<select id="districtid" name="did">
    <option selected  value="">请选择</option>
  </select>

   街道：
    <select id="streetid" name="sid">
        <option selected value="">请选择</option>
    </select>
  <br/><br/>

   类型：
    <SELECT id="typeid" name=tid>
      <OPTION selected value="" >请选择</OPTION></SELECT>

   面积：
    <SELECT id="flooa" name=flooa id="flooa">
      <OPTION selected value="">不限</OPTION>
      <OPTION value=0-40>40以下</OPTION>
      <OPTION value="41-80">41-80</OPTION>
      <OPTION  value="81-500">81-500</OPTION></SELECT>
    <script language="JavaScript">
        $("#flooa").val(${condition.flooa})
    </script>

    <label class="ui-blue"><input value="搜索房屋" type="submit" name="search"></label>

  </form></DIV>

<DIV class="main wrap">
<TABLE class=house-list>
  <TBODY>
  <c:forEach items="${pageInfo.list}" var="h">
  <TR>
    <TD class=house-thumb><span><A href="details.jsp" target="_blank">
        <img src="http://localhost:81/${h.path}" width="100" height="75" alt=""></a></span></TD>
    <TD>
      <DL>
        <DT><A href="details.jsp" target="_blank">${h.title}</A></DT>
        <DD>${h.dname}区${h.sname}街,${h.floorage}平米<BR>联系方式：${h.contact} </DD></DL></TD>
    <TD class=house-type>${h.tname}</TD>
    <TD class=house-price><SPAN>${h.price}</SPAN>元/月</TD>
  </TR>
  </c:forEach>
  <TR>无租房信息</TR></TBODY></TABLE>
<DIV class=pager>
<UL>
  <LI class=current><A href="javascript:goPage(1);">首页</A></LI>
  <LI><A href="javascript:goPage(${pageInfo.prePage==0?1:pageInfo.prePage});">上一页</A></LI>
  <LI><A href="javascript:goPage(${pageInfo.nextPage==0?pageInfo.pages:pageInfo.nextPage});">下一页</A></LI>
  <LI><A href="javascript:goPage(${pageInfo.pages});">末页</A></LI></UL><SPAN
class=total>${pageInfo.pageNum}/${pageInfo.pages}页</SPAN> </DIV></DIV>


<%--<DIV  align="center">--%>
  <%--<p>关于我们 · 联系方式 · 意见反馈 · 帮助中心</p>--%>
  <%--<p>青鸟租房 © 2018 北大青鸟 京ICP证1000001号</p>--%>
  <%--</DIV>--%>

    <footer>
        <p>青鸟租房 © 2018 北大青鸟 京ICP证1000001号   关于我们 · 联系方式 · 意见反馈 · 帮助中心 </p>

    </footer>

</BODY></HTML>

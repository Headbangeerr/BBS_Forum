<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'loginjsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/login.css">
	

  </head>
  
  <body>
   
  <jsp:include page="/pages/header.jsp"/>
  <div class="login">

    <form id="form1" role="form" action="<%=path%>/login.action" method="post">
            <!--<label for="name">用户名:</label>-->
            <input id="username" required type="text" class="form-control" name="mailAddress" style="height: 55px; margin-top: 30px;"
                   placeholder="请输入用户名">
                   <s:if test='errorFlag=="0"'>
                    <span style="color:red">用户名不存在！</span>
                   </s:if>
                  
            <input type="password" required class="form-control" name="password" style="height: 55px;margin-top: 30px;"
                   placeholder="请输入密码">
                    <s:if test='errorFlag=="2"'>
                    <span style="color:red">密码输入错误！</span>
                   </s:if>
        <div style="height: 100px;width: 100%;margin-top: 30px;">
            <div style="float:left;width: 50%;padding: 20px;">
                <input type="submit" class="btn btn-primary" value="登陆"
                   style="margin:auto;width: 80%;height: 50px;padding: 13px;"></input>
            </div>
            <div style="float:right;width: 50%;padding: 20px;">
                <a href="<%=path%>/regist.jsp" type="button" class="btn btn-primary"
                   style="margin:auto;width: 80%;height: 50px;padding: 13px;">注册</a>
            </div>

        </div>

    </form>
</div>
	<div   style="position:absolute; bottom:0;margin-top: 20px;width:100%;   background-color: rgba(0,0,0,0.8);height: 75px;color: darkgray">
	    <div style="width: 400px;padding-top: 18px;padding-left:40px;padding-right: 40px;margin:auto;">
	        <div>
	            	情链接：
	            <a href="https://github.com/Headbangeerr/BBS_Forum" style="color: darkgray">&nbsp;github&nbsp;|&nbsp;</a>
	            <a href="http://www.csdn.net/" style="color: darkgray">csdn&nbsp;|&nbsp;</a>
	            <a href="http://www.oschina.net/" style="color: darkgray">开源中国&nbsp;|&nbsp;</a>
	            <a href="http://stackoverflow.com/" style="color: darkgray">stackflow</a><br>
	            	小组成员：颜磊,许涛,徐奇奇,杨昌敏,王运发
        	</div>
    	</div>
      </div>
</body>
  <script type="text/javascript" src="js/jquery.validate.min.js"></script>
  <script type="text/javascript">
  	$(function(){
  	 	$("#signupForm").validate();
  	});
  </script>
</html>

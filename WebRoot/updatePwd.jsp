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
    
    <title>设置安全码</title>
    
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
  <div class="safety">

    <form id="form1" role="form" action="<%=path%>/updatePwd.action" method="post">
        <input id="password" required type="password" class="form-control" name="password" style="height: 55px; margin-top: 30px;"
                   placeholder="请输入新的密码">
                   
        <div style="height: 100px;width: 100%;margin-top: 30px;">
				<div style="float:left;width: 50%;padding: 20px;">
					<input type="submit" class="btn btn-primary" value="确认"
						style="margin:auto;width: 80%;height: 50px;padding: 13px;"></input>
				</div>
				<div style="float:right;width: 50%;padding: 20px;">
					<input type="reset" class="btn btn-primary" value="重置"
						style="margin:auto;width: 80%;height: 50px;padding: 13px;"></input>
				</div>
		</div>
    </form>
</div>

</body>
  <script type="text/javascript" src="js/jquery.validate.min.js"></script>
  <script type="text/javascript">
  	$(function(){
  	 	$("#signupForm").validate();
  	});
  </script>
</html>

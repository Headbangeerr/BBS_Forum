  <%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
   <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <base href="<%=basePath%>">
    <title>欢迎进入！</title>
   
  </head>
  
  <body>
	  <jsp:include page="/pages/header.jsp"></jsp:include>
	  <jsp:include page="/pages/search.jsp"></jsp:include>
	<div class="container" style="margin-top: 30px;">
    <div class="row">
        <div class="col-md-9">
            <ul class="list-group">
                <div class="list-group-item active">
                    论坛新帖
                    <a href="<%=request.getContextPath() %>/more.action?type=-1&&page=1" style="float: right;color: white">更多>></a>
                    <!--<p style="float: right"></p>-->
                </div>
                <a href="#" class="list-group-item">
                    <h4 class="list-group-item-heading">[1234]</h4>
                    123<span class="badge">新</span>
                    <p class="text-right" style="float: right;margin-right: 20px">浏览量:2&nbsp;评论量:5&nbsp;发表日期:2017-03-25</p>
                </a>
            </ul>

        </div>
       </div>
       </div>
  </body>
</html>

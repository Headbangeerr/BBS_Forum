<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%	request.setCharacterEncoding("utf-8"); 
//解决中文乱问的问题%>
<html>
<head>
<title>Insert title here</title>
</head>
<center>
<body>
<h2>新增版块成功！<br/>

<%
response.setHeader("refresh", "3;URL=manage.jsp");//这里的3,是你要确定的时间秒URL是要跳转的地址
%>
三秒后将自动跳转到首页.... <br/> 
如果没有跳转,请点击<a href="manage.jsp">这里</a>
</body>
</center>
</html>
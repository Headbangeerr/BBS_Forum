<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">    
    <title><s:property value="#request.checkedUser.username"/>的信息</title>
	<style type="text/css">
	.alert {
	    display: none;
	    position: fixed;
	    top: 50%;
	    left: 50%;
	    min-width: 200px;
	    margin-left: -100px;
	    z-index: 99999;
	    padding: 15px;
	    border: 1px solid transparent;
	    border-radius: 4px;
	}
	.alert-success {
	    color: #3c763d;
	    background-color: #dff0d8;
	    border-color: #d6e9c6;
	}

	.btn-default1:hover {
	   	color: #fff;
		background-color: #449d44;
		border-color: #398439;
	}
	.btn-default1 {
	    color: #fff;
	    background-color: #5cb85c;
	    border-color: #4cae4c;
	}

	.btn-success1:hover {
	   	color: #fff;
		background-color: #CD2626;
		border-color: #8B1A1A;
	}
	.btn-success1 {
	    color: #fff;
	    background-color: #FF4040;
	    border-color: #EE3B3B;
	}
	.alert1-false {
		width:220px;
	    color: #FFFFFF;
	    background-color: #FF3030;
	    border-color: #FFFFFF;
	}
	.alert1-success {
		width:150px;
	    color: #3c763d;
	    background-color: #dff0d8;
	    border-color: #d6e9c6;
	}
	.alert1 {
		text-align:center;
		margin-left:120px;
	    padding: 15px;
	    margin-bottom: 20px;
	    border: 1px solid transparent;
	    border-radius: 4px;
	}

		
	</style>
	<link rel="stylesheet" type="text/css" href="css/user.css">
	<link rel="stylesheet" type="text/css" href="css/font-awesome-4.4.0/css/font-awesome.min.css">
  </head>
   <script type="text/javascript" src="<%=basePath%>js/users.js" charset="gb2312"></script>
    <script type="text/javascript" src="<%=basePath%>js/jquery.min.js"></script>
  <body>
    <jsp:include page="header.jsp"></jsp:include>
	<div class="container user">
	    <div class="position clearfix"><a href="<%=basePath%>/superman.jsp">首页</a> / 用户信息</div>
	    <div class="user-cont clearfix" style="width: 600px;height: 520px;margin-left: 300px">
	        <div class="col-md-4 user-left" style="width: 70%;margin-left: 90px;margin-top: 60px">
	            <div class="user-left-n clearfix">
	               <h6> <i class="fa fa-address-card"></i>详细信息</h6>
	                <a class="user-headimg f"><img src='<s:property value="#request.checkedUser1.photoUrl"/>'></a>
	                <div class="user-name f">
	                    <h4><s:property value="#request.checkedUser1.username"/></h4>
	                    <p><s:property value="#request.checkedUser1.signature"/></p>
	                </div>
	            </div>
	            <div class="user-left-n clearfix">
	                <ul class="list-group">
	                    <li class="list-group-item" style="text-align: center;">
	                        <i class="fa fa-user-secret"></i>&nbsp;Level：<s:property value="#request.checkedUser1.level"/>
	                    </li>
	                    <li class="list-group-item" style="text-align: center;" href="">
	                       
	                        <i class="fa fa-transgender"></i>&nbsp;性别:<s:property value="#request.checkedUser1.sex"/>
	                    </li>	               
	                    <li class="list-group-item" href="" style="text-align: center;">
	                        
	                        <i class="fa fa-clock-o"></i>&nbsp;注册日期：<s:date name="#request.checkedUser1.registerDate" format="yyyy-MM-dd " />
	                    </li>
	                </ul>
	                <form id="qxForm">
	                 <input type="hidden" name="userMail" value='<%=session.getAttribute("usermailqx")%>'>
	                <s:if test='le==0'>
	                	 <a onclick="upQX()" class="btn btn-default1 infos" >&nbsp;提升权限</a>
	                </s:if>
	                <s:else>
	                	<a onclick="downQX()"  class="btn btn-success1 infos">&nbsp;降低权限</a>
	                </s:else>   
	                </form>     
	                 <div class="alert1"></div>	     
	            </div>          
	        </div>
	    </div>
    </div>
  </body>
</html>

  <%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
      <%@taglib uri="/struts-tags" prefix="s" %>
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
   <link type="text/css" rel="stylesheet" href="css/main.min.css">
    <base href="<%=basePath%>">
    <title>欢迎进入！</title>
   
  </head>
  
  <body>
	  <jsp:include page="/pages/header.jsp"></jsp:include>
	  <jsp:include page="/pages/search.jsp"></jsp:include>
	<div class="container" style="margin-top: 30px;">
    <div class="row">
    	<div class="col-md-3" style="width: 500px;margin-bottom: 320px;margin-left: 310px">    
          <s:if test='#session.user!=null'>
	        <ul class="list-group">
	                <div class="list-group-item active">
	                    	超级管理员
	                </div>
	        </ul>
			<div class="panel-body">
				<div class="row">
					<div class="profile-sidebar">
						<div class="profile-sidebar-item profile-avatar">
							<a href="http://localhost:8080/BBS_Forum/chaeckUserByUrl?mailAddress=<s:property value="#session.user.mailAddress"/>">
								<img src="<s:property value='#session.user.photoUrl'/>" alt="<s:property value='#session.user.mailAddress'/>" class="avatar avatar-lg img-circle">
							</a>
						</div>
						<div class="profile-sidebar-item profile-info">
							<span class="h5 bold"><s:property value='#session.user.username'/></span>
							<p></p>
							<div class="w150 center-block mt10">
								<a class="btn btn-success btn-outline btn-block" href="getAllUser">
									<span>查看用户</span>
								</a>
							</div>
						</div>
					</div>
				</div>
			 </div>
          </s:if> 
        </div>
        
       </div>
       </div>
       <div   style=" bottom:0;margin-top: 20px;width:100%;   background-color: rgba(0,0,0,0.8);height: 75px;color: darkgray">
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
</html>

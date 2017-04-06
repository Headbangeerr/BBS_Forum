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
   <script type="text/javascript" src="js/index.js"></script>
   
    <base href="<%=basePath%>">
    <title>欢迎进入！</title>
   
  </head>
  
  <body onload="showPostList()">
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
                <div class="tab-pane active" id="home">
					<section id="LastestPostList" class="widget bg-white post-comments">
					</section>								
				</div>			
            </ul>
        </div>
         <div class="col-md-3">
            <ul class="list-group">
                <div class="list-group-item active">
                    	论坛公告
                </div>
                         <a href="#" class="list-group-item">23423423</a>
            </ul>
          <s:if test='#session.user!=null'>
	        <ul class="list-group">
	                <div class="list-group-item active">
	                    	个人信息
	                </div>
	        </ul>
			<div class="panel-body">
				<div class="row">
					<div class="profile-sidebar">
						<div class="profile-sidebar-item profile-avatar">
							<a href="#">
								<img src="<s:property value='#session.user.photoUrl'/>" alt="Headbanger" class="avatar avatar-lg img-circle">
							</a>
						</div>
						<div class="profile-sidebar-item profile-info">
							<span class="h5 bold"><s:property value='#session.user.username'/></span>
							<p></p>
							<div class="w150 center-block mt10">
								<a class="btn btn-success btn-outline btn-block" href="#">
									<span>发布新帖子</span>
								</a>
							</div>
						</div>
						<hr>
						<ul class="profile-sidebar-item profile-numbers-count">
							<li><a href="#"><span class="bold">0</span>帖子收藏</a></li>
							<li><a href="#"><span class="bold">0</span>节点收藏</a></li>
							<li><a href="#"><span class="bold">0</span>特别关注</a></li>
						</ul>
					</div>
				</div>
			</div>
			<div class="panel-footer box">
				<a href="#"><span id="notice_count">0</span> 条未读提醒</a>
			</div>
              </s:if> 
               
               
        </div>
        
       </div>
       </div>
  </body>
</html>

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
					<section name="LastestPostList" class="widget bg-white post-comments">
						<div class="media">
							<a class="pull-left" href="https://java-china.org/member/sjswc">
								<img class="media-object avatar avatar-sm" src="upload/headicon/default_icon.jpg" alt="sjswc">
							</a>
							<div class="comment">
								<div class="comment-author h6 no-margin">
									<a href="https://java-china.org/topic/38">Hello</a>
								</div>
								<div class="comment-bt">
									<span class="label label-default"><a href="https://java-china.org/go/javaweb">JavaWeb</a></span> &nbsp;•&nbsp;
									<strong><a href="https://java-china.org/member/sjswc">sjswc</a></strong>
									 &nbsp;•&nbsp; <span>3天前</span>
								</div>
							</div>
						</div>								
						<hr>
					</section>								
					</div>			
            </ul>
        </div>
       </div>
       </div>
  </body>
</html>

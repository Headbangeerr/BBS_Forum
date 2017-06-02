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
<style type="text/css">
.greenbutton {
	color: #fff; 
	background: #5cb85c;
}

/* Blue Color */
.redbutton {
	color: #fff;
	background: #d9534f;
}
.hoverbutton {
	display: inline-block;
	position: relative;
	margin-top: 3px;
	padding: 0 20px;
	text-align: center;
	text-decoration: none;
	font: bold 12px/25px Arial, sans-serif;
	text-shadow: 1px 1px 1px rgba(255,255,255, .22);
	-webkit-border-radius: 30px;
	-moz-border-radius: 30px;
	border-radius: 30px;
	-webkit-box-shadow: 1px 1px 1px rgba(0,0,0, .29), inset 1px 1px 1px rgba(255,255,255, .44);
	-moz-box-shadow: 1px 1px 1px rgba(0,0,0, .29), inset 1px 1px 1px rgba(255,255,255, .44);
	box-shadow: 1px 1px 1px rgba(0,0,0, .29), inset 1px 1px 1px rgba(255,255,255, .44);
	-webkit-transition: all 0.15s ease;
	-moz-transition: all 0.15s ease;
	-o-transition: all 0.15s ease;
	-ms-transition: all 0.15s ease;
	transition: all 0.15s ease;
} 
  
  
  
  </style>
    <base href="<%=basePath%>">    
    <title>我的信息</title>
	<link rel="stylesheet" type="text/css" href="css/user.css">
	<link rel="stylesheet" type="text/css" href="css/font-awesome-4.4.0/css/font-awesome.min.css">
	<link type="text/css" rel="stylesheet" href="css/main.min.css">
  </head>
   <script type="text/javascript" src="<%=basePath%>js/member.js" charset="gb2312"></script>
    <script type="text/javascript" src="<%=basePath%>js/jquery.min.js"></script>
  <body>
    <jsp:include page="header.jsp"></jsp:include>
	<div class="container user" >
	    <div class="position clearfix"><a href="<%=basePath%>/superman.jsp">首页</a> / 用户列表</div>
	    <div class="user-cont clearfix" style="width: 800px">
	        <div class="col-md-8 user-right" style="width: 100%; height:auto;" >
	            <div class="user-right-n clearfix">
	                <div class="user-right-n clearfix tab-content">     
	                    <div role="tabpanel" class="tab-pane1" id="Userss">
	                  	  <input type="hidden" name="userMail" value="<s:property value="#request.checkedUser.mailAddress"/>">
		                  <s:if test='#request.userBean1.size()==0'>
	                    	 <h4>未添加任何好友。</h4>     
	                   	  </s:if>
	                   	  <s:else>	 
		                   	  	<s:iterator value="#request.userBean1" var="user">
			                   	  	<s:if test="#request.user.type==0">
			                   	  		<div class="media">	                           
				                            <a class="pull-left" href="http://localhost:8080/BBS_Forum/checkQXUserByUrl?mailAddress=${user.mailAddress }">
				                            	<img class="media-object avatar avatar-sm" src="${user.photoUrl}" alt="${user.username }">
				                            </a>
				                            <div class="comment" style="width: 525px;float: left"> 
						                         <div class="comment-author h6 no-margin">
						                        	<a href="http://localhost:8080/BBS_Forum/checkQXUserByUrl?mailAddress=${user.mailAddress}">${user.username}</a>
						                        </div>
						                        <div class="comment-bt">
						                        	<span>用户</span>
						                       	</div>                    
				                      	  </div>
				                      	  <div name="hoverbutton" style="float:right;display: none">
				                      	  	<a href="http://localhost:8080/BBS_Forum/checkQXUserByUrl?mailAddress=${user.mailAddress}" class="hoverbutton greenbutton">管理权限</a>			                      	  	
				                      	  </div>		                          	                            
				                        </div>
				                        <hr>
				                    </s:if>
				                    <s:else>
				                    	<div class="media">	                           
				                            <a class="pull-left" href="http://localhost:8080/BBS_Forum/chaeckUserByUrl?mailAddress=${user.mailAddress }">
				                            	<img class="media-object avatar avatar-sm" src="${user.photoUrl}" alt="${user.username }">
				                            </a>
				                            <div class="comment" style="width: 525px;float: left"> 
						                         <div class="comment-author h6 no-margin">
						                        	<a href="http://localhost:8080/BBS_Forum/checkQXUserByUrl?mailAddress=${user.mailAddress}">${user.username}</a>
						                        </div>
						                        <div class="comment-bt">
						                        	<span>管理员</span>
						                       	</div>                    
				                      	  </div>
				                      	  <div name="hoverbutton" style="float:right;display: none">
				                      	  	<a href="http://localhost:8080/BBS_Forum/checkQXUserByUrl?mailAddress=${user.mailAddress}" class="hoverbutton greenbutton">管理权限</a>			                      	  	
				                      	  </div>		                          	                            
				                        </div>
				                        <hr>
									</s:else>	
						  		</s:iterator>	                       
	                   	  </s:else>	                           	                              
	                    </div>	                     	                  
	                </div>
	            </div>
	        </div>
	    </div>
    </div>
  </body>
</html>
<script type="text/javascript">
$(function(){
	$(".media").on("mouseenter",function(){		
		$(this).children("div[name=hoverbutton]").show();
	});
	$(".media").on("mouseleave",function(){		
		$(this).children("div[name=hoverbutton]").hide();
	});
})
</script>


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
	.hoverbutton redbutton{
	
	}
  
  
  
  </style>
    <base href="<%=basePath%>">    
    <title>我的信息</title>
	<link rel="stylesheet" type="text/css" href="css/user.css">
	<link rel="stylesheet" type="text/css" href="css/font-awesome-4.4.0/css/font-awesome.min.css">
	<link type="text/css" rel="stylesheet" href="css/main.min.css">
  </head>
   <script type="text/javascript" src="<%=basePath%>js/member.js" charset="gb2312"></script>
    <script type="text/javascript" src="<%=basePath%>js/posts.js" charset="utf-8"></script>
    <script type="text/javascript" src="<%=basePath%>js/jquery.min.js"></script>
  <body>
    <jsp:include page="pages/header.jsp"></jsp:include>
	<div class="container user">
	    <div class="position clearfix"><a href="<%=basePath%>/index.jsp">首页</a> / 我的信息</div>
	    <div class="user-cont clearfix">
	        <div class="col-md-4 user-left">
	            <div class="user-left-n clearfix">
	               <h6> <i class="fa fa-address-card"></i>详细信息</h6>
	                <a class="user-headimg f"><img src='<s:property value="#request.checkedUser.photoUrl"/>'></a>
	                <div class="user-name f">
	                    <h4><s:property value="#request.checkedUser.username"/></h4>
	                    <p><s:property value="#request.checkedUser.signature"/></p>
	                </div>
	            </div>
	            <div class="user-left-n clearfix">
	                <ul class="list-group">
	                    <li class="list-group-item" style="text-align: center;">
	                        <i class="fa fa-user-secret"></i>&nbsp;Level：<s:property value="#request.checkedUser.level"/>
	                    </li>
	                    <li class="list-group-item" style="text-align: center;" href="">
	                       
	                        <i class="fa fa-transgender"></i>&nbsp;性别:<s:property value="#request.checkedUser.sex"/>
	                    </li>	               
	                    <li class="list-group-item" href="" style="text-align: center;">
	                        
	                        <i class="fa fa-clock-o"></i>&nbsp;注册日期：<s:date name="#request.checkedUser.registerDate" format="yyyy-MM-dd " />
	                    </li>
	                </ul>
	               <a href="#" class="btn btn-info"><i class="fa fa-cogs"></i>&nbsp;修改个人资料</a>
	            </div>
	            
	        </div>
	        <div class="col-md-8 user-right">
	            <div class="user-right-n clearfix">
	                <ul id="right-tab" class="nav nav-tabs">
	                    <li role="presentation" class="active">
	                        <a href="#myArticle" id="home-tab1" data-toggle="tab"><i class="fa fa-book"></i>&nbsp;我的帖子</a>
	                    </li>
	                    <li role="presentation">
	                        <a href="#myCollection" id="home-tab2" data-toggle="tab"><i class="fa fa-commenting-o"></i>&nbsp;我的留言板</a>
	                    </li>	 
	                    <li role="presentation">
	                        <a href="#myFriends" id="home-tab3" data-toggle="tab"><i class="fa fa-group"></i>&nbsp;我的好友</a>
	                    </li>	    
	                </ul>
	
	                <div class="user-right-n clearfix tab-content">
	                <!-- 遍历展示Ta的帖子列表 -->
	                  	<div role="tabpanel" class="tab-pane active" id="myArticle">
	                  		<input type="hidden" name="publisherMail" value="<s:property value="#request.checkedUser.mailAddress"/>">
		                  <s:if test='#request.postBean.list.size()==0'>
	                    	  <h4>未发表任何帖子。</h4>         
	                    </s:if>
	                    <s:else>	                	
	                    	<s:iterator value="#request.postBean.list" var="post">
		                    	<div class="art-row">                     
		                            <h4><a href="serchPost?pid=${post.id}" class="title">${post.title} </a></h4>	                          
		                            <span class="label label-default"><a href="checkZiPostByUrl?cid=${post.childboardId.id }">${post.childboardId.name}</a></span>
		                             <a href="http://localhost:8080/BBS_Forum/chaeckUserByUrl?mailAddress=<s:property value="publisherMail.mailAddress"/>"  class="author">
		                             <i class="fa fa-user"></i>&nbsp;<span>${post.publisherMail.username}</span>
		                             </a>
		                             <a  class="time"><i class="fa fa-clock-o"></i>&nbsp;<span><s:date name="publishTime" format="yyyy-MM-dd HH:mm" /></span></a> 	 
		                           <div name="hoverbutton" style="float:right;display: none">
			                      	  	<a  onclick="sqZhiding(this)" name="${post.id }" class="hoverbutton tembutton"><i class="fa fa-envelope-o"></i> 申请置顶</a>			                      	  	
			                      	 <a  href="serchPost1?pid=${post.id}" name="${post.id }" class="hoverbutton tembutton"><i class="fa fa-legal"></i>修改 </a>
			                      	 <a onclick="deletePost(this)" name="${post.id }" class="hoverbutton redbutton"><i class="fa  fa-times"></i> 删除</a>
			                     </div>    
			                     	                     	                            
		                        </div>
			              
	                     	</s:iterator>	                     	                     			                 
		                     <ul id="postpagefoot" class="pager">	                     	                     	
		                     	 <li class="disabled"><a href="javascript:void(0);">&laquo;</a></li>	                                       
			                     <c:forEach var="pageNum" begin="1" end="${postBean.totalPage}">
			                     	<c:choose>
			                     		<c:when test="${pageNum == 1}">
			                     			<li class="active"><a>${pageNum}</a></li>
			                     		</c:when>
			                     		<c:otherwise>
			                     			<li><a onclick="pagingMyPost(this)" href="javascript:void(0);" name="showPostByPage?page=${pageNum}&publisherMail=<s:property value="#request.checkedUser.mailAddress"/>">${pageNum}</a></li>
			                     		</c:otherwise>		                     		                     			                     
			                     	</c:choose>		                     			                     		                     	
			                     </c:forEach>
			                     <c:choose>
		                     		<c:when test="${postBean.currentPage eq postBean.totalPage}">	                     		
		                     			<li class="disabled"><a href="javascript:void(0);">&raquo;</a></li>	
		                     		</c:when>
		                     		<c:otherwise>
		                     			<li><a onclick="pagingMyPost(this)" href="javascript:void(0);" name="showPostByPage?page=${pageBean.currentPage+1}&publisherMail=<s:property value="#request.checkedUser.mailAddress"/>">&raquo;</a></li>			                     		
		                     		</c:otherwise>
		                     	</c:choose>	                        		                      	   
							</ul>													 
	                    </s:else>
	                    	                	                                
	                    </div>
						<div class="alert1"></div>  
	                    <div role="tabpanel" class="tab-pane" id="myCollection">
	                     <s:if test='#request.pageBean.list.size()==0'>
	                    	  <h4>留言板空空如也。</h4>         
	                    </s:if>
	                    <s:else>
	                    	<s:iterator value="#request.pageBean.list" var="message">
		                    	<div class="art-row">	                           
		                            <h4><a class="title">${message.content} </a></h4>	                          
		                             <a href="http://localhost:8080/BBS_Forum/chaeckUserByUrl?mailAddress=<s:property value="publisherMail.mailAddress"/>"  class="author">
		                             <i class="fa fa-user"></i>&nbsp;<span>${message.publisherMail.username}</span></a> <a  class="time"><i class="fa fa-clock-o"></i>&nbsp;<span><s:date name="publishDate" format="yyyy-MM-dd HH:mm" /></span></a> 	                          	                            	
		                        </div>	
	                     	</s:iterator>
	                     	<input type="hidden" name="receiverMail" value="<s:property value="#request.checkedUser.mailAddress"/>">	                    	
		                     <!-- 分页标签的显示 -->
		                     <ul id="pagefoot" class="pager">	                     	                     	
		                     	 <li class="disabled"><a href="javascript:void(0);">&laquo;</a></li>	                                       
			                     <c:forEach var="pageNum" begin="1" end="${pageBean.totalPage}">
			                     	<c:choose>
			                     		<c:when test="${pageNum == 1}">
			                     			<li class="active"><a>${pageNum}</a></li>
			                     		</c:when>
			                     		<c:otherwise>
			                     			<li><a onclick="paging(this)" href="javascript:void(0);" name="showMessageByPage?page=${pageNum}&receiverMail=<s:property value="#request.checkedUser.mailAddress"/>">${pageNum}</a></li>
			                     		</c:otherwise>		                     		                     			                     
			                     	</c:choose>		                     			                     		                     	
			                     </c:forEach>
			                     <c:choose>
		                     		<c:when test="${pageBean.currentPage eq pageBean.totalPage}">	                     		
		                     			<li class="disabled"><a href="javascript:void(0);">&raquo;</a></li>	
		                     		</c:when>
		                     		<c:otherwise>
		                     			<li><a onclick="paging(this)" href="javascript:void(0);" name="showMessageByPage?page=${pageBean.currentPage+1}&receiverMail=<s:property value="#request.checkedUser.mailAddress"/>">&raquo;</a></li>			                     		
		                     		</c:otherwise>
		                     	</c:choose>	                        		                      	   
							</ul>
							<!-- 分页结束 -->	                    
	                    </s:else>		                                        	                 						                 
	                    </div>	
	                    
	                    <div role="tabpanel" class="tab-pane" id="myFriends">
	                  	  <input type="hidden" name="userMail" value="<s:property value="#request.checkedUser.mailAddress"/>">
		                  <s:if test='#request.friendsBean.list.size()==0'>
	                    	 <h4>未添加任何好友。</h4>     
	                   	  </s:if>
	                   	  <s:else>	 
	                   	  	<selection  class="widget bg-white post-comments">                  
		                   	  	<s:iterator value="#request.friendsBean.list" var="user">
			                    	<div class="media">	                           
			                            <a class="pull-left" href="http://localhost:8080/BBS_Forum/chaeckUserByUrl?mailAddress=${user.mailAddress }">
			                            	<img class="media-object avatar avatar-sm" src="${user.photoUrl}" alt="${user.username }">
			                            </a>
			                            <div class="comment" style="width: 525px;float: left"> 
					                         <div class="comment-author h6 no-margin">
					                        	<a href="http://localhost:8080/BBS_Forum/chaeckUserByUrl?mailAddress=${user.mailAddress}">${user.username}</a>
					                        </div>
					                        <div class="comment-bt">
					                        	<span>${user.signature}</span>
					                       	</div>                    
			                      	  </div>
			                      	  <div name="hoverbutton" style="float:right;display: none">
			                      	  	<a class="hoverbutton greenbutton"><i class="fa fa-envelope-o"></i> 私信</a>			                      	  	
			                      	  	<br><a onclick="deleFriend(this)" name="${user.mailAddress }" class="hoverbutton redbutton"><i class="fa fa-user-times"></i> 删除</a>
			                      	  </div>		                          	                            
			                        </div>
			                        <hr>			                       
		                     	</s:iterator> 
		                     	<ul id="friednpagefoot" class="pager">	                     	                     	
		                     	 <li class="disabled"><a href="javascript:void(0);">&laquo;</a></li>	                                       
			                     <c:forEach var="pageNum" begin="1" end="${friendsBean.totalPage}">
			                     	<c:choose>
			                     		<c:when test="${pageNum == 1}">
			                     			<li class="active"><a>${pageNum}</a></li>
			                     		</c:when>
			                     		<c:otherwise>
			                     			<li><a onclick="pagingFriends(this)" href="javascript:void(0);" name="showFriendsList?page=${pageNum}&userMail=<s:property value="#request.checkedUser.mailAddress"/>">${pageNum}</a></li>
			                     		</c:otherwise>			                            			                     
			                      	</c:choose>		                     			                     		                     	
			                     </c:forEach>
			                     <a  id="currentPage" onclick="pagingFriends(this)"  name="showFriendsList?page=<s:property value="#request.friendsBean.currentPage"/>&userMail=<s:property value="#request.checkedUser.mailAddress"/>"></a>		                     		              
			                     <c:choose>
		                     		<c:when test="${friendsBean.currentPage eq friendsBean.totalPage}">	                     		
		                     			<li class="disabled"><a href="javascript:void(0);">&raquo;</a></li>	
		                     		</c:when>
		                     		<c:otherwise>
		                     			<li><a onclick="pagingFriends(this)" href="javascript:void(0);" name="showFriendsList?page=${friendsBean.currentPage+1}&userMail=<s:property value="#request.checkedUser.mailAddress"/>">&raquo;</a></li>			                     		
		                     		</c:otherwise>
		                     	</c:choose>	                        		                      	   
							</ul>
	                     	</selection>	
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
$(function(){
	$(".art-row").on("mouseenter",function(){		
		$(this).children("div[name=hoverbutton]").show();
	});
	$(".art-row").on("mouseleave",function(){		
		$(this).children("div[name=hoverbutton]").hide();
	});
})
</script>


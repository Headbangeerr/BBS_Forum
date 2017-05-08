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
		
	</style>
	<link rel="stylesheet" type="text/css" href="css/user.css">
	<link rel="stylesheet" type="text/css" href="css/font-awesome-4.4.0/css/font-awesome.min.css">
  </head>
   <script type="text/javascript" src="<%=basePath%>js/member.js" charset="gb2312"></script>
    <script type="text/javascript" src="<%=basePath%>js/jquery.min.js"></script>
  <body>
    <jsp:include page="pages/header.jsp"></jsp:include>
	<div class="container user">
	    <div class="position clearfix"><a href="<%=basePath%>/index.jsp">首页</a> / 用户信息</div>
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
	                <a href="#" class="btn btn-success infos"><i class="fa fa-user-plus"></i>&nbsp;添加好友</a>
	            </div>
	            
	        </div>
	        <div class="col-md-8 user-right">
	            <div class="user-right-n clearfix">
	                <ul id="right-tab" class="nav nav-tabs">
	                    <li role="presentation" class="active">
	                        <a href="#myArticle" id="home-tab1" data-toggle="tab"><i class="fa fa-book"></i>&nbsp;Ta的帖子</a>
	                    </li>
	                    <li role="presentation">
	                        <a href="#myCollection" id="home-tab2" data-toggle="tab"><i class="fa fa-commenting-o "></i>&nbsp;Ta的留言</a>
	                    </li>	    
	                </ul>
	
	                <div class="user-right-n clearfix tab-content">
	                <!-- 遍历展示Ta的帖子列表 -->
	                  	<div role="tabpanel" class="tab-pane active" id="myArticle">
	                  		<input type="hidden" name="publisherMail" value="<s:property value="#request.checkedUser.mailAddress"/>">
		                  <s:if test='#request.postBean.list.size()==0'>
	                    	  <h4><span name="nopost"  class="title">该用户未发布任何帖子。</span></h4>         
	                    </s:if>
	                    <s:else>
	                    	<s:iterator value="#request.postBean.list" var="post">
		                    	<div class="art-row">	                           
		                            <h4><a href="" class="title">${post.title} </a></h4>	                          
		                            <span class="label label-default"><a href="">${post.childboardId.name}</a></span>
		                             <a href="http://localhost:8080/BBS_Forum/chaeckUserByUrl?mailAddress=<s:property value="publisherMail.mailAddress"/>"  class="author">
		                             <i class="fa fa-user"></i>&nbsp;<span>${post.publisherMail.username}</span>
		                             </a>
		                             <a  class="time"><i class="fa fa-clock-o"></i>&nbsp;<span><s:date name="publishTime" format="yyyy-MM-dd HH:mm" /></span></a> 	                          	                            
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
			                     			<li><a onclick="pagingPost(this)" href="javascript:void(0);" name="showPostByPage?page=${pageNum}&publisherMail=<s:property value="#request.checkedUser.mailAddress"/>">${pageNum}</a></li>
			                     		</c:otherwise>		                     		                     			                     
			                     	</c:choose>		                     			                     		                     	
			                     </c:forEach>
			                     <c:choose>
		                     		<c:when test="${postBean.currentPage eq postBean.totalPage}">	                     		
		                     			<li class="disabled"><a href="javascript:void(0);">&raquo;</a></li>	
		                     		</c:when>
		                     		<c:otherwise>
		                     			<li><a onclick="pagingPost(this)" href="javascript:void(0);" name="showPostByPage?page=${pageBean.currentPage+1}&publisherMail=<s:property value="#request.checkedUser.mailAddress"/>">&raquo;</a></li>			                     		
		                     		</c:otherwise>
		                     	</c:choose>	                        		                      	   
							</ul>							
							                  
							 
	                    </s:else>	                	                                
	                    </div>
	
	                    <div role="tabpanel" class="tab-pane" id="myCollection">
	                     <s:if test='#request.pageBean.list.size()==0'>
	                    	  <h4><span name="nomessage"  class="title">没有人为该用户留言。</span></h4>         
	                    </s:if>
	                    <s:else>
	                    	<s:iterator value="#request.pageBean.list" var="message">
		                    	<div class="art-row">	                           
		                            <h4><a href="" class="title">${message.content} </a></h4>	                          
		                             <a href="http://localhost:8080/BBS_Forum/chaeckUserByUrl?mailAddress=<s:property value="publisherMail.mailAddress"/>"  class="author">
		                             <i class="fa fa-user"></i>&nbsp;<span>${message.publisherMail.username}</span></a> <a  class="time"><i class="fa fa-clock-o"></i>&nbsp;<span><s:date name="publishDate" format="yyyy-MM-dd HH:mm" /></span></a> 	                          	                            
		                        </div>	
	                     	</s:iterator>	                    	
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
	                                        	                 
						<a id="showLastPage" style="display: none" onclick="paging(this)"  name=''></a>
	                     <form id="messageForm">
	                     <div class="alert"></div>	            
	                     <input type="hidden" name="publisherMail" value="<s:property value="#session.user.username"/>">
	                     	<input type="hidden" name="receiverMail" value="<s:property value="#request.checkedUser.mailAddress"/>">
		                    <textarea name="content"  class="form-control" name="mailAddress" style="height: 70px; margin-top: 5px;" placeholder="对Ta说点啥"></textarea>	                  
		                     <a onclick="send_message()" style="width: 175px;float: right;" class="btn btn-success btn-outline btn-block" id="send_message" >
									<i class="fa fa-pencil"></i>&nbsp;<span>给Ta留言</span>
							 </a>	                     
	                     </form>	                     
	                    </div>		                   
	                </div>
	            </div>
	        </div>
	    </div>
    </div>
  </body>
</html>

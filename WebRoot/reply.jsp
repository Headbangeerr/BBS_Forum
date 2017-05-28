<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>post show</title>
<link rel="stylesheet" type="text/css" href="css/replay.css">
</head>
<script type="text/javascript" src="<%=basePath%>js/posts.js" charset="gb2312"></script>
<body>
<jsp:include page="pages/header.jsp"></jsp:include>
	<div class="container user">
	    <div class="position clearfix"><a href="<%=basePath%>/index.jsp">首页</a> / 帖子详情</div>
	    <div class="user-cont clearfix">
	    	<div class="col-md-8 user-right">
	            <div class="user-right-n clearfix">
	                <ul id="right-tab" class="nav nav-tabs">
	                    <li role="presentation" class="active">
	                        <a href="#myArticle1" id="home-tab1" data-toggle="tab"><i class="fa fa-book"></i>&nbsp;帖子信息</a>
	                    </li>    
	                </ul>
	                <div class="user-right-n clearfix tab-content">
	                <!-- 遍历展示Ta的帖子列表 -->
	                  	<div role="tabpanel" class="tab-pane active" id="myArticle1">
		                    <s:if test='#request.postBean.list.size()==0'>
	                    	  <h4>未发表任何帖子。</h4>         
		                    </s:if>
		                    <s:else>	                    	
			                    	<div id="tem" class="art-row">	                           
			                            <h4><a href="" class="title">${post.title} </a></h4>	                          
			                            <span class="label label-default"><a href="">${post.childboardId.name}</a></span>
			                             <a href="http://localhost:8080/BBS_Forum/chaeckUserByUrl?mailAddress=<s:property value="publisherMail.mailAddress"/>"  class="author">
			                             <i class="fa fa-user"></i>&nbsp;<span>${post.publisherMail.username}</span>
			                             </a>
			                             <a  class="time"><%=session.getAttribute("dateString")%> View:${post.pageView}</span></a> 	                          	                            
			                        </div>	                   	                     			                                   		                    									 
		                    </s:else>	                	                                
	                    </div>
	                    <div role="tabpanel" class="tab-pane active" id="myArticle">
		                    <s:if test='#request.replyBean.list.size()==0'>
	                    	  <h4>未发表任何帖子。</h4>         
		                    </s:if>
		                    <s:else>	                    	
		                    	<s:iterator value="#request.replyBean.list" var="reply">
			                    	<div class="art-row">	                           
			                            <h4><a href="serchPost?pid=${post.id}" class="title">${reply.content} </a></h4>	                          
			                             <a href="http://localhost:8080/BBS_Forum/chaeckUserByUrl?mailAddress=<s:property value="senderMail.mailAddress}"/>"  class="author">
			                             <i class="fa fa-user"></i>&nbsp;<span>${reply.senderMail.username}</span>
			                             </a>
			                             <a  class="time"><i class="fa fa-clock-o"></i>&nbsp;<span><s:date name="sendtime" format="yyyy-MM-dd HH:mm" /></span></a> 	                          	                            
			                        </div>	
		                     	</s:iterator>	
		                     	<input type="hidden" name="bids" value='<%=session.getAttribute("bid")%>'>                     	                     			                 
			                     <ul id="postpagefoot" class="pager">	                     	                     	
			                     	 <li class="disabled"><a href="javascript:void(0);">&laquo;</a></li>	                                       
				                     <c:forEach var="pageNum" begin="1" end="${replyBean.totalPage}">
				                     	<c:choose>
				                     		<c:when test="${pageNum == 1}">
				                     			<li class="active"><a>${pageNum}</a></li>
				                     		</c:when>
				                     		<c:otherwise>
				                     			<li><a onclick="pagingReply(this)" href="javascript:void(0);" name="showReplyByPage?page=${pageNum}">${pageNum}</a></li>
				                     		</c:otherwise>		                     		                     			                     
				                     	</c:choose>		                     			                     		                     	
				                     </c:forEach>
				                     <c:choose>
			                     		<c:when test="${postBean.currentPage eq postBean.totalPage}">	                     		
			                     			<li class="disabled"><a href="javascript:void(0);">&raquo;</a></li>	
			                     		</c:when>
			                     		<c:otherwise>
			                     			<li><a onclick="pagingReply(this)" href="javascript:void(0);" name="showReplyByPage?page=${pageBean.currentPage+1}"/>">&raquo;</a></li>			                     		
			                     		</c:otherwise>
			                     	</c:choose>	                        		                      	   
								</ul>										 
		                    </s:else>	                	                                
	                    </div>           	           	                  
	                </div>
	                 <div class="user-right-n clearfix tab-content">
	               		<form id="replyForm">
							<table class="fatieArea">	
								<input type="hidden" name="publisherMail" value="<s:property value="#session.user.mailAddress"/>">				
								<tr>
									<td valign="top">回帖：</td>
									<td id="textAreaWrap">
										<div id="editorToolBar"></div>
										<textarea id="textAreaContainer" name="content" class="textAreaContainer"></textarea>
										<div class="ty_bbs_preview_area"></div>
									</td>
								</tr>
								<tr>
								<td ><!--标签：--></td>
								<td style="zoom:1;">
									<a onclick="add_reply()" style="width: 150px;float: right;" class="common-submitBtn fr" id="send_message" >
										<i class="fa fa-pencil"></i>&nbsp;<span>回帖(Ctrl+Enter)</span>
									</a>
									 <div class="alert"></div>		
								</td>
								</tr>
							</table>
						</form>
	                </div>
	            </div>
	        </div>        
	    </div>
    </div>
</body>
</html>
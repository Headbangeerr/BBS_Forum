<%@ page language="java" contentType="text/html; charset=gb2312"
    pageEncoding="gb2312"%>
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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!-- 	<link rel="stylesheet" type="text/css" href="css/user.css">
	<link rel="stylesheet" type="text/css" href="css/font-awesome-4.4.0/css/font-awesome.min.css">
	<link type="text/css" rel="stylesheet" href="css/main.min.css"> -->
</head>
<body>
<jsp:include page="pages/header.jsp"></jsp:include>
<script type="text/javascript" src="<%=basePath%>js/posts.js" charset="UTF-8"></script>
	<div class="container user">
	    <div class="position clearfix"><a href="<%=basePath%>/index.jsp">首页</a> / 帖子列表</div>
	    <div class="user-cont clearfix">
	    	<div class="col-md-8 user-right">
	            <div class="user-right-n clearfix">
	                <ul id="right-tab" class="nav nav-tabs">
	                    <li role="presentation" class="active">
	                        <a href="#myArticle" id="home-tab1" data-toggle="tab"><i class="fa fa-book"></i>&nbsp;帖子信息</a>
	                    </li>    
	                </ul>
	                <div class="user-right-n clearfix tab-content">
	                <!-- 遍历展示Ta的帖子列表 -->
	                  	<div role="tabpanel" class="tab-pane active" id="myArticle">
		                    <s:if test='#request.postBean.list.size()==0'>
	                    	  <h4>未发表任何帖子。</h4>         
		                    </s:if>
		                    <s:else>	                    	
		                    	<s:iterator value="#request.postBean.list" var="post">
			                    	<div class="art-row">	                           
			                            <h4><a href="serchPost?pid=${post.id}" class="title">${post.title} </a></h4>	                          
			                            <span class="label label-default"><a href="">${post.childboardId.name}</a></span>
			                             <a href="http://localhost:8080/BBS_Forum/chaeckUserByUrl?mailAddress=<s:property value="publisherMail.mailAddress"/>"  class="author">
			                             <i class="fa fa-user"></i>&nbsp;<span>${post.publisherMail.username}</span>
			                             </a>
			                             <a  class="time"><i class="fa fa-clock-o"></i>&nbsp;<span><s:date name="publishTime" format="yyyy-MM-dd HH:mm" />  View:${post.pageView}</span></a> 	                          	                            
			                        </div>	
		                     	</s:iterator>	
		                     	<input type="hidden" name="bids" value='<%=session.getAttribute("bid")%>'>                     	                     			                 
			                     <ul id="postpagefoot" class="pager">	                     	                     	
			                     	 <li class="disabled"><a href="javascript:void(0);">&laquo;</a></li>	                                       
				                     <c:forEach var="pageNum" begin="1" end="${postBean.totalPage}">
				                     	<c:choose>
				                     		<c:when test="${pageNum == 1}">
				                     			<li class="active"><a>${pageNum}</a></li>
				                     		</c:when>
				                     		<c:otherwise>
				                     			<li><a onclick="pagingPost(this)" href="javascript:void(0);" name="showChoosePostByPage?page=${pageNum}&bid=<%=session.getAttribute("bid")%>">${pageNum}</a></li>
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
	                </div>
	            </div>
	        </div>        
	    </div>
    </div>

</body>
</html>
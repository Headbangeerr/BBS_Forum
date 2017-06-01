<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
 	<link rel="stylesheet" type="text/css" href="css/user.css">
	<link rel="stylesheet" type="text/css" href="css/font-awesome-4.4.0/css/font-awesome.min.css">
	<link type="text/css" rel="stylesheet" href="css/postshow.css"> 
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<script type="text/javascript" src="<%=basePath%>js/posts.js" charset="UTF-8"></script>
	<div class="container user">
	    <div class="position clearfix"><a href="<%=basePath%>/index.jsp">首页</a> / 帖子列表</div>
	    <div class="user-cont clearfix" style="width: 850px;float: left;height: 700px">
	    	<div class="col-md-8 user-right" style="width: 100%;height: 100%">
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
	                    	  <h4>该板块无人发帖。</h4>         
		                    </s:if>
		                    <s:else>	                    	
		                    	<s:iterator value="#request.postBean.list" var="post">
			            
			                    	
			                    	<div   class="media">
				                    	<a class="pull-left" href="http://localhost:8080/BBS_Forum/chaeckUserByUrl?mailAddress=${post.publisherMail.mailAddress }">
				                    		<img class="media-object avatar avatar-sm" src="upload/headicon/default_icon.jpg" alt="sjswc">
				                    	</a>
				                    	<div class="comment">
					                    	<div class="comment-author h6 no-margin">
						                    	<div class="comment-meta small">
							                    	<a class="badge-comment">${post.pageView}</a>
					                    		</div>
					                    		<a href="serchPost?pid=${post.id}">${post.title}</a>
					                    	</div>
					                    	<div class="comment-bt">
						                    	<span class="label label-default">
							                    	<a href="checkZiPostByUrl?cid=${post.childboardId.id }">${post.childboardId.name}</a>
							                    </span> &nbsp;•&nbsp;
							                    <strong>
							                    	<a href="http://localhost:8080/BBS_Forum/chaeckUserByUrl?mailAddress=${post.publisherMail.mailAddress }">${post.publisherMail.username}</a>
							                    </strong>&nbsp;•&nbsp;
						                    	 <span><s:date name="publishTime" format="yyyy-MM-dd HH:mm" /></span>
					                    	 </div>
					                    	 <hr>
				                    	 </div>
                    
			                                                 
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
				                     			<li><a onclick="pagingAllPost(this)" href="javascript:void(0);" name="showAllPostByPage?page=${pageNum}">${pageNum}</a></li>
				                     		</c:otherwise>		                     		                     			                     
				                     	</c:choose>		                     			                     		                     	
				                     </c:forEach>
				                     <c:choose>
			                     		<c:when test="${postBean.currentPage eq postBean.totalPage}">	                     		
			                     			<li class="disabled"><a href="javascript:void(0);">&raquo;</a></li>	
			                     		</c:when>
			                     		<c:otherwise>
			                     			<li><a onclick="pagingAllPost(this)" href="javascript:void(0);" name="showAllPostByPage?page=${pageBean.currentPage+1}">&raquo;</a></li>			                     		
			                     		</c:otherwise>
			                     	</c:choose>	                        		                      	   
								</ul>										 
		                    </s:else>	                	                                
	                    </div>           	                  
	                </div>
	            </div>
	        </div>        
    		
    </div>
		<div class="col-md-4 user-left" style="margin-top: 10px;margin-left:50px; width:200px;">
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
								<a href="http://localhost:8080/BBS_Forum/chaeckUserByUrl?mailAddress=<s:property value="#session.user.mailAddress"/>">
									<img src="<s:property value='#session.user.photoUrl'/>" alt="<s:property value='#session.user.mailAddress'/>" class="avatar avatar-lg img-circle">
								</a>
							</div>
							<div class="profile-sidebar-item profile-info">
								<span class="h5 bold"><s:property value='#session.user.username'/></span>
								<p></p>
								<div class="w150 center-block mt10">
									<a class="btn btn-success btn-outline btn-block" href="<%=path+"/publish_post.jsp"%>">
										<span>发布新帖子</span>
									</a>
								</div>
							</div>
							<hr>
	
						</div>
					</div>
				</div>
              </s:if>
	    </div>
	</div>
</body>
</html>
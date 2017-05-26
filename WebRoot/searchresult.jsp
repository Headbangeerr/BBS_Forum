<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
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
.bluebutton {
	color: #fff;
	background: #2aafc8;
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
  <link rel="stylesheet" type="text/css" href="css/main.min.css">
  <link rel="stylesheet" type="text/css" href="css/font-awesome-4.4.0/css/font-awesome.min.css">
  <script type="text/javascript" src="<%=basePath%>js/searchresult.js" charset="gb2312"></script>
    <script type="text/javascript" src="<%=basePath%>js/jquery.min.js"></script>
    <base href="<%=basePath%>"> 
    <title>搜索结果</title>
  </head>
  
  <body>
    <jsp:include page="pages/header.jsp"></jsp:include>
    <jsp:include page="pages/search.jsp"></jsp:include>
    <input type="hidden" id="searchFlag" value="${searchFlag}">
    <input type="hidden" id="keyword" value="${keyword}">
    <input type="hidden" id="username" value="${username}">
	<input type="hidden" id="search_keyword1" value="${search_keyword1}">    
	<input type="hidden" id="search_username" value="${search_username}">
	<input type="hidden" id="search_keyword2" value="${search_keyword2}">
	<input type="hidden" id="search_childboardlist" value="${search_childboardlist}">
	
    <div class="container" >
    	<div class="row row-centered">
    		<div class="col-md-10 col-md-offset-1">
    			<ul class="list-group" >
    				<div class="list-group-item active ">
	                   	 搜素结果		                    		                    
	                </div>	  		               		       
			    <s:if test="#request.searchFlag==2">
			    	<div class="tab-pane active">
                	<s:if test="#request.resultBean.list.size()==0">
                		<h2>搜索结果为空</h2>
                	</s:if>
                	<s:else>
                		<section  class="widget bg-white post-comments">
	                		<s:iterator value="#request.resultBean.list" var="user">
	                			<div class="media">
		                			<a class="pull-left" href="http://localhost:8080/BBS_Forum/chaeckUserByUrl?mailAddress=${user.mailAddress}">
		                				<img class="media-object avatar avatar-sm" src="${user.photoUrl}" alt="${user.username }">
		                			</a>		                				                		
		                			<div class="comment">
			                			<div class="comment-author h4 no-margin">
			                				<a href="#">${post.title }</a>
			                			</div>
			                			<div class="comment" style="width: 525px;float: left"> 
					                         <div class="comment-author h6 no-margin" >
					                        	<a href="http://localhost:8080/BBS_Forum/chaeckUserByUrl?mailAddress=${user.mailAddress}">${user.username}</a>					                        						                        						                        
					                        	
					                        	&nbsp;&nbsp;<span style="cursor:pointer" class="hoverbutton bluebutton"><i class="fa fa-envelope-o"></i> 私信</span>
					                        	
					                        </div>
					                        <div class="comment-bt">
					                        	<span>${user.signature}</span>
					                       </div>    
					                       <hr>                
			                      	  </div>
		                			</div>
		                		</div>				                		
	                		</s:iterator>			                					                		
	                	</section>
	            </s:else>
			    
			    </s:if>
			    <s:else>
		    	<div class="tab-pane active">
                	<s:if test="#request.resultBean.list.size()==0">
                		<h2>搜索结果为空</h2>
                	</s:if>
                	<s:else>
                		<section  class="widget bg-white post-comments">
	                		<s:iterator value="#request.resultBean.list" var="post">
	                			<div class="media">
		                			<a class="pull-left" href="http://localhost:8080/BBS_Forum/chaeckUserByUrl?mailAddress=${post.publisherMail.mailAddress}">
		                				<img class="media-object avatar avatar-sm" src="${post.publisherMail.photoUrl }" alt="${user.username }">
		                			</a>		                				                		
		                			<div class="comment">
			                			<div class="comment-author h4 no-margin">
			                				<a href="#">${post.title }</a>
			                			</div>
			                			<div class="comment-bt">
			                				<a>${post.content }</a>
			                			</div>
			                			<div class="comment-bt">
			                				<span class="label label-default"><a href="#">${post.childboardId.name }</a></span>
			                				 &nbsp;•&nbsp;
			                				 <strong><a href="http://localhost:8080/BBS_Forum/chaeckUserByUrl?mailAddress=${post.publisherMail.mailAddress}">${post.publisherMail.username}</a>
			                				 </strong>
			                				 &nbsp;•&nbsp; 
			                				 <span><s:date name="#post.publishTime"></s:date></span>
			                			</div>
		                			</div>
		                		</div>	
		                		<hr>			                		
	                		</s:iterator>			                					                		
	                	</section>
                	</s:else>		                	
                </div>  						    			
		    </s:else> 	
		    <s:if test="#request.resultBean.totalPage>1">		    	
		    	<ul id="pagefoot" class="pager">
		    		<c:choose>
		    			<c:when test="${resultBean.currentPage == 1 }">
		    				<li class="disabled"><a href="javascript:void(0);">&laquo;</a></li>
		    			</c:when>
		    			<c:otherwise>
		    				<li ><a onclick="paging(this)" href="javascript:void(0);" name="${resultBean.currentPage-1}">&laquo;</a></li>
		    			</c:otherwise>
		    		</c:choose>	                     	                     	
                   	                                       
                    <c:forEach var="pageNum" begin="1" end="${resultBean.totalPage}">
                    	
                    	<c:choose>
                    		<c:when test="${pageNum eq resultBean.currentPage}">
                    			
                    			<li class="active"><a>${pageNum}</a></li>
                    		</c:when>
                    		<c:otherwise>
                    			<li><a onclick="paging(this)" href="javascript:void(0);" name="${pageNum}">${pageNum}</a></li>
                    		</c:otherwise>		                     		                     			                     
                    	</c:choose>		                     			                     		                     	
                    </c:forEach>
                    <c:choose>
                   		<c:when test="${resultBean.currentPage eq resultBean.totalPage}">	                     		
                   			<li class="disabled"><a href="javascript:void(0);">&raquo;</a></li>	
                   		</c:when>
                   		<c:otherwise>
                   			<li><a onclick="paging(this)" href="javascript:void(0);" name="${resultBean.currentPage+1}">&raquo;</a></li>			                     		
                   		</c:otherwise>
                   	</c:choose>	                        		                      	   
				</ul>		    
		    </s:if>
		   </ul>
		  </div>
		</div>
	</div>
  </body>
</html>

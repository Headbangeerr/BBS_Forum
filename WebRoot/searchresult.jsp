<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <link rel="stylesheet" type="text/css" href="css/main.min.css">
    <base href="<%=basePath%>"> 
    <title>搜索结果</title>
  </head>
  
  <body>
    <jsp:include page="pages/header.jsp"></jsp:include>
    <jsp:include page="pages/search.jsp"></jsp:include>
    <s:if test="#request.searchFlag=='2'">
    	
    
    </s:if>
    <s:else>
    	<div class="container" >
	    	<div class="row row-centered">
	    		<div class="col-md-10 col-md-offset-1">
	    			<ul class="list-group" >
	    				<div class="list-group-item active ">
		                   	 搜素结果		                    		                    
		                </div>	  
		                <div class="tab-pane active">
		                	<section  class="widget bg-white post-comments">
		                		<div class="media">
		                			<a class="pull-left" href="http://localhost:8080/BBS_Forum/chaeckUserByUrl?mailAddress=0000"><img class="media-object avatar avatar-sm" src="upload/headicon/default_icon.jpg" alt="sjswc"></a>		                				                		
		                		</div>
		                		
		                	</section>
		                </div>  			
	    			</ul>	    			  
	    		</div>    	    	    	    
	    	</div>
    	</div>
    </s:else>
  </body>
</html>

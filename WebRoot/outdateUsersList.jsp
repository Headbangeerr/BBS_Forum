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
table.hovertable {
    font-family: verdana,arial,sans-serif;
    font-size:11px;
    color:#333333;
    border-width: 1px;
    border-color: #999999;
    border-collapse: collapse;
}
table.hovertable th {
    background-color:#c3dde0;
    border-width: 1px;
    padding: 8px;
    border-style: solid;
    border-color: #a9c6c9;
}
table.hovertable tr {
    background-color:#d4e3e5;
}
table.hovertable td {
    border-width: 1px;
    padding: 8px;
    border-style: solid;
    border-color: #a9c6c9;
}
.alert2333-false {
		width:220px;
	    color: #FFFFFF;
	    background-color: #FF3030;
	    border-color: #FFFFFF;
	}
	.alert2333-success {
		width:150px;
	    color: #3c763d;
	    background-color: #dff0d8;
	    border-color: #d6e9c6;
	}
	.alert2333 {
		text-align:center;
		margin-left:120px;
	    padding: 15px;
	    margin-bottom: 20px;
	    border: 1px solid transparent;
	    border-radius: 4px;
	}
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
    <title></title>
	<link rel="stylesheet" type="text/css" href="css/user.css">
	<link rel="stylesheet" type="text/css" href="css/font-awesome-4.4.0/css/font-awesome.min.css">
	<link type="text/css" rel="stylesheet" href="css/main.min.css">
  </head>
   <script type="text/javascript" src="js/member.js" charset="utf-8"></script>
    <script type="text/javascript" src="<%=basePath%>js/jquery.min.js"></script>
  <body>
    <jsp:include page="pages/header2.jsp"></jsp:include>
	<div class="container user">
	    <div class="position clearfix"><a href="<%=basePath%>/manage.jsp">首页</a> /过期用户列表</div>
	                <div class="user-right-n clearfix tab-content">
<div class="art-row">	   
 <table id="ta"  class="hovertable" width="100%" style="display: ">
   		<tr>
   			<th>用户名</th>
   			<th>最后登录时间</th>
   			<th>操作</th>	
   		</tr>
	
             	
	          <s:iterator value="#request.userBean.list" var="user">
	         <s:if test="#user.status<3">

		        <tr  onmouseover="this.style.backgroundColor='#ffff66';" onmouseout="this.style.backgroundColor='#d4e3e5';">                        
		         <td id="1"> <a href="" class="title">${user.username} </a></td>
		         <td> ${user.lastLoginDate}</td>
		         <td> 
					            <input type="button" onclick="deleteUser(this)" name="${user.mailAddress}" value="注销">
	
                 </td>
		             	                  
		         </tr> 
		           </s:if>	                 
	                    </s:iterator>	
	                    	
	       </table>                   	                     			                 
		                     <ul id="userpagefoot" class="pager">	                     	                     	
                             <c:choose>
		                     		<c:when test="${userBean.currentPage eq 1}">	                     		
		                     			<li class="disabled"><a href="javascript:void(0);">&laquo;</a></li>	
		                     		</c:when>
		                     		<c:otherwise>
		                     			<li><a href="showOutdateUserByPage?page=${userBean.currentPage-1}&lastLoginDate=<s:property value="#request.lastLoginDate"/>">&laquo;</a></li>			                     		
		                     		</c:otherwise>
		                     	</c:choose>	                                               
			                     <c:forEach var="pageNum" begin="1" end="${userBean.totalPage}">
			                     	<c:choose>
			                     		<c:when test="${pageNum == 1}">
			                     			<li class="active" ><a href="showOutdateUserByPage?page=${pageNum}&lastLoginDate=<s:property value="#request.lastLoginDate"/>">${pageNum}</a></li>
			                     		</c:when>
			                     		<c:otherwise>
			                     			<li><a href="showOutdateUserByPage?page=${pageNum}&lastLoginDate=<s:property value="#request.lastLoginDate"/>">${pageNum}</a></li>
			                     		</c:otherwise>		                     		                     			                     
			                     	</c:choose>		                     			                     		                     	
			                     </c:forEach>
			                     <c:choose>
		                     		<c:when test="${userBean.currentPage eq userBean.totalPage}">	                     		
		                     			<li class="disabled"><a href="javascript:void(0);">&raquo;</a></li>	
		                     		</c:when>
		                     		<c:otherwise>
		                     			<li><a href="showOutdateUserByPage?page=${userBean.currentPage+1}&lastLoginDate=<s:property value="#request.lastLoginDate"/>">&raquo;</a></li>			                     		
		                     		</c:otherwise>
		                     	</c:choose>	                        		                      	   
							</ul>						     	                                
	         </div>
	        </div>
	    
	    <div class="alert2333"></div>
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
if(document.getElementById("1")==null){
    document.getElementById("ta").style.display="none";
    alert("未查询到任何过期用户"+"点击返回首页");
    window.document.location.href="manage.jsp";
}

</script>


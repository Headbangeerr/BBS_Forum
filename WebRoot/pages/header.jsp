<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="/struts-tags" prefix="s" %>
    <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <base href="<%=basePath%>">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>导航栏</title>
<link rel="stylesheet" type="text/css" href="<%=basePath%>css/bootstrap.min.css">
	<!-- <link rel="stylesheet" type="text/css" href="<%=basePath%>css/titlebar.css">-->
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
</head>
  <body onload="getBoardList()">
  <nav class="navbar navbar-inverse "   role="navigation">
            <div class="navbar-header">
                <a class="navbar-brand" href="#">BBS技术论坛</a>
            </div>
            
            <div>
		      	<ul class="nav navbar-nav">
			         <li><a href="<%=path%>/index.jsp">首页</a></li>
			         <li class="dropdown">
			            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
			               精选板块 <b class="caret"></b>
			            </a>
			           
			            <ul id="boardlist" class="dropdown-menu">
			            	
			            </ul>
			            
			         </li>
			          <li><a href="">论坛热帖</a></li>
			          <li><a href="">论坛新帖</a></li>
			          <li><a href="">精华帖</a></li>
		      </ul>
  		 </div>
   
   
   <s:if test='#session.user==null'>
	   	<ul class="nav navbar-nav navbar-right user">
	                <li><a href="<%=request.getContextPath()%>/login.jsp">登陆</a></li>
	                <li><a href="<%=request.getContextPath()%>/regist.jsp">注册</a></li>
	    </ul>         
	    <p class="navbar-text navbar-right">尊敬的游客您好！</p>    
   </s:if>
   <s:elseif test='#session.user.type=="0"'><!-- 判断用户类型，如果为0代表用户为一般用户 -->
   		<ul class="nav navbar-nav navbar-right user">

                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                            <s:property value="#session.user.username"/><b class="caret"></b>
                        </a>
                        <ul class="dropdown-menu">
                            <li><a href="chaeckUserByUrl?mailAddress=<s:property value="#session.user.mailAddress"/>">我的信息</a></li>                            
                            <li><a href="<%=path+"/publish_post.jsp"%>">我要发帖</a></li>
                            <li class="divider"></li>
                            <li><a href="<%=path+"/logout.action"%>">退出登陆</a></li>
                        </ul>
                    </li>
                </ul>
                 <p class="navbar-text navbar-right">尊敬的用户您好！</p>
   </s:elseif>     
    </nav>   
  </body>
</html>
<script>
function getBoardList(){//通过ajax从后台获取板块列表
	$.ajax({
		type:"post",
		url:"getBoardList",
		dataType:"json",
        success:function(data){
               	$.each(data.boardList,function(index,board){        
        			var str;
        			str="<li>";
        			str+="<a href='checkPostByUrl?bid="+board.id+"'>"+board.name+"</a>";
        			str+="</li>"
        			str+="<li class='divider'></li>";
        			$("#boardlist").append(str);
        			
        	});
        }
	})
}
</script>
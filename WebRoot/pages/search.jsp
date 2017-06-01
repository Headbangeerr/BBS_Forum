<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/search.js">
</script>
<link rel="stylesheet" type="text/css" href="css/search.css">
<!--  <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">-->
    <title>搜索框</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	

  </head>
  
  <body>
   <div class="searchbox">
   	<ul class="border1">
		<li><a class="style1" onmouseover="this.style.cursor='pointer'">帖子</a></li>
		<li><a onmouseover="this.style.cursor='pointer'">用户</a></li>
	</ul>
	<div class="bodys">
		<form id="commonSearch" action="search" method="post">	
			<input type="hidden" name="searchFlag" value="0">
			<p><input type="text"  class="one" name="keyword" placeholder="输入关键字" /><button onclick="submitSearch(this)" type="button"  class="one1">搜索</button><a name="detailSearch" onmouseover="this.style.cursor='pointer'">高级搜索</a></p>
			<p><input type="text"  class="two" name="username" placeholder="输入用户名" /><button  onclick="submitSearch(this)" type="button"  class="two2">搜索</button><a  name="detailSearch"  onmouseover="this.style.cursor='pointer'">高级搜索</a></p>
		</form>
	</div>
   </div>
   
   <!-- 悬浮层1 -->
   <div id="detailedSearch">
   		<div class="row1">
          <a href="javascript:void(0)" title="关闭窗口" class="close_btn" id="closeBtn">×</a>
        </div>
   		<div class="col-xs-12 searchTitle">搜索指定版块内的帖子</div>
   		<div class="nav search_nav ">
   			<div class="login_username">
					
				<form id="searchByBoard" action="search" method="post" >
		   			选择版块：<br>
		   			<input type="hidden" name="searchFlag" value="3">
		   			<select name="search_boardlist">
		   				<option value="0">未选择</option>
		   			</select>-
		   			<select name="search_childboardlist">
		   				<option value="0">请选择</option>
		   			</select>
					<span id="boardlist_error1" class="error_cuo">
						× 请选择版块
					</span>
					<br>
					<input type="text" style="margin-top: 5px" name="search_keyword1" placeholder="&nbsp;&nbsp;输入搜索关键字"  />
					
					<span id="boardlist_error2" style="margin-top: 5px" class="error_cuo">
						× 请输入要搜索的关键字
					</span>
					<br>
					<input type="button"  style="margin-top: 30px"  value="搜索" id="search_submit" />
					<br>
					<a onmouseover="this.style.cursor='pointer'" id="changto_user" >搜索指定用户的帖子</a>
	   		</form>
			</div>	
   		</div>
   	</div>
   	<!-- 悬浮层2 -->
   	<div id="detailedSearch1">
   		<div class="row1">
          <a href="javascript:void(0)" title="关闭窗口" class="close_btn" id="closeBtn">×</a>
        </div>
   		<div class="col-xs-12 searchTitle">搜索指定用户的帖子</div>
   		<div class="nav search_nav ">
   			<div class="login_username">					
				<form id="searchByUser" action="search" method="post" >
					<input type="hidden" name="searchFlag" value="4">
		   			<input type="text" style="margin-top: 5px" name="search_username" placeholder="&nbsp;&nbsp;输入用户名"  />
					<span id="boardlist_error3" class="error_cuo">
						× 请输入要搜索的用户名
					</span>
					<br>
					<input type="text" style="margin-top: 5px" name="search_keyword2" placeholder="&nbsp;&nbsp;输入搜索关键字"  />
					<span id="boardlist_error4" style="margin-top: 5px" class="error_cuo">
						× 请输入要搜索的关键字
					</span>
					<br>
					<input type="button"  style="margin-top: 30px"  value="搜索" id="search_submit1" />
					<br>
					<a onmouseover="this.style.cursor='pointer'" id="changeto_board" >搜索指定版块内的帖子</a>
	   			</form>
			</div>	
   		</div>   
   </div>
  
  
  </body>
  
</html>

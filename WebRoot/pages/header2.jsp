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
	<script language="javascript" type="text/javascript" src="js/My97DatePicker/WdatePicker.js"></script>
</head>
  <body onload="getBoardList()">
  <nav class="navbar navbar-inverse" role="navigation">
            <div class="navbar-header">
                <a class="navbar-brand" href="#">BBS技术论坛</a>
            </div>
            
            <div>
      <ul class="nav navbar-nav">
         <li><a href="<%=path%>/manage.jsp">首页</a></li>
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
          <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
               版块管理<b class="caret"></b>
            </a>
           
            <ul id="boardlist" class="dropdown-menu">
          <li><a href="addBoard.jsp">增设板块</a></li>
          <li><a href="updateBoard.jsp">修改版块</a></li>
          <li><a href="showBoardList2.action">删除版块</a></li>
            </ul>
            
         </li>
         <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
               用户管理<b class="caret"></b>
            </a>
           
            <ul id="boardlist" class="dropdown-menu">
          <!--  <li><a href="outdateUsersList.jsp">注销用户</a></li>-->
          <li>
          <form action="showOutdateUserByPage.action" method="post">
          <input style="width:100px;display:inline;float:left" id="endDate" name="lastLoginDate" placeholder="选择注销日期" readonly="readonly" onfocus="WdatePicker({isShowWeek:true})" /> 
          <input style="display:inline;float:right" type="submit" value="提交">
          </form> 
          </li>
          <li><a href="showSensitivePost.action">禁言用户</a></li>
            </ul>
            
         </li>
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
      <s:elseif test='#session.user.type=="1"'><!-- 判断用户类型，如果为0代表用户为一般用户 -->
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
                 <p class="navbar-text navbar-right">管理员</p>
   
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
               	$.each(data.boardList,function(idnex,board){        
        			var str;
        			str="<li>";
        			str+="<a href='#'>"+board.name+"</a>";
        			str+="</li>"
        			str+="<li class='divider'></li>";
        			$("#boardlist").append(str);
        			
        	});
        }
	})
}
</script>
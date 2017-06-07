<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
        <%@taglib uri="/struts-tags" prefix="s" %>
    <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
   <script type="text/javascript" src="<%=basePath%>js/member.js" charset="gb2312"></script>
    <script type="text/javascript" src="<%=basePath%>js/jquery.min.js"></script>
<body>
    <jsp:include page="pages/header2.jsp"></jsp:include>
    <div class="position clearfix"><a href="<%=basePath%>/manage.jsp">首页</a> / <a href="BoardList.jsp">版块列表</a></div>
    <center>
   <table  class="hovertable" width="60%">
   		<tr>
   			<th>版块名</th>
   			<th>说明</th>
   			<th>操作</th>	
   		</tr>
   <s:iterator value="#request.BoardList2" var="board">
   <tr onmouseover="this.style.backgroundColor='#ffff66';" onmouseout="this.style.backgroundColor='#d4e3e5';">
          <td> ${board.name}</td>
          <td> ${board.info}</td>
          <td>   
			          <form onSubmit= "return ret()" action="deleteBoard.action" method="post" >
					            <input type="hidden" value=${board.id} name="board.id" />
					            <input type="submit" value="删除">
				      </form>
		  </td>
   </tr>
   </s:iterator>
</table>
</center>
</body>
</html>
<script>
function ret(){
  if(confirm("该板块的子版块和帖子将一并删除，是否确定？")) {
         return true;
};
return false;
}
</script>
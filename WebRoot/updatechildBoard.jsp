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
    <div class="position clearfix"><a href="<%=basePath%>/manage.jsp">首页</a> / <a href="updateBoard.jsp">修改版块信息版块</a> /<a href="updatechildBoard.jsp">修改子版块信息</a></div>
<center>
    <table  class="hovertable" width="60%"> 
    <tr onmouseover="this.style.backgroundColor='#ffff66';" onmouseout="this.style.backgroundColor='#d4e3e5';">
      <td width="30%">父版块</td>
      <td>
      <s:iterator value="#session.BoardList1" var="board">

         <input onclick="getChildBoardList(this)" type="radio" name="board.id" value="${board.id}" /> ${board.name}
      </s:iterator>
      </td>
    </tr>
     <tr onmouseover="this.style.backgroundColor='#ffff66';" onmouseout="this.style.backgroundColor='#d4e3e5';">
     <td>子版块名</td>
      <td><div id="ChildBoardName">
      <a class='childboardClass'> 
      </a>
      </div></td>
    </tr onmouseover="this.style.backgroundColor='#ffff66';" onmouseout="this.style.backgroundColor='#d4e3e5';">
      <form action="updateChildBoard.action" method="post">
    <tr onmouseover="this.style.backgroundColor='#ffff66';" onmouseout="this.style.backgroundColor='#d4e3e5';">
      <td>现版块名</td>
      <td><input id="childboard2333" type="text" name="childboard.name" />
      <input id="childboardId" type="hidden" name="childboard.id" />
      </td>
    </tr>
    
     <tr onmouseover="this.style.backgroundColor='#ffff66';" onmouseout="this.style.backgroundColor='#d4e3e5';">
      <td>重选父版块</td>
      <td>
      <s:iterator value="#session.BoardList1" var="board">
         <input type="radio" name="board.id" value='${board.id}' /> ${board.name}
      </s:iterator>
      </td>
    </tr>
    <tr onmouseover="this.style.backgroundColor='#ffff66';" onmouseout="this.style.backgroundColor='#d4e3e5';" >
       <td><input type="submit" value="提交"/> </td>   
       <td><input type="reset" value="重置"/></td>
    </tr>
    </table>
</center>
    </form>
</body>
</html>
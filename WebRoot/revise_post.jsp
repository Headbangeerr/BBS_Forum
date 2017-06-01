<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@ page import="java.util.*"%> //获取系统时间必须导入的 
<%@ page import="java.text.*"%> //获取系统时间必须导入的 
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
<title>发帖</title>
<link rel="stylesheet" type="text/css" href="css/posts.css">
</head>
<body>
	<jsp:include page="pages/header.jsp"></jsp:include>
	<script type="text/javascript" src="<%=basePath%>js/posts.js" charset="UTF-8"></script>
	<div id="bbsdoc" class="bbsdoc">
	    <div class="clearfix"></div><!--兼容个别浏览器强制改变最小字号而导致的页面错乱-->
	    <div id="hd"></div>
	    <div id="bd" class="layout-lm clearfix">
	        <div id="main">
	        	<div class="location clearfix">
					<div class="text"><b>发帖</b></div>
					<div data-ads-order="01" class="text-side ads-loc-holder clearfix">
						<a href="#" target="_blank"></a>
					</div>
				</div>
				<div id="BBS_BLOCK">
				<input type="hidden" name="mailAddresss" value='<%=session.getAttribute("mailAddress")%>'>  
					<form id="postForm1">
						<table class="fatieArea">
							<colgroup>
								<col class="titleItem"/>
								<col class="item"/>
							</colgroup>
							<tr>
								<td>标题：</td>
								<td>
									<input  type="text" name="title" value="${post.title}" class="bbsTitle fl" maxlength="34"/>
									<span class="fr">
										<input type="hidden" name="uuid" value="2_21_1.0_ca40d36e-f4c0-4ca1-900c-76bdd4e51093">
										<select name="boardlist1">
							   				<option value="">${post.childboardId.name}</option>
							   			</select>-
							   			<select name="childboardId1">
							   				<option value="">${post.childboardId.parentBoard.name}</option>
							   			</select>
						   			</span>
					   			</td>
				   			</tr>
							
							<tr>
								<td valign="top">内容：</td>
								<td id="textAreaWrap">
									<div id="editorToolBar"></div>
									<textarea  id="textAreaContainer" name="content" class="textAreaContainer">${post.content}</textarea>
									<div class="ty_bbs_preview_area"></div>
								</td>
							</tr>
							<tr>
							<td ><!--标签：--></td>
							<td style="zoom:1;">
								<a onclick="update_post()" style="width: 175px;float: right;" class="common-submitBtn fr" id="send_message" >
									<i class="fa fa-pencil"></i>&nbsp;<span>发表(Ctrl+Enter)</span>
								</a>
								 <div class="alert1"></div>		
							</td>
							</tr>
						</table>
					</form>
				</div>		
	        </div>
	    </div>
	    <div id="ft"></div>
	</div>
</body>
</html>
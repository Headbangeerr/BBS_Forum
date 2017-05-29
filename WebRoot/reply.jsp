<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>post show</title>
<link rel="stylesheet" type="text/css" href="css/replay.css">
</head>
<script type="text/javascript" src="<%=basePath%>js/posts.js" charset="UTF-8"></script>
<body>
<jsp:include page="pages/header.jsp"></jsp:include>
	<div class="container user">
	    <div class="position clearfix"><h3>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;帖子详情</h3></div>
	    <div class="user-cont clearfix">
	    	<div class="col-md-8 user-right" >
	            <div class="user-right-n clearfix">
	                <div class="user-right-n clearfix tab-content">                                       
					   <div class="pet_article_like">
				        <div class="pet_content_main pet_article_like_delete">
				          <div data-am-widget="list_news" class="am-list-news am-list-news-default am-no-layout">
				            <div class="am-list-news-bd">
				              <ul class="am-list" style="width: 800px">  
					              <li class="am-g am-list-item-desced am-list-item-thumbed am-list-item-thumb-right pet_list_one_block" >
					              	<s:if test='#request.postBean.list.size()==0'>
						            	<h4>无人回帖。</h4>         
							         </s:if>
							         <s:else>	                    		                    	
								        <div class="pet_list_one_info">
								            <div class="pet_list_one_info_l">
								                <div class="pet_list_one_info_ico"><img src="${post.publisherMail.photoUrl}" alt=""></div>
								                <div class="pet_list_one_info_name">${post.publisherMail.username}   <%=session.getAttribute("dateString")%></div>
								            </div>
								            <div class="pet_list_one_info_r">
								                <div class="pet_list_tag pet_list_tag_xxs">主贴</div>
								            </div>
								        </div>
								        <div class=" am-u-sm-8 am-list-main pet_list_one_nr">
								            <h3 class="am-list-item-hd pet_list_one_bt"  style="font-style:oblique;font-weight:500;">${post.title}</h3>
								            <div class="am-list-item-text pet_list_one_text" '>${post.content }</div>
								
								        </div>                 	                     			                                   		                    									 
							         </s:else>
								  </li>
					              <s:if test='#request.replyBean.list.size()==0'>
						          	<h4>无人回帖。</h4>         
							      </s:if>
							      <s:else>	                    	
							        <s:iterator value="#request.replyBean.list" var="reply">
					                <!--缩略图在标题右边-->
					                 <li class="am-g am-list-item-desced am-list-item-thumbed am-list-item-thumb-right pet_list_one_block">
								        <div class="pet_list_one_info">
								            <div class="pet_list_one_info_l">
								                <div class="pet_list_one_info_ico"><img src="${reply.senderMail.photoUrl}" alt=""></div>
								                <div class="pet_list_one_info_name">${reply.senderMail.username}</div>
								            </div>
								            <div class="pet_list_one_info_r">
								                <div class="pet_list_tag pet_list_tag_xxs">回帖</div>
								            </div>
								        </div>
								        <div class=" am-u-sm-8 am-list-main pet_list_one_nr">
								            <div class="am-list-item-text pet_list_one_text">${reply.content}</div>
								        </div>
								      </li>
								 	</s:iterator>
					              </s:else>
					              <li class="am-g am-list-item-desced am-list-item-thumbed am-list-item-thumb-right pet_list_one_block">
								   	 <div class="user-right-n clearfix tab-content">
					               		<form id="replyForm">
											<table class="fatieArea">	
												<input type="hidden" name="publisherMail" value="<s:property value="#session.user.mailAddress"/>">				
												<tr>
													<td valign="top">回帖：</td>
													<td id="textAreaWrap" style="width: 700px">
														<textarea onfocus="this.value=''" id="textAreaContainer" name="content" class="textAreaContainer">请输入回帖内容</textarea>								
													</td>
												</tr>
												<tr>
												<td ><!--标签：--></td>
												<td style="zoom:1;">
													<a onclick="add_reply()" style="width: 150px;float: right;" class="common-submitBtn fr" id="send_message" >
														<i class="fa fa-pencil"></i>&nbsp;<span>回帖(Ctrl+Enter)</span>
													</a>
													 	<div class="alert1"></div>	
												</td>
												</tr>
											</table>
										</form>
					                 </div>
								  </li>   
				                </ul>
				              </div>
				            </div>
				          </div>
				        </div>
	        		 </div>
	            </div>
	        </div>        
	    
	    
	    
	    	<div class="col-md-3" style="position:fixed; top:50; right:0;width: 300px; height: 30px; ">
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
								<input value="评论该帖" type="button"  class="btn btn-success btn-outline btn-block" onclick="window.scroll(0,document.body.clientHeight)" />
				
								</div>
							</div>
							<hr>
	
						</div>
					</div>
				</div>
              </s:if> 
	       	</div>
	    </div>
    </div>
</body>
</html>
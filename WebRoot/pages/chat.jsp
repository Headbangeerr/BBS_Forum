<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
    <base href="<%=basePath%>">
    <script type="text/javascript" chatset="gbk" src="js/chat.js"></script>
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <link rel="stylesheet" href="css/style.min.css" />
	<link rel="stylesheet" href="css/base.css" />
    <title>我的私信</title>
  </head>
  <body>
  	
   	<div class="message_right" style="min-height: 600px;">
   			<div class="message-main">
    			<input type="hidden" name="userMail" value="${session.user.mailAddress}">
    			<ul class="message-main-lists">
    				<div class="whisper">
    					<div class="whisper_left">
					        <div class="chat_tab_wrapper">
					            <div class="chat_tab" style="color: #00a1d6;">近期会话</div>					            
					        </div>					       
        					<div class="chat_history_list" style="height: 505px;">  
        					      	<center> <img id="user_list_loading" src="img/loading_sm.gif"></center>					
        					</div>					          			
    					</div>
    					<div class="whisper_right" style="width: 550px;display: none;">
    						<div class="chat_room_title">
    							
    						</div>
							<div class="chat_room_content" style="height: 450px;width: 550px">
								<div class="more_chat_msg" onclick="pageup(this)" name="">查看以往消息<br>									
								</div>								
								
    						</div>
    						<div class="chat_msg_sender">    				
    							<form name="sendNews" action="sendNews" method="post">  
    								<input type="hidden" name="userMail" value="${session.user.mailAddress}">
    								<input type="hidden" name="friendMail" value="">      
								    <textarea id="chat_msg" name="content" class="" placeholder="回复一下吧～" maxlength="800"></textarea>    
								    <button id="send" onclick="send_news()" type="button" class="">发送(ctrl+enter)</button>
								</form>  
							</div>
    				</div>
    			</ul>       
   			</div>
		</div>
  </body>
</html>
<script type="text/javascript">  
$(document).ready(  
    function(){  
        document.onkeydown = function()  
        {  
            var oEvent = window.event;  
            if (oEvent.keyCode == 13 && oEvent.ctrlKey) {  //这里只能用alt，shift，ctrl等去组合其他键event.altKey、event.ctrlKey、event.shiftKey 属性
                $("#send").trigger("click"); 
            }  
        }  
    }  
);  
</script> 


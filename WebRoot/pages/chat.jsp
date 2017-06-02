<<<<<<< HEAD
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <link rel="stylesheet" href="css/style.min.css" />
	<link rel="stylesheet" href="css/base.css" />
    <title>我的私信</title>
  </head>
  <body>   
   	<div class="message_right" style="min-height: 600px;">
   			<div class="message-main">
    
    			<ul class="message-main-lists">
    				<div class="whisper">
    					<div class="whisper_left">
					        <div class="chat_tab_wrapper">
					            <div class="chat_tab" style="color: #00a1d6;">近期会话</div>
					        </div>					       
        					<div class="chat_history_list" style="height: 505px;">  
        					      						
        					</div>					          			
    					</div>
    					<div class="whisper_right" style="width: 550px;display: none;">
    						<div class="chat_room_title">
    							進擊の変態様
    						</div>
							<div class="chat_room_content" style="height: 400px;width: 550px">
								<div class="more_chat_msg"><i></i>查看以往消息</div>
								<div class="my_msg" data-ts="1495959080000" data-cursor="1495959080884159144">
	    							<div class="chat_msg_time">  
	   								     16:11:20 2017-05-28    
	    							</div>
								    <div class="chat_msg">
								        <a href="//space.bilibili.com/3205747" target="_blank"><img src="//i1.hdslb.com/bfs/face/c5fbd019f7887c7acf701368ad59853b8bfb7102.jpg"></a>
								        <i class="chat_bubble_arrow" style="top: 16px;"></i>
								        <div class="chat_bubble" style="margin-top: 6px;">hahaha</div>
								    </div>
								</div>
								<div class="other_msg" data-ts="1496131852000" data-cursor="1496131852665409754">
								    <div class="chat_msg_time" style="display: none;">
								        16:10:52 2017-05-30
								    </div>
								    <div class="chat_msg">
								        <a href="//space.bilibili.com/3205747" target="_blank"><img src="//i1.hdslb.com/bfs/face/c5fbd019f7887c7acf701368ad59853b8bfb7102.jpg"></a>
								        <i class="chat_bubble_arrow" style="top: 16px;"></i>
								        <div class="chat_bubble" style="margin-top: 6px;">hello</div>
								    </div>
								</div>
								<div class="my_msg" data-ts="1495959094000" data-cursor="1495959094760916155">
								    <div class="chat_msg_time" style="display: none;">
								        16:11:34 2017-05-28
								    </div>
								    <div class="chat_msg">
								        <a href="//space.bilibili.com/3205747" target="_blank"><img src="//i1.hdslb.com/bfs/face/c5fbd019f7887c7acf701368ad59853b8bfb7102.jpg"></a>
								        <i class="chat_bubble_arrow" style="top: 16px;"></i>
								        <div class="chat_bubble" style="margin-top: 6px;">234234</div>
								    </div>
								</div>																
								<div class="my_msg" data-ts="1495959101000" data-cursor="1495959101268311595">
								    <div class="chat_msg_time" style="display: none;">
								        16:11:41 2017-05-28
								    </div>
								    <div class="chat_msg">
								        <a href="//space.bilibili.com/3205747" target="_blank"><img src="//i1.hdslb.com/bfs/face/c5fbd019f7887c7acf701368ad59853b8bfb7102.jpg"></a>
								        <i class="chat_bubble_arrow" style="top: 16px;"></i>
								        <div class="chat_bubble" style="margin-top: 6px;">2342342</div>
								    </div>
								</div>
							</div>
							<div class="chat_msg_sender">    
							    <textarea id="chat_msg" class="" placeholder="回复一下吧～" maxlength="800"></textarea>    
							    <button id="send" class="">发送</button>
							</div>
    					</div>
    				</div>
    			</ul>       
   			</div>
		</div>
  </body>
  <script type="text/javascript">
  	$(function(){  		
  		 $.ajax({  
             url:"showLastestSender",  
             type:"POST",               
             dataType:"json",  
             success:function(data){
            	 var str;
            	 if(data.lastestUsers.length==0){
        			 alert("联系人为0");
        			 str="<center><h1>空空如也~</h1></center>";
        			 $(".chat_history_list").append(str);
        		 }
            	 else{
            		 $.each(data.lastestUsers,function(index,sender){
                		 alert(sender.unread)
                		 alert(sender.user.username)
                		 if(sender.length==0){
                			 alert("联系人为0")
                			 str="<center><h1>空空如也~</h1></center>";
                		 }else{
                			 str="<a class='rname_card'  href=''>"+
    							"<img src='"+sender.user.photoUrl+"'>"+
    							"<div class='name'>"+sender.user.username+"</div>"+
    							"<div class='last_msg'>2423423423423423434534545645634524534534534534534534</div>";
    						if(sender.unread=='0'){
    							str+="<div class='msg_num' style='display:none'>"+sender.unread+"</div>"+								
    							"</a>";	
    						}	
    						else{
    							str+="<div class='msg_num' >"+sender.unread+"</div>"+								
    							"</a>";	 
    						}
                		 }        			 
    					$(".chat_history_list").append(str);
                	 }) 
            	 }            	 
            }             
  		 })  		
  	})   
  </script>
</html>
=======
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <script type="text/javascript" src="js/chat.js"></script>
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <link rel="stylesheet" href="css/style.min.css" />
	<link rel="stylesheet" href="css/base.css" />
    <title>我的私信</title>
  </head>
  <body>
  	
   	<div class="message_right" style="min-height: 600px;">
   			<div class="message-main">
    
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
								<div class="more_chat_msg"><i></i>查看以往消息</div>	
    						</div>
    						<div class="chat_msg_sender">
    							<form id="sendNews" action="sendNews" method="post">  
    								<input type="hidden" name="receiverMail" value="${session.user.mailAddress}">
    								<input type="hidden" name="senderMail" value="">      
								    <textarea id="chat_msg" name="content" class="" placeholder="回复一下吧～" maxlength="800"></textarea>    
								    <button id="send" onclick="send_news()" type="button" class="">发送</button>
								</form>  
							</div>
    				</div>
    			</ul>       
   			</div>
		</div>
  </body>
</html>
>>>>>>> Headbangeerr/master

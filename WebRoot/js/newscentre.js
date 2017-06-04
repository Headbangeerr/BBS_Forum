function handleRequest(t){
	var operate=$(t).attr("name");
	var senderMail=$(t).prevAll("[name=senderMail]").val();
	var newsId=$(t).prevAll("[name=newsId]").val();
	if(operate=="yes"){
		operate=true;
	}else{
		operate=false;
	}
	var title=$(t).parents(".message-content-wrapper").prev().children("a");
	//$(t).parents(".message-content-wrapper").prev().children("a").text("成功啦")
	$.ajax({  
             url:"handleFriRequest?operate="+operate+"&senderMail="+senderMail+"&newsId="+newsId,  
             type:"POST",   
             dataType:"json",  
             success:function(data){
            	 if(data.flag){
            		 if(operate){
            			 $(t).parent("div").remove();
            			 title.text("您已经接受了该用户发送的好友请求！");
            		 }
            		 else{
            			 $(t).parent("div").remove();
            			 title.text("您已经拒绝了该用户发送的好友请求！");
            		 }
            	 }
            	 
             }
         })
}
function showNews(){	
	 $.ajax({  
         url:"showLastestSender",  
         type:"POST",               
         dataType:"json",  
         success:function(data){ 
        	 alert("调用了show")
        	 $("#user_list_loading").parent("center").remove();
        	 var str;
        	 if(data.lastestUsers.length==0){        			 
    			 str="<center><h1>空空如也~</h1></center>";
    			 $(".chat_history_list").append(str);
    		 }
        	 else{
        		 $.each(data.lastestUsers,function(index,sender){                	
            		 if(sender.length==0){                			 
            			 str="<center><h1>空空如也~</h1></center>";
            		 }else{
            			 str="<a class='rname_card' onclick='checkNews(this)'  name='"+sender.user.mailAddress+"'>"+
							"<img src='"+sender.user.photoUrl+"'>"+
							"<div class='name'>"+sender.user.username+"</div>";    							
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
            	 }); 
        	 }            	 
        }             
		 })
}
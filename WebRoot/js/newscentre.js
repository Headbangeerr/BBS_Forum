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
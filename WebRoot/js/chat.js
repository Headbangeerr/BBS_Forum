function checkNews(t){
	var senderMail=$(t).attr("name");
	var receiverMail=$("[name=receiverMail]").val();
	//alert("receiver"+receiverMail);
	$(".rname_card").removeClass("active");
	$(t).addClass("active");
	$(".whisper_right").show();
	$.ajax({  
        url:"showLastestNewsForReceiver?senderMail="+senderMail,  
        type:"POST",           
        dataType:"json",  
        success:function(data){
        	var list=data.newsBean.list;        	
        	var str;
        	$("[name=senderMail]").val(senderMail);
        	$(".other_msg").remove();
        	$(".my_msg").remove();
        	$.each(list,function(index,news){        		
        		str="";
        		$(".chat_room_title").text(news.senderMail.username);       		
        		if(news.senderMail==receiverMail){
        			str+="<div class='my_msg'>";
        		}
        		else{
        			str+="<div class='other_msg'>";
        		}
        		if(index==0){
        			str+="<div class='chat_msg_time'>";
        		}else{
        			str+="<div class='chat_msg_time' style='display:none' >";
        		}
        		str+=news.sendDate+    
	    				"</div>"+
						"<div class='chat_msg'>"+
						    "<a href='' target='_blank'><img src='"+news.senderMail.photoUrl+"'></a>"+
								 "<i class='chat_bubble_arrow' style='top: 16px;'></i>"+
								 "<div class='chat_bubble' style='margin-top: 6px;'>"+news.content+"</div>"+
						"</div>"
					"</div>";        		
	        	$('.chat_room_content').append(str);
        	})		
        }
    })
}
function send_news(){
	var form=$("sendNews");
	var params=form.serialize();
	 $.ajax({  
         url:"sendNews",  
         type:"POST",  
         data:params,  
         dataType:"json",  
         success:function(data){ 
         }
     })
}
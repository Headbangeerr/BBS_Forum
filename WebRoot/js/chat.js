function checkNews(t){
	var friendMail=$(t).attr("name");
	var userMail=$("[name=userMail]").val();	
	$(".rname_card").removeClass("active");
	$(t).addClass("active");
	$(".whisper_right").show();
	var friendName=$(t).children(".name").text();	
	$.ajax({  
        url:"showLastestNewsForReceiver?friendMail="+friendMail,  
        type:"POST",           
        dataType:"json",  
        success:function(data){
        	$.ajax({  
                url:"checkUnreadNews?friendMail="+friendMail+"&userMail="+userMail,  
                type:"POST",           
                dataType:"json",  
                success:function(data){
                	if(data.flag){                		
                		$(t).children(".msg_num").remove();
                	}        
                }
        	});	
        	var list=data.newsBean.list;        	
        	var str;
        	var currentPage=data.newsBean.currentPage;        	
        	$(".more_chat_msg").attr("name",currentPage)        	
        	$("[name=friendMail]").val(friendMail);
        	$(".other_msg").remove();
        	$(".my_msg").remove();
        	$(".chat_room_title").text(friendName);       	
        	$.each(list,function(index,news){        		
        		str="";        			        		
        		if(news.senderMail.mailAddress==userMail){        			
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
	var params=$("form[name=sendNews]").serialize();	
	var content=$("textarea[name=content]").val();
	var regu = "^[ ]+$";
	var re = new RegExp(regu);
	if(content.length==0||re.test(content)){
		alert("留言内容不能为空！");
	}else{
		 $.ajax({  
	         url:"sendNews",  
	         type:"POST",  
	         data:params,  
	         dataType:"json",  
	         success:function(data){
	        	var news=data.newsTemp;
	        	var str="<div class='my_msg'>"+
	        	"<div class='chat_msg_time' style='display:none' >"+
	        	"</div>"+
				"<div class='chat_msg'>"+
				    "<a href='' target='_blank'><img src='"+news.senderMail.photoUrl+"'></a>"+
						 "<i class='chat_bubble_arrow' style='top: 16px;'></i>"+
						 "<div class='chat_bubble' style='margin-top: 6px;'>"+news.content+"</div>"+
				"</div>"+
			"</div>"; 
	        	$(".chat_room_content").append(str);
	        	$("textarea[name=content]").val("");
	         }
	     })
	}
}
function pageup(t){
	var str="";
	var friendMail=$("input[name=friendMail]").val();
	var userMail=$("[name=userMail]").val();	
	var currentPage=$(t).attr("name");		
	$(t).html("<img alt='载入中' src='img/loading_sm.gif'>");
	if(currentPage==1){
		$(t).after("<div class='no_more'>没有更多消息了~</div>")
		$(t).remove();
		
	}else{
		$.ajax({  
	        url:"showLastestNewsForReceiver?friendMail="+friendMail+"&page="+(currentPage-1),  
	        type:"POST",           
	        dataType:"json",  
	        success:function(data){
	        	$(t).html("查看以往消息");
	        	$(t).attr("name",(data.newsBean.currentPage))
	        	var list=data.newsBean.list;  	        	
	        	$.each(list,function(index,news){   	               			        	
	        		if(news.senderMail.mailAddress==userMail){        			
	        			str+="<div class='my_msg'>";	        		
	        		}
	        		else{
	        			str+="<div class='other_msg'>";
	        		}
	        		if(index==0){
	        			str+="<div class='chat_msg_time'>";
	        		}else{
	        			str+="<div class='chat_msg_time' style='display:none'>";
	        		}
	        		str+=news.sendDate+    
		    				"</div>"+
							"<div class='chat_msg'>"+
							    "<a href='' target='_blank'><img src='"+news.senderMail.photoUrl+"'></a>"+
									 "<i class='chat_bubble_arrow' style='top: 16px;'></i>"+
									 "<div class='chat_bubble' style='margin-top: 6px;'>"+news.content+"</div>"+
							"</div>"+
						"</div>";    	        		
	        	});	        	
	        	$(".more_chat_msg").after(str);
	        }
	     })
	}
}
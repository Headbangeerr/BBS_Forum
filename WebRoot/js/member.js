function send_message(){	
	var content=$("textarea[name=content]").val();
	var regu = "^[ ]+$";
	var re = new RegExp(regu);
	var publisher=$("#publisherMail").val()
	if(publisher.length==0){
		alert("请登录以后再再留言！")
	}else if(content.length==0||re.test(content)){
		alert("留言内容不能为空！");
	}
	else{		
		var params = $("#messageForm").serialize();  
		 $.ajax({  
             url:"addMessage",  
             type:"POST",  
             data:params,  
             dataType:"json",  
             success:function(data){                	
               if(data.flag==true){            	            	               	            	 
            	   $('.alert').html('发布成功！').addClass('alert-success').show().delay(1500).fadeOut();            	  
            	   $("textarea[name=content]").val("");
            	   if($("#myCollection>.art-row").size()==4){
           			  var totalpage=$("#pagefoot>li").size()-2;
           			  var receiverMail=$("input[name=receiverMail]").val();
           			  $("#showLastPage").attr("name","showMessageByPage?page="+totalpage+"&receiverMail="+receiverMail);
           			  $("#showLastPage").trigger("click");
           			}else{
           			 var str;            	  
           			 var temp=data.messages[0];  
           			 var date=temp.publishDate.substring(0,10);
           			 date+=" "+temp.publishDate.substring(11,16);  
           			 str="<div class='art-row'>"	                           
                              +"<h4><a href='' class='title'>"+temp.content+"</a></h4>"+	                          
                               "<a href='http://localhost:8080/BBS_Forum/chaeckUserByUrl?mailAddress="+temp.publisherMail.mailAddress+"' class='author'>"+
                               "<i class='fa fa-user'></i>&nbsp;<span>"+temp.publisherMail.username+"</span></a> <a  class='time'>" +
                               "<i class='fa fa-clock-o'></i>&nbsp;<span>"+date+"</span></a>" 	                          
                          +"</div>";  	
           			}       	        
            	   if(data.messages.length==1){//如果返回后的留言列表的长度为1，则代表这是第一次添加留言
            		   //alert("这是第一次留言")
            		   $("span[name=nomessage]").remove();            		  
            		   $("#messageForm").before(str);
            	   }else{
            		   $("#myCollection>.art-row:first").before(str);
            	   }            	               	              	  	          
               }else{
            	   alert("发布失败！")
               }
             }
         });  
	}
}
function paging(t){
	var url=$(t).attr("name");	
	var receiverMail=$("input[name=receiverMail]").val();
	$.ajax({
		type:"post",
		url:url,
		dataType:"json",
        success:function(data){
        	var str;
        	var date;
        	$("#myCollection>.art-row").remove();
        	$("#pagefoot").remove();
        	$.each(data.pageBean.list,function(index,message){        		
        	   date=message.publishDate.substring(0,10);
     		   date+=" "+message.publishDate.substring(11,16);       		   
        		str="<div class='art-row'>"	                           
                    +"<h4><a  class='title'>"+message.content+"</a></h4>"+                          
                     "<a href='http://localhost:8080/BBS_Forum/chaeckUserByUrl?mailAddress="+message.publisherMail.mailAddress+"' class='author'>"+
                     "<i class='fa fa-user'></i>&nbsp;<span>"+message.publisherMail.username+"</span></a> <a  class='time'>" +
                     "<i class='fa fa-clock-o'></i>&nbsp;<span>"+date+"</span></a>" 	                          
                +"</div>";
        		if($("#messageForm").length>0){
        			$("#messageForm").before(str);
        		}else{
        			$("#myCollection").append(str);
        		}
        		
        	});
        	var pageBean=data.pageBean;
        	var currentPage=pageBean.currentPage;
        	var pre=currentPage-1;
        	var next=currentPage+1;       
        	str="<ul id='pagefoot' class='pager'>";
        	if(currentPage==1){
        		str+="<li class='disabled'><a>&laquo;</a></li>";        
        	}else{
        		str+="<li><a onclick='paging(this)'  href='javascript:void(0);' name='showMessageByPage?page="+pre+"&receiverMail="+receiverMail+"'>&laquo;</a></li>";        
        	}        		               
        	for(var i=1;i<pageBean.totalPage+1;i++){
        		if(i==currentPage){
        			str+="<li ><a >"+i+"</a></li>";
        		}
        		else{
        			str+="<li><a  href='javascript:void(0);' onclick='paging(this)' name='showMessageByPage?page="+i+"&receiverMail="+receiverMail+"'>"+i+"</a></li>";
        		}        		
        	}
        	if(currentPage==pageBean.totalPage){
        		str+="<li class='disabled'><a>&raquo;</a></li>"+		   
	             "</ul>";	        
        	}else{
        		str+="<li ><a onclick='paging(this)' href='javascript:void(0);' name='showMessageByPage?page="+next+"&receiverMail="+receiverMail+"'>&raquo;</a></li>"+		   
	             "</ul>";	 
        	}	        
        	if($("#messageForm").length>0){
    			$("#messageForm").before(str);
    		}else{
    			$("#myCollection").append(str);
    		}
        }
	});	
}
function pagingPost(t){
	var url=$(t).attr("name");	
	var publisherMail=$("#myArticle>[name=publisherMail]").val();
	$.ajax({
		type:"post",
		url:url,
		dataType:"json",
        success:function(data){
        	var str;
        	var date;
        	$("#myArticle>.art-row").remove();
        	$("#postpagefoot").remove();        	
        	$.each(data.pageBean.list,function(index,post){         
         	   date=post.publishTime.substring(0,10);
      		   date+=" "+post.publishTime.substring(11,16);        		 
         		str="<div class='art-row'>"	                           
                     +"<h4><a href='serchPost?pid="+post.id+"' class='title'>"+post.title+"</a></h4>"+
                     "<span class='label label-default'><a href='checkZiPostByUrl?cid="+post.childboardId.id+"'>"+post.childboardId.name+"</a></span>"+
                      "<a href='http://localhost:8080/BBS_Forum/chaeckUserByUrl?mailAddress="+post.publisherMail.mailAddress+"'class='author'>"+
                      "<i class='fa fa-user'></i>&nbsp;<span>"+post.publisherMail.username+"</span></a> <a  class='time'>" +
                      "<i class='fa fa-clock-o'></i>&nbsp;<span>"+date+"</span></a>" 	                          
                +"</div>";     
         		$("#myArticle").append(str);
         	});
        	var pageBean=data.pageBean;
        	var currentPage=pageBean.currentPage;
        	var pre=currentPage-1;
        	var next=currentPage+1;         
        	str="<ul id='postpagefoot' class='pager'>";
        	if(currentPage==1){
        		str+="<li class='disabled'><a>&laquo;</a></li>";        
        	}else{
        		str+="<li><a onclick='pagingPost(this)'  href='javascript:void(0);' name='showPostByPage?page="+pre+"&publisherMail="+publisherMail+"'>&laquo;</a></li>";        
        	}            	
        	for(var i=1;i<pageBean.totalPage+1;i++){
        		if(i==currentPage){
        			str+="<li ><a >"+i+"</a></li>";
        		}
        		else{
        			str+="<li><a  href='javascript:void(0);' onclick='pagingPost(this)' name='showPostByPage?page="+i+"&publisherMail="+publisherMail+"'>"+i+"</a></li>";
        		}        		
        	}
        	if(currentPage==pageBean.totalPage){
        		str+="<li class='disabled'><a>&raquo;</a></li>"+		   
	             "</ul>";	        
        	}else{
        		str+="<li ><a onclick='pagingPost(this)' href='javascript:void(0);' name='showPostByPage?page="+next+"&publisherMail="+publisherMail+"'>&raquo;</a></li>"+		   
	             "</ul>";	 
        	}	                
        	$("#myArticle>.art-row:last").after(str);           	
        	}	
        })
}
function pagingFriends(t){
	var url=$(t).attr("name");	
	var publisherMail=$("#myFriends>[name=userMail]").val();
	$.ajax({
		type:"post",
		url:url,
		dataType:"json",
        success:function(data){
        	var str;
        	$(".media").remove();
        	$("selection>hr").remove();
        	$("#friednpagefoot").remove();        	
        	$.each(data.pageBean.list,function(index,user){           		
         		str="<div class='media'>"+	                           
			             "<a class='pull-left' href='http://localhost:8080/BBS_Forum/chaeckUserByUrl?mailAddress="+user.mailAddress+"'>"+
			                     "<img class='media-object avatar avatar-sm' src='"+user.photoUrl+"' alt='"+user.username+"'>"+
			              "</a>"+
			              "<div class='comment' style='width: 450px;float: left'>"+ 
					         "<div class='comment-author h6 no-margin'>"+
					            "<a href='http://localhost:8080/BBS_Forum/chaeckUserByUrl?mailAddress="+user.mailAddress+"'>"+user.username+"</a>"+
					          "</div>"+
					          "<div class='comment-bt'>"+
					             "<span>"+user.signature+"</span>"+
					          "</div>"+                    
			               "</div>"+
			               " <div name='hoverbutton' style='float:right;display: none'>"+
			                  	"<a class='hoverbutton greenbutton'><i class='fa fa-envelope-o'></i> 私信</a>	"+			                      	  	
			                    "<br><a onclick='deleFriend(this)' name='"+user.mailAddress+"' class='hoverbutton redbutton'><i class='fa fa-user-times'></i> 删除</a>"+
			               "</div>"+
                      "</div><hr>";         		
         		$("selection").append(str);
         	});
        	var pageBean=data.pageBean;
        	var currentPage=pageBean.currentPage;
        	var pre=currentPage-1;
        	var next=currentPage+1;         
        	str="<ul id='friednpagefoot' class='pager'>";      
        	if(currentPage==1){
        		str+="<li class='disabled'><a>&laquo;</a></li>";        
        	}else{
        		str+="<li><a onclick='pagingFriends(this)'  href='javascript:void(0);' name='showFriendsList?page="+pre+"&userMail="+publisherMail+"'>&laquo;</a></li>";        
        	}            	
        	for(var i=1;i<pageBean.totalPage+1;i++){
        		if(i==currentPage){
        			str+="<li ><a >"+i+"</a></li>";
        		}
        		else{
        			str+="<li><a  href='javascript:void(0);' onclick='pagingFriends(this)' name='showFriendsList?page="+i+"&userMail="+publisherMail+"'>"+i+"</a></li>";
        		}        		
        	}
        	if(currentPage==pageBean.totalPage){
        		str+="<li class='disabled'><a>&raquo;</a></li>"+		   
	             "</ul>";	        
        	}else{
        		str+="<li ><a onclick='pagingFriends(this)' href='javascript:void(0);' name='showFriendsList?page="+next+"&userMail="+publisherMail+"'>&raquo;</a></li>"+		   
	             "</ul>";	 
        	}	       
        	$("selection").after(str);           	
        	$(".media").on("mouseenter",function(){        		
        		$(this).children("div[name=hoverbutton]").show();
        	});
        	$(".media").on("mouseleave",function(){        	
        		$(this).children("div[name=hoverbutton]").hide();
        	});
        	}
	
        })
}
function pagingUser(t){
	var url=$(t).attr("name");	
	var publisherMail=$("#myFriends>[name=userMail]").val();
	$.ajax({
		type:"post",
		url:url,
		dataType:"json",
        success:function(data){
        	var str;
        	$("#Userss>.media").remove();
        	$("selection>hr").remove();
        	$("#friednpagefoot").remove();        	
        	$.each(data.pageBean.list,function(index,user){           		
         		str="<div class='media'>"+	                           
			             "<a class='pull-left' href='http://localhost:8080/BBS_Forum/chaeckUserByUrl?mailAddress="+user.mailAddress+"'>"+
			                     "<img class='media-object avatar avatar-sm' src='"+user.photoUrl+"' alt='"+user.username+"'>"+
			              "</a>"+
			              "<div class='comment' style='width: 450px;float: left'>"+ 
					         "<div class='comment-author h6 no-margin'>"+
					            "<a href='http://localhost:8080/BBS_Forum/chaeckUserByUrl?mailAddress="+user.mailAddress+"'>"+user.username+"</a>"+
					          "</div>"+
					          "<div class='comment-bt'>"+
					             "<span>"+user.signature+"</span>"+
					          "</div>"+                    
			               "</div>"+
			               " <div name='hoverbutton' style='float:right;display: none'>"+
			                  	"<a class='hoverbutton greenbutton'><i class='fa fa-envelope-o'></i> 私信</a>	"+			                      	  	
			                    "<br><a onclick='deleFriend(this)' name='"+user.mailAddress+"' class='hoverbutton redbutton'><i class='fa fa-user-times'></i> 删除</a>"+
			               "</div>"+
                      "</div><hr>";         		
         		$("#Userss").append(str);
         	});
        	var pageBean=data.pageBean;
        	var currentPage=pageBean.currentPage;
        	var pre=currentPage-1;
        	var next=currentPage+1;         
        	str="<ul id='friednpagefoot' class='pager'>";      
        	if(currentPage==1){
        		str+="<li class='disabled'><a>&laquo;</a></li>";        
        	}else{
        		str+="<li><a onclick='pagingUser(this)'  href='javascript:void(0);' name='showAllUser?page="+pre+"'>&laquo;</a></li>";        
        	}            	
        	for(var i=1;i<pageBean.totalPage+1;i++){
        		if(i==currentPage){
        			str+="<li ><a >"+i+"</a></li>";
        		}
        		else{
        			str+="<li><a  href='javascript:void(0);' onclick='pagingUser(this)' name='showAllUser?page="+i+"'>"+i+"</a></li>";
        		}        		
        	}
        	if(currentPage==pageBean.totalPage){
        		str+="<li class='disabled'><a>&raquo;</a></li>"+		   
	             "</ul>";	        
        	}else{
        		str+="<li ><a onclick='pagingUser(this)' href='javascript:void(0);' name='showAllUser?page="+next+"'>&raquo;</a></li>"+		   
	             "</ul>";	 
        	}	       
        	$("#Userss>.media:last").after(str);           	
        	$(".media").on("mouseenter",function(){        		
        		$(this).children("div[name=hoverbutton]").show();
        	});
        	$(".media").on("mouseleave",function(){        	
        		$(this).children("div[name=hoverbutton]").hide();
        	});
        	}
	
        })
}
function checkLogin(t){
	var user=$("input[name=userMail]").val();
	var friend=$("input[name=friendMail]").val();
	if(user==''){
		alert("请登录以后再进行此项操作！")
	}else{
		
	}
}
function deleFriend(t){
	var friendMail=$(t).attr("name");
	var userMail=$("input[name=userMail]").val();
	if(confirm("确认将此用户从好友列表中移除吗？")){		
		 $.ajax({  
             url:"deleFriends?userMail="+userMail+"&friendMail="+friendMail,  
             type:"POST",            
             dataType:"json",  
             success:function(data){
            	if(data.flag==true){
            		$(t).parents(".media").fadeOut();
            		$(t).parents(".media").next("hr").fadeOut();
            		$("#currentPage").trigger("click");            
            		if(data.friendSum==0){
            			$("#friednpagefoot").remove();
            			$("#myFriends>selection").remove();
            			$("#myFriends").append("<h4>未添加任何好友</h4>")
            		}
            	}
             }
             })
	}
}

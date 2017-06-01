function downQX(){
	var content=$("textarea[name=content]").val();
	var publisherMail=$("input[name=publisherMail]").val();

		var params = $("#qxForm").serialize();  
		 $.ajax({  
            url:"downQX",  
            type:"POST",  
            data:params,  
            dataType:"json",  
            success:function(data){    
           	 //alert(data.flag);
              if(data.flag==true){            	            	               	            	 
           	   $('.alert1').html('已降为用户').addClass('alert1-success').show().delay(1500).fadeOut();
           	   var t = setTimeout(function(){window.location.reload();},1550);
              }else{
           	   $('.alert1').html('回帖失败,请输入回帖内容！').addClass('alert1-false').show().delay(1500).fadeOut();  
              }
            }
       });  
	
}
function upQX(){
	var content=$("textarea[name=content]").val();
	var publisherMail=$("input[name=publisherMail]").val();

		var params = $("#qxForm").serialize();  
		 $.ajax({  
            url:"upQX",  
            type:"POST",  
            data:params,  
            dataType:"json",  
            success:function(data){    
           	 //alert(data.flag);
              if(data.flag==true){            	            	               	            	 
           	   $('.alert1').html('已升至管理员').addClass('alert1-success').show().delay(1500).fadeOut();
           	   var t = setTimeout(function(){window.location.reload();},1550);
              }else{
           	   $('.alert1').html('回帖失败,请输入回帖内容！').addClass('alert1-false').show().delay(1500).fadeOut();  
              }
        }
	});  
		 
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


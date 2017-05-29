function add_reply(){
	var publisherMail=$("input[name=publisherMail]").val();
	if(publisherMail.length==0){
		alert("请登陆后回复")
	}else{
		var params = $("#replyForm").serialize();  
		 $.ajax({  
            url:"addReply",  
            type:"POST",  
            data:params,  
            dataType:"json",  
            success:function(data){    
           	 //alert(data.flag);
              if(data.flag==true){            	            	               	            	 
           	   $('.alert').html('回帖成功').addClass('alert-success').show().delay(1500).fadeOut();            	  
              }else{
           	   $('.alert').html('回帖失败,请输入回帖内容！').addClass('alert-success').show().delay(1500).fadeOut();  
              }
            }
        });  
	}
}
function add_post(){	
	var content=$("textarea[name=content]").val();
	var time=$("input[name=publishTime]").val();
	var ip=$("select[name=childboardlist]").val();
	//alert(time);	
		var params = $("#postForm").serialize();  
		 $.ajax({  
             url:"addPost",  
             type:"POST",  
             data:params,  
             dataType:"json",  
             success:function(data){    
            	 //alert(data.flag);
               if(data.flag==true){            	            	               	            	 
            	   $('.alert').html('发布成功').addClass('alert-success').show().delay(1500).fadeOut();            	  
               }else{
            	   $('.alert').html('发布失败,请补全信息！').addClass('alert-success').show().delay(1500).fadeOut();  
               }
             }
         });  
}
function pagingPost(t){
	var bid=$("input[name=bids]").val();
	var url=$(t).attr("name");	
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
        	//alert(post.id)
         	   date=post.publishTime.substring(0,10);
      		   date+=" "+post.publishTime.substring(11,16);        		 
         		str="<div class='art-row'>"	                           
                     +"<h4><a href='serchPost?pid="+post.id+"' class='title'>"+post.title+"</a></h4>"+
                     "<span class='label label-default'><a href=''>"+post.childboardId.name+"</a></span>"+
                      "<a href='http://localhost:8080/BBS_Forum/chaeckUserByUrl?mailAddress="+post.publisherMail.mailAddress+"'class='author'>"+
                      "<i class='fa fa-user'></i>&nbsp;<span>"+post.publisherMail.username+"</span></a> <a  class='time'>" +
                      "<i class='fa fa-clock-o'></i>&nbsp;<span>"+date+"  View:"+post.pageView+"</span></a>" 	                          
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
        		str+="<li><a onclick='pagingPost(this)'  href='javascript:void(0);' name='showChoosePostByPage?page="+pre+"&bid="+bid+"'>&laquo;</a></li>";        
        	}            	
        	for(var i=1;i<pageBean.totalPage+1;i++){
        		if(i==currentPage){
        			str+="<li ><a >"+i+"</a></li>";
        		}
        		else{
        			str+="<li><a  href='javascript:void(0);' onclick='pagingPost(this)' name='showChoosePostByPage?page="+i+"&bid="+bid+"'>"+i+"</a></li>";
        		}        		
        	}
        	if(currentPage==pageBean.totalPage){
        		str+="<li class='disabled'><a>&raquo;</a></li>"+		   
	             "</ul>";	        
        	}else{
        		str+="<li ><a onclick='pagingPost(this)' href='javascript:void(0);' name='showChoosePostByPage?page="+next+"&bid="+bid+"'>&raquo;</a></li>"+		   
	             "</ul>";	 
        	}	                
        	$("#myArticle>.art-row:last").after(str);           	
        	}	
        })
}

function pagingReply(t){
	var bid=$("input[name=bids]").val();
	var url=$(t).attr("name");	
	$.ajax({
		type:"post",
		url:url,
		dataType:"json",
        success:function(data){
        	var str;
        	var date;
        	//alert(data.pageBean.list)
        	$("#myArticle>.art-row").remove();
        	$("#postpagefoot").remove();        	
        	$.each(data.pageBean.list,function(index,reply){    
        	//alert(post.id)
          	   date=reply.sendtime.substring(0,10);
       		   date+=" "+reply.sendtime.substring(11,16);	 
          		str="<div  class='art-row'>"	                           
                      +"<h4><a href='serchPost?pid="+reply.id+"' class='title'>"+reply.content+"</a></h4>"+       
                       "<a href='http://localhost:8080/BBS_Forum/chaeckUserByUrl?mailAddress="+reply.senderMail.mailAddress+"'class='author'>"+
                       "<i class='fa fa-user'></i>&nbsp;<span>"+reply.senderMail.username+"</span></a> <a  class='time'>" +
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
        		str+="<li><a onclick='pagingReply(this)'  href='javascript:void(0);' name='showReplyByPage?page="+pre+"'>&laquo;</a></li>";        
        	}            	
        	for(var i=1;i<pageBean.totalPage+1;i++){
        		if(i==currentPage){
        			str+="<li ><a >"+i+"</a></li>";
        		}
        		else{
        			str+="<li><a  href='javascript:void(0);' onclick='pagingReply(this)' name='showReplyByPage?page="+i+"'>"+i+"</a></li>";
        		}        		
        	}
        	if(currentPage==pageBean.totalPage){
        		str+="<li class='disabled'><a>&raquo;</a></li>"+		   
	             "</ul>";	        
        	}else{
        		str+="<li ><a onclick='pagingReply(this)' href='javascript:void(0);' name='showReplyByPage?page="+next+"'>&raquo;</a></li>"+		   
	             "</ul>";	 
        	}	                
        	$("#myArticle>.art-row:last").after(str);           	
        	}	
        })
}

$(function(){
	$.ajax({//获取搜索框中的板块列表
		type:"post",
		url:"getBoardList",
		dataType:"json",
	    success:function(data){
	           	$.each(data.boardList,function(idnex,board){        
	    			var str;
	    			str="<option value='"+board.id+"'>";
	    			str+=board.name;
	    			str+="</option>"
	    			$("*[name='boardlist']").append(str);
	    			
	    	});
	    }
	});	
	//搜索框中的下拉菜单ajax获取子版块列表
	$("select[name='boardlist']").change(function(){
		$("select[name='childboardId'] option").not(":first").remove();
		var parentBoardId=$(this).val();
		$.ajax({//获取搜索框中的板块列表
			type:"post",
			url:"getChildBoardListByParentBoardId?parentboardId="+parentBoardId,
			dataType:"json",
	        success:function(data){
	               	$.each(data.childBoardList,function(idnex,board){   
	               		//alert(board.name)
	        			var str;
	        			str="<option value='"+board.id+"'>";
	        			str+=board.name;
	        			str+="</option>"
	        			$("select[name='childboardId']").append(str);
	        			
	        	});
	        }
		});	
	})
})

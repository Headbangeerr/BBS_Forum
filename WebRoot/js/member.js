function send_message(){	
	var content=$("textarea[name=content]").val();
	var regu = "^[ ]+$";
	var re = new RegExp(regu);
	var publisher=$("input[name=publisherMail]").val()
	if(content.length==0||re.test(content)){
		alert("留言内容不能为空！");
	}else if(publisher.length==0){
		alert("请登录以后再再留言！")
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
	$.ajax({//获取搜索框中的板块列表
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
                    +"<h4><a href='' class='title'>"+message.content+"</a></h4>"+                          
                     "<a href='http://localhost:8080/BBS_Forum/chaeckUserByUrl?mailAddress="+message.publisherMail.mailAddress+"' class='author'>"+
                     "<i class='fa fa-user'></i>&nbsp;<span>"+message.publisherMail.username+"</span></a> <a  class='time'>" +
                     "<i class='fa fa-clock-o'></i>&nbsp;<span>"+date+"</span></a>" 	                          
                +"</div>";     
        		$("#messageForm").before(str);
        	});
        	var pageBean=data.pageBean;
        	var currentPage=pageBean.currentPage;
        	var pre=currentPage-1;
        	var next=currentPage+1;       
        	str="<ul id='pagefoot' class='pager'>";
        	if(currentPage==1){
        		str+="<li class='disabled'><a onclick='paging(this)'  href='javascript:void(0);' name='showMessageByPage?page="+pre+"'>&laquo;</a></li>";        
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
        		str+="<li class='disabled'><a onclick='paging(this)' href='javascript:void(0);' name='showMessageByPage?page="+next+"&receiverMail="+receiverMail+"'>&raquo;</a></li>"+		   
	             "</ul>";	        
        	}else{
        		str+="<li ><a onclick='paging(this)' href='javascript:void(0);' name='showMessageByPage?page="+next+"&receiverMail="+receiverMail+"'>&raquo;</a></li>"+		   
	             "</ul>";	 
        	}
	        
	        $("#messageForm").before(str);
        }
	});	
	
}
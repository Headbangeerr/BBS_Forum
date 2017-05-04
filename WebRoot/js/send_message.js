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
		alert("成功发布")
		var params = $("#messageForm").serialize();  
		 $.ajax({  
             url:"addMessage",  
             type:"POST",  
             data:params,  
             dataType:"json",  
             success:function(data){  
               alert(data);
               }
         });  
	}
}
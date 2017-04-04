function showPostList(){
	$.ajax({
		type:"post",
		url:"showLastestPostOnIndexPage",
		dataType:"json",
        success:function(data){
               	$.each(data,function(index,post){        
        			alert(post.title)
        	});
        }
	});	
}
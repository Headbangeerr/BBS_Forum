function showPostList(){
	$.ajax({
		type:"post",
		url:"showLastestPostOnIndexPage",
		dataType:"json",
        success:function(data){
               	$.each(data.lastestPostList,function(index,post){        
        			alert(post.title)
        	});
        }
	});	
}
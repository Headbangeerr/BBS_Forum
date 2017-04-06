function showPostList(){//页面载入时通过ajax获取相应数据
	$.ajax({//获取最新帖子列表
		type:"post",
		url:"showLastestPostOnIndexPage",
		dataType:"json",
        success:function(data){
               	$.each(data.lastestPostList,function(index,post){  
               		var str;
               		str="<div class='media'>"+
							"<a class='pull-left' href='#'>"+
								"<img class='media-object avatar avatar-sm' src='"+post.publisherMail.photoUrl+"' alt='sjswc'>"+
							"</a>"+
							"<div class='comment'>"+
								"<div class='comment-author h6 no-margin'>"+
									"<div class='comment-meta small'>"+
										"<a class='badge-comment'>"+post.pageView+"</a>"+
									"</div>"+
									"<a href='#'>"+post.title+"</a>"+
								"</div>"+
								"<div class='comment-bt'>"+
									"<span class='label label-default'><a href='#'>"+post.childboardId.name+"</a></span> &nbsp;•&nbsp;"+
									"<strong><a href='#'>"+post.publisherMail.username+"</a></strong>"+
									 "&nbsp;•&nbsp; <span>"+post.publishTime+"</span>"+
								"</div>"+
							"</div>"+
						"</div><hr>";
               		
               		$("#LastestPostList").append(str);
        	});
        }
	});
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
        			$("*[name='search_boardlist']").append(str);
        			
        	});
        }
	});	
}
$(function(){
	$.ajax({//获取最新帖子列表
		type:"post",
		url:"showLastestPostOnIndexPage",
		dataType:"json",
        success:function(data){
               	$.each(data.lastestPostList,function(index,post){  
               		var str;
               		str="<div class='media'>"+
							"<a class='pull-left' href='http://localhost:8080/BBS_Forum/chaeckUserByUrl?mailAddress="+post.publisherMail.mailAddress+"'>"+
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
									"<strong><a href='http://localhost:8080/BBS_Forum/chaeckUserByUrl?mailAddress="+post.publisherMail.mailAddress+"'>"+post.publisherMail.username+"</a></strong>"+
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
	$(".bodys p").not(":first").hide();
	$(".searchbox ul li").click(function(){
		var index = $(this).index();
		if(index==0){
			$(this).find("a").addClass("style1");
			$(".searchbox ul li").eq(1).find("a").removeClass("style2");
			$(".searchbox ul li").eq(2).find("a").removeClass("style3");
		}
		if(index==1){
			$(this).find("a").addClass("style2");
			$(".searchbox ul li").eq(0).find("a").removeClass("style1");
			$(".searchbox ul li").eq(2).find("a").removeClass("style3");
		}
		if(index==2){
			$(this).find("a").addClass("style3");
			$(".searchbox ul li").eq(0).find("a").removeClass("style1");
			$(".searchbox ul li").eq(1).find("a").removeClass("style2");
		}
		var index=$(this).index();
		$(".bodys p").eq(index).show().siblings().hide();
	});
	
	$(".bodys a").hover(function () {
		$(this).stop().animate({
			opacity: '1'
		}, 600);
	}, function () {
		$(this).stop().animate({
			opacity: '0.6'
		}, 1000);
	}).on('click', function () {
		$("body").append("<div id='mask'></div>");
		$("#mask").addClass("mask").fadeIn("slow");
		$("#detailedSearch").fadeIn("slow");
	});
	
	
	$(".close_btn").hover(function () { $(this).css({ color: 'black' }) }, function () { $(this).css({ color: '#999' }) }).on('click', function () {
		$("#detailedSearch").fadeOut("fast");
		$("#detailedSearch1").fadeOut("fast");
		
		$("#mask").css({ display: 'none' });
		$(".error_cuo").hide();
		iniForm();
	});
	
	//搜索框中的下拉菜单ajax获取子版块列表
	$("select[name='search_boardlist']").change(function(){
		$("select[name='search_childboardlist'] option").not(":first").remove();
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
	        			$("select[name='search_childboardlist']").append(str);
	        			
	        	});
	        }
		});	
	})
	
	$("#search_submit").hover(function () {
		$(this).stop().animate({
			opacity: '1'
		}, 500);
	}, function () {
		$(this).stop().animate({
			opacity: '0.5'
		}, 500);
	});
	$("#search_submit1").hover(function () {
		$(this).stop().animate({
			opacity: '1'
		}, 500);
	}, function () {
		$(this).stop().animate({
			opacity: '0.5'
		}, 500);
	});
	
	$("#search_submit").click(function(){
		$(".error_cuo").hide();
		if($("*[name='search_boardlist']").val()==0||$("select[name='search_childboardlist']").val()==0){
			$("#boardlist_error1").show();
		}
		if($("input[name='search_keyword1']").val()==""){
			$("#boardlist_error2").show();
		}
		
	})
	$("#search_submit1").click(function(){
		$(".error_cuo").hide();
		if($("*[name='search_username']").val()==""){
			$("#boardlist_error3").show();
		}
		if($("input[name='search_keyword2']").val()==""){
			$("#boardlist_error4").show();
		}
		
	})
	
	
	function iniForm(){
		$("*[name='searchByBoard']")[0].reset();
		$("*[name='searchByUser']")[0].reset();
	}
	
	$("#changto_user").click(function(){
		$("#detailedSearch").fadeOut("slow");
		$("#detailedSearch1").fadeIn("slow");
	})
	$("#changeto_board").click(function(){
		$("#detailedSearch1").fadeOut("slow");
		$("#detailedSearch").fadeIn("slow");
	})
	
});
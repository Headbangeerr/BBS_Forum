$(function(){
	$.ajax({//获取最新帖子列表
		type:"post",
		url:"showLastestPostOnIndexPage",
		dataType:"json",
        success:function(data){
               	$.each(data.lastestPostList,function(index,post){  
               		var str;
               		//alert(post.id)
               		str="<div class='media'>"+
							"<a class='pull-left' href='http://localhost:8080/BBS_Forum/chaeckUserByUrl?mailAddress="+post.publisherMail.mailAddress+"'>"+
								"<img class='media-object avatar avatar-sm' src='"+post.publisherMail.photoUrl+"' alt='sjswc'>"+
							"</a>"+
							"<div class='comment'>"+
								"<div class='comment-author h6 no-margin'>"+
									"<div class='comment-meta small'>"+
										"<a class='badge-comment'>"+post.pageView+"</a>"+
									"</div>"+
									"<a href='serchPost?pid="+post.id+"'>"+post.title+"</a>"+
								"</div>"+
								"<div class='comment-bt'>"+
									"<span class='label label-default'><a href='checkZiPostByUrl?cid="+post.childboardId.id+"'>"+post.childboardId.name+"</a></span> &nbsp;•&nbsp;"+
									"<strong><a href='http://localhost:8080/BBS_Forum/chaeckUserByUrl?mailAddress="+post.publisherMail.mailAddress+"'>"+post.publisherMail.username+"</a></strong>"+
									 "&nbsp;•&nbsp; <span>"+post.publishTime+"</span>"+
								"</div>"+
							"</div>"+
						"</div><hr>";
               		
               		$("#LastestPostList").append(str);
               		$("#loading").remove();
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
		$("input[name='searchFlag']").val(3);
		$(".error_cuo").hide();
		var regu = "^[ ]+$";
		var re = new RegExp(regu);
		if($("*[name='search_boardlist']").val()==0||$("select[name='search_childboardlist']").val()==0){
			$("#boardlist_error1").show();
		}
		else if($("input[name='search_keyword1']").val()==""||re.test($("input[name='search_keyword1']").val())){
			$("#boardlist_error2").show();
		}else//输入无误通过板块名搜索帖子
		{
			$("#searchByBoard").submit();
			iniForm();
		}
		
	})
	$("#search_submit1").click(function(){
		$("input[name='searchFlag']").val(4);
		$(".error_cuo").hide();
		var regu = "^[ ]+$";
		var re = new RegExp(regu);
		if($("*[name='search_username']").val()==""||re.test($("*[name='search_username']").val())){
			$("#boardlist_error3").show();
		}
		else if($("input[name='search_keyword2']").val()==""||re.test($("*[name='search_username']").val()))
		{
			$("#boardlist_error4").show();
		}else//输入无误开始通过用户名搜索帖子
		{
			$("#searchByUser").submit();
			iniForm();
		}
		
	})
	
	function iniForm(){
		$("#searchByUser")[0].reset();		
		$("#searchByBoard")[0].reset();
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

function submitSearch(t){		
	var keyword=$("input[name='keyword']").val();
	var username=$("input[name='username']").val();
	var regu = "^[ ]+$";
	var re = new RegExp(regu);
	if($(t).attr("class")=="one1"){
		$("input[name='searchFlag']").val(1);
		if(keyword.length==0||re.test(keyword)){
			alert("请输入关键字！")
		}else{		
			$("#commonSearch").submit();
		}
	}else{
		$("input[name='searchFlag']").val(2);
		if(username.length==0||re.test(username)){
			alert("请输入用户名！")
		}else{
			$("#commonSearch").submit();
		}
	}
	$("input[name='keyword']").val("");
	$("input[name='username']").val("");
}

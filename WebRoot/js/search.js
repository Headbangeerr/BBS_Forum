$(function(){
	$(document).ready(function(){
		$.ajax({
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
	})
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
	//点击高级搜索跳出窗体
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
	//关闭摁钮
	$(".close_btn").hover(function () { $(this).css({ color: 'black' }) }, function () { $(this).css({ color: '#999' }) }).on('click', function () {
		$("#detailedSearch").fadeOut("fast");
		$("#detailedSearch1").fadeOut("fast");
		
		$("#mask").css({ display: 'none' });
		$(".error_cuo").hide();
		iniForm();
	});
	//搜索摁钮的动画显示
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
	//提交搜索表单验证
	$("#search_submit").click(function(){
		$(".error_cuo").hide();
		if($("*[name='search_boardlist']").val()==0){
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
	
	//初始化两个搜索表单
	function iniForm(){
		$("*[name='searchByBoard']")[0].reset();
		$("*[name='searchByUser']")[0].reset();
	}
	//两个悬浮层的交替动画实现
	$("#changto_user").click(function(){
		$("#detailedSearch").fadeOut("slow");
		$("#detailedSearch1").fadeIn("slow");
	})
	$("#changeto_board").click(function(){
		$("#detailedSearch1").fadeOut("slow");
		$("#detailedSearch").fadeIn("slow");
	})
	
});
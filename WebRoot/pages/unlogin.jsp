<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <style>
			
			* {
			  -webkit-box-sizing: border-box;
			  -moz-box-sizing: border-box;
			  box-sizing: border-box;
			}			
			body {
			  background-color: #ddd;
			  font-family: 'Source Sans Pro';
			  font-weight: 300;
			}			
			.Message {
			  display: table;
			  position: relative;
			  margin: 40px auto 0;
			  width: 500px;
			  background-color: #0074d9;
			  color: #fff;
			  -webkit-transition: all 0.2s ease;
			  transition: all 0.2s ease;
			}
			.Message.is-hidden {
			  opacity: 0;
			  height: 0;
			  font-size: 0;
			  padding: 0;
			  margin: 0 auto;
			  display: block;
			}
			
			.Message--orange {
			  background-color: #f39c12;
			}
			
			.Message--red {
			  background-color: #ff4136;
			}
			
			.Message--green {
			  background-color: #2ecc40;
			}
			
			.Message-icon {
			  display: table-cell;
			  vertical-align: middle;
			  width: 60px;
			  padding: 30px;
			  text-align: center;
			  background-color: rgba(0, 0, 0, 0.25);
			}
			.Message-icon > i {
			  width: 20px;
			  font-size: 20px;
			}
			
			.Message-body {
			  display: table-cell;
			  vertical-align: middle;
			  padding: 30px 20px 30px 10px;
			}
			.Message-body > p {
			  line-height: 1.2;
			  margin-top: 6px;
			}
			
			.Message-button {
			  position: relative;
			  margin: 15px 5px -10px;
			  background-color: rgba(0, 0, 0, 0.25);
			  -webkit-box-shadow: 0 3px rgba(0, 0, 0, 0.4);
			  box-shadow: 0 3px rgba(0, 0, 0, 0.4);
			  border: none;
			  padding: 10px 15px;
			  font-size: 16px;
			  font-family: 'Source Sans Pro';
			  color: #fff;
			  outline: none;
			  cursor: pointer;
			}
			.Message-button:hover {
			  background: rgba(0, 0, 0, 0.3);
			}
			.Message-button:active {
			  background: rgba(0, 0, 0, 0.3);
			  -webkit-box-shadow: 0 0px rgba(0, 0, 0, 0.4);
			  box-shadow: 0 0px rgba(0, 0, 0, 0.4);
			  top: 3px;
			}
			
			.Message-close {
			  position: absolute;
			  background-color: rgba(0, 0, 0, 0.3);
			  color: #fff;
			  border: none;
			  outline: none;
			  font-size: 20px;
			  right: 5px;
			  top: 5px;
			  opacity: 0;
			  cursor: pointer;
			}
			.Message:hover .Message-close {
			  opacity: 1;
			}
			.Message-close:hover {
			  background-color: rgba(0, 0, 0, 0.5);
			}
			
			.u-italic {
			  font-style: italic;
			}

		</style>
    <base href="<%=basePath%>">    
    
    <title>My JSP 'unlogin.jsp' starting page</title>
  </head>
  <body>
  	<div class="Message Message--red">
	  <div class="Message-icon">
	    <i class="fa fa-times"></i>
	  </div>
	  <div class="Message-body">
	    <p>您还没有登陆，请登录后再进行此操作！</p>
	    <p><span id="mes">5</span>秒后自动跳转 </p>
	    <button class="Message-button" onclick="jump()" id="js-helpMe">去登陆</button>	   
	  </div>	  
	</div>
    
  </body>
</html>
<script>
	function jump()
	{
		window.location.href = "<%=basePath%>login.jsp"; 
	}
var i = 5;
	var intervalid; 
	intervalid = setInterval("fun()", 1000); 
	function fun() { 
	if (i == 0) { 
	window.location.href = "<%=basePath%>login.jsp"; 
	clearInterval(intervalid); 
	} 
	document.getElementById("mes").innerHTML = i; 
	i--; 
	}
</script>
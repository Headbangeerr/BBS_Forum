package com.bbsforum.action;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.bbsforum.biz.UserBiz;
import com.bbsforum.entity.User;
import com.opensymphony.xwork2.ActionSupport;

public class UserAction extends BaseAction {
private static Logger logger=Logger.getLogger(UserAction.class);
	private String mailAddress;
	private String password;
	private int errorFlag;//登录时用于标记错误信息，为0时表示用户不存在，为2时表示密码错误
	
	
	public int getErrorFlag() {
		return errorFlag;
	}
	public void setErrorFlag(int errorFlag) {
		this.errorFlag = errorFlag;
	}
	public String getMailAddress() {
		return mailAddress;
	}
	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	
	@Autowired
	UserBiz userBiz;
	public void setUserBiz(UserBiz userBiz) {
		this.userBiz = userBiz;
	}
	
	@Action(value="login",results={
			@Result(name="success",location="/index.jsp"),
			@Result(name="login",location="/login.jsp")
	})
	public String login(){//登陆
		logger.info("login……   useraddress:"+mailAddress);
		User user=userBiz.getUserByMailAddress(mailAddress);
		if(user==null){
			errorFlag=0;
			return LOGIN;
		}else{
			if(user.getPassword().endsWith(password)){
				getSession().put("user", user);
				return SUCCESS;
			}else{
				errorFlag=2;
				return LOGIN;
			}
		}
	}
	
	@Action(value="logout",results={
			@Result(name="success",location="/index.jsp")
	})
	public String logout(){
		getSession().put("user", null);
		return SUCCESS;
	}
	
	@Action(value="chaeckUserByUrl",results={
			@Result(name="others",location="/member.jsp"),
			@Result(name="self",location="/personal.jsp")
	})
	public String checkUserByUrl(){
		User user=(User) getSession().get("user");
		//如果要查看的用户与此时已登录的用户是同一个人，则跳转至用户的个人资料修改页面
		if(null==user){//在未登录的情况下，都显示该url所指用户的详细信息页
			User checked=userBiz.getUserByMailAddress(mailAddress);
			logger.info("用户未登录：   正在查看用户的详细信息…… 用户邮箱地址为："+mailAddress+"   用户名为："+checked.getUsername());
			getRequest().put("user",checked);
			return "others";
		}
		else if(mailAddress.equals(user.getMailAddress())){//在有人登陆且登陆者就是要查看的人
			getRequest().put("user", (User)getSession().get("user"));
			return "self";
		}
		else {
			User checked=userBiz.getUserByMailAddress(mailAddress);
			logger.info("用户已登录：   正在查看用户的详细信息…… 用户邮箱地址为："+mailAddress+"   用户名为："+checked.getUsername());
			getRequest().put("user",checked);
			return "others";
		}
	}
	
	
}

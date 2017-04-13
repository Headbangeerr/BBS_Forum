package com.bbsforum.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.bbsforum.biz.UserBiz;
import com.bbsforum.entity.User;
import com.opensymphony.xwork2.ActionSupport;

public class UserAction extends BaseAction {

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
	
	
}

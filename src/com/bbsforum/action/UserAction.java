package com.bbsforum.action;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.bbsforum.biz.MessageBiz;
import com.bbsforum.biz.PageViewBiz;
import com.bbsforum.biz.UserBiz;
import com.bbsforum.entity.Message;
import com.bbsforum.entity.PageBean;
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
	
	@Autowired
	MessageBiz messageBiz;
	public MessageBiz getMessageBiz() {
		return messageBiz;
	}
	private PageBean pageBean;
	public PageBean getPageBean() {
		return pageBean;
	}
	private PageBean postBean;
	public PageBean getPostBean() {
		return postBean;
	}
	@Autowired
	private PageViewBiz pageViewBiz;
	public PageViewBiz getPageViewBiz() {
		return pageViewBiz;
	}
	
	@Action(value="chaeckUserByUrl",results={
			@Result(name="others",location="/member.jsp"),
			@Result(name="self",location="/personal.jsp")
	})
	public String checkUserByUrl(){
		User user=(User) getSession().get("user");
		pageBean=new PageBean();
		postBean=new PageBean();
		//如果要查看的用户与此时已登录的用户是同一个人，则跳转至用户的个人资料修改页面
		User check;
		//List<Message> messageList=messageBiz.getMessageByReceiverMail(mailAddress);
		logger.info("mailAddress"+mailAddress);
		pageBean=pageViewBiz.showMessageBypage(1, 4, mailAddress);		
		getRequest().put("pageBean", pageBean);
		if(null==user||!mailAddress.equals(user.getMailAddress())){
			check=userBiz.getUserByMailAddress(mailAddress);
			logger.info("查看其他用户   被查看的用户账号："+mailAddress+"  用户名"+check.getUsername());
			getRequest().put("checkedUser", check);
			postBean=pageViewBiz.showPostBypage(1, 5, mailAddress, check.getPosts().size());
			return "others";
		}
		else{
			logger.info("被查看的用户是登陆者本人："+user.getUsername());
			getRequest().put("checkedUser", user);
			postBean=pageViewBiz.showPostBypage(1, 5, mailAddress, user.getPosts().size());
			pageBean=pageViewBiz.showMessageBypage(1, 5, mailAddress);	
			return "self";
		}
	}
	
}

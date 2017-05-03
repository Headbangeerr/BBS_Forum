package com.bbsforum.action;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.beans.factory.annotation.Autowired;

import com.bbsforum.dao.MessageDao;
import com.bbsforum.dao.UserDao;
import com.bbsforum.entity.Message;
import com.bbsforum.entity.User;

@ParentPackage("json-default")//要使用json必须要依赖于该包
public class MessageAction extends BaseAction {

	private static Logger logger=Logger.getLogger(MessageAction.class);
	
	String receiverMail;
	String content;
	
	@JSON(serialize=false)
	public String getReceiverMail() {
		return receiverMail;
	}
	public void setReceiverMail(String receiverMail) {
		this.receiverMail = receiverMail;
	}
	@JSON(serialize=false)
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
	
	
	@Autowired
	MessageDao messageDao;
	public MessageDao getMessageDao() {
		return messageDao;
	}
	@Autowired
	UserDao userDao;
	public UserDao getUserDao() {
		return userDao;
	}
	
	@Action(value="addMessage",results={
			@Result(name="Success",type="json")
	})
	public String addMessage(){
		boolean flag;
		
		User publishser=(User)getSession().get("user");
		User receiver=userDao.findUserByMailAddress(receiverMail);
		logger.info("正在添加留言……     发布人："+publishser.getUsername()+"   接收人："+receiver.getUsername());
		Message message=new Message();
		message.setContent(content);
		message.setPublisherMail(publishser);
		message.setReciverMail(receiver);
		if(messageDao.addMessage(message)){
			flag=true;
		}
		else{
			flag=false;
		}
		return SUCCESS;
	}
}

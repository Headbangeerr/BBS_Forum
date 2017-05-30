package com.bbsforum.action;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.beans.factory.annotation.Autowired;

import com.bbsforum.biz.MessageBiz;
import com.bbsforum.biz.PageViewBiz;
import com.bbsforum.biz.UserBiz;
import com.bbsforum.dao.MessageDao;
import com.bbsforum.dao.UserDao;
import com.bbsforum.entity.Message;
import com.bbsforum.entity.PageBean;
import com.bbsforum.entity.User;

@ParentPackage("json-default")//要使用json必须要依赖于该包
public class MessageAction extends BaseAction {

	private static Logger logger=Logger.getLogger(MessageAction.class);
	
	private String receiverMail;
	private String content;
	private PageBean pageBean;
	private int page;
	
	@JSON(serialize=false)
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public PageBean getPageBean() {
		return pageBean;
	}
	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}




	List<Message> messages=new ArrayList<Message>();
	public List<Message> getMessages() {
		return messages;
	}
	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}




	boolean flag;
	public boolean getFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	
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
	MessageBiz messageBiz;
	public void setMessageBiz(MessageBiz messageBiz) {
		this.messageBiz = messageBiz;
	}
	
	@Autowired
	UserBiz userBiz;
	public void setUserBiz(UserBiz userBiz) {
		this.userBiz = userBiz;
	}
	
	@Action(value="addMessage",results={
			@Result(name="success",type="json",params={
					"excludeProperties", "messages\\[\\d+\\]\\.publisherMail.posts,"
							+"messages\\[\\d+\\]\\.publisherMail.friends,"
							+"messages\\[\\d+\\]\\.receiverMail.friends,"
							+"messages\\[\\d+\\]\\.receiverMail.posts,"
							+"messages\\[\\d+\\]\\.receiverMail.replys,"
							+"messages\\[\\d+\\]\\.publisherMail.replys"})
	})
	public String addMessage(){
		logger.info("receiverMail:"+receiverMail);
		User publishser=(User)getSession().get("user");
		User receiver=userBiz.getUserByMailAddress(receiverMail);
		logger.info("receiver:"+receiver.getUsername());
		logger.info("content:"+content);
		logger.info("receiverMail:"+receiverMail);
		logger.info("正在添加留言……     发布人："+publishser.getUsername()+"   接收人："+receiver.getUsername());
		Message message=new Message();
		message.setContent(content);
		message.setPublisherMail(publishser);
		message.setReceiverMail(receiver);
		message.setPublishDate(new Timestamp(System.currentTimeMillis()));
		if(messageBiz.addMessage(message)){
			messages=messageBiz.getMessageByReceiverMail(receiverMail);
			logger.info("获取添加留言更新后的留言列表："+messages.size());
			flag=true;
		}
		else{
			flag=false;
		}
		logger.info("flag:"+flag);
		return SUCCESS;
	}
	
	
	@Autowired
	private PageViewBiz pageViewBiz;
	public void setPageViewBiz(PageViewBiz pageViewBiz) {
		this.pageViewBiz = pageViewBiz;
	}
	
	@Action(value="showMessageByPage",results={
			@Result(name="success",type="json",params={
					"excludeProperties", "pageBean.list\\[\\d+\\]\\.publisherMail.posts,"
							+"pageBean.list\\[\\d+\\]\\.publisherMail.friends,"
							+"pageBean.list\\[\\d+\\]\\.publisherMail.replys,"
							+"pageBean.list\\[\\d+\\]\\.receiverMail.friends,"
							+"pageBean.list\\[\\d+\\]\\.receiverMail.replys,"
							+ "pageBean.list\\[\\d+\\]\\.receiverMail.posts"})
	}) 
	public String showMessageByPage(){
		logger.info("page:"+page+"receiverMail:"+receiverMail);
		pageBean=pageViewBiz.showMessageBypage(page, 4, receiverMail);
		logger.info(pageBean.getList().size());
		return SUCCESS;
	}
	
	
}

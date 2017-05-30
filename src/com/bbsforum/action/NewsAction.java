package com.bbsforum.action;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.bbsforum.biz.FriendsBiz;
import com.bbsforum.biz.NewsBiz;
import com.bbsforum.dao.UserDao;
import com.bbsforum.entity.News;
import com.bbsforum.entity.User;


@ParentPackage("json-default")
public class NewsAction extends BaseAction {
	private Logger logger=Logger.getLogger(NewsAction.class);
	
	private String senderMail;
	private String receiverMail;
	private String content;
	private int type;
	private boolean flag;
	private List<News> news;
	private boolean operate;
	private String newsId;
	@Autowired
	NewsBiz newsBiz;
	public void setNewsBiz(NewsBiz newsBiz) {
		this.newsBiz = newsBiz;
	}
	@Autowired
	UserDao userDao;
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	@Autowired
	FriendsBiz friendBiz;
	public void setFriendBiz(FriendsBiz friendBiz) {
		this.friendBiz = friendBiz;
	}
	@Action(value="sendFriendrequest",results={
			@Result(name="success",type="json",params={
					"includeProperties","flag"
			})
	})
	public String sendFriendrequest(){		
		flag=newsBiz.sendMessage(senderMail, receiverMail, "向您发送了好友请求", 1);
		return SUCCESS;
	}
	@Action(value="checkFriRequestExist",results={
			@Result(name="success",type="json",params={
					"includeProperties","flag"
			})
	})
	public String checkFriRequestExist(){
		flag=newsBiz.checkFriRequestExist(senderMail, receiverMail);
		return SUCCESS;
	}

	@Action(value="getFriRequestListByReceiverMail",results={
			@Result(name="success",type="json",params={
					"excludeProperties", "news\\[\\d+\\]\\.senderMail.posts,"
							+"news\\[\\d+\\]\\.senderMail.friends,"
							+"news\\[\\d+\\]\\.receiverMail,"
							+"news\\[\\d+\\]\\.senderMail.replys"})
	})
	public String showFriRequestListByReceiverMail(){
		news=newsBiz.getFriRequestListByReceiverMail(receiverMail);
		return SUCCESS;
	}
	
	@Action(value="handleFriRequest",results={
			@Result(name="success",type="json",params={
					"includeProperties","flag"
			})
	})
	public String handleFriRequest(){
		
		newsBiz.handleFriendRequest(newsId, operate);
		User user=(User) getSession().get("user");
		receiverMail=user.getMailAddress();
		if(operate){
			flag=newsBiz.sendMessage(receiverMail, senderMail,"该用户已经同意了您的好友请求！", 0);			
			friendBiz.addFriends(senderMail, receiverMail);
			friendBiz.addFriends(receiverMail, senderMail);
		}
		else{
			flag=newsBiz.sendMessage(receiverMail, senderMail,"该用户拒绝了您的好友请求！", 0);
		}
		logger.info("用户：["+receiverMail+"] 对来自用户["+senderMail+"]的好友邀请做出了"+operate+"操作");
		return SUCCESS;
	}
	
	
	
	
	
	
	
	public String getNewsId() {
		return newsId;
	}
	public void setNewsId(String newsId) {
		this.newsId = newsId;
	}
	public boolean isOperate() {
		return operate;
	}
	public void setOperate(boolean operate) {
		this.operate = operate;
	}
	public List<News> getNews() {
		return news;
	}
	public void setNews(List<News> news) {
		this.news = news;
	}
	public boolean getFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public String getReceiverMail() {
		return receiverMail;
	}

	public void setReceiverMail(String receiverMail) {
		this.receiverMail = receiverMail;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSenderMail() {
		return senderMail;
	}

	public void setSenderMail(String senderMail) {
		this.senderMail = senderMail;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
}

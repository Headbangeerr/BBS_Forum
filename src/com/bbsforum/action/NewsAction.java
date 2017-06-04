package com.bbsforum.action;

import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.bbsforum.biz.FriendsBiz;
import com.bbsforum.biz.NewsBiz;
import com.bbsforum.biz.PageViewBiz;
import com.bbsforum.dao.NewsDao;
import com.bbsforum.dao.UserDao;
import com.bbsforum.entity.LastestSenderJSON;
import com.bbsforum.entity.News;
import com.bbsforum.entity.PageBean;
import com.bbsforum.entity.User;


@ParentPackage("json-default")
public class NewsAction extends BaseAction {
	private Logger logger=Logger.getLogger(NewsAction.class);
	private PageBean newsBean;
	private String senderMail;
	private String receiverMail;
	private String content;
	private int type;
	private String userMail;
	private String friendMail;
	private boolean flag;
	private News newsTemp;
	private List<News> news;
	private List<LastestSenderJSON> lastestUsers;
	private boolean operate;
	private String newsId;
	private int page;
	private User chatUser;
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
	NewsDao newsDao;
	public void setNewsDao(NewsDao newsDao) {
		this.newsDao = newsDao;
	}
	@Autowired
	FriendsBiz friendBiz;
	public void setFriendBiz(FriendsBiz friendBiz) {
		this.friendBiz = friendBiz;
	}
	@Autowired
	PageViewBiz pageViewBiz;
	public void setPageViewBiz(PageViewBiz pageViewBiz) {
		this.pageViewBiz = pageViewBiz;
	}
	@Action(value="sendFriendrequest",results={
			@Result(name="success",type="json",params={
					"includeProperties","flag"
			})
	})
	public String sendFriendrequest(){		
		flag=newsBiz.sendNews(senderMail, receiverMail, "向您发送了好友请求", 1);
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
			flag=newsBiz.sendNews(receiverMail, senderMail,"该用户已经同意了您的好友请求！", 0);			
			friendBiz.addFriends(senderMail, receiverMail);
			friendBiz.addFriends(receiverMail, senderMail);
		}
		else{
			flag=newsBiz.sendNews(receiverMail, senderMail,"该用户拒绝了您的好友请求！", 0);
		}
		logger.info("用户：["+receiverMail+"] 对来自用户["+senderMail+"]的好友邀请做出了"+operate+"操作");
		return SUCCESS;
	}
	
	@Action(value="showLastestSender",results={
			@Result(name="success",type="json",params={
					"excludeProperties", "lastestUsers\\[\\d+\\]\\.user.posts,"
							+"lastestUsers\\[\\d+\\]\\.user.friends,"						
							+"lastestUsers\\[\\d+\\]\\.user.replys"})
	})
	public String showLastestSender(){
		User user=(User)getSession().get("user");		
		lastestUsers=newsBiz.getLastestSenders(user.getMailAddress());
		return SUCCESS;
	}
	
	@Action(value="showLastestNewsForReceiver",results={
			@Result(name="success",type="json",params={
					"excludeProperties", "newsBean.list\\[\\d+\\]\\.senderMail.posts,"
							+"newsBean.list\\[\\d+\\]\\.senderMail.friends,"
							+"newsBean.list\\[\\d+\\]\\.receiverMail.posts,"
							+"newsBean.list\\[\\d+\\]\\.receiverMail.friends,"
							+"newsBean.list\\[\\d+\\]\\.receiverMail.replys,"
							+"newsBean.list\\[\\d+\\]\\.senderMail.replys"})
	})
	public String showLastestNewsForReceiver(){
		User user=(User)getSession().get("user");	
		newsBean=pageViewBiz.showLastestNews(1, 5, friendMail, user.getMailAddress());
		if(page==0){//在page参数为零时也就是默认情况下显示最后一页的未读消息
			page=newsBean.getTotalPage();
		}
		logger.info("page:"+page);
		newsBean=pageViewBiz.showLastestNews(page, 5, friendMail, user.getMailAddress());	
		logger.info("list.size:"+newsBean.getList().size());
		return SUCCESS;
	}
	
	@Action(value="sendNews",results={
			@Result(name="success",type="json",params={
					"excludeProperties", "newsTemp.receiverMail,"					
							+"newsTemp.senderMail.posts,"
							+"newsTemp.senderMail.friends,"
							+"newsTemp.senderMail.replys"							
						})
	})
	public String sendNews(){
		logger.info("：【"+userMail+"】正在向用户：【"+friendMail+"】发送消息："+content);
		flag=newsBiz.sendNews(userMail, friendMail, content, 0);
		newsTemp=newsBiz.getLastestNewsBySender(userMail);	
		logger.info("发送的最新消息的内容："+newsTemp.getContent());
		return SUCCESS;
	}
	
	@Action(value="checkUnreadNews",results={
			@Result(name="success",type="json",params={
					"includeProperties","flag"
			})
	})
	public String checkUnreadNews(){
		int changeNum=newsBiz.checkReadNews(userMail, friendMail);
		if(changeNum>0){
			flag=true;
		}else{
			flag=false;
		}
		return SUCCESS;
	}
	@Action(value="beginChat",results={
			@Result(name="success",location="/newscentre.jsp"),
			@Result(name="error",location="/pages/unlogin.jsp")
	})
	public String beginChat(){
		User user=(User)getSession().get("user");
		if(user==null){
			return ERROR;
		}
		else{
			userMail=user.getMailAddress();
			logger.info("用户：【"+userMail+"】向用户：【"+friendMail+"】发起私信");
			if(newsBiz.checkFriendInUserLastestSender(friendMail, userMail)){
				chatUser=userDao.findUserByMailAddress(friendMail);
				logger.info("用户：【"+friendMail+"】已存在与用户：【"+userMail+"】的最近联系人列表中");
				//如何私信对象已经存在自己的最近联系人中
				flag=true;
			}
			else{
				chatUser=userDao.findUserByMailAddress(friendMail);
				logger.info("用户：【"+friendMail+"】不在用户：【"+userMail+"】的最近联系人列表中");
				flag=false;
			}
			return SUCCESS;
		}
		
	}
	
	
	
	
	
	

	
	public User getChatUser() {
		return chatUser;
	}
	public void setChatUser(User chatUser) {
		this.chatUser = chatUser;
	}
	public News getNewsTemp() {
		return newsTemp;
	}
	public void setNewsTemp(News newsTemp) {
		this.newsTemp = newsTemp;
	}
	public String getUserMail() {
		return userMail;
	}
	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}
	public String getFriendMail() {
		return friendMail;
	}
	public void setFriendMail(String friendMail) {
		this.friendMail = friendMail;
	}
	public PageBean getNewsBean() {
		return newsBean;
	}
	public void setNewsBean(PageBean newsBean) {
		this.newsBean = newsBean;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public List<LastestSenderJSON> getLastestUsers() {
		return lastestUsers;
	}
	public void setLastestUsers(List<LastestSenderJSON> lastestUsers) {
		this.lastestUsers = lastestUsers;
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
	
	@Action(value="sendZDNews",results={
			@Result(name="success",type="json")
	})
	public String sendZDNews(){
		Timestamp d = new Timestamp(System.currentTimeMillis());
		User publishser=(User)getSession().get("user");
		int i=2;
		List<User> tm=userDao.getUser(i);
		System.out.println(content);
		Iterator itor=tm.iterator();
		while(itor.hasNext()){
			int x=(int)(Math.random()*100);
			String a="b"+x;
			User senduser=(User)itor.next();
			News news=new News();
			news.setId(a);
			news.setSenderMail(publishser);
			news.setReceiverMail(senduser);
			news.setSendDate(d);
			news.setContent("申请置顶，帖子id为："+content);
			news.setType(0);
			news.setState(0);
			newsDao.sendNews(news);
		}
		
			flag=true;
	
		logger.info("flag:"+flag);
		return SUCCESS;
	}
	
}


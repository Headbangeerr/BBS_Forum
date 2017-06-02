<<<<<<< HEAD
package com.bbsforum.bizimpl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.bbsforum.biz.NewsBiz;
import com.bbsforum.dao.NewsDao;
import com.bbsforum.dao.UserDao;
import com.bbsforum.entity.LastestSenderJSON;
import com.bbsforum.entity.News;
import com.bbsforum.entity.User;

public class NewsBizImpl implements NewsBiz {

	private Logger logger=Logger.getLogger(NewsBiz.class);
	@Autowired
	NewsDao newsDao;
	public NewsDao setNewsDao() {
		return newsDao;
	}
	@Autowired
	UserDao userDao;
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	@Override
	public boolean sendMessage(String senderMail, String receiverMail,
			String content, int type) {
		logger.info(" 用户：["+senderMail+"]正在 向   [ "+receiverMail+"]发送好友请求");
		News news=new News();
		User sender=userDao.findUserByMailAddress(senderMail);
		User receiver=userDao.findUserByMailAddress(receiverMail);
		String id=senderMail+System.currentTimeMillis();
		news.setId(id);
		news.setSenderMail(sender);
		news.setReceiverMail(receiver);
		news.setContent(content);
		news.setType(type);
		news.setState(0);		
		return newsDao.sendNews(news);
	}

	@Override
	public boolean handleFriendRequest(String newsId, boolean operate) {
		
		return newsDao.handleFriendRequest(newsId, operate);
	}

	@Override
	public boolean checkFriRequestExist(String senderMail, String receiverMail) {
		
		return newsDao.checkFriRequestExist(senderMail, receiverMail);
	}

	@Override
	public List<News> getFriRequestListByReceiverMail(String reciverMail) {
		logger.info("用户邮箱： ["+reciverMail+"]正在查看自己的好友请求列表");
		return newsDao.getFriRequestListByReceiverMail(reciverMail);
	}

	@Override
	public List<LastestSenderJSON> getLastestSenders(String receiverMail) {
		logger.info("用户:【"+receiverMail+"】正在获取最近联系人列表…………");
		List<User> lastestSenders=newsDao.getLastestSender(receiverMail);		
		List<LastestSenderJSON> lastestSendersJson=new ArrayList<LastestSenderJSON>();
		logger.info("已经获取到最近联系人数："+lastestSenders.size());
		for (User sender : lastestSenders) {
			LastestSenderJSON jsonTemp=new LastestSenderJSON();
			jsonTemp.setUser(sender);
			logger.info("联系人邮箱地址为："+jsonTemp.getUser().getMailAddress());
			jsonTemp.setUnread(newsDao.getUnreadSumBySender(sender.getMailAddress(),receiverMail));
			lastestSendersJson.add(jsonTemp);
		}
		return lastestSendersJson;
	}

	
}
=======
package com.bbsforum.bizimpl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.bbsforum.biz.NewsBiz;
import com.bbsforum.dao.NewsDao;
import com.bbsforum.dao.UserDao;
import com.bbsforum.entity.LastestSenderJSON;
import com.bbsforum.entity.News;
import com.bbsforum.entity.User;

public class NewsBizImpl implements NewsBiz {

	private Logger logger=Logger.getLogger(NewsBiz.class);
	@Autowired
	NewsDao newsDao;
	public NewsDao setNewsDao() {
		return newsDao;
	}
	@Autowired
	UserDao userDao;
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	@Override
	public boolean sendNews(String senderMail, String receiverMail,
			String content, int type) {
		logger.info(" 用户：["+senderMail+"]正在 向   [ "+receiverMail+"]发送消息");
		News news=new News();
		User sender=userDao.findUserByMailAddress(senderMail);
		User receiver=userDao.findUserByMailAddress(receiverMail);
		String id=senderMail+System.currentTimeMillis();
		news.setId(id);
		news.setSenderMail(sender);
		news.setReceiverMail(receiver);
		news.setContent(content);
		news.setType(type);
		news.setState(0);		
		return newsDao.sendNews(news);
	}

	@Override
	public boolean handleFriendRequest(String newsId, boolean operate) {
		
		return newsDao.handleFriendRequest(newsId, operate);
	}

	@Override
	public boolean checkFriRequestExist(String senderMail, String receiverMail) {
		
		return newsDao.checkFriRequestExist(senderMail, receiverMail);
	}

	@Override
	public List<News> getFriRequestListByReceiverMail(String reciverMail) {
		logger.info("用户邮箱： ["+reciverMail+"]正在查看自己的好友请求列表");
		return newsDao.getFriRequestListByReceiverMail(reciverMail);
	}

	@Override
	public List<LastestSenderJSON> getLastestSenders(String receiverMail) {
		logger.info("用户:【"+receiverMail+"】正在获取最近联系人列表…………");
		List<User> lastestSenders=newsDao.getLastestSender(receiverMail);		
		List<LastestSenderJSON> lastestSendersJson=new ArrayList<LastestSenderJSON>();
		logger.info("已经获取到最近联系人数："+lastestSenders.size());
		for (User sender : lastestSenders) {
			LastestSenderJSON jsonTemp=new LastestSenderJSON();
			jsonTemp.setUser(sender);
			logger.info("联系人邮箱地址为："+jsonTemp.getUser().getMailAddress());
			jsonTemp.setUnread(newsDao.getUnreadSumBySender(sender.getMailAddress(),receiverMail));
			lastestSendersJson.add(jsonTemp);
		}
		return lastestSendersJson;
	}

	
}
>>>>>>> Headbangeerr/master

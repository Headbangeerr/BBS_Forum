package com.bbsforum.test;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bbsforum.biz.NewsBiz;
import com.bbsforum.biz.PageViewBiz;
import com.bbsforum.biz.SearchBiz;
import com.bbsforum.biz.UserBiz;
import com.bbsforum.dao.NewsDao;
import com.bbsforum.dao.UserDao;
import com.bbsforum.entity.LastestSenderJSON;
import com.bbsforum.entity.News;
import com.bbsforum.entity.PageBean;
import com.bbsforum.entity.User;

public class testNews {
	private static Logger logger = Logger.getLogger(testNews.class);
	public static void main(String[] args) {
		ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
		NewsDao newsDao =(NewsDao) context.getBean("newsDao");
		UserDao userDao=(UserDao) context.getBean("userDao");
		NewsBiz newsBiz=(NewsBiz)context.getBean("newsBiz");
		PageViewBiz pageBiz=(PageViewBiz) context.getBean("pageViewBiz");
//		News temp=new News();
//		User sender=userDao.findUserByMailAddress("0000");
//		User receiver=userDao.findUserByMailAddress("1111");
//		String id;
//		id=sender.getMailAddress()+System.currentTimeMillis();
//		logger.info(id);
//		temp.setId(id);
//		temp.setSenderMail(sender);
//		temp.setReceiverMail(receiver);
//		temp.setContent("ƒ„∫√Õ€");
//		temp.setState(0);
//		temp.setType(0);
//		newsDao.sendNews(temp);
		//newsDao.handleFriendRequest("00001496060286081", true);
		//logger.info(newsDao.getUnreadSumBySender("0000", "1111"));
//		List<LastestSenderJSON> list=newsBiz.getLastestSenders("1111");
//		logger.info("333");
//		logger.info(list.size());
//		for (LastestSenderJSON lastestSenderJSON : list) {
//			logger.info(lastestSenderJSON.getUser().getMailAddress()+"     "+lastestSenderJSON.getUnread());
//		}
		PageBean pagebean=pageBiz.showLastestNews(1, 5, "0000","1111");
		List<News> news=pagebean.getList();
		for (News news2 : news) {
			logger.info(news2.getId());
		}
		
		
	}
}

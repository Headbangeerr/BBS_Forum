package com.bbsforum.test;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bbsforum.biz.SearchBiz;
import com.bbsforum.dao.NewsDao;
import com.bbsforum.dao.UserDao;
import com.bbsforum.entity.News;
import com.bbsforum.entity.User;

public class testNews {
	private static Logger logger = Logger.getLogger(test.class);
	public static void main(String[] args) {
		ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
		NewsDao newsDao =(NewsDao) context.getBean("newsDao");
		UserDao userDao=(UserDao) context.getBean("userDao");
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
		newsDao.handleFriendRequest("00001496060286081", true);
		
	}
}

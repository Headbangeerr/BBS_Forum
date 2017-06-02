package com.bbsforum.test;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bbsforum.dao.MessageDao;
import com.bbsforum.dao.NewsDao;
import com.bbsforum.dao.UserDao;
import com.bbsforum.entity.Message;
import com.bbsforum.entity.User;

public class testMessage {
	private static Logger logger = Logger.getLogger(test.class);
	public static void main(String[] args) {
		ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
		MessageDao mesDao =(MessageDao) context.getBean("messageDao");
//		UserDao userDao=(UserDao) context.getBean("userDao");
//		User sender=userDao.findUserByMailAddress("0000");
//		User receiver=userDao.findUserByMailAddress("1111");
//		Message message=new Message();
//		message.setPublisherMail(sender);
//		message.setReceiverMail(receiver);
//		message.setContent("nihaowa");
//		mesDao.addMessage(message);
		
	}
}

package com.bbsforum.test;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bbsforum.dao.NewsDao;
import com.bbsforum.dao.UserDao;

public class testUser {
	static private Logger logger=Logger.getLogger(testUser.class);
	public static void main(String[] args) {
		ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
		UserDao userDao=(UserDao) context.getBean("userDao");
//		logger.info(userDao.checkShieldForUser("0000", "5555"));
		logger.info(userDao.addShieldUser("0000", "5555"));
	}
}

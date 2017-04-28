package com.bbsforum.daoimpl;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.bbsforum.dao.UserDao;
import com.bbsforum.entity.User;

public class UserDaoImpl implements UserDao {

	private Logger logger=LoggerFactory.getLogger(UserDaoImpl.class);
	@Autowired
	SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	Session session;
	@Override
	public User findUserByMailAddress(String mailAddress) {
		logger.info("mailAddress:"+mailAddress);
		System.out.println("sessionFactory:"+sessionFactory);
		session=sessionFactory.openSession();
		User user=(User) session.get(User.class,mailAddress);
		session.close();
		return user;
	}
	

}

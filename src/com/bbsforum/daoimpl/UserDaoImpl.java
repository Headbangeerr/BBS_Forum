package com.bbsforum.daoimpl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.apache.log4j.*;
import org.springframework.beans.factory.annotation.Autowired;

import com.bbsforum.dao.UserDao;
import com.bbsforum.entity.User;

public class UserDaoImpl implements UserDao {

	private static Logger logger=Logger.getLogger(UserDaoImpl.class);
	@Autowired
	SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	Session session;
	@Override
	public User findUserByMailAddress(String mailAddress) {
		//logger.info("mailAddress:"+mailAddress);
		session=sessionFactory.openSession();
		User user=(User) session.get(User.class,mailAddress);
		session.close();
		return user;
	}
	

}

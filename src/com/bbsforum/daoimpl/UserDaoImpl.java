package com.bbsforum.daoimpl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.bbsforum.dao.UserDAO;
import com.bbsforum.entity.User;

public class UserDaoImpl implements UserDAO {

	SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	Session session;
	@Override
	public User findUserByMailAddress(String mailAddress) {
		session=sessionFactory.openSession();
		User user=(User) session.get(User.class,mailAddress);
		return user;
	}
	

}

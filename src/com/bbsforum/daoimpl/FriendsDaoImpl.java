package com.bbsforum.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.bbsforum.dao.FriendsDao;
import com.bbsforum.entity.User;

public class FriendsDaoImpl implements FriendsDao {
	private static Logger logger=Logger.getLogger(MessageDaoImpl.class);
	@Autowired
	SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	Session session;
	@Override
	public boolean addFriends(String userMail, User friend) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List getFriendList(String userMail,int offset,int pageSize) {
		session=sessionFactory.openSession();
		String sql="select * from user where mail_address in"
				+ " (select friend_mail from friends where user_mail=? )";
		Query query=session.createSQLQuery(sql)
				.addEntity(User.class);
		query.setString(0, userMail);
		query.setFirstResult(offset);
		query.setMaxResults(pageSize);
		List<User> friendList=new ArrayList<User>();
		friendList=query.list();
		return friendList;
	}
	@Override
	public boolean deleteFriend(String userMail, User friend) {
		// TODO Auto-generated method stub
		return false;
	}
}

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
	public boolean addFriends(String userMail, String friendMail) {
		session=sessionFactory.openSession();
		String sql="insert into friends(user_mail,friend_mail) values(?,?)";
		Query query=session.createSQLQuery(sql);
		query.setString(0, userMail);
		query.setString(1, friendMail);
		if(query.executeUpdate()>0){
			session.close();
			return true;
		}else{
			session.close();
			return false;
		}
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
		session.close();
		return friendList;
	}
	@Override
	public boolean deleteFriend(String userMail, String friendMail) {		
		session=sessionFactory.openSession();		
		String sql="delete from friends where user_mail=? and friend_mail=?";
		Query query=session.createSQLQuery(sql);
		query.setString(0, userMail);
		query.setString(1, friendMail);
		if(query.executeUpdate()>0){
			session.close();
			return true;
		}
		else{
			session.close();
			return false;
		}
	}

	@Override
	public boolean checkFriend(String userMail, String friendMail) {
		session=sessionFactory.openSession();
		String sql="select * from friends where user_mail=? and friend_mail=?";
		Query query=session.createSQLQuery(sql);
		query.setString(0, userMail);
		query.setString(1,friendMail);
		if(query.list().size()>0){
			session.close();
			return true;
		}else{
			session.close();
			return false;
		}
		
	}
}

package com.bbsforum.daoimpl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.bbsforum.dao.MessageDao;
import com.bbsforum.entity.Message;
import com.bbsforum.entity.Post;
import com.bbsforum.entity.User;

public class MessageDaoImpl implements MessageDao {
	private static Logger logger=Logger.getLogger(MessageDaoImpl.class);
	@Autowired
	SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	Session session;
	@Override
	public List<Message> getMessageByReceiverMail(String ReceiverMail) {
		session=sessionFactory.openSession();
		String hql="from Message m where reciverMail =? order by m.publishDate desc ";//Oder by要放在条件的后面！！！
		Query query=session.createQuery(hql);
		query.setString(0, ReceiverMail);
		List<Message> list = query.list();
		session.close();
		return list;
	}
	@Override
	public boolean addMessage(Message message) {
		session=sessionFactory.openSession();
		try {
			session.save(message);
			session.close();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			session.close();
			return false;
		}
		
	}

}

package com.bbsforum.daoimpl;


import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import com.bbsforum.dao.NewsDao;
import com.bbsforum.entity.News;
import com.bbsforum.entity.User;

public class NewsDaoImpl implements NewsDao {
	private static Logger logger=Logger.getLogger(MessageDaoImpl.class);
	@Autowired
	SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	Session session;
	Transaction tr;
	@Override
	public boolean sendNews(News news) {
		session=sessionFactory.openSession();	
		tr=session.beginTransaction();
		try{
			session.save(news);
			tr.commit();
			session.close();
			return true;
		}catch (Exception e){
			e.printStackTrace();
			session.close();
			return false;
		}
		
	}
	@Override
	public boolean handleFriendRequest(String newsId,boolean operate) {
		// TODO Auto-generated method stub
		session=sessionFactory.openSession();
		tr=session.beginTransaction();
		News news=(News) session.get(News.class, newsId);
		if(operate){//如果接受了好友请求
			news.setState(1);
		}else{//拒绝了好友请求
			news.setState(2);
		}
		
		try{
			session.update(news);
			tr.commit();
			session.close();
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			session.close();
			return false;
		}
		
	}
	@Override
	public boolean checkFriRequestExist(String senderMail, String receiverMail) {
		session=sessionFactory.openSession();
		String sql="select* from news where sender_mail=? and receiver_mail=? and type=1";
		Query query=session.createSQLQuery(sql);
		query.setString(0, senderMail);
		query.setString(1, receiverMail);
		if(query.list().size()>0){
			session.close();
			return true;
		}
		else{
			session.close();
			return false;
		}
		
	}
	@Override
	public List<News> getFriRequestListByReceiverMail(String reciverMail) {
		session=sessionFactory.openSession();
		String hql="from News where receiverMail=? and type=1 and state=0";
		Query query=session.createQuery(hql);
		query.setString(0, reciverMail);
		List<News> news=query.list();
		session.close();
		return news;
	}

	@Override
	public List<User> getLastestSender(String userMail) {
		session=sessionFactory.openSession();
		List<User> users=new ArrayList<User>();
		String sql="select * from user where mail_address in (select sender_mail  from news where timestampdiff(day,send_date,CURRENT_TIMESTAMP) < 2 and"
				+ " (sender_mail=:userMail or receiver_mail=:userMail) and type=0  union "
			+"select  receiver_mail from news where timestampdiff(day,send_date,CURRENT_TIMESTAMP) < 2 and"
			+ " (sender_mail=:userMail or receiver_mail=:userMail) and type=0) and  mail_address != :userMail";
		Query query=session.createSQLQuery(sql)
				.addEntity(User.class);
		query.setString("userMail", userMail);		
		users=query.list();
		session.close();
		return users;
	}
	@Override
	public int getUnreadSumBySender(String senderMail, String receiverMail) {
		session=sessionFactory.openSession();
		String hql="select count(*) from News where senderMail=? and receiverMail=? and type=0 and state=0";
		Query query=session.createQuery(hql);
		query.setString(0, senderMail);
		query.setString(1, receiverMail);
		Long sum=(Long) query.uniqueResult();
		int unreadSum=Integer.valueOf(sum.toString());
		session.close();
		return unreadSum;
	}
	@Override
	public List<News> getLastestNewsForReceiver(String senderMail,String receiverMail,
			int offset, int pageSize) {
		session=sessionFactory.openSession();
		String hql="from News where ((receiverMail =:senderMail and senderMail=:receiverMail) or (receiverMail=:receiverMail and senderMail=:senderMail))"
				+ "and type=:type order by sendDate asc";
		Query query=session.createQuery(hql);
		query.setString("senderMail", senderMail);
		query.setString("receiverMail",receiverMail);
		query.setInteger("type", 0);
		query.setFirstResult(offset);
		query.setMaxResults(pageSize);
		List<News> news=query.list();
		session.close();
		return news;
	}
	@Override
	public int getSumNewsForReceiver(String senderMail,String receiverMail) {
		session=sessionFactory.openSession();
		String hql="select count(*) from News where ((receiverMail =:senderMail and senderMail=:receiverMail) or "
				+ "(receiverMail=:receiverMail and senderMail=:senderMail)) and type=:type";
		Query query=session.createQuery(hql);
		query.setString("senderMail", senderMail);
		query.setString("receiverMail", receiverMail);
		query.setInteger("type", 0);
		Long sum=(Long) query.uniqueResult();
		int newsSum=Integer.valueOf(sum.toString());
		session.close();
		return newsSum;
	}
}


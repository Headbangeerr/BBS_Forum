package com.bbsforum.daoimpl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.apache.log4j.*;
import org.springframework.beans.factory.annotation.Autowired;

import com.bbsforum.dao.UserDao;
import com.bbsforum.entity.Post;
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
//		session=sessionFactory.openSession();
//		User user=(User) session.get(User.class,mailAddress);
//		logger.info("posts.size:"+user.getPosts().size());
//		session.close();	
//		return user;
		session=sessionFactory.openSession();
		User user=(User) session.get(User.class,mailAddress);
		if(user==null){
			return user;
		}else{
			logger.info("posts.size:"+user.getPosts().size());
			session.close();
			return user;
		}
	}
	@Override
	public List getAllUserList(int offset, int pageSize) {
		// TODO Auto-generated method stub
		session=sessionFactory.openSession();
		List<User> UserPage=new ArrayList<User>();
		String hql="from User";
		Query query=session.createQuery(hql);
		query.setFirstResult(offset);
		query.setMaxResults(pageSize);
		UserPage=query.list();
		session.close();
		return UserPage;
	}
	@Override
	public List getAllUserList1(int i) {
		// TODO Auto-generated method stub
		session=sessionFactory.openSession();
		List<User> UserPage=new ArrayList<User>();
		String hql="from User";
		Query query=session.createQuery(hql);
		UserPage=query.list();
		session.close();
		return UserPage;
	}
	@Override
	public boolean updaUser(User user) {
		// TODO Auto-generated method stub
		session=sessionFactory.openSession();
		Transaction tran = this.session.beginTransaction();
		try {
			session.update(user);
			tran.commit();
			//System.out.println("更新成功！！！！！！！！");
			session.close();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			session.close();
			//System.out.println("更新失败！！！！！！");
			return false;
		}
	}
	@Override
	public List getUser(int i) {
		// TODO Auto-generated method stub
		session=sessionFactory.openSession();
		List<User> UserPage=new ArrayList<User>();
		String hql="from User where type=1";
		Query query=session.createQuery(hql);
		UserPage=query.list();
		session.close();
		return UserPage;
	}
	//添加数据库记录
	public boolean addUser(User user) {
		Transaction tx=null;
		//获取session
		session=sessionFactory.openSession();
		try{
			tx=session.beginTransaction();
			session.save(user);
			tx.commit();
			return true;
		}catch(Exception e){
			if(tx!=null){
				tx.rollback();
			}
			e.printStackTrace();
			return false;
		}
	}
	//修改数据库内容
	public boolean UpdateUser(User user) {
		Transaction tx=null;	//声明事务
		session=sessionFactory.openSession();		//获取session
		try{
			tx=session.beginTransaction();
			session.update(user);
			tx.commit();
			return true;
		}catch(Exception e){
			if(tx!=null){
				tx.rollback();
			}
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public List<User> showUserByDate(Date lastLoginDate) {
		session=sessionFactory.openSession();
		System.out.println("日期"+lastLoginDate);
		String hql="from User user where user.lastLoginDate<:lastLoginDate and user.status<:status and user.type=:type";
		Query query=session.createQuery(hql);		
		query.setDate("lastLoginDate", lastLoginDate);
		query.setInteger("status", 3);
		query.setInteger("type", 0);
		List<User> list = query.list();
		session.close();
		return list;
	}
	@Override
	public List<User> getOutdateUserForPage(int offset, int pageSize,
			Date lastLoginDate) {
			session=sessionFactory.openSession();
			List<User> userPage=new ArrayList<User>();
			Query query=session.createQuery("from User user where user.lastLoginDate<? and user.status<? and user.type=?");
			query.setDate(0, lastLoginDate);
			query.setInteger(1, 3);
			query.setInteger(2, 0);
			query.setFirstResult(offset);
			query.setMaxResults(pageSize);
			userPage=query.list();
			session.close();
			return userPage;
		
	}
	@Override
	public boolean deleteUserByMailAddress(String mailAddress) {
		    session=sessionFactory.openSession();
		    Transaction tx=session.beginTransaction();
			User user=(User) session.get(User.class,mailAddress);
			user.setStatus(3);
			   try{
			session.update(user);
		    tx.commit();
			session.close();
			return true;
		 }catch(Exception e){
				   session.close();
				   System.out.println("失败");
				   return false;
			   }
	}
	@Override
	public boolean silenceUserByMailAddress(String mailAddress) {
	    session=sessionFactory.openSession();
	    Transaction tx=session.beginTransaction();
		User user=(User) session.get(User.class,mailAddress);
		user.setStatus(2);
	   try{
		session.update(user);
	    tx.commit();
		session.close();
		return true;
	   }catch(Exception e){
		   session.close();
		   System.out.println("失败");
		   return false;
	   }
	}
	@Override
	public boolean NonsilenceUserByMailAddress(String mailAddress) {
	    session=sessionFactory.openSession();
	    Transaction tx=session.beginTransaction();
		User user=(User) session.get(User.class,mailAddress);
		user.setStatus(0);
		   try{
		session.update(user);
	    tx.commit();
		session.close();
		return true;
		   }catch(Exception e){
			   session.close();
			   System.out.println("失败");
			   return false;
		   }
	}
	@Override
	public void updateUserLastLoginDate(User user) {
		Session session=sessionFactory.openSession();
	    Transaction tx=session.beginTransaction();
		session.update(user);
	    tx.commit();
		session.close();
	}
		
	@Override
	public boolean checkShieldForUser(String userMail, String shieldMail) {
		session=sessionFactory.openSession();
		String sql="select * from  shield where user_mail=? and shielder_mail=?";
		Query query=session.createSQLQuery(sql);
		query.setString(0, userMail);
		query.setString(1, shieldMail);
		List list=query.list();
		session.close();
		if(list.size()>0){
			return true;
		}else{
			return false;
		}	
	}
	@Override
	public boolean addShieldUser(String userMail, String shieldMail) {
		session=sessionFactory.openSession();
		String sql="insert into shield(user_mail,shielder_mail) values(?,?)";
		Query query=session.createSQLQuery(sql);
		query.setString(0, userMail);
		query.setString(1, shieldMail);
		if(query.executeUpdate()>0){
			session.close();
			return true;
		}else{
			session.close();
			return false;
		}
	}
	@Override
	public boolean deleShieldUser(String userMail, String shieldMail) {
		session=sessionFactory.openSession();		
		String sql="delete from shield where user_mail=? and shielder_mail=?";
		Query query=session.createSQLQuery(sql);
		query.setString(0, userMail);
		query.setString(1, shieldMail);
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
	public List<User> getShieldMail(int i) {
		// TODO Auto-generated method stub
		session=sessionFactory.openSession();
		String sql="select * from user where mail_Address in (select shielder_mail from shield)";
		Query query=session.createSQLQuery(sql)
				.addEntity(User.class);
		List<User> shield=query.list();
		session.close();
		return shield;
	}
}

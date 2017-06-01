package com.bbsforum.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.bbsforum.dao.ReplyDao;
import com.bbsforum.entity.Reply;

public class ReplyDaoImpl implements ReplyDao {
	@Autowired
	SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	private Session session;

	@Override
	public boolean addReply(Reply reply) {
		// TODO Auto-generated method stub
		session=sessionFactory.openSession();
		//Transaction tran = this.session.beginTransaction();
		try {
			session.save(reply);
			//tran.commit();
			System.out.println("³É¹¦£¡£¡£¡£¡£¡£¡£¡£¡");
			session.close();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			session.close();
			System.out.println("Ê§°Ü£¡£¡£¡£¡£¡£¡");
			return false;
		}
		
	}
	@Override
	public List<Reply> getReplyListForPage(int offset, int PageSize,String pid) {
		session=sessionFactory.openSession();
		List<Reply> ReplyPage=new ArrayList<Reply>();
		String hql="select r from Reply r where r.postId=?";
		Query query=session.createQuery(hql);
		query.setString(0, pid);
		query.setFirstResult(offset);
		query.setMaxResults(PageSize);
		ReplyPage=query.list();
		session.close();
		return ReplyPage;
	}
	@Override
	public List<Reply> getReplyListForPage1(String pid) {
		session=sessionFactory.openSession();
		List<Reply> ReplyPage=new ArrayList<Reply>();
		String hql="select r from Reply r where r.postId=?";
		Query query=session.createQuery(hql);
		query.setString(0, pid);
		ReplyPage=query.list();
//		Iterator itor=PostPage.iterator();
//		while(itor.hasNext()){
//			Post book=(Post)itor.next();
//			System.out.println(book.getTitle()+"  "+book.getId());
//		}
		session.close();
		return ReplyPage;
	}
}

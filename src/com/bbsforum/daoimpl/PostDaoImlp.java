package com.bbsforum.daoimpl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.bbsforum.dao.PostDao;
import com.bbsforum.entity.Childboard;
import com.bbsforum.entity.Message;
import com.bbsforum.entity.Post;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
public class PostDaoImlp implements PostDao {

	@Autowired
	SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	private Session session;

	@Override
	public List<Post> getLastestPostList(int pageIndex, int pageSize) {
		session=sessionFactory.openSession();
		String hql="from Post p order by p.publishTime desc";
		Query query=session.createQuery(hql);
		int startIndex = (pageIndex -1) * pageSize;
		query.setFirstResult(startIndex);
		query.setMaxResults(pageSize);
		List<Post> list = query.list();
		session.flush();
		session.close();
		return list;
	}
	@Override
	public List<Post> getPostListForPage(int offset, int PageSize,
			String PublisherMail) {
		session=sessionFactory.openSession();
		List<Post> PostPage=new ArrayList<Post>();
		Query query=session.createQuery("from Post where publisherMail =?");
		query.setString(0, PublisherMail);
		query.setFirstResult(offset);
		query.setMaxResults(PageSize);
		PostPage=query.list();
		session.close();
		return PostPage;		
	}
	@Override
	public List<Post> getChoosePostListForPage(int offset, int PageSize,int bid) {
		session=sessionFactory.openSession();
		List<Post> PostPage=new ArrayList<Post>();
		String hql="select p from Post p where p.childboardId.parentBoard.id=?";
		Query query=session.createQuery(hql);
		query.setInteger(0, bid);
		query.setFirstResult(offset);
		query.setMaxResults(PageSize);
		PostPage=query.list();
		session.close();
		return PostPage;
	}
	@Override
	public List<Post> getChoosePostListForPage1(int bid) {
		session=sessionFactory.openSession();
		List<Post> PostPage=new ArrayList<Post>();
		String hql="select p from Post p where p.childboardId.parentBoard.id=?";
		Query query=session.createQuery(hql);
		query.setInteger(0, bid);
		PostPage=query.list();
//		Iterator itor=PostPage.iterator();
//		while(itor.hasNext()){
//			Post book=(Post)itor.next();
//			System.out.println(book.getTitle()+"  "+book.getId());
//		}
		session.close();
		return PostPage;
	}
	@Override
	public boolean addPost(Post post) {
		// TODO Auto-generated method stub
		session=sessionFactory.openSession();
		Transaction tran = this.session.beginTransaction();
		try {
			session.save(post);
			tran.commit();
			System.out.println("成功！！！！！！！！");
			session.close();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			session.close();
			System.out.println("失败！！！！！！");
			return false;
		}
		
	}
	@Override
	public Post getPost(String id) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();
		Post post=(Post)session.get(Post.class, id);
		session.close();
		return post;
	}
	@Override
	public List<Post> getAllPostListForPage(int offset, int PageSize) {
		// TODO Auto-generated method stub
		session=sessionFactory.openSession();
		List<Post> PostPage=new ArrayList<Post>();
		String hql="from Post";
		Query query=session.createQuery(hql);
		query.setFirstResult(offset);
		query.setMaxResults(PageSize);
		PostPage=query.list();
		session.flush();
		session.close();
		return PostPage;

	}
	@Override
	public List getAllPostList(int i) {
		// TODO Auto-generated method stub
		Session  session=sessionFactory.openSession();
		String hql="from Post";
		Query query=session.createQuery(hql);
		List postList=query.list();
		session.flush();
		session.close();
		return postList;

	}
	@Override
	public List<Post> getViePostListForPage(int offset, int PageSize) {
		// TODO Auto-generated method stub
		session=sessionFactory.openSession();
		List<Post> PostPage=new ArrayList<Post>();
		String hql="select c from Post c where c.pageView>20";
		Query query=session.createQuery(hql);
		query.setFirstResult(offset);
		query.setMaxResults(PageSize);
		PostPage=query.list();
		//session.flush();
		session.close();
		return PostPage;
	}
	@Override
	public List getViePostList(int i) {
		// TODO Auto-generated method stub
		Session  session=sessionFactory.openSession();
		String hql="select c from Post c where c.pageView>20";
		Query query=session.createQuery(hql);
		List postList=query.list();
		session.flush();
		session.close();
		return postList;
	}
	@Override
	public List<Post> getJHPost(int i) {
		// TODO Auto-generated method stub
		Session  session=sessionFactory.openSession();
		String hql="from Post order by pageView desc";
		Query query=session.createQuery(hql);
		query.setFirstResult(0);
		query.setMaxResults(3);
		List postList=query.list();
//		Iterator itor=postList.iterator();
//		while(itor.hasNext()){
//			Post book=(Post)itor.next();
//			System.out.println(book.getTitle()+"  "+book.getId());
//		}
		session.flush();
		session.close();
		return postList;
	}
	@Override
	public boolean updaPost(Post post) {
		// TODO Auto-generated method stub
		session=sessionFactory.openSession();
		Transaction tran = this.session.beginTransaction();
		try {
			session.update(post);
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
	public List<Post> getZiPostListForPage(int offset, int PageSize, int cid) {
		// TODO Auto-generated method stub
		session=sessionFactory.openSession();
		List<Post> PostPage=new ArrayList<Post>();
		String hql="select p from Post p where p.childboardId.id=?";
		Query query=session.createQuery(hql);
		query.setInteger(0, cid);
		query.setFirstResult(offset);
		query.setMaxResults(PageSize);
		PostPage=query.list();
		session.close();
		return PostPage;
	}
	@Override
	public List<Post> getZiPostListForPage1(int cid) {
		// TODO Auto-generated method stub
		session=sessionFactory.openSession();
		List<Post> PostPage=new ArrayList<Post>();
		String hql="select p from Post p where p.childboardId.id=?";
		Query query=session.createQuery(hql);
		query.setInteger(0, cid);
		PostPage=query.list();
		session.close();
		return PostPage;
	}



}

package com.bbsforum.daoimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.bbsforum.dao.PostDao;
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
	

}

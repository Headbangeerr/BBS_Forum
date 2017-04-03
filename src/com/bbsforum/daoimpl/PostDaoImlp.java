package com.bbsforum.daoimpl;

import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.bbsforum.dao.PostDao;
import com.bbsforum.entity.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class PostDaoImlp implements PostDao {

	
	SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	@Override
	public List<Post> getLastestPostList(int pageIndex, int pageSize) {
		Session  session=sessionFactory.openSession();
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
	

}
